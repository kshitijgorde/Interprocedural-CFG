import java.awt.event.MouseEvent;
import java.awt.event.MouseAdapter;

// 
// Decompiled by Procyon v0.5.30
// 

final class Sudoku$9 extends MouseAdapter
{
    private final Sudoku \u0119;
    
    public void mouseClicked(final MouseEvent mouseEvent) {
    }
    
    public void mouseEntered(final MouseEvent mouseEvent) {
        this.\u0119.\u0264.setMouseEnter();
    }
    
    public void mouseExited(final MouseEvent mouseEvent) {
        this.\u0119.\u0264.setMouseExit();
    }
    
    public void mousePressed(final MouseEvent mouseEvent) {
        this.\u0119.\u0264.setMouseDown();
        this.\u0119.\u0187.checkMoves(!this.\u0119.\u0187.checkMoves);
        this.\u0119.repaint();
    }
    
    public void mouseReleased(final MouseEvent mouseEvent) {
        this.\u0119.\u0264.setMouseUp();
    }
    
    Sudoku$9(final Sudoku \u0119) {
        this.\u0119 = \u0119;
    }
}
