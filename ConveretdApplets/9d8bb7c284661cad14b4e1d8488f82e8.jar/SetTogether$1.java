import java.awt.event.MouseEvent;
import java.awt.event.MouseAdapter;

// 
// Decompiled by Procyon v0.5.30
// 

final class SetTogether$1 extends MouseAdapter
{
    private final SetTogether \u026c;
    
    public void mouseClicked(final MouseEvent mouseEvent) {
        this.\u026c.createRoom();
    }
    
    public void mouseEntered(final MouseEvent mouseEvent) {
        this.\u026c.\u0267B.\u015f = true;
        this.\u026c.\u0267B.repaint();
    }
    
    public void mouseExited(final MouseEvent mouseEvent) {
        this.\u026c.\u0267B.\u015f = false;
        this.\u026c.\u0267B.repaint();
    }
    
    SetTogether$1(final SetTogether \u026c) {
        this.\u026c = \u026c;
    }
}
