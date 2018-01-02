import java.awt.image.ImageObserver;
import java.awt.image.ImageProducer;
import java.awt.image.ImageFilter;
import java.awt.image.FilteredImageSource;
import java.awt.Graphics;
import java.net.URL;
import java.awt.Image;
import java.awt.Color;

// 
// Decompiled by Procyon v0.5.30
// 

public final class l extends ad
{
    public int a;
    public String a;
    public String b;
    public static final Color a;
    private int c;
    private Image b;
    
    static {
        a = new Color(255, 0, 255);
    }
    
    public l() {
        this.a = 0;
        this.a = "";
        this.b = "";
        this.c = -5263441;
        this.b = null;
        this.a();
    }
    
    public l(final URL url, final String s) {
        super(url, s);
        this.a = 0;
        this.a = "";
        this.b = "";
        this.c = -5263441;
        this.b = null;
        this.a();
    }
    
    public final void paint(final Graphics graphics) {
        super.paint(graphics);
        if (this.b == null) {
            this.a(graphics);
        }
        this.b(true);
    }
    
    private void b(final boolean b) {
        final Graphics graphics;
        (graphics = this.getGraphics()).setColor(super.b);
        int n = 0;
        int n2 = 0;
        int width = this.getWidth();
        int height = this.getHeight();
        for (int b2 = super.b, i = 0; i < b2; ++i) {
            graphics.draw3DRect(n, n2, width, height, b);
            ++n;
            ++n2;
            width -= 2;
            height -= 2;
        }
    }
    
    private void a() {
        super.b = 4;
        super.b = l.a;
    }
    
    private void a(final Graphics graphics) {
        this.b = this.createImage(new FilteredImageSource(super.a.getSource(), new t(this.c)));
        final int b = super.b;
        if (super.a) {
            this.prepareImage(this.b, this.getWidth() - 2 * b, this.getHeight() - 2 * b, this);
        }
        else {
            this.prepareImage(this.b, this);
        }
        super.paint(graphics);
    }
}
