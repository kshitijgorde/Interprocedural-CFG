// 
// Decompiled by Procyon v0.5.30
// 

package rene.util.list;

public class ListClass
{
    ListElement First;
    ListElement Last;
    
    public ListClass() {
        this.First = null;
        this.Last = null;
    }
    
    public void append(final ListElement last) {
        if (this.Last == null) {
            this.init(last);
            return;
        }
        this.Last.next(last);
        last.previous(this.Last);
        (this.Last = last).next(null);
        last.list(this);
    }
    
    public void prepend(final ListElement first) {
        if (this.First == null) {
            this.init(first);
            return;
        }
        this.First.previous(first);
        first.next(this.First);
        (this.First = first).previous(null);
        first.list(this);
    }
    
    public void insert(final ListElement listElement, final ListElement listElement2) {
        if (listElement2 == this.Last) {
            this.append(listElement);
            return;
        }
        if (listElement2 == null) {
            this.prepend(listElement);
            return;
        }
        listElement2.next().previous(listElement);
        listElement.next(listElement2.next());
        listElement2.next(listElement);
        listElement.previous(listElement2);
        listElement.list(this);
    }
    
    public void init(final ListElement listElement) {
        this.First = listElement;
        (this.Last = listElement).previous(null);
        listElement.next(null);
        listElement.list(this);
    }
    
    public void remove(final ListElement listElement) {
        if (this.First == listElement) {
            this.First = listElement.next();
            if (this.First != null) {
                this.First.previous(null);
            }
            else {
                this.Last = null;
            }
        }
        else if (this.Last == listElement) {
            this.Last = listElement.previous();
            if (this.Last != null) {
                this.Last.next(null);
            }
            else {
                this.First = null;
            }
        }
        else {
            listElement.previous().next(listElement.next());
            listElement.next().previous(listElement.previous());
        }
        listElement.next(null);
        listElement.previous(null);
        listElement.list(null);
    }
    
    public void removeall() {
        this.First = null;
        this.Last = null;
    }
    
    public void removeAfter(final ListElement last) {
        last.next(null);
        this.Last = last;
    }
    
    public ListElement first() {
        return this.First;
    }
    
    public ListElement last() {
        return this.Last;
    }
}
