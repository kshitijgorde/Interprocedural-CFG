// 
// Decompiled by Procyon v0.5.30
// 

package org.eclipse.swt.dnd;

import org.eclipse.swt.internal.win32.TCHAR;
import org.eclipse.swt.internal.win32.OS;
import org.eclipse.swt.internal.win32.MSG;
import org.eclipse.swt.internal.ole.win32.STGMEDIUM;
import org.eclipse.swt.internal.ole.win32.FORMATETC;
import org.eclipse.swt.internal.ole.win32.IDataObject;

public abstract class Transfer
{
    private static final int RETRY_LIMIT = 10;
    
    int getData(final IDataObject dataObject, final FORMATETC formatetc, final STGMEDIUM stgmedium) {
        if (dataObject.GetData(formatetc, stgmedium) == 0) {
            return 0;
        }
        try {
            Thread.sleep(50L);
        }
        catch (Throwable t) {}
        int n = dataObject.GetData(formatetc, stgmedium);
        for (int n2 = 0; n != 0 && n2++ < 10; n = dataObject.GetData(formatetc, stgmedium)) {
            OS.PeekMessage(new MSG(), 0, 0, 0, 2);
            try {
                Thread.sleep(50L);
            }
            catch (Throwable t2) {}
        }
        return n;
    }
    
    public abstract TransferData[] getSupportedTypes();
    
    public abstract boolean isSupportedType(final TransferData p0);
    
    protected abstract int[] getTypeIds();
    
    protected abstract String[] getTypeNames();
    
    protected abstract void javaToNative(final Object p0, final TransferData p1);
    
    protected abstract Object nativeToJava(final TransferData p0);
    
    public static int registerType(final String s) {
        return OS.RegisterClipboardFormat(new TCHAR(0, s, true));
    }
    
    protected boolean validate(final Object o) {
        return true;
    }
}
