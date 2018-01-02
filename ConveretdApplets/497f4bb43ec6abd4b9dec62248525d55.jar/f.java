import java.awt.event.FocusEvent;
import java.applet.Applet;
import java.awt.Component;
import java.awt.FontMetrics;
import java.awt.Font;
import java.awt.image.ImageObserver;
import java.awt.MediaTracker;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.FocusListener;
import java.awt.Canvas;

// 
// Decompiled by Procyon v0.5.30
// 

class f extends Canvas implements FocusListener
{
    private Image X;
    private int[] Y;
    private String[] Z;
    private String[] aa;
    private Image[] ab;
    private int[] ac;
    private int ad;
    private int ae;
    private boolean af;
    private int ag;
    private boolean ah;
    
    f() {
        this.aa = new String[] { "replay.gif", "pause.gif", "reload.gif", "first.gif", "prev.gif", "next.gif", "last.gif" };
        this.ab = new Image[this.aa.length];
    }
    
    private void a(final Graphics graphics, final int n, final int n2) {
        graphics.setColor((n2 == 0) ? new Color(12631461) : new Color(14011579));
        graphics.fillRect(this.Y[n] - n2 + 1, 3 - n2, this.Y[n + 1] - this.Y[n] + 2 * n2 - 3, 17 + 2 * n2);
        graphics.setColor(Color.black);
        graphics.drawString(this.Z[n], this.Y[n] + 9, 16 - n2);
        graphics.setColor(new Color(15394525));
        graphics.drawLine(this.Y[n] - n2, 5 - n2, this.Y[n] - n2, 19 + n2 / 2);
        graphics.drawLine(this.Y[n] - n2, 4 - n2, this.Y[n] - n2 + 2, 2 - n2);
        graphics.drawLine(this.Y[n] - n2 + 3, 2 - n2, this.Y[n + 1] + n2 - 3, 2 - n2);
        graphics.setColor(Color.black);
        graphics.drawLine(this.Y[n + 1] + n2 - 2, 3 - n2, this.Y[n + 1] + n2 - 1, 4 - n2);
        graphics.drawLine(this.Y[n + 1] + n2 - 1, 5 - n2, this.Y[n + 1] + n2 - 1, 19 + n2 / 2);
        graphics.setColor(new Color(10653032));
        graphics.drawLine(this.Y[n + 1] + n2 - 2, 4 - n2, this.Y[n + 1] + n2 - 2, 19 + n2 / 2);
    }
    
    private void a(final Graphics graphics, final int n, final Color color, final Color color2) {
        graphics.setColor(color);
        graphics.drawLine(n, 2, n, 16);
        graphics.drawLine(n, 1, n + 18, 1);
        graphics.setColor(color2);
        graphics.drawLine(n + 19, 1, n + 19, 17);
        graphics.drawLine(n, 17, n + 18, 17);
    }
    
    public void a(final ChessBoard chessBoard, final MediaTracker mediaTracker, final String s) {
        for (int i = 0; i < this.aa.length; ++i) {
            mediaTracker.addImage(this.ab[i] = chessBoard.getImage(chessBoard.getCodeBase(), s + this.aa[i]), 0);
        }
        this.addFocusListener(this);
    }
    
    public void a(final Color background) {
        final int width = this.getSize().width;
        final int height = this.getSize().height;
        this.setBackground(background);
        this.X = this.createImage(width, height);
        final Graphics graphics = this.X.getGraphics();
        graphics.setColor(new Color(14011579));
        graphics.drawLine(1, height - 1, width - 2, height - 1);
        graphics.drawLine(1, height - 2, width - 2, height - 2);
        graphics.setColor(new Color(15394525));
        graphics.drawLine(0, height - 1, 0, height - 3);
        graphics.drawLine(1, height - 3, width - 2, height - 3);
        graphics.drawLine(width - 1, height - 1, width - 1, height - 3);
        this.a(graphics, 0, 0);
        this.a(graphics, 1, 0);
        for (int i = 2; i < this.aa.length; ++i) {
            graphics.drawImage(this.ab[i], this.ac[i - 1], 2, this);
        }
        graphics.dispose();
    }
    
