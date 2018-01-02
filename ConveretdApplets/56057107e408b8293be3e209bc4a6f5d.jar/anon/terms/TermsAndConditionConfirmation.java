// 
// Decompiled by Procyon v0.5.30
// 

package anon.terms;

import java.util.Vector;

public interface TermsAndConditionConfirmation
{
    boolean confirmTermsAndConditions(final Vector p0, final Vector p1);
    
    public static final class AlwaysAccept implements TermsAndConditionConfirmation
    {
        public boolean confirmTermsAndConditions(final Vector vector, final Vector vector2) {
            return true;
        }
    }
}
