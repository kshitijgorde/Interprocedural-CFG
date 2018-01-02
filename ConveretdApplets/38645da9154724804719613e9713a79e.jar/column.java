// 
// Decompiled by Procyon v0.5.30
// 

class column
{
    public int value;
    public int fiveon;
    public int oneon;
    public boolean fivechanged;
    public boolean onechanged;
    
    public column() {
        this.value = 0;
        this.fiveon = 0;
        this.oneon = 0;
        this.fivechanged = true;
        this.onechanged = true;
    }
    
    public void setValue(final int n) {
        this.oneon = 0;
        this.fiveon = 0;
        if (n > 9 || n < 0) {
            return;
        }
        if (n < 5) {
            this.oneon = n;
            this.onechanged = true;
        }
        else if (n == 5) {
            this.fiveon = 1;
            this.fivechanged = true;
        }
        else if (n > 5) {
            this.fiveon = 1;
            this.oneon = n - 5;
            this.onechanged = true;
            this.fivechanged = true;
        }
        this.value = n;
    }
    
    public int getValue() {
        return this.value;
    }
}
