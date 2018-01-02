// 
// Decompiled by Procyon v0.5.30
// 

package jlog.$LM;

import java.util.Vector;

public class $KR extends $LR implements Runnable
{
    private Vector $MR;
    private int $NR;
    private Thread $OR;
    
    public void $PR(final $NO $no) {
        synchronized (this.$MR) {
            if (this.$MR.size() == this.$NR) {
                this.$MR.removeElementAt(0);
            }
            this.$MR.addElement($no);
            if (this.$OR == null) {
                (this.$OR = new Thread(this, "fireQ:" + this.$QR())).start();
            }
            this.$MR.notify();
        }
        // monitorexit(this.$MR)
    }
    
    public $KR(final int $nr) {
        this.$OR = null;
        this.$NR = $nr;
        this.$MR = new Vector();
    }
    
    public void run() {
        final Thread currentThread = Thread.currentThread();
        try {
            while (currentThread == this.$OR) {
                final $NO $no;
                synchronized (this.$MR) {
                    if (this.$MR.size() == 0 && this.$MR.size() == 0) {
                        if (currentThread == this.$OR) {
                            this.$OR = null;
                        }
                        // monitorexit(this.$MR)
                        return;
                    }
                    $no = this.$MR.elementAt(0);
                    this.$MR.removeElementAt(0);
                }
                // monitorexit(this.$MR)
                try {
                    this.$CR($no, null);
                }
                catch ($TM $tm) {
                    System.err.print("\nWarning - veto not processed in queue:" + $tm);
                }
            }
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
