// 
// Decompiled by Procyon v0.5.30
// 

public class Global
{
    public static int pieceSize;
    public static int psplus;
    public static int deep;
    
    public static void setPieceSize(final int pieceSize) {
        Global.pieceSize = pieceSize;
    }
    
    public static int randomInt(final int n) {
        return (int)(n * Math.random());
    }
    
    public static int[] rotateSet(final int[] array, final int n, final int n2) {
        final int[] array2 = new int[n * n2];
        for (int i = 0; i < n; ++i) {
            for (int j = 0; j < n2; ++j) {
                array2[i + n * j] = array[(n - i - 1) * n2 + j];
            }
        }
        return array2;
    }
}
