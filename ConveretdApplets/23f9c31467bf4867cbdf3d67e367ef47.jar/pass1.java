import java.awt.event.ActionEvent;
import java.io.IOException;
import java.io.Reader;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.awt.event.ActionListener;
import java.awt.Font;
import java.awt.Component;
import java.awt.LayoutManager;
import java.awt.Button;
import java.awt.Label;
import java.awt.TextField;
import java.io.BufferedReader;
import java.io.InputStream;
import java.net.URL;
import java.applet.Applet;

// 
// Decompiled by Procyon v0.5.30
// 

public class pass1 extends Applet
{
    private URL finalurl;
    String infile;
    String[] inuser;
    int totno;
    InputStream countConn;
    BufferedReader countData;
    URL inURL;
    TextField txtlogin;
    Label label1;
    Label label2;
    Label label3;
    TextField txtpass;
    Label lblstatus;
    Button ButOk;
    Button ButReset;
    Label lbltitle;
    
    public void init() {
        this.setLayout(null);
        this.setSize(361, 191);
        this.add(this.txtlogin);
        this.txtlogin.setBounds(156, 72, 132, 24);
        this.label1.setText("Please Enter Login Name & Password");
        this.label1.setAlignment(1);
        this.add(this.label1);
        this.label1.setFont(new Font("Dialog", 1, 12));
        this.label1.setBounds(56, 36, 250, 24);
        this.label2.setText("Login");
        this.add(this.label2);
        this.label2.setFont(new Font("Dialog", 1, 12));
        this.label2.setBounds(75, 72, 36, 24);
        this.label3.setText("Password");
        this.add(this.label3);
        this.add(this.txtpass);
        this.txtpass.setEchoChar('*');
        this.txtpass.setBounds(156, 108, 132, 24);
        this.lblstatus.setAlignment(1);
        this.label3.setFont(new Font("Dialog", 1, 12));
        this.label3.setBounds(75, 108, 70, 21);
        this.add(this.lblstatus);
        this.lblstatus.setFont(new Font("Dialog", 1, 12));
        this.lblstatus.setBounds(84, 132, 204, 24);
        this.ButOk.setLabel("OK");
        this.add(this.ButOk);
        this.ButOk.setFont(new Font("Dialog", 1, 12));
        this.ButOk.setBounds(105, 156, 59, 23);
        this.ButReset.setLabel("Reset");
        this.add(this.ButReset);
        this.ButReset.setFont(new Font("Dialog", 1, 12));
        this.ButReset.setBounds(204, 156, 59, 23);
        this.lbltitle.setAlignment(1);
        this.add(this.lbltitle);
        this.lbltitle.setFont(new Font("Dialog", 1, 12));
        this.lbltitle.setBounds(12, 14, 336, 24);
        this.lbltitle.setText(this.getParameter("title"));
        final SymAction symAction = new SymAction();
        this.ButOk.addActionListener(symAction);
        this.ButReset.addActionListener(symAction);
        this.infile = new String("in.txt");
        try {
            this.inURL = new URL(this.getCodeBase(), this.infile);
        }
        catch (MalformedURLException ex) {
            this.getAppletContext().showStatus("Bad Counter URL:" + this.inURL);
        }
        if (this.getParameter("author").trim().toUpperCase().intern() == "JACKY LEUNG") {
            this.inFile();
        }
    }
    
    public void inFile() {
        new StringBuffer();
        try {
            this.countConn = this.inURL.openStream();
            this.countData = new BufferedReader(new InputStreamReader(this.countConn));
            String line;
            while ((line = this.countData.readLine()) != null) {
                ++this.totno;
                this.inuser[this.totno] = line;
            }
        }
        catch (IOException ex) {
            this.getAppletContext().showStatus("IO Error:" + ex.getMessage());
        }
        try {
            this.countConn.close();
            this.countData.close();
        }
        catch (IOException ex2) {
            this.getAppletContext().showStatus("IO Error:" + ex2.getMessage());
        }
    }
    
    void ButOk_ActionPerformed(final ActionEvent actionEvent) {
        boolean b = false;
        for (int i = 1; i <= this.totno / 2; ++i) {
            if (this.txtlogin.getText().trim().toUpperCase().intern() == this.inuser[2 * (i - 1) + 2].trim().toUpperCase().intern() && this.txtpass.getText().trim().toUpperCase().intern() == this.inuser[2 * (i - 1) + 3].trim().toUpperCase().intern()) {
                this.lblstatus.setText("Login Success, Loading..");
                b = true;
                final String intern = this.inuser[1].trim().intern();
                try {
                    this.finalurl = new URL(this.getCodeBase(), intern);
                }
                catch (MalformedURLException ex) {
                    this.lblstatus.setText("Bad URL");
                }
                this.getAppletContext().showDocument(this.finalurl, "_self");
            }
        }
        if (!b) {
            this.lblstatus.setText("Invalid Login or Password");
        }
    }
    
    void ButReset_ActionPerformed(final ActionEvent actionEvent) {
        this.txtlogin.setText("");
        this.txtpass.setText("");
    }
    
    public pass1() {
        this.inuser = new String[20];
        this.txtlogin = new TextField();
        this.label1 = new Label();
        this.label2 = new Label();
        this.label3 = new Label();
        this.txtpass = new TextField();
        this.lblstatus = new Label();
        this.ButOk = new Button();
        this.ButReset = new Button();
        this.lbltitle = new Label();
    }
    
    class SymAction implements ActionListener
    {
        public void actionPerformed(final ActionEvent actionEvent) {
            final Object source = actionEvent.getSource();
            if (source == pass1.this.ButOk) {
                pass1.this.ButOk_ActionPerformed(actionEvent);
                return;
            }
            if (source == pass1.this.ButReset) {
                pass1.this.ButReset_ActionPerformed(actionEvent);
            }
        }
    }
}
