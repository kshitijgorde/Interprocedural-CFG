// 
// Decompiled by Procyon v0.5.30
// 

public class Cell
{
    public final int MIN_VALUE = 1;
    public int maxValue;
    private int \u00c0;
    private boolean[] \u00c1;
    private boolean[] \u00c2;
    
    Cell(final int maxValue) {
        this.maxValue = maxValue;
        this.\u00c1 = new boolean[maxValue + 1];
        this.\u00c2 = new boolean[maxValue + 1];
        this.setAllPossible();
        this.\u00c0 = 0;
    }
    
    public void setAllPossible() {
        for (int i = 1; i <= this.maxValue; ++i) {
            this.\u00c1[i] = true;
        }
    }
    
    public boolean valid(final int n) {
        return n >= 1 && n <= this.maxValue;
    }
    
    public void set(final int n) {
        if (n == 0) {
            this.\u00c0 = n;
        }
        if (this.valid(n)) {
            this.\u00c0 = n;
            for (int i = 1; i <= this.maxValue; ++i) {
                this.\u00c1[i] = false;
            }
        }
    }
    
    public int get() {
        return this.\u00c0;
    }
    
    public void setPossible(final int n) {
        if (this.valid(n)) {
            this.\u00c1[n] = true;
        }
    }
    
    public void setNotPossible(final int n) {
        if (this.valid(n)) {
            this.\u00c1[n] = false;
        }
    }
    
    public boolean isPossible(final int n) {
        return this.valid(n) && this.\u00c1[n];
    }
    
    public void setPossibleAccordingToUser(final int n) {
        if (this.valid(n)) {
            this.\u00c2[n] = true;
        }
    }
    
    public void setNotPossibleAccordingToUser(final int n) {
        if (this.valid(n)) {
            this.\u00c2[n] = false;
        }
    }
    
    public boolean isPossibleAccordingToUser(final int n) {
        return this.valid(n) && this.\u00c2[n];
    }
    
    public int getPossibleValue() {
        for (int i = 1; i <= this.maxValue; ++i) {
            if (this.\u00c1[i]) {
                return i;
            }
        }
        return 0;
    }
    
    public int[] getPossibleValues() {
        int n = 0;
        for (int i = 1; i <= this.maxValue; ++i) {
            if (this.\u00c1[i]) {
                ++n;
            }
        }
        final int[] array = new int[n];
        int n2 = 0;
        for (int j = 1; j <= this.maxValue; ++j) {
            if (this.\u00c1[j]) {
                array[n2] = j;
                ++n2;
            }
        }
        return array;
    }
    
    public int amountPossible() {
        int n = 0;
        for (int i = 1; i <= this.maxValue; ++i) {
            if (this.\u00c1[i]) {
                ++n;
            }
        }
        return n;
    }
}
