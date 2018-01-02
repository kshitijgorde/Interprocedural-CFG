// 
// Decompiled by Procyon v0.5.30
// 

package org.eclipse.swt.browser;

import org.eclipse.swt.internal.win32.TCHAR;
import org.eclipse.swt.internal.mozilla.nsIBaseWindow;
import org.eclipse.swt.internal.win32.RECT;
import org.eclipse.swt.internal.win32.OS;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.internal.Callback;

class MozillaDelegate
{
    Browser browser;
    static int MozillaProc;
    static Callback SubclassProc;
    static /* synthetic */ Class class$0;
    
    MozillaDelegate(final Browser browser) {
        this.browser = browser;
    }
    
    static Browser findBrowser(final int n) {
        return (Browser)Display.getCurrent().findWidget(n);
    }
    
    static String getLibraryName() {
        return "xpcom.dll";
    }
    
    static char[] mbcsToWcs(final String s, final byte[] array) {
        final char[] array2 = new char[array.length];
        final int multiByteToWideChar = OS.MultiByteToWideChar(0, 1, array, array.length, array2, array2.length);
        if (multiByteToWideChar == array2.length) {
            return array2;
        }
        final char[] array3 = new char[multiByteToWideChar];
        System.arraycopy(array2, 0, array3, 0, multiByteToWideChar);
        return array3;
    }
    
    static byte[] wcsToMbcs(final String s, final String s2, final boolean b) {
        final char[] array = new char[s2.length()];
        s2.getChars(0, array.length, array, 0);
        final int n;
        byte[] array2 = new byte[n = array.length * 2 + (b ? 1 : 0)];
        int wideCharToMultiByte = OS.WideCharToMultiByte(0, 0, array, array.length, array2, n, null, null);
        if (b) {
            ++wideCharToMultiByte;
        }
        else if (array2.length != wideCharToMultiByte) {
            final byte[] array3 = new byte[wideCharToMultiByte];
            System.arraycopy(array2, 0, array3, 0, wideCharToMultiByte);
            array2 = array3;
        }
        return array2;
    }
    
    static int windowProc(final int n, final int n2, final int n3, final int n4) {
        switch (n2) {
            case 20: {
                final RECT rect = new RECT();
                OS.GetClientRect(n, rect);
                OS.FillRect(n3, rect, OS.GetSysColorBrush(OS.COLOR_WINDOW));
                break;
            }
        }
        return OS.CallWindowProc(MozillaDelegate.MozillaProc, n, n2, n3, n4);
    }
    
    void addWindowSubclass() {
        final int getWindow = OS.GetWindow(this.browser.handle, 5);
        if (MozillaDelegate.SubclassProc == null) {
            Class class$0;
            if ((class$0 = MozillaDelegate.class$0) == null) {
                try {
                    class$0 = (MozillaDelegate.class$0 = Class.forName("org.eclipse.swt.browser.MozillaDelegate"));
                }
                catch (ClassNotFoundException ex) {
                    throw new NoClassDefFoundError(ex.getMessage());
                }
            }
            MozillaDelegate.SubclassProc = new Callback(class$0, "windowProc", 4);
            MozillaDelegate.MozillaProc = OS.GetWindowLongPtr(getWindow, -4);
        }
        OS.SetWindowLongPtr(getWindow, -4, MozillaDelegate.SubclassProc.getAddress());
    }
    
    int createBaseWindow(final nsIBaseWindow nsIBaseWindow) {
        return nsIBaseWindow.Create();
    }
    
    int getHandle() {
        return this.browser.handle;
    }
    
    String getJSLibraryName() {
        return "js3250.dll";
    }
    
    String getProfilePath() {
        final TCHAR tchar = new TCHAR(0, 260);
        String s;
        if (OS.SHGetFolderPath(0, 26, 0, 0, tchar) == 0) {
            s = tchar.toString(0, tchar.strlen());
        }
        else {
            s = System.getProperty("user.home");
        }
        return String.valueOf(s) + Mozilla.SEPARATOR_OS + "Mozilla" + Mozilla.SEPARATOR_OS + "eclipse";
    }
    
    String getSWTInitLibraryName() {
        return "swt-xulrunner";
    }
    
    void handleFocus() {
    }
    
    void handleMouseDown() {
    }
    
    boolean hookEnterExit() {
        return true;
    }
    
    void init() {
    }
    
    boolean needsSpinup() {
        return false;
    }
    
    void onDispose(final int n) {
        this.removeWindowSubclass();
        this.browser = null;
    }
    
    void removeWindowSubclass() {
        if (MozillaDelegate.SubclassProc == null) {
            return;
        }
        OS.SetWindowLongPtr(OS.GetWindow(this.browser.handle, 5), -4, MozillaDelegate.MozillaProc);
    }
    
    boolean sendTraverse() {
        return false;
    }
    
    void setSize(final int n, final int n2, final int n3) {
    }
}
