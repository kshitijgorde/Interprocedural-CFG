// 
// Decompiled by Procyon v0.5.30
// 

class qElement
{
    Object element;
    qElement next;
    qElement prev;
    
    qElement(final Object element, final qElement next, final qElement prev) {
        this.element = element;
        if (prev != null && next != null) {
            this.prev = prev;
            prev.next = this;
            this.next = next;
            return;
        }
        this.next = this;
        this.prev = this;
    }
}
