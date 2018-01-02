import java.awt.Graphics;
import java.awt.event.ItemEvent;
import java.awt.event.ActionEvent;
import java.awt.FlowLayout;
import java.awt.Component;
import java.awt.GridLayout;
import java.awt.Panel;
import java.awt.LayoutManager;
import java.awt.BorderLayout;
import java.awt.Choice;
import java.awt.Button;
import java.awt.Color;
import java.awt.event.ItemListener;
import java.awt.event.ActionListener;
import java.applet.Applet;

// 
// Decompiled by Procyon v0.5.30
// 

public class Slate extends Applet implements ActionListener, ItemListener
{
    private Color color;
    private ColorChooser choose;
    private Button clear;
    private Choice selectChoice;
    private Choice mainChoice;
    private DefaultPanel display;
    private DrawingPanel draw;
    
    public Slate() {
        this.color = Color.black;
    }
    
    public void init() {
        this.setLayout(new BorderLayout());
        final Panel panel = new Panel();
        panel.setLayout(new BorderLayout());
        panel.setBackground(Color.black);
        panel.setForeground(Color.black);
        final Panel panel2 = new Panel();
        panel2.setLayout(new GridLayout(1, 2, 4, 4));
        panel2.setBackground(Color.black);
        this.display = new DefaultPanel();
        this.draw = new DrawingPanel();
        panel2.add(this.display);
        panel2.add(this.draw);
        final Panel panel3 = new Panel();
        panel3.setLayout(new FlowLayout(2));
        (this.clear = new Button("Clear")).addActionListener(this);
        panel3.add(this.clear);
        (this.mainChoice = new Choice()).add("ABC");
        this.mainChoice.add("abc");
        this.mainChoice.add("123");
        this.mainChoice.addItemListener(this);
        panel3.add(this.mainChoice);
        this.selectChoice = new Choice();
        for (int i = 0; i < 26; ++i) {
            this.selectChoice.add(String.valueOf((char)(65 + i)));
        }
        this.selectChoice.addItemListener(this);
        panel3.add(this.selectChoice);
        panel.add("West", this.choose = new ColorChooser(this.draw));
        panel.add("Center", panel2);
        panel.add("South", panel3);
        final Panel[] array = new Panel[4];
        for (int j = 0; j < array.length; ++j) {
            (array[j] = new Panel()).setBackground(new Color(196, 196, 196));
        }
        this.add("North", array[0]);
        this.add("South", array[1]);
        this.add("West", array[2]);
        this.add("East", array[3]);
        this.add("Center", panel);
    }
    
    public void actionPerformed(final ActionEvent actionEvent) {
        if (actionEvent.getActionCommand().equals("Clear")) {
            this.draw.setClear(true);
        }
    }
    
    public void itemStateChanged(final ItemEvent itemEvent) {
        final Object source = itemEvent.getSource();
        if (source == this.mainChoice) {
            final String selectedItem = this.mainChoice.getSelectedItem();
            if (selectedItem.equals("ABC")) {
                this.selectChoice.removeAll();
                for (int i = 0; i < 26; ++i) {
                    this.selectChoice.add(String.valueOf((char)(65 + i)));
                }
            }
            else if (selectedItem.equals("abc")) {
                this.selectChoice.removeAll();
                for (int j = 0; j < 26; ++j) {
                    this.selectChoice.add(String.valueOf((char)(97 + j)));
                }
            }
            else if (selectedItem.equals("123")) {
                this.selectChoice.removeAll();
                for (int k = 0; k < 10; ++k) {
                    this.selectChoice.add(String.valueOf((char)(48 + k)));
                }
            }
        }
        if (source == this.selectChoice) {
            this.display.setString(this.selectChoice.getSelectedItem().trim());
        }
        this.validate();
    }
    
    public void paint(final Graphics graphics) {
        graphics.drawRect(0, 0, this.getSize().width - 2, this.getSize().height - 2);
    }
    
    public void setColor(final Color color) {
        this.color = color;
    }
}
