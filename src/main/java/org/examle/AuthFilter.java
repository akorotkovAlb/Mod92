package org.examle;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebFilter(value = "/*")
public class AuthFilter extends HttpFilter {
    @Override
    protected void doFilter(HttpServletRequest req,
                            HttpServletResponse resp,
                            FilterChain chain) throws IOException, ServletException {

        String authHeaderValue = req.getHeader("Authorization");
        if ("111".equals(authHeaderValue)) {
            chain.doFilter(req, resp);
        } else {
            resp.setStatus(401);

            resp.setContentType("application/json");
            resp.getWriter().write("{\"Error\": \"Not authorized\"}");
            resp.getWriter().close();
        }
    }
}
