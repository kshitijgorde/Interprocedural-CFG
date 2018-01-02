// 
// Decompiled by Procyon v0.5.30
// 

package jlog.jim;

import jlog.awt.text.$AOB;
import java.util.ResourceBundle;
import jlog.$BI.$M4;
import jlog.awt.text.$UOB;
import jlog.util.$F;
import jlog.awt.text.$WOB;

class $Y_C extends $WOB implements $F, $X5B, Runnable
{
    $H0B $SHC;
    
    public void $APB(final $UOB $uob) {
        try {
            final String $vob = $uob.$VOB();
            if ($vob.equals("ABOUT")) {
                this.$SHC.$MTC();
            }
            else if ($vob.equals("SHOW_IN_WINDOW") || $vob.equals("SHOW_IN_BROWSER")) {
                new Thread(this).start();
            }
            else if ($vob.equals("MANUAL")) {
                this.$SHC.$TUC();
            }
        }
        catch (Exception ex) {
            $M4.print(ex);
        }
    }
    
    public void $G(final ResourceBundle resourceBundle) {
        $VHC(this.$SHC);
    }
    
    static void $VHC(final $H0B $h0B) {
        final $AOB $x_C = $h0B.$X_C;
        String s = "!A! MANUAL " + $h0B.$TEC.getString("MANUAL") + " !A! ";
        if (!$h0B.$A2) {
            final String string = String.valueOf(s) + " \n \n!A! " + "SHOW_IN_BROWSER" + " ";
            String s2;
            if ($h0B.$CTB.$IUB()) {
                s2 = String.valueOf(string) + $h0B.$TEC.getString("SHOW_IN_BROWSER");
            }
            else {
                s2 = String.valueOf(string) + $h0B.$TEC.getString("SHOW_IN_WINDOW");
            }
            s = String.valueOf(s2) + " !A! ";
        }
        $x_C.setText(String.valueOf(s) + " \n \n!A! " + "ABOUT" + " " + $h0B.$TEC.getString("ABOUT") + " !A! ");
        $h0B.$F1C.setText($h0B.$TEC.getString("SAT_INFO"));
        $h0B.$I1C.setLabel($h0B.$TEC.getString("SHOW_IN_BROWSER"));
        $h0B.$H1C.setLabel($h0B.$TEC.getString("FLIP_FRONT"));
    }
    
    $Y_C(final $H0B $shc) {
        this.$SHC = $shc;
        $shc.$TEC.$NB(this);
        $VHC($shc);
    }
    
    public void run() {
        this.$SHC.$CTB.$DTB(this.$SHC.$CTB.$IUB() ^ true);
        $VHC(this.$SHC);
    }
}
