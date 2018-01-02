import java.net.URL;
import java.awt.Dimension;
import java.awt.event.MouseEvent;
import java.awt.Cursor;
import java.awt.Component;
import java.awt.Font;
import java.awt.Label;
import java.awt.Color;
import java.awt.LayoutManager;
import java.awt.Panel;
import java.util.Hashtable;
import java.awt.event.MouseListener;
import java.applet.Applet;

// 
// Decompiled by Procyon v0.5.30
// 

public class TableMenu extends Applet implements MouseListener
{
    Hashtable menuItems;
    Hashtable popupItems;
    String categoryColor;
    String categoryFontColor;
    String categoryFontType;
    int categoryFontSize;
    int categoryFontStyle;
    int categoryHeight;
    int categoryGap;
    int tableWidth;
    int tableBorderPad;
    String tableBorderColor;
    String menuColor;
    String menuFontColor;
    String menuHooverColor;
    int menuFontSize;
    String menuFontType;
    int menuFontStyle;
    int menuHeight;
    int menuCursor;
    String popupColor;
    String popupFontColor;
    String popupFontType;
    String popupHooverColor;
    int popupFontStyle;
    int popupFontSize;
    int popupIndent;
    int popupCursor;
    String backColor;
    String target;
    int NextCategory;
    int menuItemiY;
    int LabelWidth;
    Panel currentPanel;
    
    public TableMenu() {
        this.menuItems = new Hashtable();
        this.popupItems = new Hashtable();
        this.categoryColor = "#808080";
        this.categoryFontColor = "#FFFFFF";
        this.categoryFontType = "";
        this.categoryFontSize = 15;
        this.categoryFontStyle = 0;
        this.categoryHeight = 25;
        this.categoryGap = 30;
        this.tableWidth = 150;
        this.tableBorderPad = 2;
        this.tableBorderColor = "#000000";
        this.menuColor = "#F0FFF0";
        this.menuFontColor = "#0000FF";
        this.menuHooverColor = "#7BCCFF";
        this.menuFontSize = 10;
        this.menuFontType = "";
        this.menuFontStyle = 0;
        this.menuHeight = 15;
        this.menuCursor = 12;
        this.popupColor = "#CCCCCC";
        this.popupFontColor = "#000000";
        this.popupFontType = "";
        this.popupHooverColor = "#FF0033";
        this.popupFontStyle = 0;
        this.popupFontSize = 12;
        this.popupIndent = 40;
        this.popupCursor = 12;
        this.backColor = "#FFFFFF";
        this.target = "_self";
        this.NextCategory = 0;
        this.menuItemiY = this.tableBorderPad;
        this.LabelWidth = 0;
        this.currentPanel = null;
    }
    
    public void init() {
        final String parameter = this.getParameter("Author");
        if (parameter != null && parameter.equals("Richard Burton")) {
            this.loadSettings();
            System.out.println(this.LabelWidth + " " + this.tableWidth);
            this.setLayout(null);
            this.addMouseListener(this);
            int n = 0;
            this.getParameter("Category" + n);
            while (true) {
                ++n;
                final String parameter2 = this.getParameter("Category" + n);
                if (parameter2 == null || parameter2.equals("")) {
                    break;
                }
                this.menuItemiY = this.tableBorderPad;
                final Panel panel = new Panel();
                panel.setLayout(null);
                panel.setBackground(Color.decode(this.tableBorderColor));
                final Label label = new Label(parameter2);
                label.setFont(new Font(this.categoryFontType, this.categoryFontStyle, this.categoryFontSize));
                label.setForeground(Color.decode(this.categoryFontColor));
                label.setBackground(Color.decode(this.categoryColor));
                label.setBounds(this.tableBorderPad, this.menuItemiY, this.LabelWidth, this.categoryHeight);
                label.addMouseListener(this);
                panel.add(label);
                this.menuItemiY += this.categoryHeight + this.tableBorderPad;
                int n2 = 0;
                while (true) {
                    ++n2;
                    final String parameter3 = this.getParameter(parameter2 + "MenuItem" + n2);
                    final String parameter4 = this.getParameter(parameter2 + "MenuItem" + n2 + "Link");
                    if (parameter3 == null || parameter3.equals("")) {
                        break;
                    }
                    final Label label2 = new Label(parameter3);
                    label2.setBackground(Color.decode(this.menuColor));
                    label2.setFont(new Font(this.menuFontType, this.menuFontStyle, this.menuFontSize));
                    label2.setBounds(this.tableBorderPad, this.menuItemiY, this.LabelWidth, this.menuHeight);
                    label2.addMouseListener(this);
                    label2.setCursor(new Cursor(this.menuCursor));
                    panel.add(label2);
                    this.menuItemiY += this.menuHeight;
                    if (parameter4 != null) {
                        this.popupItems.put(label2, this.formatURL(parameter4));
                        label2.setForeground(Color.decode(this.popupFontColor));
                        label2.setCursor(new Cursor(this.popupCursor));
                    }
                    else {
                        label2.setForeground(Color.decode(this.menuFontColor));
                        int n3 = 1;
                        int n4 = 0;
                        final Panel currentPanel = new Panel();
                        (this.currentPanel = currentPanel).setLayout(null);
                        currentPanel.setBackground(Color.decode(this.menuColor));
                        currentPanel.setVisible(false);
                        this.add(currentPanel);
                        this.menuItems.put(label2, currentPanel);
                        while (true) {
                            final String parameter5 = this.getParameter(parameter2 + "MenuItem" + n2 + "Popup" + n3);
                            final String parameter6 = this.getParameter(parameter2 + "MenuItem" + n2 + "Popup" + n3 + "Link");
                            if (parameter5 == null || parameter5.equals("")) {
                                break;
                            }
                            final Label label3 = new Label(parameter5);
                            label3.setBackground(Color.decode(this.popupColor));
                            label3.setForeground(Color.decode(this.popupFontColor));
                            label3.setFont(new Font(this.popupFontType, this.popupFontStyle, this.popupFontSize));
                            currentPanel.add(label3);
                            label3.setBounds(0, n4, 150, this.menuHeight);
                            label3.addMouseListener(this);
                            label3.setCursor(new Cursor(this.popupCursor));
                            this.popupItems.put(label3, this.formatURL(parameter6));
                            n4 += this.menuHeight;
                            ++n3;
                        }
                        currentPanel.setSize(150, n4);
                    }
                }
                this.add(panel);
                panel.setBounds(0, this.NextCategory, this.tableWidth, this.menuItemiY + this.tableBorderPad);
                this.NextCategory += this.menuItemiY + this.categoryGap;
            }
        }
        else {
            this.add(new Label("By, Richard Burton"));
        }
    }
    
