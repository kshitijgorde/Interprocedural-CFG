// 
// Decompiled by Procyon v0.5.30
// 

package org.eclipse.swt.dnd;

import org.eclipse.swt.internal.ole.win32.FORMATETC;
import org.eclipse.swt.internal.ole.win32.IDataObject;
import org.eclipse.swt.internal.ole.win32.STGMEDIUM;
import org.eclipse.swt.internal.win32.OS;

public class TextTransfer extends ByteArrayTransfer
{
    private static TextTransfer _instance;
    private static final String CF_UNICODETEXT = "CF_UNICODETEXT";
    private static final String CF_TEXT = "CF_TEXT";
    private static final int CF_UNICODETEXTID = 13;
    private static final int CF_TEXTID = 1;
    
    static {
        TextTransfer._instance = new TextTransfer();
    }
    
    public static TextTransfer getInstance() {
        return TextTransfer._instance;
    }
    
    public void javaToNative(final Object o, final TransferData transferData) {
        if (!this.checkText(o) || !this.isSupportedType(transferData)) {
            DND.error(2003);
        }
        transferData.result = -2147467259;
        final String s = (String)o;
        switch (transferData.type) {
            case 13: {
                final int length = s.length();
                final char[] array = new char[length + 1];
                s.getChars(0, length, array, 0);
                final int n = array.length * 2;
                final int globalAlloc = OS.GlobalAlloc(64, n);
                OS.MoveMemory(globalAlloc, array, n);
                transferData.stgmedium = new STGMEDIUM();
                transferData.stgmedium.tymed = 1;
                transferData.stgmedium.unionField = globalAlloc;
                transferData.stgmedium.pUnkForRelease = 0;
                transferData.result = 0;
                break;
            }
            case 1: {
                final int length2 = s.length();
                final char[] array2 = new char[length2 + 1];
                s.getChars(0, length2, array2, 0);
                final int getACP = OS.GetACP();
                final int wideCharToMultiByte = OS.WideCharToMultiByte(getACP, 0, array2, -1, null, 0, null, null);
                if (wideCharToMultiByte == 0) {
                    transferData.stgmedium = new STGMEDIUM();
                    transferData.result = -2147221402;
                    return;
                }
                final int globalAlloc2 = OS.GlobalAlloc(64, wideCharToMultiByte);
                OS.WideCharToMultiByte(getACP, 0, array2, -1, globalAlloc2, wideCharToMultiByte, null, null);
                transferData.stgmedium = new STGMEDIUM();
                transferData.stgmedium.tymed = 1;
                transferData.stgmedium.unionField = globalAlloc2;
                transferData.stgmedium.pUnkForRelease = 0;
                transferData.result = 0;
                break;
            }
        }
    }
    
    public Object nativeToJava(final TransferData transferData) {
        if (!this.isSupportedType(transferData) || transferData.pIDataObject == 0) {
            return null;
        }
        final IDataObject dataObject = new IDataObject(transferData.pIDataObject);
        dataObject.AddRef();
        final FORMATETC formatetc = transferData.formatetc;
        final STGMEDIUM stgmedium = new STGMEDIUM();
        stgmedium.tymed = 1;
        transferData.result = this.getData(dataObject, formatetc, stgmedium);
        dataObject.Release();
        if (transferData.result != 0) {
            return null;
        }
        final int unionField = stgmedium.unionField;
        try {
            Label_0408: {
                switch (transferData.type) {
                    case 13: {
                        final int n = OS.GlobalSize(unionField) / 2 * 2;
                        if (n == 0) {
                            break;
                        }
                        final char[] array = new char[n / 2];
                        final int globalLock = OS.GlobalLock(unionField);
                        if (globalLock == 0) {
                            break;
                        }
                        try {
                            OS.MoveMemory(array, globalLock, n);
                            int length = array.length;
                            for (int i = 0; i < array.length; ++i) {
                                if (array[i] == '\0') {
                                    length = i;
                                    break;
                                }
                            }
                            return new String(array, 0, length);
                        }
                        finally {
                            OS.GlobalUnlock(unionField);
                        }
                    }
                    case 1: {
                        final int globalLock2 = OS.GlobalLock(unionField);
                        if (globalLock2 == 0) {
                            break;
                        }
                        try {
                            final int getACP = OS.GetACP();
                            final int multiByteToWideChar = OS.MultiByteToWideChar(getACP, 1, globalLock2, -1, null, 0);
                            if (multiByteToWideChar == 0) {
                                break;
                            }
                            final char[] array2 = new char[multiByteToWideChar - 1];
                            OS.MultiByteToWideChar(getACP, 1, globalLock2, -1, array2, array2.length);
                            return new String(array2);
                        }
                        finally {
                            OS.GlobalUnlock(unionField);
                        }
                        break Label_0408;
                    }
                }
                return null;
            }
        }
        finally {
            OS.GlobalFree(unionField);
        }
        OS.GlobalFree(unionField);
        return null;
    }
    
    protected int[] getTypeIds() {
        return new int[] { 13, 1 };
    }
    
    protected String[] getTypeNames() {
        return new String[] { "CF_UNICODETEXT", "CF_TEXT" };
    }
    
    boolean checkText(final Object o) {
        return o != null && o instanceof String && ((String)o).length() > 0;
    }
    
    protected boolean validate(final Object o) {
        return this.checkText(o);
    }
}
