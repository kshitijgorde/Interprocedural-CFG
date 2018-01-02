// 
// Decompiled by Procyon v0.5.30
// 

package jlog.jim;

import jlog.awt.image.$ZKB;
import java.awt.Color;
import jlog.awt.$DPB.$EPB;
import jlog.awt.$B4;
import java.awt.FlowLayout;
import java.awt.Canvas;
import java.awt.GridLayout;
import java.awt.LayoutManager;
import java.awt.BorderLayout;
import jlog.awt.$L.$G9;
import java.awt.event.KeyEvent;
import jlog.awt.$L.$K2B;
import jlog.awt.$L.$I9;
import jlog.$T5.$D7.$SEC;
import java.util.ResourceBundle;
import java.util.Enumeration;
import java.awt.Container;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import jlog.$BI.$M4;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.Button;
import java.awt.List;
import java.awt.Component;
import jlog.awt.$I8.$H9;
import java.awt.Panel;

class $ANC extends Panel implements $I7B, $W5B, $V0B, $H9
{
    static final String $BNC = "CARD_JECMDEditRubrik";
    $YAC $ZAC;
    Component $PEC;
    List list;
    Button $CNC;
    Button $DNC;
    
    void $ENC() {
        (this.list = new List(9, false)).addActionListener(new ActionListener() {
            public void actionPerformed(final ActionEvent actionEvent) {
                try {
                    $ANC.this.$JJC();
                }
                catch (Exception ex) {
                    $M4.print(ex);
                }
            }
        });
        this.list.addItemListener(new ItemListener() {
            public void itemStateChanged(final ItemEvent itemEvent) {
                try {
                    $ANC.this.$CNC.setEnabled(itemEvent.getStateChange() == 1);
                    $ANC.this.$DNC.setEnabled(itemEvent.getStateChange() == 1);
                }
                catch (Exception ex) {
                    $M4.print(ex);
                }
            }
        });
        final Panel panel = new Panel();
        this.$HNC(panel);
        final Panel panel2 = new Panel();
        panel2.add("Center", this.list);
        panel2.add("East", panel);
        this.add("Center", panel2);
    }
    
    void $FNC() {
        this.list.removeAll();
        final $BBC $cbc = this.$ZAC.$CBC;
        if ($cbc != null && $cbc.$LKC != null) {
            final Enumeration $nic = $cbc.$LKC.$NIC();
            while ($nic.hasMoreElements()) {
                final String s = $nic.nextElement();
                if (!s.equals("ALL_AREAS")) {
                    this.list.addItem(s);
                }
            }
        }
        final boolean b = this.list.getItemCount() > 0 && this.list.getSelectedItem() != null;
        this.$CNC.setEnabled(b);
        this.$DNC.setEnabled(b);
    }
    
    public void $G(final ResourceBundle resourceBundle) {
        $SEC.setLanguage(this, resourceBundle);
    }
    
    void $GNC() {
        new $I9(this).$W9(new $K2B() {
            public void $Z9(final KeyEvent keyEvent) {
                if (keyEvent.getKeyCode() == 27) {
                    $ANC.this.$ZAC.$INC().$OTB("CARD_APPLICATION");
                }
            }
        });
    }
    
    void $HNC(final Container container) {
        container.setLayout(new BorderLayout());
        final Panel panel = new Panel(new GridLayout(0, 1));
        (this.$CNC = new Button(this.$ZAC.$TEC.getString("EDIT_ATTRIBUTE"))).addActionListener(new ActionListener() {
            public void actionPerformed(final ActionEvent actionEvent) {
                try {
                    $ANC.this.$JJC();
                }
                catch (Throwable t) {
                    $M4.print(t);
                }
            }
        });
        panel.add(this.$CNC);
        final Button button = new Button(this.$ZAC.$TEC.getString("RUBRIK_ADD"));
        button.addActionListener(new ActionListener() {
            public void actionPerformed(final ActionEvent actionEvent) {
                try {
                    $ANC.this.$KNC();
                }
                catch (Throwable t) {
                    $M4.print(t);
                }
            }
        });
        panel.add(button);
        (this.$DNC = new Button(this.$ZAC.$TEC.getString("RUBRIK_REMOVE"))).addActionListener(new ActionListener() {
            public void actionPerformed(final ActionEvent actionEvent) {
                try {
                    $ANC.this.$LNC();
                }
                catch (Throwable t) {
                    $M4.print(t);
                }
            }
        });
        final Canvas canvas = new Canvas();
        canvas.setSize(32, 64);
        container.add("North", panel);
        container.add("Center", canvas);
        container.add("South", this.$DNC);
    }
    
    public boolean $J7B() throws Exception {
        this.$FNC();
        this.$G(this.$ZAC.$RB());
        this.$PEC.requestFocus();
        this.$ZAC.$INC().$OTB("CARD_JECMDEditRubrik");
        return true;
    }
    
    void $JJC() {
        final String selectedItem = this.list.getSelectedItem();
        if (selectedItem == null) {
            return;
        }
        final $F8B $iic = this.$ZAC.$CBC.$IIC(selectedItem);
        if ($iic != null) {
            (($MLC)this.$ZAC.$OLC()).$AEC($iic, this.$ZAC.$CBC);
            this.$ZAC.$INC().$OTB("JERubrikDialog");
        }
    }
    
    void $KNC() {
        (($MLC)this.$ZAC.$OLC()).$AEC(null, this.$ZAC.$CBC);
        this.$ZAC.$INC().$OTB("JERubrikDialog");
    }
    
    void $LNC() {
        final String selectedItem = this.list.getSelectedItem();
        if (selectedItem == null) {
            return;
        }
        final $F8B $iic = this.$ZAC.$CBC.$IIC(selectedItem);
        if ($iic != null) {
            this.$ZAC.$CBC.$LIC($iic);
        }
        this.$FNC();
    }
    
    void $QEC() {
        final Panel panel = new Panel(new FlowLayout(1, 6, 3));
        final Component component = this.$PEC = new Button("BACK2EDITOR");
        ((Button)component).addActionListener(new ActionListener() {
            public void actionPerformed(final ActionEvent actionEvent) {
                try {
                    $ANC.this.$ZAC.$INC().$OTB("CARD_APPLICATION");
                }
                catch (Exception ex) {
                    $M4.print(ex);
                }
            }
        });
        panel.add(component);
        final Panel panel2 = new Panel(new BorderLayout());
        panel2.add("North", new $B4());
        panel2.add("Center", panel);
        this.add("South", panel2);
    }
    
    $ANC(final $YAC $zac) {
        super(new BorderLayout());
        this.$ZAC = $zac;
        this.$QEC();
        this.$ENC();
        try {
            final $EPB $epb = new $EPB(new BorderLayout());
            $epb.$PHB(true);
            $epb.setBackground(Color.white);
            $epb.add("North", new $ZKB($zac.$PD.$PC("jlog/jim/buttons/attributes.gif")));
            this.add("West", $epb);
        }
        catch (Exception ex) {
            $M4.print(ex);
        }
        this.$FNC();
        this.$GNC();
    }
}
