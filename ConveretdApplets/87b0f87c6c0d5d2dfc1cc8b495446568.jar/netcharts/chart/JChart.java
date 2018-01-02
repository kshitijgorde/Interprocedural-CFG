// 
// Decompiled by Procyon v0.5.30
// 

package netcharts.chart;

import java.util.Enumeration;
import netcharts.util.NFParamException;
import java.util.Date;
import java.awt.Graphics;
import java.awt.Image;
import netcharts.util.NFVersion;
import netcharts.util.NFParam;
import netcharts.util.NFFile;
import java.applet.AppletStub;
import netcharts.util.NFGlobal;
import java.awt.Component;
import netcharts.util.NFEventAdapter;
import java.awt.LayoutManager;
import java.awt.BorderLayout;
import netcharts.util.NFContext;
import java.util.Vector;
import netcharts.util.NFCdf;
import java.awt.Dimension;
import java.applet.Applet;
import java.net.URL;
import netcharts.graphics.JGraph;
import javax.swing.JApplet;

public class JChart extends JApplet
{
    public JGraph graph;
    public URL url;
    protected Applet app;
    protected Dimension d;
    protected NFCdf cdf;
    protected Vector errs;
    protected boolean isModified;
    protected boolean loadAppletParamsOnInit;
    protected NFContext context;
    
    public JChart() {
        this.graph = null;
        this.url = null;
        this.d = new Dimension(400, 250);
        this.cdf = new NFCdf();
        this.errs = new Vector();
        this.isModified = false;
        this.loadAppletParamsOnInit = true;
        this.context = null;
        this.getRootPane().putClientProperty("defeatSystemEventQueueCheck", Boolean.TRUE);
        (this.app = this).resize(this.d);
        this.getContentPane().setLayout(new BorderLayout());
        final NFEventAdapter nfEventAdapter = new NFEventAdapter(this);
    }
    
    public JChart(final Applet app) {
        this();
        this.app = app;
    }
    
    public Applet getApplet() {
        return this.app;
    }
    
    public void setApplet(final Applet app) {
        this.app = app;
    }
    
    public NFContext getContext() {
        return this.context;
    }
    
    public void setContext(final NFContext nfContext) {
        this.context = nfContext;
        if (this.graph != null) {
            this.graph.setContext(nfContext);
        }
    }
    
    public static JChart getChart(final NFCdf nfCdf) {
        if (nfCdf != null && nfCdf.url != null) {
            return getChart(nfCdf, new NFContext(nfCdf.url, NFContext.getDefault().getCodeBase()));
        }
        return getChart(nfCdf, (NFContext)null);
    }
    
    public static JChart getChart(final NFCdf nfCdf, final Applet applet) {
        if (applet == null) {
            return getChart(nfCdf, (NFContext)null);
        }
        return getChart(nfCdf, new NFContext(applet));
    }
    
    public static JChart getChart(final NFCdf nfCdf, final NFContext nfContext) {
        return getChart(nfCdf, nfContext, true);
    }
    
    public static JChart getChart(final NFCdf cdf, final NFContext nfContext, final boolean loadAppletParamsOnInit) {
        if (cdf.name.equals("BREAK") || cdf.type == -2) {
            final JChart chart = new JChart();
            chart.cdf = cdf;
            return chart;
        }
        try {
            final JChart applet = (JChart)NFGlobal.getJChart(cdf.type);
            if (nfContext != null) {
                try {
                    applet.setContext(nfContext);
                    if (nfContext.getApplet() == null) {
                        nfContext.setApplet(applet);
                    }
                    applet.setApplet(nfContext.getApplet());
                    applet.setStub(nfContext);
                }
                catch (Exception ex2) {}
            }
            applet.url = cdf.url;
            applet.setLoadAppletParamsOnInit(loadAppletParamsOnInit);
            applet.init();
            applet.start();
            applet.getContext().mergeAuthInfo(NFContext.getDefault());
            applet.loadParams(cdf);
            return applet;
        }
        catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }
    
    public boolean getLoadAppletParamsOnInit() {
        return this.loadAppletParamsOnInit;
    }
    
    public void setLoadAppletParamsOnInit(final boolean loadAppletParamsOnInit) {
        this.loadAppletParamsOnInit = loadAppletParamsOnInit;
    }
    
    public void init() {
        if (this.getContext() == null) {
            this.setContext(new NFContext(this.getApplet()));
        }
        if (this.graph == null) {
            return;
        }
        this.graph.setContext(this.context);
        try {
            this.app.getCodeBase();
            NFFile.setFileBase(this.app);
        }
        catch (Exception ex2) {}
        try {
            if (this.loadAppletParamsOnInit) {
                this.graph.loadParams(this.app);
            }
            this.getContentPane().add("Center", this.graph);
        }
        catch (Exception ex) {
            NFParam.printError(ex);
        }
    }
    
    public void loadParams(final URL url) {
        if (this.graph == null) {
            return;
        }
        try {
            this.graph.loadParams(url);
        }
        catch (Exception ex) {
            NFParam.printError(ex);
        }
    }
    
    public void loadParams(final String s, final int n, final String s2) {
        if (this.graph == null) {
            return;
        }
        try {
            this.graph.loadParams(s, n, s2);
        }
        catch (Exception ex) {
            NFParam.printError(ex);
        }
    }
    
