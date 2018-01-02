// 
// Decompiled by Procyon v0.5.30
// 

package org.eclipse.swt.internal.mozilla;

import org.eclipse.swt.internal.C;

public class XPCOM extends C
{
    public static final String MOZILLA_FIVE_HOME = "MOZILLA_FIVE_HOME";
    public static final String MOZILLA_PLUGIN_PATH = "MOZ_PLUGIN_PATH";
    public static final String CONTENT_MAYBETEXT = "application/x-vnd.mozilla.maybe-text";
    public static final String CONTENT_MULTIPART = "multipart/x-mixed-replace";
    public static final String DOMEVENT_FOCUS = "focus";
    public static final String DOMEVENT_UNLOAD = "unload";
    public static final String DOMEVENT_MOUSEDOWN = "mousedown";
    public static final String DOMEVENT_MOUSEUP = "mouseup";
    public static final String DOMEVENT_MOUSEMOVE = "mousemove";
    public static final String DOMEVENT_MOUSEDRAG = "draggesture";
    public static final String DOMEVENT_MOUSEWHEEL = "DOMMouseScroll";
    public static final String DOMEVENT_MOUSEOVER = "mouseover";
    public static final String DOMEVENT_MOUSEOUT = "mouseout";
    public static final String DOMEVENT_KEYDOWN = "keydown";
    public static final String DOMEVENT_KEYPRESS = "keypress";
    public static final String DOMEVENT_KEYUP = "keyup";
    public static final nsID EXTERNAL_CID;
    public static final nsID NS_APPSHELL_CID;
    public static final nsID NS_CATEGORYMANAGER_CID;
    public static final nsID NS_DOWNLOAD_CID;
    public static final nsID NS_FILEPICKER_CID;
    public static final nsID NS_HELPERAPPLAUNCHERDIALOG_CID;
    public static final nsID NS_INPUTSTREAMCHANNEL_CID;
    public static final nsID NS_IOSERVICE_CID;
    public static final nsID NS_LOADGROUP_CID;
    public static final nsID NS_PROMPTSERVICE_CID;
    public static final String EXTERNAL_CONTRACTID = "@eclipse.org/external;1";
    public static final String NS_CERTOVERRIDE_CONTRACTID = "@mozilla.org/security/certoverride;1";
    public static final String NS_CERTIFICATEDIALOGS_CONTRACTID = "@mozilla.org/nsCertificateDialogs;1";
    public static final String NS_CONTEXTSTACK_CONTRACTID = "@mozilla.org/js/xpc/ContextStack;1";
    public static final String NS_COOKIEMANAGER_CONTRACTID = "@mozilla.org/cookiemanager;1";
    public static final String NS_COOKIESERVICE_CONTRACTID = "@mozilla.org/cookieService;1";
    public static final String NS_DIRECTORYSERVICE_CONTRACTID = "@mozilla.org/file/directory_service;1";
    public static final String NS_DOMSERIALIZER_CONTRACTID = "@mozilla.org/xmlextras/xmlserializer;1";
    public static final String NS_DOWNLOAD_CONTRACTID = "@mozilla.org/download;1";
    public static final String NS_FILEPICKER_CONTRACTID = "@mozilla.org/filepicker;1";
    public static final String NS_HELPERAPPLAUNCHERDIALOG_CONTRACTID = "@mozilla.org/helperapplauncherdialog;1";
    public static final String NS_MEMORY_CONTRACTID = "@mozilla.org/xpcom/memory-service;1";
    public static final String NS_MIMEINPUTSTREAM_CONTRACTID = "@mozilla.org/network/mime-input-stream;1";
    public static final String NS_SCRIPTSECURITYMANAGER_CONTRACTID = "@mozilla.org/scriptsecuritymanager;1";
    public static final String NS_OBSERVER_CONTRACTID = "@mozilla.org/observer-service;1";
    public static final String NS_PREFLOCALIZEDSTRING_CONTRACTID = "@mozilla.org/pref-localizedstring;1";
    public static final String NS_PREFSERVICE_CONTRACTID = "@mozilla.org/preferences-service;1";
    public static final String NS_PROMPTSERVICE_CONTRACTID = "@mozilla.org/embedcomp/prompt-service;1";
    public static final String NS_TRANSFER_CONTRACTID = "@mozilla.org/transfer;1";
    public static final String NS_VARIANT_CONTRACTID = "@mozilla.org/variant;1";
    public static final String NS_WEBNAVIGATIONINFO_CONTRACTID = "@mozilla.org/webnavigation-info;1";
    public static final String NS_WINDOWWATCHER_CONTRACTID = "@mozilla.org/embedcomp/window-watcher;1";
    public static final String NS_APP_APPLICATION_REGISTRY_DIR = "AppRegD";
    public static final String NS_APP_CACHE_PARENT_DIR = "cachePDir";
    public static final String NS_APP_HISTORY_50_FILE = "UHist";
    public static final String NS_APP_LOCALSTORE_50_FILE = "LclSt";
    public static final String NS_APP_PLUGINS_DIR_LIST = "APluginsDL";
    public static final String NS_APP_PREF_DEFAULTS_50_DIR = "PrfDef";
    public static final String NS_APP_PREFS_50_DIR = "PrefD";
    public static final String NS_APP_PREFS_50_FILE = "PrefF";
    public static final String NS_APP_USER_CHROME_DIR = "UChrm";
    public static final String NS_APP_USER_MIMETYPES_50_FILE = "UMimTyp";
    public static final String NS_APP_USER_PROFILE_50_DIR = "ProfD";
    public static final String NS_GRE_COMPONENT_DIR = "GreComsD";
    public static final String NS_GRE_DIR = "GreD";
    public static final String NS_OS_CURRENT_PROCESS_DIR = "CurProcD";
    public static final String NS_OS_HOME_DIR = "Home";
    public static final String NS_OS_TEMP_DIR = "TmpD";
    public static final String NS_XPCOM_COMPONENT_DIR = "ComsD";
    public static final String NS_XPCOM_CURRENT_PROCESS_DIR = "XCurProcD";
    public static final String NS_XPCOM_INIT_CURRENT_PROCESS_DIR = "MozBinD";
    public static final int NS_OK = 0;
    public static final int NS_COMFALSE = 1;
    public static final int NS_BINDING_ABORTED = -2142568446;
    public static final int NS_ERROR_BASE = -1041039360;
    public static final int NS_ERROR_NOT_INITIALIZED = -1041039359;
    public static final int NS_ERROR_ALREADY_INITIALIZED = -1041039358;
    public static final int NS_ERROR_NOT_IMPLEMENTED = -2147467263;
    public static final int NS_NOINTERFACE = -2147467262;
    public static final int NS_ERROR_NO_INTERFACE = -2147467262;
    public static final int NS_ERROR_INVALID_POINTER = -2147467261;
    public static final int NS_ERROR_NULL_POINTER = -2147467261;
    public static final int NS_ERROR_ABORT = -2147467260;
    public static final int NS_ERROR_FAILURE = -2147467259;
    public static final int NS_ERROR_UNEXPECTED = -2147418113;
    public static final int NS_ERROR_OUT_OF_MEMORY = -2147024882;
    public static final int NS_ERROR_ILLEGAL_VALUE = -2147024809;
    public static final int NS_ERROR_INVALID_ARG = -2147024809;
    public static final int NS_ERROR_NO_AGGREGATION = -2147221232;
    public static final int NS_ERROR_NOT_AVAILABLE = -2147221231;
    public static final int NS_ERROR_FACTORY_NOT_REGISTERED = -2147221164;
    public static final int NS_ERROR_FACTORY_REGISTER_AGAIN = -2147221163;
    public static final int NS_ERROR_FACTORY_NOT_LOADED = -2147221000;
    public static final int NS_ERROR_FACTORY_NO_SIGNATURE_SUPPORT = -1041039103;
    public static final int NS_ERROR_FACTORY_EXISTS = -1041039104;
    public static final int NS_ERROR_HTMLPARSER_UNRESOLVEDDTD = -2142370829;
    public static final int NS_ERROR_FILE_NOT_FOUND = -2142109678;
    public static final int NS_ERROR_FILE_UNRECOGNIZED_PATH = -2142109695;
    
    static {
        EXTERNAL_CID = new nsID("f2c59ad0-bd76-11dd-ad8b-0800200c9a66");
        NS_APPSHELL_CID = new nsID("2d96b3df-c051-11d1-a827-0040959a28c9");
        NS_CATEGORYMANAGER_CID = new nsID("16d222a6-1dd2-11b2-b693-f38b02c021b2");
        NS_DOWNLOAD_CID = new nsID("e3fa9D0a-1dd1-11b2-bdef-8c720b597445");
        NS_FILEPICKER_CID = new nsID("54ae32f8-1dd2-11b2-a209-df7c505370f8");
        NS_HELPERAPPLAUNCHERDIALOG_CID = new nsID("f68578eb-6ec2-4169-ae19-8c6243f0abe1");
        NS_INPUTSTREAMCHANNEL_CID = new nsID("6ddb050c-0d04-11d4-986e-00c04fa0cf4a");
        NS_IOSERVICE_CID = new nsID("9ac9e770-18bc-11d3-9337-00104ba0fd40");
        NS_LOADGROUP_CID = new nsID("e1c61582-2a84-11d3-8cce-0060b0fc14a3");
        NS_PROMPTSERVICE_CID = new nsID("a2112d6a-0e28-421f-b46a-25c0b308cbd0");
    }
    
    public static final native int nsDynamicFunctionLoad_sizeof();
    
    public static final native void memmove(final int p0, final nsDynamicFunctionLoad p1, final int p2);
    
    public static final native void memmove(final nsID p0, final int p1, final int p2);
    
    public static final native void memmove(final int p0, final nsID p1, final int p2);
    
    public static final native int strlen_PRUnichar(final int p0);
    
    public static final native int _JS_EvaluateUCScriptForPrincipals(final byte[] p0, final int p1, final int p2, final int p3, final char[] p4, final int p5, final byte[] p6, final int p7, final int[] p8);
    
    public static final int JS_EvaluateUCScriptForPrincipals(final byte[] array, final int n, final int n2, final int n3, final char[] array2, final int n4, final byte[] array3, final int n5, final int[] array4) {
        XPCOM.lock.lock();
        try {
            return _JS_EvaluateUCScriptForPrincipals(array, n, n2, n3, array2, n4, array3, n5, array4);
        }
        finally {
            XPCOM.lock.unlock();
        }
    }
    
    public static final native boolean _NS_Free(final byte[] p0, final int p1);
    
    public static final boolean NS_Free(final byte[] array, final int n) {
        XPCOM.lock.lock();
        try {
            return _NS_Free(array, n);
        }
        finally {
            XPCOM.lock.unlock();
        }
    }
    
    public static final native int _NS_GetComponentManager(final int[] p0);
    
    public static final int NS_GetComponentManager(final int[] array) {
        XPCOM.lock.lock();
        try {
            return _NS_GetComponentManager(array);
        }
        finally {
            XPCOM.lock.unlock();
        }
    }
    
    public static final native int _NS_GetServiceManager(final int[] p0);
    
    public static final int NS_GetServiceManager(final int[] array) {
        XPCOM.lock.lock();
        try {
            return _NS_GetServiceManager(array);
        }
        finally {
            XPCOM.lock.unlock();
        }
    }
    
    public static final native int _NS_InitXPCOM2(final int p0, final int p1, final int p2);
    
    public static final int NS_InitXPCOM2(final int n, final int n2, final int n3) {
        XPCOM.lock.lock();
        try {
            return _NS_InitXPCOM2(n, n2, n3);
        }
        finally {
            XPCOM.lock.unlock();
        }
    }
    
    public static final native int _NS_NewLocalFile(final int p0, final int p1, final int[] p2);
    
    public static final int NS_NewLocalFile(final int n, final int n2, final int[] array) {
        XPCOM.lock.lock();
        try {
            return _NS_NewLocalFile(n, n2, array);
        }
        finally {
            XPCOM.lock.unlock();
        }
    }
    
    public static final native int _nsEmbedCString_new();
    
    public static final int nsEmbedCString_new() {
        XPCOM.lock.lock();
        try {
            return _nsEmbedCString_new();
        }
        finally {
            XPCOM.lock.unlock();
        }
    }
    
    public static final native int _nsEmbedCString_new(final byte[] p0, final int p1);
    
    public static final int nsEmbedCString_new(final byte[] array, final int n) {
        XPCOM.lock.lock();
        try {
            return _nsEmbedCString_new(array, n);
        }
        finally {
            XPCOM.lock.unlock();
        }
    }
    
    public static final native int _nsEmbedCString_new(final int p0, final int p1);
    
