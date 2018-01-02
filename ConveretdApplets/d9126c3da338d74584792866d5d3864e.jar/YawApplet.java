import java.awt.Polygon;
import java.net.MalformedURLException;
import java.net.URL;
import java.awt.event.ActionEvent;
import java.awt.event.ItemEvent;
import java.awt.Toolkit;
import java.net.DatagramPacket;
import java.util.Vector;
import java.awt.LayoutManager;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Label;
import java.awt.Component;
import java.awt.MediaTracker;
import java.awt.Font;
import java.awt.image.ImageObserver;
import java.awt.Color;
import java.net.ConnectException;
import java.net.UnknownHostException;
import java.awt.MenuItem;
import java.awt.CheckboxMenuItem;
import java.awt.Menu;
import java.awt.PopupMenu;
import java.awt.event.MouseEvent;
import java.awt.Button;
import java.awt.TextField;
import java.io.DataOutputStream;
import java.awt.Graphics;
import java.awt.Image;
import java.net.DatagramSocket;
import java.io.DataInputStream;
import java.net.Socket;
import java.awt.event.ItemListener;
import java.awt.event.ActionListener;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseListener;
import java.applet.Applet;

// 
// Decompiled by Procyon v0.5.30
// 

public class YawApplet extends Applet implements Runnable, MouseListener, MouseMotionListener, ActionListener, ItemListener
{
    public boolean more;
    Socket soc;
    DataInputStream in;
    DatagramSocket dsoc;
    byte[] imgData;
    GrowableArray imgDataArray;
    GrowableArray tmpImgDataArray;
    Thread thread;
    Image img;
    boolean alive;
    boolean error;
    boolean loading;
    Image m_offScrImg;
    Graphics m_offScr;
    DataOutputStream out;
    String errorMessage;
    int xpos;
    int ypos;
    int xpan;
    int ypan;
    int panSpeed;
    int borderSize;
    boolean left;
    boolean right;
    boolean up;
    boolean down;
    boolean isPanning;
    double zoom;
    int width;
    int height;
    boolean usePass;
    boolean waiting4pass;
    boolean hasErrImg;
    String offImgUrl;
    TextField user;
    TextField pass;
    Button btn;
    public int port;
    String host;
    boolean inside;
    int UDP_PACKET_SIZE;
    int currimageid;
    int prot3CurrImgId;
    int prot3NumRecPack;
    boolean morePacksInImg;
    int packetSizeChange;
    int quality;
    boolean delayTimeout;
    int protocol;
    int change_protocol;
    Applet applet;
    public MouseEvent me;
    byte[] oldImgData;
    Prot3Pinger prot3Pinger;
    static final byte EOI = -39;
    static final byte FF = -1;
    boolean allowZoom;
    boolean firstTime;
    long fpsCountTime;
    float actualFps;
    long startTime;
    long diffTime;
    long stopTime;
    long oldTime;
    int frames;
    boolean showFps;
    PopupMenu popup;
    Menu fpsMenu;
    CheckboxMenuItem fpsShow;
    CheckboxMenuItem fpsFollow;
    CheckboxMenuItem fps30;
    CheckboxMenuItem fps25;
    CheckboxMenuItem fps20;
    CheckboxMenuItem fps15;
    CheckboxMenuItem fps10;
    CheckboxMenuItem fps5;
    CheckboxMenuItem fps1;
    Menu zoomMenu;
    MenuItem zoomIN;
    MenuItem zoomOUT;
    MenuItem zoom100;
    MenuItem aboutMenuItem;
    MenuItem heading;
    Menu transfMenu;
    CheckboxMenuItem prot1;
    CheckboxMenuItem prot2;
    CheckboxMenuItem prot3;
    Menu qualityMenu;
    CheckboxMenuItem q100;
    CheckboxMenuItem q75;
    CheckboxMenuItem q50;
    CheckboxMenuItem q40;
    CheckboxMenuItem q35;
    CheckboxMenuItem q30;
    CheckboxMenuItem q25;
    CheckboxMenuItem q20;
    CheckboxMenuItem q15;
    CheckboxMenuItem q10;
    CheckboxMenuItem q5;
    Menu pktSizeMenu;
    CheckboxMenuItem pks3000;
    CheckboxMenuItem pks2000;
    CheckboxMenuItem pks1500;
    CheckboxMenuItem pks1024;
    CheckboxMenuItem pks768;
    CheckboxMenuItem pks512;
    CheckboxMenuItem pks256;
    CheckboxMenuItem pks128;
    CheckboxMenuItem pks64;
    private int fps;
    long last;
    
    public void stop() {
        if (this.thread != null) {
            this.thread.stop();
        }
        this.alive = false;
        if (this.prot3Pinger != null) {
            this.prot3Pinger.kill();
        }
        try {
            this.soc.close();
        }
        catch (Exception ex) {}
    }
    
    public void destroy() {
        if (this.thread != null) {
            this.thread.stop();
        }
        this.alive = false;
        if (this.prot3Pinger != null) {
            this.prot3Pinger.kill();
        }
        try {
            this.soc.close();
        }
        catch (Exception ex) {}
    }
    
    public void start() {
    }
    
