// 
// Decompiled by Procyon v0.5.30
// 

package com.screencastomatic.play;

import java.util.ArrayList;
import java.util.StringTokenizer;
import java.util.Collection;
import java.awt.FontMetrics;
import java.awt.geom.Area;
import java.util.Iterator;
import java.awt.Shape;
import java.awt.geom.Rectangle2D;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.image.ImageObserver;
import java.awt.RenderingHints;
import java.awt.EventQueue;
import java.awt.event.ComponentListener;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseListener;
import java.awt.AlphaComposite;
import java.awt.Dimension;
import java.util.TreeMap;
import java.util.Map;
import java.awt.Rectangle;
import com.screencastomatic.play.stream.b;
import java.awt.Composite;
import java.awt.Font;
import com.screencastomatic.play.stream.i;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.awt.Container;

public class Display extends Container implements n
{
    private com.screencastomatic.play.a.n m_player;
    protected BufferedImage m_fullImage;
    protected Image m_image;
    protected float m_scale;
    protected int m_scaleX;
    protected int m_scaleY;
    protected i m_webcam;
    protected BufferedImage m_imageWCDropShaddow;
    private boolean m_updateFullImage;
    private boolean m_updatedZoom;
    private static final Font a;
    private static final Font b;
    private static final Font c;
    private static final Font d;
    private String m_title;
    private Image m_offScreen;
    private k m_mouseListener;
    private final Composite m_transparent;
    private String m_url;
    private String m_captionsUrl;
    private Display$CaptionsStatus m_captionsStatus;
    private long m_showingPositionMS;
    private f m_taskRunner;
    public boolean m_loadingSec;
    public int EXTRA_HEIGHT;
    public boolean m_isAudio;
    public int m_bufferedSec;
    public boolean m_bufferedPause;
    public int m_minBufferedSec;
    private b m_capturedMouse;
    private b m_lastDrawnCapturedMouse;
    private p m_drawMouse;
    private boolean m_loadingError;
    private Rectangle m_lastDrawnControlsRect;
    private Rectangle m_lastDrawnPopoutsRect;
    private Rectangle m_lastDrawnSliderRect;
    private Map m_noteMap;
    private TreeMap m_loadedCaptionsMap;
    private TreeMap m_captionsMap;
    private boolean m_showNotes;
    private boolean m_isMAC;
    private s m_notesCheckBox;
    private s m_mouseCheckBox;
    private s m_clicksCheckBox;
    private s m_haloCheckBox;
    private s m_speedCheckBox;
    private s m_slowCheckBox;
    private BufferedImage m_popoutButton;
    private BufferedImage m_playButton;
    private BufferedImage m_pauseButton;
    private BufferedImage m_controlsEmpty;
    private BufferedImage m_sliderEmpty;
    private BufferedImage m_sliderLeft;
    private BufferedImage m_sliderRight;
    private BufferedImage m_sliderPlay;
    private BufferedImage m_sliderPlayHead;
    private BufferedImage m_sliderNoteHead;
    private BufferedImage m_sliderPause;
    private BufferedImage m_sliderPauseHead;
    private BufferedImage m_noteBackground;
    private BufferedImage m_noteBottom;
    private BufferedImage m_noteTop;
    private BufferedImage m_noteIcon;
    private BufferedImage m_notePointer;
    private BufferedImage m_noteMarker;
    private BufferedImage m_timebar;
    private Rectangle m_playPauseRect;
    private a m_playPauseEvent;
    private Rectangle m_fullscreenRect;
    private a m_fullscreenEvent;
    private D m_callBack;
    private boolean m_fullScreen;
    private Dimension m_fullFrameSize;
    private boolean m_dragDidPause;
    private boolean m_isDragging;
    
    public Display(final D callBack) {
        this.m_mouseListener = new k();
        this.m_transparent = AlphaComposite.getInstance(3, 0.7f);
        this.m_captionsStatus = Display$CaptionsStatus.a;
        this.m_showingPositionMS = -1L;
        this.m_loadingSec = false;
        this.EXTRA_HEIGHT = 25;
        this.m_isAudio = true;
        this.m_bufferedSec = 0;
        this.m_bufferedPause = false;
        this.m_minBufferedSec = 20;
        this.m_noteMap = new TreeMap();
        this.m_loadedCaptionsMap = new TreeMap();
        this.m_captionsMap = new TreeMap();
        this.m_showNotes = true;
        this.m_fullScreen = false;
        this.m_dragDidPause = false;
        this.m_isDragging = false;
        this.m_callBack = callBack;
    }
    
