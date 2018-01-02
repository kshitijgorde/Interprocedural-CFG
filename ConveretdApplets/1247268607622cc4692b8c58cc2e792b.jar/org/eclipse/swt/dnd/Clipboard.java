// 
// Decompiled by Procyon v0.5.30
// 

package org.eclipse.swt.dnd;

import org.eclipse.swt.internal.ole.win32.IEnumFORMATETC;
import org.eclipse.swt.internal.ole.win32.GUID;
import org.eclipse.swt.internal.ole.win32.STGMEDIUM;
import org.eclipse.swt.internal.ole.win32.FORMATETC;
import org.eclipse.swt.internal.ole.win32.IDataObject;
import org.eclipse.swt.internal.win32.MSG;
import org.eclipse.swt.internal.ole.win32.COM;
import org.eclipse.swt.internal.win32.OS;
import org.eclipse.swt.internal.win32.TCHAR;
import org.eclipse.swt.internal.ole.win32.COMObject;
import org.eclipse.swt.widgets.Display;

public class Clipboard
{
    private static final int RETRY_LIMIT = 10;
    private Display display;
    private COMObject iDataObject;
    private int refCount;
    private Transfer[] transferAgents;
    private Object[] data;
    private int CFSTR_PREFERREDDROPEFFECT;
    static /* synthetic */ Class class$0;
    
    public Clipboard(Display display) {
        this.transferAgents = new Transfer[0];
        this.data = new Object[0];
        this.checkSubclass();
        if (display == null) {
            display = Display.getCurrent();
            if (display == null) {
                display = Display.getDefault();
            }
        }
        if (display.getThread() != Thread.currentThread()) {
            DND.error(22);
        }
        this.display = display;
        this.CFSTR_PREFERREDDROPEFFECT = OS.RegisterClipboardFormat(new TCHAR(0, "Preferred DropEffect", true));
        this.createCOMInterfaces();
        this.AddRef();
    }
    
    protected void checkSubclass() {
        final String name = this.getClass().getName();
        Class class$0;
        if ((class$0 = Clipboard.class$0) == null) {
            try {
                class$0 = (Clipboard.class$0 = Class.forName("org.eclipse.swt.dnd.Clipboard"));
            }
            catch (ClassNotFoundException ex) {
                throw new NoClassDefFoundError(ex.getMessage());
            }
        }
        if (!class$0.getName().equals(name)) {
            DND.error(43);
        }
    }
    
    protected void checkWidget() {
        final Display display = this.display;
        if (display == null) {
            DND.error(24);
        }
        if (display.getThread() != Thread.currentThread()) {
            DND.error(22);
        }
        if (display.isDisposed()) {
            DND.error(24);
        }
    }
    
    public void clearContents() {
        this.clearContents(1);
    }
    
    public void clearContents(final int n) {
        this.checkWidget();
        if ((n & 0x1) != 0x0 && COM.OleIsCurrentClipboard(this.iDataObject.getAddress()) == 0) {
            COM.OleSetClipboard(0);
        }
    }
    
    public void dispose() {
        if (this.isDisposed()) {
            return;
        }
        if (this.display.getThread() != Thread.currentThread()) {
            DND.error(22);
        }
        if (COM.OleIsCurrentClipboard(this.iDataObject.getAddress()) == 0) {
            COM.OleFlushClipboard();
        }
        this.Release();
        this.display = null;
    }
    
    public Object getContents(final Transfer transfer) {
        return this.getContents(transfer, 1);
    }
    
