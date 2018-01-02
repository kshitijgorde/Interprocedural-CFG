// 
// Decompiled by Procyon v0.5.30
// 

package jmaster.jumploader.app;

import java.util.Hashtable;
import jmaster.jumploader.model.api.common.IAttribute;
import jmaster.jumploader.model.api.common.IAttributeSet;
import java.util.HashMap;
import java.util.Enumeration;
import jmaster.util.property.D;
import java.awt.Component;
import java.awt.Color;
import java.awt.LayoutManager;
import java.awt.BorderLayout;
import jmaster.jumploader.model.impl.image.WatermarkConfig;
import java.util.ArrayList;
import netscape.javascript.JSObject;
import jmaster.util.log.console.ConsoleLogFactory;
import java.net.MalformedURLException;
import java.net.URL;
import java.awt.event.MouseEvent;
import jmaster.jumploader.model.api.common.IListSelection;
import jmaster.jumploader.model.api.upload.IUploadFile;
import jmaster.jumploader.model.api.upload.IUploader;
import java.applet.Applet;
import java.util.StringTokenizer;
import jmaster.util.property.C;
import jmaster.util.swing.SwingUtil;
import jmaster.jumploader.view.api.main.IMainView;
import jmaster.jumploader.model.api.config.ViewConfig;
import jmaster.jumploader.model.api.config.UploaderConfig;
import java.util.regex.Pattern;
import java.io.File;
import java.util.List;
import javax.swing.JFrame;
import javax.swing.JLabel;
import jmaster.jumploader.jsface.api.IJSUploader;
import jmaster.jumploader.model.api.config.AppletConfig;
import jmaster.util.swing.GUIHelper;
import jmaster.util.log.A;
import java.awt.event.MouseListener;
import jmaster.jumploader.model.api.common.IListSelectionListener;
import jmaster.jumploader.model.api.upload.B;
import javax.swing.JApplet;

public class JumpLoaderApplet extends JApplet implements IJumpLoaderApplet, B, IListSelectionListener, MouseListener, Runnable
{
    private static final long \u011a = -1228348390281469532L;
    public static final String PREFIX = "applet";
    private static final String \u011c = "gc_systemProperties";
    private static final String \u0116 = "gc_loggingLevel";
    private static final String \u0117 = "uc_uploadUrl";
    private static final String \u0118 = "uc_resumeCheckUrl";
    private static final String \u0121 = "uc_metadataDescriptorUrl";
    private static final String \u0124 = "appletMessagePumper";
    protected boolean \u0120;
    protected boolean \u0122;
    private A \u011b;
    private GUIHelper \u0125;
    private JumpLoaderMain \u0119;
    private AppletConfig \u0123;
    private IJSUploader \u011f;
    protected JLabel \u011d;
    protected JFrame \u011e;
    private List \u0126;
    static /* synthetic */ Class class$jmaster$util$http$SocketHttpUploader;
    
    public JumpLoaderApplet() {
        this.\u011b = null;
        this.\u0125 = null;
        this.\u0119 = null;
    }
    
    public void init() {
        this.\u0122 = false;
        super.init();
        try {
            System.out.println("f=" + File.createTempFile("jumploader", "jumploader"));
        }
        catch (Exception ex2) {
            return;
        }
        this.I();
        this.\u011b = jmaster.util.log.B.getInstance().getLog(this);
        if (this.\u011b.B()) {
            this.\u011b.D("Applet.init() of " + this);
        }
        final String host = this.getDocumentBase().getHost();
        if (this.\u011b.B()) {
            this.\u011b.D("*****************************************************");
            this.\u011b.D("Initializing " + this.getAppletInfo());
            this.\u011b.D("Applet code base=" + this.getCodeBase().toExternalForm());
            this.\u011b.D("Applet document base=" + this.getDocumentBase().toExternalForm());
            this.\u011b.D("Applet document host=" + host);
            this.\u011b.D("Current thread=" + Thread.currentThread().getName());
        }
        boolean matches = true;
        for (int n = 0; !matches && n < JumpLoaderVersion.ALLOWED_HOSTS.length; ++n) {
            final String s = JumpLoaderVersion.ALLOWED_HOSTS[n];
            try {
                matches = Pattern.compile(s).matcher(host).matches();
            }
            catch (Exception ex) {
                this.\u011b.E("Failed to compile pattern for regex: " + s, ex);
            }
        }
        if (!matches) {
            return;
        }
        this.J();
    }
    
