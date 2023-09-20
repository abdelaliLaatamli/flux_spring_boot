package com.fluxemail.application.web.auth;

import com.fluxemail.application.security.JwtService;
import com.fluxemail.application.security.OwnUserDetails;
import com.fluxemail.application.security.data.Role;
import com.fluxemail.application.security.data.User;
import com.fluxemail.application.security.data.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

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
        var jwtToken = jwtService.generateToken(OwnUserDetails.fromUser( createdUser));


        return AuthenticationResponse.builder().token(jwtToken).build();
    }

    public AuthenticationResponse authenticate(AuthenticationRequest request) {

        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            request.getEmail(),
                            request.getPassword()
                    )
            );
        }catch (Exception e){
            System.out.println("*************************");
            System.out.println( e );
            System.out.println("**************************");
            throw e;
        }

        var user = userRepository
                .findByEmail(request.getEmail())
                .orElseThrow( () -> new RuntimeException("User not found")  );

        var jwtToken = jwtService.generateToken(OwnUserDetails.fromUser( user ));
        return AuthenticationResponse.builder().token(jwtToken).build();
    }
}
