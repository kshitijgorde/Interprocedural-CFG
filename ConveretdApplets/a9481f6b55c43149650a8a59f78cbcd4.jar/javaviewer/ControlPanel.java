// 
// Decompiled by Procyon v0.5.30
// 

package javaviewer;

import java.awt.Container;
import java.net.URLConnection;
import java.io.IOException;
import java.io.DataInputStream;
import java.io.PrintStream;
import java.net.URL;
import java.awt.Component;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseAdapter;
import java.awt.Rectangle;
import java.awt.event.FocusEvent;
import java.awt.event.MouseEvent;
import java.awt.LayoutManager;
import java.util.Date;
import java.awt.Cursor;
import java.awt.Image;
import java.awt.Choice;
import java.awt.Label;
import java.awt.Color;
import java.awt.event.FocusListener;
import java.awt.event.MouseListener;
import java.applet.Applet;

public class ControlPanel extends Applet implements MouseListener, FocusListener
{
    private final String _$4511 = "Trigger=smtp";
    private final String _$1293 = "Trigger=ftp";
    private final String _$4512 = "Trigger=memory";
    private final String _$4513 = "Trigger=alarmout1";
    private final String _$4514 = "Trigger=alarmout2";
    private final String _$1297 = "Trigger=daynight";
    private Viewer _$4305;
    private int _$4515;
    public boolean dZoomBtnPush;
    public boolean controlBtnPush;
    public Color color_back;
    public Color color_fore;
    public ImageButton[] IC;
    private Label _$1304;
    private Choice _$1306;
    private Label _$1307;
    private Choice _$1308;
    private Image _$1310;
    private Image _$1311;
    private Choice _$1312;
    private Image _$1313;
    private String _$1314;
    private String _$1315;
    private String _$1316;
    private String _$1317;
    private String _$1318;
    private String _$1319;
    private Image _$1320;
    private Image _$1321;
    private Image _$1322;
    private Label _$1323;
    private Image _$4519;
    private Image _$4520;
    private Image _$4521;
    private Image _$4522;
    private Image _$1328;
    private Label _$4523;
    private boolean _$1330;
    private Label _$4524;
    private Choice _$1332;
    private Cursor _$1267;
    private Cursor _$1265;
    private Cursor _$1336;
    private String _$1338;
    private String _$4525;
    private String _$1013;
    private final int _$1340 = 13;
    private final int _$4526 = 31;
    private final int _$4527 = 22;
    private boolean _$1343;
    private Date _$1345;
    private Date _$4528;
    private int _$4529;
    
    public ControlPanel(final Viewer $4305, final int n, final String s, final ViewModeDetail viewModeDetail) {
        this.dZoomBtnPush = false;
        this.controlBtnPush = false;
        this.color_back = new Color(75, 110, 182);
        this.color_fore = new Color(255, 255, 255);
        this.IC = new ImageButton[3];
        this._$1304 = new Label();
        this._$1306 = new Choice();
        this._$1307 = new Label();
        this._$1308 = new Choice();
        this._$1312 = new Choice();
        this._$1323 = new Label();
        this._$4523 = new Label();
        this._$1330 = false;
        this._$4524 = new Label();
        this._$1332 = new Choice();
        this._$1267 = new Cursor(12);
        this._$1265 = this.getCursor();
        this._$1336 = new Cursor(1);
        this._$1338 = "/command/main.cgi";
        this._$4525 = "";
        this._$4529 = 6;
        try {
            Thread.currentThread().setPriority(9);
        }
        catch (SecurityException ex) {
            ex.printStackTrace();
        }
        this.setLayout(null);
        this._$4305 = $4305;
        this._$1013 = this._$4305.host;
        if (s.equals("PRM_VIEWMODE_FULL")) {
            this._$4515 = 0;
        }
        else if (s.equals("PRM_VIEWMODE_LIGHT")) {}
        $4305.logger.print("ControlPanel constructer/vmDetail.isFRateActive() is " + viewModeDetail.isFRateActive());
        if (viewModeDetail.isFRateActive()) {
            this._$1353(n);
            this._$4529 += 44;
        }
        if (viewModeDetail.isVSizeActive()) {
            this._$1355();
            this._$4529 += 58;
        }
        if (viewModeDetail.isDZoomActive()) {
            this._$4530();
            this._$4529 += 54;
        }
        if (viewModeDetail.isControlActive()) {
            this._$1359();
            this._$4529 += 54;
            this._$1360();
            this._$4529 += 58;
        }
        else if (viewModeDetail.isPresetPositionActive()) {
            this._$1360();
            this._$4529 += 58;
        }
        if (viewModeDetail.isTriggerActive()) {
            this._$1363();
        }
        this._$4308();
        this.setAllBackground(this.color_back = this._$4305.CtrlBackColor);
        this.setAllForeground(this.color_fore);
        this.reloadAllIcon(this.color_back);
        this.addMouseListener(this);
        (this._$1345 = new Date()).setTime(Long.MAX_VALUE);
        (this._$4528 = new Date()).setTime(new Long(Long.MAX_VALUE));
        this.addFocusListener(this);
    }
    
