// 
// Decompiled by Procyon v0.5.30
// 

package anon.proxy;

import logging.LogHolder;
import logging.LogType;
import java.util.Enumeration;
import java.util.StringTokenizer;
import anon.util.JAPMessages;
import java.util.Vector;
import java.util.Hashtable;

public class HTTPProxyCallback implements ProxyCallback
{
    private static final boolean FIRE_EVENT = true;
    static final int MESSAGE_TYPE_REQUEST = 0;
    static final int MESSAGE_TYPE_RESPONSE = 1;
    static final String CRLF = "\r\n";
    static final String HTTP_HEADER_END = "\r\n\r\n";
    static final byte[] HTTP_HEADER_END_BYTES;
    static final String HTTP_HEADER_DELIM = ": ";
    static final String HTTP_START_LINE_KEY = "start-line";
    static final String HTTP_VERSION_PREFIX = "HTTP/";
    static final byte[] HTTP_VERSION_PREFIX_BYTES;
    static final String[] HTTP_REQUEST_METHODS;
    static final byte[][] HTTP_REQUEST_METHODS_BYTES;
    static final String MSG_INVALID_LINETERM_REQUEST = "httpFilter.invalidlineterm.request";
    static final String MSG_INVALID_LINETERM_RESPONSE = "httpFilter.invalidlineterm.response";
    public static final String HTTP_CONTENT_LENGTH = "Content-Length";
    public static final String HTTP_CONTENT_ENCODING = "Content-Encoding";
    public static final String HTTP_HOST = "Host";
    public static final String HTTP_USER_AGENT = "User-Agent";
    public static final String HTTP_ACCEPT = "Accept";
    public static final String HTTP_ACCEPT_LANGUAGE = "Accept-Language";
    public static final String HTTP_ACCEPT_ENCODING = "Accept-Encoding";
    public static final String HTTP_ACCEPT_CHARSET = "Accept-Charset";
    public static final String HTTP_KEEP_ALIVE = "Keep-Alive";
    public static final String HTTP_PROXY_CONNECTION = "Proxy-Connection";
    public static final String HTTP_CONNECTION = "Connection";
    public static final String HTTP_REFERER = "Referer";
    public static final String HTTP_CACHE_CONTROL = "Cache-Control";
    public static final String HTTP_PRAGMA = "Pragma";
    public static final String HTTP_RANGE = "Range";
    public static final String HTTP_IE_UA_CPU = "UA-CPU";
    private Hashtable m_connectionHTTPHeaders;
    private Hashtable m_unfinishedRequests;
    private Hashtable m_unfinishedResponses;
    private Hashtable m_downstreamBytes;
    private Hashtable m_upstreamBytes;
    private Vector m_httpConnectionListeners;
    private static final IHTTPHelper UPSTREAM_HELPER;
    private static final IHTTPHelper DOWNSTREAM_HELPER;
    
    public HTTPProxyCallback() {
        this.m_connectionHTTPHeaders = null;
        this.m_unfinishedRequests = null;
        this.m_unfinishedResponses = null;
        this.m_downstreamBytes = null;
        this.m_upstreamBytes = null;
        this.m_httpConnectionListeners = null;
        this.m_connectionHTTPHeaders = new Hashtable();
        this.m_unfinishedRequests = new Hashtable();
        this.m_unfinishedResponses = new Hashtable();
        this.m_downstreamBytes = new Hashtable();
        this.m_upstreamBytes = new Hashtable();
        this.m_httpConnectionListeners = new Vector();
    }
    
    public int handleUpstreamChunk(final AnonProxyRequest anonProxyRequest, final ProxyCallbackBuffer proxyCallbackBuffer) throws ProxyCallbackNotProcessableException {
        return this.handleStreamChunk(anonProxyRequest, proxyCallbackBuffer, 0, HTTPProxyCallback.UPSTREAM_HELPER);
    }
    
