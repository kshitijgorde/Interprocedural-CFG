import java.awt.Container;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Event;
import java.awt.Frame;
import java.util.StringTokenizer;
import java.awt.Image;
import java.awt.image.MemoryImageSource;
import java.awt.image.DirectColorModel;
import java.net.URL;
import java.applet.Applet;

// 
// Decompiled by Procyon v0.5.30
// 

public class WrApp extends Applet implements Runnable
{
    Thread jm57;
    URL jm58;
    URL jm59;
    URL jm60;
    URL[] jm61;
    DirectColorModel jm62;
    MemoryImageSource jm63;
    Image jm64;
    StringTokenizer jm65;
    StringTokenizer jm66;
    static final String jm67 = "Applet initializing.";
    static final String jm68 = "http://www.durius.com/";
    String[] jm69;
    String jm70;
    String jm71;
    String jm72;
    long jm73;
    long jm74;
    long jm75;
    boolean jm76;
    boolean jm77;
    boolean jm78;
    boolean jm79;
    int jm80;
    int jm81;
    int jm82;
    int jm83;
    int jm84;
    int jm85;
    int jm86;
    int jm87;
    private Frame jm88;
    double jm89;
    
    public final boolean mouseEnter(final Event event, final int n, final int n2) {
        if (this.jm79) {
            if (this.jm85 != 0) {
                if (this.jm84 == 0) {
                    this.jm89 = this.jm81 / this.jm85;
                    this.jm89 = n / this.jm89;
                }
                else {
                    this.jm89 = this.jm82 / this.jm85;
                    this.jm89 = n2 / this.jm89;
                }
                this.showStatus(this.jm69[(int)this.jm89]);
            }
        }
        else {
            this.showStatus("http://www.durius.com/");
        }
        this.jm55(event, n, n2);
        return true;
    }
    
    public final void stop() {
        if (this.jm57 != null) {
            this.jm57.stop();
            this.jm57 = null;
            System.gc();
        }
    }
    
    public final boolean mouseExit(final Event event, final int n, final int n2) {
        this.showStatus(" ");
        this.jm56(event, n, n2);
        return true;
    }
    
    public final void paint(final Graphics graphics) {
        if (this.jm57 != null) {
            this.update(graphics);
        }
    }
    
    public WrApp() {
        this.jm76 = true;
        this.jm77 = false;
        this.jm78 = false;
        this.jm79 = false;
        this.jm83 = 0;
        this.jm89 = 0.0;
    }
    
    public void jm54(final Event event, final int n, final int n2) {
    }
    
    public void jm0() {
    }
    
    public final void start() {
        if (this.jm57 == null) {
            (this.jm57 = new Thread(this)).setPriority(3);
            this.jm57.start();
        }
    }
    
    public final String getAppletInfo() {
        return "(c) Durius/Robert Boegniel. http://www.durius.com/";
    }
    
    public final boolean mouseDown(final Event event, final int n, final int n2) {
        if (!this.jm79) {
            this.getAppletContext().showDocument(this.jm58);
        }
        else if (this.jm85 != 0) {
            if (this.jm84 == 0) {
                this.jm89 = this.jm81 / this.jm85;
                this.jm89 = n / this.jm89;
            }
            else {
                this.jm89 = this.jm82 / this.jm85;
                this.jm89 = n2 / this.jm89;
            }
            if (this.jm70 != null) {
                this.getAppletContext().showDocument(this.jm61[(int)this.jm89], this.jm70);
            }
            else {
                this.getAppletContext().showDocument(this.jm61[(int)this.jm89]);
            }
        }
        this.jm54(event, n, n2);
        return true;
    }
    
    public void jm55(final Event event, final int n, final int n2) {
    }
    
    public final void run() {
    }
    
