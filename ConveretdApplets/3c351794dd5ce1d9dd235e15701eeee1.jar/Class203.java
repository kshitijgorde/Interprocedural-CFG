import netscape.javascript.JSObject;
import java.applet.Applet;

// 
// Decompiled by Procyon v0.5.30
// 

final class Class203
{
    static final Object method2704(final String s, final Applet applet, final int n) throws Throwable {
        try {
            if (n != -26978) {
                return null;
            }
            return JSObject.getWindow(applet).call(s, (Object[])null);
        }
        catch (RuntimeException ex) {
            throw ex;
        }
    }
    
    static final Object method2705(final int n, final Applet applet, final String s, final Object[] array) throws Throwable {
        try {
            if (n != 26635) {
                return null;
            }
            return JSObject.getWindow(applet).call(s, array);
        }
        catch (RuntimeException ex) {
            throw ex;
        }
    }
    
    static final void method2706(final Applet applet, final String s, final int n) throws Throwable {
        try {
            JSObject.getWindow(applet).eval(s);
            if (n != 9202) {
                return;
            }
        }
        catch (RuntimeException ex) {
            throw ex;
        }
    }
}
