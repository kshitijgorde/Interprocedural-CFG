// 
// Decompiled by Procyon v0.5.30
// 

package javaviewer;

import java.awt.Color;
import java.io.IOException;
import java.awt.Toolkit;
import java.awt.image.ImageObserver;
import java.awt.event.MouseEvent;
import java.awt.event.MouseAdapter;
import java.awt.Rectangle;
import java.awt.Font;
import java.awt.Component;
import java.awt.Label;
import java.awt.Graphics;
import java.awt.MediaTracker;
import java.awt.Image;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.Canvas;

public final class ImageThread extends Canvas implements MouseMotionListener, MouseListener, Runnable
{
    final int G_BUFF_SIZE = 300000;
    private int _$4364;
    private int _$4365;
    public byte[][] g_buffer;
    public boolean[] full_flag;
    public boolean[] lock_flag;
    public int wBuffNum;
    private int _$4366;
    public int[] g_buffLength;
    private Image _$4367;
    private int _$4368;
    private int _$4369;
    private Image _$4370;
    private Image _$4371;
    private MediaTracker _$4372;
    public Image img;
    public Image bimg;
    private Graphics _$4375;
    private boolean _$4376;
    private int _$4377;
    private int _$4378;
    private int _$4379;
    private int _$4380;
    public int n_iSizeW;
    private int _$4381;
    private int _$4382;
    private int _$4383;
    public int vn_iSizeW;
    private int _$4385;
    public int imgSize;
    final int fn_iSizeW = 320;
    final int fn_iSizeH = 240;
    private int _$4388;
    private int _$4389;
    private int _$4390;
    public boolean digitalZoom;
    private boolean _$1879;
    private int _$4391;
    private int _$4392;
    private int _$4393;
    public boolean control;
    public boolean dispDateFlg;
    public Label dateLabel;
    public PictureFrame pictFrame;
    private Viewer _$4305;
    private boolean _$1176;
    volatile Thread runner;
    public PanoramaView panoramaView;
    public ZoomControler zoomControler;
    public UpDownControler upDownControler;
    private int _$4395;
    private String _$4396;
    public boolean areaDefining;
    public MSPoint startArea;
    public MSPoint curArea;
    
    public ImageThread(final Viewer $4305) {
        this._$4364 = 182;
        this._$4365 = 20;
        this.wBuffNum = 0;
        this._$4367 = null;
        this._$4368 = 182;
        this._$4369 = 20;
        this._$4370 = null;
        this._$4371 = null;
        this.img = null;
        this.bimg = null;
        this._$4375 = null;
        this._$4376 = false;
        this._$4377 = 0;
        this._$4378 = 0;
        this._$4379 = 0;
        this._$4380 = 0;
        this.n_iSizeW = 0;
        this._$4381 = 0;
        this._$4382 = 0;
        this._$4383 = 0;
        this.vn_iSizeW = 0;
        this._$4385 = 0;
        this.imgSize = 2;
        this._$4388 = 0;
        this._$4389 = 0;
        this._$4390 = 0;
        this.digitalZoom = false;
        this._$1879 = false;
        this._$4391 = 0;
        this._$4392 = 0;
        this._$4393 = 0;
        this.control = false;
        this.dispDateFlg = false;
        this.dateLabel = new Label();
        this._$4305 = null;
        this._$1176 = true;
        this.panoramaView = null;
        this.zoomControler = null;
        this.upDownControler = null;
        this._$4396 = "";
        this._$4372 = new MediaTracker(this);
        this.g_buffer = new byte[3][300000];
        this.full_flag = new boolean[] { false, false, false };
        this.lock_flag = new boolean[] { false, false, false };
        this.g_buffLength = new int[] { 0, 0, 0 };
        this._$4305 = $4305;
        this.imgSize = this._$4305.ViewSize;
        if (this.panoramaView == null) {
            this.panoramaView = new PanoramaView($4305);
        }
        else {
            this.panoramaView.setVisible(true);
        }
        if (this.zoomControler == null) {
            this.zoomControler = new ZoomControler($4305);
        }
        else {
            this.zoomControler.setVisible(true);
        }
        if (this.upDownControler == null) {
            this.upDownControler = new UpDownControler($4305);
            this._$4395 = this.upDownControler.getHeight();
        }
        else {
            this.upDownControler.setVisible(true);
        }
        this.pictFrame = new PictureFrame($4305);
    }
    
