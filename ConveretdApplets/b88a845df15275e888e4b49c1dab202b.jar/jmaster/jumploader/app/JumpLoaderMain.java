// 
// Decompiled by Procyon v0.5.30
// 

package jmaster.jumploader.app;

import java.util.Hashtable;
import java.util.Iterator;
import java.util.Enumeration;
import javax.swing.UIDefaults;
import java.util.List;
import java.util.Collections;
import java.util.ArrayList;
import java.util.HashMap;
import java.io.File;
import jmaster.util.property.D;
import jmaster.jumploader.model.api.config.ViewConfig;
import jmaster.jumploader.view.impl.main.MainView;
import jmaster.util.swing.SwingUtil;
import javax.swing.UIManager;
import java.util.StringTokenizer;
import java.util.Map;
import jmaster.util.swing.GUIHelper;
import jmaster.jumploader.controller.Controller;
import jmaster.jumploader.view.api.main.IMainView;
import jmaster.jumploader.model.api.B;
import jmaster.util.log.A;

public class JumpLoaderMain
{
    public static final String MESSAGES_PROPERTIES_LOCATION = "messages.properties";
    private A E;
    private B C;
    private IMainView B;
    private Controller A;
    private JumpLoaderApplet D;
    static /* synthetic */ Class class$jmaster$jumploader$model$api$B;
    
    public JumpLoaderMain() {
        (this.E = jmaster.util.log.B.getInstance().getLog(this)).D("Starting " + JumpLoaderVersion.getApplicationName());
        GUIHelper.getInstance().getProperty().putAll(jmaster.util.property.B.C().G("messages.properties"));
    }
    
    public Controller getController() {
        return this.A;
    }
    
    public B getModel() {
        return this.C;
    }
    
    public IMainView getView() {
        return this.B;
    }
    
    public JumpLoaderApplet getApplet() {
        return this.D;
    }
    
    public void setApplet(final JumpLoaderApplet d) {
        this.D = d;
    }
    
    public B createModel() {
        jmaster.util.C.B.A(!jmaster.util.C.B.A());
        jmaster.util.C.B.A(this.C == null);
        jmaster.util.C.B.A(this.B == null);
        jmaster.util.C.B.A(this.A == null);
        return this.C = new jmaster.jumploader.model.impl.A(this);
    }
    
    public void initModel() {
        if (!jmaster.util.B.A.C(this.C.B().getUploaderListeners())) {
            final StringTokenizer stringTokenizer = new StringTokenizer(this.C.B().getUploaderListeners(), ",");
            while (stringTokenizer.hasMoreTokens()) {
                final String nextToken = stringTokenizer.nextToken();
                if (this.E.B()) {
                    this.E.D("Instantiating uploader listener from class " + nextToken);
                }
                try {
                    final jmaster.jumploader.model.api.upload.B b = (jmaster.jumploader.model.api.upload.B)Class.forName(nextToken).newInstance();
                    this.C.D().addListener(b);
                    try {
                        jmaster.util.C.B.A(b, "setModel", new Class[] { (JumpLoaderMain.class$jmaster$jumploader$model$api$B == null) ? (JumpLoaderMain.class$jmaster$jumploader$model$api$B = class$("jmaster.jumploader.model.api.B")) : JumpLoaderMain.class$jmaster$jumploader$model$api$B }, new Object[] { this.C });
                    }
                    catch (Exception ex2) {}
                    try {
                        jmaster.util.C.B.A(b, "init", null, null);
                    }
                    catch (Exception ex3) {}
                }
                catch (Exception ex) {
                    this.E.E(ex, ex);
                }
            }
        }
    }
    
