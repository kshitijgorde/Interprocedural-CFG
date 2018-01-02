// 
// Decompiled by Procyon v0.5.30
// 

package jlog.jim;

import java.awt.event.MouseEvent;
import java.awt.event.MouseAdapter;

class $UMC extends MouseAdapter
{
    static $F8B $ZMC;
    $F8B $ZDC;
    $H0B $SHC;
    
    static {
        $UMC.$ZMC = null;
    }
    
    $UMC(final $F8B $zdc, final $H0B $shc) {
        this.$SHC = $shc;
        this.$ZDC = $zdc;
    }
    
    public void mouseEntered(final MouseEvent mouseEvent) {
        if (this.$ZDC != null) {
            this.$SHC.$YJC(this.$ZDC.getDescription());
        }
    }
    
    public void mouseExited(final MouseEvent mouseEvent) {
        this.$SHC.$YJC("");
    }
    
    public void mouseReleased(final MouseEvent mouseEvent) {
        if ($UMC.$ZMC == this.$ZDC || (mouseEvent.getModifiers() & 0x4) == 0x4) {
            if (this.$ZDC != null && (this.$ZDC.$CKC() & 0x1) != 0x0) {
                this.$ZDC.$TJC(this.$ZDC.$EKC ^ true);
            }
        }
        else if ((mouseEvent.getModifiers() & 0x10) == 0x10) {
            $UMC.$ZMC = this.$ZDC;
        }
    }
}
