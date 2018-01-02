import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.ClipboardOwner;
import java.awt.datatransfer.Transferable;
import java.awt.datatransfer.StringSelection;
import java.awt.Frame;
import java.io.IOException;
import java.io.Reader;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.awt.datatransfer.Clipboard;
import java.awt.Container;
import java.awt.Toolkit;

// 
// Decompiled by Procyon v0.5.30
// 

public final class ab implements bw
{
    public static Toolkit ga;
    public by f9;
    public Container f8;
    public bq e9;
    public h d4;
    public ci f7;
    public String f6;
    public boolean f5;
    public boolean f4;
    public Boolean f3;
    public boolean f2;
    public boolean f1;
    public boolean f0;
    public String f_;
    public boolean fz;
    public static Clipboard fy;
    public z fx;
    
    public ab() {
        this.f5 = false;
        this.f4 = false;
        this.f3 = new Boolean(false);
        this.f7 = null;
        this.fz = false;
        this.f9 = null;
    }
    
    public final boolean e5() {
        return this.fz;
    }
    
    public final void du(final h d4) {
        this.d4 = d4;
        if (d4 != null) {
            d4.bv(this);
            d4.bu(this);
        }
    }
    
    public final void e4(final bq e9) {
        this.e9 = e9;
    }
    
    public final void e3(final Container f8) {
        this.f8 = f8;
        if (ab.ga == null) {
            ab.ga = Toolkit.getDefaultToolkit();
        }
    }
    
    public final void e2(final String f6) {
        this.f6 = f6;
    }
    
    public final boolean e1() {
        return this.f9 != null;
    }
    
    public final void e0(final boolean b) {
        if (b) {
            try {
                (this.f9 = (by)Class.forName("mindbright.ssh.SSHCommandShellImpl").newInstance()).j6(this);
            }
            catch (Throwable t) {
                this.er("");
                this.er("The local command-shell is not available in this distribution.");
            }
        }
        else {
            this.f9 = null;
        }
    }
    
    public final void e_() {
        this.f4 = true;
    }
    
    public final void ez(final String s) {
        if (this.f1) {
            synchronized (this.f3) {
                this.fx = new z(s);
                this.f3.notify();
            }
            // monitorexit(this.f3)
        }
    }
    
    public final String ey(final String f_) {
        synchronized (this.f3) {
            if (f_ != null) {
                this.f_ = f_;
                this.d4.bx(f_);
            }
            else {
                this.f_ = "";
            }
            this.f1 = true;
            try {
                this.f3.wait();
            }
            catch (InterruptedException ex) {}
            this.f1 = false;
        }
        // monitorexit(this.f3)
        return this.f_;
    }
    
    public final String ex(final String s, final String s2, final boolean f0) throws IOException {
        this.f2 = false;
        String s3;
        if (this.d4 != null) {
            this.d4.ap(1, true);
            this.d4.bx(s);
            this.d4.ap(1, false);
            this.f0 = f0;
            s3 = this.ey(s2);
            this.f0 = false;
        }
        else {
            final BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
            System.out.print(s);
            s3 = bufferedReader.readLine();
            if (s3 == null || (s3.length() > 0 && s3.charAt(0) == '\u0004')) {
                this.f2 = true;
            }
        }
        if (this.fx != null) {
            final z fx = this.fx;
            this.fx = null;
            throw fx;
        }
        if ((this.f9 != null || this.e9.jl()) && this.f2) {
            this.f2 = false;
            throw new aa();
        }
        return s3;
    }
    
    public final void ew() {
        if (this.d4 == null || this.f8 == null) {
            return;
        }
        String title = this.d4.cb();
        if (title == null) {
            final int b2 = this.d4.b2();
            final int b3 = this.d4.b1();
            String s;
            if (this.e9.kp()) {
                s = String.valueOf(new StringBuffer(String.valueOf(this.e9.f.ce("usrname"))).append("@").append(this.e9.f.ce("server")).toString()) + " <" + this.e9.lj() + ">";
            }
            else {
                s = this.f6;
            }
            title = String.valueOf(s) + " [" + b3 + "x" + b2 + "]";
            if (!this.e9.lf) {
                title = String.valueOf(title) + " (CLONE)";
            }
        }
        if (this.f8 instanceof Frame) {
            ((Frame)this.f8).setTitle(title);
        }
    }
    
