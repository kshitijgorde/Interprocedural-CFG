// 
// Decompiled by Procyon v0.5.30
// 

package javax.management;

public class MBeanRegistrationException extends MBeanException
{
    private static final long serialVersionUID = 4482382455277067805L;
    
    public MBeanRegistrationException(final Exception e) {
        super(e);
    }
    
    public MBeanRegistrationException(final Exception e, final String message) {
        super(e, message);
    }
}
