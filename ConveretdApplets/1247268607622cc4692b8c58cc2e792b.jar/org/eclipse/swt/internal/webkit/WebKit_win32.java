// 
// Decompiled by Procyon v0.5.30
// 

package org.eclipse.swt.internal.webkit;

import org.eclipse.swt.internal.win32.OS;
import org.eclipse.swt.internal.ole.win32.COM;
import org.eclipse.swt.internal.ole.win32.GUID;
import org.eclipse.swt.internal.C;

public class WebKit_win32 extends C
{
    public static final GUID CLSID_WebCookieManager;
    public static final GUID CLSID_WebMutableURLRequest;
    public static final GUID CLSID_WebPreferences;
    public static final GUID CLSID_WebURLCredential;
    public static final GUID CLSID_WebView;
    public static final GUID IID_IWebCookieManager;
    public static final GUID IID_IWebDownloadDelegate;
    public static final GUID IID_IWebErrorPrivate;
    public static final GUID IID_IWebFrameLoadDelegate;
    public static final GUID IID_IWebFramePrivate;
    public static final GUID IID_IWebIBActions;
    public static final GUID IID_IWebMutableURLRequest;
    public static final GUID IID_IWebMutableURLRequestPrivate;
    public static final GUID IID_IWebPolicyDelegate;
    public static final GUID IID_IWebPreferences;
    public static final GUID IID_IWebResourceLoadDelegate;
    public static final GUID IID_IWebUIDelegate;
    public static final GUID IID_IWebURLCredential;
    public static final GUID IID_IWebView;
    public static final GUID IID_IWebViewPrivate;
    public static final int CFHTTPCookieSessionOnlyFlag = 2;
    public static final int FontSmoothingTypeWindows = 4;
    public static final int kCFStringEncodingUTF8 = 134217984;
    public static final int kJSTypeUndefined = 0;
    public static final int kJSTypeNull = 1;
    public static final int kJSTypeBoolean = 2;
    public static final int kJSTypeNumber = 3;
    public static final int kJSTypeString = 4;
    public static final int kJSTypeObject = 5;
    public static final int WebURLCredentialPersistenceForSession = 1;
    public static final int WebURLErrorBadURL = -1000;
    public static final int WebURLErrorServerCertificateNotYetValid = -1204;
    public static final int WebURLErrorSecureConnectionFailed = -1200;
    
    static {
        CLSID_WebCookieManager = IIDFromString("{3F35F332-BB2B-49b3-AEDD-27B317687E07}");
        CLSID_WebMutableURLRequest = IIDFromString("{a062ecc3-bb1b-4694-a569-f59e0ad6be0c}");
        CLSID_WebPreferences = IIDFromString("{67B89F90-F778-438B-ABBF-34D1ACBF8651}");
        CLSID_WebURLCredential = IIDFromString("{7433F53B-7FE9-484a-9432-72909457A646}");
        CLSID_WebView = IIDFromString("{d6bca079-f61c-4e1e-b453-32a0477d02e3}");
        IID_IWebCookieManager = IIDFromString("{7053FE94-3623-444f-A298-209A90879A8C}");
        IID_IWebDownloadDelegate = IIDFromString("{16A32AE6-C862-40cd-9225-2CAF823F40F9}");
        IID_IWebErrorPrivate = IIDFromString("{19FED49C-7016-48a6-B5C6-07ADE116531B}");
        IID_IWebFrameLoadDelegate = IIDFromString("{3354665B-84BA-4fdf-B35E-BF5CF9D96026}");
        IID_IWebFramePrivate = IIDFromString("{A1657D07-4881-4475-9D10-76548731D448}");
        IID_IWebIBActions = IIDFromString("{8F0E3A30-B924-44f8-990A-1AE61ED6C632}");
        IID_IWebMutableURLRequest = IIDFromString("{C4042773-371F-427e-AFA9-9D4B358A0D93}");
        IID_IWebMutableURLRequestPrivate = IIDFromString("{AD675B60-2CE9-478c-B2AA-CAD643FF18AC}");
        IID_IWebPolicyDelegate = IIDFromString("{9B0BAE6C-A496-4000-9E22-2E89F0747401}");
        IID_IWebPreferences = IIDFromString("{0930D594-A5A3-46e1-858E-AB17A13CD28E}");
        IID_IWebResourceLoadDelegate = IIDFromString("{AF3289AA-90DB-4ca4-A112-A1E5F0517953}");
        IID_IWebUIDelegate = IIDFromString("{042B7EE3-A5A4-4a8f-8C33-775CD9E89C7C}");
        IID_IWebURLCredential = IIDFromString("{A1E9D765-FACE-4189-BBE3-AED7EBF65EBD}");
        IID_IWebView = IIDFromString("{174BBEFD-058E-49C7-91DF-6F110AA4AC28}");
        IID_IWebViewPrivate = IIDFromString("{44914369-DEB5-4fcf-A6A3-30C02E73154F}");
    }
    
    static GUID IIDFromString(final String s) {
        final int length = s.length();
        final char[] array = new char[length + 1];
        s.getChars(0, length, array, 0);
        final GUID guid = new GUID();
        if (COM.IIDFromString(array, guid) == 0) {
            return guid;
        }
        return null;
    }
    
    public static final native int CFArrayGetCount(final int p0);
    
    public static final native int CFArrayGetValueAtIndex(final int p0, final int p1);
    
    public static final native int CFDataCreate(final int p0, final byte[] p1, final int p2);
    
