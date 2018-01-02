import java.awt.event.MouseEvent;
import java.awt.event.MouseAdapter;

// 
// Decompiled by Procyon v0.5.30
// 

final class Sudoku$4 extends MouseAdapter
{
    private final Sudoku \u0119;
    
    public void mouseClicked(final MouseEvent mouseEvent) {
        this.\u0119.\u012c = false;
        this.\u0119.\u01c0.hide();
        this.\u0119.\u0187.show();
    }
    
    public void mouseEntered(final MouseEvent mouseEvent) {
        this.\u0119.\u01fa.setMouseEnter();
    }
    
    public void mouseExited(final MouseEvent mouseEvent) {
        this.\u0119.\u01fa.setMouseExit();
    }
    
    public void mousePressed(final MouseEvent mouseEvent) {
        this.\u0119.\u01fa.setMouseDown();
    }
    
    public void mouseReleased(final MouseEvent mouseEvent) {
        this.\u0119.\u01fa.setMouseUp();
    }
    
    Sudoku$4(final Sudoku \u0119) {
        this.\u0119 = \u0119;
    }
}