    public void b() {
        try {
            this.m_popoutButton = com.screencastomatic.play.c.b.a("png", "popout_button.png");
            this.m_playButton = com.screencastomatic.play.c.b.a("png", "play_button.png");
            this.m_pauseButton = com.screencastomatic.play.c.b.a("png", "pause_button.png");
            this.m_controlsEmpty = com.screencastomatic.play.c.b.a("png", "controls_empty.png");
            this.m_sliderEmpty = com.screencastomatic.play.c.b.a("png", "slider_empty.png");
            this.m_sliderLeft = com.screencastomatic.play.c.b.a("png", "slider_left.png");
            this.m_sliderRight = com.screencastomatic.play.c.b.a("png", "slider_right.png");
            this.m_sliderPlay = com.screencastomatic.play.c.b.a("png", "slider_play.png");
            this.m_sliderPlayHead = com.screencastomatic.play.c.b.a("png", "slider_play_head.png");
            this.m_sliderNoteHead = com.screencastomatic.play.c.b.a("png", "slider_note_head.png");
            this.m_sliderPause = com.screencastomatic.play.c.b.a("png", "slider_pause.png");
            this.m_sliderPauseHead = com.screencastomatic.play.c.b.a("png", "slider_pause_head.png");
            this.m_timebar = com.screencastomatic.play.c.b.a("png", "watch_timebar.png");
            this.m_noteBackground = com.screencastomatic.play.c.b.a("png", "note_background.png");
            this.m_noteBottom = com.screencastomatic.play.c.b.a("png", "note_bottom.png");
            this.m_noteTop = com.screencastomatic.play.c.b.a("png", "note_top.png");
            this.m_noteIcon = com.screencastomatic.play.c.b.a("png", "note_icon.png");
            this.m_notePointer = com.screencastomatic.play.c.b.a("png", "note_pointer.png");
            this.m_noteMarker = com.screencastomatic.play.c.b.a("png", "note_marker.png");
            this.m_isMAC = System.getProperty("os.name").toLowerCase().startsWith("mac");
            this.m_drawMouse = new p();
            this.addMouseListener(this.m_mouseListener);
            this.addMouseMotionListener(this.m_mouseListener);
            this.addComponentListener(new A(this));
            this.m_mouseListener.a(new J(this));
            this.m_playPauseRect = new Rectangle(0, 0, this.getWidth(), this.getHeight());
            this.m_playPauseEvent = new I(this);
            this.m_mouseListener.a(this.m_playPauseRect, this.m_playPauseEvent);
            this.m_fullscreenRect = new Rectangle(this.getWidth() - this.m_popoutButton.getWidth() - 10, 10, this.m_popoutButton.getWidth(), this.m_popoutButton.getHeight());
            this.m_fullscreenEvent = new L(this);
            this.m_mouseListener.a(this.m_fullscreenRect, this.m_fullscreenEvent);
            this.m_captionsUrl = this.m_callBack.a("captionsUrl");
            System.out.println("Captions url: " + this.m_captionsUrl);
            if (this.m_captionsUrl != null) {
                this.EXTRA_HEIGHT += 78;
                this.j();
            }
            this.m_url = this.m_callBack.a("url");
            if (this.m_url == null) {
                System.out.println("ERROR: No url set.");
                return;
            }
            System.out.println("Url: " + this.m_url);
            this.m_title = this.m_callBack.a("title");
            final String a = this.m_callBack.a("bufferSec");
            if (a != null) {
                this.m_minBufferedSec = Integer.valueOf(a);
                System.out.println("Got buffer sec: " + this.m_minBufferedSec);
            }
            int n = 0;
            String a2;
            while ((a2 = this.m_callBack.a("note" + n++)) != null) {
                final String[] split = a2.split("\\s*,\\s*", 3);
                if (split.length != 3) {
                    System.out.println("Can't parse note: " + a2);
                }
                else {
                    final int intValue = Integer.valueOf(split[0]);
                    this.m_noteMap.put(intValue, new com.screencastomatic.play.b(intValue, split[1], split[2]));
                }
            }
            int intValue2 = 0;
            if (this.m_callBack.a("position") != null) {
                intValue2 = Integer.valueOf(this.m_callBack.a("position"));
                System.out.println("Got start sec: " + intValue2);
            }
            this.m_player = new com.screencastomatic.play.a.n(this, this.m_url, intValue2 * 1000, this.m_minBufferedSec);
            this.m_isAudio = this.m_player.f();
            if (this.m_callBack.a("play") != null && !this.m_callBack.a("play").equals("0")) {
                this.m_player.b();
            }
            this.m_taskRunner = new f();
        }
        catch (Exception ex) {
            System.out.println("Failed to init.");
            ex.printStackTrace();
            this.m_loadingError = true;
            this.h();
        }
    }
    
