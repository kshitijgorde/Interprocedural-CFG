// 
// Decompiled by Procyon v0.5.30
// 

package anon.terms;

import anon.infoservice.ServiceOperator;
import java.util.Vector;

public class TermsAndConditionsReadException extends Exception
{
    Vector tcsTosShow;
    
    public TermsAndConditionsReadException() {
        this.tcsTosShow = new Vector();
    }
    
    public void addTermsAndConditonsToRead(final TermsAndConditions termsAndConditions) {
        this.tcsTosShow.addElement(termsAndConditions);
    }
    
    public Vector getTermsTermsAndConditonsToRead() {
        return (Vector)this.tcsTosShow.clone();
    }
    
    public Vector getOperators() {
        final Vector vector = new Vector<ServiceOperator>();
        for (int i = 0; i < this.tcsTosShow.size(); ++i) {
            final ServiceOperator operator = this.tcsTosShow.elementAt(i).getOperator();
            if (!vector.contains(operator)) {
                vector.addElement(operator);
            }
        }
        return vector;
    }
}
