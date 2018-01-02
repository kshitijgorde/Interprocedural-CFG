// 
// Decompiled by Procyon v0.5.30
// 

package matt;

public class EditDistance
{
    private static int Minimum(final int a, final int b, final int c) {
        int mi = a;
        if (b < mi) {
            mi = b;
        }
        if (c < mi) {
            mi = c;
        }
        return mi;
    }
    
    public static float LD(final String s, final String t) {
        final int n = s.length();
        final int m = t.length();
        if (n == 0) {
            return m;
        }
        if (m == 0) {
            return n;
        }
        final int[][] d = new int[n + 1][m + 1];
        for (int i = 0; i <= n; ++i) {
            d[i][0] = i;
        }
        for (int j = 0; j <= m; ++j) {
            d[0][j] = j;
        }
        for (int i = 1; i <= n; ++i) {
            final char s_i = s.charAt(i - 1);
            for (int j = 1; j <= m; ++j) {
                final char t_j = t.charAt(j - 1);
                int cost;
                if (s_i == t_j) {
                    cost = 0;
                }
                else {
                    cost = 1;
                }
                d[i][j] = Minimum(d[i - 1][j] + 1, d[i][j - 1] + 1, d[i - 1][j - 1] + cost);
            }
        }
        for (int ii = 0; ii < d.length; ++ii) {
            for (int jj = 0; jj < d[ii].length; ++jj) {
                System.out.print(d[ii][jj] + "\t");
            }
            System.out.println();
        }
        final float ed = d[n][m];
        return ed;
    }
    
    public static int getLevenshteinDistance(final String s, final String t) {
        if (s == null || t == null) {
            throw new IllegalArgumentException("Strings must not be null");
        }
        final int n = s.length();
        final int m = t.length();
        if (n == 0) {
            return m;
        }
        if (m == 0) {
            return n;
        }
        int[] p = new int[n + 1];
        int[] d = new int[n + 1];
        for (int i = 0; i <= n; ++i) {
            p[i] = i;
        }
        for (int j = 1; j <= m; ++j) {
            final char t_j = t.charAt(j - 1);
            d[0] = j;
            for (int i = 1; i <= n; ++i) {
                final int cost = (s.charAt(i - 1) != t_j) ? 1 : 0;
                d[i] = Math.min(Math.min(d[i - 1] + 1, p[i] + 1), p[i - 1] + cost);
            }
            final int[] _d = p;
            p = d;
            d = _d;
        }
        return p[n];
    }
    
    public static float editDistance(final String feature1, final String feature2) {
        final int length_1 = feature1.toString().length();
        final int length_2 = feature2.toString().length();
        int difference = 0;
        if (length_1 == 0) {
            return length_1;
        }
        if (length_2 == 0) {
            return length_2;
        }
        final int[][] d = new int[length_1 + 1][length_2 + 1];
        for (int i = 1; i <= length_1; ++i) {
            final char sc = feature1.toString().charAt(i - 1);
            for (int j = 1; j <= length_2; ++j) {
                final int v = d[i - 1][j - 1];
                if (feature2.toString().charAt(j - 1) != sc) {
                    difference = 1;
                }
                else {
                    difference = 0;
                }
                d[i][j] = Math.min(Math.min(d[i - 1][j] + 1, d[i][j - 1] + 1), v + difference);
            }
        }
        final float ed = d[length_1][length_2];
        return ed;
    }
    
    public static int[] edSubString(final String pattern, final String text) {
        final int pLength = pattern.length();
        final int tLength = text.length();
        int difference = 0;
        if (pLength == 0) {
            return null;
        }
        if (tLength == 0) {
            return null;
        }
        final int[][] d = new int[pLength + 1][tLength + 1];
        for (int i = 0; i < tLength + 1; ++i) {
            d[0][i] = 0;
        }
        for (int i = 0; i < pLength + 1; ++i) {
            d[i][0] = i;
        }
        for (int i = 1; i <= pLength; ++i) {
            final char sc = pattern.charAt(i - 1);
            for (int j = 1; j <= tLength; ++j) {
                final int v = d[i - 1][j - 1];
                if (text.charAt(j - 1) != sc && sc != 'Z') {
                    difference = 1;
                }
                else {
                    difference = 0;
                }
                d[i][j] = Math.min(Math.min(d[i - 1][j] + 1, d[i][j - 1] + 1), v + difference);
            }
        }
        return d[pLength];
    }
    
