import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;

// 
// Decompiled by Procyon v0.5.30
// 

final class SudokuPanel$3 implements MouseMotionListener
{
    private final int \u02bc;
    private final int \u02bd;
    private final int \u02be;
    private final SudokuPanel \u0119;
    
    public void mouseMoved(final MouseEvent mouseEvent) {
    }
    
    public void mouseDragged(final MouseEvent mouseEvent) {
        if (this.\u0119.\u03c1 != 3) {
            if (this.\u0119.\u03bf) {
                this.\u0119.\u03bc[this.\u02be].lowerButton = true;
                this.\u0119.\u03bc[this.\u02be].mouseXPosition = mouseEvent.getX();
                this.\u0119.\u03bc[this.\u02be].mouseYPosition = mouseEvent.getY();
                this.\u0119.\u03bc[this.\u02be].repaint();
                this.\u0119.drawDragItem(this.\u0119.\u03c0, mouseEvent.getX() + this.\u02bd, mouseEvent.getY() + this.\u02bc);
                return;
            }
            this.\u0119.\u03c0 = this.\u02be;
            this.\u0119.\u03bf = true;
            this.\u0119.buildOffscreenBufferImage();
            this.\u0119.drawDragItem(this.\u0119.\u03c0, mouseEvent.getX() + this.\u02bd, mouseEvent.getY() + this.\u02bc);
        }
    }
    
    SudokuPanel$3(final int \u02be, final SudokuPanel \u0119, final int \u02bd, final int \u02bc) {
        this.\u02be = \u02be;
        this.\u0119 = \u0119;
        this.\u02bd = \u02bd;
        this.\u02bc = \u02bc;
    }
}
