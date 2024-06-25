package es.upm.miw.sportscentre.config;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.FilterConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;

import java.io.IOException;

public class OriginFilter implements Filter {

  private static final String ALLOWED_ORIGIN = "http://localhost:4200";

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

    String serverAddress = httpRequest.getScheme() + "://" + httpRequest.getServerName() + ":" + httpRequest.getServerPort();

    if ((ALLOWED_ORIGIN.equals(origin) || serverAddress.equals(origin)) &&
        (referer == null || referer.startsWith(ALLOWED_ORIGIN) || referer.startsWith(serverAddress))) {
      chain.doFilter(request, response);
    } else {
      httpResponse.sendError(HttpServletResponse.SC_FORBIDDEN, "Forbidden: Invalid Origin or Referer");
    }
  }

  @Override
  public void destroy() {
  }
}
