// 
// Decompiled by Procyon v0.5.30
// 

package symantec.itools.awt.util.spinner;

import java.beans.VetoableChangeListener;
import java.beans.PropertyChangeListener;
import java.beans.PropertyVetoException;
import symantec.itools.beans.PropertyChangeSupport;
import symantec.itools.beans.VetoableChangeSupport;
import java.io.Serializable;

public class NumericSpinner extends Spinner implements Serializable
{
    private VetoableChangeSupport vetos;
    private PropertyChangeSupport changes;
    
    public NumericSpinner() {
        this.vetos = new VetoableChangeSupport(this);
        this.changes = new PropertyChangeSupport(this);
        try {
            this.setIncrement(1);
        }
        catch (PropertyVetoException ex) {}
        super.min = 0;
        super.max = 10;
    }
    
    public void setMin(final int i) throws PropertyVetoException {
        super.setMin(i);
        if (super.added) {
            final int oldValue = super.textWidth;
            super.textWidth = Math.max(Integer.toString(super.min).length(), Integer.toString(super.max).length());
        }
    }
    
    public void setMax(final int i) throws PropertyVetoException {
        super.setMax(i);
        if (super.added) {
            final int oldValue = super.textWidth;
            super.textWidth = Math.max(Integer.toString(super.min).length(), Integer.toString(super.max).length());
        }
    }
    
    public void setIncrement(final int i) throws PropertyVetoException {
        final Integer oldValue = new Integer(super.increment);
        final Integer newValue = new Integer(i);
        this.vetos.fireVetoableChange("increment", oldValue, newValue);
        super.increment = i;
        this.changes.firePropertyChange("increment", oldValue, newValue);
    }
    
    public int getIncrement() {
        return super.increment;
    }
    
    public String getCurrentText() {
        return Integer.toString(super.current);
    }
    
    public void addNotify() {
        super.textWidth = Math.max(Integer.toString(super.min).length(), Integer.toString(super.max).length());
        super.text = Integer.toString(super.current);
        super.addNotify();
        this.updateText(false);
    }
    
    public synchronized void addPropertyChangeListener(final PropertyChangeListener listener) {
        super.addPropertyChangeListener(listener);
        this.changes.addPropertyChangeListener(listener);
    }
    
    public synchronized void removePropertyChangeListener(final PropertyChangeListener listener) {
        super.removePropertyChangeListener(listener);
        this.changes.removePropertyChangeListener(listener);
    }
    
    public synchronized void addVetoableChangeListener(final VetoableChangeListener listener) {
        super.addVetoableChangeListener(listener);
        this.vetos.addVetoableChangeListener(listener);
    }
    
    public synchronized void removeVetoableChangeListener(final VetoableChangeListener listener) {
        super.removeVetoableChangeListener(listener);
        this.vetos.removeVetoableChangeListener(listener);
    }
}
