// 
// Decompiled by Procyon v0.5.30
// 

package anon.infoservice;

import java.util.Date;
import java.util.TimeZone;
import java.text.SimpleDateFormat;
import java.util.Locale;
import anon.util.MyStringBuilder;
import java.text.DateFormat;
import java.io.UnsupportedEncodingException;
import org.w3c.dom.Node;
import anon.util.XMLUtil;
import org.w3c.dom.Document;

public final class HttpResponseStructure
{
    public static final int HTTP_RETURN_OK = 200;
    public static final int HTTP_RETURN_ACCEPTED = 202;
    public static final int HTTP_RETURN_BAD_REQUEST = 400;
    public static final int HTTP_RETURN_NOT_FOUND = 404;
    public static final int HTTP_RETURN_INTERNAL_SERVER_ERROR = 500;
    public static final int HTTP_TYPE_TEXT_PLAIN = 0;
    public static final int HTTP_TYPE_TEXT_HTML = 1;
    public static final int HTTP_TYPE_TEXT_XML = 2;
    public static final int HTTP_TYPE_APPLICATION_JNLP = 10;
    public static final int HTTP_ENCODING_PLAIN = 0;
    public static final int HTTP_ENCODING_ZLIB = 1;
    public static final int HTTP_ENCODING_GZIP = 2;
    public static final int HTTP_TYPE_NO_TYPE = -1;
    public static final String HTTP_11_STRING = "HTTP/1.1 ";
    public static final String HTTP_CRLF_STRING = "\r\n";
    public static final String HTTP_RETURN_OK_STRING = "200 OK";
    public static final String HTTP_RETURN_ACCEPTED_STRING = "202 Accepted";
    public static final String HTTP_RETURN_BAD_REQUEST_STRING = "400 Bad Request";
    public static final String HTTP_RETURN_NOT_FOUND_STRING = "404 Not Found";
    public static final String HTTP_RETURN_INTERNAL_SERVER_ERROR_STRING = "500 Internal Server Error";
    public static final String HTTP_HEADER_TYPE_STRING = "Content-type: ";
    public static final String HTTP_HEADER_ENCODING_STRING = "Content-Encoding: ";
    public static final String HTTP_HEADER_LENGTH_STRING = "Content-length: ";
    public static final String HTTP_HEADER_DATE_STRING = "Date: ";
    public static final String HTTP_HEADER_EXPIRES_STRING = "Expires: ";
    public static final String HTTP_HEADER_CACHE_CONTROL_STRING = "Cache-Control: ";
    public static final String HTTP_HEADER_PRAGMA_STRING = "Pragma: ";
    public static final String HTTP_HEADER_CACHE_CONTROL_STRINGS = "Cache-Control: no-cache\r\nPragma: no-cache\r\n";
    public static final String HTTP_ENCODING_ZLIB_STRING = "deflate";
    public static final String HTTP_ENCODING_GZIP_STRING = "gzip";
    public static final String HTTP_TYPE_APPLICATION_JNLP_STRING = "application/x-java-jnlp-file";
    public static final String HTTP_TYPE_TEXT_PLAIN_STRING = "text/plain";
    public static final String HTTP_TYPE_TEXT_HTML_STRING = "text/html";
    public static final String HTTP_TYPE_TEXT_XML_STRING = "text/xml";
    public static final String HTML_NOT_FOUND = "<HTML><TITLE>404 File Not Found</TITLE><H1>404 File Not Found</H1><P>File not found on this server.</P></HTML>";
    public static final String HTML_BAD_REQUEST = "<HTML><TITLE>400 Bad Request</TITLE><H1>400 Bad Request</H1><P>Your request has been rejected by the server.</P></HTML>";
    public static final String HTML_INTERNAL_SERVER_ERROR = "<HTML><TITLE>500 Internal Server Error</TITLE><H1>500 Internal Server Error</H1><P>Error while processing the request on the server.</P></HTML>";
    private byte[] m_httpReturnData;
    
    public HttpResponseStructure(final int n) {
        if (n == 200) {
            this.m_httpReturnData = this.createHttpMessage(200, -1, 0, null, false);
        }
        else if (n == 202) {
            this.m_httpReturnData = this.createHttpMessage(202, -1, 0, null, false);
        }
        else if (n == 400) {
            this.m_httpReturnData = this.createHttpMessage(400, 1, 0, "<HTML><TITLE>400 Bad Request</TITLE><H1>400 Bad Request</H1><P>Your request has been rejected by the server.</P></HTML>".getBytes(), false);
        }
        else if (n == 404) {
            this.m_httpReturnData = this.createHttpMessage(404, 1, 0, "<HTML><TITLE>404 File Not Found</TITLE><H1>404 File Not Found</H1><P>File not found on this server.</P></HTML>".getBytes(), false);
        }
        else {
            this.m_httpReturnData = this.createHttpMessage(500, 1, 0, "<HTML><TITLE>500 Internal Server Error</TITLE><H1>500 Internal Server Error</H1><P>Error while processing the request on the server.</P></HTML>".getBytes(), false);
        }
    }
    
