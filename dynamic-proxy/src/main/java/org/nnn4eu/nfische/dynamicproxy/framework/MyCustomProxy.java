package org.nnn4eu.nfische.dynamicproxy.framework;

import java.util.List;

public class MyCustomProxy {
    private List<Class<?>> interfaces;
    private Object proxy;

    public MyCustomProxy(List<Class<?>> interfaces, Object proxy) {
        this.interfaces = interfaces;
        this.proxy = proxy;
    }

    public Object getJdkProxy() {
        return proxy;
    }

    public boolean hasInterface(Class<?> expectedInterface) {
        return interfaces.contains(expectedInterface);
    }
}