    public void mousePressed(final MouseEvent mouseEvent) {
        this.dzoomCanceled();
        if (this._$4305.controler != null) {
            this._$4305.controler.toFront();
        }
    }
    
    public void mouseReleased(final MouseEvent mouseEvent) {
    }
    
    public void mouseEntered(final MouseEvent mouseEvent) {
        this.setCursor(this._$4305.curDCur);
        if (this._$4305.controler != null) {
            this._$4305.controler.toFront();
        }
    }
    
    public void mouseExited(final MouseEvent mouseEvent) {
    }
    
    public void mouseClicked(final MouseEvent mouseEvent) {
    }
    
    public void focusLost(final FocusEvent focusEvent) {
    }
    
    public void focusGained(final FocusEvent focusEvent) {
        if (this._$4305.controler != null) {
            this._$4305.controler.toFront();
        }
    }
    
    public void repaintIcon() {
        for (int i = 0; i < this.IC.length; ++i) {
            if (this.IC[i] != null) {
                this.IC[i].repaint();
            }
        }
    }
    
    private void _$4308() {
        this.setViewSize(this._$4305.ViewSize);
        if (this._$4305.pFrameRateFlg) {
            this.setFrameRate(Integer.parseInt(this._$4305.pFrameRate));
        }
    }
    
    public void destroy() {
        for (int i = 0; i < this.IC.length; ++i) {
            this.IC[i] = null;
        }
        this.IC = null;
        this._$4305 = null;
        this._$1310 = null;
        this._$1311 = null;
        this._$1312 = null;
        this._$1313 = null;
        this._$1320 = null;
        this._$1321 = null;
        this._$1322 = null;
        this._$4519 = null;
        this._$4520 = null;
        this._$4521 = null;
        this._$4522 = null;
    }
    
    public void dzoomCanceled() {
        if (this.dZoomBtnPush) {
            this.dZoomBtnPush = false;
            this._$4305.setCursor(this._$1265);
            this.setCursor(this._$1265);
            this._$4305.curDCur = this._$1265;
        }
    }
    
    public void setAllBackground(final Color color) {
        this.setBackground(color);
        this._$1304.setBackground(color);
        this._$1307.setBackground(color);
        this._$1323.setBackground(color);
        this._$4523.setBackground(color);
        this._$4524.setBackground(color);
    }
    
    public void setAllForeground(final Color color) {
        this.setForeground(color);
        this._$1304.setForeground(color);
        this._$1307.setForeground(color);
        this._$1323.setForeground(color);
        this._$4523.setForeground(color);
        this._$4524.setForeground(color);
        this._$1306.setForeground(Color.black);
        this._$1308.setForeground(Color.black);
        this._$1332.setForeground(Color.black);
        this._$1312.setForeground(Color.black);
    }
    
