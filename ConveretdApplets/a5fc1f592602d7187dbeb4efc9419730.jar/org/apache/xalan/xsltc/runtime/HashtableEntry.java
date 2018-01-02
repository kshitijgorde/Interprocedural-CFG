// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.xalan.xsltc.runtime;

class HashtableEntry
{
    int hash;
    Object key;
    Object value;
    HashtableEntry next;
    
    protected Object clone() {
        final HashtableEntry entry = new HashtableEntry();
        entry.hash = this.hash;
        entry.key = this.key;
        entry.value = this.value;
        entry.next = ((this.next != null) ? ((HashtableEntry)this.next.clone()) : null);
        return entry;
    }
}
