// 
// Decompiled by Procyon v0.5.30
// 

package jlog.jim;

import jlog.$BI.$M4;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import jlog.awt.$I8.$YNB;
import java.awt.event.KeyAdapter;

class $XYC extends KeyAdapter implements $I7B, $W5B
{
    $YAC $ZAC;
    $YNB $YGC;
    
    void $HHC(final String text) {
        this.$YGC.setText(text);
        this.$YGC.pack();
        this.$YGC.requestFocus();
        this.$YGC.show();
    }
    
    public boolean $J7B() throws Exception {
        this.$HHC(this.$ZAC.$TEC.getString("ORDER_VOLLVERSION"));
        this.$ZAC.$DHC();
        return true;
    }
    
    $XYC(final $YAC $zac) {
        this.$ZAC = $zac;
        this.$YGC = new $YNB($zac.getFrame(), "Info");
        this.$YGC.$FOB().$GQB($zac.$T_C);
        this.$YGC.addKeyListener(this);
    }
    
    public void keyReleased(final KeyEvent keyEvent) {
        if (keyEvent.getKeyCode() == 82) {
            try {
                this.$YGC.hide();
                this.$ZAC.$BYC();
            }
            catch (Exception ex) {
                $M4.print(ex);
            }
        }
    }
}
