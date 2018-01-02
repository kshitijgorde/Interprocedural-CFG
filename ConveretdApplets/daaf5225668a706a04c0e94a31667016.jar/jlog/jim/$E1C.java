// 
// Decompiled by Procyon v0.5.30
// 

package jlog.jim;

import java.net.MalformedURLException;
import java.net.URL;
import jlog.awt.text.$UOB;
import jlog.awt.text.$WOB;

class $E1C extends $WOB
{
    $H0B $SHC;
    
    public void $APB(final $UOB $uob) {
        try {
            final URL url = new URL($uob.$VOB());
            String $l1C = this.$SHC.$L1C;
            if ($l1C == null) {
                $l1C = "_empty";
            }
            this.$SHC.showDocument.showDocument(url, $l1C);
        }
        catch (MalformedURLException ex) {}
    }
    
    $E1C(final $H0B $shc) {
        this.$SHC = $shc;
    }
}
