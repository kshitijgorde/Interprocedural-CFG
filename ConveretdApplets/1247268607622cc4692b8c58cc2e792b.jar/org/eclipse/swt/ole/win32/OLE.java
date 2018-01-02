// 
// Decompiled by Procyon v0.5.30
// 

package org.eclipse.swt.ole.win32;

import org.eclipse.swt.internal.ole.win32.COM;
import java.io.File;
import org.eclipse.swt.internal.win32.OS;
import org.eclipse.swt.internal.win32.TCHAR;
import org.eclipse.swt.SWTError;
import org.eclipse.swt.SWTException;
import org.eclipse.swt.SWT;

public class OLE extends SWT
{
    public static final int S_FALSE = 1;
    public static final int S_OK = 0;
    public static final int E_FAIL = -2147467259;
    public static final int E_INVALIDARG = -2147024809;
    public static final int E_NOINTERFACE = -2147467262;
    public static final int E_NOTIMPL = -2147467263;
    public static final String IID_IUNKNOWN = "{00000000-0000-0000-C000-000000000046}";
    public static final String IID_IDISPATCH = "{00020400-0000-0000-C000-000000000046}";
    public static final int OLEIVERB_DISCARDUNDOSTATE = -6;
    public static final int OLEIVERB_HIDE = -3;
    public static final int OLEIVERB_INPLACEACTIVATE = -5;
    public static final int OLEIVERB_OPEN = -2;
    public static final int OLEIVERB_PRIMARY = 0;
    public static final int OLEIVERB_PROPERTIES = -7;
    public static final int OLEIVERB_SHOW = -1;
    public static final int OLEIVERB_UIACTIVATE = -4;
    public static final int PROPERTY_CHANGING = 0;
    public static final int PROPERTY_CHANGED = 1;
    public static final int HRESULT_UNSPECIFIED = 0;
    public static final int ERROR_CANNOT_CREATE_FILE = 1000;
    public static final int ERROR_CANNOT_CREATE_OBJECT = 1001;
    public static final int ERROR_CANNOT_OPEN_FILE = 1002;
    public static final int ERROR_INTERFACE_NOT_FOUND = 1003;
    public static final int ERROR_INVALID_CLASSID = 1004;
    public static final int ERROR_CANNOT_ACCESS_CLASSFACTORY = 1005;
    public static final int ERROR_CANNOT_CREATE_LICENSED_OBJECT = 1006;
    public static final int ERROR_OUT_OF_MEMORY = 1007;
    public static final int ERROR_CANNOT_CHANGE_VARIANT_TYPE = 1010;
    public static final int ERROR_INVALID_INTERFACE_ADDRESS = 1011;
    public static final int ERROR_APPLICATION_NOT_FOUND = 1013;
    public static final int ERROR_ACTION_NOT_PERFORMED = 1014;
    public static final int OLECMDF_SUPPORTED = 1;
    public static final int OLECMDF_ENABLED = 2;
    public static final int OLECMDF_LATCHED = 4;
    public static final int OLECMDF_NINCHED = 8;
    public static final int OLECMDTEXTF_NONE = 0;
    public static final int OLECMDTEXTF_NAME = 1;
    public static final int OLECMDTEXTF_STATUS = 2;
    public static final int OLECMDEXECOPT_DODEFAULT = 0;
    public static final int OLECMDEXECOPT_PROMPTUSER = 1;
    public static final int OLECMDEXECOPT_DONTPROMPTUSER = 2;
    public static final int OLECMDEXECOPT_SHOWHELP = 3;
    public static final int OLECMDID_OPEN = 1;
    public static final int OLECMDID_NEW = 2;
    public static final int OLECMDID_SAVE = 3;
    public static final int OLECMDID_SAVEAS = 4;
    public static final int OLECMDID_SAVECOPYAS = 5;
    public static final int OLECMDID_PRINT = 6;
    public static final int OLECMDID_PRINTPREVIEW = 7;
    public static final int OLECMDID_PAGESETUP = 8;
    public static final int OLECMDID_SPELL = 9;
    public static final int OLECMDID_PROPERTIES = 10;
    public static final int OLECMDID_CUT = 11;
    public static final int OLECMDID_COPY = 12;
    public static final int OLECMDID_PASTE = 13;
    public static final int OLECMDID_PASTESPECIAL = 14;
    public static final int OLECMDID_UNDO = 15;
    public static final int OLECMDID_REDO = 16;
    public static final int OLECMDID_SELECTALL = 17;
    public static final int OLECMDID_CLEARSELECTION = 18;
    public static final int OLECMDID_ZOOM = 19;
    public static final int OLECMDID_GETZOOMRANGE = 20;
    public static final int OLECMDID_UPDATECOMMANDS = 21;
    public static final int OLECMDID_REFRESH = 22;
    public static final int OLECMDID_STOP = 23;
    public static final int OLECMDID_HIDETOOLBARS = 24;
    public static final int OLECMDID_SETPROGRESSMAX = 25;
    public static final int OLECMDID_SETPROGRESSPOS = 26;
    public static final int OLECMDID_SETPROGRESSTEXT = 27;
    public static final int OLECMDID_SETTITLE = 28;
    public static final int OLECMDID_SETDOWNLOADSTATE = 29;
    public static final int OLECMDID_STOPDOWNLOAD = 30;
    public static int VARFLAG_FREADONLY;
    public static int VARFLAG_FSOURCE;
    public static int VARFLAG_FBINDABLE;
    public static int VARFLAG_FREQUESTEDIT;
    public static int VARFLAG_FDISPLAYBIND;
    public static int VARFLAG_FDEFAULTBIND;
    public static int VARFLAG_FHIDDEN;
    public static int VARFLAG_FRESTRICTED;
    public static int VARFLAG_FDEFAULTCOLLELEM;
    public static int VARFLAG_FUIDEFAULT;
    public static int VARFLAG_FNONBROWSABLE;
    public static int VARFLAG_FREPLACEABLE;
    public static int VARFLAG_FIMMEDIATEBIND;
    public static int VAR_PERINSTANCE;
    public static int VAR_STATIC;
    public static int VAR_CONST;
    public static int VAR_DISPATCH;
    public static short IDLFLAG_NONE;
    public static short IDLFLAG_FIN;
    public static short IDLFLAG_FOUT;
    public static short IDLFLAG_FLCID;
    public static short IDLFLAG_FRETVAL;
    public static final short VT_BOOL = 11;
    public static final short VT_BSTR = 8;
    public static final short VT_BYREF = 16384;
    public static final short VT_CY = 6;
    public static final short VT_DATE = 7;
    public static final short VT_DISPATCH = 9;
    public static final short VT_EMPTY = 0;
    public static final short VT_ERROR = 10;
    public static final short VT_I2 = 2;
    public static final short VT_I4 = 3;
    public static final short VT_NULL = 1;
    public static final short VT_R4 = 4;
    public static final short VT_R8 = 5;
    public static final short VT_UI1 = 17;
    public static final short VT_UI4 = 19;
    public static final short VT_UNKNOWN = 13;
    public static final short VT_VARIANT = 12;
    public static final short VT_PTR = 26;
    public static final short VT_USERDEFINED = 29;
    public static final short VT_HRESULT = 25;
    public static final short VT_DECIMAL = 14;
    public static final short VT_I1 = 16;
    public static final short VT_UI2 = 18;
    public static final short VT_I8 = 20;
    public static final short VT_UI8 = 21;
    public static final short VT_INT = 22;
    public static final short VT_UINT = 23;
    public static final short VT_VOID = 24;
    public static final short VT_SAFEARRAY = 27;
    public static final short VT_CARRAY = 28;
    public static final short VT_LPSTR = 30;
    public static final short VT_LPWSTR = 31;
    public static final short VT_RECORD = 36;
    public static final short VT_FILETIME = 64;
    public static final short VT_BLOB = 65;
    public static final short VT_STREAM = 66;
    public static final short VT_STORAGE = 67;
    public static final short VT_STREAMED_OBJECT = 68;
    public static final short VT_STORED_OBJECT = 69;
    public static final short VT_BLOB_OBJECT = 70;
    public static final short VT_CF = 71;
    public static final short VT_CLSID = 72;
    public static final short VT_VERSIONED_STREAM = 73;
    public static final short VT_BSTR_BLOB = 4095;
    public static final short VT_VECTOR = 4096;
    public static final short VT_ARRAY = 8192;
    public static final int INVOKE_FUNC = 1;
    public static final int INVOKE_PROPERTYGET = 2;
    public static final int INVOKE_PROPERTYPUT = 4;
    public static final int INVOKE_PROPERTYPUTREF = 8;
    public static final int FUNC_VIRTUAL = 0;
    public static final int FUNC_PUREVIRTUAL = 1;
    public static final int FUNC_NONVIRTUAL = 2;
    public static final int FUNC_STATIC = 3;
    public static final int FUNC_DISPATCH = 4;
    public static final short FUNCFLAG_FRESTRICTED = 1;
    public static final short FUNCFLAG_FSOURCE = 2;
    public static final short FUNCFLAG_FBINDABLE = 4;
    public static final short FUNCFLAG_FREQUESTEDIT = 8;
    public static final short FUNCFLAG_FDISPLAYBIND = 16;
    public static final short FUNCFLAG_FDEFAULTBIND = 32;
    public static final short FUNCFLAG_FHIDDEN = 64;
    public static final short FUNCFLAG_FUSESGETLASTERROR = 128;
    public static final short FUNCFLAG_FDEFAULTCOLLELEM = 256;
    public static final short FUNCFLAG_FUIDEFAULT = 512;
    public static final short FUNCFLAG_FNONBROWSABLE = 1024;
    public static final short FUNCFLAG_FREPLACEABLE = 2048;
    public static final short FUNCFLAG_FIMMEDIATEBIND = 4096;
    public static final int CC_FASTCALL = 0;
    public static final int CC_CDECL = 1;
    public static final int CC_MSCPASCAL = 2;
    public static final int CC_PASCAL = 2;
    public static final int CC_MACPASCAL = 3;
    public static final int CC_STDCALL = 4;
    public static final int CC_FPFASTCALL = 5;
    public static final int CC_SYSCALL = 6;
    public static final int CC_MPWCDECL = 7;
    public static final int CC_MPWPASCAL = 8;
    public static final int CC_MAX = 9;
    static final String ERROR_NOT_IMPLEMENTED_MSG = "Required functionality not currently supported.";
    static final String ERROR_CANNOT_CREATE_FILE_MSG = "Failed to create file.";
    static final String ERROR_CANNOT_CREATE_OBJECT_MSG = "Failed to create Ole Client.";
    static final String ERROR_CANNOT_OPEN_FILE_MSG = "File does not exist, is not accessible to user or does not have the correct format.";
    static final String ERROR_INTERFACE_NOT_FOUND_MSG = "Failed to find requested interface on OLE Object.";
    static final String ERROR_INVALID_CLASSID_MSG = "Class ID not found in registry";
    static final String ERROR_CANNOT_ACCESS_CLASSFACTORY_MSG = "Failed to get the class factory for the specified classID";
    static final String ERROR_CANNOT_CREATE_LICENSED_OBJECT_MSG = "Failed to create Licensed instance";
    static final String ERROR_OUT_OF_MEMORY_MSG = "Out of Memory";
    static final String ERROR_CANNOT_CHANGE_VARIANT_TYPE_MSG = "Failed to change Variant type";
    static final String ERROR_INVALID_INTERFACE_ADDRESS_MSG = "Invalid address received for Ole Interface.";
    static final String ERROR_APPLICATION_NOT_FOUND_MSG = "Unable to find Application.";
    static final String ERROR_ACTION_NOT_PERFORMED_MSG = "Action can not be performed.";
    
