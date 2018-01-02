import java.io.IOException;
import java.applet.Applet;
import java.io.InputStream;

// 
// Decompiled by Procyon v0.5.30
// 

class C23 extends InputStream
{
    InputStream a;
    String b;
    int c;
    Applet d;
    int e;
    
    public C23(final InputStream a, final int e, final Applet d) {
        this.a = a;
        this.e = e;
        this.d = d;
        this.c = 0;
    }
    
    public int read() throws IOException {
        ++this.c;
        if (this.c % 5000 == 0 && this.d != null) {
            this.b = "Loading Floorplan...";
            if (this.e > 0) {
                this.b = this.b + 100 * this.c / this.e + "%";
            }
            this.d.showStatus(this.b);
        }
        return this.a.read();
    }
}
