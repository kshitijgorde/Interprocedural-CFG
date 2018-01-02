// 
// Decompiled by Procyon v0.5.30
// 

class random_value
{
    int[] value;
    int total;
    
    random_value(int n, final int n2) {
        this.total = n2 - n + 1;
        this.value = new int[this.total];
        for (int i = 0; i < this.total; ++i) {
            this.value[i] = n;
            ++n;
        }
    }
    
    int getRandomValue() {
        if (this.total == 0) {
            return -1;
        }
        if (this.total == 1) {
            this.total = 0;
            return this.value[0];
        }
        final int n = (int)(Math.random() * this.total);
        final int n2 = this.value[n];
        --this.total;
        for (int i = n; i < this.total; ++i) {
            this.value[i] = this.value[i + 1];
        }
        return n2;
    }
}