    public void init() {
        (this.applet = this).repaint();
        try {
            this.host = this.getParameter("Host");
            if (this.host == null) {
                System.out.println("No valid URL supplied...");
                throw new Exception("No URL supplied...");
            }
            try {
                this.port = Integer.parseInt(this.getParameter("Port"));
            }
            catch (Exception ex2) {
                this.port = 8081;
            }
            this.offImgUrl = this.getParameter("OfflineImage");
            if (this.offImgUrl == null) {
                System.out.println("No offlineImage supplied...");
            }
            else {
                this.hasErrImg = true;
            }
            final String parameter = this.getParameter("Zoom");
            if (parameter == null) {
                System.out.println("Zoom enabled...");
            }
            else if (parameter.equals("false") || parameter.equals("FALSE")) {
                this.allowZoom = false;
                System.out.println("Zoom disabled...");
            }
            System.out.println("Host: " + this.host + ":" + this.port);
            this.width = this.size().width;
            this.height = this.size().height;
            this.m_offScrImg = this.createImage(this.width, this.height);
            this.m_offScr = this.m_offScrImg.getGraphics();
            this.soc = new Socket(this.host, this.port);
            this.dsoc = new DatagramSocket();
            System.out.println("Will look for UDP packs on: localhost:" + this.dsoc.getLocalPort());
            this.out = new DataOutputStream(this.soc.getOutputStream());
            this.in = new DataInputStream(this.soc.getInputStream());
            this.addMouseListener(this);
            this.addMouseMotionListener(this);
            this.btn.addActionListener(this);
            this.pass.addActionListener(this);
            this.buildMenu();
            this.thread = new Thread(this);
            if (this.thread != null) {
                this.thread.start();
            }
        }
        catch (UnknownHostException ex3) {
            this.error = true;
            this.errorMessage = "User offline!";
            System.out.println("Err. Unknown Host!");
        }
        catch (ConnectException ex4) {
            this.error = true;
            this.errorMessage = "User offline!";
            System.out.println("Err. Timed out! Usr online?");
        }
        catch (Exception ex) {
            if (ex.getMessage().equals("No URL supplied...")) {
                this.errorMessage = "Err. No URL supplied!";
            }
            else {
                ex.printStackTrace();
            }
            this.error = true;
        }
    }
    
    public void paint(final Graphics graphics) {
        if (!this.error) {
            if (this.loading) {
                graphics.drawString("Loading...", 8, 65);
                return;
            }
            if (this.usePass) {
                graphics.setColor(Color.white);
                graphics.fillRect(0, 0, this.width, this.height);
                graphics.setColor(Color.black);
                this.validate();
                return;
            }
            if (this.img != null) {
                this.m_offScr.drawImage(this.img, this.xpos - this.xpan, this.ypos - this.ypan, (int)(this.width * this.zoom), (int)(this.height * this.zoom), this);
                if (this.showFps) {
                    this.m_offScr.setColor(Color.white);
                    this.m_offScr.fillRect(6, 22, 32, 16);
                    this.m_offScr.setColor(Color.red);
                    this.m_offScr.setFont(new Font("Arial", 1, 14));
                    this.m_offScr.drawString((this.actualFps + "    ").substring(0, 4), 8, 35);
                }
                graphics.drawImage(this.m_offScrImg, this.xpos - this.xpan, this.ypos - this.ypan, (int)(this.width * this.zoom), (int)(this.height * this.zoom), this);
            }
            if (this.isPanning) {
                this.makePan();
                this.drawPanArea(graphics);
            }
        }
        else {
            if (!this.hasErrImg) {
                graphics.setColor(Color.white);
                graphics.fillRect(0, 0, this.width, this.height);
                graphics.setColor(Color.black);
                graphics.drawString(this.errorMessage, 8, 65);
                return;
            }
            System.out.println("Painting offlineImage: " + this.offImgUrl);
            this.img = this.getImage(this.getDocumentBase(), this.offImgUrl);
            System.out.println(this.img.toString());
            try {
                final MediaTracker mediaTracker = new MediaTracker(this);
                mediaTracker.addImage(this.img, 1);
                mediaTracker.waitForID(1);
            }
            catch (Exception ex) {
                ex.printStackTrace();
            }
            this.m_offScr.drawImage(this.img, this.xpos - this.xpan, this.ypos - this.ypan, (int)(this.width * this.zoom), (int)(this.height * this.zoom), this);
            graphics.drawImage(this.m_offScrImg, this.xpos - this.xpan, this.ypos - this.ypan, (int)(this.width * this.zoom), (int)(this.height * this.zoom), this);
            if (this.isPanning) {
                this.makePan();
                this.drawPanArea(graphics);
            }
        }
    }
    
    public void buildPassReq() {
        this.pass.setEchoChar('*');
        final Label label = new Label("User: ");
        final Label label2 = new Label("Pass: ");
        new Label("");
        final GridBagLayout layout = new GridBagLayout();
        final GridBagConstraints gridBagConstraints = new GridBagConstraints();
        this.setLayout(layout);
        gridBagConstraints.fill = 0;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.gridwidth = 17;
        layout.setConstraints(label, gridBagConstraints);
        this.add(label);
        gridBagConstraints.fill = 2;
        gridBagConstraints.weightx = 9.0;
        gridBagConstraints.gridwidth = 0;
        layout.setConstraints(this.user, gridBagConstraints);
        this.add(this.user);
        gridBagConstraints.fill = 0;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.gridwidth = 17;
        layout.setConstraints(label2, gridBagConstraints);
        this.add(label2);
        gridBagConstraints.fill = 2;
        gridBagConstraints.weightx = 9.0;
        gridBagConstraints.gridwidth = 0;
        layout.setConstraints(this.pass, gridBagConstraints);
        this.add(this.pass);
        gridBagConstraints.fill = 0;
        gridBagConstraints.anchor = 13;
        gridBagConstraints.weightx = 1.0;
        layout.setConstraints(this.btn, gridBagConstraints);
        this.add(this.btn);
    }
    
