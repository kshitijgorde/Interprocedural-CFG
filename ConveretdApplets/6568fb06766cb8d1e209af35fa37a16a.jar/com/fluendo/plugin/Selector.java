// 
// Decompiled by Procyon v0.5.30
// 

package com.fluendo.plugin;

import com.fluendo.utils.Debug;
import com.fluendo.jst.Buffer;
import com.fluendo.jst.Event;
import com.fluendo.jst.Pad;
import java.util.Vector;
import com.fluendo.jst.Element;

public class Selector extends Element
{
    private Vector sinks;
    int selected;
    Pad selectedPad;
    private Pad srcPad;
    
    private int findPad(final Pad pad) {
        for (int i = 0; i < this.sinks.size(); ++i) {
            if (this.sinks.elementAt(i) == pad) {
                return i;
            }
        }
        return -1;
    }
    
    public Pad requestSinkPad(final Pad pad) {
        final Pad pad2 = new Pad(2, "sink" + this.sinks.size()) {
            protected boolean eventFunc(final Event event) {
                return Selector.this.selectedPad != this || Selector.this.srcPad.pushEvent(event);
            }
            
            protected int chainFunc(final Buffer buffer) {
                int push = 0;
                Debug.debug("Selector got " + buffer.caps + " buffer on " + this.toString());
                if (Selector.this.selectedPad == this) {
                    Debug.debug("what a coincidence, we're selected - pushing");
                    push = Selector.this.srcPad.push(buffer);
                }
                return push;
            }
            
            protected boolean activateFunc(final int n) {
                return true;
            }
        };
        this.sinks.addElement(pad2);
        this.addPad(pad2);
        return pad2;
    }
    
    public Selector() {
        this.sinks = new Vector();
        this.selected = -1;
        this.selectedPad = null;
        this.addPad(this.srcPad = new Pad(1, "src") {
            protected boolean eventFunc(final Event event) {
                boolean b = true;
                for (int i = 0; i < Selector.this.sinks.size(); ++i) {
                    b &= ((Pad)Selector.this.sinks.elementAt(i)).pushEvent(event);
                }
                return b;
            }
        });
    }
    
    public boolean setProperty(final String s, final java.lang.Object o) {
        if (s.equals("selected")) {
            final int intValue = Integer.valueOf(o.toString());
            Debug.info("Selector: request to select " + intValue + " (from " + this.selected + "), within 0-" + (this.sinks.size() - 1));
            if (intValue < 0 || intValue >= this.sinks.size()) {
                this.selected = -1;
                this.selectedPad = null;
            }
            else {
                this.selected = intValue;
                this.selectedPad = this.sinks.elementAt(this.selected);
            }
            return true;
        }
        return super.setProperty(s, o);
    }
    
    public java.lang.Object getProperty(final String s) {
        if (s.equals("selected")) {
            return new Integer(this.selected);
        }
        return super.getProperty(s);
    }
    
    public String getFactoryName() {
        return "selector";
    }
}
