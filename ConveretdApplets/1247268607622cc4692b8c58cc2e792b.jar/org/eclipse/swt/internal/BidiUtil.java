// 
// Decompiled by Procyon v0.5.30
// 

package org.eclipse.swt.internal;

import org.eclipse.swt.internal.win32.GCP_RESULTS;
import org.eclipse.swt.internal.win32.TCHAR;
import org.eclipse.swt.internal.win32.RECT;
import org.eclipse.swt.internal.win32.OS;
import org.eclipse.swt.graphics.GC;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.SWT;
import java.util.Hashtable;

public class BidiUtil
{
    public static final int KEYBOARD_NON_BIDI = 0;
    public static final int KEYBOARD_BIDI = 1;
    static int isBidiPlatform;
    public static final int CLASSIN = 1;
    public static final int LINKBEFORE = 2;
    public static final int LINKAFTER = 4;
    static Hashtable languageMap;
    static Hashtable keyMap;
    static Hashtable oldProcMap;
    static final String CLASS_NAME = "org.eclipse.swt.internal.BidiUtil";
    static Callback callback;
    static final int GCP_REORDER = 2;
    static final int GCP_GLYPHSHAPE = 16;
    static final int GCP_LIGATE = 32;
    static final int GCP_CLASSIN = 524288;
    static final byte GCPCLASS_ARABIC = 2;
    static final byte GCPCLASS_HEBREW = 2;
    static final byte GCPCLASS_LOCALNUMBER = 4;
    static final byte GCPCLASS_LATINNUMBER = 5;
    static final int GCPGLYPH_LINKBEFORE = 32768;
    static final int GCPGLYPH_LINKAFTER = 16384;
    static final int ETO_CLIPPED = 4;
    static final int ETO_GLYPH_INDEX = 16;
    static final int LANG_ARABIC = 1;
    static final int LANG_HEBREW = 13;
    static final int LANG_FARSI = 41;
    static final String CD_PG_HEBREW = "1255";
    static final String CD_PG_ARABIC = "1256";
    static final int HKL_NEXT = 1;
    static final int HKL_PREV = 0;
    public static final int CLASS_HEBREW = 2;
    public static final int CLASS_ARABIC = 2;
    public static final int CLASS_LOCALNUMBER = 4;
    public static final int CLASS_LATINNUMBER = 5;
    public static final int REORDER = 2;
    public static final int LIGATE = 32;
    public static final int GLYPHSHAPE = 16;
    
    static {
        BidiUtil.isBidiPlatform = -1;
        BidiUtil.languageMap = new Hashtable();
        BidiUtil.keyMap = new Hashtable();
        BidiUtil.oldProcMap = new Hashtable();
        try {
            BidiUtil.callback = new Callback(Class.forName("org.eclipse.swt.internal.BidiUtil"), "windowProc", 4);
            if (BidiUtil.callback.getAddress() == 0) {
                SWT.error(3);
            }
        }
        catch (ClassNotFoundException ex) {}
    }
    
    public static void addLanguageListener(final int n, final Runnable runnable) {
        BidiUtil.languageMap.put(new LONG(n), runnable);
        subclass(n);
    }
    
    public static void addLanguageListener(final Control control, final Runnable runnable) {
        addLanguageListener(control.handle, runnable);
    }
    
    static int EnumSystemLanguageGroupsProc(final int n, final int n2, final int n3, final int n4, final int n5) {
        if (n == 12) {
            BidiUtil.isBidiPlatform = 1;
            return 0;
        }
        if (n == 13) {
            BidiUtil.isBidiPlatform = 1;
            return 0;
        }
        return 1;
    }
    
    public static void drawGlyphs(final GC gc, final char[] array, final int[] array2, final int n, final int n2) {
        final int length = array2.length;
        if (!OS.IsWinCE && OS.WIN32_VERSION >= OS.VERSION(4, 10) && OS.GetLayout(gc.handle) != 0) {
            reverse(array2);
            final int n3 = length - 1;
            --array2[n3];
            reverse(array);
        }
        final int setBkMode = OS.SetBkMode(gc.handle, 1);
        OS.ExtTextOutW(gc.handle, n, n2, 16, null, array, array.length, array2);
        OS.SetBkMode(gc.handle, setBkMode);
    }
    