    public static final int nsEmbedCString_new(final int n, final int n2) {
        XPCOM.lock.lock();
        try {
            return _nsEmbedCString_new(n, n2);
        }
        finally {
            XPCOM.lock.unlock();
        }
    }
    
    public static final native void _nsEmbedCString_delete(final int p0);
    
    public static final void nsEmbedCString_delete(final int n) {
        XPCOM.lock.lock();
        try {
            _nsEmbedCString_delete(n);
        }
        finally {
            XPCOM.lock.unlock();
        }
        XPCOM.lock.unlock();
    }
    
    public static final native int _nsEmbedCString_Length(final int p0);
    
    public static final int nsEmbedCString_Length(final int n) {
        XPCOM.lock.lock();
        try {
            return _nsEmbedCString_Length(n);
        }
        finally {
            XPCOM.lock.unlock();
        }
    }
    
    public static final native int _nsIScriptGlobalObject_EnsureScriptEnvironment(final int p0, final int p1);
    
    public static final int nsIScriptGlobalObject_EnsureScriptEnvironment(final int n, final int n2) {
        XPCOM.lock.lock();
        try {
            return _nsIScriptGlobalObject_EnsureScriptEnvironment(n, n2);
        }
        finally {
            XPCOM.lock.unlock();
        }
    }
    
    public static final native int _nsIScriptGlobalObject_GetScriptGlobal(final int p0, final int p1);
    
    public static final int nsIScriptGlobalObject_GetScriptGlobal(final int n, final int n2) {
        XPCOM.lock.lock();
        try {
            return _nsIScriptGlobalObject_GetScriptGlobal(n, n2);
        }
        finally {
            XPCOM.lock.unlock();
        }
    }
    
    public static final native int _nsIScriptGlobalObject_GetScriptContext(final int p0, final int p1);
    
    public static final int nsIScriptGlobalObject_GetScriptContext(final int n, final int n2) {
        XPCOM.lock.lock();
        try {
            return _nsIScriptGlobalObject_GetScriptContext(n, n2);
        }
        finally {
            XPCOM.lock.unlock();
        }
    }
    
    public static final native int _nsIScriptContext_GetNativeContext(final int p0);
    
    public static final int nsIScriptContext_GetNativeContext(final int n) {
        XPCOM.lock.lock();
        try {
            return _nsIScriptContext_GetNativeContext(n);
        }
        finally {
            XPCOM.lock.unlock();
        }
    }
    
    public static final native int _nsEmbedCString_get(final int p0);
    
    public static final int nsEmbedCString_get(final int n) {
        XPCOM.lock.lock();
        try {
            return _nsEmbedCString_get(n);
        }
        finally {
            XPCOM.lock.unlock();
        }
    }
    
    public static final native void _nsID_delete(final int p0);
    
    public static final void nsID_delete(final int n) {
        XPCOM.lock.lock();
        try {
            _nsID_delete(n);
        }
        finally {
            XPCOM.lock.unlock();
        }
        XPCOM.lock.unlock();
    }
    
    public static final native int _nsID_new();
    
    public static final int nsID_new() {
        XPCOM.lock.lock();
        try {
            return _nsID_new();
        }
        finally {
            XPCOM.lock.unlock();
        }
    }
    
    public static final native int _nsID_Equals(final int p0, final int p1);
    
    public static final int nsID_Equals(final int n, final int n2) {
        XPCOM.lock.lock();
        try {
            return _nsID_Equals(n, n2);
        }
        finally {
            XPCOM.lock.unlock();
        }
    }
    
    public static final native int _nsEmbedString_new();
    
    public static final int nsEmbedString_new() {
        XPCOM.lock.lock();
        try {
            return _nsEmbedString_new();
        }
        finally {
            XPCOM.lock.unlock();
        }
    }
    
    public static final native int _nsEmbedString_new(final char[] p0);
    
    public static final int nsEmbedString_new(final char[] array) {
        XPCOM.lock.lock();
        try {
            return _nsEmbedString_new(array);
        }
        finally {
            XPCOM.lock.unlock();
        }
    }
    
    public static final native void _nsEmbedString_delete(final int p0);
    
    public static final void nsEmbedString_delete(final int n) {
        XPCOM.lock.lock();
        try {
            _nsEmbedString_delete(n);
        }
        finally {
            XPCOM.lock.unlock();
        }
        XPCOM.lock.unlock();
    }
    
    public static final native int _nsEmbedString_Length(final int p0);
    
    public static final int nsEmbedString_Length(final int n) {
        XPCOM.lock.lock();
        try {
            return _nsEmbedString_Length(n);
        }
        finally {
            XPCOM.lock.unlock();
        }
    }
    
    public static final native int _nsEmbedString_get(final int p0);
    
    public static final int nsEmbedString_get(final int n) {
        XPCOM.lock.lock();
        try {
            return _nsEmbedString_get(n);
        }
        finally {
            XPCOM.lock.unlock();
        }
    }
    
    public static final native int _nsIMemory_Alloc(final int p0, final int p1);
    
    public static final int nsIMemory_Alloc(final int n, final int n2) {
        XPCOM.lock.lock();
        try {
            return _nsIMemory_Alloc(n, n2);
        }
        finally {
            XPCOM.lock.unlock();
        }
    }
    
    public static final native int _nsIMemory_Realloc(final int p0, final int p1, final int p2);
    
    public static final int nsIMemory_Realloc(final int n, final int n2, final int n3) {
        XPCOM.lock.lock();
        try {
            return _nsIMemory_Realloc(n, n2, n3);
        }
        finally {
            XPCOM.lock.unlock();
        }
    }
    
    public static final native int _XPCOMGlueLoadXULFunctions(final int p0);
    
    public static final int XPCOMGlueLoadXULFunctions(final int n) {
        XPCOM.lock.lock();
        try {
            return _XPCOMGlueLoadXULFunctions(n);
        }
        finally {
            XPCOM.lock.unlock();
        }
    }
    
    public static final native int _XPCOMGlueStartup(final byte[] p0);
    
    public static final int XPCOMGlueStartup(final byte[] array) {
        XPCOM.lock.lock();
        try {
            return _XPCOMGlueStartup(array);
        }
        finally {
            XPCOM.lock.unlock();
        }
    }
    
    public static final native int _XPCOMGlueShutdown();
    
    public static final int XPCOMGlueShutdown() {
        XPCOM.lock.lock();
        try {
            return _XPCOMGlueShutdown();
        }
        finally {
            XPCOM.lock.unlock();
        }
    }
    
    public static final native int _Call(final int p0);
    
    public static final int Call(final int n) {
        XPCOM.lock.lock();
        try {
            return _Call(n);
        }
        finally {
            XPCOM.lock.unlock();
        }
    }
    
    public static final native int _Call(final int p0, final int p1, final int p2, final byte[] p3, final int p4, final int p5, final int[] p6);
    
    public static final int Call(final int n, final int n2, final int n3, final byte[] array, final int n4, final int n5, final int[] array2) {
        XPCOM.lock.lock();
        try {
            return _Call(n, n2, n3, array, n4, n5, array2);
        }
        finally {
            XPCOM.lock.unlock();
        }
    }
    
    public static final native int _Call(final int p0, final int p1, final int p2, final int p3, final int p4, final int p5);
    
    public static final int Call(final int n, final int n2, final int n3, final int n4, final int n5, final int n6) {
        XPCOM.lock.lock();
        try {
            return _Call(n, n2, n3, n4, n5, n6);
        }
        finally {
            XPCOM.lock.unlock();
        }
    }
    
    static final native int _VtblCall(final int p0, final int p1);
    
    static final int VtblCall(final int n, final int n2) {
        XPCOM.lock.lock();
        try {
            return _VtblCall(n, n2);
        }
        finally {
            XPCOM.lock.unlock();
        }
    }
    
    static final native int _VtblCall(final int p0, final int p1, final byte[] p2);
    
    static final int VtblCall(final int n, final int n2, final byte[] array) {
        XPCOM.lock.lock();
        try {
            return _VtblCall(n, n2, array);
        }
        finally {
            XPCOM.lock.unlock();
        }
    }
    
    static final native int _VtblCall(final int p0, final int p1, final char[] p2);
    
    static final int VtblCall(final int n, final int n2, final char[] array) {
        XPCOM.lock.lock();
        try {
            return _VtblCall(n, n2, array);
        }
        finally {
            XPCOM.lock.unlock();
        }
    }
    
    static final native int _VtblCall(final int p0, final int p1, final double p2);
    
    static final int VtblCall(final int n, final int n2, final double n3) {
        XPCOM.lock.lock();
        try {
            return _VtblCall(n, n2, n3);
        }
        finally {
            XPCOM.lock.unlock();
        }
    }
    
    static final native int _VtblCall(final int p0, final int p1, final float p2);
    
    static final int VtblCall(final int n, final int n2, final float n3) {
        XPCOM.lock.lock();
        try {
            return _VtblCall(n, n2, n3);
        }
        finally {
            XPCOM.lock.unlock();
        }
    }
    
    static final native int _VtblCall(final int p0, final int p1, final float[] p2);
    
    static final int VtblCall(final int n, final int n2, final float[] array) {
        XPCOM.lock.lock();
        try {
            return _VtblCall(n, n2, array);
        }
        finally {
            XPCOM.lock.unlock();
        }
    }
    
    static final native int _VtblCall(final int p0, final int p1, final int p2);
    
    static final int VtblCall(final int n, final int n2, final int n3) {
        XPCOM.lock.lock();
        try {
            return _VtblCall(n, n2, n3);
        }
        finally {
            XPCOM.lock.unlock();
        }
    }
    
    static final native int _VtblCall(final int p0, final int p1, final int[] p2);
    
    static final int VtblCall(final int n, final int n2, final int[] array) {
        XPCOM.lock.lock();
        try {
            return _VtblCall(n, n2, array);
        }
        finally {
            XPCOM.lock.unlock();
        }
    }
    
    static final native int _VtblCall(final int p0, final int p1, final long p2);
    
    static final int VtblCall(final int n, final int n2, final long n3) {
        XPCOM.lock.lock();
        try {
            return _VtblCall(n, n2, n3);
        }
        finally {
            XPCOM.lock.unlock();
        }
    }
    
    static final native int _VtblCall(final int p0, final int p1, final long[] p2);
    
    static final int VtblCall(final int n, final int n2, final long[] array) {
        XPCOM.lock.lock();
        try {
            return _VtblCall(n, n2, array);
        }
        finally {
            XPCOM.lock.unlock();
        }
    }
    
    static final native int _VtblCall(final int p0, final int p1, final short[] p2);
    
    static final int VtblCall(final int n, final int n2, final short[] array) {
        XPCOM.lock.lock();
        try {
            return _VtblCall(n, n2, array);
        }
        finally {
            XPCOM.lock.unlock();
        }
    }
    
    static final native int _VtblCall(final int p0, final int p1, final int p2, final int p3);
    
    static final int VtblCall(final int n, final int n2, final int n3, final int n4) {
        XPCOM.lock.lock();
        try {
            return _VtblCall(n, n2, n3, n4);
        }
        finally {
            XPCOM.lock.unlock();
        }
    }
    
    static final native int _VtblCall(final int p0, final int p1, final long p2, final int p3);
    
    static final int VtblCall(final int n, final int n2, final long n3, final int n4) {
        XPCOM.lock.lock();
        try {
            return _VtblCall(n, n2, n3, n4);
        }
        finally {
            XPCOM.lock.unlock();
        }
    }
    
    static final native int _VtblCall(final int p0, final int p1, final long p2, final long p3);
    
    static final int VtblCall(final int n, final int n2, final long n3, final long n4) {
        XPCOM.lock.lock();
        try {
            return _VtblCall(n, n2, n3, n4);
        }
        finally {
            XPCOM.lock.unlock();
        }
    }
    
    static final native int _VtblCall(final int p0, final int p1, final int p2, final int[] p3);
    
    static final int VtblCall(final int n, final int n2, final int n3, final int[] array) {
        XPCOM.lock.lock();
        try {
            return _VtblCall(n, n2, n3, array);
        }
        finally {
            XPCOM.lock.unlock();
        }
    }
    
    static final native int _VtblCall(final int p0, final int p1, final long p2, final long[] p3);
    
    static final int VtblCall(final int n, final int n2, final long n3, final long[] array) {
        XPCOM.lock.lock();
        try {
            return _VtblCall(n, n2, n3, array);
        }
        finally {
            XPCOM.lock.unlock();
        }
    }
    
    static final native int _VtblCall(final int p0, final int p1, final long p2, final int[] p3);
    
