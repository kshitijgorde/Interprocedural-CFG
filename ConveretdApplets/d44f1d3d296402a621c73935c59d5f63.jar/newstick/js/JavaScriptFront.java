// 
// Decompiled by Procyon v0.5.30
// 

package newstick.js;

import java.applet.Applet;
import java.lang.reflect.Method;

public class JavaScriptFront
{
    private static boolean enabled;
    private static Class jsObjectClass;
    private static Method jsObjectGetwindowMethod;
    private static Method jsObjectCallMethod;
    protected static Throwable errorException;
    private Object jsWindow;
    private static /* synthetic */ Class class$java$applet$Applet;
    private static /* synthetic */ Class class$java$lang$String;
    private static /* synthetic */ Class array$Ljava$lang$Object;
    
    public void jsInvoke(final String s, final Object[] array) {
        try {
            if (JavaScriptFront.enabled) {
                JavaScriptFront.jsObjectCallMethod.invoke(this.jsWindow, new String(s), array);
            }
        }
        catch (Exception errorException) {
            (JavaScriptFront.errorException = errorException).getMessage();
        }
    }
    
    public static boolean isEnabled() {
        return JavaScriptFront.enabled;
    }
    
    public JavaScriptFront(final Applet applet) {
        try {
            this.jsWindow = JavaScriptFront.jsObjectGetwindowMethod.invoke(null, applet);
        }
        catch (Throwable errorException) {
            JavaScriptFront.enabled = false;
            JavaScriptFront.errorException = errorException;
        }
    }
    
    static {
        JavaScriptFront.errorException = null;
        try {
            JavaScriptFront.jsObjectClass = Class.forName("netscape.javascript.JSObject");
            if (JavaScriptFront.jsObjectClass != null) {
                JavaScriptFront.jsObjectGetwindowMethod = JavaScriptFront.jsObjectClass.getMethod("getWindow", (JavaScriptFront.class$java$applet$Applet != null) ? JavaScriptFront.class$java$applet$Applet : (JavaScriptFront.class$java$applet$Applet = class$("java.applet.Applet")));
                JavaScriptFront.jsObjectCallMethod = JavaScriptFront.jsObjectClass.getMethod("call", (JavaScriptFront.class$java$lang$String != null) ? JavaScriptFront.class$java$lang$String : (JavaScriptFront.class$java$lang$String = class$("java.lang.String")), (JavaScriptFront.array$Ljava$lang$Object != null) ? JavaScriptFront.array$Ljava$lang$Object : (JavaScriptFront.array$Ljava$lang$Object = class$("[Ljava.lang.Object;")));
                if (JavaScriptFront.jsObjectClass != null) {
                    JavaScriptFront.enabled = true;
                }
            }
        }
        catch (Throwable errorException) {
            JavaScriptFront.errorException = errorException;
            JavaScriptFront.jsObjectClass = null;
            JavaScriptFront.enabled = false;
        }
    }
    
    private static /* synthetic */ Class class$(final String s) {
        try {
            return Class.forName(s);
        }
        catch (ClassNotFoundException ex) {
            throw new NoClassDefFoundError(ex.getMessage());
        }
    }
}
