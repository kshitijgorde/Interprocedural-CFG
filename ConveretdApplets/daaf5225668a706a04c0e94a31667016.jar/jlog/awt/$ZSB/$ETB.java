// 
// Decompiled by Procyon v0.5.30
// 

package jlog.awt.$ZSB;

import java.awt.Component;
import java.awt.Container;
import java.awt.Color;
import java.awt.Image;
import jlog.awt.text.$BPB;

public class $ETB extends $FTB
{
    String infoText;
    static final String $GTB = "INFOTEXT_CARD";
    $BPB $HTB;
    private String $PTB;
    boolean $NTB;
    
    public void $JTB(final String text) {
        this.$HTB.setText(text);
    }
    
    public $BPB $KTB() {
        return this.$HTB;
    }
    
    public void $LTB(final Image image, final Color color) {
        this.$HTB.setImage(image, color);
    }
    
    public synchronized void $MTB(final boolean b) {
        if (b == this.$NTB) {
            return;
        }
        if (b) {
            this.$OTB("INFOTEXT_CARD");
        }
        else {
            this.$OTB(this.$PTB);
        }
    }
    
    public boolean $NTB() {
        return this.$NTB;
    }
    
    public synchronized void $OTB(final String s) {
        if (!(this.$NTB = "INFOTEXT_CARD".equals(s))) {
            this.$PTB = super.$QTB;
        }
        super.$OTB(s);
    }
    
    public $ETB(final Container container, final Container container2) {
        super(container);
        this.$PTB = "";
        this.$NTB = false;
        this.$HTB = new $BPB(container2, null, null);
        this.$F2("INFOTEXT_CARD", container2);
    }
    
    public String getInfoText() {
        return this.$HTB.getText();
    }
}
