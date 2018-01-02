// 
// Decompiled by Procyon v0.5.30
// 

package com.fluendo.player;

import com.fluendo.jst.Query;
import com.fluendo.jst.Event;
import com.fluendo.plugin.AudioSink;
import java.awt.Rectangle;
import java.awt.Dimension;
import com.fluendo.jst.Caps;
import com.fluendo.jst.Message;
import com.fluendo.utils.Debug;
import com.fluendo.jst.ElementFactory;
import java.util.Vector;
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
    private boolean ignoreAspect;
    private int enableKate;
    private Component component;
    private int bufferSize;
    private int bufferLow;
    private int bufferHigh;
    private URL documentBase;
    private Cortado application;
    private Element httpsrc;
    private Element buffer;
    private Element demux;
    private Element videodec;
    private Element audiodec;
    private Element videosink;
    private Element audiosink;
    private Element v_queue;
    private Element v_queue2;
    private Element a_queue;
    private Element overlay;
    private Pad asinkpad;
    private Pad ovsinkpad;
    private Pad oksinkpad;
    private Pad apad;
    private Pad vpad;
    private Vector katedec;
    private Vector k_queue;
    private Element kselector;
    public boolean usingJavaX;
    
    private boolean setupVideoDec(final String s) {
        this.videodec = ElementFactory.makeByName(s, "videodec");
        if (this.videodec == null) {
            this.noSuchElement(s);
            return false;
        }
        this.add(this.videodec);
        return true;
    }
    
    public void padAdded(final Pad vpad) {
        final Caps caps = vpad.getCaps();
        if (caps == null) {
            Debug.log(3, "pad added without caps: " + vpad);
            return;
        }
        Debug.log(3, "pad added " + vpad);
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
            vpad.link(this.a_queue.getPad("sink"));
            this.a_queue.getPad("src").link(this.audiodec.getPad("sink"));
            if (!this.audiodec.getPad("src").link(this.asinkpad)) {
                this.postMessage(Message.newError(this, "audiosink already linked"));
                return;
            }
            this.apad = vpad;
            this.audiodec.setState(2);
            this.a_queue.setState(2);
        }
        else if (this.enableVideo && mime.equals("video/x-theora")) {
            this.v_queue = ElementFactory.makeByName("queue", "v_queue");
            this.v_queue2 = ElementFactory.makeByName("queue", "v_queue2");
            if (this.v_queue == null) {
                this.noSuchElement("queue");
                return;
            }
            if (!this.setupVideoDec("theoradec")) {
                return;
            }
            this.v_queue.setProperty("leaky", "2");
            this.v_queue2.setProperty("maxBuffers", "1");
            this.add(this.v_queue);
            this.add(this.v_queue2);
            vpad.link(this.v_queue.getPad("sink"));
            this.v_queue.getPad("src").link(this.videodec.getPad("sink"));
            this.videodec.getPad("src").link(this.v_queue2.getPad("sink"));
            if (!this.v_queue2.getPad("src").link(this.ovsinkpad)) {
                this.postMessage(Message.newError(this, "videosink already linked"));
                return;
            }
            this.vpad = vpad;
            this.videodec.setState(2);
            this.v_queue.setState(2);
            this.v_queue2.setState(2);
        }
        else if (this.enableVideo && mime.equals("image/jpeg")) {
            if (!this.setupVideoDec("jpegdec")) {
                return;
            }
            this.videodec.setProperty("component", this.component);
            vpad.link(this.videodec.getPad("sink"));
            if (!this.videodec.getPad("src").link(this.ovsinkpad)) {
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
            vpad.link(this.videodec.getPad("sink"));
            if (!this.videodec.getPad("src").link(this.ovsinkpad)) {
                this.postMessage(Message.newError(this, "videosink already linked"));
                return;
            }
            this.vpad = vpad;
            this.videodec.setState(2);
        }
        else if (this.enableVideo && mime.equals("application/x-kate")) {
            final int size = this.katedec.size();
            Debug.debug("Found Kate stream, setting up pipeline branch");
            final Element byName = ElementFactory.makeByName("queue", "k_queue" + size);
            if (byName == null) {
                this.noSuchElement("queue");
                return;
            }
            final Element byName2 = ElementFactory.makeByName("katedec", "katedec" + size);
            if (byName2 == null) {
                this.noSuchElement("katedec");
                return;
            }
            if (this.kselector == null) {
                Debug.debug("No Kate selector yet, creating one");
                this.ovsinkpad.unlink();
                this.videodec.getPad("src").unlink();
                this.overlay = ElementFactory.makeByName("kateoverlay", "overlay");
                if (this.overlay == null) {
                    this.noSuchElement("overlay");
                    return;
                }
                this.ovsinkpad = this.overlay.getPad("videosink");
                this.oksinkpad = this.overlay.getPad("katesink");
                if (!this.videodec.getPad("src").link(this.ovsinkpad)) {
                    this.postMessage(Message.newError(this, "Failed linking video decoder to overlay"));
                    return;
                }
                this.add(this.overlay);
                this.overlay.setProperty("component", this.component);
                this.overlay.getPad("videosrc").link(this.videosink.getPad("sink"));
                this.kselector = ElementFactory.makeByName("selector", "selector");
                if (this.kselector == null) {
                    this.noSuchElement("selector");
                    return;
                }
                this.add(this.kselector);
                if (!this.kselector.getPad("src").link(this.oksinkpad)) {
                    this.postMessage(Message.newError(this, "Failed linking Kate selector to overlay"));
                    return;
                }
                this.kselector.setState(2);
            }
            final Element kselector = this.kselector;
            this.add(byName);
            this.add(byName2);
            kselector.getPad("sink");
            if (!vpad.link(byName.getPad("sink"))) {
                this.postMessage(Message.newError(this, "Failed to link new Kate stream to queue"));
                return;
            }
            if (!byName.getPad("src").link(byName2.getPad("sink"))) {
                this.postMessage(Message.newError(this, "Failed to link new Kate queue to decoder"));
                return;
            }
            if (!byName2.getPad("src").link(this.kselector.requestSinkPad(byName2.getPad("src")))) {
                this.postMessage(Message.newError(this, "kate sink already linked"));
                return;
            }
            byName2.setState(2);
            byName.setState(2);
            this.katedec.addElement(byName2);
            this.k_queue.addElement(byName);
            if (this.enableKate == this.katedec.size() - 1) {
                this.doEnableKateIndex(this.enableKate);
            }
        }
    }
    
    public void padRemoved(final Pad pad) {
        pad.unlink();
        if (pad == this.vpad) {
            Debug.log(3, "video pad removed " + pad);
            this.ovsinkpad.unlink();
            this.vpad = null;
        }
        else if (pad == this.apad) {
            Debug.log(3, "audio pad removed " + pad);
            this.asinkpad.unlink();
            this.apad = null;
        }
    }
    
    public void noMorePads() {
        boolean b = false;
        Debug.log(3, "all streams detected");
        if (this.apad == null && this.enableAudio) {
            Debug.log(3, "file has no audio, remove audiosink");
            this.audiosink.setState(1);
            this.remove(this.audiosink);
            this.audiosink = null;
            b = true;
        }
        if (this.vpad == null && this.enableVideo) {
            Debug.log(3, "file has no video, remove videosink");
            this.videosink.setState(1);
            if (this.overlay != null) {
                this.overlay.setState(1);
            }
            for (int i = 0; i < this.katedec.size(); ++i) {
                final Element element = this.katedec.elementAt(i);
                element.setState(1);
                this.remove(element);
                final Element element2 = this.k_queue.elementAt(i);
                element2.setState(1);
                this.remove(element2);
            }
            if (this.kselector != null) {
                this.kselector.setState(1);
                this.remove(this.kselector);
                this.kselector = null;
            }
            this.remove(this.videosink);
            this.remove(this.overlay);
            this.katedec.removeAllElements();
            this.k_queue.removeAllElements();
            this.videosink = null;
            this.overlay = null;
            b = true;
        }
        if (b) {
            this.scheduleReCalcState();
        }
    }
    
    public CortadoPipeline(final Cortado application) {
        super("pipeline");
        this.bufferSize = -1;
        this.bufferLow = -1;
        this.bufferHigh = -1;
        this.documentBase = null;
        this.katedec = new Vector();
        this.k_queue = new Vector();
        this.kselector = null;
        this.usingJavaX = false;
        this.enableAudio = true;
        this.enableVideo = true;
        this.application = application;
        this.enableKate = -1;
    }
    
    public void setUrl(final String url) {
        this.url = url;
    }
    
    public String getUrl() {
        return this.url;
    }
    
    public void setUserId(final String userId) {
        this.userId = userId;
    }
    
    public void setIgnoreAspect(final boolean ignoreAspect) {
        this.ignoreAspect = ignoreAspect;
    }
    
    public void setPassword(final String password) {
        this.password = password;
    }
    
    public void enableAudio(final boolean enableAudio) {
        this.enableAudio = enableAudio;
    }
    
    public boolean isAudioEnabled() {
        return this.enableAudio;
    }
    
    public void enableVideo(final boolean enableVideo) {
        this.enableVideo = enableVideo;
    }
    
    public boolean isVideoEnabled() {
        return this.enableVideo;
    }
    
    private int findKateStream(final String s, final String s2) {
        int n = -1;
        final boolean b = !s.equals("");
        final boolean b2 = !s2.equals("");
        if (b || b2) {
            for (int i = 0; i < this.katedec.size(); ++i) {
                final Element element = this.katedec.elementAt(i);
                if (element != null) {
                    final String value = String.valueOf(element.getProperty("language"));
                    final String value2 = String.valueOf(element.getProperty("category"));
                    if (s.equalsIgnoreCase(value) && (!b2 || s2.equals(value2))) {
                        n = i;
                    }
                }
            }
        }
        return n;
    }
    
    public void enableKateStream(int kateStream, final String s, final String s2) {
        if (kateStream < 0) {
            kateStream = this.findKateStream(s, s2);
        }
        if (kateStream == this.enableKate) {
            return;
        }
        this.doEnableKateIndex(kateStream);
    }
    
    private void doEnableKateIndex(final int enableKate) {
        if (this.kselector != null) {
            Debug.info("Switching Kate streams from " + this.enableKate + " to " + enableKate);
            this.kselector.setProperty("selected", new Integer(enableKate));
        }
        else {
            Debug.warning("Switching Kate stream request, but no Kate selector exists");
        }
        this.enableKate = enableKate;
    }
    
    public int getEnabledKateIndex() {
        return this.enableKate;
    }
    
    public void setComponent(final Component component) {
        this.component = component;
    }
    
    public Component getComponent() {
        return this.component;
    }
    
    public void setDocumentBase(final URL documentBase) {
        this.documentBase = documentBase;
    }
    
    public URL getDocumentBase() {
        return this.documentBase;
    }
    
    public void setBufferSize(final int bufferSize) {
        this.bufferSize = bufferSize;
    }
    
    public int getBufferSize() {
        return this.bufferSize;
    }
    
    public void setBufferLow(final int bufferLow) {
        this.bufferLow = bufferLow;
    }
    
    public int getBufferLow() {
        return this.bufferLow;
    }
    
    public void setBufferHigh(final int bufferHigh) {
        this.bufferHigh = bufferHigh;
    }
    
    public int getBufferHigh() {
        return this.bufferHigh;
    }
    
    public void resize(final Dimension dimension) {
        if (this.videosink == null || dimension == null) {
            return;
        }
        final Rectangle rectangle = new Rectangle(dimension);
        if (this.application.getShowStatus() == 1) {
            final Rectangle rectangle2 = rectangle;
            rectangle2.height -= this.application.getStatusHeight();
        }
        if (rectangle.height < 0) {
            rectangle.height = 0;
        }
        this.videosink.setProperty("bounds", rectangle);
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
            this.buffer.setProperty("highPercent", new Integer(this.bufferHigh));
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
    
    private void noSuchElement(final String s) {
        this.postMessage(Message.newError(this, "no such element: " + s + " (check plugins.ini)"));
    }
    
    private boolean build() {
        final Configure configure = new Configure();
        final String property = System.getProperty("java.vendor");
        this.httpsrc = ElementFactory.makeByName("httpsrc", "httpsrc");
        if (this.httpsrc == null) {
            this.noSuchElement("httpsrc");
            return false;
        }
        this.httpsrc.setProperty("url", this.url);
        this.httpsrc.setProperty("userId", this.userId);
        this.httpsrc.setProperty("password", this.password);
        final String string = "Cortado/" + configure.buildVersion + " " + property.substring(0, property.indexOf(" ")) + "/" + System.getProperty("java.version");
        String string2 = "(" + System.getProperty("os.name") + " " + System.getProperty("os.version") + ")";
        try {
            final String property2 = System.getProperty("http.agent");
            if (property2 != null) {
                string2 = property2;
            }
        }
        catch (Exception ex) {}
        final String string3 = string + " " + string2;
        Debug.log(3, "setting User-Agent " + string3);
        this.httpsrc.setProperty("userAgent", string3);
        this.httpsrc.setProperty("documentBase", this.documentBase);
        this.add(this.httpsrc);
        this.httpsrc.getPad("src").addCapsListener(this);
        if (this.enableAudio) {
            this.audiosink = this.newAudioSink();
            if (this.audiosink == null) {
                this.enableAudio = false;
                ((Cortado)this.component).status.setHaveAudio(false);
                this.component.repaint();
            }
            else {
                this.asinkpad = this.audiosink.getPad("sink");
                this.add(this.audiosink);
            }
        }
        if (this.enableVideo) {
            this.videosink = ElementFactory.makeByName("videosink", "videosink");
            if (this.videosink == null) {
                this.noSuchElement("videosink");
                return false;
            }
            this.videosink.setProperty("ignore-aspect", this.ignoreAspect ? "true" : "false");
            this.videosink.setProperty("component", this.component);
            this.resize(this.component.getSize());
            this.videosink.setProperty("max-lateness", Long.toString(20000L));
            this.add(this.videosink);
            this.ovsinkpad = this.videosink.getPad("sink");
        }
        if (this.audiosink == null && this.videosink == null) {
            this.postMessage(Message.newError(this, "Both audio and video are disabled, can't play anything"));
            return false;
        }
        return true;
    }
    
    protected Element newAudioSink() {
        AudioSink audioSink;
        try {
            Class.forName("javax.sound.sampled.AudioSystem");
            Class.forName("javax.sound.sampled.DataLine");
            this.usingJavaX = true;
            audioSink = (AudioSink)ElementFactory.makeByName("audiosinkj2", "audiosink");
            Debug.log(3, "using high quality javax.sound backend");
        }
        catch (Throwable t) {
            audioSink = (AudioSink)ElementFactory.makeByName("audiosinksa", "audiosink");
            Debug.log(3, "using low quality sun.audio backend");
        }
        if (audioSink == null) {
            this.noSuchElement("audiosink");
            return null;
        }
        if (!audioSink.test()) {
            return null;
        }
        return audioSink;
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
        }
        if (this.overlay != null) {
            this.remove(this.overlay);
            this.overlay = null;
            this.ovsinkpad = null;
            this.oksinkpad = null;
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
        if (this.v_queue2 != null) {
            this.remove(this.v_queue2);
            this.v_queue2 = null;
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
        for (int i = 0; i < this.katedec.size(); ++i) {
            if (this.k_queue.elementAt(i) != null) {
                this.remove((Element)this.k_queue.elementAt(i));
            }
            if (this.katedec.elementAt(i) != null) {
                this.remove((Element)this.katedec.elementAt(i));
            }
        }
        this.k_queue.removeAllElements();
        this.katedec.removeAllElements();
        if (this.kselector != null) {
            this.remove(this.kselector);
            this.kselector = null;
        }
        return true;
    }
    
    protected int changeState(final int n) {
        switch (n) {
            case 18: {
                if (!this.build()) {
                    return 0;
                }
                break;
            }
        }
        final int changeState = super.changeState(n);
        switch (n) {
            case 33: {
                this.cleanup();
                break;
            }
        }
        return changeState;
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
        final boolean sendEvent = this.httpsrc.getPad("src").sendEvent(event);
        this.getState(null, null, -1L);
        return sendEvent;
    }
    
    protected long getPosition() {
        long positionValue = 0L;
        final Query position = Query.newPosition(3);
        if (super.query(position)) {
            positionValue = position.parsePositionValue();
        }
        return positionValue;
    }
}
