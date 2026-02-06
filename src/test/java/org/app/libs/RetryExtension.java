package org.app.libs;

import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.api.extension.InvocationInterceptor;
import org.junit.jupiter.api.extension.ReflectiveInvocationContext;

import java.lang.reflect.Method;

public class RetryExtension implements InvocationInterceptor {

    private static final int MAX_RETRIES = 3;
    private static final long DELAY = 1000;

    @Override
    public void interceptTestMethod(Invocation<Void> invocation,
            ReflectiveInvocationContext<Method> invocationContext,
            ExtensionContext extensionContext) throws Throwable {
        Throwable lastThrowable = null;

        for (int i = 0; i < MAX_RETRIES; i++) {
            try {
                if (i > 0) {
                    System.out
                            .println("[Retry] Attempt " + (i + 1) + " for test: " + extensionContext.getDisplayName());

                    // Manually invoke the method on subsequent attempts because
                    // invocation.proceed()
                    // can only be called once.
                    Method method = invocationContext.getExecutable();
                    Object target = invocationContext.getTarget().orElseThrow();
                    method.setAccessible(true);
                    method.invoke(target);
                } else {
                    // First attempt uses JUnit's invocation wrapper
                    invocation.proceed();
                }
                return; // Success!
            } catch (java.lang.AssertionError t) {
                lastThrowable = t;
                handleFailure(i, t, extensionContext);
            } catch (java.lang.reflect.InvocationTargetException e) {
                lastThrowable = e.getTargetException();
                handleFailure(i, lastThrowable, extensionContext);
            } catch (Throwable t) {
                lastThrowable = t;
                handleFailure(i, t, extensionContext);
            }
        }
        throw lastThrowable;
    }

    private void handleFailure(int attempt, Throwable t, ExtensionContext context) throws Throwable {
        System.out.println("[Retry] Attempt " + (attempt + 1) + " failed: " + t.getMessage());
        if (attempt < MAX_RETRIES - 1) {
            try {
                Thread.sleep(DELAY);
            } catch (InterruptedException ie) {
                Thread.currentThread().interrupt();
                throw t;
            }
        }
    }
}