    private void _$1353(final int n) {
        this._$1304.setText("Frame rate:");
        this._$1304.setBounds(new Rectangle(2, this._$4515 + this._$4529, 153, 13));
        this._$1304.addMouseListener(new MouseAdapter() {
            public void mousePressed(final MouseEvent mouseEvent) {
                ControlPanel.this.dzoomCanceled();
            }
        });
        this._$1306.setBounds(new Rectangle(2, this._$4515 + this._$4529 + 13 + 1, 153, 22));
        this._$1306.addItemListener(new ItemListener() {
            public void itemStateChanged(final ItemEvent itemEvent) {
                ControlPanel.this._$1389(itemEvent);
            }
        });
        this._$1306.addMouseListener(new MouseAdapter() {
            public void mousePressed(final MouseEvent mouseEvent) {
                ControlPanel.this.dzoomCanceled();
            }
        });
        if (n == 30) {
            this._$1306.addItem("30 fps");
        }
        if (n >= 25) {
            this._$1306.addItem("25 fps");
        }
        if (n >= 20) {
            this._$1306.addItem("20 fps");
        }
        if (n >= 15) {
            this._$1306.addItem("15 fps");
        }
        if (n >= 10) {
            this._$1306.addItem("10 fps");
        }
        if (n >= 8) {
            this._$1306.addItem("8 fps");
        }
        if (n >= 6) {
            this._$1306.addItem("6 fps");
        }
        if (n >= 5) {
            this._$1306.addItem("5 fps");
        }
        if (n >= 4) {
            this._$1306.addItem("4 fps");
        }
        if (n >= 3) {
            this._$1306.addItem("3 fps");
        }
        if (n >= 2) {
            this._$1306.addItem("2 fps");
        }
        this._$1306.addItem("1 fps");
        this.add(this._$1304, null);
        this.add(this._$1306, null);
    }
    
    private void _$1355() {
        this._$1307.setText("View size:");
        this._$1307.setBounds(new Rectangle(2, this._$4515 + this._$4529, 153, 13));
        this._$1307.addMouseListener(new MouseAdapter() {
            public void mousePressed(final MouseEvent mouseEvent) {
                ControlPanel.this.dzoomCanceled();
            }
        });
        this._$1308.setBounds(new Rectangle(2, this._$4515 + this._$4529 + 13 + 1, 153, 22));
        this._$4305.logger.print("VSize : " + String.valueOf(this._$4515 + this._$4529));
        this._$1308.addItemListener(new ItemListener() {
            public void itemStateChanged(final ItemEvent itemEvent) {
                ControlPanel.this._$4534(itemEvent);
            }
        });
        this._$1308.addMouseListener(new MouseAdapter() {
            public void mousePressed(final MouseEvent mouseEvent) {
                ControlPanel.this.dzoomCanceled();
            }
        });
        this._$1308.addItem("AUTO");
        this._$1308.addItem("640x480");
        this._$1308.addItem("320x240");
        this._$1308.addItem("160x120");
        this.add(this._$1307, null);
        this.add(this._$1308, null);
    }
    
    private void _$4530() {
        this._$1323.setText("Digital zoom");
        this._$1323.setBounds(new Rectangle(44, this._$4515 + this._$4529 + 9, 118, 13));
        this._$1323.addMouseListener(new MouseAdapter() {
            public void mousePressed(final MouseEvent mouseEvent) {
                ControlPanel.this.dzoomCanceled();
            }
        });
        this._$1313 = this._$4305.getImage(this._$4305.getCodeBase(), "zoom_plus_highlight.gif");
        this._$1320 = this._$4305.getImage(this._$4305.getCodeBase(), "zoom_plus.gif");
        this._$1321 = this._$4305.getImage(this._$4305.getCodeBase(), "zoom_minus_highlight.gif");
        this._$1322 = this._$4305.getImage(this._$4305.getCodeBase(), "zoom_minus.gif");
        (this.IC[0] = new ImageButton(this._$1320, this._$1313, this._$1322, this._$1321, this.color_back)).setBounds(new Rectangle(0, this._$4515 + this._$4529, 38, 31));
        this._$4305.logger.print("DigitalZoom : " + String.valueOf(this._$4515 + this._$4529));
        this.IC[0].addMouseListener(new MouseAdapter() {
            public void mouseReleased(final MouseEvent mouseEvent) {
                if ((mouseEvent.getModifiers() & 0x10) != 0x0) {
                    ControlPanel.this._$4535(mouseEvent);
                }
            }
            
            public void mouseEntered(final MouseEvent mouseEvent) {
                if (ControlPanel.this._$4305.curDCur.equals(ControlPanel.this._$4305.curD)) {
                    ControlPanel.this.setCursor(ControlPanel.this._$4305.curH);
                }
                else {
                    ControlPanel.this.setCursor(ControlPanel.this._$4305.curDCur);
                }
                if (ControlPanel.this._$4305.controler != null) {
                    ControlPanel.this._$4305.controler.toFront();
                }
                ControlPanel.this.IC[0].ImCanvas_mouseDsp(true);
            }
            
            public void mouseExited(final MouseEvent mouseEvent) {
                ControlPanel.this.IC[0].ImCanvas_mouseDsp(false);
            }
        });
        this.add(this._$1323, null);
        this.add(this.IC[0], null);
    }
    
