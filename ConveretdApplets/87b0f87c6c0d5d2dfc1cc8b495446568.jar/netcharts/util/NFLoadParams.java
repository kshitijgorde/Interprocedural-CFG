// 
// Decompiled by Procyon v0.5.30
// 

package netcharts.util;

import java.io.StringBufferInputStream;
import java.util.Properties;
import java.util.Observable;
import java.awt.Color;
import java.util.Date;
import java.io.InputStream;
import java.util.Enumeration;
import java.net.URL;
import java.util.Hashtable;
import java.applet.Applet;
import java.awt.Component;
import java.util.Vector;
import java.util.Observer;

public class NFLoadParams implements Runnable, NFDataBeanObserver, Observer
{
    private NFParam a;
    private NFParse b;
    private NFToken c;
    private Vector d;
    private boolean e;
    private String f;
    private Component g;
    private Applet h;
    private Hashtable i;
    private boolean j;
    private boolean k;
    private NFContext l;
    private int m;
    private int n;
    private Vector o;
    private Vector p;
    private Vector q;
    private Vector r;
    private Vector s;
    
    public NFLoadParams(final Component component, final Applet applet) {
        this.a = null;
        this.b = null;
        this.c = null;
        this.d = new Vector();
        this.e = true;
        this.f = null;
        this.g = null;
        this.h = null;
        this.i = null;
        this.j = false;
        this.k = false;
        this.l = null;
        this.m = 0;
        this.n = 30;
        this.o = new Vector();
        this.p = new Vector();
        this.q = new Vector();
        this.r = new Vector();
        this.s = new Vector();
        this.g = component;
        this.h = applet;
        (this.a = new NFParam(applet, component)).addObserver(this);
        this.c = new NFToken();
        (this.b = new NFParse(this.a)).setApp(applet);
        this.b.setComponent(component);
        this.b.setDataBeanObserver(this);
    }
    
    public void setContext(final NFContext nfContext) {
        this.l = nfContext;
        if (this.b != null) {
            this.b.setContext(nfContext);
        }
    }
    
    public NFContext getContext() {
        return this.l;
    }
    
    public void setParam(final NFParam a) {
        if (this.a != null) {
            a.deleteObserver(this);
        }
        (this.a = a).addObserver(this);
    }
    
    public NFParam getParam() {
        return this.a;
    }
    
    public void setParse(final NFParse b) {
        this.b = b;
    }
    
    public NFParse getParse() {
        return this.b;
    }
    
    public NFToken getToken() {
        return this.c;
    }
    
    public void setApp(final Applet applet) {
        this.h = applet;
        if (this.b != null) {
            this.b.setApp(applet);
        }
    }
    
    public Applet getApp() {
        return this.h;
    }
    
    public void setFileBase(final String s) {
        this.f = s;
        if (this.b != null) {
            this.b.setFileBase(s);
        }
    }
    
    public String getFileBase() {
        return this.f;
    }
    
    private void a(final Exception ex) throws NFParamException {
        String s = ex.getMessage();
        if (s == null) {
            s = ex.toString();
        }
        this.a(s);
    }
    
    private void a(final String s) throws NFParamException {
        if (this.m == 0) {
            throw new NFParamException("ERROR: " + s);
        }
        throw new NFParamException(s);
    }
    
    private synchronized boolean a(final NFToken nfToken, final NFParamServ nfParamServ) throws Exception {
        StringBuffer sb = null;
        while (true) {
            if (nfParamServ != null && nfParamServ.closed) {
                this.c("Parameter server closed connection. loading it...");
                this.a(nfParamServ);
            }
            try {
                final NFKeyValue nextStatement = nfToken.nextStatement();
                if (nextStatement == null) {
                    break;
                }
                if (NFDebug.enabled(2L)) {
                    if (sb == null) {
                        sb = new StringBuffer();
                    }
                    else {
                        sb.setLength(0);
                    }
                    nfToken.printStatement(sb, nextStatement, "NFLoadParams: ");
                    NFDebug.print(sb.toString());
                }
                if (nfParamServ != null && nfParamServ.parse((String)nextStatement.key, (Vector)nextStatement.value)) {
                    continue;
                }
                if (((String)nextStatement.key).equals("Update")) {
                    this.a(nfParamServ);
                }
                this.b.parseStatement(nextStatement, this.o);
            }
            catch (Exception ex) {
                if (nfParamServ != null) {
                    this.c("ParamServ errored out. Notifying Observers of Failure.");
                    this.b("Parameter Server Error: " + ex.getMessage());
                    this.a(null, ex, nfParamServ);
                    continue;
                }
                this.a(ex);
            }
            if (nfParamServ != null) {
                Thread.yield();
            }
        }
        return true;
    }
    
