// 
// Decompiled by Procyon v0.5.30
// 

package com.neurotec.lang;

import com.sun.jna.win32.StdCallLibrary;
import com.neurotec.jna.ptr.HNObject;
import com.neurotec.jna.NLibrary;
import com.sun.jna.win32.W32APIOptions;
import com.neurotec.event.ErrorSuppressedEvent;
import com.neurotec.event.ErrorSuppressedListener;
import java.util.Calendar;
import com.neurotec.jna.NMemory;
import java.nio.ByteBuffer;
import java.util.Date;
import com.sun.jna.ptr.IntByReference;
import com.sun.jna.Native;
import com.neurotec.jna.ptr.NativeSize;
import com.sun.jna.ptr.PointerByReference;
import com.sun.jna.Pointer;
import javax.swing.event.EventListenerList;

public final class NCore
{
    private static final int BUFFER_SIZE = 4096;
    private static final long N_DATE_TIME_UNIX_EPOCH = 621355968000000000L;
    private static NCoreLibrary.NErrorSuppressedCallback errorSuppressedCallback;
    private static EventListenerList listenerList;
    public static final String DLL_NAME = "NCore";
    static final NCoreLibrary LIBRARY;
    public static NNativeModule NATIVE_MODULE;
    
    public static Pointer alloc(final int size) {
        return alloc((long)size);
    }
    
    public static Pointer alloc(final long size) {
        final PointerByReference rValue = new PointerByReference();
        NResult.check(NCore.LIBRARY.NAlloc(new NativeSize(size), rValue));
        return rValue.getValue();
    }
    
    public static Pointer allocString(final int len) {
        return alloc((len + 1) * Native.WCHAR_SIZE);
    }
    
    public static Pointer calloc(final int size) {
        return calloc((long)size);
    }
    
    public static Pointer calloc(final long size) {
        final PointerByReference rValue = new PointerByReference();
        NResult.check(NCore.LIBRARY.NCAlloc(new NativeSize(size), rValue));
        return rValue.getValue();
    }
    
    public static Pointer realloc(final Pointer pBlock, final int size) {
        return realloc(pBlock, (long)size);
    }
    
    public static Pointer realloc(final Pointer pBlock, final long size) {
        if (pBlock == null) {
            throw new NullPointerException("pBlock");
        }
        final PointerByReference rValue = new PointerByReference(pBlock);
        NResult.check(NCore.LIBRARY.NReAlloc(rValue, new NativeSize(size)));
        return rValue.getValue();
    }
    
    public static void free(final Pointer rBlock) {
        NCore.LIBRARY.NFree(rBlock);
    }
    
    public static void copy(final Pointer pDstBlock, final Pointer pSrcBlock, final int size) {
        copy(pDstBlock, pSrcBlock, (long)size);
    }
    
    public static void copy(final Pointer pDstBlock, final Pointer pSrcBlock, final long size) {
        NResult.check(NCore.LIBRARY.NCopy(pDstBlock, pSrcBlock, new NativeSize(size)));
    }
    
    public static void move(final Pointer pDstBlock, final Pointer pSrcBlock, final int size) {
        move(pDstBlock, pSrcBlock, (long)size);
    }
    
    public static void move(final Pointer pDstBlock, final Pointer pSrcBlock, final long size) {
        NResult.check(NCore.LIBRARY.NMove(pDstBlock, pSrcBlock, new NativeSize(size)));
    }
    
    public static void fill(final Pointer pBlock, final byte value, final int size) {
        fill(pBlock, value, (long)size);
    }
    
    public static void fill(final Pointer pBlock, final byte value, final long size) {
        NResult.check(NCore.LIBRARY.NFill(pBlock, value, new NativeSize(size)));
    }
    
    public static void clear(final Pointer pBlock, final int size) {
        clear(pBlock, (long)size);
    }
    
    public static void clear(final Pointer pBlock, final long size) {
        NResult.check(NCore.LIBRARY.NFill(pBlock, (byte)0, new NativeSize(size)));
    }
    
    public static int compare(final Pointer pBlock1, final Pointer pBlock2, final int size) {
        return compare(pBlock1, pBlock2, (long)size);
    }
    
    public static int compare(final Pointer pBlock1, final Pointer pBlock2, final long size) {
        final IntByReference rValue = new IntByReference();
        NResult.check(NCore.LIBRARY.NCompare(pBlock1, pBlock2, new NativeSize(size), rValue));
        return rValue.getValue();
    }
    
