// 
// Decompiled by Procyon v0.5.30
// 

package jlog.jim;

import java.awt.Window;
import jlog.$BI.$M4;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

class $J1C implements ActionListener, Runnable
{
    $H0B $SHC;
    Object source;
    
    $J1C(final $H0B $shc) {
        this.$SHC = $shc;
    }
    
    public void actionPerformed(final ActionEvent actionEvent) {
        this.source = actionEvent.getSource();
        new Thread(this).start();
    }
    
    public void run() {
        try {
            if (this.source == this.$SHC.$H1C) {
                Thread.currentThread();
                Thread.sleep(500L);
                final Window window = this.$SHC.$CTB.getWindow();
                window.requestFocus();
                window.toFront();
                window.show();
                window.requestFocus();
            }
            else {
                this.$SHC.$CTB.$DTB(false);
                $Y_C.$VHC(this.$SHC);
            }
        }
        catch (Exception ex) {
            $M4.print(ex);
        }
    }
}
