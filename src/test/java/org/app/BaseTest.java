package org.app;

import org.app.libs.RetryExtension;
import org.app.libs.listeners.ApiTestListener;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;

@ExtendWith({
        ApiTestListener.class,
        RetryExtension.class
})
public class BaseTest {
    @BeforeEach
    void before() {
        System.out.println("Started test");
    }
}
