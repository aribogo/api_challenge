package api.gft.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import api.gft.security.filter.AuthenticationFilter;
import api.gft.security.service.AuthenticationService;
import api.gft.security.service.UserService;

@EnableWebSecurity
@Configuration
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfiguration extends WebSecurityConfigurerAdapter{

	private final UserService userService;

	private final AuthenticationService authenticationService;

	public SecurityConfiguration( UserService userService, @Lazy AuthenticationService authenticationService) {
		this.userService = userService;
		this.authenticationService = authenticationService;
	}
	
	@Bean
	@Override
	protected AuthenticationManager authenticationManager() throws Exception{
		return super.authenticationManager();
	}

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userService).passwordEncoder(new BCryptPasswordEncoder());
	}

	@Override
	public void configure(WebSecurity web) throws Exception {
		super.configure(web);
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests()
		.antMatchers("/v1/auth/**").permitAll()
		.antMatchers(HttpMethod.GET, "/v1/party/**").permitAll()
		.antMatchers(HttpMethod.POST, "/v1/party/**").hasRole("MANAGER")
		.antMatchers(HttpMethod.PUT, "/v1/party/**").hasRole("MANAGER")
		.antMatchers(HttpMethod.DELETE, "/v1/party/**").hasRole("MANAGER")
		.antMatchers(HttpMethod.GET, "/v1/politician/**").permitAll()
		.antMatchers(HttpMethod.POST, "/v1/politician/**").hasRole("MANAGER")
		.antMatchers(HttpMethod.PUT, "/v1/politician/**").hasRole("MANAGER")
		.antMatchers(HttpMethod.DELETE, "/v1/politician/**").hasRole("MANAGER")
		.antMatchers(HttpMethod.GET, "/v1/lawsuit/**").permitAll()
		.antMatchers(HttpMethod.POST, "/v1/lawsuit/**").hasRole("MANAGER")
		.antMatchers(HttpMethod.PUT, "/v1/lawsuit/**").hasRole("MANAGER")
		.antMatchers(HttpMethod.DELETE, "/v1/lawsuit/**").hasRole("MANAGER")
		.antMatchers(HttpMethod.GET, "/v1/bill/**").permitAll()
		.antMatchers(HttpMethod.POST, "/v1/bill/**").hasRole("MANAGER")
		.antMatchers(HttpMethod.PUT, "/v1/bill/**").hasRole("MANAGER")
		.antMatchers(HttpMethod.DELETE, "/v1/bill/**").hasRole("MANAGER")
		.antMatchers("/h2/**").permitAll()
		.anyRequest().authenticated()
		.and()
		.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
		.and()
		.addFilterBefore(new AuthenticationFilter(authenticationService, userService), UsernamePasswordAuthenticationFilter.class);

		
		http.csrf().disable();
        http.headers().frameOptions().disable();
	}
	
	
	
}
