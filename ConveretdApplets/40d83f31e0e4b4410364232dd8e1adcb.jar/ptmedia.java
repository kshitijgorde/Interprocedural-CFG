import java.lang.reflect.Method;
import java.awt.Dimension;
import java.awt.Component;
import java.net.MalformedURLException;
import java.net.URL;
import java.applet.AppletStub;
import java.applet.Applet;

// 
// Decompiled by Procyon v0.5.30
// 

public class ptmedia extends Applet implements Runnable
{
    Thread sendMovie;
    String fname;
    String PTViewer;
    int vx;
    int vy;
    ptviewer pv;
    int loop;
    Object JMFplayer;
    long JMFStopTime;
    static final boolean debugJMF = false;
    static /* synthetic */ Class class$java$lang$String;
    
    public ptmedia() {
        this.fname = "file";
        this.PTViewer = "ptviewer";
        this.loop = 1;
    }
    
    public ptmedia(final ptviewer pv, final String s) {
        this.fname = "file";
        this.PTViewer = "ptviewer";
        this.loop = 1;
        this.pv = pv;
        this.setStub(new ptstub(this.pv, s));
    }
    
    public void init() {
        final String parameter = this.getParameter("file");
        if (parameter != null) {
            this.fname = parameter;
        }
        final String parameter2 = this.getParameter("PTViewer");
        if (parameter2 != null) {
            this.PTViewer = parameter2;
        }
        final String parameter3 = this.getParameter("vx");
        if (parameter3 != null) {
            this.vx = Integer.parseInt(parameter3);
        }
        final String parameter4 = this.getParameter("vy");
        if (parameter4 != null) {
            this.vy = Integer.parseInt(parameter4);
        }
        final String parameter5 = this.getParameter("loop");
        if (parameter5 != null) {
            this.loop = Integer.parseInt(parameter5);
        }
        while (this.pv == null) {
            try {
                this.pv = (ptviewer)this.getAppletContext().getApplet(this.PTViewer);
            }
            catch (Exception ex) {
                try {
                    Thread.sleep(2000L);
                }
                catch (InterruptedException ex2) {
                    return;
                }
            }
        }
        this.InitJMFMedia(this.fname);
    }
    
    public void start() {
        if (this.sendMovie == null) {
            (this.sendMovie = new Thread(this)).start();
        }
    }
    
    public synchronized void stop() {
        if (this.sendMovie != null) {
            this.sendMovie.stop();
            this.sendMovie = null;
        }
        if (this.pv != null) {
            this.StopJMFPlayer();
        }
    }
    
    public synchronized void destroy() {
        if (this.pv != null) {
            this.CloseJMFPlayer();
        }
    }
    
    void InitJMFMedia(final String s) {
        if (this.pv == null) {
            return;
        }
        String externalForm;
        try {
            externalForm = new URL(this.pv.getDocumentBase(), s).toExternalForm();
        }
        catch (MalformedURLException ex) {
            return;
        }
        Class<?> forName;
        Object instance;
        try {
            forName = Class.forName("javax.media.MediaLocator");
            instance = forName.getConstructor((ptmedia.class$java$lang$String != null) ? ptmedia.class$java$lang$String : (ptmedia.class$java$lang$String = class$("java.lang.String"))).newInstance(externalForm);
            if (instance == null) {
                return;
            }
        }
        catch (Exception ex2) {
            return;
        }
        try {
            final Class<?> forName2 = Class.forName("javax.media.Manager");
            this.JMFplayer = forName2.getMethod("createPlayer", forName).invoke(forName2, instance);
        }
        catch (Exception ex3) {
            this.JMFplayer = null;
        }
    }
    
    public void run() {
        if (this.pv == null) {
            return;
        }
        if (this.JMFplayer != null) {
            while (true) {
                Label_0066: {
                    try {
                        Class.forName("javax.media.Player").getMethod("start", (Class<?>[])null).invoke(this.JMFplayer, (Object[])null);
                        break Label_0066;
                    }
                    catch (Exception ex) {
                        return;
                    }
                    this.StartJMFPlayer();
                    if (this.JMFStopTime == 0L) {
                        try {
                            Thread.sleep(200L);
                        }
                        catch (InterruptedException ex2) {
                            return;
                        }
                    }
                }
                if (this.JMFStopTime == 0L) {
                    continue;
                }
                break;
            }
            long currentTimeMillis;
            while ((currentTimeMillis = System.currentTimeMillis()) < this.JMFStopTime) {
                try {
                    Thread.sleep(this.JMFStopTime - currentTimeMillis);
                }
                catch (InterruptedException ex3) {
                    return;
                }
            }
            this.StopJMFPlayer();
        }
    }
    
