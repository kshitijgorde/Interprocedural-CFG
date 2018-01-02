import java.awt.event.ActionEvent;
import java.awt.image.ImageObserver;
import java.awt.Graphics;
import java.awt.event.ActionListener;
import java.awt.Button;

// 
// Decompiled by Procyon v0.5.30
// 

public class a extends Button implements ActionListener
{
    String a;
    br b;
    be c;
    u d;
    
    public a(final String a, final br b, final be c) {
        this.a = a;
        this.b = b;
        this.c = c;
        this.addActionListener(this);
    }
    
    public void paint(final Graphics graphics) {
        final int width = this.getSize().width;
        final int height = this.getSize().height;
        if (this.c != null && irc.x != null) {
            final int width2 = irc.x.getWidth(this);
            final int height2 = irc.x.getHeight(this);
            int n = 0;
            int n2 = 0;
            if (width2 < width) {
                n = (width - width2) / 2;
            }
            if (height2 < height) {
                n2 = (height - height2) / 2;
            }
            graphics.drawImage(irc.x, n, n2, width2, height2, this);
            return;
        }
        if (this.b != null && irc.y != null) {
            final int width3 = irc.y.getWidth(this);
            final int height3 = irc.y.getHeight(this);
            int n3 = 0;
            int n4 = 0;
            if (width3 < width) {
                n3 = (width - width3) / 2;
            }
            if (height3 < height) {
                n4 = (height - height3) / 2;
            }
            graphics.drawImage(irc.y, n3, n4, width3, height3, this);
            return;
        }
        int n5 = 0;
        int n6 = 0;
        int n7 = width;
        int n8 = height;
        if (width > 12) {
            n5 = 6;
            n7 = width - 6;
        }
        if (height > 12) {
            n6 = 6;
            n8 = height - 6;
        }
        graphics.drawLine(n5, n6, n7, n8);
        graphics.drawLine(n5 + 1, n6, n7 + 1, n8);
        graphics.drawLine(n5, n8, n7, n6);
        graphics.drawLine(n5 + 1, n8, n7 + 1, n6);
    }
    
    public void actionPerformed(final ActionEvent actionEvent) {
        if (this.b != null) {
            this.b.a(this.a);
        }
        if (this.c != null) {
            this.c.b(this.a, false);
        }
        if (this.d != null) {
            this.d.a();
        }
    }
    
    public void a(final u d) {
        this.d = d;
    }
    
    public void a(final String a) {
        this.a = a;
    }
}
