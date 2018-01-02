// 
// Decompiled by Procyon v0.5.30
// 

package org.eclipse.swt.ole.win32;

import org.eclipse.swt.internal.win32.OS;
import org.eclipse.swt.internal.ole.win32.VARIANT;
import org.eclipse.swt.internal.ole.win32.DISPPARAMS;
import org.eclipse.swt.internal.ole.win32.IConnectionPoint;
import org.eclipse.swt.internal.ole.win32.IConnectionPointContainer;
import org.eclipse.swt.internal.ole.win32.COM;
import org.eclipse.swt.internal.ole.win32.GUID;
import org.eclipse.swt.internal.ole.win32.IUnknown;
import org.eclipse.swt.internal.ole.win32.COMObject;

final class OleEventSink
{
    private OleControlSite widget;
    private COMObject iDispatch;
    private int refCount;
    private IUnknown objIUnknown;
    private int eventCookie;
    private GUID eventGuid;
    private OleEventTable eventTable;
    
    OleEventSink(final OleControlSite widget, final int n, final GUID eventGuid) {
        this.widget = widget;
        this.eventGuid = eventGuid;
        this.objIUnknown = new IUnknown(n);
        this.createCOMInterfaces();
    }
    
    void connect() {
        final int[] array = { 0 };
        if (this.objIUnknown.QueryInterface(COM.IIDIConnectionPointContainer, array) == 0) {
            final IConnectionPointContainer connectionPointContainer = new IConnectionPointContainer(array[0]);
            final int[] array2 = { 0 };
            if (connectionPointContainer.FindConnectionPoint(this.eventGuid, array2) == 0) {
                final IConnectionPoint connectionPoint = new IConnectionPoint(array2[0]);
                final int[] array3 = { 0 };
                if (connectionPoint.Advise(this.iDispatch.getAddress(), array3) == 0) {
                    this.eventCookie = array3[0];
                }
                connectionPoint.Release();
            }
            connectionPointContainer.Release();
        }
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
    
    private void createCOMInterfaces() {
        this.iDispatch = new COMObject(new int[] { 2, 0, 0, 1, 3, 4, 8 }) {
            public int method0(final int[] array) {
                return OleEventSink.this.QueryInterface(array[0], array[1]);
            }
            
            public int method1(final int[] array) {
                return OleEventSink.this.AddRef();
            }
            
            public int method2(final int[] array) {
                return OleEventSink.this.Release();
            }
            
            public int method6(final int[] array) {
                return OleEventSink.this.Invoke(array[0], array[1], array[2], array[3], array[4], array[5], array[6], array[7]);
            }
        };
    }
    
    void disconnect() {
        if (this.eventCookie != 0 && this.objIUnknown != null) {
            final int[] array = { 0 };
            if (this.objIUnknown.QueryInterface(COM.IIDIConnectionPointContainer, array) == 0) {
                final IConnectionPointContainer connectionPointContainer = new IConnectionPointContainer(array[0]);
                if (connectionPointContainer.FindConnectionPoint(this.eventGuid, array) == 0) {
                    final IConnectionPoint connectionPoint = new IConnectionPoint(array[0]);
                    if (connectionPoint.Unadvise(this.eventCookie) == 0) {
                        this.eventCookie = 0;
                    }
                    connectionPoint.Release();
                }
                connectionPointContainer.Release();
            }
        }
    }
    
    private void disposeCOMInterfaces() {
        if (this.iDispatch != null) {
            this.iDispatch.dispose();
        }
        this.iDispatch = null;
    }
    
    private int Invoke(final int n, final int n2, final int n3, final int n4, final int n5, final int n6, final int n7, final int n8) {
        if (this.eventTable == null || !this.eventTable.hooks(n)) {
            return 0;
        }
        Variant[] arguments = null;
        if (n5 != 0) {
            final DISPPARAMS dispparams = new DISPPARAMS();
            COM.MoveMemory(dispparams, n5, DISPPARAMS.sizeof);
            arguments = new Variant[dispparams.cArgs];
            final int sizeof = VARIANT.sizeof;
            int n9 = (dispparams.cArgs - 1) * sizeof;
            for (int i = 0; i < dispparams.cArgs; ++i) {
                (arguments[i] = new Variant()).setData(dispparams.rgvarg + n9);
                n9 -= sizeof;
            }
        }
        final OleEvent oleEvent = new OleEvent();
        oleEvent.arguments = arguments;
        this.notifyListener(n, oleEvent);
        return 0;
    }
    
    private void notifyListener(final int type, final OleEvent oleEvent) {
        if (oleEvent == null) {
            OLE.error(4);
        }
        if (this.eventTable == null) {
            return;
        }
        oleEvent.type = type;
        oleEvent.widget = this.widget;
        this.eventTable.sendEvent(oleEvent);
    }
    
    private int QueryInterface(final int n, final int n2) {
        if (n == 0 || n2 == 0) {
            return -2147024809;
        }
        final GUID guid = new GUID();
        COM.MoveMemory(guid, n, GUID.sizeof);
        if (COM.IsEqualGUID(guid, COM.IIDIUnknown) || COM.IsEqualGUID(guid, COM.IIDIDispatch) || COM.IsEqualGUID(guid, this.eventGuid)) {
            OS.MoveMemory(n2, new int[] { this.iDispatch.getAddress() }, OS.PTR_SIZEOF);
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
    
    boolean hasListeners() {
        return this.eventTable.hasEntries();
    }
}