    public void buildMenu() {
        this.popup.removeAll();
        this.zoomMenu.removeAll();
        this.fpsMenu.removeAll();
        this.qualityMenu.removeAll();
        this.pktSizeMenu.removeAll();
        this.transfMenu.removeAll();
        this.heading.setEnabled(false);
        this.heading.setFont(new Font("Arial", 1, 14));
        this.popup.add(this.heading);
        this.popup.addSeparator();
        if (this.allowZoom) {
            this.zoomMenu.add(this.zoomIN);
            this.zoomIN.addActionListener(this);
            this.zoomMenu.add(this.zoomOUT);
            this.zoomOUT.addActionListener(this);
            this.zoomMenu.add(this.zoom100);
            this.zoom100.addActionListener(this);
            this.popup.add(this.zoomMenu);
        }
        this.fpsMenu.add(this.fpsShow);
        this.fpsShow.addItemListener(this);
        this.fpsFollow.addItemListener(this);
        this.fpsMenu.addSeparator();
        this.fpsMenu.add(this.fps30);
        this.fps30.addItemListener(this);
        this.fpsMenu.add(this.fps25);
        this.fps25.addItemListener(this);
        this.fpsMenu.add(this.fps20);
        this.fps20.addItemListener(this);
        this.fpsMenu.add(this.fps15);
        this.fps15.addItemListener(this);
        this.fpsMenu.add(this.fps10);
        this.fps10.addItemListener(this);
        this.fpsMenu.add(this.fps5);
        this.fps5.addItemListener(this);
        this.fpsMenu.add(this.fps1);
        this.fps1.addItemListener(this);
        this.popup.add(this.fpsMenu);
        this.fps10.setState(true);
        this.qualityMenu.add(this.q75);
        this.q75.addItemListener(this);
        this.qualityMenu.add(this.q50);
        this.q50.addItemListener(this);
        this.qualityMenu.add(this.q40);
        this.q40.addItemListener(this);
        this.qualityMenu.add(this.q35);
        this.q35.addItemListener(this);
        this.qualityMenu.add(this.q30);
        this.q30.addItemListener(this);
        this.qualityMenu.add(this.q25);
        this.q25.addItemListener(this);
        this.qualityMenu.add(this.q20);
        this.q20.addItemListener(this);
        this.qualityMenu.add(this.q15);
        this.q15.addItemListener(this);
        this.qualityMenu.add(this.q10);
        this.q10.addItemListener(this);
        this.qualityMenu.add(this.q5);
        this.q5.addItemListener(this);
        this.q30.setState(true);
        this.popup.add(this.qualityMenu);
        this.pktSizeMenu.add(this.pks3000);
        this.pks3000.addItemListener(this);
        this.pktSizeMenu.add(this.pks2000);
        this.pks2000.addItemListener(this);
        this.pktSizeMenu.add(this.pks1500);
        this.pks1500.addItemListener(this);
        this.pktSizeMenu.add(this.pks1024);
        this.pks1024.addItemListener(this);
        this.pktSizeMenu.add(this.pks768);
        this.pks768.addItemListener(this);
        this.pktSizeMenu.add(this.pks512);
        this.pks512.addItemListener(this);
        this.pktSizeMenu.add(this.pks256);
        this.pks256.addItemListener(this);
        this.pktSizeMenu.add(this.pks128);
        this.pks128.addItemListener(this);
        this.pktSizeMenu.add(this.pks64);
        this.pks64.addItemListener(this);
        this.pks1500.setState(true);
        this.popup.add(this.pktSizeMenu);
        this.transfMenu.add(this.prot1);
        this.prot1.addItemListener(this);
        this.prot2.addItemListener(this);
        this.transfMenu.add(this.prot3);
        this.prot3.addItemListener(this);
        this.prot1.setState(true);
        this.popup.add(this.transfMenu);
        this.aboutMenuItem.addActionListener(this);
        this.popup.addSeparator();
        this.popup.add(this.aboutMenuItem);
        this.add(this.popup);
        if (this.protocol == 1) {
            this.fpsFollow.setEnabled(false);
            this.fps30.setEnabled(false);
            this.fps25.setEnabled(false);
            this.fps20.setEnabled(false);
            this.fps15.setEnabled(false);
            this.fps10.setEnabled(false);
            this.fps5.setEnabled(false);
            this.fps1.setEnabled(false);
            this.pktSizeMenu.setEnabled(false);
            this.qualityMenu.setEnabled(false);
        }
    }
    
    public void update(final Graphics graphics) {
        this.paint(graphics);
    }
    
