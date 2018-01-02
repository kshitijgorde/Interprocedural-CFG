// 
// Decompiled by Procyon v0.5.30
// 

package org.slf4j.helpers;

import org.slf4j.Logger;
import java.util.ArrayList;
import java.util.List;
import org.slf4j.ILoggerFactory;

public class SubstituteLoggerFactory implements ILoggerFactory
{
    final List a;
    
    public SubstituteLoggerFactory() {
        this.a = new ArrayList();
    }
    
    public Logger a(final String s) {
        this.a.add(s);
        return NOPLogger.b;
    }
    
    public List a() {
        return this.a;
    }
}
