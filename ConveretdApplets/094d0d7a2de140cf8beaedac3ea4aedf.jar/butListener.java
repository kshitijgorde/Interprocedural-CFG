import java.awt.event.MouseEvent;
import java.awt.event.MouseAdapter;

// 
// Decompiled by Procyon v0.5.30
// 

class butListener extends MouseAdapter
{
    int x;
    int y;
    int width;
    
    public void mousePressed(final MouseEvent mouseEvent) {
        this.x = mouseEvent.getX();
        this.y = mouseEvent.getY();
        if (this.x < 155) {
            thetriv.buttonMode = 1;
        }
        else {
            thetriv.buttonMode = 2;
        }
        thetriv.bPage.repaint();
    }
    
    public void mouseReleased(final MouseEvent mouseEvent) {
        if (thetriv.buttonMode == 1 && !thetriv.start) {
            if (thetriv.inPlay) {
                thetriv.checkAnswer();
                thetriv.bPage.resetTimer();
            }
            thetriv.sPage.repaint();
            thetriv.qPage.repaint();
            ++thetriv.i;
            if (thetriv.inPlay) {
                thetriv.questionUser();
            }
            if (thetriv.i >= thetriv.qTotal) {
                thetriv.inPlay = false;
                thetriv.i = thetriv.qTotal - 1;
                thetriv.qPage.repaint();
            }
        }
        if (thetriv.buttonMode == 2 && !thetriv.inPlay) {
            thetriv.reset();
        }
        thetriv.buttonMode = 0;
        thetriv.currentAnswer = 0;
        thetriv.bPage.repaint();
        if (thetriv.start) {
            thetriv.start = false;
            thetriv.qPage.repaint();
        }
    }
}
