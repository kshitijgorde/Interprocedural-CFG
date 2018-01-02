// 
// Decompiled by Procyon v0.5.30
// 

package org.eclipse.swt.dnd;

import org.eclipse.swt.internal.ole.win32.FORMATETC;
import org.eclipse.swt.internal.ole.win32.IEnumFORMATETC;
import org.eclipse.swt.internal.win32.RECT;
import org.eclipse.swt.internal.ole.win32.GUID;
import org.eclipse.swt.internal.win32.OS;
import org.eclipse.swt.internal.win32.POINT;
import org.eclipse.swt.internal.C;
import org.eclipse.swt.internal.SWTEventListener;
import org.eclipse.swt.widgets.Tree;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.internal.ole.win32.COM;
import org.eclipse.swt.internal.ole.win32.COMObject;
import org.eclipse.swt.internal.ole.win32.IDataObject;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Widget;

public class DropTarget extends Widget
{
    Control control;
    Listener controlListener;
    Transfer[] transferAgents;
    DropTargetEffect dropEffect;
    TransferData selectedDataType;
    int selectedOperation;
    int keyOperation;
    IDataObject iDataObject;
    COMObject iDropTarget;
    int refCount;
    static final String DEFAULT_DROP_TARGET_EFFECT = "DEFAULT_DROP_TARGET_EFFECT";
    static /* synthetic */ Class class$0;
    
    public DropTarget(final Control control, final int n) {
        super(control, checkStyle(n));
        this.transferAgents = new Transfer[0];
        this.keyOperation = -1;
        this.control = control;
        if (control.getData("DropTarget") != null) {
            DND.error(2001);
        }
        control.setData("DropTarget", this);
        this.createCOMInterfaces();
        this.AddRef();
        if (COM.CoLockObjectExternal(this.iDropTarget.getAddress(), true, true) != 0) {
            DND.error(2001);
        }
        if (COM.RegisterDragDrop(control.handle, this.iDropTarget.getAddress()) != 0) {
            DND.error(2001);
        }
        control.addListener(12, this.controlListener = new Listener() {
            public void handleEvent(final Event event) {
                if (!DropTarget.this.isDisposed()) {
                    DropTarget.this.dispose();
                }
            }
        });
        this.addListener(12, new Listener() {
            public void handleEvent(final Event event) {
                DropTarget.this.onDispose();
            }
        });
        final Object data = control.getData("DEFAULT_DROP_TARGET_EFFECT");
        if (data instanceof DropTargetEffect) {
            this.dropEffect = (DropTargetEffect)data;
        }
        else if (control instanceof Table) {
            this.dropEffect = new TableDropTargetEffect((Table)control);
        }
        else if (control instanceof Tree) {
            this.dropEffect = new TreeDropTargetEffect((Tree)control);
        }
    }
    
    static int checkStyle(final int n) {
        if (n == 0) {
            return 2;
        }
        return n;
    }
    
    public void addDropListener(final DropTargetListener dropTargetListener) {
        if (dropTargetListener == null) {
            DND.error(4);
        }
        final DNDListener dndListener = new DNDListener(dropTargetListener);
        (dndListener.dndWidget = this).addListener(2002, dndListener);
        this.addListener(2003, dndListener);
        this.addListener(2004, dndListener);
        this.addListener(2005, dndListener);
        this.addListener(2006, dndListener);
        this.addListener(2007, dndListener);
    }
    
    int AddRef() {
        return ++this.refCount;
    }
    
    protected void checkSubclass() {
        final String name = this.getClass().getName();
        Class class$0;
        if ((class$0 = DropTarget.class$0) == null) {
            try {
                class$0 = (DropTarget.class$0 = Class.forName("org.eclipse.swt.dnd.DropTarget"));
            }
            catch (ClassNotFoundException ex) {
                throw new NoClassDefFoundError(ex.getMessage());
            }
        }
        if (!class$0.getName().equals(name)) {
            DND.error(43);
        }
    }
    
