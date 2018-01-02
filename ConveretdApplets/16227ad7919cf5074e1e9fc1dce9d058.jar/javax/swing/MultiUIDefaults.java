// 
// Decompiled by Procyon v0.5.30
// 

package javax.swing;

import java.util.Enumeration;

class MultiUIDefaults extends UIDefaults
{
    private UIDefaults[] tables;
    
    public MultiUIDefaults() {
        this.tables = new UIDefaults[0];
    }
    
    public MultiUIDefaults(final UIDefaults[] tables) {
        this.tables = tables;
    }
    
    public void clear() {
        super.clear();
        for (int i = 0; i < this.tables.length; ++i) {
            final UIDefaults uiDefaults = this.tables[i];
            if (uiDefaults != null) {
                uiDefaults.clear();
            }
        }
    }
    
    public Enumeration elements() {
        final Enumeration[] array = new Enumeration[1 + this.tables.length];
        array[0] = super.elements();
        for (int i = 0; i < this.tables.length; ++i) {
            final UIDefaults uiDefaults = this.tables[i];
            if (uiDefaults != null) {
                array[i + 1] = uiDefaults.elements();
            }
        }
        return new MultiUIDefaultsEnumerator(array);
    }
    
    public Object get(final Object o) {
        final Object value = super.get(o);
        if (value != null) {
            return value;
        }
        for (int i = 0; i < this.tables.length; ++i) {
            final UIDefaults uiDefaults = this.tables[i];
            final Object o2 = (uiDefaults != null) ? uiDefaults.get(o) : null;
            if (o2 != null) {
                return o2;
            }
        }
        return null;
    }
    
    public boolean isEmpty() {
        return this.size() == 0;
    }
    
    public Enumeration keys() {
        final Enumeration[] array = new Enumeration[1 + this.tables.length];
        array[0] = super.keys();
        for (int i = 0; i < this.tables.length; ++i) {
            final UIDefaults uiDefaults = this.tables[i];
            if (uiDefaults != null) {
                array[i + 1] = uiDefaults.keys();
            }
        }
        return new MultiUIDefaultsEnumerator(array);
    }
    
    public Object remove(final Object o) {
        final Object remove = super.remove(o);
        if (remove != null) {
            return remove;
        }
        for (int i = 0; i < this.tables.length; ++i) {
            final UIDefaults uiDefaults = this.tables[i];
            final Object o2 = (uiDefaults != null) ? uiDefaults.remove(o) : null;
            if (o2 != null) {
                return o2;
            }
        }
        return null;
    }
    
    public int size() {
        int size = super.size();
        for (int i = 0; i < this.tables.length; ++i) {
            final UIDefaults uiDefaults = this.tables[i];
            size += ((uiDefaults != null) ? uiDefaults.size() : 0);
        }
        return size;
    }
    
    private static class MultiUIDefaultsEnumerator implements Enumeration
    {
        Enumeration[] enums;
        int n;
        
        MultiUIDefaultsEnumerator(final Enumeration[] enums) {
            this.n = 0;
            this.enums = enums;
        }
        
        public boolean hasMoreElements() {
            for (int i = this.n; i < this.enums.length; ++i) {
                final Enumeration enumeration = this.enums[i];
                if (enumeration != null && enumeration.hasMoreElements()) {
                    return true;
                }
            }
            return false;
        }
        
        public Object nextElement() {
            while (this.n < this.enums.length) {
                final Enumeration enumeration = this.enums[this.n];
                if (enumeration != null && enumeration.hasMoreElements()) {
                    return enumeration.nextElement();
                }
                ++this.n;
            }
            return null;
        }
    }
}
