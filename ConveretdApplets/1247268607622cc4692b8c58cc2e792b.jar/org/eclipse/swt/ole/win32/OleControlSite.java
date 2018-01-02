// 
// Decompiled by Procyon v0.5.30
// 

package org.eclipse.swt.ole.win32;

import org.eclipse.swt.graphics.FontData;
import org.eclipse.swt.internal.win32.TCHAR;
import org.eclipse.swt.internal.win32.GUITHREADINFO;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.internal.win32.OS;
import org.eclipse.swt.internal.ole.win32.LICINFO;
import org.eclipse.swt.graphics.Device;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.SWT;
import org.eclipse.swt.internal.ole.win32.IOleControl;
import org.eclipse.swt.internal.ole.win32.TYPEATTR;
import org.eclipse.swt.internal.ole.win32.ITypeInfo;
import org.eclipse.swt.internal.ole.win32.IProvideClassInfo;
import org.eclipse.swt.internal.ole.win32.IProvideClassInfo2;
import org.eclipse.swt.SWTError;
import org.eclipse.swt.internal.ole.win32.IPersistStorage;
import org.eclipse.swt.internal.ole.win32.IClassFactory2;
import org.eclipse.swt.internal.ole.win32.IUnknown;
import org.eclipse.swt.internal.ole.win32.FORMATETC;
import org.eclipse.swt.internal.ole.win32.COM;
import java.io.File;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.graphics.Font;
import org.eclipse.swt.internal.ole.win32.CONTROLINFO;
import org.eclipse.swt.internal.ole.win32.GUID;
import org.eclipse.swt.internal.ole.win32.COMObject;

public class OleControlSite extends OleClientSite
{
    private COMObject iOleControlSite;
    private COMObject iDispatch;
    private OlePropertyChangeSink olePropertyChangeSink;
    private OleEventSink[] oleEventSink;
    private GUID[] oleEventSinkGUID;
    private int[] oleEventSinkIUnknown;
    private CONTROLINFO currentControlInfo;
    private int[] sitePropertyIds;
    private Variant[] sitePropertyValues;
    private Font font;
    static int SWT_RESTORECARET;
    static final String SHELL_PROG_ID = "Shell.Explorer";
    
    public OleControlSite(final Composite composite, final int n, final File file) {
        super(composite, n, file);
        this.oleEventSink = new OleEventSink[0];
        this.oleEventSinkGUID = new GUID[0];
        this.oleEventSinkIUnknown = new int[0];
        this.sitePropertyIds = new int[0];
        this.sitePropertyValues = new Variant[0];
        this.setSiteProperty(-709, new Variant(true));
        this.setSiteProperty(-710, new Variant(false));
    }
    
