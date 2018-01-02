// 
// Decompiled by Procyon v0.5.30
// 

package org.slf4j.impl;

import org.slf4j.Logger;
import java.util.HashMap;
import java.util.Map;
import org.slf4j.ILoggerFactory;

public class SimpleLoggerFactory implements ILoggerFactory
{
    static final SimpleLoggerFactory a;
    Map b;
    
    public SimpleLoggerFactory() {
        this.b = new HashMap();
    }
    
    public Logger a(final String s) {
        Logger logger = null;
        synchronized (this) {
            logger = this.b.get(s);
            if (logger == null) {
                logger = new SimpleLogger(s);
                this.b.put(s, logger);
            }
        }
        return logger;
    }
    
    static {
        a = new SimpleLoggerFactory();
    }
}
