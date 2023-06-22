package com.restapi.Employees.interceptor;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.client.ClientHttpRequestExecution;
import org.springframework.http.client.ClientHttpResponse;

import java.io.*;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class RestTemplateLoggingInterceptorTest {
    @InjectMocks
    RestTemplateLoggingInterceptor restTemplateLoggingInterceptor;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testIntercept() throws IOException {
        ClientHttpResponse result = restTemplateLoggingInterceptor.intercept(buildRequest(), "dto".getBytes(), buildClientHttpRequestExecution(false, false));
        Assertions.assertNotNull(result);
    }

    @Test
    void testIntercept_FirstCatch() throws IOException {
        ClientHttpResponse result = restTemplateLoggingInterceptor.intercept(buildRequest(), "".getBytes(), buildClientHttpRequestExecution(true, false));
        Assertions.assertNotNull(result);
    }

    @Test
    void testIntercept_Throw() {
        Assertions.assertThrows(NullPointerException.class, () -> restTemplateLoggingInterceptor.intercept(buildRequest(), "".getBytes(), buildClientHttpRequestExecution(false, true)));
    }

    private HttpRequest buildRequest() {
        return mock(HttpRequest.class);
    }

    private ClientHttpRequestExecution buildClientHttpRequestExecution(boolean firstCatch, boolean secondCatch) throws IOException {
        ClientHttpRequestExecution clientHttpRequestExecution = mock(ClientHttpRequestExecution.class);
        ClientHttpResponse clientHttpResponse = mock(ClientHttpResponse.class);
        InputStream inputStreamRs = new ByteArrayInputStream("test-response".getBytes());

        when(clientHttpResponse.getBody()).thenReturn(inputStreamRs);
        when(clientHttpResponse.getStatusCode()).thenReturn(HttpStatus.OK);
        when(clientHttpRequestExecution.execute(any(), any())).thenReturn(clientHttpResponse);
        if (firstCatch){
            when(clientHttpResponse.getBody()).thenThrow(new IOException("Expected IOEx"));
            when(clientHttpResponse.getStatusCode()).thenThrow(new IOException("Expected IOEx"));
        }
        if (secondCatch) {
            when(clientHttpResponse.getStatusCode()).thenReturn(null);
        }
        return clientHttpRequestExecution;
    }
}