    public Object getContents(final Transfer transfer, final int n) {
        this.checkWidget();
        if (transfer == null) {
            DND.error(4);
        }
        if ((n & 0x1) == 0x0) {
            return null;
        }
        int[] array;
        int n2;
        int n3;
        for (array = new int[] { 0 }, n2 = 0, n3 = COM.OleGetClipboard(array); n3 != 0 && n2++ < 10; n3 = COM.OleGetClipboard(array)) {
            try {
                Thread.sleep(50L);
            }
            catch (Throwable t) {}
            OS.PeekMessage(new MSG(), 0, 0, 0, 2);
        }
        if (n3 != 0) {
            return null;
        }
        final IDataObject dataObject = new IDataObject(array[0]);
        try {
            final TransferData[] supportedTypes = transfer.getSupportedTypes();
            for (int i = 0; i < supportedTypes.length; ++i) {
                if (dataObject.QueryGetData(supportedTypes[i].formatetc) == 0) {
                    final TransferData transferData = supportedTypes[i];
                    transferData.pIDataObject = array[0];
                    return transfer.nativeToJava(transferData);
                }
            }
        }
        finally {
            dataObject.Release();
        }
        dataObject.Release();
        return null;
    }
    
    public boolean isDisposed() {
        return this.display == null;
    }
    
    public void setContents(final Object[] array, final Transfer[] array2) {
        this.setContents(array, array2, 1);
    }
    
    public void setContents(final Object[] data, final Transfer[] transferAgents, final int n) {
        this.checkWidget();
        if (data == null || transferAgents == null || data.length != transferAgents.length || data.length == 0) {
            DND.error(5);
        }
        for (int i = 0; i < data.length; ++i) {
            if (data[i] == null || transferAgents[i] == null || !transferAgents[i].validate(data[i])) {
                DND.error(5);
            }
        }
        if ((n & 0x1) == 0x0) {
            return;
        }
        this.data = data;
        this.transferAgents = transferAgents;
        int n2 = COM.OleSetClipboard(this.iDataObject.getAddress());
        for (int n3 = 0; n2 != 0 && n3++ < 10; n2 = COM.OleSetClipboard(this.iDataObject.getAddress())) {
            try {
                Thread.sleep(50L);
            }
            catch (Throwable t) {}
            OS.PeekMessage(new MSG(), 0, 0, 0, 2);
        }
        if (n2 != 0) {
            DND.error(2002);
        }
    }
    
    private int AddRef() {
        return ++this.refCount;
    }
    
    private void createCOMInterfaces() {
        this.iDataObject = new COMObject(new int[] { 2, 0, 0, 2, 2, 1, 2, 3, 2, 4, 1, 1 }) {
            public int method0(final int[] array) {
                return Clipboard.this.QueryInterface(array[0], array[1]);
            }
            
            public int method1(final int[] array) {
                return Clipboard.this.AddRef();
            }
            
            public int method2(final int[] array) {
                return Clipboard.this.Release();
            }
            
            public int method3(final int[] array) {
                return Clipboard.this.GetData(array[0], array[1]);
            }
            
            public int method5(final int[] array) {
                return Clipboard.this.QueryGetData(array[0]);
            }
            
            public int method8(final int[] array) {
                return Clipboard.this.EnumFormatEtc(array[0], array[1]);
            }
        };
    }
    
    private void disposeCOMInterfaces() {
        if (this.iDataObject != null) {
            this.iDataObject.dispose();
        }
        this.iDataObject = null;
    }
    
    private int EnumFormatEtc(final int n, final int n2) {
        if (n == 2) {
            return -2147467263;
        }
        TransferData[] array = new TransferData[0];
        for (int i = 0; i < this.transferAgents.length; ++i) {
            final TransferData[] supportedTypes = this.transferAgents[i].getSupportedTypes();
            final TransferData[] array2 = new TransferData[array.length + supportedTypes.length];
            System.arraycopy(array, 0, array2, 0, array.length);
            System.arraycopy(supportedTypes, 0, array2, array.length, supportedTypes.length);
            array = array2;
        }
        final OleEnumFORMATETC oleEnumFORMATETC = new OleEnumFORMATETC();
        oleEnumFORMATETC.AddRef();
        final FORMATETC[] formats = new FORMATETC[array.length + 1];
        for (int j = 0; j < array.length; ++j) {
            formats[j] = array[j].formatetc;
        }
        final FORMATETC formatetc = new FORMATETC();
        formatetc.cfFormat = this.CFSTR_PREFERREDDROPEFFECT;
        formatetc.dwAspect = 1;
        formatetc.lindex = -1;
        formatetc.tymed = 1;
        formats[formats.length - 1] = formatetc;
        oleEnumFORMATETC.setFormats(formats);
        OS.MoveMemory(n2, new int[] { oleEnumFORMATETC.getAddress() }, OS.PTR_SIZEOF);
        return 0;
    }
    
