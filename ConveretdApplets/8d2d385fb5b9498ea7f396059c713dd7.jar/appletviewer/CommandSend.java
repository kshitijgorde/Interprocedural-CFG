// 
// Decompiled by Procyon v0.5.30
// 

package appletviewer;

import java.net.URLConnection;
import java.io.IOException;
import java.io.DataInputStream;
import java.io.PrintStream;
import java.net.URL;
import java.awt.Component;
import java.awt.event.KeyListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.WindowListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.MouseListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseAdapter;
import java.awt.Rectangle;
import java.awt.Font;
import java.awt.LayoutManager;
import java.awt.Dimension;
import java.awt.Color;
import java.awt.TextField;
import java.awt.Button;
import java.awt.Label;
import java.awt.Frame;

public class CommandSend extends Thread
{
    private String host;
    private String sCgi;
    private String sStr;
    private Frame pFrame;
    private Label label1;
    private Button button1;
    private boolean popUpTxt;
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
    
    public CommandSend(final String h) {
        this.label1 = new Label();
        this.button1 = new Button();
        this.popUpTxt = false;
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
        (this.pFrame = new Frame("SNC-RZ30")).setBackground(Color.lightGray);
        this.pFrame.setSize(new Dimension(498, 129));
        this.pFrame.setResizable(false);
        this.pFrame.setLayout(null);
        this.label1.setAlignment(1);
        this.label1.setFont(new Font("Monospaced", 0, 13));
        this.label1.setBounds(new Rectangle(11, 38, 476, 22));
        this.label1.setText("Protected Object. This object is protected.");
        this.button1.setLabel("OK");
        this.button1.setBounds(new Rectangle(197, 74, 105, 25));
        this.button1.addMouseListener(new MouseAdapter() {
            public void mouseClicked(final MouseEvent e) {
                CommandSend.this.button1_mouseClicked(e);
            }
        });
        (this.NPPanel = new Frame("Enter Network Password")).setLayout(null);
        this.NPPanel.setBackground(Color.lightGray);
        this.NPPanel.setSize(new Dimension(450, 250));
        this.NPPanel.setResizable(false);
        this.NPPanel.addWindowListener(new WindowAdapter() {
            public void windowClosing(final WindowEvent e) {
                CommandSend.this.buttonCL_Clicked();
            }
        });
        this.buttonOK.setLabel("OK");
        this.buttonOK.setBounds(new Rectangle(260, 200, 75, 25));
        this.buttonOK.addKeyListener(new KeyAdapter() {
            public void keyPressed(final KeyEvent e) {
                if (e.getKeyCode() == 10) {
                    CommandSend.this.buttonOK_Clicked();
                }
            }
        });
        this.buttonOK.addMouseListener(new MouseAdapter() {
            public void mouseClicked(final MouseEvent e) {
                CommandSend.this.buttonOK_Clicked();
            }
        });
        this.buttonCL.setLabel("Cancel");
        this.buttonCL.setBounds(new Rectangle(345, 200, 75, 25));
        this.buttonCL.addKeyListener(new KeyAdapter() {
            public void keyPressed(final KeyEvent e) {
                CommandSend.this.buttonCL_Clicked();
            }
        });
        this.buttonCL.addMouseListener(new MouseAdapter() {
            public void mouseClicked(final MouseEvent e) {
                CommandSend.this.buttonCL_Clicked();
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
        this.host = h;
        this.sCgi = "";
        this.sStr = "";
    }
    
    public void setCommand(final String cgi, final String cmd) {
        this.sCgi = cgi;
        this.sStr = cmd;
    }
    
    public void setAuthorize(final String auth) {
        this.uCode = auth;
    }
    
    public void run() {
        final byte[] inData = new byte[2048];
        final int inSize = 0;
        boolean firstFlg = true;
    Label_0051_Outer:
        while (true) {
            try {
                synchronized (this) {
                    this.wait();
                }
            }
            catch (InterruptedException ex) {}
            if (this.sStr.length() != 0) {
                while (true) {
                    try {
                        while (true) {
                            if (firstFlg || !this.uCode.equals("")) {
                                final URL eviURL = new URL(String.valueOf(String.valueOf(new StringBuffer("http://").append(this.host).append(this.sCgi))));
                                final URLConnection connectionSR = eviURL.openConnection();
                                if (!this.uCode.equals("")) {
                                    connectionSR.setRequestProperty("Authorization", "Basic ".concat(String.valueOf(String.valueOf(this.uCode))));
                                }
                                connectionSR.setUseCaches(false);
                                connectionSR.setDoOutput(true);
                                final PrintStream s_out = new PrintStream(connectionSR.getOutputStream());
                                s_out.print(this.sStr);
                                s_out.close();
                                if (this.dataHeaderChk(connectionSR)) {
                                    final DataInputStream s_in = new DataInputStream(connectionSR.getInputStream());
                                    s_in.close();
                                    System.out.println("COMMAND: ".concat(String.valueOf(String.valueOf(this.sStr))));
                                    this.sCgi = "";
                                    this.sStr = "";
                                    break;
                                }
                                this.uCode = "";
                                if (this.uErr > 3) {
                                    continue Label_0051_Outer;
                                }
                                this.showAuthorizeFreame(connectionSR);
                                firstFlg = false;
                            }
                            else {
                                if (this.uErr > 3) {
                                    this.pFrame.show();
                                    break;
                                }
                                try {
                                    Thread.sleep(500L);
                                }
                                catch (InterruptedException ex2) {}
                            }
                        }
                    }
                    catch (IOException Ex) {
                        System.out.println("Command Error!!");
                        continue;
                    }
                    break;
                }
            }
        }
    }
    
    private boolean dataHeaderChk(final URLConnection connect) {
        try {
            final String header = connect.getHeaderField(0);
            if (header.indexOf("401") > 0) {
                ++this.uErr;
                return false;
            }
            return true;
        }
        catch (Exception e) {
            System.out.println("HTTP header check error!!");
            return false;
        }
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
    
    private void button1_mouseClicked(final MouseEvent e) {
        this.pFrame.hide();
    }
}
