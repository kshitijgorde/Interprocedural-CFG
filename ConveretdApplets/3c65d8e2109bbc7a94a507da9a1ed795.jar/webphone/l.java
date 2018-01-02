// 
// Decompiled by Procyon v0.5.30
// 

package webphone;

import java.awt.event.KeyEvent;
import java.awt.event.KeyAdapter;

class l extends KeyAdapter
{
    private webphone a;
    
    l(final webphone a) {
        this.a = a;
    }
    
    public void keyPressed(final KeyEvent keyEvent) {
        this.a.jComboBox2_keyPressed(keyEvent);
    }
}
