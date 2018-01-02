import java.awt.Dimension;
import java.awt.Event;
import java.awt.image.ImageObserver;
import java.awt.Graphics;
import java.awt.Image;
import java.applet.Applet;
import java.awt.Canvas;

// 
// Decompiled by Procyon v0.5.30
// 

public class Button2 extends Canvas
{
    private String m_Caption;
    private Applet m_Applet;
    private String m_ImageName1;
    private String m_ImageName2;
    private int m_ImageWidth;
    private int m_ImageHeight;
    private Image m_Image1;
    private Image m_Image2;
    private boolean m_StatePressed;
    
    private void doPaint(final boolean b, final Graphics graphics) {
        if (graphics == null) {
            return;
        }
        final int width = this.size().width;
        final int height = this.size().height;
        graphics.setColor(this.getBackground());
        graphics.fillRect(0, 0, width, height);
        if (!b && this.m_Image1 != null) {
            graphics.drawImage(this.m_Image1, (width - this.m_Image1.getWidth(this)) / 2, (height - this.m_Image1.getHeight(this)) / 2, this);
        }
        if (b && this.m_Image2 != null) {
            graphics.drawImage(this.m_Image2, (width - this.m_Image2.getWidth(this)) / 2, (height - this.m_Image2.getHeight(this)) / 2, this);
        }
    }
    
    public Button2(final String caption, final Applet applet, final String imageName1, final String imageName2, final int imageWidth, final int imageHeight) {
        this.m_Image1 = null;
        this.m_Image2 = null;
        this.m_StatePressed = false;
        this.m_Caption = caption;
        this.m_Applet = applet;
        this.m_ImageName1 = imageName1;
        this.m_ImageName2 = imageName2;
        this.m_ImageWidth = imageWidth;
        this.m_ImageHeight = imageHeight;
    }
    
    public void setPressedState(final boolean statePressed) {
        this.m_StatePressed = statePressed;
        this.paint(this.getGraphics());
    }
    
    public boolean getPressedState() {
        return this.m_StatePressed;
    }
    
    public boolean mouseUp(final Event event, final int n, final int n2) {
        this.m_StatePressed = false;
        this.paint(this.getGraphics());
        this.postEvent(new Event(this, 1001, this.m_Caption));
        return true;
    }
    
    public void paint(final Graphics graphics) {
        if (this.m_Image1 == null) {
            try {
                this.m_Image1 = this.m_Applet.getImage(this.m_Applet.getCodeBase(), this.m_ImageName1);
            }
            catch (Exception ex) {}
        }
        if (this.m_Image2 == null) {
            try {
                this.m_Image2 = this.m_Applet.getImage(this.m_Applet.getCodeBase(), this.m_ImageName2);
            }
            catch (Exception ex2) {}
        }
        this.doPaint(this.m_StatePressed, graphics);
    }
    
    public Dimension minimumSize() {
        return new Dimension(this.m_ImageWidth, this.m_ImageHeight);
    }
    
    public void update(final Graphics graphics) {
        this.paint(graphics);
    }
    
    public boolean mouseDown(final Event event, final int n, final int n2) {
        this.m_StatePressed = true;
        this.paint(this.getGraphics());
        return true;
    }
    
    public Dimension preferredSize() {
        final int width = this.size().width;
        final int height = this.size().height;
        return new Dimension((width < this.m_ImageWidth) ? this.m_ImageWidth : width, (height < this.m_ImageHeight) ? this.m_ImageHeight : height);
    }
}
