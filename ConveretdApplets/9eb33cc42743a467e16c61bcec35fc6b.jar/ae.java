import java.awt.event.KeyListener;
import java.awt.Container;
import java.awt.Toolkit;
import java.io.File;

// 
// Decompiled by Procyon v0.5.30
// 

public final class ae implements Runnable
{
    public final ag cv;
    
    public final void run() {
        try {
            (this.cv.gq = new bz(this.cv.fg, this.cv.ff, this.cv.gx, new File(this.cv.gy), this.cv.gv, false)).kl(this.cv.gw);
            this.cv.gq.hk(this.cv.c);
            this.cv.gq.kk(this.cv);
            if (this.cv.g) {
                this.cv.gq.ki(this.cv.gt, this.cv.m);
            }
            else {
                this.cv.gq.kh(this.cv.o, this.cv.m);
            }
            this.cv.gp.setPriority(5);
            Toolkit.getDefaultToolkit().beep();
        }
        catch (Exception ex) {
            this.cv.c.h9("SCP Error: " + ex.getMessage());
            if (ca.mn) {
                System.out.println("SCP Error:");
                ex.printStackTrace();
            }
        }
        this.cv.gl.setText("Copied " + this.cv.gc + " file" + ((this.cv.gc != 1) ? "s" : "") + ".");
        this.cv.gm.setText(String.valueOf(this.cv.fd(this.cv.gg / 1024.0)) + " kB");
        this.cv.gb = true;
        this.cv.gj.setLabel("Done");
        a.a(this.cv.gs, new d(this.cv.gj, this.cv.gj), null);
    }
    
    public ae(final ag cv) {
        this.cv = cv;
    }
}
