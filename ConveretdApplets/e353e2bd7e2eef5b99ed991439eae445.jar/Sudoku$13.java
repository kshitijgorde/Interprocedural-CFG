import java.awt.event.MouseEvent;
import java.awt.event.MouseAdapter;

// 
// Decompiled by Procyon v0.5.30
// 

final class Sudoku$13 extends MouseAdapter
{
    private final Sudoku \u0119;
    
    public void mouseClicked(final MouseEvent mouseEvent) {
        if (!this.\u0119.\u012c) {
            this.\u0119.\u012c = true;
            this.\u0119.\u0187.requestFocus();
            this.\u0119.\u0187.hide();
            this.\u0119.\u018c.hide();
            this.\u0119.\u01a3.show();
        }
    }
    
    public void mouseEntered(final MouseEvent mouseEvent) {
        this.\u0119.\u0294.setMouseEnter();
    }
    
    public void mouseExited(final MouseEvent mouseEvent) {
        this.\u0119.\u0294.setMouseExit();
    }
    
    public void mousePressed(final MouseEvent mouseEvent) {
        this.\u0119.\u0294.setMouseDown();
    }
    
    public void mouseReleased(final MouseEvent mouseEvent) {
        this.\u0119.\u0294.setMouseUp();
    }
    
    Sudoku$13(final Sudoku \u0119) {
        this.\u0119 = \u0119;
    }
}
