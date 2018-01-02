import java.awt.event.MouseEvent;
import java.awt.event.MouseAdapter;

// 
// Decompiled by Procyon v0.5.30
// 

final class Sudoku$12 extends MouseAdapter
{
    private final Sudoku \u0119;
    
    public void mouseClicked(final MouseEvent mouseEvent) {
    }
    
    public void mouseEntered(final MouseEvent mouseEvent) {
        this.\u0119.\u028c.setMouseEnter();
    }
    
    public void mouseExited(final MouseEvent mouseEvent) {
        this.\u0119.\u028c.setMouseExit();
    }
    
    public void mousePressed(final MouseEvent mouseEvent) {
        this.\u0119.\u028c.setMouseDown();
        this.\u0119.\u0187.setShowCandidates(!this.\u0119.\u0187.showCandidates);
        this.\u0119.\u0187.requestFocus();
    }
    
    public void mouseReleased(final MouseEvent mouseEvent) {
        this.\u0119.\u028c.setMouseUp();
    }
    
    Sudoku$12(final Sudoku \u0119) {
        this.\u0119 = \u0119;
    }
}
