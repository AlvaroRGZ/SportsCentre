package es.upm.miw.sportscentre.config;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FilterConfig {
  @Bean
  public FilterRegistrationBean<OriginFilter> originFilter() {
    FilterRegistrationBean<OriginFilter> registrationBean = new FilterRegistrationBean<>();
    registrationBean.setFilter(new OriginFilter());
    registrationBean.addUrlPatterns("/*");
    return registrationBean;
  }
}
