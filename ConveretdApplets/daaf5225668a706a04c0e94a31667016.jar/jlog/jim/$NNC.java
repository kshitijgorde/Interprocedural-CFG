// 
// Decompiled by Procyon v0.5.30
// 

package jlog.jim;

import jlog.$BI.$M4;
import jlog.awt.image.$ZKB;
import jlog.awt.$DPB.$EPB;
import java.awt.Color;
import java.awt.SystemColor;
import java.awt.LayoutManager;
import java.awt.BorderLayout;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.GridBagConstraints;
import jlog.awt.$B4;
import java.awt.Label;
import java.awt.Font;
import java.awt.Insets;
import jlog.awt.$G4;
import java.awt.Container;
import jlog.awt.$I8.$YNB;
import jlog.util.$MB;
import jlog.awt.text.$HPB;
import java.util.ResourceBundle;
import java.util.Enumeration;
import java.io.File;
import java.awt.Dialog;
import java.awt.Component;
import java.awt.Button;
import java.awt.List;
import jlog.util.$F;
import jlog.awt.$I8.$H9;
import java.awt.Panel;

class $NNC extends Panel implements $I7B, $X6B, $V0B, $H9, $F
{
    static final String $BNC = "JECMDMapAssistent";
    $YAC $ZAC;
    static final boolean DEBUG = false;
    List $ONC;
    Button $PNC;
    Component $QNC;
    Button $RNC;
    Button $SNC;
    Button $TNC;
    Component $PEC;
    Dialog $YGC;
    
    void $AOC() {
        this.$ONC.removeAll();
        if (!this.$ZAC.$UF.$ZE("HISTORY_DEMO", false)) {
            this.$ZAC.$UF.$AF("HISTORY_DEMO", true);
            final File file = new File(System.getProperty("user.dir"), "demo");
            if (file.exists()) {
                final String[] list = file.list();
                for (int i = 0; i < list.length; ++i) {
                    if (list[i].endsWith(".jar")) {
                        this.$ZAC.$CHC(new File(file, list[i]).getPath());
                    }
                }
            }
        }
        final Enumeration<String> elements = this.$ZAC.$JFC.elements();
        while (elements.hasMoreElements()) {
            final String s = elements.nextElement();
            if (s != null) {
                this.$ONC.addItem(s);
            }
        }
    }
    
    public void $G(final ResourceBundle resourceBundle) {
        final $MB $tec = this.$ZAC.$TEC;
        if ($tec == null) {
            return;
        }
        this.$RNC.setLabel($tec.getString("Cancel"));
        this.$PNC.setLabel($tec.getString("CMD_OPEN_HISTORY"));
        this.$TNC.setLabel($tec.getString("LOAD.."));
        this.$SNC.setLabel($tec.getString("New"));
        (($HPB)this.$QNC).setText($tec.getString("CMD_OPEN_HISTORY_TITLE"));
    }
    
    void $HHC(final String text) {
        if (this.$YGC == null) {
            this.$YGC = new $YNB(this.$ZAC.getFrame(), text);
        }
        else {
            (($YNB)this.$YGC).setText(text);
        }
        this.$ZAC.$EHC(text);
        this.$YGC.pack();
        this.$YGC.show();
        try {
            this.$J7B();
        }
        catch (Exception ex) {}
    }
    
    public boolean $J7B() throws Exception {
        this.$ZAC.$INC().$OTB("JECMDMapAssistent");
        this.$ZAC.$ZGC(false);
        this.$AOC();
        this.$PEC.requestFocus();
        return true;
    }
    
    Container $UNC() {
        this.$ZIC(this.$ONC = this.$VNC());
        this.$QNC = new $HPB(this.$ZAC.$TEC.getString("CMD_OPEN_HISTORY_TITLE"), 300);
        final Panel panel = new Panel();
        final $G4 $g4 = new $G4(panel);
        final GridBagConstraints constraints = $g4.getConstraints();
        constraints.insets = new Insets(4, 2, 4, 2);
        final Font font = new Font("Default", 1, 24);
        constraints.anchor = 10;
        constraints.weightx = 1.0;
        constraints.fill = 0;
        final Label label;
        $g4.add(label = new Label("A"), 1, 0, 1, 1);
        label.setFont(font);
        final Label label2;
        $g4.add(label2 = new Label("B"), 1, 4, 1, 1);
        label2.setFont(font);
        final Label label3;
        $g4.add(label3 = new Label("*"), 1, 6, 1, 1);
        label3.setFont(font);
        constraints.anchor = 17;
        constraints.weightx = 1.0;
        constraints.fill = 2;
        $g4.add(this.$QNC, 2, 0, 1, 1);
        $g4.add(this.$QNC, 2, 0, 1, 1);
        $g4.add(this.$ONC, 2, 1, 1, 1);
        constraints.anchor = 13;
        constraints.fill = 0;
        $g4.add(this.$PNC, 2, 2, 1, 1);
        constraints.fill = 2;
        constraints.anchor = 17;
        $g4.add(new $B4(), 1, 3, 2, 1);
        $g4.add(this.$TNC, 2, 4, 1, 1);
        $g4.add(new $B4(), 1, 5, 2, 1);
        $g4.add(this.$SNC, 2, 6, 1, 1);
        $g4.add(new $B4(), 1, 7, 2, 1);
        constraints.fill = 0;
        constraints.anchor = 13;
        $g4.add(this.$RNC, 2, 8, 1, 1);
        return panel;
    }
    
