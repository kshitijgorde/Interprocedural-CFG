// 
// Decompiled by Procyon v0.5.30
// 

package sprite;

import java.io.InputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.StreamTokenizer;
import java.awt.Dimension;
import java.awt.FontMetrics;
import java.awt.Font;
import java.awt.Color;
import java.awt.image.ImageObserver;
import java.awt.Cursor;
import java.awt.Graphics;
import inknet.User;
import java.net.URL;
import java.applet.AudioClip;
import java.util.Vector;
import java.awt.Event;
import java.awt.Rectangle;
import java.awt.Image;
import java.awt.Canvas;

public class GameCanvas extends Canvas
{
    protected int m_nFrameRate;
    protected boolean m_bDropFrames;
    protected Image m_imageBG;
    protected boolean m_bRedraw;
    protected SpriteBase[] m_sprites;
    protected Rectangle m_rUpdate;
    protected int m_nFrameCount;
    protected Event m_saveEvent;
    Vector \u00e4;
    protected GameCanvas$InputThread m_threadProcess;
    protected GameCanvas$RenderThread m_threadRender;
    protected boolean m_bInitDone;
    protected boolean m_bFatalError;
    protected volatile boolean m_bWaiting;
    protected volatile boolean m_bStopped;
    protected volatile boolean m_bBetsActive;
    protected int m_nTempSprite;
    protected boolean m_bDollar;
    protected Object[] m_clips;
    protected AudioClip m_silentAudio;
    protected AudioThread m_audio;
    protected URL m_urlCodeBase;
    protected String m_strDataFile;
    protected int m_nGameID;
    protected User m_user;
    protected int[] m_nDataFile;
    protected String[] m_strImages;
    protected String[] m_strSounds;
    protected String[] m_strStrings;
    protected int m_nPercent;
    protected int m_nCountdown;
    protected boolean m_bLicensed;
    protected boolean m_bPrivateLabel;
    static String \u00e5;
    public String m_strXtraCopy;
    protected Image m_imgAd;
    protected String m_strMessage;
    protected boolean m_bAllLoaded;
    public boolean m_bSoundEnabled;
    private Rectangle m_rTemp;
    private Graphics \u00e6;
    protected Graphics m_gImage;
    
    public boolean mouseMove(final Event event, final int n, final int n2) {
        this.setEvent(event);
        return true;
    }
    
    public synchronized void stop() {
        System.out.println("stopped applet");
        if (this.m_audio != null) {
            this.stopSound(this.m_silentAudio);
            if (this.m_clips != null) {
                for (int i = 0; i < this.m_clips.length; ++i) {
                    this.stopSound(i);
                }
            }
            this.m_audio.finished();
        }
        final GameCanvas$InputThread threadProcess = this.m_threadProcess;
        if (threadProcess != null) {
            threadProcess.\u00e7();
            this.m_threadProcess = null;
        }
        final GameCanvas$RenderThread threadRender = this.m_threadRender;
        if (threadRender != null) {
            threadRender.\u00e7();
            this.m_threadRender = null;
        }
        final Graphics \u00e6 = this.\u00e6;
        this.\u00e6 = null;
        if (\u00e6 != null) {
            \u00e6.dispose();
        }
        final Graphics gImage = this.m_gImage;
        this.m_gImage = null;
        if (gImage != null) {
            gImage.dispose();
        }
        this.m_bStopped = true;
    }
    
    protected void sleep(final long n) {
        try {
            Thread.sleep(n);
        }
        catch (InterruptedException ex) {}
    }
    
    public boolean processEvent() {
        return true;
    }
    
    public void setSilentAudio(final AudioClip silentAudio) {
        this.loopSound(this.m_silentAudio = silentAudio);
    }
    
    public void setPrivateLabel() {
        this.m_bPrivateLabel = true;
    }
    
    public void handCursor() {
        try {
            this.setCursor(Cursor.getPredefinedCursor(12));
        }
        catch (NoSuchMethodError noSuchMethodError) {}
        catch (SecurityException ex) {}
    }
    
    public void waitCursor() {
        try {
            this.setCursor(Cursor.getPredefinedCursor(3));
        }
        catch (NoSuchMethodError noSuchMethodError) {}
        catch (SecurityException ex) {}
    }
    
