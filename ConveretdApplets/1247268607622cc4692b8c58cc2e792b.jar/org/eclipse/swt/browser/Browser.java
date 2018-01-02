// 
// Decompiled by Procyon v0.5.30
// 

package org.eclipse.swt.browser;

import org.eclipse.swt.SWTException;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;

public class Browser extends Composite
{
    WebBrowser webBrowser;
    int userStyle;
    boolean isClosing;
    static final String NO_INPUT_METHOD = "org.eclipse.swt.internal.gtk.noInputMethod";
    static final String PACKAGE_PREFIX = "org.eclipse.swt.browser.";
    static final String PROPERTY_USEWEBKITGTK = "org.eclipse.swt.browser.UseWebKitGTK";
    
    public Browser(final Composite composite, final int userStyle) {
        super(checkParent(composite), checkStyle(userStyle));
        this.userStyle = userStyle;
        if ("gtk".equals(SWT.getPlatform())) {
            composite.getDisplay().setData("org.eclipse.swt.internal.gtk.noInputMethod", null);
        }
        this.webBrowser = new BrowserFactory().createWebBrowser(userStyle);
        if (this.webBrowser != null) {
            this.webBrowser.setBrowser(this);
            this.webBrowser.create(composite, userStyle);
            return;
        }
        this.dispose();
        SWT.error(2);
    }
    
    static Composite checkParent(final Composite composite) {
        if (!"gtk".equals(SWT.getPlatform())) {
            return composite;
        }
        if (composite != null && !composite.isDisposed()) {
            final Display display = composite.getDisplay();
            if (display != null && display.getThread() == Thread.currentThread()) {
                display.setData("org.eclipse.swt.internal.gtk.noInputMethod", "true");
            }
        }
        return composite;
    }
    
    static int checkStyle(int n) {
        if ((n & 0x18000) == 0x18000) {
            n &= 0xFFFEFFFF;
        }
        final String platform = SWT.getPlatform();
        if ((n & 0x8000) != 0x0 || (n & 0x10000) != 0x0) {
            if ("carbon".equals(platform)) {
                return n | 0x1000000;
            }
            if ("motif".equals(platform)) {
                return n | 0x1000000;
            }
            return n;
        }
        else {
            if ("win32".equals(platform)) {
                return n & 0xFFFFF7FF;
            }
            if ("motif".equals(platform)) {
                return n | 0x1000000;
            }
            return n;
        }
    }
    
    protected void checkWidget() {
        super.checkWidget();
    }
    
    public static void clearSessions() {
        WebBrowser.clearSessions();
    }
    
    public static String getCookie(final String s, final String s2) {
        if (s == null) {
            SWT.error(4);
        }
        if (s2 == null) {
            SWT.error(4);
        }
        return WebBrowser.GetCookie(s, s2);
    }
    
    public static boolean setCookie(final String s, final String s2) {
        if (s == null) {
            SWT.error(4);
        }
        if (s2 == null) {
            SWT.error(4);
        }
        return WebBrowser.SetCookie(s, s2, true);
    }
    
    public void addAuthenticationListener(final AuthenticationListener authenticationListener) {
        this.checkWidget();
        if (authenticationListener == null) {
            SWT.error(4);
        }
        this.webBrowser.addAuthenticationListener(authenticationListener);
    }
    
    public void addCloseWindowListener(final CloseWindowListener closeWindowListener) {
        this.checkWidget();
        if (closeWindowListener == null) {
            SWT.error(4);
        }
        this.webBrowser.addCloseWindowListener(closeWindowListener);
    }
    
    public void addLocationListener(final LocationListener locationListener) {
        this.checkWidget();
        if (locationListener == null) {
            SWT.error(4);
        }
        this.webBrowser.addLocationListener(locationListener);
    }
    
    public void addOpenWindowListener(final OpenWindowListener openWindowListener) {
        this.checkWidget();
        if (openWindowListener == null) {
            SWT.error(4);
        }
        this.webBrowser.addOpenWindowListener(openWindowListener);
    }
    
    public void addProgressListener(final ProgressListener progressListener) {
        this.checkWidget();
        if (progressListener == null) {
            SWT.error(4);
        }
        this.webBrowser.addProgressListener(progressListener);
    }
    
    public void addStatusTextListener(final StatusTextListener statusTextListener) {
        this.checkWidget();
        if (statusTextListener == null) {
            SWT.error(4);
        }
        this.webBrowser.addStatusTextListener(statusTextListener);
    }
    
    public void addTitleListener(final TitleListener titleListener) {
        this.checkWidget();
        if (titleListener == null) {
            SWT.error(4);
        }
        this.webBrowser.addTitleListener(titleListener);
    }
    
    public void addVisibilityWindowListener(final VisibilityWindowListener visibilityWindowListener) {
        this.checkWidget();
        if (visibilityWindowListener == null) {
            SWT.error(4);
        }
        this.webBrowser.addVisibilityWindowListener(visibilityWindowListener);
    }
    
