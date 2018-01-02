// 
// Decompiled by Procyon v0.5.30
// 

package zaluc.geneo;

import java.awt.event.KeyEvent;
import java.awt.event.ItemEvent;
import java.awt.event.ActionEvent;
import java.awt.event.WindowEvent;
import java.awt.Dimension;
import java.awt.FontMetrics;
import java.awt.FlowLayout;
import java.awt.Panel;
import java.awt.Component;
import java.awt.LayoutManager;
import java.awt.BorderLayout;
import java.applet.AppletContext;
import java.awt.Frame;
import java.awt.Button;
import java.awt.event.KeyListener;
import java.awt.event.ItemListener;
import java.awt.event.ActionListener;
import java.awt.event.WindowListener;
import java.awt.Dialog;

class FindDlg extends Dialog implements WindowListener, ActionListener, ItemListener, KeyListener
{
    Button okButton;
    Button applyButton;
    Button cancelButton;
    Frame parent;
    TreeCanvas canvas;
    Person[] ourPeople;
    int ourPeopleCount;
    FindList list;
    MultiLineLabel details;
    int maxItemLen;
    
    public FindDlg(final Frame parent, final AppletContext appletContext, final String s, final TreeCanvas canvas, final PeopleList list) {
        super(parent, "Find Person", true);
        this.parent = parent;
        this.canvas = canvas;
        final int index = list.getCenterPerson().index;
        int ourPeopleCount = 0;
        final FontMetrics fontMetrics = this.getFontMetrics(this.getFont());
        final int count = list.getCount();
        this.ourPeople = new Person[count];
        this.ourPeopleCount = 0;
        this.setLayout(new BorderLayout(5, 5));
        (this.list = new FindList(Math.min(20, count))).addItemListener(this);
        for (int i = 0; i < count; ++i) {
            final Person person;
            if ((person = list.getPerson(i)) != null && !person.isBlank) {
                String s2;
                if (person.lastName != null) {
                    s2 = person.lastName;
                    if (person.firstName != null) {
                        s2 = String.valueOf(s2) + ", " + person.firstName;
                    }
                    if (person.lifeDates != null) {
                        s2 = String.valueOf(s2) + " (" + person.lifeDates + ")";
                    }
                }
                else if (person.firstName != null) {
                    s2 = person.firstName;
                    if (person.lifeDates != null) {
                        s2 = String.valueOf(s2) + " (" + person.lifeDates + ")";
                    }
                }
                else {
                    s2 = "(" + person.lifeDates + ")";
                }
                this.list.add(s2);
                this.maxItemLen = Math.max(this.maxItemLen, fontMetrics.stringWidth(s2));
                if (i == index) {
                    ourPeopleCount = this.ourPeopleCount;
                }
                this.ourPeople[this.ourPeopleCount] = person;
                ++this.ourPeopleCount;
            }
        }
        this.list.setWidth(this.maxItemLen + 20);
        this.list.makeVisible(ourPeopleCount);
        this.list.select(ourPeopleCount);
        this.details = new MultiLineLabel(this.ourPeople[ourPeopleCount].details, appletContext, s, 20, 20, 0);
        this.add("West", this.list);
        this.add("Center", this.details);
        final Panel panel = new Panel();
        panel.setLayout(new FlowLayout(1, 5, 5));
        panel.add(this.okButton = new Button("OK"));
        panel.add(this.applyButton = new Button("Apply"));
        panel.add(this.cancelButton = new Button("Cancel"));
        this.add("South", panel);
        this.addWindowListener(this);
        this.addKeyListener(this);
        this.list.addActionListener(this);
        this.list.addKeyListener(this);
        this.okButton.addActionListener(this);
        this.okButton.addKeyListener(this);
        this.applyButton.addActionListener(this);
        this.applyButton.addKeyListener(this);
        this.cancelButton.addActionListener(this);
        this.cancelButton.addKeyListener(this);
        final Dimension screenSize = this.getToolkit().getScreenSize();
        this.pack();
        final Dimension size = this.getSize();
        size.width = 640;
        this.setSize(size);
        this.setLocation((screenSize.width - this.getSize().width) / 2, (screenSize.height - this.getSize().height) / 2);
    }
    
    public void windowClosing(final WindowEvent windowEvent) {
        this.close();
    }
    
    public void windowOpened(final WindowEvent windowEvent) {
    }
    
    public void windowClosed(final WindowEvent windowEvent) {
    }
    
    public void windowIconified(final WindowEvent windowEvent) {
    }
    
    public void windowDeiconified(final WindowEvent windowEvent) {
    }
    
    public void windowActivated(final WindowEvent windowEvent) {
    }
    
    public void windowDeactivated(final WindowEvent windowEvent) {
    }
    
    public void actionPerformed(final ActionEvent actionEvent) {
        final Object source = actionEvent.getSource();
        if (source == this.okButton || source == this.list) {
            this.apply();
            this.close();
            return;
        }
        if (source == this.applyButton) {
            this.apply();
            return;
        }
        if (source == this.cancelButton) {
            this.close();
        }
    }
    
    public void itemStateChanged(final ItemEvent itemEvent) {
        final int selectedIndex = this.list.getSelectedIndex();
        if (selectedIndex >= 0 && selectedIndex < this.ourPeopleCount) {
            this.details.setLabel(this.ourPeople[selectedIndex].details);
        }
    }
    
    public void keyPressed(final KeyEvent keyEvent) {
        switch (keyEvent.getKeyCode()) {
            case 32: {
                this.apply();
            }
            case 10: {
                this.apply();
            }
            case 27: {
                this.close();
            }
            default: {}
        }
    }
    
    public void keyTyped(final KeyEvent keyEvent) {
    }
    
    public void keyReleased(final KeyEvent keyEvent) {
    }
    
    private void apply() {
        final int selectedIndex = this.list.getSelectedIndex();
        if (selectedIndex >= 0 && selectedIndex < this.ourPeopleCount) {
            this.canvas.setCenterPerson(this.ourPeople[selectedIndex]);
        }
    }
    
    private void close() {
        this.setVisible(false);
        this.dispose();
        this.parent.requestFocus();
    }
}