    public void run() {
        this.more = true;
        new Vector();
        try {
            this.out.writeBytes("streamReq\n\n");
            this.out.flush();
            final String line = this.in.readLine();
            if (line.equals("2many")) {
                this.errorMessage = "Too many Connections!";
                this.error = true;
                this.more = false;
                this.out.close();
                this.in.close();
                this.soc.close();
            }
            else if (line.equals("pass!")) {
                this.usePass = true;
                this.loading = false;
                this.waiting4pass = true;
                this.buildPassReq();
                this.repaint();
                System.out.println("Enter user/pass!");
                while (this.waiting4pass) {
                    Thread.sleep(250L);
                }
                this.repaint();
                this.out.writeBytes(String.valueOf(this.user.getText()) + "\r\n");
                this.out.writeBytes(String.valueOf(this.pass.getText()) + "\r\n");
                if (this.in.readLine().equals("go")) {
                    this.loading = false;
                    this.usePass = false;
                }
                else {
                    this.errorMessage = "Wrong user/pass!";
                    this.error = true;
                    this.more = false;
                    this.usePass = false;
                    this.loading = false;
                    this.out.close();
                    this.in.close();
                    this.soc.close();
                    this.removeAll();
                    this.validate();
                    this.repaint();
                }
            }
            else if (line.equals("banned")) {
                this.errorMessage = "You are banned!";
                this.error = true;
                this.more = false;
                this.out.close();
                this.in.close();
                this.soc.close();
            }
        }
        catch (Exception ex3) {}
        if (this.protocol == 3) {
            (this.prot3Pinger = new Prot3Pinger(this, this.out, this.in)).start();
        }
        this.last = System.currentTimeMillis();
        while (this.more && this.alive) {
            this.protocol = this.change_protocol;
            try {
                if (this.more && this.protocol == 1) {
                    this.out.write(64);
                    this.out.flush();
                    this.more = false;
                }
                final long currentTimeMillis = System.currentTimeMillis();
                this.more = false;
                if (this.protocol == 1) {
                    final int int1 = this.in.readInt();
                    this.imgData = new byte[int1];
                    for (int i = 0; i < int1; ++i) {
                        this.imgData[i] = this.in.readByte();
                    }
                }
                else if (this.protocol == 3) {
                    if (this.prot3Pinger == null) {
                        (this.prot3Pinger = new Prot3Pinger(this, this.out, this.in)).start();
                    }
                    this.UDP_PACKET_SIZE = this.packetSizeChange;
                    this.imgData = new byte[this.UDP_PACKET_SIZE];
                    if (this.tmpImgDataArray.size() > 0) {
                        this.imgDataArray.addToArray(this.tmpImgDataArray.m_array, 0, this.tmpImgDataArray.size());
                        this.tmpImgDataArray.reset();
                    }
                    boolean b = true;
                    while (this.morePacksInImg) {
                        final byte[] array = new byte[this.UDP_PACKET_SIZE];
                        final DatagramPacket datagramPacket = new DatagramPacket(array, array.length);
                        try {
                            this.dsoc.receive(datagramPacket);
                        }
                        catch (Exception ex) {
                            ex.printStackTrace();
                            if (ex.getMessage().equals("Receive timed out")) {
                                System.out.println("Socket timeout!");
                                ++this.prot3NumRecPack;
                                continue;
                            }
                        }
                        final byte[] array2 = new byte[4];
                        final byte[] array3 = new byte[4];
                        for (int j = 0; j < 4; ++j) {
                            array2[j] = array[j];
                        }
                        for (int k = 0; k < 4; ++k) {
                            array3[k] = array[k + 4];
                        }
                        final int int2 = this.readInt(array2);
                        final int int3 = this.readInt(array3);
                        if (int2 == this.currimageid) {
                            if (this.prot3NumRecPack == int3) {
                                this.imgDataArray.addToArray(array, 8, datagramPacket.getLength() - 8);
                                if (array[datagramPacket.getLength() - 1] == -39 && array[datagramPacket.getLength() - 2] == -1) {
                                    this.morePacksInImg = false;
                                }
                            }
                            else if (this.prot3NumRecPack < int3) {
                                ++this.prot3NumRecPack;
                            }
                            if (!this.morePacksInImg) {
                                this.prot3NumRecPack = 0;
                                break;
                            }
                        }
                        else {
                            if (int2 > this.currimageid) {
                                this.tmpImgDataArray.reset();
                                this.tmpImgDataArray.addToArray(array, 8, datagramPacket.getLength() - 8);
                                this.currimageid = int2;
                                this.prot3NumRecPack = int3 + 1;
                                b = false;
                                break;
                            }
                            if (int2 < this.currimageid) {
                                --this.prot3NumRecPack;
                            }
                        }
                        if (b) {
                            ++this.prot3NumRecPack;
                        }
                    }
                    this.morePacksInImg = true;
                    if (b) {
                        ++this.currimageid;
                    }
                    final int size = this.imgDataArray.size();
                    this.imgData = new byte[size];
                    System.arraycopy(this.imgDataArray.m_array, 0, this.imgData, 0, size);
                    this.imgDataArray.reset();
                }
                if (this.img != null) {
                    this.img.flush();
                }
                this.img = Toolkit.getDefaultToolkit().createImage(this.imgData);
                try {
                    final MediaTracker mediaTracker = new MediaTracker(this);
                    mediaTracker.addImage(this.img, 1);
                    mediaTracker.waitForID(1);
                    if (mediaTracker.statusID(1, false) == 8) {
                        this.stopTime = System.currentTimeMillis();
                        ++this.frames;
                        this.diffTime = this.stopTime - this.oldTime;
                        if (this.diffTime > 1000L) {
                            this.actualFps = this.frames / (0.001f * this.diffTime);
                            this.frames = 0;
                            this.oldTime = this.stopTime;
                        }
                    }
                }
                catch (Exception ex4) {}
                this.loading = false;
                this.repaint();
                this.more = true;
                if (this.protocol != 1) {
                    continue;
                }
                final long currentTimeMillis2 = System.currentTimeMillis();
                long n;
                if (currentTimeMillis2 < currentTimeMillis) {
                    n = 1000L - currentTimeMillis + currentTimeMillis2;
                }
                else {
                    n = currentTimeMillis2 - currentTimeMillis;
                }
                if (n < 0L) {
                    n = 0L;
                }
                if (this.protocol == 1 && n > 90L) {
                    n = 80L;
                }
                Thread.sleep(90L - n);
            }
            catch (Exception ex2) {
                ex2.printStackTrace();
            }
        }
    }
    
