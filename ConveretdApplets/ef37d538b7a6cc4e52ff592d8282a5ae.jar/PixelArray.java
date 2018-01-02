// 
// Decompiled by Procyon v0.5.30
// 

public final class PixelArray
{
    static final int DEFAULT_CAPACITY = 16;
    public int red;
    public int green;
    public int blue;
    public int max;
    public int min;
    public int delta;
    public int offset;
    public int size;
    public int capacity;
    public int[] pixels;
    public PixelArray next;
    
    public PixelArray(final int max, final int min, final int max2, final int n, final PixelArray next) {
        this.next = next;
        this.red = max;
        this.green = min;
        this.blue = max2;
        if (max >= min && min >= max2) {
            this.max = max;
            this.min = max2;
            this.offset = ((max == max2) ? 0 : ((min - max2) * n / (max - max2)));
        }
        else if (min >= max && max >= max2) {
            this.max = min;
            this.min = max2;
            this.offset = ((min == max2) ? 0 : ((min - max) * n / (min - max2) + n));
        }
        else if (min >= max2 && max2 >= max) {
            this.max = min;
            this.min = max;
            this.offset = ((min == max) ? 0 : ((max2 - max) * n / (min - max) + n * 2));
        }
        else if (max2 >= min && min >= max) {
            this.max = max2;
            this.min = max;
            this.offset = ((max2 == max) ? 0 : ((max2 - min) * n / (max2 - max) + n * 3));
        }
        else if (max2 >= max && max >= min) {
            this.max = max2;
            this.min = min;
            this.offset = ((max2 == min) ? 0 : ((max - min) * n / (max2 - min) + n * 4));
        }
        else {
            this.offset = (((this.max = max) == (this.min = min)) ? 0 : ((max - max2) * n / (max - min) + n * 5));
        }
        this.delta = this.max - this.min;
        this.size = 0;
        this.capacity = 16;
        this.pixels = new int[this.capacity];
    }
    
    public void add(final int n) {
        this.pixels[this.size++] = n;
        if (this.size == this.capacity) {
            this.resize();
        }
    }
    
    private void resize() {
        this.capacity = this.capacity * 3 >> 1;
        final int[] pixels = new int[this.capacity];
        System.arraycopy(this.pixels, 0, pixels, 0, this.size);
        this.pixels = pixels;
    }
}
