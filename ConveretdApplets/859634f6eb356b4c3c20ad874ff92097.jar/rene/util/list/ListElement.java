// 
// Decompiled by Procyon v0.5.30
// 

package rene.util.list;

public class ListElement
{
    ListElement Next;
    ListElement Previous;
    Object Content;
    ListClass L;
    
    public ListElement(final Object content) {
        this.Content = content;
        final ListElement listElement = null;
        this.Previous = listElement;
        this.Next = listElement;
        this.L = null;
    }
    
    public Object content() {
        return this.Content;
    }
    
    public ListElement next() {
        return this.Next;
    }
    
    public ListElement previous() {
        return this.Previous;
    }
    
    public void list(final ListClass l) {
        this.L = l;
    }
    
    public void content(final Object content) {
        this.Content = content;
    }
    
    public void next(final ListElement next) {
        this.Next = next;
    }
    
    public void previous(final ListElement previous) {
        this.Previous = previous;
    }
    
    public ListClass list() {
        return this.L;
    }
}
