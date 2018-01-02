// 
// Decompiled by Procyon v0.5.30
// 

package com.postx.web;

import java.io.InputStream;
import java.util.TimeZone;
import java.util.Hashtable;
import java.io.OutputStream;
import com.postx.payload.TOCEntry;
import com.postx.util.URLCode;
import java.io.Reader;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import com.postx.util.logging.Level;
import java.text.ParseException;
import java.io.PrintWriter;
import java.net.Socket;
import com.postx.util.FileMap;
import com.postx.util.MIMETypes;
import java.text.SimpleDateFormat;
import java.util.Date;
import com.postx.util.logging.Logger;

public class RequestHandler implements Runnable
{
    public static final String Ident = "$Id: RequestHandler.java,v 1.4 2011/01/25 21:51:19 blm Exp $";
    private static final Logger log;
    private static final boolean logInfo;
    private static final boolean logFine;
    private static final String className = "RequestHandler";
    private static final String eol = "\r\n";
    private static final Date lastMod;
    private static final String formattedLastMod;
    private static final SimpleDateFormat rfc822DateFormatter;
    private static final SimpleDateFormat rfc822DateParser;
    private static final SimpleDateFormat rfc850DateParser;
    private static final SimpleDateFormat asctimeDateParser;
    private MIMETypes mimeTypes;
    private WebServer server;
    private FileMap fileMap;
    private Socket requestSocket;
    
    private static boolean errorPage(final PrintWriter printWriter, int n, final boolean b, final boolean b2, final String s) {
        if (!b2) {
            String s2 = null;
            switch (n) {
                case 400: {
                    s2 = "Bad Request";
                    break;
                }
                case 403: {
                    s2 = "Forbidden";
                    break;
                }
                case 404: {
                    s2 = "Not Found";
                    break;
                }
                case 501: {
                    s2 = "Not Implemented";
                    break;
                }
                default: {
                    n = 500;
                    s2 = "Internal Server Error";
                    break;
                }
            }
            final String formatDate = formatDate(new Date());
            writeHeader(printWriter, "HTTP/1.0 " + n + " " + s2, null);
            writeHeader(printWriter, "Connection", "close");
            writeHeader(printWriter, "Date", formatDate);
            writeHeader(printWriter, "Content-Length", s.length() + "\r\n".length());
            writeHeader(printWriter, "Content-Type", "text/plain");
            writeHeader(printWriter, "Last-Modified", formatDate);
            if (!b) {
                printWriter.print("\r\n");
            }
        }
        if (!b) {
            printWriter.print(s);
            printWriter.print("\r\n");
        }
        return false;
    }
    
    private static Date parseDate(final String s) {
        try {
            synchronized (RequestHandler.rfc822DateParser) {
                // monitorexit(RequestHandler.rfc822DateParser)
                return RequestHandler.rfc822DateParser.parse(s);
            }
        }
        catch (ParseException ex) {
            try {
                synchronized (RequestHandler.rfc850DateParser) {
                    // monitorexit(RequestHandler.rfc850DateParser)
                    return RequestHandler.rfc850DateParser.parse(s);
                }
            }
            catch (ParseException ex2) {
                try {
                    synchronized (RequestHandler.asctimeDateParser) {
                        // monitorexit(RequestHandler.asctimeDateParser)
                        return RequestHandler.asctimeDateParser.parse(s);
                    }
                }
                catch (ParseException ex3) {
                    return null;
                }
            }
        }
    }
    
    private static String formatDate(final Date date) {
        synchronized (RequestHandler.rfc822DateFormatter) {
            // monitorexit(RequestHandler.rfc822DateFormatter)
            return RequestHandler.rfc822DateFormatter.format(new Date());
        }
    }
    
    RequestHandler(final WebServer server, final FileMap fileMap, final MIMETypes mimeTypes, final Socket requestSocket) {
        this.server = server;
        this.fileMap = fileMap;
        this.mimeTypes = mimeTypes;
        this.requestSocket = requestSocket;
        if (RequestHandler.logFine) {
            RequestHandler.log.fine("Created RequestHandler " + Thread.currentThread());
        }
    }
    
