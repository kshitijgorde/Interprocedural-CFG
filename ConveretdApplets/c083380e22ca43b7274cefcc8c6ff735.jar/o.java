import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.Color;

// 
// Decompiled by Procyon v0.5.30
// 

public class o
{
    protected Color[] Ka;
    private Rectangle[] La;
    private Rectangle Ma;
    private Color Na;
    private Rectangle Oa;
    private int Pa;
    
    public o(final int n) {
        this.Na = Color.white;
        this.Pa = 14;
        this.Ka = this.a();
        this.La = this.b(n);
        this.Oa = new Rectangle(n - 6, 20, 60, 260);
        this.Ma = new Rectangle(n + 22, 291, 10, 10);
    }
    
    public o(final Color[] ka, final Rectangle[] la, final Rectangle oa, final Rectangle ma) {
        this.Na = Color.white;
        this.Pa = 14;
        this.Oa = oa;
        this.Ka = ka;
        this.La = la;
        this.Ma = ma;
    }
    
    public void b(final int n, final int n2) {
        for (int i = 0; i < this.La.length; ++i) {
            if (this.La[i].contains(n, n2)) {
                this.Na = this.Ka[i];
                this.Pa = i;
                return;
            }
        }
    }
    
    public Color b() {
        return this.Na;
    }
    
    public int b() {
        return this.Pa;
    }
    
    public int _() {
        return 0xFF000000 | this.Na.getRed() << 16 | this.Na.getGreen() << 8 | this.Na.getBlue();
    }
    
    public void a(final Graphics graphics) {
        for (int i = 0; i < this.La.length; ++i) {
            graphics.setColor(this.Ka[i]);
            graphics.fillRect(this.La[i].x, this.La[i].y, this.La[i].width, this.La[i].height);
        }
        graphics.setColor(this.Na);
        graphics.fillRect(this.Ma.x, this.Ma.y, this.Ma.width, this.Ma.height);
    }
    
    public boolean contains(final int n, final int n2) {
        return this.Oa.contains(n, n2);
    }
    
    private Rectangle[] b(int n) {
        final Rectangle[] array = new Rectangle[16];
        int n2 = 26;
        final int n3 = 19;
        final int n4 = 14;
        int i = 0;
        int n5 = 0;
        while (i < 8) {
            for (int j = 0; j < 2; ++j, ++n5) {
                array[n5] = new Rectangle(n, n2, n3, n3);
                n += n3 + n4;
            }
            n -= 2 * (n3 + n4);
            n2 += n3 + n4;
            ++i;
        }
        return array;
    }
    
    private Color[] a() {
        return new Color[] { new Color(16749568), Color.red, new Color(11694664), new Color(8650752), new Color(65280), new Color(33792), new Color(16776960), new Color(16762566), new Color(16711935), new Color(8650884), new Color(4259839), new Color(4342527), new Color(12632256), new Color(132), Color.white, Color.black };
    }
}