    public void c() {
        if (this.m_mouseListener != null) {
            this.m_mouseListener.a();
        }
        if (this.m_player != null) {
            this.m_player.g();
        }
        if (this.m_taskRunner != null) {
            this.m_taskRunner.a();
        }
    }
    
    public int d() {
        return (this.m_player != null) ? ((int)this.m_player.h() / 1000) : 0;
    }
    
    public boolean e() {
        return this.m_player == null || this.m_player.d() || !this.m_player.c() || this.m_player.e();
    }
    
    public void a(final boolean b) {
        if (this.m_player != null) {
            this.m_player.a(b);
        }
    }
    
    public void b(final int n) {
        this.m_taskRunner.a(new K(this, n));
    }
    
    public boolean f() {
        return this.m_fullScreen;
    }
    
    public void b(final boolean fullScreen) {
        this.m_fullScreen = fullScreen;
        this.m_callBack.a(this.m_fullScreen);
    }
    
    public Dimension g() {
        return this.m_fullFrameSize;
    }
    
    public void a(final com.screencastomatic.play.stream.k k) {
        this.m_loadingSec = false;
        if (this.m_fullImage == null) {
            this.m_fullImage = new BufferedImage(k.d().getWidth(), k.d().getHeight(), 1);
        }
        if (this.m_fullFrameSize == null) {
            this.m_fullFrameSize = new Dimension(k.d().getWidth(), k.d().getHeight() + this.EXTRA_HEIGHT);
        }
        k.a(this.m_fullImage);
        EventQueue.invokeLater(new N(this, k.a().isEmpty() ? null : this.a(this.m_fullImage)));
    }
    
    public void a(final i i) {
        EventQueue.invokeLater(new M(this, (i.a() == null) ? null : this.b(i)));
    }
    
    private void j() {
        new Thread(new P(this)).start();
    }
    
    private i b(final i i) {
        if (this.m_scale == 0.0f) {
            return null;
        }
        if (this.m_scale == 1.0f) {
            return i;
        }
        final int n = (int)(i.b().x * this.m_scale);
        final int n2 = (int)(i.b().y * this.m_scale);
        final BufferedImage bufferedImage = (BufferedImage)this.createImage((int)(i.a().getWidth() * this.m_scale), (int)(i.a().getHeight() * this.m_scale));
        final Graphics2D graphics = bufferedImage.createGraphics();
        graphics.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
        graphics.drawImage(i.a(), 0, 0, bufferedImage.getWidth(null), bufferedImage.getHeight(null), null);
        graphics.dispose();
        return new i(bufferedImage, new Point(n, n2), i.c());
    }
    
    protected Image a(final BufferedImage bufferedImage) {
        if (bufferedImage == null) {
            return null;
        }
        final int width = this.getWidth();
        final int n = this.getHeight() - this.EXTRA_HEIGHT;
        if (width <= 0 || n <= 0) {
            return null;
        }
        final float n2 = bufferedImage.getWidth() * 1.0f / bufferedImage.getHeight();
        this.m_scale = 1.0f;
        final float scale = width * 1.0f / bufferedImage.getWidth();
        final float scale2 = n * 1.0f / bufferedImage.getHeight();
        int width2;
        int height;
        if (scale2 < 1.0 || scale < 1.0) {
            if ((int)(scale2 * 1000.0f) < (int)(scale * 1000.0f)) {
                width2 = (int)(bufferedImage.getWidth() * scale2);
                height = (int)(width2 / n2);
                this.m_scale = scale2;
            }
            else {
                height = (int)(bufferedImage.getHeight() * scale);
                width2 = (int)(height * n2);
                this.m_scale = scale;
            }
        }
        else {
            width2 = bufferedImage.getWidth();
            height = bufferedImage.getHeight();
        }
        final int scaleX = (width - width2) / 2;
        final int scaleY = (n - height) / 2;
        final Image image = this.createImage(width2, height);
        if (image == null) {
            System.out.println("createImage failed for resize.");
            return null;
        }
        final Graphics2D graphics2D = (Graphics2D)image.getGraphics();
        if (bufferedImage.getWidth() != width2 || bufferedImage.getHeight() != height) {
            graphics2D.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
            graphics2D.drawImage(bufferedImage, 0, 0, width2, height, null);
        }
        else {
            graphics2D.drawImage(bufferedImage, 0, 0, null);
        }
        graphics2D.dispose();
        this.m_scaleX = scaleX;
        this.m_scaleY = scaleY;
        return image;
    }
    