    public OleControlSite(final Composite composite, final int n, final String s) {
        super(composite, n);
        this.oleEventSink = new OleEventSink[0];
        this.oleEventSinkGUID = new GUID[0];
        this.oleEventSinkIUnknown = new int[0];
        this.sitePropertyIds = new int[0];
        this.sitePropertyValues = new Variant[0];
        try {
            this.appClsid = this.getClassID(s);
            if (this.appClsid == null) {
                OLE.error(1004);
            }
            final int licenseInfo = this.getLicenseInfo(this.appClsid);
            if (licenseInfo == 0) {
                this.tempStorage = this.createTempStorage();
                final int[] array = { 0 };
                final int oleCreate = COM.OleCreate(this.appClsid, COM.IIDIUnknown, 1, null, this.isICAClient() ? 0 : this.iOleClientSite.getAddress(), this.tempStorage.getAddress(), array);
                if (oleCreate != 0) {
                    OLE.error(1001, oleCreate);
                }
                this.objIUnknown = new IUnknown(array[0]);
            }
            else {
                int[] array2 = { 0 };
                try {
                    final int coGetClassObject = COM.CoGetClassObject(this.appClsid, 3, 0, COM.IIDIClassFactory2, array2);
                    if (coGetClassObject != 0) {
                        OLE.error(1005, coGetClassObject);
                    }
                    final IClassFactory2 classFactory2 = new IClassFactory2(array2[0]);
                    array2 = new int[] { 0 };
                    final int createInstanceLic = classFactory2.CreateInstanceLic(0, 0, COM.IIDIUnknown, licenseInfo, array2);
                    classFactory2.Release();
                    if (createInstanceLic != 0) {
                        OLE.error(1006, createInstanceLic);
                    }
                }
                finally {
                    COM.SysFreeString(licenseInfo);
                }
                COM.SysFreeString(licenseInfo);
                this.objIUnknown = new IUnknown(array2[0]);
                final int[] array3 = { 0 };
                if (this.objIUnknown.QueryInterface(COM.IIDIPersistStorage, array3) == 0) {
                    final IPersistStorage persistStorage = new IPersistStorage(array3[0]);
                    this.tempStorage = this.createTempStorage();
                    persistStorage.InitNew(this.tempStorage.getAddress());
                    persistStorage.Release();
                }
            }
            this.addObjectReferences();
            this.setSiteProperty(-709, new Variant(true));
            this.setSiteProperty(-710, new Variant(false));
            if (COM.OleRun(this.objIUnknown.getAddress()) == 0) {
                this.state = 1;
            }
        }
        catch (SWTError swtError) {
            this.dispose();
            this.disposeCOMInterfaces();
            throw swtError;
        }
    }
    
    public OleControlSite(final Composite composite, final int n, final String s, final File file) {
        super(composite, n, s, file);
        this.oleEventSink = new OleEventSink[0];
        this.oleEventSinkGUID = new GUID[0];
        this.oleEventSinkIUnknown = new int[0];
        this.sitePropertyIds = new int[0];
        this.sitePropertyValues = new Variant[0];
        this.setSiteProperty(-709, new Variant(true));
        this.setSiteProperty(-710, new Variant(false));
    }
    
    public void addEventListener(final int n, final OleListener oleListener) {
        if (oleListener == null) {
            OLE.error(4);
        }
        final GUID defaultEventSinkGUID = getDefaultEventSinkGUID(this.objIUnknown);
        if (defaultEventSinkGUID != null) {
            this.addEventListener(this.objIUnknown.getAddress(), defaultEventSinkGUID, n, oleListener);
        }
    }
    
    static GUID getDefaultEventSinkGUID(final IUnknown unknown) {
        final int[] array = { 0 };
        if (unknown.QueryInterface(COM.IIDIProvideClassInfo2, array) == 0) {
            final IProvideClassInfo2 provideClassInfo2 = new IProvideClassInfo2(array[0]);
            final GUID guid = new GUID();
            final int getGUID = provideClassInfo2.GetGUID(1, guid);
            provideClassInfo2.Release();
            if (getGUID == 0) {
                return guid;
            }
        }
        if (unknown.QueryInterface(COM.IIDIProvideClassInfo, array) == 0) {
            final IProvideClassInfo provideClassInfo3 = new IProvideClassInfo(array[0]);
            final int[] array2 = { 0 };
            final int[] array3 = { 0 };
            final int getClassInfo = provideClassInfo3.GetClassInfo(array2);
            provideClassInfo3.Release();
            if (getClassInfo == 0 && array2[0] != 0) {
                final ITypeInfo typeInfo = new ITypeInfo(array2[0]);
                final int[] array4 = { 0 };
                if (typeInfo.GetTypeAttr(array4) == 0 && array4[0] != 0) {
                    final TYPEATTR typeattr = new TYPEATTR();
                    COM.MoveMemory(typeattr, array4[0], TYPEATTR.sizeof);
                    typeInfo.ReleaseTypeAttr(array4[0]);
                    final int n = 7;
                    final int n2 = 3;
                    for (short n3 = 0; n3 < typeattr.cImplTypes; ++n3) {
                        final int[] array5 = { 0 };
                        if (typeInfo.GetImplTypeFlags(n3, array5) == 0 && (array5[0] & n) == n2) {
                            final int[] array6 = { 0 };
                            if (typeInfo.GetRefTypeOfImplType(n3, array6) == 0) {
                                typeInfo.GetRefTypeInfo(array6[0], array3);
                            }
                        }
                    }
                }
                typeInfo.Release();
                if (array3[0] != 0) {
                    final ITypeInfo typeInfo2 = new ITypeInfo(array3[0]);
                    final int[] array7 = { 0 };
                    final int getTypeAttr = typeInfo2.GetTypeAttr(array7);
                    GUID guid2 = null;
                    if (getTypeAttr == 0 && array7[0] != 0) {
                        guid2 = new GUID();
                        COM.MoveMemory(guid2, array7[0], GUID.sizeof);
                        typeInfo2.ReleaseTypeAttr(array7[0]);
                    }
                    typeInfo2.Release();
                    return guid2;
                }
            }
        }
        return null;
    }
    
