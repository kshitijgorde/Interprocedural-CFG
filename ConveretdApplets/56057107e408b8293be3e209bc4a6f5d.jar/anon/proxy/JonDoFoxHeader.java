// 
// Decompiled by Procyon v0.5.30
// 

package anon.proxy;

import java.util.StringTokenizer;

public final class JonDoFoxHeader extends AbstractHTTPConnectionListener
{
    static final String HTTP_ENCODING_GZIP = "gzip";
    static final String HTTP_ENCODING_DEFLATE = "deflate";
    public static final String USER_AGENT_JONDOFOX = "Mozilla/5.0 (en-US; rv:1.9.1.2) Gecko/20090729 Firefox/3.5.2";
    public static final String USER_AGENT_JONDOFOX_OLD = "Mozilla/5.0 (en-US; rv:1.9.0.7) Gecko/2009021910 Firefox/3.0.7";
    public static final String USER_AGENT_TORBUTTON = "Mozilla/5.0 (Windows; U; Windows NT 5.1; en-US; rv:1.9.0.7) Gecko/2009021910 Firefox/3.0.7";
    public static final String USER_AGENT_TORBUTTON_OLD = "Mozilla/5.0 (Windows; U; Windows NT 5.1; en-US; rv:1.8.1.16) Gecko/20080702 Firefox/2.0.0.16";
    public static final String JONDOFOX_LANGUAGE = "en";
    public static final String JONDOFOX_LANGUAGE_NEW = "en-US";
    public static final String JONDOFOX_CHARSET = "utf-8,*";
    public static final String JONDOFOX_CONTENT_TYPES = "*/*";
    public static final String JONDOFOX_ENCODING = "gzip,deflate";
    
    public JonDoFoxHeader(final int n) {
        super(n);
    }
    
    public void handleRequest(final HTTPConnectionEvent httpConnectionEvent) {
    }
    
    public void downstreamContentBytesReceived(final HTTPConnectionEvent httpConnectionEvent) {
    }
    
    private String parseDomain(final String s) {
        String substring = null;
        if (s != null) {
            final int index = s.indexOf("//");
            if (index != -1) {
                final int index2 = s.indexOf(47, index + 2);
                if (index2 != -1) {
                    substring = s.substring(0, index2 + 1);
                }
            }
        }
        return substring;
    }
    
    public void requestHeadersReceived(final HTTPConnectionEvent httpConnectionEvent) {
        if (httpConnectionEvent == null) {
            return;
        }
        final HTTPProxyCallback.HTTPConnectionHeader connectionHeader = httpConnectionEvent.getConnectionHeader();
        if (connectionHeader != null) {
            final String requestLine = connectionHeader.getRequestLine();
            if (requestLine != null) {
                final int index = requestLine.indexOf(" ");
                if (index != -1) {
                    final String domain = this.parseDomain(requestLine.substring(index + 1));
                    if (domain != null) {
                        final String[] requestHeader = connectionHeader.getRequestHeader("Referer");
                        String s;
                        if (requestHeader == null || requestHeader.length != 1) {
                            s = null;
                        }
                        else {
                            s = this.parseDomain(requestHeader[0]);
                            if (s != null && s.endsWith("/")) {
                                s = s.substring(0, s.length() - 1);
                            }
                        }
                        final StringTokenizer stringTokenizer = new StringTokenizer(domain, ".");
                        while (stringTokenizer.countTokens() > 2) {
                            stringTokenizer.nextToken();
                        }
                        String s2 = "";
                        while (stringTokenizer.countTokens() > 0) {
                            s2 += stringTokenizer.nextToken();
                            if (stringTokenizer.countTokens() > 0) {
                                s2 += ".";
                            }
                        }
                        if (s2.endsWith("/")) {
                            s2 = s2.substring(0, s2.length() - 1);
                        }
                        if (s == null || !s.endsWith(s2) || s2.trim().length() == 0) {
                            connectionHeader.replaceRequestHeader("Referer", domain);
                        }
                    }
                }
            }
            connectionHeader.replaceRequestHeader("User-Agent", "Mozilla/5.0 (en-US; rv:1.9.1.2) Gecko/20090729 Firefox/3.5.2");
            if (!httpConnectionEvent.getConnectionHeader().getRequestLine().startsWith("CONNECT")) {
                connectionHeader.replaceRequestHeader("Accept-Language", "en");
                connectionHeader.replaceRequestHeader("Accept-Charset", "utf-8,*");
                connectionHeader.replaceRequestHeader("Accept", "*/*");
                httpConnectionEvent.getAnonRequest().setInternalEncodingRequired(detectInternaEncodingRequired(connectionHeader.getRequestHeader("Accept-Encoding")));
                httpConnectionEvent.getAnonRequest().setContentEncodings(null);
                connectionHeader.replaceRequestHeader("Accept-Encoding", "gzip,deflate");
                connectionHeader.removeRequestHeader("UA-CPU");
            }
        }
    }
    
    public void responseHeadersReceived(final HTTPConnectionEvent httpConnectionEvent) {
        final AnonProxyRequest anonRequest = httpConnectionEvent.getAnonRequest();
        final HTTPProxyCallback.HTTPConnectionHeader connectionHeader = httpConnectionEvent.getConnectionHeader();
        if (anonRequest.isInternalEncodingRequired()) {
            final String[] responseHeader = connectionHeader.getResponseHeader("Content-Encoding");
            if (responseHeader != null) {
                anonRequest.setContentEncodings(responseHeader);
                connectionHeader.removeResponseHeader("Content-Encoding");
                connectionHeader.removeResponseHeader("Content-Length");
            }
            else {
                anonRequest.setInternalEncodingRequired(false);
            }
        }
    }
    
    private static boolean detectInternaEncodingRequired(final String[] array) {
        boolean equals = false;
        boolean equals2 = false;
        if (array != null) {
            for (int i = 0; i < array.length; ++i) {
                final StringTokenizer stringTokenizer = new StringTokenizer(array[i], ",");
                while (stringTokenizer.hasMoreTokens()) {
                    final String trim = stringTokenizer.nextToken().trim();
                    if (!equals) {
                        equals = trim.equals("gzip");
                    }
                    if (!equals2) {
                        equals2 = trim.trim().equals("deflate");
                    }
                }
            }
        }
        return !equals || !equals2;
    }
    
    public void upstreamContentBytesReceived(final HTTPConnectionEvent httpConnectionEvent) {
    }
}
