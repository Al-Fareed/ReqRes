package org.app.libs.listeners;

import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.api.extension.TestWatcher;

public class ApiTestListener implements TestWatcher {
    @Override
    public void testSuccessful(ExtensionContext context) {
        System.out.println("==✅ Test Passed==");
    }

    @Override
    public void testFailed(ExtensionContext context, Throwable cause) {
        System.out.println("==❌ Test Failed==");
        TestWatcher.super.testFailed(context, cause);
    }

    @Override
    public void testAborted(ExtensionContext context, Throwable cause) {
        System.out.println("==⏭ TEST ABORTED==");
        TestWatcher.super.testAborted(context, cause);
    }
}