    public void addEventListener(final OleAutomation oleAutomation, final int n, final OleListener oleListener) {
        if (oleListener == null || oleAutomation == null) {
            OLE.error(4);
        }
        final int address = oleAutomation.getAddress();
        final GUID defaultEventSinkGUID = getDefaultEventSinkGUID(new IUnknown(address));
        if (defaultEventSinkGUID != null) {
            this.addEventListener(address, defaultEventSinkGUID, n, oleListener);
        }
    }
    
    public void addEventListener(final OleAutomation oleAutomation, final String s, final int n, final OleListener oleListener) {
        if (oleListener == null || oleAutomation == null || s == null) {
            OLE.error(4);
        }
        final int address = oleAutomation.getAddress();
        if (address == 0) {
            return;
        }
        final char[] charArray = (String.valueOf(s) + "\u0000").toCharArray();
        final GUID guid = new GUID();
        if (COM.IIDFromString(charArray, guid) != 0) {
            return;
        }
        this.addEventListener(address, guid, n, oleListener);
    }
    
    void addEventListener(final int n, final GUID guid, final int n2, final OleListener oleListener) {
        if (oleListener == null || n == 0 || guid == null) {
            OLE.error(4);
        }
        int n3 = -1;
        for (int i = 0; i < this.oleEventSinkGUID.length; ++i) {
            if (COM.IsEqualGUID(this.oleEventSinkGUID[i], guid) && n == this.oleEventSinkIUnknown[i]) {
                n3 = i;
                break;
            }
        }
        if (n3 != -1) {
            this.oleEventSink[n3].addListener(n2, oleListener);
        }
        else {
            final int length = this.oleEventSink.length;
            final OleEventSink[] oleEventSink = new OleEventSink[length + 1];
            final GUID[] oleEventSinkGUID = new GUID[length + 1];
            final int[] oleEventSinkIUnknown = new int[length + 1];
            System.arraycopy(this.oleEventSink, 0, oleEventSink, 0, length);
            System.arraycopy(this.oleEventSinkGUID, 0, oleEventSinkGUID, 0, length);
            System.arraycopy(this.oleEventSinkIUnknown, 0, oleEventSinkIUnknown, 0, length);
            this.oleEventSink = oleEventSink;
            this.oleEventSinkGUID = oleEventSinkGUID;
            this.oleEventSinkIUnknown = oleEventSinkIUnknown;
            this.oleEventSink[length] = new OleEventSink(this, n, guid);
            this.oleEventSinkGUID[length] = guid;
            this.oleEventSinkIUnknown[length] = n;
            this.oleEventSink[length].AddRef();
            this.oleEventSink[length].connect();
            this.oleEventSink[length].addListener(n2, oleListener);
        }
    }
    
