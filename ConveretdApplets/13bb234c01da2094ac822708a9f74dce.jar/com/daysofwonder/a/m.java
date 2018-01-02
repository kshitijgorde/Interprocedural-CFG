// 
// Decompiled by Procyon v0.5.30
// 

package com.daysofwonder.a;

import com.daysofwonder.req.p;
import java.util.Iterator;
import com.daysofwonder.req.k;
import java.util.HashMap;
import java.util.Map;

public abstract class m implements a, Iterable
{
    private final Map a;
    
    public m() {
        this.a = new HashMap();
    }
    
    public boolean a(final String s) {
        final Boolean b = this.a.get(s);
        return b != null && b;
    }
    
    public void a(final String s, final boolean b) {
        this.a.put(s, b);
    }
    
    public Object b(final k k) {
        this.a.clear();
        for (int int1 = k.readInt(), i = 0; i < int1; ++i) {
            this.a.put(k.readUTF(), k.readBoolean());
        }
        return this;
    }
    
    public void a(final k k) {
        k.writeInt(this.a.size());
        for (final Map.Entry<String, V> entry : this.a.entrySet()) {
            k.writeUTF(entry.getKey());
            k.writeBoolean((boolean)entry.getValue());
        }
    }
    
    public int c() {
        int n = 4;
        final Iterator<Map.Entry<String, V>> iterator = this.a.entrySet().iterator();
        while (iterator.hasNext()) {
            n += p.a(iterator.next().getKey());
            ++n;
        }
        return n;
    }
    
    public boolean a() {
        return this.a.size() == 0;
    }
    
    public Iterator iterator() {
        return this.a.keySet().iterator();
    }
    
    public boolean b() {
        final Iterator<Boolean> iterator = this.a.values().iterator();
        while (iterator.hasNext()) {
            if (iterator.next()) {
                return true;
            }
        }
        return false;
    }
}