    static final int VtblCall(final int n, final int n2, final long n3, final int[] array) {
        XPCOM.lock.lock();
        try {
            return _VtblCall(n, n2, n3, array);
        }
        finally {
            XPCOM.lock.unlock();
        }
    }
    
    static final native int _VtblCall(final int p0, final int p1, final byte[] p2, final int[] p3);
    
    static final int VtblCall(final int n, final int n2, final byte[] array, final int[] array2) {
        XPCOM.lock.lock();
        try {
            return _VtblCall(n, n2, array, array2);
        }
        finally {
            XPCOM.lock.unlock();
        }
    }
    
    static final native int _VtblCall(final int p0, final int p1, final byte[] p2, final long[] p3);
    
    static final int VtblCall(final int n, final int n2, final byte[] array, final long[] array2) {
        XPCOM.lock.lock();
        try {
            return _VtblCall(n, n2, array, array2);
        }
        finally {
            XPCOM.lock.unlock();
        }
    }
    
    static final native int _VtblCall(final int p0, final int p1, final byte[] p2, final long p3);
    
    static final int VtblCall(final int n, final int n2, final byte[] array, final long n3) {
        XPCOM.lock.lock();
        try {
            return _VtblCall(n, n2, array, n3);
        }
        finally {
            XPCOM.lock.unlock();
        }
    }
    
    static final native int _VtblCall(final int p0, final int p1, final byte[] p2, final int p3);
    
    static final int VtblCall(final int n, final int n2, final byte[] array, final int n3) {
        XPCOM.lock.lock();
        try {
            return _VtblCall(n, n2, array, n3);
        }
        finally {
            XPCOM.lock.unlock();
        }
    }
    
    static final native int _VtblCall(final int p0, final int p1, final int p2, final nsID p3);
    
    static final int VtblCall(final int n, final int n2, final int n3, final nsID nsID) {
        XPCOM.lock.lock();
        try {
            return _VtblCall(n, n2, n3, nsID);
        }
        finally {
            XPCOM.lock.unlock();
        }
    }
    
    static final native int _VtblCall(final int p0, final int p1, final long p2, final nsID p3);
    
    static final int VtblCall(final int n, final int n2, final long n3, final nsID nsID) {
        XPCOM.lock.lock();
        try {
            return _VtblCall(n, n2, n3, nsID);
        }
        finally {
            XPCOM.lock.unlock();
        }
    }
    
    static final native int _VtblCall(final int p0, final int p1, final int p2, final char[] p3);
    
    static final int VtblCall(final int n, final int n2, final int n3, final char[] array) {
        XPCOM.lock.lock();
        try {
            return _VtblCall(n, n2, n3, array);
        }
        finally {
            XPCOM.lock.unlock();
        }
    }
    
    static final native int _VtblCall(final int p0, final int p1, final int[] p2, final int[] p3);
    
    static final int VtblCall(final int n, final int n2, final int[] array, final int[] array2) {
        XPCOM.lock.lock();
        try {
            return _VtblCall(n, n2, array, array2);
        }
        finally {
            XPCOM.lock.unlock();
        }
    }
    
    static final native int _VtblCall(final int p0, final int p1, final int[] p2, final long[] p3);
    
    static final int VtblCall(final int n, final int n2, final int[] array, final long[] array2) {
        XPCOM.lock.lock();
        try {
            return _VtblCall(n, n2, array, array2);
        }
        finally {
            XPCOM.lock.unlock();
        }
    }
    
    static final native int _VtblCall(final int p0, final int p1, final nsID p2, final int p3);
    
    static final int VtblCall(final int n, final int n2, final nsID nsID, final int n3) {
        XPCOM.lock.lock();
        try {
            return _VtblCall(n, n2, nsID, n3);
        }
        finally {
            XPCOM.lock.unlock();
        }
    }
    
    static final native int _VtblCall(final int p0, final int p1, final nsID p2, final long p3);
    
    static final int VtblCall(final int n, final int n2, final nsID nsID, final long n3) {
        XPCOM.lock.lock();
        try {
            return _VtblCall(n, n2, nsID, n3);
        }
        finally {
            XPCOM.lock.unlock();
        }
    }
    
    static final native int _VtblCall(final int p0, final int p1, final nsID p2, final int[] p3);
    
    static final int VtblCall(final int n, final int n2, final nsID nsID, final int[] array) {
        XPCOM.lock.lock();
        try {
            return _VtblCall(n, n2, nsID, array);
        }
        finally {
            XPCOM.lock.unlock();
        }
    }
    
    static final native int _VtblCall(final int p0, final int p1, final nsID p2, final long[] p3);
    
    static final int VtblCall(final int n, final int n2, final nsID nsID, final long[] array) {
        XPCOM.lock.lock();
        try {
            return _VtblCall(n, n2, nsID, array);
        }
        finally {
            XPCOM.lock.unlock();
        }
    }
    
    static final native int _VtblCall(final int p0, final int p1, final char[] p2, final char[] p3);
    
    static final int VtblCall(final int n, final int n2, final char[] array, final char[] array2) {
        XPCOM.lock.lock();
        try {
            return _VtblCall(n, n2, array, array2);
        }
        finally {
            XPCOM.lock.unlock();
        }
    }
    
    static final native int _VtblCall(final int p0, final int p1, final byte[] p2, final byte[] p3);
    
    static final int VtblCall(final int n, final int n2, final byte[] array, final byte[] array2) {
        XPCOM.lock.lock();
        try {
            return _VtblCall(n, n2, array, array2);
        }
        finally {
            XPCOM.lock.unlock();
        }
    }
    
    static final native int _VtblCall(final int p0, final int p1, final int p2, final byte[] p3);
    
    static final int VtblCall(final int n, final int n2, final int n3, final byte[] array) {
        XPCOM.lock.lock();
        try {
            return _VtblCall(n, n2, n3, array);
        }
        finally {
            XPCOM.lock.unlock();
        }
    }
    
    static final native int _VtblCall(final int p0, final int p1, final long p2, final byte[] p3);
    
    static final int VtblCall(final int n, final int n2, final long n3, final byte[] array) {
        XPCOM.lock.lock();
        try {
            return _VtblCall(n, n2, n3, array);
        }
        finally {
            XPCOM.lock.unlock();
        }
    }
    
    static final native int _VtblCall(final int p0, final int p1, final int p2, final char[] p3, final int p4);
    
    static final int VtblCall(final int n, final int n2, final int n3, final char[] array, final int n4) {
        XPCOM.lock.lock();
        try {
            return _VtblCall(n, n2, n3, array, n4);
        }
        finally {
            XPCOM.lock.unlock();
        }
    }
    
    static final native int _VtblCall(final int p0, final int p1, final int p2, final char[] p3, final int[] p4);
    
    static final int VtblCall(final int n, final int n2, final int n3, final char[] array, final int[] array2) {
        XPCOM.lock.lock();
        try {
            return _VtblCall(n, n2, n3, array, array2);
        }
        finally {
            XPCOM.lock.unlock();
        }
    }
    
    static final native int _VtblCall(final int p0, final int p1, final long p2, final char[] p3, final long[] p4);
    
    static final int VtblCall(final int n, final int n2, final long n3, final char[] array, final long[] array2) {
        XPCOM.lock.lock();
        try {
            return _VtblCall(n, n2, n3, array, array2);
        }
        finally {
            XPCOM.lock.unlock();
        }
    }
    
    static final native int _VtblCall(final int p0, final int p1, final long p2, final char[] p3, final int p4);
    
    static final int VtblCall(final int n, final int n2, final long n3, final char[] array, final int n4) {
        XPCOM.lock.lock();
        try {
            return _VtblCall(n, n2, n3, array, n4);
        }
        finally {
            XPCOM.lock.unlock();
        }
    }
    
    static final native int _VtblCall(final int p0, final int p1, final int[] p2, final int[] p3, final int[] p4);
    
    static final int VtblCall(final int n, final int n2, final int[] array, final int[] array2, final int[] array3) {
        XPCOM.lock.lock();
        try {
            return _VtblCall(n, n2, array, array2, array3);
        }
        finally {
            XPCOM.lock.unlock();
        }
    }
    
    static final native int _VtblCall(final int p0, final int p1, final long[] p2, final long[] p3);
    
    static final int VtblCall(final int n, final int n2, final long[] array, final long[] array2) {
        XPCOM.lock.lock();
        try {
            return _VtblCall(n, n2, array, array2);
        }
        finally {
            XPCOM.lock.unlock();
        }
    }
    
    static final native int _VtblCall(final int p0, final int p1, final int p2, final int[] p3, final int p4);
    
    static final int VtblCall(final int n, final int n2, final int n3, final int[] array, final int n4) {
        XPCOM.lock.lock();
        try {
            return _VtblCall(n, n2, n3, array, n4);
        }
        finally {
            XPCOM.lock.unlock();
        }
    }
    
    static final native int _VtblCall(final int p0, final int p1, final int p2, final int[] p3, final long p4);
    
    static final int VtblCall(final int n, final int n2, final int n3, final int[] array, final long n4) {
        XPCOM.lock.lock();
        try {
            return _VtblCall(n, n2, n3, array, n4);
        }
        finally {
            XPCOM.lock.unlock();
        }
    }
    
    static final native int _VtblCall(final int p0, final int p1, final long[] p2, final long[] p3, final long[] p4);
    
    static final int VtblCall(final int n, final int n2, final long[] array, final long[] array2, final long[] array3) {
        XPCOM.lock.lock();
        try {
            return _VtblCall(n, n2, array, array2, array3);
        }
        finally {
            XPCOM.lock.unlock();
        }
    }
    
    static final native int _VtblCall(final int p0, final int p1, final short p2, final int p3, final int p4, final int p5);
    
    static final int VtblCall(final int n, final int n2, final short n3, final int n4, final int n5, final int n6) {
        XPCOM.lock.lock();
        try {
            return _VtblCall(n, n2, n3, n4, n5, n6);
        }
        finally {
            XPCOM.lock.unlock();
        }
    }
    
    static final native int _VtblCall(final int p0, final int p1, final short p2, final long p3, final int p4, final long p5);
    
    static final int VtblCall(final int n, final int n2, final short n3, final long n4, final int n5, final long n6) {
        XPCOM.lock.lock();
        try {
            return _VtblCall(n, n2, n3, n4, n5, n6);
        }
        finally {
            XPCOM.lock.unlock();
        }
    }
    
    static final native int _VtblCall(final int p0, final int p1, final int[] p2, final long[] p3, final int[] p4);
    
    static final int VtblCall(final int n, final int n2, final int[] array, final long[] array2, final int[] array3) {
        XPCOM.lock.lock();
        try {
            return _VtblCall(n, n2, array, array2, array3);
        }
        finally {
            XPCOM.lock.unlock();
        }
    }
    
    static final native int _VtblCall(final int p0, final int p1, final int[] p2, final long[] p3, final long[] p4);
    
    static final int VtblCall(final int n, final int n2, final int[] array, final long[] array2, final long[] array3) {
        XPCOM.lock.lock();
        try {
            return _VtblCall(n, n2, array, array2, array3);
        }
        finally {
            XPCOM.lock.unlock();
        }
    }
    
    static final native int _VtblCall(final int p0, final int p1, final byte[] p2, final int p3, final int[] p4);
    
    static final int VtblCall(final int n, final int n2, final byte[] array, final int n3, final int[] array2) {
        XPCOM.lock.lock();
        try {
            return _VtblCall(n, n2, array, n3, array2);
        }
        finally {
            XPCOM.lock.unlock();
        }
    }
    
    static final native int _VtblCall(final int p0, final int p1, final byte[] p2, final long p3, final int[] p4);
    
    static final int VtblCall(final int n, final int n2, final byte[] array, final long n3, final int[] array2) {
        XPCOM.lock.lock();
        try {
            return _VtblCall(n, n2, array, n3, array2);
        }
        finally {
            XPCOM.lock.unlock();
        }
    }
    
    static final native int _VtblCall(final int p0, final int p1, final byte[] p2, final nsID p3, final long[] p4);
    
    static final int VtblCall(final int n, final int n2, final byte[] array, final nsID nsID, final long[] array2) {
        XPCOM.lock.lock();
        try {
            return _VtblCall(n, n2, array, nsID, array2);
        }
        finally {
            XPCOM.lock.unlock();
        }
    }
    
    static final native int _VtblCall(final int p0, final int p1, final byte[] p2, final nsID p3, final int[] p4);
    
    static final int VtblCall(final int n, final int n2, final byte[] array, final nsID nsID, final int[] array2) {
        XPCOM.lock.lock();
        try {
            return _VtblCall(n, n2, array, nsID, array2);
        }
        finally {
            XPCOM.lock.unlock();
        }
    }
    
    static final native int _VtblCall(final int p0, final int p1, final char[] p2, final int p3, final int[] p4);
    
