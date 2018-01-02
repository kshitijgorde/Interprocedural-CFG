// 
// Decompiled by Procyon v0.5.30
// 

package jlog.awt.image;

import java.awt.Image;
import java.net.URL;

public class $VKB extends $WKB
{
    $Q0 $XKB;
    String $ID;
    URL $VC;
    
    public void $JT(final URL $vc) throws $A6 {
        if ($vc == this.$VC || ($vc != null && $vc.equals(this.$VC))) {
            return;
        }
        Image image;
        if ($vc == null) {
            image = null;
        }
        else {
            image = this.$XKB.createImage($vc);
        }
        super.setImage(image);
        this.$ID = null;
        this.$VC = $vc;
    }
    
    public String $P$() {
        return this.$ID;
    }
    
    public $Q0 $Y0() {
        return this.$XKB;
    }
    
    public void $YKB(final String $id) throws $A6 {
        if ($id == this.$ID || ($id != null && $id.equals(this.$ID))) {
            return;
        }
        Image image;
        if ($id == null) {
            image = null;
        }
        else {
            image = this.$XKB.createImage($id);
        }
        super.setImage(image);
        this.$VC = null;
        this.$ID = $id;
    }
    
    public $VKB(final $Q0 $xkb) {
        this.$ID = null;
        this.$VC = null;
        if ($xkb == null) {
            throw new NullPointerException("imageCreator");
        }
        this.$XKB = $xkb;
    }
    
    public $VKB(final $Q0 $q0, final String s) throws $A6 {
        this($q0);
        this.$YKB(s);
    }
    
    public $VKB(final $Q0 $q0, final URL url) throws $A6 {
        this($q0);
        this.$JT(url);
    }
    
    public URL getURL() {
        return this.$VC;
    }
    
    public void reset() {
        this.$ID = null;
        this.$VC = null;
        super.setImage(null);
    }
    
    public void setImage(final Image image) {
        throw new Error("use setSrc or setURL instead of setImage");
    }
}