    public final void init() {
        this.runner = null;
        this.g_buffer = new byte[3][300000];
        this.full_flag = new boolean[] { false, false, false };
        this.lock_flag = new boolean[] { false, false, false };
        this.bimg = this.createImage(640, 480);
        (this._$4375 = this.bimg.getGraphics()).setFont(new Font("Dialog", 1, 12));
        this._$4375.setColor(this._$4305.DateForeColor);
        this.pictFrame.setForeground(this._$4305.PictFrameColor);
        this.addMouseListener(this);
        this.addMouseMotionListener(this);
        this.dateLabel.setVisible(false);
        this.dateLabel.setAlignment(0);
        this.dateLabel.setBackground(this._$4305.PictFrameColor);
        this.dateLabel.setFont(new Font("Dialog", 1, 12));
        this.dateLabel.setForeground(this._$4305.DateForeColor);
        this.dateLabel.setBounds(new Rectangle(this._$4364, 2, 160, 18));
        this.dateLabel.addMouseListener(new MouseAdapter() {
            public void mousePressed(final MouseEvent mouseEvent) {
                ImageThread.this._$4305.controlGUI.dzoomCanceled();
                if (ImageThread.this._$4305.controler != null) {
                    ImageThread.this._$4305.controler.toFront();
                }
            }
            
            public void mouseEntered(final MouseEvent mouseEvent) {
                if (ImageThread.this._$4305.controler != null) {
                    ImageThread.this._$4305.controler.toFront();
                }
            }
        });
        this._$4370 = this._$4305.getImage(this._$4305.getCodeBase(), "zoom_plus_highlight.gif");
        this._$4371 = this._$4305.getImage(this._$4305.getCodeBase(), "zoom_plus.gif");
    }
    
    public void mouseClicked(final MouseEvent mouseEvent) {
        if ((mouseEvent.getModifiers() & 0x10) != 0x0) {
            this._$1771(mouseEvent);
            this._$4305.imageThread.pictFrame.repaint();
        }
    }
    
    public void mousePressed(final MouseEvent mouseEvent) {
        if (!this._$4305.controlGUI.dZoomBtnPush && !this.digitalZoom) {
            this.startArea = new MSPoint(mouseEvent.getPoint());
            this.curArea = this.startArea;
            if (this._$4305.controlGUI.controlBtnPush) {
                this._$4305.controlGUI.setSelectedPresetPosition(0);
                this._$4305.logger.print("=====> Start area zoom or direct pan tilt");
                this.areaDefining = true;
            }
        }
        if (this._$4305.controler != null && !this._$4305.msJava) {
            this._$4305.controler.toFront();
        }
    }
    
    public void mouseReleased(final MouseEvent mouseEvent) {
        if (this.areaDefining) {
            this.curArea = new MSPoint(mouseEvent.getPoint());
            if (this.curArea.equals(this.startArea)) {
                this.areaDefining = false;
                this._$4305.logger.print("=====> Stop area zoom");
            }
            else {
                this._$4305.logger.print("=====> Direct pan tilt");
            }
            int n = (int)(this.curArea.getX() - this.startArea.getX());
            int n2;
            if (n < 0) {
                n2 = (int)this.curArea.getX();
                n = -n;
            }
            else {
                n2 = (int)this.startArea.getX();
            }
            int n3 = (int)(this.curArea.getY() - this.startArea.getY());
            int n4;
            if (n3 < 0) {
                n4 = (int)this.curArea.getY();
                n3 = -n3;
            }
            else {
                n4 = (int)this.startArea.getY();
            }
            final double n5 = this._$4382 / this.n_iSizeW;
            final StringBuffer sb = new StringBuffer();
            sb.append("AreaZoom=");
            sb.append(Integer.toString((int)((n2 + (n - this.n_iSizeW) / 2) * n5)) + ",");
            sb.append(Integer.toString((int)((n4 + (n3 - this._$4381) / 2) * n5)) + ",");
            sb.append(Integer.toString((int)(n * n5)) + ",");
            sb.append(Integer.toString((int)(n3 * n5)));
            synchronized (this._$4305.commandSend) {
                this._$4305.commandSend.setCommand("/command/ptzf.cgi", sb.toString());
                this._$4305.commandSend.notify();
            }
            this._$4305.logger.print("Zoom area widt/height : " + Integer.toString(n) + "/" + Integer.toString(n3));
            this._$4305.logger.print("View area widt/height : " + Integer.toString(this.n_iSizeW) + "/" + Integer.toString(this._$4381));
        }
        this.areaDefining = false;
        this._$1567();
        if (this._$4305.controler != null && this._$4305.msJava) {
            this._$4305.controler.toFront();
        }
    }
    
