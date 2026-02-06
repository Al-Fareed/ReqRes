package org.app.libs;

import io.restassured.filter.Filter;
import io.restassured.filter.FilterContext;
import io.restassured.response.Response;
import io.restassured.specification.FilterableRequestSpecification;
import io.restassured.specification.FilterableResponseSpecification;

public class RetryOn5xxRes implements Filter {
    private final int maxRetries;
    private final long delay;

    public RetryOn5xxRes(int maxRetries, long delay) {
        this.maxRetries = maxRetries;
        this.delay = delay;
    }

    public RetryOn5xxRes() {
        this(3, 1000); // Default: 3 retries with 1 second delay
    }

    @Override
    public Response filter(FilterableRequestSpecification requestSpec, FilterableResponseSpecification responseSpec,
            FilterContext ctx) {
        Response response = ctx.next(requestSpec, responseSpec);
        int retryCount = 0;

        while (response != null && response.getStatusCode() >= 400 && retryCount < maxRetries) {
            retryCount++;
            System.out.println("[API Retry] Received " + response.getStatusCode() + " from " + requestSpec.getURI()
                    + ". Retrying attempt " + retryCount + " after " + delay + "ms...");

            try {
                Thread.sleep(delay);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                break;
            }

            response = ctx.next(requestSpec, responseSpec);
        }

        if (response == null) {
            System.out.println("[API Retry] Response was null after " + retryCount + " attempts.");
        }

        return response;
    }
}
