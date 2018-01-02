// 
// Decompiled by Procyon v0.5.30
// 

package org.eclipse.swt.browser;

import org.eclipse.swt.internal.webkit.IWebPreferences;
import java.net.MalformedURLException;
import java.io.File;
import java.net.URL;
import org.eclipse.swt.internal.webkit.IWebMutableURLRequest;
import java.util.Enumeration;
import org.eclipse.swt.internal.webkit.IWebIBActions;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.internal.webkit.IWebDocumentRepresentation;
import org.eclipse.swt.internal.webkit.IWebDataSource;
import org.eclipse.swt.internal.webkit.IWebFrame;
import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.internal.webkit.IWebViewPrivate;
import org.eclipse.swt.internal.win32.RECT;
import org.eclipse.swt.internal.webkit.JSClassDefinition;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.SWTException;
import java.io.UnsupportedEncodingException;
import org.eclipse.swt.internal.C;
import org.eclipse.swt.widgets.Widget;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.SWTError;
import org.eclipse.swt.internal.ole.win32.COM;
import org.eclipse.swt.internal.webkit.IWebCookieManager;
import org.eclipse.swt.internal.webkit.WebKit_win32;
import org.eclipse.swt.SWT;
import org.eclipse.swt.internal.win32.OS;
import org.eclipse.swt.internal.win32.TCHAR;
import org.eclipse.swt.internal.Library;
import org.eclipse.swt.internal.Callback;
import org.eclipse.swt.internal.webkit.IWebView;

class WebKit extends WebBrowser
{
    IWebView webView;
    int webViewWindowHandle;
    int webViewData;
    int refCount;
    int lastKeyCode;
    int lastCharCode;
    WebDownloadDelegate webDownloadDelegate;
    WebFrameLoadDelegate webFrameLoadDelegate;
    WebPolicyDelegate webPolicyDelegate;
    WebResourceLoadDelegate webResourceLoadDelegate;
    WebUIDelegate webUIDelegate;
    boolean ignoreDispose;
    boolean loadingText;
    boolean traverseNext;
    boolean traverseOut;
    boolean untrustedText;
    String lastNavigateURL;
    BrowserFunction eventFunction;
    static int prefsIdentifier;
    static int ExternalClass;
    static boolean LibraryLoaded;
    static String LibraryLoadError;
    static Callback JSObjectHasPropertyProc;
    static Callback JSObjectGetPropertyProc;
    static Callback JSObjectCallAsFunctionProc;
    static final int MAX_PROGRESS = 100;
    static final String ABOUT_BLANK = "about:blank";
    static final String CHARSET_UTF8 = "UTF-8";
    static final String CLASSNAME_EXTERNAL = "External";
    static final String EMPTY_STRING = "";
    static final String FUNCTIONNAME_CALLJAVA = "callJava";
    static final String HEADER_SETCOOKIE = "Set-Cookie";
    static final String POST = "POST";
    static final String PROPERTY_LENGTH = "length";
    static final String PROTOCOL_HTTPS = "https://";
    static final String PROTOCOL_FILE = "file://";
    static final String PROTOCOL_HTTP = "http://";
    static final String USER_AGENT = "user-agent";
    static final String URI_FILEROOT = "file:///";
    static final String DOMEVENT_DRAGSTART = "dragstart";
    static final String DOMEVENT_KEYDOWN = "keydown";
    static final String DOMEVENT_KEYPRESS = "keypress";
    static final String DOMEVENT_KEYUP = "keyup";
    static final String DOMEVENT_MOUSEDOWN = "mousedown";
    static final String DOMEVENT_MOUSEUP = "mouseup";
    static final String DOMEVENT_MOUSEMOVE = "mousemove";
    static final String DOMEVENT_MOUSEOUT = "mouseout";
    static final String DOMEVENT_MOUSEOVER = "mouseover";
    static final String DOMEVENT_MOUSEWHEEL = "mousewheel";
    static /* synthetic */ Class class$0;
    
