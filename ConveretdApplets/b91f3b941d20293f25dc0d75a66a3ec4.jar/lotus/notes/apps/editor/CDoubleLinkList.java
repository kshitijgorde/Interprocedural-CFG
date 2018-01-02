// 
// Decompiled by Procyon v0.5.30
// 

package lotus.notes.apps.editor;

class CDoubleLinkList
{
    private CDoubleLinkList cNext;
    private CDoubleLinkList cPrev;
    
    final CDoubleLinkList getNext() {
        return this.cNext;
    }
    
    final CDoubleLinkList getPrevious() {
        return this.cPrev;
    }
    
    final void setNext(final CDoubleLinkList cNext) {
        this.cNext = cNext;
    }
    
    final void setPrevious(final CDoubleLinkList cPrev) {
        this.cPrev = cPrev;
    }
    
    void insert(final CDoubleLinkList list) {
        final CDoubleLinkList next = this.getNext();
        list.setNext(next);
        this.setNext(list);
        list.setPrevious(this);
        if (next != null) {
            next.setPrevious(list);
        }
    }
    
    void insertBefore(final CDoubleLinkList list) {
        final CDoubleLinkList previous = this.getPrevious();
        list.setNext(this);
        this.setPrevious(list);
        list.setPrevious(previous);
        if (previous != null) {
            previous.setNext(list);
        }
    }
    
    void addToEnd(final CDoubleLinkList list) {
        CDoubleLinkList list2;
        CDoubleLinkList next;
        for (list2 = this; (next = list2.getNext()) != null; list2 = next) {}
        list2.insert(list);
    }
    
    void remove() {
        final CDoubleLinkList next = this.getNext();
        final CDoubleLinkList previous = this.getPrevious();
        if (previous != null) {
            previous.setNext(next);
        }
        if (next != null) {
            next.setPrevious(previous);
        }
        this.setNext(null);
        this.setPrevious(null);
    }
    
    void truncate() {
        if (this.cNext != null) {
            this.cNext.setPrevious(null);
            this.cNext = null;
        }
    }
    
    CDoubleLinkList getFirst() {
        CDoubleLinkList list = this;
        while (true) {
            final CDoubleLinkList previous = list.getPrevious();
            if (previous == null) {
                break;
            }
            list = previous;
        }
        return list;
    }
    
    CDoubleLinkList getLast() {
        CDoubleLinkList list = this;
        while (true) {
            final CDoubleLinkList next = list.getNext();
            if (next == null) {
                break;
            }
            list = next;
        }
        return list;
    }
    
    CDoubleLinkList insertChain(final CDoubleLinkList next) {
        final CDoubleLinkList last = next.getLast();
        if (this.cNext != null) {
            last.setNext(this.cNext);
            this.cNext.setPrevious(last);
        }
        next.setPrevious(this);
        this.setNext(next);
        return last;
    }
}