    void createCOMInterfaces() {
        final boolean b = C.PTR_SIZEOF == 4;
        this.iDropTarget = new COMObject(new int[] { 2, 0, 0, b ? 5 : 4, b ? 4 : 3, 0, b ? 5 : 4 }) {
            public int method0(final int[] array) {
                return DropTarget.this.QueryInterface(array[0], array[1]);
            }
            
            public int method1(final int[] array) {
                return DropTarget.this.AddRef();
            }
            
            public int method2(final int[] array) {
                return DropTarget.this.Release();
            }
            
            public int method3(final int[] array) {
                if (array.length == 5) {
                    return DropTarget.this.DragEnter(array[0], array[1], array[2], array[3], array[4]);
                }
                return DropTarget.this.DragEnter_64(array[0], array[1], array[2], array[3]);
            }
            
            public int method4(final int[] array) {
                if (array.length == 4) {
                    return DropTarget.this.DragOver(array[0], array[1], array[2], array[3]);
                }
                return DropTarget.this.DragOver_64(array[0], array[1], array[2]);
            }
            
            public int method5(final int[] array) {
                return DropTarget.this.DragLeave();
            }
            
            public int method6(final int[] array) {
                if (array.length == 5) {
                    return DropTarget.this.Drop(array[0], array[1], array[2], array[3], array[4]);
                }
                return DropTarget.this.Drop_64(array[0], array[1], array[2], array[3]);
            }
        };
    }
    
    void disposeCOMInterfaces() {
        if (this.iDropTarget != null) {
            this.iDropTarget.dispose();
        }
        this.iDropTarget = null;
    }
    
    int DragEnter_64(final int n, final int n2, final long n3, final int n4) {
        final POINT point = new POINT();
        OS.MoveMemory(point, new long[] { n3 }, 8);
        return this.DragEnter(n, n2, point.x, point.y, n4);
    }
    
    int DragEnter(final int n, final int n2, final int n3, final int n4, final int n5) {
        this.selectedDataType = null;
        this.selectedOperation = 0;
        if (this.iDataObject != null) {
            this.iDataObject.Release();
        }
        this.iDataObject = null;
        final DNDEvent dndEvent = new DNDEvent();
        if (!this.setEventData(dndEvent, n, n2, n3, n4, n5)) {
            OS.MoveMemory(n5, new int[1], 4);
            return 1;
        }
        (this.iDataObject = new IDataObject(n)).AddRef();
        final int operations = dndEvent.operations;
        final TransferData[] array = new TransferData[dndEvent.dataTypes.length];
        System.arraycopy(dndEvent.dataTypes, 0, array, 0, array.length);
        this.notifyListeners(2002, dndEvent);
        this.refresh();
        if (dndEvent.detail == 16) {
            dndEvent.detail = (((operations & 0x2) != 0x0) ? 2 : 0);
        }
        this.selectedDataType = null;
        for (int i = 0; i < array.length; ++i) {
            if (TransferData.sameType(array[i], dndEvent.dataType)) {
                this.selectedDataType = array[i];
                break;
            }
        }
        this.selectedOperation = 0;
        if (this.selectedDataType != null && (operations & dndEvent.detail) != 0x0) {
            this.selectedOperation = dndEvent.detail;
        }
        OS.MoveMemory(n5, new int[] { this.opToOs(this.selectedOperation) }, 4);
        return 0;
    }
    
    int DragLeave() {
        this.keyOperation = -1;
        if (this.iDataObject == null) {
            return 1;
        }
        final DNDEvent dndEvent = new DNDEvent();
        dndEvent.widget = this;
        dndEvent.time = OS.GetMessageTime();
        dndEvent.detail = 0;
        this.notifyListeners(2003, dndEvent);
        this.refresh();
        this.iDataObject.Release();
        this.iDataObject = null;
        return 0;
    }
    
    int DragOver_64(final int n, final long n2, final int n3) {
        final POINT point = new POINT();
        OS.MoveMemory(point, new long[] { n2 }, 8);
        return this.DragOver(n, point.x, point.y, n3);
    }
    
