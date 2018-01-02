// 
// Decompiled by Procyon v0.5.30
// 

package DatePickerBean;

public class DatePickerException extends Exception
{
    String dterr;
    
    public DatePickerException(final String dterr) {
        this.dterr = dterr;
    }
    
    public String toString() {
        return this.dterr;
    }
}
