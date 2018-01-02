// 
// Decompiled by Procyon v0.5.30
// 

package jlog.awt.text;

import java.awt.Color;
import java.awt.Component;
import java.awt.LayoutManager;
import java.awt.BorderLayout;
import java.awt.Image;
import java.awt.Container;
import jlog.awt.$DPB.$EPB;
import jlog.awt.image.$ZKB;

public class $BPB
{
    $ZKB $QLB;
    $AOB $CPB;
    $EPB $FPB;
    
    public $AOB $FOB() {
        return this.$CPB;
    }
    
    public $ZKB $GPB() {
        return this.$QLB;
    }
    
    public $BPB(final Container container, final String s, final Image image) {
        container.setLayout(new BorderLayout());
        this.$QLB = new $ZKB(image);
        this.$CPB = new $AOB(s, 400);
        (this.$FPB = new $EPB(new BorderLayout())).$PHB(true);
        this.$FPB.add("North", this.$QLB);
        container.add("West", this.$FPB);
        container.add("Center", this.$CPB);
    }
    
    public String getText() {
        return this.$CPB.getText();
    }
    
    public void setImage(final Image image, final Color background) {
        this.$QLB.setImage(image);
        this.$FPB.setBackground(background);
    }
    
    public void setText(final String text) {
        this.$CPB.setText(text);
    }
}