    public void itemStateChanged(final ItemEvent itemEvent) {
        final CheckboxMenuItem checkboxMenuItem = (CheckboxMenuItem)itemEvent.getSource();
        if (checkboxMenuItem.equals(this.fpsFollow)) {
            this.delayTimeout = this.fpsFollow.getState();
            return;
        }
        if (checkboxMenuItem.equals(this.fpsShow)) {
            this.showFps = this.fpsShow.getState();
            return;
        }
        if (checkboxMenuItem.equals(this.fps30) || checkboxMenuItem.equals(this.fps25) || checkboxMenuItem.equals(this.fps20) || checkboxMenuItem.equals(this.fps15) || checkboxMenuItem.equals(this.fps10) || checkboxMenuItem.equals(this.fps5) || checkboxMenuItem.equals(this.fps1)) {
            System.out.println("fps changed...");
            this.fps30.setState(false);
            this.fps25.setState(false);
            this.fps20.setState(false);
            this.fps15.setState(false);
            this.fps10.setState(false);
            this.fps5.setState(false);
            this.fps1.setState(false);
            checkboxMenuItem.setState(true);
            if (checkboxMenuItem.equals(this.fps30)) {
                this.fps = 30;
                return;
            }
            if (checkboxMenuItem.equals(this.fps25)) {
                this.fps = 25;
                return;
            }
            if (checkboxMenuItem.equals(this.fps20)) {
                this.fps = 20;
                return;
            }
            if (checkboxMenuItem.equals(this.fps15)) {
                this.fps = 15;
                return;
            }
            if (checkboxMenuItem.equals(this.fps10)) {
                this.fps = 10;
                return;
            }
            if (checkboxMenuItem.equals(this.fps5)) {
                this.fps = 5;
                return;
            }
            if (checkboxMenuItem.equals(this.fps1)) {
                this.fps = 1;
            }
        }
        else if (checkboxMenuItem.equals(this.pks3000) || checkboxMenuItem.equals(this.pks2000) || checkboxMenuItem.equals(this.pks1500) || checkboxMenuItem.equals(this.pks1024) || checkboxMenuItem.equals(this.pks768) || checkboxMenuItem.equals(this.pks512) || checkboxMenuItem.equals(this.pks256) || checkboxMenuItem.equals(this.pks128) || checkboxMenuItem.equals(this.pks64)) {
            System.out.println("pkt size changed...");
            this.pks3000.setState(false);
            this.pks2000.setState(false);
            this.pks1500.setState(false);
            this.pks1024.setState(false);
            this.pks768.setState(false);
            this.pks512.setState(false);
            this.pks256.setState(false);
            this.pks128.setState(false);
            this.pks64.setState(false);
            checkboxMenuItem.setState(true);
            if (checkboxMenuItem.equals(this.pks3000)) {
                this.packetSizeChange = 3000;
                return;
            }
            if (checkboxMenuItem.equals(this.pks2000)) {
                this.packetSizeChange = 2000;
                return;
            }
            if (checkboxMenuItem.equals(this.pks1500)) {
                this.packetSizeChange = 1500;
                return;
            }
            if (checkboxMenuItem.equals(this.pks1024)) {
                this.packetSizeChange = 1024;
                return;
            }
            if (checkboxMenuItem.equals(this.pks768)) {
                this.packetSizeChange = 768;
                return;
            }
            if (checkboxMenuItem.equals(this.pks512)) {
                this.packetSizeChange = 512;
                return;
            }
            if (checkboxMenuItem.equals(this.pks256)) {
                this.packetSizeChange = 256;
                return;
            }
            if (checkboxMenuItem.equals(this.pks128)) {
                this.packetSizeChange = 128;
                return;
            }
            if (checkboxMenuItem.equals(this.pks64)) {
                this.packetSizeChange = 64;
            }
        }
        else if (checkboxMenuItem.equals(this.q100) || checkboxMenuItem.equals(this.q75) || checkboxMenuItem.equals(this.q50) || checkboxMenuItem.equals(this.q40) || checkboxMenuItem.equals(this.q35) || checkboxMenuItem.equals(this.q30) || checkboxMenuItem.equals(this.q25) || checkboxMenuItem.equals(this.q20) || checkboxMenuItem.equals(this.q15) || checkboxMenuItem.equals(this.q10) || checkboxMenuItem.equals(this.q5)) {
            System.out.println("Quality changed...");
            this.q100.setState(false);
            this.q75.setState(false);
            this.q50.setState(false);
            this.q40.setState(false);
            this.q35.setState(false);
            this.q30.setState(false);
            this.q25.setState(false);
            this.q20.setState(false);
            this.q15.setState(false);
            this.q10.setState(false);
            this.q5.setState(false);
            checkboxMenuItem.setState(true);
            if (checkboxMenuItem.equals(this.q100)) {
                this.quality = 100;
                return;
            }
            if (checkboxMenuItem.equals(this.q75)) {
                this.quality = 75;
                return;
            }
            if (checkboxMenuItem.equals(this.q50)) {
                this.quality = 50;
                return;
            }
            if (checkboxMenuItem.equals(this.q40)) {
                this.quality = 40;
                return;
            }
            if (checkboxMenuItem.equals(this.q35)) {
                this.quality = 35;
                return;
            }
            if (checkboxMenuItem.equals(this.q30)) {
                this.quality = 30;
                return;
            }
            if (checkboxMenuItem.equals(this.q25)) {
                this.quality = 25;
                return;
            }
            if (checkboxMenuItem.equals(this.q20)) {
                this.quality = 20;
                return;
            }
            if (checkboxMenuItem.equals(this.q15)) {
                this.quality = 15;
                return;
            }
            if (checkboxMenuItem.equals(this.q10)) {
                this.quality = 10;
                return;
            }
            if (checkboxMenuItem.equals(this.q5)) {
                this.quality = 5;
            }
        }
        else if (checkboxMenuItem.equals(this.prot1) || checkboxMenuItem.equals(this.prot2) || checkboxMenuItem.equals(this.prot3)) {
            System.out.println("Protocol changed...");
            this.prot1.setState(false);
            this.prot2.setState(false);
            this.prot3.setState(false);
            checkboxMenuItem.setState(true);
            if (checkboxMenuItem.equals(this.prot1)) {
                System.out.println("prot1");
                this.fpsFollow.setEnabled(false);
                this.fps30.setEnabled(false);
                this.fps25.setEnabled(false);
                this.fps20.setEnabled(false);
                this.fps15.setEnabled(false);
                this.fps10.setEnabled(false);
                this.fps5.setEnabled(false);
                this.fps1.setEnabled(false);
                this.pktSizeMenu.setEnabled(false);
                this.qualityMenu.setEnabled(false);
                this.change_protocol = 1;
                if (this.prot3Pinger != null) {
                    this.prot3Pinger.kill();
                    this.prot3Pinger = null;
                }
            }
            else if (checkboxMenuItem.equals(this.prot2)) {
                System.out.println("prot2");
                this.fpsFollow.setEnabled(true);
                this.fps30.setEnabled(true);
                this.fps25.setEnabled(true);
                this.fps20.setEnabled(true);
                this.fps15.setEnabled(true);
                this.fps10.setEnabled(true);
                this.fps5.setEnabled(true);
                this.fps1.setEnabled(true);
                this.pktSizeMenu.setEnabled(true);
                this.qualityMenu.setEnabled(true);
                this.change_protocol = 2;
                if (this.prot3Pinger != null) {
                    this.prot3Pinger.kill();
                    this.prot3Pinger = null;
                }
            }
            else if (checkboxMenuItem.equals(this.prot3)) {
                System.out.println("prot3");
                this.fpsFollow.setEnabled(true);
                this.fps30.setEnabled(true);
                this.fps25.setEnabled(true);
                this.fps20.setEnabled(true);
                this.fps15.setEnabled(true);
                this.fps10.setEnabled(true);
                this.fps5.setEnabled(true);
                this.fps1.setEnabled(true);
                this.pktSizeMenu.setEnabled(true);
                this.qualityMenu.setEnabled(true);
                this.change_protocol = 3;
            }
        }
    }
    
