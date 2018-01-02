import java.awt.event.MouseEvent;
import java.awt.image.ImageObserver;
import java.awt.event.MouseAdapter;

// 
// Decompiled by Procyon v0.5.30
// 

final class SudokuPanel$4 extends MouseAdapter
{
    private final ImageObserver \u02bf;
    private final int \u02bc;
    private final int \u02bd;
    private final int \u02be;
    private final SudokuPanel \u0119;
    
    public void mouseClicked(final MouseEvent mouseEvent) {
        this.\u0119.\u03bc[this.\u02be].setImages(this.\u0119.\u03b7[this.\u02be], this.\u0119.\u03b8[this.\u02be], this.\u0119.\u03b9[this.\u02be]);
        if (this.\u0119.\u03c1 != 3) {
            this.\u0119.\u03c0 = this.\u02be;
            this.\u0119.\u03bf = true;
            this.\u0119.buildOffscreenBufferImage();
        }
    }
    
    public void mouseEntered(final MouseEvent mouseEvent) {
        this.\u0119.\u03bc[this.\u02be].setMouseEnter();
    }
    
    public void mouseExited(final MouseEvent mouseEvent) {
        this.\u0119.\u03bc[this.\u02be].setMouseExit();
    }
    
    public void mousePressed(final MouseEvent mouseEvent) {
        this.\u0119.\u03bc[this.\u02be].setMouseDown();
    }
    
    public void mouseReleased(final MouseEvent mouseEvent) {
        this.\u0119.\u03bc[this.\u02be].setMouseUp();
        final int n = mouseEvent.getX() + this.\u02bd;
        final int n2 = mouseEvent.getY() + this.\u02bc;
        if (this.\u0119.\u03c1 != 50 && this.\u0119.\u03bf) {
            this.\u0119.\u03bf = false;
            if (n > 0 && n < this.\u0119.\u03bd * this.\u0119.\u03a8 + this.\u0119.\u03d0 && n2 > 0 && n2 < this.\u0119.\u03be * this.\u0119.\u03a9 + this.\u0119.\u03d1) {
                final int n3 = (n - this.\u0119.\u03d0) / this.\u0119.\u03a8;
                final int n4 = (n2 - this.\u0119.\u03d1) / this.\u0119.\u03a9;
                if (this.\u0119.\u03b2[n3][n4] == 0) {
                    SudokuPanel.\u011d(this.\u0119, 0, n3, n4, this.\u0119.\u03c0 + 1);
                }
                this.\u0119.currentGrid.determinePossible();
                this.\u0119.currentGrid.scan(false);
                this.\u0119.\u03c0 = 0;
                if (this.\u0119.boardFull()) {
                    if (this.\u0119.checkSolution()) {
                        this.\u0119.\u02e4 = this.\u0119.\u02e1;
                    }
                    else {
                        this.\u0119.\u02e4 = this.\u0119.\u02e2;
                    }
                    this.\u0119.displayResult = true;
                    this.\u0119.buildOffscreenBufferImage();
                    SudokuPanel.\u02e1(this.\u0119).drawImage(SudokuPanel.\u02e2(this.\u0119), 0, 0, this.\u02bf);
                    this.\u0119.repaint();
                }
                else {
                    this.\u0119.buildOffscreenBufferImage();
                    SudokuPanel.\u02e1(this.\u0119).drawImage(SudokuPanel.\u02e2(this.\u0119), 0, 0, this.\u02bf);
                    this.\u0119.repaint();
                }
            }
            else {
                this.\u0119.buildOffscreenBufferImage();
                SudokuPanel.\u02e1(this.\u0119).drawImage(SudokuPanel.\u02e2(this.\u0119), 0, 0, this.\u02bf);
                this.\u0119.repaint();
            }
        }
        this.\u0119.requestFocus();
    }
    
    SudokuPanel$4(final int \u02be, final SudokuPanel \u0119, final ImageObserver \u02bf, final int \u02bd, final int \u02bc) {
        this.\u02be = \u02be;
        this.\u0119 = \u0119;
        this.\u02bf = \u02bf;
        this.\u02bd = \u02bd;
        this.\u02bc = \u02bc;
    }
}
