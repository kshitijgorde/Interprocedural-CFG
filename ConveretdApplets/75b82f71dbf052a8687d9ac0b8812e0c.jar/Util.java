// 
// Decompiled by Procyon v0.5.30
// 

class Util
{
    static final float PI = 3.1415927f;
    static final float TWO_PI = 6.2831855f;
    static final float HALF_PI = 1.5707964f;
    static final float EPSILON = 1.0E-4f;
    
    static final float limit(final float val, final float min, final float max) {
        return Math.min(Math.max(val, min), max);
    }
    
    static final float wrap(final float val, final float min, final float max) {
        return (val - min) % (max - min) + min;
    }
    
    static final float[] delta(final float[] p0, final float[] p1) {
        if (p0[0] - p1[0] > 3.1415927f) {
            final int n = 0;
            p0[n] -= 6.2831855f;
        }
        else if (p1[0] - p0[0] > 3.1415927f) {
            final int n2 = 0;
            p0[n2] += 6.2831855f;
        }
        if (p0[2] - p1[2] > 3.1415927f) {
            final int n3 = 2;
            p0[n3] -= 6.2831855f;
        }
        else if (p1[2] - p0[2] > 3.1415927f) {
            final int n4 = 2;
            p0[n4] += 6.2831855f;
        }
        final float[] dp = new float[p0.length];
        dp[0] = p1[0] - p0[0];
        dp[1] = p1[1] - p0[1];
        dp[2] = p1[2] - p0[2];
        dp[3] = (float)(Math.log(p1[3]) - Math.log(p0[3]));
        return dp;
    }
    
    static final float[] delta(final float[] p0, final float[] p1, final int method) {
        if (method == 2) {
            return delta(p0, p1);
        }
        if (p0[0] - p1[0] > 3.1415927f) {
            final int n = 0;
            p0[n] -= 6.2831855f;
        }
        else if (p1[0] - p0[0] > 3.1415927f) {
            final int n2 = 0;
            p0[n2] += 6.2831855f;
        }
        if (p0[2] - p1[2] > 3.1415927f) {
            final int n3 = 2;
            p0[n3] -= 6.2831855f;
        }
        else if (p1[2] - p0[2] > 3.1415927f) {
            final int n4 = 2;
            p0[n4] += 6.2831855f;
        }
        final float[] dp = new float[p0.length];
        dp[0] = p1[0] - p0[0];
        dp[1] = p1[1] - p0[1];
        dp[2] = p1[2] - p0[2];
        dp[3] = (float)(Math.log(p1[3]) - Math.log(p0[3]));
        return dp;
    }
    
    static final float dot(final float[] a, final float[] b) {
        return a[0] * b[0] + a[1] * b[1] + a[2] * b[2];
    }
    
    static final void cross(final float[] a, final float[] b, final float[] c) {
        c[0] = a[1] * b[2] - a[2] * b[1];
        c[1] = a[2] * b[0] - a[0] * b[2];
        c[2] = a[0] * b[1] - a[1] * b[0];
    }
    
    static final boolean equal(final float[] a, final float[] b) {
        for (int i = 0; i < a.length; ++i) {
            if (a[i] != b[i]) {
                return false;
            }
        }
        return true;
    }
    
    static final float[] copy(final float[] vp) {
        final float[] p = new float[vp.length];
        System.arraycopy(vp, 0, p, 0, vp.length);
        return p;
    }
    
    public static final boolean compare(final float[] a, final float[] b) {
        for (int i = 0; i < a.length; ++i) {
            if (Math.abs(a[i] - b[i]) > 1.0E-4f) {
                return false;
            }
        }
        return true;
    }
    
    public static final boolean compare(final float[] a, final float[] b, final float[] delta) {
        for (int i = 0; i < a.length; ++i) {
            if (Math.abs(a[i] - b[i]) > delta[i]) {
                return false;
            }
        }
        return true;
    }
}
