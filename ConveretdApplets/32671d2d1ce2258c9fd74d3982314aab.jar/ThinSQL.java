import java.awt.event.TextEvent;
import java.awt.event.ActionEvent;
import java.awt.event.ItemEvent;
import java.net.URLConnection;
import java.io.ObjectInputStream;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import sun.misc.BASE64Encoder;
import java.io.ObjectOutputStream;
import java.net.URL;
import java.awt.Container;
import java.awt.ScrollPane;
import java.awt.Component;
import java.awt.FlowLayout;
import java.awt.LayoutManager;
import java.awt.BorderLayout;
import java.awt.Color;
import java.util.NoSuchElementException;
import java.util.StringTokenizer;
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

public class ThinSQL extends Applet implements ItemListener, ActionListener, TextListener
{
    TextField userField;
    TextField msgField;
    TextField passwordField;
    TextField counter1;
    Button runButton;
    Button firstButton;
    Button prevButton;
    Button nextButton;
    Button lastButton;
    Button newButton;
    Button updButton;
    Button insButton;
    Button delButton;
    Choice ausw0;
    Label counterMax;
    String updateTable;
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
    Vector drivers;
    Vector urls;
    Vector sqls;
    Vector tableName;
    Vector indexCount;
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
        System.out.println(s);
    }
    
    protected void show(final String text) {
        this.msgField.setText(text);
    }
    
    public void destroy() {
        this.log("destroy");
    }
    
    public void stop() {
        this.log("stopped");
    }
    
    public void init() {
        this.fFont = new Font("DialogInput", 1, 14);
        this.myLabel = new Label[0];
        this.myText = new TextField[0];
        (this.ausw0 = new Choice()).addItemListener(this);
        for (int i = 0; i < 99; ++i) {
            final String parameter = this.getParameter("q" + i);
            if (parameter != null) {
                try {
                    final StringTokenizer stringTokenizer = new StringTokenizer(parameter, ";");
                    this.ausw0.addItem(stringTokenizer.nextToken());
                    this.drivers.addElement(stringTokenizer.nextToken());
                    this.urls.addElement(stringTokenizer.nextToken());
                    this.sqls.addElement(stringTokenizer.nextToken());
                    this.tableName.addElement(stringTokenizer.nextToken());
                    this.indexCount.addElement(stringTokenizer.nextToken());
                }
                catch (NoSuchElementException ex) {}
            }
        }
        this.setBackground(Color.lightGray);
        this.setLayout(new BorderLayout());
        this.p.setLayout(new BorderLayout());
        this.p1.setLayout(new BorderLayout());
        this.p11.setLayout(new FlowLayout(0));
        this.p11.add(new Label("UserID:"));
        this.userField = new TextField("", 10);
        this.p11.add(this.userField);
        this.p11.add(new Label("Password:"));
        (this.passwordField = new TextField("", 10)).setEchoChar('*');
        this.p11.add(this.passwordField);
        this.p1.add("North", this.p11);
        this.p12.setLayout(new FlowLayout(0));
        this.gridCheck = new Checkbox("Grid");
        this.p12.add(this.gridCheck);
        this.gridCheck.addItemListener(this);
        this.p12.add(this.ausw0);
        (this.runButton = new Button("Reload")).addActionListener(this);
        this.p12.add(this.runButton);
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
        this.updButton.setEnabled(false);
        (this.insButton = new Button("Insert")).addActionListener(this);
        this.p22.add(this.insButton);
        this.insButton.setEnabled(false);
        (this.delButton = new Button("Delete")).addActionListener(this);
        this.p22.add(this.delButton);
        this.delButton.setEnabled(false);
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
        this.msgField = new TextField("", 50);
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
    
    public void execSQL(final String s) {
        try {
            this.show("running ");
            String s2 = this.getDocumentBase().getHost();
            final int port = this.getDocumentBase().getPort();
            if (port != -1) {
                s2 = String.valueOf(s2) + ":" + port;
            }
            final URL url = new URL("http://" + s2 + "/servlet/ThinSQLservlet");
            this.log("servlet invoked:" + url);
            final URLConnection openConnection = url.openConnection();
            openConnection.setDoOutput(true);
            openConnection.setDoInput(true);
            openConnection.setUseCaches(false);
            openConnection.setRequestProperty("Content-type", "application/octet-stream");
            final ObjectOutputStream objectOutputStream = new ObjectOutputStream(openConnection.getOutputStream());
            final int selectedIndex = this.ausw0.getSelectedIndex();
            final BASE64Encoder base64Encoder = new BASE64Encoder();
            final String[] array = { new String(base64Encoder.encodeBuffer(this.drivers.elementAt(selectedIndex).getBytes())), new String(base64Encoder.encodeBuffer(this.urls.elementAt(selectedIndex).getBytes())), new String(base64Encoder.encodeBuffer(this.userField.getText().getBytes())), new String(base64Encoder.encodeBuffer(this.passwordField.getText().getBytes())), new String(base64Encoder.encodeBuffer(s.getBytes())) };
            this.updateTable = (String)this.tableName.elementAt(selectedIndex);
            if (this.updateTable.equals("null")) {
                this.updButton.setEnabled(false);
                this.insButton.setEnabled(false);
                this.delButton.setEnabled(false);
            }
            else {
                this.updButton.setEnabled(true);
                this.insButton.setEnabled(true);
                this.delButton.setEnabled(true);
            }
            this.icount = Integer.parseInt((String)this.indexCount.elementAt(selectedIndex));
            this.log("send to servlet: " + array[4]);
            objectOutputStream.writeObject(array);
            objectOutputStream.flush();
            objectOutputStream.close();
            final GridBagLayout layout = new GridBagLayout();
            final GridBagConstraints gridBagConstraints = new GridBagConstraints();
            this.log("get message");
            final ObjectInputStream objectInputStream = new ObjectInputStream(openConnection.getInputStream());
            this.show(String.valueOf(objectInputStream.readObject()));
            this.log("get metadata");
            this.metaData = (Vector)objectInputStream.readObject();
            if (this.metaData.size() > 0) {
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
                final String[] array2 = new String[this.colsize];
                this.log("get resultssetdata");
                this.rows = (Vector)objectInputStream.readObject();
                this.rowsize = this.rows.size();
                for (int k = 0; k < this.rowsize; ++k) {
                    final Vector<String> vector = this.rows.elementAt(k);
                    for (int l = 0; l < this.colsize; ++l) {
                        array2[l] = String.valueOf(vector.elementAt(l));
                    }
                    this.gResult.addRow(array2);
                }
                for (int n = 0; n < this.myText.length; ++n) {
                    this.p21.remove(this.myText[n]);
                }
                this.myText = new TextField[this.colsize];
                if (this.rowsize > 0) {
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
                    this.satznummer = 1;
                    this.counter1.setText("1");
                    this.browseResults(0);
                    this.counterMax.setText("/" + this.rowsize);
                }
                this.show("");
            }
            else {
                objectInputStream.readObject();
            }
            this.validate();
            objectInputStream.close();
            this.log("close inputstream");
        }
        catch (Exception ex) {
            ex.printStackTrace();
            this.show(ex.toString());
        }
    }
    
    public void itemStateChanged(final ItemEvent itemEvent) {
        if (itemEvent.getSource() != this.gridCheck) {
            if (itemEvent.getSource() == this.ausw0) {
                this.execSQL(this.sqls.elementAt(this.ausw0.getSelectedIndex()));
            }
            return;
        }
        if (this.gridCheck.getState()) {
            this.layout.show(this.p2a, "grid");
            return;
        }
        this.layout.show(this.p2a, "nogrid");
    }
    
    public void actionPerformed(final ActionEvent actionEvent) {
        if (actionEvent.getSource() == this.runButton) {
            this.execSQL(this.sqls.elementAt(this.ausw0.getSelectedIndex()));
            return;
        }
        if (actionEvent.getSource() == this.updButton) {
            this.show(this.createUpdateString(this.satznummer - 1));
            final String updateString = this.createUpdateString(this.satznummer - 1);
            if (!updateString.equals("")) {
                this.execSQL(updateString);
            }
        }
        else {
            if (actionEvent.getSource() == this.delButton) {
                this.show(this.createDeleteString(this.satznummer - 1));
                this.execSQL(this.createDeleteString(this.satznummer - 1));
                return;
            }
            if (actionEvent.getSource() == this.insButton) {
                this.show(this.createInsertString(this.satznummer - 1));
                final String insertString = this.createInsertString(this.satznummer - 1);
                if (!insertString.equals("")) {
                    this.execSQL(insertString);
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
    
    public void textValueChanged(final TextEvent textEvent) {
        try {
            if (textEvent.getSource() == this.counter1) {
                this.satznummer = Integer.parseInt(this.counter1.getText());
                if (this.satznummer > 0 && this.satznummer < this.rowsize + 1) {
                    this.browseResults(this.satznummer - 1);
                    this.show("");
                    return;
                }
                this.show("Error");
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
                sb.append(this.addQuote(text, i));
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
                sb2.append(this.addQuote(text, i));
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
            sb.append(this.addQuote(this.myText[i].getText(), i));
        }
        return sb.toString();
    }
    
    public String addQuote(final String s, final int n) {
        final int n2 = this.colType[n];
        final StringBuffer sb = new StringBuffer();
        if (n2 == -5 || n2 == -2 || n2 == -7 || n2 == 3 || n2 == 8 || n2 == 6 || n2 == 2 || n2 == 7 || n2 == 5 || n2 == -6 || n2 == 4) {
            sb.append(s);
        }
        else if (this.colNullable[n] == 1 && s.equals("null")) {
            sb.append(s);
        }
        else if (n2 == 91) {
            sb.append("{d '");
            sb.append(s);
            sb.append("'}");
        }
        else if (n2 == 92) {
            sb.append("{t '");
            sb.append(s);
            sb.append("'}");
        }
        else if (n2 == 93) {
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
    
    public ThinSQL() {
        this.drivers = new Vector();
        this.urls = new Vector();
        this.sqls = new Vector();
        this.tableName = new Vector();
        this.indexCount = new Vector();
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
