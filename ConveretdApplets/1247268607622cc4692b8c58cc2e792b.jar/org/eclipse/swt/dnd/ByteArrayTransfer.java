// 
// Decompiled by Procyon v0.5.30
// 

package org.eclipse.swt.dnd;

import org.eclipse.swt.internal.ole.win32.IDataObject;
import org.eclipse.swt.internal.ole.win32.STGMEDIUM;
import org.eclipse.swt.internal.win32.OS;
import org.eclipse.swt.internal.ole.win32.FORMATETC;

public abstract class ByteArrayTransfer extends Transfer
{
    public TransferData[] getSupportedTypes() {
        final int[] typeIds = this.getTypeIds();
        final TransferData[] array = new TransferData[typeIds.length];
        for (int i = 0; i < typeIds.length; ++i) {
            array[i] = new TransferData();
            array[i].type = typeIds[i];
            array[i].formatetc = new FORMATETC();
            array[i].formatetc.cfFormat = typeIds[i];
            array[i].formatetc.dwAspect = 1;
            array[i].formatetc.lindex = -1;
            array[i].formatetc.tymed = 1;
        }
        return array;
    }
    
    public boolean isSupportedType(final TransferData transferData) {
        if (transferData == null) {
            return false;
        }
        final int[] typeIds = this.getTypeIds();
        for (int i = 0; i < typeIds.length; ++i) {
            final FORMATETC formatetc = transferData.formatetc;
            if (formatetc.cfFormat == typeIds[i] && (formatetc.dwAspect & 0x1) == 0x1 && (formatetc.tymed & 0x1) == 0x1) {
                return true;
            }
        }
        return false;
    }
    
    protected void javaToNative(final Object o, final TransferData transferData) {
        if (!this.checkByteArray(o) || !this.isSupportedType(transferData)) {
            DND.error(2003);
        }
        final byte[] array = (byte[])o;
        final int length = array.length;
        final int globalAlloc = OS.GlobalAlloc(64, length);
        OS.MoveMemory(globalAlloc, array, length);
        transferData.stgmedium = new STGMEDIUM();
        transferData.stgmedium.tymed = 1;
        transferData.stgmedium.unionField = globalAlloc;
        transferData.stgmedium.pUnkForRelease = 0;
        transferData.result = 0;
    }
    
    protected Object nativeToJava(final TransferData transferData) {
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
        final int globalSize = OS.GlobalSize(unionField);
        final byte[] array = new byte[globalSize];
        OS.MoveMemory(array, OS.GlobalLock(unionField), globalSize);
        OS.GlobalUnlock(unionField);
        OS.GlobalFree(unionField);
        return array;
    }
    
    boolean checkByteArray(final Object o) {
        return o != null && o instanceof byte[] && ((byte[])o).length > 0;
    }
}
