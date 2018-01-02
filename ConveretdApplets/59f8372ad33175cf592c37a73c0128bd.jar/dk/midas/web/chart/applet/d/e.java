// 
// Decompiled by Procyon v0.5.30
// 

package dk.midas.web.chart.applet.d;

import netscape.javascript.JSObject;
import java.applet.Applet;

public class e extends b
{
    private Applet hb;
    
    public e(final Applet hb) {
        this.hb = hb;
    }
    
    public boolean isApplication() {
        return false;
    }
    
    public String getParameter(final String s) {
        return this.hb.getParameter(s);
    }
    
    public void setPersistentString(final String s) {
        final String parameter = this.getParameter("callback");
        if (parameter == null) {
            return;
        }
        final JSObject window = JSObject.getWindow(this.hb);
        if (window == null) {
            return;
        }
        final Object[] array = { s };
        try {
            window.call(parameter, array);
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
