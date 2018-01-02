// 
// Decompiled by Procyon v0.5.30
// 

package XMLConsumer;

import java.net.MalformedURLException;
import hhapplet.URLFileHandler;
import java.awt.Dimension;
import java.awt.Label;
import hhapplet.ResourceLib;
import java.awt.LayoutManager;
import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Panel;
import java.awt.Font;
import hhapplet.BsscFontFixPatch;
import java.awt.Toolkit;
import java.awt.Color;
import java.awt.Event;
import java.awt.Point;
import java.net.URL;
import java.util.Vector;
import hhapplet.IActionSink;
import java.awt.List;
import java.awt.Button;
import java.awt.Frame;

public class SecondaryDialog extends Frame
{
    private Button m_btnDisplay;
    private Button m_btnCancel;
    private List m_list;
    private IActionSink m_accepter;
    private Vector m_vEntries;
    private URL m_baseURL;
    private String m_target;
    private boolean m_bIsIE3;
    private Point m_pntPosition;
    
    public boolean gotFocus(final Event event, final Object o) {
        if (event.target == this) {
            this.m_list.requestFocus();
            return true;
        }
        return super.gotFocus(event, o);
    }
    
    public void showTopic() {
        this.setBackground(new Color(192, 192, 192));
        if (!System.getProperty("os.name").startsWith("Windows") && !System.getProperty("os.name").startsWith("Mac OS")) {
            this.show();
        }
        final int n = 300;
        final int n2 = 240;
        final Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        final int n3 = screenSize.width / 2 - n / 2;
        final int n4 = screenSize.height / 2 - n2 / 2;
        if (!System.getProperty("os.name").startsWith("Windows") && !System.getProperty("os.name").startsWith("Mac OS")) {
            this.reshape(n3, n4, n, n2);
        }
        else {
            this.resize(n, n2);
        }
        this.m_pntPosition = new Point(n3, n4);
        if (System.getProperty("java.vendor").startsWith("Microsoft") && System.getProperty("java.version").startsWith("1.0")) {
            this.m_bIsIE3 = true;
        }
        this.m_list.setFont(new Font(BsscFontFixPatch.GetFontName(), 0, BsscFontFixPatch.GetFontSize()));
        for (int i = 0; i < this.m_vEntries.size(); ++i) {
            this.m_list.addItem(((Topic)this.m_vEntries.elementAt(i)).getName());
        }
        this.m_list.select(0);
        final Panel panel = new Panel();
        panel.add(this.m_btnDisplay);
        panel.add(this.m_btnCancel);
        this.setLayout(new BorderLayout(5, 5));
        this.m_list.setBackground(Color.white);
        this.add("North", new Label("   " + ResourceLib.GetRes("RelateTopicListPrompt")));
        this.add("Center", this.m_list);
        this.add("South", panel);
        if (System.getProperty("os.name").startsWith("Windows") || System.getProperty("os.name").startsWith("Mac OS")) {
            this.reshape(n3, n4, n, n2);
            this.move(n3, n4);
            this.setResizable(false);
            this.show();
            return;
        }
        this.toFront();
    }
    
    public SecondaryDialog(final IActionSink accepter, final URL baseURL, final String target, final Vector vEntries) {
        super(ResourceLib.GetRes("TopicsFound"));
        this.m_btnDisplay = null;
        this.m_btnCancel = null;
        this.m_list = null;
        this.m_bIsIE3 = false;
        this.m_accepter = accepter;
        this.m_vEntries = vEntries;
        this.m_baseURL = baseURL;
        this.m_btnDisplay = new Button(ResourceLib.GetRes("Display"));
        this.m_btnCancel = new Button(ResourceLib.GetRes("Cancel"));
        this.m_list = new List();
        this.m_target = target;
    }
    
    public List getList() {
        return this.m_list;
    }
    
    protected void gotoSelectedIndex() {
        final int selectedIndex = this.m_list.getSelectedIndex();
        if (selectedIndex != -1) {
            try {
                URL url = URLFileHandler.makeURL(this.m_baseURL, this.m_vEntries.elementAt(selectedIndex).getURL(), null);
                final String string = url.toString();
                if (string.indexOf("file:/\\\\") == 0) {
                    url = new URL("file://" + string.substring(8));
                }
                final Vector<String> vector = new Vector<String>();
                vector.addElement(url.toString());
                vector.addElement(this.m_target);
                this.m_accepter.accept(vector);
            }
            catch (MalformedURLException ex) {
                ex.printStackTrace();
            }
            this.closeDialog();
            this.dispose();
        }
    }
    
    public void show() {
        if (this.m_bIsIE3) {
            final Dimension size = this.size();
            this.reshape(this.m_pntPosition.x, this.m_pntPosition.y, size.width, size.height);
            super.show();
            this.reshape(this.m_pntPosition.x, this.m_pntPosition.y, size.width, size.height);
        }
        else {
            super.show();
        }
        this.m_list.requestFocus();
    }
    
    public boolean action(final Event event, final Object o) {
        if (event.target == this.m_btnDisplay || event.target == this.m_list) {
            this.gotoSelectedIndex();
            return true;
        }
        if (event.target == this.m_btnCancel) {
            this.closeDialog();
            return true;
        }
        return false;
    }
    
    public void closeDialog() {
        this.dispose();
    }
    
    public boolean handleEvent(final Event event) {
        if (System.getProperty("java.version").startsWith("1.1.5") && System.getProperty("java.vendor").startsWith("Netscape") && event.id == 401 && event.key == 10 && (event.target == this.m_btnDisplay || event.target == this.m_btnCancel)) {
            this.action(event, event.target);
            return true;
        }
        if (event.id == 201) {
            this.closeDialog();
            this.dispose();
        }
        else {
            if (event.target == this.m_list && event.key == 10 && event.id == 401) {
                this.gotoSelectedIndex();
                return true;
            }
            if (event.key == 27 && event.id == 402) {
                this.closeDialog();
                this.dispose();
            }
        }
        return super.handleEvent(event);
    }
}
