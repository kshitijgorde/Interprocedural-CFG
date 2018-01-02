// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.commons.httpclient;

import java.io.InterruptedIOException;
import org.apache.commons.httpclient.cookie.MalformedCookieException;
import org.apache.commons.httpclient.protocol.Protocol;
import org.apache.commons.httpclient.cookie.CookieSpec;
import org.apache.commons.httpclient.cookie.CookiePolicy;
import org.apache.commons.httpclient.auth.AuthScheme;
import org.apache.commons.httpclient.auth.HttpAuthenticator;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.ByteArrayOutputStream;
import org.apache.commons.httpclient.util.EncodingUtil;
import java.io.InputStream;

public abstract class HttpMethodBase implements HttpMethod
{
    protected static final Header USER_AGENT;
    private HeaderGroup requestHeaders;
    private StatusLine statusLine;
    private HeaderGroup responseHeaders;
    private HeaderGroup responseTrailerHeaders;
    private String realm;
    private String proxyRealm;
    private String path;
    private String queryString;
    private InputStream responseStream;
    private HttpConnection responseConnection;
    private byte[] responseBody;
    private boolean followRedirects;
    private boolean doAuthentication;
    private HttpVersion version;
    private boolean strictMode;
    private boolean used;
    private int recoverableExceptionCount;
    private HostConfiguration hostConfiguration;
    private MethodRetryHandler methodRetryHandler;
    private boolean connectionCloseForced;
    private static final int RESPONSE_WAIT_TIME_MS = 3000;
    
    static {
        final String agent = System.getProperties().getProperty("httpclient.useragent", "Jakarta Commons-HttpClient/2.1m1");
        USER_AGENT = new Header("User-Agent", agent);
    }
    
    public HttpMethodBase() {
        this.requestHeaders = new HeaderGroup();
        this.statusLine = null;
        this.responseHeaders = new HeaderGroup();
        this.responseTrailerHeaders = new HeaderGroup();
        this.realm = null;
        this.proxyRealm = null;
        this.path = null;
        this.queryString = null;
        this.responseStream = null;
        this.responseConnection = null;
        this.responseBody = null;
        this.followRedirects = false;
        this.doAuthentication = true;
        this.version = HttpVersion.HTTP_1_1;
        this.strictMode = false;
        this.used = false;
        this.recoverableExceptionCount = 0;
        this.connectionCloseForced = false;
    }
    
    public HttpMethodBase(String uri) throws IllegalArgumentException, IllegalStateException {
        this.requestHeaders = new HeaderGroup();
        this.statusLine = null;
        this.responseHeaders = new HeaderGroup();
        this.responseTrailerHeaders = new HeaderGroup();
        this.realm = null;
        this.proxyRealm = null;
        this.path = null;
        this.queryString = null;
        this.responseStream = null;
        this.responseConnection = null;
        this.responseBody = null;
        this.followRedirects = false;
        this.doAuthentication = true;
        this.version = HttpVersion.HTTP_1_1;
        this.strictMode = false;
        this.used = false;
        this.recoverableExceptionCount = 0;
        this.connectionCloseForced = false;
        try {
            if (uri == null || uri.equals("")) {
                uri = "/";
            }
            this.setURI(new URI(uri, true));
        }
        catch (URIException e) {
            throw new IllegalArgumentException("Invalid uri '" + uri + "': " + e.getMessage());
        }
    }
    
    public abstract String getName();
    
    public URI getURI() throws URIException {
        if (this.hostConfiguration == null) {
            final URI tmpUri = new URI(null, null, this.path, null, null);
            tmpUri.setEscapedQuery(this.queryString);
            return tmpUri;
        }
        int port = this.hostConfiguration.getPort();
        if (port == this.hostConfiguration.getProtocol().getDefaultPort()) {
            port = -1;
        }
        final URI tmpUri2 = new URI(this.hostConfiguration.getProtocol().getScheme(), null, this.hostConfiguration.getHost(), port, this.path, null);
        tmpUri2.setEscapedQuery(this.queryString);
        return tmpUri2;
    }
    
