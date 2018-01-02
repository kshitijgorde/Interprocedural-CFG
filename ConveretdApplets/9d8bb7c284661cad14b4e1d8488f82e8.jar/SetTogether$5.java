import java.awt.event.MouseEvent;
import java.awt.event.MouseAdapter;

// 
// Decompiled by Procyon v0.5.30
// 

final class SetTogether$5 extends MouseAdapter
{
    private final SetTogether \u026c;
    
    public void mouseClicked(final MouseEvent mouseEvent) {
        this.\u026c.\u03c7.hide();
        this.\u026c.\u03c8.show();
        this.\u026c.\u0432.hide();
    }
    
    public void mouseEntered(final MouseEvent mouseEvent) {
        this.\u026c.\u0432.setMouseEnter();
    }
    
    public void mouseExited(final MouseEvent mouseEvent) {
        this.\u026c.\u0432.setMouseExit();
    }
    
    public void mousePressed(final MouseEvent mouseEvent) {
        this.\u026c.\u0432.setMouseDown();
    }
    
    public void mouseReleased(final MouseEvent mouseEvent) {
        this.\u026c.\u0432.setMouseUp();
    }
    
    SetTogether$5(final SetTogether \u026c) {
        this.\u026c = \u026c;
    }
}