    static {
        OLE.VARFLAG_FREADONLY = 1;
        OLE.VARFLAG_FSOURCE = 2;
        OLE.VARFLAG_FBINDABLE = 4;
        OLE.VARFLAG_FREQUESTEDIT = 8;
        OLE.VARFLAG_FDISPLAYBIND = 16;
        OLE.VARFLAG_FDEFAULTBIND = 32;
        OLE.VARFLAG_FHIDDEN = 64;
        OLE.VARFLAG_FRESTRICTED = 128;
        OLE.VARFLAG_FDEFAULTCOLLELEM = 256;
        OLE.VARFLAG_FUIDEFAULT = 512;
        OLE.VARFLAG_FNONBROWSABLE = 1024;
        OLE.VARFLAG_FREPLACEABLE = 2048;
        OLE.VARFLAG_FIMMEDIATEBIND = 4096;
        OLE.VAR_PERINSTANCE = 0;
        OLE.VAR_STATIC = 1;
        OLE.VAR_CONST = 2;
        OLE.VAR_DISPATCH = 3;
        OLE.IDLFLAG_NONE = 0;
        OLE.IDLFLAG_FIN = 1;
        OLE.IDLFLAG_FOUT = 2;
        OLE.IDLFLAG_FLCID = 4;
        OLE.IDLFLAG_FRETVAL = 8;
    }
    
