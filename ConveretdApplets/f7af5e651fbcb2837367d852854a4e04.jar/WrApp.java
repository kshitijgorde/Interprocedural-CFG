import java.awt.Graphics;
import java.awt.Event;
import java.awt.Container;
import java.awt.Frame;
import java.awt.image.MemoryImageSource;
import java.awt.Image;
import java.util.StringTokenizer;
import java.awt.image.DirectColorModel;
import java.net.URL;
import java.applet.Applet;

// 
// Decompiled by Procyon v0.5.30
// 

public class WrApp extends Applet implements Runnable
{
    URL z0;
    int z1;
    int z2;
    int z3;
    int z4;
    int z5;
    DirectColorModel z6;
    long z7;
    URL[] z8;
    String[] z9;
    StringTokenizer z10;
    double z11;
    int z12;
    long z13;
    long z14;
    URL z15;
    Image z16;
    Thread z17;
    static final String z18 = "Applet initializing.";
    MemoryImageSource z19;
    private Frame z20;
    URL z21;
    static final String z22 = "http://www.durius.com/";
    int z23;
    int z24;
    StringTokenizer z25;
    String z26;
    long z27;
    int z28;
    String z29;
    
    public void z0() {
    }
    
    public final String getAppletInfo() {
        return "(c) Durius/R. Boegniel. http://www.durius.com/";
    }
    
    public final void init() {
        try {
            this.z21 = new URL("http://www.durius.com/");
        }
        catch (Exception ex) {}
        this.showStatus("Applet initializing.");
        this.z28 = 1;
        this.z5 = 0;
        this.z23 = 0;
        this.z1 = 321;
        this.z24 = 0;
        this.z12 = 12345;
        this.z14 = 20L;
        this.z26 = this.getParameter("url");
        if (this.z26 != null) {
            this.z10 = new StringTokenizer(this.z26);
            this.z8 = new URL[this.z10.countTokens()];
            this.z9 = new String[this.z10.countTokens()];
        }
        this.z26 = this.getParameter("reg");
        if (this.z26 != null) {
            this.z25 = new StringTokenizer(this.z26);
        }
        this.z26 = this.getParameter("fps");
        if (this.z26 != null) {
            this.z14 = 1000L / Integer.parseInt(this.z26);
        }
        this.z26 = this.getParameter("target");
        if (this.z26 != null) {
            this.z29 = this.z26;
        }
        this.z26 = this.getParameter("width");
        if (this.z26 != null) {
            this.z3 = Integer.parseInt(this.z26);
        }
        this.z26 = this.getParameter("height");
        if (this.z26 != null) {
            this.z2 = Integer.parseInt(this.z26);
        }
        final Integer n = new Integer(0);
        this.z26 = this.getParameter("bg");
        if (this.z26 != null) {
            this.z4 = Integer.valueOf(this.z26, 16);
        }
        Container container;
        for (container = this.getParent(); !(container instanceof Frame); container = ((Frame)container).getParent()) {}
        this.z20 = (Frame)container;
        this.z26 = this.getParameter("orientation");
        if (this.z26 != null && this.z26.compareTo("v") == 0) {
            this.z23 = 1;
        }
        try {
            this.z0 = this.getDocumentBase();
        }
        catch (Exception ex2) {}
        this.z0();
        if (this.z10 != null) {
            this.z20.setCursor(12);
            while (this.z10.hasMoreTokens()) {
                this.z26 = this.z10.nextToken();
                try {
                    this.z8[this.z5] = new URL(this.z26);
                }
                catch (Exception ex3) {}
                this.z9[this.z5] = this.z26;
                ++this.z5;
            }
        }
        final String s = new String(this.z21.toString());
        final String s2 = new String("file");
        final byte[] bytes = s.getBytes();
        final byte[] bytes2 = s2.getBytes();
        byte b = 0;
        byte b2 = 0;
        for (int i = 0; i < bytes.length; ++i) {
            b += bytes[i];
        }
        final int n2 = b * this.z12;
        for (int j = 0; j < bytes2.length; ++j) {
            b2 += bytes2[j];
        }
        final int n3 = b2 * this.z12;
        if (n2 == 25714635) {
            this.z28 = 0;
        }
        if (n3 != 5135520) {
            this.z28 = 1;
        }
        final String lowerCase = new String(this.z0.toString()).toLowerCase();
        final String substring = lowerCase.substring(0, lowerCase.lastIndexOf("/") + 1);
        final String lowerCase2 = new String(this.z0.getHost()).toLowerCase();
        final byte[] bytes3 = substring.getBytes();
        final byte[] bytes4 = lowerCase2.getBytes();
        if (n2 == 25714635) {
            this.z28 = 0;
        }
        if (n3 != 5135520) {
            this.z28 = 1;
        }
        byte b3 = 0;
        byte b4 = 0;
        for (int k = 0; k < bytes3.length; ++k) {
            b3 += bytes3[k];
        }
        final int n4 = b3 * this.z1;
        for (int l = 0; l < bytes4.length; ++l) {
            b4 += bytes4[l];
        }
        final int n5 = b4 * this.z1;
        if (substring.startsWith(s2)) {
            this.z24 = 1;
        }
        if (this.z25.countTokens() != 0 && this.z25.countTokens() < 6) {
            while (this.z25.hasMoreTokens()) {
                this.z26 = this.z25.nextToken();
                this.z26 = this.z26.trim();
                final int int1 = Integer.parseInt(this.z26);
                if (n4 == int1) {
                    this.z24 = 1;
                }
                if (n4 + this.z1 * 403 == int1) {
                    this.z24 = 1;
                }
                if (n4 - this.z1 * 403 == int1) {
                    this.z24 = 1;
                }
                if (n5 == int1) {
                    this.z24 = 1;
                }
                if (n5 + this.z1 * 403 == int1) {
                    this.z24 = 1;
                }
                if (n5 - this.z1 * 403 == int1) {
                    this.z24 = 1;
                }
                if (this.z28 == 1) {
                    this.z24 = 0;
                }
                if (this.z24 == 0) {
                    this.z20.setCursor(12);
                }
            }
        }
        System.gc();
    }
    
