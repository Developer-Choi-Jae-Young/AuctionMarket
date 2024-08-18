package com.example.AuctionMarket.config;

import com.example.AuctionMarket.dto.LoginDto;
import com.example.AuctionMarket.entity.UserDetailsImpl;
import com.example.AuctionMarket.service.JwtProperties;
import com.example.AuctionMarket.service.JwtToken;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import java.io.IOException;

@RequiredArgsConstructor
@Slf4j
public class JwtAuthenticationFilter extends UsernamePasswordAuthenticationFilter {
    private final JwtToken jwtToken;
    private final AuthenticationManager authenticationManager;

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response)
            throws AuthenticationException {
        try{
            log.info("Authentication Filter");
            ObjectMapper om = new ObjectMapper();
            LoginDto user = om.readValue(request.getInputStream(), LoginDto.class);
            String username = user.getUserId();
            String password = user.getPassword();

            UsernamePasswordAuthenticationToken authRequest = new UsernamePasswordAuthenticationToken(username,password);

            return authenticationManager.authenticate(authRequest);
        }
        catch (IOException e) {
            e.printStackTrace();
        }


        return null;
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain,
                                            Authentication authResult) throws IOException, ServletException {
        UserDetailsImpl principalUserDetails = (UserDetailsImpl)authResult.getPrincipal();
        String username = principalUserDetails.getUsername();

        String access = jwtToken.accessTokenCreateToken(authResult);
        String refresh = jwtToken.refreshTokenCreateToken(authResult);

        response.addHeader(JwtProperties.HEADER_ACCESS_STRING, JwtProperties.TOKEN_PREFIX + access);
        response.addHeader(JwtProperties.HEADER_REFRESH_STRING, JwtProperties.TOKEN_PREFIX + refresh);
        setSuccessResponse(response, "로그인 성공");
        log.info("성공했어");
    }

    @Override
    protected void unsuccessfulAuthentication(HttpServletRequest request, HttpServletResponse response,
                                              AuthenticationException failed) throws IOException, ServletException {

        String failReason = failed.getMessage();
        setFailResponse(response, failReason);
    }

    private void setSuccessResponse(HttpServletResponse response, String message) throws IOException {
        response.setStatus(HttpServletResponse.SC_OK);
        response.setContentType(MediaType.APPLICATION_JSON_VALUE);

        try
        {
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("success", true);
            jsonObject.put("code", 1);
            jsonObject.put("message", message);
            response.getWriter().print(jsonObject);
        }
        catch (JSONException e)
        {
            e.printStackTrace();
        }
    }

    private void setFailResponse(HttpServletResponse response, String message) throws IOException {
        response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
        response.setContentType(MediaType.APPLICATION_JSON_VALUE);
        try
        {
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("success", false);
            jsonObject.put("code", -1);
            jsonObject.put("message", message);

            response.getWriter().print(jsonObject);
        }
        catch (JSONException e)
        {
            e.printStackTrace();
        }
    }
}
