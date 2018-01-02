// 
// Decompiled by Procyon v0.5.30
// 

public final class Class18
{
    public final int[] anIntArray342;
    public final int[][] anIntArrayArray343;
    
    public Class18(final Stream stream, final int n) {
        final int unsignedWord = stream.readUnsignedWord();
        this.anIntArray342 = new int[unsignedWord];
        this.anIntArrayArray343 = new int[unsignedWord][];
        for (int i = 0; i < unsignedWord; ++i) {
            this.anIntArray342[i] = stream.readUnsignedWord();
        }
        for (int j = 0; j < unsignedWord; ++j) {
            this.anIntArrayArray343[j] = new int[stream.readUnsignedWord()];
        }
        for (int k = 0; k < unsignedWord; ++k) {
            for (int l = 0; l < this.anIntArrayArray343[k].length; ++l) {
                this.anIntArrayArray343[k][l] = stream.readUnsignedWord();
            }
        }
    }
}
