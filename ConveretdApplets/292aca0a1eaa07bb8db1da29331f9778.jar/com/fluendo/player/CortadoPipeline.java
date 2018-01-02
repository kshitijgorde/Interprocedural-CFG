// 
// Decompiled by Procyon v0.5.30
// 

package com.fluendo.player;

import com.fluendo.jst.Query;
import com.fluendo.jst.Event;
import com.fluendo.plugin.AudioSinkJ2;
import com.fluendo.jst.Caps;
import com.fluendo.jst.Message;
import com.fluendo.utils.Debug;
import com.fluendo.jst.ElementFactory;
import com.fluendo.jst.Pad;
import com.fluendo.jst.Element;
import java.net.URL;
import java.awt.Component;
import com.fluendo.jst.CapsListener;
import com.fluendo.jst.PadListener;
import com.fluendo.jst.Pipeline;

public class CortadoPipeline extends Pipeline implements PadListener, CapsListener
{
    private String url;
    private String userId;
    private String password;
    private boolean enableAudio;
    private boolean enableVideo;
    private Component component;
    private int bufferSize;
    private int bufferLow;
    private int bufferHigh;
    private URL documentBase;
    private Element httpsrc;
    private Element buffer;
    private Element demux;
    private Element videodec;
    private Element audiodec;
    private Element videosink;
    public Element audiosink;
    private Element v_queue;
    private Element a_queue;
    private Pad asinkpad;
    private Pad vsinkpad;
    private Pad apad;
    private Pad vpad;
    private int rePlayTime;
    public boolean usingJavaX;
    
    private boolean setupVideoDec(final String name) {
        this.videodec = ElementFactory.makeByName(name, "videodec");
        if (this.videodec == null) {
            this.noSuchElement(name);
            return false;
        }
        this.add(this.videodec);
        return true;
    }
    
    public void padAdded(final Pad pad) {
        final Caps caps = pad.getCaps();
        if (caps == null) {
            Debug.log(3, "pad added without caps: " + pad);
            return;
        }
        Debug.log(3, "pad added " + pad);
        final String mime = caps.getMime();
        if (this.enableAudio && mime.equals("audio/x-vorbis")) {
            this.a_queue = ElementFactory.makeByName("queue", "a_queue");
            if (this.a_queue == null) {
                this.noSuchElement("queue");
                return;
            }
            this.audiodec = ElementFactory.makeByName("vorbisdec", "audiodec");
            if (this.audiodec == null) {
                this.noSuchElement("vorbisdec");
                return;
            }
            this.add(this.a_queue);
            this.add(this.audiodec);
            pad.link(this.a_queue.getPad("sink"));
            this.a_queue.getPad("src").link(this.audiodec.getPad("sink"));
            if (!this.audiodec.getPad("src").link(this.asinkpad)) {
                this.postMessage(Message.newError(this, "audiosink already linked"));
                return;
            }
            this.apad = pad;
            this.audiodec.setState(2);
            this.a_queue.setState(2);
        }
        else if (this.enableVideo && mime.equals("video/x-theora")) {
            this.v_queue = ElementFactory.makeByName("queue", "v_queue");
            if (this.v_queue == null) {
                this.noSuchElement("queue");
                return;
            }
            if (!this.setupVideoDec("theoradec")) {
                return;
            }
            this.add(this.v_queue);
            pad.link(this.v_queue.getPad("sink"));
            this.v_queue.getPad("src").link(this.videodec.getPad("sink"));
            if (!this.videodec.getPad("src").link(this.vsinkpad)) {
                this.postMessage(Message.newError(this, "videosink already linked"));
                return;
            }
            this.vpad = pad;
            this.videodec.setState(2);
            this.v_queue.setState(2);
        }
        else if (this.enableVideo && mime.equals("image/jpeg")) {
            if (!this.setupVideoDec("jpegdec")) {
                return;
            }
            this.videodec.setProperty("component", this.component);
            pad.link(this.videodec.getPad("sink"));
            if (!this.videodec.getPad("src").link(this.vsinkpad)) {
                this.postMessage(Message.newError(this, "videosink already linked"));
                return;
            }
            this.videodec.setState(2);
        }
        else if (this.enableVideo && mime.equals("video/x-smoke")) {
            if (!this.setupVideoDec("smokedec")) {
                return;
            }
            this.videodec.setProperty("component", this.component);
            pad.link(this.videodec.getPad("sink"));
            if (!this.videodec.getPad("src").link(this.vsinkpad)) {
                this.postMessage(Message.newError(this, "videosink already linked"));
                return;
            }
            this.vpad = pad;
            this.videodec.setState(2);
        }
    }
    
