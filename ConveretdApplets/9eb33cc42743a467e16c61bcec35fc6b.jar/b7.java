import java.io.Writer;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.io.IOException;
import java.io.OutputStream;
import java.io.InputStream;
import java.util.Vector;

// 
// Decompiled by Procyon v0.5.30
// 

public final class b7 extends ca implements b6
{
    public s fo;
    public ak fl;
    public bx l2;
    public at fk;
    public at l1;
    public int l0;
    public int l_;
    public Object[] lz;
    public Vector ly;
    public ca lx;
    public bw km;
    public ci f7;
    
    public b7(final ca lx, final InputStream inputStream, final OutputStream outputStream, final ci f7, final ci ci, final bw km, final boolean b) {
        this.f7 = f7;
        this.lx = lx;
        this.km = km;
        this.lz = new Object[16];
        this.l_ = 0;
        this.l0 = 0;
        this.ly = new Vector();
        this.fo = new s(outputStream, -1);
        (this.fl = new ak(inputStream, -1)).l4(this);
        this.fo.l4(this);
        this.fl.fi(new ax(-1, ci));
        this.fk = this.fo.d1();
        if (b) {
            (this.l2 = new bx(this)).l4(this);
            this.l1 = this.l2.d1();
        }
        else {
            this.l1 = new at();
        }
    }
    
    public final void ei() {
        this.fo.start();
        this.fl.start();
        if (this.l2 != null) {
            this.l2.start();
        }
    }
    
    public final void le() throws InterruptedException {
        this.le(0L);
    }
    
    public final void le(final long n) throws InterruptedException {
        if (this.fl != null) {
            this.fl.join(n);
        }
        Thread.sleep(100L);
        this.l3();
    }
    
    public final void l3() {
        this.l_();
        this.lu();
        if (this.fl != null && this.fl.isAlive()) {
            this.fl.stop();
        }
        if (this.fo != null && this.fo.isAlive()) {
            this.fo.stop();
        }
        if (this.l2 != null && this.l2.isAlive()) {
            this.l2.stop();
        }
        this.fl = null;
        this.fo = null;
        this.l2 = null;
        System.runFinalization();
    }
    
    public final synchronized int l2() {
        final int l_ = this.l_;
        if (this.l_ < this.lz.length) {
            int l_2;
            for (l_2 = this.l_ + 1; l_2 < this.lz.length && this.lz[l_2] != null; ++l_2) {}
            this.l_ = l_2;
        }
        else {
            final Object[] lz = new Object[this.lz.length + 16];
            System.arraycopy(this.lz, 0, lz, 0, this.lz.length);
            this.lz = lz;
            ++this.l_;
        }
        return l_;
    }
    
    public final synchronized String[] l1() {
        int n = 0;
        final String[] array = new String[this.lz.length];
        for (int i = 0; i < this.lz.length; ++i) {
            if (this.lz[i] != null) {
                array[n++] = ((y)this.lz[i]).d5();
            }
        }
        final String[] array2 = new String[n];
        System.arraycopy(array, 0, array2, 0, n);
        return array2;
    }
    
    public final synchronized void l0(int n) {
        int n2;
        for (n2 = 0; n2 < this.lz.length && (this.lz[n2] == null || --n >= 0); ++n2) {}
        if (n2 < this.lz.length) {
            ((y)this.lz[n2]).ec();
        }
    }
    
    public final synchronized void l_() {
        for (int i = 0; i < this.lz.length; ++i) {
            if (this.lz[i] != null) {
                ((y)this.lz[i]).eh();
                this.lz[i] = null;
            }
        }
        this.lz = new Object[16];
    }
    
    public final synchronized void lz(final y y) throws IOException {
        ++this.l0;
        this.lz[y.fw] = y;
    }
    
    public final synchronized y ly(final int n) {
        final y y = (y)this.lz[n];
        this.lz[n] = null;
        this.l_ = ((n < this.l_) ? n : this.l_);
        --this.l0;
        return y;
    }
    
    public final boolean lx() {
        return this.lx.mh(2);
    }
    
    public final bm lw(final String s, final int n, final String s2, final int n2, final String s3) throws IOException {
        final bm fy = ar.f1(s3).fy(s, n, s2, n2, this);
        fy.l4(this);
        fy.start();
        synchronized (this.ly) {
            this.ly.addElement(fy);
        }
        // monitorexit(this.ly)
        return fy;
    }
    
    public final void lv(final String s, final int n) {
        synchronized (this.ly) {
            for (int i = 0; i < this.ly.size(); ++i) {
                final bm bm = this.ly.elementAt(i);
                if (bm.i7() == n && bm.i6().equals(s)) {
                    this.ly.removeElementAt(i);
                    bm.fh();
                    break;
                }
            }
        }
        // monitorexit(this.ly)
    }
    
    public final void lu() {
        synchronized (this.ly) {
            while (this.ly.size() > 0) {
                this.ly.elementAt(0).fh();
                this.ly.removeElementAt(0);
            }
        }
        // monitorexit(this.ly)
    }
    
    public final ay eg(final ay ay) {
        return ay;
    }
    
    public final void ee(final ay ay) {
        this.fk.hr(ay);
    }
    