    protected void addObjectReferences() {
        super.addObjectReferences();
        this.connectPropertyChangeSink();
        final int[] array = { 0 };
        if (this.objIUnknown.QueryInterface(COM.IIDIOleControl, array) == 0) {
            final IOleControl oleControl = new IOleControl(array[0]);
            oleControl.GetControlInfo(this.currentControlInfo = new CONTROLINFO());
            oleControl.Release();
        }
    }
    
    public void addPropertyListener(final int n, final OleListener oleListener) {
        if (oleListener == null) {
            SWT.error(4);
        }
        this.olePropertyChangeSink.addListener(n, oleListener);
    }
    
    private void connectPropertyChangeSink() {
        (this.olePropertyChangeSink = new OlePropertyChangeSink(this)).AddRef();
        this.olePropertyChangeSink.connect(this.objIUnknown);
    }
    
    protected void createCOMInterfaces() {
        super.createCOMInterfaces();
        this.iOleControlSite = new COMObject(new int[] { 2, 0, 0, 0, 1, 1, 3, 2, 1, 0 }) {
            public int method0(final int[] array) {
                return OleControlSite.this.QueryInterface(array[0], array[1]);
            }
            
            public int method1(final int[] array) {
                return OleControlSite.this.AddRef();
            }
            
            public int method2(final int[] array) {
                return OleControlSite.this.Release();
            }
            
            public int method3(final int[] array) {
                return OleControlSite.this.OnControlInfoChanged();
            }
            
            public int method8(final int[] array) {
                return OleControlSite.this.OnFocus(array[0]);
            }
        };
        this.iDispatch = new COMObject(new int[] { 2, 0, 0, 1, 3, 5, 8 }) {
            public int method0(final int[] array) {
                return OleControlSite.this.QueryInterface(array[0], array[1]);
            }
            
            public int method1(final int[] array) {
                return OleControlSite.this.AddRef();
            }
            
            public int method2(final int[] array) {
                return OleControlSite.this.Release();
            }
            
            public int method6(final int[] array) {
                return OleControlSite.this.Invoke(array[0], array[1], array[2], array[3], array[4], array[5], array[6], array[7]);
            }
        };
    }
    
    private void disconnectEventSinks() {
        for (int i = 0; i < this.oleEventSink.length; ++i) {
            final OleEventSink oleEventSink = this.oleEventSink[i];
            oleEventSink.disconnect();
            oleEventSink.Release();
        }
        this.oleEventSink = new OleEventSink[0];
        this.oleEventSinkGUID = new GUID[0];
        this.oleEventSinkIUnknown = new int[0];
    }
    
    private void disconnectPropertyChangeSink() {
        if (this.olePropertyChangeSink != null) {
            this.olePropertyChangeSink.disconnect(this.objIUnknown);
            this.olePropertyChangeSink.Release();
        }
        this.olePropertyChangeSink = null;
    }
    
    protected void disposeCOMInterfaces() {
        super.disposeCOMInterfaces();
        if (this.iOleControlSite != null) {
            this.iOleControlSite.dispose();
        }
        this.iOleControlSite = null;
        if (this.iDispatch != null) {
            this.iDispatch.dispose();
        }
        this.iDispatch = null;
    }
    
    public Color getBackground() {
        if (this.objIUnknown != null) {
            final OleAutomation oleAutomation = new OleAutomation(this);
            final Variant property = oleAutomation.getProperty(-501);
            oleAutomation.dispose();
            if (property != null) {
                final int[] array = { 0 };
                if (COM.OleTranslateColor(property.getInt(), this.getDisplay().hPalette, array) == 0) {
                    return Color.win32_new(this.getDisplay(), array[0]);
                }
            }
        }
        return super.getBackground();
    }
    
