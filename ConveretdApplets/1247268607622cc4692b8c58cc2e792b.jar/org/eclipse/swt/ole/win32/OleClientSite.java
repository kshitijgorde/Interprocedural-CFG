// 
// Decompiled by Procyon v0.5.30
// 

package org.eclipse.swt.ole.win32;

import org.eclipse.swt.internal.Compatibility;
import org.eclipse.swt.internal.ole.win32.CAUUID;
import org.eclipse.swt.internal.ole.win32.ISpecifyPropertyPages;
import java.io.FileOutputStream;
import org.eclipse.swt.internal.ole.win32.OLECMDTEXT;
import org.eclipse.swt.internal.ole.win32.OLECMD;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.internal.ole.win32.IPersistFile;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.internal.ole.win32.OLEINPLACEFRAMEINFO;
import org.eclipse.swt.internal.ole.win32.IOleDocument;
import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.swt.internal.ole.win32.DVTARGETDEVICE;
import org.eclipse.swt.internal.win32.SIZE;
import org.eclipse.swt.internal.ole.win32.IDispatch;
import org.eclipse.swt.internal.ole.win32.VARIANT;
import org.eclipse.swt.internal.win32.MSG;
import org.eclipse.swt.internal.C;
import org.eclipse.swt.internal.ole.win32.IMoniker;
import org.eclipse.swt.internal.ole.win32.IOleLink;
import org.eclipse.swt.internal.ole.win32.IPersist;
import org.eclipse.swt.internal.ole.win32.IPersistStorage;
import java.io.IOException;
import org.eclipse.swt.internal.win32.OS;
import java.io.FileInputStream;
import org.eclipse.swt.internal.ole.win32.IStream;
import org.eclipse.swt.internal.ole.win32.FORMATETC;
import org.eclipse.swt.SWTException;
import org.eclipse.swt.internal.ole.win32.COM;
import java.io.File;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.internal.win32.RECT;
import org.eclipse.swt.internal.ole.win32.IStorage;
import org.eclipse.swt.internal.ole.win32.IOleDocumentView;
import org.eclipse.swt.internal.ole.win32.IOleCommandTarget;
import org.eclipse.swt.internal.ole.win32.IOleInPlaceObject;
import org.eclipse.swt.internal.ole.win32.IViewObject2;
import org.eclipse.swt.internal.ole.win32.IOleObject;
import org.eclipse.swt.internal.ole.win32.IUnknown;
import org.eclipse.swt.internal.ole.win32.GUID;
import org.eclipse.swt.internal.ole.win32.COMObject;
import org.eclipse.swt.widgets.Composite;

public class OleClientSite extends Composite
{
    private COMObject iUnknown;
    COMObject iOleClientSite;
    private COMObject iAdviseSink;
    private COMObject iOleInPlaceSite;
    private COMObject iOleDocumentSite;
    protected GUID appClsid;
    private GUID objClsid;
    private int refCount;
    protected OleFrame frame;
    protected IUnknown objIUnknown;
    protected IOleObject objIOleObject;
    protected IViewObject2 objIViewObject2;
    protected IOleInPlaceObject objIOleInPlaceObject;
    protected IOleCommandTarget objIOleCommandTarget;
    protected IOleDocumentView objDocumentView;
    protected IStorage tempStorage;
    private int aspect;
    private int type;
    private boolean isStatic;
    boolean isActivated;
    private RECT borderWidths;
    private RECT indent;
    private boolean inUpdate;
    private boolean inInit;
    private boolean inDispose;
    private static final String WORDPROGID = "Word.Document";
    private Listener listener;
    static final int STATE_NONE = 0;
    static final int STATE_RUNNING = 1;
    static final int STATE_INPLACEACTIVE = 2;
    static final int STATE_UIACTIVE = 3;
    static final int STATE_ACTIVE = 4;
    int state;
    
    protected OleClientSite(Composite parent, final int n) {
        super(parent, n);
        this.borderWidths = new RECT();
        this.indent = new RECT();
        this.inUpdate = false;
        this.inInit = true;
        this.inDispose = false;
        this.state = 0;
        this.createCOMInterfaces();
        while (parent != null) {
            if (parent instanceof OleFrame) {
                this.frame = (OleFrame)parent;
                break;
            }
            parent = parent.getParent();
        }
        if (this.frame == null) {
            OLE.error(5);
        }
        this.frame.AddRef();
        this.aspect = 1;
        this.type = 1;
        this.isStatic = false;
        this.listener = new Listener() {
            public void handleEvent(final Event event) {
                switch (event.type) {
                    case 10:
                    case 11: {
                        OleClientSite.this.onResize(event);
                        break;
                    }
                    case 12: {
                        OleClientSite.this.onDispose(event);
                        break;
                    }
                    case 15: {
                        OleClientSite.this.onFocusIn(event);
                        break;
                    }
                    case 16: {
                        OleClientSite.this.onFocusOut(event);
                        break;
                    }
                    case 9: {
                        OleClientSite.this.onPaint(event);
                        break;
                    }
                    case 31: {
                        OleClientSite.this.onTraverse(event);
                        break;
                    }
                    case 1: {
                        break;
                    }
                    case 26: {
                        OleClientSite.this.isActivated = true;
                        break;
                    }
                    case 27: {
                        OleClientSite.this.isActivated = false;
                        break;
                    }
                    default: {
                        OLE.error(20);
                        break;
                    }
                }
            }
        };
        this.frame.addListener(11, this.listener);
        this.frame.addListener(10, this.listener);
        this.addListener(12, this.listener);
        this.addListener(15, this.listener);
        this.addListener(16, this.listener);
        this.addListener(9, this.listener);
        this.addListener(31, this.listener);
        this.addListener(1, this.listener);
        this.addListener(26, this.listener);
        this.addListener(27, this.listener);
    }
    
