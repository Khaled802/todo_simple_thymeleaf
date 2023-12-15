// package com.example.todobasic.filter;

// import java.io.IOException;
// import java.util.List;

// import org.springframework.web.filter.OncePerRequestFilter;

// import jakarta.servlet.FilterChain;
// import jakarta.servlet.ServletException;
// import jakarta.servlet.annotation.WebFilter;
// import jakarta.servlet.http.HttpServletRequest;
// import jakarta.servlet.http.HttpServletResponse;
// import lombok.extern.slf4j.Slf4j;

// @WebFilter("/*")
// @Slf4j
// public class SessionFilter extends OncePerRequestFilter {

//     private static List<String> included = List.of("todos", "home");

//     @Override
//     protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
//             throws ServletException, IOException {
//         log.info("included {}", isIncluded(request));
//         log.info("current url {}", request.getRequestURL().toString());

//         if (isIncluded(request) && request.getSession().getAttribute("username") == null) {

//             response.sendRedirect("/auth/login");
//         } else {
//             filterChain.doFilter(request, response);
//         }
//     }

//     private boolean isIncluded(HttpServletRequest request) {
//         String curUrl = request.getRequestURL().toString();
//         for (String includeUrl : included) {
//             if (curUrl.startsWith(getPrefixUrl(request) + "/" + includeUrl)) {
//                 return true;
//             }
//         }
//         return false;
//     }

//     public static String getPrefixUrl(HttpServletRequest request) {
//         // Get the complete URL including protocol and port
//         StringBuilder currentUrl = new StringBuilder()
//                 .append(request.getScheme())
//                 .append("://")
//                 .append(request.getServerName());

//         // Append port if not default (80 for HTTP, 443 for HTTPS)
//         int port = request.getServerPort();
//         if (port != 80 && port != 443) {
//             currentUrl.append(":").append(port);
//         }

//         return currentUrl.toString();
//     }
// }
