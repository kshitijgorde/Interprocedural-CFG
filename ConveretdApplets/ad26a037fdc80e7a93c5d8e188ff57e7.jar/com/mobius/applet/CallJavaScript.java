// 
// Decompiled by Procyon v0.5.30
// 

package com.mobius.applet;

import netscape.javascript.JSObject;
import java.applet.Applet;

public class CallJavaScript
{
    Applet applet;
    
    public CallJavaScript(final Applet applet) {
        this.applet = applet;
    }
    
    public void execute(final String s, final Object[] array) {
        try {
            final JSObject window = JSObject.getWindow(this.applet);
            if (window != null) {
                synchronized (window) {
                    if (array != null) {
                        window.call(s, array);
                    }
                    else {
                        window.eval(s + "()");
                    }
                }
            }
        }
        catch (Exception ex) {
            System.out.println("Could not get handle to JavaScript methods");
            ex.printStackTrace();
        }
    }
    
    public String getMember(final String s) {
        try {
            final JSObject window = JSObject.getWindow(this.applet);
            if (window != null) {
                synchronized (window) {
                    return (String)window.getMember(s);
                }
            }
        }
        catch (Exception ex) {
            System.out.println("Could not get handle to JavaScript variables");
            ex.printStackTrace();
        }
        return "";
    }
}