    public final void init() {
        this.jm71 = this.getParameter("bg");
        if (this.jm71 != null) {
            this.jm80 = Integer.valueOf(this.jm71, 16);
            this.setBackground(new Color(this.jm80 >> 16 & 0xFF, this.jm80 >> 8 & 0xFF, this.jm80 & 0xFF));
        }
        else {
            this.setBackground(new Color(0, 0, 0));
        }
        try {
            this.jm58 = new URL("http://www.durius.com/");
        }
        catch (Exception ex) {}
        this.showStatus("Applet initializing.");
        this.jm76 = true;
        this.jm85 = 0;
        this.jm84 = 0;
        this.jm86 = 321;
        this.jm87 = 12345;
        this.jm75 = 20L;
        this.jm71 = this.getParameter("url");
        if (this.jm71 != null) {
            this.jm65 = new StringTokenizer(this.jm71);
            this.jm61 = new URL[this.jm65.countTokens()];
            this.jm69 = new String[this.jm65.countTokens()];
        }
        this.showStatus("Applet initializing.");
        this.jm71 = this.getParameter("reg");
        if (this.jm71 != null) {
            this.jm66 = new StringTokenizer(this.jm71);
        }
        this.jm71 = this.getParameter("target");
        if (this.jm71 != null) {
            this.jm70 = this.jm71;
        }
        this.jm71 = this.getParameter("width");
        if (this.jm71 != null) {
            this.jm81 = Integer.parseInt(this.jm71);
        }
        this.jm71 = this.getParameter("height");
        if (this.jm71 != null) {
            this.jm82 = Integer.parseInt(this.jm71);
        }
        this.jm71 = this.getParameter("orientation");
        if (this.jm71 != null && this.jm71.compareTo("v") == 0) {
            this.jm84 = 1;
        }
        Container container;
        for (container = this.getParent(); !(container instanceof Frame); container = ((Frame)container).getParent()) {}
        this.jm88 = (Frame)container;
        try {
            this.jm60 = this.getDocumentBase();
        }
        catch (Exception ex2) {}
        if (this.jm65 != null) {
            this.jm88.setCursor(12);
            while (this.jm65.hasMoreTokens()) {
                this.jm71 = this.jm65.nextToken();
                try {
                    this.jm61[this.jm85] = new URL(this.jm71);
                }
                catch (Exception ex3) {}
                this.jm69[this.jm85] = this.jm71;
                ++this.jm85;
            }
        }
        final String s = new String("file");
        final String s2 = new String(this.jm58.toString());
        final char[] array = new char[s2.length()];
        final char[] array2 = new char[s.length()];
        s2.getChars(0, s2.length(), array, 0);
        s.getChars(0, s.length(), array2, 0);
        char c = '\0';
        char c2 = '\0';
        for (int i = 0; i < array.length; ++i) {
            c += array[i];
        }
        final int n = c * this.jm87;
        for (int j = 0; j < array2.length; ++j) {
            c2 += array2[j];
        }
        final int n2 = c2 * this.jm87;
        if (n == 25714635) {
            this.jm76 = false;
        }
        if (n2 != 5135520) {
            this.jm76 = true;
        }
        final String lowerCase = new String(this.jm60.toString()).toLowerCase();
        final String substring = lowerCase.substring(0, lowerCase.lastIndexOf("/") + 1);
        final String lowerCase2 = new String(this.jm60.getHost()).toLowerCase();
        final char[] array3 = new char[substring.length()];
        final char[] array4 = new char[lowerCase2.length()];
        substring.getChars(0, substring.length(), array3, 0);
        lowerCase2.getChars(0, lowerCase2.length(), array4, 0);
        if (n == 25714635) {
            this.jm76 = false;
        }
        if (n2 != 5135520) {
            this.jm76 = true;
        }
        this.jm0();
        char c3 = '\0';
        char c4 = '\0';
        for (int k = 0; k < array3.length; ++k) {
            c3 += array3[k];
        }
        final int n3 = c3 * this.jm86;
        for (int l = 0; l < array4.length; ++l) {
            c4 += array4[l];
        }
        final int n4 = c4 * this.jm86;
        if (substring.startsWith(s)) {
            this.jm79 = true;
        }
        if (this.jm66.countTokens() != 0 && this.jm66.countTokens() < 21) {
            while (this.jm66.hasMoreTokens()) {
                this.jm71 = this.jm66.nextToken();
                this.jm71 = this.jm71.trim();
                final int int1 = Integer.parseInt(this.jm71);
                if (n3 == int1) {
                    this.jm79 = true;
                }
                if (n3 + this.jm86 * 403 == int1) {
                    this.jm79 = true;
                }
                if (n3 - this.jm86 * 403 == int1) {
                    this.jm79 = true;
                }
                if (n4 == int1) {
                    this.jm79 = true;
                }
                if (n4 + this.jm86 * 403 == int1) {
                    this.jm79 = true;
                }
                if (n4 - this.jm86 * 403 == int1) {
                    this.jm79 = true;
                }
                if (this.jm76) {
                    this.jm79 = false;
                }
                if (!this.jm79) {
                    this.jm88.setCursor(12);
                }
            }
        }
        this.showStatus("Applet initializing.");
        System.gc();
    }
    
    public void jm56(final Event event, final int n, final int n2) {
    }
    
    public void jm48(final Event event, final int n, final int n2) {
    }
    
    public final boolean mouseMove(final Event event, final int n, final int n2) {
        if (this.jm79) {
            if (this.jm85 >= 2) {
                if (this.jm84 == 0) {
                    if (n > 0) {
                        this.jm89 = this.jm81 / this.jm85;
                        this.jm89 = n / this.jm89;
                    }
                    else {
                        this.jm89 = 0.0;
                    }
                }
                else if (n2 > 0) {
                    this.jm89 = this.jm82 / this.jm85;
                    this.jm89 = n2 / this.jm89;
                }
                else {
                    this.jm89 = 0.0;
                }
                this.showStatus(this.jm69[(int)this.jm89]);
            }
            if (this.jm78) {
                this.jm59 = this.getDocumentBase();
                final String s = new String(this.jm59.toString());
                this.showStatus("Image '" + s.substring(0, s.lastIndexOf("/")) + "/" + this.jm72 + "' not found!");
            }
        }
        this.jm48(event, n, n2);
        return true;
    }
}
