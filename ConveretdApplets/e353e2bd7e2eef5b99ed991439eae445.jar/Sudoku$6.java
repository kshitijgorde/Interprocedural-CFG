import java.awt.event.MouseEvent;
import java.awt.event.MouseAdapter;

// 
// Decompiled by Procyon v0.5.30
// 

final class Sudoku$6 extends MouseAdapter
{
    private final Sudoku \u0119;
    
    public void mouseClicked(final MouseEvent mouseEvent) {
        this.\u0119.\u0187.showHint();
        this.\u0119.repaint();
    }
    
    public void mouseEntered(final MouseEvent mouseEvent) {
        this.\u0119.\u0214.setMouseEnter();
    }
    
    public void mouseExited(final MouseEvent mouseEvent) {
        this.\u0119.\u0214.setMouseExit();
    }
    
    public void mousePressed(final MouseEvent mouseEvent) {
        this.\u0119.\u0214.setMouseDown();
    }
    
    public void mouseReleased(final MouseEvent mouseEvent) {
        this.\u0119.\u0214.setMouseUp();
    }
    
    Sudoku$6(final Sudoku \u0119) {
        this.\u0119 = \u0119;
    }
}
