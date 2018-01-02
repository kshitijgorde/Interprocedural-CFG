// 
// Decompiled by Procyon v0.5.30
// 

package jlog.jim;

import java.beans.PropertyChangeListener;
import jlog.io.$JW;
import java.net.URL;
import jlog.awt.image.$OKB;
import jlog.awt.image.$Q0;
import java.util.Enumeration;
import java.awt.Rectangle;
import jlog.awt.image.$MKB;
import jlog.util.Vars;
import java.awt.Dimension;
import jlog.awt.image.$VKB;
import java.io.File;
import jlog.io.$P4;
import java.awt.Color;
import java.util.Vector;
import jlog.$H4;
import jlog.$T5.util.$U5;

public class $BBC extends $U5 implements $H4, $U9B
{
    private static final boolean debug = true;
    private int $RJ;
    private $H0B $SHC;
    Vector $LPC;
    private boolean $DE;
    String $MPC;
    $XHC $LKC;
    float $NPC;
    Color $XOC;
    private $P4 $OPC;
    File $EFC;
    String $ID;
    File $DBC;
    $VKB $QKC;
    $VKB $PPC;
    private Dimension size;
    Vars vars;
    String $YMC;
    $MKB $SPC;
    
    public synchronized void $DIC(final $EIC $eic, final String s) {
        $eic.$CIC(this);
        this.$LKC.$DIC($eic, s);
        this.$LPC.addElement($eic);
        this.firePropertyChange("MAP_ADD_AREA", $eic);
        this.$IF(true);
    }
    
    public void $FPC(final Rectangle rectangle) {
        this.firePropertyChange("FOCUS_REGION", new Rectangle(rectangle));
    }
    
    public void $IF(final boolean $de) {
        this.firePropertyChange("changed", this.$DE, this.$DE = $de);
    }
    
    public $F8B $IIC(final String s) {
        return this.$LKC.$IIC(s);
    }
    
    public synchronized void $JIC(final $F8B $f8B) {
        this.$LKC.$JIC($f8B);
        if (this.$LKC.$CI.size() == 1) {
            this.firePropertyChange("PROP_RUBRIKLIST_CREATED", this.$LKC);
        }
        this.$IF(true);
    }
    
    public boolean $KE() {
        return this.$DE;
    }
    
    public synchronized void $KIC(final $EIC $eic) {
        this.$LKC.$KIC($eic);
        this.$LPC.removeElement($eic);
        this.firePropertyChange("MAP_REMOVE_AREA", $eic);
        $eic.$CIC(null);
        this.$IF(true);
    }
    
    public synchronized void $LIC(final $F8B $f8B) {
        this.$LKC.$LIC($f8B);
        if (this.$LKC.$CI.isEmpty()) {
            this.firePropertyChange("PROP_RUBRIKLIST_REMOVED", this.$LKC);
        }
        this.$IF(true);
    }
    
    public void $LJC(final int $rj) {
        this.firePropertyChange("mode", this.$RJ, this.$RJ = $rj);
    }
    
    Enumeration $MIC() {
        return this.$LPC.elements();
    }
    
    void $QPC(final $Q0 $q0) {
        (this.$QKC = new $VKB($q0)).$YLB(true);
        (this.$PPC = new $VKB($q0)).$YLB(true);
        this.$SPC = new $MKB() {
            public void $NKB(final $OKB $okb) {
                if (($BBC.this.size == null || $BBC.this.size.width < 0 || $BBC.this.size.height < 0) && $okb.$PKB()) {
                    $BBC.this.setSize($okb.getSize());
                }
            }
        };
        this.$QKC.$FLB(this.$SPC);
    }
    
    public void $RPC(final URL url) {
        final URL $vpc = this.$VPC();
        this.$OPC.$JT(url);
        this.firePropertyChange("map_base", $vpc, url);
        String name;
        if (url != null) {
            name = $JW.getFile(url.getFile());
            final int lastIndex = name.lastIndexOf(46);
            if (lastIndex > -1) {
                name = name.substring(0, lastIndex);
            }
        }
        else {
            name = "newmap";
        }
        if (name != null && name.length() != 0) {
            this.setName(name);
        }
        this.$IF(true);
    }
    
    public void $UPC() throws Exception {
        final Enumeration $hk = this.$LKC.$HK();
        while ($hk.hasMoreElements()) {
            this.$LIC($hk.nextElement());
        }
        this.setName("");
        this.setDescription("");
        this.$QKC.reset();
        this.$PPC.reset();
        this.$RPC(null);
        this.setSize(null);
        this.$IF(false);
    }
    
    public $H0B $VJC() {
        return this.$SHC;
    }
    
    public URL $VPC() {
        return this.$OPC.getURL();
    }
    
    public $P4 $WPC() {
        return this.$OPC;
    }
    
    public void $XPC(final $H0B $shc) {
        this.firePropertyChange("applet", this.$SHC, this.$SHC = $shc);
    }
    
    public $BBC(final URL url, final $H0B $shc, final $Q0 $q0) {
        this.$RJ = 0;
        this.$LPC = new Vector();
        this.$DE = false;
        this.$MPC = "info";
        this.$LKC = null;
        this.$NPC = 1.0f;
        this.$XOC = Color.green;
        this.$OPC = new $P4();
        this.$EFC = null;
        this.size = null;
        this.$YMC = null;
        this.setSource(this);
        this.$SHC = $shc;
        this.vars = new Vars();
        this.$QPC($q0);
        this.$LKC = new $XHC(this);
        this.$RPC(url);
        this.$DE = false;
    }
    
    public void addPropertyChangeListener(final PropertyChangeListener propertyChangeListener, final boolean b) {
        super.addPropertyChangeListener(propertyChangeListener, b);
        if (!b) {
            return;
        }
        final $U5 $u5 = new $U5(this);
        $u5.addPropertyChangeListener(propertyChangeListener, false);
        $u5.firePropertyChange("applet", this.$SHC);
        $u5.firePropertyChange("changed", this.$DE);
        $u5.firePropertyChange("map_base", this.$OPC);
        $u5.firePropertyChange("mode", this.$RJ);
        $u5.firePropertyChange("PROP_SIZE", this.getSize());
        if (this.$LKC != null) {
            if (!this.$LKC.$CI.isEmpty()) {
                $u5.firePropertyChange("PROP_RUBRIKLIST_CREATED", this.$LKC);
            }
            $u5.firePropertyChange("MAP_ADD_AREA", this.$MIC());
        }
        $u5.removePropertyChangeListener(propertyChangeListener);
    }
    
    void destroy() {
        this.$SHC = null;
        this.$QKC.reset();
        this.$PPC.reset();
        this.$LKC.destroy();
    }
    
    public int getMode() {
        return this.$RJ;
    }
    
    public Dimension getSize() {
        final Dimension size = this.size;
        if (size == null || size.width < 0 || size.height < 0) {
            return this.$QKC.getSize(this.$SPC);
        }
        return new Dimension(size.width, size.height);
    }
    
    public void resetSelection() {
        final Enumeration $hk = this.$LKC.$HK();
        while ($hk.hasMoreElements()) {
            $hk.nextElement().setSelectedEntry("NO_SELECTION");
        }
    }
    
    public void setSize(Dimension size) {
        final Dimension size2 = this.size;
        if ($U5.$RQ(size2, size)) {
            return;
        }
        if (size == null || size.width < 0 || size.height < 0) {
            size = this.$QKC.getSize(this.$SPC);
            if (size == null) {
                return;
            }
        }
        this.firePropertyChange("PROP_SIZE", size2, this.size = size);
        this.$IF(true);
    }
}