    protected void paintLoading(final Graphics graphics) {
        if (this.m_bFatalError) {
            graphics.setColor(this.getBackground());
            graphics.fillRect(0, 0, this.size().width, this.size().height);
            this.paintFatal(graphics);
            return;
        }
        final FontMetrics fontMetrics = graphics.getFontMetrics();
        final Dimension size = this.size();
        graphics.setColor(this.getBackground());
        int n2;
        synchronized (this) {
            if (this.m_imgAd != null) {
                final int width = this.m_imgAd.getWidth(null);
                final int height = this.m_imgAd.getHeight(null);
                final int n = (size.width - this.m_imgAd.getWidth(null)) / 2;
                n2 = 50;
                graphics.drawImage(this.m_imgAd, n, n2, null);
                graphics.fillRect(0, 0, n, size.height);
                graphics.fillRect(0, 0, size.width, n2);
                graphics.fillRect(0, n2 + height, size.width, size.height - n2 - height);
                graphics.fillRect(n + width, 0, size.width - n - width, size.height);
                n2 = size.height - fontMetrics.getHeight() - 20;
            }
            else {
                graphics.fillRect(0, 0, size.width, size.height);
                n2 = size.height / 2;
            }
        }
        graphics.setColor(this.getForeground());
        graphics.drawRect(size.width / 2 - 150 - 2, n2 - 10 - 3, 304, 25);
        graphics.setColor(new Color(153, 0, 153));
        graphics.fillRect(size.width / 2 - 150, n2 - 10, this.m_nPercent * 3, 20);
        graphics.setColor(Color.gray);
        graphics.fillRect(size.width / 2 - 150 + this.m_nPercent * 3, n2 - 10, 300 - this.m_nPercent * 3, 20);
        graphics.setColor(Color.white);
        String s;
        if (this.m_nPercent > 0) {
            s = this.m_strMessage + " " + this.m_nPercent + "%";
        }
        else {
            s = this.m_strMessage;
        }
        final int n3 = (size.width - fontMetrics.stringWidth(s)) / 2;
        n2 += 2;
        graphics.setColor(Color.black);
        graphics.drawString(s, n3 - 1, n2 - 1);
        graphics.drawString(s, n3 - 1, n2 + 1);
        graphics.drawString(s, n3 + 1, n2 + 1);
        graphics.drawString(s, n3 + 1, n2 - 1);
        graphics.setColor(Color.white);
        graphics.drawString(s, n3, n2);
        if (!this.m_bPrivateLabel) {
            final Font font = graphics.getFont();
            graphics.setFont(new Font("SansSerif", 0, 10));
            graphics.setColor(this.getForeground());
            graphics.drawString(GameCanvas.\u00e5 + this.m_strXtraCopy, 5, size.height - 5);
            graphics.setFont(font);
        }
    }
    
    public void normalCursor() {
        try {
            this.setCursor(Cursor.getDefaultCursor());
        }
        catch (NoSuchMethodError noSuchMethodError) {}
        catch (SecurityException ex) {}
    }
    
    public void setXtraCopy(final String strXtraCopy) {
        this.m_strXtraCopy = strXtraCopy;
    }
    
    public void InitCanvas(final URL urlCodeBase, final User user, final String strDataFile, final int nGameID) {
        this.m_urlCodeBase = urlCodeBase;
        this.m_strDataFile = strDataFile;
        this.m_nGameID = nGameID;
        this.m_user = user;
        System.out.println(GameCanvas.\u00e5);
    }
    
    public void setFatalMessage(final String strMessage) {
        this.m_bFatalError = true;
        this.m_strMessage = strMessage;
        if (!this.m_bInitDone) {
            this.repaint();
            return;
        }
        this.m_bRedraw = true;
    }
    
    public void setMessage(final String strMessage, final int nPercent) {
        if ((nPercent != this.m_nPercent || !strMessage.equals(this.m_strMessage)) && !this.m_bFatalError) {
            this.m_strMessage = strMessage;
            if (nPercent > 0) {
                this.m_nPercent = nPercent;
            }
            this.repaint();
        }
    }
    
    public String[] getSoundList() {
        return this.m_strSounds;
    }
    
    public void setPercent(int nPercent, final int n) {
        this.m_nCountdown += n;
        if (nPercent != this.m_nPercent) {
            if (nPercent > 100) {
                nPercent = 100;
            }
            this.m_nPercent = nPercent;
            this.repaint();
        }
    }
    
    public void loopSound(final int n) {
        if (this.m_clips != null && n < this.m_clips.length && this.m_clips[n] != null && this.m_bSoundEnabled) {
            this.m_audio.loopClip((AudioClip)this.m_clips[n]);
        }
    }
    
