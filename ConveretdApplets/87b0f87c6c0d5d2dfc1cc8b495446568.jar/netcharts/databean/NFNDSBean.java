// 
// Decompiled by Procyon v0.5.30
// 

package netcharts.databean;

import java.net.URLConnection;
import netcharts.util.NFHttpClient;
import java.net.URLEncoder;
import java.lang.reflect.InvocationTargetException;
import netcharts.util.NFContext;
import netcharts.util.NFPrivilegeBasedNetworkAccess;
import netcharts.util.NFDebug;
import netcharts.util.NFKeyValue;
import java.util.Enumeration;
import netcharts.util.NFToken;
import netcharts.util.NFUtil;
import java.net.URL;
import java.awt.Component;
import netcharts.util.NFGlobal;
import netcharts.util.NFFile;
import netcharts.util.NFParamDef;
import java.util.Vector;
import netcharts.util.NFParam;
import java.util.Hashtable;
import netcharts.util.NFDataBeanObserver;
import netcharts.util.NFTimeUnit;
import java.io.ObjectInputStream;
import java.io.InputStream;
import netcharts.util.NFDataBean;

public class NFNDSBean extends NFDataBean implements Runnable
{
    protected InputStream is;
    protected ObjectInputStream ris;
    protected String nds;
    protected NFTimeUnit tuRate;
    protected String serverName;
    protected Number serverPort;
    protected NFDataBeanObserver observer;
    protected Object info;
    protected Thread thread;
    protected Hashtable params;
    protected Hashtable variables;
    protected boolean automaticUpdates;
    protected boolean clientWait;
    protected static Hashtable lps;
    protected StringBuffer sb;
    static /* synthetic */ Class a;
    
    public NFNDSBean() {
        this.is = null;
        this.ris = null;
        this.nds = "";
        this.tuRate = new NFTimeUnit();
        this.serverName = null;
        this.serverPort = null;
        this.observer = null;
        this.info = null;
        this.thread = null;
        this.params = new Hashtable();
        this.variables = new Hashtable();
        this.automaticUpdates = false;
        this.clientWait = true;
        this.sb = new StringBuffer();
        super.name = "NFNDSBean";
        super.desc = "Named Data Set Processing";
        super.prefix = "Nds";
        super.keyword = "NDS";
    }
    
    public void defineParams(final NFParam nfParam) throws Exception {
        if (!nfParam.exists("NdsClientWait")) {
            nfParam.defineString("NdsClientWait", "ON").dataBean = this;
        }
        if (!nfParam.exists("NdsRate")) {
            nfParam.defineString("NdsRate", null).dataBean = this;
        }
        if (!nfParam.exists("NdsServer")) {
            final Vector<NFParamDef> vector = new Vector<NFParamDef>();
            vector.addElement(nfParam.defineString("ServerName", null));
            vector.addElement(nfParam.defineNumber("ServerPort", null));
            nfParam.defineTuple("NdsServer", vector).dataBean = this;
        }
        if (!nfParam.exists("NdsParams")) {
            final Vector<NFParamDef> vector2 = new Vector<NFParamDef>();
            vector2.addElement(nfParam.defineString("NdsParam-name", null));
            vector2.addElement(nfParam.defineString("NdsParam-value", null));
            nfParam.defineVector("NdsParams", nfParam.defineTuple("NdsParam-tuple", vector2));
            nfParam.getParamDef("NdsParams").dataBean = this;
        }
        if (!nfParam.exists("NdsVariables")) {
            final Vector<NFParamDef> vector3 = new Vector<NFParamDef>();
            vector3.addElement(nfParam.defineString("NdsVariable-name", null));
            vector3.addElement(nfParam.defineString("NdsVariable-value", null));
            nfParam.defineVector("NdsVariables", nfParam.defineTuple("NdsVariable-tuple", vector3));
            nfParam.getParamDef("NdsVariables").dataBean = this;
        }
        if (!nfParam.exists("NDS")) {
            nfParam.defineString("NDS", null).dataBean = this;
        }
    }
    
