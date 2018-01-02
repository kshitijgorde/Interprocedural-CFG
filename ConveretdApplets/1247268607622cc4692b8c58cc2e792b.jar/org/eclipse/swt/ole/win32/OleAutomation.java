// 
// Decompiled by Procyon v0.5.30
// 

package org.eclipse.swt.ole.win32;

import org.eclipse.swt.internal.ole.win32.EXCEPINFO;
import org.eclipse.swt.internal.ole.win32.VARIANT;
import org.eclipse.swt.internal.ole.win32.DISPPARAMS;
import org.eclipse.swt.internal.ole.win32.TYPEATTR;
import org.eclipse.swt.internal.ole.win32.FUNCDESC;
import org.eclipse.swt.internal.ole.win32.VARDESC;
import org.eclipse.swt.internal.ole.win32.GUID;
import org.eclipse.swt.SWTException;
import org.eclipse.swt.internal.ole.win32.COM;
import org.eclipse.swt.internal.win32.OS;
import org.eclipse.swt.internal.ole.win32.ITypeInfo;
import org.eclipse.swt.internal.ole.win32.IDispatch;
import org.eclipse.swt.internal.ole.win32.IUnknown;

public final class OleAutomation
{
    private IUnknown objIUnknown;
    private IDispatch objIDispatch;
    private String exceptionDescription;
    private ITypeInfo objITypeInfo;
    
    OleAutomation(final IDispatch objIDispatch) {
        if (objIDispatch == null) {
            OLE.error(1011);
        }
        (this.objIDispatch = objIDispatch).AddRef();
        final int[] array = { 0 };
        if (this.objIDispatch.GetTypeInfo(0, 2048, array) == 0) {
            this.objITypeInfo = new ITypeInfo(array[0]);
        }
    }
    
    public OleAutomation(final OleClientSite oleClientSite) {
        if (oleClientSite == null) {
            OLE.error(1011);
        }
        this.objIDispatch = oleClientSite.getAutomationObject();
        final int[] array = { 0 };
        if (this.objIDispatch.GetTypeInfo(0, 2048, array) == 0) {
            this.objITypeInfo = new ITypeInfo(array[0]);
        }
    }
    
    public OleAutomation(final String s) {
        try {
            OS.OleInitialize(0);
            final GUID classID = this.getClassID(s);
            if (classID == null) {
                OS.OleUninitialize();
                OLE.error(1004);
            }
            int n = 1;
            if (s.startsWith("Excel")) {
                n |= 0x4;
            }
            final int[] array = { 0 };
            final int coCreateInstance = COM.CoCreateInstance(classID, 0, n, COM.IIDIUnknown, array);
            if (coCreateInstance != 0) {
                OS.OleUninitialize();
                OLE.error(1001, coCreateInstance);
            }
            this.objIUnknown = new IUnknown(array[0]);
            array[0] = 0;
            if (this.objIUnknown.QueryInterface(COM.IIDIDispatch, array) != 0) {
                OLE.error(1003);
            }
            this.objIDispatch = new IDispatch(array[0]);
            array[0] = 0;
            if (this.objIDispatch.GetTypeInfo(0, 2048, array) == 0) {
                this.objITypeInfo = new ITypeInfo(array[0]);
            }
        }
        catch (SWTException ex) {
            this.dispose();
            throw ex;
        }
    }
    
    public void dispose() {
        if (this.objIDispatch != null) {
            this.objIDispatch.Release();
        }
        this.objIDispatch = null;
        if (this.objITypeInfo != null) {
            this.objITypeInfo.Release();
        }
        this.objITypeInfo = null;
        if (this.objIUnknown != null) {
            this.objIUnknown.Release();
            OS.OleUninitialize();
        }
        this.objIUnknown = null;
    }
    
    int getAddress() {
        return this.objIDispatch.getAddress();
    }
    