    int DragOver(final int n, final int n2, final int n3, final int n4) {
        if (this.iDataObject == null) {
            return 1;
        }
        final int keyOperation = this.keyOperation;
        final DNDEvent dndEvent = new DNDEvent();
        if (!this.setEventData(dndEvent, this.iDataObject.getAddress(), n, n2, n3, n4)) {
            this.keyOperation = -1;
            OS.MoveMemory(n4, new int[1], 4);
            return 1;
        }
        final int operations = dndEvent.operations;
        final TransferData[] array = new TransferData[dndEvent.dataTypes.length];
        System.arraycopy(dndEvent.dataTypes, 0, array, 0, array.length);
        if (this.keyOperation == keyOperation) {
            dndEvent.type = 2004;
            dndEvent.dataType = this.selectedDataType;
            dndEvent.detail = this.selectedOperation;
        }
        else {
            dndEvent.type = 2005;
            dndEvent.dataType = this.selectedDataType;
        }
        this.notifyListeners(dndEvent.type, dndEvent);
        this.refresh();
        if (dndEvent.detail == 16) {
            dndEvent.detail = (((operations & 0x2) != 0x0) ? 2 : 0);
        }
        this.selectedDataType = null;
        for (int i = 0; i < array.length; ++i) {
            if (TransferData.sameType(array[i], dndEvent.dataType)) {
                this.selectedDataType = array[i];
                break;
            }
        }
        this.selectedOperation = 0;
        if (this.selectedDataType != null && (operations & dndEvent.detail) == dndEvent.detail) {
            this.selectedOperation = dndEvent.detail;
        }
        OS.MoveMemory(n4, new int[] { this.opToOs(this.selectedOperation) }, 4);
        return 0;
    }
    
    int Drop_64(final int n, final int n2, final long n3, final int n4) {
        final POINT point = new POINT();
        OS.MoveMemory(point, new long[] { n3 }, 8);
        return this.Drop(n, n2, point.x, point.y, n4);
    }
    
    int Drop(final int n, final int n2, final int n3, final int n4, final int n5) {
        final DNDEvent dndEvent = new DNDEvent();
        dndEvent.widget = this;
        dndEvent.time = OS.GetMessageTime();
        if (this.dropEffect != null) {
            dndEvent.item = this.dropEffect.getItem(n3, n4);
        }
        dndEvent.detail = 0;
        this.notifyListeners(2003, dndEvent);
        this.refresh();
        final DNDEvent dndEvent2 = new DNDEvent();
        if (!this.setEventData(dndEvent2, n, n2, n3, n4, n5)) {
            this.keyOperation = -1;
            OS.MoveMemory(n5, new int[1], 4);
            return 1;
        }
        this.keyOperation = -1;
        final int operations = dndEvent2.operations;
        final TransferData[] array = new TransferData[dndEvent2.dataTypes.length];
        System.arraycopy(dndEvent2.dataTypes, 0, array, 0, array.length);
        dndEvent2.dataType = this.selectedDataType;
        dndEvent2.detail = this.selectedOperation;
        this.notifyListeners(2007, dndEvent2);
        this.refresh();
        this.selectedDataType = null;
        for (int i = 0; i < array.length; ++i) {
            if (TransferData.sameType(array[i], dndEvent2.dataType)) {
                this.selectedDataType = array[i];
                break;
            }
        }
        this.selectedOperation = 0;
        if (this.selectedDataType != null && (operations & dndEvent2.detail) == dndEvent2.detail) {
            this.selectedOperation = dndEvent2.detail;
        }
        if (this.selectedOperation == 0) {
            OS.MoveMemory(n5, new int[1], 4);
            return 0;
        }
        Object nativeToJava = null;
        for (int j = 0; j < this.transferAgents.length; ++j) {
            final Transfer transfer = this.transferAgents[j];
            if (transfer != null && transfer.isSupportedType(this.selectedDataType)) {
                nativeToJava = transfer.nativeToJava(this.selectedDataType);
                break;
            }
        }
        if (nativeToJava == null) {
            this.selectedOperation = 0;
        }
        dndEvent2.detail = this.selectedOperation;
        dndEvent2.dataType = this.selectedDataType;
        dndEvent2.data = nativeToJava;
        OS.ImageList_DragShowNolock(false);
        try {
            this.notifyListeners(2006, dndEvent2);
        }
        finally {
            OS.ImageList_DragShowNolock(true);
        }
        OS.ImageList_DragShowNolock(true);
        this.refresh();
        this.selectedOperation = 0;
        if ((operations & dndEvent2.detail) == dndEvent2.detail) {
            this.selectedOperation = dndEvent2.detail;
        }
        OS.MoveMemory(n5, new int[] { this.opToOs(this.selectedOperation) }, 4);
        return 0;
    }
    
