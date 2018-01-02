import java.io.Reader;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Enumeration;
import java.awt.Frame;
import java.awt.AWTEvent;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.awt.Insets;
import java.awt.GridLayout;
import java.awt.Panel;
import java.awt.Component;
import java.awt.Label;
import java.awt.LayoutManager;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Font;
import java.awt.Color;
import java.net.URL;
import java.util.Hashtable;
import java.awt.Button;
import java.awt.TextField;
import java.awt.event.KeyListener;
import java.awt.event.ActionListener;
import java.applet.Applet;

// 
// Decompiled by Procyon v0.5.30
// 

public class setpass extends Applet implements ActionListener, KeyListener
{
    protected TextField name;
    protected TextField psw;
    protected Button list;
    protected Hashtable hash;
    protected URL pswURL;
    
    public void init() {
        final String parameter = this.getParameter("psw_url");
        if (parameter != null) {
            try {
                if (parameter.indexOf(":") == -1) {
                    this.pswURL = new URL(this.getDocumentBase(), parameter);
                }
                else {
                    this.pswURL = new URL(parameter);
                }
            }
            catch (Exception ex) {
                System.err.println(ex);
            }
        }
        this.setBackground(new Color(Color.white.getRGB()));
        this.setFont(new Font("SansSerif", 0, 14));
        final GridBagLayout layout = new GridBagLayout();
        final GridBagConstraints gridBagConstraints = new GridBagConstraints();
        this.setLayout(layout);
        gridBagConstraints.fill = 0;
        gridBagConstraints.anchor = 13;
        this.buildConstraints(gridBagConstraints, 0, 0, 1, 1, 10, 40);
        final Label label = new Label("Username:", 0);
        layout.setConstraints(label, gridBagConstraints);
        this.add(label);
        gridBagConstraints.fill = 2;
        this.buildConstraints(gridBagConstraints, 1, 0, 1, 1, 90, 0);
        layout.setConstraints(this.name = new TextField(), gridBagConstraints);
        this.name.addKeyListener(this);
        this.add(this.name);
        gridBagConstraints.fill = 0;
        gridBagConstraints.anchor = 13;
        this.buildConstraints(gridBagConstraints, 0, 1, 1, 1, 0, 40);
        final Label label2 = new Label("Password:", 0);
        layout.setConstraints(label2, gridBagConstraints);
        this.add(label2);
        gridBagConstraints.fill = 2;
        this.buildConstraints(gridBagConstraints, 1, 1, 1, 1, 0, 0);
        (this.psw = new TextField()).setEchoChar('*');
        layout.setConstraints(this.psw, gridBagConstraints);
        this.psw.addKeyListener(this);
        this.add(this.psw);
        gridBagConstraints.fill = 0;
        gridBagConstraints.anchor = 10;
        this.buildConstraints(gridBagConstraints, 0, 2, 2, 1, 0, 20);
        final Panel panel = new Panel();
        panel.setLayout(new GridLayout(1, 3, 20, 2));
        layout.setConstraints(panel, gridBagConstraints);
        this.add(panel);
        final Button button = new Button("Add");
        button.addActionListener(this);
        button.addKeyListener(this);
        panel.add(button);
        (this.list = new Button("List")).addActionListener(this);
        this.list.addKeyListener(this);
        panel.add(this.list);
        final Button button2 = new Button("Clear");
        button2.addActionListener(this);
        button2.addKeyListener(this);
        panel.add(button2);
        this.setSize(225, 125);
    }
    
    public void start() {
        this.name.setText("");
        this.name.requestFocus();
        this.psw.setText("");
        this.hash = new Hashtable(13);
        if (this.pswURL != null) {
            this.getUsers();
        }
        if (!this.hash.isEmpty()) {
            this.list.setEnabled(true);
            return;
        }
        this.list.setEnabled(false);
    }
    
