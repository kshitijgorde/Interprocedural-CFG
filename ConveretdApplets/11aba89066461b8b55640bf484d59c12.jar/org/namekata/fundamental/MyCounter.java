// 
// Decompiled by Procyon v0.5.30
// 

package org.namekata.fundamental;

public class MyCounter
{
    private static final int DONE = -1;
    private static final int GOING = 1;
    private static final int TypeRange = 1;
    private static final int TypeConst = 2;
    private static final int TypeRangeConst = 3;
    private int type;
    private int[] counterMax;
    private int[] counterMin;
    private int[] counters;
    private int[] m;
    private int[] M;
    private int[] RightM;
    private int[] LeftCounter;
    private int length;
    private int total;
    private boolean initOK;
    private int clearpos;
    
    MyCounter(final MyCounter c) {
        this.type = 1;
        this.counterMax = null;
        this.counterMin = null;
        this.counters = null;
        this.m = null;
        this.M = null;
        this.RightM = null;
        this.LeftCounter = null;
        this.length = 1;
        this.total = 0;
        this.initOK = true;
        this.init(c.length, c.total, c.counterMin, c.counterMax);
    }
    
    private void init(final int n, final int t, final int[] counterMin, final int[] counterMax) {
        this.length = n;
        this.total = t;
        this.counters = new int[this.length];
        for (int i = 0; i < this.length; ++i) {
            this.counters[i] = ((counterMin != null) ? counterMin[i] : 0);
        }
        if (counterMin != null) {
            this.counterMin = counterMin;
            this.counterMax = counterMax;
            for (int i = 0; i < this.length; ++i) {
                final int c = counterMin[i];
                if (c > counterMax[i]) {
                    counterMax[i] = c;
                }
            }
            this.type = 1;
            return;
        }
        if ((this.counterMax = counterMax) != null) {
            int sum = 0;
            for (int j = 0; j < this.length; ++j) {
                if (counterMax[j] > this.total) {
                    counterMax[j] = this.total;
                }
                sum += counterMax[j];
            }
            if (sum < this.total) {
                this.initOK = false;
                this.type = 3;
                return;
            }
        }
        if (this.counterMax == null) {
            this.counters[this.length - 1] = this.total;
            this.type = 2;
            return;
        }
        int rest;
        int k;
        for (rest = this.total, k = this.length - 1; rest > counterMax[k]; rest -= counterMax[k], --k) {
            this.counters[k] = counterMax[k];
        }
        this.counters[k] = rest;
        this.type = 3;
        this.initmM();
    }
    
    private void initmM() {
        this.m = new int[this.length];
        this.M = new int[this.length];
        this.RightM = new int[this.length];
        this.LeftCounter = new int[this.length];
        int k = this.length - 1;
        this.RightM[k] = 0;
        while (--k >= 0) {
            this.RightM[k] = this.RightM[k + 1] + this.counterMax[k + 1];
        }
        for (int i = 0; i < this.length; ++i) {
            this.computeLeftCounter(i);
            this.computem(i);
            this.computeM(i);
        }
    }
    
    private void computeLeftCounter(final int n) {
        if (n == 0) {
            this.LeftCounter[n] = 0;
        }
        else {
            this.LeftCounter[n] = this.LeftCounter[n - 1] + this.counters[n - 1];
        }
    }
    
    private void computem(final int n) {
        final int temp = this.total - this.LeftCounter[n] - this.RightM[n];
        this.m[n] = ((temp > 0) ? temp : 0);
    }
    
    private void computeM(final int n) {
        final int temp = this.total - this.LeftCounter[n];
        this.M[n] = ((temp > this.counterMax[n]) ? this.counterMax[n] : temp);
    }
    
    public MyCounter(final int n, final int t) {
        this.type = 1;
        this.counterMax = null;
        this.counterMin = null;
        this.counters = null;
        this.m = null;
        this.M = null;
        this.RightM = null;
        this.LeftCounter = null;
        this.length = 1;
        this.total = 0;
        this.initOK = true;
        this.init((n <= 0) ? 1 : n, (t < 0) ? 0 : t, null, null);
    }
    