    public static char[] getRenderInfo(final GC gc, final String s, final int[] array, final byte[] array2, final int[] array3, final int n, final int[] array4) {
        final int getFontLanguageInfo = OS.GetFontLanguageInfo(gc.handle);
        final int getProcessHeap = OS.GetProcessHeap();
        final int[] array5 = new int[8];
        final int getTextCharset = OS.GetTextCharset(gc.handle);
        boolean b = false;
        if (!OS.IsWinCE && OS.WIN32_VERSION >= OS.VERSION(4, 10)) {
            b = (OS.GetLayout(gc.handle) != 0);
        }
        OS.TranslateCharsetInfo(getTextCharset, array5, 1);
        final int length = new TCHAR(array5[1], s, false).length();
        final boolean b2 = (n & 0x2) == 0x2;
        final boolean b3 = (n & 0x4) == 0x4;
        final GCP_RESULTS gcp_RESULTS = new GCP_RESULTS();
        gcp_RESULTS.lStructSize = GCP_RESULTS.sizeof;
        gcp_RESULTS.nGlyphs = length;
        final GCP_RESULTS gcp_RESULTS2 = gcp_RESULTS;
        final int heapAlloc = OS.HeapAlloc(getProcessHeap, 8, length * 4);
        gcp_RESULTS2.lpOrder = heapAlloc;
        final int n2 = heapAlloc;
        final GCP_RESULTS gcp_RESULTS3 = gcp_RESULTS;
        final int heapAlloc2 = OS.HeapAlloc(getProcessHeap, 8, length * 4);
        gcp_RESULTS3.lpDx = heapAlloc2;
        final int n3 = heapAlloc2;
        final GCP_RESULTS gcp_RESULTS4 = gcp_RESULTS;
        final int heapAlloc3 = OS.HeapAlloc(getProcessHeap, 8, length);
        gcp_RESULTS4.lpClass = heapAlloc3;
        final int n4 = heapAlloc3;
        final GCP_RESULTS gcp_RESULTS5 = gcp_RESULTS;
        final int heapAlloc4 = OS.HeapAlloc(getProcessHeap, 8, length * 2);
        gcp_RESULTS5.lpGlyphs = heapAlloc4;
        final int n5 = heapAlloc4;
        final boolean b4 = false;
        int n6 = 0;
        int n7 = (b4 ? 1 : 0) | 0x2;
        if ((getFontLanguageInfo & 0x20) == 0x20) {
            n7 |= 0x20;
            n6 |= 0x0;
        }
        if ((getFontLanguageInfo & 0x10) == 0x10) {
            n7 |= 0x10;
            if (b2) {
                n6 |= 0x8000;
            }
            if (b3) {
                n6 |= 0x4000;
            }
        }
        byte[] array6;
        if (b2 || b3) {
            array6 = new byte[] { (byte)n6, (byte)(n6 >> 8) };
        }
        else {
            array6 = new byte[] { (byte)n6 };
        }
        OS.MoveMemory(gcp_RESULTS.lpGlyphs, array6, array6.length);
        if ((n & 0x1) == 0x1) {
            n7 |= 0x80000;
            OS.MoveMemory(gcp_RESULTS.lpClass, array2, array2.length);
        }
        final char[] array7 = new char[gcp_RESULTS.nGlyphs];
        int n8 = 0;
        for (int i = 0; i < array4.length - 1; ++i) {
            final int n9 = array4[i];
            final int nGlyphs = array4[i + 1] - array4[i];
            gcp_RESULTS.nGlyphs = nGlyphs;
            final TCHAR tchar = new TCHAR(array5[1], s.substring(n9, n9 + nGlyphs), false);
            OS.GetCharacterPlacement(gc.handle, tchar, tchar.length(), 0, gcp_RESULTS, n7);
            if (array3 != null) {
                final int[] array8 = new int[gcp_RESULTS.nGlyphs];
                OS.MoveMemory(array8, gcp_RESULTS.lpDx, array8.length * 4);
                if (b) {
                    reverse(array8);
                }
                System.arraycopy(array8, 0, array3, n8, array8.length);
            }
            if (array != null) {
                final int[] array9 = new int[nGlyphs];
                OS.MoveMemory(array9, gcp_RESULTS.lpOrder, array9.length * 4);
                translateOrder(array9, n8, b);
                System.arraycopy(array9, 0, array, n9, nGlyphs);
            }
            if (array2 != null) {
                final byte[] array10 = new byte[nGlyphs];
                OS.MoveMemory(array10, gcp_RESULTS.lpClass, array10.length);
                System.arraycopy(array10, 0, array2, n9, nGlyphs);
            }
            final char[] array11 = new char[gcp_RESULTS.nGlyphs];
            OS.MoveMemory(array11, gcp_RESULTS.lpGlyphs, array11.length * 2);
            if (b) {
                reverse(array11);
            }
            System.arraycopy(array11, 0, array7, n8, array11.length);
            n8 += array11.length;
            final GCP_RESULTS gcp_RESULTS6 = gcp_RESULTS;
            gcp_RESULTS6.lpOrder += nGlyphs * 4;
            final GCP_RESULTS gcp_RESULTS7 = gcp_RESULTS;
            gcp_RESULTS7.lpDx += nGlyphs * 4;
            final GCP_RESULTS gcp_RESULTS8 = gcp_RESULTS;
            gcp_RESULTS8.lpClass += nGlyphs;
            final GCP_RESULTS gcp_RESULTS9 = gcp_RESULTS;
            gcp_RESULTS9.lpGlyphs += array11.length * 2;
        }
        OS.HeapFree(getProcessHeap, 0, n5);
        OS.HeapFree(getProcessHeap, 0, n4);
        OS.HeapFree(getProcessHeap, 0, n3);
        OS.HeapFree(getProcessHeap, 0, n2);
        return array7;
    }
    
