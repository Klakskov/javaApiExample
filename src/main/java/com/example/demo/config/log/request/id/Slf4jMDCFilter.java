package com.example.demo.config.log.request.id;

import lombok.*;
import org.slf4j.MDC;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.UUID;

import static com.example.demo.utils.Functions.isNullOrEmpty;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
@Component
class Slf4jMDCFilter extends OncePerRequestFilter {

    private  String responseHeader;
    private  String mdcTokenKey;
    private  String requestHeader;

    @Override
    protected void doFilterInternal(final HttpServletRequest request, final HttpServletResponse response,
                                    final FilterChain chain)
            throws java.io.IOException, ServletException {
        try {
            final String token;
            if (!isNullOrEmpty(requestHeader) && !isNullOrEmpty(request.getHeader(requestHeader))) {
                token = request.getHeader(requestHeader);
            } else {
                token = UUID.randomUUID().toString().toUpperCase().replace("-", "");
            }
            MDC.put(mdcTokenKey, token);

            if (!isNullOrEmpty(responseHeader)) {
                response.addHeader(responseHeader, token);
            }
            chain.doFilter(request, response);
        } finally {
            MDC.remove(mdcTokenKey);
        }
    }
}