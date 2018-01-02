// 
// Decompiled by Procyon v0.5.30
// 

package buddysoft;

import buddysoft.a.c;
import java.awt.PopupMenu;
import java.net.MalformedURLException;
import java.net.URL;
import java.awt.MenuItem;
import java.awt.Event;
import java.io.IOException;
import java.net.Socket;
import java.awt.Label;
import java.awt.Font;
import java.awt.Color;
import java.awt.Component;
import java.awt.GridLayout;
import java.awt.Panel;
import java.awt.LayoutManager;
import java.awt.BorderLayout;
import java.util.StringTokenizer;
import java.io.DataInputStream;
import java.io.PrintStream;
import buddysoft.a.d;
import java.awt.TextArea;
import java.awt.TextField;
import java.awt.Choice;

class a extends buddysoft.a.a
{
    private Choice else;
    private TextField g;
    private TextField char;
    private TextField void;
    private TextField goto;
    private TextArea j;
    private d b;
    private d k;
    private boolean l;
    private QuickMail null;
    private String i;
    private String e;
    private String case;
    private String long;
    private String h;
    private String f;
    private PrintStream d;
    private DataInputStream c;
    
    private void for(final String s) {
        this.goto.setText(" " + s);
        this.null.showStatus(s);
    }
    
    private boolean new(final String s) {
        if (s.equals("")) {
            return true;
        }
        if (this.null.goto != null) {
            final StringTokenizer stringTokenizer = new StringTokenizer(this.null.goto, "+");
            while (stringTokenizer.hasMoreTokens()) {
                if (this.a(s).equals(stringTokenizer.nextToken().trim().substring(2))) {
                    return true;
                }
            }
        }
        return false;
    }
    
    private void do() {
        this.j.setText(this.i);
        this.g.setText(this.e);
        this.char.setText(this.case);
        this.void.setText(this.long);
        this.f = "Done";
    }
    
    private String for() {
        return this.j.getText().trim();
    }
    
    a(final QuickMail null, final int n) {
        super(38, 12, 12, 12, n);
        this.i = "Type (paste) your message here!";
        this.e = "your e-mail";
        this.case = "your name";
        this.long = "about";
        this.h = "WARNING - Unregistreted 30-day Evaluation Copy!";
        this.if("BS Quick Mail - light");
        this.a(false);
        this.null = null;
        this.setBackground(null.else);
        this.setForeground(null.if);
        this.else = new Choice();
        final StringTokenizer stringTokenizer = new StringTokenizer(null.long, ",");
        while (stringTokenizer.hasMoreTokens()) {
            this.else.addItem(stringTokenizer.nextToken().trim());
        }
        this.setLayout(new BorderLayout(0, 4));
        final Panel panel = new Panel();
        panel.setLayout(new BorderLayout(4, 0));
        final Panel panel2 = new Panel();
        if (this.else.countItems() > 1 || this.else.getSelectedItem().equals("missing recipient email")) {
            panel2.setLayout(new GridLayout(4, 1, 0, 4));
            panel2.add(this.else);
        }
        else {
            panel2.setLayout(new GridLayout(3, 1, 0, 4));
        }
        (this.g = new TextField()).setBackground(Color.white);
        this.g.setForeground(Color.black);
        panel2.add(this.g);
        (this.char = new TextField()).setBackground(Color.white);
        this.char.setForeground(Color.black);
        panel2.add(this.char);
        (this.void = new TextField()).setBackground(Color.white);
        this.void.setForeground(Color.black);
        panel2.add(this.void);
        panel.add("Center", panel2);
        final Panel panel3 = new Panel();
        panel3.setFont(new Font("Halvetica", 1, 11));
        if (this.else.countItems() > 1 || this.else.getSelectedItem().equals("missing recipient email")) {
            panel3.setLayout(new GridLayout(4, 1, 0, 4));
            panel3.add(new Label("To"));
        }
        else {
            panel3.setLayout(new GridLayout(3, 1, 0, 4));
        }
        panel3.add(new Label("From"));
        panel3.add(new Label("Sender"));
        panel3.add(new Label("Subject"));
        panel.add("East", panel3);
        this.add("North", panel);
        (this.j = new TextArea()).setBackground(Color.white);
        this.j.setForeground(Color.black);
        this.add("Center", this.j);
        final Panel panel4 = new Panel();
        panel4.setLayout(new BorderLayout(0, 4));
        final Panel panel5 = new Panel();
        panel5.setLayout(new GridLayout(1, 2));
        (this.k = new d("Reset")).a("Reset mail form");
        this.k.setBackground(new Color(200, 200, 200));
        this.k.setForeground(Color.black);
        panel5.add(this.k);
        (this.b = new d("Send")).a("Send current email");
        this.b.setBackground(this.null.for);
        this.b.setForeground(this.null.case);
        panel5.add(this.b);
        panel4.add("Center", panel5);
        (this.goto = new TextField("")).setEditable(false);
        panel4.add("South", this.goto);
        this.add("South", panel4);
        String s = this.null.getCodeBase().getHost();
        if (s.startsWith("www.")) {
            s = s.substring(4);
        }
        this.l = this.new(s);
        this.do();
    }
    