    public int handleDownstreamChunk(final AnonProxyRequest anonProxyRequest, final ProxyCallbackBuffer proxyCallbackBuffer) throws ProxyCallbackNotProcessableException {
        boolean b = false;
        synchronized (this) {
            final HTTPConnectionHeader httpConnectionHeader = this.m_connectionHTTPHeaders.get(anonProxyRequest);
            b = (httpConnectionHeader == null || !httpConnectionHeader.isResponseExpected());
        }
        return b ? 2 : this.handleStreamChunk(anonProxyRequest, proxyCallbackBuffer, 1, HTTPProxyCallback.DOWNSTREAM_HELPER);
    }
    
    private int handleStreamChunk(final AnonProxyRequest anonProxyRequest, final ProxyCallbackBuffer proxyCallbackBuffer, final int n, final IHTTPHelper ihttpHelper) throws ProxyCallbackNotProcessableException {
        final int modificationStartOffset = proxyCallbackBuffer.getModificationStartOffset();
        final int modificationEndOffset = proxyCallbackBuffer.getModificationEndOffset();
        final int n2 = modificationEndOffset - modificationStartOffset + 1;
        final byte[] chunk = proxyCallbackBuffer.getChunk();
        if (anonProxyRequest == null) {
            throw new NullPointerException("AnonProxyRequest must not be null!");
        }
        "\r\n\r\n".length();
        byte[] array = null;
        final Hashtable hashtable = (n == 0) ? this.m_unfinishedRequests : this.m_unfinishedResponses;
        String s = null;
        final Hashtable hashtable2 = (n == 0) ? this.m_upstreamBytes : this.m_downstreamBytes;
        synchronized (this) {
            s = hashtable.get(anonProxyRequest);
        }
        if (s != null) {
            if (s.length() > HTTPProxyCallback.HTTP_HEADER_END_BYTES.length - 1) {
                array = s.substring(s.length() - (HTTPProxyCallback.HTTP_HEADER_END_BYTES.length - 1)).getBytes();
            }
            else {
                array = s.getBytes();
            }
        }
        final int indexOfHTTPHeaderEnd = indexOfHTTPHeaderEnd(array, chunk, modificationStartOffset, modificationEndOffset);
        final int n3 = (indexOfHTTPHeaderEnd == -1) ? n2 : (indexOfHTTPHeaderEnd - modificationStartOffset);
        int n4 = n2;
        if (s != null || this.hasAlignedHTTPStartLine(chunk, modificationStartOffset, n3, n)) {
            n4 = n2 - n3;
            if (!this.extractHeaderParts(anonProxyRequest, ((s == null) ? "" : s) + new String(chunk, modificationStartOffset, n3), n)) {
                return 1;
            }
            HTTPConnectionHeader httpConnectionHeader = null;
            synchronized (this) {
                httpConnectionHeader = this.m_connectionHTTPHeaders.get(anonProxyRequest);
            }
            if (httpConnectionHeader != null && httpConnectionHeader.getRequestLine() != null) {
                final byte[] dumpHeader = ihttpHelper.dumpHeader(this, httpConnectionHeader, anonProxyRequest);
                this.countContentBytes(anonProxyRequest, n4, hashtable2, true);
                final int modificationStartOffset2 = dumpHeader.length + modificationStartOffset;
                final int n5 = modificationStartOffset2 + n4;
                final byte[] chunk2 = new byte[n5 + proxyCallbackBuffer.getTrailingDataLength()];
                proxyCallbackBuffer.copyLeadingData(chunk2);
                System.arraycopy(dumpHeader, 0, chunk2, modificationStartOffset, dumpHeader.length);
                System.arraycopy(chunk, modificationEndOffset + 1 - n4, chunk2, modificationStartOffset2, n4);
                proxyCallbackBuffer.copyTrailingData(chunk2, n5);
                proxyCallbackBuffer.setChunk(chunk2);
                proxyCallbackBuffer.setModificationStartOffset(modificationStartOffset2);
                proxyCallbackBuffer.setModificationEndOffset(n5 - 1);
                return 2;
            }
        }
        this.countContentBytes(anonProxyRequest, n4, hashtable2, true);
        return 2;
    }
    
