// 
// Decompiled by Procyon v0.5.30
// 

package netscape.javascript;

import java.applet.AppletContext;
import sun.plugin.javascript.JSContext;
import java.applet.Applet;

public abstract class JSObject
{
    public abstract Object call(final String p0, final Object[] p1) throws a;
    
    public abstract Object eval(final String p0) throws a;
    
    public abstract Object getMember(final String p0) throws a;
    
    public abstract void setMember(final String p0, final Object p1) throws a;
    
    public abstract void removeMember(final String p0) throws a;
    
    public abstract Object getSlot(final int p0) throws a;
    
    public abstract void setSlot(final int p0, final Object p1) throws a;
    
    public static JSObject getWindow(final Applet applet) throws a {
        try {
            if (applet != null) {
                applet.getParameter("MAYSCRIPT");
                final AppletContext appletContext = applet.getAppletContext();
                JSObject jsObject = null;
                if (appletContext instanceof JSContext) {
                    jsObject = ((JSContext)appletContext).getJSObject();
                }
                if (jsObject != null) {
                    return jsObject;
                }
            }
        }
        catch (Throwable t) {
            throw (a)new a(6, t).initCause(t);
        }
        throw new a();
    }
}
