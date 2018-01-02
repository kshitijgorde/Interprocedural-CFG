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
    private String _$985;
    private String _$2900;
    private String _$2904;
    private Frame _$1352;
    private Label _$1358;
    private Button _$1370;
    private boolean _$1377;
    private String _$1572;
    private int _$1577;
    private Frame _$1581;
    private Button _$1588;
    private Button _$1596;
    private Label _$1604;
    private Label _$1612;
    private Label _$1621;
    private Label _$1631;
    private Label _$1638;
    private Label _$1645;
    private Label _$1655;
    private TextField _$1675;
    private TextField _$1686;
    private char[] _$1697;
    private char[] _$1706;
    private char[] _$1715;
    private int _$1724;
    private int _$1733;
    private char[] _$1742;
    
    public CommandSend(final String $985) {
        this._$1358 = new Label();
        this._$1370 = new Button();
        this._$1377 = false;
        this._$1572 = "";
        this._$1577 = 0;
        this._$1588 = new Button();
        this._$1596 = new Button();
        this._$1604 = new Label();
        this._$1612 = new Label();
        this._$1621 = new Label();
        this._$1631 = new Label();
        this._$1638 = new Label();
        this._$1645 = new Label();
        this._$1655 = new Label();
        this._$1675 = new TextField();
        this._$1686 = new TextField();
        this._$1742 = new char[] { 'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z', 'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z', '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', '+', '/' };
        (this._$1352 = new Frame("SNC-RZ30")).setBackground(Color.lightGray);
        this._$1352.setSize(new Dimension(498, 129));
        this._$1352.setResizable(false);
        this._$1352.setLayout(null);
        this._$1358.setAlignment(1);
        this._$1358.setFont(new Font("Monospaced", 0, 13));
        this._$1358.setBounds(new Rectangle(11, 38, 476, 22));
        this._$1358.setText("Protected Object. This object is protected.");
        this._$1370.setLabel("OK");
        this._$1370.setBounds(new Rectangle(197, 74, 105, 25));
        this._$1370.addMouseListener(new MouseAdapter() {
            public void mouseClicked(final MouseEvent mouseEvent) {
                CommandSend.this._$2589(mouseEvent);
            }
        });
        (this._$1581 = new Frame("Enter Network Password")).setLayout(null);
        this._$1581.setBackground(Color.lightGray);
        this._$1581.setSize(new Dimension(450, 250));
        this._$1581.setResizable(false);
        this._$1581.addWindowListener(new WindowAdapter() {
            public void windowClosing(final WindowEvent windowEvent) {
                CommandSend.this._$2684();
            }
        });
        this._$1588.setLabel("OK");
        this._$1588.setBounds(new Rectangle(260, 200, 75, 25));
        this._$1588.addKeyListener(new KeyAdapter() {
            public void keyPressed(final KeyEvent keyEvent) {
                if (keyEvent.getKeyCode() == 10) {
                    CommandSend.this._$2764();
                }
            }
        });
        this._$1588.addMouseListener(new MouseAdapter() {
            public void mouseClicked(final MouseEvent mouseEvent) {
                CommandSend.this._$2764();
            }
        });
        this._$1596.setLabel("Cancel");
        this._$1596.setBounds(new Rectangle(345, 200, 75, 25));
        this._$1596.addKeyListener(new KeyAdapter() {
            public void keyPressed(final KeyEvent keyEvent) {
                CommandSend.this._$2684();
            }
        });
        this._$1596.addMouseListener(new MouseAdapter() {
            public void mouseClicked(final MouseEvent mouseEvent) {
                CommandSend.this._$2684();
            }
        });
        this._$1604.setText("Please type your user name and password.");
        this._$1604.setBounds(new Rectangle(30, 35, 250, 12));
        this._$1612.setText("Site:");
        this._$1612.setBounds(new Rectangle(30, 70, 90, 12));
        this._$1621.setText("Realm:");
        this._$1621.setBounds(new Rectangle(30, 100, 90, 12));
        this._$1631.setText("User Name:");
        this._$1631.setBounds(new Rectangle(30, 140, 90, 12));
        this._$1638.setText("Password:");
        this._$1638.setBounds(new Rectangle(30, 170, 90, 12));
        this._$1645.setBounds(new Rectangle(160, 65, 260, 20));
        this._$1655.setBounds(new Rectangle(160, 95, 260, 20));
        this._$1675.setBounds(new Rectangle(160, 135, 260, 20));
        this._$1686.setEchoChar('*');
        this._$1686.setBounds(new Rectangle(160, 165, 260, 20));
        this._$1352.add(this._$1358, null);
        this._$1352.add(this._$1370, null);
        this._$1352.setLocation(263, 319);
        this._$1581.add(this._$1604, null);
        this._$1581.add(this._$1612, null);
        this._$1581.add(this._$1645, null);
        this._$1581.add(this._$1621, null);
        this._$1581.add(this._$1631, null);
        this._$1581.add(this._$1675, null);
        this._$1581.add(this._$1686, null);
        this._$1581.add(this._$1588, null);
        this._$1581.add(this._$1596, null);
        this._$1581.add(this._$1638, null);
        this._$1581.add(this._$1655, null);
        this._$1581.setLocation(263, 319);
        this._$985 = $985;
        this._$2900 = "";
        this._$2904 = "";
    }
    
    public void setCommand(final String $2900, final String $2901) {
        this._$2900 = $2900;
        this._$2904 = $2901;
    }
    
    public void setAuthorize(final String $1572) {
        this._$1572 = $1572;
    }
    
    public void run() {
        final byte[] array = new byte[2048];
        int n = 1;
    Label_0056_Outer:
        while (true) {
            System.out.println("CommandSend Start!!");
            try {
                synchronized (this) {
                    this.wait();
                }
            }
            catch (InterruptedException ex) {}
            if (this._$2904.length() != 0) {
                while (true) {
                    try {
                        while (true) {
                            if (n == 1 || !this._$1572.equals("")) {
                                final URLConnection openConnection = new URL(String.valueOf(String.valueOf(new StringBuffer("http://").append(this._$985).append(this._$2900)))).openConnection();
                                if (!this._$1572.equals("")) {
                                    openConnection.setRequestProperty("Authorization", "Basic ".concat(String.valueOf(String.valueOf(this._$1572))));
                                }
                                openConnection.setUseCaches(false);
                                openConnection.setDoOutput(true);
                                final PrintStream printStream = new PrintStream(openConnection.getOutputStream());
                                printStream.print(this._$2904);
                                printStream.close();
                                if (this._$3434(openConnection)) {
                                    new DataInputStream(openConnection.getInputStream()).close();
                                    System.out.println("COMMAND: ".concat(String.valueOf(String.valueOf(this._$2904))));
                                    this._$2900 = "";
                                    this._$2904 = "";
                                    break;
                                }
                                this._$1572 = "";
                                if (this._$1577 > 3) {
                                    continue Label_0056_Outer;
                                }
                                this._$3447(openConnection);
                                n = 0;
                            }
                            else {
                                if (this._$1577 > 3) {
                                    this._$1352.show();
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
                        System.out.println("Command Error!!");
                        continue;
                    }
                    break;
                }
            }
        }
    }
    
    private boolean _$3434(final URLConnection urlConnection) {
        try {
            if (urlConnection.getHeaderField(0).indexOf("401") > 0) {
                ++this._$1577;
                return false;
            }
            return true;
        }
        catch (Exception ex) {
            System.out.println("HTTP header check error!!");
            return false;
        }
    }
    
    private void _$3447(final URLConnection urlConnection) {
        this._$1645.setText(this._$985);
        final String headerField = urlConnection.getHeaderField("WWW-Authenticate");
        if (!headerField.equals(null)) {
            this._$1655.setText(headerField.substring(headerField.indexOf("realm=") + 7, headerField.length() - 1));
        }
        this._$1675.setText("");
        this._$1686.setText("");
        this._$1581.show();
        this._$1675.requestFocus();
    }
    
    private void _$2764() {
        this._$1581.hide();
        final String value = String.valueOf(String.valueOf(new StringBuffer(String.valueOf(String.valueOf(this._$1675.getText()))).append(":").append(this._$1686.getText())));
        this._$1724 = value.length();
        this._$1697 = new char[80];
        value.getChars(0, this._$1724, this._$1697, 0);
        this._$1706 = new char[128];
        this._$3939();
        this._$1572 = new String(this._$1706).trim();
    }
    
    private void _$2684() {
        this._$1581.hide();
        this._$1577 = 99;
    }
    
    private void _$3939() {
        int n = 0;
        int n2 = 0;
        int n3 = 0;
        while (this._$1724 > 2) {
            this._$4078(n, n2);
            this._$1724 -= 3;
            n += 3;
            n2 += 4;
        }
        char c;
        char c2;
        if (this._$1724 == 2) {
            c = this._$1697[n];
            c2 = this._$1697[n + 1];
            n3 = 0;
        }
        else {
            if (this._$1724 != 1) {
                this._$1706[n2 + 1] = '\0';
                return;
            }
            c = this._$1697[n];
            c2 = '\0';
        }
        final char c3 = (char)(c >> 2);
        final char c4 = (char)((c << 4 & '0') | (c2 >> 4 & '\u000f'));
        this._$1706[n2] = this._$1742[c3];
        ++n2;
        this._$1706[n2] = this._$1742[c4];
        if (this._$1724 == 1) {
            ++n2;
            this._$1706[n2] = '=';
            ++n2;
            this._$1706[n2] = '=';
        }
        else {
            final char c5 = (char)((c2 << 2 & '<') | (n3 >> 6 & 0x3));
            ++n2;
            this._$1706[n2] = this._$1742[c5];
            ++n2;
            this._$1706[n2] = '=';
        }
        this._$1706[n2 + 1] = '\0';
    }
    
    private void _$4078(final int n, final int n2) {
        final char c = (char)(this._$1697[n] >> 2 & '?');
        final char c2 = (char)((this._$1697[n] << 4 & '0') | (this._$1697[n + 1] >> 4 & '\u000f'));
        final char c3 = (char)((this._$1697[n + 1] << 2 & '<') | (this._$1697[n + 2] >> 6 & '\u0003'));
        final char c4 = (char)(this._$1697[n + 2] & '?');
        this._$1706[n2] = this._$1742[c];
        this._$1706[n2 + 1] = this._$1742[c2];
        this._$1706[n2 + 2] = this._$1742[c3];
        this._$1706[n2 + 3] = this._$1742[c4];
    }
    
    private void _$2589(final MouseEvent mouseEvent) {
        this._$1352.hide();
    }
}
