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
        // desativa a proteção de segurança do "frame options header".
        // Esse header é usado para proteger contra o ataque de "clickjacking"
        http.headers().frameOptions().disable();

        // Habilitado o CORS e desabilitado o CSRF por ser um projeto apenas de desenvolvimento
        http.cors()
                .and().csrf().disable()
                .authorizeRequests()
                    // Liberadas as rotas para poder
                    // apresentar o form default do Spring Security
                    // e também poder acessar o console do H2
                    .antMatchers( "/error/**").permitAll()
                    .antMatchers( "/login/**").permitAll()
                    .antMatchers("/h2-console/**").permitAll()
                    .anyRequest().authenticated()
                    .and()
                // Permite usar o Form de login default do Spring Security no navegador
                .formLogin().permitAll()
                    .and()
                .logout().permitAll()
                    .and()
                // Permite o uso da autenticação básica
                .httpBasic(withDefaults());;

        return http.build();
    }
}