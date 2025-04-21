package com.reminder.controller;

import com.reminder.dto.AccountDto;
import com.reminder.exception.TokenRefreshException;
import com.reminder.jwt.JwtTokenUtil;
import com.reminder.model.RefreshToken;
import com.reminder.model.User;
import com.reminder.payload.LoginRequest;
import com.reminder.payload.LoginResponse;
import com.reminder.payload.TokenRefreshRequest;
import com.reminder.payload.TokenRefreshResponse;
import com.reminder.service.RefreshTokenService;
import com.reminder.service.UserService;
import com.reminder.service.impl.UserDetailsImpl;

import jakarta.validation.Valid;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@CrossOrigin(allowedHeaders = "*")
@RestController
@RequestMapping("api/auth")
public class SecurityRestController {
    @Autowired
    private RefreshTokenService refreshTokenService;
    
    @Autowired
    private UserService userService;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @PostMapping("/login")
    public ResponseEntity<?> authenticateUser(@RequestBody @Valid LoginRequest loginRequest) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtTokenUtil.generateJwtToken(loginRequest.getUsername());
        UserDetailsImpl userDetails = (UserDetailsImpl) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        List<String> roles = userDetails.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.toList());

        RefreshToken refreshToken = refreshTokenService.insertToken(userDetails.getId());
        return ResponseEntity.ok(
                new LoginResponse(
                        jwt,
                        refreshToken.getToken(),
                        userDetails.getUsername(),
                        roles,
                        userDetails.isEnabled()));
    }

    @PostMapping("/create")
    public ResponseEntity<Map<String, String>> createAccount(@RequestBody @Valid AccountDto accountDto,
                                                           BindingResult bindingResult) {

    	accountDto.validate(accountDto, bindingResult);

        if (bindingResult.hasErrors()) {
            Map<String, String> errMap = new HashMap<>();

            for (FieldError fieldError : bindingResult.getFieldErrors()) {
                errMap.put(fieldError.getField(), fieldError.getDefaultMessage());
            }
            return new ResponseEntity<>(errMap, HttpStatus.BAD_REQUEST);
        }

        User user = new User();
        BeanUtils.copyProperties(accountDto, user);

        int countUser = this.userService.addUser(user);

        if(countUser > 0 ) {
        	Map<String, String> response = new HashMap<>();
            response.put("message", "User created successfully");
            return new ResponseEntity<>(response, HttpStatus.CREATED);
        }
        
        Map<String, String> errorResponse = new HashMap<>();
        errorResponse.put("error", "Failed to create user");
        return new ResponseEntity<>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
    }
    
    
    @PostMapping("/refreshtoken")
    public ResponseEntity<TokenRefreshResponse> refreshtoken(@Valid @RequestBody TokenRefreshRequest request) {
        String requestRefreshToken = request.getRefreshToken();

        Optional<RefreshToken> optionalToken = refreshTokenService.selectByToken(requestRefreshToken);
        
        if (optionalToken.isEmpty()) {
            throw new TokenRefreshException(requestRefreshToken, "Refresh token is not in database!");
        }
        
        RefreshToken refreshToken = refreshTokenService.verifyExpiration(optionalToken.get());
        Integer userId = refreshToken.getUserId();
        User user = userService.selectById(userId);

        if (user == null) {
            throw new TokenRefreshException(requestRefreshToken, "User not found!");
        }
        
        String newAccessToken = jwtTokenUtil.generateJwtToken(user.getEmail());

        return ResponseEntity.ok(new TokenRefreshResponse(newAccessToken, requestRefreshToken));
    }
}
