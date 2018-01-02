import java.awt.image.ImageObserver;
import java.awt.Graphics;
import java.awt.Color;
import java.awt.Image;
import java.awt.Canvas;

// 
// Decompiled by Procyon v0.5.30
// 

class EncycloCanvas extends Canvas
{
    Wave[] Wx;
    Wave[] Wy;
    int It;
    boolean decay;
    boolean color;
    boolean thick;
    double[] sin;
    Image OSC;
    int widthOfOSC;
    int heightOfOSC;
    
    EncycloCanvas() {
        this.Wx = new Wave[2];
        this.Wy = new Wave[2];
        this.It = 32;
        this.sin = new double[360];
        for (int i = 0; i < 360; ++i) {
            this.sin[i] = Math.sin(i / 180.0 * 3.141592653589793);
        }
        this.setBackground(Color.white);
        this.setForeground(Color.black);
    }
    
    public void paint(final Graphics graphics) {
        int n = 0;
        final int[] array = new int[2];
        final int[] array2 = new int[2];
        final int n2 = this.getSize().width / 2;
        final int n3 = this.getSize().height / 2;
        final double n4 = n2 / (this.Wx[0].A + this.Wx[1].A) * 0.95;
        final double n5 = n3 / (this.Wy[0].A + this.Wy[1].A) * 0.95;
        for (int i = 0; i < 2; ++i) {
            array[i] = this.Wx[i].P;
            array2[i] = this.Wy[i].P;
        }
        int n6 = (int)(n4 * (this.Wx[0].A * this.sin[this.Wx[0].P] + this.Wx[1].A * this.sin[this.Wx[1].P]) + n2);
        int n7 = (int)(n5 * (this.Wy[0].A * this.sin[this.Wy[0].P] + this.Wy[1].A * this.sin[this.Wy[1].P]) + n3);
        double n8 = 1.0;
        for (int j = 1; j <= this.It * 360; ++j) {
            if (++n == 360) {
                n = 0;
            }
            final int n9 = n6;
            final int n10 = n7;
            if (this.decay) {
                n8 *= 0.9998;
            }
            for (int k = 0; k < 2; ++k) {
                final int[] array3 = array;
                final int n11 = k;
                array3[n11] += this.Wx[k].F;
                final int[] array4 = array2;
                final int n12 = k;
                array4[n12] += this.Wy[k].F;
                if (array[k] >= 360) {
                    final int[] array5 = array;
                    final int n13 = k;
                    array5[n13] -= 360;
                }
                if (array2[k] >= 360) {
                    final int[] array6 = array2;
                    final int n14 = k;
                    array6[n14] -= 360;
                }
            }
            n6 = (int)(n4 * (this.Wx[0].A * this.sin[array[0]] + this.Wx[1].A * this.sin[array[1]]) * n8 + n2);
            n7 = (int)(n5 * (this.Wy[0].A * this.sin[array2[0]] + this.Wy[1].A * this.sin[array2[1]]) * n8 + n3);
            if (this.color) {
                graphics.setColor(Color.getHSBColor(n / 360.0f, 1.0f, 1.0f));
            }
            graphics.drawLine(n9, n10, n6, n7);
            if (this.thick) {
                graphics.drawLine(n9 + 1, n10, n6 + 1, n7);
                graphics.drawLine(n9, n10 + 1, n6, n7 + 1);
                graphics.drawLine(n9 + 1, n10 + 1, n6 + 1, n7 + 1);
            }
        }
    }
    
    public void setParams(final Wave[] wx, final Wave[] wy, final boolean decay, final boolean color, final boolean thick) {
        this.Wx = wx;
        this.Wy = wy;
        this.decay = decay;
        this.color = color;
        this.thick = thick;
    }
    
    public void update(final Graphics graphics) {
        if (this.OSC == null || this.widthOfOSC != this.getSize().width || this.heightOfOSC != this.getSize().height) {
            this.OSC = null;
            this.OSC = this.createImage(this.getSize().width, this.getSize().height);
            this.widthOfOSC = this.getSize().width;
            this.heightOfOSC = this.getSize().height;
        }
        final Graphics graphics2 = this.OSC.getGraphics();
        if (this.color) {
            this.setBackground(Color.black);
            this.setForeground(Color.white);
        }
        else {
            this.setBackground(Color.white);
            this.setForeground(Color.black);
        }
        graphics2.setColor(this.getBackground());
        graphics2.fillRect(0, 0, this.widthOfOSC, this.heightOfOSC);
        graphics2.setColor(this.getForeground());
        graphics2.setFont(this.getFont());
        this.paint(graphics2);
        graphics2.dispose();
        graphics.drawImage(this.OSC, 0, 0, this);
    }
}