    public synchronized boolean loadParams(final Applet app) throws Exception {
        if (app == null) {
            this.a("No applet has been assigned");
        }
        this.setApp(app);
        try {
            app.getAppletContext();
        }
        catch (Exception ex) {
            NFDebug.print(2L, "NFLoadParams: getAppletContext() failed");
            return false;
        }
        final Enumeration<String> elements = (Enumeration<String>)this.a.getPrimaryKeys(null).elements();
        while (elements.hasMoreElements()) {
            final String s = elements.nextElement();
            final String parameter = app.getParameter(s);
            if (parameter == null) {
                continue;
            }
            if (this.a.getType(s) == 2 && !NFToken.isQuoted(parameter)) {
                this.loadParams(s + " = {" + parameter + "}");
            }
            else {
                this.loadParams(s + " = " + parameter);
            }
        }
        final String parameter2 = app.getParameter("NFFontEncoding");
        if (parameter2 != null) {
            NFTokenInput.setFontEncoding(parameter2);
        }
        final String parameter3 = app.getParameter("NFParamURL");
        if (parameter3 != null) {
            this.loadParams(new URL(app.getDocumentBase(), parameter3));
        }
        final String parameter4 = app.getParameter("NFParamScript");
        if (parameter4 != null) {
            this.loadParams(parameter4);
        }
        String s2 = app.getParameter("NFParamServer");
        if (s2 != null) {
            final int index = s2.indexOf("/");
            String substring;
            if (index < 0) {
                substring = "";
            }
            else {
                substring = s2.substring(index + 1);
                s2 = s2.substring(0, index);
            }
            final int index2 = s2.indexOf(":");
            if (index2 < 0) {
                this.a("No port specified");
            }
            this.loadParams(s2.substring(0, index2), Integer.parseInt(s2.substring(index2 + 1)), substring, this.g);
        }
        this.c("CHECK for completeness in loadParams(Applet)");
        this.a();
        return true;
    }
    
    public synchronized boolean loadParams(final String input) throws Exception {
        this.c.setInput(input);
        return this.a(this.c, null);
    }
    
    public synchronized boolean loadParams(final StringBuffer input) throws Exception {
        this.c.setInput(input);
        return this.a(this.c, null);
    }
    
    public synchronized boolean loadParams(final URL url) throws Exception {
        try {
            InputStream input;
            if (url.getProtocol().equalsIgnoreCase("http") && NFContext.getUserAgentType() != 0) {
                input = new NFHttpClient(this.l).getContentAsInputStream(url);
            }
            else {
                input = NFNetworkAccess.getURLAsStream(url);
            }
            this.c.setInput(input);
            this.a(this.c, null);
            input.close();
        }
        catch (Exception ex) {
            this.a("NFParamURL: " + ex);
        }
        return true;
    }
    
    public synchronized void loadParams(final String s, final int n, final String s2, final Component component) throws Exception {
        this.loadParams(new NFParamServ(s.toString(), n, s2.toString()));
    }
    
    public synchronized boolean loadParams(final NFParamServ nfParamServ) throws Exception {
        nfParamServ.setComp(this.g);
        synchronized (this.d) {
            this.d.addElement(nfParamServ);
        }
        if (!this.e) {
            this.start();
        }
        return true;
    }
    
