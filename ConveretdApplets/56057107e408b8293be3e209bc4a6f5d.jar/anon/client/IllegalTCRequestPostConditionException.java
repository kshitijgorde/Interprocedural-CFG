// 
// Decompiled by Procyon v0.5.30
// 

package anon.client;

public class IllegalTCRequestPostConditionException extends Exception
{
    private StringBuffer errorMessages;
    private int errorMessageNrs;
    
    public IllegalTCRequestPostConditionException() {
        this.errorMessages = new StringBuffer();
        this.errorMessageNrs = 0;
    }
    
    public void addErrorMessage(final String s) {
        this.errorMessages.append("\n");
        this.errorMessages.append(++this.errorMessageNrs);
        this.errorMessages.append(". ");
        this.errorMessages.append(s);
    }
    
    public boolean hasErrorMessages() {
        return this.errorMessageNrs > 0;
    }
    
    public String getMessage() {
        return this.hasErrorMessages() ? this.errorMessages.toString() : null;
    }
}