    public void destroy() {
        if (this.\u011b.B()) {
            this.\u011b.D("Applet.destroy() of " + this);
        }
        this.\u0122 = true;
        this.K();
        super.destroy();
    }
    
    public void start() {
        try {
            System.out.println("f=" + File.createTempFile("jumploader", "jumploader"));
        }
        catch (Exception ex) {
            return;
        }
        if (this.\u011b.B()) {
            this.\u011b.D("Applet.start() of " + this);
        }
        this.\u0120 = false;
        super.start();
    }
    
    public void stop() {
        this.\u0120 = true;
        if (this.\u011b.B()) {
            this.\u011b.D("Applet.stop() of " + this);
        }
        super.stop();
    }
    
    public String getAppletInfo() {
        return JumpLoaderVersion.getApplicationName();
    }
    
    public IJSUploader getUploader() {
        return this.\u011f;
    }
    
    public UploaderConfig getUploaderConfig() {
        return this.\u0119.getModel().B();
    }
    
    public ViewConfig getViewConfig() {
        return this.\u0119.getModel().H();
    }
    
    public AppletConfig getAppletConfig() {
        return this.\u0119.getModel().F();
    }
    
    public IMainView getMainView() {
        return this.\u0119.getView();
    }
    
    public void showFrame(final boolean visible) {
        this.\u011e.setVisible(visible);
    }
    
    public void setUiDefault(final String s, final String s2) {
        SwingUtil.setUiDefaultString(s, s2);
        this.repaint();
    }
    
    public Object getUiDefault(final String s) {
        return SwingUtil.getUiDefault(s);
    }
    
    public String injectProperty(final Object o, final String s, final String s2) {
        String string = null;
        try {
            C.A().A((o == null) ? this : o, s, s2);
        }
        catch (Exception ex) {
            final String string2 = "Failed to inject property, target=" + o + ", property=" + s + ", value=" + s2;
            this.\u011b.E(string2, ex);
            string = string2 + ", cause: " + ex;
        }
        return string;
    }
    
    public String injectProperty(final String s, final String s2) {
        return this.injectProperty(this, s, s2);
    }
    
    public Object getProperty(final String s) {
        Object o = null;
        try {
            StringTokenizer stringTokenizer;
            Object b;
            for (stringTokenizer = new StringTokenizer(s, "."), b = this; b != null && stringTokenizer.hasMoreTokens(); b = jmaster.util.C.B.B(b, stringTokenizer.nextToken())) {}
            o = b;
        }
        catch (Exception ex) {
            this.\u011b.E("Failed to retrieve property named '" + s + "'", ex);
        }
        return o;
    }
    
    public void injectProperties(final Object o, final String s) {
        new AppletPropertyInjector(this).injectProperties(o, s);
    }
    
    public boolean isDestroying() {
        return this.\u0122;
    }
    
    public boolean isStopping() {
        return this.\u0120;
    }
    
    public void uploaderFileAdditionEnabledChanged(final IUploader uploader) {
    }
    
    public void uploaderFileRemovalEnabledChanged(final IUploader uploader) {
    }
    
    public void uploaderFileAdded(final IUploader uploader, final IUploadFile uploadFile) {
        if (this.\u0123.isFireUploaderFileAdded()) {
            this.A("uploaderFileAdded", new Object[] { this.\u011f, uploadFile });
        }
    }
    
