// 
// Decompiled by Procyon v0.5.30
// 

package org.eclipse.swt.browser;

import org.eclipse.swt.SWTException;
import org.eclipse.swt.internal.mozilla.nsIWritableVariant;
import org.eclipse.swt.internal.mozilla.nsIComponentManager;
import org.eclipse.swt.SWT;
import org.eclipse.swt.internal.mozilla.nsIVariant;
import org.eclipse.swt.internal.mozilla.nsIMemory;
import org.eclipse.swt.internal.mozilla.nsIServiceManager;
import org.eclipse.swt.internal.mozilla.nsISecurityCheckedComponent;
import org.eclipse.swt.internal.mozilla.nsIClassInfo;
import org.eclipse.swt.internal.C;
import org.eclipse.swt.internal.mozilla.nsISupports;
import org.eclipse.swt.internal.mozilla.XPCOM;
import org.eclipse.swt.internal.mozilla.XPCOMObject;
import org.eclipse.swt.internal.mozilla.nsID;

class External
{
    public static final String EXTERNAL_IID_STR = "ded01d20-ba6f-11dd-ad8b-0800200c9a66";
    public static final nsID EXTERNAL_IID;
    XPCOMObject supports;
    XPCOMObject external;
    XPCOMObject classInfo;
    XPCOMObject securityCheckedComponent;
    int refCount;
    
    static {
        EXTERNAL_IID = new nsID("ded01d20-ba6f-11dd-ad8b-0800200c9a66");
    }
    
    External() {
        this.refCount = 0;
        this.createCOMInterfaces();
    }
    
    int AddRef() {
        return ++this.refCount;
    }
    
    void createCOMInterfaces() {
        this.supports = new XPCOMObject(new int[] { 2, 0, 0 }) {
            public int method0(final int[] array) {
                return External.this.QueryInterface(array[0], array[1]);
            }
            
            public int method1(final int[] array) {
                return External.this.AddRef();
            }
            
            public int method2(final int[] array) {
                return External.this.Release();
            }
        };
        this.classInfo = new XPCOMObject(new int[] { 2, 0, 0, 2, 2, 1, 1, 1, 1, 1, 1 }) {
            public int method0(final int[] array) {
                return External.this.QueryInterface(array[0], array[1]);
            }
            
            public int method1(final int[] array) {
                return External.this.AddRef();
            }
            
            public int method2(final int[] array) {
                return External.this.Release();
            }
            
            public int method3(final int[] array) {
                return External.this.getInterfaces(array[0], array[1]);
            }
            
            public int method4(final int[] array) {
                return External.this.getHelperForLanguage(array[0], array[1]);
            }
            
            public int method5(final int[] array) {
                return External.this.getContractID(array[0]);
            }
            
            public int method6(final int[] array) {
                return External.this.getClassDescription(array[0]);
            }
            
            public int method7(final int[] array) {
                return External.this.getClassID(array[0]);
            }
            
            public int method8(final int[] array) {
                return External.this.getImplementationLanguage(array[0]);
            }
            
            public int method9(final int[] array) {
                return External.this.getFlags(array[0]);
            }
            
            public int method10(final int[] array) {
                return External.this.getClassIDNoAlloc(array[0]);
            }
        };
        this.securityCheckedComponent = new XPCOMObject(new int[] { 2, 0, 0, 2, 3, 3, 3 }) {
            public int method0(final int[] array) {
                return External.this.QueryInterface(array[0], array[1]);
            }
            
            public int method1(final int[] array) {
                return External.this.AddRef();
            }
            
            public int method2(final int[] array) {
                return External.this.Release();
            }
            
            public int method3(final int[] array) {
                return External.this.canCreateWrapper(array[0], array[1]);
            }
            
            public int method4(final int[] array) {
                return External.this.canCallMethod(array[0], array[1], array[2]);
            }
            
            public int method5(final int[] array) {
                return External.this.canGetProperty(array[0], array[1], array[2]);
            }
            
            public int method6(final int[] array) {
                return External.this.canSetProperty(array[0], array[1], array[2]);
            }
        };
        this.external = new XPCOMObject(new int[] { 2, 0, 0, 3 }) {
            public int method0(final int[] array) {
                return External.this.QueryInterface(array[0], array[1]);
            }
            
            public int method1(final int[] array) {
                return External.this.AddRef();
            }
            
            public int method2(final int[] array) {
                return External.this.Release();
            }
            
            public int method3(final int[] array) {
                return External.this.callJava(array[0], array[1], array[2]);
            }
        };
    }
    
