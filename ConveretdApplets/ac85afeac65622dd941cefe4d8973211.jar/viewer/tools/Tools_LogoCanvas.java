// 
// Decompiled by Procyon v0.5.30
// 

package viewer.tools;

import java.net.URL;
import java.awt.Event;
import java.awt.Dimension;
import java.awt.Color;
import java.awt.Component;
import java.awt.Frame;
import java.awt.Graphics;
import java.applet.Applet;
import java.awt.FontMetrics;
import java.awt.Font;
import java.awt.Canvas;

class Tools_LogoCanvas extends Canvas
{
    private String aplName;
    private String version;
    private Font font1;
    private Font font2;
    private FontMetrics fm1;
    private FontMetrics fm2;
    private int x1;
    private int x2;
    private int y1;
    private int y2;
    private int width;
    private int height;
    private Object frame;
    private Applet apl;
    
    public void paint(final Graphics graphics) {
        if (this.frame == null) {
            this.frame = this.getParent();
            while (!(this.frame instanceof Frame)) {
                this.frame = ((Component)this.frame).getParent();
            }
        }
        this.width = this.size().width - 1;
        this.height = this.size().height - 1;
        this.fm2 = this.getFontMetrics(this.font2);
        this.x2 = this.width - this.fm2.stringWidth(this.aplName) - 4;
        this.y2 = this.height - this.fm2.getHeight() / 2;
        this.fm1 = this.getFontMetrics(this.font1);
        this.x1 = this.width - this.fm1.stringWidth(this.version) - 4;
        this.y1 = this.y2 - this.fm1.getHeight() - this.fm1.getMaxDescent() / 2;
        graphics.setFont(this.font1);
        graphics.setColor(this.getBackground().darker());
        graphics.drawString(this.version, this.x1 + 1, this.y1 + 1);
        graphics.setColor(this.getBackground().brighter());
        graphics.drawString(this.version, this.x1, this.y1);
        graphics.setFont(this.font2);
        graphics.setColor(this.getBackground().darker());
        graphics.drawString(this.aplName, this.x2 + 2, this.y2 + 1);
        graphics.setColor(Color.orange);
        graphics.drawString(this.aplName, this.x2, this.y2);
    }
    
    Tools_LogoCanvas(final Applet apl) {
        this.aplName = "Image Viewer";
        this.version = "Version 1.4 deluxe";
        this.apl = apl;
        this.font1 = new Font("Verdana", 0, 11);
        this.font2 = new Font("Verdana", 1, 15);
        this.resize(50, 50);
    }
    
    public Dimension getPreferredSize() {
        return this.getMinimumSize();
    }
    
    public Dimension getMinimumSize() {
        return new Dimension(125, 50);
    }
    
    public boolean handleEvent(final Event event) {
        switch (event.id) {
            case 504: {
                if (this.frame != null) {
                    ((Frame)this.frame).setCursor(12);
                }
                this.apl.showStatus("www.java.dir.bg - The Home of Photo Album.");
                break;
            }
            case 505: {
                if (this.frame != null) {
                    ((Frame)this.frame).setCursor(0);
                }
                this.apl.showStatus("Done");
                break;
            }
            case 501: {
                try {
                    this.apl.getAppletContext().showDocument(new URL("http://java.dir.bg/"), "_blank");
                }
                catch (Exception ex) {
                    System.err.println("Logo canvas error " + ex);
                }
                break;
            }
        }
        return super.handleEvent(event);
    }
}
