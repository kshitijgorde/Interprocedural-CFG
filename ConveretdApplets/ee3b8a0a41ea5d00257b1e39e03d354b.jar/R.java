import java.awt.event.MouseEvent;
import java.awt.event.MouseAdapter;

// 
// Decompiled by Procyon v0.5.30
// 

class R extends MouseAdapter
{
    private final T setVisible;
    
    R(final T setVisible) {
        this.setVisible = setVisible;
    }
    
    public final void mouseClicked(final MouseEvent mouseEvent) {
        this.setVisible.setVisible(false);
        if (null != T.I()) {
            T.I().setVisible(false);
        }
    }
}