    static final int VtblCall(final int n, final int n2, final char[] array, final int n3, final int[] array2) {
        XPCOM.lock.lock();
        try {
            return _VtblCall(n, n2, array, n3, array2);
        }
        finally {
            XPCOM.lock.unlock();
        }
    }
    
    static final native int _VtblCall(final int p0, final int p1, final char[] p2, final long p3, final long[] p4);
    
    static final int VtblCall(final int n, final int n2, final char[] array, final long n3, final long[] array2) {
        XPCOM.lock.lock();
        try {
            return _VtblCall(n, n2, array, n3, array2);
        }
        finally {
            XPCOM.lock.unlock();
        }
    }
    
    static final native int _VtblCall(final int p0, final int p1, final int p2, final char[] p3, final char[] p4);
    
    static final int VtblCall(final int n, final int n2, final int n3, final char[] array, final char[] array2) {
        XPCOM.lock.lock();
        try {
            return _VtblCall(n, n2, n3, array, array2);
        }
        finally {
            XPCOM.lock.unlock();
        }
    }
    
    static final native int _VtblCall(final int p0, final int p1, final long p2, final char[] p3, final char[] p4);
    
    static final int VtblCall(final int n, final int n2, final long n3, final char[] array, final char[] array2) {
        XPCOM.lock.lock();
        try {
            return _VtblCall(n, n2, n3, array, array2);
        }
        finally {
            XPCOM.lock.unlock();
        }
    }
    
    static final native int _VtblCall(final int p0, final int p1, final long p2, final long p3, final long p4);
    
    static final int VtblCall(final int n, final int n2, final long n3, final long n4, final long n5) {
        XPCOM.lock.lock();
        try {
            return _VtblCall(n, n2, n3, n4, n5);
        }
        finally {
            XPCOM.lock.unlock();
        }
    }
    
    static final native int _VtblCall(final int p0, final int p1, final long p2, final long p3, final int p4);
    
    static final int VtblCall(final int n, final int n2, final long n3, final long n4, final int n5) {
        XPCOM.lock.lock();
        try {
            return _VtblCall(n, n2, n3, n4, n5);
        }
        finally {
            XPCOM.lock.unlock();
        }
    }
    
    static final native int _VtblCall(final int p0, final int p1, final int p2, final long p3, final long p4);
    
    static final int VtblCall(final int n, final int n2, final int n3, final long n4, final long n5) {
        XPCOM.lock.lock();
        try {
            return _VtblCall(n, n2, n3, n4, n5);
        }
        finally {
            XPCOM.lock.unlock();
        }
    }
    
    static final native int _VtblCall(final int p0, final int p1, final int p2, final int p3, final int p4);
    
    static final int VtblCall(final int n, final int n2, final int n3, final int n4, final int n5) {
        XPCOM.lock.lock();
        try {
            return _VtblCall(n, n2, n3, n4, n5);
        }
        finally {
            XPCOM.lock.unlock();
        }
    }
    
    static final native int _VtblCall(final int p0, final int p1, final int p2, final int p3, final long[] p4);
    
    static final int VtblCall(final int n, final int n2, final int n3, final int n4, final long[] array) {
        XPCOM.lock.lock();
        try {
            return _VtblCall(n, n2, n3, n4, array);
        }
        finally {
            XPCOM.lock.unlock();
        }
    }
    
    static final native int _VtblCall(final int p0, final int p1, final int p2, final int p3, final int[] p4);
    
    static final int VtblCall(final int n, final int n2, final int n3, final int n4, final int[] array) {
        XPCOM.lock.lock();
        try {
            return _VtblCall(n, n2, n3, n4, array);
        }
        finally {
            XPCOM.lock.unlock();
        }
    }
    
    static final native int _VtblCall(final int p0, final int p1, final long p2, final long p3, final long[] p4);
    
    static final int VtblCall(final int n, final int n2, final long n3, final long n4, final long[] array) {
        XPCOM.lock.lock();
        try {
            return _VtblCall(n, n2, n3, n4, array);
        }
        finally {
            XPCOM.lock.unlock();
        }
    }
    
    static final native int _VtblCall(final int p0, final int p1, final long p2, final int p3, final long[] p4);
    
    static final int VtblCall(final int n, final int n2, final long n3, final int n4, final long[] array) {
        XPCOM.lock.lock();
        try {
            return _VtblCall(n, n2, n3, n4, array);
        }
        finally {
            XPCOM.lock.unlock();
        }
    }
    
    static final native int _VtblCall(final int p0, final int p1, final long p2, final long p3, final int[] p4);
    
    static final int VtblCall(final int n, final int n2, final long n3, final long n4, final int[] array) {
        XPCOM.lock.lock();
        try {
            return _VtblCall(n, n2, n3, n4, array);
        }
        finally {
            XPCOM.lock.unlock();
        }
    }
    
    static final native int _VtblCall(final int p0, final int p1, final int p2, final nsID p3, final int[] p4);
    
    static final int VtblCall(final int n, final int n2, final int n3, final nsID nsID, final int[] array) {
        XPCOM.lock.lock();
        try {
            return _VtblCall(n, n2, n3, nsID, array);
        }
        finally {
            XPCOM.lock.unlock();
        }
    }
    
    static final native int _VtblCall(final int p0, final int p1, final long p2, final nsID p3, final long[] p4);
    
    static final int VtblCall(final int n, final int n2, final long n3, final nsID nsID, final long[] array) {
        XPCOM.lock.lock();
        try {
            return _VtblCall(n, n2, n3, nsID, array);
        }
        finally {
            XPCOM.lock.unlock();
        }
    }
    
    static final native int _VtblCall(final int p0, final int p1, final int p2, final int p3, final char[] p4);
    
    static final int VtblCall(final int n, final int n2, final int n3, final int n4, final char[] array) {
        XPCOM.lock.lock();
        try {
            return _VtblCall(n, n2, n3, n4, array);
        }
        finally {
            XPCOM.lock.unlock();
        }
    }
    
    static final native int _VtblCall(final int p0, final int p1, final nsID p2, final nsID p3, final long[] p4);
    
    static final int VtblCall(final int n, final int n2, final nsID nsID, final nsID nsID2, final long[] array) {
        XPCOM.lock.lock();
        try {
            return _VtblCall(n, n2, nsID, nsID2, array);
        }
        finally {
            XPCOM.lock.unlock();
        }
    }
    
    static final native int _VtblCall(final int p0, final int p1, final nsID p2, final nsID p3, final int[] p4);
    
    static final int VtblCall(final int n, final int n2, final nsID nsID, final nsID nsID2, final int[] array) {
        XPCOM.lock.lock();
        try {
            return _VtblCall(n, n2, nsID, nsID2, array);
        }
        finally {
            XPCOM.lock.unlock();
        }
    }
    
    static final native int _VtblCall(final int p0, final int p1, final byte[] p2, final byte[] p3, final int[] p4);
    
    static final int VtblCall(final int n, final int n2, final byte[] array, final byte[] array2, final int[] array3) {
        XPCOM.lock.lock();
        try {
            return _VtblCall(n, n2, array, array2, array3);
        }
        finally {
            XPCOM.lock.unlock();
        }
    }
    
    static final native int _VtblCall(final int p0, final int p1, final byte[] p2, final byte[] p3, final long[] p4);
    
    static final int VtblCall(final int n, final int n2, final byte[] array, final byte[] array2, final long[] array3) {
        XPCOM.lock.lock();
        try {
            return _VtblCall(n, n2, array, array2, array3);
        }
        finally {
            XPCOM.lock.unlock();
        }
    }
    
    static final native int _VtblCall(final int p0, final int p1, final byte[] p2, final int[] p3, final int[] p4);
    
    static final int VtblCall(final int n, final int n2, final byte[] array, final int[] array2, final int[] array3) {
        XPCOM.lock.lock();
        try {
            return _VtblCall(n, n2, array, array2, array3);
        }
        finally {
            XPCOM.lock.unlock();
        }
    }
    
    static final native int _VtblCall(final int p0, final int p1, final byte[] p2, final int[] p3, final long[] p4);
    
    static final int VtblCall(final int n, final int n2, final byte[] array, final int[] array2, final long[] array3) {
        XPCOM.lock.lock();
        try {
            return _VtblCall(n, n2, array, array2, array3);
        }
        finally {
            XPCOM.lock.unlock();
        }
    }
    
    static final native int _VtblCall(final int p0, final int p1, final byte[] p2, final long[] p3, final int[] p4);
    
    static final int VtblCall(final int n, final int n2, final byte[] array, final long[] array2, final int[] array3) {
        XPCOM.lock.lock();
        try {
            return _VtblCall(n, n2, array, array2, array3);
        }
        finally {
            XPCOM.lock.unlock();
        }
    }
    
    static final native int _VtblCall(final int p0, final int p1, final byte[] p2, final nsID p3, final int p4);
    
    static final int VtblCall(final int n, final int n2, final byte[] array, final nsID nsID, final int n3) {
        XPCOM.lock.lock();
        try {
            return _VtblCall(n, n2, array, nsID, n3);
        }
        finally {
            XPCOM.lock.unlock();
        }
    }
    
    static final native int _VtblCall(final int p0, final int p1, final byte[] p2, final nsID p3, final long p4);
    
    static final int VtblCall(final int n, final int n2, final byte[] array, final nsID nsID, final long n3) {
        XPCOM.lock.lock();
        try {
            return _VtblCall(n, n2, array, nsID, n3);
        }
        finally {
            XPCOM.lock.unlock();
        }
    }
    
    static final native int _VtblCall(final int p0, final int p1, final int p2, final byte[] p3, final char[] p4);
    
    static final int VtblCall(final int n, final int n2, final int n3, final byte[] array, final char[] array2) {
        XPCOM.lock.lock();
        try {
            return _VtblCall(n, n2, n3, array, array2);
        }
        finally {
            XPCOM.lock.unlock();
        }
    }
    
    static final native int _VtblCall(final int p0, final int p1, final long p2, final byte[] p3, final char[] p4);
    
    static final int VtblCall(final int n, final int n2, final long n3, final byte[] array, final char[] array2) {
        XPCOM.lock.lock();
        try {
            return _VtblCall(n, n2, n3, array, array2);
        }
        finally {
            XPCOM.lock.unlock();
        }
    }
    
    static final native int _VtblCall(final int p0, final int p1, final int p2, final int p3, final byte[] p4);
    
    static final int VtblCall(final int n, final int n2, final int n3, final int n4, final byte[] array) {
        XPCOM.lock.lock();
        try {
            return _VtblCall(n, n2, n3, n4, array);
        }
        finally {
            XPCOM.lock.unlock();
        }
    }
    
    static final native int _VtblCall(final int p0, final int p1, final long p2, final long p3, final byte[] p4);
    
    static final int VtblCall(final int n, final int n2, final long n3, final long n4, final byte[] array) {
        XPCOM.lock.lock();
        try {
            return _VtblCall(n, n2, n3, n4, array);
        }
        finally {
            XPCOM.lock.unlock();
        }
    }
    
    static final native int _VtblCall(final int p0, final int p1, final byte[] p2, final byte[] p3, final int p4);
    
    static final int VtblCall(final int n, final int n2, final byte[] array, final byte[] array2, final int n3) {
        XPCOM.lock.lock();
        try {
            return _VtblCall(n, n2, array, array2, n3);
        }
        finally {
            XPCOM.lock.unlock();
        }
    }
    
    static final native int _VtblCall(final int p0, final int p1, final int p2, final byte[] p3, final int[] p4);
    
    static final int VtblCall(final int n, final int n2, final int n3, final byte[] array, final int[] array2) {
        XPCOM.lock.lock();
        try {
            return _VtblCall(n, n2, n3, array, array2);
        }
        finally {
            XPCOM.lock.unlock();
        }
    }
    
    static final native int _VtblCall(final int p0, final int p1, final long p2, final byte[] p3, final long p4);
    
    static final int VtblCall(final int n, final int n2, final long n3, final byte[] array, final long n4) {
        XPCOM.lock.lock();
        try {
            return _VtblCall(n, n2, n3, array, n4);
        }
        finally {
            XPCOM.lock.unlock();
        }
    }
    
    static final native int _VtblCall(final int p0, final int p1, final int p2, final byte[] p3, final int p4);
    
    static final int VtblCall(final int n, final int n2, final int n3, final byte[] array, final int n4) {
        XPCOM.lock.lock();
        try {
            return _VtblCall(n, n2, n3, array, n4);
        }
        finally {
            XPCOM.lock.unlock();
        }
    }
    
    static final native int _VtblCall(final int p0, final int p1, final long p2, final byte[] p3, final int p4);
    