    static {
        WebKit.LibraryLoaded = false;
        try {
            Library.loadLibrary("swt-webkit");
            WebKit.LibraryLoaded = true;
        }
        catch (Throwable t) {}
        if (!WebKit.LibraryLoaded) {
            boolean b = false;
            final TCHAR tchar = new TCHAR(0, "SOFTWARE\\Apple Inc.\\Apple Application Support", true);
            final int[] array = { 0 };
            if (OS.RegOpenKeyEx(-2147483646, tchar, 0, 131097, array) == 0) {
                final int[] array2 = { 0 };
                final TCHAR tchar2 = new TCHAR(0, "InstallDir", true);
                if (OS.RegQueryValueEx(array[0], tchar2, 0, null, (TCHAR)null, array2) == 0) {
                    final TCHAR tchar3 = new TCHAR(0, array2[0] / TCHAR.sizeof);
                    if (OS.RegQueryValueEx(array[0], tchar2, 0, null, tchar3, array2) == 0) {
                        b = true;
                        final String string = tchar3.toString(0, tchar3.strlen());
                        if (OS.SetDllDirectory(new TCHAR(0, string, true))) {
                            try {
                                Library.loadLibrary("swt-webkit");
                                WebKit.LibraryLoaded = true;
                            }
                            catch (Throwable t2) {
                                WebKit.LibraryLoadError = "Failed to load the swt-webkit library";
                            }
                        }
                        else {
                            WebKit.LibraryLoadError = "Failed to add the Apple Application Support package to the library lookup path.  ";
                            WebKit.LibraryLoadError = String.valueOf(WebKit.LibraryLoadError) + "To use a SWT.WEBKIT-style Browser prepend " + string + " to your Windows 'Path' environment variable and restart.";
                        }
                    }
                }
                OS.RegCloseKey(array[0]);
            }
            if (!b) {
                WebKit.LibraryLoadError = "Safari must be installed to use a SWT.WEBKIT-style Browser";
            }
        }
        if (WebKit.LibraryLoaded) {
            Class class$0;
            if ((class$0 = WebKit.class$0) == null) {
                try {
                    class$0 = (WebKit.class$0 = Class.forName("org.eclipse.swt.browser.WebKit"));
                }
                catch (ClassNotFoundException ex) {
                    throw new NoClassDefFoundError(ex.getMessage());
                }
            }
            WebKit.JSObjectHasPropertyProc = new Callback(class$0, "JSObjectHasPropertyProc", 3);
            if (WebKit.JSObjectHasPropertyProc.getAddress() == 0) {
                SWT.error(3);
            }
            Class class$2;
            if ((class$2 = WebKit.class$0) == null) {
                try {
                    class$2 = (WebKit.class$0 = Class.forName("org.eclipse.swt.browser.WebKit"));
                }
                catch (ClassNotFoundException ex2) {
                    throw new NoClassDefFoundError(ex2.getMessage());
                }
            }
            WebKit.JSObjectGetPropertyProc = new Callback(class$2, "JSObjectGetPropertyProc", 4);
            if (WebKit.JSObjectGetPropertyProc.getAddress() == 0) {
                SWT.error(3);
            }
            Class class$3;
            if ((class$3 = WebKit.class$0) == null) {
                try {
                    class$3 = (WebKit.class$0 = Class.forName("org.eclipse.swt.browser.WebKit"));
                }
                catch (ClassNotFoundException ex3) {
                    throw new NoClassDefFoundError(ex3.getMessage());
                }
            }
            WebKit.JSObjectCallAsFunctionProc = new Callback(class$3, "JSObjectCallAsFunctionProc", 6);
            if (WebKit.JSObjectCallAsFunctionProc.getAddress() == 0) {
                SWT.error(3);
            }
            WebKit.NativeClearSessions = new Runnable() {
                public void run() {
                    final int[] array = { 0 };
                    if (WebKit_win32.WebKitCreateInstance(WebKit_win32.CLSID_WebCookieManager, 0, WebKit_win32.IID_IWebCookieManager, array) != 0 || array[0] == 0) {
                        return;
                    }
                    final IWebCookieManager webCookieManager = new IWebCookieManager(array[0]);
                    final int[] array2 = { 0 };
                    final int cookieStorage = webCookieManager.cookieStorage(array2);
                    webCookieManager.Release();
                    if (cookieStorage != 0 || array2[0] == 0) {
                        return;
                    }
                    final int cfhttpCookieStorageCopyCookies = WebKit_win32.CFHTTPCookieStorageCopyCookies(array2[0]);
                    if (cfhttpCookieStorageCopyCookies != 0) {
                        for (int cfArrayGetCount = WebKit_win32.CFArrayGetCount(cfhttpCookieStorageCopyCookies), i = 0; i < cfArrayGetCount; ++i) {
                            final int cfArrayGetValueAtIndex = WebKit_win32.CFArrayGetValueAtIndex(cfhttpCookieStorageCopyCookies, i);
                            if ((WebKit_win32.CFHTTPCookieGetFlags(cfArrayGetValueAtIndex) & 0x2) != 0x0) {
                                WebKit_win32.CFHTTPCookieStorageDeleteCookie(array2[0], cfArrayGetValueAtIndex);
                            }
                        }
                        WebKit_win32.CFRelease(cfhttpCookieStorageCopyCookies);
                    }
                }
            };
            WebKit.NativeGetCookie = new Runnable() {
                public void run() {
                    final int[] array = { 0 };
                    if (WebKit_win32.WebKitCreateInstance(WebKit_win32.CLSID_WebCookieManager, 0, WebKit_win32.IID_IWebCookieManager, array) != 0 || array[0] == 0) {
                        return;
                    }
                    final IWebCookieManager webCookieManager = new IWebCookieManager(array[0]);
                    final int[] array2 = { 0 };
                    final int cookieStorage = webCookieManager.cookieStorage(array2);
                    webCookieManager.Release();
                    if (cookieStorage != 0 || array2[0] == 0) {
                        return;
                    }
                    final char[] charArray = WebKit.CookieUrl.toCharArray();
                    final int cfStringCreateWithCharacters = WebKit_win32.CFStringCreateWithCharacters(0, charArray, charArray.length);
                    if (cfStringCreateWithCharacters != 0) {
                        final int cfurlCreateWithString = WebKit_win32.CFURLCreateWithString(0, cfStringCreateWithCharacters, 0);
                        if (cfurlCreateWithString != 0) {
                            final int cfhttpCookieStorageCopyCookiesForURL = WebKit_win32.CFHTTPCookieStorageCopyCookiesForURL(array2[0], cfurlCreateWithString, WebKit.CookieUrl.startsWith("https://"));
                            if (cfhttpCookieStorageCopyCookiesForURL != 0) {
                                for (int cfArrayGetCount = WebKit_win32.CFArrayGetCount(cfhttpCookieStorageCopyCookiesForURL), i = 0; i < cfArrayGetCount; ++i) {
                                    final int cfArrayGetValueAtIndex = WebKit_win32.CFArrayGetValueAtIndex(cfhttpCookieStorageCopyCookiesForURL, i);
                                    if (cfArrayGetValueAtIndex != 0) {
                                        final int cfhttpCookieGetName = WebKit_win32.CFHTTPCookieGetName(cfArrayGetValueAtIndex);
                                        if (cfhttpCookieGetName != 0 && WebKit.CookieName.equals(WebKit.stringFromCFString(cfhttpCookieGetName))) {
                                            final int cfhttpCookieGetValue = WebKit_win32.CFHTTPCookieGetValue(cfArrayGetValueAtIndex);
                                            if (cfhttpCookieGetValue != 0) {
                                                WebKit.CookieValue = WebKit.stringFromCFString(cfhttpCookieGetValue);
                                                break;
                                            }
                                            break;
                                        }
                                    }
                                }
                                WebKit_win32.CFRelease(cfhttpCookieStorageCopyCookiesForURL);
                            }
                            WebKit_win32.CFRelease(cfurlCreateWithString);
                        }
                        WebKit_win32.CFRelease(cfStringCreateWithCharacters);
                    }
                }
            };
            WebKit.NativeSetCookie = new Runnable() {
                public void run() {
                    final int[] array = { 0 };
                    if (WebKit_win32.WebKitCreateInstance(WebKit_win32.CLSID_WebCookieManager, 0, WebKit_win32.IID_IWebCookieManager, array) != 0 || array[0] == 0) {
                        return;
                    }
                    final IWebCookieManager webCookieManager = new IWebCookieManager(array[0]);
                    final int[] array2 = { 0 };
                    final int cookieStorage = webCookieManager.cookieStorage(array2);
                    webCookieManager.Release();
                    if (cookieStorage != 0 || array2[0] == 0) {
                        return;
                    }
                    final char[] charArray = WebKit.CookieUrl.toCharArray();
                    final int cfStringCreateWithCharacters = WebKit_win32.CFStringCreateWithCharacters(0, charArray, charArray.length);
                    if (cfStringCreateWithCharacters != 0) {
                        final int cfurlCreateWithString = WebKit_win32.CFURLCreateWithString(0, cfStringCreateWithCharacters, 0);
                        if (cfurlCreateWithString != 0) {
                            final char[] charArray2 = WebKit.CookieValue.toCharArray();
                            final int cfStringCreateWithCharacters2 = WebKit_win32.CFStringCreateWithCharacters(0, charArray2, charArray2.length);
                            if (cfStringCreateWithCharacters2 != 0) {
                                final char[] charArray3 = "Set-Cookie".toCharArray();
                                final int cfStringCreateWithCharacters3 = WebKit_win32.CFStringCreateWithCharacters(0, charArray3, charArray3.length);
                                if (cfStringCreateWithCharacters3 != 0) {
                                    final int cfDictionaryCreate = WebKit_win32.CFDictionaryCreate(0, new int[] { cfStringCreateWithCharacters3 }, new int[] { cfStringCreateWithCharacters2 }, 1, WebKit_win32.kCFCopyStringDictionaryKeyCallBacks(), WebKit_win32.kCFTypeDictionaryValueCallBacks());
                                    if (cfDictionaryCreate != 0) {
                                        final int cfhttpCookieCreateWithResponseHeaderFields = WebKit_win32.CFHTTPCookieCreateWithResponseHeaderFields(0, cfDictionaryCreate, cfurlCreateWithString);
                                        if (cfhttpCookieCreateWithResponseHeaderFields != 0) {
                                            final int cfArrayGetValueAtIndex = WebKit_win32.CFArrayGetValueAtIndex(cfhttpCookieCreateWithResponseHeaderFields, 0);
                                            if (cfArrayGetValueAtIndex != 0) {
                                                WebKit_win32.CFHTTPCookieStorageSetCookie(array2[0], cfArrayGetValueAtIndex);
                                                WebKit.CookieResult = true;
                                            }
                                            WebKit_win32.CFRelease(cfhttpCookieCreateWithResponseHeaderFields);
                                        }
                                        WebKit_win32.CFRelease(cfDictionaryCreate);
                                    }
                                    WebKit_win32.CFRelease(cfStringCreateWithCharacters3);
                                }
                                WebKit_win32.CFRelease(cfStringCreateWithCharacters2);
                            }
                            WebKit_win32.CFRelease(cfurlCreateWithString);
                        }
                        WebKit_win32.CFRelease(cfStringCreateWithCharacters);
                    }
                }
            };
            if (WebKit.NativePendingCookies != null) {
                WebBrowser.SetPendingCookies(WebKit.NativePendingCookies);
            }
            WebKit.NativePendingCookies = null;
        }
    }
    
