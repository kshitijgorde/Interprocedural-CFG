// 
// Decompiled by Procyon v0.5.30
// 

package org.eclipse.swt.browser;

import org.eclipse.swt.internal.Compatibility;
import org.eclipse.swt.internal.mozilla.nsIURI;
import org.eclipse.swt.internal.mozilla.nsIChannel;
import org.eclipse.swt.internal.mozilla.nsEmbedString;
import org.eclipse.swt.widgets.Widget;
import org.eclipse.swt.internal.mozilla.nsIAuthInformation;
import org.eclipse.swt.internal.mozilla.nsIMemory;
import org.eclipse.swt.internal.mozilla.nsIServiceManager;
import org.eclipse.swt.widgets.MessageBox;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.SWT;
import org.eclipse.swt.internal.mozilla.nsIDOMWindow;
import org.eclipse.swt.internal.mozilla.nsIPromptService2;
import org.eclipse.swt.internal.mozilla.nsIPromptService;
import org.eclipse.swt.internal.C;
import org.eclipse.swt.internal.mozilla.nsISupports;
import org.eclipse.swt.internal.mozilla.XPCOM;
import org.eclipse.swt.internal.mozilla.nsID;
import org.eclipse.swt.internal.mozilla.XPCOMObject;

class PromptService2
{
    XPCOMObject supports;
    XPCOMObject promptService;
    XPCOMObject promptService2;
    int refCount;
    static final String[] certErrorCodes;
    
    static {
        certErrorCodes = new String[] { "ssl_error_bad_cert_domain", "sec_error_ca_cert_invalid", "sec_error_expired_certificate", "sec_error_expired_issuer_certificate", "sec_error_inadequate_key_usage", "sec_error_unknown_issuer", "sec_error_untrusted_cert", "sec_error_untrusted_issuer" };
    }
    
    PromptService2() {
        this.refCount = 0;
        this.createCOMInterfaces();
    }
    
    int AddRef() {
        return ++this.refCount;
    }
    
    void createCOMInterfaces() {
        this.supports = new XPCOMObject(new int[] { 2, 0, 0 }) {
            public int method0(final int[] array) {
                return PromptService2.this.QueryInterface(array[0], array[1]);
            }
            
            public int method1(final int[] array) {
                return PromptService2.this.AddRef();
            }
            
            public int method2(final int[] array) {
                return PromptService2.this.Release();
            }
        };
        this.promptService = new XPCOMObject(new int[] { 2, 0, 0, 3, 5, 4, 6, 10, 7, 8, 7, 7 }) {
            public int method0(final int[] array) {
                return PromptService2.this.QueryInterface(array[0], array[1]);
            }
            
            public int method1(final int[] array) {
                return PromptService2.this.AddRef();
            }
            
            public int method2(final int[] array) {
                return PromptService2.this.Release();
            }
            
            public int method3(final int[] array) {
                return PromptService2.this.Alert(array[0], array[1], array[2]);
            }
            
            public int method4(final int[] array) {
                return PromptService2.this.AlertCheck(array[0], array[1], array[2], array[3], array[4]);
            }
            
            public int method5(final int[] array) {
                return PromptService2.this.Confirm(array[0], array[1], array[2], array[3]);
            }
            
            public int method6(final int[] array) {
                return PromptService2.this.ConfirmCheck(array[0], array[1], array[2], array[3], array[4], array[5]);
            }
            
            public int method7(final int[] array) {
                return PromptService2.this.ConfirmEx(array[0], array[1], array[2], array[3], array[4], array[5], array[6], array[7], array[8], array[9]);
            }
            
            public int method8(final int[] array) {
                return PromptService2.this.Prompt(array[0], array[1], array[2], array[3], array[4], array[5], array[6]);
            }
            
            public int method9(final int[] array) {
                return PromptService2.this.PromptUsernameAndPassword(array[0], array[1], array[2], array[3], array[4], array[5], array[6], array[7]);
            }
            
            public int method10(final int[] array) {
                return PromptService2.this.PromptPassword(array[0], array[1], array[2], array[3], array[4], array[5], array[6]);
            }
            
            public int method11(final int[] array) {
                return PromptService2.this.Select(array[0], array[1], array[2], array[3], array[4], array[5], array[6]);
            }
        };
        this.promptService2 = new XPCOMObject(new int[] { 2, 0, 0, 3, 5, 4, 6, 10, 7, 8, 7, 7, 7, 9 }) {
            public int method0(final int[] array) {
                return PromptService2.this.QueryInterface(array[0], array[1]);
            }
            
            public int method1(final int[] array) {
                return PromptService2.this.AddRef();
            }
            
            public int method2(final int[] array) {
                return PromptService2.this.Release();
            }
            
            public int method3(final int[] array) {
                return PromptService2.this.Alert(array[0], array[1], array[2]);
            }
            
            public int method4(final int[] array) {
                return PromptService2.this.AlertCheck(array[0], array[1], array[2], array[3], array[4]);
            }
            
            public int method5(final int[] array) {
                return PromptService2.this.Confirm(array[0], array[1], array[2], array[3]);
            }
            
            public int method6(final int[] array) {
                return PromptService2.this.ConfirmCheck(array[0], array[1], array[2], array[3], array[4], array[5]);
            }
            
            public int method7(final int[] array) {
                return PromptService2.this.ConfirmEx(array[0], array[1], array[2], array[3], array[4], array[5], array[6], array[7], array[8], array[9]);
            }
            
            public int method8(final int[] array) {
                return PromptService2.this.Prompt(array[0], array[1], array[2], array[3], array[4], array[5], array[6]);
            }
            
            public int method9(final int[] array) {
                return PromptService2.this.PromptUsernameAndPassword(array[0], array[1], array[2], array[3], array[4], array[5], array[6], array[7]);
            }
            
            public int method10(final int[] array) {
                return PromptService2.this.PromptPassword(array[0], array[1], array[2], array[3], array[4], array[5], array[6]);
            }
            
            public int method11(final int[] array) {
                return PromptService2.this.Select(array[0], array[1], array[2], array[3], array[4], array[5], array[6]);
            }
            
            public int method12(final int[] array) {
                return PromptService2.this.PromptAuth(array[0], array[1], array[2], array[3], array[4], array[5], array[6]);
            }
            
            public int method13(final int[] array) {
                return PromptService2.this.AsyncPromptAuth(array[0], array[1], array[2], array[3], array[4], array[5], array[6], array[7], array[8]);
            }
        };
    }
    