    public void uploaderFileRemoved(final IUploader uploader, final IUploadFile uploadFile) {
        if (this.\u0123.isFireUploaderFileRemoved()) {
            this.A("uploaderFileRemoved", new Object[] { this.\u011f, uploadFile });
        }
        if (uploadFile.isTempFile()) {
            final File file = uploadFile.getFile();
            if (this.\u011b.B()) {
                this.\u011b.D("Deleteing temp file: " + file.getAbsolutePath());
            }
            try {
                file.delete();
            }
            catch (Exception ex) {
                this.\u011b.E("Failed to delete file: " + file.getAbsolutePath(), ex);
            }
        }
    }
    
    public void uploaderFileMoved(final IUploader uploader, final IUploadFile uploadFile, final int n) {
        if (this.\u0123.isFireUploaderFileMoved()) {
            this.A("uploaderFileMoved", new Object[] { this.\u011f, uploadFile, "" + n });
        }
    }
    
    public void uploaderFileStatusChanged(final IUploader uploader, final IUploadFile uploadFile) {
        if (this.\u0123.isFireUploaderFileStatusChanged()) {
            this.A("uploaderFileStatusChanged", new Object[] { this.\u011f, uploadFile });
        }
    }
    
    public void uploaderFilesReset(final IUploader uploader) {
        if (this.\u0123.isFireUploaderFilesReset()) {
            this.A("uploaderFilesReset", new Object[] { this.\u011f });
        }
    }
    
    public void uploaderStatusChanged(final IUploader uploader) {
        if (this.\u0119.getModel().B().getUploadFormName() != null && uploader.isUploading()) {
            this.E(this.\u0119.getModel().B().getUploadFormName());
        }
        if (this.\u0123.isFireUploaderStatusChanged()) {
            this.A("uploaderStatusChanged", new Object[] { this.\u011f });
        }
    }
    
    public void uploaderFileUpdated(final IUploader uploader, final IUploadFile uploadFile) {
    }
    
    public void uploaderUploadEnabledChanged(final IUploader uploader) {
    }
    
    public void uploaderFileAddFailed(final IUploader uploader, final jmaster.jumploader.model.api.A.C c) {
    }
    
    public void listSelectionChanged(final IListSelection listSelection) {
        if (this.\u0123.isFireUploaderSelectionChanged()) {
            this.A("uploaderSelectionChanged", new Object[] { this.\u011f });
        }
    }
    
    public void mouseClicked(final MouseEvent mouseEvent) {
        if (mouseEvent.getSource().equals(this.\u011d)) {
            this.\u011e.setVisible(true);
        }
    }
    
    public void mouseEntered(final MouseEvent mouseEvent) {
    }
    
    public void mouseExited(final MouseEvent mouseEvent) {
    }
    
    public void mousePressed(final MouseEvent mouseEvent) {
    }
    
    public void mouseReleased(final MouseEvent mouseEvent) {
    }
    
    public void run() {
        while (true) {
            if (this.\u011b.B()) {
                this.\u011b.D("" + Thread.currentThread().getName() + " started");
                try {
                    while (this.\u0126 != null) {
                        final _A a = (this.\u0126.size() > 0) ? this.\u0126.remove(0) : null;
                        if (a != null) {
                            if (this.\u011b.B()) {
                                this.\u011b.D("Invoking JS method " + a.A + "(" + a.B + ")");
                            }
                            try {
                                this.getWindow().call(a.A, a.B);
                            }
                            catch (Exception ex) {
                                this.\u011b.E("JS method call failed: " + a.A, ex);
                                synchronized (a) {
                                    a.C = true;
                                    a.notify();
                                }
                            }
                            finally {
                                synchronized (a) {
                                    a.C = true;
                                    a.notify();
                                }
                            }
                        }
                        else {
                            synchronized (this.\u0126) {
                                this.\u0126.wait(1000L);
                            }
                        }
                    }
                }
                catch (InterruptedException ex2) {}
                catch (Throwable t) {
                    this.\u011b.E("" + Thread.currentThread().getName() + " terminated", t);
                }
                finally {
                    if (this.\u011b.B()) {
                        this.\u011b.D("" + Thread.currentThread().getName() + " terminated");
                    }
                }
                return;
            }
            continue;
        }
    }
    
