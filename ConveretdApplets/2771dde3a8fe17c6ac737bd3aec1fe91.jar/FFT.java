// 
// Decompiled by Procyon v0.5.30
// 

class FFT
{
    double[] wtabf;
    double[] wtabi;
    int size;
    
    FFT(final int size) {
        this.size = size;
        if ((this.size & this.size - 1) != 0x0) {
            System.out.println("size must be power of two!");
        }
        this.calcWTable();
    }
    
    void calcWTable() {
        this.wtabf = new double[this.size];
        this.wtabi = new double[this.size];
        for (int i = 0; i != this.size; i += 2) {
            final double n = 3.1415926535 * i / this.size;
            this.wtabf[i] = Math.cos(n);
            this.wtabf[i + 1] = Math.sin(n);
            this.wtabi[i] = this.wtabf[i];
            this.wtabi[i + 1] = -this.wtabf[i + 1];
        }
    }
    
    void transform(final double[] array, final boolean b) {
        int n = 0;
        final int n2 = this.size * 2;
        if ((this.size & this.size - 1) != 0x0) {
            System.out.println("size must be power of two!");
        }
        for (int i = 0; i != n2; i += 2) {
            if (i > n) {
                final double n3 = array[i];
                array[i] = array[n];
                array[n] = n3;
                final double n4 = array[i + 1];
                array[i + 1] = array[n + 1];
                array[n + 1] = n4;
            }
            int size;
            for (size = this.size; (size & n) != 0x0; n &= ~size, size >>= 1) {}
            n |= size;
        }
        final int n5 = this.size << 1;
        final double[] array2 = b ? this.wtabi : this.wtabf;
        for (int j = 0; j != n2; j += 4) {
            final double n6 = array[j];
            final double n7 = array[j + 1];
            final double n8 = array[j + 2];
            final double n9 = array[j + 3];
            array[j] = n6 + n8;
            array[j + 1] = n7 + n9;
            array[j + 2] = n6 - n8;
            array[j + 3] = n7 - n9;
        }
        final int n10 = n5 >> 1;
        final int n11 = b ? -1 : 1;
        for (int k = 0; k != n2; k += 8) {
            final double n12 = array[k];
            final double n13 = array[k + 1];
            final double n14 = array[k + 4];
            final double n15 = array[k + 5];
            array[k] = n12 + n14;
            array[k + 1] = n13 + n15;
            array[k + 4] = n12 - n14;
            array[k + 5] = n13 - n15;
            final double n16 = array[k + 2];
            final double n17 = array[k + 3];
            final double n18 = array[k + 6] * n11;
            final double n19 = array[k + 7] * n11;
            array[k + 2] = n16 - n19;
            array[k + 3] = n17 + n18;
            array[k + 6] = n16 + n19;
            array[k + 7] = n17 - n18;
        }
        int n20 = n10 >> 1;
        for (int l = 16; l <= n2; l <<= 1) {
            final int n21 = l >> 1;
            n20 >>= 1;
            for (int n22 = 0; n22 != 1000; ++n22) {}
            for (int n23 = 0; n23 < n2; n23 += l) {
                for (int n24 = 0, n25 = n23; n25 != n23 + n21; n25 += 2, n24 += n20) {
                    final double n26 = array2[n24];
                    final double n27 = array2[n24 + 1];
                    final double n28 = array[n25];
                    final double n29 = array[n25 + 1];
                    final int n30 = n25 + n21;
                    final double n31 = array[n30];
                    final double n32 = array[n30 + 1];
                    final double n33 = n31 * n26 - n32 * n27;
                    final double n34 = n31 * n27 + n32 * n26;
                    array[n25] = n28 + n33;
                    array[n25 + 1] = n29 + n34;
                    array[n30] = n28 - n33;
                    array[n30 + 1] = n29 - n34;
                }
            }
        }
    }
}
