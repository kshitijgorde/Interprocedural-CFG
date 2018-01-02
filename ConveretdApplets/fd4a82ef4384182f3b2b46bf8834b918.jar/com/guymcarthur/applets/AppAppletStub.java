// 
// Decompiled by Procyon v0.5.30
// 

package com.guymcarthur.applets;

import java.applet.AppletContext;
import java.net.MalformedURLException;
import java.net.URL;
import java.applet.AppletStub;

public class AppAppletStub implements AppletStub
{
    public boolean isActive() {
        return true;
    }
    
    public URL getDocumentBase() {
        try {
            return new URL("file:///");
        }
        catch (MalformedURLException ex) {
            return null;
        }
    }
    
    public URL getCodeBase() {
        try {
            return new URL("file:///");
        }
        catch (MalformedURLException ex) {
            return null;
        }
    }
    
    public String getParameter(final String s) {
        return System.getProperty(s);
    }
    
    public AppletContext getAppletContext() {
        return new AppAppletContext();
    }
    
    public void appletResize(final int n, final int n2) {
    }
}
