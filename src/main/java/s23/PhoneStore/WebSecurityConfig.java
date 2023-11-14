package s23.PhoneStore;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import s23.PhoneStore.service.UserDetailServiceImpl;

@Configuration
@EnableMethodSecurity(securedEnabled = true)
@EnableWebSecurity
public class WebSecurityConfig {
	@Autowired
	private UserDetailServiceImpl userDetailsService;

	@Bean
	public SecurityFilterChain configure(HttpSecurity http) throws Exception {

		return http.authorizeRequests(auth -> {
			auth.requestMatchers("/phoneslist/**").permitAll();
			auth.requestMatchers("/mainpage/**").permitAll();
			
			// Jos käyttäjä on ADMIN, hänellä on oikeudet kaikkialle.
			auth.requestMatchers("/add/**").hasAuthority("ADMIN");
			auth.requestMatchers("/save/**").hasAuthority("ADMIN");
			auth.requestMatchers("/delete/**").hasAuthority("ADMIN");
			auth.requestMatchers("/edit/**").hasAuthority("ADMIN");
			auth.requestMatchers("/api/**").hasAuthority("ADMIN");
			auth.requestMatchers("/applicationUsers/**").hasAuthority("ADMIN");
			auth.requestMatchers("/profile/**").hasAuthority("ADMIN");
			auth.requestMatchers("/phones/**").hasAuthority("ADMIN");
			// kaikki http pyynnöt autentikoidaan
			auth.anyRequest().authenticated();
		}).csrf().ignoringRequestMatchers("h2-console").and()
				// Kertoo minne mennä onnistuneen kirjautumisen jälkeen
				.formLogin().defaultSuccessUrl("/mainpage", true).and()
				// Uloskirjautuminen sallittu kaikille
				.logout().permitAll().and()

				//
				.httpBasic(Customizer.withDefaults()).build();
	}

	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userDetailsService).passwordEncoder(new BCryptPasswordEncoder());
	}
}