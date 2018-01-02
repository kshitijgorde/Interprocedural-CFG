import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Font;
import java.awt.Color;
import javax.swing.JPanel;

// 
// Decompiled by Procyon v0.5.30
// 

public class m extends JPanel implements w
{
    private u g;
    private double c;
    private double f;
    private int h;
    private boolean d;
    private Color e;
    private Font font;
    
    public m(final int h, final o o, final Color e) {
        this.c = 1.0;
        this.f = 0.0;
        this.d = true;
        (this.g = new e(o, h + 1)).a(this);
        this.h = h;
        this.e = e;
        this.font = new Font("Times", 0, 10);
    }
    
    public String getName() {
        return this.g.a();
    }
    
    public String toString() {
        return this.getName();
    }
    
    public void new() {
        try {
            this.c = this.g.a(1);
        }
        catch (s s) {
            this.c = 1.0;
        }
        this.f = this.c;
        for (int i = 1; i < this.g.do(); ++i) {
            try {
                if (this.g.a(i) > this.c) {
                    this.c = this.g.a(i);
                }
                if (this.g.a(i) < this.f) {
                    this.f = this.g.a(i);
                }
            }
            catch (s s2) {}
        }
        this.c = this.if(this.c);
        this.f = this.do(this.f);
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
        final double n = this.getWidth() / this.h;
        graphics.setFont(this.font);
        graphics.setColor(Color.white);
        graphics.fillRect(0, 0, this.getWidth(), this.getHeight());
        graphics.setColor(Color.black);
        graphics.drawLine(0, 0, this.getWidth() - 1, 0);
        graphics.drawLine(0, 0, 0, this.getHeight() - 1);
        graphics.drawLine(0, this.getHeight() - 1, this.getWidth() - 1, this.getHeight() - 1);
        graphics.drawLine(this.getWidth() - 1, 0, this.getWidth() - 1, this.getHeight() - 1);
        graphics.drawLine(0, this.getHeight() / 2, this.getWidth() - 1, this.getHeight() / 2);
        graphics.drawLine(0, this.getHeight() / 4, this.getWidth() - 1, this.getHeight() / 4);
        graphics.drawLine(0, 3 * this.getHeight() / 4, this.getWidth() - 1, 3 * this.getHeight() / 4);
        graphics.drawString("" + (float)this.c, 5, 10);
        graphics.drawString("" + (float)(3.0 * this.c + this.f) / 4.0f, 5, this.getHeight() / 4);
        graphics.drawString("" + (float)(this.c + this.f) / 2.0f, 5, this.getHeight() / 2);
        graphics.drawString("" + (float)(this.c + 3.0 * this.f) / 4.0f, 5, 3 * this.getHeight() / 4);
        graphics.drawString("" + (float)this.f, 5, this.getHeight() - 10);
        graphics.setColor(Color.gray);
        if (this.c > 0.0 && this.f < 0.0) {
            graphics.drawLine(0, this.getHeight() - (int)((0.0 - this.f) * this.getHeight() / (this.c - this.f)), this.getWidth(), this.getHeight() - (int)((0.0 - this.f) * this.getHeight() / (this.c - this.f)));
        }
    }
    
    public void paintComponent(final Graphics graphics) {
        super.paintComponent(graphics);
        final double n = (this.getWidth() - 2) / this.h;
        if (this.d) {
            this.a(graphics);
            graphics.setColor(this.e);
            if (this.c != this.f) {
                for (int i = 1; i < this.h; ++i) {
                    try {
                        graphics.drawLine((int)(n * i), (int)((this.getHeight() - 1) * (1.0 - (this.g.a(this.h - i) - this.f) / (this.c - this.f))), (int)(n * (i + 1)), (int)((this.getHeight() - 1) * (1.0 - (this.g.a(this.h - i - 1) - this.f) / (this.c - this.f))));
                    }
                    catch (s s2) {}
                }
            }
            graphics.setColor(Color.black);
        }
        String string;
        try {
            String s = "" + (float)this.g.a(0);
            if (Math.abs(this.g.a(0)) < 100.0 && s.length() > 4) {
                s = s.substring(0, 5);
            }
            string = String.valueOf(s) + this.g.if();
        }
        catch (s s3) {
            string = "No Data";
        }
        graphics.drawString(string, this.getWidth() / 2 - 20, this.getHeight() - 5);
    }
    
    public Dimension getPreferredSize() {
        return new Dimension(150, 100);
    }
    
    public void a(final boolean d) {
        this.d = d;
    }
}
