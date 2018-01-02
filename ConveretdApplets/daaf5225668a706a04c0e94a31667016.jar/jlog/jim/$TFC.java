// 
// Decompiled by Procyon v0.5.30
// 

package jlog.jim;

import java.awt.Container;
import jlog.util.$MB;
import java.util.ResourceBundle;
import jlog.$BI.$M4;
import java.awt.event.ActionEvent;
import java.awt.LayoutManager;
import java.awt.FlowLayout;
import java.awt.Color;
import jlog.awt.$Z7.$XAB;
import java.awt.Component;
import jlog.awt.$Z7.$FBB;
import jlog.$T5.$D7.$ZAC.$HJC;
import jlog.awt.$Z7.$NBB;
import jlog.awt.$Z7.$QCB;
import jlog.$T5.$D7.$ZAC.$OIC;
import java.awt.event.ActionListener;
import jlog.awt.$UHB.$CRB;

public class $TFC extends $CRB implements $W5B, $V0B, ActionListener, $OIC
{
    $YAC $ZAC;
    $QCB $PIC;
    $QCB $QIC;
    $QCB $RIC;
    $QCB $SIC;
    $QCB $TIC;
    $QCB $UIC;
    $QCB $VIC;
    $QCB $WIC;
    $QCB $XIC;
    $QCB $YIC;
    $NBB $CJC;
    
    public void $AJC(final $YAC $zac) {
        if (this.$ZAC != null && this.$ZAC.$PFC != null) {
            this.$ZAC.$PFC.$DJC(this);
        }
        this.$ZAC = $zac;
        if (this.$ZAC != null && this.$ZAC.$PFC != null) {
            this.$ZAC.$PFC.$WFC(this);
        }
    }
    
    public void $BJC() {
        if (this.$ZAC == null) {
            return;
        }
        final String $rj = this.$ZAC.$RJ;
        if ($rj.equals("BOX")) {
            this.$UIC.setState(true);
        }
        else if ($rj.equals("CIRCLE")) {
            this.$VIC.setState(true);
        }
        else if ($rj.equals("POLY")) {
            this.$WIC.setState(true);
        }
        else if ($rj.equals("SELECT")) {
            this.$TIC.setState(true);
        }
        else if ($rj.equals("TEST")) {
            this.$SIC.setState(true);
        }
        boolean hasMoreElements = false;
        if (this.$ZAC.$PFC != null) {
            hasMoreElements = this.$ZAC.$PFC.getSelected().hasMoreElements();
        }
        final boolean equals = $rj.equals("TEST");
        this.$XIC.setEnabled(hasMoreElements && !equals);
        this.$YIC.setEnabled(hasMoreElements && !equals);
    }
    
    public void $GJC(final $HJC $hjc) {
        this.$BJC();
    }
    
