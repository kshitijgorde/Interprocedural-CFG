import java.awt.event.MouseEvent;
import java.awt.event.MouseAdapter;

// 
// Decompiled by Procyon v0.5.30
// 

public class squarelisten2 extends MouseAdapter
{
    squarelink2 mApplet;
    squarehelper2 mButton;
    boolean bEntering;
    boolean bExiting;
    
    squarelisten2(final squarelink2 mApplet) {
        this.mApplet = mApplet;
    }
    
    squarelisten2(final squarehelper2 mButton) {
        this.mButton = mButton;
    }
    
    public void mouseEntered(final MouseEvent mouseEvent) {
        if (mouseEvent.getSource() == this.mApplet) {
            this.mApplet.handleMouseEntered();
        }
        if (mouseEvent.getSource() == this.mButton) {
            this.mButton.handleMouseEntered();
        }
    }
    
    public void mouseExited(final MouseEvent mouseEvent) {
        if (mouseEvent.getSource() == this.mApplet) {
            this.mApplet.handleMouseExited();
        }
        if (mouseEvent.getSource() == this.mButton) {
            this.mButton.handleMouseExited();
        }
    }
    
    public void mousePressed(final MouseEvent mouseEvent) {
        if (mouseEvent.getSource() == this.mButton) {
            this.mButton.handleMousePressed();
        }
    }
    
    public void mouseReleased(final MouseEvent mouseEvent) {
        if (mouseEvent.getSource() == this.mButton) {
            this.mButton.handleMouseReleased();
        }
    }
}
