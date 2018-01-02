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
    private int _$10573;
    private int _$10584;
    public byte[][] g_buffer;
    public boolean[] full_flag;
    public boolean[] lock_flag;
    public int wBuffNum;
    private int _$10595;
    public int[] g_buffLength;
    private Image _$169145;
    private int _$169153;
    private int _$169165;
    private Image _$169177;
    private Image _$169186;
    private MediaTracker _$10615;
    public Image img;
    public Image bimg;
    private Graphics _$10621;
    private boolean _$10623;
    private int _$10629;
    private int _$10637;
    private int _$10645;
    private int _$10653;
    public int n_iSizeW;
    private int _$10661;
    private int _$10669;
    private int _$10677;
    public int vn_iSizeW;
    private int _$169211;
    public int imgSize;
    final int fn_iSizeW = 320;
    final int fn_iSizeH = 240;
    private int _$10685;
    private int _$10693;
    private int _$10701;
    public boolean digitalZoom;
    private boolean _$2867;
    private int _$169238;
    private int _$169251;
    private int _$169264;
    public boolean control;
    public boolean dispDateFlg;
    public Label dateLabel;
    public PictureFrame pictFrame;
    private Viewer _$1008;
    private boolean _$4138;
    volatile Thread runner;
    public PanoramaView panoramaView;
    public ZoomControler zoomControler;
    public UpDownControler upDownControler;
    private int _$169278;
    private String _$169305;
    public boolean areaDefining;
    public MSPoint startArea;
    public MSPoint curArea;
    
    public ImageThread(final Viewer $1008) {
        this._$10573 = 182;
        this._$10584 = 20;
        this.wBuffNum = 0;
        this._$169145 = null;
        this._$169153 = 182;
        this._$169165 = 20;
        this._$169177 = null;
        this._$169186 = null;
        this.img = null;
        this.bimg = null;
        this._$10621 = null;
        this._$10623 = false;
        this._$10629 = 0;
        this._$10637 = 0;
        this._$10645 = 0;
        this._$10653 = 0;
        this.n_iSizeW = 0;
        this._$10661 = 0;
        this._$10669 = 0;
        this._$10677 = 0;
        this.vn_iSizeW = 0;
        this._$169211 = 0;
        this.imgSize = 2;
        this._$10685 = 0;
        this._$10693 = 0;
        this._$10701 = 0;
        this.digitalZoom = false;
        this._$2867 = false;
        this._$169238 = 0;
        this._$169251 = 0;
        this._$169264 = 0;
        this.control = false;
        this.dispDateFlg = false;
        this.dateLabel = new Label();
        this._$1008 = null;
        this._$4138 = true;
        this.panoramaView = null;
        this.zoomControler = null;
        this.upDownControler = null;
        this._$169305 = "";
        this._$10615 = new MediaTracker(this);
        this.g_buffer = new byte[3][300000];
        this.full_flag = new boolean[] { false, false, false };
        this.lock_flag = new boolean[] { false, false, false };
        this.g_buffLength = new int[] { 0, 0, 0 };
        this._$1008 = $1008;
        this.imgSize = this._$1008.ViewSize;
        if (this.panoramaView == null) {
            this.panoramaView = new PanoramaView($1008);
        }
        else {
            this.panoramaView.setVisible(true);
        }
        if (this.zoomControler == null) {
            this.zoomControler = new ZoomControler($1008);
        }
        else {
            this.zoomControler.setVisible(true);
        }
        if (this.upDownControler == null) {
            this.upDownControler = new UpDownControler($1008);
            this._$169278 = this.upDownControler.getHeight();
        }
        else {
            this.upDownControler.setVisible(true);
        }
        this.pictFrame = new PictureFrame($1008);
    }
    
    public final void init() {
        this.runner = null;
        this.g_buffer = new byte[3][300000];
        this.full_flag = new boolean[] { false, false, false };
        this.lock_flag = new boolean[] { false, false, false };
        this.bimg = this.createImage(640, 480);
        (this._$10621 = this.bimg.getGraphics()).setFont(new Font("Dialog", 1, 12));
        this._$10621.setColor(this._$1008.DateForeColor);
        this.pictFrame.setForeground(this._$1008.PictFrameColor);
        this.addMouseListener(this);
        this.addMouseMotionListener(this);
        this.dateLabel.setVisible(false);
        this.dateLabel.setAlignment(0);
        this.dateLabel.setBackground(this._$1008.PictFrameColor);
        this.dateLabel.setFont(new Font("Dialog", 1, 12));
        this.dateLabel.setForeground(this._$1008.DateForeColor);
        this.dateLabel.setBounds(new Rectangle(this._$10573, 2, 160, 18));
        this.dateLabel.addMouseListener(new MouseAdapter() {
            public void mousePressed(final MouseEvent mouseEvent) {
                ImageThread.this._$1008.controlGUI.dzoomCanceled();
                if (ImageThread.this._$1008.controler != null) {
                    ImageThread.this._$1008.controler.toFront();
                }
            }
            
            public void mouseEntered(final MouseEvent mouseEvent) {
                if (ImageThread.this._$1008.controler != null) {
                    ImageThread.this._$1008.controler.toFront();
                }
            }
        });
        this._$169177 = this._$1008.getImage(this._$1008.getCodeBase(), "zoom_plus_highlight.gif");
        this._$169186 = this._$1008.getImage(this._$1008.getCodeBase(), "zoom_plus.gif");
    }
    
    public void mouseClicked(final MouseEvent mouseEvent) {
        if ((mouseEvent.getModifiers() & 0x10) != 0x0) {
            this._$4944(mouseEvent);
            this._$1008.imageThread.pictFrame.repaint();
        }
    }
    
    public void mousePressed(final MouseEvent mouseEvent) {
        if (!this._$1008.controlGUI.dZoomBtnPush && !this.digitalZoom) {
            this.startArea = new MSPoint(mouseEvent.getPoint());
            this.curArea = this.startArea;
            if (this._$1008.controlGUI.controlBtnPush) {
                this._$1008.controlGUI.setSelectedPresetPosition(0);
                this._$1008.logger.print("=====> Start area zoom or direct pan tilt");
                this.areaDefining = true;
            }
        }
        if (this._$1008.controler != null && !this._$1008.msJava) {
            this._$1008.controler.toFront();
        }
    }
    
    public void mouseReleased(final MouseEvent mouseEvent) {
        if (this.areaDefining) {
            this.curArea = new MSPoint(mouseEvent.getPoint());
            if (this.curArea.equals(this.startArea)) {
                this.areaDefining = false;
                this._$1008.logger.print("=====> Stop area zoom");
            }
            else {
                this._$1008.logger.print("=====> Direct pan tilt");
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
            final double n5 = this._$10669 / this.n_iSizeW;
            final StringBuffer sb = new StringBuffer();
            sb.append("AreaZoom=");
            sb.append(String.valueOf(String.valueOf(Integer.toString((int)((n2 + (n - this.n_iSizeW) / 2) * n5)))).concat(","));
            sb.append(String.valueOf(String.valueOf(Integer.toString((int)((n4 + (n3 - this._$10661) / 2) * n5)))).concat(","));
            sb.append(String.valueOf(String.valueOf(Integer.toString((int)(n * n5)))).concat(","));
            sb.append(Integer.toString((int)(n3 * n5)));
            synchronized (this._$1008.commandSend) {
                this._$1008.commandSend.setCommand("/command/ptzf.cgi", sb.toString());
                this._$1008.commandSend.notify();
            }
            // monitorexit(this._$1008.commandSend)
            this._$1008.logger.print(String.valueOf(String.valueOf(new StringBuffer("Zoom area widt/height : ").append(Integer.toString(n)).append("/").append(Integer.toString(n3)))));
            this._$1008.logger.print(String.valueOf(String.valueOf(new StringBuffer("View area widt/height : ").append(Integer.toString(this.n_iSizeW)).append("/").append(Integer.toString(this._$10661)))));
        }
        this.areaDefining = false;
        this._$3229();
        if (this._$1008.controler != null && this._$1008.msJava) {
            this._$1008.controler.toFront();
        }
    }
    
    public void mouseEntered(final MouseEvent mouseEvent) {
        if (this._$1008.curDCur.equals(this._$1008.curD) && !this.digitalZoom && this._$1008.controlGUI.controlBtnPush) {
            this.setCursor(this._$1008.curH);
        }
        else {
            this.setCursor(this._$1008.curDCur);
        }
        if (this._$1008.controler != null) {
            this._$1008.controler.toFront();
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
            for (int i = 0; i < 1000; ++i) {
                if (!this._$4138) {
                    break;
                }
                Thread.sleep(2L);
            }
        }
        catch (Exception ex) {}
    }
    
    public final void destroy() {
        this.img = null;
        this.bimg.flush();
        this.bimg = null;
        this._$10621 = null;
    }
    
    public void paint(final Graphics graphics) {
        if (this.bimg != null) {
            graphics.drawImage(this.bimg, 0, 0, this);
        }
        if (this.areaDefining) {
            this._$169361(graphics);
        }
    }
    
    public final void update(final Graphics graphics) {
        this.paint(graphics);
    }
    
    public void run() {
        int n = 0;
        final Thread currentThread = Thread.currentThread();
        this._$4138 = true;
        try {
            try {
                Thread.currentThread().setPriority(1);
            }
            catch (SecurityException ex) {
                ex.printStackTrace();
            }
            int n2 = this.upDownControler.getLevel();
            while (this.runner == currentThread) {
                this._$10595 = 0;
                while (this._$10595 < 3) {
                    boolean b = false;
                    this._$1008.controlGUI.controlStart();
                    this._$1008.controlGUI.controlEnd();
                    Label_1453: {
                        if (this.full_flag[this._$10595] && !this.lock_flag[this._$10595]) {
                            this.lock_flag[this._$10595] = true;
                            for (int i = 0; i < 3; ++i) {
                                if (!this.lock_flag[i]) {
                                    this.full_flag[i] = false;
                                }
                            }
                            final int $10712 = this._$10712(this.g_buffer[this._$10595]);
                            if ($10712 == -1) {
                                this.initBuffer();
                                break Label_1453;
                            }
                            try {
                                this.img = Toolkit.getDefaultToolkit().createImage(this.g_buffer[this._$10595], $10712, this.g_buffLength[this._$10595]);
                            }
                            catch (Exception ex2) {
                                try {
                                    this._$1008.s_in.close();
                                }
                                catch (IOException ex3) {}
                                this.dataClear();
                                this._$1008.socketCrt();
                                break Label_1453;
                            }
                            try {
                                this._$10615.addImage(this.img, 0);
                                this._$10615.waitForID(0);
                                this._$10615.removeImage(this.img, 0);
                            }
                            catch (Exception ex4) {}
                            Label_1384: {
                                try {
                                    this._$10669 = this.img.getWidth(this);
                                    this._$10677 = this.img.getHeight(this);
                                    switch (this.imgSize) {
                                        case 1: {
                                            this.n_iSizeW = 640;
                                            this._$10661 = 480;
                                            this.vn_iSizeW = 640;
                                            this._$169211 = 480;
                                            this._$10623 = true;
                                            break;
                                        }
                                        case 2: {
                                            this.n_iSizeW = 320;
                                            this._$10661 = 240;
                                            this.vn_iSizeW = 320;
                                            this._$169211 = 240;
                                            this._$10623 = true;
                                            break;
                                        }
                                        case 3: {
                                            this.n_iSizeW = 160;
                                            this._$10661 = 120;
                                            if (this._$1008.vModeDetail.isControlActive()) {
                                                this.vn_iSizeW = 320;
                                            }
                                            else {
                                                this.vn_iSizeW = 160;
                                            }
                                            this._$169211 = 120;
                                            this._$10623 = true;
                                            break;
                                        }
                                        default: {
                                            this.n_iSizeW = this._$10669;
                                            this._$10661 = this._$10677;
                                            if (this._$10669 <= 320) {
                                                if (this._$1008.vModeDetail.isControlActive()) {
                                                    this.vn_iSizeW = 320;
                                                }
                                                else {
                                                    this.vn_iSizeW = this._$10669;
                                                }
                                            }
                                            else {
                                                this.vn_iSizeW = this._$10669;
                                            }
                                            this._$169211 = this._$10677;
                                            this._$10623 = false;
                                            break;
                                        }
                                    }
                                    if (this.n_iSizeW != this._$10629 || this._$10661 != this._$10637 || this._$10669 != this._$10645 || this._$10677 != this._$10653 || this._$1008.controlGUI.getControlStatusChanged() || n2 != this.upDownControler.getLevel() || this.zoomControler.getZooming()) {
                                        this._$1008.logger.print(String.valueOf(String.valueOf(new StringBuffer("Fetch resize ").append(this.n_iSizeW != this._$10629).append("/").append(this._$10661 != this._$10637).append("/").append(this._$10669 != this._$10645).append("/").append(this._$10677 != this._$10653).append("/").append(this._$1008.controlGUI.getControlStatusChanged()).append("/").append(n2 != this.upDownControler.getLevel()).append("/").append(this.zoomControler.getZooming()).append("/"))));
                                        b = true;
                                        this.setBounds(this._$10573 + (int)((this.vn_iSizeW - this.n_iSizeW) / 2.0), this._$10584 + (int)((this._$169211 - this._$10661) / 2.0), this.n_iSizeW, this._$10661);
                                        n2 = this.upDownControler.getLevel();
                                        int n3 = 0;
                                        int n4 = 0;
                                        int n5 = 0;
                                        this._$1008.logger.print("controlBtnPush is ".concat(String.valueOf(String.valueOf(this._$1008.controlGUI.controlBtnPush))));
                                        if (this._$1008.controlGUI.controlBtnPush) {
                                            if (n2 >= 2) {
                                                n4 = (int)(320 * this.zoomControler.getRatio()) + 3;
                                                if (n2 >= 3) {
                                                    n3 = (int)this.panoramaView.axis.getHeight() + 3;
                                                }
                                            }
                                            n5 = this._$169278 + 3;
                                        }
                                        this._$169441(this._$10573 + (int)((this.vn_iSizeW - 320) / 2.0), this._$10584 + 3 + this._$169211, 320, n3 - 3);
                                        this._$169462(this._$10573 + (int)((this.vn_iSizeW - 320) / 2.0), this._$10584 + 3 + this._$169211 + n3, 320, n4 - 3);
                                        this._$169484(this._$10573 + (int)((this.vn_iSizeW - this.upDownControler.getWidth()) / 2.0), this._$10584 + 3 + this._$169211 + n3 + n4, this.upDownControler.getWidth(), n5 - 3);
                                        this._$10741(this._$10573 - 10, this._$10584 - 20, this.vn_iSizeW + 20, this._$169211 + 3 + this.panoramaView.getHeight() + 3 + this.zoomControler.getHeight() + 3 + n5 + 23);
                                        this._$1008.volume_position_change(this._$10573, this.pictFrame.getBounds().height + 11);
                                        this._$1008.controlGUI.repaintIcon();
                                        this._$10621.clearRect(this._$10573 - 10, this._$10584 - 20, 660, 510);
                                        if (this._$1008.vModeDetail.isDateActive()) {
                                            this.dispDateFlg = true;
                                            this.dateLabel.setVisible(true);
                                        }
                                        this._$10629 = this.n_iSizeW;
                                        this._$10637 = this._$10661;
                                        this._$10645 = this._$10669;
                                        this._$10653 = this._$10677;
                                    }
                                    if (this.digitalZoom) {
                                        if (this._$2867) {
                                            this._$2867 = false;
                                            this._$10759();
                                        }
                                        this.n_iSizeW *= (int)1.4;
                                        this._$10661 *= (int)1.4;
                                    }
                                    if (this._$3229()) {
                                        break Label_1384;
                                    }
                                }
                                catch (Exception ex5) {
                                    try {
                                        this._$1008.s_in.close();
                                    }
                                    catch (IOException ex6) {}
                                    this.dataClear();
                                    this._$1008.socketCrt();
                                    this.initBuffer();
                                }
                                break Label_1453;
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
                            this._$1008.controlGUI.resetControlStatusChanged();
                            this.zoomControler.resetZooming();
                        }
                    }
                    ++this._$10595;
                }
            }
        }
        catch (Exception ex9) {}
        this._$4138 = false;
    }
    
    private boolean _$3229() {
        try {
            if (!this._$10623 && !this.digitalZoom) {
                this._$10621.drawImage(this.img, 0, 0, null);
            }
            else {
                this._$10621.drawImage(this.img, this._$10685, this._$10693, this.n_iSizeW, this._$10661, null);
            }
            if (this._$1008.vModeDetail.isDateAtMinActive()) {
                this._$10621.setFont(this._$1008.timeFont);
                this._$10621.setColor(this._$1008.timeColor);
                this._$10621.drawString(this._$169305, this.getWidth() - 145, this.getHeight() - 4);
            }
            if (this._$1008.impose) {
                this._$169512(this._$10621);
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
    
    private void _$169361(final Graphics graphics) {
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
    
    private void _$169512(final Graphics graphics) {
        int n = 0;
        int n2 = 0;
        switch (Integer.parseInt(this._$1008.imposePosition) % 3) {
            case 0: {
                n = this.getWidth() - this._$1008.imposeWidth - 1;
                break;
            }
            case 1: {
                n = 1;
                break;
            }
            case 2: {
                n = (this.getWidth() - this._$1008.imposeWidth) / 2;
                break;
            }
        }
        switch ((Integer.parseInt(this._$1008.imposePosition) - 1) / 3) {
            case 0: {
                n2 = this._$1008.imposeHeight * 2 / 3;
                break;
            }
            case 1: {
                n2 = this.getHeight() / 2 + this._$1008.imposeHeight / 3;
                break;
            }
            case 2: {
                n2 = this.getHeight() - this._$1008.imposeHeight * 1 / 4;
                break;
            }
        }
        graphics.setColor(this._$1008.imposeColor);
        graphics.setFont(this._$1008.imposeFont);
        graphics.drawString(this._$1008.imposeChars, n, n2);
    }
    
    public final void initBuffer() {
        this.full_flag[this._$10595] = false;
        this.lock_flag[this._$10595] = false;
    }
    
    private final int _$10712(final byte[] array) {
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
                    this._$169305 = s.substring(n2, index);
                    if (this.dispDateFlg) {
                        this.dateLabel.setText(this._$169305);
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
            this._$1008.camera.notifyCameraPos(s.substring(n3, n4));
        }
        return n;
    }
    
    public void mouseDragged(final MouseEvent mouseEvent) {
        if (this.areaDefining) {
            this._$1008.logger.print("=====> Trace area zoom");
            this._$1008.controlGUI.dzoomCanceled();
            if (this._$1008.controler != null && !this._$1008.msJava) {
                this._$1008.controler.toFront();
            }
            this.curArea = new MSPoint(mouseEvent.getPoint());
            if (this.curArea.getX() >= this.n_iSizeW) {
                this.curArea.setLocation(this.n_iSizeW - 1, (int)this.curArea.getY());
            }
            else if (this.curArea.getX() < 0) {
                this.curArea.setLocation(0, (int)this.curArea.getY());
            }
            if (this.curArea.getY() >= this._$10661) {
                this.curArea.setLocation((int)this.curArea.getX(), this._$10661 - 1);
            }
            else if (this.curArea.getY() < 0) {
                this.curArea.setLocation((int)this.curArea.getX(), 0);
            }
            this.repaint();
        }
    }
    
    public void mouseMoved(final MouseEvent mouseEvent) {
        if (this._$1008.controler != null) {
            this._$1008.controler.toFront();
        }
    }
    
    public final void viewSizeChanged(final int imgSize) {
        if (imgSize >= 0 && imgSize <= 3) {
            this.imgSize = imgSize;
        }
        else {
            this.imgSize = 0;
        }
        this._$10629 = 0;
        this._$10637 = 0;
        this._$2867 = true;
        this.dZoomOut();
        this._$1008.controlGUI.dZoomButton_DispChange(false);
    }
    
    public final void dZoomIn(final int n, final int n2) {
        if (!this.digitalZoom) {
            this._$10759(n, n2);
            this.digitalZoom = true;
        }
    }
    
    public final void dZoomOut() {
        this._$10685 = 0;
        this._$10693 = 0;
        this.digitalZoom = false;
        this.pictFrame.repaint();
    }
    
    private final void _$10759(final int n, final int n2) {
        this._$10701 = this.n_iSizeW;
        this._$10685 = (int)(this.n_iSizeW / 2 - 1.4 * n);
        this._$10693 = (int)(this._$10661 / 2 - 1.4 * n2);
        if (this._$10685 > 0) {
            this._$10685 = 0;
        }
        if (this._$10693 > 0) {
            this._$10693 = 0;
        }
        if (this._$10685 < -0.4 * this.n_iSizeW) {
            this._$10685 = (int)(-0.4 * this.n_iSizeW);
        }
        if (this._$10693 < -0.4 * this._$10661) {
            this._$10693 = (int)(-0.4 * this._$10661);
        }
    }
    
    private final void _$10759() {
        if (this._$10701 > this.n_iSizeW) {
            final int n = this._$10701 / this.n_iSizeW;
            this._$10685 /= n;
            this._$10693 /= n;
        }
        else {
            final int n2 = this.n_iSizeW / this._$10701;
            this._$10685 *= n2;
            this._$10693 *= n2;
        }
        this._$10701 = this.n_iSizeW;
    }
    
    public final void dataClear() {
        this.g_buffer = new byte[3][300000];
        this.full_flag = new boolean[] { false, false, false };
        this.lock_flag = new boolean[] { false, false, false };
    }
    
    public final void setImagePosition(final String s) {
        if (s.equals("PRM_VIEWMODE_MIN")) {
            this._$10840(0);
            this._$10584 = 0;
            this.pictFrame.setVisible(false);
        }
        else if (s.equals("PRM_VIEWMODE_VIEW")) {
            this._$10840(10);
            this._$10584 = 20;
        }
        else if (s.equals("PRM_VIEWMODE_LIGHT")) {
            this._$10840(185);
            this._$10584 = 20;
        }
        else if (s.equals("PRM_VIEWMODE_FULL")) {
            this._$10840(185);
            this._$10584 = 20;
        }
        this._$1008.logger.print(String.valueOf(String.valueOf(new StringBuffer(String.valueOf(String.valueOf(String.valueOf(this._$1008.vModeDetail.isVSizeActive())))).append("/").append(String.valueOf(this._$1008.vModeDetail.isDZoomActive())).append("/").append(String.valueOf(this._$1008.vModeDetail.isFRateActive())).append("/").append(String.valueOf(this._$1008.vModeDetail.isTriggerActive())).append("/").append(String.valueOf(this._$1008.vModeDetail.isControlActive())).append("/").append(String.valueOf(this._$1008.vModeDetail.isPresetPositionActive())).append("/").append(String.valueOf(this._$1008.vModeDetail.isPresetPositionIndipendent())))));
        this._$1008.noControlPanel = false;
        if (!this._$1008.vModeDetail.isVSizeActive() && !this._$1008.vModeDetail.isDZoomActive() && !this._$1008.vModeDetail.isFRateActive() && !this._$1008.vModeDetail.isTriggerActive() && !this._$1008.vModeDetail.isControlActive() && !this._$1008.vModeDetail.isPresetPositionActive() && !this._$1008.vModeDetail.isPresetPositionIndipendent()) {
            this._$1008.noControlPanel = true;
            if (this._$1008.vModeDetail.isFrameActive()) {
                this._$10840(10);
                this._$10584 = 20;
            }
            else {
                this._$10840(0);
                this._$10584 = 0;
            }
        }
    }
    
    private final void _$10840(final int $10573) {
        this._$10573 = $10573;
        this.dateLabel.setBounds(new Rectangle(this._$10573, 2, 160, 18));
    }
    
    private final void _$10741(final int n, final int n2, int n3, final int n4) {
        if (this.dateLabel.isVisible()) {
            final Rectangle bounds = this.dateLabel.getBounds();
            if (n3 <= bounds.width + 20) {
                n3 = bounds.width + 20;
            }
        }
        this.pictFrame.setBounds(new Rectangle(n, n2, n3, n4));
    }
    
    private final void _$169441(final int n, final int n2, final int n3, final int n4) {
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
        this._$1008.logger.print("Panorama call(panoramaViewSetBounds) : ".concat(String.valueOf(String.valueOf(new Rectangle(n, n2, n3, n4)))));
        this._$1008.logger.print("Panorama axis(panoramaViewSetBounds) : ".concat(String.valueOf(String.valueOf(this.panoramaView.axis))));
        this._$1008.logger.print("Panorama draw(panoramaViewSetBounds) : ".concat(String.valueOf(String.valueOf(new Rectangle(n5, n6, width, height)))));
        this.panoramaView.setBounds(new Rectangle(n5, n6, width, height));
    }
    
    private final void _$169462(final int n, final int n2, final int n3, final int n4) {
        this.zoomControler.setBounds(new Rectangle(n, n2, this.zoomControler.getWidth(), n4));
    }
    
    private final void _$169484(final int n, final int n2, final int n3, final int n4) {
        this.upDownControler.setBounds(new Rectangle(n, n2, n3, n4));
        this.upDownControler.setVisible(true);
    }
    
    private void _$4944(final MouseEvent mouseEvent) {
        if (this._$1008.controlGUI.dZoomBtnPush && !this.digitalZoom) {
            this.dZoomIn(mouseEvent.getX(), mouseEvent.getY());
            this.setCursor(this._$1008.curD);
            this._$1008.curDCur = this._$1008.curD;
            this._$1008.controlGUI.setCursor(this._$1008.curD);
            this._$1008.controlGUI.dZoomBtnPush = false;
            this._$1008.controlGUI.dZoomButton_DispChange(true);
        }
    }
}
