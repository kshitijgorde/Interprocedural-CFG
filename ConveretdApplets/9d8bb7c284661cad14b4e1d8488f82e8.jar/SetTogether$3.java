import java.awt.event.MouseEvent;
import java.awt.event.MouseAdapter;

// 
// Decompiled by Procyon v0.5.30
// 

final class SetTogether$3 extends MouseAdapter
{
    private final SetTogether \u026c;
    
    public void mouseClicked(final MouseEvent mouseEvent) {
        this.\u026c.\u03ca.hide();
        switch (this.\u026c.\u03cc) {
            case 0: {
                this.\u026c.\u03c7.show();
            }
            case 1: {
                this.\u026c.\u03c8.show();
            }
            default: {}
        }
    }
    
    SetTogether$3(final SetTogether \u026c) {
        this.\u026c = \u026c;
    }
}
