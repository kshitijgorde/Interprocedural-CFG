// 
// Decompiled by Procyon v0.5.30
// 

package com.neurotec.lang;

final class ChainedErrorCreator extends ErrorCreator
{
    private ErrorCreator[] creators;
    
    public ChainedErrorCreator(final ErrorCreator[] creators) {
        this.creators = creators;
    }
    
    protected Throwable create(final int code, final String message, final String param, final int externalError, final String callStack, final Throwable innerError) {
        Throwable error = null;
        for (int i = this.creators.length - 1; i >= 0; --i) {
            error = this.creators[i].create(code, message, param, externalError, callStack, innerError);
            if (error != null) {
                break;
            }
        }
        return error;
    }
}
