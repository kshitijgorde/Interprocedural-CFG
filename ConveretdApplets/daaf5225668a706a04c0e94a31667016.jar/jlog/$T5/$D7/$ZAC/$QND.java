// 
// Decompiled by Procyon v0.5.30
// 

package jlog.$T5.$D7.$ZAC;

import java.util.EventObject;
import java.awt.event.KeyEvent;
import jlog.awt.$L.$K2B;

class $QND extends $K2B
{
    $QFC $COD;
    
    public void $Z9(final KeyEvent keyEvent) {
        if ((keyEvent.getModifiers() & 0xA) != 0x0) {
            return;
        }
        switch (keyEvent.getKeyCode()) {
            case 127: {
                this.$COD.$TND();
                this.$COD.$SSB(new $HJC(this.$COD, 2, this.$COD.getSelected()));
                break;
            }
            case 27: {
                this.$COD.$TND();
                break;
            }
            case 32: {
                this.$COD.$YND();
                break;
            }
        }
    }
    
    $QND(final $QFC $cod) {
        this.$COD = $cod;
    }
}
