import java.io.Reader;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.awt.event.WindowEvent;
import java.awt.AWTEvent;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.awt.Insets;
import java.awt.Dimension;
import java.awt.Point;
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
import java.awt.event.WindowListener;
import java.awt.event.KeyListener;
import java.awt.event.ActionListener;
import java.awt.Frame;

// 
// Decompiled by Procyon v0.5.30
// 

public class PswFrame extends Frame implements ActionListener, KeyListener, WindowListener
{
    private final int MAX_ATTEMPTS = 3;
    private TextField name;
    private TextField psw;
    private Button login;
    private Hashtable hash;
    private int attempt;
    private boolean valid;
    private password app;
    private URL pswfile;
    
    public PswFrame(final password app, final URL pswfile) {
        super("Login");
        this.valid = false;
        this.app = app;
        this.pswfile = pswfile;
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
        panel.setLayout(new GridLayout(1, 2, 20, 2));
        layout.setConstraints(panel, gridBagConstraints);
        this.add(panel);
        (this.login = new Button("Login")).addActionListener(this);
        this.login.addKeyListener(this);
        this.login.setEnabled(false);
        panel.add(this.login);
        final Button button = new Button("Cancel");
        button.addActionListener(this);
        button.addKeyListener(this);
        panel.add(button);
        this.addWindowListener(this);
        this.setSize(225, 175);
        this.setResizable(false);
        final Dimension screenSize = this.getToolkit().getScreenSize();
        this.setLocation(new Point(Math.round((screenSize.width - 225) / 2), Math.round((screenSize.height - 175) / 2)));
        this.setVisible(true);
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
        return new Insets(20, 20, 20, 20);
    }
    
    public void keyTyped(final KeyEvent keyEvent) {
    }
    
    public void keyPressed(final KeyEvent keyEvent) {
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
    
    public void windowOpened(final WindowEvent windowEvent) {
        if (this.hasUsers()) {
            this.login.setEnabled(true);
            this.name.requestFocus();
            return;
        }
        new MsgDialog(this, "Error", "Unable to locate user database.");
        this.setVisible(false);
    }
    
    public void windowActivated(final WindowEvent windowEvent) {
    }
    
    public void windowDeactivated(final WindowEvent windowEvent) {
    }
    
    public void windowIconified(final WindowEvent windowEvent) {
        this.setVisible(false);
    }
    
    public void windowDeiconified(final WindowEvent windowEvent) {
    }
    
    public void windowClosing(final WindowEvent windowEvent) {
        this.setVisible(false);
    }
    
    public void windowClosed(final WindowEvent windowEvent) {
    }
    
    public void actionPerformed(final ActionEvent actionEvent) {
        final Button button = (Button)actionEvent.getSource();
        if (!button.getLabel().equals("Login")) {
            if (button.getLabel().equals("Cancel")) {
                this.setVisible(false);
            }
            return;
        }
        if (this.hash.containsKey(this.name.getText())) {
            if (this.hash.get(this.name.getText()).equals(jcrypt.crypt(this.name.getText().substring(0, 2), this.psw.getText()))) {
                this.valid = true;
                this.setVisible(false);
                return;
            }
            new MsgDialog(this, "Error", "You entered an invalid password. Please try again.");
            if (++this.attempt < 3) {
                this.psw.requestFocus();
                return;
            }
            this.setVisible(false);
        }
        else {
            new MsgDialog(this, "Error", "You entered an invalid username. Please try again.");
            if (++this.attempt < 3) {
                this.name.requestFocus();
                return;
            }
            this.setVisible(false);
        }
    }
    
    public boolean hasUsers() {
        try {
            final BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(this.pswfile.openStream()));
            this.hash = new Hashtable(13);
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                this.hash.put(line.substring(0, line.indexOf(32)), line.substring(line.lastIndexOf(32) + 1));
            }
            bufferedReader.close();
        }
        catch (Exception ex) {
            System.out.println(ex);
            return false;
        }
        return true;
    }
    
    public boolean isValid() {
        return this.valid;
    }
}
