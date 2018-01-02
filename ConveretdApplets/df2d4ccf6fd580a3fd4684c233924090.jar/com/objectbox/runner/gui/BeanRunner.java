// 
// Decompiled by Procyon v0.5.30
// 

package com.objectbox.runner.gui;

import com.objectbox.runner.model.JBProperties;
import java.awt.Frame;
import java.util.Enumeration;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.Point;
import java.applet.AppletStub;
import java.awt.Component;
import java.net.URL;
import com.objectbox.gui.lwcomp.JBSmallWindow;
import java.awt.Container;
import com.objectbox.runner.util.JBLogger;
import com.objectbox.runner.applet.OBStub;
import com.objectbox.runner.applet.OBContext;
import java.applet.Applet;
import java.util.Properties;
import java.util.Hashtable;
import com.objectbox.loader.JBURLClassloader;

public class BeanRunner extends Thread
{
    private JBURLClassloader loader;
    String classname;
    String server;
    String codebase;
    String jar;
    Hashtable parameters;
    Properties properties;
    Properties jbee_props;
    String documentbase;
    IAppletWindow frame;
    Thread beanthread;
    boolean isDone;
    private long delay;
    private Applet applettorun;
    private OBContext appletcontext;
    private OBStub mystub;
    
    public BeanRunner() {
        this.loader = null;
        this.classname = "";
        this.server = "";
        this.codebase = "";
        this.jar = "";
        this.parameters = new Hashtable();
        this.properties = new Properties();
        this.jbee_props = new Properties();
        this.documentbase = "";
        this.frame = null;
        this.beanthread = null;
        this.isDone = false;
        this.delay = 250L;
        this.init();
    }
    
    public BeanRunner(final Runnable runnable) {
        super(runnable);
        this.loader = null;
        this.classname = "";
        this.server = "";
        this.codebase = "";
        this.jar = "";
        this.parameters = new Hashtable();
        this.properties = new Properties();
        this.jbee_props = new Properties();
        this.documentbase = "";
        this.frame = null;
        this.beanthread = null;
        this.isDone = false;
        this.delay = 250L;
        this.init();
    }
    
    public BeanRunner(final Runnable runnable, final String s) {
        super(runnable, s);
        this.loader = null;
        this.classname = "";
        this.server = "";
        this.codebase = "";
        this.jar = "";
        this.parameters = new Hashtable();
        this.properties = new Properties();
        this.jbee_props = new Properties();
        this.documentbase = "";
        this.frame = null;
        this.beanthread = null;
        this.isDone = false;
        this.delay = 250L;
        this.init();
    }
    
    public BeanRunner(final String s) {
        super(s);
        this.loader = null;
        this.classname = "";
        this.server = "";
        this.codebase = "";
        this.jar = "";
        this.parameters = new Hashtable();
        this.properties = new Properties();
        this.jbee_props = new Properties();
        this.documentbase = "";
        this.frame = null;
        this.beanthread = null;
        this.isDone = false;
        this.delay = 250L;
        this.init();
    }
    
    public BeanRunner(final ThreadGroup threadGroup, final Runnable runnable) {
        super(threadGroup, runnable);
        this.loader = null;
        this.classname = "";
        this.server = "";
        this.codebase = "";
        this.jar = "";
        this.parameters = new Hashtable();
        this.properties = new Properties();
        this.jbee_props = new Properties();
        this.documentbase = "";
        this.frame = null;
        this.beanthread = null;
        this.isDone = false;
        this.delay = 250L;
        this.init();
    }
    
    public BeanRunner(final ThreadGroup threadGroup, final Runnable runnable, final String s) {
        super(threadGroup, runnable, s);
        this.loader = null;
        this.classname = "";
        this.server = "";
        this.codebase = "";
        this.jar = "";
        this.parameters = new Hashtable();
        this.properties = new Properties();
        this.jbee_props = new Properties();
        this.documentbase = "";
        this.frame = null;
        this.beanthread = null;
        this.isDone = false;
        this.delay = 250L;
        this.init();
    }
    
    public BeanRunner(final ThreadGroup threadGroup, final String s) {
        super(threadGroup, s);
        this.loader = null;
        this.classname = "";
        this.server = "";
        this.codebase = "";
        this.jar = "";
        this.parameters = new Hashtable();
        this.properties = new Properties();
        this.jbee_props = new Properties();
        this.documentbase = "";
        this.frame = null;
        this.beanthread = null;
        this.isDone = false;
        this.delay = 250L;
        this.init();
    }
    