    private void _$1359() {
        this._$4523.setText("Control");
        this._$4523.setBounds(new Rectangle(44, this._$4515 + this._$4529 + 9, 118, 13));
        this._$4519 = this._$4305.getImage(this._$4305.getCodeBase(), "control1.gif");
        this._$4520 = this._$4305.getImage(this._$4305.getCodeBase(), "control1_highlight.gif");
        this._$4521 = this._$4305.getImage(this._$4305.getCodeBase(), "control2.gif");
        this._$4522 = this._$4305.getImage(this._$4305.getCodeBase(), "control2_highlight.gif");
        this._$1328 = this._$4305.getImage(this._$4305.getCodeBase(), "wait.gif");
        this._$4305.logger.print("Control start");
        this.IC[2] = new ImageButton(this._$4519, this._$4520, this._$4521, this._$4522, this.color_back);
        this._$4305.logger.print("Control end");
        this.IC[2].setBounds(new Rectangle(0, this._$4515 + this._$4529, 38, 31));
        this.IC[2].addMouseListener(new MouseAdapter() {
            public void mouseReleased(final MouseEvent mouseEvent) {
                if ((mouseEvent.getModifiers() & 0x10) != 0x0) {
                    ControlPanel.this._$4537(mouseEvent);
                }
            }
            
            public void mousePressed(final MouseEvent mouseEvent) {
                ControlPanel.this.dzoomCanceled();
            }
            
            public void mouseEntered(final MouseEvent mouseEvent) {
                ControlPanel.this._$1330 = true;
                if (ControlPanel.this._$4305.curDCur.equals(ControlPanel.this._$4305.curD)) {
                    ControlPanel.this.setCursor(ControlPanel.this._$4305.curH);
                }
                else {
                    ControlPanel.this.setCursor(ControlPanel.this._$4305.curDCur);
                }
                if (ControlPanel.this._$4305.controler != null) {
                    ControlPanel.this._$4305.controler.toFront();
                }
                ControlPanel.this.IC[2].ImCanvas_mouseDsp(true);
            }
            
            public void mouseExited(final MouseEvent mouseEvent) {
                ControlPanel.this._$1330 = false;
                ControlPanel.this.IC[2].ImCanvas_mouseDsp(false);
            }
        });
        this.add(this._$4523, null);
        this.add(this.IC[2], null);
    }
    
    private void _$1360() {
        this._$4524.setText("Preset position:");
        this._$4524.setBounds(new Rectangle(2, this._$4515 + this._$4529, 153, 13));
        this._$4524.addMouseListener(new MouseAdapter() {
            public void mousePressed(final MouseEvent mouseEvent) {
                ControlPanel.this.dzoomCanceled();
            }
        });
        this._$1332.setBounds(new Rectangle(2, this._$4515 + this._$4529 + 13 + 1, 153, 22));
        this._$1332.addItemListener(new ItemListener() {
            public void itemStateChanged(final ItemEvent itemEvent) {
                ControlPanel.this._$1398(itemEvent);
            }
        });
        this._$1332.addMouseListener(new MouseAdapter() {
            public void mousePressed(final MouseEvent mouseEvent) {
                ControlPanel.this.dzoomCanceled();
            }
        });
        this._$4305.camera.loadPresetPosition();
        this._$1332.addItem("");
        for (int i = 0; i < this._$4305.camera.getPresetPositionNumber(); ++i) {
            this._$1332.addItem(this._$4305.camera.getPresetPositionName(i));
        }
        if (this._$4305.vModeDetail.isPresetPositionIndipendent()) {
            this._$4524.setVisible(true);
            this._$1332.setVisible(true);
        }
        else {
            this._$4524.setVisible(false);
            this._$1332.setVisible(false);
        }
        if (this._$4305.camera.getPresetPositionNumber() <= 0) {
            this._$4524.setVisible(false);
            this._$1332.setVisible(false);
        }
        this.add(this._$4524, null);
        this.add(this._$1332, null);
    }
    