    public void a(final b capturedMouse) {
        this.m_capturedMouse = capturedMouse;
        if (this.m_mouseCheckBox != null && this.m_mouseCheckBox.e()) {
            this.m_mouseCheckBox.b(false);
            this.m_mouseCheckBox.a(true);
        }
        if (this.m_clicksCheckBox != null && this.m_clicksCheckBox.e()) {
            this.m_clicksCheckBox.b(false);
            this.m_clicksCheckBox.a(true);
        }
        if (this.m_haloCheckBox != null && this.m_haloCheckBox.e()) {
            this.m_haloCheckBox.b(false);
            this.m_haloCheckBox.a(true);
        }
        if (this.m_speedCheckBox != null && this.m_speedCheckBox.e()) {
            this.m_speedCheckBox.b(false);
            this.m_speedCheckBox.a(true);
        }
        if (this.m_slowCheckBox != null && this.m_slowCheckBox.e()) {
            this.m_slowCheckBox.b(false);
            this.m_slowCheckBox.a(true);
        }
        EventQueue.invokeLater(new O(this));
    }
    
    public void a(final int bufferedSec) {
        System.out.println("Buffered sec: " + bufferedSec);
        final int bufferedSec2 = this.m_bufferedSec;
        this.m_bufferedSec = bufferedSec;
        if (this.m_player != null && this.m_player.c() && !this.m_player.e()) {
            if (bufferedSec == 0 && !this.m_player.d()) {
                this.m_bufferedPause = true;
                this.m_player.a(true);
                this.h();
            }
            if (bufferedSec >= this.m_minBufferedSec && this.m_player.d() && this.m_bufferedPause) {
                this.m_bufferedPause = false;
                this.m_player.a(false);
                this.h();
            }
            if (bufferedSec != bufferedSec2 && this.m_bufferedPause) {
                this.h();
            }
        }
    }
    
    public void a() {
        if (this.m_player == null) {
            return;
        }
        this.m_showingPositionMS = this.m_player.a();
        this.h();
    }
    
    public void update(final Graphics graphics) {
        this.paint(graphics);
    }
    
    public void paint(final Graphics graphics) {
        try {
            if (graphics == null) {
                return;
            }
            if (this.m_offScreen == null || this.m_offScreen.getWidth(null) != this.getWidth() || this.m_offScreen.getHeight(null) != this.getHeight()) {
                this.i();
            }
            if (this.m_offScreen == null) {
                return;
            }
            try {
                graphics.drawImage(this.m_offScreen, 0, 0, null);
            }
            catch (Exception ex) {
                System.out.println("Failed to draw so bailing.");
                ex.printStackTrace();
                final Thread thread = new Thread(new z(this));
            }
        }
        catch (Exception ex2) {
            System.out.println("Ignoring exception at paint.");
            ex2.printStackTrace();
        }
    }
    
    public void h() {
        this.i();
        this.paint(this.getGraphics());
    }
    