    void disposeCOMInterfaces() {
        if (this.supports != null) {
            this.supports.dispose();
            this.supports = null;
        }
        if (this.external != null) {
            this.external.dispose();
            this.external = null;
        }
    }
    
    int getAddress() {
        return this.external.getAddress();
    }
    
    int QueryInterface(final int n, final int n2) {
        if (n == 0 || n2 == 0) {
            return -2147467262;
        }
        final nsID nsID = new nsID();
        XPCOM.memmove(nsID, n, 16);
        if (nsID.Equals(nsISupports.NS_ISUPPORTS_IID)) {
            C.memmove(n2, new int[] { this.supports.getAddress() }, C.PTR_SIZEOF);
            this.AddRef();
            return 0;
        }
        if (nsID.Equals(nsIClassInfo.NS_ICLASSINFO_IID)) {
            C.memmove(n2, new int[] { this.classInfo.getAddress() }, C.PTR_SIZEOF);
            this.AddRef();
            return 0;
        }
        if (nsID.Equals(nsISecurityCheckedComponent.NS_ISECURITYCHECKEDCOMPONENT_IID)) {
            C.memmove(n2, new int[] { this.securityCheckedComponent.getAddress() }, C.PTR_SIZEOF);
            this.AddRef();
            return 0;
        }
        if (nsID.Equals(External.EXTERNAL_IID)) {
            C.memmove(n2, new int[] { this.external.getAddress() }, C.PTR_SIZEOF);
            this.AddRef();
            return 0;
        }
        C.memmove(n2, new int[1], C.PTR_SIZEOF);
        return -2147467262;
    }
    
    int Release() {
        --this.refCount;
        if (this.refCount == 0) {
            this.disposeCOMInterfaces();
        }
        return this.refCount;
    }
    
    int getClassDescription(final int n) {
        final int[] array = { 0 };
        final int ns_GetServiceManager = XPCOM.NS_GetServiceManager(array);
        if (ns_GetServiceManager != 0) {
            Mozilla.error(ns_GetServiceManager);
        }
        if (array[0] == 0) {
            Mozilla.error(-2147467262);
        }
        final nsIServiceManager nsIServiceManager = new nsIServiceManager(array[0]);
        array[0] = 0;
        final int getServiceByContractID = nsIServiceManager.GetServiceByContractID(MozillaDelegate.wcsToMbcs(null, "@mozilla.org/xpcom/memory-service;1", true), nsIMemory.NS_IMEMORY_IID, array);
        if (getServiceByContractID != 0) {
            Mozilla.error(getServiceByContractID);
        }
        if (array[0] == 0) {
            Mozilla.error(-2147467262);
        }
        nsIServiceManager.Release();
        final nsIMemory nsIMemory = new nsIMemory(array[0]);
        array[0] = 0;
        final byte[] wcsToMbcs = MozillaDelegate.wcsToMbcs(null, "external", true);
        final int alloc = nsIMemory.Alloc(wcsToMbcs.length);
        C.memmove(alloc, wcsToMbcs, wcsToMbcs.length);
        C.memmove(n, new int[] { alloc }, C.PTR_SIZEOF);
        nsIMemory.Release();
        return 0;
    }
    
    int getClassID(final int n) {
        return 0;
    }
    
    int getClassIDNoAlloc(final int n) {
        return 0;
    }
    
    int getContractID(final int n) {
        return 0;
    }
    
    int getFlags(final int n) {
        C.memmove(n, new int[] { 4 }, 4);
        return 0;
    }
    
