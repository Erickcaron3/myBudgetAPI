package net.erickcaron.mybudgetapi.configuration.filter;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.web.util.ContentCachingRequestWrapper;
import org.springframework.web.util.ContentCachingResponseWrapper;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.Optional;

@Slf4j
@Component
public class LoggingFilter extends OncePerRequestFilter {
    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain
                                    ) throws ServletException, IOException {

        ContentCachingRequestWrapper contentCachingRequestWrapper = new ContentCachingRequestWrapper(request);
        ContentCachingResponseWrapper contentCachingResponseWrapper = new ContentCachingResponseWrapper(response);

        long startTime = System.currentTimeMillis();
        filterChain.doFilter(contentCachingRequestWrapper, contentCachingResponseWrapper);
        long endTime = System.currentTimeMillis();

        String requestBody = Optional.of(getStringValue(contentCachingRequestWrapper.getContentAsByteArray(), request.getCharacterEncoding())).orElse("");
        String responseBody = Optional.of(getStringValue(contentCachingResponseWrapper.getContentAsByteArray(), response.getCharacterEncoding())).orElse("");

        long timeTaken = endTime - startTime;

        log.info("Filter logs: Method = {} , " +
                "response code = {},  " +
                "requestURl = {} ," +
                "Request = {} , " +
                "Response = {}, " +
                "Time taken = {} ms", request.getMethod(), response.getStatus(),request.getRequestURI(), requestBody, responseBody, timeTaken);

        contentCachingResponseWrapper.copyBodyToResponse();
    }


    private String getStringValue(byte[] contentAsByteArray, String characterEncoding) throws UnsupportedEncodingException {
        return new String(contentAsByteArray, 0, contentAsByteArray.length, characterEncoding);
    }

}