    List $VNC() {
        final List $pec = new List(7, false);
        $pec.addActionListener(new ActionListener() {
            public void actionPerformed(final ActionEvent actionEvent) {
                try {
                    new $WNC(actionEvent.getActionCommand()).start();
                }
                catch (Exception ex) {}
            }
        });
        return (List)(this.$PEC = $pec);
    }
    
    void $YNC(final String s, final Exception ex) {
        this.$HHC(this.$ZAC.$TEC.getMessage("CMD_OPEN_ERROR_LOAD", new Object[] { s, String.valueOf(ex.getMessage()) + " / " + ex.getClass().getName() }));
    }
    
    void $ZIC(final List list) {
        (this.$SNC = new Button(this.$ZAC.$TEC.getString("New"))).addActionListener(new ActionListener() {
            public void actionPerformed(final ActionEvent actionEvent) {
                new Thread() {
                    public void run() {
                        try {
                            $NNC.this.$ZAC.$XNC();
                        }
                        catch (Exception ex) {
                            $NNC.this.$YNC("", ex);
                        }
                    }
                }.start();
            }
        });
        (this.$TNC = new Button(this.$ZAC.$TEC.getString("LOAD.."))).addActionListener(new ActionListener() {
            public void actionPerformed(final ActionEvent actionEvent) {
                new Thread() {
                    public void run() {
                        try {
                            $NNC.this.$ZAC.$DFC();
                        }
                        catch (Exception ex) {
                            $NNC.this.$YNC("", ex);
                        }
                    }
                }.start();
            }
        });
        (this.$PNC = new Button(this.$ZAC.$TEC.getString("CMD_OPEN_HISTORY"))).addActionListener(new ActionListener() {
            public void actionPerformed(final ActionEvent actionEvent) {
                final String selectedItem = $NNC.this.$ONC.getSelectedItem();
                if (selectedItem != null) {
                    new $WNC(selectedItem).start();
                }
            }
        });
        this.$PNC.setEnabled(false);
        (this.$RNC = new Button(this.$ZAC.$TEC.getString("Cancel"))).addActionListener(new ActionListener() {
            public void actionPerformed(final ActionEvent actionEvent) {
                if ($NNC.this.$ZAC.$CBC != null) {
                    $NNC.this.$ZAC.$DHC();
                }
            }
        });
        list.addItemListener(new ItemListener() {
            public void itemStateChanged(final ItemEvent itemEvent) {
                $NNC.this.$PNC.setEnabled(itemEvent.getStateChange() == 1);
            }
        });
    }
    
    $NNC(final $YAC $zac) {
        super(new BorderLayout());
        this.$ZAC = $zac;
        this.setBackground(SystemColor.info);
        this.setForeground(SystemColor.infoText);
        final Panel panel = new Panel();
        panel.add(this.$UNC());
        this.$G($zac.$RB());
        $zac.$TEC.$NB(this);
        this.add("Center", panel);
        try {
            final $EPB $epb = new $EPB(new BorderLayout());
            $epb.$PHB(true);
            $epb.setBackground(Color.white);
            $epb.add("North", new $ZKB($zac.$PD.$PC("jlog/jim/buttons/new.gif")));
            this.add("West", $epb);
        }
        catch (Exception ex) {
            $M4.print(ex);
        }
    }
    
    class $WNC extends Thread
    {
        String $ID;
        
        $WNC(final String $id) {
            this.$ID = $id;
        }
        
        public void run() {
            $NNC.this.$ZAC.$ZGC(true);
            final $CFC $cfc = new $CFC($NNC.this.$ZAC);
            $cfc.$EFC = new File(this.$ID);
            try {
                if ($cfc.$FFC() != $cfc.OK) {
                    $NNC.this.$ZAC.$GFC(this.$ID);
                }
                else if ($cfc.$HFC() != $cfc.OK) {
                    $NNC.this.$ZAC.$GFC(this.$ID);
                }
            }
            catch (Exception ex) {
                $NNC.this.$ZAC.$GFC(this.$ID);
                $NNC.this.$YNC(this.$ID, ex);
            }
            finally {
                $NNC.this.$ZAC.$DHC();
                $NNC.this.$ZAC.$LJC("SELECT");
            }
        }
    }
}