    static final int VtblCall(final int n, final int n2, final long n3, final byte[] array, final int n4) {
        XPCOM.lock.lock();
        try {
            return _VtblCall(n, n2, n3, array, n4);
        }
        finally {
            XPCOM.lock.unlock();
        }
    }
    
    static final native int _VtblCall(final int p0, final int p1, final int p2, final byte[] p3, final short p4);
    
    static final int VtblCall(final int n, final int n2, final int n3, final byte[] array, final short n4) {
        XPCOM.lock.lock();
        try {
            return _VtblCall(n, n2, n3, array, n4);
        }
        finally {
            XPCOM.lock.unlock();
        }
    }
    
    static final native int _VtblCall(final int p0, final int p1, final long p2, final byte[] p3, final short p4);
    
    static final int VtblCall(final int n, final int n2, final long n3, final byte[] array, final short n4) {
        XPCOM.lock.lock();
        try {
            return _VtblCall(n, n2, n3, array, n4);
        }
        finally {
            XPCOM.lock.unlock();
        }
    }
    
    static final native int _VtblCall(final int p0, final int p1, final byte[] p2, final int p3, final nsID p4, final int[] p5);
    
    static final int VtblCall(final int n, final int n2, final byte[] array, final int n3, final nsID nsID, final int[] array2) {
        XPCOM.lock.lock();
        try {
            return _VtblCall(n, n2, array, n3, nsID, array2);
        }
        finally {
            XPCOM.lock.unlock();
        }
    }
    
    static final native int _VtblCall(final int p0, final int p1, final byte[] p2, final long p3, final nsID p4, final long[] p5);
    
    static final int VtblCall(final int n, final int n2, final byte[] array, final long n3, final nsID nsID, final long[] array2) {
        XPCOM.lock.lock();
        try {
            return _VtblCall(n, n2, array, n3, nsID, array2);
        }
        finally {
            XPCOM.lock.unlock();
        }
    }
    
    static final native int _VtblCall(final int p0, final int p1, final int p2, final byte[] p3, final int p4, final int[] p5);
    
    static final int VtblCall(final int n, final int n2, final int n3, final byte[] array, final int n4, final int[] array2) {
        XPCOM.lock.lock();
        try {
            return _VtblCall(n, n2, n3, array, n4, array2);
        }
        finally {
            XPCOM.lock.unlock();
        }
    }
    
    static final native int _VtblCall(final int p0, final int p1, final long p2, final byte[] p3, final long p4, final long[] p5);
    
    static final int VtblCall(final int n, final int n2, final long n3, final byte[] array, final long n4, final long[] array2) {
        XPCOM.lock.lock();
        try {
            return _VtblCall(n, n2, n3, array, n4, array2);
        }
        finally {
            XPCOM.lock.unlock();
        }
    }
    
    static final native int _VtblCall(final int p0, final int p1, final int p2, final char[] p3, final char[] p4, final int[] p5);
    
    static final int VtblCall(final int n, final int n2, final int n3, final char[] array, final char[] array2, final int[] array3) {
        XPCOM.lock.lock();
        try {
            return _VtblCall(n, n2, n3, array, array2, array3);
        }
        finally {
            XPCOM.lock.unlock();
        }
    }
    
    static final native int _VtblCall(final int p0, final int p1, final long p2, final char[] p3, final char[] p4, final long[] p5);
    
    static final int VtblCall(final int n, final int n2, final long n3, final char[] array, final char[] array2, final long[] array3) {
        XPCOM.lock.lock();
        try {
            return _VtblCall(n, n2, n3, array, array2, array3);
        }
        finally {
            XPCOM.lock.unlock();
        }
    }
    
    static final native int _VtblCall(final int p0, final int p1, final long p2, final char[] p3, final char[] p4, final int[] p5);
    
    static final int VtblCall(final int n, final int n2, final long n3, final char[] array, final char[] array2, final int[] array3) {
        XPCOM.lock.lock();
        try {
            return _VtblCall(n, n2, n3, array, array2, array3);
        }
        finally {
            XPCOM.lock.unlock();
        }
    }
    
    static final native int _VtblCall(final int p0, final int p1, final int p2, final int p3, final int p4, final char[] p5);
    
    static final int VtblCall(final int n, final int n2, final int n3, final int n4, final int n5, final char[] array) {
        XPCOM.lock.lock();
        try {
            return _VtblCall(n, n2, n3, n4, n5, array);
        }
        finally {
            XPCOM.lock.unlock();
        }
    }
    
    static final native int _VtblCall(final int p0, final int p1, final long p2, final long p3, final int p4, final char[] p5);
    
    static final int VtblCall(final int n, final int n2, final long n3, final long n4, final int n5, final char[] array) {
        XPCOM.lock.lock();
        try {
            return _VtblCall(n, n2, n3, n4, n5, array);
        }
        finally {
            XPCOM.lock.unlock();
        }
    }
    
    static final native int _VtblCall(final int p0, final int p1, final int p2, final int p3, final int p4, final int p5);
    
    static final int VtblCall(final int n, final int n2, final int n3, final int n4, final int n5, final int n6) {
        XPCOM.lock.lock();
        try {
            return _VtblCall(n, n2, n3, n4, n5, n6);
        }
        finally {
            XPCOM.lock.unlock();
        }
    }
    
    static final native int _VtblCall(final int p0, final int p1, final long p2, final long p3, final int p4, final int p5);
    
    static final int VtblCall(final int n, final int n2, final long n3, final long n4, final int n5, final int n6) {
        XPCOM.lock.lock();
        try {
            return _VtblCall(n, n2, n3, n4, n5, n6);
        }
        finally {
            XPCOM.lock.unlock();
        }
    }
    
    static final native int _VtblCall(final int p0, final int p1, final long p2, final long p3, final long p4, final int p5);
    
    static final int VtblCall(final int n, final int n2, final long n3, final long n4, final long n5, final int n6) {
        XPCOM.lock.lock();
        try {
            return _VtblCall(n, n2, n3, n4, n5, n6);
        }
        finally {
            XPCOM.lock.unlock();
        }
    }
    
    static final native int _VtblCall(final int p0, final int p1, final int p2, final int p3, final int p4, final int[] p5);
    
    static final int VtblCall(final int n, final int n2, final int n3, final int n4, final int n5, final int[] array) {
        XPCOM.lock.lock();
        try {
            return _VtblCall(n, n2, n3, n4, n5, array);
        }
        finally {
            XPCOM.lock.unlock();
        }
    }
    
    static final native int _VtblCall(final int p0, final int p1, final long p2, final long p3, final long p4, final int[] p5);
    
    static final int VtblCall(final int n, final int n2, final long n3, final long n4, final long n5, final int[] array) {
        XPCOM.lock.lock();
        try {
            return _VtblCall(n, n2, n3, n4, n5, array);
        }
        finally {
            XPCOM.lock.unlock();
        }
    }
    
    static final native int _VtblCall(final int p0, final int p1, final long p2, final long p3, final long p4, final long[] p5);
    
    static final int VtblCall(final int n, final int n2, final long n3, final long n4, final long n5, final long[] array) {
        XPCOM.lock.lock();
        try {
            return _VtblCall(n, n2, n3, n4, n5, array);
        }
        finally {
            XPCOM.lock.unlock();
        }
    }
    
    static final native int _VtblCall(final int p0, final int p1, final long p2, final long p3, final int p4, final int[] p5);
    
    static final int VtblCall(final int n, final int n2, final long n3, final long n4, final int n5, final int[] array) {
        XPCOM.lock.lock();
        try {
            return _VtblCall(n, n2, n3, n4, n5, array);
        }
        finally {
            XPCOM.lock.unlock();
        }
    }
    
    static final native int _VtblCall(final int p0, final int p1, final int[] p2, final int[] p3, final int[] p4, final int[] p5);
    
    static final int VtblCall(final int n, final int n2, final int[] array, final int[] array2, final int[] array3, final int[] array4) {
        XPCOM.lock.lock();
        try {
            return _VtblCall(n, n2, array, array2, array3, array4);
        }
        finally {
            XPCOM.lock.unlock();
        }
    }
    
    static final native int _VtblCall(final int p0, final int p1, final int p2, final int[] p3, final int[] p4, final int[] p5);
    
    static final int VtblCall(final int n, final int n2, final int n3, final int[] array, final int[] array2, final int[] array3) {
        XPCOM.lock.lock();
        try {
            return _VtblCall(n, n2, n3, array, array2, array3);
        }
        finally {
            XPCOM.lock.unlock();
        }
    }
    
    static final native int _VtblCall(final int p0, final int p1, final int p2, final int[] p3, final int[] p4, final long[] p5);
    
    static final int VtblCall(final int n, final int n2, final int n3, final int[] array, final int[] array2, final long[] array3) {
        XPCOM.lock.lock();
        try {
            return _VtblCall(n, n2, n3, array, array2, array3);
        }
        finally {
            XPCOM.lock.unlock();
        }
    }
    
    static final native int _VtblCall(final int p0, final int p1, final nsID p2, final byte[] p3, final byte[] p4, final int p5);
    
    static final int VtblCall(final int n, final int n2, final nsID nsID, final byte[] array, final byte[] array2, final int n3) {
        XPCOM.lock.lock();
        try {
            return _VtblCall(n, n2, nsID, array, array2, n3);
        }
        finally {
            XPCOM.lock.unlock();
        }
    }
    
    static final native int _VtblCall(final int p0, final int p1, final nsID p2, final byte[] p3, final byte[] p4, final long p5);
    
    static final int VtblCall(final int n, final int n2, final nsID nsID, final byte[] array, final byte[] array2, final long n3) {
        XPCOM.lock.lock();
        try {
            return _VtblCall(n, n2, nsID, array, array2, n3);
        }
        finally {
            XPCOM.lock.unlock();
        }
    }
    
    static final native int _VtblCall(final int p0, final int p1, final nsID p2, final int p3, final nsID p4, final int[] p5);
    
    static final int VtblCall(final int n, final int n2, final nsID nsID, final int n3, final nsID nsID2, final int[] array) {
        XPCOM.lock.lock();
        try {
            return _VtblCall(n, n2, nsID, n3, nsID2, array);
        }
        finally {
            XPCOM.lock.unlock();
        }
    }
    
    static final native int _VtblCall(final int p0, final int p1, final nsID p2, final long p3, final nsID p4, final long[] p5);
    
    static final int VtblCall(final int n, final int n2, final nsID nsID, final long n3, final nsID nsID2, final long[] array) {
        XPCOM.lock.lock();
        try {
            return _VtblCall(n, n2, nsID, n3, nsID2, array);
        }
        finally {
            XPCOM.lock.unlock();
        }
    }
    
    static final native int _VtblCall(final int p0, final int p1, final byte[] p2, final int p3, final int[] p4, final int[] p5);
    
    static final int VtblCall(final int n, final int n2, final byte[] array, final int n3, final int[] array2, final int[] array3) {
        XPCOM.lock.lock();
        try {
            return _VtblCall(n, n2, array, n3, array2, array3);
        }
        finally {
            XPCOM.lock.unlock();
        }
    }
    
    static final native int _VtblCall(final int p0, final int p1, final byte[] p2, final int p3, final long[] p4, final int[] p5);
    
    static final int VtblCall(final int n, final int n2, final byte[] array, final int n3, final long[] array2, final int[] array3) {
        XPCOM.lock.lock();
        try {
            return _VtblCall(n, n2, array, n3, array2, array3);
        }
        finally {
            XPCOM.lock.unlock();
        }
    }
    
    static final native int _VtblCall(final int p0, final int p1, final int p2, final int p3, final byte[] p4, final byte[] p5);
    
    static final int VtblCall(final int n, final int n2, final int n3, final int n4, final byte[] array, final byte[] array2) {
        XPCOM.lock.lock();
        try {
            return _VtblCall(n, n2, n3, n4, array, array2);
        }
        finally {
            XPCOM.lock.unlock();
        }
    }
    
    static final native int _VtblCall(final int p0, final int p1, final long p2, final long p3, final byte[] p4, final byte[] p5);
    
    static final int VtblCall(final int n, final int n2, final long n3, final long n4, final byte[] array, final byte[] array2) {
        XPCOM.lock.lock();
        try {
            return _VtblCall(n, n2, n3, n4, array, array2);
        }
        finally {
            XPCOM.lock.unlock();
        }
    }
    
    static final native int _VtblCall(final int p0, final int p1, final int p2, final int p3, final byte[] p4, final int p5);
    
    static final int VtblCall(final int n, final int n2, final int n3, final int n4, final byte[] array, final int n5) {
        XPCOM.lock.lock();
        try {
            return _VtblCall(n, n2, n3, n4, array, n5);
        }
        finally {
            XPCOM.lock.unlock();
        }
    }
    
