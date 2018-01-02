// 
// Decompiled by Procyon v0.5.30
// 

package webphone;

import java.awt.event.KeyEvent;
import java.awt.event.KeyAdapter;

class b extends KeyAdapter
{
    private webphone a;
    
    b(final webphone a) {
        this.a = a;
    }
    
    public void keyTyped(final KeyEvent keyEvent) {
        this.a.label7_keyTyped(keyEvent);
    }
}
