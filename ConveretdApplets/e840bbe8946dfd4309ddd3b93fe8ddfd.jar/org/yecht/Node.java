// 
// Decompiled by Procyon v0.5.30
// 

package org.yecht;

public class Node
{
    public Object id;
    public KindTag kind;
    public String type_id;
    public String anchor;
    public Data data;
    public Object shortcut;
    
    Node() {
        this.id = null;
    }
    
    public String toString() {
        if (this.kind != null) {
            switch (this.kind) {
                case Str: {
                    return "Str[id=" + this.id + ", type=" + this.type_id + ", val=" + this.data + "]";
                }
                case Seq: {
                    return "Seq[id=" + this.id + ", type=" + this.type_id + ", val=" + this.data + "]";
                }
                case Map: {
                    return "Map[id=" + this.id + ", type=" + this.type_id + ", val=" + this.data + "]";
                }
            }
        }
        return "other[id=" + this.id + ", type=" + this.type_id + ", val=" + this.data + "]";
    }
    
    public void replaceStr(final Pointer str, final int len, final ScalarStyle style) {
        final Data.Str s = (Data.Str)this.data;
        s.ptr = Pointer.create(new byte[len], 0);
        s.len = len;
        s.style = style;
        str.memcpy(s.ptr, len);
    }
    
    public void strBlowAwayCommas() {
        final Data.Str d = (Data.Str)this.data;
        final byte[] buf = d.ptr.buffer;
        for (int go = d.ptr.start, end = go + d.len; go < end; ++go) {
            if (buf[go] == 44) {
                final Data.Str str = d;
                --str.len;
                --end;
                System.arraycopy(buf, go + 1, buf, go, end - go);
            }
        }
    }
    
    public Pointer strRead() {
        return ((Data.Str)this.data).ptr;
    }
    
    public void mapEmpty() {
        final Data.Map m = (Data.Map)this.data;
        m.idx = 0;
        m.capa = 8;
        m.keys = new Object[m.capa];
        m.values = new Object[m.capa];
    }
    
    public void mapAdd(final Object key, final Object value) {
        final Data.Map m = (Data.Map)this.data;
        final int idx = m.idx;
        final Data.Map map = m;
        ++map.idx;
        if (m.idx > m.capa) {
            final Data.Map map2 = m;
            map2.capa += 8;
            m.keys = YAML.realloc(m.keys, m.capa);
            m.values = YAML.realloc(m.values, m.capa);
        }
        m.keys[idx] = key;
        m.values[idx] = value;
    }
    
    public void mapUpdate(final Node map2) {
        final Data.Map m1 = (Data.Map)this.data;
        final Data.Map m2 = (Data.Map)map2.data;
        if (m2.idx < 1) {
            return;
        }
        int new_idx;
        int new_capa;
        for (new_idx = m1.idx, new_idx += m2.idx, new_capa = m1.capa; new_idx > new_capa; new_capa += 8) {}
        if (new_capa > m1.capa) {
            m1.capa = new_capa;
            m1.keys = YAML.realloc(m1.keys, m1.capa);
            m1.values = YAML.realloc(m1.values, m1.capa);
        }
        for (new_idx = 0; new_idx < m2.idx; ++new_idx) {
            m1.keys[m1.idx] = m2.keys[new_idx];
            m1.values[m1.idx] = m2.values[new_idx];
            final Data.Map map3 = m1;
            ++map3.idx;
        }
    }
    
    public long mapCount() {
        return ((Data.Map)this.data).idx;
    }
    
    public void mapAssign(final MapPart p, final int idx, final Object id) {
        final Data.Map m = (Data.Map)this.data;
        if (p == MapPart.Key) {
            m.keys[idx] = id;
        }
        else {
            m.values[idx] = id;
        }
    }
    
    public Object mapRead(final MapPart p, final int idx) {
        final Data.Map m = (Data.Map)this.data;
        if (p == MapPart.Key) {
            return m.keys[idx];
        }
        return m.values[idx];
    }
    
    public void seqEmpty() {
        final Data.Seq s = (Data.Seq)this.data;
        s.idx = 0;
        s.capa = 8;
        s.items = new Object[s.capa];
    }
    
    public void seqAdd(final Object value) {
        final Data.Seq s = (Data.Seq)this.data;
        final int idx = s.idx;
        final Data.Seq seq = s;
        ++seq.idx;
        if (s.idx > s.capa) {
            final Data.Seq seq2 = s;
            seq2.capa += 8;
            s.items = YAML.realloc(s.items, s.capa);
        }
        s.items[idx] = value;
    }
    
    public int seqCount() {
        return ((Data.Seq)this.data).idx;
    }
    
    public void seqAssign(final int idx, final Object id) {
        ((Data.Seq)this.data).items[idx] = id;
    }
    
    public Object seqRead(final int idx) {
        return ((Data.Seq)this.data).items[idx];
    }
    
    public static Node allocMap() {
        final Data.Map m = new Data.Map();
        m.style = MapStyle.None;
        m.idx = 0;
        m.capa = 8;
        m.keys = new Object[m.capa];
        m.values = new Object[m.capa];
        final Node n = KindTag.Map.allocNode();
        n.data = m;
        return n;
    }
    
    public static Node allocSeq() {
        final Data.Seq s = new Data.Seq();
        s.style = SeqStyle.None;
        s.idx = 0;
        s.capa = 8;
        s.items = new Object[s.capa];
        final Node n = KindTag.Seq.allocNode();
        n.data = s;
        return n;
    }
    
    public static Node allocStr() {
        final Data.Str s = new Data.Str();
        s.style = ScalarStyle.None;
        s.ptr = Pointer.nullPointer();
        s.len = 0;
        final Node n = KindTag.Str.allocNode();
        n.data = s;
        return n;
    }
    
    public static Node newStr(final Pointer str, final int len, final ScalarStyle style) {
        final Node n = allocStr();
        final Data.Str s = (Data.Str)n.data;
        s.ptr = Pointer.create(new byte[len], 0);
        s.len = len;
        s.style = style;
        str.memcpy(s.ptr, len);
        return n;
    }
    
    public static Node newMap(final Object key, final Object value) {
        final Node n = allocMap();
        n.mapAdd(key, value);
        return n;
    }
    
    public static Node newSeq(final Object value) {
        final Node n = allocSeq();
        n.seqAdd(value);
        return n;
    }
}