    public synchronized boolean loadParams(final NFDataBean nfDataBean) throws Exception {
        final String exprParam = nfDataBean.getExprParam();
        Object paramDef = null;
        if (exprParam != null) {
            paramDef = this.a.getParamDef(exprParam);
        }
        nfDataBean.setContext(this.l);
        if (this.m >= this.n) {
            throw new Exception("Max Nesting Depth (" + this.n + ") Exceeded, Possible Circular Reference");
        }
        try {
            ++this.m;
            final NFToken c = this.c;
            this.c = new NFToken();
            boolean b;
            if (NFPrivilegeBasedNetworkAccess.isPrivilegeBasedSecurityEnvironment() && NFUtil.getJDKVersion() >= 1.1) {
                try {
                    b = (boolean)NFPrivilegeBasedNetworkAccess.invokePrivilegedMethod(nfDataBean, nfDataBean.getClass().getMethod("loadData", Class.forName("netcharts.util.NFDataBeanObserver"), Class.forName("java.lang.Object")), new Object[] { this, paramDef });
                }
                catch (Exception ex) {
                    System.out.println("Exception during loadData call in LoadParams: " + ex);
                    ex.printStackTrace();
                    b = false;
                }
            }
            else {
                b = nfDataBean.loadData(this, paramDef);
            }
            this.c = c;
            return b;
        }
        catch (Exception ex2) {
            this.c("DB=" + nfDataBean + " errored out. Notifying Observers of Failure.");
            this.b("DataBean Error: " + nfDataBean.getClass().getName() + ": " + ex2.getMessage());
            throw ex2;
        }
        finally {
            --this.m;
        }
    }
    
    public synchronized boolean loadParams(final Object input) throws Exception {
        if (input instanceof String) {
            return this.loadParams((String)input);
        }
        if (input instanceof Applet) {
            return this.loadParams((Applet)input);
        }
        if (input instanceof StringBuffer) {
            return this.loadParams((StringBuffer)input);
        }
        if (input instanceof URL) {
            return this.loadParams((URL)input);
        }
        if (input instanceof NFDataBean) {
            return this.loadParams((NFDataBean)input);
        }
        if (input instanceof NFParamServ) {
            return this.loadParams((NFParamServ)input);
        }
        this.c.setInput(input);
        return this.a(this.c, null);
    }
    
    public synchronized void loadParams(final String s, final Vector vector) throws Exception {
        this.b.parseTokens(s, vector, this.o);
    }
    
    public void start() {
        synchronized (this.d) {
            final Enumeration<NFParamServ> elements = this.d.elements();
            while (elements.hasMoreElements()) {
                final NFParamServ nfParamServ = elements.nextElement();
                if (nfParamServ.thread != null) {
                    continue;
                }
                nfParamServ.debug("Opening Connection");
                (nfParamServ.thread = new Thread(this)).start();
            }
        }
        synchronized (this.q) {
            for (int i = 0; i < this.q.size(); ++i) {
                ((NFDataBean)this.q.elementAt(i)).start();
            }
        }
        this.e = false;
    }
    
    public void stop() {
        synchronized (this.d) {
            final Enumeration<NFParamServ> elements = this.d.elements();
            while (elements.hasMoreElements()) {
                final NFParamServ nfParamServ = elements.nextElement();
                if (nfParamServ.thread == null) {
                    continue;
                }
                nfParamServ.debug("Closing Connection");
                nfParamServ.stop();
            }
        }
        synchronized (this.q) {
            for (int i = 0; i < this.q.size(); ++i) {
                ((NFDataBean)this.q.elementAt(i)).stop();
            }
        }
        this.e = true;
    }
    
    public void run() {
        if (NFPrivilegeBasedNetworkAccess.isPrivilegeBasedSecurityEnvironment()) {
            try {
                NFPrivilegeBasedNetworkAccess.invokePrivilegedMethod(this, this.getClass().getMethod("myRun", (Class<?>[])new Class[0]), new Object[0]);
            }
            catch (Exception ex) {
                System.out.println("Exception during myRun call in NFLoadParams: " + ex);
                ex.printStackTrace();
            }
        }
        else {
            this.myRun();
        }
    }
    