    private void _$1363() {
        this._$1402();
        if (this._$1404(this._$1314, this._$1315, this._$1316, this._$1317, this._$1318, this._$1319) == 0) {
            return;
        }
        this._$1312.setBounds(new Rectangle(40, this._$4515 + this._$4529 + 4, 113, 22));
        this._$1312.addMouseListener(new MouseAdapter() {
            public void mousePressed(final MouseEvent mouseEvent) {
                ControlPanel.this.dzoomCanceled();
            }
        });
        this._$1312.addItemListener(new ItemListener() {
            public void itemStateChanged(final ItemEvent itemEvent) {
                ControlPanel.this._$1405(itemEvent);
            }
        });
        this._$1310 = this._$4305.getImage(this._$4305.getCodeBase(), "trigger_highlight.gif");
        this._$1311 = this._$4305.getImage(this._$4305.getCodeBase(), "trigger_off.gif");
        (this.IC[1] = new ImageButton(this._$1311, this._$1310, this.color_back)).setBounds(new Rectangle(0, this._$4515 + this._$4529, 38, 31));
        this.IC[1].addMouseListener(new MouseAdapter() {
            public void mouseReleased(final MouseEvent mouseEvent) {
                ControlPanel.this._$4538(mouseEvent);
            }
            
            public void mousePressed(final MouseEvent mouseEvent) {
                ControlPanel.this.dzoomCanceled();
            }
            
            public void mouseEntered(final MouseEvent mouseEvent) {
                if (ControlPanel.this._$4305.curDCur.equals(ControlPanel.this._$4305.curD)) {
                    ControlPanel.this.setCursor(ControlPanel.this._$4305.curH);
                }
                else {
                    ControlPanel.this.setCursor(ControlPanel.this._$4305.curDCur);
                }
                if (ControlPanel.this._$4305.controler != null) {
                    ControlPanel.this._$4305.controler.toFront();
                }
                ControlPanel.this.IC[1].ImCanvas_mouseDsp(true);
            }
            
            public void mouseExited(final MouseEvent mouseEvent) {
                ControlPanel.this.IC[1].ImCanvas_mouseDsp(false);
            }
        });
        this.add(this._$1312, null);
        this.add(this.IC[1], null);
    }
    
    private void _$1402() {
        final String $1408 = this._$1408("inq=smtp&inq=ftpclient&inq=imagememory");
        final int n = $1408.indexOf("SmManualFunc=") + 13;
        if (n > 12 && $1408.substring(n, n + 2).equals("on")) {
            this._$1315 = "on";
        }
        else {
            this._$1315 = "off";
        }
        final int n2 = $1408.indexOf("FcManualFunc=") + 13;
        if (n2 > 12 && $1408.substring(n2, n2 + 2).equals("on")) {
            this._$1314 = "on";
        }
        else {
            this._$1314 = "off";
        }
        final int n3 = $1408.indexOf("ImManualFunc=") + 13;
        if (n3 > 12 && $1408.substring(n3, n3 + 2).equals("on")) {
            this._$1316 = "on";
        }
        else {
            this._$1316 = "off";
        }
        final String $1409 = this._$1408("inq=alarmout&inq=camera");
        final int n4 = $1409.indexOf("Ao1ManualFunc=") + 14;
        if (n4 > 13 && $1409.substring(n4, n4 + 2).equals("on")) {
            this._$1317 = "on";
        }
        else {
            this._$1317 = "off";
        }
        final int n5 = $1409.indexOf("Ao2ManualFunc=") + 14;
        if (n5 > 13 && $1409.substring(n5, n5 + 2).equals("on")) {
            this._$1318 = "on";
        }
        else {
            this._$1318 = "off";
        }
        final int n6 = $1409.indexOf("DnManualFunc=") + 13;
        if (n6 > 12 && $1409.substring(n6, n6 + 2).equals("on")) {
            this._$1319 = "on";
        }
        else {
            this._$1319 = "off";
        }
    }
    
    private String _$1408(final String s) {
        final byte[] array = new byte[5000];
        int n = 0;
        if (s.length() != 0) {
            try {
                final URLConnection openConnection = new URL("http://" + this._$1013 + "/command/inquiry.cgi").openConnection();
                openConnection.setDoOutput(true);
                if (!this._$4305.uCode.equals("")) {
                    openConnection.setRequestProperty("Authorization", "Basic " + this._$4305.uCode);
                }
                final PrintStream printStream = new PrintStream(openConnection.getOutputStream());
                printStream.print(s);
                printStream.close();
                DataInputStream dataInputStream;
                int read;
                for (dataInputStream = new DataInputStream(openConnection.getInputStream()); (read = dataInputStream.read(array, n, 256)) > 0; n += read) {}
                dataInputStream.close();
            }
            catch (IOException ex) {
                System.out.println("Inq Error2!!");
            }
        }
        return new String(array);
    }
    
