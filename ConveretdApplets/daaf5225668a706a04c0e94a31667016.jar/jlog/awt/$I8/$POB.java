// 
// Decompiled by Procyon v0.5.30
// 

package jlog.awt.$I8;

import java.awt.Dialog;

class $POB extends Thread
{
    Dialog $QOB;
    
    $POB(final Dialog $qob) {
        this.$QOB = $qob;
    }
    
    public void run() {
        this.$QOB.setVisible(true);
    }
}
