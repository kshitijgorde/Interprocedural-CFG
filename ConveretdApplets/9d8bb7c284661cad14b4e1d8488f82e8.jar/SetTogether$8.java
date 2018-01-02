import java.awt.event.MouseEvent;
import java.awt.event.MouseAdapter;

// 
// Decompiled by Procyon v0.5.30
// 

final class SetTogether$8 extends MouseAdapter
{
    private final int \u026d;
    private final SetTogether \u026c;
    
    public void mouseEntered(final MouseEvent mouseEvent) {
        this.\u026c.\u046a[this.\u026d].setMouseEnter();
    }
    
    public void mouseExited(final MouseEvent mouseEvent) {
        this.\u026c.\u046a[this.\u026d].setMouseExit();
    }
    
    public void mousePressed(final MouseEvent mouseEvent) {
        if (!this.\u026c.\u046a[this.\u026d].mouseDown()) {
            this.\u026c.\u046a[this.\u026d].setMouseDown();
            for (int i = 0; i < 4; ++i) {
                if (i != this.\u026d) {
                    this.\u026c.\u046a[i].setMouseUp();
                }
            }
            this.\u026c.\u028f = this.\u026d + 1;
            this.\u026c.loadPuzzle();
        }
    }
    
    SetTogether$8(final int \u026d, final SetTogether \u026c) {
        this.\u026d = \u026d;
        this.\u026c = \u026c;
    }
}
