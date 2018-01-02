import java.applet.Applet;

// 
// Decompiled by Procyon v0.5.30
// 

public class B extends Applet
{
    public String getVersion() {
        return System.getProperty("java.version");
    }
    
    public String getVendor() {
        return System.getProperty("java.vendor");
    }
}
