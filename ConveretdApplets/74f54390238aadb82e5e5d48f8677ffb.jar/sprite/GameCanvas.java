// 
// Decompiled by Procyon v0.5.30
// 

package sprite;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Color;
import java.io.InputStream;
import java.io.IOException;
import java.io.StreamTokenizer;
import java.io.FileInputStream;
import java.awt.Cursor;
import java.awt.image.ImageObserver;
import java.util.Vector;
import java.net.URL;
import java.applet.AudioClip;
import java.awt.Event;
import java.awt.Rectangle;
import java.awt.Image;
import java.awt.Graphics;
import java.awt.Canvas;

public class GameCanvas extends Canvas
{
    protected AudioThread m_audio;
    protected boolean m_bAllLoaded;
    protected volatile boolean m_bBetsActive;
    protected boolean m_bDollar;
    protected boolean m_bDropFrames;
    protected boolean m_bEraseBG;
    protected boolean m_bFatalError;
    protected boolean m_bInitDone;
    protected boolean m_bLicensed;
    protected boolean m_bPrivateLabel;
    protected boolean m_bRedraw;
    public boolean m_bSoundEnabled;
    protected volatile boolean m_bStopped;
    protected volatile boolean m_bWaiting;
    protected Object[] m_clips;
    private Graphics m_g;
    protected Graphics m_gImage;
    protected Image m_imageBG;
    protected Image m_imgAd;
    protected int m_nCountdown;
    protected int[] m_nDataFile;
    protected int m_nFrameCount;
    protected int m_nFrameRate;
    protected int m_nGameID;
    protected int m_nPercent;
    protected int m_nTempSprite;
    private Rectangle m_rTemp;
    protected Rectangle m_rUpdate;
    protected Event m_saveEvent;
    protected AudioClip m_silentAudio;
    protected SpriteBase[] m_sprites;
    protected String m_strDataFile;
    protected String[] m_strImages;
    protected String m_strMessage;
    protected String[] m_strSounds;
    protected String[] m_strStrings;
    public String m_strXtraCopy;
    protected InputThread m_threadProcess;
    protected RenderThread m_threadRender;
    protected URL m_urlCodeBase;
    private Vector m_vecEvents;
    static String s_strCopyright;
    
    static {
        GameCanvas.s_strCopyright = "(c) 2005 GameHouse, Inc. ";
    }
    
    public GameCanvas() {
        this.m_nFrameRate = 40;
        this.m_bDropFrames = true;
        this.m_bEraseBG = false;
        this.m_imageBG = null;
        this.m_bRedraw = false;
        this.m_sprites = null;
        this.m_rUpdate = new Rectangle(0, 0, 0, 0);
        this.m_nFrameCount = 0;
        this.m_saveEvent = null;
        this.m_vecEvents = new Vector();
        this.m_threadProcess = null;
        this.m_threadRender = null;
        this.m_bInitDone = false;
        this.m_bFatalError = false;
        this.m_bWaiting = false;
        this.m_bStopped = false;
        this.m_bBetsActive = false;
        this.m_nTempSprite = 0;
        this.m_bDollar = true;
        this.m_clips = null;
        this.m_silentAudio = null;
        this.m_audio = null;
        this.m_urlCodeBase = null;
        this.m_strDataFile = null;
        this.m_nGameID = 0;
        this.m_nDataFile = null;
        this.m_strImages = null;
        this.m_strSounds = null;
        this.m_strStrings = null;
        this.m_nPercent = 0;
        this.m_nCountdown = 0;
        this.m_bLicensed = false;
        this.m_bPrivateLabel = false;
        this.m_strXtraCopy = "";
        this.m_imgAd = null;
        this.m_strMessage = "Loading...";
        this.m_bAllLoaded = false;
        this.m_bSoundEnabled = true;
        this.m_rTemp = new Rectangle(0, 0, 0, 0);
        this.m_g = null;
        this.m_gImage = null;
        this.m_audio = new AudioThread();
    }
    
    public void InitCanvas(final URL url, final String strDataFile, final int nGameID) {
        this.m_urlCodeBase = url;
        this.m_strDataFile = strDataFile;
        this.m_nGameID = nGameID;
        System.out.println(GameCanvas.s_strCopyright);
    }
    
    public void SetDollarMode(final boolean bDollar) {
        this.m_bDollar = bDollar;
    }
    
