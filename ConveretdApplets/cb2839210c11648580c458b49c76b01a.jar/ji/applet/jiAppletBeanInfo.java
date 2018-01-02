// 
// Decompiled by Procyon v0.5.30
// 

package ji.applet;

import java.awt.Image;
import java.beans.PropertyDescriptor;
import java.beans.SimpleBeanInfo;

public class jiAppletBeanInfo extends SimpleBeanInfo
{
    Class a;
    String b;
    String c;
    String d;
    String e;
    static /* synthetic */ Class f;
    
    public jiAppletBeanInfo() {
        this.a = ((jiAppletBeanInfo.f == null) ? (jiAppletBeanInfo.f = class$("ji.applet.jiApplet")) : jiAppletBeanInfo.f);
        this.b = "jiBean16.GIF";
        this.c = "jiBean32.GIF";
    }
    
    public PropertyDescriptor[] getPropertyDescriptors() {
        try {
            return null;
        }
        catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }
    
    public Image getIcon(final int n) {
        switch (n) {
            case 1: {
                return (this.b != null) ? this.loadImage(this.b) : null;
            }
            case 2: {
                return (this.c != null) ? this.loadImage(this.c) : null;
            }
            case 3: {
                return (this.d != null) ? this.loadImage(this.d) : null;
            }
            case 4: {
                return (this.e != null) ? this.loadImage(this.e) : null;
            }
            default: {
                return null;
            }
        }
    }
    
    static Class class$(final String s) {
        try {
            return Class.forName(s);
        }
        catch (ClassNotFoundException ex) {
            throw new NoClassDefFoundError(ex.getMessage());
        }
    }
}