    int getHelperForLanguage(final int n, final int n2) {
        C.memmove(n2, new int[1], C.PTR_SIZEOF);
        return 0;
    }
    
    int getImplementationLanguage(final int n) {
        C.memmove(n, new int[] { 5 }, 4);
        return 0;
    }
    
    int getInterfaces(final int n, final int n2) {
        final int[] array = { 0 };
        final int ns_GetServiceManager = XPCOM.NS_GetServiceManager(array);
        if (ns_GetServiceManager != 0) {
            Mozilla.error(ns_GetServiceManager);
        }
        if (array[0] == 0) {
            Mozilla.error(-2147467262);
        }
        final nsIServiceManager nsIServiceManager = new nsIServiceManager(array[0]);
        array[0] = 0;
        final int getServiceByContractID = nsIServiceManager.GetServiceByContractID(MozillaDelegate.wcsToMbcs(null, "@mozilla.org/xpcom/memory-service;1", true), nsIMemory.NS_IMEMORY_IID, array);
        if (getServiceByContractID != 0) {
            Mozilla.error(getServiceByContractID);
        }
        if (array[0] == 0) {
            Mozilla.error(-2147467262);
        }
        nsIServiceManager.Release();
        final nsIMemory nsIMemory = new nsIMemory(array[0]);
        array[0] = 0;
        final int alloc = nsIMemory.Alloc(16);
        XPCOM.memmove(alloc, nsISecurityCheckedComponent.NS_ISECURITYCHECKEDCOMPONENT_IID, 16);
        final int alloc2 = nsIMemory.Alloc(16);
        XPCOM.memmove(alloc2, External.EXTERNAL_IID, 16);
        final int alloc3 = nsIMemory.Alloc(2 * C.PTR_SIZEOF);
        C.memmove(alloc3, new int[] { alloc }, C.PTR_SIZEOF);
        C.memmove(alloc3 + C.PTR_SIZEOF, new int[] { alloc2 }, C.PTR_SIZEOF);
        C.memmove(n2, new int[] { alloc3 }, C.PTR_SIZEOF);
        nsIMemory.Release();
        C.memmove(n, new int[] { 2 }, 4);
        return 0;
    }
    
    int canCreateWrapper(final int n, final int n2) {
        final int[] array = { 0 };
        final int ns_GetServiceManager = XPCOM.NS_GetServiceManager(array);
        if (ns_GetServiceManager != 0) {
            Mozilla.error(ns_GetServiceManager);
        }
        if (array[0] == 0) {
            Mozilla.error(-2147467262);
        }
        final nsIServiceManager nsIServiceManager = new nsIServiceManager(array[0]);
        array[0] = 0;
        final int getServiceByContractID = nsIServiceManager.GetServiceByContractID(MozillaDelegate.wcsToMbcs(null, "@mozilla.org/xpcom/memory-service;1", true), nsIMemory.NS_IMEMORY_IID, array);
        if (getServiceByContractID != 0) {
            Mozilla.error(getServiceByContractID);
        }
        if (array[0] == 0) {
            Mozilla.error(-2147467262);
        }
        nsIServiceManager.Release();
        final nsIMemory nsIMemory = new nsIMemory(array[0]);
        array[0] = 0;
        final byte[] wcsToMbcs = MozillaDelegate.wcsToMbcs(null, "allAccess", true);
        final int alloc = nsIMemory.Alloc(wcsToMbcs.length);
        C.memmove(alloc, wcsToMbcs, wcsToMbcs.length);
        C.memmove(n2, new int[] { alloc }, C.PTR_SIZEOF);
        nsIMemory.Release();
        return 0;
    }
    