    public void setURI(final URI uri) throws URIException {
        if (uri.isAbsoluteURI()) {
            if (this.hostConfiguration == null) {
                this.hostConfiguration = new HostConfiguration();
            }
            this.hostConfiguration.setHost(uri.getHost(), uri.getPort(), uri.getScheme());
        }
        this.setPath((uri.getPath() == null) ? "/" : uri.getEscapedPath());
        this.setQueryString(uri.getEscapedQuery());
    }
    
    public void setFollowRedirects(final boolean followRedirects) {
        this.followRedirects = followRedirects;
    }
    
    public boolean getFollowRedirects() {
        return this.followRedirects;
    }
    
    public void setHttp11(final boolean http11) {
        if (http11) {
            this.version = HttpVersion.HTTP_1_1;
        }
        else {
            this.version = HttpVersion.HTTP_1_0;
        }
    }
    
    public boolean getDoAuthentication() {
        return this.doAuthentication;
    }
    
    public void setDoAuthentication(final boolean doAuthentication) {
        this.doAuthentication = doAuthentication;
    }
    
    public boolean isHttp11() {
        return this.version.equals(HttpVersion.HTTP_1_1);
    }
    
    public void setPath(final String path) {
        this.path = path;
    }
    
    public void addRequestHeader(final Header header) {
        if (header != null) {
            this.getRequestHeaderGroup().addHeader(header);
        }
    }
    
    public void addResponseFooter(final Header footer) {
        this.getResponseTrailerHeaderGroup().addHeader(footer);
    }
    
    public String getPath() {
        return (this.path == null || this.path.equals("")) ? "/" : this.path;
    }
    
    public void setQueryString(final String queryString) {
        this.queryString = queryString;
    }
    
    public void setQueryString(final NameValuePair[] params) {
        this.queryString = EncodingUtil.formUrlEncode(params, "UTF-8");
    }
    
    public String getQueryString() {
        return this.queryString;
    }
    
    public void setRequestHeader(final String headerName, final String headerValue) {
        final Header header = new Header(headerName, headerValue);
        this.setRequestHeader(header);
    }
    
    public void setRequestHeader(final Header header) {
        final Header[] headers = this.getRequestHeaderGroup().getHeaders(header.getName());
        for (int i = 0; i < headers.length; ++i) {
            this.getRequestHeaderGroup().removeHeader(headers[i]);
        }
        this.getRequestHeaderGroup().addHeader(header);
    }
    
    public Header getRequestHeader(final String headerName) {
        if (headerName == null) {
            return null;
        }
        return this.getRequestHeaderGroup().getCondensedHeader(headerName);
    }
    
    public Header[] getRequestHeaders() {
        return this.getRequestHeaderGroup().getAllHeaders();
    }
    
    public Header[] getRequestHeaders(final String headerName) {
        return this.getRequestHeaderGroup().getHeaders(headerName);
    }
    
    protected HeaderGroup getRequestHeaderGroup() {
        return this.requestHeaders;
    }
    
    protected HeaderGroup getResponseTrailerHeaderGroup() {
        return this.responseTrailerHeaders;
    }
    
    protected HeaderGroup getResponseHeaderGroup() {
        return this.responseHeaders;
    }
    
    public Header[] getResponseHeaders(final String headerName) {
        return this.getResponseHeaderGroup().getHeaders(headerName);
    }
    
    public int getStatusCode() {
        return this.statusLine.getStatusCode();
    }
    
    public StatusLine getStatusLine() {
        return this.statusLine;
    }
    
    private boolean responseAvailable() {
        return this.responseBody != null || this.responseStream != null;
    }
    
    public Header[] getResponseHeaders() {
        return this.getResponseHeaderGroup().getAllHeaders();
    }
    
    public Header getResponseHeader(final String headerName) {
        if (headerName == null) {
            return null;
        }
        return this.getResponseHeaderGroup().getCondensedHeader(headerName);
    }
    
    protected long getResponseContentLength() {
        final Header[] headers = this.getResponseHeaderGroup().getHeaders("Content-Length");
        if (headers.length == 0) {
            return -1L;
        }
        headers.getClass();
        int i = headers.length - 1;
        while (i >= 0) {
            final Header header = headers[i];
            try {
                return Long.parseLong(header.getValue());
            }
            catch (NumberFormatException ex) {
                ++i;
            }
        }
        return -1L;
    }
    