    void buildConstraints(final GridBagConstraints gridBagConstraints, final int gridx, final int gridy, final int gridwidth, final int gridheight, final int n, final int n2) {
        gridBagConstraints.gridx = gridx;
        gridBagConstraints.gridy = gridy;
        gridBagConstraints.gridwidth = gridwidth;
        gridBagConstraints.gridheight = gridheight;
        gridBagConstraints.weightx = n;
        gridBagConstraints.weighty = n2;
        gridBagConstraints.ipadx = 0;
        gridBagConstraints.ipady = 0;
    }
    
    public Insets getInsets() {
        return new Insets(5, 5, 5, 5);
    }
    
    public void keyTyped(final KeyEvent keyEvent) {
    }
    
    public void keyPressed(final KeyEvent keyEvent) {
        if (!Character.isLetterOrDigit(keyEvent.getKeyChar()) && keyEvent.getKeyCode() != 10 && keyEvent.getKeyCode() != 16 && keyEvent.getKeyCode() != 37 && keyEvent.getKeyCode() != 39 && keyEvent.getKeyCode() != 127 && keyEvent.getKeyCode() != 9 && keyEvent.getKeyCode() != 8) {
            keyEvent.consume();
        }
    }
    
    public void keyReleased(final KeyEvent keyEvent) {
        if (keyEvent.getSource() instanceof Button) {
            final Button button = (Button)keyEvent.getSource();
            if (keyEvent.getKeyCode() == 10) {
                button.dispatchEvent(new ActionEvent(keyEvent.getSource(), 1001, button.getActionCommand()));
            }
        }
        else {
            final TextField textField = (TextField)keyEvent.getSource();
            if (keyEvent.getKeyCode() == 10) {
                textField.transferFocus();
            }
        }
    }
    
    boolean isValid(final String s, final int n, final int n2) {
        final StringBuffer sb = new StringBuffer(s);
        int n3 = 0;
        int i;
        for (i = 0; i < sb.length(); ++i) {
            if (Character.isDigit(sb.charAt(i))) {
                ++n3;
            }
        }
        return i >= n && n3 >= n2;
    }
    
    public void actionPerformed(final ActionEvent actionEvent) {
        final Button button = (Button)actionEvent.getSource();
        final Frame frame = MsgDialog.getFrame(this);
        if (button.getLabel().equals("Add")) {
            if (!this.isValid(this.name.getText(), 3, 0)) {
                new MsgDialog(frame, "Error", "User names must contain at least 3 letters/digits. Please try again.");
                this.name.requestFocus();
                return;
            }
            if (this.hash.containsKey(this.name.getText())) {
                new MsgDialog(frame, "Error", "Duplicate user name is already in the database. Please try again.");
                this.name.requestFocus();
                return;
            }
            if (this.isValid(this.psw.getText(), 6, 1)) {
                this.hash.put(this.name.getText(), jcrypt.crypt(this.name.getText().substring(0, 2), this.psw.getText()));
                this.name.setText("");
                this.name.requestFocus();
                this.psw.setText("");
                this.list.setEnabled(true);
                return;
            }
            new MsgDialog(frame, "Error", "Passwords must contain at least 5 letters plus 1 digit. Please try again.");
            this.psw.requestFocus();
        }
        else {
            if (button.getLabel().equals("List")) {
                String string = new String();
                final Enumeration<String> keys = this.hash.keys();
                while (keys.hasMoreElements()) {
                    final String s = keys.nextElement();
                    string = String.valueOf(string) + s + "  " + (String)this.hash.get(s) + "\n";
                }
                new MsgDialog(frame, "List", string);
                return;
            }
            if (button.getLabel().equals("Clear")) {
                this.name.setText("");
                this.name.requestFocus();
                this.psw.setText("");
            }
        }
    }
    
    void getUsers() {
        try {
            final BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(this.pswURL.openStream()));
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                this.hash.put(line.substring(0, line.indexOf(32)), line.substring(line.lastIndexOf(32) + 1));
            }
            bufferedReader.close();
        }
        catch (Exception ex) {
            System.out.println(ex);
        }
    }
}
