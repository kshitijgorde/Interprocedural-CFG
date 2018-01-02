// 
// Decompiled by Procyon v0.5.30
// 

package irc.channels;

import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.FlowLayout;
import java.awt.Component;
import java.awt.LayoutManager;
import java.awt.event.ActionEvent;
import java.awt.Frame;
import irc.EIRC;
import java.awt.Color;
import javax.swing.JButton;
import javax.swing.JColorChooser;
import javax.swing.JTabbedPane;
import java.awt.BorderLayout;
import javax.swing.JPanel;
import java.awt.event.ActionListener;
import javax.swing.JDialog;

public class AppletStyle extends JDialog implements ActionListener
{
    private JPanel panel1;
    private BorderLayout borderLayout1;
    private JTabbedPane panel;
    private JColorChooser foreground;
    private JColorChooser background;
    private JPanel footer;
    private JButton validate;
    private JButton cancel;
    private JButton defaultcolor;
    private Color forecolor;
    private Color bagrcolor;
    private EIRC eirc;
    
    public AppletStyle(final EIRC eirc, final String s, final boolean b) {
        super(eirc.getFrame(), s, b);
        this.panel1 = new JPanel();
        this.borderLayout1 = new BorderLayout();
        this.forecolor = null;
        this.bagrcolor = null;
        this.eirc = eirc;
        this.bagrcolor = EIRC.mainbg;
        try {
            this.setDefaultCloseOperation(2);
            this.jbInit();
            this.pack();
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
    }
    
    @Override
    public void actionPerformed(final ActionEvent actionEvent) {
        if (actionEvent.getSource().equals(this.validate)) {
            this.setForecolor(this.foreground.getColor());
            EIRC.mainfg = this.foreground.getColor();
            this.setBagrcolor(this.background.getColor());
            EIRC.mainbg = this.background.getColor();
            this.eirc.getAppletoption().update();
            this.bagrcolor = EIRC.mainbg;
            this.dispose();
            return;
        }
        if (actionEvent.getSource().equals(this.defaultcolor)) {
            this.eirc.setColorForeground(Color.black);
            this.foreground.setColor(Color.black);
            this.eirc.setColorBackground(new Color(205, 230, 255));
            this.background.setColor(new Color(205, 230, 255));
            return;
        }
        if (actionEvent.getSource().equals(this.cancel)) {
            EIRC.mainbg = this.bagrcolor;
            this.eirc.setColorBackground(this.bagrcolor);
            this.setForecolor(null);
            this.dispose();
        }
    }
    
    public void colorForeground(final Color color) {
    }
    
    public Color getBagrcolor() {
        return this.bagrcolor;
    }
    
    public Color getForecolor() {
        return this.forecolor;
    }
    
    private void jbInit() throws Exception {
        this.panel1.setLayout(this.borderLayout1);
        this.getContentPane().add(this.panel1);
        this.panel = new JTabbedPane();
        (this.footer = new JPanel()).setLayout(new FlowLayout());
        this.validate = new JButton("OK");
        this.cancel = new JButton("Annuler");
        this.defaultcolor = new JButton("par d\u00e9faut");
        this.footer.add(this.validate);
        this.footer.add(this.defaultcolor);
        this.footer.add(this.cancel);
        this.foreground = new JColorChooser();
        this.background = new JColorChooser();
        this.panel.addTab("Couleur de fond", this.background);
        this.panel.addTab("Couleur de texte", this.foreground);
        this.panel1.add(this.panel, "Center");
        this.panel1.add(this.footer, "South");
        this.validate.addActionListener(this);
        this.defaultcolor.addActionListener(this);
        this.cancel.addActionListener(this);
        final JColorChooser foreground = this.foreground;
        final EIRC eirc = this.eirc;
        foreground.setColor(EIRC.mainfg);
        this.foreground.getSelectionModel().addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(final ChangeEvent changeEvent) {
                AppletStyle.this.eirc.setColorForeground(AppletStyle.this.foreground.getColor());
            }
        });
        final JColorChooser background = this.background;
        final EIRC eirc2 = this.eirc;
        background.setColor(EIRC.mainbg);
        this.background.getSelectionModel().addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(final ChangeEvent changeEvent) {
                AppletStyle.this.eirc.setColorBackground(AppletStyle.this.background.getColor());
            }
        });
    }
    
    public void setBagrcolor(final Color bagrcolor) {
        this.bagrcolor = bagrcolor;
    }
    
    public void setForecolor(final Color forecolor) {
        this.forecolor = forecolor;
    }
    
    public void update() {
        final JColorChooser foreground = this.foreground;
        final EIRC eirc = this.eirc;
        foreground.setColor(EIRC.mainfg);
        final JColorChooser background = this.background;
        final EIRC eirc2 = this.eirc;
        background.setColor(EIRC.mainbg);
    }
}
