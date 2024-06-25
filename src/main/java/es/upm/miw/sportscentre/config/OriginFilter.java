package es.upm.miw.sportscentre.config;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.FilterConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.IOException;

@Configuration
public class FilterConfig {

  private static final String ALLOWED_ORIGIN = "http://localhost:4200";
  private static final String SERVER_ADDRESS = "http://localhost:8080";

  @Bean(name = "customOriginFilter")
  public Filter originFilter() {
    return new Filter() {

      @Override
      public void init(FilterConfig filterConfig) throws ServletException {
      }

      @Override
      public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
          throws IOException, ServletException {
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        HttpServletResponse httpResponse = (HttpServletResponse) response;

        String origin = httpRequest.getHeader("Origin");
        String referer = httpRequest.getHeader("Referer");

        if ((ALLOWED_ORIGIN.equals(origin) || SERVER_ADDRESS.equals(origin)) &&
            (referer == null || referer.startsWith(ALLOWED_ORIGIN) || referer.startsWith(SERVER_ADDRESS))) {
          chain.doFilter(request, response);
        } else {
          httpResponse.sendError(HttpServletResponse.SC_FORBIDDEN, "Forbidden: Invalid Origin or Referer");
        }
      }

      @Override
      public void destroy() {
      }
    };
  }
}
