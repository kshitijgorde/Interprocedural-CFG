import java.awt.event.MouseEvent;
import java.awt.event.MouseAdapter;

// 
// Decompiled by Procyon v0.5.30
// 

final class Sudoku$10 extends MouseAdapter
{
    private final Sudoku \u0119;
    
    public void mouseClicked(final MouseEvent mouseEvent) {
        if (this.\u0119.\u02a4.timerRunning) {
            this.\u0119.\u02a4.startpauze();
            this.\u0119.\u027c.setImages(this.\u0119.\u0137, this.\u0119.\u0138, this.\u0119.\u0139);
        }
        else if (!this.\u0119.\u02a4.timerStarted) {
            this.\u0119.\u02a4.startTimer(0L);
            this.\u0119.\u027c.setImages(this.\u0119.\u013a, this.\u0119.\u013b, this.\u0119.\u013c);
        }
        else {
            this.\u0119.\u02a4.stoppauze();
            this.\u0119.\u027c.setImages(this.\u0119.\u0137, this.\u0119.\u0138, this.\u0119.\u0139);
        }
        this.\u0119.\u0187.requestFocus();
    }
    
    public void mouseEntered(final MouseEvent mouseEvent) {
        this.\u0119.\u027c.setMouseEnter();
    }
    
    public void mouseExited(final MouseEvent mouseEvent) {
        this.\u0119.\u027c.setMouseExit();
    }
    
    public void mousePressed(final MouseEvent mouseEvent) {
        this.\u0119.\u027c.setMouseDown();
    }
    
    public void mouseReleased(final MouseEvent mouseEvent) {
        this.\u0119.\u027c.setMouseUp();
    }
    
    Sudoku$10(final Sudoku \u0119) {
        this.\u0119 = \u0119;
    }
}
