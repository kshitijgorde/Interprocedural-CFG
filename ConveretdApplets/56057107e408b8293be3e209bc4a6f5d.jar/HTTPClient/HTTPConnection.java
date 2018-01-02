// 
// Decompiled by Procyon v0.5.30
// 

package HTTPClient;

import java.net.SocketException;
import java.io.InterruptedIOException;
import java.io.OutputStream;
import java.net.Socket;
import java.io.IOException;
import java.net.UnknownHostException;
import java.net.InetAddress;
import java.net.URL;
import java.util.Vector;

public class HTTPConnection implements GlobalConstants, HTTPClientModuleConstants
{
    public static final String version = "RPT-HTTPClient/0.4.1-dev";
    private static final Object dflt_context;
    private Object Context;
    private int Protocol;
    int ServerProtocolVersion;
    boolean ServProtVersKnown;
    private String RequestProtocolVersion;
    private static boolean force_1_0;
    private String Host;
    private int Port;
    private String Proxy_Host;
    private int Proxy_Port;
    private static String Default_Proxy_Host;
    private static int Default_Proxy_Port;
    private static CIHashtable non_proxy_host_list;
    private static Vector non_proxy_dom_list;
    private static Vector non_proxy_addr_list;
    private static Vector non_proxy_mask_list;
    private String Tunnel_Host;
    private int Tunnel_Port;
    private static String Default_Tunnel_Host;
    private static int Default_Tunnel_Port;
    private SocksClient Socks_client;
    private static SocksClient Default_Socks_client;
    private StreamDemultiplexor input_demux;
    LinkedList DemuxList;
    private LinkedList RequestList;
    private boolean DoesKeepAlive;
    private boolean KeepAliveUnknown;
    private int KeepAliveReqMax;
    private int KeepAliveReqLeft;
    private static boolean NeverPipeline;
    private static boolean disable_nagle;
    private static int DefaultTimeout;
    private int Timeout;
    private NVPair[] DefaultHeaders;
    private static Vector DefaultModuleList;
    private Vector ModuleList;
    private static boolean DefaultAllowUI;
    private boolean AllowUI;
    private Response early_stall;
    private Response late_stall;
    private Response prev_resp;
    private boolean output_finished;
    
    public HTTPConnection(final String s) {
        this.Context = null;
        this.Proxy_Host = null;
        this.Tunnel_Host = null;
        this.Socks_client = null;
        this.input_demux = null;
        this.DemuxList = new LinkedList();
        this.RequestList = new LinkedList();
        this.DoesKeepAlive = false;
        this.KeepAliveUnknown = true;
        this.KeepAliveReqMax = -1;
        this.DefaultHeaders = new NVPair[0];
        this.early_stall = null;
        this.late_stall = null;
        this.prev_resp = null;
        this.output_finished = true;
        this.Setup(0, s, 80);
    }
    
    public HTTPConnection(final String s, final int n) {
        this.Context = null;
        this.Proxy_Host = null;
        this.Tunnel_Host = null;
        this.Socks_client = null;
        this.input_demux = null;
        this.DemuxList = new LinkedList();
        this.RequestList = new LinkedList();
        this.DoesKeepAlive = false;
        this.KeepAliveUnknown = true;
        this.KeepAliveReqMax = -1;
        this.DefaultHeaders = new NVPair[0];
        this.early_stall = null;
        this.late_stall = null;
        this.prev_resp = null;
        this.output_finished = true;
        this.Setup(0, s, n);
    }
    
    public HTTPConnection(String trim, final String s, final int n) throws ProtocolNotSuppException {
        this.Context = null;
        this.Proxy_Host = null;
        this.Tunnel_Host = null;
        this.Socks_client = null;
        this.input_demux = null;
        this.DemuxList = new LinkedList();
        this.RequestList = new LinkedList();
        this.DoesKeepAlive = false;
        this.KeepAliveUnknown = true;
        this.KeepAliveReqMax = -1;
        this.DefaultHeaders = new NVPair[0];
        this.early_stall = null;
        this.late_stall = null;
        this.prev_resp = null;
        this.output_finished = true;
        trim = trim.trim();
        if (!trim.equalsIgnoreCase("http")) {
            throw new ProtocolNotSuppException("Unsupported protocol '" + trim + "'");
        }
        if (trim.equalsIgnoreCase("http")) {
            this.Setup(0, s, n);
        }
        else if (trim.equalsIgnoreCase("https")) {
            this.Setup(1, s, n);
        }
        else if (trim.equalsIgnoreCase("shttp")) {
            this.Setup(2, s, n);
        }
        else if (trim.equalsIgnoreCase("http-ng")) {
            this.Setup(3, s, n);
        }
    }
    
    public HTTPConnection(final URL url) throws ProtocolNotSuppException {
        this(url.getProtocol(), url.getHost(), url.getPort());
    }
    
    public HTTPConnection(final URI uri) throws ProtocolNotSuppException {
        this(uri.getScheme(), uri.getHost(), uri.getPort());
    }
    
    private void Setup(final int protocol, final String s, final int port) {
        this.Protocol = protocol;
        this.Host = s.trim().toLowerCase();
        this.Port = port;
        if (this.Port == -1) {
            this.Port = URI.defaultPort(this.getProtocol());
        }
        if (HTTPConnection.Default_Proxy_Host != null && !this.matchNonProxy(s)) {
            this.setCurrentProxy(HTTPConnection.Default_Proxy_Host, HTTPConnection.Default_Proxy_Port);
        }
        else {
            this.setCurrentProxy(null, 0);
        }
        this.Socks_client = HTTPConnection.Default_Socks_client;
        this.Tunnel_Host = HTTPConnection.Default_Tunnel_Host;
        this.Tunnel_Port = HTTPConnection.Default_Tunnel_Port;
        this.Timeout = HTTPConnection.DefaultTimeout;
        this.ModuleList = (Vector)HTTPConnection.DefaultModuleList.clone();
        this.AllowUI = HTTPConnection.DefaultAllowUI;
    }
    
    private boolean matchNonProxy(final String s) {
        if (HTTPConnection.non_proxy_host_list.get(s) != null) {
            return true;
        }
        for (int i = 0; i < HTTPConnection.non_proxy_dom_list.size(); ++i) {
            if (s.endsWith((String)HTTPConnection.non_proxy_dom_list.elementAt(i))) {
                return true;
            }
        }
        if (HTTPConnection.non_proxy_addr_list.size() == 0) {
            return false;
        }
        InetAddress[] allByName;
        try {
            allByName = InetAddress.getAllByName(s);
        }
        catch (UnknownHostException ex) {
            return false;
        }
        for (int j = 0; j < HTTPConnection.non_proxy_addr_list.size(); ++j) {
            final byte[] array = HTTPConnection.non_proxy_addr_list.elementAt(j);
            final byte[] array2 = HTTPConnection.non_proxy_mask_list.elementAt(j);
        Label_0178:
            for (int k = 0; k < allByName.length; ++k) {
                final byte[] address = allByName[k].getAddress();
                if (address.length == array.length) {
                    for (int l = 0; l < address.length; ++l) {
                        if ((address[l] & array2[l]) != (array[l] & array2[l])) {
                            continue Label_0178;
                        }
                    }
                    return true;
                }
            }
        }
        return false;
    }
    
    public HTTPResponse Head(final String s) throws IOException, ModuleException {
        return this.Head(s, (String)null, null);
    }
    
    public HTTPResponse Head(final String s, final NVPair[] array) throws IOException, ModuleException {
        return this.Head(s, array, null);
    }
    
    public HTTPResponse Head(final String s, final NVPair[] array, final NVPair[] array2) throws IOException, ModuleException {
        String s2 = this.stripRef(s);
        final String nv2query = Codecs.nv2query(array);
        if (nv2query != null && nv2query.length() > 0) {
            s2 = s2 + "?" + nv2query;
        }
        return this.setupRequest("HEAD", s2, array2, null, null);
    }
    
