import java.awt.event.ItemEvent;
import java.awt.Font;
import java.util.StringTokenizer;
import java.awt.LayoutManager;
import java.awt.GridLayout;
import java.awt.Color;
import java.net.URL;
import java.awt.event.ActionEvent;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.List;
import java.awt.Button;
import java.awt.event.ItemListener;
import java.awt.event.ActionListener;
import java.applet.Applet;

// 
// Decompiled by Procyon v0.5.30
// 

public class Nav99 extends Applet implements ActionListener, ItemListener
{
    String[][] urlArray;
    String[] target;
    Button[] button;
    List[] list;
    Dimension largeButtonSize;
    Component prevList;
    Object prevSource;
    int rows;
    int smallButtonHeight;
    int listHeightSize;
    boolean startSwitch;
    
    public Nav99() {
        this.startSwitch = true;
    }
    
    public void actionPerformed(final ActionEvent actionEvent) {
        int n = 0;
        boolean b = false;
        final Object source = actionEvent.getSource();
        if (this.prevList != null) {
            this.prevList.setVisible(false);
        }
        for (int i = 0; i < this.rows; ++i) {
            if (source == this.button[i] && this.list[i].getItemCount() == 1) {
                if (!this.largeButtonSize.equals(this.button[0].getSize())) {
                    for (int j = 0; j < this.rows; ++j) {
                        this.button[j].setBounds(0, n, this.largeButtonSize.width, this.largeButtonSize.height);
                        n += this.largeButtonSize.height;
                    }
                }
                b = true;
                try {
                    this.getAppletContext().showDocument(new URL(this.urlArray[i][0]), this.target[i]);
                }
                catch (Exception ex) {
                    this.showStatus("Error " + ex);
                }
                break;
            }
        }
        if (!b) {
            if (this.prevSource == source) {
                for (int k = 0; k < this.rows; ++k) {
                    this.button[k].setBounds(0, n, this.largeButtonSize.width, this.largeButtonSize.height);
                    n += this.largeButtonSize.height;
                }
                this.prevSource = null;
            }
            else {
                this.prevSource = source;
                for (int l = 0; l < this.rows; ++l) {
                    this.button[l].setBounds(0, n, this.largeButtonSize.width, this.smallButtonHeight);
                    n += this.smallButtonHeight;
                    if (source == this.button[l]) {
                        this.prevList = this.list[l];
                        this.add(this.list[l]);
                        this.list[l].setBounds(0, n, this.largeButtonSize.width, this.listHeightSize);
                        this.list[l].setVisible(true);
                        n += this.listHeightSize;
                    }
                }
            }
        }
    }
    
    public void init() {
        int n = 0;
        this.rows = Integer.parseInt(this.getParameter("rows"));
        this.urlArray = new String[this.rows][];
        this.target = new String[this.rows];
        this.button = new Button[this.rows];
        this.list = new List[this.rows];
        String parameter = this.getParameter("fontName");
        if (parameter == null) {
            parameter = "SansSerif";
        }
        final String parameter2 = this.getParameter("fontStyle");
        int int1;
        if (parameter2 == null) {
            int1 = 1;
        }
        else {
            int1 = Integer.parseInt(parameter2);
        }
        final String parameter3 = this.getParameter("fontSize");
        int int2;
        if (parameter3 == null) {
            int2 = 12;
        }
        else {
            int2 = Integer.parseInt(parameter3);
        }
        final String parameter4 = this.getParameter("buttonColor");
        Color background;
        if (parameter4 == null) {
            background = new Color(0);
        }
        else {
            background = new Color(Integer.parseInt(parameter4));
        }
        final String parameter5 = this.getParameter("fontColor");
        Color foreground;
        if (parameter5 == null) {
            foreground = new Color(16777215);
        }
        else {
            foreground = new Color(Integer.parseInt(parameter5));
        }
        this.setLayout(new GridLayout(this.rows, 1));
        for (int i = 0; i < this.rows; ++i) {
            final StringTokenizer stringTokenizer = new StringTokenizer(this.getParameter("menu" + i), ",");
            this.urlArray[i] = new String[stringTokenizer.countTokens()];
            (this.button[i] = new Button(stringTokenizer.nextToken())).setFont(new Font(parameter, int1, int2));
            this.button[i].setBackground(background);
            this.button[i].setForeground(foreground);
            this.add(this.button[i]);
            this.button[i].addActionListener(this);
            this.target[i] = stringTokenizer.nextToken();
            this.list[i] = new List();
            while (stringTokenizer.hasMoreTokens()) {
                this.list[i].addItem(stringTokenizer.nextToken());
                this.urlArray[i][n++] = stringTokenizer.nextToken();
            }
            this.list[i].addItemListener(this);
            n = 0;
        }
    }
    
    public void itemStateChanged(final ItemEvent itemEvent) {
        final List list = (List)itemEvent.getSource();
        final int selectedIndex = list.getSelectedIndex();
        if (itemEvent.getStateChange() == 1) {
            for (int i = 0; i < this.rows; ++i) {
                if (list == this.list[i]) {
                    try {
                        this.getAppletContext().showDocument(new URL(this.urlArray[i][selectedIndex]), this.target[i]);
                    }
                    catch (Exception ex) {
                        this.showStatus("Error " + ex);
                    }
                    break;
                }
            }
        }
    }
    
    public void start() {
        if (this.startSwitch) {
            this.largeButtonSize = this.button[0].getSize();
            this.smallButtonHeight = (int)(this.largeButtonSize.height / 1.4);
            this.listHeightSize = this.largeButtonSize.height * this.rows - this.smallButtonHeight * this.rows;
            this.startSwitch = false;
        }
        else {
            this.removeAll();
            for (int i = 0; i < this.rows; ++i) {
                this.add(this.button[i]);
            }
            this.prevSource = null;
        }
    }
}