    public void mouseEntered(final MouseEvent mouseEvent) {
        if (this._$4305.curDCur.equals(this._$4305.curD) && !this.digitalZoom && this._$4305.controlGUI.controlBtnPush) {
            this.setCursor(this._$4305.curH);
        }
        else {
            this.setCursor(this._$4305.curDCur);
        }
        if (this._$4305.controler != null) {
            this._$4305.controler.toFront();
        }
    }
    
    public void mouseExited(final MouseEvent mouseEvent) {
    }
    
    public final void start() {
        if (this.runner == null) {
            (this.runner = new Thread(this)).start();
        }
    }
    
    public final void stop() {
        this.runner = null;
        try {
            for (int n = 0; n < 1000 && this._$1176; ++n) {
                Thread.sleep(2L);
            }
        }
        catch (Exception ex) {}
    }
    
    public final void destroy() {
        this.img = null;
        this.bimg.flush();
        this.bimg = null;
        this._$4375 = null;
    }
    
    public void paint(final Graphics graphics) {
        if (this.bimg != null) {
            graphics.drawImage(this.bimg, 0, 0, this);
        }
        if (this.areaDefining) {
            this._$4411(graphics);
        }
    }
    
    public final void update(final Graphics graphics) {
        this.paint(graphics);
    }
    