    public HTTPResponse Head(final String s, final String s2) throws IOException, ModuleException {
        return this.Head(s, s2, null);
    }
    
    public HTTPResponse Head(final String s, final String s2, final NVPair[] array) throws IOException, ModuleException {
        String s3 = this.stripRef(s);
        if (s2 != null && s2.length() > 0) {
            s3 = s3 + "?" + Codecs.URLEncode(s2);
        }
        return this.setupRequest("HEAD", s3, array, null, null);
    }
    
    public HTTPResponse Get(final String s) throws IOException, ModuleException {
        return this.Get(s, (String)null, null);
    }
    
    public HTTPResponse Get(final String s, final NVPair[] array) throws IOException, ModuleException {
        return this.Get(s, array, null);
    }
    
    public HTTPResponse Get(final String s, final NVPair[] array, final NVPair[] array2) throws IOException, ModuleException {
        String s2 = this.stripRef(s);
        final String nv2query = Codecs.nv2query(array);
        if (nv2query != null && nv2query.length() > 0) {
            s2 = s2 + "?" + nv2query;
        }
        return this.setupRequest("GET", s2, array2, null, null);
    }
    
    public HTTPResponse Get(final String s, final String s2) throws IOException, ModuleException {
        return this.Get(s, s2, null);
    }
    
    public HTTPResponse Get(final String s, final String s2, final NVPair[] array) throws IOException, ModuleException {
        String s3 = this.stripRef(s);
        if (s2 != null && s2.length() > 0) {
            s3 = s3 + "?" + Codecs.URLEncode(s2);
        }
        return this.setupRequest("GET", s3, array, null, null);
    }
    
    public HTTPResponse Post(final String s) throws IOException, ModuleException {
        return this.Post(s, (byte[])null, null);
    }
    
    public HTTPResponse Post(final String s, final NVPair[] array) throws IOException, ModuleException {
        return this.Post(s, Codecs.nv2query(array), new NVPair[] { new NVPair("Content-type", "application/x-www-form-urlencoded") });
    }
    
    public HTTPResponse Post(final String s, final NVPair[] array, NVPair[] addValue) throws IOException, ModuleException {
        if (Util.getIndex(addValue, "Content-Type") == -1) {
            addValue = Util.addValue(addValue, "Content-type", "application/x-www-form-urlencoded");
        }
        return this.Post(s, Codecs.nv2query(array), addValue);
    }
    
    public HTTPResponse Post(final String s, final String s2) throws IOException, ModuleException {
        return this.Post(s, s2, null);
    }
    
    public HTTPResponse Post(final String s, final String s2, final NVPair[] array) throws IOException, ModuleException {
        byte[] bytes = null;
        if (s2 != null && s2.length() > 0) {
            bytes = s2.getBytes();
        }
        return this.Post(s, bytes, array);
    }
    
    public HTTPResponse Post(final String s, final byte[] array) throws IOException, ModuleException {
        return this.Post(s, array, null);
    }
    
    public HTTPResponse Post(final String s, byte[] array, final NVPair[] array2) throws IOException, ModuleException {
        if (array == null) {
            array = new byte[0];
        }
        return this.setupRequest("POST", this.stripRef(s), array2, array, null);
    }
    
    public HTTPResponse Post(final String s, final HttpOutputStream httpOutputStream) throws IOException, ModuleException {
        return this.Post(s, httpOutputStream, null);
    }
    
    public HTTPResponse Post(final String s, final HttpOutputStream httpOutputStream, final NVPair[] array) throws IOException, ModuleException {
        return this.setupRequest("POST", this.stripRef(s), array, null, httpOutputStream);
    }
    
    public HTTPResponse Put(final String s, final String s2) throws IOException, ModuleException {
        return this.Put(s, s2, null);
    }
    
    public HTTPResponse Put(final String s, final String s2, final NVPair[] array) throws IOException, ModuleException {
        byte[] bytes = null;
        if (s2 != null) {
            bytes = s2.getBytes();
        }
        return this.Put(s, bytes, array);
    }
    
    public HTTPResponse Put(final String s, final byte[] array) throws IOException, ModuleException {
        return this.Put(s, array, null);
    }
    
    public HTTPResponse Put(final String s, byte[] array, final NVPair[] array2) throws IOException, ModuleException {
        if (array == null) {
            array = new byte[0];
        }
        return this.setupRequest("PUT", this.stripRef(s), array2, array, null);
    }
    
    public HTTPResponse Put(final String s, final HttpOutputStream httpOutputStream) throws IOException, ModuleException {
        return this.Put(s, httpOutputStream, null);
    }
    
    public HTTPResponse Put(final String s, final HttpOutputStream httpOutputStream, final NVPair[] array) throws IOException, ModuleException {
        return this.setupRequest("PUT", this.stripRef(s), array, null, httpOutputStream);
    }
    
    public HTTPResponse Options(final String s) throws IOException, ModuleException {
        return this.Options(s, null, (byte[])null);
    }
    
    public HTTPResponse Options(final String s, final NVPair[] array) throws IOException, ModuleException {
        return this.Options(s, array, (byte[])null);
    }
    
    public HTTPResponse Options(final String s, final NVPair[] array, final byte[] array2) throws IOException, ModuleException {
        return this.setupRequest("OPTIONS", this.stripRef(s), array, array2, null);
    }
    
    public HTTPResponse Options(final String s, final NVPair[] array, final HttpOutputStream httpOutputStream) throws IOException, ModuleException {
        return this.setupRequest("OPTIONS", this.stripRef(s), array, null, httpOutputStream);
    }
    
    public HTTPResponse Delete(final String s) throws IOException, ModuleException {
        return this.Delete(s, null);
    }
    
    public HTTPResponse Delete(final String s, final NVPair[] array) throws IOException, ModuleException {
        return this.setupRequest("DELETE", this.stripRef(s), array, null, null);
    }
    
    public HTTPResponse Trace(final String s, final NVPair[] array) throws IOException, ModuleException {
        return this.setupRequest("TRACE", this.stripRef(s), array, null, null);
    }
    
    public HTTPResponse Trace(final String s) throws IOException, ModuleException {
        return this.Trace(s, null);
    }
    
    public Socket Connect() throws IOException, ModuleException, ThreadInterruptedIOException {
        Socket socket = null;
        synchronized (this) {
            if (this.Proxy_Host == null) {
                socket = this.getSocket(this.Timeout);
            }
            else {
                final StreamDemultiplexor input_demux = this.input_demux;
                this.input_demux = null;
                try {
                    final HTTPResponse setupRequest = this.setupRequest("CONNECT", this.Host + ":" + this.Port, null, null, null);
                    if (setupRequest.getStatusCode() == 200) {
                        socket = this.input_demux.releaseSocket();
                    }
                    else {
                        String reasonLine = "";
                        try {
                            reasonLine = setupRequest.getReasonLine();
                        }
                        catch (Exception ex) {}
                        final String string = "HTTPClient: Connect: CONNECT was not successful. HTTP status: " + setupRequest.getStatusCode() + " reason: " + reasonLine;
                        if (setupRequest.getStatusCode() == 403) {
                            throw new ForbiddenIOException(string);
                        }
                        throw new IOException(string);
                    }
                }
                finally {
                    if (this.input_demux != null) {
                        this.input_demux.releaseHttpConnectResources();
                    }
                    this.input_demux = input_demux;
                }
            }
        }
        if (socket == null) {
            throw new IOException("HTTPClient: Connect: Internal error - could not get the Socket.");
        }
        return socket;
    }
    
    public HTTPResponse ExtensionMethod(final String s, final String s2, final byte[] array, final NVPair[] array2) throws IOException, ModuleException {
        return this.setupRequest(s.trim(), this.stripRef(s2), array2, array, null);
    }
    
