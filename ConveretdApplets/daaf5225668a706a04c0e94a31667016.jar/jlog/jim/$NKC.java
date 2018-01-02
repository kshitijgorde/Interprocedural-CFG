// 
// Decompiled by Procyon v0.5.30
// 

package jlog.jim;

import jlog.$Y_B.$Z_B;
import java.awt.Rectangle;
import java.beans.PropertyChangeEvent;
import java.awt.event.ComponentListener;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentAdapter;
import java.awt.Font;
import jlog.awt.$C0.$CAB;
import java.awt.Image;
import java.awt.Color;
import java.awt.Point;
import jlog.awt.image.$OKB;
import java.awt.Dimension;
import java.util.Enumeration;
import java.awt.Container;
import jlog.awt.$C0.$PAB;
import jlog.awt.$UHB.$HYB;
import java.util.Hashtable;
import jlog.$H4;
import jlog.awt.image.$MKB;
import java.beans.PropertyChangeListener;
import jlog.awt.$X2B.$G3B;

public class $NKC extends $G3B implements PropertyChangeListener, $MKB, Runnable, $H4
{
    Hashtable $OKC;
    $BBC $CBC;
    $H0B $SHC;
    $HYB $J3B;
    $PAB $EAB;
    private static Thread $PKC;
    private static Object $LX;
    private static long time;
    
    public $RKC $ALC(final $EIC $eic) {
        return this.$OKC.get($eic);
    }
    
    private void $BLC() {
        synchronized ($NKC.$LX) {
            $NKC.time = System.currentTimeMillis();
            if ($NKC.$PKC == null) {
                $RKC.$CLC.stop();
                ($NKC.$PKC = new Thread(this, "MapView.stopBlinkers")).start();
            }
        }
        // monitorexit($NKC.$LX)
    }
    
    public synchronized void $CIC(final $BBC $cbc, final $H0B $shc) {
        if ($cbc == this.$CBC && $shc == this.$SHC) {
            return;
        }
        this.$SHC = $shc;
        if ($cbc != this.$CBC) {
            if (this.$CBC != null) {
                this.$CBC.removePropertyChangeListener(this);
                this.$CBC.$QKC.$AMB(this);
                this.$J3B.removeAll();
                final Enumeration<$RKC> elements = this.$OKC.elements();
                while (elements.hasMoreElements()) {
                    elements.nextElement().$TKC(null);
                }
                this.$OKC.clear();
            }
            this.$EAB.image = null;
            this.$T_B(1.0f, 1.0f);
            this.$J3B.$JXB().flush();
            if ((this.$CBC = $cbc) != null) {
                final Enumeration $mic = $cbc.$MIC();
                while ($mic.hasMoreElements()) {
                    final $EIC $eic = $mic.nextElement();
                    final $RKC $rkc = new $RKC($eic);
                    this.$OKC.put($eic, $rkc);
                    this.$Q3B().$PI.put($rkc, $eic.$M1B.create());
                    $rkc.$JI(this.$J3B);
                }
                final Dimension size = $cbc.getSize();
                if (size != null) {
                    this.$L3B(size);
                }
                $cbc.addPropertyChangeListener(this, false);
                this.$EAB.image = $cbc.$QKC.getImage();
                $cbc.$QKC.$FLB(this);
            }
        }
        this.$J3B.repaintAll();
    }
    
    public void $NKB(final $OKB $okb) {
        this.$EAB.image = $okb.getImage();
        this.$J3B.repaintAll();
    }
    
    public boolean $P2B(final Point point) {
        this.$BLC();
        return super.$P2B(point);
    }
    
    public void $T_B(final float n, final float n2) {
        this.$BLC();
        super.$T_B(n, n2);
    }
    
    private void $WKC() {
        final Dimension size = this.$CBC.getSize();
        final Dimension screenSize = this.getScreenSize();
        if (size == null || size.width < 1 || size.height < 1) {
            this.$L3B(0, 0);
            return;
        }
        final Dimension $g1B = this.$G1B(size);
        if (screenSize != null && $g1B.equals(screenSize)) {
            return;
        }
        this.$L3B($g1B.width, $g1B.height);
        this.$J3B.repaintAll();
    }
    
    public $EIC $ZKC(final $RKC $rkc) {
        final Enumeration<$EIC> keys = this.$OKC.keys();
        while (keys.hasMoreElements()) {
            final $EIC $eic = keys.nextElement();
            if (this.$OKC.get($eic) == $rkc) {
                return $eic;
            }
        }
        return null;
    }
    
    static {
        $NKC.$PKC = null;
        $NKC.$LX = new Object();
        $NKC.time = 0L;
    }
    
    public $NKC(final $BBC $bbc, final $H0B $h0B) {
        this.$OKC = new Hashtable();
        this.$CBC = null;
        this.$SHC = null;
        this.$EAB = null;
        this.$J3B = ($HYB)this.$P4B();
        this.$EAB = new $PAB(null, null);
        this.$EAB.$QAB = false;
        this.$J3B.$GAB(this.$EAB);
        this.$J3B.setFont(new Font("Default", 1, 14));
        this.$CIC($bbc, $h0B);
        this.addComponentListener(new ComponentAdapter() {
            public void componentResized(final ComponentEvent componentEvent) {
                $NKC.this.$P4B().repaint();
            }
        });
    }
    
    public void layout() {
        this.$BLC();
        super.layout();
    }
    
    public void propertyChange(final PropertyChangeEvent propertyChangeEvent) {
        final String propertyName = propertyChangeEvent.getPropertyName();
        final Object newValue = propertyChangeEvent.getNewValue();
        if (propertyChangeEvent.getSource() != this.$CBC) {
            return;
        }
        if (propertyName.equals("MAP_ADD_AREA")) {
            final $EIC $eic = ($EIC)newValue;
            final $RKC $rkc = new $RKC($eic);
            final $Z_B create = $eic.$M1B.create();
            this.$Q3B().$PI.put($rkc, create);
            $rkc.setBounds(create.$S1B(this.$A7(), this.$B7()));
            this.$OKC.put($eic, $rkc);
            $rkc.$JI(this.$J3B);
            $rkc.repaint();
        }
        else if (propertyName.equals("MAP_REMOVE_AREA")) {
            final $EIC $eic2 = ($EIC)newValue;
            final $RKC $rkc2 = this.$OKC.get($eic2);
            this.$OKC.remove($eic2);
            $rkc2.$YKC(this.$J3B);
            $rkc2.$TKC(null);
        }
        else if (propertyName.equals("FOCUS_REGION")) {
            this.$R3B(this.$V1B((Rectangle)newValue));
        }
        else if (propertyName.equals("PROP_SIZE")) {
            this.$WKC();
        }
    }
    
    public void run() {
        final Thread currentThread = Thread.currentThread();
        try {
            while (currentThread == $NKC.$PKC) {
                Thread.sleep(120L);
                synchronized ($NKC.$LX) {
                    if (Math.abs(System.currentTimeMillis() - $NKC.time) > 400L) {
                        if (currentThread == $NKC.$PKC) {
                            if (this.isShowing()) {
                                $RKC.$CLC.start();
                            }
                            $NKC.$PKC = null;
                        }
                        // monitorexit($NKC.$LX)
                        return;
                    }
                }
                // monitorexit($NKC.$LX)
            }
        }
        catch (Exception ex) {}
        synchronized ($NKC.$LX) {
            if (currentThread == $NKC.$PKC) {
                if (this.isShowing()) {
                    $RKC.$CLC.start();
                }
                $NKC.$PKC = null;
            }
        }
        // monitorexit($NKC.$LX)
    }
}
