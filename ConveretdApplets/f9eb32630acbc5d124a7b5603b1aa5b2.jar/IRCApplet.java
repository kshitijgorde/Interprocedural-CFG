import java.awt.Component;
import java.awt.Label;
import java.awt.LayoutManager;
import java.awt.FlowLayout;
import java.awt.Container;
import irc.FileHandler;
import irc.SoundHandler;
import irc.ImageLoader;
import irc.URLHandler;
import irc.ConfigurationLoader;
import irc.AppletSoundHandler;
import irc.AppletImageLoader;
import irc.AppletURLHandler;
import irc.ParameterMixer;
import irc.StreamParameterProvider;
import irc.AppletFileHandler;
import irc.EventDispatcher;
import irc.IRCApplication;
import irc.ParameterProvider;
import java.applet.Applet;

// 
// Decompiled by Procyon v0.5.30
// 

public class IRCApplet extends Applet implements ParameterProvider
{
    private IRCApplication _application;
    
    public void init() {
        try {
            EventDispatcher.disableBadThreadWarning();
            EventDispatcher.dispatchEventSyncEx(this, "startEff", new Object[0]);
            EventDispatcher.enableBadThreadWarning();
        }
        catch (Throwable t) {
            throw new RuntimeException(t.toString());
        }
    }
    
    public void destroy() {
        try {
            EventDispatcher.disableBadThreadWarning();
            EventDispatcher.dispatchEventSyncEx(this, "stopEff", new Object[0]);
            EventDispatcher.enableBadThreadWarning();
        }
        catch (Throwable t) {
            throw new RuntimeException(t.toString());
        }
    }
    
    public void startEff() {
        try {
            ParameterProvider parameterProvider = this;
            final String parameter = this.getParameter("fileparameter");
            if (parameter != null) {
                parameterProvider = new ParameterMixer(parameterProvider, new StreamParameterProvider(new AppletFileHandler(this).getInputStream(parameter)));
            }
            final ConfigurationLoader configurationLoader = new ConfigurationLoader(parameterProvider, new AppletURLHandler(this.getAppletContext()), new AppletImageLoader(this), new AppletSoundHandler(this), new AppletFileHandler(this));
            (this._application = new IRCApplication(configurationLoader.loadIRCConfiguration(), configurationLoader.loadStartupConfiguration(), this)).init();
            this.setVisible(false);
            this.setVisible(true);
        }
        catch (Throwable t) {
            this.setLayout(new FlowLayout(0));
            this.add(new Label("Startup error : " + t));
        }
    }
    
    public void stopEff() {
        if (this._application != null) {
            this._application.uninit();
        }
        this._application = null;
    }
    
    public void sendString(final String s) {
        if (this._application != null) {
            EventDispatcher.dispatchEventAsync(this._application, "sendString", new Object[] { s });
        }
    }
    
    public void sendString(final String s, final String s2, final String s3, final String s4) {
        if (this._application != null) {
            EventDispatcher.dispatchEventAsync(this._application, "sendString", new Object[] { s, s2, s3, s4 });
        }
    }
    
    public void setFieldText(final String s) {
        if (this._application != null) {
            EventDispatcher.dispatchEventAsync(this._application, "setFieldText", new Object[] { s });
        }
    }
    
    public String getFieldText() {
        if (this._application != null) {
            try {
                return (String)EventDispatcher.dispatchEventAsyncAndWaitEx(this._application, "getFieldText", new Object[0]);
            }
            catch (Throwable t) {
                throw new RuntimeException(t.toString());
            }
        }
        return "";
    }
    
    public void validateText() {
        if (this._application != null) {
            EventDispatcher.dispatchEventAsync(this._application, "validateText", new Object[0]);
        }
    }
    
    public void requestSourceFocus() {
        if (this._application != null) {
            EventDispatcher.dispatchEventAsync(this._application, "requestSourceFocus", new Object[0]);
        }
    }
    
    public void requestSourceFocus(final String s, final String s2, final String s3) {
        if (this._application != null) {
            EventDispatcher.dispatchEventAsync(this._application, "requestSourceFocus", new Object[] { s, s2, s3 });
        }
    }
    
    public void sendPluginEvent(final String s, final Object o) {
        if (this._application != null) {
            EventDispatcher.dispatchEventAsync(this._application, "sendPluginEvent", new Object[] { s, o });
        }
    }
    
    public Object getPluginValue(final String s, final Object o) {
        if (this._application != null) {
            try {
                return EventDispatcher.dispatchEventAsyncAndWaitEx(this._application, "getPluginValue", new Object[] { s, o });
            }
            catch (Throwable t) {
                throw new RuntimeException(t.toString());
            }
        }
        return null;
    }
    
    public IRCApplication getIRCApplication() {
        return this._application;
    }
}