    public HTTPResponse ExtensionMethod(final String s, final String s2, final HttpOutputStream httpOutputStream, final NVPair[] array) throws IOException, ModuleException {
        return this.setupRequest(s.trim(), this.stripRef(s2), array, null, httpOutputStream);
    }
    
    public void stop() {
        for (Request request = (Request)this.RequestList.enumerate(); request != null; request = (Request)this.RequestList.next()) {
            request.aborted = true;
        }
        for (StreamDemultiplexor streamDemultiplexor = (StreamDemultiplexor)this.DemuxList.enumerate(); streamDemultiplexor != null; streamDemultiplexor = (StreamDemultiplexor)this.DemuxList.next()) {
            streamDemultiplexor.abort();
        }
    }
    
    public void setDefaultHeaders(final NVPair[] array) {
        final int n = (array == null) ? 0 : array.length;
        this.DefaultHeaders = new NVPair[n];
        int i = 0;
        int n2 = 0;
        while (i < n) {
            final String trim = array[i].getName().trim();
            if (!trim.equalsIgnoreCase("Content-length")) {
                if (!trim.equalsIgnoreCase("Host")) {
                    this.DefaultHeaders[n2++] = array[i];
                }
            }
            ++i;
        }
        if (n2 < n) {
            this.DefaultHeaders = Util.resizeArray(this.DefaultHeaders, n2);
        }
    }
    
    public NVPair[] getDefaultHeaders() {
        final NVPair[] array = new NVPair[this.DefaultHeaders.length];
        System.arraycopy(this.DefaultHeaders, 0, array, 0, array.length);
        return array;
    }
    
    public String getProtocol() {
        switch (this.Protocol) {
            case 0: {
                return "http";
            }
            case 1: {
                return "https";
            }
            case 2: {
                return "shttp";
            }
            case 3: {
                return "http-ng";
            }
            default: {
                throw new Error("HTTPClient Internal Error: invalid protocol " + this.Protocol);
            }
        }
    }
    
    public String getHost() {
        return this.Host;
    }
    
    public int getPort() {
        return this.Port;
    }
    
    public String getProxyHost() {
        return this.Proxy_Host;
    }
    
    public int getProxyPort() {
        return this.Proxy_Port;
    }
    
    public void setRawMode(final boolean b) {
        final String[] array = { "HTTPClient.CookieModule", "HTTPClient.RedirectionModule", "HTTPClient.AuthorizationModule", "HTTPClient.DefaultModule", "HTTPClient.TransferEncodingModule", "HTTPClient.ContentMD5Module", "HTTPClient.ContentEncodingModule" };
        for (int i = 0; i < array.length; ++i) {
            try {
                if (b) {
                    this.removeModule(Class.forName(array[i]));
                }
                else {
                    this.addModule(Class.forName(array[i]), -1);
                }
            }
            catch (ClassNotFoundException ex) {}
        }
    }
    
    public static void setDefaultTimeout(final int defaultTimeout) {
        HTTPConnection.DefaultTimeout = defaultTimeout;
    }
    
    public static int getDefaultTimeout() {
        return HTTPConnection.DefaultTimeout;
    }
    
    public void setTimeout(final int timeout) {
        this.Timeout = timeout;
    }
    
    public int getTimeout() {
        return this.Timeout;
    }
    
    public void setAllowUserInteraction(final boolean allowUI) {
        this.AllowUI = allowUI;
    }
    
    public boolean getAllowUserInteraction() {
        return this.AllowUI;
    }
    
    public static void setDefaultAllowUserInteraction(final boolean defaultAllowUI) {
        HTTPConnection.DefaultAllowUI = defaultAllowUI;
    }
    
    public static boolean getDefaultAllowUserInteraction() {
        return HTTPConnection.DefaultAllowUI;
    }
    
    public static Class[] getDefaultModules() {
        synchronized (HTTPConnection.DefaultModuleList) {
            final Class[] array = new Class[HTTPConnection.DefaultModuleList.size()];
            HTTPConnection.DefaultModuleList.copyInto(array);
            return array;
        }
    }
    
    public static boolean addDefaultModule(final Class clazz, final int n) {
        try {
            final HTTPClientModule httpClientModule = clazz.newInstance();
        }
        catch (RuntimeException ex) {
            throw ex;
        }
        catch (Exception ex2) {
            throw new RuntimeException(ex2.toString());
        }
        synchronized (HTTPConnection.DefaultModuleList) {
            if (HTTPConnection.DefaultModuleList.contains(clazz)) {
                return false;
            }
            if (n < 0) {
                HTTPConnection.DefaultModuleList.insertElementAt(clazz, HTTPConnection.DefaultModuleList.size() + n + 1);
            }
            else {
                HTTPConnection.DefaultModuleList.insertElementAt(clazz, n);
            }
        }
        if (GlobalConstants.DebugConn) {
            Util.logLine("Conn:  Added module " + clazz.getName() + " to default list");
        }
        return true;
    }
    
    public static boolean removeDefaultModule(final Class clazz) {
        final boolean removeElement = HTTPConnection.DefaultModuleList.removeElement(clazz);
        if (GlobalConstants.DebugConn && removeElement) {
            Util.logLine("Conn:  Removed module " + clazz.getName() + " from default list");
        }
        return removeElement;
    }
    
    public Class[] getModules() {
        synchronized (this.ModuleList) {
            final Class[] array = new Class[this.ModuleList.size()];
            this.ModuleList.copyInto(array);
            return array;
        }
    }
    
    public boolean addModule(final Class clazz, final int n) {
        try {
            final HTTPClientModule httpClientModule = clazz.newInstance();
        }
        catch (RuntimeException ex) {
            throw ex;
        }
        catch (Exception ex2) {
            throw new RuntimeException(ex2.toString());
        }
        synchronized (this.ModuleList) {
            if (this.ModuleList.contains(clazz)) {
                return false;
            }
            if (n < 0) {
                this.ModuleList.insertElementAt(clazz, this.ModuleList.size() + n + 1);
            }
            else {
                this.ModuleList.insertElementAt(clazz, n);
            }
        }
        return true;
    }
    
    public boolean removeModule(final Class clazz) {
        return clazz != null && this.ModuleList.removeElement(clazz);
    }
    
    public void setContext(final Object context) {
        if (context == null) {
            throw new IllegalArgumentException("Context must be non-null");
        }
        if (this.Context != null) {
            throw new RuntimeException("Context already set");
        }
        this.Context = context;
    }
    
    public Object getContext() {
        if (this.Context != null) {
            return this.Context;
        }
        return HTTPConnection.dflt_context;
    }
    
    static Object getDefaultContext() {
        return HTTPConnection.dflt_context;
    }
    
    public void addDigestAuthorization(final String s, final String s2, final String s3) throws AuthSchemeNotImplException {
        AuthorizationInfo.addDigestAuthorization(this.Host, this.Port, s, s2, s3, this.getContext());
    }
    
    public void addBasicAuthorization(final String s, final String s2, final String s3) throws AuthSchemeNotImplException {
        AuthorizationInfo.addBasicAuthorization(this.Host, this.Port, s, s2, s3, this.getContext());
    }
    
    public static void setProxyServer(final String s, final int default_Proxy_Port) {
        if (s == null || s.trim().length() == 0) {
            HTTPConnection.Default_Proxy_Host = null;
        }
        else {
            HTTPConnection.Default_Proxy_Host = s.trim().toLowerCase();
            HTTPConnection.Default_Proxy_Port = default_Proxy_Port;
        }
    }
    