    public void i() {
        if (this.getWidth() == 0 || this.getHeight() == 0) {
            return;
        }
        if (this.m_offScreen == null || this.m_offScreen.getWidth(null) != this.getWidth() || this.m_offScreen.getHeight(null) != this.getHeight()) {
            this.m_offScreen = this.createImage(this.getWidth(), this.getHeight());
            if (this.m_offScreen == null) {
                System.out.println("createImage failed for offscreen.");
                return;
            }
            final Graphics2D graphics2D = (Graphics2D)this.m_offScreen.getGraphics();
            graphics2D.setColor(Color.GRAY);
            graphics2D.fillRect(0, 0, this.getWidth(), this.getHeight());
        }
        final Graphics2D graphics2D2 = (Graphics2D)this.m_offScreen.getGraphics();
        graphics2D2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        if (this.m_updatedZoom || this.m_lastDrawnControlsRect != null) {
            if (this.m_image != null) {
                graphics2D2.setClip(this.a(new Rectangle2D.Double(this.m_scaleX, this.m_scaleY, this.m_image.getWidth(null), this.m_image.getHeight(null))));
            }
            graphics2D2.setColor(Color.GRAY);
            graphics2D2.fillRect(0, 0, this.getWidth(), this.getHeight());
            graphics2D2.setClip(null);
            this.m_updatedZoom = false;
        }
        if (this.m_loadingError) {
            graphics2D2.setColor(Color.BLACK);
            graphics2D2.drawString("Error while loading screencast so we can't play it... Sorry!", 10, 20);
        }
        if (this.m_image != null) {
            if (!this.m_updateFullImage) {
                if (this.m_lastDrawnCapturedMouse != null) {
                    final Rectangle a = this.m_drawMouse.a(this.m_lastDrawnCapturedMouse, 0L);
                    if (a != null) {
                        graphics2D2.setClip(a.x + this.m_scaleX, a.y + this.m_scaleY, a.width, a.height);
                        graphics2D2.drawImage(this.m_image, this.m_scaleX, this.m_scaleY, null);
                    }
                    final Rectangle a2 = this.m_drawMouse.a(this.m_lastDrawnCapturedMouse);
                    if (a2 != null) {
                        graphics2D2.setClip(a2.x + this.m_scaleX, a2.y + this.m_scaleY, a2.width, a2.height);
                        graphics2D2.drawImage(this.m_image, this.m_scaleX, this.m_scaleY, null);
                    }
                }
                if (this.m_lastDrawnControlsRect != null) {
                    graphics2D2.setClip(this.m_lastDrawnControlsRect.x, this.m_lastDrawnControlsRect.y, this.m_lastDrawnControlsRect.width, this.m_lastDrawnControlsRect.height);
                    graphics2D2.drawImage(this.m_image, this.m_scaleX, this.m_scaleY, null);
                }
                if (this.m_lastDrawnPopoutsRect != null) {
                    graphics2D2.setClip(this.m_lastDrawnPopoutsRect.x, this.m_lastDrawnPopoutsRect.y, this.m_lastDrawnPopoutsRect.width, this.m_lastDrawnPopoutsRect.height);
                    graphics2D2.drawImage(this.m_image, this.m_scaleX, this.m_scaleY, null);
                }
                graphics2D2.setClip(null);
            }
            else {
                graphics2D2.drawImage(this.m_image, this.m_scaleX, this.m_scaleY, null);
            }
            if (this.m_webcam != null) {
                boolean b = false;
                if (this.m_imageWCDropShaddow == null || com.screencastomatic.play.c.b.a(this.m_webcam.a(), 10).width != this.m_imageWCDropShaddow.getWidth()) {
                    this.m_imageWCDropShaddow = com.screencastomatic.play.c.b.b(this.m_webcam.a(), 10);
                    b = true;
                }
                if (this.m_updateFullImage || b) {
                    final Point a3 = com.screencastomatic.play.c.b.a(new Point(this.m_webcam.b().x + this.m_scaleX, this.m_webcam.b().y + this.m_scaleY), 10);
                    graphics2D2.drawImage(this.m_imageWCDropShaddow, a3.x, a3.y, null);
                }
                graphics2D2.drawImage(this.m_webcam.a(), this.m_webcam.b().x + this.m_scaleX, this.m_webcam.b().y + this.m_scaleY, null);
            }
            this.m_updateFullImage = false;
            if (this.m_capturedMouse != null) {
                graphics2D2.setClip(this.m_scaleX, this.m_scaleY, this.m_image.getWidth(null), this.m_image.getHeight(null));
                graphics2D2.translate(this.m_scaleX, this.m_scaleY);
                this.m_drawMouse.a(graphics2D2, this.m_lastDrawnCapturedMouse = this.m_capturedMouse.a(this.m_scale));
                graphics2D2.translate(-this.m_scaleX, -this.m_scaleY);
                graphics2D2.setClip(null);
            }
        }
        com.screencastomatic.play.b b2 = null;
        if (this.m_player != null) {
            if (this.m_showNotes) {
                final int n = (int)(((this.m_showingPositionMS != -1L) ? this.m_showingPositionMS : this.m_player.h()) / 1000L);
                for (final com.screencastomatic.play.b b3 : this.m_noteMap.values()) {
                    if (n >= b3.a && n < b3.b) {
                        b2 = b3;
                    }
                }
            }
            if (this.m_mouseListener.b() || !this.m_player.c() || this.m_player.d() || this.m_player.e() || this.m_loadingSec || b2 != null) {
                this.m_lastDrawnControlsRect = this.d(graphics2D2);
                if (b2 != null) {
                    this.m_lastDrawnControlsRect = this.a(graphics2D2, this.m_lastDrawnControlsRect, b2);
                }
            }
            else {
                this.m_lastDrawnControlsRect = null;
            }
        }
        graphics2D2.setColor(Color.BLACK);
        graphics2D2.fillRect(0, this.getHeight() - this.EXTRA_HEIGHT, this.getWidth(), this.EXTRA_HEIGHT);
        if (this.m_captionsUrl != null) {
            this.a(graphics2D2);
        }
        for (int i = 0; i < this.getWidth(); ++i) {
            graphics2D2.drawImage(this.m_timebar, i, this.getHeight() - 25, null);
        }
        if (this.m_player != null) {
            if (!this.m_fullScreen && (this.m_mouseListener.b() || !this.m_player.c() || this.m_player.d() || this.m_loadingSec)) {
                this.m_lastDrawnPopoutsRect = new Rectangle(this.getWidth() - 10 - this.m_popoutButton.getWidth(), 10, this.m_popoutButton.getWidth(), this.m_popoutButton.getHeight());
                graphics2D2.drawImage(this.m_popoutButton, this.getWidth() - 10 - this.m_popoutButton.getWidth(), 10, null);
            }
            else {
                this.m_lastDrawnPopoutsRect = null;
            }
            if (this.m_mouseListener.b() || !this.m_player.c() || this.m_player.d() || this.m_player.e() || this.m_loadingSec || b2 != null) {
                this.b(graphics2D2);
            }
            else {
                final String title = this.m_title;
                graphics2D2.setColor(Color.WHITE);
                graphics2D2.drawString(title, 5, this.getHeight() - 8);
                this.c(graphics2D2);
            }
        }
        graphics2D2.dispose();
    }
    
