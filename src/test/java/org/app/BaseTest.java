package org.app;

import org.app.libs.RetryOn5xxRes;
import org.app.libs.listeners.ApiTestListener;
import org.junit.jupiter.api.extension.ExtendWith;

@ExtendWith({
        ApiTestListener.class,
        ApiTestListener.class,
        RetryOn5xxRes.class
})
public class BaseTest {
}
