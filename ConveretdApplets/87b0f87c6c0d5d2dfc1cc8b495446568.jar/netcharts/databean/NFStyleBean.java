// 
// Decompiled by Procyon v0.5.30
// 

package netcharts.databean;

import java.util.StringTokenizer;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.io.InputStream;
import netcharts.util.NFHttpClient;
import java.lang.reflect.InvocationTargetException;
import netcharts.util.NFContext;
import netcharts.util.NFPrivilegeBasedNetworkAccess;
import netcharts.util.NFKeyValue;
import netcharts.util.NFToken;
import netcharts.util.NFUtil;
import java.net.URL;
import netcharts.util.NFFile;
import java.awt.Component;
import netcharts.util.NFGlobal;
import netcharts.util.NFParamDef;
import netcharts.util.NFParam;
import netcharts.util.NFDataBeanObserver;
import java.util.Vector;
import netcharts.util.NFDataBean;

public class NFStyleBean extends NFDataBean
{
    protected static final String VARIABLE_PREFIX = "$";
    protected static final String DELIMITER = ",";
    protected String style;
    protected String styleClass;
    protected Vector styleData;
    protected String chartType;
    protected Vector variables;
    protected NFDataBeanObserver observer;
    protected Object info;
    static /* synthetic */ Class a;
    
    public NFStyleBean() {
        this.style = "";
        this.styleClass = "";
        this.styleData = null;
        this.chartType = null;
        this.variables = null;
        this.observer = null;
        this.info = null;
        super.name = "NFStyleBean";
        super.desc = "Style Processing";
        super.prefix = "Style";
        super.keyword = "STYLE";
    }
    
    public void defineParams(final NFParam nfParam) throws Exception {
        if (!nfParam.exists("STYLE")) {
            final Vector<NFParamDef> vector = new Vector<NFParamDef>();
            vector.addElement(nfParam.defineString("StyleFile", null));
            vector.addElement(nfParam.defineString("StyleClass", null));
            nfParam.defineTuple("STYLE", vector, true).dataBean = this;
        }
    }
    
    public void loadParams(final NFParam nfParam) throws Exception {
        try {
            this.parseStyle(nfParam);
            final Component component = nfParam.getComponent();
            if (component != null) {
                final String name = component.getClass().getName();
                for (int i = 0; i < NFGlobal.graphClassNames.length; ++i) {
                    if (name.startsWith(NFGlobal.graphClassNames[i])) {
                        this.chartType = NFGlobal.classSymbol[i];
                        break;
                    }
                }
            }
        }
        catch (Exception ex) {
            ex.printStackTrace();
            this.debug("Exception in loadParams: " + ex.toString());
        }
    }
    
    public String getCSSClass() {
        return this.styleClass;
    }
    
    public String getResolvedCSSClass() {
        return this.getResolvedValues(this.getCSSClass());
    }
    
    public void setCSSClass(final String styleClass) {
        this.styleClass = styleClass;
    }
    
    public String getResolvedCSSPath() {
        final String resolvedValue = this.getResolvedValue(this.getCSSPath());
        try {
            return this.resolvePath(NFFile.httpFormat(resolvedValue));
        }
        catch (Exception ex) {
            return resolvedValue;
        }
    }
    
    public String getCSSPath() {
        return this.style;
    }
    
    public void setCSSPath(final String style) {
        this.style = style;
    }
    
    public Vector getStyleData() {
        return this.styleData;
    }
    
    public void setStyleData(final Vector styleData) {
        this.styleData = styleData;
    }
    
    public String resolvePath(final String s) throws Exception {
        if (s.toLowerCase().startsWith("http")) {
            try {
                return new URL(s).toExternalForm();
            }
            catch (Exception ex) {}
        }
        final URL fileURL = NFUtil.getFileURL(s, super.ctxt);
        if (fileURL.getProtocol().equalsIgnoreCase("file")) {
            throw new Exception("Relative reference to STYLE used without specifing a server.");
        }
        return fileURL.toExternalForm();
    }
    