    private void a(final Graphics2D graphics2D) {
        if (this.m_captionsStatus == Display$CaptionsStatus.b) {
            this.m_captionsMap = h.a(graphics2D, this.getWidth(), this.m_loadedCaptionsMap);
            this.m_captionsStatus = Display$CaptionsStatus.c;
        }
        graphics2D.setColor(Color.WHITE);
        String c = null;
        if (this.m_captionsStatus == Display$CaptionsStatus.a) {
            c = "Loading Captions...";
        }
        else if (this.m_captionsStatus == Display$CaptionsStatus.d) {
            c = "Error Loading Captions!";
        }
        else if (this.m_captionsStatus == Display$CaptionsStatus.c) {
            final long n = (this.m_player == null || this.m_player.d() || this.m_player.e()) ? this.m_showingPositionMS : this.m_player.h();
            for (final m m : this.m_captionsMap.values()) {
                if (n >= m.a && n < m.b) {
                    c = m.c;
                }
            }
        }
        if (c != null) {
            h.a(graphics2D, this.getWidth(), this.getHeight() - 25, c);
        }
    }
    
    private Area a(final Rectangle2D rectangle2D) {
        final Area area = new Area(new Rectangle2D.Double(0.0, 0.0, this.getWidth(), this.getHeight()));
        area.subtract(new Area(rectangle2D));
        return area;
    }
    
    private void b(final Graphics2D graphics2D) {
        final int n = this.getWidth() - 15;
        final int n2 = this.getHeight() - 21;
        if (this.m_notesCheckBox == null) {
            (this.m_notesCheckBox = new s(new Point(n, n2), new B(this), "Notes", graphics2D, !this.m_noteMap.isEmpty())).b(this.m_noteMap.isEmpty());
            this.m_mouseListener.a(this.m_notesCheckBox.f(), this.m_notesCheckBox);
        }
        final int n3 = n - (this.m_notesCheckBox.f().width + 20);
        this.m_notesCheckBox.a(graphics2D);
        if (this.m_mouseCheckBox == null) {
            (this.m_mouseCheckBox = new s(new Point(n3, n2), new C(this), "Cursor", graphics2D, false)).a(true);
            this.m_mouseListener.a(this.m_mouseCheckBox.f(), this.m_mouseCheckBox);
        }
        final int n4 = n3 - (this.m_mouseCheckBox.f().width + 20);
        this.m_mouseCheckBox.a(graphics2D);
        if (this.m_clicksCheckBox == null) {
            (this.m_clicksCheckBox = new s(new Point(n4, n2), new v(this), "Clicks", graphics2D, false)).a(true);
            this.m_mouseListener.a(this.m_clicksCheckBox.f(), this.m_clicksCheckBox);
        }
        final int n5 = n4 - (this.m_clicksCheckBox.f().width + 20);
        this.m_clicksCheckBox.a(graphics2D);
        if (this.m_haloCheckBox == null) {
            (this.m_haloCheckBox = new s(new Point(n5, n2), new w(this), "Halo", graphics2D, false)).a(true);
            this.m_mouseListener.a(this.m_haloCheckBox.f(), this.m_haloCheckBox);
        }
        final int n6 = n5 - (this.m_haloCheckBox.f().width + 20);
        this.m_haloCheckBox.a(graphics2D);
        if (this.m_speedCheckBox == null) {
            (this.m_speedCheckBox = new s(new Point(n6, n2), new x(this), "PlayFast", graphics2D, false)).a(false);
            this.m_mouseListener.a(this.m_speedCheckBox.f(), this.m_speedCheckBox);
        }
        final int n7 = n6 - (this.m_speedCheckBox.f().width + 20);
        this.m_speedCheckBox.a(graphics2D);
        if (this.m_slowCheckBox == null) {
            (this.m_slowCheckBox = new s(new Point(n7, n2), new y(this), "PlaySlow", graphics2D, false)).a(false);
            this.m_mouseListener.a(this.m_slowCheckBox.f(), this.m_slowCheckBox);
        }
        final int n8 = n7 - (this.m_slowCheckBox.f().width + 20);
        this.m_slowCheckBox.a(graphics2D);
    }
    
