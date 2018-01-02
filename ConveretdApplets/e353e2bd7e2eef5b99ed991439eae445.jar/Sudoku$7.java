import java.awt.event.MouseEvent;
import java.awt.event.MouseAdapter;

// 
// Decompiled by Procyon v0.5.30
// 

final class Sudoku$7 extends MouseAdapter
{
    private final Sudoku \u0119;
    
    public void mouseClicked(final MouseEvent mouseEvent) {
    }
    
    public void mouseEntered(final MouseEvent mouseEvent) {
        this.\u0119.\u0254.setMouseEnter();
    }
    
    public void mouseExited(final MouseEvent mouseEvent) {
        this.\u0119.\u0254.setMouseExit();
    }
    
    public void mousePressed(final MouseEvent mouseEvent) {
        this.\u0119.\u0254.setMouseDown();
        if (this.\u0119.\u012b) {
            this.\u0119.\u0187.setPencilMode(false);
            this.\u0119.\u012b = false;
        }
        else {
            this.\u0119.\u0187.setPencilMode(true);
            this.\u0119.\u012b = true;
        }
        this.\u0119.repaint();
        this.\u0119.\u0187.requestFocus();
    }
    
    public void mouseReleased(final MouseEvent mouseEvent) {
        this.\u0119.\u0254.setMouseUp();
    }
    
    Sudoku$7(final Sudoku \u0119) {
        this.\u0119 = \u0119;
    }
}
