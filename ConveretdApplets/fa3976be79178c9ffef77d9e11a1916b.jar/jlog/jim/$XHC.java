// 
// Decompiled by Procyon v0.5.30
// 

package jlog.jim;

import java.beans.PropertyChangeListener;
import jlog.$BI.$M4;
import java.awt.Image;
import java.net.URL;
import java.awt.Color;
import java.util.StringTokenizer;
import java.util.Enumeration;
import java.util.Vector;
import jlog.$H4;
import jlog.$T5.util.$U5;

public class $XHC extends $U5 implements $H4
{
    public static final String $AIC = "+RL_RUB";
    public static final String $BIC = "-RL_RUB";
    Vector $CI;
    $BBC $CBC;
    
    public void $CIC(final $BBC $cbc) {
        final $BBC $cbc2 = this.$CBC;
        if ($cbc2 == $cbc) {
            return;
        }
        if (this.$CBC != null) {
            final Enumeration $hk = this.$HK();
            while ($hk.hasMoreElements()) {
                this.$LIC($hk.nextElement());
            }
        }
        this.firePropertyChange("map", $cbc2, this.$CBC = $cbc);
    }
    
    public void $DIC(final $EIC $eic, final String s) {
        final StringTokenizer stringTokenizer = new StringTokenizer("ALL_AREAS;" + s, ";", false);
        while (stringTokenizer.hasMoreElements()) {
            final String trim = ((String)stringTokenizer.nextElement()).trim();
            if (trim.length() != 0) {
                $F8B $iic = this.$IIC(trim);
                if ($iic == null) {
                    $iic = new $F8B(trim, "", "", null, null, null);
                    this.$JIC($iic);
                }
                $iic.$DIC($eic);
                $eic.$JIC($iic);
            }
        }
    }
    
    public Enumeration $HK() {
        return ((Vector)this.$CI.clone()).elements();
    }
    
    public $F8B $IIC(final String s) {
        final Enumeration<$F8B> elements = this.$CI.elements();
        while (elements.hasMoreElements()) {
            final $F8B $f8B = elements.nextElement();
            if ($f8B.getName().equals(s)) {
                return $f8B;
            }
        }
        return null;
    }
    
    public void $JIC(final $F8B $f8B) {
        if (this.$IIC($f8B.getName()) != null) {
            throw new RuntimeException("Attributname bereits gesetzt.");
        }
        if ($f8B.getColor() == null) {
            $f8B.setColor(Color.lightGray);
        }
        this.$CI.addElement($f8B);
        $f8B.$CIC(this.$CBC);
        this.firePropertyChange("+RL_RUB", $f8B);
    }
    
    public void $KIC(final $EIC $eic) {
        final Enumeration $hk = $eic.$HK();
        while ($hk.hasMoreElements()) {
            final $F8B $f8B = $hk.nextElement();
            try {
                $eic.$LIC($f8B);
            }
            catch (Exception ex) {
                $M4.print(ex);
            }
            try {
                $f8B.$KIC($eic);
            }
            catch (Exception ex2) {
                $M4.print(ex2);
            }
        }
    }
    
    public void $LIC(final $F8B $f8B) {
        final Enumeration $mic = $f8B.$MIC();
        while ($mic.hasMoreElements()) {
            final $EIC $eic = $mic.nextElement();
            try {
                $eic.$LIC($f8B);
            }
            catch (Exception ex) {
                $M4.print(ex);
            }
            try {
                $f8B.$KIC($eic);
            }
            catch (Exception ex2) {
                $M4.print(ex2);
            }
        }
        this.$CI.removeElement($f8B);
        $f8B.$CIC(null);
        this.firePropertyChange("-RL_RUB", $f8B);
    }
    
    public Enumeration $NIC() {
        final Vector<String> vector = new Vector<String>();
        final Enumeration $hk = this.$HK();
        while ($hk.hasMoreElements()) {
            vector.addElement($hk.nextElement().getName());
        }
        return vector.elements();
    }
    
    public $XHC(final $BBC $bbc) {
        this.$CBC = null;
        this.setSource(this);
        this.$CI = new Vector();
        this.$CIC($bbc);
    }
    
    public void addPropertyChangeListener(final PropertyChangeListener propertyChangeListener, final boolean b) {
        super.addPropertyChangeListener(propertyChangeListener, b);
        if (!b) {
            return;
        }
        final $U5 $u5 = new $U5(this);
        $u5.addPropertyChangeListener(propertyChangeListener, false);
        $u5.firePropertyChange("map", this.$CBC);
        $u5.firePropertyChange("+RL_RUB", this.$HK());
        $u5.removePropertyChangeListener(propertyChangeListener);
    }
    
    void destroy() {
    }
    
    public boolean isEmpty() {
        return this.$CI.isEmpty();
    }
}
