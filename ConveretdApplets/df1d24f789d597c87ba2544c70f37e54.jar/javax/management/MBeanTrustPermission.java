// 
// Decompiled by Procyon v0.5.30
// 

package javax.management;

import java.security.BasicPermission;

public class MBeanTrustPermission extends BasicPermission
{
    private static final long serialVersionUID = -2952178077029018140L;
    
    public MBeanTrustPermission(final String name) throws IllegalArgumentException, NullPointerException {
        this(name, null);
    }
    
    public MBeanTrustPermission(final String name, final String actions) throws IllegalArgumentException, NullPointerException {
        super(name, actions);
        if (name == null) {
            throw new NullPointerException("name cannot be null");
        }
        if (!name.equals("register") && !name.equals("*")) {
            throw new IllegalArgumentException("name must be 'register' or '*'");
        }
        if (actions != null && actions.length() > 0) {
            throw new IllegalArgumentException("actions must be null or ''");
        }
    }
}
