// 
// Decompiled by Procyon v0.5.30
// 

package edu.davidson.display;

import java.awt.event.TextEvent;
import java.awt.event.TextListener;

class SNumber_this_textAdapter implements TextListener
{
    SNumber adaptee;
    
    SNumber_this_textAdapter(final SNumber adaptee) {
        this.adaptee = adaptee;
    }
    
    public void textValueChanged(final TextEvent textEvent) {
        this.adaptee.this_textValueChanged(textEvent);
    }
}