    private void _$4541() {
        if (this._$4525.length() != 0) {
            try {
                final URLConnection openConnection = new URL("http://" + this._$1013 + this._$1338).openConnection();
                if (!this._$4305.uCode.equals("")) {
                    openConnection.setRequestProperty("Authorization", "Basic " + this._$4305.uCode);
                }
                openConnection.setDoOutput(true);
                final PrintStream printStream = new PrintStream(openConnection.getOutputStream());
                printStream.print(this._$4525);
                printStream.close();
                new DataInputStream(openConnection.getInputStream()).close();
            }
            catch (IOException ex) {
                System.out.println("Socket Error2!!");
            }
        }
    }
    
    private int _$1404(final String s, final String s2, final String s3, final String s4, final String s5, final String s6) {
        int n = 0;
        if (s2.equals("on")) {
            this._$1312.addItem("e-Mail");
            if (this._$4525 == "") {
                this._$4525 = "Trigger=smtp";
            }
            ++n;
        }
        if (s.equals("on")) {
            this._$1312.addItem("FTP");
            if (this._$4525 == "") {
                this._$4525 = "Trigger=ftp";
            }
            ++n;
        }
        if (s3.equals("on")) {
            this._$1312.addItem("Image memory");
            if (this._$4525 == "") {
                this._$4525 = "Trigger=memory";
            }
            ++n;
        }
        if (s4.equals("on")) {
            this._$1312.addItem("Alarm output1");
            if (this._$4525 == "") {
                this._$4525 = "Trigger=alarmout1";
            }
            ++n;
        }
        if (s5.equals("on")) {
            this._$1312.addItem("Alarm output2");
            if (this._$4525 == "") {
                this._$4525 = "Trigger=alarmout2";
            }
            ++n;
        }
        if (s6.equals("on")) {
            this._$1312.addItem("Day/Night");
            if (this._$4525 == "") {
                this._$4525 = "Trigger=daynight";
            }
            ++n;
        }
        return n;
    }
    
    private void _$1405(final ItemEvent itemEvent) {
        if (this._$1312.getSelectedItem().equals("FTP")) {
            this._$4525 = "Trigger=ftp";
        }
        else if (this._$1312.getSelectedItem().equals("e-Mail")) {
            this._$4525 = "Trigger=smtp";
        }
        else if (this._$1312.getSelectedItem().equals("Image memory")) {
            this._$4525 = "Trigger=memory";
        }
        else if (this._$1312.getSelectedItem().equals("Alarm output1")) {
            this._$4525 = "Trigger=alarmout1";
        }
        else if (this._$1312.getSelectedItem().equals("Alarm output2")) {
            this._$4525 = "Trigger=alarmout2";
        }
        else if (this._$1312.getSelectedItem().equals("Day/Night")) {
            this._$4525 = "Trigger=daynight";
        }
        else {
            this._$4525 = "";
        }
    }
    
    private void _$4538(final MouseEvent mouseEvent) {
        this._$4541();
    }
    
    private void _$4535(final MouseEvent mouseEvent) {
        if (!this._$4305.imageThread.digitalZoom && !this.dZoomBtnPush) {
            this._$4305.setCursor(this._$1336);
            this._$4305.curDCur = this._$1336;
            this.dZoomBtnPush = true;
        }
        else {
            this._$4305.imageThread.dZoomOut();
            this._$4305.setCursor(this._$1265);
            this._$4305.curDCur = this._$1265;
            this.IC[0].imageSelect(false);
            this.dZoomBtnPush = false;
        }
        this.IC[0].ImCanvas_mouseDsp(true);
    }
    
    public void dZoomButton_DispChange(final boolean b) {
        if (this.IC[0] != null) {
            this.IC[0].imageSelect(b);
            this.IC[0].ImCanvas_mouseDsp(false);
        }
    }
    
