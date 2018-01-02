// 
// Decompiled by Procyon v0.5.30
// 

package jlog.$T5.$D7.$ZAC;

import java.awt.event.MouseEvent;
import java.awt.Rectangle;
import jlog.$T5.$D7.$NKD;
import java.awt.Component;
import java.awt.Container;
import java.awt.Point;
import jlog.$H4;
import java.awt.event.MouseMotionListener;
import jlog.$T5.$D7.$XDC.$SRC;

public class $KMD extends $SRC implements MouseMotionListener, $H4
{
    private Point start;
    private Point $QDB;
    private Point $LMD;
    private Container $TR;
    private Component owner;
    private $ANB $QO;
    private boolean visible;
    private int $RJ;
    private $NKD $MMD;
    static final int $NMD = 0;
    static final int $OMD = 1;
    static final int $CAC = 2;
    static final int $PMD = 3;
    
    public synchronized void $QMD() {
        this.owner.removeMouseMotionListener(this);
        if (this.visible) {
            if (this.$MMD != null) {
                this.$TR.remove(this.$MMD);
                this.$TR.validate();
            }
            else {
                this.$TR.remove(this);
                this.$TR.validate();
            }
        }
        if (this.$QO != null) {
            this.$QO.$FMD(new $CNB(this, this.start, this.$LMD, 1));
        }
    }
    
    public $KMD(final Container $tr, final Component owner, final Point point, final $ANB $qo, final int $rj) {
        super(null, $rj == 2);
        this.$MMD = null;
        this.visible = ($rj == 2 || $rj == 1 || $rj == 3);
        this.$TR = $tr;
        this.owner = owner;
        this.$QO = $qo;
        this.$RJ = $rj;
        this.$QDB = new Point(0, 0);
        for (Component parent = owner; parent != $tr; parent = parent.getParent()) {
            final Point location = parent.getLocation();
            this.$QDB.translate(location.x, location.y);
        }
        this.start = new Point(this.$QDB.x + point.x, this.$QDB.y + point.y);
        this.$LMD = new Point(this.start);
        if (this.visible) {
            if ($rj == 3) {
                $tr.add(this.$MMD = new $NKD(new Point(this.start), new Point(this.start)), 0);
            }
            else {
                this.setLocation(this.$QDB);
                $tr.add(this, 0);
            }
        }
        super.$BSC = true;
        owner.addMouseMotionListener(this);
        if ($qo != null) {
            $qo.$FMD(new $CNB(this, this.start, this.start, 0));
        }
    }
    
    public synchronized Rectangle getBounds() {
        return new $NKD(this.start, this.$LMD).getBounds();
    }
    
    public synchronized void mouseDragged(final MouseEvent mouseEvent) {
        this.$LMD.setLocation(this.$QDB.x + mouseEvent.getX(), this.$QDB.y + mouseEvent.getY());
        final $CNB $cnb = new $CNB(this, this.start, this.$LMD, 2);
        if (this.visible) {
            $H4 $mmd;
            if (this.$MMD != null) {
                this.$MMD.$ZKD(this.$LMD);
                $mmd = this.$MMD;
            }
            else {
                this.setBounds($cnb.getBounds());
                $mmd = this;
            }
            ((Component)$mmd).validate();
            ((Component)$mmd).repaint();
        }
        if (this.$QO != null) {
            this.$QO.$FMD($cnb);
        }
    }
    
    public synchronized void mouseMoved(final MouseEvent mouseEvent) {
        this.mouseDragged(mouseEvent);
    }
}