    public OleClientSite(final Composite composite, final int n, final File file) {
        this(composite, n);
        try {
            if (file == null || file.isDirectory() || !file.exists()) {
                OLE.error(5);
            }
            final GUID appClsid = new GUID();
            final char[] charArray = (String.valueOf(file.getAbsolutePath()) + "\u0000").toCharArray();
            final int getClassFile = COM.GetClassFile(charArray, appClsid);
            if (getClassFile != 0) {
                OLE.error(1004, getClassFile);
            }
            if (this.getProgID(appClsid) == null) {
                OLE.error(1004, getClassFile);
            }
            this.OleCreate(this.appClsid = appClsid, appClsid, charArray, file);
        }
        catch (SWTException ex) {
            this.dispose();
            this.disposeCOMInterfaces();
            throw ex;
        }
    }
    
    public OleClientSite(final Composite composite, final int n, final String s) {
        this(composite, n);
        try {
            this.appClsid = this.getClassID(s);
            if (this.appClsid == null) {
                OLE.error(1004);
            }
            this.tempStorage = this.createTempStorage();
            final int[] array = { 0 };
            final int oleCreate = COM.OleCreate(this.appClsid, COM.IIDIUnknown, 1, null, this.isICAClient() ? 0 : this.iOleClientSite.getAddress(), this.tempStorage.getAddress(), array);
            if (oleCreate != 0) {
                OLE.error(1001, oleCreate);
            }
            this.objIUnknown = new IUnknown(array[0]);
            this.addObjectReferences();
            if (COM.OleRun(this.objIUnknown.getAddress()) == 0) {
                this.state = 1;
            }
        }
        catch (SWTException ex) {
            this.dispose();
            this.disposeCOMInterfaces();
            throw ex;
        }
    }
    
    public OleClientSite(final Composite composite, final int n, final String s, final File file) {
        this(composite, n);
        try {
            if (file == null || file.isDirectory() || !file.exists()) {
                OLE.error(5);
            }
            this.appClsid = this.getClassID(s);
            if (this.appClsid == null) {
                OLE.error(1004);
            }
            final char[] charArray = (String.valueOf(file.getAbsolutePath()) + "\u0000").toCharArray();
            final GUID guid = new GUID();
            COM.GetClassFile(charArray, guid);
            this.OleCreate(this.appClsid, guid, charArray, file);
        }
        catch (SWTException ex) {
            this.dispose();
            this.disposeCOMInterfaces();
            throw ex;
        }
    }
    
    void OleCreate(final GUID guid, final GUID guid2, final char[] array, final File file) {
        final boolean office2007 = this.isOffice2007(true);
        if (!office2007 && COM.IsEqualGUID(guid, guid2)) {
            this.tempStorage = this.createTempStorage();
            final int[] array2 = { 0 };
            final int oleCreateFromFile = COM.OleCreateFromFile(guid, array, COM.IIDIUnknown, 1, null, this.iOleClientSite.getAddress(), this.tempStorage.getAddress(), array2);
            if (oleCreateFromFile != 0) {
                OLE.error(1001, oleCreateFromFile);
            }
            this.objIUnknown = new IUnknown(array2[0]);
        }
        else {
            IStorage storage;
            if (COM.StgIsStorageFile(array) == 0) {
                final int[] array3 = { 0 };
                final int stgOpenStorage = COM.StgOpenStorage(array, 0, 65552, 0, 0, array3);
                if (stgOpenStorage != 0) {
                    OLE.error(1002, stgOpenStorage);
                }
                storage = new IStorage(array3[0]);
            }
            else {
                final int[] array4 = { 0 };
                final int n = 4114;
                final int stgCreateDocfile = COM.StgCreateDocfile(null, n | 0x4000000, 0, array4);
                if (stgCreateDocfile != 0) {
                    OLE.error(1002, stgCreateDocfile);
                }
                storage = new IStorage(array4[0]);
                String s = "CONTENTS";
                final GUID classID = this.getClassID("Word.Document");
                if (classID != null && COM.IsEqualGUID(guid, classID)) {
                    s = "WordDocument";
                }
                if (office2007) {
                    s = "Package";
                }
                final int[] array5 = { 0 };
                final int createStream = storage.CreateStream(s, n, 0, 0, array5);
                if (createStream != 0) {
                    storage.Release();
                    OLE.error(1002, createStream);
                }
                final IStream stream = new IStream(array5[0]);
                try {
                    final FileInputStream fileInputStream = new FileInputStream(file);
                    final byte[] array6 = new byte[4096];
                    int read;
                    while ((read = fileInputStream.read(array6)) > 0) {
                        final int coTaskMemAlloc = OS.CoTaskMemAlloc(read);
                        OS.MoveMemory(coTaskMemAlloc, array6, read);
                        final int write = stream.Write(coTaskMemAlloc, read, null);
                        OS.CoTaskMemFree(coTaskMemAlloc);
                        if (write != 0) {
                            fileInputStream.close();
                            stream.Release();
                            storage.Release();
                            OLE.error(1002, write);
                        }
                    }
                    fileInputStream.close();
                    stream.Commit(0);
                    stream.Release();
                }
                catch (IOException ex) {
                    stream.Release();
                    storage.Release();
                    OLE.error(1002);
                }
            }
            this.tempStorage = this.createTempStorage();
            final int copyTo = storage.CopyTo(0, null, null, this.tempStorage.getAddress());
            storage.Release();
            if (copyTo != 0) {
                OLE.error(1002, copyTo);
            }
            final int[] array7 = { 0 };
            final int coCreateInstance = COM.CoCreateInstance(guid, 0, 3, COM.IIDIUnknown, array7);
            if (coCreateInstance != 0) {
                OLE.error(1001, coCreateInstance);
            }
            this.objIUnknown = new IUnknown(array7[0]);
            final int[] array8 = { 0 };
            final int queryInterface = this.objIUnknown.QueryInterface(COM.IIDIPersistStorage, array8);
            if (queryInterface != 0) {
                OLE.error(1001, queryInterface);
            }
            final IPersistStorage persistStorage = new IPersistStorage(array8[0]);
            final int load = persistStorage.Load(this.tempStorage.getAddress());
            persistStorage.Release();
            if (load != 0) {
                OLE.error(1001, load);
            }
        }
        this.addObjectReferences();
        if (COM.OleRun(this.objIUnknown.getAddress()) == 0) {
            this.state = 1;
        }
    }
    
