// 
// Decompiled by Procyon v0.5.30
// 

package appletviewer;

import java.util.Date;
import java.awt.image.ImageObserver;
import java.awt.MediaTracker;
import java.awt.Toolkit;
import java.io.IOException;
import java.io.PrintStream;
import java.awt.Component;
import java.awt.event.KeyListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.WindowListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowAdapter;
import java.awt.Dimension;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseMotionAdapter;
import java.awt.event.MouseListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseAdapter;
import java.awt.Rectangle;
import java.awt.Font;
import java.awt.Color;
import java.awt.LayoutManager;
import java.awt.TextField;
import java.awt.Cursor;
import java.awt.Button;
import java.awt.Frame;
import java.awt.Label;
import java.awt.Graphics;
import java.awt.Image;
import java.io.DataInputStream;
import java.net.URLConnection;
import java.net.URL;
import java.applet.Applet;

public class viewer extends Applet implements Runnable
{
    boolean isStandalone;
    private int nextDataChk;
    private int restartSize;
    private String host;
    private String hostSR;
    private String user;
    private int priority;
    private int rcv_size;
    private int port;
    private URL eviURL;
    private URLConnection connection;
    private DataInputStream s_in;
    private int r_len;
    private byte[] buffer;
    private boolean readCtl;
    private int saveLen;
    private boolean destroyFlg;
    private CommandSend commandSend;
    private byte[] g_buffer;
    private int g_idx;
    private byte[] z_buffer;
    private int z_idx;
    private boolean d_cut;
    private byte[] n_buffer;
    private Thread kicker;
    private Image img;
    private Image bimg;
    private Graphics bg;
    private boolean imgChg;
    private int o_iSizeW;
    private int o_iSizeH;
    private int o_dSizeW;
    private int o_dSizeH;
    private int n_iSizeW;
    private int n_iSizeH;
    private int n_dSizeW;
    private int n_dSizeH;
    private int s_time;
    private int imgSize;
    private String htmlStr;
    private String htmlStr1;
    private String htmlStr2;
    private byte[] htmlbyte;
    private byte[] htmlbyte1;
    private Label labelT;
    private Frame pFrame;
    private Label label1;
    private Button button1;
    private boolean popUpTxt;
    private String rate;
    private boolean rateChgflg;
    private long timeR1;
    private int frameCnt;
    private int frameVal1;
    private int frameVal10;
    private int rateVal;
    private int frameVal;
    private boolean dptzFlg;
    private long endWTime;
    private long endCTime;
    private ZoomBar zoomBar;
    private Image w_image;
    private Image b_image;
    private Image t_image;
    private Image e_b_image;
    private Image e_t_image;
    private Image z_pointer;
    private String zMode;
    private int zBarXSize;
    private int[][] zPosVal;
    private Cursor curH;
    private Cursor curS;
    private String uCode;
    private int uErr;
    private Frame NPPanel;
    private Button buttonOK;
    private Button buttonCL;
    private Label labelMSG;
    private Label labelSITE;
    private Label labelREALM;
    private Label labelUN;
    private Label labelPW;
    private Label labelSITEV;
    private Label labelREALMV;
    private TextField textFieldUN;
    private TextField textFieldPW;
    private char[] NamePassT;
    private char[] PasscodeT;
    private char[] CodeNameT;
    private int NamePassL;
    private int PassCodeL;
    private char[] gBase64Table;
    
    public String getParameter(final String key, final String def) {
        return this.isStandalone ? System.getProperty(key, def) : ((this.getParameter(key) != null) ? this.getParameter(key) : def);
    }
    
