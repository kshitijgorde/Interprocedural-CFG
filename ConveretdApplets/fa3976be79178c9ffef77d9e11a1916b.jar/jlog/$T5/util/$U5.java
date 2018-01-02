// 
// Decompiled by Procyon v0.5.30
// 

package jlog.$T5.util;

import java.util.Enumeration;
import jlog.$BI.$M4;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.Vector;
import java.beans.PropertyChangeSupport;
import jlog.$H4;

public class $U5 implements $H4
{
    private PropertyChangeSupport pcs;
    private Vector v;
    private String name;
    private String $APC;
    private Object source;
    public static final String $KOD = "CREATE_PROPERTY";
    public static final String $FKC = "name";
    public static final String $GKC = "desc";
    
    public static boolean $LOD(final Object o) {
        return o == null || (o instanceof String && "".equals(((String)o).trim()));
    }
    
    public static boolean $RQ(final Object o, final Object o2) {
        return o == o2 || (o != null && o2 != null && o.equals(o2) && o2.equals(o));
    }
    
    public $U5() {
        this.pcs = null;
        this.name = "";
        this.$APC = "";
        this.source = null;
    }
    
    public $U5(final Object source) {
        this();
        this.setSource(source);
    }
    
    public $U5(final String name, final String $apc) {
        this();
        this.name = name;
        this.$APC = $apc;
    }
    
    public void addPropertyChangeListener(final PropertyChangeListener propertyChangeListener, final boolean b) {
        this.pcs.addPropertyChangeListener(propertyChangeListener);
        if (b) {
            propertyChangeListener.propertyChange(new PropertyChangeEvent(this.source, "name", null, this.name));
            propertyChangeListener.propertyChange(new PropertyChangeEvent(this.source, "desc", null, this.$APC));
        }
    }
    
    public void finalize() {
    }
    
    public void firePropertyChange(final String s) {
        try {
            this.pcs.firePropertyChange(s, "CREATE_PROPERTY", null);
        }
        catch (Throwable t) {
            $M4.print(t);
        }
    }
    
    public void firePropertyChange(final String s, final float n) {
        try {
            this.pcs.firePropertyChange(s, null, new Float(n));
        }
        catch (Throwable t) {
            $M4.print(t);
        }
    }
    
    public void firePropertyChange(final String s, final float n, final float n2) {
        try {
            this.pcs.firePropertyChange(s, new Float(n), new Float(n2));
        }
        catch (Throwable t) {
            $M4.print(t);
        }
    }
    
    public void firePropertyChange(final String s, final int n) {
        try {
            this.pcs.firePropertyChange(s, null, new Integer(n));
        }
        catch (Throwable t) {
            $M4.print(t);
        }
    }
    
    public void firePropertyChange(final String s, final int n, final int n2) {
        try {
            this.pcs.firePropertyChange(s, new Integer(n), new Integer(n2));
        }
        catch (Throwable t) {
            $M4.print(t);
        }
    }
    
    public void firePropertyChange(final String s, final Object o) {
        try {
            this.pcs.firePropertyChange(s, "CREATE_PROPERTY", o);
        }
        catch (Throwable t) {
            $M4.print(t);
        }
    }
    
    public void firePropertyChange(final String s, final Object o, final Object o2) {
        try {
            this.pcs.firePropertyChange(s, o, o2);
        }
        catch (Throwable t) {
            $M4.print(t);
        }
    }
    
    public void firePropertyChange(final String s, final Enumeration enumeration) {
        try {
            while (enumeration.hasMoreElements()) {
                this.pcs.firePropertyChange(s, "CREATE_PROPERTY", enumeration.nextElement());
            }
        }
        catch (Throwable t) {
            $M4.print(t);
        }
    }
    
    public void firePropertyChange(final String s, final boolean b) {
        this.firePropertyChange(s, b ^ true, b);
    }
    
    public void firePropertyChange(final String s, final boolean b, final boolean b2) {
        try {
            this.pcs.firePropertyChange(s, b ? Boolean.TRUE : Boolean.FALSE, b2 ? Boolean.TRUE : Boolean.FALSE);
        }
        catch (Throwable t) {
            $M4.print(t);
        }
    }
    
    public String getDescription() {
        return this.$APC;
    }
    
    public String getName() {
        return this.name;
    }
    
    public void removePropertyChangeListener(final PropertyChangeListener propertyChangeListener) {
        this.pcs.removePropertyChangeListener(propertyChangeListener);
    }
    
    public void setDescription(final String $apc) {
        try {
            this.firePropertyChange("desc", this.$APC, this.$APC = $apc);
        }
        catch (Throwable t) {
            $M4.print(t);
        }
    }
    
    public void setName(final String name) {
        try {
            final String name2 = this.name;
            this.name = name;
            this.pcs.firePropertyChange("name", name2, name);
        }
        catch (Throwable t) {
            $M4.print(t);
        }
    }
    
    public void setSource(final Object source) {
        this.source = source;
        this.pcs = new PropertyChangeSupport(source);
    }
}
