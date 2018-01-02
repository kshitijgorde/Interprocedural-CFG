// 
// Decompiled by Procyon v0.5.30
// 

package jlog.$YHC;

import java.util.List;
import jlog.$BI.$I4;
import java.util.Vector;
import java.util.Enumeration;
import java.util.Hashtable;

public class $ZOC
{
    Hashtable keys;
    boolean $FSD;
    
    public Enumeration $XJC() {
        final Vector vector = new Vector<Object>();
        final Enumeration keys = this.getKeys();
        while (keys.hasMoreElements()) {
            final Enumeration $xjc = this.$XJC(keys.nextElement());
            while ($xjc.hasMoreElements()) {
                final Object nextElement = $xjc.nextElement();
                if (!vector.contains(nextElement)) {
                    vector.addElement(nextElement);
                }
            }
        }
        return vector.elements();
    }
    
    public Enumeration $XJC(final Object o) {
        $I4.$QQ(o, "KeySet.getValues: key==null");
        final Vector<Object> value = this.keys.get(o);
        List<E> list;
        if (value != null && !(value instanceof Vector)) {
            list = (List<E>)new Vector<Object>();
            ((Vector<Vector<Object>>)list).addElement(value);
        }
        else {
            list = (List<E>)value;
        }
        if (list == null) {
            return new Vector().elements();
        }
        return ((Vector<Object>)list).elements();
    }
    
    public $ZOC(final boolean $fsd) {
        this.$FSD = $fsd;
        this.keys = new Hashtable();
    }
    
    public boolean add(final Object o, final Object o2) {
        $I4.$QQ(o, "KeySet.add: key==null");
        $I4.$QQ(o2, "KeySet.add: value==null");
        final Vector<Object> value = this.keys.get(o);
        if (value == null) {
            this.keys.put(o, o2);
            return true;
        }
        Vector<Object> vector;
        if (!(value instanceof Vector)) {
            vector = new Vector<Object>();
            vector.addElement(value);
            this.keys.put(o, vector);
        }
        else {
            vector = value;
        }
        boolean b = false;
        if (this.$FSD || !vector.contains(o2)) {
            b = (vector.size() == 0);
            vector.addElement(o2);
        }
        return b;
    }
    
    public Object getKey(final Object o) {
        $I4.$QQ(o, "KeySet.getKey: value==null");
        final Enumeration<Object> keys = this.keys.keys();
        while (keys.hasMoreElements()) {
            final Object nextElement = keys.nextElement();
            final Object value = this.keys.get(nextElement);
            if (value == o || (value instanceof Vector && ((Vector)value).contains(o))) {
                return nextElement;
            }
        }
        return null;
    }
    
    public Enumeration getKeys() {
        return ((Hashtable)this.keys.clone()).keys();
    }
    
    public Object remove(final Object o) {
        $I4.$QQ(o, "KeySet.remove: key==null");
        return this.keys.remove(o);
    }
    
    public boolean remove(final Object o, final Object o2) {
        $I4.$QQ(o2, "KeySet.remove: value==null");
        $I4.$QQ(o, "KeySet.remove: key==null");
        final Vector<Object> value = this.keys.get(o);
        if (value == null) {
            throw new RuntimeException("no such key:" + o);
        }
        if (!(value instanceof Vector)) {
            if (o2 != value) {
                throw new RuntimeException("no such pair:" + o + "=" + o2);
            }
            this.keys.remove(o);
            return true;
        }
        else {
            final Vector<Object> vector = value;
            if (!vector.removeElement(o2)) {
                throw new RuntimeException("no such pair:" + o + " = " + o2);
            }
            final int size = vector.size();
            if (size == 0) {
                this.keys.remove(o);
                return true;
            }
            if (size == 1) {
                this.keys.put(o, vector.elementAt(0));
            }
            return false;
        }
    }
    
    public int size() {
        return this.keys.size();
    }
}
