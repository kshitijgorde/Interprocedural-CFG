// 
// Decompiled by Procyon v0.5.30
// 

package ca.odell.glazedlists;

class ListSelection$DeselectionToggleList extends ListSelection$DeselectedList
{
    final /* synthetic */ ListSelection f;
    
    public Object set(final int n, final Object o) {
        throw new UnsupportedOperationException("Toggling lists don't support setting items");
    }
    
    public void add(int index, final Object o) {
        index = this.a.indexOf(o);
        if (index != -1) {
            this.f.b(index);
            return;
        }
        throw new IllegalArgumentException("Added item " + o + " must be in source list");
    }
    
    public Object remove(final int n) {
        if (n < 0 || n >= this.size()) {
            throw new IndexOutOfBoundsException("Cannot remove at " + n + " on list of size " + this.size());
        }
        final int a = this.a(n);
        this.f.c(a);
        return this.a.get(a);
    }
}