    static final native int _VtblCall(final int p0, final int p1, final long p2, final long p3, final byte[] p4, final long p5);
    
    static final int VtblCall(final int n, final int n2, final long n3, final long n4, final byte[] array, final long n5) {
        XPCOM.lock.lock();
        try {
            return _VtblCall(n, n2, n3, n4, array, n5);
        }
        finally {
            XPCOM.lock.unlock();
        }
    }
    
    static final native int _VtblCall(final int p0, final int p1, final short[] p2, final int p3, final int[] p4, final int[] p5);
    
    static final int VtblCall(final int n, final int n2, final short[] array, final int n3, final int[] array2, final int[] array3) {
        XPCOM.lock.lock();
        try {
            return _VtblCall(n, n2, array, n3, array2, array3);
        }
        finally {
            XPCOM.lock.unlock();
        }
    }
    
    static final native int _VtblCall(final int p0, final int p1, final short[] p2, final long p3, final int[] p4, final long[] p5);
    
    static final int VtblCall(final int n, final int n2, final short[] array, final long n3, final int[] array2, final long[] array3) {
        XPCOM.lock.lock();
        try {
            return _VtblCall(n, n2, array, n3, array2, array3);
        }
        finally {
            XPCOM.lock.unlock();
        }
    }
    
    static final native int _VtblCall(final int p0, final int p1, final long p2, final int p3, final int p4, final int[] p5);
    
    static final int VtblCall(final int n, final int n2, final long n3, final int n4, final int n5, final int[] array) {
        XPCOM.lock.lock();
        try {
            return _VtblCall(n, n2, n3, n4, n5, array);
        }
        finally {
            XPCOM.lock.unlock();
        }
    }
    
    static final native int _VtblCall(final int p0, final int p1, final int p2, final int p3, final int[] p4, final int[] p5);
    
    static final int VtblCall(final int n, final int n2, final int n3, final int n4, final int[] array, final int[] array2) {
        XPCOM.lock.lock();
        try {
            return _VtblCall(n, n2, n3, n4, array, array2);
        }
        finally {
            XPCOM.lock.unlock();
        }
    }
    
    static final native int _VtblCall(final int p0, final int p1, final long p2, final long p3, final int[] p4, final int[] p5);
    
    static final int VtblCall(final int n, final int n2, final long n3, final long n4, final int[] array, final int[] array2) {
        XPCOM.lock.lock();
        try {
            return _VtblCall(n, n2, n3, n4, array, array2);
        }
        finally {
            XPCOM.lock.unlock();
        }
    }
    
    static final native int _VtblCall(final int p0, final int p1, final char[] p2, final int p3, final int p4, final int p5, final int p6);
    
    static final int VtblCall(final int n, final int n2, final char[] array, final int n3, final int n4, final int n5, final int n6) {
        XPCOM.lock.lock();
        try {
            return _VtblCall(n, n2, array, n3, n4, n5, n6);
        }
        finally {
            XPCOM.lock.unlock();
        }
    }
    
    static final native int _VtblCall(final int p0, final int p1, final char[] p2, final int p3, final long p4, final long p5, final long p6);
    
    static final int VtblCall(final int n, final int n2, final char[] array, final int n3, final long n4, final long n5, final long n6) {
        XPCOM.lock.lock();
        try {
            return _VtblCall(n, n2, array, n3, n4, n5, n6);
        }
        finally {
            XPCOM.lock.unlock();
        }
    }
    
    static final native int _VtblCall(final int p0, final int p1, final long p2, final long p3, final long p4, final long p5, final long p6);
    
    static final int VtblCall(final int n, final int n2, final long n3, final long n4, final long n5, final long n6, final long n7) {
        XPCOM.lock.lock();
        try {
            return _VtblCall(n, n2, n3, n4, n5, n6, n7);
        }
        finally {
            XPCOM.lock.unlock();
        }
    }
    
    static final native int _VtblCall(final int p0, final int p1, final int p2, final int p3, final int p4, final int p5, final int p6);
    
    static final int VtblCall(final int n, final int n2, final int n3, final int n4, final int n5, final int n6, final int n7) {
        XPCOM.lock.lock();
        try {
            return _VtblCall(n, n2, n3, n4, n5, n6, n7);
        }
        finally {
            XPCOM.lock.unlock();
        }
    }
    
    static final native int _VtblCall(final int p0, final int p1, final int p2, final int[] p3, final int[] p4, final int[] p5, final int[] p6);
    
    static final int VtblCall(final int n, final int n2, final int n3, final int[] array, final int[] array2, final int[] array3, final int[] array4) {
        XPCOM.lock.lock();
        try {
            return _VtblCall(n, n2, n3, array, array2, array3, array4);
        }
        finally {
            XPCOM.lock.unlock();
        }
    }
    
    static final native int _VtblCall(final int p0, final int p1, final int p2, final char[] p3, final char[] p4, final char[] p5, final int[] p6);
    
    static final int VtblCall(final int n, final int n2, final int n3, final char[] array, final char[] array2, final char[] array3, final int[] array4) {
        XPCOM.lock.lock();
        try {
            return _VtblCall(n, n2, n3, array, array2, array3, array4);
        }
        finally {
            XPCOM.lock.unlock();
        }
    }
    
    static final native int _VtblCall(final int p0, final int p1, final long p2, final char[] p3, final char[] p4, final char[] p5, final int[] p6);
    
    static final int VtblCall(final int n, final int n2, final long n3, final char[] array, final char[] array2, final char[] array3, final int[] array4) {
        XPCOM.lock.lock();
        try {
            return _VtblCall(n, n2, n3, array, array2, array3, array4);
        }
        finally {
            XPCOM.lock.unlock();
        }
    }
    
    static final native int _VtblCall(final int p0, final int p1, final byte[] p2, final int p3, final int p4, final int[] p5, final int[] p6);
    
    static final int VtblCall(final int n, final int n2, final byte[] array, final int n3, final int n4, final int[] array2, final int[] array3) {
        XPCOM.lock.lock();
        try {
            return _VtblCall(n, n2, array, n3, n4, array2, array3);
        }
        finally {
            XPCOM.lock.unlock();
        }
    }
    
    static final native int _VtblCall(final int p0, final int p1, final byte[] p2, final int p3, final long p4, final long[] p5, final int[] p6);
    
    static final int VtblCall(final int n, final int n2, final byte[] array, final int n3, final long n4, final long[] array2, final int[] array3) {
        XPCOM.lock.lock();
        try {
            return _VtblCall(n, n2, array, n3, n4, array2, array3);
        }
        finally {
            XPCOM.lock.unlock();
        }
    }
    
    static final native int _VtblCall(final int p0, final int p1, final int p2, final int p3, final byte[] p4, final int p5, final int p6);
    
    static final int VtblCall(final int n, final int n2, final int n3, final int n4, final byte[] array, final int n5, final int n6) {
        XPCOM.lock.lock();
        try {
            return _VtblCall(n, n2, n3, n4, array, n5, n6);
        }
        finally {
            XPCOM.lock.unlock();
        }
    }
    
    static final native int _VtblCall(final int p0, final int p1, final long p2, final long p3, final byte[] p4, final long p5, final int p6);
    
    static final int VtblCall(final int n, final int n2, final long n3, final long n4, final byte[] array, final long n5, final int n6) {
        XPCOM.lock.lock();
        try {
            return _VtblCall(n, n2, n3, n4, array, n5, n6);
        }
        finally {
            XPCOM.lock.unlock();
        }
    }
    
    static final native int _VtblCall(final int p0, final int p1, final int p2, final nsID p3, final int p4, final int p5, final int[] p6);
    
    static final int VtblCall(final int n, final int n2, final int n3, final nsID nsID, final int n4, final int n5, final int[] array) {
        XPCOM.lock.lock();
        try {
            return _VtblCall(n, n2, n3, nsID, n4, n5, array);
        }
        finally {
            XPCOM.lock.unlock();
        }
    }
    
    static final native int _VtblCall(final int p0, final int p1, final long p2, final nsID p3, final long p4, final long p5, final long[] p6);
    
    static final int VtblCall(final int n, final int n2, final long n3, final nsID nsID, final long n4, final long n5, final long[] array) {
        XPCOM.lock.lock();
        try {
            return _VtblCall(n, n2, n3, nsID, n4, n5, array);
        }
        finally {
            XPCOM.lock.unlock();
        }
    }
    
    static final native int _VtblCall(final int p0, final int p1, final long p2, final int p3, final long p4, final int p5, final int p6);
    
    static final int VtblCall(final int n, final int n2, final long n3, final int n4, final long n5, final int n6, final int n7) {
        XPCOM.lock.lock();
        try {
            return _VtblCall(n, n2, n3, n4, n5, n6, n7);
        }
        finally {
            XPCOM.lock.unlock();
        }
    }
    
    static final native int _VtblCall(final int p0, final int p1, final int p2, final byte[] p3, final byte[] p4, final byte[] p5, final int p6, final int[] p7);
    
    static final int VtblCall(final int n, final int n2, final int n3, final byte[] array, final byte[] array2, final byte[] array3, final int n4, final int[] array4) {
        XPCOM.lock.lock();
        try {
            return _VtblCall(n, n2, n3, array, array2, array3, n4, array4);
        }
        finally {
            XPCOM.lock.unlock();
        }
    }
    
    static final native int _VtblCall(final int p0, final int p1, final long p2, final byte[] p3, final byte[] p4, final byte[] p5, final long p6, final long[] p7);
    
    static final int VtblCall(final int n, final int n2, final long n3, final byte[] array, final byte[] array2, final byte[] array3, final long n4, final long[] array4) {
        XPCOM.lock.lock();
        try {
            return _VtblCall(n, n2, n3, array, array2, array3, n4, array4);
        }
        finally {
            XPCOM.lock.unlock();
        }
    }
    
    static final native int _VtblCall(final int p0, final int p1, final int p2, final int p3, final char[] p4, final int p5, final long p6, final int p7);
    
    static final int VtblCall(final int n, final int n2, final int n3, final int n4, final char[] array, final int n5, final long n6, final int n7) {
        XPCOM.lock.lock();
        try {
            return _VtblCall(n, n2, n3, n4, array, n5, n6, n7);
        }
        finally {
            XPCOM.lock.unlock();
        }
    }
    
    static final native int _VtblCall(final int p0, final int p1, final long p2, final long p3, final char[] p4, final int p5, final long p6, final int p7);
    
    static final int VtblCall(final int n, final int n2, final long n3, final long n4, final char[] array, final int n5, final long n6, final int n7) {
        XPCOM.lock.lock();
        try {
            return _VtblCall(n, n2, n3, n4, array, n5, n6, n7);
        }
        finally {
            XPCOM.lock.unlock();
        }
    }
    
    static final native int _VtblCall(final int p0, final int p1, final int p2, final int p3, final char[] p4, final long p5, final long p6, final long p7);
    
    static final int VtblCall(final int n, final int n2, final int n3, final int n4, final char[] array, final long n5, final long n6, final long n7) {
        XPCOM.lock.lock();
        try {
            return _VtblCall(n, n2, n3, n4, array, n5, n6, n7);
        }
        finally {
            XPCOM.lock.unlock();
        }
    }
    
    static final native int _VtblCall(final int p0, final int p1, final long p2, final long p3, final char[] p4, final long p5, final long p6, final long p7);
    
    static final int VtblCall(final int n, final int n2, final long n3, final long n4, final char[] array, final long n5, final long n6, final long n7) {
        XPCOM.lock.lock();
        try {
            return _VtblCall(n, n2, n3, n4, array, n5, n6, n7);
        }
        finally {
            XPCOM.lock.unlock();
        }
    }
    
    static final native int _VtblCall(final int p0, final int p1, final int[] p2, final int[] p3, final int[] p4, final int[] p5, final int[] p6, final int[] p7);
    
    static final int VtblCall(final int n, final int n2, final int[] array, final int[] array2, final int[] array3, final int[] array4, final int[] array5, final int[] array6) {
        XPCOM.lock.lock();
        try {
            return _VtblCall(n, n2, array, array2, array3, array4, array5, array6);
        }
        finally {
            XPCOM.lock.unlock();
        }
    }
    
    static final native int _VtblCall(final int p0, final int p1, final long[] p2, final long[] p3, final long[] p4, final long[] p5, final long[] p6, final int[] p7);
    
    static final int VtblCall(final int n, final int n2, final long[] array, final long[] array2, final long[] array3, final long[] array4, final long[] array5, final int[] array6) {
        XPCOM.lock.lock();
        try {
            return _VtblCall(n, n2, array, array2, array3, array4, array5, array6);
        }
        finally {
            XPCOM.lock.unlock();
        }
    }
    
