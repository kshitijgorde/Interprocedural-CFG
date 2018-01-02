// 
// Decompiled by Procyon v0.5.30
// 

package com.neurotec.lang;

import com.neurotec.jna.NLibrary;
import com.neurotec.io.IOErrorCreator;
import com.sun.jna.Native;
import com.sun.jna.win32.W32APIOptions;
import com.sun.jna.WString;
import java.util.concurrent.TimeoutException;
import java.io.FileNotFoundException;
import java.io.EOFException;
import java.io.IOException;
import com.neurotec.jna.ptr.HNObjectByReference;
import com.sun.jna.Pointer;
import com.sun.jna.ptr.IntByReference;
import com.neurotec.jna.ptr.HNObject;

public final class NError
{
    static final NErrorsLibrary LIBRARY;
    private static final ErrorCreator errorCreator;
    
    private static int getCode(final HNObject hError) {
        final IntByReference rValue = new IntByReference();
        return NResult.isFailed(NError.LIBRARY.NErrorGetCodeEx(hError, rValue)) ? 0 : rValue.getValue();
    }
    
    private static String getMessage(final HNObject hError) {
        int len = NError.LIBRARY.NErrorGetMessageEx(hError, null, 0);
        if (NResult.isFailed(len)) {
            return null;
        }
        final Pointer pValue = NCore.allocString(len);
        try {
            len = NError.LIBRARY.NErrorGetMessageEx(hError, pValue, len + 1);
            return NResult.isFailed(len) ? null : pValue.getString(0L, true);
        }
        finally {
            NCore.free(pValue);
        }
    }
    
    private static String getParam(final HNObject hError) {
        int len = NError.LIBRARY.NErrorGetParamEx(hError, null, 0);
        if (NResult.isFailed(len)) {
            return null;
        }
        final Pointer pValue = NCore.allocString(len);
        try {
            len = NError.LIBRARY.NErrorGetParamEx(hError, pValue, len + 1);
            return NResult.isFailed(len) ? null : pValue.getString(0L, true);
        }
        finally {
            NCore.free(pValue);
        }
    }
    
    private static int getExternalError(final HNObject hError) {
        final IntByReference rValue = new IntByReference();
        return NResult.isFailed(NError.LIBRARY.NErrorGetExternalErrorEx(hError, rValue)) ? 0 : rValue.getValue();
    }
    
    private static String getCallStack(final HNObject hError) {
        final int ecsLen = NError.LIBRARY.NErrorGetExternalCallStackEx(hError, null, 0);
        if (NResult.isFailed(ecsLen)) {
            return null;
        }
        Pointer pValue = NCore.allocString(ecsLen);
        String ecs;
        try {
            if (NResult.isFailed(NError.LIBRARY.NErrorGetExternalCallStackEx(hError, pValue, ecsLen + 1))) {
                return null;
            }
            ecs = pValue.getString(0L, true);
        }
        finally {
            NCore.free(pValue);
        }
        final StringBuilder callStack = new StringBuilder(ecs);
        if (ecsLen != 0) {
            callStack.append(System.getProperty("line.separator"));
        }
        final IntByReference rCsLen = new IntByReference();
        if (NResult.isFailed(NError.LIBRARY.NErrorGetCallStackCount(hError, rCsLen))) {
            return null;
        }
        for (int i = 0; i != rCsLen.getValue(); ++i) {
            final int fnLen = NError.LIBRARY.NErrorGetCallStackFunctionEx(hError, i, null, 0);
            if (NResult.isFailed(fnLen)) {
                return null;
            }
            pValue = NCore.allocString(fnLen);
            String fn;
            try {
                if (NResult.isFailed(NError.LIBRARY.NErrorGetCallStackFunctionEx(hError, i, pValue, fnLen + 1))) {
                    return null;
                }
                fn = pValue.getString(0L, true);
            }
            finally {
                NCore.free(pValue);
            }
            final int flLen = NError.LIBRARY.NErrorGetCallStackFileEx(hError, i, null, 0);
            pValue = NCore.allocString(flLen);
            if (NResult.isFailed(flLen)) {
                return null;
            }
            try {
                if (flLen != 0) {
                    if (NResult.isFailed(NError.LIBRARY.NErrorGetCallStackFileEx(hError, i, pValue, flLen + 1))) {
                        return null;
                    }
                    final String fl = pValue.getString(0L, true);
                    final IntByReference rLine = new IntByReference();
                    if (NResult.isFailed(NError.LIBRARY.NErrorGetCallStackLineEx(hError, i, rLine))) {
                        return null;
                    }
                    callStack.append("   at " + fn);
                    callStack.append(" in " + fl);
                    callStack.append(":line " + rLine.getValue() + System.getProperty("line.separator"));
                }
                else {
                    callStack.append("   at " + fn + System.getProperty("line.separator"));
                }
            }
            finally {
                NCore.free(pValue);
            }
        }
        return callStack.toString();
    }
    
