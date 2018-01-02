import java.awt.event.MouseEvent;
import java.awt.event.MouseAdapter;

// 
// Decompiled by Procyon v0.5.30
// 

public class ca extends MouseAdapter
{
    private b9 a;
    
    public ca(final b9 a) {
        this.a = a;
    }
    
    public void mousePressed(final MouseEvent mouseEvent) {
        this.a.a();
    }
}
