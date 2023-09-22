package com.fluxemail.application.web.auth;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fluxemail.application.security.JwtService;
import com.fluxemail.application.security.OwnUserDetails;
import com.fluxemail.application.security.data.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
@RequiredArgsConstructor
public class AuthenticationService {

    private final UserRepository userRepository;
    private final TokenRepository tokenRepository;

    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;
    public AuthenticationResponse register(RegisterRequest request) {

        if (userRepository.findByEmail(request.getEmail()).isPresent())
            throw new RuntimeException("User already exist");

        var user = User.builder()
                .fullName(request.getFullName())
                .email(request.getEmail())
                .password( passwordEncoder.encode( request.getPassword()))
                .role(Role.USER)
                .isActive(true)
                .build();

        var createdUser = userRepository.save(user);
        OwnUserDetails userDetails = OwnUserDetails.fromUser( createdUser );
        var jwtToken = jwtService.generateToken(userDetails);
        saveUserToken(jwtToken, createdUser);
        var refreshToken = jwtService.generateRefreshToken(userDetails);


        return AuthenticationResponse
                .builder()
                .accessToken(jwtToken)
                .refreshToken(refreshToken)
                .build();
    }

    private void saveUserToken(String jwtToken, User createdUser) {

        var accessToken= Token.builder()
                .token(jwtToken)
                .tokenType(TokenType.ACCESS)
                .expired(false)
                .revoked(false)
                .user(createdUser)
                .build();

        tokenRepository.save(accessToken);

    }

    public AuthenticationResponse authenticate(AuthenticationRequest request) {

//        try {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getEmail(),
                        request.getPassword()
                )
        );
//        }catch (Exception e){
//            System.out.println("*************************");
//            System.out.println( e );
//            System.out.println("**************************");
//            throw e;
//        }

        var user = userRepository
                .findByEmail(request.getEmail())
                .orElseThrow( () -> new RuntimeException("User not found")  );

        OwnUserDetails userDetails = OwnUserDetails.fromUser( user );
        var jwtToken = jwtService.generateToken( userDetails );
        revokeAllUserTokens(user);
        saveUserToken( jwtToken , user );
        var refreshToken = jwtService.generateRefreshToken(userDetails);

        return AuthenticationResponse
                .builder()
                .accessToken(jwtToken)
                .refreshToken(refreshToken)
                .build();
    }

    public void refreshToken(
            HttpServletRequest request,
            HttpServletResponse response
    ) throws IOException {

        final String authHeader = request.getHeader(HttpHeaders.AUTHORIZATION);
        final String refreshToken;
        final String userEmail;

        if( authHeader == null || !authHeader.startsWith("Bearer ") ){
            return;
        }
        refreshToken = authHeader.substring(7);
        userEmail = jwtService.extractUserEmail(refreshToken);

        if( userEmail != null ){

            var user = this.userRepository
                    .findByEmail(userEmail)
                    .orElseThrow(() -> new RuntimeException("User not found"));
            var userDetails = OwnUserDetails.fromUser(user) ;
            if (jwtService.isTokenValid(refreshToken , userDetails )){
              var accessToken = jwtService.generateToken(userDetails);
              revokeAllUserTokens(user);
              saveUserToken( accessToken , user );
              var authResponse = AuthenticationResponse
                      .builder()
                      .accessToken(accessToken)
                      .refreshToken(refreshToken)
                      .build();
                response.setHeader("Content-Type" , "application/json");
              new ObjectMapper().writeValue( response.getOutputStream() , authResponse);
            }
        }
    }

    private void revokeAllUserTokens(User user){

        var validTokens = tokenRepository.findAllValidTokensByUser(user.getId());
        if(validTokens.isEmpty())
            return;
        validTokens.forEach(token ->{
                token.setExpired(true);
                token.setRevoked(true);
        });

        tokenRepository.saveAll(validTokens);


    }
}