    private static HNObject getInnerError(final HNObject hError) {
        final HNObjectByReference rhValue = new HNObjectByReference();
        return NResult.isFailed(NError.LIBRARY.NErrorGetInnerErrorEx(hError, rhValue)) ? null : rhValue.getValue();
    }
    
    public static String getDefaultMessage(final int code) {
        final int mLen = NError.LIBRARY.NErrorGetDefaultMessageEx(code, null, 0);
        if (NResult.isFailed(mLen)) {
            return null;
        }
        final Pointer pValue = NCore.allocString(mLen);
        try {
            if (NResult.isFailed(NError.LIBRARY.NErrorGetDefaultMessageEx(code, pValue, mLen + 1))) {
                return null;
            }
            return pValue.getString(0L, true);
        }
        finally {
            NCore.free(pValue);
        }
    }
    
    public static String getSysErrorMessage(final int errno) {
        final int mLen = NError.LIBRARY.NErrorGetSysErrorMessageEx(errno, null, 0);
        if (NResult.isFailed(mLen)) {
            return null;
        }
        final Pointer pValue = NCore.allocString(mLen);
        try {
            if (NResult.isFailed(NError.LIBRARY.NErrorGetSysErrorMessageEx(errno, pValue, mLen + 1))) {
                return null;
            }
            return pValue.getString(0L, true);
        }
        finally {
            NCore.free(pValue);
        }
    }
    
    public static String getMacErrorMessage(final int err) {
        final int mLen = NError.LIBRARY.NErrorGetMacErrorMessageEx(err, null, 0);
        if (NResult.isFailed(mLen)) {
            return null;
        }
        final Pointer pValue = NCore.allocString(mLen);
        try {
            if (NResult.isFailed(NError.LIBRARY.NErrorGetMacErrorMessageEx(err, pValue, mLen + 1))) {
                return null;
            }
            return pValue.getString(0L, true);
        }
        finally {
            NCore.free(pValue);
        }
    }
    
    public static Throwable get(final HNObject hError) {
        if (hError == null || hError.getPointer() == null) {
            return null;
        }
        final HNObject hInnerError = getInnerError(hError);
        final Throwable innerError = (hInnerError == null) ? null : get(hInnerError);
        return NError.errorCreator.create(getCode(hError), getMessage(hError), getParam(hError), getExternalError(hError), getCallStack(hError), innerError);
    }
    
    public static Throwable get(final int code, final HNObject hError) {
        if (NResult.isSucceeded(code)) {
            return null;
        }
        if (hError == null || getCode(hError) != code) {
            return NError.errorCreator.create(code, getDefaultMessage(code), null, 0, null, null);
        }
        return get(hError);
    }
    
    public static Throwable getLast(final int code) {
        return get(code, NError.LIBRARY.NErrorGetLast());
    }
    
