// 
// Decompiled by Procyon v0.5.30
// 

class ring
{
    int[] ring_size;
    int[] old_x;
    int[] which_x;
    int[] which_y;
    int[] order;
    int[] which_tower;
    
    public ring(final int n) {
        this.ring_size = new int[10];
        this.old_x = new int[10];
        this.which_x = new int[10];
        this.which_y = new int[10];
        this.order = new int[10];
        this.which_tower = new int[10];
        for (int i = 0; i < n; ++i) {
            this.ring_size[i] = 40 + 20 * i;
            this.which_x[i] = 100;
            this.old_x[i] = this.which_x[i];
            this.which_y[i] = 400 - 30 * (n - i);
            this.order[i] = n - i;
            this.which_tower[i] = 0;
        }
    }
    
    public void reset(final int n) {
        for (int i = 0; i < n; ++i) {
            this.ring_size[i] = 40 + 20 * i;
            this.which_x[i] = 100;
            this.old_x[i] = this.which_x[i];
            this.which_y[i] = 400 - 30 * (n - i);
            this.order[i] = n - i;
            this.which_tower[i] = 0;
        }
    }
}