    static final native int _VtblCall(final int p0, final int p1, final int p2, final int p3, final int p4, final int p5, final int p6, final int p7);
    
    static final int VtblCall(final int n, final int n2, final int n3, final int n4, final int n5, final int n6, final int n7, final int n8) {
        XPCOM.lock.lock();
        try {
            return _VtblCall(n, n2, n3, n4, n5, n6, n7, n8);
        }
        finally {
            XPCOM.lock.unlock();
        }
    }
    
    static final native int _VtblCall(final int p0, final int p1, final long p2, final long p3, final int p4, final int p5, final int p6, final int p7);
    
    static final int VtblCall(final int n, final int n2, final long n3, final long n4, final int n5, final int n6, final int n7, final int n8) {
        XPCOM.lock.lock();
        try {
            return _VtblCall(n, n2, n3, n4, n5, n6, n7, n8);
        }
        finally {
            XPCOM.lock.unlock();
        }
    }
    
    static final native int _VtblCall(final int p0, final int p1, final int p2, final int p3, final int p4, final int p5, final int p6, final int[] p7);
    
    static final int VtblCall(final int n, final int n2, final int n3, final int n4, final int n5, final int n6, final int n7, final int[] array) {
        XPCOM.lock.lock();
        try {
            return _VtblCall(n, n2, n3, n4, n5, n6, n7, array);
        }
        finally {
            XPCOM.lock.unlock();
        }
    }
    
    static final native int _VtblCall(final int p0, final int p1, final long p2, final long p3, final long p4, final long p5, final long p6, final long[] p7);
    
    static final int VtblCall(final int n, final int n2, final long n3, final long n4, final long n5, final long n6, final long n7, final long[] array) {
        XPCOM.lock.lock();
        try {
            return _VtblCall(n, n2, n3, n4, n5, n6, n7, array);
        }
        finally {
            XPCOM.lock.unlock();
        }
    }
    
    static final native int _VtblCall(final int p0, final int p1, final nsID p2, final byte[] p3, final byte[] p4, final int p5, final byte[] p6, final byte[] p7);
    
    static final int VtblCall(final int n, final int n2, final nsID nsID, final byte[] array, final byte[] array2, final int n3, final byte[] array3, final byte[] array4) {
        XPCOM.lock.lock();
        try {
            return _VtblCall(n, n2, nsID, array, array2, n3, array3, array4);
        }
        finally {
            XPCOM.lock.unlock();
        }
    }
    
    static final native int _VtblCall(final int p0, final int p1, final nsID p2, final byte[] p3, final byte[] p4, final long p5, final byte[] p6, final byte[] p7);
    
    static final int VtblCall(final int n, final int n2, final nsID nsID, final byte[] array, final byte[] array2, final long n3, final byte[] array3, final byte[] array4) {
        XPCOM.lock.lock();
        try {
            return _VtblCall(n, n2, nsID, array, array2, n3, array3, array4);
        }
        finally {
            XPCOM.lock.unlock();
        }
    }
    
    static final native int _VtblCall(final int p0, final int p1, final int p2, final int p3, final long p4, final long p5, final long p6, final long p7);
    
    static final int VtblCall(final int n, final int n2, final int n3, final int n4, final long n5, final long n6, final long n7, final long n8) {
        XPCOM.lock.lock();
        try {
            return _VtblCall(n, n2, n3, n4, n5, n6, n7, n8);
        }
        finally {
            XPCOM.lock.unlock();
        }
    }
    
    static final native int _VtblCall(final int p0, final int p1, final long p2, final long p3, final long p4, final long p5, final long p6, final long p7);
    
    static final int VtblCall(final int n, final int n2, final long n3, final long n4, final long n5, final long n6, final long n7, final long n8) {
        XPCOM.lock.lock();
        try {
            return _VtblCall(n, n2, n3, n4, n5, n6, n7, n8);
        }
        finally {
            XPCOM.lock.unlock();
        }
    }
    
    static final native int _VtblCall(final int p0, final int p1, final int p2, final char[] p3, final char[] p4, final char[] p5, final int[] p6, final int[] p7);
    
    static final int VtblCall(final int n, final int n2, final int n3, final char[] array, final char[] array2, final char[] array3, final int[] array4, final int[] array5) {
        XPCOM.lock.lock();
        try {
            return _VtblCall(n, n2, n3, array, array2, array3, array4, array5);
        }
        finally {
            XPCOM.lock.unlock();
        }
    }
    
    static final native int _VtblCall(final int p0, final int p1, final long p2, final char[] p3, final char[] p4, final char[] p5, final int[] p6, final int[] p7);
    
    static final int VtblCall(final int n, final int n2, final long n3, final char[] array, final char[] array2, final char[] array3, final int[] array4, final int[] array5) {
        XPCOM.lock.lock();
        try {
            return _VtblCall(n, n2, n3, array, array2, array3, array4, array5);
        }
        finally {
            XPCOM.lock.unlock();
        }
    }
    
    static final native int _VtblCall(final int p0, final int p1, final byte[] p2, final byte[] p3, final byte[] p4, final int p5, final int p6, final int[] p7);
    
    static final int VtblCall(final int n, final int n2, final byte[] array, final byte[] array2, final byte[] array3, final int n3, final int n4, final int[] array4) {
        XPCOM.lock.lock();
        try {
            return _VtblCall(n, n2, array, array2, array3, n3, n4, array4);
        }
        finally {
            XPCOM.lock.unlock();
        }
    }
    
    static final native int _VtblCall(final int p0, final int p1, final byte[] p2, final byte[] p3, final byte[] p4, final int p5, final int p6, final long[] p7);
    
    static final int VtblCall(final int n, final int n2, final byte[] array, final byte[] array2, final byte[] array3, final int n3, final int n4, final long[] array4) {
        XPCOM.lock.lock();
        try {
            return _VtblCall(n, n2, array, array2, array3, n3, n4, array4);
        }
        finally {
            XPCOM.lock.unlock();
        }
    }
    
    static final native int _VtblCall(final int p0, final int p1, final int p2, final int p3, final int p4, final int p5, final int[] p6, final int[] p7);
    
    static final int VtblCall(final int n, final int n2, final int n3, final int n4, final int n5, final int n6, final int[] array, final int[] array2) {
        XPCOM.lock.lock();
        try {
            return _VtblCall(n, n2, n3, n4, n5, n6, array, array2);
        }
        finally {
            XPCOM.lock.unlock();
        }
    }
    
    static final native int _VtblCall(final int p0, final int p1, final long p2, final int p3, final int p4, final long p5, final int[] p6, final long[] p7);
    
    static final int VtblCall(final int n, final int n2, final long n3, final int n4, final int n5, final long n6, final int[] array, final long[] array2) {
        XPCOM.lock.lock();
        try {
            return _VtblCall(n, n2, n3, n4, n5, n6, array, array2);
        }
        finally {
            XPCOM.lock.unlock();
        }
    }
    
    static final native int _VtblCall(final int p0, final int p1, final int p2, final int p3, final char[] p4, final char[] p5, final int p6, final int[] p7);
    
    static final int VtblCall(final int n, final int n2, final int n3, final int n4, final char[] array, final char[] array2, final int n5, final int[] array3) {
        XPCOM.lock.lock();
        try {
            return _VtblCall(n, n2, n3, n4, array, array2, n5, array3);
        }
        finally {
            XPCOM.lock.unlock();
        }
    }
    
    static final native int _VtblCall(final int p0, final int p1, final long p2, final long p3, final char[] p4, final char[] p5, final int p6, final long[] p7);
    
    static final int VtblCall(final int n, final int n2, final long n3, final long n4, final char[] array, final char[] array2, final int n5, final long[] array3) {
        XPCOM.lock.lock();
        try {
            return _VtblCall(n, n2, n3, n4, array, array2, n5, array3);
        }
        finally {
            XPCOM.lock.unlock();
        }
    }
    
    static final native int _VtblCall(final int p0, final int p1, final int p2, final int p3, final int p4, final byte[] p5, final byte[] p6, final int p7);
    
    static final int VtblCall(final int n, final int n2, final int n3, final int n4, final int n5, final byte[] array, final byte[] array2, final int n6) {
        XPCOM.lock.lock();
        try {
            return _VtblCall(n, n2, n3, n4, n5, array, array2, n6);
        }
        finally {
            XPCOM.lock.unlock();
        }
    }
    
    static final native int _VtblCall(final int p0, final int p1, final long p2, final long p3, final long p4, final byte[] p5, final byte[] p6, final long p7);
    
    static final int VtblCall(final int n, final int n2, final long n3, final long n4, final long n5, final byte[] array, final byte[] array2, final long n6) {
        XPCOM.lock.lock();
        try {
            return _VtblCall(n, n2, n3, n4, n5, array, array2, n6);
        }
        finally {
            XPCOM.lock.unlock();
        }
    }
    
    static final native int _VtblCall(final int p0, final int p1, final int p2, final int p3, final int p4, final int[] p5, final int[] p6, final int[] p7);
    
    static final int VtblCall(final int n, final int n2, final int n3, final int n4, final int n5, final int[] array, final int[] array2, final int[] array3) {
        XPCOM.lock.lock();
        try {
            return _VtblCall(n, n2, n3, n4, n5, array, array2, array3);
        }
        finally {
            XPCOM.lock.unlock();
        }
    }
    
    static final native int _VtblCall(final int p0, final int p1, final long p2, final int p3, final long p4, final int[] p5, final int[] p6, final int[] p7);
    
    static final int VtblCall(final int n, final int n2, final long n3, final int n4, final long n5, final int[] array, final int[] array2, final int[] array3) {
        XPCOM.lock.lock();
        try {
            return _VtblCall(n, n2, n3, n4, n5, array, array2, array3);
        }
        finally {
            XPCOM.lock.unlock();
        }
    }
    
    static final native int _VtblCall(final int p0, final int p1, final int p2, final char[] p3, final char[] p4, final int p5, final int[] p6, final int[] p7, final int[] p8);
    
    static final int VtblCall(final int n, final int n2, final int n3, final char[] array, final char[] array2, final int n4, final int[] array3, final int[] array4, final int[] array5) {
        XPCOM.lock.lock();
        try {
            return _VtblCall(n, n2, n3, array, array2, n4, array3, array4, array5);
        }
        finally {
            XPCOM.lock.unlock();
        }
    }
    
    static final native int _VtblCall(final int p0, final int p1, final long p2, final char[] p3, final char[] p4, final int p5, final long[] p6, final int[] p7, final int[] p8);
    
    static final int VtblCall(final int n, final int n2, final long n3, final char[] array, final char[] array2, final int n4, final long[] array3, final int[] array4, final int[] array5) {
        XPCOM.lock.lock();
        try {
            return _VtblCall(n, n2, n3, array, array2, n4, array3, array4, array5);
        }
        finally {
            XPCOM.lock.unlock();
        }
    }
    
    static final native int _VtblCall(final int p0, final int p1, final int p2, final char[] p3, final char[] p4, final int[] p5, final char[] p6, final int[] p7, final int[] p8);
    
    static final int VtblCall(final int n, final int n2, final int n3, final char[] array, final char[] array2, final int[] array3, final char[] array4, final int[] array5, final int[] array6) {
        XPCOM.lock.lock();
        try {
            return _VtblCall(n, n2, n3, array, array2, array3, array4, array5, array6);
        }
        finally {
            XPCOM.lock.unlock();
        }
    }
    
    static final native int _VtblCall(final int p0, final int p1, final long p2, final char[] p3, final char[] p4, final long[] p5, final char[] p6, final int[] p7, final int[] p8);
    
    static final int VtblCall(final int n, final int n2, final long n3, final char[] array, final char[] array2, final long[] array3, final char[] array4, final int[] array5, final int[] array6) {
        XPCOM.lock.lock();
        try {
            return _VtblCall(n, n2, n3, array, array2, array3, array4, array5, array6);
        }
        finally {
            XPCOM.lock.unlock();
        }
    }
    
    static final native int _VtblCall(final int p0, final int p1, final int p2, final int p3, final int p4, final int p5, final char[] p6, final int[] p7, final int[] p8);
    
    static final int VtblCall(final int n, final int n2, final int n3, final int n4, final int n5, final int n6, final char[] array, final int[] array2, final int[] array3) {
        XPCOM.lock.lock();
        try {
            return _VtblCall(n, n2, n3, n4, n5, n6, array, array2, array3);
        }
        finally {
            XPCOM.lock.unlock();
        }
    }
    
    static final native int _VtblCall(final int p0, final int p1, final long p2, final long p3, final int p4, final long p5, final char[] p6, final int[] p7, final int[] p8);
    
