// 
// Decompiled by Procyon v0.5.30
// 

package jlog.jim;

import java.beans.PropertyChangeListener;
import java.awt.Rectangle;
import jlog.awt.$V_;
import java.util.Enumeration;
import java.util.Vector;
import java.awt.Polygon;
import jlog.$Y_B.$Z_B;
import jlog.$H4;
import jlog.$T5.util.$U5;

public class $EIC extends $U5 implements $H4
{
    static final boolean debug = false;
    public static final String $JKC = "PROP_MARKED";
    public static final String $QSC = "PROP_HREF";
    public static final String $RSC = "R_ADD";
    public static final String $SSC = "R_REM";
    $Z_B $M1B;
    boolean $CEC;
    Polygon p;
    Vector $TSC;
    Vector $EKC;
    String $TOB;
    String $CP;
    int selected;
    $BBC $CBC;
    int mouse;
    int $N5;
    
    public boolean $ASC() {
        return this.$CEC;
    }
    
    public void $ATC(final Vector vector) {
        this.$BTC(vector.elements());
    }
    
    public void $BTC(final Enumeration enumeration) {
        final Vector vector = new Vector<$F8B>();
        while (enumeration.hasMoreElements()) {
            vector.addElement(enumeration.nextElement());
        }
        final Enumeration $hk = this.$HK();
        while ($hk.hasMoreElements()) {
            final $F8B $f8B = $hk.nextElement();
            if (!vector.contains($f8B)) {
                this.$LIC($f8B);
            }
        }
        while (enumeration.hasMoreElements()) {
            final $F8B $f8B2 = enumeration.nextElement();
            if (!this.$YSC($f8B2)) {
                this.$JIC($f8B2);
            }
        }
    }
    
    public void $CIC(final $BBC $cbc) {
        this.firePropertyChange("map", this.$CBC, this.$CBC = $cbc);
    }
    
    public String $EP() {
        return this.$CP;
    }
    
    public String $GSC() {
        String s = "";
        final Enumeration $hk = this.$HK();
        while ($hk.hasMoreElements()) {
            final $F8B $f8B = $hk.nextElement();
            if (!$f8B.getName().equals("ALL_AREAS")) {
                if (s.length() != 0) {
                    s = String.valueOf(s) + ";";
                }
                s = String.valueOf(s) + $f8B.getName();
            }
        }
        return s;
    }
    
    public Enumeration $HK() {
        return ((Vector)this.$TSC.clone()).elements();
    }
    
    public $F8B $IIC(final String s) {
        final Enumeration<$F8B> elements = this.$TSC.elements();
        while (elements.hasMoreElements()) {
            final $F8B $f8B = elements.nextElement();
            if ($f8B.getName().equals(s)) {
                return $f8B;
            }
        }
        return null;
    }
    
    public void $IT(final String $cp) {
        if ($cp == null || $cp.equals(this.$CP)) {
            return;
        }
        this.firePropertyChange("target", this.$CP, this.$CP = $cp);
    }
    
    public void $JCB(final Polygon p) {
        this.firePropertyChange("p", this.p, this.p = p);
    }
    
    public void $JIC(final $F8B $f8B) {
        this.$TSC.addElement($f8B);
        this.$TJC($f8B, $f8B.$EKC);
        if (!$f8B.getName().equals("ALL_AREAS")) {
            this.firePropertyChange("R_ADD", $f8B);
        }
    }
    
    public void $LIC(final $F8B $f8B) {
        this.$TJC($f8B, false);
        this.$TSC.removeElement($f8B);
        this.firePropertyChange("R_REM", $f8B);
    }
    
    public void $OBB(int selected) {
        final int selected2 = this.selected;
        if (selected < 0) {
            selected = 0;
        }
        this.selected = selected;
        if (selected2 == 0 == (selected == 0)) {
            return;
        }
        this.firePropertyChange("selected", this.selected != 0);
    }
    
    public void $OBB(final boolean b) {
        if (b) {
            this.$OBB(this.selected + 1);
        }
        else {
            this.$OBB(this.selected - 1);
        }
    }
    
    public void $TJC(final $F8B $f8B, final boolean b) {
        if (this.$WSC($f8B) == b) {
            return;
        }
        if (b) {
            this.$EKC.addElement($f8B);
        }
        else {
            this.$EKC.removeElement($f8B);
        }
        this.firePropertyChange("PROP_MARKED", $f8B, new Boolean(b));
    }
    
    public $BBC $UJC() {
        return this.$CBC;
    }
    
    public $H0B $VJC() {
        return (this.$CBC != null) ? this.$CBC.$VJC() : null;
    }
    
    public String $VOB() {
        return this.$TOB;
    }
    
