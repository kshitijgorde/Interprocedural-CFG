import java.io.Serializable;

// 
// Decompiled by Procyon v0.5.30
// 

public class JMUserData implements Serializable
{
    public StringBuffer props;
    public String signature;
    
    public JMUserData(final StringBuffer props, final String signature) {
        this.props = props;
        this.signature = signature;
    }
}
