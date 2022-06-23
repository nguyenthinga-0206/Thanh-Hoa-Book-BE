package dut.udn.vn.thanhhoabook.config;

import dut.udn.vn.thanhhoabook.security.jwt.AuthEntryPointJwt;
import dut.udn.vn.thanhhoabook.security.jwt.JwtRequestFilter;
import dut.udn.vn.thanhhoabook.security.service.MyUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    @Bean
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Bean
    public AuthEntryPointJwt entryPointJwt() {
        return new AuthEntryPointJwt();
    }

    @Bean
    public JwtRequestFilter jwtRequestFilter() {
        return new JwtRequestFilter();
    }

    @Autowired
    private MyUserDetailsService myUserDetailService;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(myUserDetailService);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
//        http.csrf().disable();
//        http.authorizeRequests().anyRequest().permitAll();

        http.csrf().ignoringAntMatchers("/api/**");
        http.antMatcher("/api/**").httpBasic().authenticationEntryPoint(entryPointJwt())
                .and()
                .authorizeRequests()
                //All
                .antMatchers("/api/home").permitAll()
                .antMatchers(HttpMethod.POST, "/api/login").permitAll()
                .antMatchers(HttpMethod.POST, "/api/register").permitAll()
                .antMatchers(HttpMethod.GET, "/api/users/profile").permitAll()
                .antMatchers(HttpMethod.PUT, "/api/users/change-password").permitAll()
                .antMatchers(HttpMethod.PUT, "/api/users/profile").permitAll()
                .antMatchers(HttpMethod.GET, "/api/book/**").permitAll()
                .antMatchers(HttpMethod.GET, "/api/book/search").permitAll()
                .antMatchers(HttpMethod.GET, "api/author").permitAll()
                .antMatchers(HttpMethod.GET, "api/category").permitAll()
                .antMatchers(HttpMethod.GET, "api/book/category/**").permitAll()
                .antMatchers(HttpMethod.GET, "api/producer").permitAll()
                .antMatchers(HttpMethod.GET, "/api/orders/**").permitAll()
                .antMatchers(HttpMethod.GET, "/api/orders/**/detail").permitAll()
                //Admin, management
                .antMatchers(HttpMethod.GET, "/api/users").hasAnyRole("ADMIN", "MANAGEMENT")
                .antMatchers(HttpMethod.POST, "/api/users").hasRole("ADMIN")
                .antMatchers(HttpMethod.PUT, "/api/users").hasRole("ADMIN")
                .antMatchers(HttpMethod.DELETE, "/api/users/**").hasRole("ADMIN")
                .antMatchers(HttpMethod.GET, "/api/orders").hasAnyRole("ADMIN", "MANAGEMENT")
                .antMatchers(HttpMethod.PUT, "/api/orders/status").hasAnyRole("ADMIN", "MANAGEMENT")
                .antMatchers(HttpMethod.POST, "/api/book").hasAnyRole("ADMIN", "MANAGEMENT")
                .antMatchers(HttpMethod.PUT, "/api/book").hasAnyRole("ADMIN", "MANAGEMENT")
                .antMatchers(HttpMethod.DELETE, "/api/book/**").hasAnyRole("ADMIN", "MANAGEMENT")
                .antMatchers(HttpMethod.POST, "api/author").hasAnyRole("ADMIN", "MANAGEMENT")
                .antMatchers(HttpMethod.PUT, "api/author").hasAnyRole("ADMIN", "MANAGEMENT")
                .antMatchers(HttpMethod.DELETE, "api/author/**").hasAnyRole("ADMIN", "MANAGEMENT")
                .antMatchers(HttpMethod.POST, "api/category").hasAnyRole("ADMIN", "MANAGEMENT")
                .antMatchers(HttpMethod.PUT, "api/category").hasAnyRole("ADMIN", "MANAGEMENT")
                .antMatchers(HttpMethod.DELETE, "api/category/**").hasAnyRole("ADMIN", "MANAGEMENT")
                .antMatchers(HttpMethod.POST, "api/producer").hasAnyRole("ADMIN", "MANAGEMENT")
                .antMatchers(HttpMethod.PUT, "api/producer").hasAnyRole("ADMIN", "MANAGEMENT")
                .antMatchers(HttpMethod.DELETE, "api/producer/**").hasAnyRole("ADMIN", "MANAGEMENT")
//                .antMatchers(HttpMethod.GET, "api/orders/statistic-revenue").hasRole("ADMIN")
                .antMatchers(HttpMethod.GET, "api/orders/statistic-top").hasRole("ADMIN")
                //User
                .antMatchers(HttpMethod.POST, "/api/cart").hasRole("USER")
                .antMatchers(HttpMethod.GET, "/api/cart").hasRole("USER")
                .antMatchers(HttpMethod.PUT, "/api/cart").hasRole("USER")
                .antMatchers(HttpMethod.DELETE, "/api/cart/**").hasRole("USER")
                .antMatchers(HttpMethod.POST, "api/orders").hasRole("USER")
                .antMatchers(HttpMethod.GET, "api/orders/history").hasRole("USER")
                .and().sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .addFilterBefore(jwtRequestFilter(), UsernamePasswordAuthenticationFilter.class);
    }
}