    public void actionPerformed(final ActionEvent actionEvent) {
        final String actionCommand = actionEvent.getActionCommand();
        System.out.println(actionCommand);
        if (actionCommand.equals("About...")) {
            try {
                this.getAppletContext().showDocument(new URL("http://www.yawcam.com"));
                return;
            }
            catch (MalformedURLException ex) {
                this.showStatus("URL not found");
                return;
            }
        }
        if (actionCommand.equals("In")) {
            this.zoom += 0.1;
            this.xpos = (int)(0.0 - (this.width * this.zoom - this.width) / 2.0);
            this.ypos = (int)(0.0 - (this.height * this.zoom - this.height) / 2.0);
            this.fixEdges();
            if (this.zoom == 1.0) {
                this.zoom = 1.0;
                final boolean b = false;
                this.ypos = (b ? 1 : 0);
                this.xpos = (b ? 1 : 0);
                final boolean b2 = false;
                this.ypan = (b2 ? 1 : 0);
                this.xpan = (b2 ? 1 : 0);
            }
        }
        else if (actionCommand.equals("Out")) {
            if (this.zoom >= 1.1) {
                this.zoom -= 0.1;
            }
            this.xpos = (int)(0.0 - (this.width * this.zoom - this.width) / 2.0);
            this.ypos = (int)(0.0 - (this.height * this.zoom - this.height) / 2.0);
            this.fixEdges();
            if (this.zoom == 1.0) {
                this.zoom = 1.0;
                final boolean b3 = false;
                this.ypos = (b3 ? 1 : 0);
                this.xpos = (b3 ? 1 : 0);
                final boolean b4 = false;
                this.ypan = (b4 ? 1 : 0);
                this.xpan = (b4 ? 1 : 0);
            }
        }
        else {
            if (actionCommand.equals("100%")) {
                this.zoom = 1.0;
                final boolean b5 = false;
                this.ypos = (b5 ? 1 : 0);
                this.xpos = (b5 ? 1 : 0);
                final boolean b6 = false;
                this.ypan = (b6 ? 1 : 0);
                this.xpan = (b6 ? 1 : 0);
                return;
            }
            this.waiting4pass = false;
            this.loading = true;
            this.removeAll();
            this.validate();
        }
    }
    
    public void mouseClicked(final MouseEvent mouseEvent) {
    }
    
    public void mouseEntered(final MouseEvent mouseEvent) {
        this.inside = true;
    }
    
    public void showPopup() {
        this.popup.show(this.me.getComponent(), this.me.getX(), this.me.getY());
    }
    
