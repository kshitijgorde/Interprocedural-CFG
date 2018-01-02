import java.awt.event.MouseEvent;
import java.awt.event.MouseAdapter;

// 
// Decompiled by Procyon v0.5.30
// 

class boxCheckListener extends MouseAdapter
{
    int x;
    int y;
    int width;
    
    public void mousePressed(final MouseEvent mouseEvent) {
        this.x = mouseEvent.getX();
        this.y = mouseEvent.getY();
        if (this.x > 10 && this.x < 25) {
            if (this.y > 40 && this.y < 55) {
                thetriv.currentAnswer = 1;
            }
            if (this.y > 60 && this.y < 75) {
                thetriv.currentAnswer = 2;
            }
            if (this.y > 80 && this.y < 95) {
                thetriv.currentAnswer = 3;
            }
            if (this.y > 100 && this.y < 115) {
                thetriv.currentAnswer = 4;
            }
        }
        thetriv.qPage.repaint();
    }
}
