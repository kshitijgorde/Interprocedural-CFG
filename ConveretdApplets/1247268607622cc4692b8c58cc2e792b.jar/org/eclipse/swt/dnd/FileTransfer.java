// 
// Decompiled by Procyon v0.5.30
// 

package org.eclipse.swt.dnd;

import org.eclipse.swt.internal.ole.win32.FORMATETC;
import org.eclipse.swt.internal.ole.win32.IDataObject;
import org.eclipse.swt.internal.ole.win32.STGMEDIUM;
import org.eclipse.swt.internal.win32.OS;
import org.eclipse.swt.internal.win32.DROPFILES;
import org.eclipse.swt.internal.win32.TCHAR;

public class FileTransfer extends ByteArrayTransfer
{
    private static FileTransfer _instance;
    private static final String CF_HDROP = "CF_HDROP ";
    private static final int CF_HDROPID = 15;
    private static final String CF_HDROP_SEPARATOR = "\u0000";
    
    static {
        FileTransfer._instance = new FileTransfer();
    }
    
    public static FileTransfer getInstance() {
        return FileTransfer._instance;
    }
    
    public void javaToNative(final Object o, final TransferData transferData) {
        if (!this.checkFile(o) || !this.isSupportedType(transferData)) {
            DND.error(2003);
        }
        final String[] array = (String[])o;
        final StringBuffer sb = new StringBuffer();
        for (int i = 0; i < array.length; ++i) {
            sb.append(array[i]);
            sb.append("\u0000");
        }
        final TCHAR tchar = new TCHAR(0, sb.toString(), true);
        final DROPFILES dropfiles = new DROPFILES();
        dropfiles.pFiles = DROPFILES.sizeof;
        final DROPFILES dropfiles2 = dropfiles;
        final DROPFILES dropfiles3 = dropfiles;
        final boolean b = false;
        dropfiles3.pt_y = (b ? 1 : 0);
        dropfiles2.pt_x = (b ? 1 : 0);
        dropfiles.fNC = 0;
        dropfiles.fWide = (OS.IsUnicode ? 1 : 0);
        final int n = tchar.length() * TCHAR.sizeof;
        final int globalAlloc = OS.GlobalAlloc(64, DROPFILES.sizeof + n);
        OS.MoveMemory(globalAlloc, dropfiles, DROPFILES.sizeof);
        OS.MoveMemory(globalAlloc + DROPFILES.sizeof, tchar, n);
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
        final FORMATETC formatetc = new FORMATETC();
        formatetc.cfFormat = 15;
        formatetc.ptd = 0;
        formatetc.dwAspect = 1;
        formatetc.lindex = -1;
        formatetc.tymed = 1;
        final STGMEDIUM stgmedium = new STGMEDIUM();
        stgmedium.tymed = 1;
        transferData.result = this.getData(dataObject, formatetc, stgmedium);
        dataObject.Release();
        if (transferData.result != 0) {
            return null;
        }
        final int dragQueryFile = OS.DragQueryFile(stgmedium.unionField, -1, null, 0);
        final String[] array = new String[dragQueryFile];
        for (int i = 0; i < dragQueryFile; ++i) {
            final int n = OS.DragQueryFile(stgmedium.unionField, i, null, 0) + 1;
            final TCHAR tchar = new TCHAR(0, n);
            OS.DragQueryFile(stgmedium.unionField, i, tchar, n);
            array[i] = tchar.toString(0, tchar.strlen());
        }
        OS.DragFinish(stgmedium.unionField);
        return array;
    }
    
    protected int[] getTypeIds() {
        return new int[] { 15 };
    }
    
    protected String[] getTypeNames() {
        return new String[] { "CF_HDROP " };
    }
    
    boolean checkFile(final Object o) {
        if (o == null || !(o instanceof String[]) || ((String[])o).length == 0) {
            return false;
        }
        final String[] array = (String[])o;
        for (int i = 0; i < array.length; ++i) {
            if (array[i] == null || array[i].length() == 0) {
                return false;
            }
        }
        return true;
    }
    
    protected boolean validate(final Object o) {
        return this.checkFile(o);
    }
}