    WebKit() {
        this.refCount = 0;
        this.loadingText = false;
        this.traverseNext = true;
        this.traverseOut = false;
    }
    
    static int createBSTR(final String s) {
        return COM.SysAllocString((String.valueOf(s) + '\0').toCharArray());
    }
    
    static String error(final int n) {
        throw new SWTError("WebKit error " + n);
    }
    
    static String extractBSTR(final int n) {
        final int sysStringByteLen = COM.SysStringByteLen(n);
        if (sysStringByteLen == 0) {
            return "";
        }
        final char[] array = new char[(sysStringByteLen + 1) / 2];
        OS.MoveMemory(array, n, sysStringByteLen);
        return new String(array);
    }
    
    static Browser findBrowser(final int n) {
        if (n == 0) {
            return null;
        }
        final IWebView webView = new IWebView(n);
        final int[] array = { 0 };
        if (webView.hostWindow(array) == 0 && array[0] != 0) {
            final Widget widget = Display.getCurrent().findWidget(array[0]);
            if (widget != null && widget instanceof Browser) {
                return (Browser)widget;
            }
        }
        return null;
    }
    
    static int JSObjectCallAsFunctionProc(final int n, final int n2, final int n3, final int n4, final int n5, final int n6) {
        WebKit_win32.JSGlobalContextRetain(n);
        if (WebKit_win32.JSValueIsObjectOfClass(n, n3, WebKit.ExternalClass) == 0) {
            return WebKit_win32.JSValueMakeUndefined(n);
        }
        final int jsObjectGetPrivate = WebKit_win32.JSObjectGetPrivate(n3);
        final int[] array = { 0 };
        C.memmove(array, jsObjectGetPrivate, C.PTR_SIZEOF);
        final Browser browser = findBrowser(array[0]);
        if (browser == null) {
            return WebKit_win32.JSValueMakeUndefined(n);
        }
        return ((WebKit)browser.webBrowser).callJava(n, n2, n3, n4, n5, n6);
    }
    
    static int JSObjectGetPropertyProc(final int n, final int n2, final int n3, final int n4) {
        final byte[] array = null;
        byte[] array2;
        try {
            array2 = "callJava\u0000".getBytes("UTF-8");
        }
        catch (UnsupportedEncodingException ex) {
            array2 = "callJava\u0000".getBytes();
        }
        final int jsStringCreateWithUTF8CString = WebKit_win32.JSStringCreateWithUTF8CString(array2);
        final int jsObjectMakeFunctionWithCallback = WebKit_win32.JSObjectMakeFunctionWithCallback(n, jsStringCreateWithUTF8CString, WebKit_win32.JSObjectCallAsFunctionProc_CALLBACK(WebKit.JSObjectCallAsFunctionProc.getAddress()));
        WebKit_win32.JSStringRelease(jsStringCreateWithUTF8CString);
        return jsObjectMakeFunctionWithCallback;
    }
    
    static int JSObjectHasPropertyProc(final int n, final int n2, final int n3) {
        final byte[] array = null;
        byte[] array2;
        try {
            array2 = "callJava\u0000".getBytes("UTF-8");
        }
        catch (UnsupportedEncodingException ex) {
            array2 = "callJava\u0000".getBytes();
        }
        return WebKit_win32.JSStringIsEqualToUTF8CString(n3, array2);
    }
    
