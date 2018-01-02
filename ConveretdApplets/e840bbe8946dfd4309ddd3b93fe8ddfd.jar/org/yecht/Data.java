// 
// Decompiled by Procyon v0.5.30
// 

package org.yecht;

import java.util.Arrays;

public abstract class Data
{
    public abstract Data copy();
    
    public static class Map extends Data
    {
        public MapStyle style;
        public Object[] keys;
        public Object[] values;
        public int capa;
        public int idx;
        
        public String toString() {
            return "{idx=" + this.idx + ", capa=" + this.capa + ", keys=" + Arrays.asList(this.keys) + ", values=" + Arrays.asList(this.values) + "}";
        }
        
        public Map copy() {
            final Map m = new Map();
            m.style = this.style;
            m.keys = new Object[this.keys.length];
            System.arraycopy(this.keys, 0, m.keys, 0, this.keys.length);
            m.values = new Object[this.values.length];
            System.arraycopy(this.values, 0, m.values, 0, this.values.length);
            m.capa = this.capa;
            m.idx = this.idx;
            return m;
        }
    }
    
    public static class Seq extends Data
    {
        public SeqStyle style;
        public Object[] items;
        public int capa;
        public int idx;
        
        public String toString() {
            return "[idx=" + this.idx + ", capa=" + this.capa + ", items=" + Arrays.asList(this.items) + "]";
        }
        
        public Seq copy() {
            final Seq m = new Seq();
            m.style = this.style;
            m.items = new Object[this.items.length];
            System.arraycopy(this.items, 0, m.items, 0, this.items.length);
            m.capa = this.capa;
            m.idx = this.idx;
            return m;
        }
    }
    
    public static class Str extends Data
    {
        public ScalarStyle style;
        public Pointer ptr;
        public int len;
        
        public String toString() {
            return "\"" + new String(this.ptr.buffer, this.ptr.start, this.len) + "\"";
        }
        
        public Str copy() {
            final Str m = new Str();
            m.ptr = Pointer.create(this.ptr.buffer, this.ptr.start);
            m.len = this.len;
            return m;
        }
    }
}