    protected void addObjectReferences() {
        final int[] array = { 0 };
        if (this.objIUnknown.QueryInterface(COM.IIDIPersist, array) == 0) {
            final IPersist persist = new IPersist(array[0]);
            final GUID objClsid = new GUID();
            if (persist.GetClassID(objClsid) == 0) {
                this.objClsid = objClsid;
            }
            persist.Release();
        }
        final int[] array2 = { 0 };
        final int queryInterface = this.objIUnknown.QueryInterface(COM.IIDIViewObject2, array2);
        if (queryInterface != 0) {
            OLE.error(1003, queryInterface);
        }
        (this.objIViewObject2 = new IViewObject2(array2[0])).SetAdvise(this.aspect, 0, this.iAdviseSink.getAddress());
        final int[] array3 = { 0 };
        final int queryInterface2 = this.objIUnknown.QueryInterface(COM.IIDIOleObject, array3);
        if (queryInterface2 != 0) {
            OLE.error(1003, queryInterface2);
        }
        this.objIOleObject = new IOleObject(array3[0]);
        final int[] array4 = { 0 };
        this.objIOleObject.GetClientSite(array4);
        if (array4[0] == 0) {
            this.objIOleObject.SetClientSite(this.iOleClientSite.getAddress());
        }
        else {
            this.Release();
        }
        this.objIOleObject.Advise(this.iAdviseSink.getAddress(), new int[1]);
        this.objIOleObject.SetHostNames("main", "main");
        COM.OleSetContainedObject(this.objIUnknown.getAddress(), true);
        final int[] array5 = { 0 };
        if (this.objIUnknown.QueryInterface(COM.IIDIOleLink, array5) == 0) {
            final IOleLink oleLink = new IOleLink(array5[0]);
            final int[] array6 = { 0 };
            if (oleLink.GetSourceMoniker(array6) == 0) {
                new IMoniker(array6[0]).Release();
                this.type = 0;
                oleLink.BindIfRunning();
            }
            else {
                this.isStatic = true;
            }
            oleLink.Release();
        }
    }
    
    protected int AddRef() {
        return ++this.refCount;
    }
    
    private int CanInPlaceActivate() {
        if (this.aspect == 1 && this.type == 1) {
            return 0;
        }
        return 1;
    }
    
    private int ContextSensitiveHelp(final int n) {
        return 0;
    }
    
    protected void createCOMInterfaces() {
        this.iUnknown = new COMObject(new int[] { 2, 0, 0 }) {
            public int method0(final int[] array) {
                return OleClientSite.this.QueryInterface(array[0], array[1]);
            }
            
            public int method1(final int[] array) {
                return OleClientSite.this.AddRef();
            }
            
            public int method2(final int[] array) {
                return OleClientSite.this.Release();
            }
        };
        this.iOleClientSite = new COMObject(new int[] { 2, 0, 0, 0, 3, 1, 0, 1, 0 }) {
            public int method0(final int[] array) {
                return OleClientSite.this.QueryInterface(array[0], array[1]);
            }
            
            public int method1(final int[] array) {
                return OleClientSite.this.AddRef();
            }
            
            public int method2(final int[] array) {
                return OleClientSite.this.Release();
            }
            
            public int method3(final int[] array) {
                return OleClientSite.this.SaveObject();
            }
            
            public int method5(final int[] array) {
                return OleClientSite.this.GetContainer(array[0]);
            }
            
            public int method6(final int[] array) {
                return OleClientSite.this.ShowObject();
            }
            
            public int method7(final int[] array) {
                return OleClientSite.this.OnShowWindow(array[0]);
            }
        };
        this.iAdviseSink = new COMObject(new int[] { 2, 0, 0, 2, 2, 1, 0, 0 }) {
            public int method0(final int[] array) {
                return OleClientSite.this.QueryInterface(array[0], array[1]);
            }
            
            public int method1(final int[] array) {
                return OleClientSite.this.AddRef();
            }
            
            public int method2(final int[] array) {
                return OleClientSite.this.Release();
            }
            
            public int method3(final int[] array) {
                return OleClientSite.this.OnDataChange(array[0], array[1]);
            }
            
            public int method4(final int[] array) {
                return OleClientSite.this.OnViewChange(array[0], array[1]);
            }
            
            public int method6(final int[] array) {
                OleClientSite.this.OnSave();
                return 0;
            }
            
            public int method7(final int[] array) {
                return OleClientSite.this.OnClose();
            }
        };
        this.iOleInPlaceSite = new COMObject(new int[] { 2, 0, 0, 1, 1, 0, 0, 0, 5, (C.PTR_SIZEOF == 4) ? 2 : 1, 1, 0, 0, 0, 1 }) {
            public int method0(final int[] array) {
                return OleClientSite.this.QueryInterface(array[0], array[1]);
            }
            
            public int method1(final int[] array) {
                return OleClientSite.this.AddRef();
            }
            
            public int method2(final int[] array) {
                return OleClientSite.this.Release();
            }
            
            public int method3(final int[] array) {
                return OleClientSite.this.GetWindow(array[0]);
            }
            
            public int method4(final int[] array) {
                return OleClientSite.this.ContextSensitiveHelp(array[0]);
            }
            
            public int method5(final int[] array) {
                return OleClientSite.this.CanInPlaceActivate();
            }
            
            public int method6(final int[] array) {
                return OleClientSite.this.OnInPlaceActivate();
            }
            
            public int method7(final int[] array) {
                return OleClientSite.this.OnUIActivate();
            }
            
            public int method8(final int[] array) {
                return OleClientSite.this.GetWindowContext(array[0], array[1], array[2], array[3], array[4]);
            }
            
            public int method9(final int[] array) {
                if (array.length == 2) {
                    return OleClientSite.this.Scroll(array[0], array[1]);
                }
                return OleClientSite.this.Scroll_64(array[0]);
            }
            
            public int method10(final int[] array) {
                return OleClientSite.this.OnUIDeactivate(array[0]);
            }
            
            public int method11(final int[] array) {
                return OleClientSite.this.OnInPlaceDeactivate();
            }
            
            public int method14(final int[] array) {
                return OleClientSite.this.OnPosRectChange(array[0]);
            }
        };
        this.iOleDocumentSite = new COMObject(new int[] { 2, 0, 0, 1 }) {
            public int method0(final int[] array) {
                return OleClientSite.this.QueryInterface(array[0], array[1]);
            }
            
            public int method1(final int[] array) {
                return OleClientSite.this.AddRef();
            }
            
            public int method2(final int[] array) {
                return OleClientSite.this.Release();
            }
            
            public int method3(final int[] array) {
                return OleClientSite.this.ActivateMe(array[0]);
            }
        };
    }
    
