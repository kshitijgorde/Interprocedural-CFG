import java.awt.MenuItem;
import java.awt.LayoutManager;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.net.MalformedURLException;
import java.net.URL;
import java.awt.event.ActionEvent;
import java.awt.Component;
import java.awt.Event;
import java.awt.Color;
import java.awt.Button;
import java.awt.PopupMenu;
import java.awt.event.ActionListener;
import java.applet.Applet;

// 
// Decompiled by Procyon v0.5.30
// 

public class TMenu extends Applet implements ActionListener
{
    PopupMenu popup;
    Button titleLabel;
    int maxWidth;
    int maxHeight;
    String webpage;
    String separator;
    String target;
    String tspacestr;
    String title;
    String pageURL;
    String[] holdPage;
    String[] holdURL;
    Color foreColor;
    Color backColor;
    int NUMBER_OF_URLS;
    int i;
    int bh;
    int tspace;
    
    public TMenu() {
        this.NUMBER_OF_URLS = 256;
        this.tspace = 0;
    }
    
    Color ReadColor(final String s, final Color color) {
        final String readText = this.ReadText(s);
        int n3;
        int n2;
        int n = n2 = (n3 = -1);
        if (readText != null) {
            if (readText.startsWith("#")) {
                if (readText.length() == 7) {
                    n2 = Integer.parseInt(readText.substring(1, 3), 16);
                    n = Integer.parseInt(readText.substring(3, 5), 16);
                    n3 = Integer.parseInt(readText.substring(5), 16);
                }
            }
            else {
                final int index = readText.indexOf(44);
                final int lastIndex = readText.lastIndexOf(44);
                if (index > 0 && lastIndex > 0 && index != lastIndex) {
                    n2 = Integer.parseInt(readText.substring(0, index));
                    n = Integer.parseInt(readText.substring(index + 1, lastIndex));
                    n3 = Integer.parseInt(readText.substring(lastIndex + 1));
                }
            }
        }
        return (n2 >= 0 && n2 <= 255 && n >= 0 && n <= 255 && n3 >= 0 && n3 <= 255) ? new Color(n2, n, n3) : color;
    }
    
    String ReadText(final String s) {
        String parameter = null;
        try {
            parameter = this.getParameter(s);
        }
        catch (Exception ex) {
            System.out.println(ex);
        }
        return parameter;
    }
    
    public boolean action(final Event event, final Object o) {
        if (event.target instanceof Button) {
            this.popup.show(this.titleLabel, 0, this.maxHeight - 3);
        }
        return true;
    }
    
    public void actionPerformed(final ActionEvent actionEvent) {
        final String actionCommand = actionEvent.getActionCommand();
        this.i = 0;
        while (this.i < this.NUMBER_OF_URLS) {
            if (actionCommand.equals(this.holdPage[this.i])) {
                try {
                    this.getAppletContext().showDocument(new URL(this.holdURL[this.i]), this.target);
                }
                catch (MalformedURLException ex) {
                    System.err.println(ex);
                }
            }
            ++this.i;
        }
    }
    
    public void init() {
        this.maxWidth = this.size().width;
        this.maxHeight = this.size().height;
        this.backColor = this.ReadColor("BACKGROUND", Color.white);
        this.foreColor = this.ReadColor("FOREGROUND", Color.white);
        this.setBackground(this.backColor);
        this.setForeground(this.foreColor);
        this.target = this.ReadText("TARGET");
        this.title = this.ReadText("TITLE");
        this.tspacestr = this.getParameter("TSPACE");
        if (this.tspacestr == null) {
            this.tspacestr = "";
        }
        else {
            this.tspace = Integer.parseInt(this.tspacestr);
            this.tspacestr = "";
            this.i = 1;
            while (this.i < this.tspace) {
                this.tspacestr = " " + this.tspacestr;
                ++this.i;
            }
        }
        final GridBagLayout layout = new GridBagLayout();
        final GridBagConstraints gridBagConstraints = new GridBagConstraints();
        this.setLayout(layout);
        (this.titleLabel = new Button(" ")).setSize(this.maxWidth, this.maxHeight);
        this.titleLabel.setLabel(String.valueOf(this.tspacestr) + this.title + this.tspacestr);
        gridBagConstraints.weightx = 0.0;
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        layout.setConstraints(this.titleLabel, gridBagConstraints);
        this.add(this.titleLabel);
        this.holdPage = new String[this.NUMBER_OF_URLS];
        this.holdURL = new String[this.NUMBER_OF_URLS];
        this.popup = new PopupMenu("MENULIST");
        this.i = 0;
        while (this.i < this.NUMBER_OF_URLS) {
            this.webpage = this.getParameter("page" + this.i);
            this.pageURL = this.getParameter("url" + this.i);
            this.separator = this.getParameter("separator" + this.i);
            if (this.webpage == null) {
                break;
            }
            if (this.pageURL == null) {
                break;
            }
            this.holdPage[this.i] = this.webpage;
            this.holdURL[this.i] = this.pageURL;
            final MenuItem menuItem = new MenuItem(this.holdPage[this.i]);
            menuItem.addActionListener(this);
            this.popup.add(menuItem);
            if (this.separator != null) {
                this.popup.addSeparator();
            }
            ++this.i;
        }
        this.add(this.popup);
    }
}