    public byte[] getResponseBody() {
        if (this.responseBody == null) {
            try {
                final InputStream instream = this.getResponseBodyAsStream();
                if (instream != null) {
                    final ByteArrayOutputStream outstream = new ByteArrayOutputStream();
                    final byte[] buffer = new byte[4096];
                    int len;
                    while ((len = instream.read(buffer)) > 0) {
                        outstream.write(buffer, 0, len);
                    }
                    outstream.close();
                    this.setResponseStream(null);
                    this.responseBody = outstream.toByteArray();
                }
            }
            catch (IOException e) {
                this.responseBody = null;
            }
        }
        return this.responseBody;
    }
    
    public InputStream getResponseBodyAsStream() throws IOException {
        if (this.responseStream != null) {
            return this.responseStream;
        }
        if (this.responseBody != null) {
            final InputStream byteResponseStream = new ByteArrayInputStream(this.responseBody);
            return byteResponseStream;
        }
        return null;
    }
    
    public String getResponseBodyAsString() {
        byte[] rawdata = null;
        if (this.responseAvailable()) {
            rawdata = this.getResponseBody();
        }
        if (rawdata != null) {
            return HttpConstants.getContentString(rawdata, this.getResponseCharSet());
        }
        return null;
    }
    
    public Header[] getResponseFooters() {
        return this.getResponseTrailerHeaderGroup().getAllHeaders();
    }
    
    public Header getResponseFooter(final String footerName) {
        if (footerName == null) {
            return null;
        }
        return this.getResponseTrailerHeaderGroup().getCondensedHeader(footerName);
    }
    
    protected void setResponseStream(final InputStream responseStream) {
        this.responseStream = responseStream;
    }
    
    protected InputStream getResponseStream() {
        return this.responseStream;
    }
    
    public String getStatusText() {
        return this.statusLine.getReasonPhrase();
    }
    
    public void setStrictMode(final boolean strictMode) {
        this.strictMode = strictMode;
    }
    
    public boolean isStrictMode() {
        return this.strictMode;
    }
    
    public void addRequestHeader(final String headerName, final String headerValue) {
        this.addRequestHeader(new Header(headerName, headerValue));
    }
    
    protected boolean isConnectionCloseForced() {
        return this.connectionCloseForced;
    }
    
    protected void setConnectionCloseForced(final boolean b) {
        this.connectionCloseForced = b;
    }
    
    protected boolean shouldCloseConnection(final HttpConnection conn) {
        if (this.isConnectionCloseForced()) {
            return true;
        }
        Header connectionHeader = null;
        if (!conn.isTransparent()) {
            connectionHeader = this.responseHeaders.getFirstHeader("proxy-connection");
        }
        if (connectionHeader == null) {
            connectionHeader = this.responseHeaders.getFirstHeader("connection");
        }
        if (connectionHeader != null) {
            if (connectionHeader.getValue().equalsIgnoreCase("close")) {
                return true;
            }
            if (connectionHeader.getValue().equalsIgnoreCase("keep-alive")) {
                return false;
            }
        }
        this.version.greaterEquals(HttpVersion.HTTP_1_1);
        return this.version.lessEquals(HttpVersion.HTTP_1_0);
    }
    
    private void checkExecuteConditions(final HttpState state, final HttpConnection conn) throws HttpException {
        if (state == null) {
            throw new IllegalArgumentException("HttpState parameter may not be null");
        }
        if (conn == null) {
            throw new IllegalArgumentException("HttpConnection parameter may not be null");
        }
        if (!this.validate()) {
            throw new ProtocolException("HttpMethodBase object not valid");
        }
    }
    
    public int execute(final HttpState state, final HttpConnection conn) throws HttpException, HttpRecoverableException, IOException {
        this.checkExecuteConditions(state, this.responseConnection = conn);
        this.statusLine = null;
        conn.setLastResponseInputStream(null);
        boolean requestSent = false;
        this.writeRequest(state, conn);
        requestSent = true;
        this.readResponse(state, conn);
        this.used = true;
        return this.statusLine.getStatusCode();
    }
    
