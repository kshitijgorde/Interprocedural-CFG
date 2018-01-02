import java.net.MalformedURLException;
import java.net.URL;
import java.awt.event.MouseAdapter;
import java.awt.Cursor;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.awt.Component;
import java.awt.image.ImageObserver;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseListener;
import java.awt.image.ImageProducer;
import java.awt.MediaTracker;
import java.awt.image.ColorModel;
import java.awt.image.PixelGrabber;
import java.awt.image.MemoryImageSource;
import java.awt.Graphics;
import java.awt.Image;
import java.applet.Applet;

// 
// Decompiled by Procyon v0.5.30
// 

public class a extends Applet implements Runnable
{
    Thread n;
    Image m;
    Graphics if;
    MemoryImageSource else;
    int[] byte;
    PixelGrabber h;
    ColorModel try;
    MediaTracker do;
    int[] null;
    int[] for;
    char[][] long;
    Image[] char;
    int i;
    int b;
    int k;
    int case;
    int j;
    int g;
    int d;
    int l;
    boolean int;
    boolean f;
    int goto;
    int e;
    char[] new;
    int void;
    int a;
    boolean c;
    
    public a() {
        this.try = ColorModel.getRGBdefault();
        this.int = false;
        this.f = false;
        this.goto = 77;
        this.e = 12;
        this.new = new char[] { '\0', '\0', '\0', '\0', '\0', '\0', '\0', '\0', '\0', '\0', '\0', '\0', '\0', '\0', '\0', '\0', '\0', '\0', '\0', '\0', '\0', '\0', '\0', '\0', '\0', '\0', '\0', '\0', '\0', '\0', '\0', '\0', '\0', '\0', '\0', '\0', '\0', 'º', 'U', '\0', '\0', '\0', '\0', '\0', '\0', '\0', '\0', '\0', '\0', '\0', '\0', '\0', '\0', '\0', 'º', 'V', '\0', 'º', 'V', '\0', '\0', '\0', '\0', '\0', '\0', '\0', '\0', '\0', '\0', '\0', '\0', '\0', '\0', '\0', '\'', 'v', '\0', '\0', '\0', '\0', '\0', '\0', '\0', '\0', '\0', '\0', '\0', '\0', '\0', '\0', '\0', '\0', '\0', '\0', '\0', '\0', '\0', '\0', '\0', '\0', '\0', '\0', '\0', '\0', '\0', '\0', '\0', '\0', '\0', '\0', '\0', '\0', '\0', '\0', 'U', '\'', '\0', '\0', '\0', '\0', '\0', '\0', '\0', '\0', '\0', '\0', '\0', '\0', '\0', '\0', '\0', '\u00ed', 'v', '\0', '\u00ee', 'v', '\0', '\0', '\0', '\0', '\0', '\0', '\0', '\0', '\0', '\0', '\0', '\0', '\0', '\0', '\0', '©', '¨', '\0', '\u0087', '6', '\0', '6', '¨', '\0', '\0', 'v', 'w', '\u0087', '6', '\0', '7', '¨', '\0', '\0', 'v', 'v', '\u0087', '7', '\0', '7', '¨', '\0', '\0', 'v', 'v', '\0', '\0', '\0', '\0', '\u0087', '\u00ed', '\u00dc', 'w', '\0', '\0', '\u0087', '6', '\0', '\0', '¨', '\u00ed', '\u00ca', 'v', 'v', '\0', '\0', '\u0087', '\u00ee', '\u00dc', 'v', '\0', '\0', '\u00ed', 'v', '\0', '\u00ed', '\u0097', '\u00dc', '\u00dc', 'v', '\0', '\0', '\0', '\0', '\0', '\0', '\u0098', '\u00ed', '\u00ee', '©', '\0', '6', '\u00ee', '\u00ed', 'v', 'º', '\u0098', '\0', '\u0087', '\u00ff', 'V', '\0', '\u00dc', 'v', 'º', '\u0097', '\0', '\u0087', '\u00ff', 'V', '\0', '\u00dc', 'v', '¹', '\u0097', '\0', '\u0086', '\u00ff', 'V', '\0', '\u00dc', 'v', '\0', '\0', '\0', '\u0098', '\u00ff', 'w', 'v', '\u00ff', 'v', '\0', '\u00ee', 'v', '\0', '¨', '\u00ff', 'v', '¨', '\u00ff', '¨', '\0', '\u0098', '\u00ff', 'v', 'v', '\u00ff', 'w', '\0', '\u00ed', 'w', '\0', '\u00ed', '\u00ff', '\u0087', 'v', '\u00ff', 'v', '\0', '\0', '\0', '\0', '©', '\u00ff', 'v', 'v', '\u00ff', '\u0087', '6', '\u00ee', '\u00ed', 'v', 'v', '\u00ed', '\0', '\u00ca', '\u00ed', '\u0098', '\0', '\u00ff', '\0', 'w', '\u00ed', '\0', '\u00cb', '\u00ee', '\u0097', '\0', '\u00ff', '\0', 'v', '\u00ed', '\0', '\u00cb', '\u00ed', '\u0097', '\0', '\u00ff', '\0', '\0', '\0', '\0', '\u00ff', '6', '\0', '\0', '\u0087', '\u00ca', '\0', '\u00ed', 'v', '\0', '\u00ff', '6', '\0', '\0', '\u00cb', '¨', '\0', '\u00ff', '6', '\0', '\0', '\u0087', '\u00cb', '\0', '\u00ed', 'v', '\0', '\u00ed', '\u0087', '\0', '\0', '\u0087', '\u00cb', '\0', '\0', '\0', '\0', 'v', '6', '\0', '\0', '\u00ca', '¨', '\0', '©', '¨', '\0', '\0', '\u00ff', '\'', '\u00ff', 'v', '\u00dc', 'v', '\u00dc', '\0', '\0', '\u00ff', '\'', '\u00ff', 'v', '\u00dc', 'w', '\u00dc', '\0', '\0', '\u00ff', '\'', '\u00ff', 'v', '\u00dc', 'v', '\u00dd', '\0', '\0', '\0', '\0', '\u00ff', '\u00ff', '\u00ff', '\u00ff', '\u00ff', '\u00ee', '\0', '\u00ed', 'v', '\0', '\u00ff', '\0', '\0', '\0', '©', '¨', '\0', '\u00ff', '\u00ff', '\u00ff', '\u00ff', '\u00ff', '\u00ed', '\0', '\u00ee', 'w', '\0', '\u00ed', 'v', '\0', '\0', 'v', '\u00ee', '\0', '\0', '\0', '\0', 'U', '\u00dc', '\u00ff', '\u00ff', '\u00ff', '¨', '\0', '©', '¨', '\0', '\0', '\u00cb', '\u0097', '\u00ee', '\'', '\u00ff', '\u00cb', '\u0087', '\0', '\0', '\u00ca', '\u0098', '\u00ed', '\'', '\u00ff', '\u00cb', '\u0087', '\0', '\0', '\u00cb', '\u0097', '\u00ee', '\'', '\u00ff', '\u00ca', '\u0086', '\0', '\0', '\0', '\0', '\u00ff', '\'', '\0', '\0', '\'', '\'', '\0', '\u00ed', 'v', '\0', '\u00ff', '\'', '\0', '\0', '¹', '¨', '\0', '\u00ff', '\'', '\0', '\0', '\'', '\'', '\0', '\u00ee', 'w', '\0', '\u00ed', 'v', '\0', '\0', 'v', '\u00dc', '\0', '\0', '\0', '\0', '\u00ff', '\u0098', '6', '\0', '©', '¨', '\0', '¨', '¨', '\0', '\0', 'v', '\u00ff', '¨', '\0', '\u00ff', '\u00ff', '\'', '\0', '\0', 'w', '\u00ff', '¨', '\0', '\u00ff', '\u00ff', '\'', '\0', '\0', 'w', '\u00ff', '©', '\0', '\u00ff', '\u00ff', '\'', '\0', '\0', '\0', '\0', '\u00cb', '\u00cb', '\'', '\'', '\u00dc', '©', '\0', '\u00ed', 'v', '\0', '\u00cb', '\u00ca', '\0', 'V', '\u00ff', '©', '\0', '\u00cb', '\u00ca', '\'', '\'', '\u00dc', '©', '\0', '\u00ee', 'v', '\0', '\u00ee', '\u00ff', '&', '6', '\u00ff', 'w', '\0', '\0', '\0', '\0', '\u00ff', 'V', '\0', 'w', '\u00ff', '¨', '\0', '¨', 'º', '\0', '\0', '\0', '\u00ff', 'v', '\0', 'º', '\u00dc', '\0', '\0', '\0', '\0', '\u00ff', 'v', '\0', '¹', '\u00dc', '\0', '\0', '\0', '\0', '\u00ff', 'v', '\0', 'º', '\u00dc', '\0', '\0', '©', '©', '\0', '\'', '\u00cb', '\u00ff', '\u00ff', '\u00cb', '\'', '\0', '\u00ed', 'v', '\0', '\'', '\u00dc', '\u00ff', '\u00ff', '\u00dc', '©', '\0', '\'', '\u00cb', '\u00ff', '\u00ff', '\u00cb', '\'', '\0', '\u00ed', 'v', '\0', '\u00ed', '\u00cb', '\u00ff', '\u00ff', '©', '\0', '\0', '©', '©', '\0', '\u0087', '\u00ff', '\u00ff', '\u00ff', '\u00ca', '\u00cb', '\0', 'v', '\u00ff', '\u00cb', '\0', '\0', '\0', '\0', '\0', '\0', '\0', '\0', '\0', '\0', '\0', '\0', '\0', '\0', '\0', '\0', '\0', '\0', '\0', '\0', '\0', '\0', '\0', '\0', '\0', '\0', '\0', '\0', '\0', '\0', '\0', '\0', '\0', '\0', '\0', '\0', '\0', '\0', '\0', '\0', 'v', '\'', '\0', '\0', '\u00cb', '\u0087', '\0', '\0', '\0', '\0', '\0', '\0', '\0', '\0', '\0', '\0', '\0', '\0', '\0', '\0', '\0', '\0', '\0', '\0', '\0', '\0', '\0', '\0', '\0', '\0', '\0', '\0', '\0', '\0', '\0', '\0', '\0', '\0', '\0', '\0', '\0', '\0', '\0', '\0', '\0', '\0', '\0', '\0', '\0', '\0', '\0', '\0', '\0', '\0', '\0', '\0', '\0', '\0', '\0', '\0', '\0', '\0', '\0', '\0', '\0', '\0', '\0', '\0', '\0', '\0', '\0', '\0', '\0', '\0', '\0', '\0', '\0', '©', '\u00ff', '¨', '\u00ca', '\u00ff', '\'', '\0', '\0', '\0', '\0', '\0', '\0', '\0', '\0', '\0', '\0', '\0', '\0', '\0', '\0', '\0', '\0', '\0', '\0', '\0', '\0', '\0', '\0', '\0', '\0', '\0', '\0', '\0', '\0', '\0', '\0', '\0', '\0', '\0', '\0', '\0', '\0', '\0', '\0', '\0', '\0', '\0', '\0', '\0', '\0', '\0', '\0', '\0', '\0', '\0', '\0', '\0', '\0', '\0', '\0', '\0', '\0', '\0', '\0', '\0', '\0', '\0', '\0', '\0', '\0', '\0', '\0', '\0', '\0', '\0', '\0', '\0', '\0', 'v', '©', '\u0097', '\'', '\0', '\0', '\0', '\0', '\0', '\0', '\0', '\0', '\0', '\0', '\0', '\0', '\0', '\0', '\0', '\0', '\0', '\0', '\0', '\0', '\0', '\0', '\0', '\0', '\0', '\0', '\0', '\0', '\0', '\0', '\0', '\0' };
        this.c = true;
    }
    
