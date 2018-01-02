// 
// Decompiled by Procyon v0.5.30
// 

package javax.management;

import java.util.Collection;
import java.util.ArrayList;

public class AttributeList extends ArrayList
{
    private static final long serialVersionUID = -4077085769279709076L;
    
    public AttributeList() {
    }
    
    public AttributeList(final int initialCapacity) {
        super(initialCapacity);
    }
    
    public AttributeList(final AttributeList list) {
        super(list);
    }
    
    public void add(final Attribute object) {
        super.add(object);
    }
    
    public void add(final int index, final Attribute object) {
        super.add(index, object);
    }
    
    public void set(final int index, final Attribute object) {
        super.set(index, object);
    }
    
    public boolean addAll(final AttributeList list) {
        return super.addAll(list);
    }
    
    public boolean addAll(final int index, final AttributeList list) {
        return super.addAll(index, list);
    }
}
