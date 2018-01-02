// 
// Decompiled by Procyon v0.5.30
// 

package jlog.awt.$ZSB;

import jlog.awt.text.$HPB;
import java.util.Enumeration;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.Rectangle;
import java.awt.CardLayout;
import java.awt.Container;
import java.awt.Component;
import java.awt.Window;

public class $BTB extends $ETB
{
    Window $EX;
    $FTB $ZTB;
    Component $AUB;
    boolean $BUB;
    Container $CUB;
    CardLayout $DUB;
    static final String $EUB = "SAT_INFO_CARD";
    public boolean $FUB;
    Rectangle $GUB;
    
    void $DTB() {
        final String $wtb = this.$WTB();
        final boolean $ntb = super.$NTB;
        this.$OTB("SAT_INFO_CARD");
        super.$RD = this.$CUB;
        super.$RTB = this.$DUB;
        this.$LUB(this, this.$ZTB);
        super.$RD = this.$ZTB.$RD;
        super.$RTB = this.$ZTB.$RTB;
        if (this.$FUB && this.$GUB != null) {
            this.$EX.setBounds(this.$GUB);
            this.$EX.validate();
        }
        else if (!this.$EX.isVisible()) {
            this.$EX.pack();
            this.$EX.setSize(this.$CUB.getSize());
            $K8(this.$EX);
        }
        this.$OTB($wtb);
        if ($ntb) {
            this.$MTB(true);
        }
        this.$EX.setVisible(true);
        this.$EX.toFront();
        this.$BUB = true;
    }
    
    public synchronized void $DTB(final boolean b) {
        if (b == this.$BUB) {
            if (b) {
                this.$EX.toFront();
            }
            return;
        }
        if (b) {
            this.$DTB();
        }
        else {
            this.$JUB();
        }
    }
    
    public Component $HUB() {
        return this.$AUB;
    }
    
    public boolean $IUB() {
        return this.$BUB;
    }
    
    void $JUB() {
        this.$EX.setVisible(false);
        this.$GUB = this.$EX.getBounds();
        super.$RD = this.$CUB;
        super.$RTB = this.$DUB;
        this.$LUB(this.$ZTB, this);
        super.$RD.setVisible(false);
        if (super.$NTB) {
            this.$OTB(super.$QTB);
            this.$MTB(true);
        }
        else {
            this.$OTB(super.$QTB);
        }
        super.$RD.setVisible(true);
        this.$BUB = false;
    }
    
    public static void $K8(final Window window) {
        final Toolkit toolkit = window.getToolkit();
        if (toolkit != null) {
            final Dimension screenSize = toolkit.getScreenSize();
            if (screenSize != null) {
                final Dimension size = window.getSize();
                window.setLocation((screenSize.width - size.width) / 2, (screenSize.height - size.height) / 2);
            }
        }
    }
    
    void $LUB(final $FTB $ftb, final $FTB $ftb2) {
        $ftb2.$RD.setBackground($ftb.$RD.getBackground());
        $ftb2.$RD.setForeground($ftb.$RD.getForeground());
        final Enumeration $ttb = this.$TTB();
        while ($ttb.hasMoreElements()) {
            final String s = $ttb.nextElement();
            if (!s.equals("SAT_INFO_CARD")) {
                final Component component = super.$STB.get(s);
                if (component == null) {
                    continue;
                }
                $ftb.$RD.remove(component);
                $ftb2.$RD.add(s, component);
            }
        }
    }
    
    public $BTB(final Container container, final Container container2, final Window $ex, Component $aub) {
        super(container, container2);
        this.$BUB = false;
        this.$FUB = true;
        this.$GUB = null;
        this.$CUB = super.$RD;
        this.$DUB = super.$RTB;
        if ($aub == null) {
            $aub = new $HPB("Sat started", 100);
        }
        this.$F2("SAT_INFO_CARD", $aub);
        this.$AUB = $aub;
        this.$EX = $ex;
        this.$ZTB = new $FTB($ex);
        if ($ex.isVisible()) {
            $ex.setVisible(false);
            this.$DTB(true);
        }
    }
    
    public Window getWindow() {
        return this.$EX;
    }
}