    int canCallMethod(final int n, final int n2, final int n3) {
        final int[] array = { 0 };
        final int ns_GetServiceManager = XPCOM.NS_GetServiceManager(array);
        if (ns_GetServiceManager != 0) {
            Mozilla.error(ns_GetServiceManager);
        }
        if (array[0] == 0) {
            Mozilla.error(-2147467262);
        }
        final nsIServiceManager nsIServiceManager = new nsIServiceManager(array[0]);
        array[0] = 0;
        final int getServiceByContractID = nsIServiceManager.GetServiceByContractID(MozillaDelegate.wcsToMbcs(null, "@mozilla.org/xpcom/memory-service;1", true), nsIMemory.NS_IMEMORY_IID, array);
        if (getServiceByContractID != 0) {
            Mozilla.error(getServiceByContractID);
        }
        if (array[0] == 0) {
            Mozilla.error(-2147467262);
        }
        nsIServiceManager.Release();
        final nsIMemory nsIMemory = new nsIMemory(array[0]);
        array[0] = 0;
        final int strlen_PRUnichar = XPCOM.strlen_PRUnichar(n2);
        final char[] array2 = new char[strlen_PRUnichar];
        C.memmove(array2, n2, strlen_PRUnichar * 2);
        byte[] array3;
        if (new String(array2).equals("callJava")) {
            array3 = MozillaDelegate.wcsToMbcs(null, "allAccess", true);
        }
        else {
            array3 = MozillaDelegate.wcsToMbcs(null, "noAccess", true);
        }
        final int alloc = nsIMemory.Alloc(array3.length);
        C.memmove(alloc, array3, array3.length);
        C.memmove(n3, new int[] { alloc }, C.PTR_SIZEOF);
        nsIMemory.Release();
        return 0;
    }
    
    int canGetProperty(final int n, final int n2, final int n3) {
        final int[] array = { 0 };
        final int ns_GetServiceManager = XPCOM.NS_GetServiceManager(array);
        if (ns_GetServiceManager != 0) {
            Mozilla.error(ns_GetServiceManager);
        }
        if (array[0] == 0) {
            Mozilla.error(-2147467262);
        }
        final nsIServiceManager nsIServiceManager = new nsIServiceManager(array[0]);
        array[0] = 0;
        final int getServiceByContractID = nsIServiceManager.GetServiceByContractID(MozillaDelegate.wcsToMbcs(null, "@mozilla.org/xpcom/memory-service;1", true), nsIMemory.NS_IMEMORY_IID, array);
        if (getServiceByContractID != 0) {
            Mozilla.error(getServiceByContractID);
        }
        if (array[0] == 0) {
            Mozilla.error(-2147467262);
        }
        nsIServiceManager.Release();
        final nsIMemory nsIMemory = new nsIMemory(array[0]);
        array[0] = 0;
        final byte[] wcsToMbcs = MozillaDelegate.wcsToMbcs(null, "noAccess", true);
        final int alloc = nsIMemory.Alloc(wcsToMbcs.length);
        C.memmove(alloc, wcsToMbcs, wcsToMbcs.length);
        C.memmove(n3, new int[] { alloc }, C.PTR_SIZEOF);
        nsIMemory.Release();
        return 0;
    }
    
    int canSetProperty(final int n, final int n2, final int n3) {
        final int[] array = { 0 };
        final int ns_GetServiceManager = XPCOM.NS_GetServiceManager(array);
        if (ns_GetServiceManager != 0) {
            Mozilla.error(ns_GetServiceManager);
        }
        if (array[0] == 0) {
            Mozilla.error(-2147467262);
        }
        final nsIServiceManager nsIServiceManager = new nsIServiceManager(array[0]);
        array[0] = 0;
        final int getServiceByContractID = nsIServiceManager.GetServiceByContractID(MozillaDelegate.wcsToMbcs(null, "@mozilla.org/xpcom/memory-service;1", true), nsIMemory.NS_IMEMORY_IID, array);
        if (getServiceByContractID != 0) {
            Mozilla.error(getServiceByContractID);
        }
        if (array[0] == 0) {
            Mozilla.error(-2147467262);
        }
        nsIServiceManager.Release();
        final nsIMemory nsIMemory = new nsIMemory(array[0]);
        array[0] = 0;
        final byte[] wcsToMbcs = MozillaDelegate.wcsToMbcs(null, "noAccess", true);
        final int alloc = nsIMemory.Alloc(wcsToMbcs.length);
        C.memmove(alloc, wcsToMbcs, wcsToMbcs.length);
        C.memmove(n3, new int[] { alloc }, C.PTR_SIZEOF);
        nsIMemory.Release();
        return 0;
    }
    
