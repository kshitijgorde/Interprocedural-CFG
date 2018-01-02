import java.awt.event.MouseEvent;
import java.awt.event.MouseAdapter;

// 
// Decompiled by Procyon v0.5.30
// 

final class Sudoku$2 extends MouseAdapter
{
    private final Sudoku \u0119;
    
    public void mouseClicked(final MouseEvent mouseEvent) {
        this.\u0119.\u012c = false;
        this.\u0119.\u01a3.hide();
        this.\u0119.\u0187.show();
    }
    
    public void mouseEntered(final MouseEvent mouseEvent) {
        this.\u0119.\u01b8.setMouseEnter();
    }
    
    public void mouseExited(final MouseEvent mouseEvent) {
        this.\u0119.\u01b8.setMouseExit();
    }
    
    public void mousePressed(final MouseEvent mouseEvent) {
        this.\u0119.\u01b8.setMouseDown();
    }
    
    public void mouseReleased(final MouseEvent mouseEvent) {
        this.\u0119.\u01b8.setMouseUp();
    }
    
    Sudoku$2(final Sudoku \u0119) {
        this.\u0119 = \u0119;
    }
}
