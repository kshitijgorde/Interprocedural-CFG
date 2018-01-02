import java.awt.event.MouseEvent;
import java.awt.event.MouseAdapter;

// 
// Decompiled by Procyon v0.5.30
// 

final class SudokuPanel$2 extends MouseAdapter
{
    private final SudokuPanel \u0119;
    
    public void mouseClicked(final MouseEvent mouseEvent) {
        this.\u0119.\u018c.hide();
        this.\u0119.repaint();
    }
    
    public void mouseEntered(final MouseEvent mouseEvent) {
    }
    
    public void mouseExited(final MouseEvent mouseEvent) {
    }
    
    public void mousePressed(final MouseEvent mouseEvent) {
    }
    
    SudokuPanel$2(final SudokuPanel \u0119) {
        this.\u0119 = \u0119;
    }
}