    protected IStorage createTempStorage() {
        final int[] array = { 0 };
        final int stgCreateDocfile = COM.StgCreateDocfile(null, 67108882, 0, array);
        if (stgCreateDocfile != 0) {
            OLE.error(1000, stgCreateDocfile);
        }
        return new IStorage(array[0]);
    }
    
    public void deactivateInPlaceClient() {
        if (this.objIOleInPlaceObject != null) {
            this.objIOleInPlaceObject.InPlaceDeactivate();
        }
    }
    
    private void deleteTempStorage() {
        if (this.tempStorage != null) {
            this.tempStorage.Release();
        }
        this.tempStorage = null;
    }
    
    protected void disposeCOMInterfaces() {
        if (this.iUnknown != null) {
            this.iUnknown.dispose();
        }
        this.iUnknown = null;
        if (this.iOleClientSite != null) {
            this.iOleClientSite.dispose();
        }
        this.iOleClientSite = null;
        if (this.iAdviseSink != null) {
            this.iAdviseSink.dispose();
        }
        this.iAdviseSink = null;
        if (this.iOleInPlaceSite != null) {
            this.iOleInPlaceSite.dispose();
        }
        this.iOleInPlaceSite = null;
        if (this.iOleDocumentSite != null) {
            this.iOleDocumentSite.dispose();
        }
        this.iOleDocumentSite = null;
    }
    
    public int doVerb(final int n) {
        if (this.state == 0 && COM.OleRun(this.objIUnknown.getAddress()) == 0) {
            this.state = 1;
        }
        if (this.state == 0 || this.isStatic) {
            return -2147467259;
        }
        final RECT rect = new RECT();
        OS.GetClientRect(this.handle, rect);
        final int doVerb = this.objIOleObject.DoVerb(n, null, this.iOleClientSite.getAddress(), 0, this.handle, rect);
        if (this.state != 1 && this.inInit) {
            this.updateStorage();
            this.inInit = false;
        }
        return doVerb;
    }
    
    public int exec(final int n, final int n2, final Variant variant, final Variant variant2) {
        if (this.objIOleCommandTarget == null) {
            final int[] array = { 0 };
            if (this.objIUnknown.QueryInterface(COM.IIDIOleCommandTarget, array) != 0) {
                return 1003;
            }
            this.objIOleCommandTarget = new IOleCommandTarget(array[0]);
        }
        int globalAlloc = 0;
        if (variant != null) {
            globalAlloc = OS.GlobalAlloc(64, VARIANT.sizeof);
            variant.getData(globalAlloc);
        }
        int globalAlloc2 = 0;
        if (variant2 != null) {
            globalAlloc2 = OS.GlobalAlloc(64, VARIANT.sizeof);
            variant2.getData(globalAlloc2);
        }
        final int exec = this.objIOleCommandTarget.Exec(null, n, n2, globalAlloc, globalAlloc2);
        if (globalAlloc != 0) {
            COM.VariantClear(globalAlloc);
            OS.GlobalFree(globalAlloc);
        }
        if (globalAlloc2 != 0) {
            variant2.setData(globalAlloc2);
            COM.VariantClear(globalAlloc2);
            OS.GlobalFree(globalAlloc2);
        }
        return exec;
    }
    
    IDispatch getAutomationObject() {
        final int[] array = { 0 };
        if (this.objIUnknown.QueryInterface(COM.IIDIDispatch, array) != 0) {
            return null;
        }
        return new IDispatch(array[0]);
    }
    
    protected GUID getClassID(final String s) {
        final GUID guid = new GUID();
        char[] array = null;
        if (s != null) {
            final int length = s.length();
            array = new char[length + 1];
            s.getChars(0, length, array, 0);
        }
        if (COM.CLSIDFromProgID(array, guid) != 0 && COM.CLSIDFromString(array, guid) != 0) {
            return null;
        }
        return guid;
    }
    
    private int GetContainer(final int n) {
        if (n != 0) {
            OS.MoveMemory(n, new int[1], OS.PTR_SIZEOF);
        }
        return -2147467262;
    }
    
    private SIZE getExtent() {
        final SIZE size = new SIZE();
        if (this.objIOleObject != null) {
            if (this.objIViewObject2 != null && !COM.OleIsRunning(this.objIOleObject.getAddress())) {
                this.objIViewObject2.GetExtent(this.aspect, -1, null, size);
            }
            else {
                this.objIOleObject.GetExtent(this.aspect, size);
            }
        }
        return this.xFormHimetricToPixels(size);
    }
    
    public Rectangle getIndent() {
        return new Rectangle(this.indent.left, this.indent.right, this.indent.top, this.indent.bottom);
    }
    
    public String getProgramID() {
        return this.getProgID(this.appClsid);
    }
    
