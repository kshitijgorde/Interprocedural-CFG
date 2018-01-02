// 
// Decompiled by Procyon v0.5.30
// 

package jlog.jim;

import java.beans.PropertyChangeEvent;
import java.awt.event.MouseListener;
import java.awt.Component;
import java.awt.event.ActionListener;
import jlog.awt.$B8.$GJB;
import jlog.util.$F;
import java.util.Enumeration;
import jlog.util.$MB;
import java.util.Vector;
import java.util.Hashtable;
import jlog.$H4;
import java.beans.PropertyChangeListener;
import jlog.awt.$B8.$BJB;

public class $LMC extends $BJB implements PropertyChangeListener, $H4, $DCC
{
    static boolean $NJC;
    static boolean $OJC;
    Hashtable $MMC;
    Vector $NMC;
    $XHC $LKC;
    $BBC $CBC;
    $H0B $SHC;
    $MB $TEC;
    Hashtable $RMC;
    
    public void $CIC(final $BBC $cbc) {
        if (this.$CBC == $cbc) {
            return;
        }
        if (this.$CBC != null) {
            this.$LKC.removePropertyChangeListener(this);
            this.$CBC.removePropertyChangeListener(this);
            this.$LKC = null;
            final Enumeration<$MJC> elements = this.$MMC.elements();
            while (elements.hasMoreElements()) {
                this.$OMC(elements.nextElement());
            }
        }
        this.$CBC = $cbc;
        if (this.$CBC != null) {
            this.$LKC = this.$CBC.$LKC;
            final Enumeration $hk = this.$LKC.$HK();
            while ($hk.hasMoreElements()) {
                this.$PMC($hk.nextElement());
            }
            this.$QMC(this.$CBC.getMode());
            this.$CBC.addPropertyChangeListener(this, false);
            this.$LKC.addPropertyChangeListener(this, false);
        }
    }
    
    private void $OMC(final $MJC $mjc) {
        this.$TEC.$OB($mjc);
        this.$MMC.remove($mjc.$ZDC);
        final $GJB $gjb = this.$RMC.get($mjc.$ZDC);
        this.$RMC.remove($mjc.$ZDC);
        this.removeItem($gjb);
        this.$NMC.removeElement($mjc);
        $mjc.$AEC(null);
    }
    
    private void $PMC(final $F8B $f8B) {
        if ($f8B.getName().equals("ALL_AREAS")) {
            $f8B.$SMC($f8B.getIndex() & 0xFFFFFFFE);
        }
        final int index = $f8B.getIndex();
        final int $ckc = $f8B.$CKC();
        final $MJC $mjc = new $MJC($f8B);
        this.addActionListener($mjc);
        this.$TEC.$NB($mjc);
        $mjc.$G(this.$TEC.$RB());
        this.$MMC.put($f8B, $mjc);
        final $GJB addItem = this.addItem($f8B.getName(), $mjc);
        addItem.addMouseListener(new $UMC($f8B, this.$SHC));
        final $VDC component = new $VDC($f8B);
        component.setFont(this.$SHC.$VMC.getFont());
        addItem.setComponent(component);
        if ($f8B.getName().equals("ALL_AREAS")) {
            addItem.setLabel(this.$SHC.$TEC.getString("ALL_AREAS"));
        }
        this.$RMC.put($f8B, addItem);
        if ((index & 0x1) == 0x0 && $f8B.$UJC().getMode() == 0) {
            this.$NMC.addElement($mjc);
            $f8B.$TJC(($ckc & 0x2) != 0x0);
            this.removeItem(addItem);
        }
        if (this.$SHC != null) {
            this.$SHC.$WMC($mjc);
        }
    }
    
    void $QMC(final int n) {
        if (n == 0) {
            final Enumeration<$F8B> keys = this.$MMC.keys();
            while (keys.hasMoreElements()) {
                final $F8B $f8B = keys.nextElement();
                if (($f8B.getIndex() & 0x1) == 0x0) {
                    final $MJC $mjc = this.$MMC.get($f8B);
                    this.removeItem(($GJB)this.$RMC.get($mjc.$ZDC));
                    this.$NMC.addElement($mjc);
                }
                $f8B.$TJC(($f8B.$CKC() & 0x2) != 0x0);
            }
            final String $ymc = this.$CBC.$YMC;
            if ($ymc != null) {
                this.select($ymc);
            }
        }
        else {
            while (!this.$NMC.isEmpty()) {
                final $MJC $mjc2 = this.$NMC.elementAt(0);
                this.$NMC.removeElementAt(0);
                this.addItem(($GJB)this.$RMC.get($mjc2.$ZDC), $mjc2);
            }
        }
        this.validate();
    }
    
    private void $XMC(final $F8B $f8B) {
        final $MJC $mjc = this.$MMC.get($f8B);
        this.$MMC.remove($f8B);
        this.$TEC.$OB($mjc);
        final $GJB $gjb = this.$RMC.get($f8B);
        if (($f8B.getIndex() & 0x1) == 0x0 && $f8B.$UJC().getMode() == 0) {
            this.$NMC.removeElement($mjc);
        }
        else if ($gjb != null) {
            this.removeItem($gjb);
            this.validate();
        }
        if ($gjb != null) {
            this.$RMC.remove($gjb);
        }
        this.removeActionListener($mjc);
        $mjc.$AEC(null);
    }
    
    static {
        $LMC.$NJC = false;
        $LMC.$OJC = false;
    }
    
    public $LMC(final $BBC $bbc, final $H0B $shc) {
        this.$MMC = new Hashtable();
        this.$NMC = new Vector();
        this.$LKC = null;
        this.$CBC = null;
        this.$SHC = null;
        this.$TEC = null;
        this.$RMC = new Hashtable();
        this.$SHC = $shc;
        this.$TEC = $shc.$TEC;
        this.$CIC($bbc);
    }
    
    public void propertyChange(final PropertyChangeEvent propertyChangeEvent) {
        final Object newValue = propertyChangeEvent.getNewValue();
        final String propertyName = propertyChangeEvent.getPropertyName();
        if (propertyName.equals("+RL_RUB")) {
            this.$PMC(($F8B)newValue);
        }
        else if (propertyName.equals("-RL_RUB")) {
            this.$XMC(($F8B)newValue);
        }
        else if (propertyName.equals("mode")) {
            this.$QMC((int)newValue);
        }
    }
}