    public synchronized void setCurrentProxy(final String s, final int proxy_Port) {
        if (s == null || s.trim().length() == 0) {
            this.Proxy_Host = null;
        }
        else {
            this.Proxy_Host = s.trim().toLowerCase();
            if (proxy_Port <= 0) {
                this.Proxy_Port = 80;
            }
            else {
                this.Proxy_Port = proxy_Port;
            }
        }
        switch (this.Protocol) {
            case 0:
            case 1: {
                if (HTTPConnection.force_1_0) {
                    this.ServerProtocolVersion = 65536;
                    this.ServProtVersKnown = true;
                    this.RequestProtocolVersion = "HTTP/1.0";
                    break;
                }
                this.ServerProtocolVersion = 65537;
                this.ServProtVersKnown = false;
                this.RequestProtocolVersion = "HTTP/1.1";
                break;
            }
            case 3: {
                this.ServerProtocolVersion = -1;
                this.ServProtVersKnown = false;
                this.RequestProtocolVersion = "";
                break;
            }
            case 2: {
                this.ServerProtocolVersion = -1;
                this.ServProtVersKnown = false;
                this.RequestProtocolVersion = "Secure-HTTP/1.3";
                break;
            }
            default: {
                throw new Error("HTTPClient Internal Error: invalid protocol " + this.Protocol);
            }
        }
        this.KeepAliveUnknown = true;
        this.DoesKeepAlive = false;
        this.input_demux = null;
        this.early_stall = null;
        this.late_stall = null;
        this.prev_resp = null;
    }
    
    public static void dontProxyFor(String lowerCase) throws ParseException {
        lowerCase = lowerCase.trim().toLowerCase();
        if (lowerCase.charAt(0) == '.') {
            if (!HTTPConnection.non_proxy_dom_list.contains(lowerCase)) {
                HTTPConnection.non_proxy_dom_list.addElement(lowerCase);
            }
            return;
        }
        for (int i = 0; i < lowerCase.length(); ++i) {
            if (!Character.isDigit(lowerCase.charAt(i)) && lowerCase.charAt(i) != '.' && lowerCase.charAt(i) != '/') {
                HTTPConnection.non_proxy_host_list.put(lowerCase, "");
                return;
            }
        }
        final int index;
        byte[] array;
        byte[] string2arr;
        if ((index = lowerCase.indexOf(47)) != -1) {
            array = string2arr(lowerCase.substring(0, index));
            string2arr = string2arr(lowerCase.substring(index + 1));
            if (array.length != string2arr.length) {
                throw new ParseException("length of IP-address (" + array.length + ") != length of netmask (" + string2arr.length + ")");
            }
        }
        else {
            array = string2arr(lowerCase);
            string2arr = new byte[array.length];
            for (int j = 0; j < string2arr.length; ++j) {
                string2arr[j] = -1;
            }
        }
    Label_0311:
        for (int k = 0; k < HTTPConnection.non_proxy_addr_list.size(); ++k) {
            final byte[] array2 = HTTPConnection.non_proxy_addr_list.elementAt(k);
            final byte[] array3 = HTTPConnection.non_proxy_mask_list.elementAt(k);
            if (array2.length == array.length) {
                for (int l = 0; l < array2.length; ++l) {
                    if ((array[l] & array3[l]) != (array2[l] & array3[l])) {
                        continue Label_0311;
                    }
                    if (array3[l] != string2arr[l]) {
                        continue Label_0311;
                    }
                }
                return;
            }
        }
        HTTPConnection.non_proxy_addr_list.addElement(array);
        HTTPConnection.non_proxy_mask_list.addElement(string2arr);
    }
    
    public static boolean doProxyFor(String lowerCase) throws ParseException {
        lowerCase = lowerCase.trim().toLowerCase();
        if (lowerCase.charAt(0) == '.') {
            return HTTPConnection.non_proxy_dom_list.removeElement(lowerCase);
        }
        for (int i = 0; i < lowerCase.length(); ++i) {
            if (!Character.isDigit(lowerCase.charAt(i)) && lowerCase.charAt(i) != '.' && lowerCase.charAt(i) != '/') {
                return HTTPConnection.non_proxy_host_list.remove(lowerCase) != null;
            }
        }
        final int index;
        byte[] array;
        byte[] string2arr;
        if ((index = lowerCase.indexOf(47)) != -1) {
            array = string2arr(lowerCase.substring(0, index));
            string2arr = string2arr(lowerCase.substring(index + 1));
            if (array.length != string2arr.length) {
                throw new ParseException("length of IP-address (" + array.length + ") != length of netmask (" + string2arr.length + ")");
            }
        }
        else {
            array = string2arr(lowerCase);
            string2arr = new byte[array.length];
            for (int j = 0; j < string2arr.length; ++j) {
                string2arr[j] = -1;
            }
        }
    Label_0323:
        for (int k = 0; k < HTTPConnection.non_proxy_addr_list.size(); ++k) {
            final byte[] array2 = HTTPConnection.non_proxy_addr_list.elementAt(k);
            final byte[] array3 = HTTPConnection.non_proxy_mask_list.elementAt(k);
            if (array2.length == array.length) {
                for (int l = 0; l < array2.length; ++l) {
                    if ((array[l] & array3[l]) != (array2[l] & array3[l])) {
                        continue Label_0323;
                    }
                    if (array3[l] != string2arr[l]) {
                        continue Label_0323;
                    }
                }
                HTTPConnection.non_proxy_addr_list.removeElementAt(k);
                HTTPConnection.non_proxy_mask_list.removeElementAt(k);
                return true;
            }
        }
        return false;
    }
    
    private static byte[] string2arr(final String s) {
        final char[] array = new char[s.length()];
        s.getChars(0, array.length, array, 0);
        int n = 0;
        for (int i = 0; i < array.length; ++i) {
            if (array[i] == '.') {
                ++n;
            }
        }
        final byte[] array2 = new byte[n + 1];
        int n2 = 0;
        int n3 = 0;
        for (int j = 0; j < array.length; ++j) {
            if (array[j] == '.') {
                array2[n2] = (byte)Integer.parseInt(s.substring(n3, j));
                ++n2;
                n3 = j + 1;
            }
        }
        array2[n2] = (byte)Integer.parseInt(s.substring(n3));
        return array2;
    }
    
    public static void setSocksServer(final String s) {
        setSocksServer(s, 1080);
    }
    
    public static void setSocksServer(final String s, int n) {
        if (n <= 0) {
            n = 1080;
        }
        if (s == null || s.length() == 0) {
            HTTPConnection.Default_Socks_client = null;
        }
        else {
            HTTPConnection.Default_Socks_client = new SocksClient(s, n);
        }
    }
    
    public static void setSocksServer(final String s, int n, final int n2) throws SocksException {
        if (n <= 0) {
            n = 1080;
        }
        if (s == null || s.length() == 0) {
            HTTPConnection.Default_Socks_client = null;
        }
        else {
            HTTPConnection.Default_Socks_client = new SocksClient(s, n, n2);
        }
    }
    
    private final String stripRef(String s) {
        if (s == null) {
            return "/";
        }
        final int index = s.indexOf(35);
        if (index != -1) {
            s = s.substring(0, index).trim();
        }
        else {
            s = s.trim();
        }
        if (s.length() == 0) {
            s = "/";
        }
        return s;
    }
    
    private HTTPResponse setupRequest(final String s, final String s2, final NVPair[] array, final byte[] array2, final HttpOutputStream httpOutputStream) throws IOException, ModuleException {
        final Request request = new Request(this, s, s2, this.mergedHeaders(array), array2, httpOutputStream, this.AllowUI);
        this.RequestList.addToEnd(request);
        try {
            final HTTPResponse httpResponse = new HTTPResponse(this.gen_mod_insts(), this.Timeout, request);
            this.handleRequest(request, httpResponse, null, true);
            return httpResponse;
        }
        finally {
            this.RequestList.remove(request);
        }
    }
    