    public void myRun() {
        final Thread currentThread = Thread.currentThread();
        NFParamServ nfParamServ = null;
        String message = null;
        try {
            NFDebug.print(16384L, "NFLoadParams: Thread started = " + currentThread);
            synchronized (this.d) {
                final Enumeration<NFParamServ> elements = (Enumeration<NFParamServ>)this.d.elements();
                while (elements.hasMoreElements()) {
                    nfParamServ = elements.nextElement();
                    if (nfParamServ.thread == currentThread) {
                        break;
                    }
                }
                if (nfParamServ == null || nfParamServ.thread != currentThread) {
                    return;
                }
            }
            try {
                nfParamServ.open();
            }
            catch (Exception ex) {
                this.c("NFParamServ errored out. Notifying Observers of Failure.");
                this.b("Parameter Server Error: " + ex.getMessage());
                message = ex.getMessage();
                throw ex;
            }
            message = "Unable to create tokenizer for server";
            final NFToken nfToken = new NFToken();
            nfToken.setInput(nfParamServ.is);
            message = null;
            this.a(nfToken, nfParamServ);
            this.a(nfParamServ);
            this.a.update();
        }
        catch (Exception ex2) {
            this.c("NFParamServ errored out. Notifying Observers of Failure.");
            this.b("Parameter Server Error: " + ex2.getMessage());
            this.a(message, ex2, nfParamServ);
        }
        if (nfParamServ != null) {
            synchronized (this.d) {
                this.d.removeElement(nfParamServ);
            }
            try {
                nfParamServ.close();
            }
            catch (Exception ex3) {}
        }
        NFDebug.print(16384L, "NFLoadParams: Thread stopped = " + currentThread);
    }
    
    public void clearDependList() {
        this.q.removeAllElements();
        this.r.removeAllElements();
    }
    
    public void clearDBList() {
        this.o.removeAllElements();
        this.p.removeAllElements();
    }
    
    public boolean reloadNeeded(final long n) {
        return this.reloadNeeded(new Date(n));
    }
    
    public boolean reloadNeeded(final Date date) {
        if (NFDebug.enabled(32768L)) {
            NFDebug.print("NFLoadParams: reloadNeeded, ts = " + date);
        }
        for (int i = 0; i < this.q.size(); ++i) {
            final NFDataBean nfDataBean = this.q.elementAt(i);
            if (nfDataBean.reloadNeeded(date)) {
                if (NFDebug.enabled(32768L)) {
                    NFDebug.print("NFLoadParams: RELOAD " + nfDataBean);
                }
                return true;
            }
            if (NFDebug.enabled(32768L)) {
                NFDebug.print("NFLoadParams: NO RELOAD " + nfDataBean);
            }
        }
        return false;
    }
    
    public boolean loadDataNeeded() {
        while (this.o.size() > 0) {
            final NFDataBean element = this.o.elementAt(0);
            if (!(element instanceof NFDataBean)) {
                this.o.removeElementAt(0);
            }
            else {
                final NFDataBean nfDataBean = element;
                if (nfDataBean.loadDataMode(nfDataBean.getExprParam()) == 2) {
                    return true;
                }
                this.q.addElement(nfDataBean);
                this.o.removeElementAt(0);
            }
        }
        return false;
    }
    
    public void updateData() {
        if (this.o.size() == 0) {
            for (int i = 0; i < this.q.size(); ++i) {
                this.o.addElement(this.q.elementAt(i));
            }
        }
        this.q.removeAllElements();
    }
    
    public boolean loadData(final String s, final Object o) throws Exception {
        return this.b.loadData(s, o);
    }
    
    public synchronized boolean loadData(final Vector vector) throws Exception {
        while (this.o.size() > 0) {
            final NFDataBean element = this.o.elementAt(0);
            if (!(element instanceof NFDataBean)) {
                this.o.removeElementAt(0);
            }
            else {
                final NFDataBean nfDataBean = element;
                try {
                    if (nfDataBean.loadDataMode(nfDataBean.getExprParam()) == 2) {
                        if (this.loadParams(nfDataBean)) {
                            return true;
                        }
                        this.p.addElement(nfDataBean);
                    }
                }
                catch (Exception ex) {
                    this.c("DB=" + nfDataBean + " errored out. Notifying Observers of Failure.");
                    this.b("DataBean Error: " + nfDataBean.getClass().getName() + ": " + ex.getMessage());
                    if (vector == null) {
                        throw ex;
                    }
                    vector.addElement(ex);
                }
                this.q.addElement(nfDataBean);
                this.o.removeElementAt(0);
            }
        }
        for (int i = 0; i < this.p.size(); ++i) {
            final NFDataBean nfDataBean2 = this.p.elementAt(i);
            try {
                nfDataBean2.close();
            }
            catch (Exception ex2) {
                if (vector == null) {
                    throw ex2;
                }
                vector.addElement(ex2);
            }
        }
        this.p.removeAllElements();
        this.c("CHECK for completeness in loadData");
        this.a();
        return false;
    }
    