    public static Date toUtcDate(final long value) {
        final long dateValue = (value - 621355968000000000L + 5000L) / 10000L;
        if (dateValue < 0L) {
            throw new ArithmeticException();
        }
        return new Date(dateValue);
    }
    
    public static long toRawUtcDate(final Date value) {
        long dateValue = value.getTime();
        if (922337203685477L < dateValue) {
            throw new ArithmeticException();
        }
        dateValue *= 10000L;
        if (8602016068854775807L < dateValue) {
            throw new ArithmeticException();
        }
        return dateValue + 621355968000000000L;
    }
    
    public static ByteBuffer getByteBuffer(final Pointer pointer, final long size) {
        return getByteBuffer(pointer, size, true);
    }
    
    public static ByteBuffer getByteBuffer(final Pointer pointer, final long size, final boolean ownsPointer) {
        return new NMemory(pointer, size, ownsPointer).getByteBuffer(0L, size);
    }
    
    public static Date toDate(final long value) {
        final Date localDate = toUtcDate(value);
        final Calendar cal = Calendar.getInstance();
        cal.setTime(localDate);
        return new Date(localDate.getTime() - cal.get(15) - cal.get(16));
    }
    
    public static long toRawDate(final Date value) {
        final Calendar cal = Calendar.getInstance();
        cal.setTime(value);
        final Date localDate = new Date(value.getTime() + cal.get(15) + cal.get(16));
        return toRawUtcDate(localDate);
    }
    
    public static synchronized void addErrorSuppressedListener(final ErrorSuppressedListener listener) {
        if (NCore.listenerList.getListenerCount() == 0) {
            NCore.LIBRARY.NAddErrorSuppressedCallback(NCore.errorSuppressedCallback, Pointer.NULL);
        }
        NCore.listenerList.add(ErrorSuppressedListener.class, listener);
    }
    
    public static synchronized void removeErrorSuppressedListener(final ErrorSuppressedListener listener) {
        NCore.listenerList.remove(ErrorSuppressedListener.class, listener);
        if (NCore.listenerList.getListenerCount() == 0) {
            NCore.LIBRARY.NRemoveErrorSuppressedCallback(NCore.errorSuppressedCallback, Pointer.NULL);
        }
    }
    
    private static synchronized void errorSuppressed(final Throwable error) {
        final Object[] listeners = NCore.listenerList.getListenerList();
        for (int i = listeners.length - 2; i >= 0; i -= 2) {
            if (listeners[i] == ErrorSuppressedListener.class) {
                ((ErrorSuppressedListener)listeners[i + 1]).errorSuppressed(new ErrorSuppressedEvent(NCore.class, error));
            }
        }
    }
    
    static {
        NCore.errorSuppressedCallback = new NCoreLibrary.NErrorSuppressedCallback() {
            public void invoke(final int error, final Pointer pParam) {
                errorSuppressed(NError.getLast(error));
            }
        };
        NCore.listenerList = new EventListenerList();
        LIBRARY = (NCoreLibrary)Native.loadLibrary("NCore", NCoreLibrary.class, W32APIOptions.UNICODE_OPTIONS);
        NCore.NATIVE_MODULE = NNativeModule.fromHandle(NCore.LIBRARY.NCoreModuleOf());
        NCore.errorSuppressedCallback = new NCoreLibrary.NErrorSuppressedCallback() {
            public void invoke(final int error, final Pointer pParam) {
                System.out.println(error + " " + pParam);
                errorSuppressed(NError.getLast(error));
            }
        };
    }
    
    interface NCoreLibrary extends NLibrary
    {
        HNObject NCoreModuleOf();
        
        int NAddErrorSuppressedCallback(final NErrorSuppressedCallback p0, final Pointer p1);
        
        int NRemoveErrorSuppressedCallback(final NErrorSuppressedCallback p0, final Pointer p1);
        
        int NAlloc(final NativeSize p0, final PointerByReference p1);
        
        int NCAlloc(final NativeSize p0, final PointerByReference p1);
        
        int NReAlloc(final PointerByReference p0, final NativeSize p1);
        
        void NFree(final Pointer p0);
        
        int NCopy(final Pointer p0, final Pointer p1, final NativeSize p2);
        
        int NMove(final Pointer p0, final Pointer p1, final NativeSize p2);
        
        int NFill(final Pointer p0, final byte p1, final NativeSize p2);
        
        int NCompare(final Pointer p0, final Pointer p1, final NativeSize p2, final IntByReference p3);
        
        public interface NErrorSuppressedCallback extends StdCallLibrary.StdCallCallback
        {
            void invoke(final int p0, final Pointer p1);
        }
    }
}
