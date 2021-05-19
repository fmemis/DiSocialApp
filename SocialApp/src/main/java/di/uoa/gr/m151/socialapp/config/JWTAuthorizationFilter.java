package di.uoa.gr.m151.socialapp.config;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTDecodeException;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

public class JWTAuthorizationFilter extends BasicAuthenticationFilter {

    private JWTConstants jwtProperties;

    public JWTAuthorizationFilter(AuthenticationManager authManager, JWTConstants jwtProperties) {
        super(authManager);
        this.jwtProperties = jwtProperties;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest req,
                                    HttpServletResponse res,
                                    FilterChain chain) throws IOException, ServletException {
        String header = req.getHeader(jwtProperties.getHeaderString());

        if (header == null || !header.startsWith(jwtProperties.getTokenPrefix())) {
            chain.doFilter(req, res);
            return;
        }

        UsernamePasswordAuthenticationToken authentication = getAuthentication(req);

        SecurityContextHolder.getContext().setAuthentication(authentication);
        chain.doFilter(req, res);
    }

    // Reads the JWT from the Authorization header, and then uses JWT to validate the token
    private UsernamePasswordAuthenticationToken getAuthentication(HttpServletRequest request) {
        String token = request.getHeader(jwtProperties.getHeaderString());

        if (token != null) {
            try {
                String user = JWT.require(Algorithm.HMAC512(jwtProperties.getJwtSecret().getBytes()))
                    .build()
                    .verify(token.replace(jwtProperties.getTokenPrefix(), ""))
                    .getSubject();
                if (user != null) {
                    // new arraylist means authorities
                    return new UsernamePasswordAuthenticationToken(user, null, new ArrayList<>());
                }
            } catch (JWTDecodeException jwtExc) {
                jwtExc.printStackTrace();
            }
            catch (Exception exception) {
                exception.printStackTrace();
            }

            return null;
        }
        return null;
    }
}