    public void init() {
        this.void = this.size().width;
        this.a = this.size().height;
        this.byte = new int[this.void * this.a];
        this.char = new Image[2];
        this.i = Integer.parseInt(this.getParameter("delay"));
        this.b = Integer.parseInt(this.getParameter("delaystart"));
        this.k = Integer.parseInt(this.getParameter("encryptionduration"));
        this.case = Integer.parseInt(this.getParameter("mode"));
        this.j = Integer.parseInt(this.getParameter("key1"));
        this.g = Integer.parseInt(this.getParameter("key2"));
        this.d = Integer.parseInt(this.getParameter("key3"));
        final String host = this.getDocumentBase().getHost();
        this.l = 83499;
        for (int i = 0; i < host.length(); ++i) {
            this.l += 2 + i + host.charAt(i) * host.charAt(i);
        }
        this.l %= 50000;
        System.out.println("Domain: <" + host + ">");
        if (this.l == this.j || this.l == this.g || this.l == this.d) {
            this.int = true;
        }
        this.if = this.getGraphics();
        this.null = new int[this.void * this.a];
        this.for = new int[this.void * this.a];
        this.long = new char[this.void][this.a];
        (this.else = new MemoryImageSource(this.void, this.a, this.null, 0, this.void)).setAnimated(true);
        this.m = this.createImage(this.else);
        this.if();
        this.a();
        this.addMouseListener(new a());
        this.addMouseMotionListener(new b());
    }
    
