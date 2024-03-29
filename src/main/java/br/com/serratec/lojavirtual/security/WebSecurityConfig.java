package br.com.serratec.lojavirtual.security;

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
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity 
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
	
	@Autowired
	private CustomUserDetailsService customUserDetailsService;
	
	@Autowired
	private JWTAuthenticationFilter jwtAuthenticationFilter;
	
	
	@Override
    public void configure(AuthenticationManagerBuilder authenticationManagerBuilder) throws Exception {
        authenticationManagerBuilder
                .userDetailsService(customUserDetailsService)
                .passwordEncoder(passwordEnconder());
    }
	
		
	@Bean
	public PasswordEncoder passwordEnconder() {
		return new BCryptPasswordEncoder();
	}
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		
		http
			.cors().and().csrf().disable()
			.exceptionHandling()
			.and()
			.sessionManagement()
			.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
			.and()
			.authorizeRequests()
			
					 			
			.antMatchers("/api/login") 
			.permitAll() 
			
			.antMatchers("/api/recuperar")
			.permitAll()
			
			.antMatchers(HttpMethod.PATCH, "/api/recuperar/email/{senha}")
			.permitAll()
			
			.antMatchers(HttpMethod.POST, "/api/clientes")
			.permitAll() 
			
			.antMatchers("/api/categorias", "/api/categorias/**")
			.permitAll()
			
			.antMatchers("/api/produtos", "/api/produtos/**")
			.permitAll()
			
			.antMatchers(HttpMethod.GET, "/api/pedidos", "/api/pedidos**")
			.permitAll()
			
			.antMatchers(HttpMethod.DELETE, "/api/pedidos", "/api/pedidos**")
			.permitAll()

			
<<<<<<< HEAD
			// .anyRequest().authenticated();
=======
>>>>>>> cfb0d47327fbb616f32b121a2f832b41c4ba1995
			.anyRequest().permitAll(); 
			
		http.addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);
			
	}	
	
	
    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }
}