    public void loopSound(final AudioClip audioClip) {
        if (audioClip != null && this.m_bSoundEnabled) {
            this.m_audio.loopClip(audioClip);
        }
    }
    
    public void update(final Graphics graphics) {
        if (this.\u00e6 == null) {
            this.\u00e6 = this.getGraphics();
        }
        if (this.m_imageBG == null) {
            final Dimension size = this.size();
            this.setImageSize(size.width, size.height);
        }
        this.paint(graphics);
    }
    
    public synchronized void start() {
        this.m_bInitDone = true;
        if (this.m_imgAd != null) {
            this.m_imgAd.flush();
            this.m_imgAd = null;
        }
        if (this.m_threadProcess == null) {
            this.m_threadProcess = new GameCanvas$InputThread(this, this);
        }
        if (this.m_threadRender == null) {
            this.m_threadRender = new GameCanvas$RenderThread(this, this);
        }
        if (this.m_audio != null) {
            this.m_audio.start();
            this.loopSound(this.m_silentAudio);
        }
        if (this.m_user != null) {
            this.m_user.runCommand("LOADED");
        }
        this.m_bStopped = false;
        this.repaint();
    }
    
    protected int readNumber(final StreamTokenizer streamTokenizer) {
        try {
            streamTokenizer.nextToken();
        }
        catch (IOException ex) {
            return 0;
        }
        if (streamTokenizer.ttype != -2) {
            return 0;
        }
        return (int)streamTokenizer.nval;
    }
    
    public void setLoadingImage(final Image imgAd) {
        this.m_imgAd = imgAd;
    }
    
    public String[] getImageList() {
        return this.m_strImages;
    }
    
    protected void clearEvents(final int n) {
        synchronized (this.\u00e4) {
            int i = 0;
            while (i < this.\u00e4.size()) {
                if (((Event)this.\u00e4.elementAt(i)).id == n) {
                    this.\u00e4.removeElementAt(i);
                }
                else {
                    ++i;
                }
            }
        }
        // monitorexit(this.\u00e4)
    }
    
    public boolean isStopped() {
        return this.m_bStopped;
    }
    
    protected void paintFatal(final Graphics graphics) {
        final FontMetrics fontMetrics = graphics.getFontMetrics();
        graphics.setColor(Color.black);
        final int n = this.size().width / 2;
        final int n2 = this.size().height / 2;
        final int n3 = n / 2;
        int n4 = n2 / 2;
        graphics.fillRect(n3, n4, n, n2);
        graphics.setColor(Color.white);
        final int n5 = fontMetrics.getHeight() + 4;
        String s = this.m_strMessage;
        int i;
        do {
            i = s.indexOf(10);
            String substring;
            if (i == -1) {
                substring = s;
            }
            else {
                substring = s.substring(0, i);
                s = s.substring(i + 1);
            }
            final int n6 = n - (fontMetrics.stringWidth(substring) + 4) / 2;
            n4 += n5;
            graphics.drawString(substring, n6, n4);
        } while (i != -1);
    }
    
    public synchronized void changed(final Rectangle rectangle) {
        if (rectangle != null && rectangle.width == 0) {
            return;
        }
        this.m_bRedraw = true;
        if (rectangle == null) {
            this.m_rUpdate.reshape(0, 0, this.size().width, this.size().height);
            return;
        }
        if (this.m_rUpdate.width == 0) {
            this.m_rUpdate.reshape(rectangle.x, rectangle.y, rectangle.width, rectangle.height);
            return;
        }
        this.m_rUpdate.add(rectangle);
    }
    
    protected String readString(final StreamTokenizer streamTokenizer) {
        try {
            streamTokenizer.nextToken();
        }
        catch (IOException ex) {
            return null;
        }
        return streamTokenizer.sval;
    }
    
    public void SetDollarMode(final boolean bDollar) {
        this.m_bDollar = bDollar;
    }
    
