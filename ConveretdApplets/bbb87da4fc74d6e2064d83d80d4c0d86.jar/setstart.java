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
import java.awt.Button;
import java.awt.TextField;
import java.awt.event.KeyListener;
import java.awt.event.ActionListener;
import java.applet.Applet;

// 
// Decompiled by Procyon v0.5.30
// 

public class setstart extends Applet implements ActionListener, KeyListener
{
    protected TextField intext;
    protected TextField outtext;
    protected Button encode;
    private cipher c3;
    
    public void init() {
        this.setBackground(new Color(Color.white.getRGB()));
        this.setFont(new Font("SansSerif", 0, 14));
        final GridBagLayout layout = new GridBagLayout();
        final GridBagConstraints gridBagConstraints = new GridBagConstraints();
        this.setLayout(layout);
        gridBagConstraints.fill = 0;
        gridBagConstraints.anchor = 13;
        this.buildConstraints(gridBagConstraints, 0, 0, 1, 1, 5, 40);
        final Label label = new Label("INPUT:", 0);
        layout.setConstraints(label, gridBagConstraints);
        this.add(label);
        gridBagConstraints.fill = 2;
        this.buildConstraints(gridBagConstraints, 1, 0, 1, 1, 95, 0);
        layout.setConstraints(this.intext = new TextField(), gridBagConstraints);
        this.intext.addKeyListener(this);
        this.add(this.intext);
        gridBagConstraints.fill = 0;
        gridBagConstraints.anchor = 13;
        this.buildConstraints(gridBagConstraints, 0, 1, 1, 1, 0, 40);
        final Label label2 = new Label("OUTPUT:", 0);
        layout.setConstraints(label2, gridBagConstraints);
        this.add(label2);
        gridBagConstraints.fill = 2;
        this.buildConstraints(gridBagConstraints, 1, 1, 1, 1, 0, 0);
        layout.setConstraints(this.outtext = new TextField(), gridBagConstraints);
        this.outtext.addKeyListener(this);
        this.add(this.outtext);
        gridBagConstraints.fill = 0;
        gridBagConstraints.anchor = 10;
        this.buildConstraints(gridBagConstraints, 0, 2, 2, 1, 0, 20);
        final Panel panel = new Panel();
        panel.setLayout(new GridLayout(1, 2, 20, 2));
        layout.setConstraints(panel, gridBagConstraints);
        this.add(panel);
        (this.encode = new Button("Encode")).addActionListener(this);
        this.encode.addKeyListener(this);
        panel.add(this.encode);
        final Button button = new Button("Clear");
        button.addActionListener(this);
        button.addKeyListener(this);
        panel.add(button);
        this.setSize(550, 125);
    }
    
    public void start() {
        System.out.println("Starting setstart applet...");
        this.intext.setText("");
        this.intext.requestFocus();
        this.outtext.setText("");
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
    
    public void actionPerformed(final ActionEvent actionEvent) {
        final Button button = (Button)actionEvent.getSource();
        final Frame frame = MsgDialog.getFrame(this);
        if (!button.getLabel().equals("Encode")) {
            if (button.getLabel().equals("Clear")) {
                this.intext.setText("");
                this.intext.requestFocus();
                this.outtext.setText("");
            }
            return;
        }
        if (this.intext.getText().length() > 4) {
            final String key = this.c3.getKey(this.intext.getText());
            this.outtext.setText(String.valueOf(new Character((char)(key.length() + 100))) + key + this.c3.encode(this.intext.getText(), key));
            this.outtext.requestFocus();
            return;
        }
        new MsgDialog(frame, "Error", "Input URL must contain at least 5 characters. Please try again.");
        this.intext.requestFocus();
    }
    
    public setstart() {
        this.c3 = new cipher3();
    }
}