    void StartJMFPlayer() {
        if (this.JMFplayer == null) {
            return;
        }
        int intValue;
        try {
            intValue = (int)this.JMFplayer.getClass().getField("Realized").get(this.JMFplayer);
        }
        catch (Exception ex) {
            return;
        }
        if (this.JMFGetPlayerState() >= intValue) {
            try {
                final Component component = (Component)Class.forName("javax.media.Player").getMethod("getVisualComponent", (Class<?>[])null).invoke(this.JMFplayer, (Object[])null);
                if (component != null) {
                    this.pv.add(component);
                    final Dimension preferredSize = component.getPreferredSize();
                    component.setBounds(this.vx, this.vy, preferredSize.width, preferredSize.height);
                }
            }
            catch (Exception ex2) {
                return;
            }
            try {
                final Class<?> forName = Class.forName("javax.media.Duration");
                final Object invoke = forName.getMethod("getDuration", (Class[])null).invoke(this.JMFplayer, (Object[])null);
                final Object value = forName.getField("DURATION_UNBOUNDED").get(this.JMFplayer);
                final Object value2 = forName.getField("DURATION_UNKNOWN").get(this.JMFplayer);
                if (invoke == value || invoke == value2) {
                    this.JMFStopTime = System.currentTimeMillis() + 86400000L;
                    return;
                }
                this.JMFStopTime = System.currentTimeMillis() + (long)((double)invoke.getClass().getMethod("getSeconds", (Class<?>[])null).invoke(invoke, (Object[])null) * 1000.0 + 5000.0);
            }
            catch (Exception ex3) {
                this.JMFplayer = null;
            }
        }
    }
    
    void StopJMFPlayer() {
        Class<?> forName = null;
        if (this.JMFplayer == null) {
            return;
        }
        int intValue;
        try {
            intValue = (int)this.JMFplayer.getClass().getField("Realized").get(this.JMFplayer);
        }
        catch (Exception ex) {
            return;
        }
        if (this.JMFGetPlayerState() < intValue) {
            return;
        }
        try {
            forName = Class.forName("javax.media.Controller");
        }
        catch (Exception ex2) {}
        if (this.JMFplayer == null || forName == null) {
            return;
        }
        try {
            final Component component = (Component)Class.forName("javax.media.Player").getMethod("getVisualComponent", (Class<?>[])null).invoke(this.JMFplayer, (Object[])null);
            if (component != null) {
                this.pv.remove(component);
            }
            forName.getMethod("stop", (Class[])null).invoke(this.JMFplayer, (Object[])null);
        }
        catch (Exception ex3) {
            return;
        }
        this.JMFStopTime = 0L;
        try {
            Class.forName("javax.media.Clock").getMethod("setMediaTime", Class.forName("javax.media.Time")).invoke(this.JMFplayer, Class.forName("javax.media.Time").getConstructor(Long.TYPE).newInstance(new Long(0L)));
        }
        catch (Exception ex4) {}
    }
    
    void CloseJMFPlayer() {
        Class<?> forName;
        try {
            forName = Class.forName("javax.media.Controller");
        }
        catch (Exception ex) {
            return;
        }
        if (this.JMFplayer == null || forName == null) {
            return;
        }
        this.StopJMFPlayer();
        try {
            forName.getMethod("deallocate", (Class[])null).invoke(this.JMFplayer, (Object[])null);
            forName.getMethod("close", (Class[])null).invoke(this.JMFplayer, (Object[])null);
        }
        catch (Exception ex2) {}
        this.JMFplayer = null;
    }
    
    int JMFGetPlayerState() {
        Class<?> forName = null;
        try {
            forName = Class.forName("javax.media.Controller");
        }
        catch (Exception ex) {}
        if (this.JMFplayer == null || forName == null) {
            return -1;
        }
        int intValue;
        try {
            final Method method = forName.getMethod("getState", (Class[])null);
            try {
                intValue = (int)method.invoke(this.JMFplayer, (Object[])null);
            }
            catch (Exception ex2) {
                this.JMFplayer = null;
                return -1;
            }
        }
        catch (Exception ex3) {
            this.JMFplayer = null;
            return -1;
        }
        return intValue;
    }
    
    static /* synthetic */ Class class$(final String s) {
        try {
            return Class.forName(s);
        }
        catch (ClassNotFoundException ex) {
            throw new NoClassDefFoundError(ex.getMessage());
        }
    }
}
