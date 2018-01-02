import java.awt.image.ImageObserver;
import java.awt.image.MemoryImageSource;
import java.awt.Color;
import java.awt.image.PixelGrabber;
import java.awt.image.ImageProducer;
import java.awt.image.ImageFilter;
import java.awt.image.FilteredImageSource;
import java.awt.image.CropImageFilter;
import java.awt.Component;
import java.awt.MediaTracker;
import java.awt.Image;

// 
// Decompiled by Procyon v0.5.30
// 

public class a
{
    private int[] do;
    private int int;
    private int char;
    private int for;
    private int if;
    private int a;
    private int else;
    private Image byte;
    private MediaTracker case;
    private int[] new;
    private int try;
    
    public Image a(final Component component, final int n, final int n2, final int a, final int else1) {
        this.a = a;
        this.else = else1;
        this.case = new MediaTracker(component);
        this.byte = component.createImage(new FilteredImageSource(this.byte.getSource(), new CropImageFilter(n, n2, this.a, this.else)));
        this.case.addImage(this.byte, 1);
        try {
            this.case.waitForAll();
        }
        catch (InterruptedException ex) {
            System.out.println("Error waiting for image to load");
        }
        return this.byte;
    }
    
    public void a(final Image byte1, final int a, final int else1) {
        this.a = a;
        this.else = else1;
        this.byte = byte1;
    }
    
    public Image if(final Component component) {
        this.case = new MediaTracker(component);
        this.do = new int[this.a * this.else];
        final PixelGrabber pixelGrabber = new PixelGrabber(this.byte, 0, 0, this.a, this.else, this.do, 0, this.a);
        try {
            pixelGrabber.grabPixels();
        }
        catch (InterruptedException ex) {
            System.err.println("interrupted waiting for pixels!");
            return null;
        }
        return null;
    }
    
    public void a(final Component component, final int n, final int n2, final Color color, final Color color2, final int[] new1, final int try1) {
        this.new = new1;
        this.try = try1;
        this.int = (0xFF000000 | color.getRed() << 16 | color.getGreen() << 8 | color.getBlue());
        this.char = (0xFF000000 | color2.getRed() << 16 | color2.getGreen() << 8 | color2.getBlue());
        this.a(n, n2, this.int, this.char);
    }
    
    public Image a(final Component component) {
        this.byte = component.createImage(new MemoryImageSource(this.a, this.else, this.do, 0, this.a));
        try {
            this.case.waitForAll();
        }
        catch (InterruptedException ex) {
            System.out.println("Error waiting for image to load");
        }
        return this.byte;
    }
    
    public Image a(final Component component, final Image image) {
        this.a = image.getWidth(component);
        this.else = image.getHeight(component);
        this.case = new MediaTracker(component);
        this.do = new int[this.a * this.else];
        final PixelGrabber pixelGrabber = new PixelGrabber(image, 0, 0, this.a, this.else, this.do, 0, this.a);
        try {
            pixelGrabber.grabPixels();
        }
        catch (InterruptedException ex) {
            System.err.println("interrupted waiting for pixels!");
            return null;
        }
        this.byte = component.createImage(new MemoryImageSource(this.a, this.else, this.do, 0, this.a));
        try {
            this.case.waitForAll();
        }
        catch (InterruptedException ex2) {
            System.out.println("Error waiting for image to load");
        }
        return this.byte;
    }
    
    public void a(final int n, final int n2, final int n3, final int n4) {
        int n5 = -1;
        for (int n6 = n; n6 >= 0 && this.do[n2 * this.a + n6] != n3; --n6) {
            this.do[n2 * this.a + n6] = n4;
            this.new[n2 * this.a + n6 + this.for] = this.try;
            n5 = n6;
        }
        if (n5 == -1) {
            return;
        }
        int n7 = n;
        for (int n8 = n + 1; n8 < this.a && this.do[n2 * this.a + n8] != n3; ++n8) {
            this.do[n2 * this.a + n8] = n4;
            this.new[n2 * this.a + n8 + this.for] = this.try;
            n7 = n8;
        }
        if (n2 > 0) {
            int n9 = 1;
            for (int i = n5; i <= n7; ++i) {
                final int n10 = this.do[(n2 - 1) * this.a + i];
                if (n9 == 1) {
                    if (n10 != n3 && n10 != n4) {
                        this.a(i, n2 - 1, n3, n4);
                        n9 = 0;
                    }
                }
                else if (n10 == n3 || n10 == n4) {
                    n9 = 1;
                }
            }
        }
        if (n2 < this.else - 1) {
            int n11 = 1;
            for (int j = n5; j <= n7; ++j) {
                final int n12 = this.do[(n2 + 1) * this.a + j];
                if (n11 == 1) {
                    if (n12 != n3 && n12 != n4) {
                        this.a(j, n2 + 1, n3, n4);
                        n11 = 0;
                    }
                }
                else if (n12 == n3 || n12 == n4) {
                    n11 = 1;
                }
            }
        }
    }
    
