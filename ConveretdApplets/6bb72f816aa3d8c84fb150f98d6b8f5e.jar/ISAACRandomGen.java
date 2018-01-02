// 
// Decompiled by Procyon v0.5.30
// 

public final class ISAACRandomGen
{
    public int count;
    public int[] results;
    public int[] memory;
    public int accumulator;
    public int lastResult;
    public int counter;
    
    public ISAACRandomGen(final int[] array) {
        this.memory = new int[256];
        System.arraycopy(array, 0, this.results = new int[256], 0, array.length);
        this.initializeKeySet();
    }
    
    public int getNextKey() {
        if (this.count-- == 0) {
            this.generateNextKeySet();
            this.count = 255;
        }
        return this.results[this.count];
    }
    
    public void generateNextKeySet() {
        this.lastResult += ++this.counter;
        for (int i = 0; i < 256; ++i) {
            final int n = this.memory[i];
            if ((i & 0x3) == 0x0) {
                this.accumulator ^= this.accumulator << 13;
            }
            else if ((i & 0x3) == 0x1) {
                this.accumulator ^= this.accumulator >>> 6;
            }
            else if ((i & 0x3) == 0x2) {
                this.accumulator ^= this.accumulator << 2;
            }
            else if ((i & 0x3) == 0x3) {
                this.accumulator ^= this.accumulator >>> 16;
            }
            this.accumulator += this.memory[i + 128 & 0xFF];
            this.results[i] = (this.lastResult = this.memory[((this.memory[i] = this.memory[(n & 0x3FC) >> 2] + this.accumulator + this.lastResult) >> 8 & 0x3FC) >> 2] + n);
        }
    }
    
    public void initializeKeySet() {
        int n8;
        int n7;
        int n6;
        int n5;
        int n4;
        int n3;
        int n2;
        int n = n2 = (n3 = (n4 = (n5 = (n6 = (n7 = (n8 = -1640531527))))));
        for (int i = 0; i < 4; ++i) {
            final int n9 = n2 ^ n << 11;
            final int n10 = n4 + n9;
            final int n11 = n + n3 ^ n3 >>> 2;
            final int n12 = n5 + n11;
            final int n13 = n3 + n10 ^ n10 << 8;
            final int n14 = n6 + n13;
            n4 = (n10 + n12 ^ n12 >>> 16);
            final int n15 = n7 + n4;
            n5 = (n12 + n14 ^ n14 << 10);
            final int n16 = n8 + n5;
            n6 = (n14 + n15 ^ n15 >>> 4);
            final int n17 = n9 + n6;
            n7 = (n15 + n16 ^ n16 << 8);
            n = n11 + n7;
            n8 = (n16 + n17 ^ n17 >>> 9);
            n3 = n13 + n8;
            n2 = n17 + n;
        }
        for (int j = 0; j < 256; j += 8) {
            final int n18 = n2 + this.results[j];
            final int n19 = n + this.results[j + 1];
            final int n20 = n3 + this.results[j + 2];
            final int n21 = n4 + this.results[j + 3];
            final int n22 = n5 + this.results[j + 4];
            final int n23 = n6 + this.results[j + 5];
            final int n24 = n7 + this.results[j + 6];
            final int n25 = n8 + this.results[j + 7];
            final int n26 = n18 ^ n19 << 11;
            final int n27 = n21 + n26;
            final int n28 = n19 + n20 ^ n20 >>> 2;
            final int n29 = n22 + n28;
            final int n30 = n20 + n27 ^ n27 << 8;
            final int n31 = n23 + n30;
            n4 = (n27 + n29 ^ n29 >>> 16);
            final int n32 = n24 + n4;
            n5 = (n29 + n31 ^ n31 << 10);
            final int n33 = n25 + n5;
            n6 = (n31 + n32 ^ n32 >>> 4);
            final int n34 = n26 + n6;
            n7 = (n32 + n33 ^ n33 << 8);
            n = n28 + n7;
            n8 = (n33 + n34 ^ n34 >>> 9);
            n3 = n30 + n8;
            n2 = n34 + n;
            this.memory[j] = n2;
            this.memory[j + 1] = n;
            this.memory[j + 2] = n3;
            this.memory[j + 3] = n4;
            this.memory[j + 4] = n5;
            this.memory[j + 5] = n6;
            this.memory[j + 6] = n7;
            this.memory[j + 7] = n8;
        }
        for (int k = 0; k < 256; k += 8) {
            final int n35 = n2 + this.memory[k];
            final int n36 = n + this.memory[k + 1];
            final int n37 = n3 + this.memory[k + 2];
            final int n38 = n4 + this.memory[k + 3];
            final int n39 = n5 + this.memory[k + 4];
            final int n40 = n6 + this.memory[k + 5];
            final int n41 = n7 + this.memory[k + 6];
            final int n42 = n8 + this.memory[k + 7];
            final int n43 = n35 ^ n36 << 11;
            final int n44 = n38 + n43;
            final int n45 = n36 + n37 ^ n37 >>> 2;
            final int n46 = n39 + n45;
            final int n47 = n37 + n44 ^ n44 << 8;
            final int n48 = n40 + n47;
            n4 = (n44 + n46 ^ n46 >>> 16);
            final int n49 = n41 + n4;
            n5 = (n46 + n48 ^ n48 << 10);
            final int n50 = n42 + n5;
            n6 = (n48 + n49 ^ n49 >>> 4);
            final int n51 = n43 + n6;
            n7 = (n49 + n50 ^ n50 << 8);
            n = n45 + n7;
            n8 = (n50 + n51 ^ n51 >>> 9);
            n3 = n47 + n8;
            n2 = n51 + n;
            this.memory[k] = n2;
            this.memory[k + 1] = n;
            this.memory[k + 2] = n3;
            this.memory[k + 3] = n4;
            this.memory[k + 4] = n5;
            this.memory[k + 5] = n6;
            this.memory[k + 6] = n7;
            this.memory[k + 7] = n8;
        }
        this.generateNextKeySet();
        this.count = 256;
    }
}
