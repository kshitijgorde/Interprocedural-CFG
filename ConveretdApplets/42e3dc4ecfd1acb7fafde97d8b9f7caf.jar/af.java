import java.awt.event.MouseEvent;
import java.awt.event.MouseAdapter;

// 
// Decompiled by Procyon v0.5.30
// 

public class af extends MouseAdapter
{
    private v a;
    
    public af(final v a) {
        this.a = a;
    }
    
    public void mouseClicked(final MouseEvent mouseEvent) {
        this.a.b();
    }
}
