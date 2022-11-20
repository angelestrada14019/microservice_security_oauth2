package com.dh.usersms.security;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.oauth2.jwt.NimbusJwtDecoder;

@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class OAuth2ResourceServerSecurityConfiguration extends WebSecurityConfigurerAdapter {

  @Value("${property.keycloak.jwt.cert.uri}")
  private String hostAndRealm;

  @Override
  protected void configure(HttpSecurity http) throws Exception {
    http
            .oauth2ResourceServer()
            .jwt()
            .jwtAuthenticationConverter(new KeyCloakJwtAuthenticationConverter());
    http
            .authorizeRequests()
            .anyRequest()
            .authenticated();
  }
  @Bean
  public JwtDecoder jwtDecoder() {
    return NimbusJwtDecoder.withJwkSetUri(hostAndRealm).build();
  }
}