    String getProgID(final GUID guid) {
        if (guid != null) {
            final int[] array = { 0 };
            if (COM.ProgIDFromCLSID(guid, array) == 0) {
                final int n = array[0];
                final int globalSize = OS.GlobalSize(n);
                final int globalLock = OS.GlobalLock(n);
                final char[] array2 = new char[globalSize];
                OS.MoveMemory(array2, globalLock, globalSize);
                OS.GlobalUnlock(n);
                OS.GlobalFree(n);
                final String s = new String(array2);
                return s.substring(0, s.indexOf("\u0000"));
            }
        }
        return null;
    }
    
    int ActivateMe(final int n) {
        if (n == 0) {
            final int[] array = { 0 };
            if (this.objIUnknown.QueryInterface(COM.IIDIOleDocument, array) != 0) {
                return -2147467259;
            }
            final IOleDocument oleDocument = new IOleDocument(array[0]);
            if (oleDocument.CreateView(this.iOleInPlaceSite.getAddress(), 0, 0, array) != 0) {
                return -2147467259;
            }
            oleDocument.Release();
            this.objDocumentView = new IOleDocumentView(array[0]);
        }
        else {
            (this.objDocumentView = new IOleDocumentView(n)).AddRef();
            this.objDocumentView.SetInPlaceSite(this.iOleInPlaceSite.getAddress());
        }
        this.objDocumentView.UIActivate(1);
        this.objDocumentView.SetRect(this.getRect());
        this.objDocumentView.Show(1);
        return 0;
    }
    
    protected int GetWindow(final int n) {
        if (n == 0) {
            return -2147024809;
        }
        if (this.frame == null) {
            OS.MoveMemory(n, new int[1], OS.PTR_SIZEOF);
            return -2147467263;
        }
        OS.MoveMemory(n, new int[] { this.handle }, OS.PTR_SIZEOF);
        return 0;
    }
    
    RECT getRect() {
        final Rectangle clientArea = this.getClientArea();
        final RECT rect = new RECT();
        rect.left = clientArea.x;
        rect.top = clientArea.y;
        rect.right = clientArea.x + clientArea.width;
        rect.bottom = clientArea.y + clientArea.height;
        return rect;
    }
    
    private int GetWindowContext(final int n, final int n2, final int n3, final int n4, final int n5) {
        if (this.frame == null || n == 0) {
            return -2147467263;
        }
        OS.MoveMemory(n, new int[] { this.frame.getIOleInPlaceFrame() }, OS.PTR_SIZEOF);
        this.frame.AddRef();
        if (n2 != 0) {
            OS.MoveMemory(n2, new int[1], OS.PTR_SIZEOF);
        }
        final RECT rect = this.getRect();
        if (n3 != 0) {
            OS.MoveMemory(n3, rect, RECT.sizeof);
        }
        if (n4 != 0) {
            OS.MoveMemory(n4, rect, RECT.sizeof);
        }
        final OLEINPLACEFRAMEINFO oleinplaceframeinfo = new OLEINPLACEFRAMEINFO();
        oleinplaceframeinfo.cb = OLEINPLACEFRAMEINFO.sizeof;
        oleinplaceframeinfo.fMDIApp = 0;
        oleinplaceframeinfo.hwndFrame = this.frame.handle;
        final Shell shell = this.getShell();
        final Menu menuBar = shell.getMenuBar();
        if (menuBar != null && !menuBar.isDisposed()) {
            final int handle = shell.handle;
            final int sendMessage = OS.SendMessage(handle, 32768, 0, 0);
            if (sendMessage != 0) {
                final int sendMessage2 = OS.SendMessage(handle, 32769, 0, 0);
                if (sendMessage2 != 0) {
                    oleinplaceframeinfo.cAccelEntries = sendMessage;
                    oleinplaceframeinfo.haccel = sendMessage2;
                }
            }
        }
        COM.MoveMemory(n5, oleinplaceframeinfo, OLEINPLACEFRAMEINFO.sizeof);
        return 0;
    }
    
    boolean isICAClient() {
        return this.getProgramID().startsWith("Citrix.ICAClient");
    }
    
    public boolean isDirty() {
        final int[] array = { 0 };
        if (this.objIOleObject.QueryInterface(COM.IIDIPersistFile, array) != 0) {
            return true;
        }
        final IPersistFile persistFile = new IPersistFile(array[0]);
        final int isDirty = persistFile.IsDirty();
        persistFile.Release();
        return isDirty != 1;
    }
    
    public boolean isFocusControl() {
        this.checkWidget();
        int i = OS.GetFocus();
        if (this.objIOleInPlaceObject == null) {
            return this.handle == i;
        }
        final int[] array = { 0 };
        this.objIOleInPlaceObject.GetWindow(array);
        while (i != 0) {
            if (array[0] == i) {
                return true;
            }
            i = OS.GetParent(i);
        }
        return false;
    }
    
    private boolean isOffice2007(final boolean b) {
        String s = this.getProgramID();
        if (s == null) {
            return false;
        }
        if (b) {
            final int lastIndex = s.lastIndexOf(46);
            if (lastIndex != -1) {
                s = this.getProgID(this.getClassID(s.substring(0, lastIndex)));
                if (s == null) {
                    return false;
                }
            }
        }
        return s.equals("Word.Document.12") || s.equals("Excel.Sheet.12") || s.equals("PowerPoint.Show.12");
    }
    
    private int OnClose() {
        return 0;
    }
    
    private int OnDataChange(final int n, final int n2) {
        return 0;
    }
    
    private void onDispose(final Event event) {
        this.inDispose = true;
        this.removeListener(12, this.listener);
        this.removeListener(15, this.listener);
        this.removeListener(16, this.listener);
        this.removeListener(9, this.listener);
        this.removeListener(31, this.listener);
        this.removeListener(1, this.listener);
        if (this.state != 0) {
            this.doVerb(-6);
        }
        this.deactivateInPlaceClient();
        this.releaseObjectInterfaces();
        this.deleteTempStorage();
        this.frame.removeListener(11, this.listener);
        this.frame.removeListener(10, this.listener);
        this.frame.Release();
        this.frame = null;
    }
    
