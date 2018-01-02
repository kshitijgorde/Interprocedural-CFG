// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.log4j;

import java.util.Hashtable;

public class MDC
{
    static final MDC mdc;
    static final int HT_SIZE = 7;
    
    public static Object get(final String s) {
        return MDC.mdc.get0(s);
    }
    
    public static void remove(final String s) {
        MDC.mdc.remove0(s);
    }
    
    public static Hashtable getContext() {
        return MDC.mdc.getContext0();
    }
    
    private void put0(final String s, final Object o) {
    }
    
    private Object get0(final String s) {
        return null;
    }
    
    private void remove0(final String s) {
    }
    
    private Hashtable getContext0() {
        return null;
    }
    
    static {
        mdc = new MDC();
    }
}
