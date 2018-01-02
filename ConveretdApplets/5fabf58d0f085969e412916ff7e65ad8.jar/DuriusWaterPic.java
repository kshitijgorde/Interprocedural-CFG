import java.awt.Image;
import java.awt.Component;
import java.awt.MediaTracker;
import java.awt.image.ImageProducer;
import java.awt.image.ColorModel;
import java.awt.image.MemoryImageSource;
import java.awt.image.DirectColorModel;
import java.awt.image.ImageObserver;
import java.awt.Graphics;
import java.awt.event.MouseEvent;

// 
// Decompiled by Procyon v0.5.30
// 

public final class DuriusWaterPic extends WrApp implements Runnable
{
    private Screen32 D;
    private Screen32 E;
    private Waterpic F;
    int G;
    int H;
    int I;
    private boolean J;
    private boolean K;
    private boolean L;
    
    public final void mouseMoved(final MouseEvent mouseEvent) {
        super.mouseMoved(mouseEvent);
        if (this.K) {
            final int x = mouseEvent.getX();
            final int y = mouseEvent.getY();
            int n = x - this.F.j / 2;
            int n2 = y - this.F.j / 2;
            if (n > super.r - this.F.j) {
                n = super.r - this.F.j;
            }
            if (n2 > super.s - this.F.j) {
                n2 = super.s - this.F.j;
            }
            if (n < 0) {
                n = 0;
            }
            if (n2 < 0) {
                n2 = 0;
            }
            this.F.a(n, n2, this.F.k, this.F.j);
        }
    }
    
    public void c() {
    }
    
    public final void update(final Graphics graphics) {
        super.j = System.currentTimeMillis();
        if (super.a != null) {
            if (super.m) {
                this.showStatus(b(")w\u007f\u0015/n,|\u0012bog~\u0017|4p%\u0006z,,"));
            }
            else {
                this.F.a();
                if (this.J) {
                    if (this.L) {
                        if (this.I > 0) {
                            this.F.a((int)(Math.random() * (this.F.c >> 1) + (this.F.c >> 2) - (this.F.l >> 1)), (int)(Math.random() * (this.F.d >> 1) + (this.F.d >> 2) - (this.F.l >> 1)), this.F.k / 2, this.F.l);
                            this.I -= (int)WrApp.l;
                        }
                    }
                    else {
                        this.F.a((int)(Math.random() * (this.F.c >> 1) + (this.F.c >> 2) - (this.F.l >> 1)), (int)(Math.random() * (this.F.d >> 1) + (this.F.d >> 2) - (this.F.l >> 1)), this.F.k / 2, this.F.l);
                    }
                }
                this.F.a(this.D, this.E);
            }
            super.d.flush();
            graphics.drawImage(super.d, 0, 0, null);
        }
    }
    
    public void init() {
        super.t = 15637;
        super.init();
        (this.D = new Screen32(super.r, super.s)).a(super.q);
        super.b = new DirectColorModel(32, 16711680, 65280, 255);
        super.c = new MemoryImageSource(this.D.d(), this.D.b(), super.b, this.D.c(), 0, this.D.d());
        super.d = this.createImage(super.c);
        this.showStatus(b("\u0000s{\tp5#b\u000b|5jj\t|;je\u0002;"));
        this.E = new Screen32(super.r, super.s);
        this.F = new Waterpic(super.r, super.s);
        this.J = false;
        this.L = false;
        this.I = 0;
        this.K = true;
        super.h = this.getParameter(b(",l~\u0016p"));
        if (super.h != null) {
            this.F.j = Integer.parseInt(super.h);
            if (Integer.parseInt(super.h) == 0) {
                this.K = false;
            }
        }
        super.h = this.getParameter(b("/lb\u0016p"));
        if (super.h != null) {
            this.F.l = Integer.parseInt(super.h);
            if (Integer.parseInt(super.h) != 0) {
                super.h = this.getParameter(b("5jf\u0000g"));
                if (super.h != null && Integer.parseInt(super.h) >= 1) {
                    this.L = true;
                    this.I = Integer.parseInt(super.h);
                }
                this.J = true;
            }
        }
        super.h = this.getParameter(b("%jf"));
        if (super.h != null) {
            this.F.g = Integer.parseInt(super.h);
        }
        super.h = this.getParameter(b("2wy\u0000{&wc"));
        if (super.h != null) {
            this.F.k = Integer.parseInt(super.h);
        }
        super.h = this.getParameter(b("(nj\u0002p"));
        final Image image = this.getImage(this.getDocumentBase(), super.h);
        final MediaTracker mediaTracker = new MediaTracker(this);
        mediaTracker.addImage(image, 0);
        try {
            mediaTracker.waitForAll();
        }
        catch (InterruptedException ex) {
            this.showStatus(b("\bm\u007f\u0000g3v{\u0011p%Fs\u0006p1wb\n{"));
        }
        if (mediaTracker.isErrorAny()) {
            super.o = true;
            super.i = super.h;
            super.n = true;
            return;
        }
        this.G = image.getWidth(this);
        this.H = image.getHeight(this);
        this.E.a(image);
        super.n = true;
        this.showStatus("");
    }
    
    private static String b(final String s) {
        final char[] charArray = s.toCharArray();
        final int length = charArray.length;
        int n = 0;
        while (true) {
            Label_0089: {
                if (length > 1) {
                    break Label_0089;
                }
                char[] array2;
                char[] array = array2 = charArray;
                int n3;
                int n2 = n3 = n;
                while (true) {
                    final char c = array2[n3];
                    char c2 = '\0';
                    switch (n % 5) {
                        case 0: {
                            c2 = 'A';
                            break;
                        }
                        case 1: {
                            c2 = '\u0003';
                            break;
                        }
                        case 2: {
                            c2 = '\u000b';
                            break;
                        }
                        case 3: {
                            c2 = 'e';
                            break;
                        }
                        default: {
                            c2 = '\u0015';
                            break;
                        }
                    }
                    array[n2] = (char)(c ^ c2);
                    ++n;
                    if (length != 0) {
                        break;
                    }
                    array = (array2 = charArray);
                    n2 = (n3 = length);
                }
            }
            if (n >= length) {
                return new String(charArray);
            }
            continue;
        }
    }
}
