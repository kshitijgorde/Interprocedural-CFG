// 
// Decompiled by Procyon v0.5.30
// 

package jlog.awt.$I8;

import java.awt.Window;
import java.awt.event.WindowListener;
import java.awt.Button;
import java.awt.Component;
import java.awt.Frame;
import jlog.awt.text.$AOB;

public class $YNB extends $ZNB
{
    $AOB $BOB;
    
    public $AOB $FOB() {
        return this.$BOB;
    }
    
    public $YNB(final Frame frame, final String s) {
        this(frame, s, 300);
    }
    
    public $YNB(final Frame frame, final String text, final int n) {
        super(frame);
        this.add("Center", this.$BOB = new $AOB("", n));
        this.setText(text);
        final Button button = new Button("OK");
        this.$COB().add(button);
        button.addActionListener(this.$DOB());
        this.addWindowListener(this.$EOB());
    }
    
    public void setText(String string) {
        string = String.valueOf(string) + " \n \n";
        if (!string.equals(this.$BOB.getText())) {
            this.$BOB.setText(string);
            if (this.isVisible()) {
                this.pack();
                $J8.$K8(this);
            }
        }
    }
}