    private synchronized long countContentBytes(final AnonProxyRequest anonRequest, final int n, final Hashtable hashtable, final boolean b) {
        long longValue = 0L;
        final Long n2 = hashtable.remove(anonRequest);
        if (n2 != null) {
            longValue = n2;
        }
        final long n3 = longValue + n;
        hashtable.put(anonRequest, new Long(n3));
        if (b) {
            final HTTPConnectionEvent httpConnectionEvent = new HTTPConnectionEvent();
            httpConnectionEvent.setAnonRequest(anonRequest);
            httpConnectionEvent.setConnectionHeader(this.m_connectionHTTPHeaders.get(anonRequest));
            if (hashtable == this.m_downstreamBytes) {
                httpConnectionEvent.setUpStreamContentBytes(this.getUpStreamContentBytes(anonRequest));
                httpConnectionEvent.setDownStreamContentBytes(n3);
                this.fireDownstreamContentBytesReceived(httpConnectionEvent);
            }
            else if (hashtable == this.m_upstreamBytes) {
                httpConnectionEvent.setDownStreamContentBytes(this.getDownStreamContentBytes(anonRequest));
                httpConnectionEvent.setUpStreamContentBytes(n3);
                this.fireUpstreamContentBytesReceived(httpConnectionEvent);
            }
        }
        return n3;
    }
    
    public synchronized long getUpStreamContentBytes(final AnonProxyRequest anonProxyRequest) {
        return this.getContentBytes(anonProxyRequest, this.m_upstreamBytes);
    }
    
    public synchronized long getDownStreamContentBytes(final AnonProxyRequest anonProxyRequest) {
        return this.getContentBytes(anonProxyRequest, this.m_downstreamBytes);
    }
    
    private long getContentBytes(final AnonProxyRequest anonProxyRequest, final Hashtable hashtable) {
        if (hashtable == null) {
            throw new NullPointerException("Bug: No count table specified for getContentBytes");
        }
        final Long n = hashtable.get(anonProxyRequest);
        return (n == null) ? 0L : n;
    }
    
    private synchronized HTTPConnectionEvent getEvent(final AnonProxyRequest anonProxyRequest) {
        return new HTTPConnectionEvent(this.m_connectionHTTPHeaders.get(anonProxyRequest), this.getUpStreamContentBytes(anonProxyRequest), this.getDownStreamContentBytes(anonProxyRequest), anonProxyRequest);
    }
    
    private synchronized boolean extractHeaderParts(final AnonProxyRequest anonProxyRequest, final String s, final int n) throws ProxyCallbackNotProcessableException {
        if (anonProxyRequest == null) {
            throw new NullPointerException("AnonProxyRequest must not be null!");
        }
        HTTPConnectionHeader httpConnectionHeader = this.m_connectionHTTPHeaders.get(anonProxyRequest);
        if (httpConnectionHeader != null) {
            if (n == 0 && httpConnectionHeader.isRequestFinished()) {
                httpConnectionHeader.clearRequest();
                this.m_upstreamBytes.remove(anonProxyRequest);
            }
            else if (n == 1 && httpConnectionHeader.isResponseFinished()) {
                httpConnectionHeader.clearResponse();
                this.m_downstreamBytes.remove(anonProxyRequest);
            }
        }
        if (httpConnectionHeader == null) {
            httpConnectionHeader = new HTTPConnectionHeader();
            this.m_connectionHTTPHeaders.put(anonProxyRequest, httpConnectionHeader);
        }
        final Hashtable hashtable = (n == 0) ? this.m_unfinishedRequests : this.m_unfinishedResponses;
        final int index = s.indexOf("\r\n\r\n");
        final String s2 = (index == -1) ? s : s.substring(0, index);
        if (!checkValidity(s2)) {
            String s3 = null;
            if (n == 0) {
                httpConnectionHeader.setRequestFinished(true);
                s3 = "httpFilter.invalidlineterm.request";
            }
            else if (n == 1) {
                httpConnectionHeader.setResponseFinished(true);
                s3 = "httpFilter.invalidlineterm.response";
            }
            hashtable.remove(anonProxyRequest);
            throw new HTTPHeaderParseException(400, n, JAPMessages.getString(s3));
        }
        if (index != -1) {
            this.parseHTTPHeader(s2, httpConnectionHeader, n);
            if (n == 0) {
                httpConnectionHeader.setRequestFinished(true);
                httpConnectionHeader.setResponseExpected(true);
            }
            else if (n == 1) {
                httpConnectionHeader.setResponseFinished(true);
                httpConnectionHeader.setResponseExpected(false);
            }
            hashtable.remove(anonProxyRequest);
            return true;
        }
        hashtable.put(anonProxyRequest, s);
        return false;
    }
    
