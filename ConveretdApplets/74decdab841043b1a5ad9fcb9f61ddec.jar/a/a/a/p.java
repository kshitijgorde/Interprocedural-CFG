// 
// Decompiled by Procyon v0.5.30
// 

package a.a.a;

import java.applet.Applet;
import netscape.javascript.JSObject;

public final class p implements h
{
    private JSObject a;
    private t b;
    
    public p(final Applet applet) {
        try {
            (this.b = new t("JSCallBack")).a(false);
            if (applet != null) {
                this.a = JSObject.getWindow(applet);
                if (this.a == null) {
                    System.err.println("Failed to get mainWindow for javascript.");
                }
            }
        }
        catch (Exception ex) {
            System.err.println("Exception while getting mainWindow for javascript: " + ex.getMessage());
        }
    }
    
    public final void a() {
        if (this.b != null) {
            this.b.a();
        }
        this.a = null;
        this.b = null;
    }
    
    public final Object a(String s, String... array) {
        final p p2 = this;
        final String s2 = s;
        array = array;
        s = s2;
        this = p2;
        if (p2.b != null) {
            this.b.a(new m(this, s, false, array));
        }
        return null;
    }
}
