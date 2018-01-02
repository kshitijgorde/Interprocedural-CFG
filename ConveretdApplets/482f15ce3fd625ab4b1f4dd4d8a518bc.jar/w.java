import java.net.URL;

// 
// Decompiled by Procyon v0.5.30
// 

public final class w extends Thread
{
    private aE a;
    private au a;
    private String a;
    private String b;
    
    public w(final au a, final aE a2, final String a3, final String b) {
        this.a = a;
        this.a = a2;
        this.a = a3;
        this.b = b;
    }
    
    public final void run() {
        this.a.a(true);
        this.a.a("Downloading '" + this.b + "'", 1200, 0);
        try {
            final O a = this.a.a;
            this.a.a(new URL("http", a.b, a.c + "?id=" + this.a).toString());
            this.a.a("Downloading '" + this.b + "' successful...", 8, 0);
        }
        catch (Exception ex) {
            this.a.a("Downloading '" + this.b + "' failed", true);
        }
    }
}