    protected void loadDataFile(URL url) {
        this.m_bAllLoaded = false;
        try {
            InputStream openStream;
            if (url == null) {
                openStream = new FileInputStream(this.m_strDataFile);
            }
            else {
                url = new URL(url, this.m_strDataFile);
                openStream = url.openStream();
            }
            final StreamTokenizer streamTokenizer = new StreamTokenizer(openStream);
            streamTokenizer.eolIsSignificant(false);
            streamTokenizer.commentChar(59);
            streamTokenizer.quoteChar(34);
            streamTokenizer.parseNumbers();
            final int number = this.readNumber(streamTokenizer);
            this.m_strImages = new String[number];
            for (int i = 0; i < number; ++i) {
                this.m_strImages[i] = this.readString(streamTokenizer);
            }
            final int number2 = this.readNumber(streamTokenizer);
            this.m_strSounds = new String[number2];
            for (int j = 0; j < number2; ++j) {
                this.m_strSounds[j] = this.readString(streamTokenizer);
            }
            final int number3 = this.readNumber(streamTokenizer);
            this.m_nDataFile = new int[number3];
            for (int k = 0; k < number3; ++k) {
                this.m_nDataFile[k] = this.readNumber(streamTokenizer);
            }
            final int number4 = this.readNumber(streamTokenizer);
            this.m_strStrings = new String[number4];
            for (int l = 0; l < number4; ++l) {
                this.m_strStrings[l] = this.readString(streamTokenizer);
            }
            openStream.close();
        }
        catch (IOException ex) {}
    }
    
    public boolean keyDown(final Event event, final int n) {
        if (!this.m_bInitDone) {
            return false;
        }
        switch (n) {
            case 83:
            case 115: {
                this.enableSound(!this.m_bSoundEnabled);
                break;
            }
        }
        return true;
    }
    
    public void playSound(final int n) {
        if (this.m_clips != null && n < this.m_clips.length && this.m_clips[n] != null && this.m_bSoundEnabled) {
            this.m_audio.playClip((AudioClip)this.m_clips[n]);
        }
    }
    
    public void playSound(final AudioClip audioClip) {
        if (audioClip != null && this.m_bSoundEnabled) {
            this.m_audio.playClip(audioClip);
        }
    }
    
    protected synchronized void redraw() {
        if (this.m_imageBG == null) {
            return;
        }
        this.m_bRedraw = false;
        if (this.m_gImage == null) {
            this.m_gImage = this.m_imageBG.getGraphics();
        }
        final Graphics create = this.m_gImage.create();
        create.setColor(this.getBackground());
        if (this.m_rUpdate.width > 0) {
            create.clipRect(this.m_rUpdate.x, this.m_rUpdate.y, this.m_rUpdate.width, this.m_rUpdate.height);
            create.fillRect(this.m_rUpdate.x, this.m_rUpdate.y, this.m_rUpdate.width, this.m_rUpdate.height);
        }
        for (int i = 0; i < this.m_sprites.length; ++i) {
            if (this.m_sprites[i] != null && (this.m_rUpdate.width <= 0 || this.m_rUpdate.intersects(this.m_sprites[i].getBounding()))) {
                this.m_sprites[i].drawSprite(create);
            }
        }
        this.m_rUpdate.reshape(0, 0, 0, 0);
        create.dispose();
        if (this.m_bFatalError) {
            final Graphics create2 = this.m_gImage.create();
            create2.setColor(Color.black);
            final int n = this.size().width / 2;
            final int n2 = this.size().height / 2;
            create2.fillRect(n / 2, n2 / 2, n, n2);
            create2.setColor(Color.white);
            this.paintFatal(create2);
            create2.dispose();
        }
    }
    
    public GameCanvas() {
        this.m_nFrameRate = 40;
        this.m_bDropFrames = true;
        this.m_imageBG = null;
        this.m_bRedraw = false;
        this.m_sprites = null;
        this.m_rUpdate = new Rectangle(0, 0, 0, 0);
        this.m_nFrameCount = 0;
        this.m_saveEvent = null;
        this.\u00e4 = new Vector();
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
        this.m_user = null;
        this.m_nDataFile = null;
        this.m_strImages = null;
        this.m_strSounds = null;
        this.m_strStrings = null;
        this.m_nPercent = 0;
        this.m_nCountdown = 0;
        this.m_bLicensed = false;
        this.m_bPrivateLabel = false;
        this.m_strXtraCopy = "http://www.gamehouse.com";
        this.m_imgAd = null;
        this.m_strMessage = "Loading...";
        this.m_bAllLoaded = false;
        this.m_bSoundEnabled = true;
        this.m_rTemp = new Rectangle(0, 0, 0, 0);
        this.\u00e6 = null;
        this.m_gImage = null;
        this.m_audio = new AudioThread();
    }
    
    public void doUpdate() {
        final Graphics \u00e6 = this.\u00e6;
        if (this.m_bRedraw && \u00e6 != null) {
            final Graphics create = \u00e6.create();
            synchronized (this) {
                if (this.m_rUpdate.width > 0) {
                    create.clipRect(this.m_rUpdate.x, this.m_rUpdate.y, this.m_rUpdate.width, this.m_rUpdate.height);
                }
                this.redraw();
            }
            final Image imageBG = this.m_imageBG;
            if (imageBG != null) {
                create.drawImage(imageBG, 0, 0, null);
            }
            create.dispose();
        }
    }
    
