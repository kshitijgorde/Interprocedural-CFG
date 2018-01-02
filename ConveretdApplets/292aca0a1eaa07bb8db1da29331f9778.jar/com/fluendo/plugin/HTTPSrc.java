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
    private String mime;
    private Caps outCaps;
    private boolean discont;
    private URL documentBase;
    private static final int DEFAULT_READSIZE = 4096;
    private int readSize;
    private Pad srcpad;
    
    private InputStream openWithConnection(final URL url, final long offset) throws IOException {
        InputStream dis = null;
        final URLConnection uc = url.openConnection();
        uc.setRequestProperty("Connection", "Keep-Alive");
        String range;
        if (offset != 0L && this.contentLength != -1L) {
            range = "bytes=" + offset + "-" + (this.contentLength - 1L);
        }
        else if (offset != 0L) {
            range = "bytes=" + offset + "-";
        }
        else {
            range = null;
        }
        if (range != null) {
            Debug.log(3, "doing range: " + range);
            uc.setRequestProperty("Range", range);
        }
        uc.setRequestProperty("User-Agent", this.userAgent);
        if (this.userId != null && this.password != null) {
            final String userPassword = this.userId + ":" + this.password;
            final String encoding = Base64Converter.encode(userPassword.getBytes());
            uc.setRequestProperty("Authorization", "Basic " + encoding);
        }
        uc.setRequestProperty("Content-Type", "application/octet-stream");
        dis = uc.getInputStream();
        this.contentLength = uc.getHeaderFieldInt("Content-Length", 0) + offset;
        this.mime = uc.getContentType();
        return dis;
    }
    
    private InputStream getInputStream(final long offset) throws Exception {
        InputStream dis = null;
        try {
            this.postMessage(Message.newResource(this, "Opening " + this.urlString));
            Debug.log(3, "reading from url " + this.urlString);
            final boolean isAbsolute = this.urlString.startsWith("http://");
            URL url;
            if (!isAbsolute && this.documentBase != null) {
                Debug.log(3, "parsing in document base");
                url = new URL(this.documentBase, this.urlString);
            }
            else {
                Debug.log(3, "parsing as abslute URL");
                url = new URL(this.urlString);
            }
            Debug.log(3, "trying to open " + url + " at offset " + offset);
            dis = this.openWithConnection(url, offset);
            this.discont = true;
            Debug.log(3, "opened " + url);
            Debug.log(3, "contentLength: " + this.contentLength);
            Debug.log(3, "server contentType: " + this.mime);
        }
        catch (SecurityException e) {
            e.printStackTrace();
            this.postMessage(Message.newError(this, "Not allowed " + this.urlString + "..."));
        }
        catch (Exception e2) {
            e2.printStackTrace();
            this.postMessage(Message.newError(this, "Failed opening " + this.urlString + "..."));
        }
        catch (Throwable t) {
            t.printStackTrace();
            this.postMessage(Message.newError(this, "Failed opening " + this.urlString + "..."));
        }
        return dis;
    }
    
    public String getFactoryName() {
        return "httpsrc";
    }
    
    public HTTPSrc() {
        this.userAgent = "Cortado";
        this.readSize = 4096;
        this.addPad(this.srcpad = new Pad(1, "src") {
            private boolean doSeek(final Event event) {
                final int format = event.parseSeekFormat();
                long position = event.parseSeekPosition();
                if (format == 5 && HTTPSrc.this.contentLength != -1L) {
                    position = position * HTTPSrc.this.contentLength / 1000000L;
                }
                else if (format != 2) {
                    Debug.log(2, "can only seek in bytes");
                    return false;
                }
                Debug.log(4, this + " flushing");
                this.pushEvent(Event.newFlushStart());
                boolean result;
                synchronized (this.streamLock) {
                    Debug.log(4, this + " synced");
                    result = false;
                    try {
                        HTTPSrc.this.input = HTTPSrc.this.getInputStream(position);
                        if (HTTPSrc.this.input != null) {
                            result = true;
                        }
                    }
                    catch (Exception e) {
                        e.printStackTrace();
                    }
                    this.pushEvent(Event.newFlushStop());
                    if (result) {
                        this.pushEvent(Event.newNewsegment(false, 2, position, HTTPSrc.this.contentLength, position));
                        HTTPSrc.this.postMessage(Message.newStreamStatus(this, true, 0, "restart after seek"));
                        result = this.startTask("cortado-HTTPSrc-Stream-" + Debug.genId());
                    }
                    else {
                        HTTPSrc.this.postMessage(Message.newError(this, "error: Seek failed"));
                    }
                }
                return result;
            }
            
            protected boolean eventFunc(final Event event) {
                boolean res = false;
                switch (event.getType()) {
                    case 5: {
                        res = this.doSeek(event);
                        break;
                    }
                    default: {
                        res = super.eventFunc(event);
                        break;
                    }
                }
                return res;
            }
            
            protected void taskFunc() {
                final Buffer data = Buffer.create();
                data.ensureSize(HTTPSrc.this.readSize);
                data.offset = 0;
                try {
                    data.length = HTTPSrc.this.input.read(data.data, 0, HTTPSrc.this.readSize);
                }
                catch (Exception e) {
                    e.printStackTrace();
                    data.length = 0;
                }
                if (data.length <= 0) {
                    data.free();
                    Debug.log(3, this + " reached EOS");
                    this.pushEvent(Event.newEOS());
                    HTTPSrc.this.postMessage(Message.newStreamStatus(this, false, -3, "reached EOS"));
                    this.pauseTask();
                }
                else {
                    if (HTTPSrc.this.srcpad.getCaps() == null) {
                        final String typeMime = ElementFactory.typeFindMime(data.data, data.offset, data.length);
                        if (typeMime != null) {
                            if (!typeMime.equals(HTTPSrc.this.mime)) {
                                Debug.log(2, "server contentType: " + HTTPSrc.this.mime + " disagrees with our typeFind: " + typeMime);
                            }
                            Debug.log(3, "using typefind contentType: " + typeMime);
                            HTTPSrc.this.mime = typeMime;
                        }
                        else {
                            Debug.log(3, "typefind failed, using server contentType: " + HTTPSrc.this.mime);
                        }
                        HTTPSrc.this.outCaps = new Caps(HTTPSrc.this.mime);
                        HTTPSrc.this.srcpad.setCaps(HTTPSrc.this.outCaps);
                    }
                    data.caps = HTTPSrc.this.outCaps;
                    data.setFlag(1, HTTPSrc.this.discont);
                    HTTPSrc.this.discont = false;
                    final int ret;
                    if ((ret = this.push(data)) != 0) {
                        if (Pad.isFlowFatal(ret) || ret == -1) {
                            HTTPSrc.this.postMessage(Message.newError(this, "error: " + Pad.getFlowName(ret)));
                            this.pushEvent(Event.newEOS());
                        }
                        HTTPSrc.this.postMessage(Message.newStreamStatus(this, false, ret, "reason: " + Pad.getFlowName(ret)));
                        this.pauseTask();
                    }
                }
            }
            
            protected boolean activateFunc(final int mode) {
                boolean res = true;
                switch (mode) {
                    case 0: {
                        HTTPSrc.this.postMessage(Message.newStreamStatus(this, false, -2, "stopping"));
                        res = this.stopTask();
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
                                res = false;
                            }
                        }
                        catch (Exception e) {
                            res = false;
                        }
                        if (res) {
                            HTTPSrc.this.postMessage(Message.newStreamStatus(this, true, 0, "activating"));
                            res = this.startTask("cortado-HTTPSrc-Stream-" + Debug.genId());
                            break;
                        }
                        break;
                    }
                    default: {
                        res = false;
                        break;
                    }
                }
                return res;
            }
        });
    }
    
    public synchronized boolean setProperty(final String name, final java.lang.Object value) {
        boolean res = true;
        if (name.equals("url")) {
            this.urlString = String.valueOf(value);
        }
        else if (name.equals("documentBase")) {
            this.documentBase = (URL)value;
        }
        else if (name.equals("userId")) {
            this.userId = String.valueOf(value);
        }
        else if (name.equals("userAgent")) {
            this.userAgent = String.valueOf(value);
        }
        else if (name.equals("password")) {
            this.password = String.valueOf(value);
        }
        else if (name.equals("readSize")) {
            this.readSize = Integer.parseInt((String)value);
        }
        else {
            res = false;
        }
        return res;
    }
}