    public void destroy() {
        final ThreadGroup threadGroup = this.getThreadGroup();
        final Thread[] array = new Thread[threadGroup.activeCount() + 5];
        for (int enumerate = threadGroup.enumerate(array, true), i = 0; i < enumerate; ++i) {
            JBLogger.log("Stopping: " + array[i].getName());
        }
        super.destroy();
    }
    
    public void finalize() {
    }
    
    public OBContext getAppletContext() {
        return this.appletcontext;
    }
    
    public long getDelay() {
        return this.delay;
    }
    
    public Container getFrame() {
        return (Container)this.frame;
    }
    
    public synchronized boolean getIsDone() {
        if (JBee.killall) {
            this.isDone = true;
        }
        return this.isDone;
    }
    
    public JBURLClassloader getLoader() throws NullPointerException {
        if (this.loader == null) {
            throw new NullPointerException("Loader is null");
        }
        return this.loader;
    }
    
    private void init() {
        this.setDaemon(true);
    }
    
    public void invokeApplet(final JBURLClassloader jburlClassloader, final String s, final String server, final String s2, final String jarfile, final Hashtable hashtable, final String s3) {
        Applet instance = null;
        String codebase = "";
        String string = "";
        try {
            jburlClassloader.setSecurityID("-" + s2.hashCode() + s3.hashCode() + s.hashCode());
            final String rootclass = s.endsWith(".class") ? s.substring(0, s.lastIndexOf(".")) : s;
            string = ((s3.startsWith("http") || s3.startsWith("file")) ? s3 : (String.valueOf(server) + "/" + s3));
            if (!string.endsWith("/")) {
                string = String.valueOf(string) + "/";
            }
            if (s2.startsWith("http") || s2.startsWith("file")) {
                codebase = s2;
            }
            else if (s2.startsWith("/")) {
                codebase = String.valueOf(server) + ((s2.compareTo(".") == 0) ? "" : s2);
            }
            else {
                codebase = String.valueOf(string) + ((s2.compareTo(".") == 0) ? "" : s2);
            }
            if (!codebase.endsWith("/")) {
                codebase = String.valueOf(codebase) + "/";
            }
            jburlClassloader.setCodebase(codebase);
            jburlClassloader.setServer(server);
            jburlClassloader.setRootclass(rootclass);
            jburlClassloader.setJarfile(jarfile);
            jburlClassloader.readCacheFromFile();
            jburlClassloader.setOwner(this);
            jburlClassloader.setState(1);
            final Class resolveClass = jburlClassloader.resolveClass(rootclass);
            jburlClassloader.setState(5);
            instance = resolveClass.newInstance();
        }
        catch (Throwable t) {
            JBLogger.log("BeanRunner invokeapplet " + t);
            jburlClassloader.setState(4);
        }
        if (instance == null) {
            JBee.showMessage("Can't start applet: \n" + s + "\n Check the applet properties.", false);
            this.setIsDone(true);
            return;
        }
        String s4 = ((Hashtable<K, String>)this.jbee_props).get("Window type");
        if (s4 == null) {
            s4 = "Frame";
        }
        final String validUrl = jburlClassloader.makeValidUrl(s);
        if (s4.equals("Small")) {
            this.frame = new JBSmallWindow(JBee.getRunningInstanceFrame());
        }
        else if (s4.equals("Standard")) {
            this.frame = new AppletFrame(validUrl);
        }
        ((Container)this.frame).setSize(200, 200);
        if (instance instanceof Applet) {
            this.applettorun = instance;
            this.mystub = null;
            try {
                this.mystub = new OBStub(this.applettorun, (Component)this.frame, new URL(codebase), new URL(string));
                this.applettorun.setStub(this.mystub);
                this.appletcontext = (OBContext)this.mystub.getAppletContext();
                final String preference = JBee.getPreference("useimagecache");
                if (preference == null || preference.compareTo("true") == 0) {
                    this.appletcontext.setUseImageCache(true);
                }
                else {
                    this.appletcontext.setUseImageCache(false);
                }
            }
            catch (Throwable t2) {
                JBLogger.log(t2.toString());
            }
            final Enumeration<String> keys = this.parameters.keys();
            while (keys.hasMoreElements()) {
                final String s5 = keys.nextElement();
                this.mystub.addParameter(s5, (String)this.parameters.get(s5));
            }
            final String value = ((Hashtable<K, String>)this.jbee_props).get("xPos");
            final String value2 = ((Hashtable<K, String>)this.jbee_props).get("yPos");
            int int1 = 0;
            int int2 = 0;
            if (value != null && value2 != null) {
                int1 = Integer.parseInt(value);
                int2 = Integer.parseInt(value2);
            }
            final Point location = new Point(int1, int2);
            final Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
            final String string2 = ((Hashtable<K, Object>)this.properties).get("width").toString();
            final String string3 = ((Hashtable<K, Object>)this.properties).get("height").toString();
            int int3;
            int int4;
            try {
                if (string2.endsWith("%")) {
                    int3 = (int)(screenSize.width * (Integer.parseInt(string2.substring(0, string2.lastIndexOf("%"))) / 100.0));
                }
                else {
                    int3 = Integer.parseInt(string2);
                }
                if (string3.endsWith("%")) {
                    int4 = (int)(screenSize.height * (Integer.parseInt(string3.substring(0, string3.lastIndexOf("%"))) / 100.0));
                }
                else {
                    int4 = Integer.parseInt(string3);
                }
            }
            catch (Throwable t3) {
                int3 = screenSize.width - 200;
                int4 = screenSize.height - 200;
                JBLogger.log("Applet dimension problem:" + t3);
            }
            final Dimension size = new Dimension(int3, int4);
            if (location != null && size != null) {
                JBLogger.log("Restoring window persistent state");
                this.applettorun.setSize(size);
                ((Container)this.frame).setLocation(location);
            }
            else {
                if (this.applettorun.getSize().width == 0 || this.applettorun.getSize().height == 0) {
                    int int5 = 100;
                    int int6 = 100;
                    try {
                        int6 = Integer.parseInt(((Hashtable<K, String>)this.properties).get("width"));
                        int5 = Integer.parseInt(((Hashtable<K, String>)this.properties).get("height"));
                    }
                    catch (NumberFormatException ex) {
                        JBLogger.log(ex.toString());
                    }
                    this.applettorun.setSize(new Dimension(int6, int5));
                }
                final Dimension size2 = new Dimension(this.applettorun.getSize().width, this.applettorun.getSize().height + AppletFrame.statusheight);
                JBLogger.log("Applet size: " + size2);
                ((Container)this.frame).setSize(size2);
            }
            this.frame.addApplet(this.applettorun);
            ((Container)this.frame).addNotify();
            this.applettorun.addNotify();
            if (this.frame instanceof JBSmallWindow) {
                ((JBSmallWindow)this.frame).validate();
            }
            try {
                this.applettorun.init();
            }
            catch (Throwable t4) {
                JBLogger.log(t4.toString());
            }
            try {
                this.applettorun.start();
                this.mystub.setIsActive(true);
            }
            catch (Throwable t5) {
                JBLogger.log(t5.toString());
                this.setIsDone(true);
            }
            ((Container)this.frame).setVisible(true);
            ((Container)this.frame).validate();
            this.applettorun.requestFocus();
        }
    }
    