    public Font getFont() {
        if (this.font != null && !this.font.isDisposed()) {
            return this.font;
        }
        if (this.objIUnknown != null) {
            final OleAutomation oleAutomation = new OleAutomation(this);
            final Variant property = oleAutomation.getProperty(-512);
            oleAutomation.dispose();
            if (property != null) {
                final OleAutomation automation = property.getAutomation();
                final Variant property2 = automation.getProperty(0);
                final Variant property3 = automation.getProperty(2);
                final Variant property4 = automation.getProperty(4);
                final Variant property5 = automation.getProperty(3);
                automation.dispose();
                if (property2 != null && property3 != null && property4 != null && property5 != null) {
                    return this.font = new Font(this.getShell().getDisplay(), property2.getString(), property3.getInt(), 3 * property5.getInt() + 2 * property4.getInt());
                }
            }
        }
        return super.getFont();
    }
    
    public Color getForeground() {
        if (this.objIUnknown != null) {
            final OleAutomation oleAutomation = new OleAutomation(this);
            final Variant property = oleAutomation.getProperty(-513);
            oleAutomation.dispose();
            if (property != null) {
                final int[] array = { 0 };
                if (COM.OleTranslateColor(property.getInt(), this.getDisplay().hPalette, array) == 0) {
                    return Color.win32_new(this.getDisplay(), array[0]);
                }
            }
        }
        return super.getForeground();
    }
    
    protected int getLicenseInfo(final GUID guid) {
        final int[] array = { 0 };
        if (COM.CoGetClassObject(guid, 3, 0, COM.IIDIClassFactory, array) != 0) {
            return 0;
        }
        int n = 0;
        final IUnknown unknown = new IUnknown(array[0]);
        if (unknown.QueryInterface(COM.IIDIClassFactory2, array) == 0) {
            final IClassFactory2 classFactory2 = new IClassFactory2(array[0]);
            final LICINFO licinfo = new LICINFO();
            if (classFactory2.GetLicInfo(licinfo) == 0) {
                final int[] array2 = { 0 };
                if (licinfo != null && licinfo.fRuntimeKeyAvail && classFactory2.RequestLicKey(0, array2) == 0) {
                    n = array2[0];
                }
            }
            classFactory2.Release();
        }
        unknown.Release();
        return n;
    }
    