    public void padRemoved(final Pad pad) {
        pad.unlink();
        if (pad == this.vpad) {
            Debug.log(3, "video pad removed " + pad);
            this.vsinkpad.unlink();
            this.vpad = null;
        }
        else if (pad == this.apad) {
            Debug.log(3, "audio pad removed " + pad);
            this.asinkpad.unlink();
            this.apad = null;
        }
    }
    
    public void noMorePads() {
        boolean changed = false;
        Debug.log(3, "all streams detected");
        if (this.apad == null && this.enableAudio) {
            Debug.log(3, "file has no audio, remove audiosink");
            this.audiosink.setState(1);
            this.remove(this.audiosink);
            this.audiosink = null;
            changed = true;
        }
        if (this.vpad == null && this.enableVideo) {
            Debug.log(3, "file has no video, remove videosink");
            this.videosink.setState(1);
            this.remove(this.videosink);
            this.videosink = null;
            changed = true;
        }
        if (changed) {
            this.scheduleReCalcState();
        }
    }
    
    public CortadoPipeline() {
        super("pipeline");
        this.bufferSize = -1;
        this.bufferLow = -1;
        this.bufferHigh = -1;
        this.documentBase = null;
        this.rePlayTime = 30;
        this.usingJavaX = false;
        this.enableAudio = true;
        this.enableVideo = true;
    }
    
    public void setUrl(final String anUrl) {
        this.url = anUrl;
    }
    
    public String getUrl() {
        return this.url;
    }
    
    public void setUserId(final String aUserId) {
        this.userId = aUserId;
    }
    
    public void setPassword(final String aPassword) {
        this.password = aPassword;
    }
    
    public void enableAudio(final boolean b) {
        this.enableAudio = b;
    }
    
    public boolean isAudioEnabled() {
        return this.enableAudio;
    }
    
    public void enableVideo(final boolean b) {
        this.enableVideo = b;
    }
    
    public boolean isVideoEnabled() {
        return this.enableVideo;
    }
    
    public void setComponent(final Component c) {
        this.component = c;
    }
    
    public Component getComponent() {
        return this.component;
    }
    
    public void setDocumentBase(final URL base) {
        this.documentBase = base;
    }
    
    public URL getDocumentBase() {
        return this.documentBase;
    }
    
    public void setBufferSize(final int size) {
        this.bufferSize = size;
    }
    
    public int getBufferSize() {
        return this.bufferSize;
    }
    
    public void setBufferLow(final int size) {
        this.bufferLow = size;
    }
    
    public int getBufferLow() {
        return this.bufferLow;
    }
    
    public void setBufferHigh(final int size) {
        this.bufferHigh = size;
    }
    
    public int getBufferHigh() {
        return this.bufferHigh;
    }
    
    public boolean buildOgg() {
        this.demux = ElementFactory.makeByName("oggdemux", "demux");
        if (this.demux == null) {
            this.noSuchElement("oggdemux");
            return false;
        }
        this.buffer = ElementFactory.makeByName("queue", "buffer");
        if (this.buffer == null) {
            this.demux = null;
            this.noSuchElement("queue");
            return false;
        }
        this.buffer.setProperty("isBuffer", Boolean.TRUE);
        if (this.bufferSize != -1) {
            this.buffer.setProperty("maxSize", new Integer(this.bufferSize * 1024));
        }
        if (this.bufferLow != -1) {
            this.buffer.setProperty("lowPercent", new Integer(this.bufferLow));
        }
        if (this.bufferHigh != -1) {
            this.buffer.setProperty("highercent", new Integer(this.bufferHigh));
        }
        this.add(this.demux);
        this.add(this.buffer);
        this.httpsrc.getPad("src").link(this.buffer.getPad("sink"));
        this.buffer.getPad("src").link(this.demux.getPad("sink"));
        this.demux.addPadListener(this);
        this.buffer.setState(2);
        this.demux.setState(2);
        return true;
    }
    
    public boolean buildMultipart() {
        this.demux = ElementFactory.makeByName("multipartdemux", "demux");
        if (this.demux == null) {
            this.noSuchElement("multipartdemux");
            return false;
        }
        this.add(this.demux);
        this.httpsrc.getPad("src").link(this.demux.getPad("sink"));
        this.demux.addPadListener(this);
        return true;
    }
    
    public void capsChanged(final Caps caps) {
        final String mime = caps.getMime();
        if (mime.equals("application/ogg")) {
            this.buildOgg();
        }
        else if (mime.equals("multipart/x-mixed-replace")) {
            this.buildMultipart();
        }
        else {
            this.postMessage(Message.newError(this, "unknown type: " + mime));
        }
    }
    
    private void noSuchElement(final String elemName) {
        this.postMessage(Message.newError(this, "no such element: " + elemName + " (check plugins.ini)"));
    }
    