    public void kill() {
        try {
            if (this.frame != null) {
                ((Hashtable<String, String>)this.jbee_props).put("xPos", String.valueOf(((Container)this.frame).getLocation().x));
                ((Hashtable<String, String>)this.jbee_props).put("yPos", String.valueOf(((Container)this.frame).getLocation().y));
                ((Hashtable<String, String>)this.properties).put("height", String.valueOf(this.applettorun.getSize().height));
                ((Hashtable<String, String>)this.properties).put("width", String.valueOf(this.applettorun.getSize().width));
                ((Container)this.frame).setVisible(false);
                JBLogger.log("Saving window persistent state");
            }
            if (this.loader != null) {
                this.loader.saveme();
                this.loader.dispose();
                this.loader.setState(6);
            }
            final ThreadGroup threadGroup = this.getThreadGroup();
            final Thread[] array = new Thread[threadGroup.activeCount() + 5];
            for (int enumerate = threadGroup.enumerate(array, true), i = 0; i < enumerate; ++i) {
                if (array[i] != this) {
                    JBLogger.log("Stopping: " + array[i].getName() + array[i].isAlive());
                    array[i].stop();
                    array[i] = null;
                }
            }
        }
        catch (Exception ex) {
            JBLogger.log("Beanrunner.kill " + ex.toString());
            this.setIsDone(true);
        }
        finally {
            this.frame.kill();
            if (this.frame instanceof Frame) {
                ((Frame)this.frame).dispose();
                ((Frame)this.frame).removeNotify();
            }
            else if (this.frame instanceof JBSmallWindow) {
                ((JBSmallWindow)this.frame).dispose();
                ((JBSmallWindow)this.frame).removeNotify();
            }
            this.setIsDone(true);
            this.appletcontext = null;
            this.mystub = null;
            this.applettorun = null;
            this.frame = null;
            this.loader = null;
            this.parameters = null;
            this.properties = null;
            System.gc();
            JBLogger.log("Kill in finally block");
        }
        this.frame.kill();
        if (this.frame instanceof Frame) {
            ((Frame)this.frame).dispose();
            ((Frame)this.frame).removeNotify();
        }
        else if (this.frame instanceof JBSmallWindow) {
            ((JBSmallWindow)this.frame).dispose();
            ((JBSmallWindow)this.frame).removeNotify();
        }
        this.setIsDone(true);
        this.appletcontext = null;
        this.mystub = null;
        this.applettorun = null;
        this.frame = null;
        this.loader = null;
        this.parameters = null;
        this.properties = null;
        System.gc();
        JBLogger.log("Kill in finally block");
    }
    
