// 
// Decompiled by Procyon v0.5.30
// 

package jlog.awt.image;

import java.awt.Container;

class $ELB implements $MKB
{
    $ZKB $QLB;
    
    public void $NKB(final $OKB $okb) {
        this.$QLB.$DLB = false;
        Container parent = this.$QLB.getParent();
        this.$QLB.invalidate();
        while (true) {
            final Container parent2 = parent.getParent();
            if (parent2 == null) {
                break;
            }
            parent = parent2;
        }
        parent.validate();
        this.$QLB.repaint();
    }
    
    $ELB(final $ZKB $qlb) {
        this.$QLB = $qlb;
    }
}