    private void c(final Graphics2D graphics2D) {
        final Font font = graphics2D.getFont();
        graphics2D.setFont(Display.d);
        final int n = this.getHeight() - 8;
        this.m_showingPositionMS = this.m_player.h();
        final int n2 = (int)(this.m_showingPositionMS / 1000L);
        final int n3 = (int)this.m_player.a() / 1000;
        final FontMetrics fontMetrics = graphics2D.getFontMetrics(Display.a);
        String s = String.format("%d:%02d / %d:%02d", n2 / 60, n2 % 60, n3 / 60, n3 % 60);
        if (!this.m_isAudio) {
            s += " (No Audio)";
        }
        final int n4 = this.getWidth() - fontMetrics.stringWidth(s);
        graphics2D.setColor(Color.WHITE);
        graphics2D.drawString(s, n4, n);
        graphics2D.setFont(font);
    }
    
    private Rectangle a(final Graphics2D graphics2D, final Rectangle rectangle, final com.screencastomatic.play.b b) {
        graphics2D.setFont(Display.c);
        graphics2D.setColor(Color.BLACK);
        final Collection a = this.a(graphics2D, b.d, this.getWidth() - this.m_noteIcon.getWidth() - 20);
        final int height = graphics2D.getFontMetrics().getHeight();
        final int n = a.size() * height;
        final int n2 = 10;
        final int max = Math.max(this.m_noteIcon.getHeight(), n + n2 * 2);
        final int n3 = this.getHeight() - max - this.EXTRA_HEIGHT - rectangle.height - this.m_noteTop.getHeight() - 1;
        for (int i = 0; i < this.getWidth(); ++i) {
            graphics2D.drawImage(this.m_noteTop, i, n3, null);
        }
        graphics2D.setClip(0, n3 + this.m_noteTop.getHeight(), this.getWidth(), this.m_noteTop.getHeight() + max);
        for (int j = 0; j < this.getWidth(); j += this.m_noteBackground.getWidth()) {
            for (int k = n3 + this.m_noteTop.getHeight(); k < n3 + this.m_noteTop.getHeight() + max; k += this.m_noteBackground.getHeight()) {
                graphics2D.drawImage(this.m_noteBackground, j, k, null);
            }
        }
        graphics2D.setClip(null);
        for (int l = 0; l < this.getWidth(); ++l) {
            graphics2D.drawImage(this.m_noteBottom, l, n3 + this.m_noteTop.getHeight() + max, null);
        }
        graphics2D.drawImage(this.m_notePointer, b.e - this.m_notePointer.getWidth() / 2, rectangle.y, null);
        graphics2D.drawImage(this.m_noteIcon, 0, n3, null);
        final int width = this.m_noteIcon.getWidth();
        int n4 = n3 + n2 + height;
        if (a.size() == 1) {
            n4 += 5;
        }
        final Iterator<String> iterator = a.iterator();
        for (int n5 = 0; n5 < a.size(); ++n5, n4 += height) {
            graphics2D.drawString(iterator.next(), width, n4);
        }
        return new Rectangle(0, n3, this.getWidth(), this.getHeight() - n3);
    }
    
