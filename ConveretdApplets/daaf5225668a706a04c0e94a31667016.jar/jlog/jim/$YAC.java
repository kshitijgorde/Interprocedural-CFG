// 
// Decompiled by Procyon v0.5.30
// 

package jlog.jim;

import java.util.Hashtable;
import java.awt.Dimension;
import jlog.awt.image.$MKB;
import java.awt.Menu;
import jlog.$T5.$D7.$YFC;
import java.util.Locale;
import jlog.awt.$L.$G9;
import java.awt.event.KeyEvent;
import jlog.awt.$L.$K2B;
import java.io.File;
import jlog.util.$UD.$FE;
import java.awt.Polygon;
import java.awt.Rectangle;
import jlog.awt.$Z6.$X_B;
import java.util.Enumeration;
import jlog.$Y_B.$Z_B;
import jlog.$T5.$D7.$ZAC.$HJC;
import java.util.ResourceBundle;
import java.awt.Container;
import jlog.awt.$I8.$J8;
import java.awt.Window;
import jlog.$BI.$M4;
import java.util.Properties;
import jlog.awt.$L.$I9;
import java.awt.Component;
import java.util.Vector;
import jlog.$T5.$D7.$ZAC.$QFC;
import java.awt.PopupMenu;
import jlog.$H4;
import jlog.util.$F;
import jlog.$T5.$D7.$ZAC.$OIC;

public class $YAC extends $H0B implements $X5B, $V0B, $W5B, $OIC, $ADC, $U9B, $F, $H4
{
    $NFC $MFC;
    $NFC $ZFC;
    $TFC $SFC;
    boolean $UXC;
    PopupMenu $XFC;
    private static int id;
    $QFC $PFC;
    String $RJ;
    Vector $JFC;
    int $IFC;
    private $I7B $VXC;
    private $I7B $WXC;
    private $I7B $DFC;
    private $I7B $XXC;
    private $I7B $YXC;
    private $I7B $ZXC;
    private $I7B $IGC;
    private $I7B $VEC;
    private $I7B $AYC;
    private $I7B $BYC;
    private $I7B $CYC;
    private $I7B $DYC;
    private $I7B $EYC;
    private $I7B $FYC;
    Component $GYC;
    boolean $JY;
    $I9 $UYC;
    private Object $IZC;
    
    boolean $BYC() throws Exception {
        if (this.$BYC == null) {
            this.$BYC = new $VYC(this);
        }
        return this.$BYC.$J7B();
    }
    
    void $CHC(final String s) {
        this.$GFC(s);
        this.$JFC.insertElementAt(s, 0);
        if (this.$JFC.size() > this.$IFC) {
            this.$JFC.removeElementAt(this.$IFC - 1);
        }
        this.$TYC(super.$UF);
    }
    
    public void $CIC(final $BBC $bbc) {
        if ($bbc != super.$CBC) {
            try {
                this.$YXC();
                super.$CIC($bbc);
            }
            catch (Exception ex) {
                $M4.print(ex);
            }
        }
    }
    
    boolean $DFC() throws Exception {
        if (this.$XXC == null) {
            this.$XXC = new $OEC(this);
            ((Window)this.$XXC).pack();
            $J8.$K8((Window)this.$XXC);
        }
        ((Window)this.$XXC).pack();
        return this.$XXC.$J7B();
    }
    
    public void $DHC() {
        synchronized (super.$CTB) {
            if (super.$CBC == null) {
                try {
                    this.$IJC();
                    final Container parent = this.getParent();
                    if (parent != null) {
                        parent.validate();
                        parent.repaint();
                        this.$FZC();
                    }
                }
                catch (Exception ex) {
                    $M4.print(ex);
                }
            }
            else {
                super.$DHC();
            }
        }
        // monitorexit(super.$CTB)
    }
    
    boolean $FGC() throws Exception {
        if (this.$DYC == null) {
            this.$DYC = new $GGC(this);
            ((Window)this.$DYC).pack();
            $J8.$K8((Window)this.$DYC);
        }
        ((Window)this.$DYC).pack();
        return this.$DYC.$J7B();
    }
    
    public void $G(final ResourceBundle language) {
        if (language == null) {
            return;
        }
        if (this.$SFC != null) {
            this.$SFC.setLanguage(language);
        }
        if (this.$GYC != null) {
            (($UUC)this.$GYC).setLanguage(language);
        }
        if (this.$EYC != null) {
            (($MLC)this.$EYC).setLanguage(language);
        }
        this.$DYC = null;
        this.$VXC = null;
    }
    
