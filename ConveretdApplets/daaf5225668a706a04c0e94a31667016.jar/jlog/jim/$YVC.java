// 
// Decompiled by Procyon v0.5.30
// 

package jlog.jim;

import java.awt.GridBagConstraints;
import java.awt.Frame;
import java.awt.FlowLayout;
import jlog.awt.$B4;
import java.awt.Insets;
import jlog.awt.$G4;
import java.awt.LayoutManager;
import java.awt.BorderLayout;
import jlog.awt.image.$A6;
import java.io.IOException;
import jlog.io.$P4;
import java.net.URL;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.Container;
import jlog.awt.$L.$G9;
import jlog.$BI.$M4;
import java.awt.event.KeyEvent;
import jlog.awt.$L.$K2B;
import jlog.awt.$L.$I9;
import jlog.util.$MB;
import java.util.ResourceBundle;
import jlog.awt.$YH.$SQB;
import jlog.awt.$YH.$ARB;
import jlog.util.$XG.$YG;
import jlog.awt.$YH.$UQB;
import java.awt.TextField;
import java.awt.Label;
import java.awt.Button;
import java.awt.Component;
import jlog.awt.$I8.$H9;
import jlog.util.$F;
import java.awt.Panel;

class $YVC extends Panel implements $S6B, $F, $H9, $I7B
{
    static final String $BNC = "JEBGImageDialog";
    $YAC $ZAC;
    Component $PEC;
    $ZVC $AWC;
    $ZVC $BWC;
    Button $CWC;
    Label $DWC;
    TextField $EWC;
    $UQB $HWC;
    Label $GWC;
    
    public void $CIC(final $BBC $bbc) {
        this.$AWC.$DE = false;
        this.$BWC.$DE = false;
        this.$MWC(this.$AWC, $bbc.$QKC.getURL());
        this.$MWC(this.$BWC, $bbc.$PPC.getURL());
        this.$EWC.setText($bbc.$MPC);
        this.$HWC.setColor($bbc.$XOC);
    }
    
    void $FWC() {
        (this.$HWC = new $UQB($YG.$YH, $YG.$ZH, "user defined", "(default)", new $ARB(this.$ZAC.getFrame(), true))).setLabel("XOR Color");
        this.$GWC = new Label("XOR Color:");
    }
    
    public void $G(final ResourceBundle resourceBundle) {
        if (resourceBundle == null) {
            return;
        }
        final $MB $tec = this.$ZAC.$TEC;
        this.$AWC.$OQC.setText($tec.getString("BG_IMAGE"));
        this.$BWC.$OQC.setText($tec.getString("ZOOM_IMAGE"));
        this.$CWC.setLabel($tec.getString("ZOOM_USES_BG_IMAGE"));
        this.$DWC.setText($tec.getString("default target"));
    }
    
    void $GNC() {
        new $I9(this).$W9(new $K2B() {
            public void $Z9(final KeyEvent keyEvent) {
                if (keyEvent.getKeyCode() == 27) {
                    try {
                        $YVC.this.$ZAC.$DHC();
                    }
                    catch (Throwable t) {
                        $M4.print(t);
                    }
                }
            }
        });
    }
    
    Container $IWC() {
        final Panel panel = new Panel();
        final Button button = new Button(this.$ZAC.$TEC.getString("OK"));
        button.addActionListener(new ActionListener() {
            public void actionPerformed(final ActionEvent actionEvent) {
                try {
                    if ($YVC.this.$NWC($YVC.this.$ZAC.$CBC)) {
                        $YVC.this.$ZAC.$DHC();
                    }
                }
                catch (Exception ex) {
                    $M4.print(ex);
                }
            }
        });
        panel.add(button);
        final Button button2 = new Button(this.$ZAC.$TEC.getString("Cancel"));
        button2.addActionListener(new ActionListener() {
            public void actionPerformed(final ActionEvent actionEvent) {
                $YVC.this.$ZAC.$DHC();
            }
        });
        panel.add(button2);
        return panel;
    }
    
