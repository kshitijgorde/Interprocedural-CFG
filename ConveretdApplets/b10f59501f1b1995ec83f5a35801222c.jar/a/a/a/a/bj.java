// 
// Decompiled by Procyon v0.5.30
// 

package a.a.a.a;

import java.awt.image.ImageObserver;
import java.awt.image.DataBufferUShort;
import java.awt.image.DataBufferByte;
import java.awt.image.DataBufferInt;
import java.awt.image.BufferedImage;
import java.awt.Component;

public class bj extends i
{
    int H;
    short[] G;
    byte[] I;
    private static int K;
    private int J;
    
    static {
        bj.K = 0;
    }
    
    public bj() {
        this.H = 0;
        this.G = null;
        this.I = null;
        this.J = 0;
    }
    
    public void if() {
        super.if();
        this.G = null;
    }
    
    public void a(final Component z, final boolean a, final l c) throws Exception {
        final BufferedImage bufferedImage = (BufferedImage)z.createImage(1, 1);
        bj.K = bufferedImage.getColorModel().getPixelSize();
        this.J = bufferedImage.getRaster().getDataBuffer().getDataType();
        if ((bj.K == 32 && this.J == 3) || (bj.K == 24 && this.J == 3)) {
            ((DataBufferInt)bufferedImage.getRaster().getDataBuffer()).getData();
        }
        else if (bj.K == 24 && this.J == 0) {
            ac.af = false;
            ((DataBufferByte)bufferedImage.getRaster().getDataBuffer()).getData();
        }
        else {
            if (bj.K != 16 || this.J != 1) {
                ac.af = false;
                throw new Exception();
            }
            ac.af = false;
            ((DataBufferUShort)bufferedImage.getRaster().getDataBuffer()).getData();
        }
        super.A = a;
        super.z = z;
        super.C = c;
        if (c.E == null) {
            System.out.println("Render mode 1.");
        }
    }
    
    public void char() {
    }
    
    public void for() {
        try {
            if (!ac.af) {
                try {
                    int n = 0;
                    if (bj.K == 16) {
                        while (true) {
                            final int n2;
                            this.G[n] = (short)(((n2 = super.q[n++]) >> 3 & 0x1F) | (n2 >> 5 & 0x7E0) | (n2 >> 8 & 0xF800));
                        }
                    }
                    else if (bj.K == 24) {
                        int n3 = 0;
                        while (true) {
                            this.I[n++] = (byte)(super.q[n3++] & 0xFF);
                            this.I[n++] = (byte)(super.q[n3] >> 8 & 0xFF);
                            this.I[n++] = (byte)(super.q[n3] >> 16 & 0xFF);
                        }
                    }
                }
                catch (Exception ex) {}
            }
            this.else();
            super.z.getGraphics().drawImage(super.B, 0, 0, super.z);
        }
        catch (Exception ex2) {}
    }
    
    protected void else() {
        if (super.C.E != null && !super.C.E.z) {
            if (!super.C.a && super.C.K != null) {
                final int width = super.C.K.getWidth(null);
                final int height = super.C.K.getHeight(null);
                int new1 = super.j - width >> 1;
                int do1 = super.u - height >> 1;
                if (super.C.new != -1000 && super.C.do != -1000) {
                    new1 = super.C.new;
                    do1 = super.C.do;
                }
                super.B.getGraphics().drawImage(super.C.K, new1, do1, super.z);
            }
            else if (super.C.a) {
                ap.a(super.w, this, super.j - super.w.j >> 1, super.u - super.w.u >> 1, super.w.j, super.w.u);
            }
        }
    }
    
    public void try() {
        if (super.j == super.C.width && super.u == super.C.height) {
            return;
        }
        super.j = super.C.width;
        super.u = super.C.height;
        super.t = super.j;
        super.k = super.u;
        super.B = null;
        System.gc();
        if (ac.af) {
            super.B = super.z.createImage(super.j, super.u);
            super.q = ((DataBufferInt)((BufferedImage)super.B).getRaster().getDataBuffer()).getData();
            for (int i = 0; i < super.j * super.u; ++i) {
                super.q[i] = super.C.D;
            }
        }
        else {
            super.q = new int[super.j * super.u];
            super.B = super.z.createImage(super.j, super.u);
            if (bj.K == 16) {
                this.G = ((DataBufferUShort)((BufferedImage)super.B).getRaster().getDataBuffer()).getData();
                final int d = super.C.D;
                final short n = (short)((d >> 3 & 0x1F) | (d >> 5 & 0x7E0) | (d >> 8 & 0xF800 & 0xFFFF));
                for (int j = 0; j < super.j * super.u; ++j) {
                    super.q[j] = super.C.D;
                    this.G[j] = n;
                }
            }
            else if (bj.K == 24) {
                this.I = ((DataBufferByte)((BufferedImage)super.B).getRaster().getDataBuffer()).getData();
                final int d2 = super.C.D;
                final byte b = (byte)(d2 >> 16 & 0xFF);
                final byte b2 = (byte)(d2 >> 8 & 0xFF);
                final byte b3 = (byte)(d2 & 0xFF);
                int n2 = 0;
                for (int k = 0; k < super.j * super.u; ++k) {
                    super.q[k] = super.C.D;
                    this.I[n2++] = b3;
                    this.I[n2++] = b2;
                    this.I[n2++] = b;
                }
            }
        }
        if (super.C.E != null && !super.C.E.z) {
            this.a(super.B.getGraphics(), super.j, super.u);
        }
    }
}