    public void paint(final Graphics graphics) {
        if (this.m_bInitDone) {
            synchronized (this) {
                final Image imageBG = this.m_imageBG;
                if (imageBG != null) {
                    graphics.drawImage(imageBG, 0, 0, null);
                }
                return;
            }
        }
        this.paintLoading(graphics);
    }
    
    protected synchronized void releaseTempSprite(int n) {
        if (this.m_sprites[n] == null) {
            return;
        }
        this.changed(this.m_sprites[n].getBounding());
        this.m_sprites[n] = null;
        if (n == this.m_nTempSprite - 1) {
            do {
                --this.m_nTempSprite;
                --n;
            } while (this.m_sprites[n] == null);
        }
    }
    
    public void destroy() {
        if (!this.isStopped()) {
            this.stop();
        }
        System.out.println("destroyed applet");
        if (this.m_imageBG != null) {
            this.m_imageBG.flush();
        }
    }
    
    protected double readDouble(final StreamTokenizer streamTokenizer) {
        try {
            streamTokenizer.nextToken();
        }
        catch (IOException ex) {
            return 0.0;
        }
        if (streamTokenizer.ttype != -2) {
            return 0.0;
        }
        return streamTokenizer.nval;
    }
    
    public boolean initGame() {
        return false;
    }
    
    public void setImageSize(final int n, final int n2) {
        if (n == 0 || n2 == 0) {
            return;
        }
        try {
            if (this.m_imageBG != null) {
                this.m_imageBG.flush();
            }
            this.m_imageBG = this.createImage(n, n2);
        }
        catch (RuntimeException ex) {
            System.out.println("Error creating offscreen image");
        }
    }
    
    public void resize(final int n, final int n2) {
        super.resize(n, n2);
        this.setImageSize(n, n2);
    }
    
    public void resize(final Dimension dimension) {
        super.resize(dimension);
        this.setImageSize(dimension.width, dimension.height);
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
    
    public void setLicensed() {
        this.m_bLicensed = true;
    }
    
    public boolean licensed() {
        return this.m_bLicensed;
    }
    
    public boolean isLoaded() {
        return this.m_bAllLoaded;
    }
    
    static {
        GameCanvas.\u00e5 = "(c) 1998-2003 GameHouse, Inc. ";
    }
    
    protected synchronized void waitAnimation() {
        while (this.m_bWaiting && !this.m_bStopped) {
            try {
                this.wait(1000L);
            }
            catch (InterruptedException ex) {}
        }
    }
    
    public boolean mouseDown(final Event event, final int n, final int n2) {
        if (!this.m_bInitDone) {
            return false;
        }
        this.setEvent(event);
        return true;
    }
    
    public void setImages(final Image[] array, final Object[] clips) {
        this.changed(null);
        this.m_clips = clips;
        this.m_strImages = null;
        this.m_bAllLoaded = true;
    }
    
    protected synchronized int makeTempSprite(final int n, final int n2, final SpriteBase spriteBase) {
        for (int i = n; i < n2; ++i) {
            if (this.m_sprites[i] == null) {
                this.m_sprites[i] = spriteBase;
                if (i >= this.m_nTempSprite) {
                    this.m_nTempSprite = i + 1;
                }
                return i;
            }
        }
        return -1;
    }
    
    public void enableSound(final boolean bSoundEnabled) {
        if (!(this.m_bSoundEnabled = bSoundEnabled)) {
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
    
    public void stopSound(final int n) {
        if (this.m_clips != null && this.m_clips[n] != null) {
            this.m_audio.stopClip((AudioClip)this.m_clips[n]);
        }
    }
    
    public void stopSound(final AudioClip audioClip) {
        if (audioClip != null) {
            this.m_audio.stopClip(audioClip);
        }
    }
    
    protected void setEvent(final Event event) {
        if (event == null) {
            return;
        }
        if (!this.m_bInitDone || this.m_bStopped) {
            return;
        }
        synchronized (this.\u00e4) {
            this.\u00e4.addElement(event);
            this.\u00e4.notifyAll();
        }
        // monitorexit(this.\u00e4)
    }
    
    public boolean mouseDrag(final Event event, final int n, final int n2) {
        this.setEvent(event);
        return true;
    }
    
    public boolean betsActive() {
        return this.m_bBetsActive;
    }
}
