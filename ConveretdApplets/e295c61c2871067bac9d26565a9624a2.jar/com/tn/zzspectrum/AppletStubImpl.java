// 
// Decompiled by Procyon v0.5.30
// 

package com.tn.zzspectrum;

import java.net.URL;
import java.applet.AppletContext;
import java.applet.AppletStub;

public class AppletStubImpl implements AppletStub
{
    @Override
    public void appletResize(final int width, final int height) {
    }
    
    @Override
    public AppletContext getAppletContext() {
        return null;
    }
    
    @Override
    public URL getCodeBase() {
        try {
            return new URL("valoader:/c:\\program files\\ibm\\visualage for java\\ide\\project_resources\\ZX/");
        }
        catch (Exception e) {
            throw new RuntimeException("Problem hacking codebase:" + e);
        }
    }
    
    @Override
    public URL getDocumentBase() {
        return null;
    }
    
    @Override
    public String getParameter(final String name) {
        if (name.trim().equalsIgnoreCase("if1")) {
            return "y";
        }
        if (name.trim().equalsIgnoreCase("truespeed")) {
            return "y";
        }
        return null;
    }
    
    @Override
    public boolean isActive() {
        return true;
    }
}