    public boolean hasBeenUsed() {
        return this.used;
    }
    
    public void recycle() {
        this.releaseConnection();
        this.path = null;
        this.followRedirects = false;
        this.doAuthentication = true;
        this.realm = null;
        this.proxyRealm = null;
        this.queryString = null;
        this.getRequestHeaderGroup().clear();
        this.getResponseHeaderGroup().clear();
        this.getResponseTrailerHeaderGroup().clear();
        this.statusLine = null;
        this.used = false;
        this.version = HttpVersion.HTTP_1_1;
        this.responseBody = null;
        this.recoverableExceptionCount = 0;
        this.connectionCloseForced = false;
    }
    
    public void releaseConnection() {
        if (this.responseStream != null) {
            try {
                this.responseStream.close();
            }
            catch (IOException e) {}
        }
        else {
            this.ensureConnectionRelease();
        }
    }
    
    public void removeRequestHeader(final String headerName) {
        final Header[] headers = this.getRequestHeaderGroup().getHeaders(headerName);
        for (int i = 0; i < headers.length; ++i) {
            this.getRequestHeaderGroup().removeHeader(headers[i]);
        }
    }
    
    public boolean validate() {
        return true;
    }
    
    protected void addAuthorizationRequestHeader(final HttpState state, final HttpConnection conn) throws IOException, HttpException {
        if (this.getRequestHeader("Authorization") == null) {
            final Header[] challenges = this.getResponseHeaderGroup().getHeaders("WWW-Authenticate");
            if (challenges.length > 0) {
                try {
                    final AuthScheme authscheme = HttpAuthenticator.selectAuthScheme(challenges);
                    HttpAuthenticator.authenticate(authscheme, this, conn, state);
                }
                catch (HttpException ex) {}
            }
        }
    }
    
    protected void addCookieRequestHeader(final HttpState state, final HttpConnection conn) throws IOException, HttpException {
        this.removeRequestHeader("cookie");
        final CookieSpec matcher = CookiePolicy.getSpecByPolicy(state.getCookiePolicy());
        final Cookie[] cookies = matcher.match(conn.getHost(), conn.getPort(), this.getPath(), conn.isSecure(), state.getCookies());
        if (cookies != null && cookies.length > 0) {
            if (this.isStrictMode()) {
                this.getRequestHeaderGroup().addHeader(matcher.formatCookieHeader(cookies));
            }
            else {
                for (int i = 0; i < cookies.length; ++i) {
                    this.getRequestHeaderGroup().addHeader(matcher.formatCookieHeader(cookies[i]));
                }
            }
        }
    }
    
    protected void addHostRequestHeader(final HttpState state, final HttpConnection conn) throws IOException, HttpException {
        String host = conn.getVirtualHost();
        if (host == null) {
            host = conn.getHost();
        }
        final int port = conn.getPort();
        if (this.getRequestHeader("host") != null) {
            return;
        }
        if (conn.getProtocol().getDefaultPort() != port) {
            host = String.valueOf(host) + ":" + port;
        }
        this.setRequestHeader("Host", host);
    }
    
    protected void addProxyAuthorizationRequestHeader(final HttpState state, final HttpConnection conn) throws IOException, HttpException {
        if (this.getRequestHeader("Proxy-Authorization") == null) {
            final Header[] challenges = this.getResponseHeaderGroup().getHeaders("Proxy-Authenticate");
            if (challenges.length > 0) {
                try {
                    final AuthScheme authscheme = HttpAuthenticator.selectAuthScheme(challenges);
                    HttpAuthenticator.authenticateProxy(authscheme, this, conn, state);
                }
                catch (HttpException ex) {}
            }
        }
    }
    
    protected void addProxyConnectionHeader(final HttpState state, final HttpConnection conn) throws IOException, HttpException {
        if (!conn.isTransparent()) {
            this.setRequestHeader("Proxy-Connection", "Keep-Alive");
        }
    }
    