    private boolean build() {
        final Configure configure = new Configure();
        final String vendor = System.getProperty("java.vendor");
        this.httpsrc = ElementFactory.makeByName("httpsrc", "httpsrc");
        if (this.httpsrc == null) {
            this.noSuchElement("httpsrc");
            return false;
        }
        this.httpsrc.setProperty("url", this.url);
        this.httpsrc.setProperty("userId", this.userId);
        this.httpsrc.setProperty("password", this.password);
        String userAgent = "Cortado/" + configure.buildVersion + " " + vendor.substring(0, vendor.indexOf(" ")) + "/" + System.getProperty("java.version");
        String extra = "(" + System.getProperty("os.name") + " " + System.getProperty("os.version") + ")";
        try {
            final String agent = System.getProperty("http.agent");
            if (agent != null) {
                extra = agent;
            }
        }
        catch (Exception ex) {}
        userAgent = userAgent + " " + extra;
        Debug.log(3, "setting User-Agent " + userAgent);
        this.httpsrc.setProperty("userAgent", userAgent);
        this.httpsrc.setProperty("documentBase", this.documentBase);
        this.add(this.httpsrc);
        this.httpsrc.getPad("src").addCapsListener(this);
        if (this.enableAudio) {
            try {
                Class.forName("javax.sound.sampled.AudioSystem");
                this.usingJavaX = true;
                this.audiosink = ElementFactory.makeByName("audiosinkj2", "audiosink");
                final AudioSinkJ2 audioSinkJ2 = (AudioSinkJ2)this.audiosink;
                audioSinkJ2.setTimeSRePlay(this.rePlayTime);
                Debug.log(3, "using high quality javax.sound backend");
            }
            catch (ClassNotFoundException e) {
                this.audiosink = ElementFactory.makeByName("audiosinksa", "audiosink");
                Debug.log(3, "using low quality sun.audio backend");
            }
            if (this.audiosink == null) {
                this.noSuchElement("audiosink");
                return false;
            }
            this.asinkpad = this.audiosink.getPad("sink");
            this.add(this.audiosink);
        }
        if (this.enableVideo) {
            this.videosink = ElementFactory.makeByName("videosink", "videosink");
            if (this.videosink == null) {
                this.noSuchElement("videosink");
                return false;
            }
            this.videosink.setProperty("component", this.component);
            this.vsinkpad = this.videosink.getPad("sink");
            this.add(this.videosink);
        }
        return true;
    }
    
    private boolean cleanup() {
        Debug.log(3, "cleanup");
        if (this.httpsrc != null) {
            this.remove(this.httpsrc);
            this.httpsrc = null;
        }
        if (this.audiosink != null) {
            this.remove(this.audiosink);
            this.audiosink = null;
            this.asinkpad = null;
        }
        if (this.videosink != null) {
            this.remove(this.videosink);
            this.videosink = null;
            this.vsinkpad = null;
        }
        if (this.buffer != null) {
            this.remove(this.buffer);
            this.buffer = null;
        }
        if (this.demux != null) {
            this.demux.removePadListener(this);
            this.remove(this.demux);
            this.demux = null;
        }
        if (this.v_queue != null) {
            this.remove(this.v_queue);
            this.v_queue = null;
        }
        if (this.a_queue != null) {
            this.remove(this.a_queue);
            this.a_queue = null;
        }
        if (this.videodec != null) {
            this.remove(this.videodec);
            this.videodec = null;
        }
        if (this.audiodec != null) {
            this.remove(this.audiodec);
            this.audiodec = null;
        }
        return true;
    }
    
    protected int changeState(final int transition) {
        switch (transition) {
            case 18: {
                if (!this.build()) {
                    return 0;
                }
                break;
            }
        }
        final int res = super.changeState(transition);
        switch (transition) {
            case 33: {
                this.cleanup();
                break;
            }
        }
        return res;
    }
    
    protected boolean doSendEvent(final Event event) {
        if (event.getType() != 5) {
            return false;
        }
        if (event.parseSeekFormat() != 5) {
            return false;
        }
        if (this.httpsrc == null) {
            return false;
        }
        final boolean res = this.httpsrc.getPad("src").sendEvent(event);
        this.getState(null, null, 50000L);
        return res;
    }
    
    protected long getPosition() {
        long result = 0L;
        final Query q = Query.newPosition(3);
        if (super.query(q)) {
            result = q.parsePositionValue();
        }
        return result;
    }
    
    public int getRePlayTime() {
        return this.rePlayTime;
    }
    
    public void setRePlayTime(final int rePlayTime) {
        this.rePlayTime = rePlayTime;
    }
}
