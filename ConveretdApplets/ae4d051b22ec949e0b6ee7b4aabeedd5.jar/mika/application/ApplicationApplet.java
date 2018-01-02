// 
// Decompiled by Procyon v0.5.30
// 

package mika.application;

import java.awt.Component;
import mika.system.S_Debug;
import java.applet.Applet;

public abstract class ApplicationApplet extends Applet implements Runnable
{
    Thread m_Thread;
    Application m_Application;
    
    public abstract Application createApplication();
    
    public ApplicationApplet() {
        this.m_Thread = null;
        this.m_Application = null;
    }
    
    public String getAppletInfo() {
        return this.m_Application.getInformation();
    }
    
    public void init() {
        S_Debug.start(this);
        System.out.println("ApplicationApplet::init");
        if (this.m_Thread == null) {
            this.m_Thread = new Thread(this, "Main application thread(applet)");
        }
    }
    
    public void destroy() {
        System.out.println("ApplicationApplet::destroy");
        if (this.m_Thread != null) {
            this.m_Thread.stop();
            this.m_Thread = null;
        }
        if (this.m_Application != null) {
            this.m_Application.destroy();
            this.m_Application = null;
        }
    }
    
    public void start() {
        System.out.println("ApplicationApplet::start");
        if (this.m_Thread != null) {
            this.m_Thread.start();
        }
        if (this.m_Application != null) {
            System.out.println("" + this.size().width + "  " + this.size().height);
            this.resize(this.m_Application.getWidth(), this.m_Application.getHeight());
            System.out.println("" + this.size().width + "  " + this.size().height);
            System.out.println("app reshape  " + this.m_Application.getWidth() + "  " + this.m_Application.getHeight());
            this.m_Application.reshape();
        }
    }
    
    public void stop() {
        System.out.println("ApplicationApplet::stop");
        if (this.m_Thread != null) {
            this.m_Thread.stop();
        }
    }
    
    public void run() {
        System.out.println("ApplicationApplet::run");
        try {
            this.m_Application = this.createApplication();
            final Application application = this.m_Application;
            Application.setApplet(this);
            this.m_Application.readParameters();
            this.add(this.m_Application);
            this.m_Application.setOffset(0, 0);
            this.m_Application.prepareRunApplication();
            this.m_Application.runApplication();
        }
        catch (Exception ex) {
            System.out.println(ex);
        }
    }
}