    private void a(String s, final Exception ex, final NFParamServ nfParamServ) {
        if (s == null) {
            s = ex.getMessage();
            if (s == null) {
                s = ex.toString();
            }
        }
        if (nfParamServ != null && !nfParamServ.closed) {
            nfParamServ.statusMsg(s);
        }
        else {
            NFParam.printError("NFParamServer: " + s);
        }
    }
    
    public String getCDLForSymbol(final Number n, final Hashtable hashtable) {
        final Enumeration<String> keys = (Enumeration<String>)hashtable.keys();
        while (keys.hasMoreElements()) {
            final String s = keys.nextElement();
            final Number n2 = hashtable.get(s);
            if (n2 != null && n2.equals(n)) {
                return s;
            }
        }
        this.d("could not locate symbol table entry for " + n);
        return "";
    }
    
    public String getCDLForDef(final NFParamDef nfParamDef, final Object o) {
        if (nfParamDef == null) {
            this.d("null parameter definition to getCDLForDef.");
            try {
                return o.toString();
            }
            catch (Exception ex) {
                return "";
            }
        }
        final StringBuffer sb = new StringBuffer();
        switch (nfParamDef.type) {
            case 6: {
                final NFParamDef vector_def = nfParamDef.vector_def;
                final Vector vector = (Vector)o;
                if (vector == null) {
                    return "";
                }
                for (int i = 0; i < vector.size(); ++i) {
                    if (i > 0) {
                        sb.append(", ");
                    }
                    sb.append(this.getCDLForDef(vector_def, vector.elementAt(i)));
                }
                break;
            }
            case 5: {
                final Vector vector2 = (Vector)o;
                if (vector2 == null) {
                    return "()";
                }
                sb.append("(");
                final Vector tuple_def = nfParamDef.tuple_def;
                for (int j = 0; j < vector2.size(); ++j) {
                    int n = j;
                    if (n >= nfParamDef.tuple_def.size()) {
                        n = nfParamDef.tuple_def.size() - 1;
                    }
                    if (j > 0) {
                        sb.append(", ");
                    }
                    final Object element = vector2.elementAt(j);
                    String cdlForDef;
                    if (element == null) {
                        cdlForDef = "";
                    }
                    else {
                        cdlForDef = this.getCDLForDef(tuple_def.elementAt(n), element);
                    }
                    sb.append(cdlForDef);
                }
                sb.append(")");
                break;
            }
            case 2: {
                if (o == null) {
                    return "";
                }
                sb.append("\"" + NFParam.doEscapes(o.toString()) + "\"");
                break;
            }
            case 4: {
                if (o == null) {
                    return "";
                }
                sb.append(NFColor.toString((Color)o));
                break;
            }
            case 7: {
                if (o == null) {
                    return "";
                }
                String s;
                if (o instanceof NFParamImage) {
                    s = ((NFParamImage)o).filename;
                }
                else {
                    s = nfParamDef.imageLabel;
                }
                if (s == null || s.length() < 1) {
                    return "null";
                }
                sb.append("\"" + NFParam.doEscapes(s) + "\"");
                break;
            }
            case 3: {
                if (o == null) {
                    return "null";
                }
                sb.append(this.getCDLForSymbol((Number)o, nfParamDef.symtable));
                break;
            }
            case 1: {
                if (o == null) {
                    return "null";
                }
                if (o instanceof Double && ((Double)o).isNaN()) {
                    return "null";
                }
                String s2 = o.toString();
                if (s2.endsWith(".0")) {
                    s2 = s2.substring(0, s2.length() - 2);
                }
                sb.append(s2);
                break;
            }
            case 8: {
                if (o == null || (o instanceof Double && ((Double)o).isNaN())) {
                    return "null";
                }
                String s3 = o.toString();
                if (o instanceof NFDate || o instanceof NFTimeUnit) {
                    s3 = "\"" + s3 + "\"";
                }
                else if (s3.endsWith(".0")) {
                    s3 = s3.substring(0, s3.length() - 2);
                }
                sb.append(s3);
                break;
            }
            default: {
                this.d("Unknown def type=" + nfParamDef.type + " using default.");
                try {
                    sb.append((o == null) ? "null" : o.toString());
                }
                catch (Exception ex2) {
                    sb.append("");
                }
                break;
            }
        }
        return sb.toString();
    }
    
