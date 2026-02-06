package org.app.libs;

import io.restassured.response.Response;
import org.app.libs.store.ApiContext;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.api.extension.InvocationInterceptor;
import org.junit.jupiter.api.extension.ReflectiveInvocationContext;

import java.lang.reflect.Method;

public class RetryOn5xxRes implements InvocationInterceptor {
    private static final int MAX_RETRIES = 2;
    @Override
    public void interceptTestMethod(
            Invocation<Void> invocation,
            ReflectiveInvocationContext<Method> invocationContext,
            ExtensionContext extensionContext)
            throws Throwable {
        int attempt = 0;

        while (attempt <= MAX_RETRIES) {

            try {
                invocation.proceed(); // execute test
                return; // success

            } catch (Throwable throwable) {

                Response response =
                        ApiContext.getResponse();

                if (response == null) {
                    throw throwable;
                }

                int status = response.getStatusCode();

                if (status >= 500 ) {

                    attempt++;

                    System.out.println(
                            "🔁 Retrying test → Attempt "
                                    + attempt +
                                    " Status: " + status
                    );

                    if (attempt > MAX_RETRIES) {
                        throw throwable;
                    }

                } else {
                    // Not server error → no retry
                    throw throwable;
                }
            }
        }
    }
}