    public void mousePressed(final MouseEvent me) {
        this.me = me;
        final int modifiers = me.getModifiers();
        if ((modifiers & 0x4) != 0x0 || (modifiers & 0x8) != 0x0) {
            new Thread(new Runnable() {
                public void run() {
                    YawApplet.this.showPopup();
                }
            }).start();
        }
        else if (this.allowZoom) {
            if (!me.isShiftDown()) {
                this.zoom += 0.1;
            }
            else if (this.zoom >= 1.1) {
                this.zoom -= 0.1;
            }
            this.xpos = (int)(0.0 - (this.width * this.zoom - this.width) / 2.0);
            this.ypos = (int)(0.0 - (this.height * this.zoom - this.height) / 2.0);
            this.fixEdges();
            if (this.zoom == 1.0) {
                this.zoom = 1.0;
                final boolean b = false;
                this.ypos = (b ? 1 : 0);
                this.xpos = (b ? 1 : 0);
                final boolean b2 = false;
                this.ypan = (b2 ? 1 : 0);
                this.xpan = (b2 ? 1 : 0);
            }
            if (me.isControlDown()) {
                this.zoom = 1.0;
                final boolean b3 = false;
                this.ypos = (b3 ? 1 : 0);
                this.xpos = (b3 ? 1 : 0);
                final boolean b4 = false;
                this.ypan = (b4 ? 1 : 0);
                this.xpan = (b4 ? 1 : 0);
            }
            this.repaint();
        }
        me.consume();
    }
    
    public void mouseReleased(final MouseEvent mouseEvent) {
    }
    
    public void mouseExited(final MouseEvent mouseEvent) {
        this.isPanning = false;
        this.inside = false;
    }
    
    public void mouseMoved(final MouseEvent mouseEvent) {
        final int x = mouseEvent.getX();
        final int y = mouseEvent.getY();
        this.isPanning = false;
        if (x > this.width - this.borderSize) {
            this.right = true;
            this.isPanning = true;
        }
        else {
            this.right = false;
        }
        if (x < this.borderSize) {
            this.left = true;
            this.isPanning = true;
        }
        else {
            this.left = false;
        }
        if (y > this.height - this.borderSize) {
            this.up = true;
            this.isPanning = true;
        }
        else {
            this.up = false;
        }
        if (y < this.borderSize) {
            this.down = true;
            this.isPanning = true;
        }
        else {
            this.down = false;
        }
        this.repaint();
    }
    
    public void makePan() {
        if (this.right) {
            this.xpan += this.panSpeed;
        }
        if (this.left) {
            this.xpan -= this.panSpeed;
        }
        if (this.up) {
            this.ypan += this.panSpeed;
        }
        if (this.down) {
            this.ypan -= this.panSpeed;
        }
        this.fixEdges();
    }
    
    public void drawPanArea(final Graphics graphics) {
        if (this.zoom != 1.0) {
            final Color white = Color.white;
            final Color blue = Color.blue;
            if (this.left) {
                graphics.setColor(white);
                graphics.fillRect(0, 0, this.borderSize, this.height);
                final int[] array = { this.borderSize, 0, this.borderSize, 0, this.borderSize };
                final Polygon polygon = new Polygon(array, new int[] { 0, this.height / 4, this.height / 2, 3 * this.height / 4, this.height }, array.length);
                graphics.setColor(blue);
                graphics.fillPolygon(polygon);
            }
            if (this.right) {
                graphics.setColor(white);
                graphics.fillRect(this.width - this.borderSize, 0, this.borderSize, this.height);
                final int[] array2 = { this.width - this.borderSize, this.width, this.width - this.borderSize, this.width, this.width - this.borderSize };
                final Polygon polygon2 = new Polygon(array2, new int[] { 0, this.height / 4, this.height / 2, 3 * this.height / 4, this.height }, array2.length);
                graphics.setColor(blue);
                graphics.fillPolygon(polygon2);
            }
            if (this.down) {
                graphics.setColor(white);
                graphics.fillRect(0, 0, this.width, this.borderSize);
                final int[] array3 = { 0, this.width / 4, this.width / 2, 3 * this.width / 4, this.width };
                final Polygon polygon3 = new Polygon(array3, new int[] { this.borderSize, 0, this.borderSize, 0, this.borderSize }, array3.length);
                graphics.setColor(blue);
                graphics.fillPolygon(polygon3);
            }
            if (this.up) {
                graphics.setColor(white);
                graphics.fillRect(0, this.height - this.borderSize, this.width, this.borderSize);
                final int[] array4 = { 0, this.width / 4, this.width / 2, 3 * this.width / 4, this.width };
                final Polygon polygon4 = new Polygon(array4, new int[] { this.height - this.borderSize, this.height, this.height - this.borderSize, this.height, this.height - this.borderSize }, array4.length);
                graphics.setColor(blue);
                graphics.fillPolygon(polygon4);
            }
            graphics.setColor(Color.black);
        }
    }
    
    public void mouseDragged(final MouseEvent mouseEvent) {
    }
    
    private void fixEdges() {
        if (this.xpos - this.xpan > 0) {
            this.xpan += this.xpos - this.xpan;
        }
        if (this.xpos - this.xpan + this.width * this.zoom < this.width) {
            this.xpan -= this.width - (this.xpos - this.xpan + (int)(this.width * this.zoom));
        }
        if (this.ypos - this.ypan > 0) {
            this.ypan += this.ypos - this.ypan;
        }
        if (this.ypos - this.ypan + this.height * this.zoom < this.height) {
            this.ypan -= this.height - (this.ypos - this.ypan + (int)(this.height * this.zoom));
        }
    }
    
