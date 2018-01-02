// 
// Decompiled by Procyon v0.5.30
// 

package com.diginet.digichat.util.filetransfer;

import com.diginet.digichat.common.User;
import com.diginet.digichat.client.h;

public class cb
{
    private static Boolean a;
    private static Boolean b;
    public static boolean c;
    
    public static ag a(final h h, final b6 b6) {
        if (a()) {
            return new cc(h, b6);
        }
        if (b()) {
            ce.a();
            return new cd(h, b6);
        }
        return new cd(h, b6);
    }
    
    public static ag a(final h h, final User user) {
        if (a()) {
            return new cc(h, user);
        }
        if (b()) {
            ce.a();
            return new cd(h, user);
        }
        return new cd(h, user);
    }
    
    public static synchronized boolean a() {
        if (cb.a == null) {
            cb.a = Boolean.FALSE;
            try {
                Class.forName("netscape.security.UserDialogHelper");
                cb.a = Boolean.TRUE;
            }
            catch (Exception ex) {}
            catch (Throwable t) {}
        }
        return cb.a;
    }
    
    public static synchronized boolean b() {
        if (cb.b == null) {
            cb.b = Boolean.FALSE;
            try {
                Class.forName("com.ms.security.PolicyEngine");
                cb.b = Boolean.TRUE;
            }
            catch (Exception ex) {}
            catch (Throwable t) {}
        }
        return cb.b;
    }
    
    static {
        cb.c = false;
        if (b()) {
            cb.c = ce.a();
        }
        if (a()) {
            cb.c = c2.a();
        }
        if (!cb.c && !b() && !a()) {
            try {
                System.getProperty("user.home");
                cb.c = true;
            }
            catch (Exception ex) {}
        }
    }
}
