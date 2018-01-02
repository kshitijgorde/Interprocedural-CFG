// 
// Decompiled by Procyon v0.5.30
// 

package webphone;

import java.awt.event.InputMethodEvent;
import java.awt.event.InputMethodListener;

class bq implements InputMethodListener
{
    private webphone a;
    
    bq(final webphone a) {
        this.a = a;
    }
    
    public void inputMethodTextChanged(final InputMethodEvent inputMethodEvent) {
    }
    
    public void caretPositionChanged(final InputMethodEvent inputMethodEvent) {
        this.a.jSlider1_caretPositionChanged(inputMethodEvent);
    }
}
