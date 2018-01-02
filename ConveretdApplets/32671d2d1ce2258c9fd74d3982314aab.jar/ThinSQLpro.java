import java.awt.event.TextEvent;
import java.awt.event.ActionEvent;
import java.awt.event.ItemEvent;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.net.URLConnection;
import java.io.ObjectInputStream;
import sun.misc.BASE64Encoder;
import java.io.ObjectOutputStream;
import java.net.URL;
import java.awt.Frame;
import java.awt.Container;
import java.awt.ScrollPane;
import java.awt.Component;
import java.awt.FlowLayout;
import java.awt.LayoutManager;
import java.awt.BorderLayout;
import java.util.NoSuchElementException;
import java.util.StringTokenizer;
import java.awt.Color;
import java.awt.Panel;
import java.awt.CardLayout;
import java.util.Vector;
import java.awt.Font;
import java.awt.Checkbox;
import java.awt.Label;
import java.awt.Choice;
import java.awt.Button;
import java.awt.TextField;
import java.awt.event.TextListener;
import java.awt.event.ActionListener;
import java.awt.event.ItemListener;
import java.applet.Applet;

// 
// Decompiled by Procyon v0.5.30
// 

public class ThinSQLpro extends Applet implements ItemListener, ActionListener, TextListener
{
    TextField userField;
    TextField msgField;
    TextField passwordField;
    TextField counter1;
    Button runButton;
    Button loginButton;
    Button firstButton;
    Button prevButton;
    Button nextButton;
    Button lastButton;
    Button newButton;
    Button updButton;
    Button insButton;
    Button delButton;
    String updateTable;
    String wherePart;
    String orderPart;
    String mainPart;
    String user;
    String password;
    String driver;
    String url;
    String urlalt;
    String query;
    Choice ausw0;
    Choice ausw1;
    Label counterMax;
    int colTypeChoice;
    Checkbox gridCheck;
    Font fFont;
    Label[] myLabel;
    TextField[] myText;
    String[] colLabel;
    int[] colDisplaySize;
    int[] colType;
    boolean[] colAutoIncrement;
    boolean[] colSigned;
    int[] colNullable;
    boolean[] colCurrency;
    int[] colScale;
    int satznummer;
    int colsize;
    int rowsize;
    int icount;
    String[] conArray;
    String[] conArrayzw;
    Vector drivers;
    Vector urls;
    Vector sqls;
    Vector tableName;
    Vector indexCount;
    Vector groupName;
    Vector metaData;
    Vector rows;
    CardLayout layout;
    Panel p;
    Panel p1;
    Panel p11;
    Panel p12;
    Panel p2;
    Panel p2a;
    Panel p21;
    Panel p22;
    Panel p23;
    Panel p3;
    Grid gResult;
    
    public String getAppletInfo() {
        return "Name: ThinSQL\r\nAuthor: Klaus Gotthardt   http://www.kgo.de  \r\n";
    }
    
    protected void log(final String s) {
    }
    
    protected void show(final String text) {
        this.msgField.setText(text);
    }
    
    public void stop() {
        this.execSQL2("disconnect");
        this.log("stopped");
    }
    
    public void start() {
        this.conArrayzw[1] = "";
        this.log("started");
    }
    
