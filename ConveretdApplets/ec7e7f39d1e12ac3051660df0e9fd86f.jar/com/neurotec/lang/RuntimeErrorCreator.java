// 
// Decompiled by Procyon v0.5.30
// 

package com.neurotec.lang;

final class RuntimeErrorCreator extends ErrorCreator
{
    protected Throwable create(final int code, final String message, final String param, final int externalError, final String callStack, final Throwable cause) {
        switch (code) {
            case -25:
            case -2:
            case -1: {
                return new NRuntimeException(code, message, callStack, cause);
            }
            case -16:
            case -13:
            case -12:
            case -10: {
                return new NIllegalArgumentException(code, message, param, callStack, cause);
            }
            case -11:
            case -3: {
                return new NNullPointerException(code, message, param, callStack, cause);
            }
            case -17:
            case -8: {
                return new NArithmeticException(code, message, callStack, cause);
            }
            case -28:
            case -27:
            case -26: {
                return new NUnsatisfiedLinkError(code, message, param, callStack, cause);
            }
            case -9: {
                return new NIndexOutOfBoundsException(code, message, callStack, cause);
            }
            case -18: {
                return new NClassCastException(code, message, callStack, cause);
            }
            case -7: {
                return new NIllegalStateException(code, message, callStack, cause);
            }
            case -6:
            case -5: {
                return new NUnsupportedOperationException(code, message, callStack, cause);
            }
            case -4: {
                return new NOutOfMemoryError(code, message, callStack, cause);
            }
            case -24: {
                return new NSecurityException(code, message, callStack, cause);
            }
            case -97:
            case -96:
            case -95:
            case -94:
            case -92:
            case -91:
            case -90: {
                return new ExternalException(code, externalError, message, callStack, cause);
            }
            case -100: {
                return new ParameterException(code, message, callStack, cause);
            }
            case -101: {
                return new ParameterReadOnlyException(code, message, callStack, cause);
            }
            case -200: {
                return new NotActivatedException(code, message, callStack, cause);
            }
            default: {
                return new NeurotecException(code, message, callStack, cause);
            }
        }
    }
}
