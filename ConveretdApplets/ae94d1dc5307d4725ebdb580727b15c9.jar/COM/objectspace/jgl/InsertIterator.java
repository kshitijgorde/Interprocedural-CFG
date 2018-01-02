// 
// Decompiled by Procyon v0.5.30
// 

package COM.objectspace.jgl;

public class InsertIterator implements OutputIterator
{
    Container my_container;
    
    public InsertIterator(final Container my_container) {
        this.my_container = my_container;
    }
    
    public InsertIterator(final InsertIterator insertIterator) {
        this.my_container = insertIterator.my_container;
    }
    
    public void put(final Object o) {
        this.my_container.add(o);
    }
    
    public void advance() {
    }
    
    public void advance(final int n) {
    }
    
    public Object clone() {
        return new InsertIterator(this);
    }
}
