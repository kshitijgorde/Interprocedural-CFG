// 
// Decompiled by Procyon v0.5.30
// 

package edu.hws.eck.umb.palette;

import java.util.Iterator;
import javax.swing.event.ChangeListener;
import java.util.ArrayList;
import javax.swing.event.ChangeEvent;

public class PaletteMapping implements Cloneable
{
    private final ChangeEvent changeEvent;
    private ArrayList<ChangeListener> listeners;
    private int length;
    private int offset;
    
    public PaletteMapping() {
        this.changeEvent = new ChangeEvent(this);
        this.listeners = new ArrayList<ChangeListener>();
    }
    
    public PaletteMapping(final int length, final int offset) {
        this.changeEvent = new ChangeEvent(this);
        this.listeners = new ArrayList<ChangeListener>();
        this.length = length;
        this.offset = offset;
    }
    
    public int getLength() {
        return this.length;
    }
    
    public void setLength(int length) {
        if (length < 0) {
            length = 0;
        }
        if (this.length == length) {
            return;
        }
        this.length = length;
        this.changed();
    }
    
    public int getOffset() {
        return this.offset;
    }
    
    public void setOffset(final int offset) {
        if (this.offset == offset) {
            return;
        }
        this.offset = offset;
        this.changed();
    }
    
    public void addChangeListener(final ChangeListener changeListener) {
        if (!this.listeners.contains(changeListener)) {
            this.listeners.add(changeListener);
        }
    }
    
    public void removeChangeListener(final ChangeListener changeListener) {
        this.listeners.remove(changeListener);
    }
    
    public boolean equals(final Object o) {
        if (o == null || !(o instanceof PaletteMapping)) {
            return false;
        }
        final PaletteMapping paletteMapping = (PaletteMapping)o;
        return paletteMapping.length == this.length && paletteMapping.offset == this.offset;
    }
    
    public PaletteMapping clone() {
        return new PaletteMapping(this.length, this.offset);
    }
    
    private void changed() {
        final Iterator<ChangeListener> iterator = this.listeners.iterator();
        while (iterator.hasNext()) {
            iterator.next().stateChanged(this.changeEvent);
        }
    }
}