    public void run() {
        int n = 0;
        final Thread currentThread = Thread.currentThread();
        this._$1176 = true;
        try {
            try {
                Thread.currentThread().setPriority(1);
            }
            catch (SecurityException ex) {
                ex.printStackTrace();
            }
            int n2 = this.upDownControler.getLevel();
            while (this.runner == currentThread) {
                this._$4366 = 0;
                while (this._$4366 < 3) {
                    boolean b = false;
                    this._$4305.controlGUI.controlStart();
                    this._$4305.controlGUI.controlEnd();
                    Label_1458: {
                        if (this.full_flag[this._$4366] && !this.lock_flag[this._$4366]) {
                            this.lock_flag[this._$4366] = true;
                            for (int i = 0; i < 3; ++i) {
                                if (!this.lock_flag[i]) {
                                    this.full_flag[i] = false;
                                }
                            }
                            final int $4417 = this._$4417(this.g_buffer[this._$4366]);
                            if ($4417 == -1) {
                                this.initBuffer();
                                break Label_1458;
                            }
                            try {
                                this.img = Toolkit.getDefaultToolkit().createImage(this.g_buffer[this._$4366], $4417, this.g_buffLength[this._$4366]);
                            }
                            catch (Exception ex2) {
                                try {
                                    this._$4305.s_in.close();
                                }
                                catch (IOException ex3) {}
                                this.dataClear();
                                this._$4305.socketCrt();
                                break Label_1458;
                            }
                            try {
                                this._$4372.addImage(this.img, 0);
                                this._$4372.waitForID(0);
                                this._$4372.removeImage(this.img, 0);
                            }
                            catch (Exception ex4) {}
                            try {
                                this._$4382 = this.img.getWidth(this);
                                this._$4383 = this.img.getHeight(this);
                                switch (this.imgSize) {
                                    case 1: {
                                        this.n_iSizeW = 640;
                                        this._$4381 = 480;
                                        this.vn_iSizeW = 640;
                                        this._$4385 = 480;
                                        this._$4376 = true;
                                        break;
                                    }
                                    case 2: {
                                        this.n_iSizeW = 320;
                                        this._$4381 = 240;
                                        this.vn_iSizeW = 320;
                                        this._$4385 = 240;
                                        this._$4376 = true;
                                        break;
                                    }
                                    case 3: {
                                        this.n_iSizeW = 160;
                                        this._$4381 = 120;
                                        if (this._$4305.vModeDetail.isControlActive()) {
                                            this.vn_iSizeW = 320;
                                        }
                                        else {
                                            this.vn_iSizeW = 160;
                                        }
                                        this._$4385 = 120;
                                        this._$4376 = true;
                                        break;
                                    }
                                    default: {
                                        this.n_iSizeW = this._$4382;
                                        this._$4381 = this._$4383;
                                        if (this._$4382 <= 320) {
                                            if (this._$4305.vModeDetail.isControlActive()) {
                                                this.vn_iSizeW = 320;
                                            }
                                            else {
                                                this.vn_iSizeW = this._$4382;
                                            }
                                        }
                                        else {
                                            this.vn_iSizeW = this._$4382;
                                        }
                                        this._$4385 = this._$4383;
                                        this._$4376 = false;
                                        break;
                                    }
                                }
                                if (this.n_iSizeW != this._$4377 || this._$4381 != this._$4378 || this._$4382 != this._$4379 || this._$4383 != this._$4380 || this._$4305.controlGUI.getControlStatusChanged() || n2 != this.upDownControler.getLevel() || this.zoomControler.getZooming()) {
                                    this._$4305.logger.print("Fetch resize " + (this.n_iSizeW != this._$4377) + "/" + (this._$4381 != this._$4378) + "/" + (this._$4382 != this._$4379) + "/" + (this._$4383 != this._$4380) + "/" + this._$4305.controlGUI.getControlStatusChanged() + "/" + (n2 != this.upDownControler.getLevel()) + "/" + this.zoomControler.getZooming() + "/");
                                    b = true;
                                    this.setBounds(this._$4364 + (int)((this.vn_iSizeW - this.n_iSizeW) / 2.0), this._$4365 + (int)((this._$4385 - this._$4381) / 2.0), this.n_iSizeW, this._$4381);
                                    n2 = this.upDownControler.getLevel();
                                    int n3 = 0;
                                    int n4 = 0;
                                    int n5 = 0;
                                    this._$4305.logger.print("controlBtnPush is " + this._$4305.controlGUI.controlBtnPush);
                                    if (this._$4305.controlGUI.controlBtnPush) {
                                        if (n2 >= 2) {
                                            n4 = (int)(320.0 * this.zoomControler.getRatio()) + 3;
                                            if (n2 >= 3) {
                                                n3 = (int)this.panoramaView.axis.getHeight() + 3;
                                            }
                                        }
                                        n5 = this._$4395 + 3;
                                    }
                                    this._$4431(this._$4364 + (int)((this.vn_iSizeW - 320) / 2.0), this._$4365 + 3 + this._$4385, 320, n3 - 3);
                                    this._$4432(this._$4364 + (int)((this.vn_iSizeW - 320) / 2.0), this._$4365 + 3 + this._$4385 + n3, 320, n4 - 3);
                                    this._$4433(this._$4364 + (int)((this.vn_iSizeW - this.upDownControler.getWidth()) / 2.0), this._$4365 + 3 + this._$4385 + n3 + n4, this.upDownControler.getWidth(), n5 - 3);
                                    this._$4434(this._$4364 - 10, this._$4365 - 20, this.vn_iSizeW + 20, this._$4385 + 3 + this.panoramaView.getHeight() + 3 + this.zoomControler.getHeight() + 3 + n5 + 23);
                                    this._$4305.volume_position_change(this._$4364, this.pictFrame.getBounds().height + 11);
                                    this._$4305.controlGUI.repaintIcon();
                                    this._$4375.clearRect(this._$4364 - 10, this._$4365 - 20, 660, 510);
                                    if (this._$4305.vModeDetail.isDateActive()) {
                                        this.dispDateFlg = true;
                                        this.dateLabel.setVisible(true);
                                    }
                                    this._$4377 = this.n_iSizeW;
                                    this._$4378 = this._$4381;
                                    this._$4379 = this._$4382;
                                    this._$4380 = this._$4383;
                                }
                                if (this.digitalZoom) {
                                    if (this._$1879) {
                                        this._$1879 = false;
                                        this._$4437();
                                    }
                                    this.n_iSizeW *= (int)1.4;
                                    this._$4381 *= (int)1.4;
                                }
                                if (!this._$1567()) {
                                    break Label_1458;
                                }
                            }
                            catch (Exception ex5) {
                                try {
                                    this._$4305.s_in.close();
                                }
                                catch (IOException ex6) {}
                                this.dataClear();
                                this._$4305.socketCrt();
                                this.initBuffer();
                                break Label_1458;
                            }
                            this.initBuffer();
                            try {
                                Thread.sleep(5L);
                            }
                            catch (InterruptedException ex7) {
                                break;
                            }
                            n = 0;
                        }
                        else if (++n > 3) {
                            try {
                                Thread.sleep(20L);
                            }
                            catch (InterruptedException ex8) {
                                break;
                            }
                            n = 0;
                        }
                        if (b) {
                            this._$4305.controlGUI.resetControlStatusChanged();
                            this.zoomControler.resetZooming();
                        }
                    }
                    ++this._$4366;
                }
            }
        }
        catch (Exception ex9) {}
        this._$1176 = false;
    }
    