    public static boolean checkValidity(final String s) {
        int index = -1;
        int index2 = -1;
        boolean b = false;
        boolean b2 = false;
        boolean b3 = false;
        for (boolean b4 = Math.max(index + 1, index2 + 1) >= s.length(); !b4; b4 = (Math.max(index + 1, index2 + 1) >= s.length())) {
            index = s.indexOf(13, index + 1);
            index2 = s.indexOf(10, index2 + 1);
            if (index == -1 && index2 == -1) {
                break;
            }
            b2 = (index2 != -1 && (index == -1 || index != index2 - 1));
            if (b2) {
                break;
            }
            b = (index != -1 && (index2 == -1 || index != index2 - 1));
            b3 = (index == s.length() - 1);
            if (b3) {
                break;
            }
        }
        return !b2 && (!b || b3);
    }
    
    private boolean hasAlignedHTTPStartLine(final String s, final int n) {
        return (n == 0) ? this.isRequest(s) : this.isResponse(s);
    }
    
    private boolean hasAlignedHTTPStartLine(final byte[] array, final int n, final int n2, final int n3) {
        return (n3 == 0) ? this.isRequest(array, n, n2) : this.isResponse(array, n, n2);
    }
    
    private boolean isRequest(final String s) {
        for (int i = 0; i < HTTPProxyCallback.HTTP_REQUEST_METHODS.length; ++i) {
            if (s.startsWith(HTTPProxyCallback.HTTP_REQUEST_METHODS[i])) {
                return true;
            }
        }
        return false;
    }
    
    private boolean isRequest(final byte[] array, final int n, final int n2) {
        int n3 = 1;
        for (int i = 0; i < HTTPProxyCallback.HTTP_REQUEST_METHODS_BYTES.length; ++i) {
            for (int min = Math.min(n2, HTTPProxyCallback.HTTP_REQUEST_METHODS_BYTES[i].length), j = n; j < min; ++j) {
                if (array[j] != HTTPProxyCallback.HTTP_REQUEST_METHODS_BYTES[i][j]) {
                    n3 = 0;
                    break;
                }
            }
            if (n3 != 0) {
                return true;
            }
            n3 = 1;
        }
        return false;
    }
    
    private boolean isResponse(final String s) {
        return s.startsWith("HTTP/");
    }
    
    private boolean isResponse(final byte[] array, final int n, final int n2) {
        for (int min = Math.min(n2, HTTPProxyCallback.HTTP_VERSION_PREFIX_BYTES.length), i = n; i < min; ++i) {
            if (array[i] != HTTPProxyCallback.HTTP_VERSION_PREFIX_BYTES[i]) {
                return false;
            }
        }
        return true;
    }
    
    public static int indexOfHTTPHeaderEnd(final byte[] array, final int n, final int n2) {
        for (int i = n; i <= n2 - (HTTPProxyCallback.HTTP_HEADER_END_BYTES.length - 1); ++i) {
            boolean b = true;
            for (int j = 0; j < HTTPProxyCallback.HTTP_HEADER_END_BYTES.length; ++j) {
                if (array[i + j] != HTTPProxyCallback.HTTP_HEADER_END_BYTES[j]) {
                    b = false;
                    break;
                }
            }
            if (b) {
                return i + HTTPProxyCallback.HTTP_HEADER_END_BYTES.length;
            }
        }
        return -1;
    }
    