    public boolean $J7B() throws Exception {
        this.$CIC(this.$ZAC.$CBC);
        this.validate();
        this.$ZAC.$INC().$OTB("JEBGImageDialog");
        return true;
    }
    
    void $JWC() {
        this.$CWC.addActionListener(new ActionListener() {
            public void actionPerformed(final ActionEvent actionEvent) {
                try {
                    $YVC.this.$MWC($YVC.this.$BWC, null);
                    $YVC.this.$ZAC.$CBC.$PPC.$JT(null);
                }
                catch (Exception ex) {
                    $M4.print(ex);
                }
            }
        });
    }
    
    void $MWC(final $ZVC $zvc, final URL url) {
        String substring;
        if (url == null) {
            substring = " - ";
        }
        else {
            final String file = url.getFile();
            substring = file.substring(file.lastIndexOf(47) + 1);
        }
        $zvc.$RWC.setText(substring);
    }
    
    public boolean $NWC(final $BBC $bbc) throws IOException, $A6 {
        final URL $vpc = $bbc.$VPC();
        URL url = $bbc.$PPC.getURL();
        if (!this.$PWC()) {
            return false;
        }
        if (this.$AWC.$DE) {
            final URL url2 = new URL($vpc, this.$AWC.$ERC.getName());
            $P4.copy(this.$AWC.$CRC, url2);
            $bbc.$QKC.$JT(url2);
            this.$AWC.$DE = false;
            $bbc.$IF(true);
        }
        if (this.$BWC.$DE) {
            if (this.$BWC.$CRC != null) {
                url = new URL($vpc, this.$BWC.$ERC.getName());
                $P4.copy(this.$BWC.$CRC, url);
            }
            this.$BWC.$DE = false;
            $bbc.$IF(true);
        }
        $bbc.$PPC.$JT(url);
        return true;
    }
    
    boolean $PWC() {
        final String trim = this.$EWC.getText().trim();
        if (this.$XVC(trim)) {
            this.$EWC.requestFocus();
            return false;
        }
        if (this.$ZAC.$CBC != null) {
            this.$ZAC.$CBC.$MPC = trim;
            this.$ZAC.$CBC.$XOC = this.$HWC.getColor();
        }
        return true;
    }
    
    boolean $XVC(final String s) {
        return s.indexOf(59) != -1 || s.indexOf(44) != -1 || s.indexOf(124) != -1;
    }
    
    $YVC(final $YAC $zac) {
        super(new BorderLayout());
        this.$DWC = new Label();
        this.$EWC = new TextField();
        this.$ZAC = $zac;
        final Frame frame = $zac.getFrame();
        final Panel panel = new Panel();
        final $G4 $g4 = new $G4(panel);
        final GridBagConstraints constraints = $g4.getConstraints();
        constraints.insets = new Insets(4, 2, 4, 2);
        constraints.weightx = 1.0;
        constraints.anchor = 17;
        constraints.fill = 2;
        $g4.add(this.$AWC = new $ZVC(frame), 0, 0, 2, 1);
        $g4.add(this.$BWC = new $ZVC(frame), 0, 1, 2, 1);
        constraints.fill = 0;
        constraints.anchor = 13;
        $g4.add(this.$CWC = new Button(), 1, 2, 1, 1);
        constraints.fill = 2;
        $g4.add(new $B4(), 0, 3, 3, 1);
        constraints.anchor = 17;
        this.$FWC();
        $g4.add(this.$GWC, 0, 4, 1, 1);
        $g4.add(this.$HWC, 1, 4, 1, 1);
        constraints.fill = 2;
        $g4.add(new $B4(), 0, 5, 3, 1);
        $g4.add(this.$DWC, 0, 6, 1, 1);
        $g4.add(this.$EWC, 1, 6, 1, 1);
        final Panel panel2 = new Panel(new FlowLayout(1));
        panel2.add(panel);
        this.add("North", panel2);
        this.add("South", this.$IWC());
        this.$GNC();
        this.$G($zac.$TEC.$RB());
        $zac.$TEC.$NB(this);
        this.$JWC();
        $zac.$INC().$F2("JEBGImageDialog", this);
    }
}
