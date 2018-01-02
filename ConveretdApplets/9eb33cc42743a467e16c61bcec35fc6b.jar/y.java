import java.io.IOException;
import java.io.OutputStream;
import java.io.BufferedOutputStream;
import java.io.InputStream;
import java.io.BufferedInputStream;
import java.net.Socket;

// 
// Decompiled by Procyon v0.5.30
// 

public class y implements b6
{
    public int fw;
    public int fv;
    public boolean fu;
    public boolean ft;
    public boolean fs;
    public boolean fr;
    public b7 fq;
    public Socket fp;
    public s fo;
    public ak fl;
    public at fk;
    public String fj;
    
    public y(final Socket fp, final int fw, final int fv, final b7 fq) throws IOException {
        this.fp = fp;
        this.fw = fw;
        this.fv = fv;
        this.fq = fq;
        this.fu = false;
        this.ft = false;
        this.fs = false;
        this.fr = false;
        if (fp != null) {
            try {
                this.fl = new ak(new BufferedInputStream(fp.getInputStream(), 8192), fw);
                this.fo = new s(new BufferedOutputStream(fp.getOutputStream()), fw);
            }
            catch (Exception ex) {
                throw new IOException("Could not create tunnel: " + ex.toString());
            }
            this.fk = this.fo.d1();
            this.fl.fi(new av(23, fq.f7));
            this.fo.l4(this);
            this.fl.l4(this);
        }
    }
    
    public final String el() {
        if (this.fp != null) {
            return this.fp.getLocalAddress().getHostAddress();
        }
        return "N/A";
    }
    
    public final boolean ek() {
        return this.fv != -4;
    }
    
    public final boolean ej(final int fv) {
        if (this.ek()) {
            return false;
        }
        this.fv = fv;
        return true;
    }
    
    public final void ei() {
        this.fo.start();
        this.fl.start();
    }
    
    public final void eh() {
        if (this.fp != null) {
            try {
                this.fp.close();
            }
            catch (IOException ex) {}
        }
    }
    
    public final ay eg(final ay ay) throws IOException {
        ((av)ay).writeInt(this.fv);
        return ay;
    }
    
    public void ef(final ay ay) {
        this.fq.ee(ay);
    }
    
    public void ee(final ay ay) {
        this.fk.hr(ay);
    }
    
    public final void ed(final b8 b8) {
        if (b8 == null || b8 instanceof s) {
            this.ea();
            try {
                this.fp.close();
            }
            catch (IOException ex) {
                this.fq.h9("Error closing socket for: " + this.fw + " : " + ex.toString());
            }
        }
        else {
            this.d9();
        }
        this.eb();
    }
    
    public final synchronized void ec() {
        this.ed(null);
    }
    
    public final synchronized void eb() {
        if (this.fu && this.ft && this.fs && this.fr) {
            this.fq.ly(this.fw);
            if (this.fo != null && this.fo.isAlive()) {
                this.fo.stop();
            }
            if (this.fl != null && this.fl.isAlive()) {
                this.fl.stop();
            }
        }
    }
    
    public final void ea() {
        if (this.ft) {
            return;
        }
        try {
            final av av = new av(25, this.fq.f7);
            av.writeInt(this.fv);
            this.fq.ee(av);
            this.ft = true;
        }
        catch (Exception ex) {
            this.fq.h9("Error sending output-closed: " + ex.toString());
        }
    }
    
    public final void d9() {
        if (this.fu) {
            return;
        }
        try {
            final av av = new av(24, this.fq.f7);
            av.writeInt(this.fv);
            this.fq.ee(av);
            this.fu = true;
        }
        catch (Exception ex) {
            this.fq.h9("Error sending input-EOF: " + ex.toString());
        }
    }
    
    public final void d8() {
        if (this.fl != null) {
            this.fl.stop();
        }
        this.fr = true;
        this.d9();
        this.eb();
    }
    
    public final void d7() {
        if (this.fo != null) {
            this.fo.d0();
        }
        this.fs = true;
        this.eb();
    }
    
    public final void d6(final String fj) {
        this.fj = fj;
    }
    
    public String d5() {
        if (this.fp != null) {
            return String.valueOf(this.fp.getInetAddress().getHostAddress()) + ":" + this.fp.getPort() + " <--> " + this.el() + ":" + this.fp.getLocalPort() + " <-ssh-> " + this.fj;
        }
        return "< N/A >";
    }
}
