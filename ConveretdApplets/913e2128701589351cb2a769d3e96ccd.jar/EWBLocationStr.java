// 
// Decompiled by Procyon v0.5.30
// 

class EWBLocationStr
{
    String n;
    String s;
    String e;
    String w;
    String minDate;
    String maxDate;
    
    EWBLocationStr() {
    }
    
    EWBLocationStr(final String n, final String s, final String e, final String w) {
        this.n = n;
        this.s = s;
        this.e = e;
        this.w = w;
    }
    
    EWBLocationStr(final String n, final String s, final String e, final String w, final String minDate, final String maxDate) {
        this.n = n;
        this.s = s;
        this.e = e;
        this.w = w;
        this.minDate = minDate;
        this.maxDate = maxDate;
    }
}
