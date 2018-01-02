// 
// Decompiled by Procyon v0.5.30
// 

package com.diginet.digichat.util.filetransfer;

import com.diginet.digichat.common.j;
import com.diginet.digichat.client.i;

public class bw
{
    private static Boolean a;
    private static Boolean b;
    public static boolean c;
    
    public static ab a(final i i, final bu bu) {
        if (a()) {
            return new bx(i, bu);
        }
        if (b()) {
            bz.a();
            return new by(i, bu);
        }
        return new by(i, bu);
    }
    
    public static ab a(final i i, final j j) {
        if (a()) {
            return new bx(i, j);
        }
        if (b()) {
            bz.a();
            return new by(i, j);
        }
        return new by(i, j);
    }
    
    public static synchronized boolean a() {
        if (bw.a == null) {
            bw.a = Boolean.FALSE;
            try {
                Class.forName("netscape.security.UserDialogHelper");
                bw.a = Boolean.TRUE;
            }
            catch (Exception ex) {}
            catch (Throwable t) {}
        }
        return bw.a;
    }
    
    public static synchronized boolean b() {
        if (bw.b == null) {
            bw.b = Boolean.FALSE;
            try {
                Class.forName("com.ms.security.PolicyEngine");
                bw.b = Boolean.TRUE;
            }
            catch (Exception ex) {}
            catch (Throwable t) {}
        }
        return bw.b;
    }
    
    static {
        bw.c = false;
        if (b()) {
            bw.c = bz.a();
        }
        if (a()) {
            bw.c = cg.a();
        }
        if (!bw.c && !b() && !a()) {
            try {
                System.getProperty("user.home");
                bw.c = true;
            }
            catch (Exception ex) {}
        }
    }
}