    void $GFC(final String s) {
        for (int i = 0; i < this.$JFC.size(); ++i) {
            if (s.equals(this.$JFC.elementAt(i))) {
                this.$JFC.removeElementAt(i);
                break;
            }
        }
        this.$TYC(super.$UF);
    }
    
    public synchronized void $GJC(final $HJC $hjc) {
        final int type = $hjc.getType();
        $EIC $eic = null;
        try {
            if (type == 2) {
                final Enumeration components = $hjc.getComponents();
                super.$CBC.$IF(true);
                while (components.hasMoreElements()) {
                    final $RKC nextElement = components.nextElement();
                    if (nextElement instanceof $RKC) {
                        super.$CBC.$KIC(nextElement.$FIC);
                    }
                }
            }
            else {
                if (type == 5) {
                    this.showPopup($hjc);
                    return;
                }
                if (type == 7) {
                    final $X_B $q3B = super.$RFC.$Q3B();
                    final float $a7 = $q3B.$A7();
                    final Component component = $hjc.getComponents().nextElement();
                    final Rectangle bounds = component.getBounds();
                    final $Z_B $l1B = $q3B.$L1B(component);
                    $l1B.x = bounds.x / $a7;
                    $l1B.y = bounds.y / $a7;
                    super.$CBC.$IF(true);
                    return;
                }
                if (type == 8) {
                    final $X_B $q3B2 = super.$RFC.$Q3B();
                    final float $a8 = $q3B2.$A7();
                    final Component component2 = $hjc.getComponents().nextElement();
                    final Rectangle bounds2 = component2.getBounds();
                    final $Z_B $l1B2 = $q3B2.$L1B(component2);
                    $l1B2.width = bounds2.width / $a8;
                    $l1B2.height = bounds2.height / $a8;
                    super.$CBC.$IF(true);
                    return;
                }
                if (type == 6) {
                    this.$RUC();
                    return;
                }
            }
            final Rectangle bounds3 = $hjc.getBounds();
            if (bounds3.width < 2 || bounds3.height < 2) {
                return;
            }
            if (type == 0) {
                if (this.$RJ.equals("BOX")) {
                    $eic = new $EIC(new $Z_B(bounds3), "BOX" + $YAC.id++, "", false);
                }
                else if (this.$RJ.equals("CIRCLE")) {
                    $eic = new $EIC(new $Z_B(bounds3), "CIRCLE" + $YAC.id++, "", true);
                }
            }
            else if (type == 1) {
                final Polygon $kcb = $hjc.$KCB();
                if ($kcb.npoints > 2) {
                    $eic = new $EIC("POLYGON" + $YAC.id++, "", $kcb, 1.0f);
                }
            }
            if ($eic != null) {
                String s = "";
                final Enumeration $hk = super.$CBC.$LKC.$HK();
                while ($hk.hasMoreElements()) {
                    final $F8B $f8B = $hk.nextElement();
                    if (($f8B.$CKC() & 0x20) != 0x0) {
                        if (!s.equals("")) {
                            s = String.valueOf(s) + ';';
                        }
                        s = String.valueOf(s) + $f8B.getName();
                    }
                }
                $eic.$M1B.$Z6(1.0f / super.$RFC.$A7(), 1.0f / super.$RFC.$B7());
                super.$CBC.$DIC($eic, s);
                if (this.$GYC != null) {
                    final $UUC $uuc = ($UUC)this.$GYC;
                    if (!$UUC.$EVC) {
                        return;
                    }
                }
                this.$YLC($eic);
            }
        }
        catch (Exception ex) {
            $M4.print(ex);
        }
    }
    
    void $HGC() {
        try {
            this.getFrame().setVisible(false);
            final $FE $fe = ($FE)super.$UF;
            if ($fe.$KE()) {
                try {
                    $fe.remove("MAP_SRC");
                    $fe.$JE(".java" + File.separator + "jlog/jim/Editor.properties");
                }
                catch (Exception ex) {
                    $M4.print(ex);
                }
            }
        }
        finally {
            if (super.$CBC != null) {
                try {
                    this.$YXC();
                }
                catch (Exception ex2) {
                    $M4.print(ex2);
                }
            }
            System.exit(0);
        }
    }
    
