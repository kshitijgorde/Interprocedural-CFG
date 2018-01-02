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
    private String _$989;
    private String _$3597;
    private String _$3601;
    private Frame _$2529;
    private Label _$2535;
    private Button _$2547;
    private boolean _$2554;
    private String _$2744;
    private int _$2749;
    private Frame _$2753;
    private Button _$2760;
    private Button _$2768;
    private Label _$2776;
    private Label _$2784;
    private Label _$2793;
    private Label _$2803;
    private Label _$2810;
    private Label _$2817;
    private Label _$2827;
    private TextField _$2847;
    private TextField _$2858;
    private char[] _$2869;
    private char[] _$2878;
    private char[] _$2887;
    private int _$2896;
    private int _$2905;
    private char[] _$2914;
    
    public CommandSend(final String $989) {
        this._$2535 = new Label();
        this._$2547 = new Button();
        this._$2554 = false;
        this._$2744 = "";
        this._$2749 = 0;
        this._$2760 = new Button();
        this._$2768 = new Button();
        this._$2776 = new Label();
        this._$2784 = new Label();
        this._$2793 = new Label();
        this._$2803 = new Label();
        this._$2810 = new Label();
        this._$2817 = new Label();
        this._$2827 = new Label();
        this._$2847 = new TextField();
        this._$2858 = new TextField();
        this._$2914 = new char[] { 'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z', 'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z', '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', '+', '/' };
        (this._$2529 = new Frame("SNC-RZ30")).setBackground(Color.lightGray);
        this._$2529.setSize(new Dimension(498, 129));
        this._$2529.setResizable(false);
        this._$2529.setLayout(null);
        this._$2535.setAlignment(1);
        this._$2535.setFont(new Font("Monospaced", 0, 13));
        this._$2535.setBounds(new Rectangle(11, 38, 476, 22));
        this._$2535.setText("Protected Object. This object is protected.");
        this._$2547.setLabel("OK");
        this._$2547.setBounds(new Rectangle(197, 74, 105, 25));
        this._$2547.addMouseListener(new MouseAdapter() {
            public void mouseClicked(final MouseEvent mouseEvent) {
                CommandSend.this._$3292(mouseEvent);
            }
        });
        (this._$2753 = new Frame("Enter Network Password")).setLayout(null);
        this._$2753.setBackground(Color.lightGray);
        this._$2753.setSize(new Dimension(450, 250));
        this._$2753.setResizable(false);
        this._$2753.addWindowListener(new WindowAdapter() {
            public void windowClosing(final WindowEvent windowEvent) {
                CommandSend.this._$3387();
            }
        });
        this._$2760.setLabel("OK");
        this._$2760.setBounds(new Rectangle(260, 200, 75, 25));
        this._$2760.addKeyListener(new KeyAdapter() {
            public void keyPressed(final KeyEvent keyEvent) {
                if (keyEvent.getKeyCode() == 10) {
                    CommandSend.this._$3466();
                }
            }
        });
        this._$2760.addMouseListener(new MouseAdapter() {
            public void mouseClicked(final MouseEvent mouseEvent) {
                CommandSend.this._$3466();
            }
        });
        this._$2768.setLabel("Cancel");
        this._$2768.setBounds(new Rectangle(345, 200, 75, 25));
        this._$2768.addKeyListener(new KeyAdapter() {
            public void keyPressed(final KeyEvent keyEvent) {
                CommandSend.this._$3387();
            }
        });
        this._$2768.addMouseListener(new MouseAdapter() {
            public void mouseClicked(final MouseEvent mouseEvent) {
                CommandSend.this._$3387();
            }
        });
        this._$2776.setText("Please type your user name and password.");
        this._$2776.setBounds(new Rectangle(30, 35, 250, 12));
        this._$2784.setText("Site:");
        this._$2784.setBounds(new Rectangle(30, 70, 90, 12));
        this._$2793.setText("Realm:");
        this._$2793.setBounds(new Rectangle(30, 100, 90, 12));
        this._$2803.setText("User Name:");
        this._$2803.setBounds(new Rectangle(30, 140, 90, 12));
        this._$2810.setText("Password:");
        this._$2810.setBounds(new Rectangle(30, 170, 90, 12));
        this._$2817.setBounds(new Rectangle(160, 65, 260, 20));
        this._$2827.setBounds(new Rectangle(160, 95, 260, 20));
        this._$2847.setBounds(new Rectangle(160, 135, 260, 20));
        this._$2858.setEchoChar('*');
        this._$2858.setBounds(new Rectangle(160, 165, 260, 20));
        this._$2529.add(this._$2535, null);
        this._$2529.add(this._$2547, null);
        this._$2529.setLocation(263, 319);
        this._$2753.add(this._$2776, null);
        this._$2753.add(this._$2784, null);
        this._$2753.add(this._$2817, null);
        this._$2753.add(this._$2793, null);
        this._$2753.add(this._$2803, null);
        this._$2753.add(this._$2847, null);
        this._$2753.add(this._$2858, null);
        this._$2753.add(this._$2760, null);
        this._$2753.add(this._$2768, null);
        this._$2753.add(this._$2810, null);
        this._$2753.add(this._$2827, null);
        this._$2753.setLocation(263, 319);
        this._$989 = $989;
        this._$3597 = "";
        this._$3601 = "";
    }
    
    public void setCommand(final String $3597, final String $3598) {
        this._$3597 = $3597;
        this._$3601 = $3598;
    }
    
    public void setAuthorize(final String $2744) {
        this._$2744 = $2744;
    }
    
    public void run() {
        final byte[] array = new byte[2048];
        int n = 1;
    Label_0048_Outer:
        while (true) {
            try {
                synchronized (this) {
                    this.wait();
                }
            }
            catch (InterruptedException ex) {}
            if (this._$3601.length() != 0) {
                while (true) {
                    try {
                        while (true) {
                            if (n == 1 || !this._$2744.equals("")) {
                                final URLConnection openConnection = new URL(String.valueOf(String.valueOf(new StringBuffer("http://").append(this._$989).append(this._$3597)))).openConnection();
                                if (!this._$2744.equals("")) {
                                    openConnection.setRequestProperty("Authorization", "Basic ".concat(String.valueOf(String.valueOf(this._$2744))));
                                }
                                openConnection.setUseCaches(false);
                                openConnection.setDoOutput(true);
                                final PrintStream printStream = new PrintStream(openConnection.getOutputStream());
                                printStream.print(this._$3601);
                                printStream.close();
                                if (this._$3907(openConnection)) {
                                    new DataInputStream(openConnection.getInputStream()).close();
                                    this._$3597 = "";
                                    this._$3601 = "";
                                    break;
                                }
                                this._$2744 = "";
                                if (this._$2749 > 3) {
                                    continue Label_0048_Outer;
                                }
                                this._$3920(openConnection);
                                n = 0;
                            }
                            else {
                                if (this._$2749 > 3) {
                                    this._$2529.show();
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
    
    private boolean _$3907(final URLConnection urlConnection) {
        try {
            if (urlConnection.getHeaderField(0).indexOf("401") > 0) {
                ++this._$2749;
                return false;
            }
            return true;
        }
        catch (Exception ex) {
            System.out.println("HTTP header check error!!");
            return false;
        }
    }
    
    private void _$3920(final URLConnection urlConnection) {
        this._$2817.setText(this._$989);
        final String headerField = urlConnection.getHeaderField("WWW-Authenticate");
        if (!headerField.equals(null)) {
            this._$2827.setText(headerField.substring(headerField.indexOf("realm=") + 7, headerField.length() - 1));
        }
        this._$2847.setText("");
        this._$2858.setText("");
        this._$2753.show();
        this._$2847.requestFocus();
    }
    
    private void _$3466() {
        this._$2753.hide();
        final String value = String.valueOf(String.valueOf(new StringBuffer(String.valueOf(String.valueOf(this._$2847.getText()))).append(":").append(this._$2858.getText())));
        this._$2896 = value.length();
        this._$2869 = new char[80];
        value.getChars(0, this._$2896, this._$2869, 0);
        this._$2878 = new char[128];
        this._$4312();
        this._$2744 = new String(this._$2878).trim();
    }
    
    private void _$3387() {
        this._$2753.hide();
        this._$2749 = 99;
    }
    
    private void _$4312() {
        int n = 0;
        int n2 = 0;
        int n3 = 0;
        while (this._$2896 > 2) {
            this._$4451(n, n2);
            this._$2896 -= 3;
            n += 3;
            n2 += 4;
        }
        char c;
        char c2;
        if (this._$2896 == 2) {
            c = this._$2869[n];
            c2 = this._$2869[n + 1];
            n3 = 0;
        }
        else {
            if (this._$2896 != 1) {
                this._$2878[n2 + 1] = '\0';
                return;
            }
            c = this._$2869[n];
            c2 = '\0';
        }
        final char c3 = (char)(c >> 2);
        final char c4 = (char)((c << 4 & '0') | (c2 >> 4 & '\u000f'));
        this._$2878[n2] = this._$2914[c3];
        ++n2;
        this._$2878[n2] = this._$2914[c4];
        if (this._$2896 == 1) {
            ++n2;
            this._$2878[n2] = '=';
            ++n2;
            this._$2878[n2] = '=';
        }
        else {
            final char c5 = (char)((c2 << 2 & '<') | (n3 >> 6 & 0x3));
            ++n2;
            this._$2878[n2] = this._$2914[c5];
            ++n2;
            this._$2878[n2] = '=';
        }
        this._$2878[n2 + 1] = '\0';
    }
    
    private void _$4451(final int n, final int n2) {
        final char c = (char)(this._$2869[n] >> 2 & '?');
        final char c2 = (char)((this._$2869[n] << 4 & '0') | (this._$2869[n + 1] >> 4 & '\u000f'));
        final char c3 = (char)((this._$2869[n + 1] << 2 & '<') | (this._$2869[n + 2] >> 6 & '\u0003'));
        final char c4 = (char)(this._$2869[n + 2] & '?');
        this._$2878[n2] = this._$2914[c];
        this._$2878[n2 + 1] = this._$2914[c2];
        this._$2878[n2 + 2] = this._$2914[c3];
        this._$2878[n2 + 3] = this._$2914[c4];
    }
    
    private void _$3292(final MouseEvent mouseEvent) {
        this._$2529.hide();
    }
}
