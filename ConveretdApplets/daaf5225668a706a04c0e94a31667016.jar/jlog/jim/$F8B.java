// 
// Decompiled by Procyon v0.5.30
// 

package jlog.jim;

import java.awt.Rectangle;
import java.beans.PropertyChangeListener;
import java.util.Vector;
import java.util.Enumeration;
import java.awt.Image;
import jlog.$YHC.$ZOC;
import jlog.awt.$W5;
import java.net.URL;
import java.awt.Color;
import jlog.$H4;
import jlog.$T5.util.$U5;

public class $F8B extends $U5 implements $H4
{
    public static final String MOUSE_NAME = "MOUSE";
    public static final String $COC = "MARKER";
    public static final String $DOC = "INDEX";
    public static final String $EOC = "ICON";
    public static final int $DKC = 1;
    public static final String $FOC = "SELECTABLE";
    public static final int SELECTED = 2;
    public static final String $GOC = "SELECTED";
    public static final int $HOC = 4;
    public static final String $IOC = "INVERSE";
    public static final int $JOC = 8;
    public static final String $KOC = "POPUP";
    public static final int $LOC = 16;
    public static final String $MOC = "BLINK";
    public static final int DEFAULT = 32;
    public static final String $NOC = "DEFAULT";
    public static final int $OOC = 64;
    public static final String $POC = "LIST";
    public static final int $K9B = 128;
    public static final String $QOC = "HIDELIST";
    public static final int $J9B = 256;
    public static final String $ROC = "HIDENAME";
    public static final int $I9B = 512;
    public static final String $SOC = "HIDEMARKER";
    public static final int $TOC = 4096;
    public static final String $UOC = "NULL";
    private int $N5;
    private int $TMC;
    private int mouse;
    public static final String $QGC = "ALL_AREAS";
    public static final String $RGC = "ALL_DESCRIPTION";
    public static final String $G8B = "NO_SELECTION";
    public static final String $HKC = "R_ADD_NAME";
    public static final String $IKC = "R_REMOVE_NAME";
    public static final String $JKC = "PROP_MARKED";
    public static final String $VOC = "MARKER_COLOR";
    public static final String $WOC = "XOR_COLOR";
    String id;
    Color $YH;
    Color $XOC;
    private URL $YOC;
    private $W5 $JGC;
    boolean $EKC;
    String $BKC;
    $BBC $CBC;
    $ZOC $WJC;
    
    public $W5 $BEC() {
        return this.$JGC;
    }
    
    public void $BPC(final String id) {
        if (id.equals(this.id)) {
            return;
        }
        this.firePropertyChange("id", this.id, this.id = id);
    }
    
    public void $CIC(final $BBC $cbc) {
        this.firePropertyChange("map", this.$CBC, this.$CBC = $cbc);
    }
    
    public int $CKC() {
        return this.$TMC;
    }
    
    public void $CPC(final URL $yoc, final Image image) {
        final URL $yoc2 = this.$YOC;
        this.$YOC = $yoc;
        this.$JGC.setImage(image);
        this.firePropertyChange("icon_url", $yoc2, $yoc);
        this.firePropertyChange("icon", this.$JGC);
    }
    
    public boolean $DIC(final $EIC $eic) {
        final String name = $eic.getName();
        final boolean add = this.$WJC.add(name, $eic);
        if (add) {
            this.firePropertyChange("R_ADD_NAME", name);
        }
        return add;
    }
    
    public URL $DPC() {
        return this.$YOC;
    }
    
    public void $GPC(final $EIC $eic, final String s, final String selectedEntry) {
        if (this.$WJC.remove(s, $eic)) {
            this.firePropertyChange("R_REMOVE_NAME", s);
        }
        if (this.$WJC.add(selectedEntry, $eic)) {
            this.firePropertyChange("R_ADD_NAME", selectedEntry);
        }
        if (s.equals(this.$BKC)) {
            this.setSelectedEntry(selectedEntry);
        }
    }
    
    public boolean $HPC(final $EIC $eic) {
        return this.$WJC.getKey($eic) != null;
    }
    
    public void $JPC(final int $tmc) {
        this.firePropertyChange("marker", this.$TMC, this.$TMC = $tmc);
    }
    
    public boolean $KIC(final $EIC $eic) {
        final boolean remove = this.$WJC.remove($eic.getName(), $eic);
        if (remove) {
            this.firePropertyChange("R_REMOVE_NAME", $eic.getName());
        }
        return remove;
    }
    
    public void $KPC(final int mouse) {
        this.firePropertyChange("mouse", this.mouse, this.mouse = mouse);
    }
    