    public viewer() {
        this.isStandalone = false;
        this.nextDataChk = 15;
        this.restartSize = 256000000;
        this.readCtl = true;
        this.saveLen = 0;
        this.destroyFlg = false;
        this.commandSend = null;
        this.g_idx = 0;
        this.z_idx = 0;
        this.d_cut = false;
        this.kicker = null;
        this.bg = null;
        this.imgChg = false;
        this.o_iSizeW = 0;
        this.o_iSizeH = 0;
        this.o_dSizeW = 0;
        this.o_dSizeH = 0;
        this.n_iSizeW = 0;
        this.n_iSizeH = 0;
        this.n_dSizeW = 0;
        this.n_dSizeH = 0;
        this.s_time = 0;
        this.imgSize = 0;
        this.htmlStr = "";
        this.htmlStr1 = "";
        this.htmlStr2 = "";
        this.labelT = new Label();
        this.label1 = new Label();
        this.button1 = new Button();
        this.popUpTxt = false;
        this.rate = "";
        this.rateChgflg = false;
        this.frameCnt = 1;
        this.frameVal1 = 0;
        this.frameVal10 = 99;
        this.rateVal = 0;
        this.frameVal = 0;
        this.dptzFlg = false;
        this.zMode = "";
        this.zBarXSize = 320;
        this.zPosVal = new int[][] { { 38, 0 }, { 42, 1 }, { 43, 2819 }, { 48, 5638 }, { 53, 5798 }, { 57, 8529 }, { 62, 9432 }, { 67, 10336 }, { 72, 10890 }, { 76, 11445 }, { 81, 11914 }, { 86, 12384 }, { 91, 12697 }, { 95, 13011 }, { 100, 13324 }, { 105, 13637 }, { 110, 13878 }, { 114, 14119 }, { 119, 14312 }, { 124, 14505 }, { 129, 14709 }, { 133, 14914 }, { 138, 15046 }, { 143, 15179 }, { 148, 15336 }, { 152, 15493 }, { 157, 15613 }, { 162, 15733 }, { 167, 15841 }, { 171, 15950 }, { 176, 16034 }, { 181, 16119 }, { 186, 16208 }, { 190, 16297 }, { 195, 16340 }, { 182, 16383 }, { 234, 16384 }, { 235, 16385 }, { 239, 24576 }, { 245, 27264 }, { 251, 28672 }, { 257, 29504 }, { 261, 30016 }, { 267, 30400 }, { 273, 30720 }, { 280, 30976 }, { 285, 31104 }, { 290, 31296 }, { 291, 31423 }, { 320, 31424 }, { 998, 99999 }, { 999, 99999 } };
        this.uCode = "";
        this.uErr = 0;
        this.buttonOK = new Button();
        this.buttonCL = new Button();
        this.labelMSG = new Label();
        this.labelSITE = new Label();
        this.labelREALM = new Label();
        this.labelUN = new Label();
        this.labelPW = new Label();
        this.labelSITEV = new Label();
        this.labelREALMV = new Label();
        this.textFieldUN = new TextField();
        this.textFieldPW = new TextField();
        this.gBase64Table = new char[] { 'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z', 'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z', '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', '+', '/' };
    }
    