    public Control getControl() {
        return this.control;
    }
    
    public DropTargetListener[] getDropListeners() {
        final Listener[] listeners = this.getListeners(2002);
        final int length = listeners.length;
        final DropTargetListener[] array = new DropTargetListener[length];
        int n = 0;
        for (final Listener listener : listeners) {
            if (listener instanceof DNDListener) {
                array[n] = (DropTargetListener)((DNDListener)listener).getEventListener();
                ++n;
            }
        }
        if (n == length) {
            return array;
        }
        final DropTargetListener[] array2 = new DropTargetListener[n];
        System.arraycopy(array, 0, array2, 0, n);
        return array2;
    }
    
    public DropTargetEffect getDropTargetEffect() {
        return this.dropEffect;
    }
    
    int getOperationFromKeyState(final int n) {
        final boolean b = (n & 0x8) != 0x0;
        final boolean b2 = (n & 0x4) != 0x0;
        if ((n & 0x20) != 0x0) {
            if (b || b2) {
                return 16;
            }
            return 4;
        }
        else {
            if (b && b2) {
                return 4;
            }
            if (b) {
                return 1;
            }
            if (b2) {
                return 2;
            }
            return 16;
        }
    }
    
    public Transfer[] getTransfer() {
        return this.transferAgents;
    }
    
    void onDispose() {
        if (this.control == null) {
            return;
        }
        COM.RevokeDragDrop(this.control.handle);
        if (this.controlListener != null) {
            this.control.removeListener(12, this.controlListener);
        }
        this.controlListener = null;
        this.control.setData("DropTarget", null);
        this.transferAgents = null;
        this.control = null;
        COM.CoLockObjectExternal(this.iDropTarget.getAddress(), false, true);
        this.Release();
        if (this.iDataObject != null) {
            this.iDataObject.Release();
        }
        this.iDataObject = null;
        if (COM.FreeUnusedLibraries) {
            COM.CoFreeUnusedLibraries();
        }
    }
    