    boolean $IGC() throws Exception {
        if (this.$IGC == null) {
            if (!this.$JY) {
                return this.$ZXC();
            }
            try {
                this.$IGC = new $WYC(this);
            }
            catch (Throwable t) {
                return this.$ZXC();
            }
        }
        new Thread() {
            public void run() {
                try {
                    $YAC.this.$IGC.$J7B();
                }
                catch (Exception ex) {
                    $M4.print(ex);
                }
            }
        }.start();
        return true;
    }
    
    boolean $IJC() throws Exception {
        if (this.$AYC == null) {
            this.$AYC = new $NNC(this);
            this.$INC().$F2("JECMDMapAssistent", (Component)this.$AYC);
        }
        else if (this.$UYC == null) {
            (this.$UYC = new $I9((Component)this.$AYC)).$W9(new $K2B() {
                public void $Z9(final KeyEvent keyEvent) {
                    if (keyEvent.getKeyCode() == 27) {
                        $YAC.this.$DHC();
                    }
                }
            });
        }
        return this.$AYC.$J7B();
    }
    
    boolean $JJC() throws Exception {
        if (this.$VXC == null) {
            this.$VXC = new $ANC(this);
            this.$INC().$F2("CARD_JECMDEditRubrik", (Component)this.$VXC);
        }
        return this.$VXC.$J7B();
    }
    
    boolean $KE() {
        final $BBC $cbc = super.$CBC;
        return $cbc != null && $cbc.$KE();
    }
    
    void $KJC() {
        final Enumeration selected = this.$PFC.getSelected();
        while (selected.hasMoreElements()) {
            final Component component = selected.nextElement();
            if (component instanceof $RKC) {
                final $EIC $fic = (($RKC)component).$FIC;
                final $EIC $eic = ($EIC)$fic.clone();
                final String $gsc = $fic.$GSC();
                final $Z_B $m1B = $eic.$M1B;
                $m1B.x += 4.0f;
                final $Z_B $m1B2 = $eic.$M1B;
                $m1B2.y += 4.0f;
                super.$CBC.$DIC($eic, $gsc);
            }
        }
    }
    
    void $KYC() {
        (super.$LYC = new $YEC(this)).start();
    }
    
    void $LJC(final String $rj) {
        final String $rj2 = this.$RJ;
        this.$RJ = $rj;
        if ($rj.equals("BOX")) {
            this.$PFC.$LJC(2);
        }
        else if ($rj.equals("CIRCLE")) {
            this.$PFC.$LJC(3);
        }
        else if ($rj.equals("SELECT")) {
            this.$PFC.$LJC(1);
        }
        else if ($rj.equals("POLY")) {
            this.$PFC.$LJC(4);
        }
        if (super.$CBC != null) {
            if ($rj.equals("TEST")) {
                this.$PFC.$LJC(0);
                super.$CBC.resetSelection();
                super.$CBC.$LJC(0);
            }
            else {
                super.$CBC.$LJC(1);
            }
        }
        this.$MFC.$EUC();
        this.$SFC.$BJC();
        if (!$rj.equals($rj2) && ("TEST".equals($rj2) || "TEST".equals($rj))) {
            this.validate();
        }
    }
    
    public void $LUC(final Locale locale) throws Exception {
        super.$LUC(locale);
        final ResourceBundle $rb = super.$TEC.$RB();
        if ($rb == null) {
            return;
        }
        if (this.$XFC != null) {
            $YFC.setLanguage(this.$XFC, $rb);
        }
    }
    
    $I7B $OLC() {
        if (this.$EYC == null) {
            synchronized (this.$IZC) {
                if (this.$EYC == null) {
                    this.$EYC = new $MLC(this, null, super.$CBC, super.$TEC.$RB());
                    this.$INC().$F2("JERubrikDialog", (Component)this.$EYC);
                }
            }
            // monitorexit(this.$IZC)
        }
        return this.$EYC;
    }
    
    boolean $OUC() {
        return super.$UF.$ZE("DOCUMENT_AUTOLOAD", false);
    }
    
    void $QYC() {
        super.$RFC.$T_B(1.0f, 1.0f);
        final Dimension preferredSize = super.$RFC.$P4B().getPreferredSize();
        final Dimension size = super.$CBC.$QKC.getSize(null);
        if (size == null) {
            return;
        }
        preferredSize.width = Math.max(preferredSize.width, size.width);
        preferredSize.height = Math.max(preferredSize.height, size.height);
        super.$CBC.setSize(preferredSize);
    }
    
