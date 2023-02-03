package com.snapchat.clone.clone.security

import com.auth0.jwt.JWT
import com.auth0.jwt.algorithms.Algorithm
import com.snapchat.clone.clone.filter.CustomAuthenticationFilter
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.http.SessionCreationPolicy
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.security.web.SecurityFilterChain
import java.util.*

@Configuration
class SecurityConfig(
        private val userDetailsService: UserDetailsService,
        private val bCrypt: BCryptPasswordEncoder
) {
    @Bean
    fun filterChain(
            http: HttpSecurity,
            authenticationManager: AuthenticationManager
    ): SecurityFilterChain {
        val customAuthenticationFilter = CustomAuthenticationFilter(authenticationManager)
        customAuthenticationFilter.setFilterProcessesUrl("/api/login")
        http.csrf()?.disable()?.cors()
        http.sessionManagement()?.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
        http.authorizeHttpRequests().requestMatchers("/api/login/**", "/api/refresh/token/**", "/api/users/save/**")
        http.authorizeHttpRequests().anyRequest().authenticated()
        http.addFilter(customAuthenticationFilter)
        return http.build()
    }
}