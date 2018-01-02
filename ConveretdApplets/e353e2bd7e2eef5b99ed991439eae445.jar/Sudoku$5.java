import java.awt.event.MouseEvent;
import java.awt.event.MouseAdapter;

// 
// Decompiled by Procyon v0.5.30
// 

final class Sudoku$5 extends MouseAdapter
{
    private final Sudoku \u0119;
    
    public void mouseClicked(final MouseEvent mouseEvent) {
        this.\u0119.\u0187.undoMove();
    }
    
    public void mouseEntered(final MouseEvent mouseEvent) {
        this.\u0119.\u020c.setMouseEnter();
    }
    
    public void mouseExited(final MouseEvent mouseEvent) {
        this.\u0119.\u020c.setMouseExit();
    }
    
    public void mousePressed(final MouseEvent mouseEvent) {
        this.\u0119.\u020c.setMouseDown();
    }
    
    public void mouseReleased(final MouseEvent mouseEvent) {
        this.\u0119.\u020c.setMouseUp();
    }
    
    Sudoku$5(final Sudoku \u0119) {
        this.\u0119 = \u0119;
    }
}