    void $RUC() {
        final Enumeration selected = this.$PFC.getSelected();
        if (!selected.hasMoreElements()) {
            return;
        }
        this.$YLC(selected.nextElement().$FIC);
    }
    
    boolean $SUC() throws Exception {
        if (this.$FYC == null) {
            this.$FYC = new $YVC(this);
        }
        return this.$FYC.$J7B();
    }
    
    void $TYC(final Properties properties) {
        for (int n = 0; properties.remove("DOCUMENT_HISTORY" + n) != null; ++n) {}
        for (int i = 0; i < this.$JFC.size(); ++i) {
            final String s = this.$JFC.elementAt(i);
            if (s != null) {
                ((Hashtable<String, String>)properties).put("DOCUMENT_HISTORY" + i, s);
            }
        }
    }
    
    boolean $UEC() throws Exception {
        if (this.$DFC == null) {
            this.$DFC = new $CFC(this);
        }
        return this.$DFC.$J7B();
    }
    
    boolean $VEC() throws Exception {
        if (this.$VEC == null) {
            if (!this.$JY) {
                return this.$ZXC();
            }
            try {
                this.$VEC = new $VGC(this);
            }
            catch (Throwable t) {
                return this.$ZXC();
            }
        }
        new Thread() {
            public void run() {
                try {
                    $YAC.this.$VEC.$J7B();
                }
                catch (Exception ex) {
                    $M4.print(ex);
                }
            }
        }.start();
        return true;
    }
    
    void $WMC(final $MJC $mjc) {
        new $DLC($mjc, this);
    }
    
    boolean $XEC() throws Exception {
        if (this.$WXC == null) {
            this.$WXC = new $BRC(this);
        }
        return this.$WXC.$J7B();
    }
    
    boolean $XNC() throws Exception {
        if (this.$CYC == null) {
            this.$CYC = new $WEC(this);
            ((Window)this.$CYC).pack();
            $J8.$K8((Window)this.$CYC);
        }
        ((Window)this.$CYC).pack();
        return this.$CYC.$J7B();
    }
    
    void $YLC(final $EIC $eic) {
        if ($eic == null) {
            return;
        }
        if (this.$GYC == null) {
            this.$GYC = new $UUC(this);
            this.$INC().$F2("jlog.jim.JEAreaDialog", this.$GYC);
        }
        (($UUC)this.$GYC).$TKC($eic);
        this.$INC().$OTB("jlog.jim.JEAreaDialog");
    }
    
    boolean $YXC() throws Exception {
        if (this.$YXC == null) {
            this.$YXC = new $XAC(this);
        }
        return this.$YXC.$J7B();
    }
    
    boolean $ZXC() throws Exception {
        if (this.$ZXC == null) {
            this.$ZXC = new $XYC(this);
        }
        return this.$ZXC.$J7B();
    }
    
    static {
        $YAC.id = 1;
    }
    
    public $YAC() {
        this.$UXC = true;
        this.$RJ = "SELECT";
        this.$VXC = null;
        this.$WXC = null;
        this.$DFC = null;
        this.$XXC = null;
        this.$YXC = null;
        this.$ZXC = null;
        this.$IGC = null;
        this.$VEC = null;
        this.$AYC = null;
        this.$BYC = null;
        this.$CYC = null;
        this.$DYC = null;
        this.$EYC = null;
        this.$FYC = null;
        this.$GYC = null;
        this.$JY = false;
        this.$UYC = null;
        this.$IZC = new Object();
        super.$LL = 2;
        super.$IYC = "jlog.jim.JEResources";
        super.$TEC.$NB(this);
    }
    
    void delete() {
        final Enumeration selected = this.$PFC.getSelected();
        while (selected.hasMoreElements()) {
            final Component component = selected.nextElement();
            if (component instanceof $RKC) {
                super.$CBC.$KIC((($RKC)component).$FIC);
            }
        }
    }
    
    public void init() {
        super.init();
        try {
            this.$JYC(600000L);
            this.$JY = super.$UF.$ZE("XOR_MASK", false);
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
        this.$LJC(this.$RJ);
    }
    
    void setDocumentAutoload(final boolean b) {
        super.$UF.$AF("DOCUMENT_AUTOLOAD", b);
    }
    
    private void showPopup(final $HJC $hjc) {
        try {
            final Component component = $hjc.getComponents().nextElement();
            final Rectangle bounds = $hjc.getBounds();
            this.$ZFC.$EUC();
            this.$XFC.show(component, bounds.x, bounds.y);
        }
        catch (Exception ex) {}
    }
}
