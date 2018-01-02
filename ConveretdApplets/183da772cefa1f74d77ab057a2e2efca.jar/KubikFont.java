// 
// Decompiled by Procyon v0.5.30
// 

public class KubikFont
{
    private static int X;
    private static int Y;
    private static int Z;
    private static int[][][] local;
    private static int[][][] surface;
    private static int[] width;
    private static int height;
    private static int ZOOM;
    public static int SPACEWIDTH;
    public static int LINEHEIGHT;
    
    public static int[][] getLocal(final char c) {
        final int length = KubikFont.local[c - 'A'].length;
        final int[][] array = new int[2 * length][3];
        for (int i = 0; i < length; ++i) {
            array[i][KubikFont.X] = KubikFont.ZOOM * KubikFont.local[c - 'A'][i][KubikFont.X];
            array[i][KubikFont.Y] = KubikFont.ZOOM * KubikFont.local[c - 'A'][i][KubikFont.Y];
            array[i][KubikFont.Z] = -KubikFont.ZOOM;
            array[i + length][KubikFont.X] = KubikFont.ZOOM * KubikFont.local[c - 'A'][i][KubikFont.X];
            array[i + length][KubikFont.Y] = KubikFont.ZOOM * KubikFont.local[c - 'A'][i][KubikFont.Y];
            array[i + length][KubikFont.Z] = KubikFont.ZOOM;
        }
        return array;
    }
    
    public static int[][] getLocal(final String s) {
        int n = 0;
        for (int i = 0; i < s.length(); ++i) {
            n += KubikFont.local[s.charAt(i) - 'A'].length;
        }
        final int[][] array = new int[2 * n][3];
        int n2 = 0;
        for (int j = 0; j < s.length(); ++j) {
            for (int k = 0; k < KubikFont.local[s.charAt(j) - 'A'].length; ++k) {
                array[n2][KubikFont.X] = KubikFont.ZOOM * KubikFont.local[s.charAt(j) - 'A'][k][KubikFont.X];
                array[n2][KubikFont.Y] = KubikFont.ZOOM * KubikFont.local[s.charAt(j) - 'A'][k][KubikFont.Y];
                array[n2][KubikFont.Z] = -KubikFont.ZOOM;
                array[n2 + n][KubikFont.X] = KubikFont.ZOOM * KubikFont.local[s.charAt(j) - 'A'][k][KubikFont.X];
                array[n2 + n][KubikFont.Y] = KubikFont.ZOOM * KubikFont.local[s.charAt(j) - 'A'][k][KubikFont.Y];
                array[n2 + n][KubikFont.Z] = KubikFont.ZOOM;
                ++n2;
            }
        }
        return array;
    }
    
    public static int[][] getSurface(final char c) {
        final int length = KubikFont.surface[c - 'A'].length;
        int n = 2 * length;
        for (int i = 0; i < length; ++i) {
            n += KubikFont.surface[c - 'A'][i].length;
        }
        final int[][] array = new int[n][];
        for (int j = 0; j < length; ++j) {
            final int length2 = KubikFont.surface[c - 'A'][j].length;
            array[j] = new int[length2];
            for (int k = 0; k < length2; ++k) {
                array[j][k] = KubikFont.surface[c - 'A'][j][k];
            }
            array[j + length] = new int[length2];
            for (int l = 0; l < length2; ++l) {
                array[j + length][l] = array[j][l] + KubikFont.local[c - 'A'].length;
            }
        }
        int n2 = 2 * length;
        for (int n3 = 0; n3 < length; ++n3) {
            for (int length3 = array[n3].length, n4 = 0; n4 < length3; ++n4, ++n2) {
                (array[n2] = new int[4])[0] = array[n3][n4];
                array[n2][1] = array[n3][(n4 + 1) % length3];
                array[n2][2] = array[n3 + length][(n4 + 1) % length3];
                array[n2][3] = array[n3 + length][n4];
            }
        }
        return array;
    }
    
    public static int getWidth(final char c) {
        return KubikFont.width[c - 'A'] * KubikFont.ZOOM;
    }
    
