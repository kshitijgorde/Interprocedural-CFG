import java.awt.event.WindowListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.ActionEvent;
import java.awt.Font;
import java.awt.Component;
import java.awt.Color;
import java.awt.LayoutManager;
import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.TextArea;
import java.awt.Label;
import java.awt.Frame;
import java.awt.Container;
import netscape.javascript.JSObject;
import java.util.ResourceBundle;
import java.awt.event.ActionListener;
import java.awt.Panel;

// 
// Decompiled by Procyon v0.5.30
// 

class MainContainer extends Panel implements ActionListener
{
    private final ResourceBundle rb;
    private JSObject win;
    private Container theParent;
    private Frame frame;
    private Label labTitle;
    private TextArea taEdit;
    private Button btnRun;
    private Button btnClear;
    private Button btnTgl;
    private Button btnEx;
    private Button btnHelp;
    
    public MainContainer(final JSObject win) {
        this.rb = ResourceBundle.getBundle(this.getClass().getName());
        this.win = win;
        this.setLayout(new BorderLayout());
        this.setBackground(Color.blue);
        (this.labTitle = new Label(this.rb.getString("PROG_TITLE"))).setForeground(Color.white);
        this.add(this.labTitle, "North");
        (this.taEdit = new TextArea()).setFont(new Font("Courier", 0, 14));
        this.taEdit.setBackground(Color.white);
        this.add(this.taEdit, "Center");
        final Panel panel = new Panel();
        this.add(panel, "South");
        (this.btnRun = new Button(this.rb.getString("BTN_RUN"))).addActionListener(this);
        panel.add(this.btnRun);
        (this.btnClear = new Button(this.rb.getString("BTN_CLEAR"))).addActionListener(this);
        panel.add(this.btnClear);
        (this.btnTgl = new Button(this.rb.getString("BTN_TGL_DETACH"))).addActionListener(this);
        panel.add(this.btnTgl);
        (this.btnEx = new Button(this.rb.getString("BTN_EX"))).addActionListener(this);
        panel.add(this.btnEx);
        (this.btnHelp = new Button(this.rb.getString("BTN_HELP"))).addActionListener(this);
        panel.add(this.btnHelp);
    }
    
    public void actionPerformed(final ActionEvent actionEvent) {
        final Object source = actionEvent.getSource();
        if (source == this.btnRun) {
            try {
                String s;
                if (this.taEdit.getText().length() != 0) {
                    s = this.taEdit.getText();
                }
                else {
                    s = this.rb.getString("MSG_NOSOURCE");
                }
                this.win.eval(s);
            }
            catch (Exception ex) {
                ex.printStackTrace();
            }
        }
        else if (source == this.btnClear) {
            this.taEdit.setText("");
        }
        else if (source == this.btnEx) {
            this.taEdit.setText(this.rb.getString("EX"));
        }
        else if (source == this.btnHelp) {
            try {
                this.win.eval(this.rb.getString("HELP_WIN"));
            }
            catch (Exception ex2) {
                ex2.printStackTrace();
            }
        }
        else if (actionEvent.getActionCommand().equals(this.rb.getString("BTN_TGL_DETACH"))) {
            if (this.theParent == null) {
                this.theParent = this.getParent();
            }
            this.theParent.remove(this);
            this.frame = new Frame(this.rb.getString("PROG_TITLE"));
            this.btnTgl.setLabel(this.rb.getString("BTN_TGL_ATTACH"));
            this.remove(this.labTitle);
            this.frame.add(this, "Center");
            this.frame.pack();
            this.frame.addWindowListener(new WindowAdapter() {
                public void windowClosing(final WindowEvent windowEvent) {
                    MainContainer.this.attach();
                }
            });
            this.frame.setVisible(true);
        }
        else if (actionEvent.getActionCommand().equals(this.rb.getString("BTN_TGL_ATTACH"))) {
            this.attach();
        }
    }
    
    private void attach() {
        this.frame.remove(this);
        this.btnTgl.setLabel(this.rb.getString("BTN_TGL_DETACH"));
        this.add(this.labTitle, "North");
        this.theParent.add(this, "Center");
        this.theParent.validate();
        this.frame.setVisible(false);
    }
}