    public void run() {
        boolean b = true;
        if (this.case == 2) {
            for (int i = 0; i < this.k; ++i) {
                for (int j = 0; j < this.void; ++j) {
                    for (int k = 0; k < this.a; ++k) {
                        final int n = k * this.void + j;
                        final char c = this.long[j][k];
                        if (c == 'u') {
                            this.null[n] = this.for[n - this.void];
                        }
                        else if (c == 'd') {
                            this.null[n] = this.for[n + this.void];
                        }
                        else if (c == 'l') {
                            this.null[n] = this.for[n - 1];
                        }
                        else {
                            this.null[n] = this.for[n + 1];
                        }
                    }
                }
                for (int l = 0; l < this.void; ++l) {
                    for (int n2 = 0; n2 < this.a; ++n2) {
                        final int n3 = n2 * this.void + l;
                        this.for[n3] = this.null[n3];
                    }
                }
            }
        }
        if (this.b > 0) {
            if (!this.int) {
                this.do();
            }
            for (int n4 = 0; n4 < this.b; ++n4) {
                this.else.newPixels();
                this.if.drawImage(this.m, 0, 0, this);
                try {
                    Thread.sleep(100L);
                }
                catch (InterruptedException ex) {}
            }
        }
        while (b) {
            if (!this.c) {
                break;
            }
            if (this.case == 2) {
                --this.k;
                if (this.k <= 0) {
                    b = false;
                }
            }
            if (this.case == 3) {
                for (int n5 = 0; n5 < this.void; ++n5) {
                    for (int n6 = 1; n6 < this.a - 1; ++n6) {
                        final int n7 = n6 * this.void + n5;
                        final char c2 = this.long[n5][n6];
                        if (c2 == 'd') {
                            this.null[n7 - this.void] = this.for[n7];
                        }
                        else if (c2 == 'u') {
                            this.null[n7 + this.void] = this.for[n7];
                        }
                        else if (c2 == 'l') {
                            this.null[n7 - 1] = this.for[n7];
                        }
                        else {
                            this.null[n7 + 1] = this.for[n7];
                        }
                    }
                }
            }
            else {
                for (int n8 = 0; n8 < this.void; ++n8) {
                    for (int n9 = 0; n9 < this.a; ++n9) {
                        final int n10 = n9 * this.void + n8;
                        final char c3 = this.long[n8][n9];
                        if (c3 == 'u') {
                            this.null[n10 - this.void] = this.for[n10];
                        }
                        else if (c3 == 'd') {
                            this.null[n10 + this.void] = this.for[n10];
                        }
                        else if (c3 == 'l') {
                            this.null[n10 - 1] = this.for[n10];
                        }
                        else {
                            this.null[n10 + 1] = this.for[n10];
                        }
                    }
                }
            }
            for (int n11 = 0; n11 < this.void; ++n11) {
                for (int n12 = 0; n12 < this.a; ++n12) {
                    final int n13 = n12 * this.void + n11;
                    this.for[n13] = this.null[n13];
                }
            }
            if (!this.int) {
                this.do();
            }
            this.else.newPixels();
            this.if.drawImage(this.m, 0, 0, this);
            try {
                Thread.sleep(this.i);
            }
            catch (InterruptedException ex2) {}
        }
        while (this.c) {
            this.else.newPixels();
            this.if.drawImage(this.m, 0, 0, this);
            try {
                Thread.sleep(100L);
            }
            catch (InterruptedException ex3) {}
        }
        this.c = false;
    }
    