    Object convertToJava(final nsIVariant nsIVariant, final short n) {
        switch (n) {
            case 13:
            case 255: {
                return null;
            }
            case 254: {
                return new Object[0];
            }
            case 10: {
                final int[] array = { 0 };
                final int getAsBool = nsIVariant.GetAsBool(array);
                if (getAsBool != 0) {
                    Mozilla.error(getAsBool);
                }
                return new Boolean(array[0] != 0);
            }
            case 2: {
                final int[] array2 = { 0 };
                final int getAsInt32 = nsIVariant.GetAsInt32(array2);
                if (getAsInt32 != 0) {
                    Mozilla.error(getAsInt32);
                }
                return new Double(array2[0]);
            }
            case 9: {
                final int malloc = C.malloc(8);
                final int getAsDouble = nsIVariant.GetAsDouble(malloc);
                if (getAsDouble != 0) {
                    Mozilla.error(getAsDouble);
                }
                final double[] array3 = { 0.0 };
                C.memmove(array3, malloc, 8);
                C.free(malloc);
                return new Double(array3[0]);
            }
            case 22: {
                final int[] array4 = { 0 };
                final int[] array5 = { 0 };
                final int getAsWStringWithSize = nsIVariant.GetAsWStringWithSize(array4, array5);
                if (getAsWStringWithSize != 0) {
                    Mozilla.error(getAsWStringWithSize);
                }
                final char[] array6 = new char[array4[0]];
                C.memmove(array6, array5[0], array4[0] * 2);
                return new String(array6);
            }
            case 20: {
                Object[] array7 = new Object[0];
                final int malloc2 = C.malloc(16);
                C.memset(malloc2, 0, 16);
                final int[] array8 = { 0 };
                final short[] array9 = { 0 };
                final int[] array10 = { 0 };
                final int getAsArray = nsIVariant.GetAsArray(array9, malloc2, array8, array10);
                if (getAsArray != 0) {
                    Mozilla.error(getAsArray);
                }
                if (array10[0] == 0) {
                    Mozilla.error(-2147467261);
                }
                final nsID nsID = new nsID();
                XPCOM.memmove(nsID, malloc2, 16);
                C.free(malloc2);
                final int[] array11 = { 0 };
                final int ns_GetServiceManager = XPCOM.NS_GetServiceManager(array11);
                if (ns_GetServiceManager != 0) {
                    Mozilla.error(ns_GetServiceManager);
                }
                if (array11[0] == 0) {
                    Mozilla.error(-2147467262);
                }
                final nsIServiceManager nsIServiceManager = new nsIServiceManager(array11[0]);
                array11[0] = 0;
                final int getServiceByContractID = nsIServiceManager.GetServiceByContractID(MozillaDelegate.wcsToMbcs(null, "@mozilla.org/xpcom/memory-service;1", true), nsIMemory.NS_IMEMORY_IID, array11);
                if (getServiceByContractID != 0) {
                    Mozilla.error(getServiceByContractID);
                }
                if (array11[0] == 0) {
                    Mozilla.error(-2147467262);
                }
                nsIServiceManager.Release();
                final nsIMemory nsIMemory = new nsIMemory(array11[0]);
                array11[0] = 0;
                if (nsID.Equals(nsIVariant.NS_IVARIANT_IID)) {
                    array7 = new Object[array8[0]];
                    for (int i = 0; i < array8[0]; ++i) {
                        final int[] array12 = { 0 };
                        C.memmove(array12, array10[0] + i * C.PTR_SIZEOF, C.PTR_SIZEOF);
                        final int queryInterface = new nsISupports(array12[0]).QueryInterface(nsIVariant.NS_IVARIANT_IID, array11);
                        if (queryInterface != 0) {
                            Mozilla.error(queryInterface);
                        }
                        if (array11[0] == 0) {
                            Mozilla.error(-2147467262);
                        }
                        final nsIVariant nsIVariant2 = new nsIVariant(array11[0]);
                        array9[array11[0] = 0] = 0;
                        final int getDataType = nsIVariant2.GetDataType(array9);
                        if (getDataType != 0) {
                            Mozilla.error(getDataType);
                        }
                        try {
                            array7[i] = this.convertToJava(nsIVariant2, array9[0]);
                            nsIVariant2.Release();
                        }
                        catch (IllegalArgumentException ex) {
                            nsIVariant2.Release();
                            nsIMemory.Free(array10[0]);
                            nsIMemory.Release();
                            throw ex;
                        }
                    }
                }
                else {
                    switch (array9[0]) {
                        case 9: {
                            array7 = new Object[array8[0]];
                            for (int j = 0; j < array8[0]; ++j) {
                                final double[] array13 = { 0.0 };
                                C.memmove(array13, array10[0] + j * 8, 8);
                                array7[j] = new Double(array13[0]);
                            }
                            break;
                        }
                        case 10: {
                            array7 = new Object[array8[0]];
                            for (int k = 0; k < array8[0]; ++k) {
                                final int[] array14 = { 0 };
                                C.memmove(array14, array10[0] + k * 4, 4);
                                array7[k] = new Boolean(array14[0] != 0);
                            }
                            break;
                        }
                        case 2: {
                            array7 = new Object[array8[0]];
                            for (int l = 0; l < array8[0]; ++l) {
                                final int[] array15 = { 0 };
                                C.memmove(array15, array10[0] + l * 4, 4);
                                array7[l] = new Double(array15[0]);
                            }
                            break;
                        }
                        case 17: {
                            array7 = new Object[array8[0]];
                            for (int n2 = 0; n2 < array8[0]; ++n2) {
                                final int n3 = array10[0] + n2 * C.PTR_SIZEOF;
                                final int[] array16 = { 0 };
                                C.memmove(array16, n3, C.PTR_SIZEOF);
                                final int strlen_PRUnichar = XPCOM.strlen_PRUnichar(array16[0]);
                                final char[] array17 = new char[strlen_PRUnichar];
                                C.memmove(array17, array16[0], strlen_PRUnichar * 2);
                                array7[n2] = new String(array17);
                            }
                            break;
                        }
                        default: {
                            nsIMemory.Free(array10[0]);
                            nsIMemory.Release();
                            SWT.error(5);
                            break;
                        }
                    }
                }
                nsIMemory.Free(array10[0]);
                nsIMemory.Release();
                return array7;
            }
            default: {
                SWT.error(5);
                return null;
            }
        }
    }
    