    void onFocusIn(final Event event) {
        if (this.inDispose) {
            return;
        }
        if (this.state != 3) {
            final int[] array = { 0 };
            if (this.objIUnknown.QueryInterface(COM.IIDIOleInPlaceObject, array) == 0) {
                new IOleInPlaceObject(array[0]).Release();
                this.doVerb(-1);
            }
        }
        if (this.objIOleInPlaceObject == null) {
            return;
        }
        if (this.isFocusControl()) {
            return;
        }
        final int[] array2 = { 0 };
        this.objIOleInPlaceObject.GetWindow(array2);
        if (array2[0] == 0) {
            return;
        }
        OS.SetFocus(array2[0]);
    }
    
    void onFocusOut(final Event event) {
    }
    
    private int OnInPlaceActivate() {
        this.state = 2;
        this.frame.setCurrentDocument(this);
        if (this.objIOleObject == null) {
            return 0;
        }
        final int[] array = { 0 };
        if (this.objIOleObject.QueryInterface(COM.IIDIOleInPlaceObject, array) == 0) {
            this.objIOleInPlaceObject = new IOleInPlaceObject(array[0]);
        }
        return 0;
    }
    
    private int OnInPlaceDeactivate() {
        if (this.objIOleInPlaceObject != null) {
            this.objIOleInPlaceObject.Release();
        }
        this.objIOleInPlaceObject = null;
        this.state = 1;
        this.redraw();
        final Shell shell = this.getShell();
        if (this.isFocusControl() || this.frame.isFocusControl()) {
            shell.traverse(16);
        }
        return 0;
    }
    
    private int OnPosRectChange(final int n) {
        final Point size = this.getSize();
        this.setExtent(size.x, size.y);
        return 0;
    }
    
    private void onPaint(final Event event) {
        if (this.state == 1 || this.state == 2) {
            final SIZE extent = this.getExtent();
            final Rectangle clientArea = this.getClientArea();
            final RECT rect = new RECT();
            if (this.getProgramID().startsWith("Excel.Sheet")) {
                rect.left = clientArea.x;
                rect.right = clientArea.x + clientArea.height * extent.cx / extent.cy;
                rect.top = clientArea.y;
                rect.bottom = clientArea.y + clientArea.height;
            }
            else {
                rect.left = clientArea.x;
                rect.right = clientArea.x + extent.cx;
                rect.top = clientArea.y;
                rect.bottom = clientArea.y + extent.cy;
            }
            final int globalAlloc = OS.GlobalAlloc(64, RECT.sizeof);
            OS.MoveMemory(globalAlloc, rect, RECT.sizeof);
            COM.OleDraw(this.objIUnknown.getAddress(), this.aspect, event.gc.handle, globalAlloc);
            OS.GlobalFree(globalAlloc);
        }
    }
    
    private void onResize(final Event event) {
        this.setBounds();
    }
    
    private void OnSave() {
    }
    
    private int OnShowWindow(final int n) {
        return 0;
    }
    
    private int OnUIActivate() {
        if (this.objIOleInPlaceObject == null) {
            return -2147467259;
        }
        this.state = 3;
        final int[] array = { 0 };
        if (this.objIOleInPlaceObject.GetWindow(array) == 0) {
            OS.SetWindowPos(array[0], 0, 0, 0, 0, 0, 3);
        }
        return 0;
    }
    
    int OnUIDeactivate(final int n) {
        if (this.frame == null || this.frame.isDisposed()) {
            return 0;
        }
        this.state = 2;
        this.frame.SetActiveObject(0, 0);
        this.redraw();
        final Shell shell = this.getShell();
        if (this.isFocusControl() || this.frame.isFocusControl()) {
            shell.traverse(16);
        }
        final Menu menuBar = shell.getMenuBar();
        if (menuBar == null || menuBar.isDisposed()) {
            return 0;
        }
        final int handle = shell.handle;
        OS.SetMenu(handle, menuBar.handle);
        return COM.OleSetMenuDescriptor(0, handle, 0, 0, 0);
    }
    
    private void onTraverse(final Event event) {
        switch (event.detail) {
            case 2:
            case 4:
            case 8:
            case 16:
            case 128:
            case 256:
            case 512: {
                event.doit = true;
                break;
            }
        }
    }
    
    private int OnViewChange(final int n, final int n2) {
        return 0;
    }
    
    protected int QueryInterface(final int n, final int n2) {
        if (n == 0 || n2 == 0) {
            return -2147467262;
        }
        final GUID guid = new GUID();
        COM.MoveMemory(guid, n, GUID.sizeof);
        if (COM.IsEqualGUID(guid, COM.IIDIUnknown)) {
            OS.MoveMemory(n2, new int[] { this.iUnknown.getAddress() }, OS.PTR_SIZEOF);
            this.AddRef();
            return 0;
        }
        if (COM.IsEqualGUID(guid, COM.IIDIAdviseSink)) {
            OS.MoveMemory(n2, new int[] { this.iAdviseSink.getAddress() }, OS.PTR_SIZEOF);
            this.AddRef();
            return 0;
        }
        if (COM.IsEqualGUID(guid, COM.IIDIOleClientSite)) {
            OS.MoveMemory(n2, new int[] { this.iOleClientSite.getAddress() }, OS.PTR_SIZEOF);
            this.AddRef();
            return 0;
        }
        if (COM.IsEqualGUID(guid, COM.IIDIOleInPlaceSite)) {
            OS.MoveMemory(n2, new int[] { this.iOleInPlaceSite.getAddress() }, OS.PTR_SIZEOF);
            this.AddRef();
            return 0;
        }
        if (COM.IsEqualGUID(guid, COM.IIDIOleDocumentSite) && !this.getProgramID().startsWith("PowerPoint")) {
            OS.MoveMemory(n2, new int[] { this.iOleDocumentSite.getAddress() }, OS.PTR_SIZEOF);
            this.AddRef();
            return 0;
        }
        OS.MoveMemory(n2, new int[1], OS.PTR_SIZEOF);
        return -2147467262;
    }
    
