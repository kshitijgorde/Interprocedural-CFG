// 
// Decompiled by Procyon v0.5.30
// 

package wordle.core;

import java.awt.Font;
import java.io.InputStream;
import java.awt.image.ImageObserver;
import java.awt.Image;
import java.awt.Component;
import java.awt.Graphics;
import java.awt.Shape;
import java.awt.RenderingHints;
import java.awt.Color;
import java.awt.geom.GeneralPath;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

public class w
{
    private final BufferedImage[] a;
    private transient int b;
    
    public w(int i) {
        this.a = new BufferedImage[12];
        this.b = 0;
        final double n = 3.141592653589793 / (4 * this.a.length);
        final double n2 = 16 / 2.0;
        final double n3 = 16 / 8.0;
        BufferedImage[] a;
        int n4;
        BufferedImage bufferedImage;
        Graphics2D graphics2D;
        int j;
        double n5;
        GeneralPath generalPath;
        double n6;
        double n7;
        float n8;
        for (i = 0; i < this.a.length; ++i) {
            a = this.a;
            n4 = i;
            bufferedImage = new BufferedImage(16, 16, 2);
            a[n4] = bufferedImage;
            (graphics2D = (Graphics2D)bufferedImage.getGraphics()).translate(16 / 2.0, 16 / 2.0);
            for (j = 0; j < this.a.length; ++j) {
                n5 = (i + j) % this.a.length * 3.141592653589793 * 2.0 / this.a.length;
                generalPath = new GeneralPath();
                n6 = n5 - n;
                n7 = n5 + n;
                generalPath.moveTo((float)(n3 * Math.cos(n6)), (float)(n3 * Math.sin(n6)));
                generalPath.lineTo((float)(n2 * Math.cos(n6)), (float)(n2 * Math.sin(n6)));
                generalPath.lineTo((float)(n2 * Math.cos(n7)), (float)(n2 * Math.sin(n7)));
                generalPath.lineTo((float)(n3 * Math.cos(n7)), (float)(n3 * Math.sin(n7)));
                generalPath.closePath();
                n8 = j / this.a.length;
                graphics2D.setColor(new Color(n8, n8, n8));
                graphics2D.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                graphics2D.fill(generalPath);
            }
            graphics2D.dispose();
        }
    }
    
    public final void a() {
        this.b = (this.b + 1) % this.a.length;
    }
    
    public final void a(final Graphics graphics, final int n, final int n2, final Component component) {
        graphics.drawImage(this.a[this.b], 3, 4, component);
    }
    
    public w() {
    }
    
    public static Font a(final InputStream inputStream) {
        try {
            return Font.createFont(0, inputStream);
        }
        finally {
            inputStream.close();
        }
    }
}
