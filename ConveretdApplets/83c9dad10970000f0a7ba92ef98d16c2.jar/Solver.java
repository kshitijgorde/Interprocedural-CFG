import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

// 
// Decompiled by Procyon v0.5.30
// 

abstract class Solver implements Runnable
{
    boolean prepared;
    boolean wanttostop;
    boolean running;
    int sollen;
    int[] solmoves;
    int[] solamount;
    int[][] positionlist;
    ActionListener main;
    
    public Solver(final ActionListener main) {
        this.prepared = false;
        this.wanttostop = false;
        this.running = false;
        this.solmoves = new int[40];
        this.solamount = new int[40];
        this.main = main;
    }
    
    public void run() {
        if (!this.prepared) {
            this.init();
            this.prepared = true;
            this.doEvent(0);
            return;
        }
        if (!Cubie.settings.solving && !this.wanttostop) {
            Cubie.settings.solving = true;
            this.running = true;
            this.doEvent(4);
            final boolean solve = this.solve();
            this.running = false;
            Cubie.settings.solving = false;
            if (this.wanttostop) {
                this.wanttostop = false;
                this.doEvent(2);
                return;
            }
            if (!solve) {
                this.doEvent(3);
                return;
            }
            Cubie.settings.generator = this.getGenerator();
            this.doEvent(1);
        }
    }
    
    public void stopSolving() {
        if (this.running) {
            this.wanttostop = true;
        }
    }
    
    void doEvent(final int n) {
        this.main.actionPerformed(new ActionEvent(this, 1001, (new String[] { "a", "b", "c", "d", "e" })[n]));
    }
    
    public boolean isPrepared() {
        return this.prepared;
    }
    
    public boolean isRunning() {
        return this.running;
    }
    
    public abstract boolean setPosition(final CubePosition p0, final boolean p1);
    
    protected abstract boolean solve();
    
    protected abstract void init();
    
    public abstract void mix(final CubePosition p0);
    
    public abstract MoveSequence getGenerator();
    
    protected void swap(final int[] array, final int n, final int n2) {
        final int n3 = array[n];
        array[n] = array[n2];
        array[n2] = n3;
    }
    
    protected void cycle(final int[] array, final int n, final int n2, final int n3, final int n4) {
        final int n5 = array[n];
        array[n] = array[n2];
        array[n2] = array[n3];
        array[n3] = array[n4];
        array[n4] = n5;
    }
    
    protected boolean parityOdd(final int[] array, final int n, final int n2) {
        boolean b = false;
        for (int i = 0; i < n2; ++i) {
            for (int j = 0; j < i; ++j) {
                b ^= (array[n + i] < array[n + j]);
            }
        }
        return b;
    }
    
    protected void num2perm(final int[] array, final int n, final int n2, int n3) {
        final int[] array2 = { 0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11 };
        for (int i = 0; i < n2; ++i) {
            int n4 = n3 % (n2 - i);
            n3 = (n3 - n4) / (n2 - i);
            array[n + i] = array2[n4];
            while (++n4 < n2) {
                array2[n4 - 1] = array2[n4];
            }
        }
    }
    
    protected void num2partperm(final int[] array, final int n, final int n2, final int n3, final int n4, int n5) {
        for (int i = 0; i < n3; ++i) {
            int n6 = n5 % (n2 - i);
            n5 = (n5 - n6) / (n2 - i);
            int n7;
            for (n7 = n; n7 < n + n2 && ((array[n7] >= n4 && array[n7] < n4 + n3) || n6 > 0); ++n7) {
                if (array[n7] < n4 || array[n7] >= n4 + n3) {
                    --n6;
                }
            }
            array[n7] = n4 + i;
        }
    }
    
    protected void num2ori(final int[] array, final int n, final int n2, final int n3, int n4) {
        int n5 = 0;
        for (int i = 0; i < n2 - 1; ++i) {
            final int n6 = n4 % n3;
            n5 += n3 - n6;
            n4 = (n4 - n6) / n3;
            array[n + i] = n6;
        }
        array[n + n2 - 1] = n5 % n3;
    }
    
    protected int ori2num(final int[] array, final int n, final int n2, final int n3) {
        int n4 = 0;
        for (int i = n2 - 2; i >= 0; --i) {
            n4 = n4 * n3 + array[n + i] % n3;
        }
        return n4;
    }
    
    protected int perm2num(final int[] array, final int n, final int n2) {
        int n3 = 0;
        for (int i = n2 - 1; i >= 0; --i) {
            int n4 = 0;
            for (int j = i + 1; j < n2; ++j) {
                if (array[n + j] < array[n + i]) {
                    ++n4;
                }
            }
            n3 = n3 * (n2 - i) + n4;
        }
        return n3;
    }
    
    protected int partperm2num(final int[] array, final int n, final int n2, final int n3, final int n4) {
        int n5 = 0;
        for (int i = n4 - 1; i >= 0; --i) {
            int n6 = 0;
            for (int n7 = 0; n7 < n && array[n2 + n7] != n3 + i; ++n7) {
                if (array[n2 + n7] < n3 || array[n2 + n7] > n3 + i) {
                    ++n6;
                }
            }
            n5 = n5 * (n - i) + n6;
        }
        return n5;
    }
}