    public static void getOrderInfo(final GC gc, final String s, final int[] array, final byte[] array2, final int n, final int[] array3) {
        final int getFontLanguageInfo = OS.GetFontLanguageInfo(gc.handle);
        final int getProcessHeap = OS.GetProcessHeap();
        final int[] array4 = new int[8];
        OS.TranslateCharsetInfo(OS.GetTextCharset(gc.handle), array4, 1);
        final int length = new TCHAR(array4[1], s, false).length();
        boolean b = false;
        if (!OS.IsWinCE && OS.WIN32_VERSION >= OS.VERSION(4, 10)) {
            b = (OS.GetLayout(gc.handle) != 0);
        }
        final GCP_RESULTS gcp_RESULTS = new GCP_RESULTS();
        gcp_RESULTS.lStructSize = GCP_RESULTS.sizeof;
        gcp_RESULTS.nGlyphs = length;
        final GCP_RESULTS gcp_RESULTS2 = gcp_RESULTS;
        final int heapAlloc = OS.HeapAlloc(getProcessHeap, 8, length * 4);
        gcp_RESULTS2.lpOrder = heapAlloc;
        final int n2 = heapAlloc;
        final GCP_RESULTS gcp_RESULTS3 = gcp_RESULTS;
        final int heapAlloc2 = OS.HeapAlloc(getProcessHeap, 8, length);
        gcp_RESULTS3.lpClass = heapAlloc2;
        final int n3 = heapAlloc2;
        int n4 = 0x0 | 0x2;
        if ((getFontLanguageInfo & 0x20) == 0x20) {
            n4 |= 0x20;
        }
        if ((getFontLanguageInfo & 0x10) == 0x10) {
            n4 |= 0x10;
        }
        if ((n & 0x1) == 0x1) {
            n4 |= 0x80000;
            OS.MoveMemory(gcp_RESULTS.lpClass, array2, array2.length);
        }
        int n5 = 0;
        for (int i = 0; i < array3.length - 1; ++i) {
            final int n6 = array3[i];
            final int nGlyphs = array3[i + 1] - array3[i];
            gcp_RESULTS.nGlyphs = nGlyphs;
            final TCHAR tchar = new TCHAR(array4[1], s.substring(n6, n6 + nGlyphs), false);
            OS.GetCharacterPlacement(gc.handle, tchar, tchar.length(), 0, gcp_RESULTS, n4);
            if (array != null) {
                final int[] array5 = new int[nGlyphs];
                OS.MoveMemory(array5, gcp_RESULTS.lpOrder, array5.length * 4);
                translateOrder(array5, n5, b);
                System.arraycopy(array5, 0, array, n6, nGlyphs);
            }
            if (array2 != null) {
                final byte[] array6 = new byte[nGlyphs];
                OS.MoveMemory(array6, gcp_RESULTS.lpClass, array6.length);
                System.arraycopy(array6, 0, array2, n6, nGlyphs);
            }
            n5 += gcp_RESULTS.nGlyphs;
            final GCP_RESULTS gcp_RESULTS4 = gcp_RESULTS;
            gcp_RESULTS4.lpOrder += nGlyphs * 4;
            final GCP_RESULTS gcp_RESULTS5 = gcp_RESULTS;
            gcp_RESULTS5.lpClass += nGlyphs;
        }
        OS.HeapFree(getProcessHeap, 0, n3);
        OS.HeapFree(getProcessHeap, 0, n2);
    }
    
