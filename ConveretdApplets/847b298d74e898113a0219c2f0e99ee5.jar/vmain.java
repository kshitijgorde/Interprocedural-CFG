import java.applet.Applet;

// 
// Decompiled by Procyon v0.5.30
// 

public class vmain extends Applet
{
    public String jver;
    
    public vmain() {
        try {
            this.jver = System.getProperty("java.version");
        }
        catch (Throwable t) {}
    }
}