    public static void error(final int n) {
        error(n, 0);
    }
    
    public static void error(final int n, final int n2) {
        switch (n) {
            case 1011: {
                throw new IllegalArgumentException("Invalid address received for Ole Interface.");
            }
            case 1000: {
                String string = "Failed to create file.";
                if (n2 != 0) {
                    string = String.valueOf(string) + " result = " + n2;
                }
                throw new SWTException(n, string);
            }
            case 1001: {
                String string2 = "Failed to create Ole Client.";
                if (n2 != 0) {
                    string2 = String.valueOf(string2) + " result = " + n2;
                }
                throw new SWTException(n, string2);
            }
            case 1002: {
                String string3 = "File does not exist, is not accessible to user or does not have the correct format.";
                if (n2 != 0) {
                    string3 = String.valueOf(string3) + " result = " + n2;
                }
                throw new SWTException(n, string3);
            }
            case 1003: {
                String string4 = "Failed to find requested interface on OLE Object.";
                if (n2 != 0) {
                    string4 = String.valueOf(string4) + " result = " + n2;
                }
                throw new SWTException(n, string4);
            }
            case 1004: {
                String string5 = "Class ID not found in registry";
                if (n2 != 0) {
                    string5 = String.valueOf(string5) + " result = " + n2;
                }
                throw new SWTException(n, string5);
            }
            case 1005: {
                String string6 = "Failed to get the class factory for the specified classID";
                if (n2 != 0) {
                    string6 = String.valueOf(string6) + " result = " + n2;
                }
                throw new SWTException(n, string6);
            }
            case 1006: {
                String string7 = "Failed to create Licensed instance";
                if (n2 != 0) {
                    string7 = String.valueOf(string7) + " result = " + n2;
                }
                throw new SWTException(n, string7);
            }
            case 1010: {
                String string8 = "Failed to change Variant type";
                if (n2 != 0) {
                    string8 = String.valueOf(string8) + " result = " + n2;
                }
                throw new SWTException(n, string8);
            }
            case 1013: {
                String string9 = "Unable to find Application.";
                if (n2 != 0) {
                    string9 = String.valueOf(string9) + " result = " + n2;
                }
                throw new SWTException(n, string9);
            }
            case 1014: {
                String string10 = "Action can not be performed.";
                if (n2 != 0) {
                    string10 = String.valueOf(string10) + " result = " + n2;
                }
                throw new SWTException(n, string10);
            }
            case 1007: {
                String string11 = "Action can not be performed.";
                if (n2 != 0) {
                    string11 = String.valueOf(string11) + " result = " + n2;
                }
                throw new SWTError(n, string11);
            }
            default: {
                SWT.error(n);
            }
        }
    }
    
