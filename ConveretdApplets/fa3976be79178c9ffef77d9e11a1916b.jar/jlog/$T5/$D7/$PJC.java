// 
// Decompiled by Procyon v0.5.30
// 

package jlog.$T5.$D7;

import jlog.$H4;
import java.awt.List;

public class $PJC extends List implements $H4
{
    public boolean copies;
    public boolean $AKC;
    
    public $PJC() {
        this.copies = false;
        this.$AKC = true;
    }
    
    public $PJC(final int n) {
        super(n);
        this.copies = false;
        this.$AKC = true;
    }
    
    public $PJC(final int n, final boolean b) {
        super(n, b);
        this.copies = false;
        this.$AKC = true;
    }
    
    public void add(final String s) {
        if (!this.copies && this.indexOf(s) != -1) {
            return;
        }
        if (!this.$AKC) {
            super.add(s);
            return;
        }
        final String[] items = this.getItems();
        final int length = items.length;
        int n = 0;
        for (String lowerCase = s.toLowerCase(); n < length && lowerCase.compareTo(items[n].toLowerCase()) >= 1; ++n) {}
        this.add(s, n);
    }
    
    public void add(final String s, final int n) {
        super.add(s, n);
    }
    
    public void deselect(final String s) {
        final int index = this.indexOf(s);
        if (index != -1) {
            this.deselect(index);
        }
    }
    
    public void deselect(final String[] array) {
        int length = array.length;
        while (length-- != 0) {
            this.deselect(array[length]);
        }
    }
    
    public int indexOf(final String s) {
        final String[] items = this.getItems();
        int length = items.length;
        while (length-- != 0) {
            if (items[length].equals(s)) {
                return length;
            }
        }
        return -1;
    }
    
    public void remove(final String s) {
        super.remove(s);
    }
    
    public void select(final String s) {
        final int index = this.indexOf(s);
        if (index != -1) {
            this.select(index);
        }
    }
    
    public void select(final String[] array) {
        int length = array.length;
        while (length-- != 0) {
            this.select(array[length]);
        }
    }
}