    private boolean handleRequest() {
        OutputStream outputStream;
        try {
            outputStream = this.requestSocket.getOutputStream();
        }
        catch (IOException ex) {
            if (RequestHandler.logInfo) {
                RequestHandler.log.log(Level.INFO, "Exception getting response stream", ex);
            }
            return false;
        }
        final PrintWriter printWriter = new PrintWriter(outputStream);
        boolean equals = false;
        boolean b = true;
        boolean b2 = false;
        try {
            final BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(this.requestSocket.getInputStream()));
            final String line;
            if ((line = bufferedReader.readLine()) == null) {
                return false;
            }
            if (RequestHandler.logFine) {
                RequestHandler.log.fine("Read request \"" + line + "\"");
            }
            int index;
            String substring;
            if ((index = line.indexOf(32)) == -1) {
                substring = line;
            }
            else {
                substring = line.substring(0, index++);
            }
            equals = substring.equals("HEAD");
            if (index == -1) {
                return errorPage(printWriter, 400, equals, true, "Malformed Request-Line, no Request-URI");
            }
            String s = null;
            Label_0552: {
                final int index2;
                if ((index2 = line.indexOf(32, index)) == -1) {
                    s = line.substring(index);
                }
                else {
                    if (index == index2) {
                        return errorPage(printWriter, 400, equals, false, "Malformed Request-Line, no Request-URI");
                    }
                    b = false;
                    s = line.substring(index, index2);
                    int n = index2 + 1;
                    if (n + 5 <= line.length() && line.substring(n, n + 5).equals("HTTP/")) {
                        final String s2 = line;
                        final int n2 = 46;
                        n += 5;
                        final int index3;
                        if ((index3 = s2.indexOf(n2, n)) != -1 && index3 != line.length() - 1) {
                            int int1;
                            int int2;
                            try {
                                if ((int1 = Integer.parseInt(line.substring(n, index3))) < 0) {
                                    throw new NumberFormatException("Major version can't be negative");
                                }
                                if ((int2 = Integer.parseInt(line.substring(index3 + 1))) < 0) {
                                    throw new NumberFormatException("Minor version can't be negative");
                                }
                            }
                            catch (NumberFormatException ex3) {
                                return errorPage(printWriter, 400, equals, false, "Malformed Request-Line, invalid HTTP-Version");
                            }
                            if (int1 != 1 || (int2 != 0 && int2 != 1)) {
                                return errorPage(printWriter, 501, equals, false, "Version " + int1 + "." + int2 + " not supported");
                            }
                            break Label_0552;
                        }
                    }
                    return errorPage(printWriter, 400, equals, false, "Malformed Request-Line, invalid HTTP-Version");
                }
            }
            if (!equals && !substring.equals("GET")) {
                return errorPage(printWriter, 501, equals, b, "Method \"" + substring + "\" not supported");
            }
            Hashtable<Object, String> headers = null;
            if (!b && (headers = (Hashtable<Object, String>)this.readHeaders(bufferedReader)) == null) {
                return errorPage(printWriter, 400, equals, b, "Malformed headers");
            }
            boolean b3 = true;
            final String s3;
            if (!equals && headers != null && (s3 = headers.get("if-modified-since")) != null) {
                final Date date = parseDate(s3);
                b3 = (date == null || date.before(RequestHandler.lastMod));
            }
            TOCEntry tocEntry = null;
            final String decode = URLCode.decode(s, 128);
            long n3 = 0L;
            if (decode.startsWith("/")) {
                if (decode.length() == 1) {
                    return errorPage(printWriter, 403, equals, b, "\"/\" isn't accessible");
                }
                if ((tocEntry = (TOCEntry)this.fileMap.get(decode.substring(1), 6)) != null) {
                    n3 = tocEntry.getLength();
                }
            }
            if (tocEntry == null) {
                if (RequestHandler.logFine) {
                    RequestHandler.log.fine(s + " requested, not found");
                }
                return errorPage(printWriter, 404, equals, b, "\"" + decode + "\" not found");
            }
            String s4 = null;
            if (!b) {
                writeHeader(printWriter, "HTTP/1.0 " + (b3 ? "200 OK" : "304 Not Modified"), null);
                b2 = true;
                writeHeader(printWriter, "Connection", "close");
                writeHeader(printWriter, "Date", formatDate(new Date()));
                if (b3) {
                    if (n3 > 0L) {
                        writeHeader(printWriter, "Content-Length", n3);
                    }
                    s4 = this.mimeTypes.contentType(decode);
                    if (tocEntry.getEncoding() != null && this.mimeTypes.needCharset(s4)) {
                        s4 = s4 + "; charset=" + tocEntry.getEncoding();
                    }
                    writeHeader(printWriter, "Content-Type", s4);
                    if (!equals) {
                        writeHeader(printWriter, "Last-Modified", RequestHandler.formattedLastMod);
                    }
                    if (tocEntry.isSecure()) {
                        writeHeader(printWriter, "Pragma", "no-cache");
                        writeHeader(printWriter, "Cache-Control", "no-cache");
                        writeHeader(printWriter, "Expires", "Thu Jan  1 00:00:00 GMT 1970");
                    }
                    printWriter.print("\r\n");
                }
                printWriter.flush();
            }
            if (b3) {
                synchronized (tocEntry) {
                    writeBody(outputStream, tocEntry.getInputStream());
                }
                // monitorexit(tocEntry)
            }
            if (RequestHandler.logFine) {
                RequestHandler.log.fine(s + " requested, returned " + tocEntry + ((s4 == null) ? "" : ("; " + s4)));
            }
            return true;
        }
        catch (IOException ex2) {
            RequestHandler.log.log(Level.FINER, "Unexpected exception", ex2);
            if (b2) {
                return false;
            }
            return errorPage(printWriter, 500, equals, b, "Internal error");
        }
        finally {
            try {
                outputStream.close();
            }
            catch (IOException ex4) {}
        }
    }
    
    private Hashtable readHeaders(final BufferedReader bufferedReader) throws IOException {
        final Hashtable<String, String> hashtable = new Hashtable<String, String>();
        String string = null;
        String line;
        do {
            line = bufferedReader.readLine();
            final char char1;
            if (line != null && line.length() > 0 && ((char1 = line.charAt(0)) == ' ' || char1 == '\t')) {
                if (string == null) {
                    RequestHandler.log.warning("Continuation line at start of headers");
                    return null;
                }
                string += line.substring(1);
            }
            else {
                if (string != null) {
                    final int index;
                    if ((index = string.indexOf(58)) == -1) {
                        RequestHandler.log.info("Header without a \":\"");
                        return null;
                    }
                    int n;
                    char char2;
                    for (n = index + 1; (char2 = string.charAt(n)) == ' ' || char2 == '\t'; ++n) {}
                    final String substring = string.substring(0, index);
                    final String substring2 = string.substring(n);
                    hashtable.put(substring.toLowerCase(), substring2);
                    if (RequestHandler.logFine) {
                        RequestHandler.log.fine("Read header \"" + substring + ": " + substring2 + "\"");
                    }
                }
                string = line;
            }
        } while (line != null && line.length() > 0);
        return hashtable;
    }
    
    static {
        log = Logger.global;
        logInfo = RequestHandler.log.isLoggable(Level.INFO);
        logFine = RequestHandler.log.isLoggable(Level.FINE);
        rfc822DateFormatter = new SimpleDateFormat("EEE, dd MMM yyyy HH:mm:ss 'GMT'");
        rfc822DateParser = new SimpleDateFormat("EEE, dd MMM yyyy HH:mm:ss 'GMT'");
        rfc850DateParser = new SimpleDateFormat("EEEE, dd-MMM-yy HH:mm:ss 'GMT'");
        asctimeDateParser = new SimpleDateFormat("EEE MMM dd HH:mm:ss yyyy");
        final TimeZone timeZone = TimeZone.getTimeZone("GMT");
        RequestHandler.rfc822DateFormatter.setTimeZone(timeZone);
        RequestHandler.rfc822DateParser.setLenient(false);
        RequestHandler.rfc822DateParser.setTimeZone(timeZone);
        RequestHandler.rfc850DateParser.setLenient(false);
        RequestHandler.rfc850DateParser.setTimeZone(timeZone);
        RequestHandler.asctimeDateParser.setLenient(false);
        RequestHandler.asctimeDateParser.setTimeZone(timeZone);
        lastMod = new Date();
        final long time = RequestHandler.lastMod.getTime();
        RequestHandler.lastMod.setTime(time - time % 1000L);
        formattedLastMod = formatDate(RequestHandler.lastMod);
    }
    
    private static void writeHeader(final PrintWriter printWriter, final String s, final long n) {
        writeHeader(printWriter, s, String.valueOf(n));
    }
    
    private static void writeHeader(final PrintWriter printWriter, final String s, final String s2) {
        printWriter.print(s);
        if (s2 != null) {
            printWriter.print(": ");
            printWriter.print(s2);
        }
        printWriter.print("\r\n");
        if (RequestHandler.logFine) {
            RequestHandler.log.fine("Wrote " + ((s2 != null) ? "header " : "response ") + "\"" + s + ((s2 != null) ? (": " + s2) : "") + "\"");
        }
    }
    
    public void run() {
        if (RequestHandler.logFine) {
            RequestHandler.log.entering("RequestHandler", "run", null);
        }
        if (this.handleRequest()) {
            this.server.incrementSuccesses();
        }
        try {
            this.requestSocket.close();
        }
        catch (Exception ex) {}
        if (RequestHandler.logFine) {
            RequestHandler.log.exiting("RequestHandler", "run");
        }
    }
    
    private static void writeBody(final OutputStream outputStream, final InputStream inputStream) throws IOException {
        if (RequestHandler.logFine) {
            RequestHandler.log.fine("Writing body from " + inputStream);
        }
        final byte[] array = new byte[16384];
        long n = 0L;
        int read;
        while ((read = inputStream.read(array)) >= 0) {
            if (read > 0) {
                outputStream.write(array, 0, read);
                n += read;
            }
        }
        if (RequestHandler.logFine) {
            RequestHandler.log.fine("Wrote " + n + " byte body");
        }
    }
}
