// 
// Decompiled by Procyon v0.5.30
// 

package com.photochannel.easyUploader;

import netscape.javascript.JSObject;
import java.applet.Applet;
import javax.swing.JApplet;

public class EasyUploader extends JApplet
{
    private static Boolean a;
    private b b;
    private d c;
    private static /* synthetic */ boolean d;
    
    public EasyUploader() {
        this.b = new b();
        this.c = null;
    }
    
    public void init() {
        synchronized (EasyUploader.a) {
            if (EasyUploader.a) {
                System.out.println(".....2nd instance detected --- IGNORING");
            }
            EasyUploader.a = true;
        }
        final Applet applet;
        applet.init();
    }
    
    private AppletParams b() {
        final AppletParams appletParams;
        (appletParams = new AppletParams()).f(this.getParameter("appContextID"));
        appletParams.d(this.getParameter("batchInitURL"));
        final AppletParams appletParams2 = appletParams;
        final String s = "DEBUG";
        if (!EasyUploader.d && s.length() <= 0) {
            throw new AssertionError();
        }
        final String parameter;
        appletParams2.a((parameter = this.getParameter(s)) != null && Boolean.parseBoolean(parameter));
        appletParams.e(this.getParameter("errorLoggingURL"));
        appletParams.a(this.getParameter("debugLogFile"));
        appletParams.g(this.getParameter("userID"));
        appletParams.c(this.getParameter("speedTest"));
        appletParams.h(this.getParameter("reducePhotosDefault"));
        appletParams.b(this.getParameter("language"));
        if (this.getParameter("UploadMaxThreads") != null) {
            appletParams.a(Integer.parseInt(this.getParameter("UploadMaxThreads")));
        }
        if (this.getParameter("ImageDownscalingJpegQFactor") != null) {
            appletParams.b(Integer.parseInt(this.getParameter("ImageDownscalingJpegQFactor")));
        }
        if (this.getParameter("ImageDownscalingMinDimensionPx1") != null) {
            appletParams.c(Integer.parseInt(this.getParameter("ImageDownscalingMinDimensionPx1")));
        }
        if (this.getParameter("ImageDownscalingMinDimensionPx2") != null) {
            appletParams.d(Integer.parseInt(this.getParameter("ImageDownscalingMinDimensionPx2")));
        }
        if (this.getParameter("imageMinimumSize") != null) {
            this.getParameter("imageMinimumSize");
        }
        int int1 = Integer.MAX_VALUE;
        if (this.getParameter("maxImageSize") != null) {
            try {
                int1 = Integer.parseInt(this.getParameter("maxImageSize"));
            }
            catch (Throwable t) {
                int1 = Integer.MAX_VALUE;
            }
        }
        appletParams.e(int1);
        boolean boolean1 = false;
        if (this.getParameter("enableArchiveClientLog") != null) {
            try {
                boolean1 = Boolean.parseBoolean(this.getParameter("enableArchiveClientLog"));
            }
            catch (Throwable t2) {
                boolean1 = false;
            }
        }
        appletParams.b(boolean1);
        return appletParams;
    }
    
    public void start() {
        this.b.a(this.b(), this, this.getCodeBase(), this.getContentPane());
        this.b.a();
    }
    
    public void destroy() {
        EasyUploader.a = false;
        this.b.b();
    }
    
    public void log(final String s) {
        com.photochannel.easyUploader.b.a(s);
    }
    
    public void stop() {
        this.b.c();
    }
    
    public void pickFiles() {
        this.b.d();
    }
    
    public void removeAll() {
        this.b.e();
    }
    
    public void cancelUpload() {
        this.b.f();
    }
    
    public void viewAsThumbnails() {
        this.b.g();
    }
    
    public void viewAsList() {
        this.b.h();
    }
    
    public void startUpload(final String s, final String s2) {
        this.b.a(s, s2);
    }
    
    public int getPhotoCount() {
        return this.b.i();
    }
    
    public final d a() {
        if (this.c == null) {
            this.c = new d(JSObject.getWindow((Applet)this));
        }
        return this.c;
    }
    
    static {
        EasyUploader.d = !EasyUploader.class.desiredAssertionStatus();
        EasyUploader.a = new Boolean(false);
    }
}