    public void init() {
        this.myLabel = new Label[0];
        this.myText = new TextField[0];
        (this.ausw0 = new Choice()).addItemListener(this);
        this.setBackground(Color.lightGray);
        final String parameter = this.getParameter("BGCOLOR");
        if (parameter != null) {
            this.setBackground(new Color(Integer.valueOf(parameter, 16)));
        }
        for (int i = 0; i < 99; ++i) {
            final String parameter2 = this.getParameter("q" + i);
            if (parameter2 != null) {
                try {
                    final StringTokenizer stringTokenizer = new StringTokenizer(parameter2, ";");
                    this.ausw0.addItem(stringTokenizer.nextToken());
                    this.drivers.addElement(stringTokenizer.nextToken());
                    this.urls.addElement(stringTokenizer.nextToken());
                    this.sqls.addElement(stringTokenizer.nextToken());
                    this.tableName.addElement(stringTokenizer.nextToken());
                    this.indexCount.addElement(stringTokenizer.nextToken());
                    this.groupName.addElement(stringTokenizer.nextToken());
                }
                catch (NoSuchElementException ex) {}
            }
        }
        this.setLayout(new BorderLayout());
        this.p.setLayout(new BorderLayout());
        this.p1.setLayout(new BorderLayout());
        this.p11.setLayout(new FlowLayout(0));
        (this.loginButton = new Button("Login")).addActionListener(this);
        this.p11.add(this.loginButton);
        (this.runButton = new Button("Reload")).addActionListener(this);
        this.p11.add(this.runButton);
        this.p1.add("North", this.p11);
        this.p12.setLayout(new FlowLayout(0));
        this.gridCheck = new Checkbox("Grid");
        this.p12.add(this.gridCheck);
        this.gridCheck.addItemListener(this);
        this.p12.add(this.ausw0);
        this.ausw1 = new Choice();
        this.p12.add(this.ausw1);
        this.ausw1.addItemListener(this);
        this.ausw1.setVisible(false);
        this.p1.add("Center", this.p12);
        this.p2.setLayout(new BorderLayout());
        final ScrollPane scrollPane = new ScrollPane(0);
        scrollPane.add(this.p21);
        this.p22.setLayout(new FlowLayout(0));
        (this.firstButton = new Button("|<")).addActionListener(this);
        this.p22.add(this.firstButton);
        (this.prevButton = new Button("<")).addActionListener(this);
        this.p22.add(this.prevButton);
        (this.counter1 = new TextField("", 4)).addTextListener(this);
        this.p22.add(this.counter1);
        this.counterMax = new Label("       ");
        this.p22.add(this.counterMax);
        (this.nextButton = new Button(">")).addActionListener(this);
        this.p22.add(this.nextButton);
        (this.lastButton = new Button(">|")).addActionListener(this);
        this.p22.add(this.lastButton);
        (this.newButton = new Button(">*")).addActionListener(this);
        this.p22.add(this.newButton);
        (this.updButton = new Button("Update")).addActionListener(this);
        this.p22.add(this.updButton);
        this.updButton.setVisible(false);
        (this.insButton = new Button("Insert")).addActionListener(this);
        this.p22.add(this.insButton);
        this.insButton.setVisible(false);
        (this.delButton = new Button("Delete")).addActionListener(this);
        this.p22.add(this.delButton);
        this.delButton.setVisible(false);
        this.p23.setLayout(new FlowLayout(0));
        this.p2.add("North", this.p23);
        this.p2.add("Center", scrollPane);
        this.p2.add("South", this.p22);
        this.layout = new CardLayout();
        this.p2a.setLayout(this.layout);
        this.p2a.add("nogrid", this.p2);
        this.gResult = new Grid();
        this.p2a.add("grid", this.gResult);
        this.layout.show(this.p2a, "nogrid");
        this.p3.setLayout(new BorderLayout());
        this.msgField = new TextField("(c)1999 Klaus Gotthardt www.kgo.de (not registrated version)", 50);
        this.p3.add("Center", this.msgField);
        this.p.add("North", this.p1);
        this.p.add("Center", this.p2a);
        this.p.add("South", this.p3);
        this.add("Center", this.p);
        this.add("North", new Panel());
        this.add("West", new Panel());
        this.add("East", new Panel());
        this.add("South", new Panel());
    }
    