    public void loadParams(final String s) {
        if (this.graph == null) {
            return;
        }
        try {
            this.graph.loadParams(s);
        }
        catch (Exception ex) {
            NFParam.printError(ex);
        }
    }
    
    public void loadParams(final StringBuffer sb) {
        if (this.graph == null) {
            return;
        }
        try {
            this.graph.loadParams(sb);
        }
        catch (Exception ex) {
            NFParam.printError(ex);
        }
    }
    
    public void loadParams(final NFCdf cdf) {
        if (this.graph == null) {
            return;
        }
        this.cdf = cdf;
        try {
            this.errs.removeAllElements();
            this.graph.reload(cdf, this.errs);
            this.graph.loadParams("Update");
        }
        catch (Exception ex) {
            NFParam.printError(ex);
        }
    }
    
    public void loadParamsJS(final String s) {
        if (this.graph == null) {
            return;
        }
        try {
            this.graph.loadParams(s);
        }
        catch (Exception ex) {
            NFParam.printError(ex);
        }
    }
    
    public void setScale(final double scale) {
        if (this.graph != null) {
            this.graph.setScale(scale);
        }
    }
    
    public String getCDL() {
        try {
            return this.graph.getLoadParams().getCDL();
        }
        catch (Exception ex) {
            return "";
        }
    }
    
    public NFCdf getCdf() {
        return this.cdf;
    }
    
    public void setCdf(final NFCdf cdf) {
        this.cdf = cdf;
    }
    
    public boolean getModified() {
        return this.isModified;
    }
    
    public void setModified(final boolean isModified) {
        this.isModified = isModified;
    }
    
    public String getName() {
        if (this.cdf == null) {
            return null;
        }
        return this.cdf.name;
    }
    
    public Vector getErrors() {
        return this.errs;
    }
    
    public void display() {
        this.loadParams("Update");
    }
    
    public String getAppletInfo() {
        return NFVersion.info;
    }
    
    public void stop() {
        if (this.graph == null) {
            return;
        }
        this.graph.stop(this.app);
    }
    
    public void start() {
        if (this.graph == null) {
            return;
        }
        this.graph.start(this.app);
    }
    
    public Image drawImage(final int n, final int n2) {
        if (this.graph == null) {
            return null;
        }
        return this.graph.drawImage(n, n2);
    }
    
    public void printAll(final Graphics graphics) {
        if (this.graph == null) {
            return;
        }
        this.graph.print(graphics);
    }
    
    public void resize(final int width, final int height) {
        super.resize(this.d.width = width, this.d.height = height);
    }
    
    public void reshape(final int n, final int n2, final int width, final int height) {
        super.reshape(n, n2, this.d.width = width, this.d.height = height);
    }
    
    public Dimension preferredSize() {
        return this.d;
    }
    
    public boolean reloadNeeded(final Date date) {
        return this.graph.reloadNeeded(date);
    }
    
    public void reloadChart() {
        this.reloadChart(true);
    }
    
    public void reloadChart(final boolean b) {
        if (!b && !this.reloadNeeded(null)) {
            return;
        }
        this.loadParams(this.getCdf());
    }
    
    public void set(final Object o) throws NFParamException {
        if (this.graph == null) {
            return;
        }
        try {
            this.graph.getLoadParams().loadParams(o);
        }
        catch (Exception ex) {
            throw new NFParamException(ex.getMessage());
        }
    }
    
    public void set(final String s, final Object o) throws NFParamException {
        if (this.graph != null) {
            this.graph.getParse().setAttr(s, o);
        }
    }
    
    public void set(final String s, final int n, final Object o) throws NFParamException {
        if (this.graph != null) {
            this.graph.getParse().setAttr(s, n, o);
        }
    }
    
    public void set(final String s, final int n, final int n2, final Object o) throws NFParamException {
        if (this.graph != null) {
            this.graph.getParse().setAttr(s, n, n2, o);
        }
    }
    
    public void set(final String s, final Object o, final Object o2) throws NFParamException {
        if (this.graph != null) {
            this.graph.getParse().setAttr(s, o, o2);
        }
    }
    
    public void set(final String s, final Object o, final Object o2, final Object o3) throws NFParamException {
        if (this.graph != null) {
            this.graph.getParse().setAttr(s, o, o2, o3);
        }
    }
    
    public int getIndex(final String s, final Object o) throws NFParamException {
        if (this.graph == null) {
            return 0;
        }
        return this.graph.getParse().getItemIndex(s, o, false);
    }
    
    public Object get(final String s) throws NFParamException {
        if (this.graph == null) {
            return null;
        }
        return this.graph.getParse().getAttr(s);
    }
    
    public String getParam(final String s) throws NFParamException {
        if (this.graph == null) {
            return null;
        }
        return this.graph.getParam().toString(s);
    }
    
    public String getParam(final String s, final Object o) throws NFParamException {
        if (this.graph == null) {
            return null;
        }
        return this.graph.getParam().toString(s, o);
    }
    
    public Vector getMetaData(final String s) {
        if (this.graph == null) {
            return null;
        }
        return this.graph.getMetaData(s);
    }
    
    public Enumeration getMetaDataKeys() {
        if (this.graph == null) {
            return null;
        }
        return this.graph.getMetaDataKeys();
    }
    
    public void clean() {
        if (this.graph != null) {
            this.graph.clean();
        }
    }
    
    public String getTextualDescription() {
        if (this.graph == null) {
            return null;
        }
        return this.graph.getTextualDescription();
    }
}
