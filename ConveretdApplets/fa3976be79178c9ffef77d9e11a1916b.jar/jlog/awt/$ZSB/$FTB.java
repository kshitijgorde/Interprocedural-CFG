// 
// Decompiled by Procyon v0.5.30
// 

package jlog.awt.$ZSB;

import java.awt.LayoutManager;
import java.util.Enumeration;
import java.util.NoSuchElementException;
import java.awt.Component;
import java.util.Vector;
import java.util.Hashtable;
import java.awt.Container;
import java.awt.CardLayout;

public class $FTB
{
    static final boolean DEBUG = false;
    CardLayout $RTB;
    Container $RD;
    String $QTB;
    Hashtable $STB;
    Vector $QO;
    
    public synchronized Component $A8(final String s) {
        final Component component = this.$STB.get(s);
        this.$RD.remove(component);
        this.$RD.validate();
        if (this.$STB.remove(s) == null) {
            return component;
        }
        try {
            this.$OTB(this.$STB.keys().nextElement());
        }
        catch (NoSuchElementException ex) {
            this.$QTB = "";
        }
        return component;
    }
    
    public synchronized void $F2(final String s, final Component component) {
        if (this.$STB.containsKey(s) || this.$STB.contains(component)) {
            return;
        }
        this.$RD.add(s, component);
        this.$RD.validate();
        this.$STB.put(s, component);
        if (this.$QTB.length() == 0) {
            this.$OTB(s);
        }
    }
    
    public synchronized void $OTB(final String $qtb) {
        if (this.$STB.containsKey($qtb)) {
            this.$RTB.show(this.$RD, $qtb);
            this.$QTB = $qtb;
            if (this.$QO != null) {
                final Enumeration<$UTB> elements = ((Vector)this.$QO.clone()).elements();
                while (elements.hasMoreElements()) {
                    elements.nextElement().$VTB(this, $qtb);
                }
            }
        }
        else {
            debug("Card " + $qtb + " not found.");
        }
    }
    
    public Enumeration $TTB() {
        return this.$STB.keys();
    }
    
    public String $WTB() {
        return this.$QTB;
    }
    
    public void $XTB(final $UTB $utb) {
        if (this.$QO == null) {
            this.$QO = new Vector();
        }
        this.$QO.addElement($utb);
    }
    
    public void $YTB(final $UTB $utb) {
        if (this.$QO != null) {
            this.$QO.removeElement($utb);
        }
    }
    
    public $FTB(final Container $rd) {
        this.$QTB = "";
        this.$QO = null;
        (this.$RD = $rd).setLayout(this.$RTB = new CardLayout());
        this.$STB = new Hashtable();
    }
    
    static final void debug(final String s) {
        System.err.println("jlog.awt.flip:" + s);
        System.err.flush();
    }
}