    nsIVariant convertToJS(final Object o, final nsIComponentManager nsIComponentManager) {
        final int[] array = { 0 };
        nsIComponentManager.CreateInstanceByContractID(MozillaDelegate.wcsToMbcs(null, "@mozilla.org/variant;1", true), 0, nsIWritableVariant.NS_IWRITABLEVARIANT_IID, array);
        final nsIWritableVariant nsIWritableVariant = new nsIWritableVariant(array[0]);
        array[0] = 0;
        if (o == null) {
            final int setAsEmpty = nsIWritableVariant.SetAsEmpty();
            if (setAsEmpty != 0) {
                Mozilla.error(setAsEmpty);
            }
            return nsIWritableVariant;
        }
        if (o instanceof String) {
            final String s = (String)o;
            final int length = s.length();
            final char[] array2 = new char[length];
            s.getChars(0, length, array2, 0);
            final int setAsWStringWithSize = nsIWritableVariant.SetAsWStringWithSize(length, array2);
            if (setAsWStringWithSize != 0) {
                Mozilla.error(setAsWStringWithSize);
            }
            return nsIWritableVariant;
        }
        if (o instanceof Boolean) {
            final int setAsBool = nsIWritableVariant.SetAsBool(((boolean)o) ? 1 : 0);
            if (setAsBool != 0) {
                Mozilla.error(setAsBool);
            }
            return nsIWritableVariant;
        }
        if (o instanceof Number) {
            final int setAsDouble = nsIWritableVariant.SetAsDouble(((Number)o).doubleValue());
            if (setAsDouble != 0) {
                Mozilla.error(setAsDouble);
            }
            return nsIWritableVariant;
        }
        if (o instanceof Object[]) {
            final Object[] array3 = (Object[])o;
            final int length2 = array3.length;
            if (length2 == 0) {
                final int setAsEmptyArray = nsIWritableVariant.SetAsEmptyArray();
                if (setAsEmptyArray != 0) {
                    Mozilla.error(setAsEmptyArray);
                }
            }
            else {
                final int malloc = C.malloc(C.PTR_SIZEOF * length2);
                for (int i = 0; i < length2; ++i) {
                    final Object o2 = array3[i];
                    try {
                        C.memmove(malloc + C.PTR_SIZEOF * i, new int[] { this.convertToJS(o2, nsIComponentManager).getAddress() }, C.PTR_SIZEOF);
                    }
                    catch (SWTException ex) {
                        C.free(malloc);
                        nsIWritableVariant.Release();
                        for (int j = 0; j < i; ++j) {
                            final int[] array4 = { 0 };
                            C.memmove(array4, malloc + C.PTR_SIZEOF * j, C.PTR_SIZEOF);
                            new nsISupports(array4[0]).Release();
                        }
                        throw ex;
                    }
                }
                final int malloc2 = C.malloc(16);
                XPCOM.memmove(malloc2, nsIVariant.NS_IVARIANT_IID, 16);
                final int setAsArray = nsIWritableVariant.SetAsArray((short)19, malloc2, length2, malloc);
                C.free(malloc2);
                C.free(malloc);
                if (setAsArray != 0) {
                    Mozilla.error(setAsArray);
                }
            }
            return nsIWritableVariant;
        }
        nsIWritableVariant.Release();
        SWT.error(51);
        return null;
    }
    
