// 
// Decompiled by Procyon v0.5.30
// 

package org.eclipse.swt.ole.win32;

import org.eclipse.swt.internal.win32.OS;
import org.eclipse.swt.internal.ole.win32.GUID;
import org.eclipse.swt.internal.ole.win32.IConnectionPoint;
import org.eclipse.swt.internal.ole.win32.IConnectionPointContainer;
import org.eclipse.swt.internal.ole.win32.COM;
import org.eclipse.swt.internal.ole.win32.IUnknown;
import org.eclipse.swt.internal.ole.win32.COMObject;

final class OlePropertyChangeSink
{
    private OleControlSite controlSite;
    private COMObject iUnknown;
    private COMObject iPropertyNotifySink;
    private int refCount;
    private int propertyCookie;
    private OleEventTable eventTable;
    
    OlePropertyChangeSink(final OleControlSite controlSite) {
        this.controlSite = controlSite;
        this.createCOMInterfaces();
    }
    
    void addListener(final int n, final OleListener oleListener) {
        if (oleListener == null) {
            OLE.error(4);
        }
        if (this.eventTable == null) {
            this.eventTable = new OleEventTable();
        }
        this.eventTable.hook(n, oleListener);
    }
    
    int AddRef() {
        return ++this.refCount;
    }
    
    void connect(final IUnknown unknown) {
        final int[] array = { 0 };
        if (unknown.QueryInterface(COM.IIDIConnectionPointContainer, array) == 0) {
            final IConnectionPointContainer connectionPointContainer = new IConnectionPointContainer(array[0]);
            if (connectionPointContainer.FindConnectionPoint(COM.IIDIPropertyNotifySink, array) == 0) {
                final IConnectionPoint connectionPoint = new IConnectionPoint(array[0]);
                final int[] array2 = { 0 };
                if (connectionPoint.Advise(this.iPropertyNotifySink.getAddress(), array2) == 0) {
                    this.propertyCookie = array2[0];
                }
                connectionPoint.Release();
            }
            connectionPointContainer.Release();
        }
    }
    
    private void createCOMInterfaces() {
        this.iUnknown = new COMObject(new int[] { 2, 0, 0 }) {
            public int method0(final int[] array) {
                return OlePropertyChangeSink.this.QueryInterface(array[0], array[1]);
            }
            
            public int method1(final int[] array) {
                return OlePropertyChangeSink.this.AddRef();
            }
            
            public int method2(final int[] array) {
                return OlePropertyChangeSink.this.Release();
            }
        };
        this.iPropertyNotifySink = new COMObject(new int[] { 2, 0, 0, 1, 1 }) {
            public int method0(final int[] array) {
                return OlePropertyChangeSink.this.QueryInterface(array[0], array[1]);
            }
            
            public int method1(final int[] array) {
                return OlePropertyChangeSink.this.AddRef();
            }
            
            public int method2(final int[] array) {
                return OlePropertyChangeSink.this.Release();
            }
            
            public int method3(final int[] array) {
                return OlePropertyChangeSink.this.OnChanged(array[0]);
            }
            
            public int method4(final int[] array) {
                return OlePropertyChangeSink.this.OnRequestEdit(array[0]);
            }
        };
    }
    
    void disconnect(final IUnknown unknown) {
        if (this.propertyCookie != 0 && unknown != null) {
            final int[] array = { 0 };
            if (unknown.QueryInterface(COM.IIDIConnectionPointContainer, array) == 0) {
                final IConnectionPointContainer connectionPointContainer = new IConnectionPointContainer(array[0]);
                if (connectionPointContainer.FindConnectionPoint(COM.IIDIPropertyNotifySink, array) == 0) {
                    final IConnectionPoint connectionPoint = new IConnectionPoint(array[0]);
                    if (connectionPoint.Unadvise(this.propertyCookie) == 0) {
                        this.propertyCookie = 0;
                    }
                    connectionPoint.Release();
                }
                connectionPointContainer.Release();
            }
        }
    }
    
    private void disposeCOMInterfaces() {
        if (this.iUnknown != null) {
            this.iUnknown.dispose();
        }
        this.iUnknown = null;
        if (this.iPropertyNotifySink != null) {
            this.iPropertyNotifySink.dispose();
        }
        this.iPropertyNotifySink = null;
    }
    
    private void notifyListener(final int type, final OleEvent oleEvent) {
        if (oleEvent == null) {
            OLE.error(4);
        }
        if (this.eventTable == null) {
            return;
        }
        oleEvent.type = type;
        oleEvent.widget = this.controlSite;
        this.eventTable.sendEvent(oleEvent);
    }
    
    private int OnChanged(final int n) {
        if (this.eventTable == null || !this.eventTable.hooks(n)) {
            return 0;
        }
        final OleEvent oleEvent = new OleEvent();
        oleEvent.detail = 1;
        this.notifyListener(n, oleEvent);
        return 0;
    }
    
    private int OnRequestEdit(final int n) {
        if (this.eventTable == null || !this.eventTable.hooks(n)) {
            return 0;
        }
        final OleEvent oleEvent = new OleEvent();
        oleEvent.doit = true;
        oleEvent.detail = 0;
        this.notifyListener(n, oleEvent);
        return oleEvent.doit ? 0 : 1;
    }
    
    private int QueryInterface(final int n, final int n2) {
        if (n == 0 || n2 == 0) {
            return -2147024809;
        }
        final GUID guid = new GUID();
        COM.MoveMemory(guid, n, GUID.sizeof);
        if (COM.IsEqualGUID(guid, COM.IIDIUnknown)) {
            OS.MoveMemory(n2, new int[] { this.iUnknown.getAddress() }, OS.PTR_SIZEOF);
            this.AddRef();
            return 0;
        }
        if (COM.IsEqualGUID(guid, COM.IIDIPropertyNotifySink)) {
            OS.MoveMemory(n2, new int[] { this.iPropertyNotifySink.getAddress() }, OS.PTR_SIZEOF);
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
        }
        return this.refCount;
    }
    
    void removeListener(final int n, final OleListener oleListener) {
        if (oleListener == null) {
            OLE.error(4);
        }
        if (this.eventTable == null) {
            return;
        }
        this.eventTable.unhook(n, oleListener);
    }
}