    public static int indexOfHTTPHeaderEnd(final byte[] array, final byte[] array2, final int n, final int n2) {
        if (array != null) {
            final int length = array.length;
            final int n3 = (length >= HTTPProxyCallback.HTTP_HEADER_END_BYTES.length - 1) ? (length - (HTTPProxyCallback.HTTP_HEADER_END_BYTES.length - 1)) : 0;
            if (n2 + 1 + length < HTTPProxyCallback.HTTP_HEADER_END_BYTES.length) {
                return -1;
            }
            for (int i = n3; i < length; ++i) {
                final int n4 = HTTPProxyCallback.HTTP_HEADER_END_BYTES.length - (length - i);
                if (n4 > n2 - n + 1) {
                    return -1;
                }
                int n5 = 1;
                int n6;
                for (n6 = 0; i + n6 < length; ++n6) {
                    if (array[i + n6] != HTTPProxyCallback.HTTP_HEADER_END_BYTES[n6]) {
                        n5 = 0;
                        break;
                    }
                }
                if (n5 != 0) {
                    int n7;
                    for (n7 = n; n7 < n4 || n6 < HTTPProxyCallback.HTTP_HEADER_END_BYTES.length; ++n7) {
                        if (array2[n7] != HTTPProxyCallback.HTTP_HEADER_END_BYTES[n6++]) {
                            n5 = 0;
                            break;
                        }
                    }
                    if (n5 != 0) {
                        return n7;
                    }
                }
            }
        }
        return indexOfHTTPHeaderEnd(array2, n, n2);
    }
    
    private synchronized void parseHTTPHeader(final String s, final HTTPConnectionHeader httpConnectionHeader, final int n) {
        final StringTokenizer stringTokenizer = new StringTokenizer(s, "\r\n");
        if (stringTokenizer.countTokens() == 0) {
            return;
        }
        String trim = null;
        if (n == 0) {
            httpConnectionHeader.setRequestHeader("start-line", stringTokenizer.nextToken());
        }
        else if (n == 1) {
            httpConnectionHeader.setResponseHeader("start-line", stringTokenizer.nextToken());
        }
        while (stringTokenizer.hasMoreTokens()) {
            final String nextToken = stringTokenizer.nextToken();
            int n2 = nextToken.indexOf(": ");
            if (n2 == -1) {
                n2 = nextToken.indexOf("\n\n");
            }
            if (n2 != -1) {
                final String trim2 = nextToken.substring(0, n2).trim();
                if (n2 + 1 < nextToken.length()) {
                    trim = nextToken.substring(n2 + 1).trim();
                }
                if (trim2 == null || trim == null) {
                    continue;
                }
                if (n == 0) {
                    httpConnectionHeader.setRequestHeader(trim2, trim);
                }
                else {
                    if (n != 1) {
                        continue;
                    }
                    httpConnectionHeader.setResponseHeader(trim2, trim);
                }
            }
        }
    }
    
    public synchronized void addHTTPConnectionListener(final AbstractHTTPConnectionListener abstractHTTPConnectionListener) {
        if (!this.m_httpConnectionListeners.contains(abstractHTTPConnectionListener)) {
            int n;
            for (n = 0; n < this.m_httpConnectionListeners.size() && ((AbstractHTTPConnectionListener)this.m_httpConnectionListeners.elementAt(n)).getPriority() < abstractHTTPConnectionListener.getPriority(); ++n) {}
            this.m_httpConnectionListeners.insertElementAt(abstractHTTPConnectionListener, n);
        }
    }
    
    public synchronized void removeHTTPConnectionListener(final AbstractHTTPConnectionListener abstractHTTPConnectionListener) {
        this.m_httpConnectionListeners.removeElement(abstractHTTPConnectionListener);
    }
    