    void $ZIC(final $YAC $yac) {
        this.add(new $FBB(60, true));
        this.add(new $FBB(60, true));
        final $XAB $xab = new $XAB();
        $xab.$CCB = new Color(14737632);
        $xab.$CBB = true;
        $xab.$GCB = 6;
        this.$CJC = new $NBB();
        $xab.$ECB = this.$CJC;
        $xab.$ZAB = $yac.$PD.$PC("jlog/jim/buttons/run.gif");
        this.add(this.$SIC = new $QCB("TEST", "TEST", $xab));
        this.$SIC.addActionListener(this);
        this.add(new $FBB(60));
        $xab.$ZAB = $yac.$PD.$PC("jlog/jim/buttons/select.gif");
        this.add(this.$TIC = new $QCB("SELECT", "SELECT", $xab));
        this.$TIC.addActionListener(this);
        $xab.$ZAB = $yac.$PD.$PC("jlog/jim/buttons/rectangle.gif");
        this.add(this.$UIC = new $QCB("BOX", "BOX", $xab));
        this.$UIC.addActionListener(this);
        $xab.$ZAB = $yac.$PD.$PC("jlog/jim/buttons/circle.gif");
        this.add(this.$VIC = new $QCB("CIRCLE", "CIRCLE", $xab));
        this.$VIC.addActionListener(this);
        $xab.$ZAB = $yac.$PD.$PC("jlog/jim/buttons/polygon.gif");
        this.add(this.$WIC = new $QCB("POLY", "POLY", $xab));
        this.$WIC.addActionListener(this);
        this.add(new $FBB(60));
        $xab.$ECB = null;
        $xab.$ZAB = $yac.$PD.$PC("jlog/jim/buttons/clone.gif");
        this.add(this.$XIC = new $QCB("CLONE", "CLONE", $xab));
        this.$XIC.addActionListener(this);
        $xab.$ZAB = $yac.$PD.$PC("jlog/jim/buttons/delete.gif");
        this.add(this.$YIC = new $QCB("DELETE", "DELETE", $xab));
        this.$YIC.addActionListener(this);
        this.add(new $FBB(60, false));
        $xab.$ZAB = $yac.$PD.$PC("jlog/jim/buttons/attributes.gif");
        this.add(this.$QIC = new $QCB("ATTRIBUTES_BUTTON", "ATTRIBUTES_BUTTON", $xab));
        this.$QIC.addActionListener(this);
        this.add(new $FBB(60));
        $xab.$ZAB = $yac.$PD.$PC("jlog/jim/buttons/new.gif");
        this.add(this.$PIC = new $QCB("MAP_ASSISTENT", "MAP_ASSISTENT", $xab));
        this.$PIC.addActionListener(this);
        $xab.$ZAB = $yac.$PD.$PC("jlog/jim/buttons/save.gif");
        this.add(this.$RIC = new $QCB("SAVE", "SAVE", $xab));
        this.$RIC.addActionListener(this);
    }
    
    public $TFC(final $YAC $yac) {
        super(new FlowLayout(0, 2, 2));
        this.$ZIC($yac);
        this.$AJC($yac);
        this.$BJC();
    }
    
    public void actionPerformed(final ActionEvent actionEvent) {
        try {
            final String actionCommand = actionEvent.getActionCommand();
            if (actionCommand.equals("SAVE")) {
                this.$ZAC.$IGC();
            }
            else if (actionCommand.equals("MAP_ASSISTENT")) {
                this.$ZAC.$IJC();
            }
            else if (actionCommand.equals("ATTRIBUTES_BUTTON")) {
                this.$ZAC.$JJC();
            }
            if (actionCommand.equals("DELETE")) {
                this.$ZAC.delete();
            }
            else if (actionCommand.equals("CLONE")) {
                this.$ZAC.$KJC();
            }
            else if (actionCommand.equals("BOX") || actionCommand.equals("CIRCLE") || actionCommand.equals("POLY") || actionCommand.equals("SELECT") || actionCommand.equals("TEST")) {
                this.$ZAC.$LJC(actionCommand);
            }
        }
        catch (Exception ex) {
            $M4.print(ex);
        }
    }
    
    public void setLanguage(final ResourceBundle resourceBundle) {
        if (resourceBundle == null) {
            return;
        }
        final $MB $tec = this.$ZAC.$TEC;
        this.$RIC.setLabel($tec.getString("SAVE"));
        this.$PIC.setLabel($tec.getString("MAP_ASSISTENT"));
        this.$SIC.setLabel($tec.getString("TEST"));
        this.$TIC.setLabel($tec.getString("SELECT"));
        this.$UIC.setLabel($tec.getString("BOX"));
        this.$VIC.setLabel($tec.getString("CIRCLE"));
        this.$WIC.setLabel($tec.getString("POLY"));
        this.$QIC.setLabel($tec.getString("ATTRIBUTES_BUTTON"));
        this.$XIC.setLabel($tec.getString("CLONE"));
        this.$YIC.setLabel($tec.getString("DELETE"));
        final Container parent = this.getParent();
        if (parent != null) {
            parent.validate();
        }
    }
}