    public int readInt(final byte[] array) {
        final int n = 255;
        return (array[0] & n) | (array[1] & n) << 8 | (array[2] & n) << 16 | (array[3] & n) << 24;
    }
    
    public int getFps() {
        return this.fps;
    }
    
    public int getQuality() {
        return this.quality;
    }
    
    public int getPktSize() {
        return this.packetSizeChange;
    }
    
    public void setPktSize(final int udp_PACKET_SIZE) {
        this.UDP_PACKET_SIZE = udp_PACKET_SIZE;
    }
    
    void PrintTime(final String s) {
        final long currentTimeMillis = System.currentTimeMillis();
        System.out.println(String.valueOf(s) + " " + (currentTimeMillis - this.last));
        this.last = currentTimeMillis;
    }
    
    public YawApplet() {
        this.imgDataArray = new GrowableArray();
        this.tmpImgDataArray = new GrowableArray();
        this.alive = true;
        this.error = false;
        this.loading = true;
        this.errorMessage = "Err. Chk host! Usr online?";
        this.panSpeed = 3;
        this.borderSize = 15;
        this.isPanning = false;
        this.zoom = 1.0;
        this.width = 320;
        this.height = 240;
        this.usePass = false;
        this.waiting4pass = false;
        this.hasErrImg = false;
        this.user = new TextField();
        this.pass = new TextField();
        this.btn = new Button("Connect");
        this.port = 8081;
        this.inside = false;
        this.UDP_PACKET_SIZE = 1500;
        this.morePacksInImg = true;
        this.packetSizeChange = 1500;
        this.quality = 30;
        this.delayTimeout = false;
        this.protocol = 1;
        this.change_protocol = 1;
        this.oldImgData = new byte[0];
        this.allowZoom = true;
        this.firstTime = true;
        this.showFps = false;
        this.popup = new PopupMenu();
        this.fpsMenu = new Menu("Fps");
        this.fpsShow = new CheckboxMenuItem("Show fps");
        this.fpsFollow = new CheckboxMenuItem("Follow strict");
        this.fps30 = new CheckboxMenuItem("30 fps");
        this.fps25 = new CheckboxMenuItem("25 fps");
        this.fps20 = new CheckboxMenuItem("20 fps");
        this.fps15 = new CheckboxMenuItem("15 fps");
        this.fps10 = new CheckboxMenuItem("10 fps");
        this.fps5 = new CheckboxMenuItem("5 fps");
        this.fps1 = new CheckboxMenuItem("1 fps");
        this.zoomMenu = new Menu("Zoom");
        this.zoomIN = new MenuItem("In");
        this.zoomOUT = new MenuItem("Out");
        this.zoom100 = new MenuItem("100%");
        this.aboutMenuItem = new MenuItem("About...");
        this.heading = new MenuItem("Yawcam");
        this.transfMenu = new Menu("Transfer");
        this.prot1 = new CheckboxMenuItem("Protocol 1");
        this.prot2 = new CheckboxMenuItem("Protocol 2");
        this.prot3 = new CheckboxMenuItem("Protocol 2");
        this.qualityMenu = new Menu("Quality");
        this.q100 = new CheckboxMenuItem("100 %");
        this.q75 = new CheckboxMenuItem("75  %");
        this.q50 = new CheckboxMenuItem("50  %");
        this.q40 = new CheckboxMenuItem("40  %");
        this.q35 = new CheckboxMenuItem("35  %");
        this.q30 = new CheckboxMenuItem("30  %");
        this.q25 = new CheckboxMenuItem("25  %");
        this.q20 = new CheckboxMenuItem("20  %");
        this.q15 = new CheckboxMenuItem("15  %");
        this.q10 = new CheckboxMenuItem("10  %");
        this.q5 = new CheckboxMenuItem("5   %");
        this.pktSizeMenu = new Menu("Pkt. Size");
        this.pks3000 = new CheckboxMenuItem("3000 B");
        this.pks2000 = new CheckboxMenuItem("2000 B");
        this.pks1500 = new CheckboxMenuItem("1500 B");
        this.pks1024 = new CheckboxMenuItem("1024 B");
        this.pks768 = new CheckboxMenuItem("768 B");
        this.pks512 = new CheckboxMenuItem("512 B");
        this.pks256 = new CheckboxMenuItem("256 B");
        this.pks128 = new CheckboxMenuItem("128 B");
        this.pks64 = new CheckboxMenuItem("64 B");
        this.fps = 10;
    }
    
    class GrowableArray
    {
        private int m_count;
        private int m_allocated;
        public byte[] m_array;
        
        public void addToArray(final byte[] array, final int n, final int n2) {
            if (this.m_count + n2 > this.m_allocated) {
                this.m_allocated = Math.max(this.m_count + n2, this.m_allocated * 2);
                final byte[] array2 = new byte[this.m_allocated];
                System.arraycopy(this.m_array, 0, array2, 0, this.m_count);
                this.m_array = array2;
            }
            System.arraycopy(array, n, this.m_array, this.m_count, n2);
            this.m_count += n2;
        }
        
        public void reset() {
            this.m_count = 0;
            this.m_allocated = 0;
            this.m_array = null;
            this.m_array = new byte[0];
        }
        
        public int size() {
            return this.m_count;
        }
        
        GrowableArray() {
            this.m_array = new byte[0];
        }
    }
}