    private String try() {
        return "\"" + this.char.getText().trim() + "\"";
    }
    
    private String char() {
        return this.else.getSelectedItem();
    }
    
    private void else() {
        this.int();
        Socket socket;
        try {
            this.do("Connecting to server....");
            socket = new Socket(this.null.getCodeBase().getHost(), 25);
            this.d = new PrintStream(socket.getOutputStream());
            this.c = new DataInputStream(socket.getInputStream());
        }
        catch (IOException ex) {
            this.do("Error : Cannot connect to the server!");
            this.goto();
            return;
        }
        try {
            this.case();
            this.int("HELO localhost");
            this.case();
            this.int("MAIL FROM: " + this.try() + "<" + this.byte() + ">");
            this.case();
            this.int("RCPT TO: " + this.char());
            this.case();
            if (this.null.new) {
                this.int("RCPT TO: " + this.try() + "<" + this.byte() + ">");
                this.case();
            }
            this.int("DATA");
            this.case();
            String s = "To: " + this.char() + "\r\n";
            if (this.null.new) {
                s = s + "Cc: " + this.try() + "<" + this.byte() + ">\r\n";
            }
            String s2 = s + "From: " + this.try() + "<" + this.byte() + ">\r\n" + "Subject: " + this.null.byte + " " + this.new() + "\r\n" + "MIME-version: 1.0\r\n" + "Content-type: TEXT/PLAIN; charset=US-ASCII\r\n\r\n";
            if (this.null.char) {
                s2 = s2 + "Sent from: " + this.null.getDocumentBase() + "\r\n--\r\n";
            }
            this.int(s2 + this.j.getText().trim() + "\r\n.\r\n");
            this.case();
            this.int("QUIT");
            this.case();
            this.case();
            this.d.close();
            this.c.close();
            socket.close();
        }
        catch (IOException ex2) {
            this.do("IO Error: While sending mail");
            this.goto();
            return;
        }
        this.do("Your message was sent successfully!");
        this.goto();
    }
    
    private void goto() {
        this.else.enable();
        this.j.enable();
        this.g.enable();
        this.char.enable();
        this.void.enable();
        this.b.enable();
        this.k.enable();
    }
    
    private void do(final String f) {
        this.f = f;
        this.goto.setText(" " + f);
        this.null.showStatus(f);
    }
    
    private void int() {
        this.b.disable();
        this.k.disable();
        this.else.disable();
        this.j.disable();
        this.g.disable();
        this.char.disable();
        this.void.disable();
    }
    
    public boolean handleEvent(final Event event) {
        switch (event.id) {
            case 1001: {
                if (event.target == this.k) {
                    this.do();
                    break;
                }
                if (event.target == this.b) {
                    if (this.byte().equals(this.e)) {
                        this.do("Please enter your e-mail !");
                        break;
                    }
                    if (this.try().equals(this.case)) {
                        this.do("Please enter your name !");
                        break;
                    }
                    if (this.void.getText().trim().equals(this.long)) {
                        this.do("Please enter a subject !");
                        break;
                    }
                    if (this.j.getText().trim().equals(this.i)) {
                        this.do("Please enter a message !");
                        break;
                    }
                    this.else();
                    break;
                }
                else {
                    if (event.target instanceof MenuItem) {
                        URL url = null;
                        try {
                            url = new URL("http://www.buddysoft.net/");
                        }
                        catch (MalformedURLException ex) {}
                        this.null.getAppletContext().showDocument(url, "_new");
                        break;
                    }
                    break;
                }
                break;
            }
            case 501: {
                if (event.metaDown()) {
                    final PopupMenu popupMenu = new PopupMenu();
                    popupMenu.add(new MenuItem("About " + this.null.try));
                    this.add(popupMenu);
                    popupMenu.show(this, event.x, event.y);
                    break;
                }
                break;
            }
            case 504: {
                if (event.target instanceof c) {
                    this.for(((c)event.target).a());
                    break;
                }
                break;
            }
            case 505: {
                if (!this.l) {
                    this.f = this.h;
                }
                this.do(this.f);
                break;
            }
        }
        return super.handleEvent(event);
    }
    
    private void int(final String s) throws IOException {
        this.d.println(s);
        this.d.flush();
        if (this.null.do) {
            System.out.println("s : " + s);
        }
    }
    
    private String byte() {
        return this.g.getText().trim();
    }
    
    private void case() throws IOException {
        final String line = this.c.readLine();
        this.do(line);
        if (this.null.do) {
            System.out.println("r : " + line);
        }
    }
    
    private String new() {
        return this.void.getText().trim();
    }
}