    public final h ev() {
        return this.d4;
    }
    
    public final void eu(final byte[] array) {
        if (this.fz) {
            this.es(new String(array));
        }
    }
    
    public final void et(final byte[] array) {
        if (this.fz) {
            this.es(new String(array));
        }
    }
    
    public final void es(final String s) {
        if (this.d4 != null) {
            this.d4.bx(s);
        }
        else {
            System.out.print(s);
        }
    }
    
    public final void er(final String s) {
        if (this.d4 != null) {
            this.d4.bx(String.valueOf(s) + "\n\r");
        }
        else {
            System.out.println(s);
        }
    }
    
    public final void eq(final b7 b7, final ci f7) {
        this.f7 = f7;
        this.fz = true;
    }
    
    public final void ep(final String s) {
        this.f7 = null;
        this.fz = false;
        this.er(s);
    }
    
    public final void ah(final char c) throws IOException {
        if (this.fz) {
            if (this.f4 || (this.f9 != null && this.f9.j3(c))) {
                this.f4 = false;
                this.f9.j4();
            }
            else {
                this.e9.ko(c);
            }
        }
        else {
            synchronized (this.f3) {
                if (this.f1) {
                    if (c == '\u0004' && this.f9 != null) {
                        this.f2 = true;
                        this.f3.notify();
                    }
                    else if (c == '\u007f' || c == '\b') {
                        if (this.f_.length() > 0) {
                            boolean b = false;
                            if (this.f_.charAt(this.f_.length() - 1) < ' ') {
                                b = true;
                            }
                            this.f_ = this.f_.substring(0, this.f_.length() - 1);
                            this.d4.bx('\b');
                            if (b) {
                                this.d4.bx('\b');
                            }
                            this.d4.bx(' ');
                            if (b) {
                                this.d4.bx(' ');
                            }
                            this.d4.bx('\b');
                            if (b) {
                                this.d4.bx('\b');
                            }
                        }
                        else {
                            this.d4.bs();
                        }
                    }
                    else if (c == '\r') {
                        this.f3.notify();
                        this.d4.bx("\n\r");
                    }
                    else {
                        this.f_ = String.valueOf(this.f_) + c;
                        if (this.f0) {
                            this.d4.bx('*');
                        }
                        else {
                            this.d4.bx(c);
                        }
                    }
                }
            }
            // monitorexit(this.f3)
        }
    }
    
    public final void bt(final byte[] array) throws IOException {
        if (this.fz) {
            this.e9.kn(array);
        }
        else {
            for (int i = 0; i < array.length; ++i) {
                this.ah((char)array[i]);
            }
        }
    }
    
    public final void al(final int n, final int n2, final int n3, final int n4) {
        if (this.fz) {
            this.e9.al(n, n2, n3, n4);
        }
        this.ew();
    }
    
    public final void eo(String s) {
        final Clipboard em = em();
        if (em == null || this.d4 == null) {
            return;
        }
        if (s == null) {
            s = "";
        }
        final StringSelection stringSelection = new StringSelection(s);
        em.setContents(stringSelection, stringSelection);
    }
    
    public final String v() {
        final Clipboard em = em();
        String s = null;
        if (em == null || this.d4 == null) {
            return s;
        }
        final Transferable contents = em.getContents(this);
        if (contents != null) {
            try {
                s = (String)contents.getTransferData(DataFlavor.stringFlavor);
                return s;
            }
            catch (Exception ex) {
                try {
                    ab.ga.beep();
                }
                catch (Throwable t) {}
            }
        }
        try {
            ab.ga.beep();
        }
        catch (Throwable t2) {}
        return s;
    }
    
    public final void en(final boolean f5) {
        this.f5 = f5;
        this.e9.cn();
    }
    
    public static synchronized Clipboard em() {
        Clipboard clipboard;
        if (ab.fy == null) {
            try {
                clipboard = ab.ga.getSystemClipboard();
            }
            catch (Throwable t) {
                clipboard = (ab.fy = new Clipboard("MindTerm-local-clipboard"));
            }
        }
        else {
            clipboard = ab.fy;
        }
        return clipboard;
    }
    
    static {
        ab.fy = null;
    }
}