    private boolean B(final String s) {
        return this.getParameter(s) != null;
    }
    
    private String D(final String s) {
        return this.getParameter(s);
    }
    
    private String C(final String s) {
        return this.A(this.D(s));
    }
    
    private String A(final String s) {
        URL url = null;
        try {
            url = new URL(s);
        }
        catch (MalformedURLException ex) {
            try {
                url = new URL(this.getCodeBase(), s);
            }
            catch (MalformedURLException ex2) {
                this.\u011b.E("Failed to construct url from parameter: " + s, ex);
            }
        }
        return (url == null) ? null : url.toExternalForm();
    }
    
    private void I() {
        if (this.B("gc_loggingLevel")) {
            ((ConsoleLogFactory)jmaster.util.log.B.getInstance()).setDefaultPriority(this.D("gc_loggingLevel"));
        }
    }
    
    public JSObject getWindow() {
        return JSObject.getWindow((Applet)this);
    }
    
    private void A(final String a, final Object[] b) {
        final _A a2 = new _A();
        a2.A = a;
        a2.B = b;
        synchronized (this.\u0126) {
            this.\u0126.add(a2);
            this.\u0126.notify();
        }
        synchronized (a2) {
            while (!a2.C) {
                try {
                    a2.wait();
                }
                catch (InterruptedException ex) {}
            }
        }
    }
    