    public static final native int CFDataGetBytePtr(final int p0);
    
    public static final native int CFDataGetLength(final int p0);
    
    public static final native int CFDictionaryCreate(final int p0, final int[] p1, final int[] p2, final int p3, final int p4, final int p5);
    
    public static final native int CFHTTPCookieCreateWithResponseHeaderFields(final int p0, final int p1, final int p2);
    
    public static final native int CFHTTPCookieGetFlags(final int p0);
    
    public static final native int CFHTTPCookieGetName(final int p0);
    
    public static final native int CFHTTPCookieGetValue(final int p0);
    
    public static final native int CFHTTPCookieStorageCopyCookies(final int p0);
    
    public static final native int CFHTTPCookieStorageCopyCookiesForURL(final int p0, final int p1, final boolean p2);
    
    public static final native void CFHTTPCookieStorageDeleteCookie(final int p0, final int p1);
    
    public static final native void CFHTTPCookieStorageSetCookie(final int p0, final int p1);
    
    public static final native void CFRelease(final int p0);
    
    public static final native int CFStringCreateWithCharacters(final int p0, final char[] p1, final int p2);
    
    public static final native char CFStringGetCharacterAtIndex(final int p0, final int p1);
    
    public static final native int CFStringGetCharactersPtr(final int p0);
    
    public static final native int CFStringGetLength(final int p0);
    
    public static final native int CFURLCreateWithString(final int p0, final int p1, final int p2);
    
    public static final native int CFURLRequestCreateMutableCopy(final int p0, final int p1);
    
    public static final native int CFURLRequestCopyHTTPRequestBody(final int p0);
    
    public static final native void CFURLRequestSetHTTPRequestBody(final int p0, final int p1);
    
    public static final native void CFURLRequestSetURL(final int p0, final int p1);
    
    public static final native int JSClassCreate(final int p0);
    
    public static final native int JSClassRetain(final int p0);
    
    public static final native int JSContextGetGlobalObject(final int p0);
    
    public static final native int JSEvaluateScript(final int p0, final int p1, final int p2, final int p3, final int p4, final int[] p5);
    
    public static final native int JSGlobalContextRetain(final int p0);
    
    public static final native int JSObjectGetPrivate(final int p0);
    
    public static final native int JSObjectGetProperty(final int p0, final int p1, final int p2, final int[] p3);
    
    public static final native int JSObjectGetPropertyAtIndex(final int p0, final int p1, final int p2, final int[] p3);
    
    public static final native int JSObjectMake(final int p0, final int p1, final int p2);
    
    public static final native int JSObjectMakeArray(final int p0, final int p1, final int[] p2, final int[] p3);
    
    public static final native int JSObjectMakeFunctionWithCallback(final int p0, final int p1, final int p2);
    
    public static final native void JSObjectSetProperty(final int p0, final int p1, final int p2, final int p3, final int p4, final int[] p5);
    
    public static final native int JSStringCreateWithUTF8CString(final byte[] p0);
    
    public static final native int JSStringGetLength(final int p0);
    
    public static final native int JSStringGetMaximumUTF8CStringSize(final int p0);
    
    public static final native int JSStringGetUTF8CString(final int p0, final byte[] p1, final int p2);
    
    public static final native int JSStringIsEqualToUTF8CString(final int p0, final byte[] p1);
    
    public static final native void JSStringRelease(final int p0);
    
    public static final native int JSValueGetType(final int p0, final int p1);
    
    public static final native int JSValueIsObjectOfClass(final int p0, final int p1, final int p2);
    
    public static final native int JSValueMakeBoolean(final int p0, final int p1);
    
    public static final native int JSValueMakeNull(final int p0);
    
    public static final native int JSValueMakeNumber(final int p0, final double p1);
    
    public static final native int JSValueMakeString(final int p0, final int p1);
    
    public static final native int JSValueMakeUndefined(final int p0);
    
    public static final native int JSValueToBoolean(final int p0, final int p1);
    
    public static final native double JSValueToNumber(final int p0, final int p1, final int[] p2);
    
    public static final native int JSValueToStringCopy(final int p0, final int p1, final int[] p2);
    
    public static final native int kCFCopyStringDictionaryKeyCallBacks();
    
    public static final native int kCFTypeDictionaryValueCallBacks();
    
    public static final native int JSClassDefinition_sizeof();
    
    public static final native void memmove(final int p0, final JSClassDefinition p1, final int p2);
    
    public static final native int WebKitCreateInstance(final byte[] p0, final int p1, final byte[] p2, final int[] p3);
    
    public static final int WebKitCreateInstance(final GUID guid, final int n, final GUID guid2, final int[] array) {
        final byte[] array2 = new byte[GUID.sizeof];
        OS.IIDFromString((String.valueOf(guid.toString()) + '\0').toCharArray(), array2);
        final byte[] array3 = new byte[GUID.sizeof];
        OS.IIDFromString((String.valueOf(guid2.toString()) + '\0').toCharArray(), array3);
        return WebKitCreateInstance(array2, n, array3, array);
    }
    
    public static final native int JSObjectCallAsFunctionProc_CALLBACK(final int p0);
    
    public static final native int JSObjectGetPropertyProc_CALLBACK(final int p0);
    
    public static final native int JSObjectHasPropertyProc_CALLBACK(final int p0);
    
    public static final native int willPerformClientRedirectToURL_CALLBACK(final int p0);
}