    int opToOs(final int n) {
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
    
    int osToOp(final int n) {
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
    
    int QueryInterface(final int n, final int n2) {
        if (n == 0 || n2 == 0) {
            return -2147024809;
        }
        final GUID guid = new GUID();
        COM.MoveMemory(guid, n, GUID.sizeof);
        if (COM.IsEqualGUID(guid, COM.IIDIUnknown) || COM.IsEqualGUID(guid, COM.IIDIDropTarget)) {
            OS.MoveMemory(n2, new int[] { this.iDropTarget.getAddress() }, OS.PTR_SIZEOF);
            this.AddRef();
            return 0;
        }
        OS.MoveMemory(n2, new int[1], OS.PTR_SIZEOF);
        return -2147467262;
    }
    
    int Release() {
        --this.refCount;
        if (this.refCount == 0) {
            this.disposeCOMInterfaces();
            if (COM.FreeUnusedLibraries) {
                COM.CoFreeUnusedLibraries();
            }
        }
        return this.refCount;
    }
    
    void refresh() {
        if (this.control == null || this.control.isDisposed()) {
            return;
        }
        final int handle = this.control.handle;
        final RECT rect = new RECT();
        if (OS.GetUpdateRect(handle, rect, false)) {
            OS.ImageList_DragShowNolock(false);
            OS.RedrawWindow(handle, rect, 0, 257);
            OS.ImageList_DragShowNolock(true);
        }
    }
    
    public void removeDropListener(final DropTargetListener dropTargetListener) {
        if (dropTargetListener == null) {
            DND.error(4);
        }
        this.removeListener(2002, dropTargetListener);
        this.removeListener(2003, dropTargetListener);
        this.removeListener(2004, dropTargetListener);
        this.removeListener(2005, dropTargetListener);
        this.removeListener(2006, dropTargetListener);
        this.removeListener(2007, dropTargetListener);
    }
    
    public void setDropTargetEffect(final DropTargetEffect dropEffect) {
        this.dropEffect = dropEffect;
    }
    
    boolean setEventData(final DNDEvent dndEvent, final int piDataObject, final int n, final int x, final int y, final int n2) {
        if (piDataObject == 0 || n2 == 0) {
            return false;
        }
        final int style = this.getStyle();
        final int[] array = { 0 };
        OS.MoveMemory(array, n2, 4);
        array[0] = (this.osToOp(array[0]) & style);
        if (array[0] == 0) {
            return false;
        }
        int operationFromKeyState = this.getOperationFromKeyState(n);
        if ((this.keyOperation = operationFromKeyState) == 16) {
            if ((style & 0x10) == 0x0) {
                operationFromKeyState = (((array[0] & 0x2) != 0x0) ? 2 : 0);
            }
        }
        else if ((operationFromKeyState & array[0]) == 0x0) {
            operationFromKeyState = 0;
        }
        TransferData[] dataTypes = new TransferData[0];
        final IDataObject dataObject = new IDataObject(piDataObject);
        dataObject.AddRef();
        try {
            final int[] array2 = { 0 };
            if (dataObject.EnumFormatEtc(1, array2) != 0) {
                return false;
            }
            final IEnumFORMATETC enumFORMATETC = new IEnumFORMATETC(array2[0]);
            try {
                final int globalAlloc = OS.GlobalAlloc(64, FORMATETC.sizeof);
                try {
                    final int[] array3 = { 0 };
                    enumFORMATETC.Reset();
                    while (enumFORMATETC.Next(1, globalAlloc, array3) == 0) {
                        if (array3[0] != 1) {
                            break;
                        }
                        final TransferData transferData = new TransferData();
                        COM.MoveMemory(transferData.formatetc = new FORMATETC(), globalAlloc, FORMATETC.sizeof);
                        transferData.type = transferData.formatetc.cfFormat;
                        transferData.pIDataObject = piDataObject;
                        for (int i = 0; i < this.transferAgents.length; ++i) {
                            final Transfer transfer = this.transferAgents[i];
                            if (transfer != null && transfer.isSupportedType(transferData)) {
                                final TransferData[] array4 = new TransferData[dataTypes.length + 1];
                                System.arraycopy(dataTypes, 0, array4, 0, dataTypes.length);
                                array4[dataTypes.length] = transferData;
                                dataTypes = array4;
                                break;
                            }
                        }
                    }
                }
                finally {
                    OS.GlobalFree(globalAlloc);
                }
                OS.GlobalFree(globalAlloc);
            }
            finally {
                enumFORMATETC.Release();
            }
            enumFORMATETC.Release();
        }
        finally {
            dataObject.Release();
        }
        dataObject.Release();
        if (dataTypes.length == 0) {
            return false;
        }
        dndEvent.widget = this;
        dndEvent.x = x;
        dndEvent.y = y;
        dndEvent.time = OS.GetMessageTime();
        dndEvent.feedback = 1;
        dndEvent.dataTypes = dataTypes;
        dndEvent.dataType = dataTypes[0];
        if (this.dropEffect != null) {
            dndEvent.item = this.dropEffect.getItem(x, y);
        }
        dndEvent.operations = array[0];
        dndEvent.detail = operationFromKeyState;
        return true;
    }
    
    public void setTransfer(final Transfer[] transferAgents) {
        if (transferAgents == null) {
            DND.error(4);
        }
        this.transferAgents = transferAgents;
    }
}
