// 
// Decompiled by Procyon v0.5.30
// 

package octoshape.applets.init;

import netscape.javascript.JSObject;
import java.applet.Applet;

public class OctoTestApplet extends Applet
{
    private AppletDebug debug;
    private JSObject jso;
    private static final long serialVersionUID = 1L;
    
    public void init() {
        final String parameter = this.getParameter("display");
        final String parameter2 = this.getParameter("callbackstring");
        String parameter3 = this.getParameter("initfunction");
        if (parameter3 == null) {
            parameter3 = "octopv_initAppletInited";
        }
        this.jso = JSObject.getWindow((Applet)this);
        this.debug = new AppletDebug(parameter2, parameter, this.jso);
        final String property = System.getProperty("java.version");
        this.jso.call(parameter3, (Object[])new String[] { parameter2, new StringBuffer(String.valueOf(property.charAt(2))).toString(), (property.length() >= 5) ? new StringBuffer(String.valueOf(property.charAt(4))).toString() : "0", (property.indexOf("_") != -1) ? property.substring(property.indexOf("_") + 1) : "0", System.getProperty("os.version") });
        this.debug.printInfo();
    }
    
    public void start() {
        this.debug.print("start test applet called");
    }
}
