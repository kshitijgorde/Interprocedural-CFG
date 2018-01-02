import java.awt.Component;
import java.awt.Frame;
import java.net.MalformedURLException;
import java.awt.Event;
import java.awt.image.ImageObserver;
import java.awt.Font;
import java.awt.Color;
import java.awt.Rectangle;
import java.net.URL;
import java.awt.Graphics;
import java.awt.Image;
import java.applet.Applet;

// 
// Decompiled by Procyon v0.5.30
// 

public class RGBMixer extends Applet
{
    Image Buffer;
    Graphics gBuffer;
    URL url;
    int h;
    Slider sl1;
    Slider sl2;
    Slider sl3;
    Rectangle r;
    boolean overLink;
    
    public void drawStuff() {
        this.gBuffer.setColor(Color.lightGray);
        this.gBuffer.fillRect(0, 0, this.size().width, this.size().height);
        this.gBuffer.draw3DRect(0, 0, this.size().width - 1, this.size().height - 1, true);
        this.gBuffer.draw3DRect(4, 4, this.size().width - 9, this.size().height - 9, false);
        this.gBuffer.draw3DRect(5, 5, this.size().width - 11, this.size().height - 11, true);
        this.gBuffer.draw3DRect(18, 38, 133, 98, false);
        this.sl1.paint(this.gBuffer);
        this.sl2.paint(this.gBuffer);
        this.sl3.paint(this.gBuffer);
        final Color mixColor = new Color(this.sl1.getVal(), this.sl2.getVal(), this.sl3.getVal());
        this.gBuffer.setColor(mixColor);
        this.gBuffer.fillRect(20, 40, 130, 95);
        final String red = (this.sl1.getVal() < 20) ? ("0" + Integer.toHexString(this.sl1.getVal())) : Integer.toHexString(this.sl1.getVal());
        final String green = (this.sl2.getVal() < 20) ? ("0" + Integer.toHexString(this.sl2.getVal())) : Integer.toHexString(this.sl2.getVal());
        final String blue = (this.sl3.getVal() < 20) ? ("0" + Integer.toHexString(this.sl3.getVal())) : Integer.toHexString(this.sl3.getVal());
        final String hex = "color=\"#" + red + green + blue + "\"";
        final Font font = new Font("Helvetica", 0, 12);
        this.gBuffer.setFont(font);
        this.gBuffer.setColor(Color.black);
        this.gBuffer.drawString(hex, 45, 25);
        final Font smallFont = new Font("Helvetica", 0, 9);
        this.gBuffer.setFont(smallFont);
        this.gBuffer.setColor(Color.gray);
        this.gBuffer.drawString("©2001 by Johannes Wallroth", 20, 157);
        this.gBuffer.setColor(Color.blue);
        this.gBuffer.drawString("www.programming.de", 165, 157);
        this.gBuffer.drawLine(165, 158, 251, 158);
    }
    
    public void paint(final Graphics g) {
        this.drawStuff();
        g.drawImage(this.Buffer, 0, 0, this);
    }
    
    public boolean mouseUp(final Event evt, final int x, final int y) {
        this.sl1.reportMouseUp(x, y);
        this.sl2.reportMouseUp(x, y);
        this.sl3.reportMouseUp(x, y);
        this.repaint();
        return true;
    }
    
    public void update(final Graphics g) {
        this.paint(g);
    }
    
    public boolean mouseDown(final Event evt, final int x, final int y) {
        this.sl1.reportMouseDown(x, y);
        this.sl2.reportMouseDown(x, y);
        this.sl3.reportMouseDown(x, y);
        if (this.overLink) {
            this.getAppletContext().showDocument(this.url, "_blank");
        }
        this.repaint();
        return true;
    }
    
    public RGBMixer() {
        this.h = 35;
        this.sl1 = new Slider(180, this.h, Color.red);
        this.sl2 = new Slider(210, this.h, Color.green);
        this.sl3 = new Slider(240, this.h, Color.blue);
        this.r = new Rectangle(165, 150, 87, 10);
    }
    
    public void init() {
        this.Buffer = this.createImage(this.size().width, this.size().height);
        this.gBuffer = this.Buffer.getGraphics();
        try {
            this.url = new URL("http://www.programming.de/");
        }
        catch (MalformedURLException ex) {}
    }
    
    public boolean mouseDrag(final Event evt, final int x, final int y) {
        this.sl1.reportMouseDrag(x, y);
        this.sl2.reportMouseDrag(x, y);
        this.sl3.reportMouseDrag(x, y);
        this.repaint();
        return true;
    }
    
    public boolean mouseMove(final Event evt, final int x, final int y) {
        this.sl1.reportMouseMove(x, y);
        this.sl2.reportMouseMove(x, y);
        this.sl3.reportMouseMove(x, y);
        if (this.r.inside(x, y)) {
            this.overLink = true;
        }
        else {
            this.overLink = false;
        }
        Component ParentComponent;
        for (ParentComponent = this.getParent(); ParentComponent != null && !(ParentComponent instanceof Frame); ParentComponent = ParentComponent.getParent()) {}
        final Frame BrowserFrame = (Frame)ParentComponent;
        if (this.overLink) {
            BrowserFrame.setCursor(12);
        }
        else {
            BrowserFrame.setCursor(0);
        }
        this.repaint();
        return true;
    }
}
