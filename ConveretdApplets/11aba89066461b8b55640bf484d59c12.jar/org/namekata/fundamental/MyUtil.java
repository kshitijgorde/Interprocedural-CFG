// 
// Decompiled by Procyon v0.5.30
// 

package org.namekata.fundamental;

public class MyUtil
{
    private static final int BASE = 26;
    private static char baseChar;
    private static boolean[] used;
    
    static {
        MyUtil.baseChar = 'A';
    }
    
    public static int[][] permutation(final int n) {
        final int[][] ret = new int[n][n];
        for (int j = 0; j < n; ++j) {
            for (int i = 0; i < n; ++i) {
                if ((ret[i][j] = a(ret, i, j)) == -1) {
                    --j;
                    break;
                }
            }
        }
        return ret;
    }
    
    public static int[] reverse(final int[] p) {
        if (p == null) {
            return null;
        }
        final int len = p.length;
        final int[] ret = new int[len];
        for (int i = 0; i < len; ++i) {
            ret[p[i]] = i;
        }
        return ret;
    }
    
    private static int a(final int[][] r, final int i, final int j) {
        if (j == 0) {
            return i;
        }
        final int n = r.length;
        final boolean[] used1 = new boolean[n];
        for (int k = 0; k < n; ++k) {
            used1[k] = false;
        }
        for (int k = 0; k < j; ++k) {
            used1[r[i][k]] = true;
        }
        if (i == 0) {
            MyUtil.used = new boolean[n];
            for (int k = 0; k < n; ++k) {
                MyUtil.used[k] = false;
            }
        }
        double rand = Math.random();
        int count = n;
        for (int l = 0; l < n; ++l) {
            if (used1[l] || MyUtil.used[l]) {
                --count;
            }
        }
        rand *= count;
        int randi = (int)rand;
        for (int m = 0; m < n; ++m) {
            if (!MyUtil.used[m] && !used1[m]) {
                if (randi <= 0) {
                    MyUtil.used[m] = true;
                    return m;
                }
                --randi;
            }
        }
        return -1;
    }
    
    public static void toUpper() {
        MyUtil.baseChar = 'A';
    }
    
    public static void toLower() {
        MyUtil.baseChar = 'a';
    }
    
    public static String intToString(final int i) {
        int quo = i;
        final StringBuffer retVal = new StringBuffer();
        while (true) {
            final int res = quo % 26;
            quo /= 26;
            retVal.insert(0, (char)(MyUtil.baseChar + res));
            if (quo == 0) {
                break;
            }
            --quo;
        }
        return retVal.toString();
    }
    
    public static int stringToInt(final String str) {
        boolean valid = true;
        int retValue = 0;
        final char[] chs = str.toCharArray();
        for (int len = chs.length, i = 0; i < len; ++i) {
            final char ch = Character.toUpperCase(chs[i]);
            if (!Character.isUpperCase(ch)) {
                valid = false;
                break;
            }
            retValue = ch - 'A' + ((i < len - 1) ? 1 : 0) + retValue * 26;
        }
        if (valid) {
            return retValue;
        }
        return 0;
    }
    
    public static String[] permutations(final int n) {
        if (n > 6) {
            return new String[0];
        }
        final int[] minCounter = new int[n];
        final int[] maxCounter = new int[n];
        for (int i = 0; i < n; ++i) {
            minCounter[i] = 0;
            maxCounter[i] = n - 1;
        }
        int len = 1;
        for (int j = 2; j <= n; ++j) {
            len *= j;
        }
        final String[] retVal = new String[len];
        int k = 0;
        final MyCounter myCounter = new MyCounter(n, minCounter, maxCounter);
        do {
            final int[] counter = myCounter.getCounter();
            if (isPermutation(counter)) {
                retVal[k++] = permutation(counter);
            }
        } while (myCounter.inc() > 0);
        return retVal;
    }
    
    private static boolean isPermutation(final int[] counter) {
        final int len = counter.length;
        final int[] tmp = new int[len];
        for (int i = 0; i < len; ++i) {
            tmp[i] = 1;
        }
        for (int i = 0; i < len; ++i) {
            tmp[counter[i]] = 0;
        }
        int sum = 0;
        for (int j = 0; j < len; ++j) {
            sum += tmp[j];
        }
        return sum == 0;
    }
    
    private static String permutation(final int[] counter) {
        final StringBuffer retVal = new StringBuffer();
        for (int i = 0; i < counter.length; ++i) {
            retVal.append((char)(MyUtil.baseChar + counter[i]));
        }
        return retVal.toString();
    }
}