    public final void ef(final ay ay) {
        final ax ax = (ax)ay;
        try {
            switch (ax.h_) {
                case 17: {
                    if (this.km != null) {
                        this.km.eu(ax.jv());
                        break;
                    }
                    break;
                }
                case 18: {
                    if (this.km != null) {
                        this.km.et(ax.jv());
                        break;
                    }
                    break;
                }
                case 23: {
                    final int int1 = ax.readInt();
                    final y y = (y)this.lz[int1];
                    if (y != null) {
                        y.ee(ay);
                        break;
                    }
                    throw new Exception("Data on nonexistent channel: " + int1);
                }
                case 21: {
                    final int int2 = ax.readInt();
                    final y y2 = (y)this.lz[int2];
                    if (y2 == null) {
                        throw new Exception("Open confirm on nonexistent: " + int2);
                    }
                    if (!y2.ej(ax.readInt())) {
                        throw new Exception("Open confirmation on allready opened channel!");
                    }
                    y2.ei();
                    break;
                }
                case 22: {
                    final int int3 = ax.readInt();
                    final y ly;
                    if ((ly = this.ly(int3)) != null) {
                        this.h9("Channel open failure on " + ly.fj);
                        ly.eh();
                        break;
                    }
                    throw new Exception("Open failure on nonexistent channel: " + int3);
                }
                case 24: {
                    final int int4 = ax.readInt();
                    final y y3 = (y)this.lz[int4];
                    if (y3 != null) {
                        y3.d7();
                        break;
                    }
                    throw new Exception("Input eof on nonexistent channel: " + int4);
                }
                case 25: {
                    final int int5 = ax.readInt();
                    final y y4;
                    if (int5 < this.lz.length && (y4 = (y)this.lz[int5]) != null) {
                        y4.d8();
                        break;
                    }
                    throw new Exception("Output closed on nonexistent channel: " + int5);
                }
                default: {
                    throw new Exception("Unknown packet type (" + ax.h_ + "), disconnecting...");
                }
                case 20: {
                    final av av = new av(33, this.f7);
                    final int int6 = ax.readInt();
                    if (this.km != null) {
                        if (int6 != 0) {
                            this.km.ep(String.valueOf(this.lr().lo().getHostName()) + " disconnected: " + int6);
                        }
                        else {
                            this.km.ep("Connection to " + this.lr().lo().getHostName() + " closed.");
                        }
                    }
                    this.ee(av);
                    this.lr().lc(true);
                    break;
                }
                case 27:
                case 29: {
                    this.l1.hr(ax);
                    break;
                }
                case 1: {
                    this.lc("Peer disconnected: " + ax.jw());
                    break;
                }
                case 19: {
                    System.out.println("!!! EOF received...");
                }
                case 11:
                case 16:
                case 33: {
                    break;
                }
            }
        }
        catch (Exception ex) {
            final StringWriter stringWriter = new StringWriter();
            ex.printStackTrace(new PrintWriter(stringWriter));
            System.out.println("\nplease send a mail to mats@mindbright.se with:");
            System.out.println("(I found a bug in MindTerm!), error: " + ex.getMessage());
            System.out.println(stringWriter.toString());
            this.ls("please send a mail to mats@mindbright.se with:\n\r(I found a bug in MindTerm!), error: " + ex.getMessage() + "\n\r" + lt(stringWriter.toString()));
        }
    }
    
    public static String lt(final String s) {
        int n = 0;
        String string = "";
        int index;
        while ((index = s.indexOf(10, n)) != -1) {
            string = String.valueOf(string) + s.substring(n, index) + "\n\r";
            n = index + 1;
        }
        return String.valueOf(string) + s.substring(n);
    }
    
    public final void ed(final b8 b8) {
        if (b8 instanceof bx) {
            ca.md("Controller connect-channel closed");
        }
        else if (b8 instanceof s) {
            ca.md("Controller TX-channel closed");
        }
        else if (b8 instanceof ak) {
            ca.md("Controller RX-channel closed");
        }
        else if (b8 instanceof bm) {
            ca.md("Listen channel for port " + ((bm)b8).i7() + " closed");
        }
        else {
            this.h9("Bug in SSHChannelController.close 'chan' is: " + b8);
        }
    }
    
    public final void lc(final String s) {
        if (this.lx.l5) {
            this.lr().lc(false);
        }
        if (this.fo != null) {
            this.fo.d0();
        }
        if (this.km != null) {
            this.km.ep("\r\nDisconnecting, " + s);
        }
        else {
            ca.me("\r\nDisconnecting, " + s);
        }
        if (!this.lx.l5 && this.fl != null) {
            this.fl.fh();
        }
    }
    
    public final void ls(final String s) {
        try {
            final av av = new av(1, this.f7);
            av.jt(s);
            if (this.fk != null) {
                this.fk.hq(av);
            }
            Thread.sleep(300L);
            this.lc(s);
        }
        catch (Exception ex) {
            this.h9("Error in sendDisconnect: " + ex.toString());
        }
    }
    
    public final void h9(final String s) {
        if (this.lx.l5) {
            final bo f2 = this.lr().g1.f2();
            if (f2 != null) {
                f2.h9(s);
            }
        }
        else {
            ca.me(s);
        }
    }
    
    public final b5 lr() {
        return (b5)this.lx;
    }
    
    public final void j1(final String s, final String s2, final int n) {
        this.l2.j1(s, s2, n);
    }
}