    static {
        KubikFont.Y = 1;
        KubikFont.Z = 2;
        KubikFont.local = new int[][][] { { { -3, -5 }, { 3, -5 }, { 3, 5 }, { 1, 5 }, { 1, 1 }, { -1, 1 }, { -1, 5 }, { -3, 5 }, { -1, -3 }, { 1, -3 }, { 1, -1 }, { -1, -1 } }, { { -3, -5 }, { 3, -5 }, { 3, -2 }, { 1, 0 }, { 3, 2 }, { 3, 5 }, { -3, 5 }, { -1, -3 }, { 1, -3 }, { -1, -1 }, { -1, 1 }, { 1, 3 }, { -1, 3 } }, { { -3, -5 }, { 3, -5 }, { 3, -3 }, { -1, -3 }, { -1, 3 }, { 3, 3 }, { 3, 5 }, { -3, 5 } }, { { -3, -5 }, { 0, -5 }, { 4, -1 }, { 4, 1 }, { 0, 5 }, { -3, 5 }, { -1, -3 }, { 2, 0 }, { -1, 3 } }, { { -3, -5 }, { 3, -5 }, { 3, -3 }, { -1, -3 }, { -1, -1 }, { 3, -1 }, { 3, 1 }, { -1, 1 }, { -1, 3 }, { 3, 3 }, { 3, 5 }, { -3, 5 } }, { { -3, -5 }, { 3, -5 }, { 3, -3 }, { -1, -3 }, { -1, -1 }, { 3, -1 }, { 3, 1 }, { -1, 1 }, { -1, 5 }, { -3, 5 } }, { { -3, -5 }, { 3, -5 }, { 3, -3 }, { -1, -3 }, { -1, 3 }, { 1, 3 }, { 1, -1 }, { 3, -1 }, { 3, 5 }, { -3, 5 } }, { { -3, -5 }, { -1, -5 }, { -1, -1 }, { 1, -1 }, { 1, -5 }, { 3, -5 }, { 3, 5 }, { 1, 5 }, { 1, 1 }, { -1, 1 }, { -1, 5 }, { -3, 5 } }, { { -1, -5 }, { 1, -5 }, { 1, 5 }, { -1, 5 } }, { { -3, 1 }, { -1, 1 }, { -1, 3 }, { 1, 3 }, { 1, -5 }, { 3, -5 }, { 3, 5 }, { -3, 5 } }, { { -3, -5 }, { -1, -5 }, { -1, -1 }, { 1, -5 }, { 4, -5 }, { 1, 0 }, { 4, 5 }, { 1, 5 }, { -1, 1 }, { -1, 5 }, { -3, 5 } }, { { -3, -5 }, { -1, -5 }, { -1, 3 }, { 3, 3 }, { 3, 5 }, { -3, 5 } }, { { -4, -5 }, { -2, -5 }, { 0, -1 }, { 2, -5 }, { 4, -5 }, { 4, 5 }, { 2, 5 }, { 2, -1 }, { 0, 3 }, { -2, -1 }, { -2, 5 }, { -4, 5 } }, { { -3, -5 }, { -1, -5 }, { 1, 0 }, { 1, -5 }, { 3, -5 }, { 3, 5 }, { 1, 5 }, { -1, 0 }, { -1, 5 }, { -3, 5 } }, { { -3, -5 }, { 3, -5 }, { 3, 5 }, { -3, 5 }, { -1, -3 }, { 1, -3 }, { 1, 3 }, { -1, 3 } }, { { -3, -5 }, { 3, -5 }, { 3, 1 }, { -1, 1 }, { -1, 5 }, { -3, 5 }, { -1, -3 }, { 1, -3 }, { 1, -1 }, { -1, -1 } }, { { -3, -5 }, { 3, -5 }, { 3, 4 }, { 4, 6 }, { 3, 7 }, { 2, 5 }, { -3, 5 }, { -1, -3 }, { 1, -3 }, { 1, 0 }, { 0, 1 }, { 1, 3 }, { -1, 3 } }, { { -3, -5 }, { 3, -5 }, { 3, 1 }, { 1, 1 }, { 4, 5 }, { 1, 5 }, { -1, 2 }, { -1, 5 }, { -3, 5 }, { -1, -3 }, { 1, -3 }, { 1, -1 }, { -1, -1 } }, { { -3, -5 }, { 3, -5 }, { 3, -3 }, { -1, -3 }, { -1, -1 }, { 3, -1 }, { 3, 5 }, { -3, 5 }, { -3, 3 }, { 1, 3 }, { 1, 1 }, { -3, 1 } }, { { -3, -5 }, { 3, -5 }, { 3, -3 }, { 1, -3 }, { 1, 5 }, { -1, 5 }, { -1, -3 }, { -3, -3 } }, { { -3, -5 }, { -1, -5 }, { -1, 3 }, { 1, 3 }, { 1, -5 }, { 3, -5 }, { 3, 5 }, { -3, 5 } }, { { -3, -5 }, { -1, -5 }, { 0, 0 }, { 1, -5 }, { 3, -5 }, { 1, 5 }, { -1, 5 } }, { { -4, 5 }, { -2, 5 }, { 0, 1 }, { 2, 5 }, { 4, 5 }, { 4, -5 }, { 2, -5 }, { 2, 1 }, { 0, -3 }, { -2, 1 }, { -2, -5 }, { -4, -5 } }, { { -3, -5 }, { -1, -5 }, { 0, -3 }, { 1, -5 }, { 3, -5 }, { 1, 0 }, { 3, 5 }, { 1, 5 }, { 0, 3 }, { -1, 5 }, { -3, 5 }, { -1, 0 } }, { { -3, -5 }, { -1, -5 }, { 0, -3 }, { 1, -5 }, { 3, -5 }, { 1, 0 }, { 1, 5 }, { -1, 5 }, { -1, 0 } }, { { -3, -5 }, { 3, -5 }, { 3, -3 }, { 0, 3 }, { 3, 3 }, { 3, 5 }, { -3, 5 }, { -3, 3 }, { 0, -3 }, { -3, -3 } } };
        KubikFont.surface = new int[][][] { { { 0, 1, 2, 3, 4, 5, 6, 7 }, { 8, 9, 10, 11 } }, { { 0, 1, 2, 3, 4, 5, 6 }, { 7, 8, 9 }, { 10, 11, 12 } }, { { 0, 1, 2, 3, 4, 5, 6, 7 } }, { { 0, 1, 2, 3, 4, 5 }, { 6, 7, 8 } }, { { 0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11 } }, { { 0, 1, 2, 3, 4, 5, 6, 7, 8, 9 } }, { { 0, 1, 2, 3, 4, 5, 6, 7, 8, 9 } }, { { 0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11 } }, { { 0, 1, 2, 3 } }, { { 0, 1, 2, 3, 4, 5, 6, 7 } }, { { 0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10 } }, { { 0, 1, 2, 3, 4, 5 } }, { { 0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11 } }, { { 0, 1, 2, 3, 4, 5, 6, 7, 8, 9 } }, { { 0, 1, 2, 3 }, { 4, 5, 6, 7 } }, { { 0, 1, 2, 3, 4, 5 }, { 6, 7, 8, 9 } }, { { 0, 1, 2, 3, 4, 5, 6 }, { 7, 8, 9, 10, 11, 12 } }, { { 0, 1, 2, 3, 4, 5, 6, 7, 8 }, { 9, 10, 11, 12 } }, { { 0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11 } }, { { 0, 1, 2, 3, 4, 5, 6, 7 } }, { { 0, 1, 2, 3, 4, 5, 6, 7 } }, { { 0, 1, 2, 3, 4, 5, 6 } }, { { 0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11 } }, { { 0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11 } }, { { 0, 1, 2, 3, 4, 5, 6, 7, 8 } }, { { 0, 1, 2, 3, 4, 5, 6, 7, 8, 9 } } };
        KubikFont.width = new int[] { 6, 6, 6, 6, 6, 6, 6, 6, 2, 6, 6, 6, 8, 6, 6, 6, 6, 6, 6, 6, 6, 6, 8, 6, 6, 6 };
        KubikFont.height = 10;
        KubikFont.ZOOM = 10;
        KubikFont.SPACEWIDTH = 2 * KubikFont.ZOOM;
        KubikFont.LINEHEIGHT = (KubikFont.height + 2) * KubikFont.ZOOM;
    }
}