    public Enumeration $MIC() {
        return this.$WJC.$XJC();
    }
    
    public void $SMC(final int $n5) {
        this.firePropertyChange("index", this.$N5, this.$N5 = $n5);
    }
    
    public void $TJC(final boolean $ekc) {
        final boolean $ekc2 = this.$EKC;
        if ($ekc2 == $ekc) {
            return;
        }
        this.$EKC = $ekc;
        final Enumeration $xjc = this.$WJC.$XJC();
        while ($xjc.hasMoreElements()) {
            $xjc.nextElement().$TJC(this, this.$EKC);
        }
        this.firePropertyChange("PROP_MARKED", $ekc2, $ekc);
    }
    
    public $BBC $UJC() {
        return this.$CBC;
    }
    
    public $EIC[] $ULC() {
        if (this.$BKC == null) {
            return null;
        }
        final Vector vector = new Vector<Object>();
        final Enumeration $xjc = this.$WJC.$XJC(this.$BKC);
        while ($xjc.hasMoreElements()) {
            vector.addElement($xjc.nextElement());
        }
        if (vector.size() != 0) {
            final $EIC[] array = new $EIC[vector.size()];
            vector.copyInto(array);
            return array;
        }
        return null;
    }
    
    public $F8B(final String s, final String s2, final String s3, final Color color, final URL url, final Image image) {
        super(s, s3);
        this.$N5 = 5;
        this.$TMC = 1;
        this.mouse = 12;
        this.$XOC = null;
        this.$YOC = null;
        this.$JGC = new $W5();
        this.$EKC = false;
        this.$BKC = "";
        this.$CBC = null;
        this.$WJC = new $ZOC(false);
        this.setSource(this);
        this.$BPC(s2);
        this.setColor(color);
        this.$CPC(url, image);
    }
    
    public void addPropertyChangeListener(final PropertyChangeListener propertyChangeListener, final boolean b) {
        super.addPropertyChangeListener(propertyChangeListener, b);
        if (!b) {
            return;
        }
        final $U5 $u5 = new $U5(this);
        $u5.addPropertyChangeListener(propertyChangeListener, false);
        $u5.firePropertyChange("color", this.$YH);
        $u5.firePropertyChange("icon_url", this.$YOC);
        $u5.firePropertyChange("icon", this.$JGC);
        $u5.firePropertyChange("id", this.id);
        $u5.firePropertyChange("PROP_MARKED", this.$EKC);
        $u5.firePropertyChange("map", this.$CBC);
        $u5.firePropertyChange("marker", this.$TMC);
        $u5.firePropertyChange("mouse", this.mouse);
        $u5.firePropertyChange("index", this.$N5);
        $u5.firePropertyChange("R_ADD_NAME", this.$WJC.getKeys());
        $u5.firePropertyChange("selected_name", this.$BKC);
        $u5.removePropertyChangeListener(propertyChangeListener);
    }
    
    public Color getColor() {
        return this.$YH;
    }
    
    public String getID() {
        return this.id;
    }
    
    public $W5 getImage() {
        return this.$JGC;
    }
    
    public int getIndex() {
        return this.$N5;
    }
    
    public int getMouse() {
        return this.mouse;
    }
    
    public String getSelectedEntry() {
        return this.$BKC;
    }
    
    public boolean isEmpty() {
        return this.$WJC.getKeys().hasMoreElements() ^ true;
    }
    
    public void setColor(final Color $yh) {
        if ($yh != null && $yh.equals(this.$YH)) {
            return;
        }
        this.firePropertyChange("color", this.$YH, this.$YH = $yh);
    }
    
    public synchronized void setSelectedEntry(final String $bkc) {
        if (this.$BKC.equals($bkc)) {
            return;
        }
        final String $bkc2 = this.$BKC;
        this.$BKC = $bkc;
        final Enumeration $xjc = this.$WJC.$XJC($bkc2);
        while ($xjc.hasMoreElements()) {
            $xjc.nextElement().$OBB(false);
        }
        final Enumeration $xjc2 = this.$WJC.$XJC($bkc);
        Rectangle rectangle = null;
        while ($xjc2.hasMoreElements()) {
            final $EIC $eic = $xjc2.nextElement();
            $eic.$OBB(true);
            if (rectangle == null) {
                rectangle = $eic.getBounds();
            }
            else {
                rectangle = rectangle.union($eic.getBounds());
            }
        }
        this.firePropertyChange("selected_name", $bkc2, $bkc);
        if (rectangle != null) {
            this.$CBC.$FPC(rectangle);
        }
    }
}
