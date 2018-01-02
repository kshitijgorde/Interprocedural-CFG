// 
// Decompiled by Procyon v0.5.30
// 

package DatePickerBean;

public class LessThanMinDateException extends DatePickerException
{
    public LessThanMinDateException() {
        super("The Date you tried to set in less than the Minimum Date of this object.");
    }
}
