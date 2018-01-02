// 
// Decompiled by Procyon v0.5.30
// 

package DatePickerBean;

public class InvalidDateException extends DatePickerException
{
    public InvalidDateException() {
        super("The Date you tried to set is Invalid.");
    }
}
