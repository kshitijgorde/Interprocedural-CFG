// 
// Decompiled by Procyon v0.5.30
// 

package com.q10.util;

import netscape.javascript.JSObject;
import java.applet.Applet;

public class Link
{
    private String param;
    private Applet applet;
    private String[] args;
    
    public Link(final String param, final Applet applet) {
        this.param = param;
        this.applet = applet;
    }
    
    public void disparaLink(final String s) {
        final JSObject window = JSObject.getWindow(this.applet);
        final String[] array = { this.param };
        window.call(s, (Object[])((this.args == null) ? array : this.args));
    }
    
    public void setParametros(final String[] array) {
        System.arraycopy(array, 0, this.args = new String[array.length], 0, array.length);
    }
}
