// 
// Decompiled by Procyon v0.5.30
// 

package borland.jbcl.util;

public class VetoException extends Exception
{
    private String vetoMessage;
    
    public VetoException() {
        this.vetoMessage = null;
    }
    
    public VetoException(final String vetoMessage) {
        super(vetoMessage);
        this.vetoMessage = vetoMessage;
    }
    
    public String getVetoMessage() {
        return this.vetoMessage;
    }
}