    public String getCDL(final String s, final Object o) {
        NFParamDef paramDef;
        try {
            paramDef = this.a.getParamDef(s);
            if (!paramDef.loaded) {
                this.d("Param=" + s + " was never loaded. skipping...");
                return "";
            }
        }
        catch (Exception ex) {
            this.d("could not get parameter definition for " + s + ".");
            return "";
        }
        return s + " = " + this.getCDLForDef(paramDef, o) + ";\n";
    }
    
    public String getCDL() {
        return this.getCDL(false);
    }
    
    public String getCDL(final boolean b) {
        final StringBuffer sb = new StringBuffer();
        final Vector primaryKeys = this.a.getPrimaryKeys(null);
        try {
            if (primaryKeys.contains("ChartSize") && !this.a.getParamDef("ChartSize").loaded) {
                primaryKeys.addElement("ChartWidth");
                primaryKeys.addElement("ChartHeight");
            }
        }
        catch (Exception ex2) {}
        for (int i = 0; i < primaryKeys.size(); ++i) {
            final String s = primaryKeys.elementAt(i);
            if (!this.a.isAliasedParam(s) || b) {
                if (NFDataBeanTable.getDefault().getDataBeanForParam(s) != null || NFDataBeanTable.getDefault().getDataBeanForKeyword(s) != null) {
                    this.d("DataBean reference: param=" + s);
                }
                else {
                    try {
                        final Object value = this.a.getValue(this.a.getParamDef(s));
                        if (value == null) {
                            this.d("param=" + s + " undefined.");
                        }
                        else {
                            sb.append(this.getCDL(s, value));
                        }
                    }
                    catch (Exception ex) {
                        this.d("ParseException getting param=" + s + " e=" + ex);
                    }
                }
            }
        }
        return sb.toString();
    }
    
    public synchronized void dataBeanLoadData(final Object o, final Object o2, final String s, final Vector vector) throws Exception {
        this.c("DataBean LOADED DATA: db=" + o);
        this.a(o);
        this.b.loadDataTable(s, vector);
        this.a();
    }
    
    public synchronized void dataBeanLoadParams(final Object o, final Object o2, final Object o3) throws Exception {
        this.c("DataBean LOADED PARAMS: db=" + o);
        this.a(o);
        this.loadParams(o3);
        this.a();
    }
    
    public synchronized void dataBeanUserInput(final Object o, final Object o2, final String s) throws Exception {
        this.a.update("UserInput");
    }
    
    public synchronized void dataBeanFailure(final Object o, final String s) {
        this.c("DataBean FAILURE: db=" + o + " reason:" + s);
        this.b(s);
    }
    
    public void dataBeanRequestInitialParamsLoadedNotification(final Object o) {
        if (o instanceof NFInitialParamsLoadedObserver) {
            this.addInitialParamsLoadedObserver((NFInitialParamsLoadedObserver)o);
        }
        else {
            NFDebug.print(2L, "NFLoadParams: dataBeanRequestInitialParamsLoadedNotification called with \nnon InitialParamsLoadedObserver = " + o);
        }
    }
    
    public Hashtable dataBeanGetExtraParams(final Object o) {
        return this.getDataBeanParameters();
    }
    
