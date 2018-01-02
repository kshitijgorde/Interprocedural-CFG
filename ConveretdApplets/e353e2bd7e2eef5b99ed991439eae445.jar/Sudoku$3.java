import java.awt.event.MouseEvent;
import java.awt.event.MouseAdapter;

// 
// Decompiled by Procyon v0.5.30
// 

final class Sudoku$3 extends MouseAdapter
{
    private final Sudoku \u0119;
    
    public void mouseClicked(final MouseEvent mouseEvent) {
        Sudoku.\u011d(this.\u0119);
        this.\u0119.\u01c0.hide();
        this.\u0119.\u0187.show();
        this.\u0119.\u012c = false;
    }
    
    public void mouseEntered(final MouseEvent mouseEvent) {
        this.\u0119.\u01ee.setMouseEnter();
    }
    
    public void mouseExited(final MouseEvent mouseEvent) {
        this.\u0119.\u01ee.setMouseExit();
    }
    
    public void mousePressed(final MouseEvent mouseEvent) {
        this.\u0119.\u01ee.setMouseDown();
    }
    
    public void mouseReleased(final MouseEvent mouseEvent) {
        this.\u0119.\u01ee.setMouseUp();
    }
    
    Sudoku$3(final Sudoku \u0119) {
        this.\u0119 = \u0119;
    }
}
