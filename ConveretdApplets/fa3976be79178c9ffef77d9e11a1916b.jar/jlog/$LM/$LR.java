// 
// Decompiled by Procyon v0.5.30
// 

package jlog.$LM;

import java.util.Vector;

public class $LR implements $UM
{
    public boolean $KX;
    private Vector v;
    private Object $LX;
    private Object $WM;
    private boolean $MX;
    
    public void $AU(final $QM $qm) {
        synchronized (this.$LX) {
            if (this.v == null) {
                this.v = new Vector();
            }
            else if (this.$KX) {
                this.$QQ(this.v.contains($qm) ^ true, "listener allready registered:" + $qm);
            }
            if ($qm instanceof $UM) {
                (($UM)$qm).$VM(this.$QR());
                (($UM)$qm).$XM(this.$MX);
            }
            this.v.addElement($qm);
        }
        // monitorexit(this.$LX)
    }
    
    public void $AU(final $QM $qm, final $NO $no) throws $TM {
        this.$AU($qm);
        try {
            $no.$PO($qm);
        }
        catch ($TM $tm) {
            this.$BU($qm);
            throw $tm;
        }
    }
    
    public void $BU(final $QM $qm) {
        synchronized (this.$LX) {
            if (this.v == null) {
                if (this.$KX) {
                    this.$QQ(false, "listener not registered:" + $qm);
                }
                // monitorexit(this.$LX)
                return;
            }
            if (!this.v.removeElement($qm)) {
                if (this.$KX) {
                    this.$QQ(false, "listener not registered:" + $qm);
                }
            }
            else if ($qm instanceof $UM) {
                if (!this.$MX) {
                    (($UM)$qm).$XM(true);
                }
                (($UM)$qm).$VM(null);
            }
            if (this.v.isEmpty()) {
                this.v = null;
            }
        }
        // monitorexit(this.$LX)
    }
    
    public void $CR(final $NO $no, final $NO $no2) throws $TM {
        synchronized (this.$LX) {
            final Object[] $nx = this.$NX();
            for (int length = $nx.length, i = 0; i < length; ++i) {
                try {
                    $no.$PO(($QM)$nx[i]);
                }
                catch ($TM $tm2) {
                    if ($no2 != null) {
                        while (i != -1) {
                            try {
                                $no2.$PO(($QM)$nx[i]);
                            }
                            catch ($TM $tm) {
                                System.err.print("inkonsistenter Zustand - kein undo");
                                $tm.printStackTrace();
                            }
                            --i;
                        }
                    }
                    throw $tm2;
                }
            }
        }
        // monitorexit(this.$LX)
    }
    
    public void $FU() {
        synchronized (this.$LX) {
            final Object[] $nx = this.$NX();
            int i = $nx.length;
            while (i != 0) {
                --i;
                this.$BU(($QM)$nx[i]);
            }
        }
        // monitorexit(this.$LX)
    }
    
    public Object[] $NX() {
        if (this.v == null) {
            return new Object[0];
        }
        final Object[] array = new Object[this.v.size()];
        this.v.copyInto(array);
        return array;
    }
    
    public boolean $OX() {
        return this.$MX;
    }
    
    public void $QQ(final boolean b, final String s) {
        if (!b) {
            throw new RuntimeException(String.valueOf(this.getClass().getName()) + ":" + s);
        }
    }
    
    public Object $QR() {
        return (this.$WM == null) ? this : this.$WM;
    }
    
    public static boolean $RQ(final Object o, final Object o2) {
        return o == o2 || (o != null && o2 != null && o.equals(o2) && o2.equals(o));
    }
    
    public static boolean $SQ(final Object o) {
        return o == null || "".equals(o);
    }
    
    public void $VM(final Object $wm) {
        synchronized (this.$LX) {
            if (this.$WM == this.$QR()) {
                // monitorexit(this.$LX)
                return;
            }
            final Object[] $nx = this.$NX();
            int i = $nx.length;
            this.$WM = $wm;
            while (i != 0) {
                --i;
                if ($nx[i] instanceof $UM) {
                    (($UM)$nx[i]).$VM($wm);
                }
            }
        }
        // monitorexit(this.$LX)
    }
    
    public void $XM(final boolean $mx) {
        synchronized (this.$LX) {
            if ($mx == this.$OX()) {
                // monitorexit(this.$LX)
                return;
            }
            final Object[] $nx = this.$NX();
            int i = $nx.length;
            this.$MX = $mx;
            while (i != 0) {
                --i;
                if ($nx[i] instanceof $UM) {
                    (($UM)$nx[i]).$XM(this.$MX);
                }
            }
        }
        // monitorexit(this.$LX)
    }
    
    public $LR() {
        this.$KX = true;
        this.v = null;
        this.$LX = new Object();
        this.$WM = null;
        this.$MX = false;
    }
}