    public HttpResponseStructure(final Document document) {
        this(document, 0);
    }
    
    public HttpResponseStructure(final Document document, final int n) {
        final String string = XMLUtil.toString(document);
        if (string == null) {
            this.m_httpReturnData = this.createHttpMessage(500, 1, 0, "<HTML><TITLE>500 Internal Server Error</TITLE><H1>500 Internal Server Error</H1><P>Error while processing the request on the server.</P></HTML>".getBytes(), false);
        }
        else {
            try {
                this.m_httpReturnData = this.createHttpMessage(200, 2, n, string.getBytes("UTF8"), false);
            }
            catch (UnsupportedEncodingException ex) {
                this.m_httpReturnData = this.createHttpMessage(200, 2, n, string.getBytes(), false);
            }
        }
    }
    
    public HttpResponseStructure(final int n, final int n2, final String s) {
        try {
            this.m_httpReturnData = this.createHttpMessage(200, n, n2, s.getBytes("UTF8"), false);
        }
        catch (UnsupportedEncodingException ex) {
            this.m_httpReturnData = this.createHttpMessage(200, n, n2, s.getBytes(), false);
        }
    }
    
    public HttpResponseStructure(final int n, final int n2, final byte[] array) {
        this.m_httpReturnData = this.createHttpMessage(200, n, n2, array, false);
    }
    
    public HttpResponseStructure(final int n, final int n2, final String s, final boolean b) {
        this.m_httpReturnData = this.createHttpMessage(200, n, n2, s.getBytes(), b);
    }
    
    public HttpResponseStructure(final int n, final String s) {
        this.m_httpReturnData = this.createHttpMessage(n, 1, 0, s.getBytes(), false);
    }
    
    public byte[] getResponseData() {
        return this.m_httpReturnData;
    }
    
    private byte[] createHttpMessage(final int n, final int n2, final int n3, final byte[] array, final boolean b) {
        return this.createHttpMessage(n, n2, n3, array, b, null);
    }
    
    private byte[] createHttpMessage(final int n, final int n2, final int n3, final byte[] array, final boolean b, DateFormat dateFormat) {
        final MyStringBuilder myStringBuilder = new MyStringBuilder(2048);
        myStringBuilder.append("HTTP/1.1 ");
        if (n == 200) {
            myStringBuilder.append("200 OK");
        }
        else if (n == 202) {
            myStringBuilder.append("202 Accepted");
        }
        else if (n == 400) {
            myStringBuilder.append("400 Bad Request");
        }
        else if (n == 404) {
            myStringBuilder.append("404 Not Found");
        }
        else if (n == 500) {
            myStringBuilder.append("500 Internal Server Error");
        }
        myStringBuilder.append("\r\n");
        if (array != null) {
            myStringBuilder.append("Content-length: ");
            myStringBuilder.append(array.length);
            myStringBuilder.append("\r\n");
        }
        if (n2 != -1) {
            myStringBuilder.append("Content-type: ");
            if (n2 == 0) {
                myStringBuilder.append("text/plain");
            }
            else if (n2 == 1) {
                myStringBuilder.append("text/html");
            }
            else if (n2 == 2) {
                myStringBuilder.append("text/xml");
            }
            else if (n2 == 10) {
                myStringBuilder.append("application/x-java-jnlp-file");
            }
            myStringBuilder.append("\r\n");
        }
        if (n3 != 0) {
            myStringBuilder.append("Content-Encoding: ");
            if (n3 == 1) {
                myStringBuilder.append("deflate");
            }
            else if (n3 == 2) {
                myStringBuilder.append("gzip");
            }
            myStringBuilder.append("\r\n");
        }
        if (dateFormat == null) {
            dateFormat = new SimpleDateFormat("EEE, dd MMM yyyy HH:mm:ss zzz", Locale.US);
            dateFormat.setTimeZone(TimeZone.getTimeZone("GMT"));
        }
        final String format = dateFormat.format(new Date());
        myStringBuilder.append("Expires: ");
        myStringBuilder.append(format);
        myStringBuilder.append("\r\n");
        myStringBuilder.append("Date: ");
        myStringBuilder.append(format);
        myStringBuilder.append("\r\n");
        myStringBuilder.append("Cache-Control: no-cache\r\nPragma: no-cache\r\n");
        myStringBuilder.append("\r\n");
        byte[] array3;
        if (array != null && !b) {
            byte[] array2;
            try {
                array2 = myStringBuilder.toString().getBytes("UTF8");
            }
            catch (UnsupportedEncodingException ex) {
                array2 = myStringBuilder.toString().getBytes();
            }
            array3 = new byte[array2.length + array.length];
            System.arraycopy(array2, 0, array3, 0, array2.length);
            System.arraycopy(array, 0, array3, array2.length, array.length);
        }
        else {
            try {
                array3 = myStringBuilder.toString().getBytes("UTF8");
            }
            catch (UnsupportedEncodingException ex2) {
                array3 = myStringBuilder.toString().getBytes();
            }
        }
        return array3;
    }
}