    public void run() {
        try {
            this.setIsDone(false);
            this.invokeApplet(this.loader, this.classname, this.server, this.codebase, this.jar, this.parameters, this.documentbase);
            this.loader.setState(0);
            while (!this.getIsDone() && !this.frame.isDone()) {
                try {
                    Thread.sleep(this.delay);
                }
                catch (InterruptedException ex) {
                    JBLogger.log("BeanRunner.run " + ex.toString());
                    this.setIsDone(true);
                    break;
                }
            }
            JBLogger.log("BeanRunner.run start to kill");
            this.kill();
            JBLogger.log("BeanRunner.run done killing");
        }
        catch (Throwable t) {
            JBLogger.log("BeanRunner.run " + t.toString());
        }
    }
    
    public void set(final JBURLClassloader loader, final JBProperties jbProperties) {
        this.properties = jbProperties.getProps();
        this.parameters = jbProperties.getParameters();
        this.jbee_props = jbProperties.getJBeeProps();
        final String classname = ((Hashtable<K, String>)this.properties).get("code");
        final String codebase = ((Hashtable<K, String>)this.properties).get("codebase");
        final String server = ((Hashtable<K, String>)this.properties).get("host");
        final String jar = ((Hashtable<K, String>)this.properties).get("archive");
        final String documentbase = ((Hashtable<K, String>)this.properties).get("documentbase");
        final String s = ((Hashtable<K, String>)this.properties).get("width");
        final String s2 = ((Hashtable<K, String>)this.properties).get("height");
        final String s3 = ((Hashtable<K, String>)this.properties).get("object");
        final String s4 = ((Hashtable<K, String>)this.properties).get("alt");
        final String s5 = ((Hashtable<K, String>)this.properties).get("align");
        this.classname = classname;
        this.server = server;
        this.codebase = codebase;
        this.jar = jar;
        this.documentbase = documentbase;
        this.loader = loader;
    }
    
    public void set(final JBURLClassloader loader, final String classname, final String server, final String codebase, final String jar, final Hashtable parameters, final String documentbase) {
        this.classname = classname;
        this.server = server;
        this.codebase = codebase;
        this.jar = jar;
        this.parameters = parameters;
        this.documentbase = documentbase;
        this.loader = loader;
    }
    
    void setAppletcontext(final OBContext appletcontext) {
        this.appletcontext = appletcontext;
    }
    
    public void setDelay(final long delay) {
        this.delay = delay;
    }
    
    public void setIsDone(final boolean isDone) {
        this.isDone = isDone;
    }
    
    public void showThreads() {
        final ThreadGroup threadGroup = this.getThreadGroup();
        final Thread[] array = new Thread[threadGroup.activeCount() + 5];
        for (int enumerate = threadGroup.enumerate(array, true), i = 0; i < enumerate; ++i) {
            JBLogger.log(i + " Thread: " + array[i].getName() + " " + array[i].isAlive());
        }
    }
}
