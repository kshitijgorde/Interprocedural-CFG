// 
// Decompiled by Procyon v0.5.30
// 

package jlog.awt.$I$B;

import jlog.util.$IC;
import jlog.util.$BC;
import java.awt.Color;
import java.net.URL;
import java.awt.Image;

class $A_B implements $K$B
{
    Image image;
    boolean $H_B;
    URL $VC;
    int $Q6;
    Color $G1;
    $BC $G_B;
    $T$B $L_B;
    
    $A_B(final String s, final $T$B $l_B) throws Exception {
        this.$H_B = false;
        this.$VC = null;
        this.$G1 = null;
        this.$G_B = null;
        this.$L_B = $l_B;
        final $IC $ic = new $IC(s, ',');
        final String nextToken = $ic.nextToken(null);
        if (nextToken == null) {
            throw new Exception("missing image in " + s);
        }
        this.image = $l_B.$U$B.$PC(nextToken);
        if (this.image == null) {
            throw new Exception("missing image " + nextToken);
        }
        final String nextToken2 = $ic.nextToken(null);
        if (nextToken2 != null) {
            this.$VC = new URL(nextToken2);
        }
        final String nextToken3 = $ic.nextToken(null);
        if (nextToken3 != null) {
            this.$Q6 = Integer.parseInt(nextToken3);
        }
        else {
            this.$Q6 = $l_B.$V$B("BANNER_DEFAULT_DURATION", 2000);
        }
        this.$G_B = new $BC($ic.nextToken(null));
        final String nextToken4 = $ic.nextToken($l_B.$U$B.getProperty("BANNER_DEFAULT_BG", null));
        if (nextToken4 != null) {
            this.$G1 = new Color(Integer.parseInt(nextToken4.substring(1), 16));
        }
    }
    
    public void dispose() {
        this.flush();
    }
    
    void flush() {
        if (this.image != null) {
            this.image.flush();
            this.image = null;
            this.$H_B = false;
        }
    }
    
    public String toString() {
        return String.valueOf(super.toString()) + " image=" + this.image + " url=" + this.$VC + " dateRange=" + this.$G_B;
    }
}
