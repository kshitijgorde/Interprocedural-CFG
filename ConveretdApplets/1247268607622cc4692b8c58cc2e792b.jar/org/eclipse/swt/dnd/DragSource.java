// 
// Decompiled by Procyon v0.5.30
// 

package org.eclipse.swt.dnd;

import org.eclipse.swt.internal.ole.win32.GUID;
import org.eclipse.swt.internal.ole.win32.STGMEDIUM;
import org.eclipse.swt.internal.ole.win32.FORMATETC;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.internal.ole.win32.COM;
import org.eclipse.swt.internal.win32.POINT;
import org.eclipse.swt.internal.win32.CREATESTRUCT;
import org.eclipse.swt.internal.win32.RECT;
import org.eclipse.swt.internal.ImageList;
import org.eclipse.swt.internal.win32.OS;
import org.eclipse.swt.internal.SWTEventListener;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.Tree;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.internal.win32.TCHAR;
import org.eclipse.swt.internal.ole.win32.COMObject;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Widget;

public class DragSource extends Widget
{
    Control control;
    Listener controlListener;
    Transfer[] transferAgents;
    DragSourceEffect dragEffect;
    Composite topControl;
    int hwndDrag;
    COMObject iDropSource;
    COMObject iDataObject;
    int refCount;
    int dataEffect;
    static final String DEFAULT_DRAG_SOURCE_EFFECT = "DEFAULT_DRAG_SOURCE_EFFECT";
    static final int CFSTR_PERFORMEDDROPEFFECT;
    static final TCHAR WindowClass;
    static /* synthetic */ Class class$0;
    
    static {
        CFSTR_PERFORMEDDROPEFFECT = Transfer.registerType("Performed DropEffect");
        WindowClass = new TCHAR(0, "#32770", true);
    }
    
    public DragSource(final Control control, final int n) {
        super(control, checkStyle(n));
        this.transferAgents = new Transfer[0];
        this.dataEffect = 0;
        this.control = control;
        if (control.getData("DragSource") != null) {
            DND.error(2000);
        }
        control.setData("DragSource", this);
        this.createCOMInterfaces();
        this.AddRef();
        control.addListener(12, this.controlListener = new Listener() {
            public void handleEvent(final Event event) {
                if (event.type == 12 && !DragSource.this.isDisposed()) {
                    DragSource.this.dispose();
                }
                if (event.type == 29 && !DragSource.this.isDisposed()) {
                    DragSource.this.drag(event);
                }
            }
        });
        control.addListener(29, this.controlListener);
        this.addListener(12, new Listener() {
            public void handleEvent(final Event event) {
                DragSource.this.onDispose();
            }
        });
        final Object data = control.getData("DEFAULT_DRAG_SOURCE_EFFECT");
        if (data instanceof DragSourceEffect) {
            this.dragEffect = (DragSourceEffect)data;
        }
        else if (control instanceof Tree) {
            this.dragEffect = new TreeDragSourceEffect((Tree)control);
        }
        else if (control instanceof Table) {
            this.dragEffect = new TableDragSourceEffect((Table)control);
        }
    }
    
    static int checkStyle(final int n) {
        if (n == 0) {
            return 2;
        }
        return n;
    }
    
    public void addDragListener(final DragSourceListener dragSourceListener) {
        if (dragSourceListener == null) {
            DND.error(4);
        }
        final DNDListener dndListener = new DNDListener(dragSourceListener);
        (dndListener.dndWidget = this).addListener(2008, dndListener);
        this.addListener(2001, dndListener);
        this.addListener(2000, dndListener);
    }
    
    private int AddRef() {
        return ++this.refCount;
    }
    
    private void createCOMInterfaces() {
        this.iDropSource = new COMObject(new int[] { 2, 0, 0, 2, 1 }) {
            public int method0(final int[] array) {
                return DragSource.this.QueryInterface(array[0], array[1]);
            }
            
            public int method1(final int[] array) {
                return DragSource.this.AddRef();
            }
            
            public int method2(final int[] array) {
                return DragSource.this.Release();
            }
            
            public int method3(final int[] array) {
                return DragSource.this.QueryContinueDrag(array[0], array[1]);
            }
            
            public int method4(final int[] array) {
                return DragSource.this.GiveFeedback(array[0]);
            }
        };
        this.iDataObject = new COMObject(new int[] { 2, 0, 0, 2, 2, 1, 2, 3, 2, 4, 1, 1 }) {
            public int method0(final int[] array) {
                return DragSource.this.QueryInterface(array[0], array[1]);
            }
            
            public int method1(final int[] array) {
                return DragSource.this.AddRef();
            }
            
            public int method2(final int[] array) {
                return DragSource.this.Release();
            }
            
            public int method3(final int[] array) {
                return DragSource.this.GetData(array[0], array[1]);
            }
            
            public int method5(final int[] array) {
                return DragSource.this.QueryGetData(array[0]);
            }
            
            public int method7(final int[] array) {
                return DragSource.this.SetData(array[0], array[1], array[2]);
            }
            
            public int method8(final int[] array) {
                return DragSource.this.EnumFormatEtc(array[0], array[1]);
            }
        };
    }
    
