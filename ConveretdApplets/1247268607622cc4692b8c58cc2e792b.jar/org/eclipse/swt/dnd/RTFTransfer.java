// 
// Decompiled by Procyon v0.5.30
// 

package org.eclipse.swt.dnd;

import org.eclipse.swt.internal.ole.win32.FORMATETC;
import org.eclipse.swt.internal.ole.win32.IDataObject;
import org.eclipse.swt.internal.ole.win32.STGMEDIUM;
import org.eclipse.swt.internal.win32.OS;

public class RTFTransfer extends ByteArrayTransfer
{
    private static RTFTransfer _instance;
    private static final String CF_RTF = "Rich Text Format";
    private static final int CF_RTFID;
    
    static {
        RTFTransfer._instance = new RTFTransfer();
        CF_RTFID = Transfer.registerType("Rich Text Format");
    }
    
    public static RTFTransfer getInstance() {
        return RTFTransfer._instance;
    }
    
    public void javaToNative(final Object o, final TransferData transferData) {
        if (!this.checkRTF(o) || !this.isSupportedType(transferData)) {
            DND.error(2003);
        }
        final String s = (String)o;
        final int length = s.length();
        final char[] array = new char[length + 1];
        s.getChars(0, length, array, 0);
        final int getACP = OS.GetACP();
        final int wideCharToMultiByte = OS.WideCharToMultiByte(getACP, 0, array, -1, null, 0, null, null);
        if (wideCharToMultiByte == 0) {
            transferData.stgmedium = new STGMEDIUM();
            transferData.result = -2147221402;
            return;
        }
        final int globalAlloc = OS.GlobalAlloc(64, wideCharToMultiByte);
        OS.WideCharToMultiByte(getACP, 0, array, -1, globalAlloc, wideCharToMultiByte, null, null);
        transferData.stgmedium = new STGMEDIUM();
        transferData.stgmedium.tymed = 1;
        transferData.stgmedium.unionField = globalAlloc;
        transferData.stgmedium.pUnkForRelease = 0;
        transferData.result = 0;
    }
    
    public Object nativeToJava(final TransferData transferData) {
        if (!this.isSupportedType(transferData) || transferData.pIDataObject == 0) {
            return null;
        }
        final IDataObject dataObject = new IDataObject(transferData.pIDataObject);
        dataObject.AddRef();
        final STGMEDIUM stgmedium = new STGMEDIUM();
        final FORMATETC formatetc = transferData.formatetc;
        stgmedium.tymed = 1;
        transferData.result = this.getData(dataObject, formatetc, stgmedium);
        dataObject.Release();
        if (transferData.result != 0) {
            return null;
        }
        final int unionField = stgmedium.unionField;
        try {
            final int globalLock = OS.GlobalLock(unionField);
            if (globalLock != 0) {
                try {
                    final int getACP = OS.GetACP();
                    final int multiByteToWideChar = OS.MultiByteToWideChar(getACP, 1, globalLock, -1, null, 0);
                    if (multiByteToWideChar == 0) {
                        return null;
                    }
                    final char[] array = new char[multiByteToWideChar - 1];
                    OS.MultiByteToWideChar(getACP, 1, globalLock, -1, array, array.length);
                    return new String(array);
                }
                finally {
                    OS.GlobalUnlock(unionField);
                }
            }
            return null;
        }
        finally {
            OS.GlobalFree(unionField);
        }
    }
    
    protected int[] getTypeIds() {
        return new int[] { RTFTransfer.CF_RTFID };
    }
    
    protected String[] getTypeNames() {
        return new String[] { "Rich Text Format" };
    }
    
    boolean checkRTF(final Object o) {
        return o != null && o instanceof String && ((String)o).length() > 0;
    }
    
    protected boolean validate(final Object o) {
        return this.checkRTF(o);
    }
}