    private int GetData(final int n, final int n2) {
        if (n == 0 || n2 == 0) {
            return -2147024809;
        }
        if (this.QueryGetData(n) != 0) {
            return -2147221404;
        }
        final TransferData transferData = new TransferData();
        COM.MoveMemory(transferData.formatetc = new FORMATETC(), n, FORMATETC.sizeof);
        transferData.type = transferData.formatetc.cfFormat;
        transferData.stgmedium = new STGMEDIUM();
        transferData.result = -2147467259;
        if (transferData.type == this.CFSTR_PREFERREDDROPEFFECT) {
            final STGMEDIUM stgmedium = new STGMEDIUM();
            stgmedium.tymed = 1;
            OS.MoveMemory(stgmedium.unionField = OS.GlobalAlloc(64, 4), new int[] { 1 }, 4);
            stgmedium.pUnkForRelease = 0;
            COM.MoveMemory(n2, stgmedium, STGMEDIUM.sizeof);
            return 0;
        }
        int n3 = -1;
        for (int i = 0; i < this.transferAgents.length; ++i) {
            if (this.transferAgents[i].isSupportedType(transferData)) {
                n3 = i;
                break;
            }
        }
        if (n3 == -1) {
            return -2147221404;
        }
        this.transferAgents[n3].javaToNative(this.data[n3], transferData);
        COM.MoveMemory(n2, transferData.stgmedium, STGMEDIUM.sizeof);
        return transferData.result;
    }
    
    private int QueryGetData(final int n) {
        if (this.transferAgents == null) {
            return -2147467259;
        }
        final TransferData transferData = new TransferData();
        COM.MoveMemory(transferData.formatetc = new FORMATETC(), n, FORMATETC.sizeof);
        transferData.type = transferData.formatetc.cfFormat;
        if (transferData.type == this.CFSTR_PREFERREDDROPEFFECT) {
            return 0;
        }
        for (int i = 0; i < this.transferAgents.length; ++i) {
            if (this.transferAgents[i].isSupportedType(transferData)) {
                return 0;
            }
        }
        return -2147221404;
    }
    
    private int QueryInterface(final int n, final int n2) {
        if (n == 0 || n2 == 0) {
            return -2147024809;
        }
        final GUID guid = new GUID();
        COM.MoveMemory(guid, n, GUID.sizeof);
        if (COM.IsEqualGUID(guid, COM.IIDIUnknown) || COM.IsEqualGUID(guid, COM.IIDIDataObject)) {
            OS.MoveMemory(n2, new int[] { this.iDataObject.getAddress() }, OS.PTR_SIZEOF);
            this.AddRef();
            return 0;
        }
        OS.MoveMemory(n2, new int[1], OS.PTR_SIZEOF);
        return -2147467262;
    }
    
    private int Release() {
        --this.refCount;
        if (this.refCount == 0) {
            this.data = new Object[0];
            this.transferAgents = new Transfer[0];
            this.disposeCOMInterfaces();
            if (COM.FreeUnusedLibraries) {
                COM.CoFreeUnusedLibraries();
            }
        }
        return this.refCount;
    }
    
    public TransferData[] getAvailableTypes() {
        return this.getAvailableTypes(1);
    }
    