    private NVPair[] mergedHeaders(final NVPair[] array) {
        final int n = (array != null) ? array.length : 0;
        final int n2 = (this.DefaultHeaders != null) ? this.DefaultHeaders.length : 0;
        NVPair[] resizeArray = new NVPair[n + n2];
        System.arraycopy(this.DefaultHeaders, 0, resizeArray, 0, n2);
        int n3 = n2;
        for (int i = 0; i < n; ++i) {
            final String trim = array[i].getName().trim();
            if (!trim.equalsIgnoreCase("Content-length")) {
                if (!trim.equalsIgnoreCase("Host")) {
                    int n4;
                    for (n4 = 0; n4 < n3 && !resizeArray[n4].getName().trim().equalsIgnoreCase(trim); ++n4) {}
                    resizeArray[n4] = array[i];
                    if (n4 == n3) {
                        ++n3;
                    }
                }
            }
        }
        if (n3 < resizeArray.length) {
            resizeArray = Util.resizeArray(resizeArray, n3);
        }
        return resizeArray;
    }
    
    private HTTPClientModule[] gen_mod_insts() {
        final HTTPClientModule[] array = new HTTPClientModule[this.ModuleList.size()];
        for (int i = 0; i < this.ModuleList.size(); ++i) {
            final Class<HTTPClientModule> clazz = this.ModuleList.elementAt(i);
            try {
                array[i] = clazz.newInstance();
            }
            catch (Exception ex) {
                throw new Error("HTTPClient Internal Error: could not create instance of " + clazz.getName() + " -\n" + ex);
            }
        }
        return array;
    }
    
    void handleRequest(final Request request, final HTTPResponse httpResponse, final Response response, final boolean b) throws IOException, ModuleException {
        final Response[] array = { response };
        final HTTPClientModule[] modules = httpResponse.getModules();
        Label_0303: {
            if (b) {
                for (int i = 0; i < modules.length; ++i) {
                    final int requestHandler = modules[i].requestHandler(request, array);
                    switch (requestHandler) {
                        case 0: {
                            break;
                        }
                        case 1: {
                            i = -1;
                            break;
                        }
                        case 2: {
                            break Label_0303;
                        }
                        case 3:
                        case 4: {
                            if (array[0] == null) {
                                throw new Error("HTTPClient Internal Error: no response returned by module " + modules[i].getClass().getName());
                            }
                            httpResponse.set(request, array[0]);
                            if (request.getStream() != null) {
                                request.getStream().ignoreData(request);
                            }
                            if (request.internal_subrequest) {
                                return;
                            }
                            if (requestHandler == 3) {
                                httpResponse.handleResponse();
                            }
                            else {
                                httpResponse.init(array[0]);
                            }
                            return;
                        }
                        case 5: {
                            if (request.internal_subrequest) {
                                return;
                            }
                            request.getConnection().handleRequest(request, httpResponse, array[0], true);
                            return;
                        }
                        case 6: {
                            if (request.internal_subrequest) {
                                return;
                            }
                            request.getConnection().handleRequest(request, httpResponse, array[0], false);
                            return;
                        }
                        default: {
                            throw new Error("HTTPClient Internal Error: invalid status " + requestHandler + " returned by module " + modules[i].getClass().getName());
                        }
                    }
                }
            }
        }
        if (request.internal_subrequest) {
            return;
        }
        if (request.getStream() != null && request.getStream().getLength() == -1) {
            if (!this.ServProtVersKnown || this.ServerProtocolVersion < 65537) {
                request.getStream().goAhead(request, null, httpResponse.getTimeout());
                httpResponse.set(request, request.getStream());
            }
            else {
                try {
                    request.setHeaders(Util.addToken(request.getHeaders(), "Transfer-Encoding", "chunked"));
                }
                catch (ParseException ex) {
                    throw new IOException(ex.toString());
                }
                httpResponse.set(request, this.sendRequest(request, httpResponse.getTimeout()));
            }
        }
        else {
            httpResponse.set(request, this.sendRequest(request, httpResponse.getTimeout()));
        }
        if (request.aborted) {
            throw new IOException("Request aborted by user");
        }
    }
    