    public static int getFontBidiAttributes(final GC gc) {
        int n = 0;
        final int getFontLanguageInfo = OS.GetFontLanguageInfo(gc.handle);
        if ((getFontLanguageInfo & 0x2) != 0x0) {
            n |= 0x2;
        }
        if ((getFontLanguageInfo & 0x20) != 0x0) {
            n |= 0x20;
        }
        if ((getFontLanguageInfo & 0x10) != 0x0) {
            n |= 0x10;
        }
        return n;
    }
    
    public static int getKeyboardLanguage() {
        return isBidiLang(OS.GetKeyboardLayout(0)) ? 1 : 0;
    }
    
    static int[] getKeyboardLanguageList() {
        final int n = 10;
        final int[] array = new int[n];
        final int getKeyboardLayoutList = OS.GetKeyboardLayoutList(n, array);
        final int[] array2 = new int[getKeyboardLayoutList];
        System.arraycopy(array, 0, array2, 0, getKeyboardLayoutList);
        return array2;
    }
    
    static boolean isBidiLang(final int n) {
        final short primarylangid = OS.PRIMARYLANGID(OS.LOWORD(n));
        return primarylangid == 1 || primarylangid == 13 || primarylangid == 41;
    }
    
    public static boolean isBidiPlatform() {
        if (OS.IsWinCE) {
            return false;
        }
        if (BidiUtil.isBidiPlatform != -1) {
            return BidiUtil.isBidiPlatform == 1;
        }
        BidiUtil.isBidiPlatform = 0;
        if (!isKeyboardBidi()) {
            return false;
        }
        Callback callback = null;
        try {
            callback = new Callback(Class.forName("org.eclipse.swt.internal.BidiUtil"), "EnumSystemLanguageGroupsProc", 5);
            final int address = callback.getAddress();
            if (address == 0) {
                SWT.error(3);
            }
            OS.EnumSystemLanguageGroups(address, 1, 0);
            callback.dispose();
        }
        catch (ClassNotFoundException ex) {
            if (callback != null) {
                callback.dispose();
            }
        }
        if (BidiUtil.isBidiPlatform == 1) {
            return true;
        }
        final String value = String.valueOf(OS.GetACP());
        if ("1256".equals(value) || "1255".equals(value)) {
            BidiUtil.isBidiPlatform = 1;
        }
        return BidiUtil.isBidiPlatform == 1;
    }
    
