// 
// Decompiled by Procyon v0.5.30
// 

package com.fluendo.plugin;

import com.fluendo.jst.ElementFactory;
import com.fluendo.jst.Buffer;
import com.fluendo.jst.Event;
import com.fluendo.jst.Object;
import com.fluendo.jst.Message;
import java.io.IOException;
import java.net.URLConnection;
import java.util.Locale;
import java.text.MessageFormat;
import com.fluendo.utils.Base64Converter;
import com.fluendo.utils.Debug;
import com.fluendo.jst.Pad;
import java.net.URL;
import com.fluendo.jst.Caps;
import java.io.InputStream;
import com.fluendo.jst.Element;

public class HTTPSrc extends Element
{
    private String userId;
    private String password;
    private String userAgent;
    private String urlString;
    private InputStream input;
    private long contentLength;
    private long offset;
    private long offsetLastMessage;
    private long skipBytes;
    private String mime;
    private Caps outCaps;
    private boolean discont;
    private URL documentBase;
    private boolean microSoft;
    private static final int DEFAULT_READSIZE = 4096;
    private int readSize;
    private Pad srcpad;
    
    private InputStream openWithConnection(final URL url, final long n) throws IOException {
        final URLConnection openConnection = url.openConnection();
        openConnection.setRequestProperty("Connection", "Keep-Alive");
        String s;
        if (n != 0L && this.contentLength != -1L) {
            s = "bytes=" + n + "-" + (this.contentLength - 1L);
        }
        else if (n != 0L) {
            s = "bytes=" + n + "-";
        }
        else {
            s = null;
        }
        if (s != null) {
            Debug.info("doing range: " + s);
            openConnection.setRequestProperty("Range", s);
        }
        openConnection.setRequestProperty("User-Agent", this.userAgent);
        if (this.userId != null && this.password != null) {
            openConnection.setRequestProperty("Authorization", "Basic " + Base64Converter.encode((this.userId + ":" + this.password).getBytes()));
        }
        openConnection.setRequestProperty("Content-Type", "application/octet-stream");
        final InputStream inputStream = openConnection.getInputStream();
        final String headerField = openConnection.getHeaderField("Content-Range");
        long longValue;
        if (headerField == null) {
            Debug.info("Response contained no Content-Range field, assuming offset=0");
            longValue = 0L;
        }
        else {
            try {
                final MessageFormat messageFormat = new MessageFormat("bytes {0,number}-{1,number}");
                messageFormat.setLocale(Locale.US);
                longValue = ((Number)messageFormat.parse(headerField)[0]).longValue();
                if (longValue < 0L) {
                    longValue = 0L;
                }
                Debug.debug("Stream successfully with offset " + longValue);
            }
            catch (Exception ex) {
                Debug.info("Error parsing Content-Range header");
                longValue = 0L;
            }
        }
        this.contentLength = openConnection.getHeaderFieldInt("Content-Length", -1) + longValue;
        this.mime = openConnection.getContentType();
        this.offset = longValue;
        if (longValue < n) {
            this.skipBytes = n - longValue;
        }
        else {
            this.skipBytes = 0L;
        }
        return inputStream;
    }
    
    private InputStream getInputStream(final long n) throws Exception {
        InputStream openWithConnection = null;
        try {
            this.postMessage(Message.newResource(this, "Opening " + this.urlString));
            Debug.log(3, "reading from url " + this.urlString);
            URL url;
            if (!this.urlString.startsWith("http://") && this.documentBase != null) {
                Debug.log(3, "parsing in document base");
                url = new URL(this.documentBase, this.urlString);
            }
            else {
                Debug.log(3, "parsing as absolute URL");
                url = new URL(this.urlString);
            }
            Debug.log(3, "trying to open " + url + " at offset " + n);
            openWithConnection = this.openWithConnection(url, n);
            this.discont = true;
            if (this.contentLength != -1L) {
                this.postMessage(Message.newDuration(this, 2, this.contentLength));
            }
            Debug.log(3, "opened " + url);
            Debug.log(3, "contentLength: " + this.contentLength);
            Debug.log(3, "server contentType: " + this.mime);
        }
        catch (SecurityException ex) {
            ex.printStackTrace();
            this.postMessage(Message.newError(this, "Not allowed " + this.urlString + "..."));
        }
        catch (Exception ex2) {
            ex2.printStackTrace();
            this.postMessage(Message.newError(this, "Failed opening " + this.urlString + "..."));
        }
        catch (Throwable t) {
            t.printStackTrace();
            this.postMessage(Message.newError(this, "Failed opening " + this.urlString + "..."));
        }
        return openWithConnection;
    }
    