    public TransferData[] getAvailableTypes(final int n) {
        this.checkWidget();
        if ((n & 0x1) == 0x0) {
            return new TransferData[0];
        }
        final FORMATETC[] getAvailableTypes = this._getAvailableTypes();
        final TransferData[] array = new TransferData[getAvailableTypes.length];
        for (int i = 0; i < getAvailableTypes.length; ++i) {
            array[i] = new TransferData();
            array[i].type = getAvailableTypes[i].cfFormat;
            array[i].formatetc = getAvailableTypes[i];
        }
        return array;
    }
    
    public String[] getAvailableTypeNames() {
        this.checkWidget();
        final FORMATETC[] getAvailableTypes = this._getAvailableTypes();
        final String[] array = new String[getAvailableTypes.length];
        final int n = 128;
        for (int i = 0; i < getAvailableTypes.length; ++i) {
            final TCHAR tchar = new TCHAR(0, n);
            final int getClipboardFormatName = OS.GetClipboardFormatName(getAvailableTypes[i].cfFormat, tchar, n);
            if (getClipboardFormatName != 0) {
                array[i] = tchar.toString(0, getClipboardFormatName);
            }
            else {
                switch (getAvailableTypes[i].cfFormat) {
                    case 15: {
                        array[i] = "CF_HDROP";
                        break;
                    }
                    case 1: {
                        array[i] = "CF_TEXT";
                        break;
                    }
                    case 2: {
                        array[i] = "CF_BITMAP";
                        break;
                    }
                    case 3: {
                        array[i] = "CF_METAFILEPICT";
                        break;
                    }
                    case 4: {
                        array[i] = "CF_SYLK";
                        break;
                    }
                    case 5: {
                        array[i] = "CF_DIF";
                        break;
                    }
                    case 6: {
                        array[i] = "CF_TIFF";
                        break;
                    }
                    case 7: {
                        array[i] = "CF_OEMTEXT";
                        break;
                    }
                    case 8: {
                        array[i] = "CF_DIB";
                        break;
                    }
                    case 9: {
                        array[i] = "CF_PALETTE";
                        break;
                    }
                    case 10: {
                        array[i] = "CF_PENDATA";
                        break;
                    }
                    case 11: {
                        array[i] = "CF_RIFF";
                        break;
                    }
                    case 12: {
                        array[i] = "CF_WAVE";
                        break;
                    }
                    case 13: {
                        array[i] = "CF_UNICODETEXT";
                        break;
                    }
                    case 14: {
                        array[i] = "CF_ENHMETAFILE";
                        break;
                    }
                    case 16: {
                        array[i] = "CF_LOCALE";
                        break;
                    }
                    case 17: {
                        array[i] = "CF_MAX";
                        break;
                    }
                    default: {
                        array[i] = "UNKNOWN";
                        break;
                    }
                }
            }
        }
        return array;
    }
    
    private FORMATETC[] _getAvailableTypes() {
        FORMATETC[] array = new FORMATETC[0];
        final int[] array2 = { 0 };
        if (COM.OleGetClipboard(array2) != 0) {
            return array;
        }
        final IDataObject dataObject = new IDataObject(array2[0]);
        final int[] array3 = { 0 };
        final int enumFormatEtc = dataObject.EnumFormatEtc(1, array3);
        dataObject.Release();
        if (enumFormatEtc != 0) {
            return array;
        }
        final IEnumFORMATETC enumFORMATETC = new IEnumFORMATETC(array3[0]);
        final int globalAlloc = OS.GlobalAlloc(64, FORMATETC.sizeof);
        final int[] array4 = { 0 };
        enumFORMATETC.Reset();
        while (enumFORMATETC.Next(1, globalAlloc, array4) == 0 && array4[0] == 1) {
            final FORMATETC formatetc = new FORMATETC();
            COM.MoveMemory(formatetc, globalAlloc, FORMATETC.sizeof);
            final FORMATETC[] array5 = new FORMATETC[array.length + 1];
            System.arraycopy(array, 0, array5, 0, array.length);
            array5[array.length] = formatetc;
            array = array5;
        }
        OS.GlobalFree(globalAlloc);
        enumFORMATETC.Release();
        return array;
    }
}