    void loadSettings() {
        final String parameter = this.getParameter("CategoryColor");
        final String parameter2 = this.getParameter("CategoryFontColor");
        final String parameter3 = this.getParameter("CategoryFontType");
        final String parameter4 = this.getParameter("CategoryFontSize");
        final String parameter5 = this.getParameter("CategoryFontStyle");
        final String parameter6 = this.getParameter("CategoryHeight");
        final String parameter7 = this.getParameter("CategoryGap");
        final String parameter8 = this.getParameter("TableWidth");
        final String parameter9 = this.getParameter("TableBorderPad");
        final String parameter10 = this.getParameter("TableBorderColor");
        final String parameter11 = this.getParameter("MenuBackColor");
        final String parameter12 = this.getParameter("MenuFontColor");
        final String parameter13 = this.getParameter("MenuHooverColor");
        final String parameter14 = this.getParameter("MenuFontSize");
        final String parameter15 = this.getParameter("MenuFontStyle");
        final String parameter16 = this.getParameter("MenuFontType");
        final String parameter17 = this.getParameter("MenuHeight");
        final String parameter18 = this.getParameter("MenuCursor");
        final String parameter19 = this.getParameter("PopupColor");
        final String parameter20 = this.getParameter("PopupFontColor");
        final String parameter21 = this.getParameter("PopupHooverColor");
        final String parameter22 = this.getParameter("PopupFontSize");
        final String parameter23 = this.getParameter("PopupFontStyle");
        final String parameter24 = this.getParameter("PopupFontType");
        final String parameter25 = this.getParameter("PopupCursor");
        final String parameter26 = this.getParameter("PopupIndent");
        final String parameter27 = this.getParameter("BackColor");
        final String parameter28 = this.getParameter("Target");
        if (parameter != null && !parameter.equals("")) {
            this.categoryColor = parameter;
        }
        if (parameter2 != null && !parameter2.equals("")) {
            this.categoryFontColor = parameter2;
        }
        if (parameter3 != null && !parameter3.equals("")) {
            this.categoryFontType = parameter3;
        }
        if (parameter4 != null && !parameter4.equals("")) {
            this.categoryFontSize = Integer.valueOf(parameter4);
        }
        if (parameter5 != null && !parameter5.equals("")) {
            this.categoryFontStyle = this.fontStyle(parameter5);
        }
        if (parameter6 != null && !parameter6.equals("")) {
            this.categoryHeight = Integer.valueOf(parameter6);
        }
        if (parameter7 != null && !parameter7.equals("")) {
            this.categoryGap = Integer.valueOf(parameter7);
        }
        if (parameter8 != null && !parameter8.equals("")) {
            this.tableWidth = Integer.valueOf(parameter8);
        }
        if (parameter9 != null && !parameter9.equals("")) {
            this.tableBorderPad = Integer.valueOf(parameter9);
        }
        if (parameter10 != null && !parameter10.equals("")) {
            this.tableBorderColor = parameter10;
        }
        if (parameter11 != null && !parameter11.equals("")) {
            this.menuColor = parameter11;
        }
        if (parameter12 != null && !parameter12.equals("")) {
            this.menuFontColor = parameter12;
        }
        if (parameter13 != null && !parameter13.equals("")) {
            this.menuHooverColor = parameter13;
        }
        if (parameter14 != null && !parameter14.equals("")) {
            this.menuFontSize = Integer.valueOf(parameter14);
        }
        if (parameter15 != null && !parameter15.equals("")) {
            this.menuFontStyle = this.fontStyle(parameter15);
        }
        if (parameter16 != null && !parameter16.equals("")) {
            this.menuFontType = parameter16;
        }
        if (parameter17 != null && !parameter17.equals("")) {
            this.menuHeight = Integer.valueOf(parameter17);
        }
        if (parameter18 != null && !parameter18.equals("")) {
            this.menuCursor = this.cursorType(parameter18);
        }
        if (parameter19 != null && !parameter19.equals("")) {
            this.popupColor = parameter19;
        }
        if (parameter20 != null && !parameter20.equals("")) {
            this.popupFontColor = parameter20;
        }
        if (parameter22 != null && !parameter22.equals("")) {
            this.popupFontSize = Integer.valueOf(parameter22);
        }
        if (parameter23 != null && !parameter23.equals("")) {
            this.popupFontStyle = this.fontStyle(parameter23);
        }
        if (parameter24 != null && !parameter24.equals("")) {
            this.popupFontType = parameter24;
        }
        if (parameter25 != null && !parameter25.equals("")) {
            this.popupCursor = this.cursorType(parameter25);
        }
        if (parameter21 != null && !parameter21.equals("")) {
            this.popupHooverColor = parameter21;
        }
        if (parameter26 != null && !parameter26.equals("")) {
            this.popupIndent = Integer.valueOf(parameter26);
        }
        if (parameter27 != null && !parameter27.equals("")) {
            this.setBackground(Color.decode(parameter27));
        }
        else {
            this.setBackground(Color.decode(this.backColor));
        }
        if (parameter28 != null && !parameter28.equals("")) {
            this.target = parameter28;
        }
        this.LabelWidth = this.tableWidth - this.tableBorderPad * 2;
    }
    
