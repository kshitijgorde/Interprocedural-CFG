// 
// Decompiled by Procyon v0.5.30
// 

package jlog.jim;

import java.awt.Font;
import java.awt.Container;
import jlog.awt.text.$BPB;
import java.net.MalformedURLException;
import java.net.URL;
import jlog.awt.text.$UOB;
import jlog.awt.text.$XOB;
import jlog.awt.$UHB.$CRB;
import java.io.IOException;
import jlog.util.$UD.$VD;
import java.awt.Image;
import java.awt.event.WindowListener;
import jlog.awt.$I8.$GOB;
import java.awt.Component;
import java.awt.Color;
import jlog.applet.$HEC;
import jlog.util.$MC.$NC;
import jlog.util.$UD.$XD;
import java.awt.Window;
import java.awt.Frame;

class $T0B extends Frame implements $GBC
{
    public $T0B(final Window window, final $XD $xd, final $NC $nc, final $HEC $hec) throws $VD, IOException {
        this.setTitle($xd.getProperty("PROJECT_NAME", $xd.getProperty("APPLICATION_NAME")));
        final Image $pc = $nc.$PC($xd.getProperty("PROJECT_ICON", $xd.getProperty("APPLICATION_ICON")));
        if ($pc != null) {
            this.setIconImage($pc);
        }
        this.setBackground(Color.white);
        this.setForeground(Color.black);
        if ($xd.getProperty("PROJECT_NAME", null) != null) {
            this.add("North", new $LGC("PROJECT_", $xd, $nc, $hec));
        }
        this.add("Center", new $LGC("APPLICATION_", $xd, $nc, $hec));
        this.addWindowListener(new $GOB());
        this.pack();
    }
    
    class $LGC extends $CRB implements $XOB
    {
        $HEC showDocument;
        
        public void $APB(final $UOB $uob) {
            final String $vob = $uob.$VOB();
            if ($vob.indexOf(58) > 0) {
                try {
                    this.showDocument.showDocument(new URL($vob), "_empty");
                }
                catch (MalformedURLException ex) {}
            }
        }
        
        public void $YOB(final $UOB $uob) {
        }
        
        public void $ZOB(final $UOB $uob) {
        }
        
        $LGC(final String s, final $XD $xd, final $NC $nc, final $HEC showDocument) throws $VD, IOException {
            this.showDocument = showDocument;
            final Font $df = $xd.$DF("APPLICATION_FONT");
            $xd.$DF(String.valueOf(s) + "FONT", $df);
            this.setFont($df);
            String s2 = String.valueOf($xd.getProperty(new StringBuffer(String.valueOf(s)).append("NAME").toString())) + "\n ";
            final String property = $xd.getProperty(String.valueOf(s) + "VERSION", null);
            if (property != null) {
                s2 = String.valueOf(s2) + "\n" + property;
            }
            final String property2 = $xd.getProperty(String.valueOf(s) + "COPYRIGHT", null);
            if (property2 != null) {
                s2 = String.valueOf(s2) + "\n" + property2;
            }
            final String property3 = $xd.getProperty(String.valueOf(s) + "EMAIL", null);
            if (property3 != null) {
                s2 = String.valueOf(s2) + "\n \nEmail: " + property3;
            }
            final String property4 = $xd.getProperty(String.valueOf(s) + "URL", null);
            if (property4 != null) {
                s2 = String.valueOf(s2) + "\n \n" + property4;
            }
            final String property5 = $xd.getProperty(String.valueOf(s) + "ICON", null);
            Image $pc = null;
            if (property5 != null) {
                $pc = $nc.$PC(property5);
            }
            new $BPB(this, String.valueOf(s2) + "\n \n", $pc).$FOB().$GQB(this);
        }
    }
}
