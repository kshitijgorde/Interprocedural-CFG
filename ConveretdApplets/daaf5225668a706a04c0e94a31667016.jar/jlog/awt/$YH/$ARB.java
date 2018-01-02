// 
// Decompiled by Procyon v0.5.30
// 

package jlog.awt.$YH;

import java.awt.event.WindowListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowAdapter;
import java.awt.LayoutManager;
import java.awt.BorderLayout;
import java.awt.Container;
import jlog.awt.$UHB.$CRB;
import java.awt.Color;
import java.awt.Frame;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.Button;
import java.awt.Panel;
import java.awt.Dialog;

public class $ARB extends Dialog implements $SQB
{
    $WQB $BRB;
    $UQB $VQB;
    boolean $FRB;
    
    void $DRB() {
        final Panel panel = new Panel();
        final Button button = new Button("OK");
        button.addActionListener(new ActionListener() {
            public void actionPerformed(final ActionEvent actionEvent) {
                if ($ARB.this.$VQB != null) {
                    $ARB.this.$VQB.$ERB = $ARB.this.$BRB.getColor();
                    $ARB.this.$VQB.setColor($ARB.this.$BRB.getColor());
                }
                $ARB.this.setVisible(false);
            }
        });
        panel.add(button);
        this.add("South", panel);
    }
    
    public void $TQB(final $UQB $vqb) {
        this.$VQB = $vqb;
        this.setColor($vqb.getColor(), $vqb.getLabel());
        if (!this.$FRB) {
            this.$FRB = true;
            this.pack();
        }
        this.setVisible(true);
        this.toFront();
        this.requestFocus();
    }
    
    public $ARB(final Frame frame, final boolean b) {
        super(frame, b);
        this.$VQB = null;
        this.$FRB = false;
        this.setBackground(Color.lightGray);
        final $CRB $crb = new $CRB();
        this.$BRB = new $WQB($crb);
        this.setLayout(new BorderLayout(20, 20));
        this.add("Center", $crb);
        this.$DRB();
        this.addWindowListener(new WindowAdapter() {
            public void windowClosing(final WindowEvent windowEvent) {
                $ARB.this.setVisible(false);
            }
        });
    }
    
    public void setColor(Color white, final String title) {
        if (white == null) {
            white = Color.white;
        }
        if (title != null) {
            this.setTitle(title);
        }
        this.$BRB.setColor(white);
    }
}