    public void do() {
        final int n = 10;
        final int n2 = 10;
        for (int i = 0; i < this.goto; ++i) {
            for (int j = 0; j < this.e; ++j) {
                this.null[(j + n2) * this.void + i + n] = (0xFF000000 | this.new[i + j * this.goto] << 16 | this.new[i + j * this.goto] << 8 | this.new[i + j * this.goto]);
            }
        }
        for (int k = -2; k < this.goto + 2; ++k) {
            this.null[(n2 - 3) * this.void + k + n] = -986896;
            this.null[(n2 - 2) * this.void + k + n] = -16777216;
            this.null[(n2 - 1) * this.void + k + n] = -16777216;
            this.null[(n2 + this.e) * this.void + k + n] = -16777216;
            this.null[(n2 + this.e + 1) * this.void + k + n] = -986896;
        }
        for (int l = -3; l < this.e + 2; ++l) {
            this.null[(l + n2) * this.void + n - 3] = -986896;
            if (l >= 0 && l <= this.e) {
                this.null[(l + n2) * this.void + n - 2] = -16777216;
                this.null[(l + n2) * this.void + n - 1] = -16777216;
                this.null[(l + n2) * this.void + n + this.goto] = -16777216;
                this.null[(l + n2) * this.void + n + this.goto + 1] = -16777216;
            }
            this.null[(l + n2) * this.void + n + this.goto + 2] = -986896;
        }
    }
    
