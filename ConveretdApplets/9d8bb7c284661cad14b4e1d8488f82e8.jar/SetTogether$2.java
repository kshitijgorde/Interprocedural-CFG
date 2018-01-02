import java.awt.event.MouseEvent;
import java.awt.event.MouseAdapter;

// 
// Decompiled by Procyon v0.5.30
// 

final class SetTogether$2 extends MouseAdapter
{
    private final SetTogether \u026c;
    
    public void mouseClicked(final MouseEvent mouseEvent) {
        this.\u026c.\u03c8.hide();
        this.\u026c.\u03c7.show();
        this.\u026c.\u0432.show();
    }
    
    public void mouseEntered(final MouseEvent mouseEvent) {
        this.\u026c.\u0268B.\u015f = true;
        this.\u026c.\u0268B.repaint();
    }
    
    public void mouseExited(final MouseEvent mouseEvent) {
        this.\u026c.\u0268B.\u015f = false;
        this.\u026c.\u0268B.repaint();
    }
    
    SetTogether$2(final SetTogether \u026c) {
        this.\u026c = \u026c;
    }
}
