// 
// Decompiled by Procyon v0.5.30
// 

package org.eclipse.swt.internal.mozilla.init;

import org.eclipse.swt.internal.Platform;

public class XPCOMInit extends Platform
{
    public static final int PATH_MAX = 4096;
    
    public static final native int GREVersionRange_sizeof();
    
    public static final native int _GRE_GetGREPathWithProperties(final GREVersionRange p0, final int p1, final int p2, final int p3, final int p4, final int p5);
    
    public static final int GRE_GetGREPathWithProperties(final GREVersionRange greVersionRange, final int n, final int n2, final int n3, final int n4, final int n5) {
        XPCOMInit.lock.lock();
        try {
            return _GRE_GetGREPathWithProperties(greVersionRange, n, n2, n3, n4, n5);
        }
        finally {
            XPCOMInit.lock.unlock();
        }
    }
    
    public static final native int _XPCOMGlueStartup(final byte[] p0);
    
    public static final int XPCOMGlueStartup(final byte[] array) {
        XPCOMInit.lock.lock();
        try {
            return _XPCOMGlueStartup(array);
        }
        finally {
            XPCOMInit.lock.unlock();
        }
    }
    
    public static final native int _XPCOMGlueShutdown();
    
    public static final int XPCOMGlueShutdown() {
        XPCOMInit.lock.lock();
        try {
            return _XPCOMGlueShutdown();
        }
        finally {
            XPCOMInit.lock.unlock();
        }
    }
}
