import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

// 
// Decompiled by Procyon v0.5.30
// 

public final class a implements FocusListener
{
    public final zipdecode a;
    
    public final void focusGained(final FocusEvent focusEvent) {
        this.a.m();
    }
    
    public final void focusLost(final FocusEvent focusEvent) {
        this.a.m();
    }
    
    public a(final zipdecode a) {
        this.a = a;
    }
}
