import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.awt.Frame;
import java.awt.event.MouseAdapter;
import java.awt.Toolkit;
import java.awt.image.ImageObserver;
import java.awt.event.ActionEvent;
import java.awt.Label;
import java.awt.event.MouseEvent;
import java.awt.Component;
import java.awt.event.MouseListener;
import java.awt.event.ActionListener;
import java.awt.Rectangle;
import java.awt.LayoutManager;
import java.awt.Color;
import java.awt.MenuItem;
import java.awt.PopupMenu;
import java.awt.Panel;
import java.awt.Graphics;
import java.awt.Image;
import java.net.URL;
import java.applet.Applet;

// 
// Decompiled by Procyon v0.5.30
// 

public class WebCam extends Applet
{
    MyConnecter m_myCon;
    String sFileName;
    String sPathName;
    URL urlApplet;
    URL urlTemp;
    int iPort;
    boolean bError;
    boolean boolInitialParam;
    boolean boolInitialApplet;
    boolean boolErrorMsg;
    boolean boolLoadMsg;
    boolean boolControlPanel;
    int iCamList;
    int iCurCam;
    int iFrameW;
    int iFrameH;
    int iNewH;
    int iNewW;
    int iImgW;
    int iImgH;
    int iZoomx;
    int iZoomy;
    int iShiftX;
    int iShiftY;
    int iOffsetX;
    int iOffsetY;
    float fRatio;
    int iOL;
    int iOT;
    int iOR;
    int iOB;
    int iSL;
    int iST;
    int iSR;
    int iSB;
    int iBorder;
    int iBorders;
    int iBgR;
    int iBgG;
    int iBgB;
    Image bufferimage;
    Graphics bufg;
    Image[] image_Zoom;
    Image[] image_Move;
    Image[] image_Cam;
    Image[] image_Ss;
    boolean threadInitial;
    boolean threadStatus;
    boolean isStandalone;
    Panel ControlPanel;
    Snapshot ss;
    MyMouseAdapter m_MouseAdapter;
    MyActionListener m_ActionListener;
    PopupMenu menu_Camera;
    MenuItem[] mi_Cam;
    PopupMenu menu_Room;
    MenuItem mi_In;
    MenuItem mi_Out;
    MenuItem mi_Fit;
    PopupMenu menu_Move;
    MenuItem mi_up;
    MenuItem mi_down;
    MenuItem mi_left;
    MenuItem mi_right;
    ImageButton bt_Zoom;
    ImageButton bt_Move;
    ImageButton bt_Cam;
    ImageButton bt_Ss;
    
    public WebCam() {
        this.bError = false;
        this.boolInitialParam = false;
        this.boolInitialApplet = false;
        this.boolErrorMsg = false;
        this.boolLoadMsg = false;
        this.boolControlPanel = false;
        this.iCamList = 0;
        this.iFrameW = 0;
        this.iFrameH = 0;
        this.iBorder = 4;
        this.iBorders = 8;
        this.image_Zoom = new Image[3];
        this.image_Move = new Image[3];
        this.image_Cam = new Image[3];
        this.image_Ss = new Image[3];
        this.threadInitial = false;
        this.threadStatus = false;
        this.isStandalone = false;
        this.ControlPanel = new Panel();
        this.ss = new Snapshot("Snapshot");
        this.m_MouseAdapter = new MyMouseAdapter();
        this.m_ActionListener = new MyActionListener();
        this.menu_Camera = new PopupMenu();
        this.mi_Cam = new MenuItem[16];
        this.menu_Room = new PopupMenu();
        this.mi_In = new MenuItem();
        this.mi_Out = new MenuItem();
        this.mi_Fit = new MenuItem();
        this.menu_Move = new PopupMenu();
        this.mi_up = new MenuItem();
        this.mi_down = new MenuItem();
        this.mi_left = new MenuItem();
        this.mi_right = new MenuItem();
    }
    