    public IMainView createView() {
        jmaster.util.C.B.A(jmaster.util.C.B.A());
        jmaster.util.C.B.A(this.C != null);
        jmaster.util.C.B.A(this.B == null);
        jmaster.util.C.B.A(this.A == null);
        final ViewConfig h = this.C.H();
        Label_0186: {
            if (!jmaster.util.B.A.C(h.getLookAndFeel())) {
                if (!"system".equals(h.getLookAndFeel())) {
                    if ("crossPlatform".equals(h.getLookAndFeel())) {
                        try {
                            UIManager.setLookAndFeel(UIManager.getCrossPlatformLookAndFeelClassName());
                        }
                        catch (Exception ex) {
                            this.E.E(ex, ex);
                        }
                        break Label_0186;
                    }
                    try {
                        UIManager.setLookAndFeel(h.getLookAndFeel());
                    }
                    catch (Throwable t) {
                        this.E.E("Failed to set look and feel: " + h.getLookAndFeel(), t);
                    }
                    break Label_0186;
                }
            }
            try {
                UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
            }
            catch (Exception ex2) {
                this.E.E(ex2, ex2);
            }
        }
        if (h.getUiDefaults() != null) {
            final StringTokenizer stringTokenizer = new StringTokenizer(h.getUiDefaults(), ";");
            while (stringTokenizer.hasMoreTokens()) {
                final String nextToken = stringTokenizer.nextToken();
                try {
                    final int index = nextToken.indexOf(61);
                    if (index == -1) {
                        continue;
                    }
                    SwingUtil.setUiDefaultString(nextToken.substring(0, index).trim(), (nextToken.length() > index + 1) ? nextToken.substring(index + 1).trim() : null);
                }
                catch (Exception ex3) {
                    this.E.E("Failed to process uidefaults property pair: " + nextToken, ex3);
                }
            }
        }
        if (h.isDumpUiDefaults()) {
            this.dumpUiDefaults();
        }
        if (h.getGuiProperties() != null) {
            final D property = GUIHelper.getInstance().getProperty();
            final StringTokenizer stringTokenizer2 = new StringTokenizer(h.getGuiProperties(), ";");
            while (stringTokenizer2.hasMoreTokens()) {
                final String nextToken2 = stringTokenizer2.nextToken();
                try {
                    final int index2 = nextToken2.indexOf(61);
                    if (index2 == -1) {
                        continue;
                    }
                    property.setProperty(nextToken2.substring(0, index2).trim(), (nextToken2.length() > index2 + 1) ? nextToken2.substring(index2 + 1).trim() : null);
                }
                catch (Exception ex4) {
                    this.E.E("Failed to process gui properties pair: " + nextToken2, ex4);
                }
            }
        }
        this.B = new MainView(this.C);
        if (this.E.B()) {
            this.E.D("View created: " + this.B);
        }
        return this.B;
    }
    
    public Controller createController() {
        jmaster.util.C.B.A(!jmaster.util.C.B.A());
        jmaster.util.C.B.A(this.C != null);
        jmaster.util.C.B.A(this.B != null);
        jmaster.util.C.B.A(this.A == null);
        this.A = new Controller(this.C, this.B);
        if (this.E.B()) {
            this.E.D("Controller created: " + this.A);
        }
        return this.A;
    }
    
    public void startController() {
        jmaster.util.C.B.A(this.C != null);
        jmaster.util.C.B.A(this.B != null);
        jmaster.util.C.B.A(this.A != null);
        if (this.E.B()) {
            this.E.D("Starting controller, model config objects dump");
            this.E.D("UploaderConfig: \r\r" + this.C.B());
            this.E.D("ViewConfig: \r\n" + this.C.H());
            this.E.D("AppletConfig: \r\n" + this.C.F());
        }
        this.A.start();
        if (!this.C.H().isDisableLocalFileSystem()) {
            final File[] filesFromPropertyString = ViewConfig.getFilesFromPropertyString(this.C.H().getFileBrowserInitialLocation());
            if (filesFromPropertyString != null && filesFromPropertyString.length > 0) {
                this.C.G().setPath(filesFromPropertyString[0]);
            }
        }
    }
    
    public void destroy(final boolean b) {
        try {
            this.A.destroy();
            this.A = null;
            this.B.destroy();
            this.B = null;
            this.C.A(b);
            this.C = null;
        }
        catch (Exception ex) {
            this.E.E("Failed to destroy " + this, ex);
        }
    }
    
    public void dumpUiDefaults() {
        final UIDefaults defaults = UIManager.getDefaults();
        final HashMap<String, Object> hashMap = new HashMap<String, Object>();
        final ArrayList list = new ArrayList<Object>();
        final Enumeration<Object> keys = ((Hashtable<Object, V>)defaults).keys();
        while (keys.hasMoreElements()) {
            final Object nextElement = keys.nextElement();
            hashMap.put("" + nextElement, nextElement);
            list.add("" + nextElement);
        }
        Collections.sort(list);
        for (int i = 0; i < list.size(); ++i) {
            final Object value = hashMap.get(list.get(i));
            final Object value2 = UIManager.get(value);
            if (this.E.B()) {
                this.E.D("" + value + "=" + value2);
            }
        }
    }
    
    public void injectSystemProperties() {
        final D properties = this.C.I().getProperties();
        for (final Object next : ((Hashtable<Object, V>)properties).keySet()) {
            System.setProperty("" + next, "" + ((Hashtable<K, Object>)properties).get(next));
        }
    }
    
    static /* synthetic */ Class class$(final String s) {
        try {
            return Class.forName(s);
        }
        catch (ClassNotFoundException ex) {
            throw new NoClassDefFoundError().initCause(ex);
        }
    }
}
