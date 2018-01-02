// 
// Decompiled by Procyon v0.5.30
// 

package prefuse.util.display;

import prefuse.util.ArrayLib;
import java.util.Arrays;
import prefuse.visual.VisualItem;
import prefuse.visual.sort.ItemSorter;

public class RenderingQueue
{
    private static final int DEFAULT_SIZE = 256;
    public ItemSorter sort;
    public VisualItem[] ritems;
    public int[] rscores;
    public int rsize;
    public VisualItem[] pitems;
    public int[] pscores;
    public int psize;
    public boolean psorted;
    static transient VisualItem[] items_buf;
    static transient int[] scores_buf;
    
    public RenderingQueue() {
        this.sort = new ItemSorter();
        this.ritems = new VisualItem[256];
        this.rscores = new int[256];
        this.rsize = 0;
        this.pitems = new VisualItem[256];
        this.pscores = new int[256];
        this.psize = 0;
        this.psorted = false;
    }
    
    public void clear() {
        Arrays.fill(this.ritems, 0, this.rsize, null);
        Arrays.fill(this.pitems, 0, this.psize, null);
        this.rsize = 0;
        this.psize = 0;
    }
    
    public void addToRenderQueue(final VisualItem visualItem) {
        if (this.ritems.length == this.rsize) {
            final int n = 3 * this.ritems.length / 2 + 1;
            final VisualItem[] ritems = new VisualItem[n];
            final int[] rscores = new int[n];
            System.arraycopy(this.ritems, 0, ritems, 0, this.rsize);
            System.arraycopy(this.rscores, 0, rscores, 0, this.rsize);
            this.ritems = ritems;
            this.rscores = rscores;
        }
        this.ritems[this.rsize] = visualItem;
        this.rscores[this.rsize++] = ((this.sort != null) ? this.sort.score(visualItem) : 0);
    }
    
    public void addToPickingQueue(final VisualItem visualItem) {
        if (this.pitems.length == this.psize) {
            final int n = 3 * this.pitems.length / 2 + 1;
            final VisualItem[] pitems = new VisualItem[n];
            final int[] pscores = new int[n];
            System.arraycopy(this.pitems, 0, pitems, 0, this.psize);
            System.arraycopy(this.pscores, 0, pscores, 0, this.psize);
            this.pitems = pitems;
            this.pscores = pscores;
        }
        this.pitems[this.psize] = visualItem;
        this.pscores[this.psize++] = ((this.sort != null) ? this.sort.score(visualItem) : 0);
        this.psorted = false;
    }
    
    public void sortRenderQueue() {
        this.sort(this.ritems, this.rscores, this.rsize);
    }
    
    public void sortPickingQueue() {
        this.sort(this.pitems, this.pscores, this.psize);
        this.psorted = true;
    }
    
    private void sort(final VisualItem[] array, final int[] array2, final int n) {
        if (this.sort == null) {
            return;
        }
        if (RenderingQueue.items_buf == null || RenderingQueue.items_buf.length < n) {
            RenderingQueue.items_buf = new VisualItem[array.length];
            RenderingQueue.scores_buf = new int[array2.length];
        }
        ArrayLib.sort(array2, array, RenderingQueue.scores_buf, RenderingQueue.items_buf, 0, n);
    }
}