    public void a(final String s, final String s2, final Color color) {
        this.setFont(new Font("SanSerif", 0, 12));
        final FontMetrics fontMetrics = this.getFontMetrics(this.getFont());
        this.Y = new int[3];
        this.Z = new String[2];
        this.ac = new int[this.aa.length - 1];
        this.Z[0] = s;
        this.Z[1] = s2;
        this.Y[0] = 2;
        this.Y[1] = fontMetrics.stringWidth(s) + this.Y[0] + 19;
        this.Y[2] = fontMetrics.stringWidth(s2) + this.Y[1] + 19;
        this.ac[0] = 268;
        for (int i = 1; i < this.ac.length; ++i) {
            this.ac[i] = this.ac[i - 1] + 24;
        }
        this.a(color);
        this.ad = 0;
        this.ae = -1;
        this.af = false;
        this.ag = 2100;
    }
    
    public void a(final int ad, final Component component, final Component component2) {
        this.ad = ad;
        component.setVisible(ad == 0);
        component2.setVisible(ad != 0);
        this.repaint();
    }
    
    public int a(final int n, final int n2) {
        final int n3 = this.ad ^ 0x1;
        if (n2 < 3 || n2 > this.getSize().height - 3) {
            return -1;
        }
        if (this.Y[n3] < n && n < this.Y[n3 + 1] - 1) {
            return n3;
        }
        return -1;
    }
    
    public boolean N() {
        return this.af;
    }
    
    public void b(final boolean af) {
        this.af = af;
    }
    
    public int O() {
        return this.ag;
    }
    
    public void a(final Applet applet, final boolean b) {
        applet.getAppletContext().showStatus(b ? ("CVD: Replaying the game [" + (this.ag - 100.0) / 1000.0 + "s delay]") : "");
    }
    
    public void c(final boolean b) {
        if (b) {
            if (this.ag <= 100) {
                return;
            }
            this.ag -= 500;
        }
        else {
            if (this.ag > 10000) {
                return;
            }
            this.ag += 1000;
        }
    }
    
    public void d(final boolean ah) {
        this.ah = ah;
    }
    
    public boolean P() {
        return this.ah;
    }
    
    public int a(final Component component, final int n, final int n2) {
        if (this.ae == -1) {
            return -1;
        }
        if (n2 > 1 && n2 < this.getSize().height - 5 && this.ac[this.ae] - 2 < n && n < this.ac[this.ae] + 18) {
            return this.ae;
        }
        final Graphics graphics = component.getGraphics();
        final Color background = this.getBackground();
        this.a(graphics, this.ac[this.ae] - 2, background, background);
        this.ae = -1;
        graphics.dispose();
        return -1;
    }
    
    public boolean b(final Component component, final int n, final int n2) {
        final Graphics graphics = component.getGraphics();
        if (this.ae != -1) {
            final Color background = this.getBackground();
            this.a(graphics, this.ac[this.ae] - 2, background, background);
            this.ae = -1;
        }
        if (n2 > 1 && n2 < this.getSize().height - 5) {
            for (int i = 0; i < this.ac.length; ++i) {
                if (this.ac[i] - 2 < n && n < this.ac[i] + 18) {
                    this.a(graphics, this.ac[i] - 2, new Color(15394525), new Color(10653032));
                    this.ae = i;
                    graphics.dispose();
                    return true;
                }
            }
        }
        graphics.dispose();
        return false;
    }
    
    public void paint(final Graphics graphics) {
        graphics.drawImage(this.X, 0, 0, this);
        this.a(graphics, this.ad, 2);
        graphics.drawImage(this.ab[this.af], this.ac[0], 2, this);
        if (this.ae != -1) {
            this.a(graphics, this.ac[this.ae] - 2, new Color(15394525), new Color(10653032));
        }
    }
    
    public void update(final Graphics graphics) {
        this.paint(graphics);
    }
    
    public void focusGained(final FocusEvent focusEvent) {
        this.getParent().requestFocus();
    }
    
    public void focusLost(final FocusEvent focusEvent) {
    }
}
