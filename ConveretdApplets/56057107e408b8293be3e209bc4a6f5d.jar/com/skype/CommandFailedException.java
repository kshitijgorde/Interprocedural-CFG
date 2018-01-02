// 
// Decompiled by Procyon v0.5.30
// 

package com.skype;

public final class CommandFailedException extends SkypeException
{
    private static final long serialVersionUID = 5247715297475793607L;
    private int code;
    private String message;
    
    CommandFailedException(String response) {
        super(response);
        if (response.startsWith("ERROR ")) {
            response = response.substring("ERROR ".length());
        }
        final int spaceIndex = response.indexOf(32);
        this.code = Integer.parseInt(response.substring(0, spaceIndex));
        this.message = response.substring(spaceIndex + 1);
    }
    
    public int getCode() {
        return this.code;
    }
    
    public String getMessage() {
        return this.message;
    }
}
