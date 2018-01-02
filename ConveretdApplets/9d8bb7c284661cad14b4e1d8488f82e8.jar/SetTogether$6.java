import java.awt.event.MouseEvent;
import java.awt.event.MouseAdapter;

// 
// Decompiled by Procyon v0.5.30
// 

final class SetTogether$6 extends MouseAdapter
{
    private final SetTogether \u026c;
    
    public void mouseClicked(final MouseEvent mouseEvent) {
        if (!this.\u026c.\u02a4) {
            this.\u026c.\u02a4 = true;
            this.\u026c.\u03c5.hide();
            this.\u026c.\u011cB.show();
        }
    }
    
    public void mouseEntered(final MouseEvent mouseEvent) {
        this.\u026c.\u0252.setMouseEnter();
    }
    
    public void mouseExited(final MouseEvent mouseEvent) {
        this.\u026c.\u0252.setMouseExit();
    }
    
    public void mousePressed(final MouseEvent mouseEvent) {
        this.\u026c.\u0252.setMouseDown();
    }
    
    public void mouseReleased(final MouseEvent mouseEvent) {
        this.\u026c.\u0252.setMouseUp();
    }
    
    SetTogether$6(final SetTogether \u026c) {
        this.\u026c = \u026c;
    }
}
