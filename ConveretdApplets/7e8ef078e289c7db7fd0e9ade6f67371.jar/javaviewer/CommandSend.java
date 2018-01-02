// 
// Decompiled by Procyon v0.5.30
// 

package javaviewer;

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
    private String _$1300;
    private String _$5820;
    private String _$5824;
    public boolean ready;
    private Frame _$168079;
    private Label _$168085;
    private Button _$168091;
    private boolean _$168098;
    private String _$2649;
    private int _$4250;
    private Frame _$4259;
    private Button _$4272;
    private Button _$4280;
    private Label _$4288;
    private Label _$4296;
    private Label _$4305;
    private Label _$4315;
    private Label _$4322;
    private Label _$4329;
    private Label _$4339;
    private TextField _$4359;
    private TextField _$4370;
    private char[] _$4381;
    private char[] _$4390;
    private char[] _$4399;
    private int _$4408;
    private int _$4417;
    private char[] _$4426;
    
    public CommandSend(final String $1300) {
        this.ready = false;
        this._$168085 = new Label();
        this._$168091 = new Button();
        this._$168098 = false;
        this._$2649 = "";
        this._$4250 = 0;
        this._$4272 = new Button();
        this._$4280 = new Button();
        this._$4288 = new Label();
        this._$4296 = new Label();
        this._$4305 = new Label();
        this._$4315 = new Label();
        this._$4322 = new Label();
        this._$4329 = new Label();
        this._$4339 = new Label();
        this._$4359 = new TextField();
        this._$4370 = new TextField();
        this._$4426 = new char[] { 'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z', 'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z', '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', '+', '/' };
        (this._$168079 = new Frame("Sony Network Camera SNC-RZ25")).setBackground(Color.lightGray);
        this._$168079.setSize(new Dimension(498, 129));
        this._$168079.setResizable(false);
        this._$168079.setLayout(null);
        this._$168085.setAlignment(1);
        this._$168085.setFont(new Font("Monospaced", 0, 13));
        this._$168085.setBounds(new Rectangle(11, 38, 476, 22));
        this._$168085.setText("Protected Object. This object is protected.");
        this._$168091.setLabel("OK");
        this._$168091.setBounds(new Rectangle(197, 74, 105, 25));
        this._$168091.addMouseListener(new MouseAdapter() {
            public void mouseClicked(final MouseEvent mouseEvent) {
                CommandSend.this._$168131(mouseEvent);
            }
        });
        (this._$4259 = new Frame("Enter Network Password")).setLayout(null);
        this._$4259.setBackground(Color.lightGray);
        this._$4259.setSize(new Dimension(450, 250));
        this._$4259.setResizable(false);
        this._$4259.addWindowListener(new WindowAdapter() {
            public void windowClosing(final WindowEvent windowEvent) {
                CommandSend.this._$5087();
            }
        });
        this._$4272.setLabel("OK");
        this._$4272.setBounds(new Rectangle(260, 200, 75, 25));
        this._$4272.addKeyListener(new KeyAdapter() {
            public void keyPressed(final KeyEvent keyEvent) {
                if (keyEvent.getKeyCode() == 10) {
                    CommandSend.this._$5172();
                }
            }
        });
        this._$4272.addMouseListener(new MouseAdapter() {
            public void mouseClicked(final MouseEvent mouseEvent) {
                CommandSend.this._$5172();
            }
        });
        this._$4280.setLabel("Cancel");
        this._$4280.setBounds(new Rectangle(345, 200, 75, 25));
        this._$4280.addKeyListener(new KeyAdapter() {
            public void keyPressed(final KeyEvent keyEvent) {
                CommandSend.this._$5087();
            }
        });
        this._$4280.addMouseListener(new MouseAdapter() {
            public void mouseClicked(final MouseEvent mouseEvent) {
                CommandSend.this._$5087();
            }
        });
        this._$4288.setText("Please type your user name and password.");
        this._$4288.setBounds(new Rectangle(30, 35, 250, 12));
        this._$4296.setText("Site:");
        this._$4296.setBounds(new Rectangle(30, 70, 90, 12));
        this._$4305.setText("Realm:");
        this._$4305.setBounds(new Rectangle(30, 100, 90, 12));
        this._$4315.setText("User Name:");
        this._$4315.setBounds(new Rectangle(30, 140, 90, 12));
        this._$4322.setText("Password:");
        this._$4322.setBounds(new Rectangle(30, 170, 90, 12));
        this._$4329.setBounds(new Rectangle(160, 65, 260, 20));
        this._$4339.setBounds(new Rectangle(160, 95, 260, 20));
        this._$4359.setBounds(new Rectangle(160, 135, 260, 20));
        this._$4370.setEchoChar('*');
        this._$4370.setBounds(new Rectangle(160, 165, 260, 20));
        this._$168079.add(this._$168085, null);
        this._$168079.add(this._$168091, null);
        this._$168079.setLocation(263, 319);
        this._$4259.add(this._$4288, null);
        this._$4259.add(this._$4296, null);
        this._$4259.add(this._$4329, null);
        this._$4259.add(this._$4305, null);
        this._$4259.add(this._$4315, null);
        this._$4259.add(this._$4359, null);
        this._$4259.add(this._$4370, null);
        this._$4259.add(this._$4272, null);
        this._$4259.add(this._$4280, null);
        this._$4259.add(this._$4322, null);
        this._$4259.add(this._$4339, null);
        this._$4259.setLocation(263, 319);
        this._$1300 = $1300;
        this._$5820 = "";
        this._$5824 = "";
    }
    
    public void setCommand(final String $5820, final String $5821) {
        this._$5820 = $5820;
        this._$5824 = $5821;
    }
    
    public void setAuthorize(final String $2649) {
        this._$2649 = $2649;
    }
    
    public void run() {
        final byte[] array = new byte[2048];
        int n = 1;
    Label_0058_Outer:
        while (true) {
            this.ready = true;
            try {
                synchronized (this) {
                    this.wait();
                }
            }
            catch (InterruptedException ex) {}
            this.ready = false;
            if (this._$5824.length() != 0) {
                while (true) {
                    try {
                        while (true) {
                            if (n == 1 || !this._$2649.equals("")) {
                                final URLConnection openConnection = new URL(String.valueOf(String.valueOf(new StringBuffer("http://").append(this._$1300).append(this._$5820)))).openConnection();
                                if (!this._$2649.equals("")) {
                                    openConnection.setRequestProperty("Authorization", "Basic ".concat(String.valueOf(String.valueOf(this._$2649))));
                                }
                                openConnection.setUseCaches(false);
                                openConnection.setDoOutput(true);
                                final PrintStream printStream = new PrintStream(openConnection.getOutputStream());
                                printStream.print(this._$5824);
                                printStream.close();
                                if (this._$5788(openConnection)) {
                                    new DataInputStream(openConnection.getInputStream()).close();
                                    this._$5820 = "";
                                    this._$5824 = "";
                                    break;
                                }
                                this._$2649 = "";
                                if (this._$4250 > 3) {
                                    continue Label_0058_Outer;
                                }
                                this._$5801(openConnection);
                                n = 0;
                            }
                            else {
                                if (this._$4250 > 3) {
                                    this._$168079.show();
                                    break;
                                }
                                try {
                                    Thread.sleep(500L);
                                }
                                catch (InterruptedException ex2) {}
                            }
                        }
                    }
                    catch (IOException ex3) {
                        continue;
                    }
                    break;
                }
            }
        }
    }
    
    private boolean _$5788(final URLConnection urlConnection) {
        try {
            if (urlConnection.getHeaderField(0).indexOf("401") > 0) {
                ++this._$4250;
                return false;
            }
            return true;
        }
        catch (Exception ex) {
            return false;
        }
    }
    
    private void _$5801(final URLConnection urlConnection) {
        this._$4329.setText(this._$1300);
        final String headerField = urlConnection.getHeaderField("WWW-Authenticate");
        if (!headerField.equals(null)) {
            this._$4339.setText(headerField.substring(headerField.indexOf("realm=") + 7, headerField.length() - 1));
        }
        this._$4359.setText("");
        this._$4370.setText("");
        this._$4259.show();
        this._$4359.requestFocus();
    }
    
    private void _$5172() {
        this._$4259.hide();
        final String value = String.valueOf(String.valueOf(new StringBuffer(String.valueOf(String.valueOf(this._$4359.getText()))).append(":").append(this._$4370.getText())));
        this._$4408 = value.length();
        this._$4381 = new char[80];
        value.getChars(0, this._$4408, this._$4381, 0);
        this._$4390 = new char[128];
        this._$5924();
        this._$2649 = new String(this._$4390).trim();
    }
    
    private void _$5087() {
        this._$4259.hide();
        this._$4250 = 99;
    }
    
    private void _$5924() {
        int n = 0;
        int n2 = 0;
        int n3 = 0;
        while (this._$4408 > 2) {
            this._$6059(n, n2);
            this._$4408 -= 3;
            n += 3;
            n2 += 4;
        }
        char c;
        char c2;
        if (this._$4408 == 2) {
            c = this._$4381[n];
            c2 = this._$4381[n + 1];
            n3 = 0;
        }
        else {
            if (this._$4408 != 1) {
                this._$4390[n2 + 1] = '\0';
                return;
            }
            c = this._$4381[n];
            c2 = '\0';
        }
        final char c3 = (char)(c >> 2);
        final char c4 = (char)((c << 4 & '0') | (c2 >> 4 & '\u000f'));
        this._$4390[n2] = this._$4426[c3];
        ++n2;
        this._$4390[n2] = this._$4426[c4];
        if (this._$4408 == 1) {
            ++n2;
            this._$4390[n2] = '=';
            ++n2;
            this._$4390[n2] = '=';
        }
        else {
            final char c5 = (char)((c2 << 2 & '<') | (n3 >> 6 & 0x3));
            ++n2;
            this._$4390[n2] = this._$4426[c5];
            ++n2;
            this._$4390[n2] = '=';
        }
        this._$4390[n2 + 1] = '\0';
    }
    
    private void _$6059(final int n, final int n2) {
        final char c = (char)(this._$4381[n] >> 2 & '?');
        final char c2 = (char)((this._$4381[n] << 4 & '0') | (this._$4381[n + 1] >> 4 & '\u000f'));
        final char c3 = (char)((this._$4381[n + 1] << 2 & '<') | (this._$4381[n + 2] >> 6 & '\u0003'));
        final char c4 = (char)(this._$4381[n + 2] & '?');
        this._$4390[n2] = this._$4426[c];
        this._$4390[n2 + 1] = this._$4426[c2];
        this._$4390[n2 + 2] = this._$4426[c3];
        this._$4390[n2 + 3] = this._$4426[c4];
    }
    
    private void _$168131(final MouseEvent mouseEvent) {
        this._$168079.hide();
    }
}