    public void execAusw0() {
        this.log("userid " + this.user);
        this.log("password " + this.password);
        final int selectedIndex = this.ausw0.getSelectedIndex();
        this.updateTable = (String)this.tableName.elementAt(selectedIndex);
        this.driver = (String)this.drivers.elementAt(selectedIndex);
        this.urlalt = this.url;
        this.url = (String)this.urls.elementAt(selectedIndex);
        this.query = (String)this.sqls.elementAt(selectedIndex);
        if (!this.url.equals(this.urlalt)) {
            final MyLogin myLogin = new MyLogin(new Frame(""));
            this.requestFocus();
            this.user = myLogin.username.getText();
            this.password = myLogin.password.getText();
            myLogin.dispose();
        }
        int n = this.query.indexOf("where", 0);
        if (n == -1) {
            n = this.query.indexOf("WHERE", 0);
        }
        int n2 = this.query.indexOf("order", 0);
        if (n2 == -1) {
            n2 = this.query.indexOf("ORDER", 0);
        }
        if (n > -1) {
            if (n2 > -1) {
                this.wherePart = this.query.substring(n, n2);
                this.orderPart = this.query.substring(n2, this.query.length());
            }
            else {
                this.wherePart = this.query.substring(n, this.query.length());
                this.orderPart = "";
            }
        }
        else if (n2 > -1) {
            this.wherePart = "";
            this.orderPart = this.query.substring(n2, this.query.length());
        }
        else {
            this.wherePart = "";
            this.orderPart = "";
        }
        if (n > -1) {
            this.mainPart = this.query.substring(0, n);
        }
        else if (n2 > -1) {
            this.mainPart = this.query.substring(0, n2);
        }
        else {
            this.mainPart = this.query;
        }
        this.log("main: " + this.mainPart);
        this.log("where: " + this.wherePart);
        this.log("order: " + this.orderPart);
        if (((String)this.groupName.elementAt(selectedIndex)).equals("null")) {
            this.ausw1.setVisible(false);
            this.execSQL2((String)this.sqls.elementAt(selectedIndex));
            this.execSQL21();
            return;
        }
        this.execSQL2(this.createChoiceString(selectedIndex));
        this.execSQL22();
    }
    
    public void execSQL2(final String s) {
        try {
            this.show("running ");
            String s2 = this.getDocumentBase().getHost();
            final int port = this.getDocumentBase().getPort();
            if (port != -1) {
                s2 = String.valueOf(s2) + ":" + port;
            }
            final URL url = new URL("http://" + s2 + "/servlet/ThinSQLservletpro");
            this.log("servlet invoked:" + url);
            final URLConnection openConnection = url.openConnection();
            openConnection.setDoOutput(true);
            openConnection.setDoInput(true);
            openConnection.setUseCaches(false);
            openConnection.setRequestProperty("Content-type", "application/octet-stream");
            final ObjectOutputStream objectOutputStream = new ObjectOutputStream(openConnection.getOutputStream());
            final int selectedIndex = this.ausw0.getSelectedIndex();
            final BASE64Encoder base64Encoder = new BASE64Encoder();
            this.conArray[0] = new String(base64Encoder.encodeBuffer(this.driver.getBytes()));
            this.conArray[1] = new String(base64Encoder.encodeBuffer(this.url.getBytes()));
            this.conArray[2] = new String(base64Encoder.encodeBuffer(this.user.getBytes()));
            this.conArray[3] = new String(base64Encoder.encodeBuffer(this.password.getBytes()));
            this.conArray[4] = new String(base64Encoder.encodeBuffer(s.getBytes()));
            this.icount = Integer.parseInt((String)this.indexCount.elementAt(selectedIndex));
            final String s3 = new String(base64Encoder.encodeBuffer("".getBytes()));
            this.updateTable = (String)this.tableName.elementAt(selectedIndex);
            if (this.conArray[0].equals(this.conArrayzw[0]) && this.conArray[1].equals(this.conArrayzw[1]) && this.conArray[2].equals(this.conArrayzw[2]) && this.conArray[3].equals(this.conArrayzw[3])) {
                this.conArray[0] = s3;
                this.conArray[1] = s3;
                this.conArray[2] = s3;
                this.conArray[3] = s3;
            }
            else {
                this.conArrayzw[0] = this.conArray[0];
                this.conArrayzw[1] = this.conArray[1];
                this.conArrayzw[2] = this.conArray[2];
                this.conArrayzw[3] = this.conArray[3];
            }
            this.log("send to servlet: " + s);
            objectOutputStream.writeObject(this.conArray);
            objectOutputStream.flush();
            objectOutputStream.close();
            this.log("get message");
            final ObjectInputStream objectInputStream = new ObjectInputStream(openConnection.getInputStream());
            this.show(String.valueOf(objectInputStream.readObject()));
            this.log("get metadata");
            this.metaData = (Vector)objectInputStream.readObject();
            if (this.metaData.size() > 0) {
                this.rows = (Vector)objectInputStream.readObject();
            }
            objectInputStream.close();
            this.log("close inputstream");
        }
        catch (Exception ex) {
            this.show(ex.toString());
        }
    }
    
