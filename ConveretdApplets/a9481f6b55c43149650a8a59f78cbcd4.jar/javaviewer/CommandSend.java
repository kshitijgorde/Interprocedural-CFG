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
    private String _$1013;
    private String _$1014;
    private String _$1015;
    public boolean ready;
    private Frame _$1017;
    private Label _$1019;
    private Button _$4884;
    private boolean _$4885;
    private String _$1023;
    private int _$1024;
    private Frame _$1025;
    private Button _$1026;
    private Button _$1027;
    private Label _$1028;
    private Label _$1029;
    private Label _$1030;
    private Label _$1031;
    private Label _$1032;
    private Label _$1033;
    private Label _$1034;
    private TextField _$1036;
    private TextField _$1037;
    private char[] _$1038;
    private char[] _$1039;
    private char[] _$1040;
    private int _$1041;
    private int _$1042;
    private char[] _$1043;
    
    public CommandSend(final String $1013) {
        this.ready = false;
        this._$1019 = new Label();
        this._$4884 = new Button();
        this._$4885 = false;
        this._$1023 = "";
        this._$1024 = 0;
        this._$1026 = new Button();
        this._$1027 = new Button();
        this._$1028 = new Label();
        this._$1029 = new Label();
        this._$1030 = new Label();
        this._$1031 = new Label();
        this._$1032 = new Label();
        this._$1033 = new Label();
        this._$1034 = new Label();
        this._$1036 = new TextField();
        this._$1037 = new TextField();
        this._$1043 = new char[] { 'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z', 'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z', '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', '+', '/' };
        (this._$1017 = new Frame("Sony Network Camera SNC-RZ25")).setBackground(Color.lightGray);
        this._$1017.setSize(new Dimension(498, 129));
        this._$1017.setResizable(false);
        this._$1017.setLayout(null);
        this._$1019.setAlignment(1);
        this._$1019.setFont(new Font("Monospaced", 0, 13));
        this._$1019.setBounds(new Rectangle(11, 38, 476, 22));
        this._$1019.setText("Protected Object. This object is protected.");
        this._$4884.setLabel("OK");
        this._$4884.setBounds(new Rectangle(197, 74, 105, 25));
        this._$4884.addMouseListener(new MouseAdapter() {
            public void mouseClicked(final MouseEvent mouseEvent) {
                CommandSend.this._$1065(mouseEvent);
            }
        });
        (this._$1025 = new Frame("Enter Network Password")).setLayout(null);
        this._$1025.setBackground(Color.lightGray);
        this._$1025.setSize(new Dimension(450, 250));
        this._$1025.setResizable(false);
        this._$1025.addWindowListener(new WindowAdapter() {
            public void windowClosing(final WindowEvent windowEvent) {
                CommandSend.this._$1070();
            }
        });
        this._$1026.setLabel("OK");
        this._$1026.setBounds(new Rectangle(260, 200, 75, 25));
        this._$1026.addKeyListener(new KeyAdapter() {
            public void keyPressed(final KeyEvent keyEvent) {
                if (keyEvent.getKeyCode() == 10) {
                    CommandSend.this._$1077();
                }
            }
        });
        this._$1026.addMouseListener(new MouseAdapter() {
            public void mouseClicked(final MouseEvent mouseEvent) {
                CommandSend.this._$1077();
            }
        });
        this._$1027.setLabel("Cancel");
        this._$1027.setBounds(new Rectangle(345, 200, 75, 25));
        this._$1027.addKeyListener(new KeyAdapter() {
            public void keyPressed(final KeyEvent keyEvent) {
                CommandSend.this._$1070();
            }
        });
        this._$1027.addMouseListener(new MouseAdapter() {
            public void mouseClicked(final MouseEvent mouseEvent) {
                CommandSend.this._$1070();
            }
        });
        this._$1028.setText("Please type your user name and password.");
        this._$1028.setBounds(new Rectangle(30, 35, 250, 12));
        this._$1029.setText("Site:");
        this._$1029.setBounds(new Rectangle(30, 70, 90, 12));
        this._$1030.setText("Realm:");
        this._$1030.setBounds(new Rectangle(30, 100, 90, 12));
        this._$1031.setText("User Name:");
        this._$1031.setBounds(new Rectangle(30, 140, 90, 12));
        this._$1032.setText("Password:");
        this._$1032.setBounds(new Rectangle(30, 170, 90, 12));
        this._$1033.setBounds(new Rectangle(160, 65, 260, 20));
        this._$1034.setBounds(new Rectangle(160, 95, 260, 20));
        this._$1036.setBounds(new Rectangle(160, 135, 260, 20));
        this._$1037.setEchoChar('*');
        this._$1037.setBounds(new Rectangle(160, 165, 260, 20));
        this._$1017.add(this._$1019, null);
        this._$1017.add(this._$4884, null);
        this._$1017.setLocation(263, 319);
        this._$1025.add(this._$1028, null);
        this._$1025.add(this._$1029, null);
        this._$1025.add(this._$1033, null);
        this._$1025.add(this._$1030, null);
        this._$1025.add(this._$1031, null);
        this._$1025.add(this._$1036, null);
        this._$1025.add(this._$1037, null);
        this._$1025.add(this._$1026, null);
        this._$1025.add(this._$1027, null);
        this._$1025.add(this._$1032, null);
        this._$1025.add(this._$1034, null);
        this._$1025.setLocation(263, 319);
        this._$1013 = $1013;
        this._$1014 = "";
        this._$1015 = "";
    }
    
    public void setCommand(final String $1014, final String $1015) {
        this._$1014 = $1014;
        this._$1015 = $1015;
    }
    
    public void setAuthorize(final String $1023) {
        this._$1023 = $1023;
    }
    
    public void run() {
        final byte[] array = new byte[2048];
        int n = 1;
    Label_0013:
        while (true) {
            while (true) {
                this.ready = true;
                try {
                    synchronized (this) {
                        this.wait();
                    }
                }
                catch (InterruptedException ex) {}
                this.ready = false;
                if (this._$1015.length() != 0) {
                Label_0061_Outer:
                    while (true) {
                        while (true) {
                            try {
                                while (true) {
                                    if (n == 1 || !this._$1023.equals("")) {
                                        final URLConnection openConnection = new URL("http://" + this._$1013 + this._$1014).openConnection();
                                        if (!this._$1023.equals("")) {
                                            openConnection.setRequestProperty("Authorization", "Basic " + this._$1023);
                                        }
                                        openConnection.setUseCaches(false);
                                        openConnection.setDoOutput(true);
                                        final PrintStream printStream = new PrintStream(openConnection.getOutputStream());
                                        printStream.print(this._$1015);
                                        printStream.close();
                                        if (this._$1102(openConnection)) {
                                            new DataInputStream(openConnection.getInputStream()).close();
                                            this._$1014 = "";
                                            this._$1015 = "";
                                            continue Label_0013;
                                        }
                                        this._$1023 = "";
                                        if (this._$1024 > 3) {
                                            continue Label_0061_Outer;
                                        }
                                        this._$1834(openConnection);
                                        n = 0;
                                    }
                                    else {
                                        if (this._$1024 > 3) {
                                            this._$1017.show();
                                            continue Label_0013;
                                        }
                                        try {
                                            Thread.sleep(500L);
                                        }
                                        catch (InterruptedException ex2) {}
                                    }
                                }
                            }
                            catch (IOException ex3) {
                                continue Label_0061_Outer;
                            }
                            continue;
                        }
                    }
                }
            }
            break;
        }
    }
    
    private boolean _$1102(final URLConnection urlConnection) {
        try {
            if (urlConnection.getHeaderField(0).indexOf("401") > 0) {
                ++this._$1024;
                return false;
            }
            return true;
        }
        catch (Exception ex) {
            return false;
        }
    }
    
    private void _$1834(final URLConnection urlConnection) {
        this._$1033.setText(this._$1013);
        final String headerField = urlConnection.getHeaderField("WWW-Authenticate");
        if (!headerField.equals(null)) {
            this._$1034.setText(headerField.substring(headerField.indexOf("realm=") + 7, headerField.length() - 1));
        }
        this._$1036.setText("");
        this._$1037.setText("");
        this._$1025.show();
        this._$1036.requestFocus();
    }
    
    private void _$1077() {
        this._$1025.hide();
        final String string = this._$1036.getText() + ":" + this._$1037.getText();
        this._$1041 = string.length();
        this._$1038 = new char[80];
        string.getChars(0, this._$1041, this._$1038, 0);
        this._$1039 = new char[128];
        this._$1117();
        this._$1023 = new String(this._$1039).trim();
    }
    
    private void _$1070() {
        this._$1025.hide();
        this._$1024 = 99;
    }
    
    private void _$1117() {
        int n = 0;
        int n2 = 0;
        int n3 = 0;
        while (this._$1041 > 2) {
            this._$1127(n, n2);
            this._$1041 -= 3;
            n += 3;
            n2 += 4;
        }
        char c;
        char c2;
        if (this._$1041 == 2) {
            c = this._$1038[n];
            c2 = this._$1038[n + 1];
            n3 = 0;
        }
        else {
            if (this._$1041 != 1) {
                this._$1039[n2 + 1] = '\0';
                return;
            }
            c = this._$1038[n];
            c2 = '\0';
        }
        final char c3 = (char)(c >> 2);
        final char c4 = (char)((c << 4 & '0') | (c2 >> 4 & '\u000f'));
        this._$1039[n2] = this._$1043[c3];
        ++n2;
        this._$1039[n2] = this._$1043[c4];
        if (this._$1041 == 1) {
            ++n2;
            this._$1039[n2] = '=';
            ++n2;
            this._$1039[n2] = '=';
        }
        else {
            final char c5 = (char)((c2 << 2 & '<') | (n3 >> 6 & 0x3));
            ++n2;
            this._$1039[n2] = this._$1043[c5];
            ++n2;
            this._$1039[n2] = '=';
        }
        this._$1039[n2 + 1] = '\0';
    }
    
    private void _$1127(final int n, final int n2) {
        final char c = (char)(this._$1038[n] >> 2 & '?');
        final char c2 = (char)((this._$1038[n] << 4 & '0') | (this._$1038[n + 1] >> 4 & '\u000f'));
        final char c3 = (char)((this._$1038[n + 1] << 2 & '<') | (this._$1038[n + 2] >> 6 & '\u0003'));
        final char c4 = (char)(this._$1038[n + 2] & '?');
        this._$1039[n2] = this._$1043[c];
        this._$1039[n2 + 1] = this._$1043[c2];
        this._$1039[n2 + 2] = this._$1043[c3];
        this._$1039[n2 + 3] = this._$1043[c4];
    }
    
    private void _$1065(final MouseEvent mouseEvent) {
        this._$1017.hide();
    }
}