    protected void addRequestHeaders(final HttpState state, final HttpConnection conn) throws IOException, HttpException {
        this.addUserAgentRequestHeader(state, conn);
        this.addHostRequestHeader(state, conn);
        this.addCookieRequestHeader(state, conn);
        this.addAuthorizationRequestHeader(state, conn);
        this.addProxyAuthorizationRequestHeader(state, conn);
        this.addProxyConnectionHeader(state, conn);
    }
    
    protected void addUserAgentRequestHeader(final HttpState state, final HttpConnection conn) throws IOException, HttpException {
        if (this.getRequestHeader("user-agent") == null) {
            this.setRequestHeader(HttpMethodBase.USER_AGENT);
        }
    }
    
    protected void checkNotUsed() throws IllegalStateException {
        if (this.used) {
            throw new IllegalStateException("Already used.");
        }
    }
    
    protected void checkUsed() throws IllegalStateException {
        if (!this.used) {
            throw new IllegalStateException("Not Used.");
        }
    }
    
    protected static String generateRequestLine(final HttpConnection connection, final String name, final String requestPath, final String query, final String version) {
        final StringBuffer buf = new StringBuffer();
        buf.append(name);
        buf.append(" ");
        if (!connection.isTransparent()) {
            final Protocol protocol = connection.getProtocol();
            buf.append(protocol.getScheme().toLowerCase());
            buf.append("://");
            buf.append(connection.getHost());
            if (connection.getPort() != -1 && connection.getPort() != protocol.getDefaultPort()) {
                buf.append(":");
                buf.append(connection.getPort());
            }
        }
        if (requestPath == null) {
            buf.append("/");
        }
        else {
            if (!connection.isTransparent() && !requestPath.startsWith("/")) {
                buf.append("/");
            }
            buf.append(requestPath);
        }
        if (query != null) {
            if (query.indexOf("?") != 0) {
                buf.append("?");
            }
            buf.append(query);
        }
        buf.append(" ");
        buf.append(version);
        buf.append("\r\n");
        return buf.toString();
    }
    
    protected void processResponseBody(final HttpState state, final HttpConnection conn) {
    }
    
    protected void processResponseHeaders(final HttpState state, final HttpConnection conn) {
        Header[] headers = this.getResponseHeaderGroup().getHeaders("set-cookie2");
        if (headers.length == 0) {
            headers = this.getResponseHeaderGroup().getHeaders("set-cookie");
        }
        final CookieSpec parser = CookiePolicy.getSpecByPolicy(state.getCookiePolicy());
        for (int i = 0; i < headers.length; ++i) {
            final Header header = headers[i];
            Cookie[] cookies = null;
            try {
                cookies = parser.parse(conn.getHost(), conn.getPort(), this.getPath(), conn.isSecure(), header);
            }
            catch (MalformedCookieException ex) {}
            if (cookies != null) {
                for (int j = 0; j < cookies.length; ++j) {
                    final Cookie cookie = cookies[j];
                    try {
                        parser.validate(conn.getHost(), conn.getPort(), this.getPath(), conn.isSecure(), cookie);
                        state.addCookie(cookie);
                    }
                    catch (MalformedCookieException ex2) {}
                }
            }
        }
    }
    
    protected void processStatusLine(final HttpState state, final HttpConnection conn) {
    }
    
    protected void readResponse(final HttpState state, final HttpConnection conn) throws IOException, HttpException {
        while (this.statusLine == null) {
            this.readStatusLine(state, conn);
            this.processStatusLine(state, conn);
            this.readResponseHeaders(state, conn);
            this.processResponseHeaders(state, conn);
            final int status = this.statusLine.getStatusCode();
            if (status >= 100 && status < 200) {
                this.statusLine = null;
            }
        }
        this.readResponseBody(state, conn);
        this.processResponseBody(state, conn);
    }
    
    protected void readResponseBody(final HttpState state, final HttpConnection conn) throws IOException, HttpException {
        final InputStream stream = this.readResponseBody(conn);
        if (stream == null) {
            this.responseBodyConsumed();
        }
        else {
            conn.setLastResponseInputStream(stream);
            this.setResponseStream(stream);
        }
    }
    