    protected void J() {
        try {
            this.\u0125 = GUIHelper.getInstance();
            (this.\u0119 = new JumpLoaderMain()).setApplet(this);
            this.\u0126 = new ArrayList();
            this.\u0119.createModel();
            this.\u0123 = this.\u0119.getModel().F();
            final jmaster.util.B.B b = new jmaster.util.B.B();
            b.A(this);
            b.A("appletMessagePumper");
            b.A(true);
            b.B(true);
            this.\u0119.getModel().C().A(b);
            if (this.B("gc_systemProperties")) {
                final D a = C.A(this.D("gc_systemProperties"), ";");
                final Enumeration<Object> keys = ((Hashtable<Object, V>)a).keys();
                while (keys.hasMoreElements()) {
                    final Object nextElement = keys.nextElement();
                    this.\u0119.getModel().I().getProperties().put(nextElement, ((Hashtable<K, Object>)a).get(nextElement));
                }
            }
            this.\u0119.injectSystemProperties();
            this.\u011f = new jmaster.jumploader.jsface.A.A(this.\u0119.getModel(), this.\u0119.getModel().D());
            this.I();
            final AppletPropertyInjector appletPropertyInjector = new AppletPropertyInjector(this);
            final UploaderConfig b2 = this.\u0119.getModel().B();
            appletPropertyInjector.injectProperties(b2, "uc_");
            final ViewConfig h = this.\u0119.getModel().H();
            appletPropertyInjector.injectProperties(h, "vc_");
            final AppletConfig f = this.\u0119.getModel().F();
            appletPropertyInjector.injectProperties(f, "ac_");
            appletPropertyInjector.injectProperties(this.\u0119.getModel().J(), "ic_");
            if (b2.getScaledInstanceWatermarkNames() != null) {
                final StringTokenizer stringTokenizer = new StringTokenizer(b2.getScaledInstanceWatermarkNames(), ",");
                final int countTokens = stringTokenizer.countTokens();
                final ArrayList<WatermarkConfig> scaledInstanceWatermarks = new ArrayList<WatermarkConfig>();
                for (int i = 0; i < countTokens; ++i) {
                    final String nextToken = stringTokenizer.nextToken();
                    if (!"null".equals(nextToken)) {
                        final String d = this.D(nextToken);
                        if (this.\u011b.B()) {
                            this.\u011b.D("Loading watermark config from parameter '" + nextToken + "': " + d);
                        }
                        final WatermarkConfig watermarkConfig = new WatermarkConfig();
                        C.A().A(watermarkConfig, C.A(d, ";"), null);
                        watermarkConfig.setImageUrl(this.A(watermarkConfig.getImageUrl()));
                        scaledInstanceWatermarks.add(watermarkConfig);
                    }
                    else {
                        scaledInstanceWatermarks.add(null);
                    }
                }
                b2.setScaledInstanceWatermarks(scaledInstanceWatermarks);
            }
            if (this.B("uc_uploadUrl")) {
                b2.setUploadUrl(this.C("uc_uploadUrl"));
            }
            if (this.B("uc_resumeCheckUrl")) {
                b2.setResumeCheckUrl(this.C("uc_resumeCheckUrl"));
            }
            if (this.B("uc_metadataDescriptorUrl")) {
                b2.setMetadataDescriptorUrl(this.C("uc_metadataDescriptorUrl"));
            }
            if (h.getUploadViewStartUploadButtonImageUrl() != null) {
                h.setUploadViewStartUploadButtonImageUrl(this.A(h.getUploadViewStartUploadButtonImageUrl()));
            }
            if (h.getUploadViewStopUploadButtonImageUrl() != null) {
                h.setUploadViewStopUploadButtonImageUrl(this.A(h.getUploadViewStopUploadButtonImageUrl()));
            }
            if (jmaster.util.B.A.C(b2.getUserAgent())) {
                try {
                    b2.setUserAgent("" + this.getWindow().eval("navigator.userAgent"));
                }
                catch (Throwable t) {
                    this.\u011b.E(t, t);
                }
            }
            if (jmaster.util.B.A.C(b2.getCookie())) {
                try {
                    b2.setCookie("" + this.getWindow().eval("document.cookie"));
                }
                catch (Throwable t2) {
                    this.\u011b.E(t2, t2);
                }
            }
            if (b2.getUserAgent() != null && b2.getUserAgent().toLowerCase().indexOf("opera") != -1) {
                b2.setHttpUploaderClassName(((JumpLoaderApplet.class$jmaster$util$http$SocketHttpUploader == null) ? (JumpLoaderApplet.class$jmaster$util$http$SocketHttpUploader = class$("jmaster.util.http.SocketHttpUploader")) : JumpLoaderApplet.class$jmaster$util$http$SocketHttpUploader).getName());
            }
            if (this.\u0119.getModel().K() != null) {
                this.\u0119.getModel().M();
            }
            final IUploader d2 = this.\u0119.getModel().D();
            d2.addListener(this);
            d2.getSelection().addListener(this);
            this.\u0119.initModel();
            jmaster.util.C.B.C(this.\u0119, "createView");
            try {
                while (this.\u0119.getView() == null) {
                    Thread.sleep(1000L);
                }
            }
            catch (InterruptedException ex3) {}
            if ("framed".equals(this.\u0123.getMode())) {
                this.\u011d = new JLabel();
                this.\u011e = new JFrame();
                this.getContentPane().setLayout(new BorderLayout());
                this.getContentPane().setBackground(Color.WHITE);
                this.\u0125.injectProperties(this.\u011d, "applet", "cmdShowFrame");
                this.getContentPane().add(this.\u011d, "Center");
                JFrame.setDefaultLookAndFeelDecorated(true);
                this.\u011e.setTitle(JumpLoaderVersion.getApplicationName());
                this.\u0125.injectProperties(this.\u011e, "applet", "frame");
                this.\u011e.getContentPane().add((Component)this.\u0119.getView());
                this.\u011e.pack();
                this.\u011e.requestFocus();
                this.\u011d.addMouseListener(this);
            }
            else {
                this.getContentPane().add((Component)this.\u0119.getView());
            }
            this.\u0119.createController();
            this.\u0119.startController();
            if (f.getProperties() != null) {
                final C a2 = C.A();
                final StringTokenizer stringTokenizer2 = new StringTokenizer(f.getProperties(), ";");
                while (stringTokenizer2.hasMoreTokens()) {
                    final String nextToken2 = stringTokenizer2.nextToken();
                    try {
                        final int index = nextToken2.indexOf(61);
                        if (index == -1) {
                            continue;
                        }
                        a2.A(this, nextToken2.substring(0, index).trim(), (nextToken2.length() > index + 1) ? nextToken2.substring(index + 1).trim() : null);
                    }
                    catch (Exception ex) {
                        this.\u011b.E("Failed to process property pair: " + nextToken2, ex);
                    }
                }
            }
            if (this.\u0123.isFireAppletInitialized()) {
                this.A("appletInitialized", new Object[] { this });
            }
        }
        catch (Exception ex2) {
            this.\u011b.E("Failed to initialize applet " + this, ex2);
            if (ex2 instanceof RuntimeException) {
                throw (RuntimeException)ex2;
            }
            throw new RuntimeException(ex2);
        }
    }
    
