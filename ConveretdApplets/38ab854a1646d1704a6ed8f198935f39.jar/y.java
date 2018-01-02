import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Font;
import java.awt.Color;
import javax.swing.JPanel;

// 
// Decompiled by Procyon v0.5.30
// 

public class y extends JPanel implements k
{
    private o byte;
    private double int;
    private double try;
    private Color new;
    private Font font;
    private static final int for = 10;
    
    public y(final o byte1, final Color new1) {
        this.int = 1.0;
        this.try = 0.0;
        byte1.a(this);
        this.byte = byte1;
        this.new = new1;
        this.font = new Font("Times", 0, 10);
    }
    
    public String getName() {
        return this.byte.do();
    }
    
    public String toString() {
        return this.getName();
    }
    
    public void a(final double n) {
        if (n > this.int) {
            this.int = this.if(n);
        }
        if (n < this.try) {
            this.try = this.do(this.try);
        }
        this.repaint();
    }
    
    private double do(final double n) {
        if (n == 0.0) {
            return 0.0;
        }
        final double pow = Math.pow(10.0, (int)(Math.log(Math.abs(n)) / Math.log(10.0)) - 1);
        return ((int)(n / pow) - ((n < 0.0) ? 1 : 0)) * pow;
    }
    
    private double if(final double n) {
        if (n == 0.0) {
            return 0.0;
        }
        final double pow = Math.pow(10.0, (int)(Math.log(Math.abs(n)) / Math.log(10.0)) - 1);
        return (int)(n / pow + 1.0 - ((n < 0.0) ? 1 : 0)) * pow;
    }
    
    private void a(final Graphics graphics) {
        graphics.setFont(this.font);
        graphics.setColor(Color.white);
        graphics.fillRect(0, 0, this.getWidth(), this.getHeight());
        graphics.setColor(Color.black);
        graphics.drawArc(0, 0, this.getWidth(), (this.getHeight() - 10) * 2, -1, 180);
        for (int i = 0; i < 4; ++i) {
            this.if(i * 3.141592653589793 / 4.0, graphics);
        }
        graphics.setColor(Color.gray);
        for (int j = 0; j < 4; ++j) {
            this.a(j * 3.141592653589793 / 4.0, graphics);
        }
    }
    
    private void if(final double n, final Graphics graphics) {
        final int n2 = (int)(this.getWidth() * Math.cos(n) / 2.0);
        final int n3 = -(int)((this.getHeight() - 10) * Math.sin(n));
        final int n4 = this.getWidth() / 2;
        final int n5 = this.getHeight() - 10;
        graphics.drawLine(n4 + (int)(n2 * 0.9), n5 + (int)(n3 * 0.9), n4 + n2, n5 + n3);
    }
    
    private void a(final double n, final Graphics graphics) {
        final int n2 = this.getWidth() / 2 + (int)(0.85 * this.getWidth() * Math.cos(n) / 2.0);
        final int n3 = this.getHeight() - 10 - (int)(0.85 * (this.getHeight() - 10) * Math.sin(n));
        final String string = "" + ((this.int - this.try) * (1.0 - n / 3.141592653589793) + this.try);
        graphics.drawString(string, n2 - graphics.getFontMetrics().stringWidth(string) / 2, n3);
    }
    
    public void paintComponent(final Graphics graphics) {
        super.paintComponent(graphics);
        this.a(graphics);
        graphics.setColor(this.new);
        try {
            if (this.int != this.try) {
                final double n = (1.0 - (this.byte.if() - this.try) / (this.int - this.try)) * 3.141592653589793;
                graphics.drawLine(this.getWidth() / 2, this.getHeight() - 10, this.getWidth() / 2 + (int)(this.getWidth() * Math.cos(n) / 2.0), this.getHeight() - (int)((this.getHeight() - 10) * Math.sin(n)) - 10);
            }
            graphics.setColor(Color.black);
            String s = "" + (float)this.byte.if();
            if (Math.abs(this.byte.if()) < 100.0 && s.length() > 4) {
                s = s.substring(0, 5);
            }
            graphics.drawString(String.valueOf(s) + this.byte.for(), this.getWidth() / 2 - 20, this.getHeight() - 5);
        }
        catch (s s2) {}
    }
    
    public Dimension getPreferredSize() {
        return new Dimension(100, 80);
    }
}