    public synchronized void removeAllHTTPConnectionListeners() {
        this.m_httpConnectionListeners.removeAllElements();
    }
    
    public synchronized void fireRequestHeadersReceived(final HTTPConnectionEvent httpConnectionEvent) {
        final Enumeration<AbstractHTTPConnectionListener> elements = this.m_httpConnectionListeners.elements();
        while (elements.hasMoreElements()) {
            final AbstractHTTPConnectionListener abstractHTTPConnectionListener = elements.nextElement();
            if (abstractHTTPConnectionListener != null) {
                abstractHTTPConnectionListener.requestHeadersReceived(httpConnectionEvent);
            }
        }
    }
    
    public synchronized void fireResponseHeadersReceived(final HTTPConnectionEvent httpConnectionEvent) {
        final Enumeration<AbstractHTTPConnectionListener> elements = this.m_httpConnectionListeners.elements();
        while (elements.hasMoreElements()) {
            final AbstractHTTPConnectionListener abstractHTTPConnectionListener = elements.nextElement();
            if (abstractHTTPConnectionListener != null) {
                abstractHTTPConnectionListener.responseHeadersReceived(httpConnectionEvent);
            }
        }
    }
    
    public synchronized void fireDownstreamContentBytesReceived(final HTTPConnectionEvent httpConnectionEvent) {
        final Enumeration<AbstractHTTPConnectionListener> elements = this.m_httpConnectionListeners.elements();
        while (elements.hasMoreElements()) {
            final AbstractHTTPConnectionListener abstractHTTPConnectionListener = elements.nextElement();
            if (abstractHTTPConnectionListener != null) {
                abstractHTTPConnectionListener.downstreamContentBytesReceived(httpConnectionEvent);
            }
        }
    }
    
    public synchronized void fireUpstreamContentBytesReceived(final HTTPConnectionEvent httpConnectionEvent) {
        final Enumeration<AbstractHTTPConnectionListener> elements = this.m_httpConnectionListeners.elements();
        while (elements.hasMoreElements()) {
            final AbstractHTTPConnectionListener abstractHTTPConnectionListener = elements.nextElement();
            if (abstractHTTPConnectionListener != null) {
                abstractHTTPConnectionListener.upstreamContentBytesReceived(httpConnectionEvent);
            }
        }
    }
    
    public synchronized void closeRequest(final AnonProxyRequest anonProxyRequest) {
        final HTTPConnectionHeader httpConnectionHeader = this.m_connectionHTTPHeaders.get(anonProxyRequest);
        if (httpConnectionHeader != null) {
            httpConnectionHeader.clearRequest();
            httpConnectionHeader.clearResponse();
            this.m_upstreamBytes.remove(anonProxyRequest);
            this.m_downstreamBytes.remove(anonProxyRequest);
            this.m_connectionHTTPHeaders.remove(anonProxyRequest);
        }
    }
    
    static {
        HTTP_HEADER_END_BYTES = new byte[] { 13, 10, 13, 10 };
        HTTP_VERSION_PREFIX_BYTES = "HTTP/".getBytes();
        HTTP_REQUEST_METHODS = new String[] { "GET", "POST", "CONNECT", "HEAD", "PUT", "OPTIONS", "DELETE", "TRACE" };
        HTTP_REQUEST_METHODS_BYTES = new byte[HTTPProxyCallback.HTTP_REQUEST_METHODS.length][];
        for (int i = 0; i < HTTPProxyCallback.HTTP_REQUEST_METHODS.length; ++i) {
            HTTPProxyCallback.HTTP_REQUEST_METHODS_BYTES[i] = HTTPProxyCallback.HTTP_REQUEST_METHODS[i].getBytes();
        }
        UPSTREAM_HELPER = new IHTTPHelper() {
            public byte[] dumpHeader(final HTTPProxyCallback httpProxyCallback, final HTTPConnectionHeader httpConnectionHeader, final AnonProxyRequest anonProxyRequest) {
                httpProxyCallback.fireRequestHeadersReceived(httpProxyCallback.getEvent(anonProxyRequest));
                return httpConnectionHeader.dumpRequestHeaders();
            }
        };
        DOWNSTREAM_HELPER = new IHTTPHelper() {
            public byte[] dumpHeader(final HTTPProxyCallback httpProxyCallback, final HTTPConnectionHeader httpConnectionHeader, final AnonProxyRequest anonProxyRequest) {
                httpProxyCallback.fireResponseHeadersReceived(httpProxyCallback.getEvent(anonProxyRequest));
                return httpConnectionHeader.dumpResponseHeaders();
            }
        };
    }
    
