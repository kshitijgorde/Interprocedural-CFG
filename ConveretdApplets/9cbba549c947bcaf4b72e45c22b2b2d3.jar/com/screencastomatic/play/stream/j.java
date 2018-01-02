// 
// Decompiled by Procyon v0.5.30
// 

package com.screencastomatic.play.stream;

import com.screencastomatic.play.c.b;
import java.awt.Color;
import java.awt.image.BufferedImage;
import java.awt.image.ImageObserver;
import java.awt.Composite;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.io.ObjectInputStream;
import java.io.InputStream;
import java.io.DataInputStream;
import java.awt.Image;
import java.awt.Point;
import java.awt.Dimension;

public class j
{
    public byte[] a;
    public MouseCursor$ImgBytesType b;
    public Dimension c;
    public Point d;
    public Image e;
    
    public j(final DataInputStream dataInputStream) {
        dataInputStream.readByte();
        m.a(dataInputStream, this.a = new byte[m.a(dataInputStream)]);
        this.b = MouseCursor$ImgBytesType.values()[m.a(dataInputStream)];
        try {
            final ObjectInputStream objectInputStream = new ObjectInputStream(dataInputStream);
            this.c = (Dimension)objectInputStream.readObject();
            this.d = (Point)objectInputStream.readObject();
        }
        catch (ClassNotFoundException ex) {}
    }
    
    public Rectangle a(final int n, final int n2) {
        return new Rectangle(n - this.d.x, n2 - this.d.y, this.c.width, this.c.height);
    }
    
    public void a(final Graphics2D graphics2D, final int n, final int n2) {
        try {
            Composite composite = null;
            try {
                if (this.b == MouseCursor$ImgBytesType.b) {
                    composite = graphics2D.getComposite();
                    graphics2D.setComposite(new c(this, null));
                }
                else if (this.b == MouseCursor$ImgBytesType.e) {
                    composite = graphics2D.getComposite();
                    graphics2D.setComposite(new l(this, null));
                }
                else if (this.b == MouseCursor$ImgBytesType.d) {
                    composite = graphics2D.getComposite();
                    graphics2D.setComposite(new f(this, null));
                }
                graphics2D.drawImage(this.a(), n - this.d.x, n2 - this.d.y, null);
            }
            finally {
                if (composite != null) {
                    graphics2D.setComposite(composite);
                }
            }
        }
        catch (Exception ex) {
            System.out.println("Failed to draw mouse: " + this + " (x,y: " + n + "," + n2 + ")");
            ex.printStackTrace();
        }
    }
    
    private Image a() {
        if (this.e != null) {
            return this.e;
        }
        if (this.b == MouseCursor$ImgBytesType.a) {
            final BufferedImage e = new BufferedImage(this.c.width, this.c.height, 2);
            final int[] array = new int[this.c.width * this.c.height];
            int n = 0;
            for (int i = 0; i < array.length; ++i) {
                array[i] = (0x0 | (this.a[n++] & 0xFF) | (this.a[n++] << 8 & 0xFF00) | (this.a[n++] << 16 & 0xFF0000) | (this.a[n++] << 24 & 0xFF000000));
            }
            e.getRaster().setDataElements(0, 0, this.c.width, this.c.height, array);
            this.e = e;
        }
        else if (this.b == MouseCursor$ImgBytesType.c) {
            final BufferedImage bufferedImage = new BufferedImage(this.c.width, this.c.height, 9);
            final short[] array2 = new short[this.c.width * this.c.height];
            int n2 = 0;
            for (int j = 0; j < array2.length; ++j) {
                array2[j] = (short)((short)(0x0 | (this.a[n2++] & 0xFF)) | (this.a[n2++] << 8 & 0xFF00));
            }
            bufferedImage.getRaster().setDataElements(0, 0, this.c.width, this.c.height, array2);
            this.e = com.screencastomatic.play.c.b.a(bufferedImage, Color.BLACK);
        }
        else if (this.b == MouseCursor$ImgBytesType.e) {
            this.e = new BufferedImage(this.c.width, this.c.height, 9);
        }
        else {
            this.e = new BufferedImage(this.c.width, this.c.height, 2);
        }
        return this.e;
    }
    
    public String toString() {
        return "MouseCursor: len=" + this.a.length + " t=" + this.b + " w=" + this.c.width + " h=" + this.c.height + " hotX=" + this.d.x + " hotY=" + this.d.y;
    }
    
    private static int b(final byte b, final int n) {
        return b >> 7 - n & 0x1;
    }
}
