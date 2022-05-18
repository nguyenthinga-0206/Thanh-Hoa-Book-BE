package dut.udn.vn.thanhhoabook.config;

import dut.udn.vn.thanhhoabook.security.jwt.AuthEntryPointJwt;
import dut.udn.vn.thanhhoabook.security.jwt.JwtRequestFilter;
import dut.udn.vn.thanhhoabook.security.service.MyUserDetailsService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

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
        http.csrf().disable();
        http.authorizeRequests().anyRequest().permitAll();

//        http.csrf().ignoringAntMatchers("/api/**");
//        http.antMatcher("/api/**").httpBasic().authenticationEntryPoint(entryPointJwt())
//                .and()
//                .authorizeRequests()
//                .antMatchers("/api/**").permitAll()
//                .antMatchers("/api/home").permitAll()
//                .antMatchers(HttpMethod.POST, "/api/login").permitAll()
//                .antMatchers(HttpMethod.POST, "/api/register").permitAll()
//                .antMatchers(HttpMethod.POST, "/api/logout").permitAll()
//                .antMatchers(HttpMethod.GET, "/api/users").hasAnyRole("ADMIN", "MANAGEMENT")
//                .antMatchers(HttpMethod.GET, "/api/orders").hasAnyRole("ADMIN", "MANAGEMENT")
//                .and().sessionManagement()
//                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
//                .and()
//                .addFilterBefore(jwtRequestFilter(), UsernamePasswordAuthenticationFilter.class);
    }
}

