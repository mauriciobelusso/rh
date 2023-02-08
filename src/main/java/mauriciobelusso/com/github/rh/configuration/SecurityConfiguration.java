package mauriciobelusso.com.github.rh.configuration;

import lombok.SneakyThrows;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

import static org.springframework.security.config.Customizer.withDefaults;

@EnableWebSecurity
@Configuration
public class SecurityConfiguration {

    @Bean
    @SneakyThrows
    public SecurityFilterChain filterChain(HttpSecurity http) {
        http.headers().frameOptions().disable();

        http.cors()
                .and().csrf().disable()
                .authorizeRequests()
                    .antMatchers( "/error/**").permitAll()
                    .antMatchers( "/login/**").permitAll()
                    .antMatchers("/h2-console/**").permitAll()
                    .anyRequest().authenticated()
                    .and()
                .formLogin().permitAll()
                    .and()
                .logout().permitAll()
                    .and()
                .httpBasic(withDefaults());;

        return http.build();
    }
}