    public boolean betsActive() {
        return this.m_bBetsActive;
    }
    
    public synchronized void changed(final Rectangle r) {
        if (r != null && r.width == 0) {
            return;
        }
        this.m_bRedraw = true;
        if (r == null) {
            this.m_rUpdate.reshape(0, 0, this.size().width, this.size().height);
        }
        else if (this.m_rUpdate.width == 0) {
            this.m_rUpdate.reshape(r.x, r.y, r.width, r.height);
        }
        else {
            this.m_rUpdate.add(r);
        }
    }
    
    protected void clearEvents(final int nEventID) {
        synchronized (this.m_vecEvents) {
            int i = 0;
            while (i < this.m_vecEvents.size()) {
                final Event evt = this.m_vecEvents.elementAt(i);
                if (evt.id == nEventID) {
                    this.m_vecEvents.removeElementAt(i);
                }
                else {
                    ++i;
                }
            }
        }
    }
    
    public void destroy() {
        if (!this.isStopped()) {
            this.stop();
        }
        if (this.m_imageBG != null) {
            this.m_imageBG.flush();
        }
    }
    
    public void doUpdate() {
        Graphics g = this.m_g;
        if (this.m_bRedraw && g != null) {
            g = g.create();
            synchronized (this) {
                if (this.m_rUpdate.width > 0) {
                    g.clipRect(this.m_rUpdate.x, this.m_rUpdate.y, this.m_rUpdate.width, this.m_rUpdate.height);
                }
                this.redraw();
            }
            final Image bg = this.m_imageBG;
            if (bg != null) {
                g.drawImage(bg, 0, 0, null);
            }
            g.dispose();
            g = null;
        }
    }
    
    public void enableSound(final boolean bEnable) {
        if (!(this.m_bSoundEnabled = bEnable)) {
            this.stopSound(this.m_silentAudio);
            if (this.m_clips != null) {
                for (int i = 0; i < this.m_clips.length; ++i) {
                    this.stopSound(i);
                }
            }
        }
        else {
            this.loopSound(this.m_silentAudio);
        }
    }
    
    public String[] getImageList() {
        return this.m_strImages;
    }
    
    public String[] getSoundList() {
        return this.m_strSounds;
    }
    
    public void handCursor() {
        try {
            this.setCursor(Cursor.getPredefinedCursor(12));
        }
        catch (NoSuchMethodError e) {
            e.printStackTrace();
        }
        catch (SecurityException e2) {
            e2.printStackTrace();
        }
    }
    
    public boolean initGame() {
        return false;
    }
    
    public boolean isLoaded() {
        return this.m_bAllLoaded;
    }
    
    public boolean isStopped() {
        return this.m_bStopped;
    }
    
    public boolean keyDown(final Event evt, final int nKey) {
        if (!this.m_bInitDone) {
            return false;
        }
        switch (nKey) {
            case 83:
            case 115: {
                this.enableSound(!this.m_bSoundEnabled);
                break;
            }
        }
        return true;
    }
    
    public boolean licensed() {
        return this.m_bLicensed;
    }
    