    public MyCounter(final int n, final int[] counterMin, final int[] counterMax) {
        this.type = 1;
        this.counterMax = null;
        this.counterMin = null;
        this.counters = null;
        this.m = null;
        this.M = null;
        this.RightM = null;
        this.LeftCounter = null;
        this.length = 1;
        this.total = 0;
        this.initOK = true;
        final int nn = (n <= 0) ? 1 : n;
        if (counterMin == null || counterMax == null) {
            this.init(nn, 0, null, null);
        }
        else if (nn == counterMin.length && nn == counterMax.length) {
            this.init(nn, 0, counterMin, counterMax);
        }
        else {
            this.init(nn, 0, null, null);
        }
    }
    
    public MyCounter(final int n, final int t, final int[] counterMax) {
        this.type = 1;
        this.counterMax = null;
        this.counterMin = null;
        this.counters = null;
        this.m = null;
        this.M = null;
        this.RightM = null;
        this.LeftCounter = null;
        this.length = 1;
        this.total = 0;
        this.initOK = true;
        if (counterMax == null || n == 0) {
            this.initOK = false;
            this.counters = null;
        }
        else if (counterMax.length != n) {
            this.initOK = false;
            this.counters = null;
        }
        else {
            this.init(n, (t < 0) ? 0 : t, null, counterMax);
            if (!this.initOK) {
                this.counters = null;
            }
        }
    }
    
    public int[] getCounter() {
        return this.counters;
    }
    
    public int getTotalCount() {
        if (!this.initOK) {
            return 0;
        }
        int retVal = 0;
        switch (this.type) {
            case 1: {
                retVal = 1;
                for (int i = 0; i < this.length; ++i) {
                    retVal *= this.counterMax[i] - this.counterMin[i] + 1;
                }
                break;
            }
            case 2:
            case 3: {
                final MyCounter temp = new MyCounter(this);
                retVal = 0;
                do {
                    ++retVal;
                } while (temp.inc() >= 0);
                break;
            }
        }
        return retVal;
    }
    
    public int inc() {
        if (!this.initOK) {
            return -1;
        }
        int ret = -1;
        switch (this.type) {
            case 1: {
                ret = this.inc22(this.length - 1);
                break;
            }
            case 2: {
                ret = this.inc11(this.length - 2);
                break;
            }
            case 3: {
                ret = this.inc13();
                break;
            }
        }
        return ret;
    }
    
    private int inc22(final int n) {
        if (n == 0) {
            if (this.counters[0] == this.counterMax[0]) {
                return -1;
            }
            final int[] counters = this.counters;
            final int n2 = 0;
            ++counters[n2];
            return 1;
        }
        else {
            if (this.counters[n] == this.counterMax[n]) {
                this.counters[n] = this.counterMin[n];
                return this.inc22(n - 1);
            }
            final int[] counters2 = this.counters;
            ++counters2[n];
            return 1;
        }
    }
    
    private int inc11(final int n) {
        if (this.counters[0] == this.total) {
            return -1;
        }
        int sum = 0;
        for (int i = 0; i <= n; ++i) {
            sum += this.counters[i];
        }
        if (sum == this.total) {
            this.counters[n] = 0;
            this.inc11((this.clearpos = n) - 1);
            return this.clearpos;
        }
        final int[] counters = this.counters;
        ++counters[n];
        this.inc12();
        return this.length - 1;
    }
    
    private void inc12() {
        int sum = 0;
        for (int i = 0; i < this.length - 1; ++i) {
            sum += this.counters[i];
        }
        this.counters[this.length - 1] = this.total - sum;
    }
    
    private int inc13() {
        int i = this.length - 1;
        while (this.counters[i] >= this.M[i] && --i >= 0) {}
        if (i < 0) {
            return -1;
        }
        final int[] counters = this.counters;
        final int n = i;
        ++counters[n];
        for (int k = i + 1; k < this.length; ++k) {
            this.computeLeftCounter(k);
            this.computem(k);
            this.computeM(k);
            this.counters[k] = this.m[k];
        }
        return 1;
    }
    
    @Override
    public String toString() {
        if (!this.initOK) {
            return "None!";
        }
        String ret = "";
        for (int i = 0; i < this.length; ++i) {
            ret = String.valueOf(ret) + this.counters[i] + ((i == this.length - 1) ? "" : ", ");
        }
        return ret;
    }
}