    static String stringFromCFString(final int n) {
        if (n == 0) {
            return null;
        }
        final int cfStringGetLength = WebKit_win32.CFStringGetLength(n);
        final int cfStringGetCharactersPtr = WebKit_win32.CFStringGetCharactersPtr(n);
        final char[] array = new char[cfStringGetLength];
        if (cfStringGetCharactersPtr != 0) {
            OS.MoveMemory(array, cfStringGetCharactersPtr, cfStringGetLength);
        }
        else {
            for (int i = 0; i < cfStringGetLength; ++i) {
                array[i] = WebKit_win32.CFStringGetCharacterAtIndex(n, i);
            }
        }
        return new String(array);
    }
    
    static String stringFromJSString(final int n) {
        if (n == 0) {
            return null;
        }
        final int jsStringGetLength = WebKit_win32.JSStringGetLength(n);
        final byte[] array = new byte[jsStringGetLength + 1];
        WebKit_win32.JSStringGetUTF8CString(n, array, jsStringGetLength + 1);
        return new String(array);
    }
    
    public boolean back() {
        final int[] array = { 0 };
        this.webView.goBack(array);
        return array[0] != 0;
    }
    
    int callJava(final int n, final int n2, final int n3, final int n4, final int n5, final int n6) {
        Object o = null;
        if (n4 == 2) {
            final int[] array = { 0 };
            C.memmove(array, n5, C.PTR_SIZEOF);
            if (WebKit_win32.JSValueGetType(n, array[0]) == 3) {
                final int intValue = (int)this.convertToJava(n, array[0]);
                array[0] = 0;
                if (intValue > 0) {
                    final BrowserFunction browserFunction = this.functions.get(new Integer(intValue));
                    if (browserFunction != null) {
                        try {
                            C.memmove(array, n5 + C.PTR_SIZEOF, C.PTR_SIZEOF);
                            final Object convertToJava = this.convertToJava(n, array[0]);
                            if (convertToJava instanceof Object[]) {
                                final Object[] array2 = (Object[])convertToJava;
                                try {
                                    o = browserFunction.function(array2);
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
                }
            }
        }
        return this.convertToJS(n, o);
    }
    
    public boolean close() {
        return this.shouldClose();
    }
    
    Object convertToJava(final int n, final int n2) {
        switch (WebKit_win32.JSValueGetType(n, n2)) {
            case 2: {
                return new Boolean(WebKit_win32.JSValueToBoolean(n, n2) != 0);
            }
            case 3: {
                return new Double(WebKit_win32.JSValueToNumber(n, n2, null));
            }
            case 4: {
                final int jsValueToStringCopy = WebKit_win32.JSValueToStringCopy(n, n2, null);
                if (jsValueToStringCopy == 0) {
                    return "";
                }
                final int jsStringGetMaximumUTF8CStringSize = WebKit_win32.JSStringGetMaximumUTF8CStringSize(jsValueToStringCopy);
                final byte[] array = new byte[jsStringGetMaximumUTF8CStringSize];
                final int jsStringGetUTF8CString = WebKit_win32.JSStringGetUTF8CString(jsValueToStringCopy, array, jsStringGetMaximumUTF8CStringSize);
                WebKit_win32.JSStringRelease(jsValueToStringCopy);
                try {
                    return new String(array, 0, jsStringGetUTF8CString - 1, "UTF-8");
                }
                catch (UnsupportedEncodingException ex) {
                    return new String(array);
                }
                return null;
            }
            case 0:
            case 1: {
                return null;
            }
            case 5: {
                final byte[] array2 = null;
                byte[] array3;
                try {
                    array3 = "length\u0000".getBytes("UTF-8");
                }
                catch (UnsupportedEncodingException ex2) {
                    array3 = "length\u0000".getBytes();
                }
                final int jsStringCreateWithUTF8CString = WebKit_win32.JSStringCreateWithUTF8CString(array3);
                final int jsObjectGetProperty = WebKit_win32.JSObjectGetProperty(n, n2, jsStringCreateWithUTF8CString, null);
                WebKit_win32.JSStringRelease(jsStringCreateWithUTF8CString);
                if (WebKit_win32.JSValueGetType(n, jsObjectGetProperty) == 3) {
                    final int n3 = (int)WebKit_win32.JSValueToNumber(n, jsObjectGetProperty, null);
                    final Object[] array4 = new Object[n3];
                    for (int i = 0; i < n3; ++i) {
                        final int jsObjectGetPropertyAtIndex = WebKit_win32.JSObjectGetPropertyAtIndex(n, n2, i, null);
                        if (jsObjectGetPropertyAtIndex != 0) {
                            array4[i] = this.convertToJava(n, jsObjectGetPropertyAtIndex);
                        }
                    }
                    return array4;
                }
                break;
            }
        }
        SWT.error(5);
        return null;
    }
    
    int convertToJS(final int n, final Object o) {
        if (o == null) {
            return WebKit_win32.JSValueMakeNull(n);
        }
        if (o instanceof String) {
            final byte[] array = null;
            byte[] array2;
            try {
                array2 = (String.valueOf(o) + '\0').getBytes("UTF-8");
            }
            catch (UnsupportedEncodingException ex) {
                array2 = (String.valueOf(o) + '\0').getBytes();
            }
            final int jsStringCreateWithUTF8CString = WebKit_win32.JSStringCreateWithUTF8CString(array2);
            final int jsValueMakeString = WebKit_win32.JSValueMakeString(n, jsStringCreateWithUTF8CString);
            WebKit_win32.JSStringRelease(jsStringCreateWithUTF8CString);
            return jsValueMakeString;
        }
        if (o instanceof Boolean) {
            return WebKit_win32.JSValueMakeBoolean(n, ((boolean)o) ? 1 : 0);
        }
        if (o instanceof Number) {
            return WebKit_win32.JSValueMakeNumber(n, ((Number)o).doubleValue());
        }
        if (o instanceof Object[]) {
            final Object[] array3 = (Object[])o;
            final int length = array3.length;
            final int[] array4 = new int[length];
            for (int i = 0; i < length; ++i) {
                array4[i] = this.convertToJS(n, array3[i]);
            }
            return WebKit_win32.JSObjectMakeArray(n, length, array4, null);
        }
        SWT.error(51);
        return 0;
    }
    
    public void create(final Composite composite, final int n) {
        if (!WebKit.LibraryLoaded) {
            this.browser.dispose();
            SWT.error(2, null, (WebKit.LibraryLoadError == null) ? null : (" [" + WebKit.LibraryLoadError + ']'));
        }
        if (WebKit.ExternalClass == 0) {
            final JSClassDefinition jsClassDefinition = new JSClassDefinition();
            final byte[] bytes = "External\u0000".getBytes();
            C.memmove(jsClassDefinition.className = C.malloc(bytes.length), bytes, bytes.length);
            jsClassDefinition.hasProperty = WebKit_win32.JSObjectHasPropertyProc_CALLBACK(WebKit.JSObjectHasPropertyProc.getAddress());
            jsClassDefinition.getProperty = WebKit_win32.JSObjectGetPropertyProc_CALLBACK(WebKit.JSObjectGetPropertyProc.getAddress());
            final int malloc = C.malloc(JSClassDefinition.sizeof);
            WebKit_win32.memmove(malloc, jsClassDefinition, JSClassDefinition.sizeof);
            WebKit_win32.JSClassRetain(WebKit.ExternalClass = WebKit_win32.JSClassCreate(malloc));
        }
        final int[] array = { 0 };
        final int webKitCreateInstance = WebKit_win32.WebKitCreateInstance(WebKit_win32.CLSID_WebView, 0, WebKit_win32.IID_IWebView, array);
        if (webKitCreateInstance != 0 || array[0] == 0) {
            this.browser.dispose();
            error(webKitCreateInstance);
        }
        this.webView = new IWebView(array[0]);
        C.memmove(this.webViewData = C.malloc(C.PTR_SIZEOF), new int[] { this.webView.getAddress() }, C.PTR_SIZEOF);
        final int setHostWindow = this.webView.setHostWindow(this.browser.handle);
        if (setHostWindow != 0) {
            this.browser.dispose();
            error(setHostWindow);
        }
        final int initWithFrame = this.webView.initWithFrame(new RECT(), 0, 0);
        if (initWithFrame != 0) {
            this.browser.dispose();
            error(initWithFrame);
        }
        array[0] = 0;
        final int queryInterface = this.webView.QueryInterface(WebKit_win32.IID_IWebViewPrivate, array);
        if (queryInterface != 0 || array[0] == 0) {
            this.browser.dispose();
            error(queryInterface);
        }
        final IWebViewPrivate webViewPrivate = new IWebViewPrivate(array[0]);
        array[0] = 0;
        final int viewWindow = webViewPrivate.viewWindow(array);
        if (viewWindow != 0 || array[0] == 0) {
            this.browser.dispose();
            error(viewWindow);
        }
        webViewPrivate.Release();
        this.webViewWindowHandle = array[0];
        this.webFrameLoadDelegate = new WebFrameLoadDelegate(this.browser);
        final int setFrameLoadDelegate = this.webView.setFrameLoadDelegate(this.webFrameLoadDelegate.getAddress());
        if (setFrameLoadDelegate != 0) {
            this.browser.dispose();
            error(setFrameLoadDelegate);
        }
        this.webUIDelegate = new WebUIDelegate(this.browser);
        final int setUIDelegate = this.webView.setUIDelegate(this.webUIDelegate.getAddress());
        if (setUIDelegate != 0) {
            this.browser.dispose();
            error(setUIDelegate);
        }
        this.webResourceLoadDelegate = new WebResourceLoadDelegate(this.browser);
        final int setResourceLoadDelegate = this.webView.setResourceLoadDelegate(this.webResourceLoadDelegate.getAddress());
        if (setResourceLoadDelegate != 0) {
            this.browser.dispose();
            error(setResourceLoadDelegate);
        }
        this.webDownloadDelegate = new WebDownloadDelegate(this.browser);
        final int setDownloadDelegate = this.webView.setDownloadDelegate(this.webDownloadDelegate.getAddress());
        if (setDownloadDelegate != 0) {
            this.browser.dispose();
            error(setDownloadDelegate);
        }
        this.webPolicyDelegate = new WebPolicyDelegate(this.browser);
        final int setPolicyDelegate = this.webView.setPolicyDelegate(this.webPolicyDelegate.getAddress());
        if (setPolicyDelegate != 0) {
            this.browser.dispose();
            error(setPolicyDelegate);
        }
        this.initializeWebViewPreferences();
        final Listener listener = new Listener() {
            public void handleEvent(final Event event) {
                switch (event.type) {
                    case 12: {
                        if (WebKit.this.ignoreDispose) {
                            WebKit.this.ignoreDispose = false;
                            break;
                        }
                        WebKit.this.ignoreDispose = true;
                        WebKit.this.browser.notifyListeners(event.type, event);
                        event.type = 0;
                        WebKit.this.onDispose();
                        break;
                    }
                    case 15: {
                        OS.SetFocus(WebKit.this.webViewWindowHandle);
                        break;
                    }
                    case 11: {
                        final Rectangle clientArea = WebKit.this.browser.getClientArea();
                        OS.SetWindowPos(WebKit.this.webViewWindowHandle, 0, clientArea.x, clientArea.y, clientArea.width, clientArea.height, 32);
                        break;
                    }
                    case 31: {
                        if (WebKit.this.traverseOut) {
                            event.doit = true;
                            WebKit.this.traverseOut = false;
                            break;
                        }
                        event.doit = false;
                        break;
                    }
                }
            }
        };
        this.browser.addListener(12, listener);
        this.browser.addListener(1, listener);
        this.browser.addListener(15, listener);
        this.browser.addListener(11, listener);
        this.browser.addListener(31, listener);
        this.eventFunction = new BrowserFunction(this.browser, "HandleWebKitEvent") {
            public Object function(final Object[] array) {
                return WebKit.this.handleEvent(array) ? Boolean.TRUE : Boolean.FALSE;
            }
        };
    }
    
    public boolean execute(final String s) {
        final int[] array = { 0 };
        if (this.webView.mainFrame(array) != 0 || array[0] == 0) {
            return false;
        }
        final IWebFrame webFrame = new IWebFrame(array[0]);
        final int globalContext = webFrame.globalContext();
        webFrame.Release();
        if (globalContext == 0) {
            return false;
        }
        final byte[] array2 = null;
        byte[] array3;
        try {
            array3 = (String.valueOf(s) + '\0').getBytes("UTF-8");
        }
        catch (UnsupportedEncodingException ex) {
            array3 = (String.valueOf(s) + '\0').getBytes();
        }
        final int jsStringCreateWithUTF8CString = WebKit_win32.JSStringCreateWithUTF8CString(array3);
        if (jsStringCreateWithUTF8CString == 0) {
            return false;
        }
        byte[] array4;
        try {
            array4 = (String.valueOf(this.getUrl()) + '\0').getBytes("UTF-8");
        }
        catch (UnsupportedEncodingException ex2) {
            array4 = (String.valueOf(this.getUrl()) + '\0').getBytes();
        }
        final int jsStringCreateWithUTF8CString2 = WebKit_win32.JSStringCreateWithUTF8CString(array4);
        if (jsStringCreateWithUTF8CString2 == 0) {
            WebKit_win32.JSStringRelease(jsStringCreateWithUTF8CString);
            return false;
        }
        final int jsEvaluateScript = WebKit_win32.JSEvaluateScript(globalContext, jsStringCreateWithUTF8CString, 0, jsStringCreateWithUTF8CString2, 0, null);
        WebKit_win32.JSStringRelease(jsStringCreateWithUTF8CString2);
        WebKit_win32.JSStringRelease(jsStringCreateWithUTF8CString);
        return jsEvaluateScript != 0;
    }
    
    public boolean forward() {
        final int[] array = { 0 };
        this.webView.goForward(array);
        return array[0] != 0;
    }
    
    public String getBrowserType() {
        return "webkit";
    }
    
    public String getText() {
        final int[] array = { 0 };
        if (this.webView.mainFrame(array) != 0 || array[0] == 0) {
            return "";
        }
        final IWebFrame webFrame = new IWebFrame(array[0]);
        array[0] = 0;
        final int dataSource = webFrame.dataSource(array);
        webFrame.Release();
        if (dataSource != 0 || array[0] == 0) {
            return "";
        }
        final IWebDataSource webDataSource = new IWebDataSource(array[0]);
        array[0] = 0;
        final int representation = webDataSource.representation(array);
        webDataSource.Release();
        if (representation != 0 || array[0] == 0) {
            return "";
        }
        final IWebDocumentRepresentation webDocumentRepresentation = new IWebDocumentRepresentation(array[0]);
        array[0] = 0;
        final int documentSource = webDocumentRepresentation.documentSource(array);
        webDocumentRepresentation.Release();
        if (documentSource != 0 || array[0] == 0) {
            return "";
        }
        final String bstr = extractBSTR(array[0]);
        COM.SysFreeString(array[0]);
        return bstr;
    }
    
    public String getUrl() {
        return this.webFrameLoadDelegate.getUrl();
    }
    
    boolean handleEvent(final Object[] array) {
        final String s = (String)array[0];
        if (s.equals("keydown")) {
            final int translateKey = this.translateKey((int)array[1]);
            switch (this.lastKeyCode = translateKey) {
                case 9:
                case 127:
                case 65536:
                case 131072:
                case 262144:
                case 4194304:
                case 16777217:
                case 16777218:
                case 16777219:
                case 16777220:
                case 16777221:
                case 16777222:
                case 16777223:
                case 16777224:
                case 16777225:
                case 16777226:
                case 16777227:
                case 16777228:
                case 16777229:
                case 16777230:
                case 16777231:
                case 16777232:
                case 16777233:
                case 16777234:
                case 16777235:
                case 16777236:
                case 16777237:
                case 16777298:
                case 16777299:
                case 16777300:
                case 16777301: {
                    final Event event = new Event();
                    event.widget = this.browser;
                    event.type = (s.equals("keydown") ? 1 : 2);
                    switch (event.keyCode = translateKey) {
                        case 8: {
                            event.character = '\b';
                            break;
                        }
                        case 127: {
                            event.character = '\u007f';
                            break;
                        }
                        case 27: {
                            event.character = '\u001b';
                            break;
                        }
                        case 9: {
                            event.character = '\t';
                            break;
                        }
                    }
                    this.lastCharCode = event.character;
                    event.stateMask = ((array[3] ? 65536 : 0) | (array[4] ? 262144 : 0) | (array[5] ? 131072 : 0) | (array[6] ? 4194304 : 0));
                    final Event event2 = event;
                    event2.stateMask &= ~translateKey;
                    if (!this.sendKeyEvent(event) || this.browser.isDisposed()) {
                        return false;
                    }
                    break;
                }
            }
            return true;
        }
        if (s.equals("keypress")) {
            if (this.lastKeyCode == 0) {
                return true;
            }
            this.lastCharCode = (int)array[2];
            if ((boolean)array[4] && this.lastCharCode >= 0 && this.lastCharCode <= 127) {
                if (97 <= this.lastCharCode && this.lastCharCode <= 122) {
                    this.lastCharCode -= 32;
                }
                if (64 <= this.lastCharCode && this.lastCharCode <= 95) {
                    this.lastCharCode -= 64;
                }
            }
            final Event event3 = new Event();
            event3.widget = this.browser;
            event3.type = 1;
            event3.keyCode = this.lastKeyCode;
            event3.character = (char)this.lastCharCode;
            event3.stateMask = ((array[3] ? 65536 : 0) | (array[4] ? 262144 : 0) | (array[5] ? 131072 : 0) | (array[6] ? 4194304 : 0));
            return this.sendKeyEvent(event3) && !this.browser.isDisposed();
        }
        else if (s.equals("keyup")) {
            final int translateKey2 = this.translateKey((int)array[1]);
            if (translateKey2 == 0) {
                return true;
            }
            if (translateKey2 != this.lastKeyCode) {
                this.lastKeyCode = translateKey2;
                this.lastCharCode = 0;
            }
            final Event event4 = new Event();
            event4.widget = this.browser;
            event4.type = 2;
            event4.keyCode = this.lastKeyCode;
            event4.character = (char)this.lastCharCode;
            event4.stateMask = ((array[3] ? 65536 : 0) | (array[4] ? 262144 : 0) | (array[5] ? 131072 : 0) | (array[6] ? 4194304 : 0));
            switch (this.lastKeyCode) {
                case 65536:
                case 131072:
                case 262144:
                case 4194304: {
                    final Event event5 = event4;
                    event5.stateMask |= this.lastKeyCode;
                    break;
                }
            }
            this.browser.notifyListeners(event4.type, event4);
            final boolean b = false;
            this.lastCharCode = (b ? 1 : 0);
            this.lastKeyCode = (b ? 1 : 0);
            return event4.doit && !this.browser.isDisposed();
        }
        else {
            if ((s.equals("mouseover") || s.equals("mouseout")) && (boolean)array[9]) {
                return true;
            }
            final Point map = this.browser.getDisplay().map(null, this.browser, new Point((int)array[1], (int)array[2]));
            final Event event6 = new Event();
            event6.widget = this.browser;
            event6.x = map.x;
            event6.y = map.y;
            final int n = (array[5] ? 65536 : 0) | (array[6] ? 262144 : 0) | (array[7] ? 131072 : 0);
            event6.stateMask = n;
            if (!s.equals("mousedown")) {
                if (s.equals("mouseup")) {
                    event6.type = 4;
                    event6.count = (int)array[3];
                    switch (event6.button = (int)array[4]) {
                        case 1: {
                            final Event event7 = event6;
                            event7.stateMask |= 0x80000;
                            break;
                        }
                        case 2: {
                            final Event event8 = event6;
                            event8.stateMask |= 0x100000;
                            break;
                        }
                        case 3: {
                            final Event event9 = event6;
                            event9.stateMask |= 0x200000;
                            break;
                        }
                        case 4: {
                            final Event event10 = event6;
                            event10.stateMask |= 0x800000;
                            break;
                        }
                        case 5: {
                            final Event event11 = event6;
                            event11.stateMask |= 0x2000000;
                            break;
                        }
                    }
                }
                else if (s.equals("mousemove")) {
                    event6.type = 5;
                }
                else if (s.equals("mousewheel")) {
                    event6.type = 37;
                    event6.count = (int)array[3];
                }
                else if (s.equals("mouseover")) {
                    event6.type = 6;
                }
                else if (s.equals("mouseout")) {
                    event6.type = 7;
                    if (event6.x < 0) {
                        event6.x = -1;
                    }
                    if (event6.y < 0) {
                        event6.y = -1;
                    }
                }
                else if (s.equals("dragstart")) {
                    event6.type = 29;
                    switch (event6.button = (int)array[4] + 1) {
                        case 1: {
                            final Event event12 = event6;
                            event12.stateMask |= 0x80000;
                            break;
                        }
                        case 2: {
                            final Event event13 = event6;
                            event13.stateMask |= 0x100000;
                            break;
                        }
                        case 3: {
                            final Event event14 = event6;
                            event14.stateMask |= 0x200000;
                            break;
                        }
                        case 4: {
                            final Event event15 = event6;
                            event15.stateMask |= 0x800000;
                            break;
                        }
                        case 5: {
                            final Event event16 = event6;
                            event16.stateMask |= 0x2000000;
                            break;
                        }
                    }
                }
                this.browser.notifyListeners(event6.type, event6);
                return true;
            }
            event6.type = 3;
            event6.count = (int)array[3];
            event6.button = (int)array[4];
            this.browser.notifyListeners(event6.type, event6);
            if (this.browser.isDisposed()) {
                return true;
            }
            if ((int)array[3] == 2) {
                final Event event17 = new Event();
                event17.type = 8;
                event17.widget = this.browser;
                event17.x = map.x;
                event17.y = map.y;
                event17.stateMask = n;
                event17.count = (int)array[3];
                event17.button = (int)array[4];
                this.browser.notifyListeners(event17.type, event17);
            }
            return true;
        }
    }
    
    public boolean isBackEnabled() {
        final int[] array = { 0 };
        if (this.webView.QueryInterface(WebKit_win32.IID_IWebIBActions, array) != 0 || array[0] == 0) {
            return false;
        }
        final IWebIBActions webIBActions = new IWebIBActions(array[0]);
        final int[] array2 = { 0 };
        webIBActions.canGoBack(this.webView.getAddress(), array2);
        webIBActions.Release();
        return array2[0] != 0;
    }
    
    public boolean isFocusControl() {
        final int getFocus = OS.GetFocus();
        return getFocus != 0 && getFocus == this.webViewWindowHandle;
    }
    
    public boolean isForwardEnabled() {
        final int[] array = { 0 };
        if (this.webView.QueryInterface(WebKit_win32.IID_IWebIBActions, array) != 0 || array[0] == 0) {
            return false;
        }
        final IWebIBActions webIBActions = new IWebIBActions(array[0]);
        final int[] array2 = { 0 };
        webIBActions.canGoForward(this.webView.getAddress(), array2);
        webIBActions.Release();
        return array2[0] != 0;
    }
    
    void onDispose() {
        if (!this.browser.isDisposed() && !this.browser.isClosing) {
            this.webUIDelegate.prompt = false;
            this.shouldClose();
            this.webUIDelegate.prompt = true;
        }
        final Enumeration<BrowserFunction> elements = this.functions.elements();
        while (elements.hasMoreElements()) {
            elements.nextElement().dispose(false);
        }
        this.functions = null;
        this.eventFunction.dispose();
        this.eventFunction = null;
        C.free(this.webViewData);
        this.webView.setPreferences(0);
        this.webView.setHostWindow(0);
        this.webView.setFrameLoadDelegate(0);
        this.webView.setResourceLoadDelegate(0);
        this.webView.setUIDelegate(0);
        this.webView.setPolicyDelegate(0);
        this.webView.setDownloadDelegate(0);
        this.webView.Release();
        this.webView = null;
        this.webDownloadDelegate = null;
        this.webFrameLoadDelegate = null;
        this.webPolicyDelegate = null;
        this.webResourceLoadDelegate = null;
        this.webUIDelegate = null;
        this.lastNavigateURL = null;
    }
    
    public void refresh() {
        this.webFrameLoadDelegate.html = null;
        final int[] array = { 0 };
        if (this.webView.QueryInterface(WebKit_win32.IID_IWebIBActions, array) != 0 || array[0] == 0) {
            return;
        }
        final IWebIBActions webIBActions = new IWebIBActions(array[0]);
        webIBActions.reload(this.webView.getAddress());
        webIBActions.Release();
    }
    
    boolean sendKeyEvent(final Event event) {
        boolean doit = true;
        switch (event.keyCode) {
            case 9:
            case 13:
            case 27:
            case 16777217:
            case 16777218:
            case 16777219:
            case 16777220:
            case 16777221:
            case 16777222: {
                break;
            }
            default: {
                if (this.translateMnemonics() && event.character != '\0' && (event.stateMask & 0x50000) == 0x10000) {
                    final int n = 128;
                    final boolean doit2 = event.doit;
                    event.doit = true;
                    doit = !this.browser.traverse(n, event);
                    event.doit = doit2;
                    break;
                }
                break;
            }
        }
        if (doit) {
            this.browser.notifyListeners(event.type, event);
            doit = event.doit;
        }
        return doit;
    }
    
    public boolean setText(final String html, final boolean b) {
        final boolean b2 = this.webFrameLoadDelegate.html != null;
        this.webFrameLoadDelegate.html = html;
        this.untrustedText = !b;
        if (b2) {
            return true;
        }
        final int[] array = { 0 };
        if (this.webView.mainFrame(array) != 0 || array[0] == 0) {
            return false;
        }
        final IWebFrame webFrame = new IWebFrame(array[0]);
        array[0] = 0;
        if (WebKit_win32.WebKitCreateInstance(WebKit_win32.CLSID_WebMutableURLRequest, 0, WebKit_win32.IID_IWebMutableURLRequest, array) != 0 || array[0] == 0) {
            webFrame.Release();
            return false;
        }
        final IWebMutableURLRequest webMutableURLRequest = new IWebMutableURLRequest(array[0]);
        final int bstr = createBSTR("about:blank");
        int n = webMutableURLRequest.setURL(bstr);
        COM.SysFreeString(bstr);
        if (n == 0) {
            n = webFrame.loadRequest(webMutableURLRequest.getAddress());
        }
        webFrame.Release();
        webMutableURLRequest.Release();
        return n == 0;
    }
    
    public boolean setUrl(String lastNavigateURL, final String s, final String[] array) {
        if (lastNavigateURL.length() == 0) {
            return false;
        }
        try {
            new URL(lastNavigateURL);
        }
        catch (MalformedURLException ex) {
            String s2;
            if (new File(lastNavigateURL).isAbsolute()) {
                s2 = "file://" + lastNavigateURL;
            }
            else {
                s2 = "http://" + lastNavigateURL;
            }
            try {
                new URL(s2);
                lastNavigateURL = s2;
            }
            catch (MalformedURLException ex2) {}
        }
        this.webFrameLoadDelegate.html = null;
        this.lastNavigateURL = lastNavigateURL;
        final int[] array2 = { 0 };
        if (this.webView.mainFrame(array2) != 0 || array2[0] == 0) {
            return false;
        }
        final IWebFrame webFrame = new IWebFrame(array2[0]);
        array2[0] = 0;
        if (WebKit_win32.WebKitCreateInstance(WebKit_win32.CLSID_WebMutableURLRequest, 0, WebKit_win32.IID_IWebMutableURLRequest, array2) != 0 || array2[0] == 0) {
            webFrame.Release();
            return false;
        }
        final IWebMutableURLRequest webMutableURLRequest = new IWebMutableURLRequest(array2[0]);
        int n = 0;
        if (array != null) {
            for (int i = 0; i < array.length; ++i) {
                final String s3 = array[i];
                if (s3 != null) {
                    final int index = s3.indexOf(58);
                    if (index != -1) {
                        final String trim = s3.substring(0, index).trim();
                        final String trim2 = s3.substring(index + 1).trim();
                        if (trim.length() > 0 && trim2.length() > 0) {
                            final int bstr = createBSTR(trim2);
                            if (trim.equalsIgnoreCase("user-agent")) {
                                n = this.webView.setCustomUserAgent(bstr);
                            }
                            else {
                                final int bstr2 = createBSTR(trim);
                                n = webMutableURLRequest.setValue(bstr, bstr2);
                                COM.SysFreeString(bstr2);
                            }
                            COM.SysFreeString(bstr);
                        }
                    }
                }
            }
        }
        if (n == 0) {
            final int bstr3 = createBSTR(lastNavigateURL);
            n = webMutableURLRequest.setURL(bstr3);
            COM.SysFreeString(bstr3);
            if (n == 0) {
                n = webFrame.loadRequest(webMutableURLRequest.getAddress());
            }
            this.webView.setCustomUserAgent(0);
        }
        webFrame.Release();
        webMutableURLRequest.Release();
        return n == 0;
    }
    
    boolean shouldClose() {
        if (!this.jsEnabled) {
            return true;
        }
        final int[] array = { 0 };
        if (this.webView.QueryInterface(WebKit_win32.IID_IWebViewPrivate, array) != 0 || array[0] == 0) {
            return false;
        }
        final IWebViewPrivate webViewPrivate = new IWebViewPrivate(array[0]);
        final int[] array2 = { 0 };
        webViewPrivate.shouldClose(array2);
        webViewPrivate.Release();
        return array2[0] != 0;
    }
    
    public void stop() {
        this.webFrameLoadDelegate.html = null;
        final int[] array = { 0 };
        if (this.webView.QueryInterface(WebKit_win32.IID_IWebIBActions, array) != 0 || array[0] == 0) {
            return;
        }
        final IWebIBActions webIBActions = new IWebIBActions(array[0]);
        webIBActions.stopLoading(this.webView.getAddress());
        webIBActions.Release();
    }
    
    void initializeWebViewPreferences() {
        final int[] array = { 0 };
        if (WebKit_win32.WebKitCreateInstance(WebKit_win32.CLSID_WebPreferences, 0, WebKit_win32.IID_IWebPreferences, array) == 0 && array[0] != 0) {
            final IWebPreferences webPreferences = new IWebPreferences(array[0]);
            array[0] = 0;
            final int initWithIdentifier = webPreferences.initWithIdentifier(createBSTR(String.valueOf(WebKit.prefsIdentifier++)), array);
            webPreferences.Release();
            if (initWithIdentifier == 0 && array[0] != 0) {
                final IWebPreferences webPreferences2 = new IWebPreferences(array[0]);
                this.webView.setPreferences(webPreferences2.getAddress());
                webPreferences2.Release();
            }
        }
        array[0] = 0;
        if (this.webView.preferences(array) == 0 && array[0] != 0) {
            final IWebPreferences webPreferences3 = new IWebPreferences(array[0]);
            webPreferences3.setJavaScriptEnabled(1);
            webPreferences3.setJavaScriptCanOpenWindowsAutomatically(1);
            webPreferences3.setJavaEnabled(0);
            webPreferences3.setTabsToLinks(1);
            webPreferences3.setFontSmoothing(4);
            webPreferences3.Release();
        }
    }
}
