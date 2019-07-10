package ru.amaev.budget.configs

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter
import org.springframework.security.config.http.SessionCreationPolicy
import ru.amaev.budget.security.jwt.JwtConfigurer
import ru.amaev.budget.security.jwt.JwtTokenProvider


@Configuration
class WebSecurityConfig : WebSecurityConfigurerAdapter() {

    @Autowired
    lateinit var jwtTokenProvider: JwtTokenProvider

//    @Autowired
//    lateinit var authProvider: CustomAuthenticationProvider

    @Bean
    override fun authenticationManagerBean(): AuthenticationManager {
        return super.authenticationManagerBean()
    }

    override fun configure(http: HttpSecurity?) {
        http!!
                .httpBasic().disable()
                .csrf().disable()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                    .formLogin()
                    .loginPage("/login")
                    .permitAll()
                .and()
                .authorizeRequests()
                    .antMatchers("/").permitAll()
                    .antMatchers("/auth1/login1").permitAll()
                    .antMatchers("/admin").hasRole("ADMIN")
                    .anyRequest().authenticated()
                .and()
                .apply(JwtConfigurer(jwtTokenProvider))

    }

    //    override fun configure(http: HttpSecurity) {
//        http.csrf().disable()
//        http.authorizeRequests()
//                .antMatchers("/").permitAll()
//                .anyRequest().authenticated()
//                .antMatchers("/admin").hasRole("ADMIN")
//                .and()
//                    .formLogin()
//                    .loginPage("/login")
//                    .permitAll()
//                .and()
//                    .logout()
//                    .permitAll()
//
//        http.exceptionHandling().accessDeniedPage("/403");
//    }
//
//    override fun configure(auth: AuthenticationManagerBuilder) {
//        auth.authenticationProvider(authProvider)
//    }
}