    protected void K() {
        if (this.\u011b.B()) {
            this.\u011b.D("Destroying " + this);
        }
        final IUploader d = this.\u0119.getModel().D();
        try {
            while (d.getFileCount() > 0) {
                d.removeFile(d.getFile(0));
            }
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
        this.\u0126 = null;
        try {
            this.\u0119.getModel().C().C("appletMessagePumper");
        }
        catch (Exception ex3) {}
        try {
            this.\u011f.destroy();
            this.\u0119.destroy(false);
            this.\u0125 = null;
            this.\u0119 = null;
            this.\u0126 = null;
            this.getRootPane().removeAll();
            super.destroy();
        }
        catch (Exception ex2) {
            this.\u011b.E(ex2, ex2);
        }
    }
    
    public String toString() {
        return "jl_" + ((this.\u0119 == null) ? "" : ("" + this.\u0119.hashCode()));
    }
    
    private void E(final String s) {
        final JSObject jsObject = (JSObject)this.getWindow().eval("document.forms['" + s + "']");
        if (jsObject == null) {
            this.\u011b.E("Form not found: " + s);
        }
        else {
            final HashMap<String, String> hashMap = (HashMap<String, String>)new HashMap<Object, String>();
            final Integer n = (Integer)jsObject.eval("elements.length");
            for (int i = 0; i < n; ++i) {
                final JSObject jsObject2 = (JSObject)jsObject.eval("elements[ " + i + " ]");
                final String string = "" + jsObject2.getMember("type");
                new StringBuffer().append("").append(jsObject2.getMember("tagName")).toString();
                final String string2 = "" + jsObject2.getMember("name");
                String stringValue = null;
                if ("radio".equalsIgnoreCase(string)) {
                    stringValue = hashMap.get(string2);
                    if (!hashMap.containsKey(string2)) {
                        stringValue = null;
                    }
                    if (Boolean.TRUE.equals(jsObject2.getMember("checked"))) {
                        stringValue = "" + jsObject2.getMember("value");
                    }
                    hashMap.put(string2, stringValue);
                }
                else if ("checkbox".equalsIgnoreCase(string)) {
                    if (Boolean.TRUE.equals(jsObject2.getMember("checked"))) {
                        stringValue = "" + jsObject2.getMember("value");
                    }
                }
                else {
                    stringValue = ((jsObject2.getMember("value") == null) ? null : ("" + jsObject2.getMember("value")));
                }
                if (string2 != null) {
                    final IAttributeSet attributeSet = this.\u0119.getModel().D().getAttributeSet();
                    final IAttribute attributeByName = attributeSet.getAttributeByName(string2);
                    if (attributeByName == null && stringValue != null) {
                        attributeSet.createStringAttribute(string2, stringValue);
                    }
                    else if (attributeByName != null && stringValue == null) {
                        attributeSet.removeAttribute(attributeByName);
                    }
                    else if (stringValue != null) {
                        attributeByName.setStringValue(stringValue);
                    }
                }
            }
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
    
    protected class _A
    {
        String A;
        Object[] B;
        boolean C;
    }
}