    public static boolean isKeyboardBidi() {
        final int[] keyboardLanguageList = getKeyboardLanguageList();
        for (int i = 0; i < keyboardLanguageList.length; ++i) {
            if (isBidiLang(keyboardLanguageList[i])) {
                return true;
            }
        }
        return false;
    }
    
    public static void removeLanguageListener(final int n) {
        BidiUtil.languageMap.remove(new LONG(n));
        unsubclass(n);
    }
    
    public static void removeLanguageListener(final Control control) {
        removeLanguageListener(control.handle);
    }
    
    public static void setKeyboardLanguage(final int n) {
        if (n == getKeyboardLanguage()) {
            return;
        }
        final boolean b = n == 1;
        final int[] keyboardLanguageList = getKeyboardLanguageList();
        for (int i = 0; i < keyboardLanguageList.length; ++i) {
            if (b == isBidiLang(keyboardLanguageList[i])) {
                OS.ActivateKeyboardLayout(keyboardLanguageList[i], 0);
                return;
            }
        }
    }
    
    public static boolean setOrientation(final int n, final int n2) {
        if (OS.IsWinCE) {
            return false;
        }
        if (OS.WIN32_VERSION < OS.VERSION(4, 10)) {
            return false;
        }
        final int getWindowLong = OS.GetWindowLong(n, -20);
        int n3;
        if ((n2 & 0x4000000) != 0x0) {
            n3 = (getWindowLong | 0x400000);
        }
        else {
            n3 = (getWindowLong & 0xFFBFFFFF);
        }
        OS.SetWindowLong(n, -20, n3);
        return true;
    }
    
    public static boolean setOrientation(final Control control, final int n) {
        return setOrientation(control.handle, n);
    }
    
    static void subclass(final int n) {
        final LONG long1 = new LONG(n);
        if (BidiUtil.oldProcMap.get(long1) == null) {
            BidiUtil.oldProcMap.put(long1, new LONG(OS.GetWindowLongPtr(n, -4)));
            OS.SetWindowLongPtr(n, -4, BidiUtil.callback.getAddress());
        }
    }
    
    static void reverse(final char[] array) {
        for (int length = array.length, i = 0; i <= (length - 1) / 2; ++i) {
            final char c = array[i];
            array[i] = array[length - 1 - i];
            array[length - 1 - i] = c;
        }
    }
    
    static void reverse(final int[] array) {
        for (int length = array.length, i = 0; i <= (length - 1) / 2; ++i) {
            final int n = array[i];
            array[i] = array[length - 1 - i];
            array[length - 1 - i] = n;
        }
    }
    
    static void translateOrder(final int[] array, final int n, final boolean b) {
        int max = 0;
        final int length = array.length;
        if (b) {
            for (int i = 0; i < length; ++i) {
                max = Math.max(max, array[i]);
            }
        }
        for (int j = 0; j < length; ++j) {
            if (b) {
                array[j] = max - array[j];
            }
            final int n2 = j;
            array[n2] += n;
        }
    }
    
    static void unsubclass(final int n) {
        final LONG long1 = new LONG(n);
        if (BidiUtil.languageMap.get(long1) == null && BidiUtil.keyMap.get(long1) == null) {
            final LONG long2 = BidiUtil.oldProcMap.remove(long1);
            if (long2 == null) {
                return;
            }
            OS.SetWindowLongPtr(n, -4, long2.value);
        }
    }
    
    static int windowProc(final int n, final int n2, final int n3, final int n4) {
        final LONG long1 = new LONG(n);
        switch (n2) {
            case 81: {
                final Runnable runnable = BidiUtil.languageMap.get(long1);
                if (runnable != null) {
                    runnable.run();
                    break;
                }
                break;
            }
        }
        return OS.CallWindowProc(((LONG)BidiUtil.oldProcMap.get(long1)).value, n, n2, n3, n4);
    }
}
