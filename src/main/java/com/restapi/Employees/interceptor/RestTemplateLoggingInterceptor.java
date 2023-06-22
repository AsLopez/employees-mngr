package com.restapi.Employees.interceptor;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpRequest;
import org.springframework.http.client.ClientHttpRequestExecution;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.ClientHttpResponse;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;

public class RestTemplateLoggingInterceptor implements ClientHttpRequestInterceptor {
    private static final Logger LOGGER = LoggerFactory.getLogger(RestTemplateLoggingInterceptor.class);

    public RestTemplateLoggingInterceptor() {
    }

    @Override
    public ClientHttpResponse intercept(HttpRequest request, byte[] body, ClientHttpRequestExecution clientHttpRequestExecution) throws IOException {
        this.logRequest(request, body);
        ClientHttpResponse response = clientHttpRequestExecution.execute(request, body);
        this.logResponse(response);
        return response;
    }

    private void logRequest(HttpRequest request, byte[] body) {
        String loggingBody = new String(body, StandardCharsets.UTF_8);
        loggingBody = loggingBody.isEmpty() ? "{ NoPayload }" : loggingBody;
        LOGGER.info("<RestTemplate request> method: [{}], url: {}, headers: [{}], payload: {}", new Object[]{request.getMethod(), request.getURI(), request.getHeaders(), loggingBody});
    }

    private void logResponse(ClientHttpResponse response) {
        StringBuilder responseBodyBuilder = new StringBuilder();

        try {
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(response.getBody(), StandardCharsets.UTF_8));
            Throwable throwable4 = null;

            try {
                for (String line = bufferedReader.readLine(); line != null; line = bufferedReader.readLine()) {
                    responseBodyBuilder.append(line).append(System.lineSeparator());
                }

                String logginResponse = responseBodyBuilder.toString();
                LOGGER.info("<RestTemplate response> status: ({}: {}), body: {}", new Object[]{response.getStatusCode(), response.getStatusCode().getReasonPhrase(), logginResponse});
            } catch (Throwable throwable17) {
                throwable4 = throwable17;
                throw throwable17;
            } finally {
                if (throwable4 != null) {
                    try {
                        bufferedReader.close();
                    } catch (Throwable throwable16) {
                        throwable4.addSuppressed(throwable16);
                    }
                } else {
                    bufferedReader.close();
                }
            }
        } catch (IOException throwable19) {
            try {
                LOGGER.error("<RestTemplate error> Service Response: ({}: {}). Couldn't read response body. Error: {}", new Object[]{response.getStatusCode(), response.getStatusCode().getReasonPhrase(), throwable19.getLocalizedMessage()});
            } catch (IOException throwable15) {
                LOGGER.error("<RestTemplate error> Couldn't read response body. Error: {}", throwable19.getLocalizedMessage());
            }
        }
    }
}