    void disposeCOMInterfaces() {
        if (this.supports != null) {
            this.supports.dispose();
            this.supports = null;
        }
        if (this.promptService != null) {
            this.promptService.dispose();
            this.promptService = null;
        }
        if (this.promptService2 != null) {
            this.promptService2.dispose();
            this.promptService2 = null;
        }
    }
    
    int getAddress() {
        return this.promptService2.getAddress();
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
        if (nsID.Equals(nsIPromptService.NS_IPROMPTSERVICE_IID)) {
            C.memmove(n2, new int[] { this.promptService.getAddress() }, C.PTR_SIZEOF);
            this.AddRef();
            return 0;
        }
        if (nsID.Equals(nsIPromptService2.NS_IPROMPTSERVICE2_IID)) {
            C.memmove(n2, new int[] { this.promptService2.getAddress() }, C.PTR_SIZEOF);
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
    
    Browser getBrowser(final int n) {
        if (n == 0) {
            return null;
        }
        return Mozilla.findBrowser(new nsIDOMWindow(n));
    }
    
    String getLabel(final int n, final int n2, final int n3) {
        String s = null;
        switch ((n & 255 * n2) / n2) {
            case 2: {
                s = SWT.getMessage("SWT_Cancel");
                break;
            }
            case 4: {
                s = SWT.getMessage("SWT_No");
                break;
            }
            case 1: {
                s = SWT.getMessage("SWT_OK");
                break;
            }
            case 5: {
                s = SWT.getMessage("SWT_Save");
                break;
            }
            case 3: {
                s = SWT.getMessage("SWT_Yes");
                break;
            }
            case 127: {
                final int strlen_PRUnichar = XPCOM.strlen_PRUnichar(n3);
                final char[] array = new char[strlen_PRUnichar];
                C.memmove(array, n3, strlen_PRUnichar * 2);
                s = new String(array);
                break;
            }
        }
        return s;
    }
    
    int Alert(final int n, final int n2, final int n3) {
        final Browser browser = this.getBrowser(n);
        final int strlen_PRUnichar = XPCOM.strlen_PRUnichar(n2);
        final char[] array = new char[strlen_PRUnichar];
        C.memmove(array, n2, strlen_PRUnichar * 2);
        final String text = new String(array);
        final int strlen_PRUnichar2 = XPCOM.strlen_PRUnichar(n3);
        final char[] array2 = new char[strlen_PRUnichar2];
        C.memmove(array2, n3, strlen_PRUnichar2 * 2);
        final String message = new String(array2);
        if (browser != null) {
            for (int i = 0; i < PromptService2.certErrorCodes.length; ++i) {
                if (message.indexOf(PromptService2.certErrorCodes[i]) != -1) {
                    final Mozilla mozilla = (Mozilla)browser.webBrowser;
                    mozilla.isRetrievingBadCert = true;
                    browser.setUrl(mozilla.lastNavigateURL);
                    return 0;
                }
            }
        }
        final MessageBox messageBox = new MessageBox((browser == null) ? new Shell() : browser.getShell(), 40);
        messageBox.setText(text);
        messageBox.setMessage(message);
        messageBox.open();
        return 0;
    }
    
    int AlertCheck(final int n, final int n2, final int n3, final int n4, final int n5) {
        final Browser browser = this.getBrowser(n);
        final int strlen_PRUnichar = XPCOM.strlen_PRUnichar(n2);
        final char[] array = new char[strlen_PRUnichar];
        C.memmove(array, n2, strlen_PRUnichar * 2);
        final String s = new String(array);
        final int strlen_PRUnichar2 = XPCOM.strlen_PRUnichar(n3);
        final char[] array2 = new char[strlen_PRUnichar2];
        C.memmove(array2, n3, strlen_PRUnichar2 * 2);
        final String s2 = new String(array2);
        final int strlen_PRUnichar3 = XPCOM.strlen_PRUnichar(n4);
        final char[] array3 = new char[strlen_PRUnichar3];
        C.memmove(array3, n4, strlen_PRUnichar3 * 2);
        final String s3 = new String(array3);
        final PromptDialog promptDialog = new PromptDialog((browser == null) ? new Shell() : browser.getShell());
        final int[] array4 = { 0 };
        if (n5 != 0) {
            C.memmove(array4, n5, 4);
        }
        promptDialog.alertCheck(s, s2, s3, array4);
        if (n5 != 0) {
            C.memmove(n5, array4, 4);
        }
        return 0;
    }
    
    int AsyncPromptAuth(final int n, final int n2, final int n3, final int n4, final int n5, final int n6, final int n7, final int n8, final int n9) {
        return -2147467263;
    }
    
    int Confirm(final int n, final int n2, final int n3, final int n4) {
        final Browser browser = this.getBrowser(n);
        if (browser != null && ((Mozilla)browser.webBrowser).ignoreAllMessages) {
            C.memmove(n4, new int[] { 1 }, 4);
            return 0;
        }
        final int strlen_PRUnichar = XPCOM.strlen_PRUnichar(n2);
        final char[] array = new char[strlen_PRUnichar];
        C.memmove(array, n2, strlen_PRUnichar * 2);
        final String text = new String(array);
        final int strlen_PRUnichar2 = XPCOM.strlen_PRUnichar(n3);
        final char[] array2 = new char[strlen_PRUnichar2];
        C.memmove(array2, n3, strlen_PRUnichar2 * 2);
        final String message = new String(array2);
        final MessageBox messageBox = new MessageBox((browser == null) ? new Shell() : browser.getShell(), 292);
        messageBox.setText(text);
        messageBox.setMessage(message);
        C.memmove(n4, new int[] { messageBox.open() == 32 }, 4);
        return 0;
    }
    
    int ConfirmCheck(final int n, final int n2, final int n3, final int n4, final int n5, final int n6) {
        return -2147467263;
    }
    
    int ConfirmEx(final int n, final int n2, final int n3, final int n4, final int n5, final int n6, final int n7, final int n8, final int n9, final int n10) {
        final Browser browser = this.getBrowser(n);
        final int strlen_PRUnichar = XPCOM.strlen_PRUnichar(n2);
        final char[] array = new char[strlen_PRUnichar];
        C.memmove(array, n2, strlen_PRUnichar * 2);
        final String s = new String(array);
        final int strlen_PRUnichar2 = XPCOM.strlen_PRUnichar(n3);
        final char[] array2 = new char[strlen_PRUnichar2];
        C.memmove(array2, n3, strlen_PRUnichar2 * 2);
        final String s2 = new String(array2);
        String s3 = null;
        if (n8 != 0) {
            final int strlen_PRUnichar3 = XPCOM.strlen_PRUnichar(n8);
            final char[] array3 = new char[strlen_PRUnichar3];
            C.memmove(array3, n8, strlen_PRUnichar3 * 2);
            s3 = new String(array3);
        }
        final String label = this.getLabel(n4, 1, n5);
        final String label2 = this.getLabel(n4, 256, n6);
        final String label3 = this.getLabel(n4, 65536, n7);
        int n11 = 0;
        if ((n4 & 0x1000000) != 0x0) {
            n11 = 1;
        }
        else if ((n4 & 0x2000000) != 0x0) {
            n11 = 2;
        }
        final PromptDialog promptDialog = new PromptDialog((browser == null) ? new Shell() : browser.getShell());
        final int[] array4 = { 0 };
        final int[] array5 = { 0 };
        if (n9 != 0) {
            C.memmove(array4, n9, 4);
        }
        promptDialog.confirmEx(s, s2, s3, label, label2, label3, n11, array4, array5);
        if (n9 != 0) {
            C.memmove(n9, array4, 4);
        }
        C.memmove(n10, array5, 4);
        return 0;
    }
    
    int Prompt(final int n, final int n2, final int n3, final int n4, final int n5, final int n6, final int n7) {
        final Browser browser = this.getBrowser(n);
        String s = null;
        String s2 = null;
        final String[] array = { null };
        if (n2 != 0) {
            final int strlen_PRUnichar = XPCOM.strlen_PRUnichar(n2);
            final char[] array2 = new char[strlen_PRUnichar];
            C.memmove(array2, n2, strlen_PRUnichar * 2);
            s = new String(array2);
        }
        final int strlen_PRUnichar2 = XPCOM.strlen_PRUnichar(n3);
        final char[] array3 = new char[strlen_PRUnichar2];
        C.memmove(array3, n3, strlen_PRUnichar2 * 2);
        final String s3 = new String(array3);
        final int[] array4 = { 0 };
        C.memmove(array4, n4, C.PTR_SIZEOF);
        if (array4[0] != 0) {
            final int strlen_PRUnichar3 = XPCOM.strlen_PRUnichar(array4[0]);
            final char[] array5 = new char[strlen_PRUnichar3];
            C.memmove(array5, array4[0], strlen_PRUnichar3 * 2);
            array[0] = new String(array5);
        }
        if (n5 != 0) {
            final int strlen_PRUnichar4 = XPCOM.strlen_PRUnichar(n5);
            if (strlen_PRUnichar4 > 0) {
                final char[] array6 = new char[strlen_PRUnichar4];
                C.memmove(array6, n5, strlen_PRUnichar4 * 2);
                s2 = new String(array6);
            }
        }
        final PromptDialog promptDialog = new PromptDialog((browser == null) ? new Shell() : browser.getShell());
        final int[] array7 = { 0 };
        final int[] array8 = { 0 };
        if (n6 != 0) {
            C.memmove(array7, n6, 4);
        }
        promptDialog.prompt(s, s3, s2, array, array7, array8);
        C.memmove(n7, array8, 4);
        if (array8[0] == 1 && array[0] != null) {
            final int[] array9 = { 0 };
            final int ns_GetServiceManager = XPCOM.NS_GetServiceManager(array9);
            if (ns_GetServiceManager != 0) {
                SWT.error(ns_GetServiceManager);
            }
            if (array9[0] == 0) {
                SWT.error(-2147467262);
            }
            final nsIServiceManager nsIServiceManager = new nsIServiceManager(array9[0]);
            array9[0] = 0;
            final int getServiceByContractID = nsIServiceManager.GetServiceByContractID(MozillaDelegate.wcsToMbcs(null, "@mozilla.org/xpcom/memory-service;1", true), nsIMemory.NS_IMEMORY_IID, array9);
            if (getServiceByContractID != 0) {
                SWT.error(getServiceByContractID);
            }
            if (array9[0] == 0) {
                SWT.error(-2147467262);
            }
            nsIServiceManager.Release();
            final nsIMemory nsIMemory = new nsIMemory(array9[0]);
            array9[0] = 0;
            final int length = array[0].length();
            final char[] array10 = new char[length + 1];
            array[0].getChars(0, length, array10, 0);
            final int n8 = array10.length * 2;
            final int alloc = nsIMemory.Alloc(n8);
            C.memmove(alloc, array10, n8);
            C.memmove(n4, new int[] { alloc }, C.PTR_SIZEOF);
            if (array4[0] != 0) {
                nsIMemory.Free(array4[0]);
            }
            nsIMemory.Release();
        }
        if (n6 != 0) {
            C.memmove(n6, array7, 4);
        }
        return 0;
    }
    
    int PromptAuth(final int n, final int n2, final int n3, final int n4, final int n5, final int n6, final int n7) {
        final nsIAuthInformation nsIAuthInformation = new nsIAuthInformation(n4);
        final Browser browser = this.getBrowser(n);
        if (browser != null) {
            final Mozilla mozilla = (Mozilla)browser.webBrowser;
            if (mozilla.authCount++ < 3) {
                for (int i = 0; i < mozilla.authenticationListeners.length; ++i) {
                    final AuthenticationEvent authenticationEvent = new AuthenticationEvent(browser);
                    authenticationEvent.location = mozilla.lastNavigateURL;
                    mozilla.authenticationListeners[i].authenticate(authenticationEvent);
                    if (!authenticationEvent.doit) {
                        C.memmove(n7, new int[1], 4);
                        return 0;
                    }
                    if (authenticationEvent.user != null && authenticationEvent.password != null) {
                        final nsEmbedString nsEmbedString = new nsEmbedString(authenticationEvent.user);
                        final int setUsername = nsIAuthInformation.SetUsername(nsEmbedString.getAddress());
                        if (setUsername != 0) {
                            SWT.error(setUsername);
                        }
                        nsEmbedString.dispose();
                        final nsEmbedString nsEmbedString2 = new nsEmbedString(authenticationEvent.password);
                        final int setPassword = nsIAuthInformation.SetPassword(nsEmbedString2.getAddress());
                        if (setPassword != 0) {
                            SWT.error(setPassword);
                        }
                        nsEmbedString2.dispose();
                        C.memmove(n7, new int[] { 1 }, 4);
                        return 0;
                    }
                }
            }
        }
        String s = null;
        final int[] array = { 0 };
        final String[] array2 = { null };
        final String[] array3 = { null };
        final String message = SWT.getMessage("SWT_Authentication_Required");
        if (n5 != 0 && n6 != 0) {
            final int strlen_PRUnichar = XPCOM.strlen_PRUnichar(n5);
            final char[] array4 = new char[strlen_PRUnichar];
            C.memmove(array4, n5, strlen_PRUnichar * 2);
            s = new String(array4);
            C.memmove(array, n6, 4);
        }
        final int nsEmbedString_new = XPCOM.nsEmbedString_new();
        final int getUsername = nsIAuthInformation.GetUsername(nsEmbedString_new);
        if (getUsername != 0) {
            SWT.error(getUsername);
        }
        final int nsEmbedString_Length = XPCOM.nsEmbedString_Length(nsEmbedString_new);
        final int nsEmbedString_get = XPCOM.nsEmbedString_get(nsEmbedString_new);
        final char[] array5 = new char[nsEmbedString_Length];
        C.memmove(array5, nsEmbedString_get, nsEmbedString_Length * 2);
        array2[0] = new String(array5);
        XPCOM.nsEmbedString_delete(nsEmbedString_new);
        final int nsEmbedString_new2 = XPCOM.nsEmbedString_new();
        final int getPassword = nsIAuthInformation.GetPassword(nsEmbedString_new2);
        if (getPassword != 0) {
            SWT.error(getPassword);
        }
        final int nsEmbedString_Length2 = XPCOM.nsEmbedString_Length(nsEmbedString_new2);
        final int nsEmbedString_get2 = XPCOM.nsEmbedString_get(nsEmbedString_new2);
        final char[] array6 = new char[nsEmbedString_Length2];
        C.memmove(array6, nsEmbedString_get2, nsEmbedString_Length2 * 2);
        array3[0] = new String(array6);
        XPCOM.nsEmbedString_delete(nsEmbedString_new2);
        final int nsEmbedString_new3 = XPCOM.nsEmbedString_new();
        final int getRealm = nsIAuthInformation.GetRealm(nsEmbedString_new3);
        if (getRealm != 0) {
            SWT.error(getRealm);
        }
        final int nsEmbedString_Length3 = XPCOM.nsEmbedString_Length(nsEmbedString_new3);
        final int nsEmbedString_get3 = XPCOM.nsEmbedString_get(nsEmbedString_new3);
        final char[] array7 = new char[nsEmbedString_Length3];
        C.memmove(array7, nsEmbedString_get3, nsEmbedString_Length3 * 2);
        final String s2 = new String(array7);
        XPCOM.nsEmbedString_delete(nsEmbedString_new3);
        final nsIChannel nsIChannel = new nsIChannel(n2);
        final int[] array8 = { 0 };
        final int getURI = nsIChannel.GetURI(array8);
        if (getURI != 0) {
            SWT.error(getURI);
        }
        if (array8[0] == 0) {
            Mozilla.error(-2147467262);
        }
        final nsIURI nsIURI = new nsIURI(array8[0]);
        final int nsEmbedCString_new = XPCOM.nsEmbedCString_new();
        final int getHost = nsIURI.GetHost(nsEmbedCString_new);
        if (getHost != 0) {
            SWT.error(getHost);
        }
        final int nsEmbedCString_Length = XPCOM.nsEmbedCString_Length(nsEmbedCString_new);
        final int nsEmbedCString_get = XPCOM.nsEmbedCString_get(nsEmbedCString_new);
        final byte[] array9 = new byte[nsEmbedCString_Length];
        C.memmove(array9, nsEmbedCString_get, nsEmbedCString_Length);
        final String s3 = new String(array9);
        XPCOM.nsEmbedCString_delete(nsEmbedCString_new);
        nsIURI.Release();
        String message2;
        if (s2.length() > 0 && s3.length() > 0) {
            message2 = Compatibility.getMessage("SWT_Enter_Username_and_Password", new String[] { s2, s3 });
        }
        else {
            message2 = "";
        }
        final PromptDialog promptDialog = new PromptDialog((browser == null) ? new Shell() : browser.getShell());
        final int[] array10 = { 0 };
        promptDialog.promptUsernameAndPassword(message, message2, s, array2, array3, array, array10);
        C.memmove(n7, array10, 4);
        if (array10[0] == 1) {
            final nsEmbedString nsEmbedString3 = new nsEmbedString(array2[0]);
            final int setUsername2 = nsIAuthInformation.SetUsername(nsEmbedString3.getAddress());
            if (setUsername2 != 0) {
                SWT.error(setUsername2);
            }
            nsEmbedString3.dispose();
            final nsEmbedString nsEmbedString4 = new nsEmbedString(array3[0]);
            final int setPassword2 = nsIAuthInformation.SetPassword(nsEmbedString4.getAddress());
            if (setPassword2 != 0) {
                SWT.error(setPassword2);
            }
            nsEmbedString4.dispose();
        }
        if (n6 != 0) {
            C.memmove(n6, array, 4);
        }
        return 0;
    }
    
    int PromptUsernameAndPassword(final int n, final int n2, final int n3, final int n4, final int n5, final int n6, final int n7, final int n8) {
        final Browser browser = this.getBrowser(n);
        String user = null;
        String password = null;
        if (browser != null) {
            final Mozilla mozilla = (Mozilla)browser.webBrowser;
            if (mozilla.authCount++ < 3) {
                for (int i = 0; i < mozilla.authenticationListeners.length; ++i) {
                    final AuthenticationEvent authenticationEvent = new AuthenticationEvent(browser);
                    authenticationEvent.location = mozilla.lastNavigateURL;
                    mozilla.authenticationListeners[i].authenticate(authenticationEvent);
                    if (!authenticationEvent.doit) {
                        C.memmove(n8, new int[1], 4);
                        return 0;
                    }
                    if (authenticationEvent.user != null && authenticationEvent.password != null) {
                        user = authenticationEvent.user;
                        password = authenticationEvent.password;
                        C.memmove(n8, new int[] { 1 }, 4);
                        break;
                    }
                }
            }
        }
        if (user == null) {
            String s = null;
            final String[] array = { null };
            final String[] array2 = { null };
            String message;
            if (n2 != 0) {
                final int strlen_PRUnichar = XPCOM.strlen_PRUnichar(n2);
                final char[] array3 = new char[strlen_PRUnichar];
                C.memmove(array3, n2, strlen_PRUnichar * 2);
                message = new String(array3);
            }
            else {
                message = SWT.getMessage("SWT_Authentication_Required");
            }
            final int strlen_PRUnichar2 = XPCOM.strlen_PRUnichar(n3);
            final char[] array4 = new char[strlen_PRUnichar2];
            C.memmove(array4, n3, strlen_PRUnichar2 * 2);
            final String s2 = new String(array4);
            final int[] array5 = { 0 };
            C.memmove(array5, n4, C.PTR_SIZEOF);
            if (array5[0] != 0) {
                final int strlen_PRUnichar3 = XPCOM.strlen_PRUnichar(array5[0]);
                final char[] array6 = new char[strlen_PRUnichar3];
                C.memmove(array6, array5[0], strlen_PRUnichar3 * 2);
                array[0] = new String(array6);
            }
            final int[] array7 = { 0 };
            C.memmove(array7, n5, C.PTR_SIZEOF);
            if (array7[0] != 0) {
                final int strlen_PRUnichar4 = XPCOM.strlen_PRUnichar(array7[0]);
                final char[] array8 = new char[strlen_PRUnichar4];
                C.memmove(array8, array7[0], strlen_PRUnichar4 * 2);
                array2[0] = new String(array8);
            }
            if (n6 != 0) {
                final int strlen_PRUnichar5 = XPCOM.strlen_PRUnichar(n6);
                if (strlen_PRUnichar5 > 0) {
                    final char[] array9 = new char[strlen_PRUnichar5];
                    C.memmove(array9, n6, strlen_PRUnichar5 * 2);
                    s = new String(array9);
                }
            }
            final PromptDialog promptDialog = new PromptDialog((browser == null) ? new Shell() : browser.getShell());
            final int[] array10 = { 0 };
            final int[] array11 = { 0 };
            if (n7 != 0) {
                C.memmove(array10, n7, 4);
            }
            promptDialog.promptUsernameAndPassword(message, s2, s, array, array2, array10, array11);
            C.memmove(n8, array11, 4);
            if (array11[0] == 1) {
                user = array[0];
                password = array2[0];
            }
            if (n7 != 0) {
                C.memmove(n7, array10, 4);
            }
        }
        if (user != null) {
            final int[] array12 = { 0 };
            C.memmove(array12, n4, C.PTR_SIZEOF);
            final int[] array13 = { 0 };
            C.memmove(array13, n5, C.PTR_SIZEOF);
            final int[] array14 = { 0 };
            final int ns_GetServiceManager = XPCOM.NS_GetServiceManager(array14);
            if (ns_GetServiceManager != 0) {
                SWT.error(ns_GetServiceManager);
            }
            if (array14[0] == 0) {
                SWT.error(-2147467262);
            }
            final nsIServiceManager nsIServiceManager = new nsIServiceManager(array14[0]);
            array14[0] = 0;
            final int getServiceByContractID = nsIServiceManager.GetServiceByContractID(MozillaDelegate.wcsToMbcs(null, "@mozilla.org/xpcom/memory-service;1", true), nsIMemory.NS_IMEMORY_IID, array14);
            if (getServiceByContractID != 0) {
                SWT.error(getServiceByContractID);
            }
            if (array14[0] == 0) {
                SWT.error(-2147467262);
            }
            nsIServiceManager.Release();
            final nsIMemory nsIMemory = new nsIMemory(array14[0]);
            array14[0] = 0;
            if (array12[0] != 0) {
                nsIMemory.Free(array12[0]);
            }
            if (array13[0] != 0) {
                nsIMemory.Free(array13[0]);
            }
            nsIMemory.Release();
            final int length = user.length();
            final char[] array15 = new char[length + 1];
            user.getChars(0, length, array15, 0);
            final int n9 = array15.length * 2;
            final int malloc = C.malloc(n9);
            C.memmove(malloc, array15, n9);
            C.memmove(n4, new int[] { malloc }, C.PTR_SIZEOF);
            final int length2 = password.length();
            final char[] array16 = new char[length2 + 1];
            password.getChars(0, length2, array16, 0);
            final int n10 = array16.length * 2;
            final int malloc2 = C.malloc(n10);
            C.memmove(malloc2, array16, n10);
            C.memmove(n5, new int[] { malloc2 }, C.PTR_SIZEOF);
        }
        return 0;
    }
    
    int PromptPassword(final int n, final int n2, final int n3, final int n4, final int n5, final int n6, final int n7) {
        return -2147467263;
    }
    
    int Select(final int n, final int n2, final int n3, final int n4, final int n5, final int n6, final int n7) {
        return -2147467263;
    }
}