    Response sendRequest(final Request request, final int n) throws IOException, ModuleException {
        final ExtByteArrayOutputStream extByteArrayOutputStream = new ExtByteArrayOutputStream(600);
        Response enableSSLTunneling = null;
        if (this.early_stall != null) {
            try {
                if (GlobalConstants.DebugConn) {
                    Util.logLine("Conn:  Early-stalling Request: " + request.getMethod() + " " + request.getRequestURI());
                }
                synchronized (this.early_stall) {
                    try {
                        this.early_stall.getVersion();
                    }
                    catch (IOException ex5) {}
                    this.early_stall = null;
                }
            }
            catch (NullPointerException ex6) {}
        }
        final String[] assembleHeaders = this.assembleHeaders(request, extByteArrayOutputStream);
        boolean b;
        try {
            b = ((this.ServerProtocolVersion >= 65537 && !Util.hasToken(assembleHeaders[0], "close")) || (this.ServerProtocolVersion == 65536 && Util.hasToken(assembleHeaders[0], "keep-alive")));
        }
        catch (ParseException ex) {
            throw new IOException(ex.toString());
        }
        synchronized (this) {
            if (this.late_stall != null) {
                if (this.input_demux != null || this.KeepAliveUnknown) {
                    if (GlobalConstants.DebugConn) {
                        Util.logLine("Conn:  Stalling Request: " + request.getMethod() + " " + request.getRequestURI());
                    }
                    try {
                        this.late_stall.getVersion();
                        if (this.KeepAliveUnknown) {
                            this.determineKeepAlive(this.late_stall);
                        }
                    }
                    catch (IOException ex7) {}
                }
                this.late_stall = null;
            }
            if (request.getMethod().equals("POST") && this.prev_resp != null && this.input_demux != null) {
                if (GlobalConstants.DebugConn) {
                    Util.logLine("Conn:  Stalling Request: " + request.getMethod() + " " + request.getRequestURI());
                }
                try {
                    this.prev_resp.getVersion();
                }
                catch (IOException ex8) {}
            }
            if (!this.output_finished) {
                try {
                    this.wait();
                }
                catch (InterruptedException ex2) {
                    throw new IOException(ex2.toString());
                }
            }
            if (request.aborted) {
                throw new IOException("Request aborted by user");
            }
            int n2 = 3;
            while (n2-- > 0) {
                try {
                    Socket socket;
                    if (this.input_demux == null || (socket = this.input_demux.getSocket()) == null) {
                        socket = this.getSocket(n);
                        try {
                            if (HTTPConnection.disable_nagle) {
                                socket.setTcpNoDelay(true);
                            }
                        }
                        catch (Throwable t) {}
                        if (this.Protocol == 1) {
                            if (HTTPConnection.Default_Tunnel_Host == null && this.Tunnel_Host != null) {
                                this.Proxy_Host = this.Tunnel_Host;
                            }
                            if (this.Proxy_Host != null) {
                                final Socket[] array = { socket };
                                enableSSLTunneling = this.enableSSLTunneling(array, request, n);
                                if (enableSSLTunneling != null) {
                                    enableSSLTunneling.final_resp = true;
                                    return enableSSLTunneling;
                                }
                                socket = array[0];
                                this.Tunnel_Host = this.Proxy_Host;
                                this.Tunnel_Port = this.Proxy_Port;
                                this.Proxy_Host = null;
                            }
                        }
                        if (this.input_demux != null && this.input_demux.isHttpConnectCompatibilityModeUsed()) {
                            this.input_demux.releaseHttpConnectResources();
                        }
                        this.input_demux = new StreamDemultiplexor(this.Protocol, socket, this, request.getMethod().equals("CONNECT"));
                        this.DemuxList.addToEnd(this.input_demux);
                        this.KeepAliveReqLeft = this.KeepAliveReqMax;
                    }
                    if (request.aborted) {
                        throw new IOException("Request aborted by user");
                    }
                    if (GlobalConstants.DebugConn) {
                        Util.logLine("Conn:  Sending Request: ");
                        Util.logLine();
                        extByteArrayOutputStream.writeTo(System.err);
                    }
                    final OutputStream outputStream = socket.getOutputStream();
                    boolean hasToken;
                    try {
                        hasToken = Util.hasToken(assembleHeaders[1], "100-continue");
                    }
                    catch (ParseException ex3) {
                        throw new IOException(ex3.toString());
                    }
                    if (request.getData() != null && request.getData().length > 0 && request.getData().length < 10000 && request.delay_entity == 0L && (!this.ServProtVersKnown || this.ServerProtocolVersion < 65537 || !hasToken)) {
                        extByteArrayOutputStream.write(request.getData());
                        extByteArrayOutputStream.writeTo(outputStream);
                    }
                    else {
                        extByteArrayOutputStream.writeTo(outputStream);
                        try {
                            if (this.ServProtVersKnown && this.ServerProtocolVersion >= 65537 && hasToken) {
                                enableSSLTunneling = new Response(request, this.Proxy_Host != null && this.Protocol != 1, this.input_demux);
                                enableSSLTunneling.timeout = 60;
                                if (enableSSLTunneling.getContinue() != 100) {
                                    break;
                                }
                            }
                        }
                        catch (InterruptedIOException ex9) {}
                        finally {
                            if (enableSSLTunneling != null) {
                                enableSSLTunneling.timeout = 0;
                            }
                        }
                        if (request.getData() != null && request.getData().length > 0) {
                            if (request.delay_entity > 0L) {
                                final long n3 = request.delay_entity / 100L;
                                final long n4 = request.delay_entity / n3;
                                for (int n5 = 0; n5 < n3 && this.input_demux.available(null) == 0; ++n5) {
                                    try {
                                        Thread.sleep(n4);
                                    }
                                    catch (InterruptedException ex10) {}
                                }
                                if (this.input_demux.available(null) == 0) {
                                    outputStream.write(request.getData());
                                }
                                else {
                                    b = false;
                                }
                            }
                            else {
                                outputStream.write(request.getData());
                            }
                        }
                    }
                    if (request.getStream() != null) {
                        request.getStream().goAhead(request, outputStream, 0);
                    }
                    else {
                        outputStream.flush();
                    }
                    if (enableSSLTunneling == null) {
                        enableSSLTunneling = new Response(request, this.Proxy_Host != null && this.Protocol != 1, this.input_demux);
                    }
                    this.prev_resp = enableSSLTunneling;
                    break;
                }
                catch (IOException ex4) {
                    if (GlobalConstants.DebugConn) {
                        Util.logLine("Conn:  ");
                        Util.logStackTrace(ex4);
                    }
                    this.closeDemux(ex4);
                    if (n2 == 0 || ex4 instanceof UnknownHostException || ex4 instanceof InterruptedIOException || request.aborted) {
                        throw ex4;
                    }
                    if (!GlobalConstants.DebugConn) {
                        continue;
                    }
                    Util.logLine("Conn:  Retrying request");
                }
            }
            if (((!this.KeepAliveUnknown && !this.DoesKeepAlive) || !b || (this.KeepAliveReqMax != -1 && this.KeepAliveReqLeft-- == 0)) && !request.getMethod().equals("CONNECT")) {
                this.input_demux.markForClose(enableSSLTunneling);
                this.input_demux = null;
            }
            else {
                this.input_demux.restartTimer();
            }
            if (GlobalConstants.DebugConn && this.KeepAliveReqMax != -1) {
                Util.logLine("Conn:  Number of requests left: " + this.KeepAliveReqLeft);
            }
            if (!this.ServProtVersKnown) {
                (this.early_stall = enableSSLTunneling).markAsFirstResponse(request);
            }
            if (this.KeepAliveUnknown || !IdempotentSequence.methodIsIdempotent(request.getMethod()) || request.dont_pipeline || HTTPConnection.NeverPipeline) {
                this.late_stall = enableSSLTunneling;
            }
            if (request.getStream() != null) {
                this.output_finished = false;
            }
            else {
                this.output_finished = true;
                this.notify();
            }
            if (GlobalConstants.DebugConn) {
                Util.logLine("Conn:  Request sent");
            }
        }
        return enableSSLTunneling;
    }
    
    private Socket getSocket(final int n) throws IOException, ThreadInterruptedIOException {
        Socket socket = null;
        String s;
        int n2;
        if (this.Tunnel_Host != null) {
            s = this.Tunnel_Host;
            n2 = this.Tunnel_Port;
        }
        else if (this.Proxy_Host != null) {
            s = this.Proxy_Host;
            n2 = this.Proxy_Port;
        }
        else {
            s = this.Host;
            n2 = this.Port;
        }
        if (GlobalConstants.DebugConn) {
            Util.logLine("Conn:  Creating Socket: " + s + ":" + n2);
        }
        if (n == 0) {
            if (this.Socks_client != null) {
                socket = this.Socks_client.getSocket(s, n2);
            }
            else {
                final InetAddress[] allByName = InetAddress.getAllByName(s);
                int i = 0;
                while (i < allByName.length) {
                    try {
                        socket = new Socket(allByName[i], n2);
                        break;
                    }
                    catch (SocketException ex) {
                        if (i == allByName.length - 1) {
                            throw ex;
                        }
                        ++i;
                    }
                }
            }
        }
        else {
            final EstablishConnection establishConnection = new EstablishConnection(s, n2, this.Socks_client);
            establishConnection.start();
            try {
                establishConnection.join(n);
            }
            catch (InterruptedException ex2) {
                establishConnection.getSocket();
                establishConnection.forget();
                throw new ThreadInterruptedIOException("Current thread was interrupted!");
            }
            if (establishConnection.getException() != null) {
                throw establishConnection.getException();
            }
            if ((socket = establishConnection.getSocket()) == null) {
                establishConnection.forget();
                if ((socket = establishConnection.getSocket()) == null) {
                    throw new InterruptedIOException("Connection establishment timed out");
                }
            }
        }
        return socket;
    }
    
    private Response enableSSLTunneling(final Socket[] array, final Request request, final int n) throws IOException, ModuleException {
        final Vector vector = new Vector<NVPair>();
        for (int i = 0; i < request.getHeaders().length; ++i) {
            final String name = request.getHeaders()[i].getName();
            if (name.equalsIgnoreCase("User-Agent") || name.equalsIgnoreCase("Proxy-Authorization")) {
                vector.addElement(request.getHeaders()[i]);
            }
        }
        final NVPair[] array2 = new NVPair[vector.size()];
        vector.copyInto(array2);
        final Request request2 = new Request(this, "CONNECT", this.Host + ":" + this.Port, array2, null, null, request.allowUI());
        request2.internal_subrequest = true;
        final ExtByteArrayOutputStream extByteArrayOutputStream = new ExtByteArrayOutputStream(600);
        final HTTPResponse httpResponse = new HTTPResponse(this.gen_mod_insts(), n, request2);
        Response response = null;
        while (true) {
            this.handleRequest(request2, httpResponse, response, true);
            extByteArrayOutputStream.reset();
            this.assembleHeaders(request2, extByteArrayOutputStream);
            if (GlobalConstants.DebugConn) {
                Util.logLine("Conn:  Sending SSL-Tunneling Subrequest: ");
                Util.logLine();
                extByteArrayOutputStream.writeTo(System.err);
            }
            extByteArrayOutputStream.writeTo(array[0].getOutputStream());
            response = new Response(request2, array[0].getInputStream());
            if (response.getStatusCode() == 200) {
                return null;
            }
            try {
                response.getData();
            }
            catch (IOException ex) {}
            try {
                array[0].close();
            }
            catch (IOException ex2) {}
            httpResponse.set(request2, response);
            if (!httpResponse.handleResponse()) {
                return response;
            }
            array[0] = this.getSocket(n);
        }
    }
    