    public final class HTTPConnectionHeader
    {
        private Hashtable reqHeaders;
        private Hashtable resHeaders;
        private Vector reqHeaderOrder;
        private Vector resHeaderOrder;
        private boolean requestFinished;
        private boolean responseFinished;
        private boolean responseExpected;
        
        public HTTPConnectionHeader() {
            this.reqHeaders = new Hashtable();
            this.resHeaders = new Hashtable();
            this.reqHeaderOrder = new Vector();
            this.resHeaderOrder = new Vector();
            this.requestFinished = false;
            this.responseFinished = false;
            this.responseExpected = false;
        }
        
        private synchronized boolean isResponseExpected() {
            return this.responseExpected;
        }
        
        private synchronized void setResponseExpected(final boolean responseExpected) {
            this.responseExpected = responseExpected;
        }
        
        public synchronized boolean isResponseFinished() {
            return this.responseFinished;
        }
        
        private synchronized void setResponseFinished(final boolean responseFinished) {
            this.responseFinished = responseFinished;
        }
        
        private synchronized boolean isRequestFinished() {
            return this.requestFinished;
        }
        
        public synchronized void setRequestFinished(final boolean requestFinished) {
            this.requestFinished = requestFinished;
        }
        
        protected synchronized void setRequestHeader(final String s, final String s2) {
            this.setHeader(this.reqHeaders, this.reqHeaderOrder, s, s2);
        }
        
        protected synchronized void setResponseHeader(final String s, final String s2) {
            this.setHeader(this.resHeaders, this.resHeaderOrder, s, s2);
        }
        
        public synchronized void replaceRequestHeader(final String s, final String s2) {
            this.replaceHeader(this.reqHeaders, this.reqHeaderOrder, s, s2);
        }
        
        public synchronized void replaceResponseHeader(final String s, final String s2) {
            this.replaceHeader(this.resHeaders, this.resHeaderOrder, s, s2);
        }
        
        public synchronized String getRequestLine() {
            return this.getStartLine(this.reqHeaders);
        }
        
        public synchronized String getResponseLine() {
            return this.getStartLine(this.resHeaders);
        }
        
        public synchronized void replaceResponseLine(final String s) {
            final Vector<String> vector = new Vector<String>();
            vector.addElement(s);
            this.resHeaders.put("start-line".toLowerCase(), vector);
        }
        
        public synchronized String[] getRequestHeader(final String s) {
            return this.getHeader(this.reqHeaders, s);
        }
        
        public synchronized String[] getResponseHeader(final String s) {
            return this.getHeader(this.resHeaders, s);
        }
        
        public synchronized String[] removeRequestHeader(final String s) {
            return this.removeHeader(this.reqHeaders, this.reqHeaderOrder, s);
        }
        
        public synchronized String[] removeResponseHeader(final String s) {
            return this.removeHeader(this.resHeaders, this.resHeaderOrder, s);
        }
        
        protected synchronized void clearRequest() {
            this.clearHeader(this.reqHeaders, this.reqHeaderOrder);
        }
        
        protected synchronized void clearResponse() {
            this.clearHeader(this.resHeaders, this.resHeaderOrder);
        }
        