    public final boolean mouseDown(final Event event, final int n, final int n2) {
        if (this.z24 == 0) {
            this.getAppletContext().showDocument(this.z21);
        }
        else if (this.z5 != 0) {
            if (this.z23 == 0) {
                this.z11 = this.z3 / this.z5;
                this.z11 = n / this.z11;
            }
            else {
                this.z11 = this.z2 / this.z5;
                this.z11 = n2 / this.z11;
            }
            if (this.z29 != null) {
                this.getAppletContext().showDocument(this.z8[(int)this.z11], this.z29);
            }
            else {
                this.getAppletContext().showDocument(this.z8[(int)this.z11]);
            }
        }
        this.z0(event, n, n2);
        return true;
    }
    
    public final boolean mouseEnter(final Event event, final int n, final int n2) {
        if (this.z24 == 1) {
            if (this.z5 != 0) {
                if (this.z23 == 0) {
                    this.z11 = this.z3 / this.z5;
                    this.z11 = n / this.z11;
                }
                else {
                    this.z11 = this.z2 / this.z5;
                    this.z11 = n2 / this.z11;
                }
                this.showStatus(this.z9[(int)this.z11]);
            }
        }
        else {
            this.showStatus("http://www.durius.com/");
        }
        this.z1(event, n, n2);
        return true;
    }
    
    public final boolean mouseExit(final Event event, final int n, final int n2) {
        this.showStatus(" ");
        this.z2(event, n, n2);
        return true;
    }
    
    public final boolean mouseMove(final Event event, final int n, final int n2) {
        if (this.z24 == 1 && this.z5 >= 2) {
            if (this.z23 == 0) {
                this.z11 = this.z3 / this.z5;
                this.z11 = n / this.z11;
            }
            else {
                this.z11 = this.z2 / this.z5;
                this.z11 = n2 / this.z11;
            }
            this.showStatus(this.z9[(int)this.z11]);
        }
        this.z3(event, n, n2);
        return true;
    }
    
    public void z0(final Event event, final int n, final int n2) {
    }
    
    public void z1(final Event event, final int n, final int n2) {
    }
    
    public void z2(final Event event, final int n, final int n2) {
    }
    
    public void z3(final Event event, final int n, final int n2) {
    }
    
    public final void paint(final Graphics graphics) {
        if (this.z17 != null) {
            this.update(graphics);
        }
    }
    
    public final void run() {
        this.z7 = System.currentTimeMillis();
    }
    
    public final void start() {
        if (this.z17 == null) {
            (this.z17 = new Thread(this)).setPriority(1);
            this.z17.start();
        }
    }
    
    public final void stop() {
        if (this.z17 != null) {
            this.z17.stop();
            this.z17 = null;
            System.gc();
        }
    }
    
    public synchronized void z1() {
        Thread.yield();
        this.getToolkit().sync();
        final long n = 10L - (System.currentTimeMillis() - this.z7);
        if (n > 0L) {
            try {
                Thread.sleep(n);
            }
            catch (InterruptedException ex) {}
        }
        else {
            try {
                Thread.sleep(1L);
            }
            catch (InterruptedException ex2) {}
        }
        this.z7 = System.currentTimeMillis();
    }
}