    private String[] assembleHeaders(final Request request, final ExtByteArrayOutputStream extByteArrayOutputStream) throws IOException {
        final String[] array = { "", "" };
        final NVPair[] headers = request.getHeaders();
        final String escapeUnsafeChars = Util.escapeUnsafeChars(request.getRequestURI());
        if (request.getMethod().equals("CONNECT")) {
            extByteArrayOutputStream.write(request.getMethod(), " ", this.Host);
            extByteArrayOutputStream.write(":", Integer.toString(this.Port), "");
            extByteArrayOutputStream.write(" ", this.RequestProtocolVersion, "\r\n");
        }
        else if (this.Proxy_Host != null && this.Protocol != 1 && !escapeUnsafeChars.equals("*")) {
            extByteArrayOutputStream.write(request.getMethod(), " http://", this.Host);
            extByteArrayOutputStream.write(":", Integer.toString(this.Port), escapeUnsafeChars);
            extByteArrayOutputStream.write(" ", this.RequestProtocolVersion, "\r\n");
        }
        else {
            extByteArrayOutputStream.write(request.getMethod(), " ", escapeUnsafeChars);
            extByteArrayOutputStream.write(" ", this.RequestProtocolVersion, "\r\n");
        }
        if (this.Port != 80 || request.getMethod().equals("CONNECT")) {
            extByteArrayOutputStream.write("Host: ", this.Host, ":");
            extByteArrayOutputStream.write(Integer.toString(this.Port), "\r\n");
        }
        else {
            extByteArrayOutputStream.write("Host: ", this.Host, "\r\n");
        }
        int n = -1;
        int n2 = -1;
        int n3 = -1;
        int n4 = -1;
        int n5 = -1;
        int n6 = -1;
        int n7 = -1;
        int n8 = -1;
        int n9 = -1;
        for (int i = 0; i < headers.length; ++i) {
            final String trim = headers[i].getName().trim();
            if (trim.equalsIgnoreCase("Content-Type")) {
                n = i;
            }
            else if (trim.equalsIgnoreCase("User-Agent")) {
                n2 = i;
            }
            else if (trim.equalsIgnoreCase("Connection")) {
                n3 = i;
            }
            else if (trim.equalsIgnoreCase("Proxy-Connection")) {
                n4 = i;
            }
            else if (trim.equalsIgnoreCase("Keep-Alive")) {
                n5 = i;
            }
            else if (trim.equalsIgnoreCase("Expect")) {
                n6 = i;
            }
            else if (trim.equalsIgnoreCase("TE")) {
                n7 = i;
            }
            else if (trim.equalsIgnoreCase("Transfer-Encoding")) {
                n8 = i;
            }
            else if (trim.equalsIgnoreCase("Upgrade")) {
                n9 = i;
            }
        }
        String s = null;
        if (!this.ServProtVersKnown || this.ServerProtocolVersion < 65537 || n3 != -1) {
            if (n3 == -1) {
                s = "Keep-Alive";
                array[0] = "Keep-Alive";
            }
            else {
                array[0] = headers[n3].getValue().trim();
                s = array[0];
            }
            try {
                if (n5 != -1 && Util.hasToken(array[0], "keep-alive")) {
                    extByteArrayOutputStream.write("Keep-Alive: ", headers[n5].getValue().trim(), "\r\n");
                }
            }
            catch (ParseException ex) {
                throw new IOException(ex.toString());
            }
        }
        if (this.Proxy_Host != null && this.Protocol != 1 && (!this.ServProtVersKnown || this.ServerProtocolVersion < 65537) && s != null) {
            extByteArrayOutputStream.write("Proxy-Connection: ", s, "\r\n");
            s = null;
        }
        Label_0697: {
            if (s != null) {
                try {
                    if (!Util.hasToken(s, "TE")) {
                        s += ", TE";
                    }
                    break Label_0697;
                }
                catch (ParseException ex2) {
                    throw new IOException(ex2.toString());
                }
            }
            s = "TE";
        }
        if (n9 != -1) {
            s += ", Upgrade";
        }
        if (s != null) {
            extByteArrayOutputStream.write("Connection: ", s, "\r\n");
        }
        if (n7 != -1) {
            extByteArrayOutputStream.write("TE: ");
            Vector header;
            try {
                header = Util.parseHeader(headers[n7].getValue());
            }
            catch (ParseException ex3) {
                throw new IOException(ex3.toString());
            }
            if (!header.contains(new HttpHeaderElement("trailers"))) {
                extByteArrayOutputStream.write("trailers, ");
            }
            extByteArrayOutputStream.write(headers[n7].getValue().trim(), "\r\n");
        }
        else {
            extByteArrayOutputStream.write("TE: trailers\r\n");
        }
        if (n2 != -1) {
            extByteArrayOutputStream.write("User-Agent: ", headers[n2].getValue().trim(), " ");
            extByteArrayOutputStream.write("RPT-HTTPClient/0.4.1-dev", "\r\n");
        }
        else {
            extByteArrayOutputStream.write("User-Agent: ", "RPT-HTTPClient/0.4.1-dev", "\r\n");
        }
        for (int j = 0; j < headers.length; ++j) {
            if (j != n && j != n2 && j != n3 && j != n4 && j != n5 && j != n6 && j != n7) {
                extByteArrayOutputStream.write(headers[j].getName().trim(), ": ");
                extByteArrayOutputStream.write(headers[j].getValue().trim(), "\r\n");
            }
        }
        if (request.getData() != null || request.getStream() != null) {
            if (n != -1) {
                extByteArrayOutputStream.write("Content-type: ", headers[n].getValue().trim(), "\r\n");
            }
            else {
                extByteArrayOutputStream.write("Content-type: application/octet-stream\r\n");
            }
            if (request.getData() != null) {
                extByteArrayOutputStream.write("Content-length: ", Integer.toString(request.getData().length), "\r\n");
            }
            else if (request.getStream().getLength() != -1 && n8 == -1) {
                extByteArrayOutputStream.write("Content-length: ", Integer.toString(request.getStream().getLength()), "\r\n");
            }
            if (n6 != -1) {
                extByteArrayOutputStream.write("Expect: ", array[1] = headers[n6].getValue().trim(), "\r\n");
            }
        }
        else if (n6 != -1) {
            Vector header2;
            try {
                header2 = Util.parseHeader(headers[n6].getValue());
            }
            catch (ParseException ex4) {
                throw new IOException(ex4.toString());
            }
            while (header2.removeElement(new HttpHeaderElement("100-continue"))) {}
            if (!header2.isEmpty()) {
                extByteArrayOutputStream.write("Expect: ", array[1] = Util.assembleHeader(header2), "\r\n");
            }
        }
        extByteArrayOutputStream.write("\r\n");
        return array;
    }
    
    boolean handleFirstRequest(final Request request, final Response response) throws IOException {
        this.ServerProtocolVersion = String2ProtVers(response.getVersion());
        this.ServProtVersKnown = true;
        if (this.Proxy_Host != null && this.Protocol != 1 && response.getHeader("Via") == null) {
            this.ServerProtocolVersion = 65536;
        }
        if (GlobalConstants.DebugConn) {
            Util.logLine("Conn:  Protocol Version established: " + ProtVers2String(this.ServerProtocolVersion));
        }
        if (this.ServerProtocolVersion == 65536 && (response.getStatusCode() == 400 || response.getStatusCode() == 500)) {
            if (this.input_demux != null && this.input_demux.isHttpConnectCompatibilityModeUsed()) {
                this.input_demux.releaseHttpConnectResources();
            }
            this.input_demux.markForClose(response);
            this.input_demux = null;
            this.RequestProtocolVersion = "HTTP/1.0";
            return false;
        }
        return true;
    }
    