        private void setHeader(final Hashtable hashtable, final Vector vector, final String s, final String s2) {
            Vector<?> vector2 = hashtable.get(s.toLowerCase());
            if (vector2 == null) {
                boolean b = true;
                final Enumeration<String> elements = vector.elements();
                while (elements.hasMoreElements()) {
                    if (elements.nextElement().equalsIgnoreCase(s)) {
                        b = false;
                    }
                }
                if (b) {
                    vector.addElement(s);
                }
                vector2 = new Vector<Object>();
            }
            vector2.addElement(s2);
            hashtable.put(s.toLowerCase(), vector2);
        }
        
        private void replaceHeader(final Hashtable hashtable, final Vector vector, final String s, final String s2) {
            this.removeHeader(hashtable, vector, s);
            this.setHeader(hashtable, vector, s, s2);
        }
        
        private String[] getHeader(final Hashtable hashtable, final String s) {
            return this.valuesToArray(hashtable.get(s.toLowerCase()));
        }
        
        private String[] removeHeader(final Hashtable hashtable, final Vector vector, final String s) {
            return this.valuesToArray(hashtable.remove(s.toLowerCase()));
        }
        
        private void clearHeader(final Hashtable hashtable, final Vector vector) {
            hashtable.clear();
            vector.removeAllElements();
        }
        
        private String getStartLine(final Hashtable hashtable) {
            final Vector<String> vector = hashtable.get("start-line".toLowerCase());
            if (vector == null || vector.size() == 0) {
                LogHolder.log(3, LogType.FILTER, "Invalid request because it contains no startline");
                return null;
            }
            if (vector.size() > 1) {
                String string = "";
                for (int i = 0; i < vector.size(); ++i) {
                    string = string + (Object)vector.elementAt(i) + "\n";
                }
                LogHolder.log(3, LogType.FILTER, "This HTTP message seems to be invalid, because it has multiple start lines:\n" + string);
            }
            return vector.elementAt(0);
        }
        
        private String[] valuesToArray(final Vector vector) {
            if (vector == null) {
                return null;
            }
            final int size = vector.size();
            if (size == 0) {
                return null;
            }
            final String[] array = new String[size];
            final Enumeration<String> elements = vector.elements();
            int n = 0;
            while (elements.hasMoreElements()) {
                array[n] = elements.nextElement();
                ++n;
            }
            return array;
        }
        
        private byte[] dumpRequestHeaders() {
            return this.dumpHeaders(this.reqHeaders, this.reqHeaderOrder);
        }
        
        private byte[] dumpResponseHeaders() {
            return this.dumpHeaders(this.resHeaders, this.resHeaderOrder);
        }
        
        private byte[] dumpHeaders(final Hashtable hashtable, final Vector vector) {
            String s = "";
            final Enumeration<String> elements = vector.elements();
            while (elements.hasMoreElements()) {
                final String s2 = elements.nextElement();
                if (s2.equalsIgnoreCase("start-line")) {
                    if (!s.equals("")) {
                        LogHolder.log(3, LogType.FILTER, "HTTP startline set after Message-Header. This is a Bug. please report this.");
                        throw new IllegalStateException("HTTP startline set after Message-Header. This is a Bug. please report this.");
                    }
                    s = s + this.getStartLine(hashtable) + "\r\n";
                }
                else {
                    final String[] header = this.getHeader(hashtable, s2);
                    if (header == null) {
                        continue;
                    }
                    for (int i = 0; i < header.length; ++i) {
                        s = s + s2 + ": " + header[i] + "\r\n";
                    }
                }
            }
            final String string = s + "\r\n";
            if (LogHolder.isLogged(6, LogType.FILTER)) {
                LogHolder.log(6, LogType.FILTER, Thread.currentThread().getName() + ": header dump:\n" + string);
            }
            return string.getBytes();
        }
    }
    
    private interface IHTTPHelper
    {
        byte[] dumpHeader(final HTTPProxyCallback p0, final HTTPConnectionHeader p1, final AnonProxyRequest p2);
    }
}
