// 
// Decompiled by Procyon v0.5.30
// 

package anon.terms;

import java.util.Vector;

public class TCComposite extends TCComponent
{
    protected Vector tcComponents;
    
    public TCComposite() {
        this.tcComponents = new Vector();
    }
    
    public TCComposite(final double n, final Object o) {
        super(n, o);
        this.tcComponents = new Vector();
    }
    
    public void addTCComponent(final TCComponent tcComponent) {
        for (int i = 0; i < this.tcComponents.size(); ++i) {
            final TCComponent tcComponent2 = this.tcComponents.elementAt(i);
            if (tcComponent2.getId() == tcComponent.getId()) {
                this.tcComponents.removeElementAt(i);
                this.tcComponents.insertElementAt(tcComponent, i);
                return;
            }
            if (tcComponent2.getId() > tcComponent.getId()) {
                this.tcComponents.insertElementAt(tcComponent, i);
                return;
            }
        }
        this.tcComponents.addElement(tcComponent);
    }
    
    public void removeTCComponent(final double n) {
        for (int i = 0; i < this.tcComponents.size(); ++i) {
            if (((TCComponent)this.tcComponents.elementAt(i)).getId() == n) {
                this.tcComponents.removeElementAt(i);
            }
        }
    }
    
    public int getTCComponentCount() {
        return this.tcComponents.size();
    }
    
    public TCComponent[] getTCComponents() {
        final TCComponent[] array = new TCComponent[this.tcComponents.size()];
        for (int i = 0; i < this.tcComponents.size(); ++i) {
            array[i] = (TCComponent)this.tcComponents.elementAt(i);
        }
        return array;
    }
    
    public TCComponent getTCComponent(final double n) {
        for (int i = 0; i < this.tcComponents.size(); ++i) {
            final TCComponent tcComponent = this.tcComponents.elementAt(i);
            if (tcComponent.getId() == n) {
                return tcComponent;
            }
        }
        return null;
    }
    
    public boolean hasContent() {
        return super.hasContent() || this.getTCComponentCount() > 0;
    }
    
    public String toString() {
        return this.getClass() + "@" + super.id + ": " + this.tcComponents.toString();
    }
    
    public Object clone() {
        TCComposite tcComposite = null;
        try {
            tcComposite = (TCComposite)this.getClass().newInstance();
        }
        catch (InstantiationException ex) {}
        catch (IllegalAccessException ex2) {}
        if (tcComposite != null) {
            tcComposite.id = super.id;
            tcComposite.content = super.content;
            final TCComponent[] tcComponents = this.getTCComponents();
            for (int i = 0; i < tcComponents.length; ++i) {
                tcComposite.tcComponents.addElement(tcComponents[i].clone());
            }
        }
        return tcComposite;
    }
}
