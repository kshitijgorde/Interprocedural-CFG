import java.io.ByteArrayInputStream;
import java.awt.AWTEvent;

// 
// Decompiled by Procyon v0.5.30
// 

public class OmegaAnswerEvent extends AWTEvent
{
    public String[] str;
    public ByteArrayInputStream input;
    public OmegaClient omega;
    
    public OmegaAnswerEvent(final Object o, final OmegaClient omega) {
        super(o, 2999);
        this.omega = omega;
    }
}