    public Variant getSiteProperty(final int n) {
        for (int i = 0; i < this.sitePropertyIds.length; ++i) {
            if (this.sitePropertyIds[i] == n) {
                return this.sitePropertyValues[i];
            }
        }
        return null;
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
    
    private int Invoke(final int n, final int n2, final int n3, final int n4, final int n5, final int n6, final int n7, final int n8) {
        if (n6 == 0 || n4 != 2) {
            if (n7 != 0) {
                OS.MoveMemory(n7, new int[1], OS.PTR_SIZEOF);
            }
            if (n8 != 0) {
                OS.MoveMemory(n8, new int[1], 4);
            }
            return -2147352573;
        }
        final Variant siteProperty = this.getSiteProperty(n);
        if (siteProperty != null) {
            if (n6 != 0) {
                siteProperty.getData(n6);
            }
            return 0;
        }
        switch (n) {
            case -714:
            case -712:
            case -711: {
                if (n6 != 0) {
                    OS.MoveMemory(n6, new int[1], OS.PTR_SIZEOF);
                }
                if (n7 != 0) {
                    OS.MoveMemory(n7, new int[1], OS.PTR_SIZEOF);
                }
                if (n8 != 0) {
                    OS.MoveMemory(n8, new int[1], 4);
                }
                return 1;
            }
            case -5502:
            case -5501:
            case -706:
            case -705:
            case -704:
            case -703:
            case -701: {
                if (n6 != 0) {
                    OS.MoveMemory(n6, new int[1], OS.PTR_SIZEOF);
                }
                if (n7 != 0) {
                    OS.MoveMemory(n7, new int[1], OS.PTR_SIZEOF);
                }
                if (n8 != 0) {
                    OS.MoveMemory(n8, new int[1], 4);
                }
                return -2147467263;
            }
            default: {
                if (n6 != 0) {
                    OS.MoveMemory(n6, new int[1], OS.PTR_SIZEOF);
                }
                if (n7 != 0) {
                    OS.MoveMemory(n7, new int[1], OS.PTR_SIZEOF);
                }
                if (n8 != 0) {
                    OS.MoveMemory(n8, new int[1], 4);
                }
                return -2147352573;
            }
        }
    }
    
    private int OnControlInfoChanged() {
        final int[] array = { 0 };
        if (this.objIUnknown.QueryInterface(COM.IIDIOleControl, array) == 0) {
            final IOleControl oleControl = new IOleControl(array[0]);
            oleControl.GetControlInfo(this.currentControlInfo = new CONTROLINFO());
            oleControl.Release();
        }
        return 0;
    }
    
    protected int OnUIDeactivate(final int n) {
        return super.OnUIDeactivate(n);
    }
    
    void onFocusIn(final Event event) {
        final String programID = this.getProgramID();
        if (programID == null) {
            return;
        }
        if (!programID.startsWith("Shell.Explorer")) {
            super.onFocusIn(event);
            return;
        }
        if (this.objIOleInPlaceObject == null) {
            return;
        }
        if (!this.isActivated) {
            this.doVerb(-4);
        }
        if (this.isFocusControl()) {
            return;
        }
        final int[] array = { 0 };
        this.objIOleInPlaceObject.GetWindow(array);
        if (array[0] == 0) {
            return;
        }
        OS.SetFocus(array[0]);
    }
    
    void onFocusOut(final Event event) {
        if (this.objIOleInPlaceObject == null) {
            return;
        }
        final String programID = this.getProgramID();
        if (programID == null) {
            return;
        }
        if (!programID.startsWith("Shell.Explorer")) {
            super.onFocusOut(event);
            return;
        }
        if (this.isFocusControl()) {
            return;
        }
        final int getCurrentThreadId = OS.GetCurrentThreadId();
        final GUITHREADINFO guithreadinfo = new GUITHREADINFO();
        guithreadinfo.cbSize = GUITHREADINFO.sizeof;
        OS.GetGUIThreadInfo(getCurrentThreadId, guithreadinfo);
        this.objIOleInPlaceObject.UIDeactivate();
        if (guithreadinfo.hwndCaret != 0) {
            final GUITHREADINFO guithreadinfo2 = new GUITHREADINFO();
            guithreadinfo2.cbSize = GUITHREADINFO.sizeof;
            OS.GetGUIThreadInfo(getCurrentThreadId, guithreadinfo2);
            if (guithreadinfo2.hwndCaret == 0 && guithreadinfo.hwndCaret == OS.GetFocus()) {
                if (OleControlSite.SWT_RESTORECARET == 0) {
                    OleControlSite.SWT_RESTORECARET = OS.RegisterWindowMessage(new TCHAR(0, "SWT_RESTORECARET", true));
                }
                if (OS.SendMessage(guithreadinfo.hwndCaret, OleControlSite.SWT_RESTORECARET, 0, 0) == 0) {
                    OS.CreateCaret(guithreadinfo.hwndCaret, 0, guithreadinfo.right - guithreadinfo.left, guithreadinfo.bottom - guithreadinfo.top);
                    OS.SetCaretPos(guithreadinfo.left, guithreadinfo.top);
                    OS.ShowCaret(guithreadinfo.hwndCaret);
                }
            }
        }
    }
    
    private int OnFocus(final int n) {
        return 0;
    }
    
    protected int QueryInterface(final int n, final int n2) {
        final int queryInterface = super.QueryInterface(n, n2);
        if (queryInterface == 0) {
            return queryInterface;
        }
        if (n == 0 || n2 == 0) {
            return -2147024809;
        }
        final GUID guid = new GUID();
        COM.MoveMemory(guid, n, GUID.sizeof);
        if (COM.IsEqualGUID(guid, COM.IIDIOleControlSite)) {
            OS.MoveMemory(n2, new int[] { this.iOleControlSite.getAddress() }, OS.PTR_SIZEOF);
            this.AddRef();
            return 0;
        }
        if (COM.IsEqualGUID(guid, COM.IIDIDispatch)) {
            OS.MoveMemory(n2, new int[] { this.iDispatch.getAddress() }, OS.PTR_SIZEOF);
            this.AddRef();
            return 0;
        }
        OS.MoveMemory(n2, new int[1], OS.PTR_SIZEOF);
        return -2147467262;
    }
    
    protected int Release() {
        final int release = super.Release();
        if (release == 0) {
            for (int i = 0; i < this.sitePropertyIds.length; ++i) {
                this.sitePropertyValues[i].dispose();
            }
            this.sitePropertyIds = new int[0];
            this.sitePropertyValues = new Variant[0];
        }
        return release;
    }
    
    protected void releaseObjectInterfaces() {
        this.disconnectEventSinks();
        this.disconnectPropertyChangeSink();
        super.releaseObjectInterfaces();
    }
    
    public void removeEventListener(final int n, final OleListener oleListener) {
        this.checkWidget();
        if (oleListener == null) {
            SWT.error(4);
        }
        final GUID defaultEventSinkGUID = getDefaultEventSinkGUID(this.objIUnknown);
        if (defaultEventSinkGUID != null) {
            this.removeEventListener(this.objIUnknown.getAddress(), defaultEventSinkGUID, n, oleListener);
        }
    }
    
    public void removeEventListener(final OleAutomation oleAutomation, final GUID guid, final int n, final OleListener oleListener) {
        this.checkWidget();
        if (oleAutomation == null || oleListener == null || guid == null) {
            SWT.error(4);
        }
        this.removeEventListener(oleAutomation.getAddress(), guid, n, oleListener);
    }
    
    public void removeEventListener(final OleAutomation oleAutomation, final int n, final OleListener oleListener) {
        this.checkWidget();
        if (oleAutomation == null || oleListener == null) {
            SWT.error(4);
        }
        final int address = oleAutomation.getAddress();
        final GUID defaultEventSinkGUID = getDefaultEventSinkGUID(new IUnknown(address));
        if (defaultEventSinkGUID != null) {
            this.removeEventListener(address, defaultEventSinkGUID, n, oleListener);
        }
    }
    
    void removeEventListener(final int n, final GUID guid, final int n2, final OleListener oleListener) {
        if (oleListener == null || guid == null) {
            SWT.error(4);
        }
        for (int i = 0; i < this.oleEventSink.length; ++i) {
            if (COM.IsEqualGUID(this.oleEventSinkGUID[i], guid) && n == this.oleEventSinkIUnknown[i]) {
                this.oleEventSink[i].removeListener(n2, oleListener);
                if (!this.oleEventSink[i].hasListeners()) {
                    this.oleEventSink[i].disconnect();
                    this.oleEventSink[i].Release();
                    final int length = this.oleEventSink.length;
                    if (length == 1) {
                        this.oleEventSink = new OleEventSink[0];
                        this.oleEventSinkGUID = new GUID[0];
                        this.oleEventSinkIUnknown = new int[0];
                    }
                    else {
                        final OleEventSink[] oleEventSink = new OleEventSink[length - 1];
                        System.arraycopy(this.oleEventSink, 0, oleEventSink, 0, i);
                        System.arraycopy(this.oleEventSink, i + 1, oleEventSink, i, length - i - 1);
                        this.oleEventSink = oleEventSink;
                        final GUID[] oleEventSinkGUID = new GUID[length - 1];
                        System.arraycopy(this.oleEventSinkGUID, 0, oleEventSinkGUID, 0, i);
                        System.arraycopy(this.oleEventSinkGUID, i + 1, oleEventSinkGUID, i, length - i - 1);
                        this.oleEventSinkGUID = oleEventSinkGUID;
                        final int[] oleEventSinkIUnknown = new int[length - 1];
                        System.arraycopy(this.oleEventSinkIUnknown, 0, oleEventSinkIUnknown, 0, i);
                        System.arraycopy(this.oleEventSinkIUnknown, i + 1, oleEventSinkIUnknown, i, length - i - 1);
                        this.oleEventSinkIUnknown = oleEventSinkIUnknown;
                    }
                }
                return;
            }
        }
    }
    
    public void removePropertyListener(final int n, final OleListener oleListener) {
        if (oleListener == null) {
            SWT.error(4);
        }
        this.olePropertyChangeSink.removeListener(n, oleListener);
    }
    
    public void setBackground(final Color background) {
        super.setBackground(background);
        if (this.objIUnknown != null) {
            final OleAutomation oleAutomation = new OleAutomation(this);
            oleAutomation.setProperty(-501, new Variant(background.handle));
            oleAutomation.dispose();
        }
    }
    
    public void setFont(final Font font) {
        super.setFont(font);
        if (this.objIUnknown != null) {
            final OleAutomation oleAutomation = new OleAutomation(this);
            final Variant property = oleAutomation.getProperty(-512);
            oleAutomation.dispose();
            if (property != null) {
                final OleAutomation automation = property.getAutomation();
                final FontData[] fontData = font.getFontData();
                automation.setProperty(0, new Variant(fontData[0].getName()));
                automation.setProperty(2, new Variant(fontData[0].getHeight()));
                automation.setProperty(4, new Variant(fontData[0].getStyle() & 0x2));
                automation.setProperty(3, new Variant(fontData[0].getStyle() & 0x1));
                automation.dispose();
            }
        }
        this.font = font;
    }
    
    public void setForeground(final Color foreground) {
        super.setForeground(foreground);
        if (this.objIUnknown != null) {
            final OleAutomation oleAutomation = new OleAutomation(this);
            oleAutomation.setProperty(-513, new Variant(foreground.handle));
            oleAutomation.dispose();
        }
    }
    
    public void setSiteProperty(final int n, final Variant variant) {
        for (int i = 0; i < this.sitePropertyIds.length; ++i) {
            if (this.sitePropertyIds[i] == n) {
                if (this.sitePropertyValues[i] != null) {
                    this.sitePropertyValues[i].dispose();
                }
                if (variant != null) {
                    this.sitePropertyValues[i] = variant;
                }
                else {
                    final int length = this.sitePropertyIds.length;
                    final int[] sitePropertyIds = new int[length - 1];
                    final Variant[] sitePropertyValues = new Variant[length - 1];
                    System.arraycopy(this.sitePropertyIds, 0, sitePropertyIds, 0, i);
                    System.arraycopy(this.sitePropertyIds, i + 1, sitePropertyIds, i, length - i - 1);
                    System.arraycopy(this.sitePropertyValues, 0, sitePropertyValues, 0, i);
                    System.arraycopy(this.sitePropertyValues, i + 1, sitePropertyValues, i, length - i - 1);
                    this.sitePropertyIds = sitePropertyIds;
                    this.sitePropertyValues = sitePropertyValues;
                }
                return;
            }
        }
        final int length2 = this.sitePropertyIds.length;
        final int[] sitePropertyIds2 = new int[length2 + 1];
        final Variant[] sitePropertyValues2 = new Variant[length2 + 1];
        System.arraycopy(this.sitePropertyIds, 0, sitePropertyIds2, 0, length2);
        System.arraycopy(this.sitePropertyValues, 0, sitePropertyValues2, 0, length2);
        sitePropertyIds2[length2] = n;
        sitePropertyValues2[length2] = variant;
        this.sitePropertyIds = sitePropertyIds2;
        this.sitePropertyValues = sitePropertyValues2;
    }
}