    public int queryStatus(final int cmdID) {
        if (this.objIOleCommandTarget == null) {
            final int[] array = { 0 };
            if (this.objIUnknown.QueryInterface(COM.IIDIOleCommandTarget, array) != 0) {
                return 0;
            }
            this.objIOleCommandTarget = new IOleCommandTarget(array[0]);
        }
        final OLECMD olecmd = new OLECMD();
        olecmd.cmdID = cmdID;
        if (this.objIOleCommandTarget.QueryStatus(null, 1, olecmd, null) != 0) {
            return 0;
        }
        return olecmd.cmdf;
    }
    
    protected int Release() {
        --this.refCount;
        if (this.refCount == 0) {
            this.disposeCOMInterfaces();
        }
        return this.refCount;
    }
    
    protected void releaseObjectInterfaces() {
        if (this.objIOleInPlaceObject != null) {
            this.objIOleInPlaceObject.Release();
        }
        this.objIOleInPlaceObject = null;
        if (this.objIOleObject != null) {
            this.objIOleObject.Close(1);
            this.objIOleObject.Release();
        }
        this.objIOleObject = null;
        if (this.objDocumentView != null) {
            this.objDocumentView.Release();
        }
        this.objDocumentView = null;
        if (this.objIViewObject2 != null) {
            this.objIViewObject2.SetAdvise(this.aspect, 0, 0);
            this.objIViewObject2.Release();
        }
        this.objIViewObject2 = null;
        if (this.objIOleCommandTarget != null) {
            this.objIOleCommandTarget.Release();
        }
        this.objIOleCommandTarget = null;
        if (this.objIUnknown != null) {
            this.objIUnknown.Release();
        }
        this.objIUnknown = null;
        if (COM.FreeUnusedLibraries) {
            COM.CoFreeUnusedLibraries();
        }
    }
    
    public boolean save(final File file, final boolean b) {
        if (this.isOffice2007(false)) {
            return this.saveOffice2007(file);
        }
        if (b) {
            return this.saveToStorageFile(file);
        }
        return this.saveToTraditionalFile(file);
    }
    
    private boolean saveFromContents(final int n, final File file) {
        boolean b = false;
        final IStream stream = new IStream(n);
        stream.AddRef();
        try {
            final FileOutputStream fileOutputStream = new FileOutputStream(file);
            final int n2 = 4096;
            final int coTaskMemAlloc = OS.CoTaskMemAlloc(n2);
            final int[] array = { 0 };
            while (stream.Read(coTaskMemAlloc, n2, array) == 0 && array[0] > 0) {
                final byte[] array2 = new byte[array[0]];
                OS.MoveMemory(array2, coTaskMemAlloc, array[0]);
                fileOutputStream.write(array2);
                b = true;
            }
            OS.CoTaskMemFree(coTaskMemAlloc);
            fileOutputStream.close();
        }
        catch (IOException ex) {}
        stream.Release();
        return b;
    }
    
    private boolean saveFromOle10Native(final int n, final File file) {
        boolean b = false;
        final IStream stream = new IStream(n);
        stream.AddRef();
        final int coTaskMemAlloc = OS.CoTaskMemAlloc(4);
        final int[] array = { 0 };
        final int read = stream.Read(coTaskMemAlloc, 4, null);
        OS.MoveMemory(array, coTaskMemAlloc, 4);
        OS.CoTaskMemFree(coTaskMemAlloc);
        if (read == 0 && array[0] > 0) {
            final byte[] array2 = new byte[array[0]];
            final int coTaskMemAlloc2 = OS.CoTaskMemAlloc(array[0]);
            stream.Read(coTaskMemAlloc2, array[0], null);
            OS.MoveMemory(array2, coTaskMemAlloc2, array[0]);
            OS.CoTaskMemFree(coTaskMemAlloc2);
            try {
                final FileOutputStream fileOutputStream = new FileOutputStream(file);
                fileOutputStream.write(array2);
                fileOutputStream.close();
                b = true;
            }
            catch (IOException ex) {}
        }
        stream.Release();
        return b;
    }
    
    private int SaveObject() {
        this.updateStorage();
        return 0;
    }
    
    private boolean saveOffice2007(final File file) {
        if (file == null || file.isDirectory()) {
            return false;
        }
        if (!this.updateStorage()) {
            return false;
        }
        boolean saveFromContents = false;
        final int[] array = { 0 };
        IPersistStorage persistStorage = null;
        if (this.objIUnknown.QueryInterface(COM.IIDIPersistStorage, array) == 0) {
            persistStorage = new IPersistStorage(array[0]);
            this.tempStorage.AddRef();
            persistStorage.HandsOffStorage();
        }
        final int[] array2 = { 0 };
        if (this.tempStorage.OpenStream("Package", 0, 16, 0, array2) == 0) {
            saveFromContents = this.saveFromContents(array2[0], file);
        }
        if (persistStorage != null) {
            persistStorage.SaveCompleted(this.tempStorage.getAddress());
            this.tempStorage.Release();
            persistStorage.Release();
        }
        return saveFromContents;
    }
    
    private boolean saveToStorageFile(final File file) {
        if (file == null || file.isDirectory()) {
            return false;
        }
        if (!this.updateStorage()) {
            return false;
        }
        final int[] array = { 0 };
        if (this.objIOleObject.QueryInterface(COM.IIDIPersistStorage, array) != 0) {
            return false;
        }
        final IPersistStorage persistStorage = new IPersistStorage(array[0]);
        try {
            final int[] array2 = { 0 };
            if (COM.StgCreateDocfile((String.valueOf(file.getAbsolutePath()) + "\u0000").toCharArray(), 69650, 0, array2) != 0) {
                return false;
            }
            final IStorage storage = new IStorage(array2[0]);
            try {
                if (COM.OleSave(persistStorage.getAddress(), storage.getAddress(), false) == 0 && storage.Commit(0) == 0) {
                    return true;
                }
            }
            finally {
                storage.Release();
            }
            storage.Release();
        }
        finally {
            persistStorage.Release();
        }
        persistStorage.Release();
        return false;
    }
    