    public void if() {
        this.do = new MediaTracker(this);
        this.char[0] = this.getImage(this.getDocumentBase(), this.getParameter("movematrixname"));
        this.do.addImage(this.char[0], 0);
        this.do.checkID(0, true);
        this.char[1] = this.getImage(this.getDocumentBase(), this.getParameter("imagename"));
        this.do.addImage(this.char[1], 1);
        this.do.checkID(1, true);
        while (!this.do.checkAll()) {
            try {
                Thread.sleep(50L);
            }
            catch (InterruptedException ex) {}
        }
    }
    
    public void a() {
        this.h = new PixelGrabber(this.char[1], 0, 0, this.void, this.a, this.byte, 0, this.void);
        try {
            this.h.grabPixels();
        }
        catch (InterruptedException ex) {
            System.err.println("interrupted waiting for pixels!");
            return;
        }
        for (int i = 0; i < this.void * this.a; ++i) {
            this.for[i] = (0xFF000000 | this.try.getRed(this.byte[i]) << 16 | this.try.getGreen(this.byte[i]) << 8 | this.try.getBlue(this.byte[i]));
            this.null[i] = this.for[i];
        }
        this.h = new PixelGrabber(this.char[0], 0, 0, this.void, this.a, this.byte, 0, this.void);
        try {
            this.h.grabPixels();
        }
        catch (InterruptedException ex2) {
            System.err.println("interrupted waiting for pixels!");
            return;
        }
        for (int j = 0; j < this.void; ++j) {
            for (int k = 0; k < this.a; ++k) {
                final int n = this.void * k + j;
                final int red = this.try.getRed(this.byte[n]);
                final int green = this.try.getGreen(this.byte[n]);
                final int blue = this.try.getBlue(this.byte[n]);
                if (red > 128 && green < 128 && blue < 128) {
                    this.long[j][k] = 'u';
                }
                else if (red > 128 && green > 128 && blue < 128) {
                    this.long[j][k] = 'r';
                }
                else if (red < 128 && green > 128 && blue < 128) {
                    this.long[j][k] = 'd';
                }
                else {
                    this.long[j][k] = 'l';
                }
            }
        }
    }
    
    public void update(final Graphics graphics) {
    }
    
    public void paint(final Graphics graphics) {
    }
    
    public void start() {
        if (this.n == null) {
            (this.n = new Thread(this)).start();
        }
    }
    
    public void stop() {
        if (this.n != null) {
            this.n.stop();
            this.n = null;
            this.c = false;
        }
    }
    
    class b extends MouseMotionAdapter
    {
        public void mouseMoved(final MouseEvent mouseEvent) {
            if (mouseEvent.getX() < 89 && mouseEvent.getY() < 24) {
                if (!a.this.f) {
                    a.this.setCursor(new Cursor(12));
                    a.this.f = true;
                }
            }
            else if (a.this.f) {
                a.this.setCursor(new Cursor(0));
                a.this.f = false;
            }
        }
        
        public void mouseDragged(final MouseEvent mouseEvent) {
            this.mouseMoved(mouseEvent);
        }
    }
    
    class a extends MouseAdapter
    {
        public void mousePressed(final MouseEvent mouseEvent) {
            if (!a.this.int && mouseEvent.getX() < 89 && mouseEvent.getY() < 24) {
                try {
                    a.this.getAppletContext().showDocument(new URL("http://www.eigelb.at"), "_blank");
                }
                catch (MalformedURLException ex) {}
            }
        }
    }
}