    protected void checkSubclass() {
        final String name = this.getClass().getName();
        Class class$0;
        if ((class$0 = DragSource.class$0) == null) {
            try {
                class$0 = (DragSource.class$0 = Class.forName("org.eclipse.swt.dnd.DragSource"));
            }
            catch (ClassNotFoundException ex) {
                throw new NoClassDefFoundError(ex.getMessage());
            }
        }
        if (!class$0.getName().equals(name)) {
            DND.error(43);
        }
    }
    
    private void disposeCOMInterfaces() {
        if (this.iDropSource != null) {
            this.iDropSource.dispose();
        }
        this.iDropSource = null;
        if (this.iDataObject != null) {
            this.iDataObject.dispose();
        }
        this.iDataObject = null;
    }
    
    private void drag(final Event event) {
        final DNDEvent dndEvent = new DNDEvent();
        dndEvent.widget = this;
        dndEvent.x = event.x;
        dndEvent.y = event.y;
        dndEvent.time = OS.GetMessageTime();
        dndEvent.doit = true;
        this.notifyListeners(2008, dndEvent);
        if (!dndEvent.doit || this.transferAgents == null || this.transferAgents.length == 0) {
            return;
        }
        final int[] array = { 0 };
        final int opToOs = this.opToOs(this.getStyle());
        final Display display = this.control.getDisplay();
        final String s = "org.eclipse.swt.internal.win32.runMessagesInIdle";
        final Object data = display.getData(s);
        display.setData(s, new Boolean(true));
        ImageList list = null;
        final Image image = dndEvent.image;
        this.hwndDrag = 0;
        this.topControl = null;
        if (image != null) {
            list = new ImageList(0);
            list.add(image);
            this.topControl = this.control.getShell();
            int offsetX = dndEvent.offsetX;
            this.hwndDrag = this.topControl.handle;
            if ((this.topControl.getStyle() & 0x4000000) != 0x0) {
                offsetX = image.getBounds().width - offsetX;
                final RECT rect = new RECT();
                OS.GetClientRect(this.topControl.handle, rect);
                OS.ShowWindow(this.hwndDrag = OS.CreateWindowEx(1048608, DragSource.WindowClass, null, 1140850688, 0, 0, rect.right - rect.left, rect.bottom - rect.top, this.topControl.handle, 0, OS.GetModuleHandle(null), null), 5);
            }
            OS.ImageList_BeginDrag(list.getHandle(), 0, offsetX, dndEvent.offsetY);
            if (OS.IsWinCE) {
                OS.UpdateWindow(this.topControl.handle);
            }
            else {
                OS.RedrawWindow(this.topControl.handle, null, 0, 384);
            }
            final POINT point = new POINT();
            point.x = event.x;
            point.y = event.y;
            OS.MapWindowPoints(this.control.handle, 0, point, 1);
            final RECT rect2 = new RECT();
            OS.GetWindowRect(this.hwndDrag, rect2);
            OS.ImageList_DragEnter(this.hwndDrag, point.x - rect2.left, point.y - rect2.top);
        }
        int doDragDrop = 262401;
        try {
            doDragDrop = COM.DoDragDrop(this.iDataObject.getAddress(), this.iDropSource.getAddress(), opToOs, array);
        }
        finally {
            if (this.hwndDrag != 0) {
                OS.ImageList_DragLeave(this.hwndDrag);
                OS.ImageList_EndDrag();
                list.dispose();
                if (this.hwndDrag != this.topControl.handle) {
                    OS.DestroyWindow(this.hwndDrag);
                }
                this.hwndDrag = 0;
                this.topControl = null;
            }
            display.setData(s, data);
        }
        if (this.hwndDrag != 0) {
            OS.ImageList_DragLeave(this.hwndDrag);
            OS.ImageList_EndDrag();
            list.dispose();
            if (this.hwndDrag != this.topControl.handle) {
                OS.DestroyWindow(this.hwndDrag);
            }
            this.hwndDrag = 0;
            this.topControl = null;
        }
        display.setData(s, data);
        int detail = this.osToOp(array[0]);
        if (this.dataEffect == 2) {
            detail = ((detail == 0 || detail == 1) ? 8 : 2);
        }
        else if (this.dataEffect != 0) {
            detail = this.dataEffect;
        }
        final DNDEvent dndEvent2 = new DNDEvent();
        dndEvent2.widget = this;
        dndEvent2.time = OS.GetMessageTime();
        dndEvent2.doit = (doDragDrop == 262400);
        dndEvent2.detail = detail;
        this.notifyListeners(2000, dndEvent2);
        this.dataEffect = 0;
    }
    
