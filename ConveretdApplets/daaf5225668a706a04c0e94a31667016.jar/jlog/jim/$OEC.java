// 
// Decompiled by Procyon v0.5.30
// 

package jlog.jim;

import jlog.awt.image.$ZKB;
import java.awt.Color;
import java.awt.LayoutManager;
import jlog.awt.$DPB.$EPB;
import java.awt.BorderLayout;
import jlog.$BI.$M4;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.Button;
import java.awt.Container;
import jlog.$T5.$D7.$SEC;
import java.util.ResourceBundle;
import java.awt.Component;
import jlog.awt.$I8.$YNB;

class $OEC extends $YNB implements $I7B, $W5B
{
    $YAC $ZAC;
    Component $PEC;
    
    public void $G(final ResourceBundle resourceBundle) {
        $SEC.setLanguage(this, resourceBundle);
    }
    
    public boolean $J7B() throws Exception {
        if (!this.$ZAC.$KE()) {
            return this.$ZAC.$UEC();
        }
        this.setText();
        this.$PEC.requestFocus();
        this.$OOB();
        return true;
    }
    
    void $QEC(final Container container) {
        container.removeAll();
        final Button button = new Button(this.$ZAC.$TEC.getString("OK, save map!"));
        button.addActionListener(new ActionListener() {
            public void actionPerformed(final ActionEvent actionEvent) {
                $OEC.this.setVisible(false);
                try {
                    $OEC.this.$ZAC.$VEC();
                }
                catch (Exception ex) {
                    $M4.print(ex);
                }
            }
        });
        container.add(button);
        final Button button2 = new Button(this.$ZAC.$TEC.getString("No, load!"));
        button2.addActionListener(new ActionListener() {
            public void actionPerformed(final ActionEvent actionEvent) {
                $OEC.this.setVisible(false);
                try {
                    $OEC.this.$ZAC.$UEC();
                }
                catch (Exception ex) {
                    $M4.print(ex);
                }
            }
        });
        container.add(button2);
        final Component component = this.$PEC = new Button("Cancel");
        ((Button)component).addActionListener(new ActionListener() {
            public void actionPerformed(final ActionEvent actionEvent) {
                $OEC.this.setVisible(false);
            }
        });
        container.add(component);
    }
    
    $OEC(final $YAC $zac) {
        super($zac.getFrame(), "", 400);
        this.$ZAC = $zac;
        this.$QEC(this.$COB());
        this.setText();
        try {
            final $EPB $epb = new $EPB(new BorderLayout());
            $epb.$PHB(true);
            $epb.setBackground(Color.white);
            $epb.add("North", new $ZKB($zac.$REC()));
            this.add("West", $epb);
        }
        catch (Exception ex) {
            $M4.print(ex);
        }
    }
    
    void setText() {
        this.$G(this.$ZAC.$RB());
        String name = "";
        if (this.$ZAC.$CBC != null) {
            name = this.$ZAC.$CBC.getName();
        }
        this.setText(this.$ZAC.$TEC.getMessage("SAVE_MAP {0}", name));
    }
}