    public static String findProgramID(String string) {
        if (string == null) {
            SWT.error(4);
        }
        if (string.length() == 0) {
            return "";
        }
        if (string.charAt(0) != '.') {
            string = "." + string;
        }
        final String keyValue = getKeyValue(new TCHAR(0, string, true));
        if (keyValue != null) {
            if (getKeyExists(new TCHAR(0, String.valueOf(keyValue) + "\\NotInsertable", true))) {
                return "";
            }
            if (getKeyExists(new TCHAR(0, String.valueOf(keyValue) + "\\Insertable", true))) {
                return keyValue;
            }
            if (getKeyExists(new TCHAR(0, String.valueOf(keyValue) + "\\protocol\\StdFileEditing\\server", true))) {
                return keyValue;
            }
        }
        return "";
    }
    
    static String getKeyValue(final TCHAR tchar) {
        final int[] array = { 0 };
        if (OS.RegOpenKeyEx(Integer.MIN_VALUE, tchar, 0, 131097, array) != 0) {
            return null;
        }
        String string = null;
        final int[] array2 = { 0 };
        if (OS.RegQueryValueEx(array[0], null, 0, null, (TCHAR)null, array2) == 0) {
            final int n = array2[0] / TCHAR.sizeof;
            if (n == 0) {
                string = "";
            }
            else {
                final TCHAR tchar2 = new TCHAR(0, n);
                if (OS.RegQueryValueEx(array[0], null, 0, null, tchar2, array2) == 0) {
                    string = tchar2.toString(0, Math.max(0, tchar2.length() - 1));
                }
            }
        }
        if (array[0] != 0) {
            OS.RegCloseKey(array[0]);
        }
        return string;
    }
    
    private static boolean getKeyExists(final TCHAR tchar) {
        final int[] array = { 0 };
        if (OS.RegOpenKeyEx(Integer.MIN_VALUE, tchar, 0, 131097, array) != 0) {
            return false;
        }
        if (array[0] != 0) {
            OS.RegCloseKey(array[0]);
        }
        return true;
    }
    
    public static boolean isOleFile(final File file) {
        return file != null && file.exists() && !file.isDirectory() && COM.StgIsStorageFile((String.valueOf(file.getAbsolutePath()) + "\u0000").toCharArray()) == 0;
    }
}
