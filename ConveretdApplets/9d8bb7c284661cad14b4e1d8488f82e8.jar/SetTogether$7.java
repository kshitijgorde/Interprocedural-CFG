import java.awt.event.MouseEvent;
import java.awt.event.MouseAdapter;

// 
// Decompiled by Procyon v0.5.30
// 

final class SetTogether$7 extends MouseAdapter
{
    private final SetTogether \u026c;
    
    public void mouseClicked(final MouseEvent mouseEvent) {
        this.\u026c.\u02a4 = false;
        this.\u026c.\u011cB.hide();
        this.\u026c.\u03c5.show();
    }
    
    public void mouseEntered(final MouseEvent mouseEvent) {
        this.\u026c.\u04df.setMouseEnter();
    }
    
    public void mouseExited(final MouseEvent mouseEvent) {
        this.\u026c.\u04df.setMouseExit();
    }
    
    public void mousePressed(final MouseEvent mouseEvent) {
        this.\u026c.\u04df.setMouseDown();
    }
    
    public void mouseReleased(final MouseEvent mouseEvent) {
        this.\u026c.\u04df.setMouseUp();
    }
    
    SetTogether$7(final SetTogether \u026c) {
        this.\u026c = \u026c;
    }
}
