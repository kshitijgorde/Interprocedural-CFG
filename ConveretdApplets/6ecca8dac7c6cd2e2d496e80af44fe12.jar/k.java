import java.awt.Event;

// 
// Decompiled by Procyon v0.5.30
// 

public class k extends j
{
    p i;
    
    k(final p i) {
        super(bm.dl, bm.dm, null, false);
        this.i = i;
    }
    
    public boolean action(final Event event, final Object o) {
        final String text = super.d.getText();
        if (this.i != null && !text.equals("")) {
            this.i.c(text);
        }
        this.hide();
        return true;
    }
}
