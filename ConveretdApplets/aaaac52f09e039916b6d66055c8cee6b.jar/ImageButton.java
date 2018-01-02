import java.awt.event.MouseEvent;
import java.awt.image.ImageObserver;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.MouseListener;
import java.awt.Label;

// 
// Decompiled by Procyon v0.5.30
// 

public class ImageButton extends Label implements MouseListener
{
    public static final int NORMAL = 0;
    public static final int PRESSED = 1;
    public static final int ROLLOVER = 2;
    public static final int TOTAL_STATES = 3;
    public Image[] image;
    protected String sName;
    protected int iState;
    
    public ImageButton(final Image image) {
        this.image = new Image[3];
        this.sName = "";
        this.iState = 0;
        this.image[0] = image;
        this.addMouseListener(this);
    }
    
    public void SetPressedImage(final Image image) {
        this.image[1] = image;
    }
    
    public void SetRolloverImage(final Image image) {
        this.image[2] = image;
    }
    
    public void SetDefaultString(final String sName) {
        this.setText(this.sName = sName);
    }
    
    public void update(final Graphics graphics) {
        this.paint(graphics);
    }
    
    public void paint(final Graphics graphics) {
        graphics.drawImage(this.image[this.iState], 0, 0, this);
    }
    
    public void mouseClicked(final MouseEvent mouseEvent) {
        if (this.image[1] != null) {
            this.iState = 1;
            this.repaint();
        }
    }
    
    public void mouseEntered(final MouseEvent mouseEvent) {
        if (this.image[2] != null) {
            this.iState = 2;
            this.repaint();
        }
    }
    
    public void mouseExited(final MouseEvent mouseEvent) {
        if (this.image[0] != null) {
            this.iState = 0;
            this.repaint();
        }
    }
    
    public void mousePressed(final MouseEvent mouseEvent) {
        if (this.image[1] != null) {
            this.iState = 1;
            this.repaint();
        }
    }
    
    public void mouseReleased(final MouseEvent mouseEvent) {
        if (this.image[0] != null) {
            this.iState = 0;
            this.repaint();
        }
    }
}