    public StringBuffer getParam(final String s, StringBuffer sb) {
        if (sb == null) {
            sb = new StringBuffer();
        }
        if (super.exprParam != null && s.equals(super.exprParam)) {
            sb.append("STYLE ");
            sb.append("(");
            sb.append("\"");
            NFToken.doEscapes(this.style, sb, false);
            sb.append("\"");
            sb.append(",");
            if (this.styleClass != null) {
                sb.append("\"");
                NFToken.doEscapes(this.styleClass, sb, false);
                sb.append("\"");
            }
            if (this.styleData != null && this.styleData.size() > 0) {
                sb.append(",");
                for (int i = 0; i < this.styleData.size(); ++i) {
                    if (i > 0) {
                        sb.append(",");
                    }
                    sb.append("\"");
                    NFToken.doEscapes(String.valueOf(this.styleData.elementAt(i)), sb, false);
                    sb.append("\"");
                }
            }
            sb.append(")");
            return sb;
        }
        return sb;
    }
    
    public Vector getParams(Vector vector) {
        if (vector == null) {
            vector = new Vector<NFKeyValue>();
        }
        if (super.exprParam != null) {
            final NFKeyValue nfKeyValue = new NFKeyValue();
            nfKeyValue.key = super.exprParam;
            nfKeyValue.value = this.getParam((String)nfKeyValue.key, null);
            vector.addElement(nfKeyValue);
        }
        return vector;
    }
    
    public int loadDataMode(final String s) {
        return 1;
    }
    
    public boolean loadData(final NFDataBeanObserver observer, final Object info) throws Exception {
        this.observer = observer;
        this.info = info;
        if (NFPrivilegeBasedNetworkAccess.isPrivilegeBasedSecurityEnvironment()) {
            try {
                NFPrivilegeBasedNetworkAccess.invokePrivilegedMethod(this, this.getClass().getMethod("myRun", (Class<?>[])new Class[0]), new Object[0]);
            }
            catch (Exception ex) {
                System.out.println("Exception during myRun call in NFStyleBean: " + ex);
                ex.printStackTrace();
            }
        }
        else {
            this.myRun();
        }
        return false;
    }
    
    public void myRun() {
        URL url;
        try {
            url = this.createURL();
        }
        catch (Exception ex) {
            final String string = "Unable to create URL\n ERROR: " + ex.getMessage();
            this.statusMsg(string);
            this.debug("EXCEPTION from createURL. e=" + ex);
            this.observer.dataBeanFailure(this, string);
            return;
        }
        final URL documentBase = super.ctxt.getDocumentBase();
        if (NFContext.getUserAgentType() == 3 && documentBase != null && documentBase.getHost().equals(url.getHost()) && documentBase.getPort() == url.getPort()) {
            try {
                this.handleReply(this.getServerReply(url));
            }
            catch (Throwable targetException) {
                if (targetException instanceof InvocationTargetException) {
                    targetException = ((InvocationTargetException)targetException).getTargetException();
                }
                final String string2 = "Lost connection to server\n ERROR: " + targetException.getMessage();
                this.statusMsg(string2);
                this.debug("EXCEPTION from handleReply(getServerReply). e=" + targetException);
                this.observer.dataBeanFailure(this, string2);
            }
        }
        else {
            try {
                final InputStream open = this.open(url);
                this.handleReply(new String(NFHttpClient.readBytesFully(open)));
                try {
                    open.close();
                }
                catch (Exception ex3) {}
            }
            catch (Exception ex2) {
                final String string3 = "Lost connection to server\n ERROR: " + ex2.getMessage();
                this.statusMsg(string3);
                this.debug("EXCEPTION from processStream. e=" + ex2);
                this.observer.dataBeanFailure(this, string3);
            }
        }
    }
    
    public URL createURL() throws Exception {
        final StringBuffer sb = new StringBuffer();
        final String resolvedCSSPath = this.getResolvedCSSPath();
        if (resolvedCSSPath.toLowerCase().startsWith("file:")) {
            throw new Exception("Open called with no server specified.");
        }
        sb.append(resolvedCSSPath);
        sb.append("?output=cdl");
        if (this.chartType != null) {
            sb.append("&chartType=" + this.chartType);
        }
        final String resolvedCSSClass = this.getResolvedCSSClass();
        if (resolvedCSSClass != null) {
            sb.append("&class=" + resolvedCSSClass);
        }
        if (this.styleData != null && this.styleData.size() > 0) {
            for (int i = 0; i < this.styleData.size(); ++i) {
                sb.append("&data=" + URLEncoder.encode(String.valueOf(this.styleData.elementAt(i))));
            }
        }
        if (!super.exprParam.equalsIgnoreCase("STYLE")) {
            sb.append("&param=");
            sb.append(super.exprParam);
        }
        final URL url = new URL(sb.toString());
        this.debug("trying url=" + url.toExternalForm());
        return url;
    }
    
