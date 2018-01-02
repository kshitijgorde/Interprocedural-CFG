// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.commons.httpclient.auth;

import org.apache.commons.httpclient.ConnectTimeoutException;

public class LOG
{
    public static boolean isDebugEnabled() {
        return false;
    }
    
    public static boolean isErrorEnabled() {
        return false;
    }
    
    public static boolean isInfoEnabled() {
        return false;
    }
    
    public static boolean isTraceEnabled() {
        return false;
    }
    
    public static boolean isWarnEnabled() {
        return false;
    }
    
    public static void debug(final String string) {
    }
    
    public static void debug(final String string, final ConnectTimeoutException e) {
    }
    
    public static void debug(final String string, final InterruptedException e) {
    }
    
    public static void error(final String string) {
    }
    
    public static void error(final String string, final AuthenticationException e) {
    }
    
    public static void error(final String string, final UnsupportedOperationException e) {
    }
    
    public static void error(final String string, final MalformedChallengeException e) {
    }
    
    public static void info(final String string) {
    }
    
    public static void trace(final String string) {
    }
    
    public static void warn(final String string) {
    }
}
