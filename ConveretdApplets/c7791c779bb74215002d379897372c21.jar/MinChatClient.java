import java.awt.image.ImageObserver;
import java.awt.Graphics;
import java.awt.event.WindowListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowAdapter;
import java.awt.Component;
import java.awt.LayoutManager;
import java.awt.BorderLayout;
import java.awt.Cursor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.Font;
import java.io.IOException;
import java.net.UnknownHostException;
import java.io.Reader;
import java.io.InputStreamReader;
import java.applet.AudioClip;
import java.applet.Applet;
import java.awt.Color;
import java.awt.Panel;
import java.awt.Image;
import java.awt.List;
import java.awt.TextArea;
import java.awt.TextField;
import java.awt.Checkbox;
import java.awt.Choice;
import java.io.BufferedReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.awt.Frame;

// 
// Decompiled by Procyon v0.5.30
// 

public class MinChatClient extends Frame
{
    static final int DEFAULT_PORT = 53376;
    Socket socket;
    PrintWriter os;
    BufferedReader is;
    int cLines;
    StreamListenerMin listener;
    Choice sound_choice;
    Checkbox time_checkbox;
    TextField inputfield;
    TextArea outputarea;
    List clientlist;
    Image image;
    Panel toppanel;
    Color default_fore;
    Color default_back;
    Applet applet;
    AudioClip bell;
    public static final int SOUND_NONE = 0;
    public static final int SOUND_BEEP = 1;
    public static final int SOUND_EFFECT = 2;
    int sound;
    boolean timestamp;
    String newfontname;
    int newfontstyle;
    int newfontsize;
    
    public MinChatClient(final String s, final int n) {
        this(s, n, null);
    }
    
    public MinChatClient(final String s, final int n, final Applet applet) {
        super("ChatClient");
        this.socket = null;
        this.os = null;
        this.is = null;
        this.cLines = 0;
        this.default_fore = Color.black;
        this.default_back = Color.white;
        this.applet = null;
        this.bell = null;
        this.sound = 0;
        this.timestamp = false;
        this.newfontname = new String("SansSerif");
        this.newfontstyle = 0;
        this.newfontsize = 12;
        this.applet = applet;
        if (applet != null) {
            this.bell = applet.getAudioClip(applet.getCodeBase(), "bonus.au");
        }
        if (this.bell == null) {
            System.err.println("bell is null");
        }
        try {
            this.socket = new Socket(s, n);
            this.os = new PrintWriter(this.socket.getOutputStream());
            this.is = new BufferedReader(new InputStreamReader(this.socket.getInputStream()));
        }
        catch (UnknownHostException ex) {
            final String string = "Can't find hostname: " + s + " : " + ex;
            System.err.println(string);
            if (applet != null) {
                applet.showStatus(string);
            }
        }
        catch (IOException ex2) {
            final String string2 = "Can't connect to " + s + ":" + n + " : " + ex2;
            System.err.println(string2);
            if (applet != null) {
                applet.showStatus(string2);
            }
        }
        final String string3 = "ChatClient - Connected to " + s + ":" + n;
        this.setTitle(string3);
        if (applet != null) {
            applet.showStatus(string3);
        }
        if (this.socket != null && this.os != null && this.is != null) {
            this.setFont(new Font("SansSerif", 0, 12));
            (this.inputfield = new TextField(40)).addActionListener(new ActionListener() {
                public void actionPerformed(final ActionEvent actionEvent) {
                    MinChatClient.this.os.println(MinChatClient.this.inputfield.getText());
                    MinChatClient.this.os.flush();
                    MinChatClient.this.inputfield.setText("");
                }
            });
            (this.outputarea = new TextArea("", 24, 60, 1)).setEditable(false);
            this.clientlist = new List(10);
            this.default_fore = this.outputarea.getForeground();
            this.default_back = this.outputarea.getBackground();
            this.outputarea.setForeground(Color.black);
            this.outputarea.setBackground(Color.white);
            this.outputarea.setCursor(Cursor.getDefaultCursor());
            this.setLayout(new BorderLayout());
            this.add("South", this.inputfield);
            this.add("Center", this.outputarea);
            this.add(this.clientlist, "East");
            this.addWindowListener(new WindowAdapter() {
                public void windowClosing(final WindowEvent windowEvent) {
                    MinChatClient.this.closeChat(true);
                }
            });
            this.pack();
            this.show();
            this.inputfield.requestFocus();
            this.listener = new StreamListenerMin(this.is, this.outputarea, this);
        }
    }
    
    void closeChat(final boolean b) {
        if (this.applet != null) {
            this.applet.showStatus("ChatClient - Connection closed.");
        }
        if (b) {
            this.os.println("/bye");
            this.os.flush();
        }
        this.setVisible(false);
        this.dispose();
        System.gc();
        try {
            if (this.applet == null) {
                System.exit(0);
            }
            this.os.println("/bye");
            this.os.flush();
        }
        catch (SecurityException ex) {}
    }
    
    public static void main(final String[] array) {
        if (array.length < 1 || array.length > 2) {
            System.err.println("Usage: java ChatClient <hostname> [<port>]");
            System.exit(0);
        }
        int int1 = 53376;
        try {
            if (array.length == 2) {
                int1 = Integer.parseInt(array[1]);
            }
        }
        catch (NumberFormatException ex) {
            System.err.println("Usage: java ChatClient <hostname> [<port>]");
            System.exit(0);
        }
        new MinChatClient(array[0], int1);
    }
    
    public void paint(final Graphics graphics) {
        graphics.drawImage(this.image, 20, 20, this);
    }
}
