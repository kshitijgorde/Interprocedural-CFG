import java.util.EventListener;

// 
// Decompiled by Procyon v0.5.30
// 

public interface IAXEventListener extends EventListener
{
    void answer(final int p0);
    
    void dial();
    
    void hangup(final int p0);
    
    void hold();
    
    void mute(final int p0, final boolean p1);
    
    void register(final String p0);
    
    void text(final String p0);
}