    public boolean back() {
        this.checkWidget();
        return this.webBrowser.back();
    }
    
    protected void checkSubclass() {
        final String name = this.getClass().getName();
        if (!name.substring(0, name.lastIndexOf(46) + 1).equals("org.eclipse.swt.browser.")) {
            SWT.error(43);
        }
    }
    
    public boolean execute(final String s) {
        this.checkWidget();
        if (s == null) {
            SWT.error(4);
        }
        return this.webBrowser.execute(s);
    }
    
    public boolean close() {
        this.checkWidget();
        if (this.webBrowser.close()) {
            this.isClosing = true;
            this.dispose();
            this.isClosing = false;
            return true;
        }
        return false;
    }
    
    public Object evaluate(final String s) throws SWTException {
        this.checkWidget();
        if (s == null) {
            SWT.error(4);
        }
        return this.webBrowser.evaluate(s);
    }
    
    public boolean forward() {
        this.checkWidget();
        return this.webBrowser.forward();
    }
    
    public String getBrowserType() {
        this.checkWidget();
        return this.webBrowser.getBrowserType();
    }
    
    public boolean getJavascriptEnabled() {
        this.checkWidget();
        return this.webBrowser.jsEnabled;
    }
    
    public int getStyle() {
        return super.getStyle() | (this.userStyle & 0x800);
    }
    
    public String getText() {
        this.checkWidget();
        return this.webBrowser.getText();
    }
    
    public String getUrl() {
        this.checkWidget();
        return this.webBrowser.getUrl();
    }
    
    public Object getWebBrowser() {
        this.checkWidget();
        return this.webBrowser.getWebBrowser();
    }
    
    public boolean isBackEnabled() {
        this.checkWidget();
        return this.webBrowser.isBackEnabled();
    }
    
    public boolean isFocusControl() {
        this.checkWidget();
        return this.webBrowser.isFocusControl() || super.isFocusControl();
    }
    
    public boolean isForwardEnabled() {
        this.checkWidget();
        return this.webBrowser.isForwardEnabled();
    }
    
    public void refresh() {
        this.checkWidget();
        this.webBrowser.refresh();
    }
    
    public void removeAuthenticationListener(final AuthenticationListener authenticationListener) {
        this.checkWidget();
        if (authenticationListener == null) {
            SWT.error(4);
        }
        this.webBrowser.removeAuthenticationListener(authenticationListener);
    }
    
    public void removeCloseWindowListener(final CloseWindowListener closeWindowListener) {
        this.checkWidget();
        if (closeWindowListener == null) {
            SWT.error(4);
        }
        this.webBrowser.removeCloseWindowListener(closeWindowListener);
    }
    
    public void removeLocationListener(final LocationListener locationListener) {
        this.checkWidget();
        if (locationListener == null) {
            SWT.error(4);
        }
        this.webBrowser.removeLocationListener(locationListener);
    }
    
    public void removeOpenWindowListener(final OpenWindowListener openWindowListener) {
        this.checkWidget();
        if (openWindowListener == null) {
            SWT.error(4);
        }
        this.webBrowser.removeOpenWindowListener(openWindowListener);
    }
    
    public void removeProgressListener(final ProgressListener progressListener) {
        this.checkWidget();
        if (progressListener == null) {
            SWT.error(4);
        }
        this.webBrowser.removeProgressListener(progressListener);
    }
    
    public void removeStatusTextListener(final StatusTextListener statusTextListener) {
        this.checkWidget();
        if (statusTextListener == null) {
            SWT.error(4);
        }
        this.webBrowser.removeStatusTextListener(statusTextListener);
    }
    
    public void removeTitleListener(final TitleListener titleListener) {
        this.checkWidget();
        if (titleListener == null) {
            SWT.error(4);
        }
        this.webBrowser.removeTitleListener(titleListener);
    }
    
    public void removeVisibilityWindowListener(final VisibilityWindowListener visibilityWindowListener) {
        this.checkWidget();
        if (visibilityWindowListener == null) {
            SWT.error(4);
        }
        this.webBrowser.removeVisibilityWindowListener(visibilityWindowListener);
    }
    
    public void setJavascriptEnabled(final boolean jsEnabled) {
        this.checkWidget();
        this.webBrowser.jsEnabled = jsEnabled;
        this.webBrowser.jsEnabledChanged = true;
    }
    
    public boolean setText(final String s) {
        this.checkWidget();
        return this.setText(s, true);
    }
    
    public boolean setText(final String s, final boolean b) {
        this.checkWidget();
        if (s == null) {
            SWT.error(4);
        }
        return this.webBrowser.setText(s, b);
    }
    
    public boolean setUrl(final String s) {
        this.checkWidget();
        return this.setUrl(s, null, null);
    }
    
    public boolean setUrl(final String s, final String s2, final String[] array) {
        this.checkWidget();
        if (s == null) {
            SWT.error(4);
        }
        return this.webBrowser.setUrl(s, s2, array);
    }
    
    public void stop() {
        this.checkWidget();
        this.webBrowser.stop();
    }
}