    public static float minEdSemex(final int[] pattern, final int[] text) {
        final int pLength = pattern.length;
        final int tLength = text.length;
        int difference = 0;
        if (pLength == 0) {
            return -1.0f;
        }
        if (tLength == 0) {
            return -1.0f;
        }
        final int[][] d = new int[pLength + 1][tLength + 1];
        for (int i = 0; i < tLength + 1; ++i) {
            d[0][i] = 0;
        }
        for (int i = 0; i < pLength + 1; ++i) {
            d[i][0] = i;
        }
        for (int i = 1; i <= pLength; ++i) {
            final int sc = pattern[i - 1];
            for (int j = 1; j <= tLength; ++j) {
                final int v = d[i - 1][j - 1];
                if (j - 2 < 0 || i - 2 < 0) {
                    difference = 1;
                }
                else if (text[j - 1] - text[j - 2] != pattern[i - 1] - pattern[i - 2]) {
                    difference = 1;
                }
                else {
                    difference = 0;
                }
                d[i][j] = Math.min(Math.min(d[i - 1][j] + 1, d[i][j - 1] + 1), v + difference);
            }
        }
        System.out.println();
        final int[] lastRow = d[pLength];
        int min = Integer.MAX_VALUE;
        for (int k = 1; k < tLength + 1; ++k) {
            final int c = lastRow[k];
            if (c < min) {
                min = c;
            }
        }
        return min;
    }
    
    public static float minEdSubString(final String pattern, final String text) {
        final int[] lastRow = edSubString(pattern, text);
        int min = Integer.MAX_VALUE;
        for (int tLength = text.length(), i = 0; i < tLength + 1; ++i) {
            final int c = lastRow[i];
            if (c < min) {
                min = c;
            }
        }
        final float ed = min;
        return ed;
    }
    
    public static void main(final String[] args) {
        final int[] text = { 72, 71, 72, 69, 71, 72, 71, 69, 67, 64, 67, 69, 67, 69, 74, 74, 73, 74, 76, 77, 76, 74, 72, 69, 67, 66, 67, 69, 71, 72, 71, 72, 69, 71, 72, 71, 69, 67, 64, 66, 67, 64, 62, 64, 66, 67, 69, 71, 72, 69, 74, 72, 69, 67, 66, 67, 69, 71, 72, 71, 72, 69, 71, 72, 71, 69, 67, 64, 67, 69, 67, 69, 74, 74, 73, 74, 76, 77, 76, 74, 72, 69, 67, 66, 67, 69, 71, 72, 71, 72, 69, 71, 72, 71, 69, 67, 64, 66, 67, 64, 62, 64, 66, 67, 69, 71, 72, 69, 74, 72, 69, 67, 66, 62, 62, 76, 79, 81, 79, 81, 79, 81, 79, 76, 79, 81, 79, 76, 74, 71, 74, 76, 81, 83, 81, 83, 79, 81, 79, 76, 81, 81, 79, 76, 74, 71, 74, 76, 79, 81, 79, 81, 79, 81, 79, 76, 79, 79, 76, 74, 76, 78, 79, 81, 78, 79, 76, 78, 74, 76, 73, 74, 72, 69, 67, 66, 62, 62, 76, 79, 81, 79, 81, 79, 81, 79, 76, 79, 81, 79, 76, 74, 71, 74, 76, 81, 83, 81, 83, 79, 81, 79, 76, 81, 81, 79, 76, 74, 71, 74, 76, 79, 81, 79, 81, 79, 81, 79, 76, 79, 79, 76, 74, 76, 78, 79, 81, 78, 79, 76, 78, 74, 76, 73, 74, 72, 69, 67, 66, 67, 69, 71 };
        final int[] pattern = { 36, 69, 71, 72, 72, 72, 72, 71, 72, 69, 71, 72, 69, 71, 69, 67, 64, 67, 69, 67, 69, 72, 74, 73, 74, 78, 76, 74, 73, 69, 67, 64, 66, 67, 69, 71, 72, 72, 71, 72, 69, 71, 72, 69, 70, 69, 67, 64, 67, 67, 69, 67, 62, 64, 66, 67, 69, 71, 72, 71, 69, 74, 72, 69, 67, 69, 62, 71, 72, 72, 71, 72, 69, 71, 72, 69, 70, 69, 67, 64, 67, 69, 67, 69, 74, 72, 74, 78, 77, 76, 74, 72, 69, 66, 81, 71, 72, 71, 72, 69, 71, 72, 69, 71, 69, 67, 64, 67, 70, 67, 62, 64, 66, 69, 71, 72, 71, 69, 74, 72, 69, 67, 69, 62, 69, 76, 79, 83, 79, 69, 79, 83, 79, 79, 76, 79, 82, 79, 67, 76, 74, 72, 74, 76, 69, 81, 83, 81, 81, 83, 72, 81, 79, 64, 79, 81, 82, 81, 79, 76, 74, 72, 74, 76, 79, 82, 79, 81, 81, 79, 76, 78, 81, 78, 76, 74, 78, 79, 81, 69, 78, 79, 76, 78, 74, 76, 77, 71, 72, 74, 72, 69, 67, 69, 74, 76, 79, 82, 79, 79, 81, 79, 82, 81, 79, 76, 79, 82, 81, 79, 79, 76, 74, 72, 74, 76, 81, 83, 81, 83, 83, 81, 79, 76, 80, 81, 82, 81, 79, 76, 74, 72, 74, 76, 79, 81, 79, 79, 81, 79, 76, 78, 69, 78, 78, 76, 74, 78, 79, 81, 78, 79, 76, 77, 78, 74, 76, 77, 71, 72, 74, 72, 69, 66, 69, 71, 72, 72 };
        System.out.println("Transposition invariant ED: " + minEdSemex(pattern, text));
    }
}