    private boolean _$1567() {
        try {
            if (!this._$4376 && !this.digitalZoom) {
                this._$4375.drawImage(this.img, 0, 0, null);
            }
            else {
                this._$4375.drawImage(this.img, this._$4388, this._$4389, this.n_iSizeW, this._$4381, null);
            }
            if (this._$4305.vModeDetail.isDateAtMinActive()) {
                this._$4375.setFont(this._$4305.timeFont);
                this._$4375.setColor(this._$4305.timeColor);
                this._$4375.drawString(this._$4396, this.getWidth() - 145, this.getHeight() - 4);
            }
            if (this._$4305.impose) {
                this._$4443(this._$4375);
            }
        }
        catch (Exception ex) {
            System.out.println("JPEG draw Error!!");
            ex.printStackTrace();
            return false;
        }
        this.img.flush();
        this.repaint();
        return true;
    }
    
    private void _$4411(final Graphics graphics) {
        int n = (int)(this.curArea.getX() - this.startArea.getX());
        int n2;
        if (n < 0) {
            n2 = (int)this.curArea.getX();
            n = -n;
        }
        else {
            n2 = (int)this.startArea.getX();
        }
        int n3 = (int)(this.curArea.getY() - this.startArea.getY());
        int n4;
        if (n3 < 0) {
            n4 = (int)this.curArea.getY();
            n3 = -n3;
        }
        else {
            n4 = (int)this.startArea.getY();
        }
        graphics.setColor(Color.red);
        graphics.drawRect(n2, n4, n, n3);
    }
    
    private void _$4443(final Graphics graphics) {
        int n = 0;
        int n2 = 0;
        switch (Integer.parseInt(this._$4305.imposePosition) % 3) {
            case 0: {
                n = this.getWidth() - this._$4305.imposeWidth - 1;
                break;
            }
            case 1: {
                n = 1;
                break;
            }
            case 2: {
                n = (this.getWidth() - this._$4305.imposeWidth) / 2;
                break;
            }
        }
        switch ((Integer.parseInt(this._$4305.imposePosition) - 1) / 3) {
            case 0: {
                n2 = this._$4305.imposeHeight * 2 / 3;
                break;
            }
            case 1: {
                n2 = this.getHeight() / 2 + this._$4305.imposeHeight / 3;
                break;
            }
            case 2: {
                n2 = this.getHeight() - this._$4305.imposeHeight * 1 / 4;
                break;
            }
        }
        graphics.setColor(this._$4305.imposeColor);
        graphics.setFont(this._$4305.imposeFont);
        graphics.drawString(this._$4305.imposeChars, n, n2);
    }
    
    public final void initBuffer() {
        this.full_flag[this._$4366] = false;
        this.lock_flag[this._$4366] = false;
    }
    
