// 
// Decompiled by Procyon v0.5.30
// 

package javax.accessibility;

import java.awt.IllegalComponentStateException;
import java.util.Locale;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public abstract class AccessibleContext
{
    public static final String ACCESSIBLE_NAME_PROPERTY = "AccessibleName";
    public static final String ACCESSIBLE_DESCRIPTION_PROPERTY = "AccessibleDescription";
    public static final String ACCESSIBLE_STATE_PROPERTY = "AccessibleState";
    public static final String ACCESSIBLE_VALUE_PROPERTY = "AccessibleValue";
    public static final String ACCESSIBLE_SELECTION_PROPERTY = "AccessibleSelection";
    public static final String ACCESSIBLE_TEXT_PROPERTY = "AccessibleText";
    public static final String ACCESSIBLE_CARET_PROPERTY = "AccessibleCaret";
    public static final String ACCESSIBLE_VISIBLE_DATA_PROPERTY = "AccessibleVisibleData";
    public static final String ACCESSIBLE_CHILD_PROPERTY = "AccessibleChild";
    public static final String ACCESSIBLE_ACTIVE_DESCENDANT_PROPERTY = "AccessibleActiveDescendant";
    protected Accessible accessibleParent;
    protected String accessibleName;
    protected String accessibleDescription;
    private PropertyChangeSupport accessibleChangeSupport;
    
    public AccessibleContext() {
        this.accessibleParent = null;
        this.accessibleName = null;
        this.accessibleDescription = null;
        this.accessibleChangeSupport = null;
    }
    
    public void addPropertyChangeListener(final PropertyChangeListener propertyChangeListener) {
        if (this.accessibleChangeSupport == null) {
            this.accessibleChangeSupport = new PropertyChangeSupport(this);
        }
        this.accessibleChangeSupport.addPropertyChangeListener(propertyChangeListener);
    }
    
    public void firePropertyChange(final String s, final Object o, final Object o2) {
        if (this.accessibleChangeSupport != null) {
            this.accessibleChangeSupport.firePropertyChange(s, o, o2);
        }
    }
    
    public AccessibleAction getAccessibleAction() {
        return null;
    }
    
    public abstract Accessible getAccessibleChild(final int p0);
    
    public abstract int getAccessibleChildrenCount();
    
    public AccessibleComponent getAccessibleComponent() {
        return null;
    }
    
    public String getAccessibleDescription() {
        return this.accessibleDescription;
    }
    
    public abstract int getAccessibleIndexInParent();
    
    public String getAccessibleName() {
        return this.accessibleName;
    }
    
    public Accessible getAccessibleParent() {
        return this.accessibleParent;
    }
    
    public abstract AccessibleRole getAccessibleRole();
    
    public AccessibleSelection getAccessibleSelection() {
        return null;
    }
    
    public abstract AccessibleStateSet getAccessibleStateSet();
    
    public AccessibleText getAccessibleText() {
        return null;
    }
    
    public AccessibleValue getAccessibleValue() {
        return null;
    }
    
    public abstract Locale getLocale() throws IllegalComponentStateException;
    
    public void removePropertyChangeListener(final PropertyChangeListener propertyChangeListener) {
        if (this.accessibleChangeSupport != null) {
            this.accessibleChangeSupport.removePropertyChangeListener(propertyChangeListener);
        }
    }
    
    public void setAccessibleDescription(final String accessibleDescription) {
        this.firePropertyChange("AccessibleDescription", this.accessibleDescription, this.accessibleDescription = accessibleDescription);
    }
    
    public void setAccessibleName(final String accessibleName) {
        this.firePropertyChange("AccessibleName", this.accessibleName, this.accessibleName = accessibleName);
    }
    
    public void setAccessibleParent(final Accessible accessibleParent) {
        this.accessibleParent = accessibleParent;
    }
}