    public void loadParams(final NFParam nfParam) throws Exception {
        try {
            this.parseServer((Vector)nfParam.get("NdsServer"));
            this.parseRate((String)nfParam.get("NdsRate"));
            this.nds = (String)nfParam.get("NDS");
            this.nds = NFFile.httpFormat(this.nds);
            if (!this.nds.endsWith(".nds") && !this.nds.endsWith(".ndx")) {
                this.nds += ".nds";
            }
            this.parseParams((Vector)nfParam.get("NdsParams"));
            this.parseVariables((Vector)nfParam.get("NdsVariables"));
            final Component component = nfParam.getComponent();
            if (component != null) {
                final String name = component.getClass().getName();
                for (int i = 0; i < NFGlobal.graphClassNames.length; ++i) {
                    if (name.startsWith(NFGlobal.graphClassNames[i])) {
                        this.params.put("chartType", NFGlobal.classSymbol[i]);
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
    
    public Hashtable getNdsParamTable() {
        return this.params;
    }
    
    public Hashtable getNdsVariableTable() {
        return this.variables;
    }
    
    public String getNdsType() {
        String s = this.params.get("NdsType");
        if (s == null) {
            s = "Nds";
        }
        return s;
    }
    
    public void setNdsPath(final String nds) {
        this.nds = nds;
    }
    
    public String getNdsPath() {
        return this.nds;
    }
    
    public String getNdsName() {
        try {
            return this.resolvePath(this.nds);
        }
        catch (Exception ex) {
            return this.nds;
        }
    }
    
    public void setNdsParams(final Hashtable params) {
        this.params = params;
    }
    
    public Hashtable getNdsParams() {
        return this.params;
    }
    
    public void setNdsVariables(final Hashtable variables) {
        this.variables = variables;
    }
    
    public Hashtable getNdsVariables() {
        return this.variables;
    }
    
    public void setNdsType(final String s) {
        this.params.put("NdsType", s);
    }
    
    public String resolvePath(final String s) throws Exception {
        if (s.toLowerCase().startsWith("http")) {
            try {
                return new URL(s).toExternalForm();
            }
            catch (Exception ex) {}
        }
        URL fileURL = NFUtil.getFileURL(s, super.ctxt);
        if (fileURL.getProtocol().equalsIgnoreCase("file") && this.serverName == null) {
            throw new Exception("Relative reference to NDS used without specifing a server.");
        }
        if (this.serverName != null || this.serverPort != null) {
            fileURL = new URL(fileURL.getProtocol(), (this.serverName == null) ? fileURL.getHost() : this.serverName, (this.serverPort == null) ? ((fileURL.getPort() == -1) ? 80 : fileURL.getPort()) : this.serverPort.intValue(), fileURL.getFile());
        }
        return fileURL.toExternalForm();
    }
    
    public StringBuffer getParam(final String s, StringBuffer sb) {
        if (sb == null) {
            sb = this.sb;
            sb.setLength(0);
        }
        if (s.equals("NdsClientWait")) {
            sb.append("\"");
            if (this.clientWait) {
                sb.append("ON");
            }
            else {
                sb.append("OFF");
            }
            sb.append("\"");
            return sb;
        }
        if (s.equals("NdsRate")) {
            sb.append("\"");
            if (this.automaticUpdates) {
                sb.append("AUTO");
            }
            else {
                sb.append(this.tuRate.toString());
            }
            sb.append("\"");
            return sb;
        }
        if (s.equals("NdsServer")) {
            if (this.serverName == null && this.serverPort == null) {
                return sb;
            }
            sb.append("(");
            if (this.serverName != null) {
                sb.append("\"");
                NFToken.doEscapes(this.serverName, sb, false);
                sb.append("\"");
            }
            if (this.serverPort != null) {
                sb.append(",");
                NFToken.doEscapes(String.valueOf(this.serverPort.intValue()), sb, false);
            }
            sb.append(")");
            return sb;
        }
        else {
            if (super.exprParam != null && s.equals(super.exprParam)) {
                sb.append("NDS \"");
                NFToken.doEscapes(this.nds, sb, false);
                sb.append("\"");
                return sb;
            }
            if (s.equals("NdsParams")) {
                final Enumeration keys = this.params.keys();
                if (!keys.hasMoreElements()) {
                    return sb;
                }
                int n = 0;
                while (keys.hasMoreElements()) {
                    if (n != 0) {
                        sb.append(", ");
                    }
                    final String s2 = keys.nextElement();
                    final String s3 = this.params.get(s2);
                    sb.append("(\"");
                    NFToken.doEscapes(s2, sb, false);
                    sb.append("\", \"");
                    NFToken.doEscapes(s3, sb, false);
                    sb.append("\")");
                    n = 1;
                }
                return sb;
            }
            else {
                if (!s.equals("NdsVariables")) {
                    sb.append("ERROR");
                    return sb;
                }
                final Enumeration keys2 = this.variables.keys();
                if (!keys2.hasMoreElements()) {
                    return sb;
                }
                int n2 = 0;
                while (keys2.hasMoreElements()) {
                    if (n2 != 0) {
                        sb.append(", ");
                    }
                    final String s4 = keys2.nextElement();
                    final String s5 = this.variables.get(s4);
                    sb.append("(\"");
                    NFToken.doEscapes(s4, sb, false);
                    sb.append("\", \"");
                    NFToken.doEscapes(s5, sb, false);
                    sb.append("\")");
                    n2 = 1;
                }
                return sb;
            }
        }
    }
    
    public Vector getParams(Vector vector) {
        if (vector == null) {
            vector = new Vector<NFKeyValue>();
        }
        final NFKeyValue nfKeyValue = new NFKeyValue();
        nfKeyValue.key = "NdsRate";
        nfKeyValue.value = this.getParam((String)nfKeyValue.key, new StringBuffer());
        if (nfKeyValue.value != null && ((StringBuffer)nfKeyValue.value).length() != 0) {
            vector.addElement(nfKeyValue);
        }
        final NFKeyValue nfKeyValue2 = new NFKeyValue();
        nfKeyValue2.key = "NdsClientWait";
        nfKeyValue2.value = this.getParam((String)nfKeyValue2.key, new StringBuffer());
        if (nfKeyValue2.value != null && ((StringBuffer)nfKeyValue2.value).length() != 0) {
            vector.addElement(nfKeyValue2);
        }
        final NFKeyValue nfKeyValue3 = new NFKeyValue();
        nfKeyValue3.key = "NdsServer";
        nfKeyValue3.value = this.getParam((String)nfKeyValue3.key, new StringBuffer());
        if (nfKeyValue3.value != null && ((StringBuffer)nfKeyValue3.value).length() != 0) {
            vector.addElement(nfKeyValue3);
        }
        final NFKeyValue nfKeyValue4 = new NFKeyValue();
        nfKeyValue4.key = "NdsParams";
        nfKeyValue4.value = this.getParam((String)nfKeyValue4.key, new StringBuffer());
        if (nfKeyValue4.value != null && ((StringBuffer)nfKeyValue4.value).length() != 0) {
            vector.addElement(nfKeyValue4);
        }
        final NFKeyValue nfKeyValue5 = new NFKeyValue();
        nfKeyValue5.key = "NdsVariables";
        nfKeyValue5.value = this.getParam((String)nfKeyValue5.key, new StringBuffer());
        if (nfKeyValue5.value != null && ((StringBuffer)nfKeyValue5.value).length() != 0) {
            vector.addElement(nfKeyValue5);
        }
        if (super.exprParam != null) {
            final NFKeyValue nfKeyValue6 = new NFKeyValue();
            nfKeyValue6.key = super.exprParam;
            nfKeyValue6.value = this.getParam((String)nfKeyValue6.key, new StringBuffer());
            vector.addElement(nfKeyValue6);
        }
        return vector;
    }
    
    public int loadDataMode(final String s) {
        if (s.startsWith(super.prefix)) {
            return 0;
        }
        if (super.exprParam != null && super.exprParam.equals(s)) {
            return 1;
        }
        return 0;
    }
    
    public boolean loadData(final NFDataBeanObserver observer, final Object info) throws Exception {
        this.observer = observer;
        this.info = info;
        this.run();
        if (this.tuRate.getSeconds() > 0.0) {
            this.myStart();
        }
        return false;
    }
    
    public void myStart() {
        if (this.thread != null) {
            return;
        }
        this.debug("Opening Connection");
        (this.thread = new Thread(this)).start();
    }
    
    public void stop() {
        if (this.thread != null) {
            this.debug("Closing Connection");
            this.disconnect();
            NFDebug.print(16384L, "NFNDSBean: Thread stopped = " + this.thread);
            this.thread.stop();
        }
        this.thread = null;
    }
    
    public void run() {
        if (NFPrivilegeBasedNetworkAccess.isPrivilegeBasedSecurityEnvironment()) {
            try {
                NFPrivilegeBasedNetworkAccess.invokePrivilegedMethod(this, this.getClass().getMethod("myRun", (Class<?>[])new Class[0]), new Object[0]);
            }
            catch (Exception ex) {
                System.out.println("Exception during myRun call in NFNDSBean: " + ex);
                ex.printStackTrace();
            }
        }
        else {
            this.myRun();
        }
    }
    
    public void myRun() {
        NFDebug.print(16384L, "NFNDSBean: Thread started = " + Thread.currentThread());
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
        do {
            if (this.thread != null && !this.automaticUpdates && this.clientWait && this.tuRate.getSeconds() > 0.0) {
                try {
                    Thread.sleep((long)(this.tuRate.getSeconds() * 1000.0));
                }
                catch (Exception ex4) {}
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
                    this.open(url);
                }
                catch (Exception ex2) {
                    final String string3 = "Unable to connect to server\n ERROR: " + ex2.getMessage();
                    this.statusMsg(string3);
                    this.debug("EXCEPTION from open. e=" + ex2);
                    this.observer.dataBeanFailure(this, string3);
                }
                try {
                    this.processStream();
                }
                catch (Exception ex3) {
                    final String string4 = "Lost connection to server\n ERROR: " + ex3.getMessage();
                    this.statusMsg(string4);
                    this.debug("EXCEPTION from processStream. e=" + ex3);
                    this.observer.dataBeanFailure(this, string4);
                }
                try {
                    this.disconnect();
                }
                catch (Exception ex5) {}
            }
        } while (this.thread != null && !this.automaticUpdates && this.clientWait && this.tuRate.getSeconds() > 0.0);
        NFDebug.print(16384L, "NFNDSBean: Thread stopped = " + Thread.currentThread());
    }
    
    public URL createURL() throws Exception {
        final StringBuffer sb = new StringBuffer();
        final String resolvePath = this.resolvePath(this.nds);
        if (!resolvePath.toLowerCase().startsWith("http://")) {
            throw new Exception("Open called with no server specified.");
        }
        sb.append(resolvePath);
        sb.append("?rate=");
        if (this.automaticUpdates) {
            sb.append("-1");
        }
        else if (this.clientWait) {
            sb.append("0");
        }
        else {
            sb.append((int)this.tuRate.getSeconds());
        }
        if (!super.exprParam.equalsIgnoreCase("NDS")) {
            sb.append("&param=");
            sb.append(super.exprParam);
        }
        sb.append("&output=data&protocol=raw");
        final Hashtable dataBeanGetExtraParams = this.observer.dataBeanGetExtraParams(this);
        final Enumeration<String> enumeration = (dataBeanGetExtraParams == null) ? null : dataBeanGetExtraParams.keys();
        while (enumeration != null && enumeration.hasMoreElements()) {
            final String s = enumeration.nextElement();
            if (this.params.containsKey(s)) {
                continue;
            }
            final String s2 = dataBeanGetExtraParams.get(s);
            this.debug("name=" + s + " value=" + s2);
            sb.append("&" + URLEncoder.encode(s) + "=" + URLEncoder.encode(s2));
        }
        final Enumeration<String> keys = (Enumeration<String>)this.params.keys();
        while (keys.hasMoreElements()) {
            final String s3 = keys.nextElement();
            final String s4 = this.params.get(s3);
            this.debug("name=" + s3 + " value=" + s4);
            sb.append("&" + URLEncoder.encode(s3) + "=" + URLEncoder.encode(s4));
        }
        final StringBuffer sb2 = new StringBuffer();
        int n = 0;
        final Enumeration<String> keys2 = (Enumeration<String>)this.variables.keys();
        while (keys2.hasMoreElements()) {
            final String s5 = keys2.nextElement();
            final String s6 = this.variables.get(s5);
            this.debug("variable name=" + s5 + " value=" + s6);
            if (n != 0) {
                sb2.append("&");
            }
            sb2.append(URLEncoder.encode(s5) + "=" + URLEncoder.encode(s6));
            n = 1;
        }
        if (sb2.length() > 0) {
            sb.append("&vars=" + URLEncoder.encode(sb2.toString()));
        }
        final URL url = new URL(sb.toString());
        this.debug("trying url=" + url.toExternalForm());
        return url;
    }
    
    public void open(final URL url) throws Exception {
        if (NFContext.getUserAgentType() == 0) {
            final URLConnection openConnection = url.openConnection();
            openConnection.setUseCaches(false);
            this.is = openConnection.getInputStream();
        }
        else {
            this.is = new NFHttpClient(super.ctxt).getInputStream(url, true);
        }
    }
    
    public void processStream() throws Exception {
        this.ris = new ObjectInputStream(this.is);
        while (true) {
            if (this.is.available() > 1) {
                final Object object = this.ris.readObject();
                if (object == null) {
                    break;
                }
                final String name = ((String)object).getClass().getName();
                String s;
                if (object instanceof String) {
                    s = (String)object;
                }
                else {
                    if (!name.equals("[B")) {
                        throw new Exception("Read Unsupported Object.  Object must be String or byte[].");
                    }
                    s = new String((byte[])object);
                }
                if (s.startsWith("STATUS ")) {
                    this.statusMsg(s.substring(7));
                }
                else {
                    if (s.startsWith("CLOSE ")) {
                        this.statusMsg(s.substring(6));
                        break;
                    }
                    this.handleReply(s);
                    if (!this.automaticUpdates && (this.clientWait || this.tuRate.getSeconds() == 0.0)) {
                        break;
                    }
                    continue;
                }
            }
            else {
                try {
                    Thread.sleep(500L);
                }
                catch (Exception ex) {
                    this.debug("thread interrupted during sleep waiting on available.");
                }
            }
        }
        this.ris.close();
    }
    
    protected void handleReply(String s) throws Exception {
        if (s == null) {
            return;
        }
        if (this.automaticUpdates || this.tuRate.getSeconds() > 0.0) {
            if (!s.trim().endsWith(";")) {
                s += ";";
            }
            s += "\nUpdate;";
        }
        this.observer.dataBeanLoadParams(this, this.info, s);
    }
    
    protected void statusMsg(final String s) {
        final StringBuffer sb = new StringBuffer();
        sb.append("   NDS: ");
        try {
            sb.append(this.resolvePath(this.nds));
        }
        catch (Exception ex) {
            sb.append(this.nds);
        }
        sb.append("\nSTATUS: ");
        int n = 0;
        for (int i = s.indexOf("<br>", n); i != -1; i = s.indexOf("<br>", n)) {
            sb.append(s.substring(n, i));
            sb.append("\n        ");
            n = i + 4;
        }
        sb.append(s.substring(n));
        this.statusMsg("NDS Status", sb.toString());
    }
    
    public void disconnect() {
        this.debug("closing server connection: ");
        try {
            this.ris.close();
        }
        catch (Exception ex) {
            this.debug("exception closing ris: " + ex);
        }
        finally {
            this.ris = null;
        }
        try {
            this.is.close();
        }
        catch (Exception ex2) {
            this.debug("exception closing is: " + ex2);
        }
        finally {
            this.is = null;
        }
    }
    
    protected void parseRate(String s) {
        if (NFContext.getUserAgentType() == 3) {
            s = "0d";
        }
        if (s == null) {
            this.debug("Null string in parseRate");
            return;
        }
        if (s.equalsIgnoreCase("AUTO")) {
            s = "0d";
        }
        try {
            this.tuRate.parse(s);
        }
        catch (Exception ex) {
            this.debug("Malformed TimeUnit Expression for NDS Update Rate" + ex.toString());
        }
    }
    
    protected void parseServer(final Vector vector) {
        if (vector == null || vector.size() == 0) {
            this.debug("null or empty vector in parseServer");
            return;
        }
        try {
            this.serverName = vector.elementAt(0);
            this.serverPort = (Number)vector.elementAt(1);
        }
        catch (Exception ex) {
            this.debug("Exception parsing Server string " + ex.toString());
        }
    }
    
    protected void parseParams(final Vector vector) {
        if (vector == null || vector.size() == 0) {
            this.debug("null or empty vector in parseParams");
            return;
        }
        this.params.clear();
        for (int i = 0; i < vector.size(); ++i) {
            this.parseParam(vector.elementAt(i));
        }
    }
    
    protected void parseParam(final Vector vector) {
        if (vector == null || vector.size() == 0) {
            this.debug("null or empty vector in parseParam");
            return;
        }
        if (vector.size() != 2) {
            this.debug("Param missing name or value or both.");
            return;
        }
        final String s = vector.elementAt(0);
        String s2 = vector.elementAt(1);
        if (s2 == null) {
            s2 = "null";
        }
        if (s != null) {
            this.params.put(s, s2);
            this.debug("putting name=" + s + " value=" + s2);
        }
    }
    
    protected void parseVariables(final Vector vector) {
        if (vector == null || vector.size() == 0) {
            this.debug("null or empty vector in parseVariables");
            return;
        }
        this.variables.clear();
        for (int i = 0; i < vector.size(); ++i) {
            this.parseVariable(vector.elementAt(i));
        }
    }
    
    protected void parseVariable(final Vector vector) {
        if (vector == null || vector.size() == 0) {
            this.debug("null or empty vector in parseVariable");
            return;
        }
        if (vector.size() != 2) {
            this.debug("Variable missing name or value or both.");
            return;
        }
        final String s = vector.elementAt(0);
        String s2 = vector.elementAt(1);
        if (s2 == null) {
            s2 = "null";
        }
        if (s != null) {
            this.variables.put(s, s2);
            this.debug("putting variable name=" + s + " value=" + s2);
        }
    }
    
    protected String getServerReply(final URL url) throws Exception {
        return new String((byte[])Class.forName("chartworks.es.util.ServerReply").getMethod("getContentObject", (Class<?>[])null).invoke(Class.forName("chartworks.es.server.ServerReplyUtil").getMethod("getServerReply", (NFNDSBean.a == null) ? (NFNDSBean.a = class$("java.lang.String")) : NFNDSBean.a, (NFNDSBean.a == null) ? (NFNDSBean.a = class$("java.lang.String")) : NFNDSBean.a).invoke(null, url.getFile(), NFContext.getUserAgent()), (Object[])null));
    }
    
    static /* synthetic */ Class class$(final String s) {
        try {
            return Class.forName(s);
        }
        catch (ClassNotFoundException ex) {
            throw new NoClassDefFoundError(ex.getMessage());
        }
    }
    
    static {
        NFNDSBean.lps = new Hashtable();
    }
}