    public void execSQL21() {
        if (this.metaData.size() > 0) {
            if (this.icount == 0) {
                this.updButton.setVisible(false);
                this.insButton.setVisible(false);
                this.delButton.setVisible(false);
            }
            else {
                this.updButton.setVisible(true);
                this.insButton.setVisible(true);
                this.delButton.setVisible(true);
            }
            final GridBagLayout layout = new GridBagLayout();
            final GridBagConstraints gridBagConstraints = new GridBagConstraints();
            this.p21.setLayout(layout);
            this.colLabel = this.metaData.elementAt(0);
            this.colDisplaySize = this.metaData.elementAt(1);
            this.colType = this.metaData.elementAt(2);
            this.colAutoIncrement = this.metaData.elementAt(3);
            this.colSigned = this.metaData.elementAt(4);
            this.colNullable = this.metaData.elementAt(5);
            this.colCurrency = this.metaData.elementAt(6);
            this.colScale = this.metaData.elementAt(7);
            this.gResult.setHead(this.colLabel);
            this.colsize = this.colLabel.length;
            for (int i = 0; i < this.myLabel.length; ++i) {
                this.p21.remove(this.myLabel[i]);
            }
            this.myLabel = new Label[this.colsize];
            for (int j = 0; j < this.colsize; ++j) {
                (this.myLabel[j] = new Label()).setText(this.colLabel[j]);
                gridBagConstraints.anchor = 13;
                gridBagConstraints.gridy = j;
                gridBagConstraints.gridx = 1;
                layout.setConstraints(this.myLabel[j], gridBagConstraints);
                this.p21.add(this.myLabel[j]);
            }
            final String[] array = new String[this.colsize];
            this.log("get resultssetdata");
            this.rowsize = this.rows.size();
            for (int k = 0; k < this.rowsize; ++k) {
                final Vector<String> vector = this.rows.elementAt(k);
                for (int l = 0; l < this.colsize; ++l) {
                    array[l] = String.valueOf(vector.elementAt(l));
                }
                this.gResult.addRow(array);
            }
            for (int n = 0; n < this.myText.length; ++n) {
                this.p21.remove(this.myText[n]);
            }
            this.myText = new TextField[this.colsize];
            for (int gridy = 0; gridy < this.colsize; ++gridy) {
                int n2 = this.colDisplaySize[gridy];
                if (n2 > 999) {
                    n2 = 999;
                }
                this.myText[gridy] = new TextField("", n2);
                if (this.colAutoIncrement[gridy]) {
                    this.myText[gridy].setEditable(false);
                }
                else {
                    this.myText[gridy].setEditable(true);
                }
                gridBagConstraints.anchor = 17;
                gridBagConstraints.gridy = gridy;
                gridBagConstraints.gridx = 2;
                layout.setConstraints(this.myText[gridy], gridBagConstraints);
                this.p21.add(this.myText[gridy]);
            }
            if (this.rowsize > 0) {
                this.satznummer = 1;
                this.counter1.setText("1");
                this.browseResults(0);
                this.counterMax.setText("/" + this.rowsize);
            }
            this.validate();
        }
    }
    
    public void execSQL22() {
        if (this.metaData.size() > 0) {
            this.rowsize = this.rows.size();
            this.p12.remove(this.ausw1);
            this.ausw1 = new Choice();
            this.colType = this.metaData.elementAt(2);
            this.colTypeChoice = this.colType[0];
            for (int i = 0; i < this.rowsize; ++i) {
                final Vector<String> vector = this.rows.elementAt(i);
                this.log(String.valueOf(vector.elementAt(0)));
                this.ausw1.addItem(String.valueOf(vector.elementAt(0)));
            }
            this.ausw1.addItemListener(this);
            this.p12.add(this.ausw1);
            this.validate();
        }
    }
    