    public String getFactoryName() {
        return "httpsrc";
    }
    
    public HTTPSrc() {
        this.userAgent = "Cortado";
        this.offsetLastMessage = 0L;
        this.skipBytes = 0L;
        this.microSoft = false;
        this.readSize = 4096;
        this.srcpad = new Pad(1, "src") {
            private boolean doSeek(final Event event) {
                final int seekFormat = event.parseSeekFormat();
                long seekPosition = event.parseSeekPosition();
                if (seekFormat == 5 && HTTPSrc.this.contentLength != -1L) {
                    seekPosition = seekPosition * HTTPSrc.this.contentLength / 1000000L;
                }
                else if (seekFormat != 2) {
                    Debug.log(2, "can only seek in bytes");
                    return false;
                }
                Debug.log(4, this + " flushing");
                this.pushEvent(Event.newFlushStart());
                boolean startTask;
                synchronized (super.streamLock) {
                    Debug.log(4, this + " synced");
                    startTask = false;
                    try {
                        HTTPSrc.this.input = HTTPSrc.this.getInputStream(seekPosition);
                        if (HTTPSrc.this.input != null) {
                            startTask = true;
                        }
                    }
                    catch (Exception ex) {
                        ex.printStackTrace();
                    }
                    this.pushEvent(Event.newFlushStop());
                    if (startTask) {
                        this.pushEvent(Event.newNewsegment(false, 2, seekPosition, HTTPSrc.this.contentLength, seekPosition));
                        HTTPSrc.this.postMessage(Message.newStreamStatus(this, true, 0, "restart after seek"));
                        startTask = this.startTask("cortado-HTTPSrc-Stream-" + Debug.genId());
                    }
                    else {
                        HTTPSrc.this.postMessage(Message.newError(this, "error: Seek failed"));
                    }
                }
                return startTask;
            }
            
            protected boolean eventFunc(final Event event) {
                boolean b = false;
                switch (event.getType()) {
                    case 5: {
                        b = this.doSeek(event);
                        break;
                    }
                    default: {
                        b = super.eventFunc(event);
                        break;
                    }
                }
                return b;
            }
            
            protected void taskFunc() {
                if (HTTPSrc.this.skipBytes > 0L) {
                    Debug.info("Skipping " + HTTPSrc.this.skipBytes + " input bytes");
                    try {
                        HTTPSrc.this.offset += HTTPSrc.this.input.skip(HTTPSrc.this.skipBytes);
                    }
                    catch (IOException ex) {
                        Debug.error("input.skip error: " + ex);
                        HTTPSrc.this.postMessage(Message.newError(this, "File read error"));
                        return;
                    }
                    HTTPSrc.this.skipBytes = 0L;
                }
                long n;
                if (HTTPSrc.this.contentLength != -1L) {
                    if (HTTPSrc.this.microSoft) {
                        if (HTTPSrc.this.contentLength == 0L) {
                            n = 0L;
                        }
                        else {
                            n = HTTPSrc.this.contentLength - 1L - HTTPSrc.this.offset;
                        }
                    }
                    else {
                        n = HTTPSrc.this.contentLength - HTTPSrc.this.offset;
                    }
                }
                else {
                    n = -1L;
                }
                int access$600;
                if (n != -1L && n < HTTPSrc.this.readSize) {
                    access$600 = (int)n;
                }
                else {
                    access$600 = HTTPSrc.this.readSize;
                }
                final Buffer create = Buffer.create();
                create.ensureSize(access$600);
                create.offset = 0;
                try {
                    if (access$600 > 0) {
                        create.length = HTTPSrc.this.input.read(create.data, 0, access$600);
                    }
                    else {
                        create.length = -1;
                    }
                }
                catch (Exception ex2) {
                    ex2.printStackTrace();
                    create.length = 0;
                }
                if (create.length <= 0) {
                    HTTPSrc.this.postMessage(Message.newBytePosition(this, HTTPSrc.this.offset));
                    HTTPSrc.this.offsetLastMessage = HTTPSrc.this.offset;
                    try {
                        HTTPSrc.this.input.close();
                    }
                    catch (Exception ex3) {
                        ex3.printStackTrace();
                    }
                    create.free();
                    Debug.log(3, this + " reached EOS");
                    this.pushEvent(Event.newEOS());
                    HTTPSrc.this.postMessage(Message.newStreamStatus(this, false, -3, "reached EOS"));
                    this.pauseTask();
                    return;
                }
                HTTPSrc.this.offset += create.length;
                if (HTTPSrc.this.offsetLastMessage > HTTPSrc.this.offset) {
                    HTTPSrc.this.offsetLastMessage = 0L;
                }
                if (HTTPSrc.this.offset - HTTPSrc.this.offsetLastMessage > HTTPSrc.this.contentLength / 100L) {
                    HTTPSrc.this.postMessage(Message.newBytePosition(this, HTTPSrc.this.offset));
                    HTTPSrc.this.offsetLastMessage = HTTPSrc.this.offset;
                }
                if (HTTPSrc.this.srcpad.getCaps() == null) {
                    final String typeFindMime = ElementFactory.typeFindMime(create.data, create.offset, create.length);
                    if (typeFindMime != null) {
                        if (!typeFindMime.equals(HTTPSrc.this.mime)) {
                            Debug.log(2, "server contentType: " + HTTPSrc.this.mime + " disagrees with our typeFind: " + typeFindMime);
                        }
                        Debug.log(3, "using typefind contentType: " + typeFindMime);
                        HTTPSrc.this.mime = typeFindMime;
                    }
                    else {
                        Debug.log(3, "typefind failed, using server contentType: " + HTTPSrc.this.mime);
                    }
                    HTTPSrc.this.outCaps = new Caps(HTTPSrc.this.mime);
                    HTTPSrc.this.srcpad.setCaps(HTTPSrc.this.outCaps);
                }
                create.caps = HTTPSrc.this.outCaps;
                create.setFlag(1, HTTPSrc.this.discont);
                HTTPSrc.this.discont = false;
                final int push;
                if ((push = this.push(create)) != 0) {
                    if (Pad.isFlowFatal(push) || push == -1) {
                        HTTPSrc.this.postMessage(Message.newError(this, "error: " + Pad.getFlowName(push)));
                        this.pushEvent(Event.newEOS());
                    }
                    HTTPSrc.this.postMessage(Message.newStreamStatus(this, false, push, "reason: " + Pad.getFlowName(push)));
                    this.pauseTask();
                }
            }
            
            protected boolean activateFunc(final int n) {
                boolean b = true;
                switch (n) {
                    case 0: {
                        HTTPSrc.this.postMessage(Message.newStreamStatus(this, false, -2, "stopping"));
                        b = this.stopTask();
                        HTTPSrc.this.input = null;
                        HTTPSrc.this.outCaps = null;
                        HTTPSrc.this.mime = null;
                        break;
                    }
                    case 1: {
                        try {
                            HTTPSrc.this.contentLength = -1L;
                            HTTPSrc.this.input = HTTPSrc.this.getInputStream(0L);
                            if (HTTPSrc.this.input == null) {
                                b = false;
                            }
                        }
                        catch (Exception ex) {
                            b = false;
                        }
                        if (b) {
                            HTTPSrc.this.postMessage(Message.newStreamStatus(this, true, 0, "activating"));
                            b = this.startTask("cortado-HTTPSrc-Stream-" + Debug.genId());
                            break;
                        }
                        break;
                    }
                    default: {
                        b = false;
                        break;
                    }
                }
                return b;
            }
        };
        if (System.getProperty("java.vendor").toUpperCase().startsWith("MICROSOFT", 0)) {
            Debug.log(2, "Found MS JVM, work around inputStream EOS bugs.");
            this.microSoft = true;
        }
        this.addPad(this.srcpad);
    }
    
    public synchronized boolean setProperty(final String s, final java.lang.Object o) {
        boolean b = true;
        if (s.equals("url")) {
            this.urlString = String.valueOf(o);
        }
        else if (s.equals("documentBase")) {
            this.documentBase = (URL)o;
        }
        else if (s.equals("userId")) {
            this.userId = ((o == null) ? null : String.valueOf(o));
        }
        else if (s.equals("userAgent")) {
            this.userAgent = String.valueOf(o);
        }
        else if (s.equals("password")) {
            this.password = ((o == null) ? null : String.valueOf(o));
        }
        else if (s.equals("readSize")) {
            this.readSize = Integer.parseInt((String)o);
        }
        else {
            b = false;
        }
        return b;
    }
}
