// 
// Decompiled by Procyon v0.5.30
// 

package org.eclipse.swt.browser;

import org.eclipse.swt.SWT;

public class BrowserFunction
{
    Browser browser;
    String name;
    String functionString;
    int index;
    boolean isEvaluate;
    
    public BrowserFunction(final Browser browser, final String s) {
        this(browser, s, true);
    }
    
    BrowserFunction(final Browser browser, final String name, final boolean b) {
        if (browser == null) {
            SWT.error(4);
        }
        if (name == null) {
            SWT.error(4);
        }
        if (browser.isDisposed()) {
            SWT.error(24);
        }
        browser.checkWidget();
        this.browser = browser;
        this.name = name;
        if (b) {
            browser.webBrowser.createFunction(this);
        }
    }
    
    public void dispose() {
        this.dispose(true);
    }
    
    void dispose(final boolean b) {
        if (this.index < 0) {
            return;
        }
        if (b) {
            this.browser.webBrowser.destroyFunction(this);
        }
        this.browser = null;
        final String s = null;
        this.functionString = s;
        this.name = s;
        this.index = -1;
    }
    
    public Object function(final Object[] array) {
        if (this.index < 0) {
            SWT.error(49);
        }
        this.browser.checkWidget();
        return null;
    }
    
    public Browser getBrowser() {
        if (this.index < 0) {
            SWT.error(49);
        }
        this.browser.checkWidget();
        return this.browser;
    }
    
    public String getName() {
        if (this.index < 0) {
            SWT.error(49);
        }
        this.browser.checkWidget();
        return this.name;
    }
    
    public boolean isDisposed() {
        return this.index < 0;
    }
}
