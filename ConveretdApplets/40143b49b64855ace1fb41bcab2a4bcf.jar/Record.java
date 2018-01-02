// 
// Decompiled by Procyon v0.5.30
// 

class Record
{
    String[] FIELDvalue;
    
    public Record(final int n) {
        this.FIELDvalue = new String[n];
        for (int i = 0; i < n; ++i) {
            this.FIELDvalue[i] = new String("");
        }
    }
    
    public String getFIELDvalue(final int n) {
        return this.FIELDvalue[n];
    }
    
    public String getRECORD() {
        String string = new String();
        for (int i = 0; i < this.FIELDvalue.length; ++i) {
            string = string + this.FIELDvalue[i] + " ";
        }
        return string;
    }
}
