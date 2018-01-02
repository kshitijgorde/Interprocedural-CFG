import java.awt.event.MouseEvent;
import java.awt.event.MouseAdapter;

// 
// Decompiled by Procyon v0.5.30
// 

public class ak extends MouseAdapter
{
    private ah a;
    
    public ak(final ah a) {
        this.a = a;
    }
    
    public void mouseClicked(final MouseEvent mouseEvent) {
        this.a.a();
    }
}