    private Rectangle d(final Graphics2D graphics2D) {
        final int n = 0;
        final int n2 = this.getHeight() - this.m_controlsEmpty.getHeight() - this.EXTRA_HEIGHT;
        graphics2D.setFont(Display.b);
        graphics2D.setColor(Color.WHITE);
        if (this.m_player.c() && (!this.m_player.d() || this.m_dragDidPause) && !this.m_player.e()) {
            graphics2D.drawImage(this.m_pauseButton, n, n2, null);
        }
        else {
            graphics2D.drawImage(this.m_playButton, n, n2, null);
        }
        final int n3 = n + this.m_playButton.getWidth();
        final int n4 = 115;
        final int n5 = n2 + 22;
        final int n6 = this.getWidth() - n3 - n4;
        final long a = this.m_player.a();
        final long showingPositionMS = (this.m_showingPositionMS == -1L || (!this.m_player.d() && this.m_player.c() && !this.m_player.e())) ? this.m_player.h() : this.m_showingPositionMS;
        this.m_showingPositionMS = showingPositionMS;
        final long n7 = showingPositionMS;
        graphics2D.drawImage(this.m_sliderLeft, n3, n2, null);
        int i = n3 + this.m_sliderLeft.getWidth();
        final int n8 = n6 - this.m_sliderLeft.getWidth() - this.m_sliderRight.getWidth();
        final int n9 = (int)(n8 * (n7 * 1.0f / a));
        final Rectangle lastDrawnSliderRect = new Rectangle(i, n2, n8, this.m_sliderEmpty.getHeight());
        int n10;
        for (n10 = i; i < n10 + n9; ++i) {
            if (!this.m_player.c() || (!this.m_dragDidPause && this.m_player.d()) || this.m_player.e()) {
                graphics2D.drawImage(this.m_sliderPause, i, n2, null);
            }
            else {
                graphics2D.drawImage(this.m_sliderPlay, i, n2, null);
            }
        }
        final int n11 = i;
        while (i < n10 + n8) {
            graphics2D.drawImage(this.m_sliderEmpty, i, n2, null);
            ++i;
        }
        graphics2D.drawImage(this.m_sliderRight, i, n2, null);
        int j = i + this.m_sliderRight.getWidth();
        boolean b = false;
        for (final com.screencastomatic.play.b b2 : this.m_noteMap.values()) {
            final int e = (int)(b2.a * 1000 / (a / lastDrawnSliderRect.width)) + this.m_playButton.getWidth() + this.m_sliderLeft.getWidth();
            graphics2D.drawImage(this.m_noteMarker, e - this.m_noteMarker.getWidth() / 2, n2, null);
            b2.e = e;
            if (n11 >= e && n11 < e + this.m_noteMarker.getWidth()) {
                b = true;
            }
        }
        if (!b) {
            if (!this.m_player.c() || (!this.m_dragDidPause && this.m_player.d()) || this.m_player.e()) {
                graphics2D.drawImage(this.m_sliderPauseHead, n11 - this.m_sliderPauseHead.getWidth() / 2, n2, null);
            }
            else {
                graphics2D.drawImage(this.m_sliderPlayHead, n11 - this.m_sliderPlayHead.getWidth() / 2, n2, null);
            }
        }
        else {
            graphics2D.drawImage(this.m_sliderNoteHead, n11 - this.m_sliderNoteHead.getWidth() / 2, n2, null);
        }
        if (this.m_lastDrawnSliderRect != null) {
            this.m_mouseListener.b(this.m_lastDrawnSliderRect);
        }
        this.m_lastDrawnSliderRect = lastDrawnSliderRect;
        this.m_mouseListener.a(this.m_lastDrawnSliderRect, new E(this, lastDrawnSliderRect, a));
        final int n12 = j;
        while (j < this.getWidth()) {
            graphics2D.drawImage(this.m_controlsEmpty, j, n2, null);
            ++j;
        }
        String s;
        if (!this.m_isDragging && this.m_player.d() && this.m_bufferedSec < this.m_minBufferedSec) {
            s = "Buffering " + this.m_bufferedSec * 100 / this.m_minBufferedSec + "%";
        }
        else {
            s = String.format("%d:%02d.%d / %d:%02d", n7 / 1000L / 60L, n7 / 1000L % 60L, n7 % 1000L / 100L, a / 1000L / 60L, a / 1000L % 60L);
        }
        graphics2D.drawString(s, n12 + (n4 - graphics2D.getFontMetrics().stringWidth(s)) / 2, n5);
        return new Rectangle(0, n2, this.getWidth(), this.getHeight() - n2 - this.EXTRA_HEIGHT);
    }
    
    private void k() {
        try {
            this.m_loadingSec = true;
            this.m_player.g();
            this.m_capturedMouse = null;
            this.h();
            System.out.println("Loading new pos: " + this.m_showingPositionMS);
            this.m_player = new com.screencastomatic.play.a.n(this, this.m_url, this.m_showingPositionMS, this.m_minBufferedSec);
            if (this.m_speedCheckBox != null && this.m_speedCheckBox.d()) {
                this.m_player.a(2);
            }
            else if (this.m_slowCheckBox != null && this.m_slowCheckBox.d()) {
                this.m_player.a(-2);
            }
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
    }
    
    private Collection a(final Graphics graphics, final String s, final int n) {
        final FontMetrics fontMetrics = graphics.getFontMetrics(Display.a);
        final StringTokenizer stringTokenizer = new StringTokenizer(s, " ");
        final ArrayList<String> list = new ArrayList<String>();
        String s2 = "";
        while (stringTokenizer.hasMoreTokens()) {
            final String nextToken = stringTokenizer.nextToken();
            final String s3 = (s2.length() > 0) ? (s2 + " " + nextToken) : nextToken;
            if (fontMetrics.stringWidth(s3) > n) {
                list.add(s2);
                s2 = nextToken;
            }
            else {
                s2 = s3;
            }
        }
        list.add(s2);
        return list;
    }
    
    static {
        a = new Font("Arial", 0, 16);
        b = new Font("Arial", 0, 14);
        c = new Font("Arial", 0, 16);
        d = new Font("Arial", 0, 14);
    }
}
