package org.nnn4eu.nfische.dynamicproxy.framework;
public class ProxyInvocationFailedException extends RuntimeException {
    public ProxyInvocationFailedException(String message, Throwable cause) {
        super(message, cause);
    }
}