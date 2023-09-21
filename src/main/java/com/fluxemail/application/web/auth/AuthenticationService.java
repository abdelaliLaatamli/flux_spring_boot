package com.fluxemail.application.web.auth;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fluxemail.application.security.JwtService;
import com.fluxemail.application.security.OwnUserDetails;
import com.fluxemail.application.security.data.Role;
import com.fluxemail.application.security.data.User;
import com.fluxemail.application.security.data.UserRepository;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
@RequiredArgsConstructor
public class AuthenticationService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;
    public AuthenticationResponse register(RegisterRequest request) {

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
        var refreshToken = jwtService.generateRefreshToken(userDetails);


        return AuthenticationResponse
                .builder()
                .accessToken(jwtToken)
                .refreshToken(refreshToken)
                .build();
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
}