    public void $VSC() {
        this.mouse = 0;
        this.$N5 = 0;
        final Enumeration<$F8B> elements = this.$TSC.elements();
        while (elements.hasMoreElements()) {
            final $F8B $f8B = elements.nextElement();
            if (!$f8B.getName().equals("ALL_AREAS")) {
                final int mouse = $f8B.getMouse();
                if ((mouse & 0x1) == 0x0 || $f8B.$EKC) {
                    this.mouse |= mouse;
                }
                this.$N5 |= $f8B.getIndex();
            }
        }
    }
    
    public boolean $WSC(final $F8B $f8B) {
        return this.$EKC.indexOf($f8B) != -1;
    }
    
    public void $XSC(final String $tob) {
        this.firePropertyChange("PROP_HREF", this.$TOB, this.$TOB = $tob);
    }
    
    public boolean $YSC(final $F8B $f8B) {
        return this.$TSC.indexOf($f8B) != -1;
    }
    
    public Vector $ZSC() {
        return (Vector)this.$TSC.clone();
    }
    
    public $EIC(final String s, final String s2, final Polygon polygon, final float n) {
        this(null, s, s2, false);
        this.p = new $V_(polygon);
        final Rectangle bounds = polygon.getBounds();
        this.$M1B = new $Z_B(bounds.x * n, bounds.y * n, bounds.width * n, bounds.height * n);
    }
    
    public $EIC(final $Z_B $z_B, final String s, final String s2, final boolean $cec) {
        super(s, s2);
        this.p = null;
        this.$TSC = new Vector();
        this.$EKC = new Vector();
        this.$TOB = "";
        this.$CP = "";
        this.selected = 0;
        this.$CBC = null;
        this.mouse = 0;
        this.$N5 = 0;
        this.setSource(this);
        if ($z_B != null) {
            this.$M1B = $z_B.create();
        }
        this.$CEC = $cec;
    }
    
    public $EIC(final $EIC $eic) {
        super($eic.getName(), $eic.getDescription());
        this.p = null;
        this.$TSC = new Vector();
        this.$EKC = new Vector();
        this.$TOB = "";
        this.$CP = "";
        this.selected = 0;
        this.$CBC = null;
        this.mouse = 0;
        this.$N5 = 0;
        this.setSource(this);
        this.$CBC = $eic.$CBC;
        this.$M1B = $eic.$M1B.create();
        this.$TOB = $eic.$TOB;
        this.$CEC = $eic.$CEC;
        this.mouse = $eic.mouse;
        this.$N5 = $eic.$N5;
        if ($eic.p != null) {
            this.p = new $V_($eic.p);
        }
        else {
            this.p = null;
        }
        this.$CP = $eic.$CP;
    }
    
    public void addPropertyChangeListener(final PropertyChangeListener propertyChangeListener, final boolean b) {
        super.addPropertyChangeListener(propertyChangeListener, b);
        if (!b) {
            return;
        }
        final $U5 $u5 = new $U5(this);
        $u5.addPropertyChangeListener(propertyChangeListener, false);
        $u5.firePropertyChange("bounds", this.getBounds());
        $u5.firePropertyChange("p", this.p);
        $u5.firePropertyChange("map", this.$CBC);
        $u5.firePropertyChange("PROP_HREF", this.$TOB);
        $u5.firePropertyChange("target", this.$CP);
        $u5.firePropertyChange("selected", this.selected);
        $u5.firePropertyChange("R_ADD", this.$HK());
        final Enumeration<Object> elements = this.$EKC.elements();
        while (elements.hasMoreElements()) {
            $u5.firePropertyChange("PROP_MARKED", elements.nextElement(), Boolean.TRUE);
        }
        $u5.removePropertyChangeListener(propertyChangeListener);
    }
    
    public Object clone() {
        return new $EIC(this);
    }
    
    public Rectangle getBounds() {
        return this.$M1B.getRectangle();
    }
    
    public int getIndex() {
        this.$VSC();
        return this.$N5;
    }
    
    public int getMouse() {
        this.$VSC();
        return this.mouse;
    }
    
    public int getSelected() {
        return this.selected;
    }
    
    public void setBounds(final $Z_B $m1B) {
        if ($m1B.equals(this.$M1B)) {
            return;
        }
        final $Z_B $m1B2 = this.$M1B;
        this.$M1B = $m1B;
        this.firePropertyChange("bounds", $m1B2.getRectangle(), this.getBounds());
    }
    
    public void setName(final String name) {
        final String name2 = this.getName();
        if (name2.equals(name)) {
            return;
        }
        super.setName(name);
        if (name2.length() != 0) {
            final Enumeration $hk = this.$HK();
            while ($hk.hasMoreElements()) {
                $hk.nextElement().$GPC(this, name2, name);
            }
        }
    }
}