    private final int _$4417(final byte[] array) {
        int n = -1;
        for (int i = 0; i < array.length; ++i) {
            if (array[i] == -1 && array[i + 1] == -40) {
                n = i;
                break;
            }
        }
        if (n == -1) {
            return n;
        }
        final String s = new String(array, 0, n);
        final int n2 = s.indexOf("CamTim: ", 0) + 8;
        if (n2 >= 8) {
            try {
                final int index = s.indexOf("\r\n", n2);
                if (index != -1) {
                    this._$4396 = s.substring(n2, index);
                    if (this.dispDateFlg) {
                        this.dateLabel.setText(this._$4396);
                    }
                }
            }
            catch (Exception ex) {}
        }
        final int n3 = s.indexOf("CamPos: ") + 8;
        int n4 = s.indexOf("\r\n", n3);
        if (n3 > 0) {
            if (n4 < 0) {
                n4 = s.length();
            }
            this._$4305.camera.notifyCameraPos(s.substring(n3, n4));
        }
        return n;
    }
    
    public void mouseDragged(final MouseEvent mouseEvent) {
        if (this.areaDefining) {
            this._$4305.logger.print("=====> Trace area zoom");
            this._$4305.controlGUI.dzoomCanceled();
            if (this._$4305.controler != null && !this._$4305.msJava) {
                this._$4305.controler.toFront();
            }
            this.curArea = new MSPoint(mouseEvent.getPoint());
            if (this.curArea.getX() >= this.n_iSizeW) {
                this.curArea.setLocation(this.n_iSizeW - 1, (int)this.curArea.getY());
            }
            else if (this.curArea.getX() < 0.0) {
                this.curArea.setLocation(0, (int)this.curArea.getY());
            }
            if (this.curArea.getY() >= this._$4381) {
                this.curArea.setLocation((int)this.curArea.getX(), this._$4381 - 1);
            }
            else if (this.curArea.getY() < 0.0) {
                this.curArea.setLocation((int)this.curArea.getX(), 0);
            }
            this.repaint();
        }
    }
    
    public void mouseMoved(final MouseEvent mouseEvent) {
        if (this._$4305.controler != null) {
            this._$4305.controler.toFront();
        }
    }
    
    public final void viewSizeChanged(final int imgSize) {
        if (0 <= imgSize && imgSize <= 3) {
            this.imgSize = imgSize;
        }
        else {
            this.imgSize = 0;
        }
        this._$4377 = 0;
        this._$4378 = 0;
        this._$1879 = true;
        this.dZoomOut();
        this._$4305.controlGUI.dZoomButton_DispChange(false);
    }
    
    public final void dZoomIn(final int n, final int n2) {
        if (!this.digitalZoom) {
            this._$4437(n, n2);
            this.digitalZoom = true;
        }
    }
    
    public final void dZoomOut() {
        this._$4388 = 0;
        this._$4389 = 0;
        this.digitalZoom = false;
        this.pictFrame.repaint();
    }
    
    private final void _$4437(final int n, final int n2) {
        this._$4390 = this.n_iSizeW;
        this._$4388 = (int)(this.n_iSizeW / 2 - 1.4 * n);
        this._$4389 = (int)(this._$4381 / 2 - 1.4 * n2);
        if (this._$4388 > 0) {
            this._$4388 = 0;
        }
        if (this._$4389 > 0) {
            this._$4389 = 0;
        }
        if (this._$4388 < -0.4 * this.n_iSizeW) {
            this._$4388 = (int)(-0.4 * this.n_iSizeW);
        }
        if (this._$4389 < -0.4 * this._$4381) {
            this._$4389 = (int)(-0.4 * this._$4381);
        }
    }
    
    private final void _$4437() {
        if (this._$4390 > this.n_iSizeW) {
            final int n = this._$4390 / this.n_iSizeW;
            this._$4388 /= n;
            this._$4389 /= n;
        }
        else {
            final int n2 = this.n_iSizeW / this._$4390;
            this._$4388 *= n2;
            this._$4389 *= n2;
        }
        this._$4390 = this.n_iSizeW;
    }
    
    public final void dataClear() {
        this.g_buffer = new byte[3][300000];
        this.full_flag = new boolean[] { false, false, false };
        this.lock_flag = new boolean[] { false, false, false };
    }
    
