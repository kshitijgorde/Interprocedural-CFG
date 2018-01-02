// 
// Decompiled by Procyon v0.5.30
// 

package jap;

import anon.infoservice.ServiceOperator;

public interface TermsAndCondtionsTableController
{
    boolean handleOperatorAction(final ServiceOperator p0, final boolean p1);
    
    void handleSelectLineAction(final ServiceOperator p0);
    
    void handleAcceptAction(final ServiceOperator p0, final boolean p1);
}
