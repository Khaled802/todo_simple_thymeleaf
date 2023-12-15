// package com.example.todobasic.configuration;

// import org.springframework.boot.web.servlet.FilterRegistrationBean;
// import org.springframework.context.annotation.Bean;
// import org.springframework.context.annotation.Configuration;

// import com.example.todobasic.filter.SessionFilter;

// @Configuration
// public class WebConfiguration {
//     @Bean
//     FilterRegistrationBean<SessionFilter> sessionFilter() {
//         FilterRegistrationBean<SessionFilter> registrationBean = new FilterRegistrationBean<>();
//         registrationBean.setFilter(new SessionFilter());
//         registrationBean.addUrlPatterns("/*"); // Adjust URL patterns as needed
//         return registrationBean;
//     }
// }
