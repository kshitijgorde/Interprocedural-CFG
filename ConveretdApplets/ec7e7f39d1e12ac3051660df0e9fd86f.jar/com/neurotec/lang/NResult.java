// 
// Decompiled by Procyon v0.5.30
// 

package com.neurotec.lang;

import com.neurotec.jna.ptr.HNObject;
import java.util.concurrent.TimeoutException;
import java.io.IOException;

public final class NResult
{
    public static final int OK = 0;
    public static final int E_FAILED = -1;
    public static final int E_CORE = -2;
    public static final int E_ABANDONED_MUTEX = -25;
    public static final int E_ARGUMENT = -10;
    public static final int E_ARGUMENT_NULL = -11;
    public static final int E_ARGUMENT_OUT_OF_RANGE = -12;
    public static final int E_INVALID_ENUM_ARGUMENT = -16;
    public static final int E_ARITHMETIC = -17;
    public static final int E_OVERFLOW = -8;
    public static final int E_BAD_IMAGE_FORMAT = -26;
    public static final int E_DLL_NOT_FOUND = -27;
    public static final int E_ENTRY_POINT_NOT_FOUND = -28;
    public static final int E_TIMEOUT = -30;
    public static final int E_FORMAT = -13;
    public static final int E_FILE_FORMAT = -29;
    public static final int E_INDEX_OUT_OF_RANGE = -9;
    public static final int E_INVALID_CAST = -18;
    public static final int E_INVALID_OPERATION = -7;
    public static final int E_IO = -14;
    public static final int E_DIRECTORY_NOT_FOUND = -19;
    public static final int E_DRIVE_NOT_FOUND = -20;
    public static final int E_END_OF_STREAM = -15;
    public static final int E_FILE_NOT_FOUND = -21;
    public static final int E_FILE_LOAD = -22;
    public static final int E_PATH_TOO_LONG = -23;
    public static final int E_NOT_IMPLEMENTED = -5;
    public static final int E_NOT_SUPPORTED = -6;
    public static final int E_NULL_REFERENCE = -3;
    public static final int E_OUT_OF_MEMORY = -4;
    public static final int E_SECURITY = -24;
    public static final int E_TARGET_INVOCATION = -29;
    public static final int E_EXTERNAL = -90;
    public static final int E_CLR = -93;
    public static final int E_COM = -92;
    public static final int E_CPP = -96;
    public static final int E_JVM = -97;
    public static final int E_MAC = -95;
    public static final int E_SYS = -94;
    public static final int E_WIN32 = -91;
    public static final int E_PARAMETER = -100;
    public static final int E_PARAMETER_READ_ONLY = -101;
    public static final int E_NOT_ACTIVATED = -200;
    
    public static boolean isFailed(final int result) {
        return result < 0;
    }
    
    public static boolean isSucceeded(final int result) {
        return result >= 0;
    }
    
    public static int check(final int result) {
        if (isFailed(result)) {
            raiseError(result);
        }
        return result;
    }
    
    public static Throwable checkUnchecked(final int result) {
        final Throwable th = NError.getLast(result);
        if (th == null) {
            return null;
        }
        if (th instanceof RuntimeException) {
            throw (RuntimeException)th;
        }
        if (th instanceof Error) {
            throw (Error)th;
        }
        return th;
    }
    
    public static Throwable checkIO(final Throwable th) throws IOException {
        if (th == null) {
            return null;
        }
        if (th instanceof IOException) {
            throw (IOException)th;
        }
        return th;
    }
    
    public static Throwable checkTimeout(final Throwable th) throws TimeoutException {
        if (th == null) {
            return null;
        }
        if (th instanceof TimeoutException) {
            throw (TimeoutException)th;
        }
        return th;
    }
    
    public static void checkAll(final Throwable th) {
        if (th == null) {
            return;
        }
        throw new RuntimeException(th);
    }
    
    private static void raiseError(final int error) {
        if (error >= 0) {
            throw new IllegalArgumentException("error is greater than or equal to zero");
        }
        checkAll(checkUnchecked(error));
    }
    
    public static void suppressError(final Throwable error) {
        if (error == null) {
            throw new NullPointerException("error");
        }
        NError.suppress(error);
    }
    
    public static Throwable getError(final int error, final HNObject hError) {
        return NError.get(error, hError);
    }
}
