// 
// Decompiled by Procyon v0.5.30
// 

package sexy.util;

import java.applet.Applet;
import netscape.javascript.JSObject;
import sexy.gui.SexyApplet;

public class AsyncJSCall implements Runnable
{
    SexyApplet mApplet;
    String mMethod;
    Object[] mParams;
    
    public AsyncJSCall(final SexyApplet mApplet, final String mMethod, final Object[] mParams) {
        this.mApplet = mApplet;
        this.mMethod = mMethod;
        this.mParams = mParams;
        new Thread(this).start();
    }
    
    public void run() {
        try {
            if (this.mApplet.mJSObject == null) {
                this.mApplet.mJSObject = JSObject.getWindow((Applet)this.mApplet);
            }
            ((JSObject)this.mApplet.mJSObject).call(this.mMethod, this.mParams);
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
