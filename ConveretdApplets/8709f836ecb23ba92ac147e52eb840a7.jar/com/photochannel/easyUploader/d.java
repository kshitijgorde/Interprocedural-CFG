// 
// Decompiled by Procyon v0.5.30
// 

package com.photochannel.easyUploader;

import netscape.javascript.JSObject;
import java.lang.reflect.InvocationTargetException;
import java.security.AccessControlException;
import java.lang.reflect.Method;

public class d
{
    private Object a;
    private Method b;
    private static /* synthetic */ boolean c;
    
    public d(final Object a) {
        if (a == null) {
            throw new RuntimeException("Can't access host web page.");
        }
        try {
            Method[] methods;
            for (int length = (methods = a.getClass().getMethods()).length, i = 0; i < length; ++i) {
                final Method b;
                if ((b = methods[i]).toString().contains(".call")) {
                    this.b = b;
                    break;
                }
            }
            if (this.b == null) {
                throw new RuntimeException("error: 'call' not found -- cannot access JS on host webpage");
            }
        }
        catch (AccessControlException ex) {
            System.out.println("TRAPPED: java.security.AccessControlException during reflection");
        }
        this.a = a;
    }
    
    private Object a(final String s, final Object[] array) {
        if (!d.c && s.length() <= 0) {
            throw new AssertionError();
        }
        try {
            return this.b.invoke(this.a, s, array);
        }
        catch (IllegalArgumentException ex2) {
            final IllegalArgumentException ex = ex2;
            ex2.printStackTrace();
            throw new RuntimeException(ex);
        }
        catch (IllegalAccessException ex4) {
            final IllegalAccessException ex3 = ex4;
            ex4.printStackTrace();
            throw new RuntimeException(ex3);
        }
        catch (InvocationTargetException ex6) {
            final InvocationTargetException ex5 = ex6;
            ex6.printStackTrace();
            throw new RuntimeException(ex5);
        }
    }
    
    public final void a(final String s, final String s2) {
        this.a("onUploadComplete", new Object[] { s, s2 });
    }
    
    public final void a(final String s) {
        if (!d.c && (s == null || s.length() <= 0)) {
            throw new AssertionError();
        }
        if (this.b == null) {
            ((JSObject)this.a).call("stateChangeEvent", new Object[] { s });
            return;
        }
        this.a("stateChangeEvent", new Object[] { s });
    }
    
    public final void b(final String s) {
        if (!d.c && s == null) {
            throw new AssertionError();
        }
        this.a("saveDirectoryCookie", new Object[] { s });
    }
    
    public final String a() {
        final Object a;
        if ((a = this.a("eatDirectoryCookie", (Object[])null)) instanceof String) {
            return (String)a;
        }
        return null;
    }
    
    public final void b() {
        this.a("pressUploadButton", (Object[])null);
    }
    
    public final void c() {
        this.a("launchHelpPopUp", (Object[])null);
    }
    
    static {
        d.c = !d.class.desiredAssertionStatus();
    }
}
