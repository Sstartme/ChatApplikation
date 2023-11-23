package ch.bbw.pr.sospri;


import ch.bbw.pr.sospri.Auth.CustomAuthenticationProvider;
import ch.bbw.pr.sospri.Auth.CustomWebAuthenticationDetailsSource;
import ch.bbw.pr.sospri.member.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.crypto.password.Pbkdf2PasswordEncoder;

import javax.annotation.Resource;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Locale;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

   @Resource
   private CustomWebAuthenticationDetailsSource authenticationDetailsSource;

//    @Autowired
//    public void globalSecurityConfiguration(AuthenticationManagerBuilder auth) throws Exception {
//        auth.inMemoryAuthentication().withUser("user").password("{noop}1234").roles("user");
//        auth.inMemoryAuthentication().withUser("admin").password("{noop}5678").roles("user", "admin");
//    }

//    @Override
//    protected void configure(AuthenticationManagerBuilder auth) throws Exception{
//        auth.authenticationProvider(daoAuthenticationProvider());
//    }

   @Autowired
   MemberService memberservice;

//    @Autowired
//    public PasswordEncoder passwordEncoder(){
//        return new BCryptPasswordEncoder();
//    }

//    @Bean
//    public DaoAuthenticationProvider daoAuthenticationProvider() {
//        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
//        provider.setPasswordEncoder(passwordEncoder());
//        provider.setUserDetailsService(this.memberservice);
//        return provider;
//    }

   protected void configure(HttpSecurity http) throws Exception {
      System.out.println("Using default configure(HttpSecurity)" +
            "if subclasse this will potentially override subclass configure(HttpSecurity)");

      http.authorizeRequests()

              .antMatchers("/h2-console/**").permitAll()
            .antMatchers("/fragments/**").permitAll()
            .antMatchers("/css/**").permitAll()
            .antMatchers("/img/**").permitAll()
            .antMatchers("/").permitAll()
              .antMatchers("/oauth_login").permitAll()
            .antMatchers("/noSecurity.html").permitAll()
            .antMatchers("/get-register").permitAll()
            .antMatchers("/contact.html").permitAll()
            .antMatchers("/get-members").hasAnyAuthority("admin")
            .antMatchers("/get-messages").hasAnyAuthority("admin", "supervisor", "member", "moderator", "ROLE_USER")
            .antMatchers("/get-channel").hasAnyAuthority("admin", "supervisor", "member", "moderator", "ROLE_USER")
            .antMatchers("/edit-member").hasAuthority("admin")
            .antMatchers("/delete-member").hasAuthority("admin")
            .antMatchers("/edit-message").hasAuthority("moderator")
            .antMatchers("/delete-message").hasAuthority("moderator")
             // .anyRequest().authenticated()
            .and().formLogin().authenticationDetailsSource(authenticationDetailsSource).loginPage("/login").permitAll()
            .and().logout().permitAll()
              .and().oauth2Login().loginPage("/login").permitAll()
            .and().exceptionHandling().accessDeniedPage("/403.html")
            .and().httpBasic();

      http.csrf().ignoringAntMatchers("/h2-console/**")
            .and().headers().frameOptions().sameOrigin();
   }

   @Bean
   public CustomAuthenticationProvider daoAuthenticationProvider() throws IOException {
      CustomAuthenticationProvider provider = new CustomAuthenticationProvider();
      provider.setPasswordEncoder(passwordEncoder());
      provider.setUserDetailsService(this.memberservice);
      return provider;
   }

   @Bean
   public PasswordEncoder passwordEncoder() throws IOException {
      String pepper = "A3";
      int iterations = 20000;
      int hashWidth = 256;
      Pbkdf2PasswordEncoder pbkdf2PasswordEncoder = new Pbkdf2PasswordEncoder(pepper, iterations, hashWidth);
      pbkdf2PasswordEncoder.setEncodeHashAsBase64(true);
      return pbkdf2PasswordEncoder;
   }

   @Override
   protected void configure(AuthenticationManagerBuilder authenticationManagerBuilder) throws Exception {
      authenticationManagerBuilder.authenticationProvider(daoAuthenticationProvider());
   }
}