    private boolean saveToTraditionalFile(final File file) {
        if (file == null || file.isDirectory()) {
            return false;
        }
        if (!this.updateStorage()) {
            return false;
        }
        final int[] array = { 0 };
        if (this.tempStorage.OpenStream("CONTENTS", 0, 16, 0, array) == 0) {
            return this.saveFromContents(array[0], file);
        }
        return this.tempStorage.OpenStream("\u0001Ole10Native", 0, 16, 0, array) == 0 && this.saveFromOle10Native(array[0], file);
    }
    
    private int Scroll_64(final int n) {
        return 0;
    }
    
    private int Scroll(final int n, final int n2) {
        return 0;
    }
    
    void setBorderSpace(final RECT borderWidths) {
        this.borderWidths = borderWidths;
        this.setBounds();
    }
    
    void setBounds() {
        final Rectangle clientArea = this.frame.getClientArea();
        this.setBounds(this.borderWidths.left, this.borderWidths.top, clientArea.width - this.borderWidths.left - this.borderWidths.right, clientArea.height - this.borderWidths.top - this.borderWidths.bottom);
        this.setObjectRects();
    }
    
    private void setExtent(final int cx, final int cy) {
        if (this.objIOleObject == null || this.isStatic || this.inUpdate) {
            return;
        }
        final SIZE extent = this.getExtent();
        if (cx == extent.cx && cy == extent.cy) {
            return;
        }
        final SIZE size = new SIZE();
        size.cx = cx;
        size.cy = cy;
        final SIZE xFormPixelsToHimetric = this.xFormPixelsToHimetric(size);
        final boolean oleIsRunning = COM.OleIsRunning(this.objIOleObject.getAddress());
        if (!oleIsRunning) {
            COM.OleRun(this.objIOleObject.getAddress());
        }
        if (this.objIOleObject.SetExtent(this.aspect, xFormPixelsToHimetric) == 0) {
            this.inUpdate = true;
            this.objIOleObject.Update();
            this.inUpdate = false;
            if (!oleIsRunning) {
                this.objIOleObject.Close(0);
            }
        }
    }
    
    public void setIndent(final Rectangle rectangle) {
        this.indent = new RECT();
        this.indent.left = rectangle.x;
        this.indent.right = rectangle.width;
        this.indent.top = rectangle.y;
        this.indent.bottom = rectangle.height;
    }
    
    private void setObjectRects() {
        if (this.objIOleInPlaceObject == null) {
            return;
        }
        final RECT rect = this.getRect();
        this.objIOleInPlaceObject.SetObjectRects(rect, rect);
    }
    
    private int ShowObject() {
        this.setBounds();
        return 0;
    }
    
    public void showProperties(final String s) {
        final int[] array = { 0 };
        if (this.objIUnknown.QueryInterface(COM.IIDISpecifyPropertyPages, array) != 0) {
            return;
        }
        final ISpecifyPropertyPages specifyPropertyPages = new ISpecifyPropertyPages(array[0]);
        final CAUUID cauuid = new CAUUID();
        final int getPages = specifyPropertyPages.GetPages(cauuid);
        specifyPropertyPages.Release();
        if (getPages != 0) {
            return;
        }
        char[] array2 = null;
        if (s != null) {
            array2 = new char[s.length()];
            s.getChars(0, s.length(), array2, 0);
        }
        COM.OleCreatePropertyFrame(this.frame.handle, 10, 10, array2, 1, new int[] { this.objIUnknown.getAddress() }, cauuid.cElems, cauuid.pElems, 2048, 0, 0);
        OS.CoTaskMemFree(cauuid.pElems);
    }
    
    private boolean updateStorage() {
        if (this.tempStorage == null) {
            return false;
        }
        final int[] array = { 0 };
        if (this.objIUnknown.QueryInterface(COM.IIDIPersistStorage, array) != 0) {
            return false;
        }
        final IPersistStorage persistStorage = new IPersistStorage(array[0]);
        if (COM.OleSave(persistStorage.getAddress(), this.tempStorage.getAddress(), true) != 0) {
            COM.WriteClassStg(this.tempStorage.getAddress(), this.objClsid);
            persistStorage.Save(this.tempStorage.getAddress(), true);
        }
        this.tempStorage.Commit(0);
        persistStorage.SaveCompleted(0);
        persistStorage.Release();
        return true;
    }
    
    private SIZE xFormHimetricToPixels(final SIZE size) {
        final int getDC = OS.GetDC(0);
        final int getDeviceCaps = OS.GetDeviceCaps(getDC, 88);
        final int getDeviceCaps2 = OS.GetDeviceCaps(getDC, 90);
        OS.ReleaseDC(0, getDC);
        final int round = Compatibility.round(size.cx * getDeviceCaps, 2540);
        final int round2 = Compatibility.round(size.cy * getDeviceCaps2, 2540);
        final SIZE size2 = new SIZE();
        size2.cx = round;
        size2.cy = round2;
        return size2;
    }
    
    private SIZE xFormPixelsToHimetric(final SIZE size) {
        final int getDC = OS.GetDC(0);
        final int getDeviceCaps = OS.GetDeviceCaps(getDC, 88);
        final int getDeviceCaps2 = OS.GetDeviceCaps(getDC, 90);
        OS.ReleaseDC(0, getDC);
        final int round = Compatibility.round(size.cx * 2540, getDeviceCaps);
        final int round2 = Compatibility.round(size.cy * 2540, getDeviceCaps2);
        final SIZE size2 = new SIZE();
        size2.cx = round;
        size2.cy = round2;
        return size2;
    }
}
