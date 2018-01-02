// 
// Decompiled by Procyon v0.5.30
// 

final class Sounds
{
    private static final Sounds[] aSoundsArray325s;
    public static final int[] anIntArray326;
    private static byte[] aByteArray327;
    private static Stream aStream_328;
    private final Class6[] aClass6Array329;
    private int anInt330;
    private int anInt331;
    
    private Sounds() {
        this.aClass6Array329 = new Class6[10];
    }
    
    public static void unpack(final Stream stream) {
        Sounds.aByteArray327 = new byte[441000];
        Sounds.aStream_328 = new Stream(Sounds.aByteArray327);
        Class6.method166();
        while (true) {
            final int unsignedWord = stream.readUnsignedWord();
            if (unsignedWord == 65535) {
                break;
            }
            (Sounds.aSoundsArray325s[unsignedWord] = new Sounds()).method242(stream);
            Sounds.anIntArray326[unsignedWord] = Sounds.aSoundsArray325s[unsignedWord].method243();
        }
    }
    
    public static Stream method241(final int n, final int n2) {
        if (Sounds.aSoundsArray325s[n2] != null) {
            return Sounds.aSoundsArray325s[n2].method244(n);
        }
        return null;
    }
    
    private void method242(final Stream stream) {
        for (int i = 0; i < 10; ++i) {
            if (stream.readUnsignedByte() != 0) {
                --stream.currentOffset;
                (this.aClass6Array329[i] = new Class6()).method169(stream);
            }
        }
        this.anInt330 = stream.readUnsignedWord();
        this.anInt331 = stream.readUnsignedWord();
    }
    
    private int method243() {
        int n = 9999999;
        for (int i = 0; i < 10; ++i) {
            if (this.aClass6Array329[i] != null && this.aClass6Array329[i].anInt114 / 20 < n) {
                n = this.aClass6Array329[i].anInt114 / 20;
            }
        }
        if (this.anInt330 < this.anInt331 && this.anInt330 / 20 < n) {
            n = this.anInt330 / 20;
        }
        if (n == 9999999 || n == 0) {
            return 0;
        }
        for (int j = 0; j < 10; ++j) {
            if (this.aClass6Array329[j] != null) {
                final Class6 class6 = this.aClass6Array329[j];
                class6.anInt114 -= n * 20;
            }
        }
        if (this.anInt330 < this.anInt331) {
            this.anInt330 -= n * 20;
            this.anInt331 -= n * 20;
        }
        return n;
    }
    
    private Stream method244(final int n) {
        final int method245 = this.method245(n);
        Sounds.aStream_328.currentOffset = 0;
        Sounds.aStream_328.writeDWord(1380533830);
        Sounds.aStream_328.method403(36 + method245);
        Sounds.aStream_328.writeDWord(1463899717);
        Sounds.aStream_328.writeDWord(1718449184);
        Sounds.aStream_328.method403(16);
        Sounds.aStream_328.method400(1);
        Sounds.aStream_328.method400(1);
        Sounds.aStream_328.method403(22050);
        Sounds.aStream_328.method403(22050);
        Sounds.aStream_328.method400(1);
        Sounds.aStream_328.method400(8);
        Sounds.aStream_328.writeDWord(1684108385);
        Sounds.aStream_328.method403(method245);
        final Stream aStream_328 = Sounds.aStream_328;
        aStream_328.currentOffset += method245;
        return Sounds.aStream_328;
    }
    
    private int method245(int n) {
        int n2 = 0;
        for (int i = 0; i < 10; ++i) {
            if (this.aClass6Array329[i] != null && this.aClass6Array329[i].anInt113 + this.aClass6Array329[i].anInt114 > n2) {
                n2 = this.aClass6Array329[i].anInt113 + this.aClass6Array329[i].anInt114;
            }
        }
        if (n2 == 0) {
            return 0;
        }
        int n3 = 22050 * n2 / 1000;
        int n4 = 22050 * this.anInt330 / 1000;
        int n5 = 22050 * this.anInt331 / 1000;
        if (n4 < 0 || n4 > n3 || n5 < 0 || n5 > n3 || n4 >= n5) {
            n = 0;
        }
        int n6 = n3 + (n5 - n4) * (n - 1);
        for (int j = 44; j < n6 + 44; ++j) {
            Sounds.aByteArray327[j] = -128;
        }
        for (int k = 0; k < 10; ++k) {
            if (this.aClass6Array329[k] != null) {
                final int n7 = this.aClass6Array329[k].anInt113 * 22050 / 1000;
                final int n8 = this.aClass6Array329[k].anInt114 * 22050 / 1000;
                final int[] method167 = this.aClass6Array329[k].method167(n7, this.aClass6Array329[k].anInt113);
                for (int l = 0; l < n7; ++l) {
                    final byte[] aByteArray327 = Sounds.aByteArray327;
                    final int n9 = l + n8 + 44;
                    aByteArray327[n9] += (byte)(method167[l] >> 8);
                }
            }
        }
        if (n > 1) {
            n4 += 44;
            n5 += 44;
            n3 += 44;
            n6 += 44;
            final int n10 = n6 - n3;
            for (int n11 = n3 - 1; n11 >= n5; --n11) {
                Sounds.aByteArray327[n11 + n10] = Sounds.aByteArray327[n11];
            }
            for (int n12 = 1; n12 < n; ++n12) {
                System.arraycopy(Sounds.aByteArray327, n4, Sounds.aByteArray327, n4 + (n5 - n4) * n12, n5 - n4);
            }
            n6 -= 44;
        }
        return n6;
    }
    
    static {
        aSoundsArray325s = new Sounds[5000];
        anIntArray326 = new int[5000];
    }
}