    private int EnumFormatEtc(final int n, final int n2) {
        if (n == 2) {
            return -2147467263;
        }
        TransferData[] array = new TransferData[0];
        for (int i = 0; i < this.transferAgents.length; ++i) {
            final Transfer transfer = this.transferAgents[i];
            if (transfer != null) {
                final TransferData[] supportedTypes = transfer.getSupportedTypes();
                final TransferData[] array2 = new TransferData[array.length + supportedTypes.length];
                System.arraycopy(array, 0, array2, 0, array.length);
                System.arraycopy(supportedTypes, 0, array2, array.length, supportedTypes.length);
                array = array2;
            }
        }
        final OleEnumFORMATETC oleEnumFORMATETC = new OleEnumFORMATETC();
        oleEnumFORMATETC.AddRef();
        final FORMATETC[] formats = new FORMATETC[array.length];
        for (int j = 0; j < formats.length; ++j) {
            formats[j] = array[j].formatetc;
        }
        oleEnumFORMATETC.setFormats(formats);
        OS.MoveMemory(n2, new int[] { oleEnumFORMATETC.getAddress() }, OS.PTR_SIZEOF);
        return 0;
    }
    
    public Control getControl() {
        return this.control;
    }
    
    private int GetData(final int n, final int n2) {
        if (n == 0 || n2 == 0) {
            return -2147024809;
        }
        if (this.QueryGetData(n) != 0) {
            return -2147221404;
        }
        final TransferData dataType = new TransferData();
        COM.MoveMemory(dataType.formatetc = new FORMATETC(), n, FORMATETC.sizeof);
        dataType.type = dataType.formatetc.cfFormat;
        dataType.stgmedium = new STGMEDIUM();
        dataType.result = -2147467259;
        final DNDEvent dndEvent = new DNDEvent();
        dndEvent.widget = this;
        dndEvent.time = OS.GetMessageTime();
        dndEvent.dataType = dataType;
        this.notifyListeners(2001, dndEvent);
        if (!dndEvent.doit) {
            return -2147467259;
        }
        Transfer transfer = null;
        for (int i = 0; i < this.transferAgents.length; ++i) {
            final Transfer transfer2 = this.transferAgents[i];
            if (transfer2 != null && transfer2.isSupportedType(dataType)) {
                transfer = transfer2;
                break;
            }
        }
        if (transfer == null) {
            return -2147221404;
        }
        transfer.javaToNative(dndEvent.data, dataType);
        if (dataType.result != 0) {
            return dataType.result;
        }
        COM.MoveMemory(n2, dataType.stgmedium, STGMEDIUM.sizeof);
        return dataType.result;
    }
    
    public DragSourceListener[] getDragListeners() {
        final Listener[] listeners = this.getListeners(2008);
        final int length = listeners.length;
        final DragSourceListener[] array = new DragSourceListener[length];
        int n = 0;
        for (final Listener listener : listeners) {
            if (listener instanceof DNDListener) {
                array[n] = (DragSourceListener)((DNDListener)listener).getEventListener();
                ++n;
            }
        }
        if (n == length) {
            return array;
        }
        final DragSourceListener[] array2 = new DragSourceListener[n];
        System.arraycopy(array, 0, array2, 0, n);
        return array2;
    }
    
    public DragSourceEffect getDragSourceEffect() {
        return this.dragEffect;
    }
    
    public Transfer[] getTransfer() {
        return this.transferAgents;
    }
    
    private int GiveFeedback(final int n) {
        return 262402;
    }
    
