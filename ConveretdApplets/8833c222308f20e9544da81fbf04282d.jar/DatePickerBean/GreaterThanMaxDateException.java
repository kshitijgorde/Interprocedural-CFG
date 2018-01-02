// 
// Decompiled by Procyon v0.5.30
// 

package DatePickerBean;

public class GreaterThanMaxDateException extends DatePickerException
{
    public GreaterThanMaxDateException() {
        super("The Date you tried to set in greater than the Maximum Date of this object.");
    }
}