    public InputStream open(final URL url) throws Exception {
        if (NFContext.getUserAgentType() == 0) {
            final URLConnection openConnection = url.openConnection();
            openConnection.setUseCaches(false);
            return openConnection.getInputStream();
        }
        return new NFHttpClient(super.ctxt).getInputStream(url, true);
    }
    
    protected void handleReply(String string) throws Exception {
        if (string == null) {
            return;
        }
        if (string.startsWith("STATUS ")) {
            this.statusMsg(string.substring(7));
        }
        else if (string.startsWith("CLOSE ")) {
            this.statusMsg(string.substring(6));
        }
        else {
            if (!string.trim().endsWith(";")) {
                string += ";";
            }
            this.observer.dataBeanLoadParams(this, this.info, string);
        }
    }
    
    protected void statusMsg(final String s) {
        final StringBuffer sb = new StringBuffer();
        sb.append("   STYLE: ");
        sb.append(this.getResolvedCSSPath());
        sb.append("\nSTATUS: ");
        int n = 0;
        for (int i = s.indexOf("<br>", n); i != -1; i = s.indexOf("<br>", n)) {
            sb.append(s.substring(n, i));
            sb.append("\n        ");
            n = i + 4;
        }
        sb.append(s.substring(n));
        this.statusMsg("STYLE Status", sb.toString());
    }
    
    protected void parseStyle(final NFParam nfParam) throws Exception {
        final NFParamDef paramDef = nfParam.getParamDef("MetaData");
        final Vector vector = (paramDef == null) ? null : ((Vector)nfParam.getValue(paramDef));
        if (vector != null) {
            this.variables = (Vector)vector.clone();
        }
        else {
            this.variables = null;
        }
        final Vector vector2 = (Vector)nfParam.get("STYLE");
        if (vector2 == null || vector2.size() == 0) {
            this.debug("null or empty vector in parseStyle");
            return;
        }
        try {
            this.style = NFUtil.getString(vector2, 0, null);
            this.styleClass = NFUtil.getString(vector2, 1, null);
            if (vector2.size() > 2) {
                final Vector styleData = (Vector)vector2.clone();
                styleData.removeElementAt(0);
                styleData.removeElementAt(0);
                this.styleData = styleData;
            }
        }
        catch (Exception ex) {
            this.debug("Exception parsing STYLE " + ex.toString());
        }
    }
    
    protected static String getVariable(final String s) {
        if (s == null) {
            return null;
        }
        if (s.startsWith("$")) {
            return s.substring("$".length());
        }
        return null;
    }
    
    protected String getResolvedValues(final String s) {
        if (s == null || this.variables == null) {
            return s;
        }
        final StringTokenizer stringTokenizer = new StringTokenizer(s, ",", true);
        final StringBuffer sb = new StringBuffer();
        while (stringTokenizer.hasMoreTokens()) {
            String s2 = stringTokenizer.nextToken();
            if (!s2.equals(",")) {
                s2 = this.getResolvedValue(s2);
            }
            sb.append(s2);
        }
        return sb.toString();
    }
    
    protected String getResolvedValue(final String s) {
        if (s == null || this.variables == null) {
            return s;
        }
        final String variable = getVariable(s);
        for (int n = 0; variable != null && n < this.variables.size(); ++n) {
            final Vector vector = this.variables.elementAt(n);
            if (vector != null) {
                if (variable.equals(NFUtil.getString(vector, 0, null))) {
                    final String string = NFUtil.getString(vector, 1, null);
                    if (string != null) {
                        return string;
                    }
                }
            }
        }
        return s;
    }
    
    protected String getServerReply(final URL url) throws Exception {
        final Object invoke = Class.forName("chartworks.es.util.ServerReply").getMethod("getContentObject", (Class<?>[])null).invoke(Class.forName("chartworks.es.server.ServerReplyUtil").getMethod("getServerReply", (NFStyleBean.a == null) ? (NFStyleBean.a = class$("java.lang.String")) : NFStyleBean.a, (NFStyleBean.a == null) ? (NFStyleBean.a = class$("java.lang.String")) : NFStyleBean.a).invoke(null, url.getFile(), NFContext.getUserAgent()), (Object[])null);
        if (invoke == null) {
            return null;
        }
        String string;
        if (invoke instanceof String) {
            string = (String)invoke;
        }
        else {
            string = invoke.toString();
        }
        return string;
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
