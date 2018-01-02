// 
// Decompiled by Procyon v0.5.30
// 

package mika.application;

import java.awt.Component;
import mika.system.S_Debug;
import java.applet.Applet;

public abstract class ApplicationWindow extends Applet implements Runnable
{
    Thread m_Thread;
    ApplicationFrame m_Window;
    Application m_Application;
    
    public abstract Application createApplication();
    
    public ApplicationWindow() {
        this.m_Thread = null;
        this.m_Window = null;
        this.m_Application = null;
    }
    
    public String getAppletInfo() {
        return this.m_Application.getInformation();
    }
    
    public void init() {
        S_Debug.start(this);
        S_Debug.print("ApplicationWindow::init");
    }
    
    public void destroy() {
        S_Debug.print("ApplicationWindow::destroy");
        if (this.m_Application != null) {
            this.m_Application.destroy();
            this.m_Application = null;
        }
    }
    
    public void start() {
        S_Debug.print("ApplicationWindow::start");
        if (this.m_Thread == null) {
            (this.m_Thread = new Thread(this, "Main application thread(windowed)")).start();
        }
        if (this.m_Application != null) {
            this.m_Application.reshape();
        }
    }
    
    public void stop() {
        S_Debug.print("ApplicationWindow::stop");
        if (this.m_Thread != null) {
            this.m_Thread.stop();
            this.m_Thread = null;
        }
        if (this.m_Window != null) {
            this.m_Window.dispose();
            this.m_Window = null;
        }
    }
    
    public void run() {
        S_Debug.print("ApplicationWindow::run");
        try {
            this.m_Application = this.createApplication();
            final Application application = this.m_Application;
            Application.setApplet(this);
            this.m_Application.readParameters();
            (this.m_Window = new ApplicationFrame(this.m_Application.getName(), 0, 0, this.m_Application)).move((this.getToolkit().getScreenSize().width - this.m_Application.getWidth() - this.m_Window.insets().left - this.m_Window.insets().left) / 2, (this.getToolkit().getScreenSize().height - this.m_Application.getHeight() - this.m_Window.insets().top - this.m_Window.insets().bottom) / 2);
            this.m_Window.resize(this.m_Application.getWidth() + this.m_Window.insets().left + this.m_Window.insets().right, this.m_Application.getHeight() + this.m_Window.insets().top + this.m_Window.insets().bottom);
            this.m_Window.add(this.m_Application);
            this.m_Window.show();
            this.m_Application.move(this.m_Window.insets().left, this.m_Window.insets().top);
            this.m_Application.setOffset(this.m_Window.insets().left, this.m_Window.insets().top);
            this.m_Application.requestFocus();
            this.m_Application.prepareRunApplication();
            this.m_Application.runApplication();
        }
        catch (Exception ex) {
            S_Debug.print(ex);
        }
    }
}
