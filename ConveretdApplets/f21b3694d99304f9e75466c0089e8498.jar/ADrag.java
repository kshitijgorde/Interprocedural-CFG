// 
// Decompiled by Procyon v0.5.30
// 

public class ADrag
{
    int date1;
    int date2;
    double value1;
    double value2;
    boolean infinite;
    
    ADrag(final int i, final double d, final int j, final double d1, final boolean flag) {
        this.infinite = false;
        this.date1 = i;
        this.date2 = j;
        this.value1 = d;
        this.value2 = d1;
        this.infinite = flag;
    }
    
    int getDate1() {
        return this.date1;
    }
    
    int getDate2() {
        return this.date2;
    }
    
    double getValue1() {
        return this.value1;
    }
    
    double getValue2() {
        return this.value2;
    }
}