    private InputStream readResponseBody(final HttpConnection conn) throws HttpException, IOException {
        this.responseBody = null;
        InputStream is = conn.getResponseInputStream();
        if (Wire.enabled()) {
            is = new WireLogInputStream(is);
        }
        InputStream result = null;
        final Header transferEncodingHeader = this.responseHeaders.getFirstHeader("Transfer-Encoding");
        if (transferEncodingHeader != null) {
            final String transferEncoding = transferEncodingHeader.getValue();
            if (!"chunked".equalsIgnoreCase(transferEncoding)) {
                "identity".equalsIgnoreCase(transferEncoding);
            }
            final HeaderElement[] encodings = transferEncodingHeader.getElements();
            final int len = encodings.length;
            if (len > 0 && "chunked".equalsIgnoreCase(encodings[len - 1].getName())) {
                if (conn.isResponseAvailable(conn.getSoTimeout())) {
                    result = new ChunkedInputStream(is, this);
                }
                else if (this.isStrictMode()) {
                    throw new ProtocolException("Chunk-encoded body declared but not sent");
                }
            }
            else {
                this.setConnectionCloseForced(true);
                result = is;
            }
        }
        else {
            final long expectedLength = this.getResponseContentLength();
            if (expectedLength == -1L) {
                if (canResponseHaveBody(this.statusLine.getStatusCode())) {
                    this.setConnectionCloseForced(true);
                    result = is;
                }
            }
            else {
                result = new ContentLengthInputStream(is, expectedLength);
            }
        }
        if (result != null) {
            result = new AutoCloseInputStream(result, new ResponseConsumedWatcher() {
                public void responseConsumed() {
                    HttpMethodBase.this.responseBodyConsumed();
                }
            });
        }
        return result;
    }
    
    protected void readResponseHeaders(final HttpState state, final HttpConnection conn) throws IOException, HttpException {
        this.getResponseHeaderGroup().clear();
        final Header[] headers = HttpParser.parseHeaders(conn.getResponseInputStream());
        if (Wire.enabled()) {
            for (int i = 0; i < headers.length; ++i) {
                Wire.input(headers[i].toExternalForm());
            }
        }
        this.getResponseHeaderGroup().setHeaders(headers);
    }
    
    protected void readStatusLine(final HttpState state, final HttpConnection conn) throws IOException, HttpRecoverableException, HttpException {
        String statusString;
        for (statusString = conn.readLine(); statusString != null && !statusString.startsWith("HTTP"); statusString = conn.readLine()) {
            if (Wire.enabled()) {
                Wire.input(String.valueOf(statusString) + "\r\n");
            }
        }
        if (statusString == null) {
            throw new HttpRecoverableException("Error in parsing the status  line from the response: unable to find line starting with \"HTTP\"");
        }
        if (Wire.enabled()) {
            Wire.input(String.valueOf(statusString) + "\r\n");
        }
        this.statusLine = new StatusLine(statusString);
        final String versionStr = this.statusLine.getHttpVersion();
        if (!this.strictMode && versionStr.equals("HTTP")) {
            this.version = HttpVersion.HTTP_1_0;
        }
        else {
            this.version = HttpVersion.parse(versionStr);
        }
    }
    
    protected void writeRequest(final HttpState state, final HttpConnection conn) throws IOException, HttpException {
        this.writeRequestLine(state, conn);
        this.writeRequestHeaders(state, conn);
        conn.writeLine();
        conn.flushRequestOutputStream();
        if (Wire.enabled()) {
            Wire.output("\r\n");
        }
        final Header expectheader = this.getRequestHeader("Expect");
        String expectvalue = null;
        if (expectheader != null) {
            expectvalue = expectheader.getValue();
        }
        if (expectvalue != null && expectvalue.compareToIgnoreCase("100-continue") == 0) {
            if (this.isHttp11()) {
                final int readTimeout = conn.getSoTimeout();
                try {
                    conn.setSoTimeout(3000);
                    this.readStatusLine(state, conn);
                    this.processStatusLine(state, conn);
                    this.readResponseHeaders(state, conn);
                    this.processResponseHeaders(state, conn);
                    if (this.statusLine.getStatusCode() != 100) {
                        return;
                    }
                    this.statusLine = null;
                }
                catch (InterruptedIOException e) {
                    this.removeRequestHeader("Expect");
                }
                finally {
                    conn.setSoTimeout(readTimeout);
                }
                conn.setSoTimeout(readTimeout);
            }
            else {
                this.removeRequestHeader("Expect");
            }
        }
        this.writeRequestBody(state, conn);
        conn.flushRequestOutputStream();
    }
    
