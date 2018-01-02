import java.io.IOException;
import java.io.DataInputStream;
import java.net.URL;

// 
// Decompiled by Procyon v0.5.30
// 

public final class c extends Thread
{
    private URL \u00f0;
    private boolean \u00f1;
    private byte[] \u00f2;
    private String \u00f3;
    
    public c(final URL \u00f0) {
        this.\u00f1 = false;
        this.\u00f3 = "";
        this.\u00f1 = false;
        this.\u00f0 = \u00f0;
    }
    
    public boolean k() {
        return this.\u00f1;
    }
    
    public byte[] j() {
        return this.\u00f2;
    }
    
    public void run() {
        try {
            this.\u00f2 = b.d(new DataInputStream(this.\u00f0.openStream()));
        }
        catch (IOException ex) {
            this.\u00f3 = "Cannot load :" + this.\u00f0.toString();
        }
        this.\u00f1 = true;
    }
    
    public String i() {
        return this.\u00f3;
    }
}