    private int QueryContinueDrag(final int n, final int n2) {
        if (this.topControl != null && this.topControl.isDisposed()) {
            return 262401;
        }
        if (n != 0) {
            if (this.hwndDrag != 0) {
                OS.ImageList_DragLeave(this.hwndDrag);
            }
            return 262401;
        }
        if ((n2 & 0x13) == 0x0) {
            if (this.hwndDrag != 0) {
                OS.ImageList_DragLeave(this.hwndDrag);
            }
            return 262400;
        }
        if (this.hwndDrag != 0) {
            final POINT point = new POINT();
            OS.GetCursorPos(point);
            final RECT rect = new RECT();
            OS.GetWindowRect(this.hwndDrag, rect);
            OS.ImageList_DragMove(point.x - rect.left, point.y - rect.top);
        }
        return 0;
    }
    
    private void onDispose() {
        if (this.control == null) {
            return;
        }
        this.Release();
        if (this.controlListener != null) {
            this.control.removeListener(12, this.controlListener);
            this.control.removeListener(29, this.controlListener);
        }
        this.controlListener = null;
        this.control.setData("DragSource", null);
        this.control = null;
        this.transferAgents = null;
    }
    
    private int opToOs(final int n) {
        int n2 = 0;
        if ((n & 0x1) != 0x0) {
            n2 |= 0x1;
        }
        if ((n & 0x4) != 0x0) {
            n2 |= 0x4;
        }
        if ((n & 0x2) != 0x0) {
            n2 |= 0x2;
        }
        return n2;
    }
    
    private int osToOp(final int n) {
        int n2 = 0;
        if ((n & 0x1) != 0x0) {
            n2 |= 0x1;
        }
        if ((n & 0x4) != 0x0) {
            n2 |= 0x4;
        }
        if ((n & 0x2) != 0x0) {
            n2 |= 0x2;
        }
        return n2;
    }
    
    private int QueryGetData(final int n) {
        if (this.transferAgents == null) {
            return -2147467259;
        }
        final TransferData transferData = new TransferData();
        COM.MoveMemory(transferData.formatetc = new FORMATETC(), n, FORMATETC.sizeof);
        transferData.type = transferData.formatetc.cfFormat;
        for (int i = 0; i < this.transferAgents.length; ++i) {
            final Transfer transfer = this.transferAgents[i];
            if (transfer != null && transfer.isSupportedType(transferData)) {
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
        if (COM.IsEqualGUID(guid, COM.IIDIUnknown) || COM.IsEqualGUID(guid, COM.IIDIDropSource)) {
            OS.MoveMemory(n2, new int[] { this.iDropSource.getAddress() }, OS.PTR_SIZEOF);
            this.AddRef();
            return 0;
        }
        if (COM.IsEqualGUID(guid, COM.IIDIDataObject)) {
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
            this.disposeCOMInterfaces();
            if (COM.FreeUnusedLibraries) {
                COM.CoFreeUnusedLibraries();
            }
        }
        return this.refCount;
    }
    
    public void removeDragListener(final DragSourceListener dragSourceListener) {
        if (dragSourceListener == null) {
            DND.error(4);
        }
        this.removeListener(2008, dragSourceListener);
        this.removeListener(2001, dragSourceListener);
        this.removeListener(2000, dragSourceListener);
    }
    
    private int SetData(final int n, final int n2, final int n3) {
        if (n == 0 || n2 == 0) {
            return -2147024809;
        }
        final FORMATETC formatetc = new FORMATETC();
        COM.MoveMemory(formatetc, n, FORMATETC.sizeof);
        if (formatetc.cfFormat == DragSource.CFSTR_PERFORMEDDROPEFFECT && formatetc.tymed == 1) {
            final STGMEDIUM stgmedium = new STGMEDIUM();
            COM.MoveMemory(stgmedium, n2, STGMEDIUM.sizeof);
            final int[] array = { 0 };
            OS.MoveMemory(array, stgmedium.unionField, OS.PTR_SIZEOF);
            final int[] array2 = { 0 };
            OS.MoveMemory(array2, array[0], 4);
            this.dataEffect = this.osToOp(array2[0]);
        }
        if (n3 == 1) {
            COM.ReleaseStgMedium(n2);
        }
        return 0;
    }
    
    public void setDragSourceEffect(final DragSourceEffect dragEffect) {
        this.dragEffect = dragEffect;
    }
    
    public void setTransfer(final Transfer[] transferAgents) {
        this.transferAgents = transferAgents;
    }
}