    public void a(final Color color, final int[] array, final String s, final int n, final Image image, final int n2) {
        if (s.equals("FILL")) {
            this.a(color, array, n2);
        }
        if (s.equals("FADE")) {
            this.a(color, array, n, n2);
        }
        if (s.equals("BRIGHT")) {
            this.if(array, n, n2);
        }
        if (s.equals("DARK")) {
            this.a(array, n, n2);
        }
    }
    
    private void a(final int[] array, final int n, final int n2) {
        for (int i = 0; i < this.else; ++i) {
            for (int j = 0; j < this.a; ++j) {
                final int n3 = i * this.a;
                if (array[n3 + j] == n2) {
                    final int n4 = this.do[n3 + j];
                    int n5 = this.for(n4) - this.for(n4) * n / 100;
                    int n6 = this.do(n4) - this.do(n4) * n / 100;
                    int n7 = this.if(n4) - this.if(n4) * n / 100;
                    if (n5 < 0) {
                        n5 = 0;
                    }
                    if (n6 < 0) {
                        n6 = 0;
                    }
                    if (n7 < 0) {
                        n7 = 0;
                    }
                    this.do[n3 + j] = (0xFF000000 | n5 << 16 | n6 << 8 | n7);
                }
            }
        }
    }
    
    private void if(final int[] array, final int n, final int n2) {
        for (int i = 0; i < this.else; ++i) {
            for (int j = 0; j < this.a; ++j) {
                final int n3 = i * this.a;
                if (array[n3 + j] == n2) {
                    final int n4 = this.do[n3 + j];
                    int n5 = this.for(n4) + this.for(n4) * n / 100;
                    int n6 = this.do(n4) + this.do(n4) * n / 100;
                    int n7 = this.if(n4) + this.if(n4) * n / 100;
                    if (n5 > 255) {
                        n5 = 255;
                    }
                    if (n6 > 255) {
                        n6 = 255;
                    }
                    if (n7 > 255) {
                        n7 = 255;
                    }
                    this.do[n3 + j] = (0xFF000000 | n5 << 16 | n6 << 8 | n7);
                }
            }
        }
    }
    
    private void a(final Color color, final int[] array, final int n) {
        int i = 0;
        int j = 0;
        final int n2 = 0xFF000000 | color.getRed() << 16 | color.getGreen() << 8 | color.getBlue();
        try {
            for (j = 0; j < this.else; ++j) {
                for (i = 0; i < this.a; ++i) {
                    final int n3 = j * this.a;
                    if (array[n3 + i] == n) {
                        this.do[n3 + i] = n2;
                    }
                }
            }
        }
        catch (Exception ex) {
            System.out.println(String.valueOf(String.valueOf(new StringBuffer("").append(i).append(":").append(j))));
        }
    }
    
    private void a(final Color color, final int[] array, int n, final int n2) {
        int i = 0;
        int j = 0;
        final int n3 = color.getRed() * n / 100;
        final int n4 = color.getGreen() * n / 100;
        final int n5 = color.getBlue() * n / 100;
        n = 100 - n;
        try {
            for (j = 0; j < this.else; ++j) {
                for (i = 0; i < this.a; ++i) {
                    final int n6 = j * this.a;
                    if (array[n6 + i] == n2) {
                        final int n7 = this.do[n6 + i];
                        this.do[n6 + i] = (0xFF000000 | this.for(n7) * n / 100 + n3 << 16 | this.do(n7) * n / 100 + n4 << 8 | this.if(n7) * n / 100 + n5);
                    }
                }
            }
        }
        catch (Exception ex) {
            System.out.println(String.valueOf(String.valueOf(new StringBuffer("").append(i).append(":").append(j))));
        }
    }
    
    public int a(final int n) {
        return (n & 0xFF000000) >>> 24;
    }
    
    public int for(final int n) {
        return (n & 0xFF0000) >> 16;
    }
    
    public int do(final int n) {
        return (n & 0xFF00) >> 8;
    }
    
    public int if(final int n) {
        return n & 0xFF;
    }
}