    protected boolean writeRequestBody(final HttpState state, final HttpConnection conn) throws IOException, HttpException {
        return true;
    }
    
    protected void writeRequestHeaders(final HttpState state, final HttpConnection conn) throws IOException, HttpException {
        this.addRequestHeaders(state, conn);
        final Header[] headers = this.getRequestHeaders();
        for (int i = 0; i < headers.length; ++i) {
            final String s = headers[i].toExternalForm();
            if (Wire.enabled()) {
                Wire.output(s);
            }
            conn.print(s);
        }
    }
    
    protected void writeRequestLine(final HttpState state, final HttpConnection conn) throws IOException, HttpException {
        final String requestLine = this.getRequestLine(conn);
        if (Wire.enabled()) {
            Wire.output(requestLine);
        }
        conn.print(requestLine);
    }
    
    private String getRequestLine(final HttpConnection conn) {
        return generateRequestLine(conn, this.getName(), this.getPath(), this.getQueryString(), this.getHttpVersion().toString());
    }
    
    public HttpVersion getHttpVersion() {
        return this.version;
    }
    
    public void setHttpVersion(final HttpVersion version) {
        if (version == null) {
            this.version = HttpVersion.HTTP_1_1;
        }
        else {
            this.version = version;
        }
    }
    
    private static boolean canResponseHaveBody(final int status) {
        boolean result = true;
        if ((status >= 100 && status <= 199) || status == 204 || status == 304) {
            result = false;
        }
        return result;
    }
    
    public String getProxyAuthenticationRealm() {
        return this.proxyRealm;
    }
    
    public String getAuthenticationRealm() {
        return this.realm;
    }
    
    protected static String getContentCharSet(final Header contentheader) {
        String charset = null;
        if (contentheader != null) {
            final HeaderElement[] values = contentheader.getElements();
            if (values.length == 1) {
                final NameValuePair param = values[0].getParameterByName("charset");
                if (param != null) {
                    charset = param.getValue();
                }
            }
        }
        if (charset == null) {
            charset = "ISO-8859-1";
        }
        return charset;
    }
    
    public String getRequestCharSet() {
        return getContentCharSet(this.getRequestHeader("Content-Type"));
    }
    
    public String getResponseCharSet() {
        return getContentCharSet(this.getResponseHeader("Content-Type"));
    }
    
    public int getRecoverableExceptionCount() {
        return this.recoverableExceptionCount;
    }
    
    protected void responseBodyConsumed() {
        this.responseStream = null;
        this.responseConnection.setLastResponseInputStream(null);
        if (this.shouldCloseConnection(this.responseConnection)) {
            this.responseConnection.close();
        }
        this.ensureConnectionRelease();
    }
    
    private void ensureConnectionRelease() {
        if (this.responseConnection != null) {
            this.responseConnection.releaseConnection();
            this.responseConnection = null;
        }
    }
    
    public HostConfiguration getHostConfiguration() {
        return this.hostConfiguration;
    }
    
    public void setHostConfiguration(final HostConfiguration hostConfiguration) {
        this.hostConfiguration = hostConfiguration;
    }
    
    public MethodRetryHandler getMethodRetryHandler() {
        if (this.methodRetryHandler == null) {
            this.methodRetryHandler = new DefaultMethodRetryHandler();
        }
        return this.methodRetryHandler;
    }
    
    public void setMethodRetryHandler(final MethodRetryHandler handler) {
        this.methodRetryHandler = handler;
    }
    
    void fakeResponse(final StatusLine statusline, final HeaderGroup responseheaders, final InputStream responseStream) {
        this.used = true;
        this.statusLine = statusline;
        this.responseHeaders = responseheaders;
        this.responseBody = null;
        this.responseStream = responseStream;
    }
}
