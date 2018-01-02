// 
// Decompiled by Procyon v0.5.30
// 

package jlog.jim;

import java.awt.Container;
import java.awt.PopupMenu;
import java.awt.MenuComponent;
import java.awt.event.ActionListener;
import java.awt.Menu;
import jlog.$T5.$D7.$YFC;
import java.io.File;
import java.awt.Frame;
import jlog.$T5.$D7.$ZAC.$OIC;
import java.awt.event.WindowListener;
import java.awt.Component;
import java.awt.Color;
import java.awt.SystemColor;
import jlog.$T5.$D7.$ZAC.$QFC;
import java.awt.MenuBar;
import java.util.Vector;

class $YEC extends $ZEC implements $ADC
{
    $YAC $ZAC;
    
    void $AFC() throws Exception {
        this.$ZAC.$IFC = super.$UF.getIntProperty("DOCUMENT_HISTORY_LENGTH");
        this.$ZAC.$JFC = new Vector();
        try {
            for (int i = 0; i < this.$ZAC.$IFC; ++i) {
                final String property = super.$UF.getProperty("DOCUMENT_HISTORY" + i);
                super.$UF.remove("DOCUMENT_HISTORY" + i);
                this.$ZAC.$JFC.addElement(property);
            }
        }
        catch (Exception ex) {}
    }
    
    void $LFC(final $BBC $bbc) throws Exception {
        super.$LFC($bbc);
        final Frame frame = this.$ZAC.getFrame();
        frame.setMenuBar(this.$ZAC.$MFC = new $NFC(this.$ZAC, false));
        this.$OFC();
        this.$ZAC.$PFC = new $QFC(this.$ZAC.$RFC.$P4B(), 1);
        (this.$ZAC.$SFC = new $TFC(this.$ZAC)).setLanguage(this.$ZAC.$RB());
        this.$ZAC.$SFC.setBackground(SystemColor.control);
        this.$ZAC.$SFC.setForeground(SystemColor.controlText);
        this.$ZAC.$UFC.add("North", this.$ZAC.$SFC);
        frame.addWindowListener(new $VFC(this.$ZAC));
        this.$ZAC.$PFC.$WFC(this.$ZAC);
        this.$ZAC.$PFC.$WFC(this.$ZAC.$MFC);
    }
    
    public void $O6() throws Exception {
        this.$AFC();
        super.$O6();
        this.$ZAC.$UF.$IF(false);
        if (super.$UF.$ZE("DOCUMENT_AUTOLOAD", false)) {
            final String property = super.$UF.getProperty("DOCUMENT_HISTORY0", null);
            if (property != null) {
                final $CFC $cfc = new $CFC(this.$ZAC);
                $cfc.$EFC = new File(property);
                try {
                    if ($cfc.$FFC() != $cfc.OK) {
                        this.$ZAC.$GFC(property);
                    }
                    else if ($cfc.$HFC() != $cfc.OK) {
                        this.$ZAC.$GFC(property);
                    }
                }
                catch (Exception ex) {
                    this.$ZAC.$GFC(property);
                    throw ex;
                }
            }
        }
    }
    
    private void $OFC() {
        if (this.$ZAC.$RFC == null) {
            return;
        }
        final Container $p4B = this.$ZAC.$RFC.$P4B();
        if ($p4B == null) {
            return;
        }
        if (this.$ZAC.$XFC != null) {
            $YFC.removeActionListener(this.$ZAC.$XFC, this.$ZAC.$ZFC);
            $p4B.remove(this.$ZAC.$XFC);
        }
        this.$ZAC.$ZFC = new $NFC(this.$ZAC, true);
        this.$ZAC.$XFC = new PopupMenu();
        final $NFC $zfc = this.$ZAC.$ZFC;
        final PopupMenu $xfc = this.$ZAC.$XFC;
        $xfc.add($zfc.select);
        $xfc.addSeparator();
        $xfc.add($zfc.$AGC);
        $xfc.add($zfc.$BGC);
        $xfc.add($zfc.$W_);
        $xfc.addSeparator();
        $xfc.add($zfc.clone);
        $xfc.addSeparator();
        $xfc.add($zfc.delete);
        $xfc.addSeparator();
        $xfc.add($zfc.$CGC);
        $xfc.add($zfc.$DGC);
        $xfc.addSeparator();
        $xfc.add($zfc.$X1);
        $YFC.setLanguage($xfc, this.$ZAC.$RB());
        $YFC.addActionListener($xfc, this.$ZAC.$ZFC);
        $p4B.add($xfc);
    }
    
    $YEC(final $YAC $zac) {
        super($zac);
        this.$ZAC = $zac;
    }
}