    public void init() {
        try {
            this.dataInit();
            if (this.boolControlPanel) {
                this.uiInit();
            }
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
    }
    
    private void dataInit() throws Exception {
        if (this.boolInitialApplet) {
            return;
        }
        this.iShiftX = 0;
        this.iShiftY = 0;
        this.iOffsetX = 0;
        this.iOffsetY = 0;
        this.iZoomx = 0;
        this.iZoomy = 0;
        this.iCurCam = 0;
        this.iOL = 0;
        this.iOT = 0;
        this.iOR = 320;
        this.iOB = 240;
        this.iNewH = 0;
        this.iNewW = 0;
        this.fRatio = 1.0f;
        if (!this.boolInitialParam) {
            this.paraInit();
        }
        this.bufferimage = this.createImage(this.iFrameW, this.iFrameH);
        (this.bufg = this.bufferimage.getGraphics()).setColor(new Color(this.iBgR, this.iBgG, this.iBgB));
        this.bufg.fillRect(0, 0, this.iFrameW, this.iFrameH);
        this.bufg.setColor(Color.black);
        this.setBackground(new Color(this.iBgR, this.iBgG, this.iBgB));
        final int port = this.getCodeBase().getPort();
        final int n = (port != -1) ? port : 80;
        this.image_Zoom[0] = this.getImage(this.getCodeBase(), "Images/zoom.gif");
        this.image_Move[0] = this.getImage(this.getCodeBase(), "Images/move.gif");
        this.image_Cam[0] = this.getImage(this.getCodeBase(), "Images/cc.gif");
        this.image_Ss[0] = this.getImage(this.getCodeBase(), "Images/ss.gif");
        this.image_Zoom[1] = this.getImage(this.getCodeBase(), "Images/zoom_pre.gif");
        this.image_Move[1] = this.getImage(this.getCodeBase(), "Images/move_pre.gif");
        this.image_Cam[1] = this.getImage(this.getCodeBase(), "Images/cc_pre.gif");
        this.image_Ss[1] = this.getImage(this.getCodeBase(), "Images/ss_pre.gif");
        this.image_Zoom[2] = this.getImage(this.getCodeBase(), "Images/zoom_over.gif");
        this.image_Move[2] = this.getImage(this.getCodeBase(), "Images/move_over.gif");
        this.image_Cam[2] = this.getImage(this.getCodeBase(), "Images/cc_over.gif");
        this.image_Ss[2] = this.getImage(this.getCodeBase(), "Images/ss_over.gif");
        (this.bt_Zoom = new ImageButton(this.image_Zoom[0])).SetPressedImage(this.image_Zoom[1]);
        this.bt_Zoom.SetRolloverImage(this.image_Zoom[2]);
        this.bt_Zoom.SetDefaultString("Zoom");
        (this.bt_Move = new ImageButton(this.image_Move[0])).SetPressedImage(this.image_Move[1]);
        this.bt_Move.SetRolloverImage(this.image_Move[2]);
        this.bt_Move.SetDefaultString("Move");
        (this.bt_Cam = new ImageButton(this.image_Cam[0])).SetPressedImage(this.image_Cam[1]);
        this.bt_Cam.SetRolloverImage(this.image_Cam[2]);
        this.bt_Cam.SetDefaultString("Cam");
        (this.bt_Ss = new ImageButton(this.image_Ss[0])).SetPressedImage(this.image_Ss[1]);
        this.bt_Ss.SetRolloverImage(this.image_Ss[2]);
        this.bt_Ss.SetDefaultString("Shot");
        (this.m_myCon = new MyConnecter(this.getCodeBase().getHost(), n, this.sPathName, this)).startThread();
        this.boolInitialApplet = true;
    }
    
    private void paraInit() {
        if (this.getParameter("width") == null) {}
        this.iFrameW = 330;
        if (this.getParameter("height") == null) {}
        this.iFrameH = 250;
        String parameter = this.getParameter("camList");
        if (parameter == null) {
            parameter = "0";
        }
        this.iCamList = Integer.parseInt(parameter);
        final String parameter2 = this.getParameter("controlPanel");
        try {
            this.boolControlPanel = (Integer.parseInt(parameter2) == 1);
        }
        catch (Exception ex) {
            this.boolControlPanel = true;
        }
        final String parameter3 = this.getParameter("color");
        try {
            final int n = 0;
            final int index = parameter3.indexOf(44);
            this.iBgR = Integer.parseInt(parameter3.substring(n, index));
            final int n2 = index + 1;
            final int index2 = parameter3.indexOf(44, n2);
            this.iBgG = Integer.parseInt(parameter3.substring(n2, index2));
            this.iBgB = Integer.parseInt(parameter3.substring(index2 + 1, parameter3.length()));
        }
        catch (Exception ex2) {
            this.iBgR = 238;
            this.iBgG = 237;
            this.iBgB = 212;
        }
        this.iSL = 5;
        this.iST = 5;
        this.iSR = this.iFrameW - 10;
        this.iSB = this.iFrameH - 10;
        final String parameter4 = this.getParameter("inicamera");
        if (parameter4 == null) {
            return;
        }
        this.iCurCam = Integer.parseInt(parameter4);
        this.urlApplet = this.getCodeBase();
        this.sFileName = String.valueOf(String.valueOf(new StringBuffer("cam").append(this.iCurCam).append(".jpg")));
        try {
            this.urlTemp = new URL(this.urlApplet, this.sFileName);
        }
        catch (Exception ex3) {}
        this.sPathName = this.urlTemp.toString();
        this.boolInitialParam = true;
    }
    
    private void uiInit() throws Exception {
        this.setLayout(null);
        this.ControlPanel.setBackground(new Color(this.iBgR, this.iBgG, this.iBgB));
        this.ControlPanel.setBounds(new Rectangle(330, 5, 42, 250));
        this.ControlPanel.setLayout(null);
        for (int i = 0; i < 16; ++i) {
            if ((1 << i & this.iCamList) != 0x0) {
                (this.mi_Cam[i] = new MenuItem()).setLabel(new Integer(i + 1).toString());
                this.menu_Camera.add(this.mi_Cam[i]);
                this.mi_Cam[i].addActionListener(this.m_ActionListener);
            }
        }
        this.mi_In.setLabel("In");
        this.mi_In.addActionListener(this.m_ActionListener);
        this.mi_Out.setLabel("Out");
        this.mi_Out.addActionListener(this.m_ActionListener);
        this.mi_Fit.setLabel("Fit");
        this.mi_Fit.addActionListener(this.m_ActionListener);
        this.mi_up.setLabel("up");
        this.mi_up.addActionListener(this.m_ActionListener);
        this.mi_down.setLabel("down");
        this.mi_down.addActionListener(this.m_ActionListener);
        this.mi_left.setLabel("left");
        this.mi_left.addActionListener(this.m_ActionListener);
        this.mi_right.setLabel("right");
        this.mi_right.addActionListener(this.m_ActionListener);
        this.setEnabled(true);
        this.bt_Zoom.setBounds(new Rectangle(5, 10, 32, 32));
        this.bt_Zoom.addMouseListener(this.m_MouseAdapter);
        this.bt_Move.setBounds(new Rectangle(5, 50, 32, 32));
        this.bt_Move.addMouseListener(this.m_MouseAdapter);
        this.bt_Ss.setBounds(new Rectangle(5, 90, 32, 32));
        this.bt_Ss.addMouseListener(this.m_MouseAdapter);
        this.bt_Cam.setBounds(new Rectangle(5, 130, 32, 32));
        this.bt_Cam.addMouseListener(this.m_MouseAdapter);
        this.menu_Room.add(this.mi_In);
        this.menu_Room.add(this.mi_Out);
        this.menu_Room.add(this.mi_Fit);
        this.menu_Move.add(this.mi_up);
        this.menu_Move.add(this.mi_down);
        this.menu_Move.add(this.mi_left);
        this.menu_Move.add(this.mi_right);
        this.bt_Zoom.add(this.menu_Room);
        this.bt_Move.add(this.menu_Move);
        this.bt_Cam.add(this.menu_Camera);
        this.ControlPanel.add(this.bt_Zoom, null);
        this.ControlPanel.add(this.bt_Move, null);
        this.ControlPanel.add(this.bt_Cam, null);
        this.ControlPanel.add(this.bt_Ss, null);
        this.add(this.ControlPanel, null);
    }
    
    void myMouseClicked(final MouseEvent mouseEvent) {
        if (mouseEvent.getSource().equals(this.bt_Zoom)) {
            this.menu_Room.show(this.bt_Zoom, mouseEvent.getX(), mouseEvent.getY());
        }
        else if (mouseEvent.getSource().equals(this.bt_Move)) {
            this.menu_Move.show(this.bt_Move, mouseEvent.getX(), mouseEvent.getY());
        }
        else if (mouseEvent.getSource().equals(this.bt_Cam)) {
            this.menu_Camera.show(this.bt_Cam, mouseEvent.getX(), mouseEvent.getY());
        }
        else if (mouseEvent.getSource().equals(this.bt_Ss)) {
            this.snapShot();
        }
    }
    
    void myMouseEntered(final MouseEvent mouseEvent) {
        mouseEvent.getSource().getClass().toString();
        if (mouseEvent.getSource().getClass().isInstance(new Label())) {
            final Label label = (Label)mouseEvent.getSource();
            label.setForeground(Color.yellow);
            label.setText(label.getText());
        }
    }
    
    void myMouseExited(final MouseEvent mouseEvent) {
        if (mouseEvent.getSource().getClass().isInstance(new Label())) {
            final Label label = (Label)mouseEvent.getSource();
            label.setForeground(Color.white);
            label.setText(label.getText());
        }
    }
    
    void myActionPerformed(final ActionEvent actionEvent) {
        final Object source = actionEvent.getSource();
        final String actionCommand = actionEvent.getActionCommand();
        actionEvent.getID();
        actionEvent.paramString();
        if (source.equals(this.mi_down)) {
            this.PressMove(0);
        }
        else if (source.equals(this.mi_left)) {
            this.PressMove(3);
        }
        else if (source.equals(this.mi_right)) {
            this.PressMove(2);
        }
        else if (source.equals(this.mi_up)) {
            this.PressMove(1);
        }
        else if (source.equals(this.mi_Fit)) {
            this.PressZoom(2);
        }
        else if (source.equals(this.mi_In)) {
            this.PressZoom(1);
        }
        else if (source.equals(this.mi_Out)) {
            this.PressZoom(0);
        }
        else {
            this.SwitchCamera(Integer.parseInt(actionCommand) - 1);
        }
    }
    
    public void start() {
        this.showLoading();
        if (this.m_myCon == null && !this.threadInitial) {
            if (!this.boolInitialParam) {
                this.paraInit();
            }
            this.iPort = this.getCodeBase().getPort();
            this.iPort = ((this.iPort != -1) ? this.iPort : 80);
            this.m_myCon = new MyConnecter(this.getCodeBase().getHost(), this.iPort, String.valueOf(this.sPathName) + String.valueOf(this.sFileName), this);
            this.threadInitial = true;
            this.threadStatus = true;
            this.m_myCon.startThread();
        }
        else if (!this.threadStatus) {
            this.m_myCon.startThread();
            this.threadStatus = true;
        }
    }
    
    public void stop() {
        this.threadStatus = false;
        this.m_myCon.stopThread();
        try {
            this.m_myCon.join(100L);
        }
        catch (Exception ex) {}
    }
    
    public void destory() {
        this.m_myCon.stopThread();
        this.m_myCon.destroy();
        this.threadInitial = false;
        this.threadStatus = false;
        super.destroy();
    }
    
    public synchronized void showLoading() {
        final int n = 93;
        final int n2 = 15;
        final int n3 = this.iFrameW - this.iBorders - 2;
        final int n4 = this.iFrameH - this.iBorders - 2;
        final int n5 = (n3 - n) / 2;
        final int n6 = (n4 - n2) / 2;
        this.ClearImageBuffer();
        this.bufg.drawString("Loading Image...", n5, n6);
        this.repaint();
    }
    
    public synchronized void showError() {
        this.bError = true;
        final int n = 131;
        final int n2 = 15;
        final int n3 = this.iFrameW - this.iBorders - 2;
        final int n4 = this.iFrameH - this.iBorders - 2;
        final int n5 = (n3 - n) / 2;
        final int n6 = (n4 - n2) / 2;
        if (this.iNewH < this.iFrameH || this.iNewW < this.iFrameW) {
            this.ClearImageBuffer();
        }
        this.ClearImageBuffer();
        this.bufg.drawString(String.valueOf(String.valueOf(new StringBuffer("Camera #").append(this.iCurCam + 1).append(" is not ready"))), n5, n6);
        this.repaint();
        try {
            Thread.sleep(100L);
        }
        catch (Exception ex) {}
        this.bError = false;
    }
    
    public synchronized void showImage(final Image image) {
        final int n = this.iFrameW - this.iBorders - 2;
        final int n2 = this.iFrameH - this.iBorders - 2;
        this.bufg.drawRect(this.iBorder, this.iBorder, this.iFrameW - this.iBorders, this.iFrameH - this.iBorders);
        if (this.iNewH <= 0 && this.iNewW <= 0) {
            this.CaculateNewImageSize();
            this.CaculateZoomSize();
            this.CaculateDrawPos();
        }
        this.bufg.drawImage(image, this.iSL, this.iST, this.iSR, this.iSB, this.iOL, this.iOT, this.iOR, this.iOB, this);
        this.repaint();
    }
    
    public void update(final Graphics graphics) {
        this.paint(graphics);
    }
    
    public void paint(final Graphics graphics) {
        graphics.drawImage(this.bufferimage, 0, 0, this);
    }
    
    public void CaculateNewImageSize() {
        final Image image = this.m_myCon.getImage();
        if (image == null) {
            return;
        }
        this.iImgW = image.getWidth(this);
        this.iImgH = image.getHeight(this);
        if (this.iImgW < 0) {
            this.iNewW = 0;
            this.iNewH = 0;
            return;
        }
        this.iNewW = (int)(this.iImgW * this.fRatio);
        this.iNewH = (int)(this.iImgH * this.fRatio);
    }
    
    public void CaculateZoomSize() {
        if (this.iImgW < 0) {
            return;
        }
        final int iFrameW = this.iFrameW;
        final int iFrameH = this.iFrameH;
        this.iZoomx = (iFrameW - this.iNewW) / 2;
        this.iZoomy = (iFrameH - this.iNewH) / 2;
        if (this.iZoomx < 0) {
            this.iOffsetX = Math.abs((int)(this.iZoomx / this.fRatio));
            if (Math.abs(this.iShiftX) > Math.abs(this.iOffsetX)) {
                if ((this.iShiftX > 0 && this.iOffsetX > 0) || (this.iShiftX < 0 && this.iOffsetX < 0)) {
                    this.iShiftX = this.iOffsetX;
                }
                else if (this.iShiftX > 0 && this.iOffsetX < 0) {
                    this.iShiftX = Math.abs(this.iOffsetX);
                }
                else if (this.iShiftX < 0 && this.iOffsetX > 0) {
                    this.iShiftX = -1 * this.iOffsetX;
                }
            }
        }
        else {
            this.iShiftX = 0;
            this.iOffsetX = 0;
        }
        if (this.iZoomy < 0) {
            this.iOffsetY = Math.abs((int)(this.iZoomy / this.fRatio));
            if (Math.abs(this.iShiftY) > Math.abs(this.iOffsetY)) {
                if ((this.iShiftY > 0 && this.iOffsetY > 0) || (this.iShiftY < 0 && this.iOffsetY < 0)) {
                    this.iShiftY = this.iOffsetY;
                }
                else if (this.iShiftY > 0 && this.iOffsetY < 0) {
                    this.iShiftY = Math.abs(this.iOffsetY);
                }
                else if (this.iShiftY < 0 && this.iOffsetY > 0) {
                    this.iShiftY = -1 * this.iOffsetY;
                }
            }
        }
        else {
            this.iShiftX = 0;
            this.iOffsetX = 0;
        }
    }
    
    public void CaculateDrawPos() {
        if (this.iImgW < 0) {
            return;
        }
        final int iFrameW = this.iFrameW;
        final int iFrameH = this.iFrameH;
        if (this.iZoomx > 0) {
            this.iSL = this.iZoomx;
            this.iSR = iFrameW - this.iZoomx;
            this.iOL = 0;
            this.iOR = this.iImgW;
        }
        else {
            this.iSL = this.iBorder + 1;
            this.iSR = iFrameW - this.iBorder - 1;
            this.iOL = Math.abs(this.iOffsetX);
            this.iOR = this.iImgW - this.iOL;
            this.iOL += this.iShiftX;
            this.iOR += this.iShiftX;
        }
        if (this.iZoomy > 0) {
            this.iST = this.iZoomy;
            this.iSB = iFrameH - this.iZoomy;
            this.iOT = 0;
            this.iOB = this.iImgH;
        }
        else {
            this.iST = this.iBorder + 1;
            this.iSB = iFrameH - this.iBorder - 1;
            this.iOT = Math.abs(this.iOffsetY);
            this.iOB = this.iImgH - this.iOT;
            this.iOT += this.iShiftY;
            this.iOB += this.iShiftY;
        }
    }
    
    public void PressZoom(final int n) {
        if (this.boolErrorMsg) {
            return;
        }
        if (this.m_myCon == null) {
            return;
        }
        final int n2 = this.iFrameW - 10;
        final int n3 = this.iFrameH - 10;
        if (n == 0) {
            if (this.fRatio <= 1.0) {
                return;
            }
            this.fRatio -= 0.25;
            this.ClearImageBuffer();
        }
        else if (n == 1) {
            if (this.fRatio >= 4.0) {
                return;
            }
            this.fRatio += 0.25;
        }
        else if (n == 2) {
            if (this.fRatio == 1.0) {
                return;
            }
            this.fRatio = 1.0f;
            this.iShiftY = 0;
            this.iShiftX = 0;
            this.iOffsetX = 0;
            this.iOffsetY = 0;
            this.ClearImageBuffer();
        }
        this.CaculateNewImageSize();
        this.CaculateZoomSize();
        this.CaculateDrawPos();
        if (this.iNewH < this.iFrameH || this.iNewW < this.iFrameW) {
            this.ClearImageBuffer();
        }
        this.repaint();
    }
    
    public void SwitchCamera(final int iCurCam) {
        this.boolErrorMsg = false;
        this.boolLoadMsg = false;
        this.iCurCam = iCurCam;
        this.sFileName = String.valueOf(String.valueOf(new StringBuffer("cam").append(iCurCam).append(".jpg")));
        try {
            this.urlTemp = new URL(this.urlApplet, this.sFileName);
        }
        catch (Exception ex) {}
        this.sPathName = this.urlTemp.toString();
        if (this.m_myCon == null) {
            return;
        }
        this.m_myCon.setImage(this.sPathName);
        if (!this.m_myCon.isAlive()) {
            this.m_myCon.startThread();
        }
        this.CaculateNewImageSize();
        this.CaculateZoomSize();
        this.CaculateDrawPos();
        this.ClearImageBuffer();
        this.repaint();
    }
    
    public void PressMove(final int n) {
        if (this.boolErrorMsg) {
            return;
        }
        switch (n) {
            case 0: {
                if (this.iNewH < this.iFrameH) {
                    this.iShiftY = 0;
                    return;
                }
                if (Math.abs(this.iShiftY) > Math.abs(this.iOffsetY)) {
                    return;
                }
                if (Math.abs(this.iShiftY) != this.iOffsetY) {
                    int n2;
                    if (this.iShiftY + 20 > this.iOffsetY) {
                        n2 = this.iOffsetY - this.iShiftY;
                    }
                    else {
                        n2 = 20;
                    }
                    this.iShiftY += n2;
                    break;
                }
                if (this.iShiftY < 0) {
                    int abs;
                    if (this.iShiftY + 20 > this.iOffsetY) {
                        abs = Math.abs(this.iOffsetY);
                    }
                    else {
                        abs = 20;
                    }
                    this.iShiftY += abs;
                    break;
                }
                return;
            }
            case 1: {
                if (this.iNewH < this.iFrameH) {
                    this.iShiftY = 0;
                    return;
                }
                if (Math.abs(this.iShiftY) > Math.abs(this.iOffsetY)) {
                    return;
                }
                if (Math.abs(this.iShiftY) != this.iOffsetY) {
                    int n3;
                    if (Math.abs(this.iShiftY - 20) > this.iOffsetY) {
                        n3 = this.iOffsetY - Math.abs(this.iShiftY);
                    }
                    else {
                        n3 = 20;
                    }
                    this.iShiftY -= n3;
                    break;
                }
                if (this.iShiftY > 0) {
                    int iOffsetY;
                    if (Math.abs(this.iShiftY - 20) > this.iOffsetY) {
                        iOffsetY = this.iOffsetY;
                    }
                    else {
                        iOffsetY = 20;
                    }
                    this.iShiftY -= iOffsetY;
                    break;
                }
                return;
            }
            case 2: {
                if (this.iNewW < this.iFrameW) {
                    this.iShiftX = 0;
                    return;
                }
                if (Math.abs(this.iShiftX) > Math.abs(this.iOffsetX)) {
                    return;
                }
                if (Math.abs(this.iShiftX) != this.iOffsetX) {
                    int n4;
                    if (this.iShiftX + 20 > this.iOffsetX) {
                        n4 = this.iOffsetX - this.iShiftX;
                    }
                    else {
                        n4 = 20;
                    }
                    this.iShiftX += n4;
                    break;
                }
                if (this.iShiftX < 0) {
                    int abs2;
                    if (this.iShiftX + 20 > this.iOffsetX) {
                        abs2 = Math.abs(this.iOffsetX);
                    }
                    else {
                        abs2 = 20;
                    }
                    this.iShiftX += abs2;
                    break;
                }
                return;
            }
            case 3: {
                if (this.iNewW < this.iFrameW) {
                    this.iShiftX = 0;
                    return;
                }
                if (Math.abs(this.iShiftX) > Math.abs(this.iOffsetX)) {
                    return;
                }
                if (Math.abs(this.iShiftX) != this.iOffsetX) {
                    int n5;
                    if (Math.abs(this.iShiftX - 20) > this.iOffsetX) {
                        n5 = this.iOffsetX - Math.abs(this.iShiftX);
                    }
                    else {
                        n5 = 20;
                    }
                    this.iShiftX -= n5;
                    break;
                }
                if (this.iShiftX > 0) {
                    int iOffsetX;
                    if (Math.abs(this.iShiftX - 20) > this.iOffsetX) {
                        iOffsetX = this.iOffsetX;
                    }
                    else {
                        iOffsetX = 20;
                    }
                    this.iShiftX -= iOffsetX;
                    break;
                }
                return;
            }
        }
        this.CaculateDrawPos();
        this.repaint();
    }
    
    public void snapShot() {
        this.ss.setBounds(0, 0, 340, 300);
        this.ss.setResizable(false);
        this.ss.img = Toolkit.getDefaultToolkit().createImage(this.bufferimage.getSource());
        this.ss.setVisible(true);
    }
    
    public void ClearImageBuffer() {
        this.bufg.clearRect(this.iBorder + 1, this.iBorder + 1, this.iFrameW - this.iBorders - 2, this.iFrameH - this.iBorders - 2);
    }
    
    public class MyActionListener implements ActionListener
    {
        public void actionPerformed(final ActionEvent actionEvent) {
            WebCam.this.myActionPerformed(actionEvent);
        }
    }
    
    public class MyMouseAdapter extends MouseAdapter
    {
        public void mouseClicked(final MouseEvent mouseEvent) {
            WebCam.this.myMouseClicked(mouseEvent);
        }
        
        public void mouseEntered(final MouseEvent mouseEvent) {
            WebCam.this.myMouseEntered(mouseEvent);
        }
        
        public void mouseExited(final MouseEvent mouseEvent) {
            WebCam.this.myMouseExited(mouseEvent);
        }
    }
    
    public class Snapshot extends Frame implements WindowListener
    {
        public Image img;
        
        public void windowClosing(final WindowEvent windowEvent) {
            this.setVisible(false);
        }
        
        public void windowOpened(final WindowEvent windowEvent) {
        }
        
        public void windowClosed(final WindowEvent windowEvent) {
        }
        
        public void windowIconified(final WindowEvent windowEvent) {
        }
        
        public void windowDeiconified(final WindowEvent windowEvent) {
        }
        
        public void windowActivated(final WindowEvent windowEvent) {
        }
        
        public void windowDeactivated(final WindowEvent windowEvent) {
        }
        
        public void update(final Graphics graphics) {
            this.paint(graphics);
        }
        
        public void paint(final Graphics graphics) {
            graphics.drawImage(this.img, 5, 30, this);
        }
        
        public Snapshot() {
            this.addWindowListener(this);
        }
        
        public Snapshot(final String s) {
            super(s);
            this.addWindowListener(this);
        }
    }
}
