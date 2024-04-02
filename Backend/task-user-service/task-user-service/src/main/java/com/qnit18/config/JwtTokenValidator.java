package com.qnit18.config;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.crypto.SecretKey;
import java.io.IOException;
import java.util.List;

/**
 * Customer filter for validating JWT tokens
 *  This filter is responsible for extracting the JWT token from the request header
 *  validating it, and setting the authentication in the SecurityContext Holder
 */

public class JwtTokenValidator extends OncePerRequestFilter {

    /**
     * Performs the actual filtering logic for each incoming request.
     * Extracts the JWT token from the request header, validates it,
     * and sets the authentication in the SecurityContextHolder if the token is valid.
     *
     * @param request     HTTP servlet request
     * @param response    HTTP servlet response
     * @param filterChain filter chain for dispatching the request
     * @throws ServletException if an error occurs while processing the request
     * @throws IOException      if an I/O error occurs while processing the request
     */
    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain)
            throws ServletException, IOException {
        // Extract JWT token from request
        String jwt = request.getHeader(JwtConstant.JWT_HEADER);

        if (jwt != null){
            // Extract token
            jwt = jwt.substring(7);

            try{
                // Retrieve secret_key for validating
                SecretKey key = Keys.hmacShaKeyFor(JwtConstant.SECRET_KEY.getBytes());

                Claims claims = Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(jwt).getBody();

                String email = String.valueOf(claims.get("email"));
                String authorities = String.valueOf(claims.get("authorities"));

                List<GrantedAuthority> auths = AuthorityUtils.commaSeparatedStringToAuthorityList(authorities);

                Authentication authentication = new UsernamePasswordAuthenticationToken(email, null, auths);

                // Set the authentication in the security context
                SecurityContextHolder.getContext().setAuthentication(authentication);
            } catch (Exception e){
                throw new BadCredentialsException("Invalid token...");
            }
        }
        // continues the filter chain processing
        filterChain.doFilter(request, response);
    }
}