    public void init() {
        try {
            this.user = this.getParameter("USER", "");
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        try {
            this.priority = Integer.parseInt(this.getParameter("PRIORITY", "0"));
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        try {
            this.rcv_size = Integer.parseInt(this.getParameter("RCVSIZE", "256"));
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        try {
            this.jbInit();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    private void jbInit() throws Exception {
        String rtnStr = "";
        this.host = this.getCodeBase().getHost();
        this.port = this.getCodeBase().getPort();
        if (this.port != 80 && this.port != -1) {
            this.host = String.valueOf(String.valueOf(new StringBuffer(String.valueOf(String.valueOf(this.host))).append(":").append(this.port)));
        }
        if (this.host.equals("")) {
            this.host = "43.4.152.58";
        }
        this.hostSR = this.host;
        System.out.println("HOST: ".concat(String.valueOf(String.valueOf(this.host))));
        rtnStr = this.commandSR("/command/inquiry.cgi", "inq=sysinfo", true);
        final int startIdx = rtnStr.indexOf("DefFrameRate=") + 13;
        this.rate = "0";
        if (startIdx > 12) {
            final int endIdx = rtnStr.indexOf("&", startIdx + 1);
            if (endIdx > 0) {
                this.rate = rtnStr.substring(startIdx, endIdx);
            }
        }
        if (this.user.equals("ADMIN")) {
            this.host = String.valueOf(String.valueOf(this.host)).concat("/administratoronly");
        }
        this.w_image = this.getImage(this.getCodeBase(), "wide.jpg");
        this.b_image = this.getImage(this.getCodeBase(), "bar.jpg");
        this.t_image = this.getImage(this.getCodeBase(), "tele.jpg");
        this.e_b_image = this.getImage(this.getCodeBase(), "bar_e.jpg");
        this.e_t_image = this.getImage(this.getCodeBase(), "tele_e.jpg");
        this.z_pointer = this.getImage(this.getCodeBase(), "indicator.jpg");
        this.setLayout(null);
        this.labelT.setAlignment(0);
        this.labelT.setBackground(Color.black);
        this.labelT.setFont(new Font("Dialog", 1, 13));
        this.labelT.setForeground(Color.white);
        this.labelT.setBounds(new Rectangle(0, 0, 200, 20));
        this.labelT.setText("");
        this.label1.setAlignment(1);
        this.label1.setFont(new Font("Monospaced", 0, 13));
        this.label1.setBounds(new Rectangle(11, 38, 476, 22));
        (this.zoomBar = new ZoomBar(this.w_image, this.b_image, this.t_image, this.e_b_image, this.e_t_image, this.z_pointer)).setBackground(Color.black);
        this.zoomBar.setCursor(null);
        this.zoomBar.setBounds(new Rectangle(0, 505, 320, 28));
        this.zoomBar.addMouseListener(new MouseAdapter() {
            public void mouseExited(final MouseEvent e) {
                viewer.this.zoomBar_mouseExited(e);
            }
        });
        this.zoomBar.addMouseListener(new MouseAdapter() {
            public void mouseEntered(final MouseEvent e) {
                viewer.this.zoomBar_mouseEntered(e);
            }
        });
        this.zoomBar.addMouseMotionListener(new MouseMotionAdapter() {
            public void mouseExited(final MouseEvent e) {
                viewer.this.zoomBar_mouseExited(e);
            }
        });
        this.zoomBar.addMouseListener(new MouseAdapter() {
            public void mouseClicked(final MouseEvent e) {
                viewer.this.zoomBar_mouseClicked(e);
            }
        });
        this.zoomBar.setVisible(false);
        this.setBackground(Color.black);
        this.bimg = this.createImage(736, 572);
        (this.bg = this.bimg.getGraphics()).setColor(Color.black);
        this.htmlbyte1 = new byte[this.rcv_size - this.nextDataChk];
        this.g_buffer = new byte[400000];
        this.z_buffer = new byte[this.rcv_size + this.nextDataChk];
        this.n_buffer = new byte[this.nextDataChk];
        (this.pFrame = new Frame("SNC-Z20")).setBackground(Color.lightGray);
        this.pFrame.setSize(new Dimension(498, 129));
        this.pFrame.setResizable(false);
        this.pFrame.setLayout(null);
        this.button1.setLabel("OK");
        this.button1.setBounds(new Rectangle(197, 74, 105, 25));
        this.button1.addMouseListener(new MouseAdapter() {
            public void mouseClicked(final MouseEvent e) {
                viewer.this.button1_mouseClicked(e);
            }
        });
        (this.NPPanel = new Frame("Enter Network Password")).setLayout(null);
        this.NPPanel.setBackground(Color.lightGray);
        this.NPPanel.setSize(new Dimension(450, 250));
        this.NPPanel.setResizable(false);
        this.NPPanel.addWindowListener(new WindowAdapter() {
            public void windowClosing(final WindowEvent e) {
                viewer.this.buttonCL_Clicked();
            }
        });
        this.buttonOK.setLabel("OK");
        this.buttonOK.setBounds(new Rectangle(260, 200, 75, 25));
        this.buttonOK.addKeyListener(new KeyAdapter() {
            public void keyPressed(final KeyEvent e) {
                viewer.this.buttonOK_Clicked();
            }
        });
        this.buttonOK.addMouseListener(new MouseAdapter() {
            public void mouseClicked(final MouseEvent e) {
                viewer.this.buttonOK_Clicked();
            }
        });
        this.buttonCL.setLabel("Cancel");
        this.buttonCL.setBounds(new Rectangle(345, 200, 75, 25));
        this.buttonCL.addKeyListener(new KeyAdapter() {
            public void keyPressed(final KeyEvent e) {
                viewer.this.buttonCL_Clicked();
            }
        });
        this.buttonCL.addMouseListener(new MouseAdapter() {
            public void mouseClicked(final MouseEvent e) {
                viewer.this.buttonCL_Clicked();
            }
        });
        this.labelMSG.setText("Please type your user name and password.");
        this.labelMSG.setBounds(new Rectangle(30, 35, 250, 12));
        this.labelSITE.setText("Site:");
        this.labelSITE.setBounds(new Rectangle(30, 70, 90, 12));
        this.labelREALM.setText("Realm:");
        this.labelREALM.setBounds(new Rectangle(30, 100, 90, 12));
        this.labelUN.setText("User Name:");
        this.labelUN.setBounds(new Rectangle(30, 140, 90, 12));
        this.labelPW.setText("Password:");
        this.labelPW.setBounds(new Rectangle(30, 170, 90, 12));
        this.labelSITEV.setBounds(new Rectangle(160, 65, 260, 20));
        this.labelREALMV.setBounds(new Rectangle(160, 95, 260, 20));
        this.textFieldUN.setBounds(new Rectangle(160, 135, 260, 20));
        this.textFieldPW.setEchoChar('*');
        this.textFieldPW.setBounds(new Rectangle(160, 165, 260, 20));
        this.add(this.labelT, null);
        this.add(this.zoomBar, null);
        this.pFrame.add(this.label1, null);
        this.pFrame.add(this.button1, null);
        this.pFrame.setLocation(263, 319);
        this.NPPanel.add(this.labelMSG, null);
        this.NPPanel.add(this.labelSITE, null);
        this.NPPanel.add(this.labelSITEV, null);
        this.NPPanel.add(this.labelREALM, null);
        this.NPPanel.add(this.labelUN, null);
        this.NPPanel.add(this.textFieldUN, null);
        this.NPPanel.add(this.textFieldPW, null);
        this.NPPanel.add(this.buttonOK, null);
        this.NPPanel.add(this.buttonCL, null);
        this.NPPanel.add(this.labelPW, null);
        this.NPPanel.add(this.labelREALMV, null);
        this.NPPanel.setLocation(263, 319);
        this.curH = new Cursor(12);
        this.curS = this.getCursor();
        try {
            this.socketCrt();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    private String commandSR(final String sCgi, final String sStr, final boolean ans) {
        final byte[] inData = new byte[2048];
        int inSize = 0;
        if (sStr.length() != 0) {
            try {
                final URL eviURL = new URL(String.valueOf(String.valueOf(new StringBuffer("http://").append(this.hostSR).append(sCgi))));
                final URLConnection connection = eviURL.openConnection();
                connection.setDoOutput(true);
                final PrintStream s_out = new PrintStream(connection.getOutputStream());
                s_out.print(sStr);
                s_out.close();
                final DataInputStream s_in = new DataInputStream(connection.getInputStream());
                if (ans) {
                    inSize = s_in.read(inData);
                }
                s_in.close();
                System.out.println("COMMAND: ".concat(String.valueOf(String.valueOf(sStr))));
            }
            catch (IOException Ex) {
                System.out.println("Inq Error!!");
            }
        }
        if (ans) {
            return new String(inData, 0, inSize);
        }
        return "";
    }
    
    public void start() {
        if (this.kicker == null && !this.destroyFlg) {
            (this.kicker = new Thread(this)).start();
            (this.commandSend = new CommandSend(this.hostSR)).start();
            this.commandSend.setAuthorize(this.uCode);
        }
    }
    
    public void stop() {
        if (this.kicker != null) {
            this.kicker = null;
            this.commandSend = null;
        }
    }
    
    public void destroy() {
        this.destroyFlg = true;
        if (this.kicker != null) {
            this.kicker = null;
            this.commandSend = null;
        }
        try {
            this.s_in.close();
        }
        catch (Exception ex) {}
    }
    
    public String getAppletInfo() {
        return "Applet Information";
    }
    
    public String[][] getParameterInfo() {
        final String[][] pinfo = { { "USER", "String", "" }, { "PRIORITY", "String", "0" }, { "RCVSIZE", "String", "256" } };
        return pinfo;
    }
    
    public void run() {
        boolean ans = false;
        final int errCnt = 0;
        Thread.currentThread().setPriority(3 + this.priority);
        while (this.kicker != null) {
            if (this.readCtl) {
                try {
                    this.buffer = new byte[this.rcv_size];
                    this.r_len = this.s_in.read(this.buffer, this.nextDataChk, this.rcv_size - this.nextDataChk);
                    this.rateVal += this.r_len;
                    this.saveLen += this.r_len;
                }
                catch (Exception e) {
                    System.out.println("read() Error!!");
                    continue;
                }
                this.htmltrm(this.buffer, this.r_len);
                this.popupdsp();
                ans = this.datatrm(this.r_len + this.nextDataChk, this.buffer);
            }
            if (this.endCTime != 0 && this.endWTime != 0) {
                this.ctrlTimeChk();
            }
            if (!this.dptzFlg) {
                this.zoomBar.setVisible(false);
            }
            else {
                if (this.o_iSizeH != 0) {
                    this.zoomBar.setBounds(new Rectangle(0, this.o_iSizeH + 25, this.zBarXSize, 28));
                }
                this.zoomBar.setVisible(true);
            }
            if (!ans) {
                try {
                    this.img = Toolkit.getDefaultToolkit().createImage(this.g_buffer);
                }
                catch (Exception ea) {
                    System.out.println("JPEG Image Error!! (createImage)");
                    try {
                        this.s_in.close();
                    }
                    catch (IOException ex) {}
                    this.dataClear();
                    this.socketCrt();
                    continue;
                }
                try {
                    final MediaTracker mt = new MediaTracker(this);
                    mt.addImage(this.img, 0);
                    mt.waitForAll();
                    this.n_dSizeW = this.img.getWidth(null);
                    this.n_dSizeH = this.img.getHeight(null);
                    switch (this.imgSize) {
                        case 1: {
                            this.n_iSizeW = 640;
                            this.n_iSizeH = 480;
                            this.imgChg = true;
                            break;
                        }
                        case 2: {
                            this.n_iSizeW = 320;
                            this.n_iSizeH = 240;
                            this.imgChg = true;
                            break;
                        }
                        case 3: {
                            this.n_iSizeW = 160;
                            this.n_iSizeH = 120;
                            this.imgChg = true;
                            break;
                        }
                        default: {
                            this.n_iSizeW = this.img.getWidth(null);
                            this.n_iSizeH = this.img.getHeight(null);
                            this.imgChg = false;
                            break;
                        }
                    }
                }
                catch (Exception e) {
                    System.out.println("JPEG Image Error!! (CheckImage)");
                    try {
                        this.s_in.close();
                    }
                    catch (IOException ex2) {}
                    this.dataClear();
                    this.socketCrt();
                    continue;
                }
                if (this.n_iSizeW != this.o_iSizeW || this.n_iSizeH != this.o_iSizeH || this.n_dSizeW != this.o_dSizeW || this.n_dSizeH != this.o_dSizeH) {
                    this.bg.clearRect(0, 0, 736, 572);
                    this.o_iSizeW = this.n_iSizeW;
                    this.o_iSizeH = this.n_iSizeH;
                    this.o_dSizeW = this.n_dSizeW;
                    this.o_dSizeH = this.n_dSizeH;
                    if (this.n_iSizeW >= 400 || this.n_iSizeH >= 300) {
                        this.s_time = 5;
                    }
                    else {
                        this.s_time = 0;
                    }
                    this.zoomBar.setBounds(new Rectangle(0, this.n_iSizeH + 25, this.zBarXSize, 28));
                    this.zoomBar.repaint();
                }
                try {
                    if (!this.imgChg) {
                        this.bg.drawImage(this.img, 0, 0, null);
                    }
                    else {
                        this.bg.drawImage(this.img, 0, 0, this.n_iSizeW, this.n_iSizeH, null);
                    }
                }
                catch (Exception ed) {
                    System.out.println("JPEG draw Error!!");
                    try {
                        this.s_in.close();
                    }
                    catch (IOException ex3) {}
                    this.dataClear();
                    this.socketCrt();
                    continue;
                }
                this.img.flush();
                this.repaint();
                ++this.frameVal;
                ++this.frameVal1;
                this.readCtl = this.rdydata();
                this.reloadChk();
                this.rateChk();
                if (this.kicker != null) {
                    this.rateDsp();
                }
                if (this.s_time > 0) {
                    try {
                        Thread.sleep(this.s_time);
                    }
                    catch (InterruptedException ex4) {}
                }
                Thread.yield();
            }
        }
        this.kicker = null;
    }
    
    private void socketCrt() {
        boolean firstFlg = true;
        while (true) {
            if (this.rate.equals("")) {
                final String evi_adr = String.valueOf(String.valueOf(new StringBuffer("http://").append(this.host).append("/image")));
                break Label_0083;
            }
            final String evi_adr = String.valueOf(String.valueOf(new StringBuffer("http://").append(this.host).append("/image?speed=").append(this.rate)));
            try {
                while (true) {
                    if (firstFlg || !this.uCode.equals("")) {
                        this.eviURL = new URL(evi_adr);
                        (this.connection = this.eviURL.openConnection()).setAllowUserInteraction(true);
                        this.connection.setRequestProperty("Connection", "Close");
                        this.connection.setRequestProperty("Pragma", "no-cache");
                        if (!this.uCode.equals("")) {
                            this.connection.setRequestProperty("Authorization", "Basic ".concat(String.valueOf(String.valueOf(this.uCode))));
                        }
                        this.connection.setUseCaches(false);
                        if (this.dataHeaderChk(this.connection)) {
                            this.s_in = new DataInputStream(this.connection.getInputStream());
                            break;
                        }
                        this.uCode = "";
                        if (this.uErr > 3) {
                            continue;
                        }
                        this.showAuthorizeFreame(this.connection);
                        firstFlg = false;
                    }
                    else {
                        if (this.uErr > 3) {
                            this.label1.setText("Protected Object. This object is protected.");
                            this.popUpTxt = true;
                            this.popupdsp();
                            this.destroy();
                            break;
                        }
                        try {
                            Thread.sleep(500L);
                        }
                        catch (InterruptedException ex) {}
                    }
                }
            }
            catch (IOException Ex) {
                System.out.println("Socket Error!!");
                continue;
            }
            break;
        }
    }
    
    private boolean dataHeaderChk(final URLConnection connect) {
        final String header = connect.getHeaderField(0);
        if (header.indexOf("401") > 0) {
            ++this.uErr;
            return false;
        }
        return true;
    }
    
    private void showAuthorizeFreame(final URLConnection connect) {
        this.labelSITEV.setText(this.host);
        final String header = connect.getHeaderField("WWW-Authenticate");
        if (!header.equals(null)) {
            final int sidx = header.indexOf("realm=");
            this.labelREALMV.setText(header.substring(sidx + 7, header.length() - 1));
        }
        this.textFieldUN.setText("");
        this.textFieldPW.setText("");
        this.NPPanel.show();
        this.textFieldUN.requestFocus();
    }
    
    private void htmltrm(final byte[] p_buf, final int h_len) {
        String camPos = "";
        this.htmlbyte = new byte[(this.rcv_size - this.nextDataChk) * 2];
        int lp_cnt1;
        for (lp_cnt1 = 0; lp_cnt1 < this.rcv_size - this.nextDataChk; ++lp_cnt1) {
            this.htmlbyte[lp_cnt1] = this.htmlbyte1[lp_cnt1];
        }
        this.htmlbyte1 = new byte[this.rcv_size - this.nextDataChk];
        for (int lp_cnt2 = 0; lp_cnt2 < this.rcv_size - this.nextDataChk; ++lp_cnt2) {
            this.htmlbyte[lp_cnt1] = p_buf[lp_cnt2 + this.nextDataChk];
            this.htmlbyte1[lp_cnt2] = p_buf[lp_cnt2 + this.nextDataChk];
            ++lp_cnt1;
        }
        this.htmlStr = new String(this.htmlbyte);
        int sIdx = this.htmlStr.indexOf("CamTim: ", 0) + 8;
        if (sIdx >= 8) {
            try {
                final int eIdx = this.htmlStr.indexOf("\r\n", sIdx);
                if (eIdx != -1) {
                    this.labelT.setText(this.htmlStr.substring(sIdx, eIdx));
                }
            }
            catch (Exception ex) {}
        }
        sIdx = this.htmlStr.indexOf("PopUp: ", 0) + 7;
        if (sIdx >= 7) {
            try {
                final int eIdx = this.htmlStr.indexOf("\r\n", sIdx);
                if (eIdx != -1) {
                    this.label1.setText(this.htmlStr.substring(sIdx, eIdx));
                    this.popUpTxt = true;
                }
            }
            catch (Exception et) {
                System.out.println("PopUpSch Error!!");
            }
        }
        int zPos = -1;
        sIdx = this.htmlStr.indexOf("CamPos: ", 0) + 8;
        if (sIdx >= 8) {
            try {
                final int eIdx = this.htmlStr.indexOf("\r\n", sIdx);
                if (eIdx != -1) {
                    camPos = this.htmlStr.substring(sIdx, eIdx);
                    if (camPos.charAt(2) == '1') {
                        zPos = Integer.parseInt(camPos.substring(11, 15), 16);
                    }
                }
            }
            catch (Exception et) {
                System.out.println("CamPosSch Error!!");
            }
        }
        if (zPos > -1) {
            for (lp_cnt1 = 0; this.zPosVal[lp_cnt1][0] < 999; ++lp_cnt1) {
                if (this.zPosVal[lp_cnt1][1] > zPos) {
                    if (!this.zMode.equals("full") && lp_cnt1 - 1 > 36) {
                        lp_cnt1 = 37;
                    }
                    int zPoint = 0;
                    switch (lp_cnt1 - 1) {
                        case 0: {
                            zPoint = this.w_image.getWidth(null) / 2 - 2;
                            break;
                        }
                        case 36: {
                            zPoint = this.w_image.getWidth(null) + this.b_image.getWidth(null) + this.t_image.getWidth(null) / 2 - 2;
                            break;
                        }
                        case 49: {
                            zPoint = this.w_image.getWidth(null) + this.b_image.getWidth(null) + this.t_image.getWidth(null) + this.e_b_image.getWidth(null) + this.e_t_image.getWidth(null) / 2 - 3;
                            break;
                        }
                        default: {
                            zPoint = this.zPosVal[lp_cnt1 - 1][0] - 4;
                            break;
                        }
                    }
                    this.zoomBar.setZoomPoint(zPoint);
                    this.zoomBar.dspZoomPointer(true);
                    this.zoomBar.repaint();
                    break;
                }
            }
        }
    }
    
    private boolean datatrm(final int d_len, final byte[] d_buf) {
        for (int lp_cnt = 0; lp_cnt < this.nextDataChk; ++lp_cnt) {
            d_buf[lp_cnt] = this.n_buffer[lp_cnt];
        }
        int d_idx;
        for (d_idx = 0; d_len - this.nextDataChk > d_idx; ++d_idx) {
            if (!this.d_cut) {
                if (d_buf[d_idx] == -1 && d_buf[d_idx + 1] == -40) {
                    this.d_cut = true;
                    this.g_buffer[0] = d_buf[d_idx];
                    this.g_idx = 1;
                }
            }
            else {
                if (d_buf[d_idx] == 13 && d_buf[d_idx + 1] == 10 && d_buf[d_idx + 2] == 45 && d_buf[d_idx + 3] == 45) {
                    if (d_buf[d_idx + 14] == 45 && d_buf[d_idx + 15] == 45) {
                        this.kicker = null;
                    }
                    this.d_cut = false;
                    this.datanext(d_len, d_idx, d_buf);
                    this.n_buffer = new byte[this.nextDataChk];
                    return false;
                }
                try {
                    this.g_buffer[this.g_idx] = d_buf[d_idx];
                }
                catch (ArrayIndexOutOfBoundsException e) {
                    this.dataClear();
                    System.out.println("Image Cut Error!!");
                    return true;
                }
                ++this.g_idx;
            }
        }
        for (int lp_cnt = 0; lp_cnt < this.nextDataChk; ++lp_cnt) {
            this.n_buffer[lp_cnt] = d_buf[d_idx + lp_cnt];
        }
        return true;
    }
    
    private void datanext(final int c_len, int c_idx, final byte[] c_buf) {
        for (int lp_cnt = 0; lp_cnt < this.nextDataChk; ++lp_cnt) {
            this.z_buffer[lp_cnt] = 0;
        }
        this.z_idx = this.nextDataChk;
        while (c_len > c_idx) {
            try {
                this.z_buffer[this.z_idx] = c_buf[c_idx];
                ++this.z_idx;
            }
            catch (IndexOutOfBoundsException ex) {}
            ++c_idx;
        }
    }
    
    private boolean rdydata() {
        this.g_buffer = new byte[400000];
        this.g_idx = 0;
        System.gc();
        return this.datatrm(this.z_idx, this.z_buffer);
    }
    
    private void dataClear() {
        this.g_buffer = new byte[400000];
        this.g_idx = 0;
        this.z_buffer = new byte[this.rcv_size + this.nextDataChk];
        this.z_idx = 0;
        this.n_buffer = new byte[this.nextDataChk];
        this.d_cut = false;
        this.readCtl = true;
        System.gc();
    }
    
    private void popupdsp() {
        if (this.popUpTxt) {
            this.pFrame.show();
            this.popUpTxt = false;
        }
    }
    
    public void paint(final Graphics g) {
        if (this.bimg != null) {
            g.drawImage(this.bimg, 0, 20, this);
            super.paint(g);
        }
    }
    
    public void update(final Graphics g) {
        this.paint(g);
    }
    
    public void reloadChk() {
        if (this.saveLen > this.restartSize) {
            this.rateChgflg = true;
            this.saveLen = 0;
        }
    }
    
    public void rateControl(final String rc) {
        final int rci = new Integer(rc);
        if (rci >= 0 && rci <= 25) {
            this.rate = rc;
        }
        else {
            this.rate = "";
        }
        this.rateChgflg = true;
    }
    
    private void rateChk() {
        if (this.rateChgflg) {
            try {
                this.s_in.close();
                this.dataClear();
                this.socketCrt();
                this.rateChgflg = false;
            }
            catch (IOException e) {
                System.out.print("Socket Close Error!!");
            }
        }
    }
    
    private void rateDsp() {
        final long timeR2 = new Date().getTime();
        if (timeR2 - this.timeR1 >= 1000) {
            try {
                if (timeR2 - this.timeR1 > 2000) {
                    this.showStatus("0 frames/sec, received 0 bytes/sec ...");
                }
                else if (this.frameVal1 >= this.frameVal10) {
                    this.showStatus(String.valueOf(String.valueOf(new StringBuffer(String.valueOf(String.valueOf(this.frameVal10))).append(" frames/sec, received ").append(this.rateVal).append(" bytes/sec ..."))));
                }
                else {
                    this.showStatus(String.valueOf(String.valueOf(new StringBuffer(String.valueOf(String.valueOf(this.frameVal1))).append(" frames/sec, received ").append(this.rateVal).append(" bytes/sec ..."))));
                }
            }
            catch (NullPointerException ex) {}
            if (this.frameCnt >= 10) {
                this.frameVal10 = this.frameVal / 10;
                this.frameVal1 = 0;
                this.frameVal = 0;
                this.frameCnt = 1;
            }
            else {
                this.frameVal1 = 0;
                ++this.frameCnt;
            }
            this.rateVal = 0;
            this.timeR1 = timeR2;
        }
    }
    
    public void imgSizeChange(final int is) {
        if (is >= 0 && is <= 3) {
            this.imgSize = is;
        }
        else {
            this.imgSize = 0;
        }
        this.o_iSizeW = 0;
        this.o_iSizeH = 0;
    }
    
    public void setDPTZtime(final String dptz_val) {
        String rtnStr = "";
        System.out.println("setDPTZtime: ".concat(String.valueOf(String.valueOf(dptz_val))));
        rtnStr = this.commandSR("/command/inquiry.cgi", "inq=camera", true);
        final int startIdx = rtnStr.indexOf("ZoomMode=") + 9;
        this.zMode = "full";
        if (startIdx > 8) {
            final int endIdx = rtnStr.indexOf("&", startIdx + 1);
            if (endIdx > 0) {
                this.zMode = rtnStr.substring(startIdx, endIdx);
            }
        }
        if (this.zMode.equals("full")) {
            this.zBarXSize = 320;
        }
        else {
            this.zBarXSize = 234;
        }
        if (dptz_val.equals("Err") || dptz_val.equals("")) {
            this.endWTime = 0L;
            this.endCTime = 0L;
            this.dptzFlg = false;
        }
        else if (dptz_val.equals("no limit")) {
            this.endWTime = 0L;
            this.endCTime = 0L;
            this.dptzFlg = true;
        }
        else {
            final int idx = dptz_val.indexOf(",");
            if (idx != -1) {
                final int wTime = Integer.parseInt(dptz_val.substring(0, idx));
                this.endWTime = new Date().getTime() + wTime * 1000;
                final int cTime = Integer.parseInt(dptz_val.substring(idx + 1));
                this.endCTime = this.endWTime + cTime * 1000;
            }
        }
    }
    
    private void ctrlTimeChk() {
        final long nowTime = new Date().getTime();
        if (this.endWTime >= nowTime) {
            this.dptzFlg = false;
        }
        else if (this.endCTime >= nowTime) {
            this.dptzFlg = true;
        }
        else {
            this.dptzFlg = false;
        }
    }
    
    private void button1_mouseClicked(final MouseEvent e) {
        this.pFrame.hide();
    }
    
    private void buttonOK_Clicked() {
        this.NPPanel.hide();
        final String NamePassS = String.valueOf(String.valueOf(new StringBuffer(String.valueOf(String.valueOf(this.textFieldUN.getText()))).append(":").append(this.textFieldPW.getText())));
        this.NamePassL = NamePassS.length();
        this.NamePassT = new char[80];
        NamePassS.getChars(0, this.NamePassL, this.NamePassT, 0);
        this.PasscodeT = new char[128];
        this.BuildBase64String();
        this.uCode = new String(this.PasscodeT).trim();
    }
    
    private void buttonCL_Clicked() {
        this.NPPanel.hide();
        this.uErr = 99;
    }
    
    private void BuildBase64String() {
        int inidx = 0;
        int outidx = 0;
        int theThirdInputByte = 0;
        while (this.NamePassL > 2) {
            this.BuildBase64Group(inidx, outidx);
            this.NamePassL -= 3;
            inidx += 3;
            outidx += 4;
        }
        int theFirstInputByte;
        int theSecondInputByte;
        if (this.NamePassL == 2) {
            theFirstInputByte = this.NamePassT[inidx];
            theSecondInputByte = this.NamePassT[inidx + 1];
            theThirdInputByte = 0;
        }
        else {
            if (this.NamePassL != 1) {
                this.PasscodeT[outidx + 1] = '\0';
                return;
            }
            theFirstInputByte = this.NamePassT[inidx];
            theSecondInputByte = 0;
        }
        final char theFirstOutputByte = (char)(theFirstInputByte >> 2);
        final char theSecondOutputByte = (char)((theFirstInputByte << 4 & 0x30) | (theSecondInputByte >> 4 & 0xF));
        this.PasscodeT[outidx] = this.gBase64Table[theFirstOutputByte];
        ++outidx;
        this.PasscodeT[outidx] = this.gBase64Table[theSecondOutputByte];
        if (this.NamePassL == 1) {
            ++outidx;
            this.PasscodeT[outidx] = '=';
            ++outidx;
            this.PasscodeT[outidx] = '=';
        }
        else {
            final char theThirdOutputByte = (char)((theSecondInputByte << 2 & 0x3C) | (theThirdInputByte >> 6 & 0x3));
            ++outidx;
            this.PasscodeT[outidx] = this.gBase64Table[theThirdOutputByte];
            ++outidx;
            this.PasscodeT[outidx] = '=';
        }
        this.PasscodeT[outidx + 1] = '\0';
    }
    
    private void BuildBase64Group(final int iidx, final int oidx) {
        final char theFirstByte = (char)(this.NamePassT[iidx] >> 2 & '?');
        final char theSecondByte = (char)((this.NamePassT[iidx] << 4 & '0') | (this.NamePassT[iidx + 1] >> 4 & '\u000f'));
        final char theThirdByte = (char)((this.NamePassT[iidx + 1] << 2 & '<') | (this.NamePassT[iidx + 2] >> 6 & '\u0003'));
        final char theFourthByte = (char)(this.NamePassT[iidx + 2] & '?');
        this.PasscodeT[oidx] = this.gBase64Table[theFirstByte];
        this.PasscodeT[oidx + 1] = this.gBase64Table[theSecondByte];
        this.PasscodeT[oidx + 2] = this.gBase64Table[theThirdByte];
        this.PasscodeT[oidx + 3] = this.gBase64Table[theFourthByte];
    }
    
    private void zoomBar_mouseClicked(final MouseEvent e) {
        String vCmd = "";
        char[] tmp = new char[4];
        final int xPoint = e.getX();
        for (int lp_cnt = 0; this.zPosVal[lp_cnt][0] < 999; ++lp_cnt) {
            if (xPoint < this.zPosVal[lp_cnt][0]) {
                tmp = Integer.toHexString(this.zPosVal[lp_cnt][1]).toUpperCase().toCharArray();
                switch (tmp.length) {
                    case 1: {
                        vCmd = "0000000".concat(String.valueOf(String.valueOf(tmp[0])));
                        break;
                    }
                    case 2: {
                        vCmd = String.valueOf(String.valueOf(new StringBuffer("00000").append(tmp[0]).append("0").append(tmp[1])));
                        break;
                    }
                    case 3: {
                        vCmd = String.valueOf(String.valueOf(new StringBuffer("000").append(tmp[0]).append("0").append(tmp[1]).append("0").append(tmp[2])));
                        break;
                    }
                    case 4: {
                        vCmd = String.valueOf(String.valueOf(new StringBuffer("0").append(tmp[0]).append("0").append(tmp[1]).append("0").append(tmp[2]).append("0").append(tmp[3])));
                        break;
                    }
                }
                synchronized (this.commandSend) {
                    this.commandSend.setCommand("/command/ptzf.cgi", String.valueOf(String.valueOf(new StringBuffer("visca=81010447").append(vCmd).append("FF"))));
                    this.commandSend.notify();
                    // monitorexit(this.commandSend)
                    break;
                }
            }
        }
    }
    
    private void zoomBar_mouseEntered(final MouseEvent e) {
        if (this.dptzFlg) {
            this.setCursor(this.curH);
        }
    }
    
    private void zoomBar_mouseExited(final MouseEvent e) {
        this.setCursor(this.curS);
    }
}