    private void _$4537(final MouseEvent mouseEvent) {
        this.IC[2].ImCanvas_mouseDsp(this._$1330);
        this._$4305.imageThread.panoramaView.enable();
        if (!this.controlBtnPush) {
            switch (this._$4305.camera.getControl()) {
                case -2: {
                    this.IC[2].ImCanvas_mouseDsp(false);
                    final AlarmDialog alarmDialog = new AlarmDialog(this);
                    alarmDialog.setMessage("Change your browser setting to accept cookies.");
                    alarmDialog.show(100, 100);
                    break;
                }
                case -1: {
                    this.IC[2].ImCanvas_mouseDsp(false);
                    final AlarmDialog alarmDialog2 = new AlarmDialog(this);
                    alarmDialog2.setMessage("After a while, please access again.");
                    alarmDialog2.show(100, 100);
                    break;
                }
                case 0: {
                    this._$1345.setTime(0L);
                    this._$4528.setTime(new Long(Long.MAX_VALUE));
                    break;
                }
                case 1: {
                    final long waitTimeToControl = this._$4305.camera.getWaitTimeToControl();
                    final long controlTime = this._$4305.camera.getControlTime();
                    this._$1345.setTime(new Date().getTime() + waitTimeToControl * 1000L);
                    this._$4528.setTime(this._$1345.getTime() + waitTimeToControl + controlTime * 1000L);
                    this.IC[2].setImage(this._$1328, this._$1328, this._$1328);
                    this.IC[2].repaint();
                    break;
                }
            }
        }
        else {
            if (this._$4305.controler == null) {
                (this._$4305.controler = new Controler(this._$4305)).setLocation(5, 28);
            }
            this._$4305.controler.setVisible(true);
        }
    }
    
    public void controlStart() {
        if (new Date().after(this._$1345)) {
            this.IC[2].imageSelect(true);
            this.controlBtnPush = true;
            this.IC[2].ImCanvas_mouseDsp(this._$1330);
            this._$4305.imageThread.pictFrame.repaint();
            if (this._$4305.camera.getPresetPositionNumber() > 0 && this._$4305.vModeDetail.isPresetPositionActive()) {
                this._$4524.setVisible(true);
                this._$1332.setVisible(true);
            }
            this.IC[2].setVisible(true);
            this._$4523.setText("Control");
            this._$1343 = true;
            this._$1345.setTime(new Long(Long.MAX_VALUE));
        }
        else if (this._$1345.getTime() < new Long(Long.MAX_VALUE)) {
            this._$4523.setText(String.valueOf((this._$1345.getTime() - new Date().getTime()) / 1000L));
        }
    }
    
    public void controlEnd() {
        if (this._$4528.getTime() < new Long(Long.MAX_VALUE)) {
            if (new Date().after(this._$4528)) {
                this.controlBtnPush = false;
                this._$4524.setVisible(false);
                this._$1332.setVisible(false);
                this._$4523.setText("Control");
                this._$1343 = true;
                this.controlButton_DispChange(false);
                this._$4528.setTime(new Long(Long.MAX_VALUE));
                this._$4305.imageThread.pictFrame.repaint();
                if (this._$4305.controler != null) {
                    this._$4305.controler.setVisible(false);
                }
                this._$4305.imageThread.pictFrame.repaint();
            }
            else if (this.controlBtnPush) {
                this._$4523.setText(String.valueOf((this._$4528.getTime() - new Date().getTime()) / 1000L));
            }
        }
    }
    
    public void controlButton_DispChange(final boolean b) {
        if (this.IC[2] != null) {
            this.IC[2].imageSelect(b);
            this.IC[2].ImCanvas_mouseDsp(this._$1330);
        }
    }
    
    private void _$1389(final ItemEvent itemEvent) {
        this._$4305.rateControl(this._$1306.getSelectedItem());
    }
    
    private void _$4534(final ItemEvent itemEvent) {
        this._$4305.imageThread.viewSizeChanged(this._$1308.getSelectedIndex());
    }
    
    private void _$1398(final ItemEvent itemEvent) {
        this._$4305.camera.setPresetPosition(this._$4305.camera.getPresetPositionNo(this._$1332.getSelectedIndex() - 1));
    }
    
    public void setFrameRate(int n) {
        if (n == 7) {
            n = 6;
        }
        else if (n >= 8 && n <= 9) {
            n = 8;
        }
        else if (n >= 10 && n <= 14) {
            n = 10;
        }
        else if (n >= 15 && n <= 19) {
            n = 15;
        }
        else if (n >= 20 && n <= 24) {
            n = 20;
        }
        else if (n >= 25 && n <= 29) {
            n = 25;
        }
        final String string = n + " fps";
        if (this._$1306.getItemCount() != 0) {
            this._$1306.select(string);
            if (!this._$1306.getSelectedItem().equals(string)) {
                this._$1306.select(0);
            }
        }
    }
    
