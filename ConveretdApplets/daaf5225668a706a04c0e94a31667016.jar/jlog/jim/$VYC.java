// 
// Decompiled by Procyon v0.5.30
// 

package jlog.jim;

import java.awt.event.ActionEvent;
import java.awt.Button;
import java.awt.Component;
import java.awt.Label;
import java.awt.LayoutManager;
import java.awt.GridLayout;
import java.awt.Window;
import jlog.awt.$I8.$J8;
import java.awt.TextField;
import java.awt.Dialog;
import java.awt.event.ActionListener;

class $VYC implements $I7B, $W5B, $ADC, ActionListener
{
    $YAC $ZAC;
    Dialog $QOB;
    TextField $C4C;
    TextField $D4C;
    
    private void $E4C() {
        this.$QOB.pack();
        this.$C4C.requestFocus();
        $J8.$K8(this.$QOB);
        this.$QOB.show();
    }
    
    private void $F4C() {
        final boolean equalsIgnoreCase = this.$D4C.getText().trim().equalsIgnoreCase($G4C.$H4C(this.$C4C.getText().trim()));
        this.$ZAC.$JY = equalsIgnoreCase;
        this.$ZAC.$UF.$AF("XOR_MASK", equalsIgnoreCase);
    }
    
    public boolean $J7B() throws Exception {
        if (!this.$ZAC.$JY) {
            this.$E4C();
            this.$ZAC.$DHC();
        }
        return true;
    }
    
    $VYC(final $YAC $zac) {
        this.$ZAC = $zac;
        (this.$QOB = new Dialog($zac.getFrame(), true)).setLayout(new GridLayout(3, 2));
        this.$QOB.add(new Label("Name:"));
        this.$QOB.add(this.$C4C = new TextField(15));
        this.$QOB.add(new Label("Code:"));
        this.$QOB.add(this.$D4C = new TextField(15));
        this.$D4C.setEchoCharacter('*');
        final Button button;
        this.$QOB.add(button = new Button("OK"));
        this.$D4C.addActionListener(this);
        button.addActionListener(this);
    }
    
    public void actionPerformed(final ActionEvent actionEvent) {
        this.$F4C();
        this.$QOB.hide();
    }
}