    private void determineKeepAlive(final Response response) throws IOException {
        try {
            String s;
            if (this.ServerProtocolVersion >= 65537 || ((((this.Proxy_Host == null || this.Protocol == 1) && (s = response.getHeader("Connection")) != null) || (this.Proxy_Host != null && this.Protocol != 1 && (s = response.getHeader("Proxy-Connection")) != null)) && Util.hasToken(s, "keep-alive"))) {
                this.DoesKeepAlive = true;
                this.KeepAliveUnknown = false;
                if (GlobalConstants.DebugConn) {
                    Util.logLine("Conn:  Keep-Alive enabled");
                }
            }
            else if (response.getStatusCode() < 400) {
                this.KeepAliveUnknown = false;
            }
            final String header;
            if (this.DoesKeepAlive && this.ServerProtocolVersion == 65536 && (header = response.getHeader("Keep-Alive")) != null) {
                final HttpHeaderElement element = Util.getElement(Util.parseHeader(header), "max");
                if (element != null && element.getValue() != null) {
                    this.KeepAliveReqMax = Integer.parseInt(element.getValue());
                    this.KeepAliveReqLeft = this.KeepAliveReqMax;
                    if (GlobalConstants.DebugConn) {
                        Util.logLine("Conn:  Max Keep-Alive requests: " + this.KeepAliveReqMax);
                    }
                }
            }
        }
        catch (ParseException ex) {}
        catch (NumberFormatException ex2) {}
        catch (ClassCastException ex3) {}
    }
    
    synchronized void outputFinished() {
        this.output_finished = true;
        this.notify();
    }
    
    synchronized void closeDemux(final IOException ex) {
        if (this.input_demux != null) {
            this.input_demux.close(ex, true);
        }
        this.early_stall = null;
        this.late_stall = null;
        this.prev_resp = null;
    }
    
    static final String ProtVers2String(final int n) {
        return "HTTP/" + (n >>> 16) + "." + (n & 0xFFFF);
    }
    
    static final int String2ProtVers(final String s) {
        final String substring = s.substring(5);
        final int index = substring.indexOf(46);
        return Integer.parseInt(substring.substring(0, index)) << 16 | Integer.parseInt(substring.substring(index + 1));
    }
    
    public String toString() {
        return this.getProtocol() + "://" + this.getHost() + ((this.getPort() != URI.defaultPort(this.getProtocol())) ? (":" + this.getPort()) : "");
    }
    
    static {
        dflt_context = new Object();
        HTTPConnection.force_1_0 = false;
        HTTPConnection.Default_Proxy_Host = null;
        HTTPConnection.non_proxy_host_list = new CIHashtable();
        HTTPConnection.non_proxy_dom_list = new Vector();
        HTTPConnection.non_proxy_addr_list = new Vector();
        HTTPConnection.non_proxy_mask_list = new Vector();
        HTTPConnection.Default_Tunnel_Host = null;
        HTTPConnection.Default_Socks_client = null;
        HTTPConnection.NeverPipeline = false;
        HTTPConnection.disable_nagle = false;
        HTTPConnection.DefaultTimeout = 0;
        HTTPConnection.DefaultAllowUI = true;
        try {
            final String property = System.getProperty("http.proxyHost");
            if (property == null) {
                throw new Exception();
            }
            final int intValue = Integer.getInteger("http.proxyPort", -1);
            if (GlobalConstants.DebugConn) {
                Util.logLine("Conn:  using proxy " + property + ":" + intValue);
            }
            setProxyServer(property, intValue);
        }
        catch (Exception ex2) {
            try {
                if (Boolean.getBoolean("proxySet")) {
                    final String property2 = System.getProperty("proxyHost");
                    final int intValue2 = Integer.getInteger("proxyPort", -1);
                    if (GlobalConstants.DebugConn) {
                        Util.logLine("Conn:  using proxy " + property2 + ":" + intValue2);
                    }
                    setProxyServer(property2, intValue2);
                }
            }
            catch (Exception ex3) {
                HTTPConnection.Default_Proxy_Host = null;
            }
        }
        try {
            HTTPConnection.Default_Tunnel_Host = System.getProperty("HTTPClient.tunnelHost");
            if (HTTPConnection.Default_Tunnel_Host != null) {
                HTTPConnection.Default_Tunnel_Host = HTTPConnection.Default_Tunnel_Host.trim().toLowerCase();
            }
            HTTPConnection.Default_Tunnel_Port = Integer.getInteger("HTTPClient.tunnelPort");
            if (GlobalConstants.DebugConn && HTTPConnection.Default_Tunnel_Host != null) {
                Util.logLine("Conn:  using tunnel " + HTTPConnection.Default_Tunnel_Host + ":" + HTTPConnection.Default_Tunnel_Port);
            }
        }
        catch (Exception ex4) {
            HTTPConnection.Default_Tunnel_Host = null;
        }
        try {
            String s = System.getProperty("HTTPClient.nonProxyHosts");
            if (s == null) {
                s = System.getProperty("http.nonProxyHosts");
            }
            final String[] splitProperty = Util.splitProperty(s);
            for (int i = 0; i < splitProperty.length; ++i) {
                dontProxyFor(splitProperty[i]);
            }
        }
        catch (Exception ex5) {}
        try {
            final String property3 = System.getProperty("HTTPClient.socksHost");
            if (property3 != null && property3.length() > 0) {
                final int intValue3 = Integer.getInteger("HTTPClient.socksPort", -1);
                final int intValue4 = Integer.getInteger("HTTPClient.socksVersion", -1);
                if (GlobalConstants.DebugConn) {
                    Util.logLine("Conn:  using SOCKS " + property3 + ":" + intValue3);
                }
                if (intValue4 == -1) {
                    setSocksServer(property3, intValue3);
                }
                else {
                    setSocksServer(property3, intValue3, intValue4);
                }
            }
        }
        catch (Exception ex6) {
            HTTPConnection.Default_Socks_client = null;
        }
        String property4 = "HTTPClient.RetryModule|HTTPClient.CookieModule|HTTPClient.RedirectionModule|HTTPClient.AuthorizationModule|HTTPClient.DefaultModule|HTTPClient.TransferEncodingModule|HTTPClient.ContentMD5Module|HTTPClient.ContentEncodingModule";
        boolean b = false;
        try {
            property4 = System.getProperty("HTTPClient.Modules", property4);
        }
        catch (SecurityException ex7) {
            b = true;
        }
        HTTPConnection.DefaultModuleList = new Vector();
        final String[] splitProperty2 = Util.splitProperty(property4);
        for (int j = 0; j < splitProperty2.length; ++j) {
            try {
                HTTPConnection.DefaultModuleList.addElement(Class.forName(splitProperty2[j]));
                if (GlobalConstants.DebugConn) {
                    Util.logLine("Conn:  added module " + splitProperty2[j]);
                }
            }
            catch (ClassNotFoundException ex) {
                if (!b) {
                    throw new NoClassDefFoundError(ex.getMessage());
                }
            }
        }
        try {
            HTTPConnection.NeverPipeline = Boolean.getBoolean("HTTPClient.disablePipelining");
            if (GlobalConstants.DebugConn && HTTPConnection.NeverPipeline) {
                Util.logLine("Conn:  disabling pipelining");
            }
        }
        catch (Exception ex8) {}
        try {
            HTTPConnection.disable_nagle = Boolean.getBoolean("HTTPClient.disableNagle");
            if (GlobalConstants.DebugConn && HTTPConnection.disable_nagle) {
                Util.logLine("Conn:  disabling Nagle");
            }
        }
        catch (Exception ex9) {}
        try {
            HTTPConnection.force_1_0 = Boolean.getBoolean("HTTPClient.forceHTTP_1.0");
            if (GlobalConstants.DebugConn && HTTPConnection.force_1_0) {
                Util.logLine("Conn:  forcing HTTP/1.0 requests");
            }
        }
        catch (Exception ex10) {}
    }
}
