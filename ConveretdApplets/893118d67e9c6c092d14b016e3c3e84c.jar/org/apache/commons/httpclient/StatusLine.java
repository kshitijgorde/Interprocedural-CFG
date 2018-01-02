// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.commons.httpclient;

public class StatusLine
{
    private final String httpVersion;
    private final String reasonPhrase;
    private final String statusLine;
    private final int statusCode;
    
    public StatusLine(final String statusLine) throws HttpException {
        this.statusLine = new String(statusLine);
        final int length = statusLine.length();
        if (!statusLine.startsWith("HTTP")) {
            throw new ProtocolException("Status-Line '" + statusLine + "' does not start with HTTP");
        }
        int at = statusLine.indexOf(" ");
        if (at <= 0) {
            throw new ProtocolException("Unable to parse HTTP-Version from the status line: '" + statusLine + "'");
        }
        this.httpVersion = statusLine.substring(0, at).toUpperCase();
        while (statusLine.charAt(at) == ' ') {
            ++at;
        }
        int to = statusLine.indexOf(" ", at);
        if (to < 0) {
            to = length;
        }
        try {
            this.statusCode = Integer.parseInt(statusLine.substring(at, to));
        }
        catch (NumberFormatException e) {
            throw new ProtocolException("Unable to parse status code from status line: '" + statusLine + "'");
        }
        at = to + 1;
        try {
            if (at < length) {
                this.reasonPhrase = statusLine.substring(at).trim();
            }
            else {
                this.reasonPhrase = "";
            }
        }
        catch (StringIndexOutOfBoundsException e2) {
            throw new ProtocolException("Status text not specified: '" + statusLine + "'");
        }
    }
    
    public final String getHttpVersion() {
        return this.httpVersion;
    }
    
    public final String getReasonPhrase() {
        return this.reasonPhrase;
    }
    
    public final int getStatusCode() {
        return this.statusCode;
    }
    
    public final String toString() {
        return this.statusLine;
    }
}
