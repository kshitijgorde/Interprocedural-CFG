import java.awt.Graphics;
import java.net.MalformedURLException;
import java.awt.Color;
import java.net.URL;

// 
// Decompiled by Procyon v0.5.30
// 

public class RectangularHotspot implements fvr2Hotspot
{
    static final boolean debug = false;
    int frame_i;
    int frame_j;
    int x;
    int y;
    int width;
    int height;
    URL mouseUpURL;
    String mouseUpTarget;
    String mouseOverText;
    fvr2 myApplet;
    boolean[] isVisible;
    Color[] boxColor;
    int state;
    static final int MOUSE_OUT = 0;
    static final int MOUSE_OVER = 1;
    static final int MOUSE_DOWN = 2;
    
    public RectangularHotspot() {
        this.frame_i = -1;
        this.frame_j = -1;
        this.x = 0;
        this.y = 0;
        this.width = 0;
        this.height = 0;
        this.state = 0;
        this.mouseUpTarget = null;
        this.mouseOverText = null;
        this.isVisible = new boolean[3];
        this.setVisibleMouseOut(true);
        this.setVisibleMouseOver(true);
        this.setVisibleMouseDown(true);
        (this.boxColor = new Color[3])[0] = Color.black;
        this.boxColor[1] = Color.white;
        this.boxColor[2] = Color.red;
    }
    
    public void attachApplet(final fvr2 myApplet) {
        this.myApplet = myApplet;
    }
    
    public void setFrame(final int frame_i, final int frame_j) {
        this.frame_i = frame_i;
        this.frame_j = frame_j;
    }
    
    public void setCorner(final int x, final int y) {
        this.x = x;
        this.y = y;
    }
    
    public void setSize(final int width, final int height) {
        this.width = width;
        this.height = height;
    }
    
    public void setMouseUpURL(final String s) throws MalformedURLException {
        this.mouseUpURL = new URL(this.myApplet.getDocumentBase(), s);
    }
    
    public void setMouseUpTarget(final String mouseUpTarget) {
        this.mouseUpTarget = mouseUpTarget;
    }
    
    public void setMouseOverText(final String mouseOverText) {
        this.mouseOverText = mouseOverText;
    }
    
    public void setVisibleMouseOut(final boolean b) {
        this.isVisible[0] = b;
    }
    
    public void setVisibleMouseOver(final boolean b) {
        this.isVisible[1] = b;
    }
    
    public void setVisibleMouseDown(final boolean b) {
        this.isVisible[2] = b;
    }
    
    public void beInvisible() {
        for (int i = 0; i < this.isVisible.length; ++i) {
            this.isVisible[i] = false;
        }
    }
    
    public void setMouseOutColor(final String s) throws NumberFormatException {
        this.boxColor[0] = parseColorString(s);
    }
    
    public void setMouseOverColor(final String s) throws NumberFormatException {
        this.boxColor[1] = parseColorString(s);
    }
    
    public void setMouseDownColor(final String s) throws NumberFormatException {
        this.boxColor[2] = parseColorString(s);
    }
    
    public void mouseOver() {
        this.setState(1);
        this.myApplet.setCursor(0);
        if (this.mouseOverText != null) {
            this.myApplet.getAppletContext().showStatus(this.mouseOverText);
        }
    }
    
    public void mouseOut() {
        this.setState(0);
        this.myApplet.getAppletContext().showStatus(null);
    }
    
    public void mouseDown() {
        this.setState(2);
    }
    
    public void mouseUp() {
        this.setState(1);
        System.out.println("Mouse up event over hotspot!");
        if (this.mouseUpTarget == null) {
            this.myApplet.getAppletContext().showDocument(this.mouseUpURL);
            return;
        }
        this.myApplet.getAppletContext().showDocument(this.mouseUpURL, this.mouseUpTarget);
    }
    
    public boolean isMouseOver(final int n, final int n2, final int n3, final int n4) {
        return this.isInFrame(n, n2) && n3 > this.x && n3 < this.x + this.width && n4 > this.y && n4 < this.y + this.height;
    }
    
    public boolean isInFrame(final int n, final int n2) {
        return this.frame_i == n && this.frame_j == n2;
    }
    
    public void paint(final Graphics graphics) {
        if (this.isVisible[this.state]) {
            graphics.setColor(this.boxColor[this.state]);
            graphics.drawRect(this.x, this.y, this.width, this.height);
        }
    }
    
    void setState(final int n) {
        if (this.isVisible[this.state] && !this.isVisible[n]) {
            this.state = n;
            this.myApplet.repaint();
            return;
        }
        final Graphics graphics = this.myApplet.getGraphics();
        this.state = n;
        this.paint(graphics);
    }
    
    static Color parseColorString(final String s) throws NumberFormatException {
        return new Color(Integer.parseInt(s, 16));
    }
}
