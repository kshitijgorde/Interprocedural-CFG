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
    int I;
    short[] H;
    byte[] J;
    private static int L;
    private int K;
    
    static {
        bj.L = 0;
    }
    
    public bj() {
        this.I = 0;
        this.H = null;
        this.J = null;
        this.K = 0;
    }
    
    public void if() {
        super.if();
        this.H = null;
    }
    
    public void a(final Component a, final boolean b, final l d) throws Exception {
        final BufferedImage bufferedImage = (BufferedImage)a.createImage(1, 1);
        bj.L = bufferedImage.getColorModel().getPixelSize();
        this.K = bufferedImage.getRaster().getDataBuffer().getDataType();
        if ((bj.L == 32 && this.K == 3) || (bj.L == 24 && this.K == 3)) {
            ((DataBufferInt)bufferedImage.getRaster().getDataBuffer()).getData();
        }
        else if (bj.L == 24 && this.K == 0) {
            ac.ah = false;
            ((DataBufferByte)bufferedImage.getRaster().getDataBuffer()).getData();
        }
        else {
            if (bj.L != 16 || this.K != 1) {
                ac.ah = false;
                throw new Exception();
            }
            ac.ah = false;
            ((DataBufferUShort)bufferedImage.getRaster().getDataBuffer()).getData();
        }
        super.B = b;
        super.A = a;
        super.D = d;
        if (d.G == null) {
            System.out.println("Render mode 1.");
        }
    }
    
    public void byte() {
    }
    
    public void for() {
        try {
            if (!ac.ah) {
                try {
                    int n = 0;
                    if (bj.L == 16) {
                        while (true) {
                            final int n2;
                            this.H[n] = (short)(((n2 = super.x[n++]) >> 3 & 0x1F) | (n2 >> 5 & 0x7E0) | (n2 >> 8 & 0xF800));
                        }
                    }
                    else if (bj.L == 24) {
                        int n3 = 0;
                        while (true) {
                            this.J[n++] = (byte)(super.x[n3++] & 0xFF);
                            this.J[n++] = (byte)(super.x[n3] >> 8 & 0xFF);
                            this.J[n++] = (byte)(super.x[n3] >> 16 & 0xFF);
                        }
                    }
                }
                catch (Exception ex) {}
            }
            if (super.D.else) {
                if (a.a.a.a.l.E && super.D.a) {
                    super.A.getGraphics().drawImage(super.C, 0, 22, super.A);
                }
                else {
                    super.A.getGraphics().drawImage(super.C, 0, 0, super.A);
                }
            }
        }
        catch (Exception ex2) {}
    }
    
    public void new() {
        if (super.s == super.D.width && super.r == super.D.height) {
            return;
        }
        super.s = super.D.width;
        super.r = super.D.height;
        super.l = super.s;
        super.p = super.r;
        super.C = null;
        System.gc();
        if (ac.ah) {
            super.C = super.A.createImage(super.s, super.r);
            super.x = ((DataBufferInt)((BufferedImage)super.C).getRaster().getDataBuffer()).getData();
            System.out.println("---");
        }
        else {
            super.x = new int[super.s * super.r];
            super.C = super.A.createImage(super.s, super.r);
            if (bj.L == 16) {
                this.H = ((DataBufferUShort)((BufferedImage)super.C).getRaster().getDataBuffer()).getData();
            }
            else if (bj.L == 24) {
                this.J = ((DataBufferByte)((BufferedImage)super.C).getRaster().getDataBuffer()).getData();
            }
        }
    }
}
