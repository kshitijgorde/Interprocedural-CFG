import java.awt.event.WindowListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowAdapter;
import java.awt.Component;
import java.awt.LayoutManager;
import java.awt.BorderLayout;
import java.awt.Cursor;
import java.awt.Menu;
import java.awt.MenuBar;
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

public class ChatClient extends Frame
{
    static final int DEFAULT_PORT = 53376;
    Socket socket;
    PrintWriter os;
    BufferedReader is;
    int cLines;
    StreamListener listener;
    Choice sound_choice;
    Checkbox time_checkbox;
    TextField inputfield;
    TextArea outputarea;
    List clientlist;
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
    AboutBox about;
    
    public ChatClient(final String s, final int n) {
        this(s, n, null);
    }
    
    public ChatClient(final String s, final int n, final Applet applet) {
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
                    ChatClient.this.os.println(ChatClient.this.inputfield.getText());
                    ChatClient.this.os.flush();
                    ChatClient.this.inputfield.setText("");
                }
            });
            final MenuBar menuBar = new MenuBar();
            final Menu menu = new Menu("Exit");
            menu.add("Close");
            menu.addActionListener(new ActionListener() {
                public void actionPerformed(final ActionEvent actionEvent) {
                    ChatClient.this.closeChat(true);
                }
            });
            menuBar.add(menu);
            final Menu menu2 = new Menu("Font");
            final String[] fontList = this.getToolkit().getFontList();
            for (int i = 0; i < fontList.length; ++i) {
                menu2.add(fontList[i]);
            }
            menu2.addActionListener(new ActionListener() {
                public void actionPerformed(final ActionEvent actionEvent) {
                    final Font font = new Font(actionEvent.getActionCommand(), ChatClient.this.newfontstyle, ChatClient.this.newfontsize);
                    ChatClient.this.outputarea.setFont(font);
                    ChatClient.this.inputfield.setFont(font);
                    ChatClient.this.doLayout();
                }
            });
            menuBar.add(menu2);
            final Menu menu3 = new Menu("Font Size");
            for (int j = 8; j <= 24; ++j) {
                menu3.add(Integer.toString(j));
            }
            menu3.addActionListener(new ActionListener() {
                public void actionPerformed(final ActionEvent actionEvent) {
                    ChatClient.this.newfontsize = Integer.valueOf(actionEvent.getActionCommand());
                    final Font font = new Font(ChatClient.this.newfontname, ChatClient.this.newfontstyle, ChatClient.this.newfontsize);
                    ChatClient.this.outputarea.setFont(font);
                    ChatClient.this.inputfield.setFont(font);
                    ChatClient.this.doLayout();
                }
            });
            menuBar.add(menu3);
            final Menu menu4 = new Menu("Font Style");
            menu4.add("Plain");
            menu4.add("Bold");
            menu4.add("Italic");
            menu4.add("BoldItalic");
            menu4.addActionListener(new ActionListener() {
                public void actionPerformed(final ActionEvent actionEvent) {
                    final String actionCommand = actionEvent.getActionCommand();
                    if (actionCommand.equals("Plain")) {
                        ChatClient.this.newfontstyle = 0;
                    }
                    else if (actionCommand.equals("Bold")) {
                        ChatClient.this.newfontstyle = 1;
                    }
                    else if (actionCommand.equals("Italic")) {
                        ChatClient.this.newfontstyle = 2;
                    }
                    else if (actionCommand.equals("BoldItalic")) {
                        ChatClient.this.newfontstyle = 3;
                    }
                    final Font font = new Font(ChatClient.this.newfontname, ChatClient.this.newfontstyle, ChatClient.this.newfontsize);
                    ChatClient.this.outputarea.setFont(font);
                    ChatClient.this.inputfield.setFont(font);
                    ChatClient.this.doLayout();
                }
            });
            menuBar.add(menu4);
            final Menu menu5 = new Menu("BackColor");
            menu5.add("Black");
            menu5.add("Blue");
            menu5.add("Cyan");
            menu5.add("DarkGray");
            menu5.add("Gray");
            menu5.add("Green");
            menu5.add("LightGray");
            menu5.add("Magenta");
            menu5.add("Orange");
            menu5.add("Pink");
            menu5.add("Red");
            menu5.add("White");
            menu5.add("Yellow");
            menu5.addActionListener(new ActionListener() {
                public void actionPerformed(final ActionEvent actionEvent) {
                    ChatClient.this.outputarea.setBackground(ChatClient.this.name_to_color(actionEvent.getActionCommand(), ChatClient.this.default_back));
                }
            });
            menuBar.add(menu5);
            final Menu menu6 = new Menu("ForeColor");
            menu6.add("Black");
            menu6.add("Blue");
            menu6.add("Cyan");
            menu6.add("DarkGray");
            menu6.add("Gray");
            menu6.add("Green");
            menu6.add("LightGray");
            menu6.add("Magenta");
            menu6.add("Orange");
            menu6.add("Pink");
            menu6.add("Red");
            menu6.add("White");
            menu6.add("Yellow");
            menu6.addActionListener(new ActionListener() {
                public void actionPerformed(final ActionEvent actionEvent) {
                    ChatClient.this.outputarea.setForeground(ChatClient.this.name_to_color(actionEvent.getActionCommand(), ChatClient.this.default_fore));
                }
            });
            menuBar.add(menu6);
            final Menu menu7 = new Menu("Timestamp");
            menu7.add("Yes");
            menu7.add("No");
            menu7.addActionListener(new ActionListener() {
                public void actionPerformed(final ActionEvent actionEvent) {
                    final String actionCommand = actionEvent.getActionCommand();
                    if (actionCommand.equals("Yes")) {
                        ChatClient.this.timestamp = true;
                    }
                    else if (actionCommand.equals("No")) {
                        ChatClient.this.timestamp = false;
                    }
                }
            });
            menuBar.add(menu7);
            final Menu menu8 = new Menu("Sound");
            menu8.add("None");
            menu8.add("Beep");
            menu8.add("Effect");
            menu8.addActionListener(new ActionListener() {
                public void actionPerformed(final ActionEvent actionEvent) {
                    final String actionCommand = actionEvent.getActionCommand();
                    if (actionCommand.equals("None")) {
                        ChatClient.this.sound = 0;
                    }
                    else if (actionCommand.equals("Beep")) {
                        ChatClient.this.sound = 1;
                    }
                    else if (actionCommand.equals("Effect")) {
                        ChatClient.this.sound = 2;
                    }
                }
            });
            menuBar.add(menu8);
            final Menu menu9 = new Menu("Help");
            menu9.add("For Help Type '/help'");
            menu9.add("About..");
            menu9.addActionListener(new ActionListener() {
                public void actionPerformed(final ActionEvent actionEvent) {
                    final String actionCommand = actionEvent.getActionCommand();
                    if (actionCommand.equals("About..")) {
                        ChatClient.this.outputarea.append("'Botchi Chat Server'(Build 0.96)/n");
                        (ChatClient.this.about = new AboutBox()).show();
                    }
                    if (actionCommand.equals("For Help Type '/help'")) {
                        ChatClient.this.os.println("/help");
                        ChatClient.this.os.flush();
                    }
                }
            });
            menuBar.add(menu9);
            (this.outputarea = new TextArea("", 24, 60, 1)).setEditable(false);
            (this.clientlist = new List(10, false)).addActionListener(new ActionListener() {
                public void actionPerformed(final ActionEvent actionEvent) {
                    final String actionCommand = actionEvent.getActionCommand();
                    ChatClient.this.inputfield.setText(" ");
                    ChatClient.this.inputfield.requestFocus();
                    ChatClient.this.inputfield.setText("/tell " + actionCommand + " ");
                    ChatClient.this.inputfield.setCaretPosition(7 + actionCommand.length());
                }
            });
            this.default_fore = this.outputarea.getForeground();
            this.default_back = this.outputarea.getBackground();
            this.outputarea.setForeground(Color.black);
            this.outputarea.setBackground(Color.white);
            this.outputarea.setCursor(Cursor.getDefaultCursor());
            this.setLayout(new BorderLayout());
            this.add("South", this.inputfield);
            this.add("Center", this.outputarea);
            this.add("East", this.clientlist);
            this.setMenuBar(menuBar);
            this.show();
            this.addWindowListener(new WindowAdapter() {
                public void windowClosing(final WindowEvent windowEvent) {
                    ChatClient.this.closeChat(true);
                }
            });
            this.pack();
            this.show();
            this.inputfield.requestFocus();
            this.listener = new StreamListener(this.is, this.outputarea, this);
        }
    }
    
    public void closeChat(final boolean b) {
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
                this.os.println("/bye");
            }
            this.os.flush();
            System.exit(0);
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
        new ChatClient(array[0], int1);
    }
    
    Color name_to_color(final String s, final Color color) {
        if (s.equals("Black")) {
            return Color.black;
        }
        if (s.equals("Blue")) {
            return Color.blue;
        }
        if (s.equals("Cyan")) {
            return Color.cyan;
        }
        if (s.equals("DarkGray")) {
            return Color.darkGray;
        }
        if (s.equals("Gray")) {
            return Color.gray;
        }
        if (s.equals("Green")) {
            return Color.green;
        }
        if (s.equals("LightGray")) {
            return Color.lightGray;
        }
        if (s.equals("Magenta")) {
            return Color.magenta;
        }
        if (s.equals("Orange")) {
            return Color.orange;
        }
        if (s.equals("Pink")) {
            return Color.pink;
        }
        if (s.equals("Red")) {
            return Color.red;
        }
        if (s.equals("White")) {
            return Color.white;
        }
        if (s.equals("Yellow")) {
            return Color.yellow;
        }
        return color;
    }
}
