// 
// Decompiled by Procyon v0.5.30
// 

package org.eclipse.swt.dnd;

import org.eclipse.swt.internal.ole.win32.STGMEDIUM;
import org.eclipse.swt.internal.ole.win32.FORMATETC;

public class TransferData
{
    public int type;
    public FORMATETC formatetc;
    public STGMEDIUM stgmedium;
    public int result;
    public int pIDataObject;
    
    public TransferData() {
        this.result = -2147467259;
    }
    
    static boolean sameType(final TransferData transferData, final TransferData transferData2) {
        return transferData == transferData2 || (transferData != null && transferData2 != null && (transferData.type == transferData2.type && transferData.formatetc.cfFormat == transferData2.formatetc.cfFormat && transferData.formatetc.dwAspect == transferData2.formatetc.dwAspect && transferData.formatetc.tymed == transferData2.formatetc.tymed));
    }
}