    public final void setImagePosition(final String s) {
        if (s.equals("PRM_VIEWMODE_MIN")) {
            this._$4459(0);
            this._$4365 = 0;
            this.pictFrame.setVisible(false);
        }
        else if (s.equals("PRM_VIEWMODE_VIEW")) {
            this._$4459(10);
            this._$4365 = 20;
        }
        else if (s.equals("PRM_VIEWMODE_LIGHT")) {
            this._$4459(185);
            this._$4365 = 20;
        }
        else if (s.equals("PRM_VIEWMODE_FULL")) {
            this._$4459(185);
            this._$4365 = 20;
        }
        this._$4305.logger.print(String.valueOf(this._$4305.vModeDetail.isVSizeActive()) + "/" + String.valueOf(this._$4305.vModeDetail.isDZoomActive()) + "/" + String.valueOf(this._$4305.vModeDetail.isFRateActive()) + "/" + String.valueOf(this._$4305.vModeDetail.isTriggerActive()) + "/" + String.valueOf(this._$4305.vModeDetail.isControlActive()) + "/" + String.valueOf(this._$4305.vModeDetail.isPresetPositionActive()) + "/" + String.valueOf(this._$4305.vModeDetail.isPresetPositionIndipendent()));
        this._$4305.noControlPanel = false;
        if (!this._$4305.vModeDetail.isVSizeActive() && !this._$4305.vModeDetail.isDZoomActive() && !this._$4305.vModeDetail.isFRateActive() && !this._$4305.vModeDetail.isTriggerActive() && !this._$4305.vModeDetail.isControlActive() && !this._$4305.vModeDetail.isPresetPositionActive() && !this._$4305.vModeDetail.isPresetPositionIndipendent()) {
            this._$4305.noControlPanel = true;
            if (this._$4305.vModeDetail.isFrameActive()) {
                this._$4459(10);
                this._$4365 = 20;
            }
            else {
                this._$4459(0);
                this._$4365 = 0;
            }
        }
    }
    
    private final void _$4459(final int $4364) {
        this._$4364 = $4364;
        this.dateLabel.setBounds(new Rectangle(this._$4364, 2, 160, 18));
    }
    
    private final void _$4434(final int n, final int n2, int n3, final int n4) {
        if (this.dateLabel.isVisible()) {
            final Rectangle bounds = this.dateLabel.getBounds();
            if (n3 <= bounds.width + 20) {
                n3 = bounds.width + 20;
            }
        }
        this.pictFrame.setBounds(new Rectangle(n, n2, n3, n4));
    }
    
    private final void _$4431(final int n, final int n2, final int n3, final int n4) {
        int width = this.panoramaView.panoramaData.getWidth(this);
        if (width > n3) {
            width = n3;
        }
        int height = this.panoramaView.panoramaData.getHeight(this);
        if (height > n4) {
            height = n4;
        }
        final int n5 = (n3 - width) / 2 + n;
        final int n6 = (n4 - height) / 2 + n2;
        this._$4305.logger.print("Panorama call(panoramaViewSetBounds) : " + new Rectangle(n, n2, n3, n4));
        this._$4305.logger.print("Panorama axis(panoramaViewSetBounds) : " + this.panoramaView.axis);
        this._$4305.logger.print("Panorama draw(panoramaViewSetBounds) : " + new Rectangle(n5, n6, width, height));
        this.panoramaView.setBounds(new Rectangle(n5, n6, width, height));
    }
    
    private final void _$4432(final int n, final int n2, final int n3, final int n4) {
        this.zoomControler.setBounds(new Rectangle(n, n2, this.zoomControler.getWidth(), n4));
    }
    
    private final void _$4433(final int n, final int n2, final int n3, final int n4) {
        this.upDownControler.setBounds(new Rectangle(n, n2, n3, n4));
        this.upDownControler.setVisible(true);
    }
    
    private void _$1771(final MouseEvent mouseEvent) {
        if (this._$4305.controlGUI.dZoomBtnPush && !this.digitalZoom) {
            this.dZoomIn(mouseEvent.getX(), mouseEvent.getY());
            this.setCursor(this._$4305.curD);
            this._$4305.curDCur = this._$4305.curD;
            this._$4305.controlGUI.setCursor(this._$4305.curD);
            this._$4305.controlGUI.dZoomBtnPush = false;
            this._$4305.controlGUI.dZoomButton_DispChange(true);
        }
    }
}