    GUID getClassID(final String s) {
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
    
    public String getHelpFile(final int n) {
        if (this.objITypeInfo == null) {
            return null;
        }
        final String[] array = { null };
        if (this.objITypeInfo.GetDocumentation(n, null, null, null, array) == 0) {
            return array[0];
        }
        return null;
    }
    
    public String getDocumentation(final int n) {
        if (this.objITypeInfo == null) {
            return null;
        }
        final String[] array = { null };
        if (this.objITypeInfo.GetDocumentation(n, null, array, null, null) == 0) {
            return array[0];
        }
        return null;
    }
    
    public OlePropertyDescription getPropertyDescription(final int n) {
        if (this.objITypeInfo == null) {
            return null;
        }
        final int[] array = { 0 };
        if (this.objITypeInfo.GetVarDesc(n, array) != 0) {
            return null;
        }
        final VARDESC vardesc = new VARDESC();
        COM.MoveMemory(vardesc, array[0], VARDESC.sizeof);
        final OlePropertyDescription olePropertyDescription = new OlePropertyDescription();
        olePropertyDescription.id = vardesc.memid;
        olePropertyDescription.name = this.getName(vardesc.memid);
        olePropertyDescription.type = vardesc.elemdescVar_tdesc_vt;
        if (olePropertyDescription.type == 26) {
            final short[] array2 = { 0 };
            OS.MoveMemory(array2, vardesc.elemdescVar_tdesc_union + OS.PTR_SIZEOF, 2);
            olePropertyDescription.type = array2[0];
        }
        olePropertyDescription.flags = vardesc.wVarFlags;
        olePropertyDescription.kind = vardesc.varkind;
        olePropertyDescription.description = this.getDocumentation(vardesc.memid);
        olePropertyDescription.helpFile = this.getHelpFile(vardesc.memid);
        this.objITypeInfo.ReleaseVarDesc(array[0]);
        return olePropertyDescription;
    }
    
    public OleFunctionDescription getFunctionDescription(final int n) {
        if (this.objITypeInfo == null) {
            return null;
        }
        final int[] array = { 0 };
        if (this.objITypeInfo.GetFuncDesc(n, array) != 0) {
            return null;
        }
        final FUNCDESC funcdesc = new FUNCDESC();
        COM.MoveMemory(funcdesc, array[0], FUNCDESC.sizeof);
        final OleFunctionDescription oleFunctionDescription = new OleFunctionDescription();
        oleFunctionDescription.id = funcdesc.memid;
        oleFunctionDescription.optionalArgCount = funcdesc.cParamsOpt;
        oleFunctionDescription.invokeKind = funcdesc.invkind;
        oleFunctionDescription.funcKind = funcdesc.funckind;
        oleFunctionDescription.flags = funcdesc.wFuncFlags;
        oleFunctionDescription.callingConvention = funcdesc.callconv;
        oleFunctionDescription.documentation = this.getDocumentation(funcdesc.memid);
        oleFunctionDescription.helpFile = this.getHelpFile(funcdesc.memid);
        final String[] names = this.getNames(funcdesc.memid, funcdesc.cParams + 1);
        if (names.length > 0) {
            oleFunctionDescription.name = names[0];
        }
        oleFunctionDescription.args = new OleParameterDescription[funcdesc.cParams];
        for (int i = 0; i < oleFunctionDescription.args.length; ++i) {
            oleFunctionDescription.args[i] = new OleParameterDescription();
            if (names.length > i + 1) {
                oleFunctionDescription.args[i].name = names[i + 1];
            }
            final short[] array2 = { 0 };
            OS.MoveMemory(array2, funcdesc.lprgelemdescParam + i * COM.ELEMDESC_sizeof() + OS.PTR_SIZEOF, 2);
            if (array2[0] == 26) {
                final int[] array3 = { 0 };
                OS.MoveMemory(array3, funcdesc.lprgelemdescParam + i * COM.ELEMDESC_sizeof(), OS.PTR_SIZEOF);
                final short[] array4 = { 0 };
                OS.MoveMemory(array4, array3[0] + OS.PTR_SIZEOF, 2);
                array2[0] = (short)(array4[0] | 0x4000);
            }
            oleFunctionDescription.args[i].type = array2[0];
            final short[] array5 = { 0 };
            OS.MoveMemory(array5, funcdesc.lprgelemdescParam + i * COM.ELEMDESC_sizeof() + COM.TYPEDESC_sizeof() + OS.PTR_SIZEOF, 2);
            oleFunctionDescription.args[i].flags = array5[0];
        }
        oleFunctionDescription.returnType = funcdesc.elemdescFunc_tdesc_vt;
        if (oleFunctionDescription.returnType == 26) {
            final short[] array6 = { 0 };
            OS.MoveMemory(array6, funcdesc.elemdescFunc_tdesc_union + OS.PTR_SIZEOF, 2);
            oleFunctionDescription.returnType = array6[0];
        }
        this.objITypeInfo.ReleaseFuncDesc(array[0]);
        return oleFunctionDescription;
    }
    
    public TYPEATTR getTypeInfoAttributes() {
        if (this.objITypeInfo == null) {
            return null;
        }
        final int[] array = { 0 };
        if (this.objITypeInfo.GetTypeAttr(array) != 0) {
            return null;
        }
        final TYPEATTR typeattr = new TYPEATTR();
        COM.MoveMemory(typeattr, array[0], TYPEATTR.sizeof);
        this.objITypeInfo.ReleaseTypeAttr(array[0]);
        return typeattr;
    }
    
    public String getName(final int n) {
        if (this.objITypeInfo == null) {
            return null;
        }
        final String[] array = { null };
        if (this.objITypeInfo.GetDocumentation(n, array, null, null, null) == 0) {
            return array[0];
        }
        return null;
    }
    
    public String[] getNames(final int n, final int n2) {
        if (this.objITypeInfo == null) {
            return new String[0];
        }
        final String[] array = new String[n2];
        final int[] array2 = { 0 };
        if (this.objITypeInfo.GetNames(n, array, n2, array2) == 0) {
            final String[] array3 = new String[array2[0]];
            System.arraycopy(array, 0, array3, 0, array2[0]);
            return array3;
        }
        return new String[0];
    }
    
    public int[] getIDsOfNames(final String[] array) {
        final int[] array2 = new int[array.length];
        if (this.objIDispatch.GetIDsOfNames(new GUID(), array, array.length, 2048, array2) != 0) {
            return null;
        }
        return array2;
    }
    
    public String getLastError() {
        return this.exceptionDescription;
    }
    
    public Variant getProperty(final int n) {
        final Variant variant = new Variant();
        return (this.invoke(n, 2, null, null, variant) == 0) ? variant : null;
    }
    
    public Variant getProperty(final int n, final Variant[] array) {
        final Variant variant = new Variant();
        return (this.invoke(n, 2, array, null, variant) == 0) ? variant : null;
    }
    
    public Variant getProperty(final int n, final Variant[] array, final int[] array2) {
        final Variant variant = new Variant();
        return (this.invoke(n, 2, array, array2, variant) == 0) ? variant : null;
    }
    
    public boolean equals(final Object o) {
        if (o == this) {
            return true;
        }
        if (!(o instanceof OleAutomation)) {
            return false;
        }
        if (this.objIDispatch == null) {
            return false;
        }
        final OleAutomation oleAutomation = (OleAutomation)o;
        return oleAutomation.objIDispatch != null && this.objIDispatch.getAddress() == oleAutomation.objIDispatch.getAddress();
    }
    
    public Variant invoke(final int n) {
        final Variant variant = new Variant();
        return (this.invoke(n, 1, null, null, variant) == 0) ? variant : null;
    }
    
    public Variant invoke(final int n, final Variant[] array) {
        final Variant variant = new Variant();
        return (this.invoke(n, 1, array, null, variant) == 0) ? variant : null;
    }
    
    public Variant invoke(final int n, final Variant[] array, final int[] array2) {
        final Variant variant = new Variant();
        return (this.invoke(n, 1, array, array2, variant) == 0) ? variant : null;
    }
    
    private int invoke(final int n, final int n2, final Variant[] array, final int[] array2, final Variant variant) {
        if (this.objIDispatch == null) {
            return -2147467259;
        }
        final DISPPARAMS dispparams = new DISPPARAMS();
        if (array != null && array.length > 0) {
            dispparams.cArgs = array.length;
            dispparams.rgvarg = OS.GlobalAlloc(64, VARIANT.sizeof * array.length);
            int n3 = 0;
            for (int i = array.length - 1; i >= 0; --i) {
                array[i].getData(dispparams.rgvarg + n3);
                n3 += VARIANT.sizeof;
            }
        }
        if (array2 != null && array2.length > 0) {
            dispparams.cNamedArgs = array2.length;
            dispparams.rgdispidNamedArgs = OS.GlobalAlloc(64, 4 * array2.length);
            int n4 = 0;
            for (int j = array2.length; j > 0; --j) {
                OS.MoveMemory(dispparams.rgdispidNamedArgs + n4, new int[] { array2[j - 1] }, 4);
                n4 += 4;
            }
        }
        final EXCEPINFO excepinfo = new EXCEPINFO();
        final int[] array3 = { 0 };
        int globalAlloc = 0;
        if (variant != null) {
            globalAlloc = OS.GlobalAlloc(64, VARIANT.sizeof);
        }
        final int invoke = this.objIDispatch.Invoke(n, new GUID(), 2048, n2, dispparams, globalAlloc, excepinfo, array3);
        if (globalAlloc != 0) {
            variant.setData(globalAlloc);
            COM.VariantClear(globalAlloc);
            OS.GlobalFree(globalAlloc);
        }
        if (dispparams.rgdispidNamedArgs != 0) {
            OS.GlobalFree(dispparams.rgdispidNamedArgs);
        }
        if (dispparams.rgvarg != 0) {
            int n5 = 0;
            for (int k = 0; k < array.length; ++k) {
                COM.VariantClear(dispparams.rgvarg + n5);
                n5 += VARIANT.sizeof;
            }
            OS.GlobalFree(dispparams.rgvarg);
        }
        this.manageExcepinfo(invoke, excepinfo);
        return invoke;
    }
    
    public void invokeNoReply(final int n) {
        final int invoke = this.invoke(n, 1, null, null, null);
        if (invoke != 0) {
            OLE.error(1014, invoke);
        }
    }
    
    public void invokeNoReply(final int n, final Variant[] array) {
        final int invoke = this.invoke(n, 1, array, null, null);
        if (invoke != 0) {
            OLE.error(1014, invoke);
        }
    }
    
    public void invokeNoReply(final int n, final Variant[] array, final int[] array2) {
        final int invoke = this.invoke(n, 1, array, array2, null);
        if (invoke != 0) {
            OLE.error(1014, invoke);
        }
    }
    
    private void manageExcepinfo(final int n, final EXCEPINFO excepinfo) {
        if (n == 0) {
            this.exceptionDescription = "No Error";
            return;
        }
        if (n == -2147352567) {
            if (excepinfo.bstrDescription != 0) {
                final int sysStringByteLen = COM.SysStringByteLen(excepinfo.bstrDescription);
                final char[] array = new char[(sysStringByteLen + 1) / 2];
                OS.MoveMemory(array, excepinfo.bstrDescription, sysStringByteLen);
                this.exceptionDescription = new String(array);
            }
            else {
                this.exceptionDescription = "OLE Automation Error Exception ";
                if (excepinfo.wCode != 0) {
                    this.exceptionDescription = String.valueOf(this.exceptionDescription) + "code = " + excepinfo.wCode;
                }
                else if (excepinfo.scode != 0) {
                    this.exceptionDescription = String.valueOf(this.exceptionDescription) + "code = " + excepinfo.scode;
                }
            }
        }
        else {
            this.exceptionDescription = "OLE Automation Error HResult : " + n;
        }
        if (excepinfo.bstrDescription != 0) {
            COM.SysFreeString(excepinfo.bstrDescription);
        }
        if (excepinfo.bstrHelpFile != 0) {
            COM.SysFreeString(excepinfo.bstrHelpFile);
        }
        if (excepinfo.bstrSource != 0) {
            COM.SysFreeString(excepinfo.bstrSource);
        }
    }
    
    public boolean setProperty(final int n, final Variant variant) {
        final Variant[] array = { variant };
        final int[] array2 = { -3 };
        int n2 = 4;
        if ((variant.getType() & 0x4000) == 0x4000) {
            n2 = 8;
        }
        return this.invoke(n, n2, array, array2, new Variant()) == 0;
    }
    
    public boolean setProperty(final int n, final Variant[] array) {
        final int[] array2 = { -3 };
        int n2 = 4;
        for (int i = 0; i < array.length; ++i) {
            if ((array[i].getType() & 0x4000) == 0x4000) {
                n2 = 8;
            }
        }
        return this.invoke(n, n2, array, array2, new Variant()) == 0;
    }
}
