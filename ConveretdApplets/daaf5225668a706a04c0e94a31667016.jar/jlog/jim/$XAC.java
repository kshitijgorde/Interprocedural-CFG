// 
// Decompiled by Procyon v0.5.30
// 

package jlog.jim;

import jlog.io.$EBC;

class $XAC implements $I7B
{
    $YAC $ZAC;
    
    boolean $ABC() {
        final boolean b = true;
        final $BBC $cbc = this.$ZAC.$CBC;
        if ($cbc == null || $cbc.$DBC == null) {
            return b;
        }
        return b & $EBC.$FBC($cbc.$DBC);
    }
    
    public boolean $J7B() throws Exception {
        return this.$ABC();
    }
    
    $XAC(final $YAC $zac) {
        this.$ZAC = $zac;
    }
}