    public void setViewSize(final int n) {
        if (this._$1308.getItemCount() != 0) {
            switch (n) {
                case 0: {
                    this._$1308.select("AUTO");
                    break;
                }
                case 1: {
                    this._$1308.select("640x480");
                    break;
                }
                case 2: {
                    this._$1308.select("320x240");
                    break;
                }
                case 3: {
                    this._$1308.select("160x120");
                    break;
                }
            }
        }
    }
    
    public void setPresetPosition(final int n) {
        if (this._$1332.getItemCount() != 0) {
            switch (n) {
                case 0: {
                    this._$1332.select("AUTO");
                    break;
                }
                case 1: {
                    this._$1332.select("640x480");
                    break;
                }
                case 2: {
                    this._$1332.select("320x240");
                    break;
                }
                case 3: {
                    this._$1332.select("160x120");
                    break;
                }
            }
        }
    }
    
    public void trigger_send(final String s) {
        this._$4305.logger.print("Trigger send " + s);
        if (s.equals("PRM_TRIGGER_FTP")) {
            if (this._$1314.equals("on")) {
                this._$1312.select("FTP");
                this._$4525 = "Trigger=ftp";
            }
        }
        else if (s.equals("PRM_TRIGGER_SMTP")) {
            if (this._$1315.equals("on")) {
                this._$1312.select("e-Mail");
                this._$4525 = "Trigger=smtp";
            }
        }
        else if (s.equals("PRM_TRIGGER_MEMORY")) {
            if (this._$1316.equals("on")) {
                this._$1312.select("Image memory");
                this._$4525 = "Trigger=memory";
            }
        }
        else if (s.equals("PRM_TRIGGER_ALARM1")) {
            if (this._$1317.equals("on")) {
                this._$1312.select("Alarm output1");
                this._$4525 = "Trigger=alarmout1";
            }
        }
        else if (s.equals("PRM_TRIGGER_ALARM2")) {
            if (this._$1318.equals("on")) {
                this._$1312.select("Alarm output2");
                this._$4525 = "Trigger=alarmout2";
            }
        }
        else if (s.equals("PRM_TRIGGER_DAYNIGHT") && this._$1319.equals("on")) {
            this._$1312.select("Day/Night");
            this._$4525 = "Trigger=daynight";
        }
        this._$4541();
    }
    
    public void reloadAllIcon(final Color color) {
        for (int i = 0; i < this.IC.length; ++i) {
            if (this.IC[i] != null) {
                this.IC[i].reloadIcon(color);
            }
        }
    }
    
    public void setFRateVisible(final boolean b) {
        if (this._$1304 != null && this._$1306 != null) {
            this._$1304.setVisible(b);
            this._$1306.setVisible(b);
        }
    }
    
    public void setVSizeVisible(final boolean b) {
        if (this._$1307 != null && this._$1308 != null) {
            this._$1307.setVisible(b);
            this._$1308.setVisible(b);
        }
    }
    
    public void setDZoomVisible(final boolean b) {
        if (this.IC[0] != null && this._$1323 != null) {
            this.IC[0].setVisible(b);
            this._$1323.setVisible(b);
        }
    }
    
    public void setControlVisible(final boolean b) {
        if (this.IC[2] != null && this._$4523 != null) {
            this.IC[2].setVisible(b);
            this._$4523.setVisible(b);
        }
    }
    
    public void setPresetPositionVisible(final boolean b) {
        if (this._$4524 != null && this._$1332 != null && this._$4305.camera.getPresetPositionNumber() > 0) {
            this._$4524.setVisible(b);
            this._$1332.setVisible(b);
        }
    }
    
    public void setTriggerVisible(final boolean b) {
        if (this.IC[1] != null && this._$1312 != null) {
            this.IC[1].setVisible(b);
            this._$1312.setVisible(b);
        }
    }
    
    public void setVolumeVisible(final boolean visible) {
        if (this._$4305.volumeGUI != null) {
            this._$4305.volumeGUI.setVisible(visible);
        }
    }
    
    public void setDateVisible(final boolean b) {
        if (this._$4305.imageThread.dateLabel != null) {
            this._$4305.imageThread.dateLabel.setVisible(b);
            this._$4305.imageThread.dispDateFlg = b;
        }
    }
    
    public boolean getControlStatusChanged() {
        return this._$1343;
    }
    
    public void resetControlStatusChanged() {
        this._$1343 = false;
    }
    
    public void setSelectedPresetPosition(final int n) {
        this._$1332.select(n);
    }
}
