import java.net.MalformedURLException;
import java.awt.image.ImageObserver;
import java.awt.Graphics;
import java.net.URL;
import java.awt.Image;

// 
// Decompiled by Procyon v0.5.30
// 

class Scroller$Section
{
    private final Scroller \u00d0;
    private String \u00d1;
    private Image \u00c1;
    private Image \u00d2;
    private int \u00c5;
    private int \u00c3;
    private boolean \u00d3;
    private URL \u00d4;
    private String \u00d5;
    
    Scroller$Section(final Scroller scroller, final String \u00f1, final int \u00e3) {
        this.\u00d0 = scroller;
        this.\u00d0 = scroller;
        this.\u00d3 = false;
        this.\u00c3 = \u00e3;
        this.\u00d1 = \u00f1;
        scroller.getGraphics();
        this.\u00c5 = Scroller.\u00c5(scroller, \u00f1);
        final int n = 20;
        this.\u00c1 = scroller.createImage(this.\u00c5, n);
        final Graphics graphics = this.\u00c1.getGraphics();
        graphics.setColor(Scroller.\u00c6(scroller));
        graphics.fillRect(0, 0, this.\u00c5, 20);
        graphics.setColor(Scroller.\u00c7(scroller));
        graphics.drawString(this.\u00d1, 0, n / 2 + graphics.getFontMetrics().getHeight() / 2 - 2);
    }
    
    Scroller$Section(final Scroller scroller, final Image \u00e1, final int \u00e3) {
        this.\u00d0 = scroller;
        this.\u00d0 = scroller;
        this.\u00d3 = false;
        this.\u00c3 = \u00e3;
        this.\u00c1 = \u00e1;
        this.\u00c5 = this.\u00c1.getWidth(null);
    }
    
    public void setAsLink(final String s, final String \u00f5) {
        this.\u00d2 = this.\u00d0.createImage(this.\u00c5, 20);
        final Graphics graphics = this.\u00d2.getGraphics();
        graphics.setColor(Scroller.\u00c6(this.\u00d0));
        graphics.fillRect(0, 0, this.\u00c5, 20);
        graphics.setColor(Scroller.\u00c8(this.\u00d0));
        graphics.drawString(this.\u00d1, 0, 10 + graphics.getFontMetrics().getHeight() / 2 - 2);
        graphics.drawLine(0, 10 + graphics.getFontMetrics().getHeight() / 2, this.\u00c5, 10 + graphics.getFontMetrics().getHeight() / 2);
        final Graphics graphics2 = this.\u00c1.getGraphics();
        graphics2.setColor(Scroller.\u00c7(this.\u00d0));
        graphics2.drawLine(0, 10 + graphics2.getFontMetrics().getHeight() / 2, this.\u00c5, 10 + graphics2.getFontMetrics().getHeight() / 2);
        try {
            this.\u00d4 = new URL(this.\u00d0.getDocumentBase(), s);
        }
        catch (MalformedURLException ex) {
            System.out.println("bad link url [" + s + "]");
        }
        this.\u00d5 = \u00f5;
        this.\u00d3 = true;
    }
    
    private boolean \u00d0() {
        return this.\u00d4 != null;
    }
    
    private void \u00d1() {
        if (this.\u00d4 != null) {
            if (this.\u00d5 != null) {
                this.\u00d0.getAppletContext().showDocument(this.\u00d4, this.\u00d5);
                return;
            }
            this.\u00d0.getAppletContext().showDocument(this.\u00d4);
        }
    }
    
    public boolean display(final int n, final int n2) {
        return this.\u00c3 > n - this.\u00c5 && this.\u00c3 < n2;
    }
    
    public Image getImage(final boolean b) {
        if (b && this.\u00d3) {
            return this.\u00d2;
        }
        return this.\u00c1;
    }
    
    static int \u00c2(final Scroller$Section scroller$Section) {
        return scroller$Section.\u00c3;
    }
    
    static int \u00c3(final Scroller$Section scroller$Section) {
        return scroller$Section.\u00c5;
    }
    
    static boolean \u00c4(final Scroller$Section scroller$Section) {
        return scroller$Section.\u00d0();
    }
    
    static void \u00c5(final Scroller$Section scroller$Section) {
        scroller$Section.\u00d1();
    }
}
