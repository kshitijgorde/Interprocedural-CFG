import java.awt.event.MouseEvent;
import java.awt.event.MouseAdapter;

// 
// Decompiled by Procyon v0.5.30
// 

final class j extends MouseAdapter
{
    private final eb a;
    
    j(final eb a) {
        this.a = a;
    }
    
    public void mouseClicked(final MouseEvent mouseEvent) {
        eb.a(this.a).e();
    }
}
