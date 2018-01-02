// 
// Decompiled by Procyon v0.5.30
// 

package org.slf4j;

import org.slf4j.helpers.Util;
import org.slf4j.impl.StaticMDCBinder;
import java.util.Map;
import org.slf4j.spi.MDCAdapter;

public class MDC
{
    static final String NULL_MDCA_URL = "http://www.slf4j.org/codes.html#null_MDCA";
    static final String NO_STATIC_MDC_BINDER_URL = "http://www.slf4j.org/codes.html#no_static_mdc_binder";
    static MDCAdapter mdcAdapter;
    
    public static void put(final String key, final String val) throws IllegalArgumentException {
        if (key == null) {
            throw new IllegalArgumentException("key parameter cannot be null");
        }
        if (MDC.mdcAdapter == null) {
            throw new IllegalStateException("MDCAdapter cannot be null. See also http://www.slf4j.org/codes.html#null_MDCA");
        }
        MDC.mdcAdapter.put(key, val);
    }
    
    public static String get(final String key) throws IllegalArgumentException {
        if (key == null) {
            throw new IllegalArgumentException("key parameter cannot be null");
        }
        if (MDC.mdcAdapter == null) {
            throw new IllegalStateException("MDCAdapter cannot be null. See also http://www.slf4j.org/codes.html#null_MDCA");
        }
        return MDC.mdcAdapter.get(key);
    }
    
    public static void remove(final String key) throws IllegalArgumentException {
        if (key == null) {
            throw new IllegalArgumentException("key parameter cannot be null");
        }
        if (MDC.mdcAdapter == null) {
            throw new IllegalStateException("MDCAdapter cannot be null. See also http://www.slf4j.org/codes.html#null_MDCA");
        }
        MDC.mdcAdapter.remove(key);
    }
    
    public static void clear() {
        if (MDC.mdcAdapter == null) {
            throw new IllegalStateException("MDCAdapter cannot be null. See also http://www.slf4j.org/codes.html#null_MDCA");
        }
        MDC.mdcAdapter.clear();
    }
    
    public static Map getCopyOfContextMap() {
        if (MDC.mdcAdapter == null) {
            throw new IllegalStateException("MDCAdapter cannot be null. See also http://www.slf4j.org/codes.html#null_MDCA");
        }
        return MDC.mdcAdapter.getCopyOfContextMap();
    }
    
    public static void setContextMap(final Map contextMap) {
        if (MDC.mdcAdapter == null) {
            throw new IllegalStateException("MDCAdapter cannot be null. See also http://www.slf4j.org/codes.html#null_MDCA");
        }
        MDC.mdcAdapter.setContextMap(contextMap);
    }
    
    public static MDCAdapter getMDCAdapter() {
        return MDC.mdcAdapter;
    }
    
    static {
        try {
            MDC.mdcAdapter = StaticMDCBinder.SINGLETON.getMDCA();
        }
        catch (NoClassDefFoundError ncde) {
            final String msg = ncde.getMessage();
            if (msg != null && msg.indexOf("org/slf4j/impl/StaticMDCBinder") != -1) {
                Util.reportFailure("Failed to load class \"org.slf4j.impl.StaticMDCBinder\".");
                Util.reportFailure("See http://www.slf4j.org/codes.html#no_static_mdc_binder for further details.");
            }
            throw ncde;
        }
        catch (Exception e) {
            Util.reportFailure("Could not bind with an instance of class [" + StaticMDCBinder.SINGLETON.getMDCAdapterClassStr() + "]", e);
        }
    }
}