    int fontStyle(final String s) {
        switch (s.charAt(0)) {
            case 'I':
            case 'i': {
                return 2;
            }
            case 'B':
            case 'b': {
                return 1;
            }
            default: {
                return 0;
            }
        }
    }
    
    int cursorType(final String s) {
        if (s.equalsIgnoreCase("crosshair")) {
            return 1;
        }
        if (s.equalsIgnoreCase("wait")) {
            return 3;
        }
        if (s.equalsIgnoreCase("text")) {
            return 2;
        }
        return 12;
    }
    
    public String formatURL(String string) {
        if (string.toLowerCase().indexOf("http://") == -1) {
            if (string.charAt(0) != '\\' || string.charAt(0) != '/') {
                string = "/" + string;
            }
            return "http://" + this.getDocumentBase().getHost() + string;
        }
        return string;
    }
    
    public void mouseClicked(final MouseEvent mouseEvent) {
        final Object source = mouseEvent.getSource();
        if (((Label)source).getClass().getName().indexOf("Label") != -1) {
            final Object value = this.popupItems.get(source);
            if (value != null) {
                this.showStatus((String)value);
            }
        }
    }
    
    public void mouseEntered(final MouseEvent mouseEvent) {
        final Object source = mouseEvent.getSource();
        if (((Label)source).getClass().getName().indexOf("Label") != -1) {
            final Label label = (Label)source;
            final Object value = this.menuItems.get(label);
            if (value != null) {
                final Panel currentPanel = (Panel)value;
                label.setForeground(Color.decode(this.menuHooverColor));
                final Dimension size = currentPanel.getSize();
                this.currentPanel.setVisible(false);
                (this.currentPanel = currentPanel).setVisible(true);
                this.currentPanel.setBounds(this.popupIndent, label.getLocation().y + label.getParent().getLocation().y + this.menuHeight - 1, size.width, size.height);
            }
            else if (this.currentPanel != label.getParent()) {
                final String s = this.popupItems.get(label);
                if (s != null) {
                    label.setForeground(Color.decode(this.popupHooverColor));
                    this.showStatus(s);
                }
                this.currentPanel.setVisible(false);
            }
            else {
                label.setForeground(Color.decode(this.popupHooverColor));
                this.showStatus((String)this.popupItems.get(label));
            }
        }
        else {
            this.currentPanel.setVisible(false);
        }
    }
    
    public void mouseExited(final MouseEvent mouseEvent) {
        final Object source = mouseEvent.getSource();
        if (((Label)source).getClass().getName().indexOf("Label") != -1) {
            final Label label = (Label)source;
            if (this.popupItems.get(label) != null) {
                label.setForeground(Color.decode(this.popupFontColor));
            }
            else if (this.menuItems.get(label) != null) {
                label.setForeground(Color.decode(this.menuFontColor));
            }
        }
    }
    
    public void mousePressed(final MouseEvent mouseEvent) {
        final Object source = mouseEvent.getSource();
        if (((Label)source).getClass().getName().indexOf("Label") != -1) {
            final String s = this.popupItems.get(source);
            if (s != null) {
                try {
                    this.getAppletContext().showDocument(new URL(s), this.target);
                }
                catch (Exception ex) {}
            }
        }
    }
    
    public void mouseReleased(final MouseEvent mouseEvent) {
    }
}
