// 
// Decompiled by Procyon v0.5.30
// 

package javax.accessibility;

public abstract class AccessibleHyperlink implements AccessibleAction
{
    public abstract boolean doAccessibleAction(final int p0);
    
    public abstract Object getAccessibleActionAnchor(final int p0);
    
    public abstract int getAccessibleActionCount();
    
    public abstract String getAccessibleActionDescription(final int p0);
    
    public abstract Object getAccessibleActionObject(final int p0);
    
    public abstract int getEndIndex();
    
    public abstract int getStartIndex();
    
    public abstract boolean isValid();
}
