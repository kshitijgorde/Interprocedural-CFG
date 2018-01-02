// 
// Decompiled by Procyon v0.5.30
// 

class Towering
{
    int[] num_rings;
    int[] top_size;
    
    public Towering(final int n) {
        this.num_rings = new int[3];
        this.top_size = new int[3];
        this.num_rings[0] = n;
        this.num_rings[1] = 0;
        this.num_rings[2] = 0;
        this.top_size[0] = 40;
        this.top_size[1] = 1000;
        this.top_size[2] = 1000;
    }
    
    public void reset(final int n) {
        this.num_rings[0] = n;
        this.num_rings[1] = 0;
        this.num_rings[2] = 0;
        this.top_size[0] = 40;
        this.top_size[1] = 1000;
        this.top_size[2] = 1000;
    }
}
