// 
// Decompiled by Procyon v0.5.30
// 

package com.neurotec.io;

import com.neurotec.lang.ErrorCreator;

public final class IOErrorCreator extends ErrorCreator
{
    protected Throwable create(final int code, final String message, final String param, final int externalError, final String callStack, final Throwable cause) {
        switch (code) {
            case -29:
            case -14: {
                return new NIOException(code, message, param, callStack, cause);
            }
            case -23:
            case -22:
            case -21:
            case -20:
            case -19: {
                return new NFileNotFoundException(code, message, param, callStack, cause);
            }
            case -15: {
                return new NEOFException(code, message, callStack, cause);
            }
            default: {
                return null;
            }
        }
    }
}