    static final int VtblCall(final int n, final int n2, final long n3, final long n4, final int n5, final long n6, final char[] array, final int[] array2, final int[] array3) {
        XPCOM.lock.lock();
        try {
            return _VtblCall(n, n2, n3, n4, n5, n6, array, array2, array3);
        }
        finally {
            XPCOM.lock.unlock();
        }
    }
    
    static final native int _VtblCall(final int p0, final int p1, final int p2, final int p3, final int p4, final int p5, final long p6, final int p7, final int p8);
    
    static final int VtblCall(final int n, final int n2, final int n3, final int n4, final int n5, final int n6, final long n7, final int n8, final int n9) {
        XPCOM.lock.lock();
        try {
            return _VtblCall(n, n2, n3, n4, n5, n6, n7, n8, n9);
        }
        finally {
            XPCOM.lock.unlock();
        }
    }
    
    static final native int _VtblCall(final int p0, final int p1, final long p2, final long p3, final long p4, final long p5, final long p6, final long p7, final long p8);
    
    static final int VtblCall(final int n, final int n2, final long n3, final long n4, final long n5, final long n6, final long n7, final long n8, final long n9) {
        XPCOM.lock.lock();
        try {
            return _VtblCall(n, n2, n3, n4, n5, n6, n7, n8, n9);
        }
        finally {
            XPCOM.lock.unlock();
        }
    }
    
    static final native int _VtblCall(final int p0, final int p1, final int p2, final int p3, final int p4, final int p5, final int[] p6, final int[] p7, final int[] p8);
    
    static final int VtblCall(final int n, final int n2, final int n3, final int n4, final int n5, final int n6, final int[] array, final int[] array2, final int[] array3) {
        XPCOM.lock.lock();
        try {
            return _VtblCall(n, n2, n3, n4, n5, n6, array, array2, array3);
        }
        finally {
            XPCOM.lock.unlock();
        }
    }
    
    static final native int _VtblCall(final int p0, final int p1, final long p2, final int p3, final long p4, final long p5, final int[] p6, final int[] p7, final int[] p8);
    
    static final int VtblCall(final int n, final int n2, final long n3, final int n4, final long n5, final long n6, final int[] array, final int[] array2, final int[] array3) {
        XPCOM.lock.lock();
        try {
            return _VtblCall(n, n2, n3, n4, n5, n6, array, array2, array3);
        }
        finally {
            XPCOM.lock.unlock();
        }
    }
    
    static final native int _VtblCall(final int p0, final int p1, final int p2, final char[] p3, final char[] p4, final int[] p5, final int[] p6, final char[] p7, final int[] p8, final int[] p9);
    
    static final int VtblCall(final int n, final int n2, final int n3, final char[] array, final char[] array2, final int[] array3, final int[] array4, final char[] array5, final int[] array6, final int[] array7) {
        XPCOM.lock.lock();
        try {
            return _VtblCall(n, n2, n3, array, array2, array3, array4, array5, array6, array7);
        }
        finally {
            XPCOM.lock.unlock();
        }
    }
    
    static final native int _VtblCall(final int p0, final int p1, final long p2, final char[] p3, final char[] p4, final long[] p5, final long[] p6, final char[] p7, final int[] p8, final int[] p9);
    
    static final int VtblCall(final int n, final int n2, final long n3, final char[] array, final char[] array2, final long[] array3, final long[] array4, final char[] array5, final int[] array6, final int[] array7) {
        XPCOM.lock.lock();
        try {
            return _VtblCall(n, n2, n3, array, array2, array3, array4, array5, array6, array7);
        }
        finally {
            XPCOM.lock.unlock();
        }
    }
    
    static final native int _VtblCall(final int p0, final int p1, final int p2, final int p3, final int p4, final int p5, final int p6, final int p7, final int p8, final int[] p9);
    
    static final int VtblCall(final int n, final int n2, final int n3, final int n4, final int n5, final int n6, final int n7, final int n8, final int n9, final int[] array) {
        XPCOM.lock.lock();
        try {
            return _VtblCall(n, n2, n3, n4, n5, n6, n7, n8, n9, array);
        }
        finally {
            XPCOM.lock.unlock();
        }
    }
    
    static final native int _VtblCall(final int p0, final int p1, final int p2, final long p3, final long p4, final long p5, final long p6, final long p7, final long p8, final long[] p9);
    
    static final int VtblCall(final int n, final int n2, final int n3, final long n4, final long n5, final long n6, final long n7, final long n8, final long n9, final long[] array) {
        XPCOM.lock.lock();
        try {
            return _VtblCall(n, n2, n3, n4, n5, n6, n7, n8, n9, array);
        }
        finally {
            XPCOM.lock.unlock();
        }
    }
    
    static final native int _VtblCall(final int p0, final int p1, final int p2, final int p3, final int p4, final int p5, final int p6, final int p7, final char[] p8, final int[] p9, final int[] p10);
    
    static final int VtblCall(final int n, final int n2, final int n3, final int n4, final int n5, final int n6, final int n7, final int n8, final char[] array, final int[] array2, final int[] array3) {
        XPCOM.lock.lock();
        try {
            return _VtblCall(n, n2, n3, n4, n5, n6, n7, n8, array, array2, array3);
        }
        finally {
            XPCOM.lock.unlock();
        }
    }
    
    static final native int _VtblCall(final int p0, final int p1, final long p2, final long p3, final long p4, final long p5, final int p6, final long p7, final char[] p8, final int[] p9, final long[] p10);
    
    static final int VtblCall(final int n, final int n2, final long n3, final long n4, final long n5, final long n6, final int n7, final long n8, final char[] array, final int[] array2, final long[] array3) {
        XPCOM.lock.lock();
        try {
            return _VtblCall(n, n2, n3, n4, n5, n6, n7, n8, array, array2, array3);
        }
        finally {
            XPCOM.lock.unlock();
        }
    }
    
    static final native int _VtblCall(final int p0, final int p1, final int p2, final int p3, final int p4, final int p5, final int p6, final int p7, final int p8, final int p9, final int p10, final int p11);
    
    static final int VtblCall(final int n, final int n2, final int n3, final int n4, final int n5, final int n6, final int n7, final int n8, final int n9, final int n10, final int n11, final int n12) {
        XPCOM.lock.lock();
        try {
            return _VtblCall(n, n2, n3, n4, n5, n6, n7, n8, n9, n10, n11, n12);
        }
        finally {
            XPCOM.lock.unlock();
        }
    }
    
    static final native int _VtblCall(final int p0, final int p1, final long p2, final int p3, final int p4, final long p5, final int p6, final int p7, final int p8, final int p9, final int p10, final int p11);
    
    static final int VtblCall(final int n, final int n2, final long n3, final int n4, final int n5, final long n6, final int n7, final int n8, final int n9, final int n10, final int n11, final int n12) {
        XPCOM.lock.lock();
        try {
            return _VtblCall(n, n2, n3, n4, n5, n6, n7, n8, n9, n10, n11, n12);
        }
        finally {
            XPCOM.lock.unlock();
        }
    }
    
    static final native int _VtblCall(final int p0, final int p1, final int p2, final char[] p3, final char[] p4, final int p5, final char[] p6, final char[] p7, final char[] p8, final char[] p9, final int[] p10, final int[] p11);
    
    static final int VtblCall(final int n, final int n2, final int n3, final char[] array, final char[] array2, final int n4, final char[] array3, final char[] array4, final char[] array5, final char[] array6, final int[] array7, final int[] array8) {
        XPCOM.lock.lock();
        try {
            return _VtblCall(n, n2, n3, array, array2, n4, array3, array4, array5, array6, array7, array8);
        }
        finally {
            XPCOM.lock.unlock();
        }
    }
    
    static final native int _VtblCall(final int p0, final int p1, final long p2, final char[] p3, final char[] p4, final int p5, final char[] p6, final char[] p7, final char[] p8, final char[] p9, final int[] p10, final int[] p11);
    
    static final int VtblCall(final int n, final int n2, final long n3, final char[] array, final char[] array2, final int n4, final char[] array3, final char[] array4, final char[] array5, final char[] array6, final int[] array7, final int[] array8) {
        XPCOM.lock.lock();
        try {
            return _VtblCall(n, n2, n3, array, array2, n4, array3, array4, array5, array6, array7, array8);
        }
        finally {
            XPCOM.lock.unlock();
        }
    }
    
    static final native int _VtblCall(final int p0, final int p1, final int p2, final int p3, final int p4, final int p5, final char[] p6, final int p7, final int p8, final int p9, final int p10, final int p11, final int[] p12, final int[] p13);
    
    static final int VtblCall(final int n, final int n2, final int n3, final int n4, final int n5, final int n6, final char[] array, final int n7, final int n8, final int n9, final int n10, final int n11, final int[] array2, final int[] array3) {
        XPCOM.lock.lock();
        try {
            return _VtblCall(n, n2, n3, n4, n5, n6, array, n7, n8, n9, n10, n11, array2, array3);
        }
        finally {
            XPCOM.lock.unlock();
        }
    }
    
    static final native int _VtblCall(final int p0, final int p1, final long p2, final long p3, final long p4, final int p5, final char[] p6, final long p7, final long p8, final int p9, final long p10, final int p11, final long[] p12, final long[] p13);
    
    static final int VtblCall(final int n, final int n2, final long n3, final long n4, final long n5, final int n6, final char[] array, final long n7, final long n8, final int n9, final long n10, final int n11, final long[] array2, final long[] array3) {
        XPCOM.lock.lock();
        try {
            return _VtblCall(n, n2, n3, n4, n5, n6, array, n7, n8, n9, n10, n11, array2, array3);
        }
        finally {
            XPCOM.lock.unlock();
        }
    }
    
    static final native int _VtblCall(final int p0, final int p1, final int p2, final int p3, final int p4, final int p5, final char[] p6, final byte[] p7, final int p8, final int p9, final int p10, final int p11, final int p12, final int[] p13, final int[] p14);
    
    static final int VtblCall(final int n, final int n2, final int n3, final int n4, final int n5, final int n6, final char[] array, final byte[] array2, final int n7, final int n8, final int n9, final int n10, final int n11, final int[] array3, final int[] array4) {
        XPCOM.lock.lock();
        try {
            return _VtblCall(n, n2, n3, n4, n5, n6, array, array2, n7, n8, n9, n10, n11, array3, array4);
        }
        finally {
            XPCOM.lock.unlock();
        }
    }
    
    static final native int _VtblCall(final int p0, final int p1, final long p2, final long p3, final long p4, final int p5, final char[] p6, final byte[] p7, final long p8, final long p9, final int p10, final long p11, final int p12, final long[] p13, final long[] p14);
    
    static final int VtblCall(final int n, final int n2, final long n3, final long n4, final long n5, final int n6, final char[] array, final byte[] array2, final long n7, final long n8, final int n9, final long n10, final int n11, final long[] array3, final long[] array4) {
        XPCOM.lock.lock();
        try {
            return _VtblCall(n, n2, n3, n4, n5, n6, array, array2, n7, n8, n9, n10, n11, array3, array4);
        }
        finally {
            XPCOM.lock.unlock();
        }
    }
    
    static final native int _VtblCall(final int p0, final int p1, final int p2, final int p3, final int p4, final int p5, final int p6, final int p7, final int p8, final int p9, final int p10, final int p11, final int p12, final int p13, final int p14, final short p15, final int p16);
    
    static final int VtblCall(final int n, final int n2, final int n3, final int n4, final int n5, final int n6, final int n7, final int n8, final int n9, final int n10, final int n11, final int n12, final int n13, final int n14, final int n15, final short n16, final int n17) {
        XPCOM.lock.lock();
        try {
            return _VtblCall(n, n2, n3, n4, n5, n6, n7, n8, n9, n10, n11, n12, n13, n14, n15, n16, n17);
        }
        finally {
            XPCOM.lock.unlock();
        }
    }
    
    static final native int _VtblCall(final int p0, final int p1, final long p2, final int p3, final int p4, final long p5, final int p6, final int p7, final int p8, final int p9, final int p10, final int p11, final int p12, final int p13, final int p14, final short p15, final long p16);
    
    static final int VtblCall(final int n, final int n2, final long n3, final int n4, final int n5, final long n6, final int n7, final int n8, final int n9, final int n10, final int n11, final int n12, final int n13, final int n14, final int n15, final short n16, final long n17) {
        XPCOM.lock.lock();
        try {
            return _VtblCall(n, n2, n3, n4, n5, n6, n7, n8, n9, n10, n11, n12, n13, n14, n15, n16, n17);
        }
        finally {
            XPCOM.lock.unlock();
        }
    }
}