    public void itemStateChanged(final ItemEvent itemEvent) {
        if (itemEvent.getSource() == this.gridCheck) {
            if (this.gridCheck.getState()) {
                this.layout.show(this.p2a, "grid");
                return;
            }
            this.layout.show(this.p2a, "nogrid");
        }
        else {
            if (itemEvent.getSource() == this.ausw0) {
                this.execAusw0();
                return;
            }
            if (itemEvent.getSource() == this.ausw1) {
                final int selectedIndex = this.ausw0.getSelectedIndex();
                this.ausw1.getSelectedIndex();
                final StringBuffer sb = new StringBuffer();
                sb.append(this.mainPart);
                if (this.wherePart == "") {
                    sb.append(" where ");
                }
                else {
                    sb.append(this.wherePart);
                    sb.append(" and ");
                }
                sb.append(this.groupName.elementAt(selectedIndex));
                sb.append(" = ");
                sb.append(this.addQuote(this.ausw1.getSelectedItem(), this.colTypeChoice));
                sb.append(" ");
                sb.append(this.orderPart);
                this.execSQL2(sb.toString());
                this.execSQL21();
            }
        }
    }
    
    public void actionPerformed(final ActionEvent actionEvent) {
        if (actionEvent.getSource() == this.runButton) {
            this.execAusw0();
            return;
        }
        if (actionEvent.getSource() == this.loginButton) {
            this.url = "";
            this.execAusw0();
            return;
        }
        if (actionEvent.getSource() == this.updButton) {
            this.show(this.createUpdateString(this.satznummer - 1));
            final String updateString = this.createUpdateString(this.satznummer - 1);
            if (!updateString.equals("")) {
                this.execSQL2(updateString);
            }
        }
        else {
            if (actionEvent.getSource() == this.delButton) {
                final MsgBox msgBox = new MsgBox(new Frame(""), " Are you sure to delete the row ? ", true);
                this.requestFocus();
                if (msgBox.id) {
                    this.show(this.createDeleteString(this.satznummer - 1));
                    this.execSQL2(this.createDeleteString(this.satznummer - 1));
                }
                msgBox.dispose();
                return;
            }
            if (actionEvent.getSource() == this.insButton) {
                this.show(this.createInsertString(this.satznummer - 1));
                final String insertString = this.createInsertString(this.satznummer - 1);
                if (!insertString.equals("")) {
                    this.execSQL2(insertString);
                }
            }
            else {
                if (actionEvent.getSource() == this.newButton) {
                    for (int i = 0; i < this.colsize; ++i) {
                        if (this.colAutoIncrement[i]) {
                            this.myText[i].setText("(auto)");
                        }
                        else if (this.colSigned[i]) {
                            this.myText[i].setText("0");
                        }
                        else {
                            this.myText[i].setText("");
                        }
                    }
                    return;
                }
                if (this.rowsize > 0) {
                    if (actionEvent.getSource() == this.firstButton) {
                        this.satznummer = 1;
                    }
                    else if (actionEvent.getSource() == this.prevButton && this.satznummer > 1) {
                        --this.satznummer;
                    }
                    else if (actionEvent.getSource() == this.nextButton && this.satznummer < this.rowsize) {
                        ++this.satznummer;
                    }
                    else if (actionEvent.getSource() == this.lastButton) {
                        this.satznummer = this.rowsize;
                    }
                    this.browseResults(this.satznummer - 1);
                    this.counter1.setText(String.valueOf(this.satznummer));
                }
            }
        }
    }
    
    private Frame getFrame() {
        Container container;
        for (container = this.getParent(); container != null && !(container instanceof Frame); container = container.getParent()) {}
        return (Frame)container;
    }
    
    public void textValueChanged(final TextEvent textEvent) {
        try {
            if (textEvent.getSource() == this.counter1) {
                this.satznummer = Integer.parseInt(this.counter1.getText());
                if (this.satznummer > 0 && this.satznummer < this.rowsize + 1) {
                    this.browseResults(this.satznummer - 1);
                }
            }
        }
        catch (Exception ex) {
            this.show("Error");
        }
    }
    
    public void browseResults(final int n) {
        final Vector<String> vector = this.rows.elementAt(n);
        for (int i = 0; i < this.colsize; ++i) {
            this.myText[i].setText(String.valueOf(vector.elementAt(i)));
        }
        this.validate();
    }
    
