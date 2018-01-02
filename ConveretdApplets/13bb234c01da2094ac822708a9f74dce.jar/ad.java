import java.awt.FontMetrics;
import java.awt.image.ImageObserver;
import java.awt.Color;
import java.awt.Toolkit;
import com.daysofwonder.applet.y;
import java.awt.Dimension;
import java.awt.Font;
import com.daysofwonder.util.UIProperties;
import com.daysofwonder.util.K;
import java.awt.Image;
import java.awt.Graphics;
import com.daysofwonder.applet.c;
import javax.swing.JPanel;

// 
// Decompiled by Procyon v0.5.30
// 

class ad extends JPanel implements com.daysofwonder.applet.ad, c
{
    private Graphics a;
    private Image b;
    private String c;
    private int d;
    private int e;
    private K f;
    private String g;
    private String h;
    private UIProperties i;
    private Font j;
    
    public ad(final Image b, final Image image, final Image image2, final UIProperties i) {
        this.b = b;
        this.i = i;
    }
    
    public Dimension preferredSize() {
        return new Dimension(798, 597);
    }
    
    public Dimension minimumSize() {
        return new Dimension(798, 597);
    }
    
    public synchronized void a(final String s, final int e, final int d) {
        this.c = this.i.b("loading") + " " + s + " (" + e + "/" + d + ")";
        this.e = e;
        this.d = d;
        this.repaint();
    }
    
    public synchronized void a(final int e, final int d) {
        this.c = this.i.b("preparing") + " (" + e + "/" + d + ")";
        this.e = e;
        this.d = d;
        this.repaint();
    }
    
    public synchronized void update(final Graphics graphics) {
        this.paint(graphics);
    }
    
    public synchronized void paint(final Graphics a) {
        this.a(this.getSize());
        this.a = a;
        if (this.a != null) {
            this.a();
            this.a(a);
        }
    }
    
    public void a(final Graphics graphics) {
        if (com.daysofwonder.applet.y.a) {
            Toolkit.getDefaultToolkit().sync();
        }
    }
    
    public synchronized void a(final Dimension dimension) {
    }
    
    public synchronized void a() {
        this.a.setColor(Color.white);
        this.a.fillRect(0, 0, this.getSize().width, this.getSize().height);
        if (this.b != null && this.getSize() != null) {
            final int width = this.b.getWidth(this);
            final int height = this.b.getHeight(this);
            final int n = (this.getSize().width - width) / 2;
            final int n2 = (this.getSize().height - height) / 2;
            this.a.drawImage(this.b, 0, 0, null);
            this.a.setColor(Color.black);
            if (this.f != null) {
                int n3 = (this.getSize().width - 300) / 2;
                int n4 = n2 + height + 40;
                final FontMetrics fontMetrics = this.a.getFontMetrics();
                final int height2 = fontMetrics.getHeight();
                final com.daysofwonder.util.y e = this.f.e();
                while (e.a()) {
                    final String s = (String)e.b();
                    if (this.f.c() == 1) {
                        n3 = (this.getSize().width - fontMetrics.stringWidth(s)) / 2;
                        this.a.drawString(s, n3, n4);
                    }
                    else {
                        this.a.drawString(s, n3 + 20, n4);
                    }
                    n4 += height2;
                }
            }
            if (this.h != null) {
                final Font font = this.a.getFont();
                this.a.setFont(this.j);
                final int n5 = (this.getSize().width - this.a.getFontMetrics().stringWidth(this.h)) / 2;
                final int n6 = n2 + height + 80;
                this.a.getFontMetrics().getHeight();
                this.a.drawString(this.h, n5, n6);
                this.a.setFont(font);
            }
            if (this.g != null) {
                final Font font2 = this.a.getFont();
                this.a.setFont(new Font("Courrier", 1, 14));
                final int n7 = (this.getSize().width - this.a.getFontMetrics().stringWidth(this.g)) / 2;
                final int n8 = 50;
                this.a.getFontMetrics().getHeight();
                this.a.drawString(this.g, n7, n8);
                this.a.setFont(font2);
            }
            if (this.c != null) {
                this.a.drawRect(300, 504, 200, 10);
                this.a.fillRect(300, 504, (int)(this.e / this.d * 200.0f), 10);
            }
            if (com.daysofwonder.applet.y.a) {
                Toolkit.getDefaultToolkit().sync();
            }
        }
    }
}
