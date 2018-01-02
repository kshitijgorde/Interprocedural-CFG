import java.awt.image.MemoryImageSource;
import java.awt.image.ImageProducer;
import java.awt.image.PixelGrabber;
import java.awt.Image;

// 
// Decompiled by Procyon v0.5.30
// 

public class o
{
    private Image Ba;
    private int Ca;
    private int Da;
    private int Ea;
    private int[] Fa;
    private int[] Ga;
    private int Ha;
    private int Ia;
    private static String e = "\u7f7f\u7f65";
    
    public o(final Image ba, final int ha, final int ia) {
        this.Da = -16777216;
        this.Ea = -16777216;
        this.Ba = ba;
        this.Ha = ha;
        this.Ia = ia;
    }
    
    public void init() {
        this.a();
        this.b();
    }
    
    private void a() {
        this.Fa = new int[this.Ha * this.Ia];
        final PixelGrabber pixelGrabber = new PixelGrabber(this.Ba, 0, 0, this.Ha, this.Ia, this.Fa, 0, this.Ha);
        try {
            pixelGrabber.grabPixels();
        }
        catch (InterruptedException ex) {
            System.out.println(String.valueOf(this.getClass().getName()) + o.e + ex.getMessage());
        }
    }
    
    private void b() {
        this.Ga = new int[this.Ha * this.Ia];
        for (int i = 0; i < this.Fa.length; ++i) {
            if (this.Fa[i] == this.Ea || this.Fa[i] == this.Da) {
                this.Ga[i] = this.Ea;
            }
            else {
                this.Ga[i] = -1;
            }
        }
    }
    
    public int b(final int n, final int n2) {
        return this.Fa[n2 * this.Ha + n];
    }
    
    public ImageProducer _(final int n, final int n2) {
        return this.a(this.b(n, n2));
    }
    
    public ImageProducer a(final int n) {
        for (int i = 0; i < this.Fa.length; ++i) {
            if (this.Fa[i] == n) {
                this.Ga[i] = this.Ca;
            }
        }
        return new MemoryImageSource(this.Ha, this.Ia, this.Ga, 0, this.Ha);
    }
    
    public void b(final int ca) {
        this.Ca = ca;
    }
    
    public void _(final int da) {
        this.Da = da;
    }
    
    public int _(final int n, final int n2) {
        return this.Ga[n2 * this.Ha + n];
    }
    
    private static String a(final String s) {
        final int length = s.length();
        final char[] array = new char[length];
        for (int i = 0; i < length; ++i) {
            array[i] = (char)(s.charAt(i) ^ '\u7f45');
        }
        return new String(array);
    }
    
    static {
        o.e = a(o.e);
    }
}
