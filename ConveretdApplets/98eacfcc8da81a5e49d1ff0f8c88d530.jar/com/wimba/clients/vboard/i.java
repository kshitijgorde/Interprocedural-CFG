// 
// Decompiled by Procyon v0.5.30
// 

package com.wimba.clients.vboard;

import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

final class i implements DocumentListener
{
    private final f a;
    
    i(final f a) {
        this.a = a;
    }
    
    public final void insertUpdate(final DocumentEvent documentEvent) {
        f.a(this.a, true);
    }
    
    public final void removeUpdate(final DocumentEvent documentEvent) {
        f.a(this.a, true);
    }
    
    public final void changedUpdate(final DocumentEvent documentEvent) {
        f.a(this.a, true);
    }
}