    protected void loadDataFile(URL url) {
        this.m_bAllLoaded = false;
        try {
            InputStream is;
            if (url == null) {
                is = new FileInputStream(this.m_strDataFile);
            }
            else {
                url = new URL(url, this.m_strDataFile);
                is = url.openStream();
            }
            final StreamTokenizer st = new StreamTokenizer(is);
            st.eolIsSignificant(false);
            st.commentChar(59);
            st.quoteChar(34);
            st.parseNumbers();
            int nNum = this.readNumber(st);
            this.m_strImages = new String[nNum];
            for (int i = 0; i < nNum; ++i) {
                this.m_strImages[i] = this.readString(st);
            }
            nNum = this.readNumber(st);
            this.m_strSounds = new String[nNum];
            for (int i = 0; i < nNum; ++i) {
                this.m_strSounds[i] = this.readString(st);
            }
            nNum = this.readNumber(st);
            this.m_nDataFile = new int[nNum];
            for (int i = 0; i < nNum; ++i) {
                this.m_nDataFile[i] = this.readNumber(st);
            }
            nNum = this.readNumber(st);
            this.m_strStrings = new String[nNum];
            for (int i = 0; i < nNum; ++i) {
                this.m_strStrings[i] = this.readString(st);
            }
            is.close();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    public void loopSound(final AudioClip clip) {
        if (clip != null && this.m_bSoundEnabled) {
            this.m_audio.loopClip(clip);
        }
    }
    
    public void loopSound(final int i) {
        if (this.m_clips != null && i < this.m_clips.length && this.m_clips[i] != null && this.m_bSoundEnabled) {
            this.m_audio.loopClip((AudioClip)this.m_clips[i]);
        }
    }
    
    protected synchronized int makeTempSprite(final int nStart, final int nEnd, final SpriteBase spr) {
        for (int i = nStart; i < nEnd; ++i) {
            if (this.m_sprites[i] == null) {
                this.m_sprites[i] = spr;
                if (i >= this.m_nTempSprite) {
                    this.m_nTempSprite = i + 1;
                }
                return i;
            }
        }
        return -1;
    }
    
    public boolean mouseDown(final Event evt, final int x, final int y) {
        if (!this.m_bInitDone) {
            return false;
        }
        this.setEvent(evt);
        return true;
    }
    
    public boolean mouseDrag(final Event evt, final int x, final int y) {
        this.setEvent(evt);
        return true;
    }
    
    public boolean mouseMove(final Event evt, final int x, final int y) {
        this.setEvent(evt);
        return true;
    }
    
    public synchronized void nextFrame() {
        if (this.m_sprites != null) {
            boolean b = false;
            this.m_rTemp.reshape(0, 0, 0, 0);
            for (int i = 0; i < this.m_sprites.length; ++i) {
                if (this.m_sprites[i] != null) {
                    b = (this.m_sprites[i].nextFrame(this.m_nFrameCount, this.m_rTemp) || b);
                }
            }
            if (b) {
                this.changed(this.m_rTemp);
            }
            ++this.m_nFrameCount;
        }
    }
    
    public void normalCursor() {
        try {
            this.setCursor(Cursor.getDefaultCursor());
        }
        catch (NoSuchMethodError e) {
            e.printStackTrace();
        }
        catch (SecurityException e2) {
            e2.printStackTrace();
        }
    }
    
    public void paint(final Graphics g) {
        if (this.m_bInitDone) {
            synchronized (this) {
                final Image bg = this.m_imageBG;
                if (bg != null) {
                    g.drawImage(bg, 0, 0, null);
                }
            }
        }
        else {
            this.paintLoading(g);
        }
    }
    
    protected void paintFatal(final Graphics g) {
        final FontMetrics fm = g.getFontMetrics();
        g.setColor(Color.black);
        final int w = this.size().width / 2;
        int h = this.size().height / 2;
        int x = w / 2;
        int y = h / 2;
        g.fillRect(x, y, w, h);
        g.setColor(Color.white);
        h = fm.getHeight() + 4;
        String str = this.m_strMessage;
        int index;
        do {
            index = str.indexOf(10);
            String strOut;
            if (index == -1) {
                strOut = str;
            }
            else {
                strOut = str.substring(0, index);
                str = str.substring(index + 1);
            }
            x = w - (fm.stringWidth(strOut) + 4) / 2;
            y += h;
            g.drawString(strOut, x, y);
        } while (index != -1);
    }
    
    protected void paintLoading(final Graphics g) {
        if (this.m_bFatalError) {
            g.setColor(this.getBackground());
            g.fillRect(0, 0, this.size().width, this.size().height);
            this.paintFatal(g);
            return;
        }
        final FontMetrics fm = g.getFontMetrics();
        final Dimension d = this.size();
        g.setColor(this.getBackground());
        int y;
        synchronized (this) {
            if (this.m_imgAd != null) {
                final int w = this.m_imgAd.getWidth(null);
                final int h = this.m_imgAd.getHeight(null);
                final int x = (d.width - this.m_imgAd.getWidth(null)) / 2;
                y = 50;
                g.drawImage(this.m_imgAd, x, y, null);
                g.fillRect(0, 0, x, d.height);
                g.fillRect(0, 0, d.width, y);
                g.fillRect(0, y + h, d.width, d.height - y - h);
                g.fillRect(x + w, 0, d.width - x - w, d.height);
                y = d.height - fm.getHeight() - 20;
            }
            else {
                g.fillRect(0, 0, d.width, d.height);
                y = d.height / 2;
            }
        }
        g.setColor(this.getForeground());
        g.drawRect(d.width / 2 - 150 - 2, y - 10 - 3, 304, 25);
        g.setColor(new Color(153, 0, 153));
        g.fillRect(d.width / 2 - 150, y - 10, this.m_nPercent * 3, 20);
        g.setColor(Color.gray);
        g.fillRect(d.width / 2 - 150 + this.m_nPercent * 3, y - 10, 300 - this.m_nPercent * 3, 20);
        g.setColor(Color.white);
        String strOut;
        if (this.m_nPercent > 0) {
            strOut = this.m_strMessage + " " + this.m_nPercent + "%";
        }
        else {
            strOut = this.m_strMessage;
        }
        int x = fm.stringWidth(strOut);
        x = (d.width - x) / 2;
        y += 2;
        g.setColor(Color.black);
        g.drawString(strOut, x - 1, y - 1);
        g.drawString(strOut, x - 1, y + 1);
        g.drawString(strOut, x + 1, y + 1);
        g.drawString(strOut, x + 1, y - 1);
        g.setColor(Color.white);
        g.drawString(strOut, x, y);
        if (!this.m_bPrivateLabel) {
            final Font fnt = g.getFont();
            g.setFont(new Font("SansSerif", 0, 10));
            g.setColor(this.getForeground());
            g.drawString(GameCanvas.s_strCopyright + this.m_strXtraCopy, 5, d.height - 5);
            g.setFont(fnt);
        }
    }
    
    public void playSound(final AudioClip clip) {
        if (clip != null && this.m_bSoundEnabled) {
            this.m_audio.playClip(clip);
        }
    }
    
    public void playSound(final int i) {
        if (this.m_clips != null && i < this.m_clips.length && this.m_clips[i] != null && this.m_bSoundEnabled) {
            this.m_audio.playClip((AudioClip)this.m_clips[i]);
        }
    }
    
    public boolean processEvent() {
        return true;
    }
    
    protected double readDouble(final StreamTokenizer st) {
        try {
            st.nextToken();
        }
        catch (IOException e) {
            e.printStackTrace();
            return 0.0;
        }
        if (st.ttype != -2) {
            return 0.0;
        }
        return st.nval;
    }
    
    protected int readNumber(final StreamTokenizer st) {
        try {
            st.nextToken();
        }
        catch (IOException e) {
            e.printStackTrace();
            return 0;
        }
        if (st.ttype != -2) {
            return 0;
        }
        return (int)st.nval;
    }
    
    protected String readString(final StreamTokenizer st) {
        try {
            st.nextToken();
        }
        catch (IOException e) {
            e.printStackTrace();
            return null;
        }
        return st.sval;
    }
    
    protected synchronized void redraw() {
        if (this.m_imageBG == null) {
            return;
        }
        this.m_bRedraw = false;
        if (this.m_gImage == null) {
            this.m_gImage = this.m_imageBG.getGraphics();
        }
        Graphics g = this.m_gImage.create();
        if (this.m_rUpdate.width > 0) {
            g.clipRect(this.m_rUpdate.x, this.m_rUpdate.y, this.m_rUpdate.width, this.m_rUpdate.height);
            if (this.m_bEraseBG) {
                g.setColor(this.getBackground());
                g.fillRect(this.m_rUpdate.x, this.m_rUpdate.y, this.m_rUpdate.width, this.m_rUpdate.height);
            }
        }
        for (int i = 0; i < this.m_sprites.length; ++i) {
            if (this.m_sprites[i] != null && (this.m_rUpdate.width <= 0 || this.m_rUpdate.intersects(this.m_sprites[i].getBounding()))) {
                this.m_sprites[i].drawSprite(g);
                goto Label_0188;
            }
        }
        this.m_rUpdate.reshape(0, 0, 0, 0);
        g.dispose();
        if (this.m_bFatalError) {
            g = this.m_gImage.create();
            g.setColor(Color.black);
            final int w = this.size().width / 2;
            final int h = this.size().height / 2;
            final int x = w / 2;
            final int y = h / 2;
            g.fillRect(x, y, w, h);
            g.setColor(Color.white);
            this.paintFatal(g);
            g.dispose();
        }
    }
    
    protected synchronized void releaseTempSprite(int i) {
        if (this.m_sprites[i] == null) {
            return;
        }
        this.changed(this.m_sprites[i].getBounding());
        this.m_sprites[i] = null;
        if (i == this.m_nTempSprite - 1) {
            do {
                --this.m_nTempSprite;
                --i;
            } while (this.m_sprites[i] == null);
        }
    }
    
    public void resize(final Dimension d) {
        super.resize(d);
        this.setImageSize(d.width, d.height);
    }
    
    public void resize(final int width, final int height) {
        super.resize(width, height);
        this.setImageSize(width, height);
    }
    
    protected void setEvent(final Event evt) {
        if (evt == null) {
            return;
        }
        if (!this.m_bInitDone || this.m_bStopped) {
            return;
        }
        synchronized (this.m_vecEvents) {
            this.m_vecEvents.addElement(evt);
            this.m_vecEvents.notifyAll();
        }
    }
    
    public void setFatalMessage(final String str) {
        this.m_bFatalError = true;
        this.m_strMessage = str;
        if (!this.m_bInitDone) {
            this.repaint();
        }
        else {
            this.m_bRedraw = true;
        }
    }
    
    public void setImageSize(final int width, final int height) {
        if (width == 0 || height == 0) {
            return;
        }
        try {
            if (this.m_imageBG != null) {
                this.m_imageBG.flush();
            }
            this.m_imageBG = this.createImage(width, height);
        }
        catch (RuntimeException e) {
            System.out.println("Error creating offscreen image");
        }
    }
    
    public void setImages(final Image[] image, final Object[] clips) {
        this.changed(null);
        this.m_clips = clips;
        this.m_strImages = null;
        this.m_bAllLoaded = true;
    }
    
    public void setLicensed() {
        this.m_bLicensed = true;
    }
    
    public void setLoadingImage(final Image img) {
        this.m_imgAd = img;
    }
    
    public void setMessage(final String str, final int nPercent) {
        if ((nPercent != this.m_nPercent || !str.equals(this.m_strMessage)) && !this.m_bFatalError) {
            this.m_strMessage = str;
            if (nPercent > 0) {
                this.m_nPercent = nPercent;
            }
            this.repaint();
        }
    }
    
    public void setPercent(int nPercent, final int nCountdown) {
        this.m_nCountdown += nCountdown;
        if (nPercent != this.m_nPercent) {
            if (nPercent > 100) {
                nPercent = 100;
            }
            this.m_nPercent = nPercent;
            this.repaint();
        }
    }
    
    public void setPrivateLabel() {
        this.m_bPrivateLabel = true;
    }
    
    public void setSilentAudio(final AudioClip clip) {
        this.loopSound(this.m_silentAudio = clip);
    }
    
    public void setXtraCopy(final String str) {
        this.m_strXtraCopy = str;
    }
    
    protected void sleep(final long l) {
        try {
            Thread.sleep(l);
        }
        catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    
    public synchronized void start() {
        this.m_bInitDone = true;
        if (this.m_imgAd != null) {
            this.m_imgAd.flush();
            this.m_imgAd = null;
        }
        if (this.m_threadProcess == null) {
            this.m_threadProcess = new InputThread(this);
        }
        if (this.m_threadRender == null) {
            this.m_threadRender = new RenderThread(this);
        }
        if (this.m_audio != null) {
            this.m_audio.start();
            this.loopSound(this.m_silentAudio);
        }
        this.m_bStopped = false;
        this.repaint();
    }
    
    public synchronized void stop() {
        if (this.m_audio != null) {
            this.stopSound(this.m_silentAudio);
            if (this.m_clips != null) {
                for (int i = 0; i < this.m_clips.length; ++i) {
                    this.stopSound(i);
                }
            }
            this.m_audio.finished();
        }
        final InputThread thr = this.m_threadProcess;
        if (thr != null) {
            thr.kill();
            this.m_threadProcess = null;
        }
        final RenderThread thrR = this.m_threadRender;
        if (thrR != null) {
            thrR.kill();
            this.m_threadRender = null;
        }
        Graphics g = this.m_g;
        this.m_g = null;
        if (g != null) {
            g.dispose();
        }
        g = this.m_gImage;
        this.m_gImage = null;
        if (g != null) {
            g.dispose();
        }
        this.m_bStopped = true;
    }
    
    public void stopSound(final AudioClip clip) {
        if (clip != null) {
            this.m_audio.stopClip(clip);
        }
    }
    
    public void stopSound(final int i) {
        if (this.m_clips != null && this.m_clips[i] != null) {
            this.m_audio.stopClip((AudioClip)this.m_clips[i]);
        }
    }
    
    public void update(final Graphics g) {
        if (this.m_g == null) {
            this.m_g = this.getGraphics();
        }
        if (this.m_imageBG == null) {
            final Dimension d = this.size();
            this.setImageSize(d.width, d.height);
        }
        this.paint(g);
    }
    
    protected synchronized void waitAnimation() {
        while (this.m_bWaiting && !this.m_bStopped) {
            try {
                this.wait(1000L);
            }
            catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
    
    public void waitCursor() {
        try {
            this.setCursor(Cursor.getPredefinedCursor(3));
        }
        catch (NoSuchMethodError e) {
            e.printStackTrace();
        }
        catch (SecurityException e2) {
            e2.printStackTrace();
        }
    }
    
    private class InputThread extends Thread
    {
        private boolean m_bStopped;
        private GameCanvas m_canvas;
        
        InputThread(final GameCanvas canvas) {
            this.m_canvas = canvas;
            this.m_bStopped = false;
            this.start();
        }
        
        void kill() {
            this.m_bStopped = true;
        }
        
        public void run() {
            while (!this.m_bStopped) {
                try {
                    final Vector v = this.m_canvas.m_vecEvents;
                    synchronized (v) {
                        if (v.size() > 0) {
                            this.m_canvas.m_saveEvent = v.firstElement();
                            v.removeElementAt(0);
                        }
                    }
                    this.m_canvas.processEvent();
                    this.m_canvas.m_saveEvent = null;
                    this.waitForEvent();
                }
                catch (RuntimeException e) {
                    e.printStackTrace();
                }
            }
        }
        
        private void waitForEvent() {
            final Vector v = this.m_canvas.m_vecEvents;
            synchronized (v) {
                while (v.size() == 0 && !this.m_bStopped) {
                    try {
                        v.wait(3000L);
                    }
                    catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }
    
    private class RenderThread extends Thread
    {
        private boolean m_bStopped;
        private GameCanvas m_canvas;
        
        RenderThread(final GameCanvas canvas) {
            this.m_canvas = canvas;
            this.m_bStopped = false;
            this.start();
        }
        
        void kill() {
            this.m_bStopped = true;
        }
        
        public void run() {
            long nStartTime = System.currentTimeMillis();
            long nFrame = 0L;
            int nFrameRate = 0;
            while (!this.m_bStopped) {
                if (nFrameRate != GameCanvas.this.m_nFrameRate) {
                    nFrameRate = GameCanvas.this.m_nFrameRate;
                    nStartTime = System.currentTimeMillis() - nFrame * GameCanvas.this.m_nFrameRate;
                }
                try {
                    this.m_canvas.nextFrame();
                    ++nFrame;
                    long nSleep = nFrame * nFrameRate + nStartTime - System.currentTimeMillis();
                    if (nSleep > -40L || !GameCanvas.this.m_bDropFrames) {
                        this.m_canvas.doUpdate();
                    }
                    else if (nSleep < -10000L) {
                        nStartTime = System.currentTimeMillis() - nFrame * nFrameRate;
                    }
                    if (nSleep > 20L) {
                        if ((nFrame & 0x3FFL) == 0x0L) {
                            System.gc();
                            System.runFinalization();
                            nSleep = nFrame * nFrameRate + nStartTime - System.currentTimeMillis();
                        }
                        if (nSleep > nFrameRate * 2) {
                            nStartTime = System.currentTimeMillis() - nFrame * nFrameRate;
                            nSleep = nFrameRate;
                        }
                    }
                    if (nSleep <= 0L) {
                        continue;
                    }
                    Thread.sleep(nSleep);
                }
                catch (InterruptedException e) {
                    e.printStackTrace();
                    break;
                }
                catch (Exception e2) {
                    try {
                        e2.printStackTrace();
                        System.out.println(e2.toString());
                        Thread.sleep(nFrameRate);
                    }
                    catch (InterruptedException e3) {}
                }
            }
        }
    }
}