    int callJava(final int n, final int n2, final int n3) {
        final BrowserFunction browserFunction = Mozilla.AllFunctions.get(new Integer(n));
        Object o = null;
        if (browserFunction != null) {
            final short[] array = { 0 };
            final nsIVariant nsIVariant = new nsIVariant(n2);
            final int getDataType = nsIVariant.GetDataType(array);
            if (getDataType != 0) {
                Mozilla.error(getDataType);
            }
            try {
                final Object[] array2 = (Object[])this.convertToJava(nsIVariant, array[0]);
                if (array2 instanceof Object[]) {
                    final Object[] array3 = array2;
                    try {
                        o = browserFunction.function(array3);
                    }
                    catch (Exception ex) {
                        o = WebBrowser.CreateErrorString(ex.getLocalizedMessage());
                    }
                }
            }
            catch (IllegalArgumentException ex2) {
                if (browserFunction.isEvaluate) {
                    browserFunction.function(new String[] { WebBrowser.CreateErrorString(new SWTException(51).getLocalizedMessage()) });
                }
                o = WebBrowser.CreateErrorString(ex2.getLocalizedMessage());
            }
        }
        final int[] array4 = { 0 };
        final int ns_GetComponentManager = XPCOM.NS_GetComponentManager(array4);
        if (ns_GetComponentManager != 0) {
            Mozilla.error(ns_GetComponentManager);
        }
        if (array4[0] == 0) {
            Mozilla.error(-2147467262);
        }
        final nsIComponentManager nsIComponentManager = new nsIComponentManager(array4[0]);
        array4[0] = 0;
        nsIVariant nsIVariant2;
        try {
            nsIVariant2 = this.convertToJS(o, nsIComponentManager);
        }
        catch (SWTException ex3) {
            nsIVariant2 = this.convertToJS(WebBrowser.CreateErrorString(ex3.getLocalizedMessage()), nsIComponentManager);
        }
        nsIComponentManager.Release();
        C.memmove(n3, new int[] { nsIVariant2.getAddress() }, C.PTR_SIZEOF);
        return 0;
    }
}