    public static int setLast(final Throwable error) {
        if (error == null) {
            throw new NullPointerException("error");
        }
        final Class errorType = error.getClass();
        final Throwable innerException = error.getCause();
        if (innerException != null) {
            setLast(innerException);
        }
        int code = 0;
        String message = error.getMessage();
        final String param = null;
        int externalError = 0;
        if (Throwable.class.equals(errorType) || Exception.class.equals(errorType) || Error.class.equals(errorType)) {
            code = -1;
        }
        else if (RuntimeException.class.equals(errorType)) {
            code = -2;
        }
        else if (OutOfMemoryError.class.isAssignableFrom(errorType)) {
            code = -4;
        }
        else if (UnsupportedOperationException.class.isAssignableFrom(errorType)) {
            code = -6;
        }
        else if (IllegalStateException.class.isAssignableFrom(errorType)) {
            code = -7;
        }
        else if (IndexOutOfBoundsException.class.isAssignableFrom(errorType)) {
            code = -9;
        }
        else if (IllegalArgumentException.class.isAssignableFrom(errorType)) {
            code = -10;
        }
        else if (NullPointerException.class.isAssignableFrom(errorType)) {
            code = -11;
        }
        else if (IOException.class.isAssignableFrom(errorType)) {
            if (EOFException.class.isAssignableFrom(errorType)) {
                code = -15;
            }
            else if (FileNotFoundException.class.isAssignableFrom(errorType)) {
                code = -21;
            }
            else {
                code = -14;
            }
        }
        else if (ArithmeticException.class.isAssignableFrom(errorType)) {
            code = -17;
        }
        else if (ClassCastException.class.isAssignableFrom(errorType)) {
            code = -18;
        }
        else if (SecurityException.class.isAssignableFrom(errorType)) {
            code = -24;
        }
        else if (UnsatisfiedLinkError.class.isAssignableFrom(errorType)) {
            code = -28;
        }
        else if (TimeoutException.class.isAssignableFrom(errorType)) {
            code = -30;
        }
        else if (ExternalException.class.isAssignableFrom(errorType)) {
            externalError = ((ExternalException)error).getExternalError();
        }
        if (error instanceof NThrowable) {
            code = ((NThrowable)error).getCode();
        }
        else if (code != 0) {
            code = -97;
            message = errorType.getName() + ((message.length() != 0) ? (": " + message) : "");
        }
        NError.LIBRARY.NErrorSetLast(code, new WString(message), new WString(param), externalError, new WString(error.toString()), innerException != null);
        return code;
    }
    
    public static void suppress(final Throwable error) {
        NError.LIBRARY.NErrorSuppress(setLast(error));
    }
    
    public static String getMessage(final String message, final String paramName, final String param) {
        return message + ((param == null || param.equals("")) ? "" : (System.getProperty("line.separator") + paramName + ": " + param));
    }
    
    static {
        LIBRARY = (NErrorsLibrary)Native.loadLibrary("NCore", NErrorsLibrary.class, W32APIOptions.UNICODE_OPTIONS);
        errorCreator = new ChainedErrorCreator(new ErrorCreator[] { new RuntimeErrorCreator(), new IOErrorCreator() });
    }
    
    public interface NErrorsLibrary extends NLibrary
    {
        int NErrorGetDefaultMessageEx(final int p0, final Pointer p1, final int p2);
        
        int NErrorGetSysErrorMessageEx(final int p0, final Pointer p1, final int p2);
        
        int NErrorGetMacErrorMessageEx(final int p0, final Pointer p1, final int p2);
        
        int NErrorSetLast(final int p0, final WString p1, final WString p2, final int p3, final WString p4, final boolean p5);
        
        void NErrorSuppress(final int p0);
        
        HNObject NErrorGetLast();
        
        int NErrorGetCodeEx(final HNObject p0, final IntByReference p1);
        
        int NErrorGetMessageEx(final HNObject p0, final Pointer p1, final int p2);
        
        int NErrorGetParamEx(final HNObject p0, final Pointer p1, final int p2);
        
        int NErrorGetExternalErrorEx(final HNObject p0, final IntByReference p1);
        
        int NErrorGetExternalCallStackEx(final HNObject p0, final Pointer p1, final int p2);
        
        int NErrorGetCallStackCount(final HNObject p0, final IntByReference p1);
        
        int NErrorGetCallStackFunctionEx(final HNObject p0, final int p1, final Pointer p2, final int p3);
        
        int NErrorGetCallStackFileEx(final HNObject p0, final int p1, final Pointer p2, final int p3);
        
        int NErrorGetCallStackLineEx(final HNObject p0, final int p1, final IntByReference p2);
        
        int NErrorGetInnerErrorEx(final HNObject p0, final HNObjectByReference p1);
    }
}