    public String createUpdateString(final int n) {
        final StringBuffer sb = new StringBuffer();
        int n2 = 0;
        sb.append("update ");
        sb.append(this.updateTable);
        sb.append(" set ");
        final Vector<String> vector = this.rows.elementAt(n);
        for (int i = 0; i < this.colsize; ++i) {
            final String text = this.myText[i].getText();
            final String s = vector.elementAt(i);
            if ((vector.elementAt(i) != null || !text.equals("null")) && !text.equals(s)) {
                ++n2;
                sb.append(this.colLabel[i]);
                sb.append(" = ");
                sb.append(this.addQuote(text, this.colType[i]));
                sb.append(",");
            }
        }
        if (n2 == 0) {
            return "";
        }
        sb.setCharAt(sb.length() - 1, ' ');
        sb.append(this.whereString(this.icount));
        return sb.toString();
    }
    
    public String createChoiceString(final int n) {
        final String s = this.groupName.elementAt(n);
        final StringBuffer sb = new StringBuffer();
        sb.append("select ");
        sb.append(s);
        sb.append(" from  ");
        sb.append(this.updateTable);
        sb.append(" ");
        sb.append(this.wherePart);
        sb.append(" group by ");
        sb.append(s);
        sb.append(" order by ");
        sb.append(s);
        this.log(sb.toString());
        return sb.toString();
    }
    
    public String createDeleteString(final int n) {
        final StringBuffer sb = new StringBuffer();
        sb.append("delete from ");
        sb.append(this.updateTable);
        sb.append(this.whereString(this.icount));
        return sb.toString();
    }
    
    public String createInsertString(final int n) {
        int n2 = 0;
        final StringBuffer sb = new StringBuffer();
        final StringBuffer sb2 = new StringBuffer();
        final Vector vector = this.rows.elementAt(n);
        for (int i = 0; i < this.colsize; ++i) {
            if (!this.colAutoIncrement[i]) {
                ++n2;
                sb.append(this.colLabel[i]);
                sb.append(",");
                String text = this.myText[i].getText();
                if (this.colNullable[i] == 1 && text.equals("")) {
                    text = "null";
                }
                sb2.append(this.addQuote(text, this.colType[i]));
                sb2.append(",");
            }
        }
        if (n2 == 0) {
            return "";
        }
        sb.setCharAt(sb.length() - 1, ' ');
        sb2.setCharAt(sb2.length() - 1, ' ');
        final StringBuffer sb3 = new StringBuffer();
        sb3.append("insert into ");
        sb3.append(this.updateTable);
        sb3.append(" (");
        sb3.append(sb.toString());
        sb3.append(") values (");
        sb3.append(sb2.toString());
        sb3.append(")");
        return sb3.toString();
    }
    
    public String whereString(final int n) {
        final StringBuffer sb = new StringBuffer();
        for (int i = 0; i < n; ++i) {
            if (i == 0) {
                sb.append(" where ");
            }
            else {
                sb.append(" and ");
            }
            sb.append(this.colLabel[i]);
            sb.append(" = ");
            sb.append(this.addQuote(this.myText[i].getText(), this.colType[i]));
        }
        return sb.toString();
    }
    
    public String addQuote(final String s, final int n) {
        final StringBuffer sb = new StringBuffer();
        if (n == -5 || n == -2 || n == -7 || n == 3 || n == 8 || n == 6 || n == 2 || n == 7 || n == 5 || n == -6 || n == 4) {
            sb.append(s);
        }
        else if (n == 91) {
            sb.append("{d '");
            sb.append(s);
            sb.append("'}");
        }
        else if (n == 92) {
            sb.append("{t '");
            sb.append(s);
            sb.append("'}");
        }
        else if (n == 93) {
            sb.append("{ts '");
            sb.append(s);
            sb.append("'}");
        }
        else {
            sb.append("'");
            sb.append(s);
            sb.append("'");
        }
        return sb.toString();
    }
    
    public ThinSQLpro() {
        this.conArray = new String[5];
        this.conArrayzw = new String[] { "", "", "", "", "" };
        this.drivers = new Vector();
        this.urls = new Vector();
        this.sqls = new Vector();
        this.tableName = new Vector();
        this.indexCount = new Vector();
        this.groupName = new Vector();
        this.p = new Panel();
        this.p1 = new Panel();
        this.p11 = new Panel();
        this.p12 = new Panel();
        this.p2 = new Panel();
        this.p2a = new Panel();
        this.p21 = new Panel();
        this.p22 = new Panel();
        this.p23 = new Panel();
        this.p3 = new Panel();
    }
}
