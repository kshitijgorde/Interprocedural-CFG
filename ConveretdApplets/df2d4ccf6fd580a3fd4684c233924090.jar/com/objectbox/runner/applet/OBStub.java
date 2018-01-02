// 
// Decompiled by Procyon v0.5.30
// 

package com.objectbox.runner.applet;

import java.applet.AppletContext;
import com.objectbox.runner.util.JBLogger;
import com.objectbox.gui.lwcomp.JBSmallWindow;
import com.objectbox.runner.gui.AppletFrame;
import java.awt.Component;
import java.applet.Applet;
import java.util.Hashtable;
import java.net.URL;
import java.applet.AppletStub;

public class OBStub implements AppletStub
{
    private URL codeBase;
    private URL documentBase;
    private Hashtable parameterhash;
    private boolean isactive;
    private Applet applet;
    private Component parentframe;
    private OBContext appletcontext;
    
    public OBStub(final Applet applet, final Component parentframe, final URL codeBase, final URL documentBase) {
        this.codeBase = null;
        this.documentBase = null;
        this.parameterhash = null;
        this.isactive = false;
        this.applet = null;
        this.parentframe = null;
        this.appletcontext = null;
        this.applet = applet;
        this.parentframe = parentframe;
        this.codeBase = codeBase;
        this.documentBase = documentBase;
        this.parameterhash = new Hashtable();
    }
    
    public boolean addParameter(final String s, final String s2) {
        return this.parameterhash.put(s.toUpperCase(), s2) != null;
    }
    
    public void appletResize(final int n, final int n2) {
        if (this.parentframe instanceof AppletFrame) {
            this.parentframe.setSize(n + 4, n2 + AppletFrame.statusheight);
        }
        else if (this.parentframe instanceof JBSmallWindow) {
            this.parentframe.setSize(n, n2 + JBSmallWindow.header_height);
        }
        this.applet.resize(n, n2);
        JBLogger.log("OBStub appletResize " + n + "," + n2);
    }
    
    public void finalize() {
    }
    
    public AppletContext getAppletContext() {
        JBLogger.log("Applet Stub: getAppletContext");
        if (this.appletcontext == null) {
            this.appletcontext = new OBContext(this.applet, this.parentframe);
        }
        else {
            this.appletcontext.setContext(this.applet, this.parentframe);
        }
        return this.appletcontext;
    }
    
    public URL getCodeBase() {
        JBLogger.log("Applet Stub: getCodebase " + this.codeBase);
        return this.codeBase;
    }
    
    public URL getDocumentBase() {
        JBLogger.log("Applet Stub: getDocumentBase " + this.documentBase);
        return this.documentBase;
    }
    
    public String getParameter(final String s) {
        JBLogger.log("Applet Stub: getParameter " + s);
        return this.parameterhash.get(s.toUpperCase());
    }
    
    public boolean isActive() {
        JBLogger.log("Applet Stub: isActive " + this.isactive);
        return this.isactive;
    }
    
    public void setIsActive(final boolean isactive) {
        JBLogger.log("Applet Stub: setIsActive");
        this.isactive = isactive;
    }
}