    public void setDataBeanParameters(final Hashtable i) {
        this.i = i;
    }
    
    public Hashtable getDataBeanParameters() {
        return this.i;
    }
    
    private void a(final Object o) {
        if (o == null) {
            return;
        }
        synchronized (this.r) {
            if (!this.r.contains(o)) {
                this.c("Adding " + o + "..." + Thread.currentThread());
                this.r.addElement(o);
            }
        }
    }
    
    private void a() {
        this.c("Checking for completeness..." + Thread.currentThread());
        synchronized (this.r) {
            synchronized (this.o) {
                for (int i = 0; i < this.o.size(); ++i) {
                    final NFDataBean nfDataBean = this.o.elementAt(i);
                    if (!this.r.contains(nfDataBean)) {
                        this.c("DB=" + nfDataBean + " not loaded yet.");
                        return;
                    }
                }
            }
            synchronized (this.q) {
                for (int j = 0; j < this.q.size(); ++j) {
                    final NFDataBean nfDataBean2 = this.q.elementAt(j);
                    if (!this.r.contains(nfDataBean2)) {
                        this.c("DB=" + nfDataBean2 + " not loaded yet.");
                        return;
                    }
                }
            }
            synchronized (this.d) {
                for (int k = 0; k < this.d.size(); ++k) {
                    final NFParamServ nfParamServ = this.d.elementAt(k);
                    if (!this.r.contains(nfParamServ)) {
                        this.c("NFParamServ=" + nfParamServ + " not loaded yet.");
                        return;
                    }
                }
            }
        }
        this.b();
    }
    
    public void addInitialParamsLoadedObserver(final NFInitialParamsLoadedObserver nfInitialParamsLoadedObserver) {
        synchronized (this.s) {
            this.s.addElement(nfInitialParamsLoadedObserver);
        }
    }
    
    public void removeInitialParamsLoadedObserver(final NFInitialParamsLoadedObserver nfInitialParamsLoadedObserver) {
        synchronized (this.s) {
            if (this.s.contains(nfInitialParamsLoadedObserver)) {
                this.s.removeElement(nfInitialParamsLoadedObserver);
            }
        }
    }
    
    private void b(final String s) {
        this.c("Initial Load Failed.");
        synchronized (this.s) {
            final Vector s2 = this.s;
            this.s = new Vector();
            for (int i = 0; i < s2.size(); ++i) {
                s2.elementAt(i).initialParamsLoadFailure(this, s);
            }
        }
    }
    
    private void b() {
        this.c("Initial Load Complete. CDL:");
        synchronized (this.s) {
            final Vector s = this.s;
            this.s = new Vector();
            for (int i = 0; i < s.size(); ++i) {
                this.c("Notifiying: " + s.elementAt(i));
                s.elementAt(i).initialParamsLoaded(this);
            }
        }
    }
    
    public void update(final Observable observable, final Object o) {
        if (observable == this.a && o instanceof String && ((String)o).equals("LoadParams")) {
            this.a();
        }
    }
    
    private void c(final String s) {
        if (this.j) {
            NFDebug.print(2L, "NFLoadParams: Initial CDL Load: " + s);
        }
    }
    
    private void d(final String s) {
        if (this.k) {
            NFDebug.print(2L, "NFLoadParams: CDL Load/Print: " + s);
        }
    }
    
    public void clean() {
        this.clearDependList();
        this.clearDBList();
        if (this.a != null) {
            this.a.clean();
        }
    }
    
    public Properties loadRuntimeProperties(Properties properties) {
        if (properties == null) {
            properties = new Properties();
        }
        String parameter = null;
        try {
            parameter = this.getApp().getParameter("NFRuntimeProperties");
        }
        catch (Exception ex) {}
        if (parameter != null) {
            try {
                InputStream inputStreamForString;
                if (NFUtil.getJDKVersion() >= 1.1) {
                    inputStreamForString = NF11Util.getInputStreamForString(parameter);
                }
                else {
                    inputStreamForString = new StringBufferInputStream(parameter);
                }
                properties.load(inputStreamForString);
                inputStreamForString.close();
            }
            catch (Exception ex2) {}
        }
        return properties;
    }
}
