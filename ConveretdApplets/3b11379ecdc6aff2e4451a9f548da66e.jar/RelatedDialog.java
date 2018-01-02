import java.awt.Event;
import java.awt.Dimension;
import java.awt.Canvas;
import java.awt.GridLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Panel;
import java.awt.LayoutManager;
import java.awt.BorderLayout;
import java.awt.List;
import java.awt.Label;
import java.awt.Button;
import java.awt.Frame;
import java.util.Vector;
import java.awt.Dialog;

// 
// Decompiled by Procyon v0.5.30
// 

class RelatedDialog extends Dialog implements Runnable
{
    DialogLayout m_Layout;
    boolean m_fInitialized;
    HHCtrl m_applet;
    String m_targetFrame;
    Vector m_itemList;
    private Thread m_callingThread;
    Frame m_parent;
    public static final int ACTION_PENDING = 0;
    public static final int ACTION_DISPLAY = 1;
    public static final int ACTION_CANCEL = 2;
    int status;
    Button btnDisplay;
    Button btnCancel;
    Label IDC_STATIC1;
    List lstItems;
    
    boolean isInitialized() {
        return this.m_fInitialized;
    }
    
    boolean CreateControls() {
        int intValue = 0;
        int intValue2 = 0;
        if (this.m_fInitialized) {
            return false;
        }
        try {
            intValue = Integer.valueOf(this.m_applet.getString("rel.dlgwidth"));
            intValue2 = Integer.valueOf(this.m_applet.getString("rel.dlgheight"));
        }
        catch (Exception ex) {
            if (intValue == 0) {
                intValue = 294;
            }
            if (intValue2 == 0) {
                intValue2 = 238;
            }
        }
        this.resize(intValue, intValue2);
        this.setLayout(new BorderLayout(10, 7));
        final Panel panel = new Panel();
        panel.setLayout(new BorderLayout(4, 5));
        panel.add("North", new Label(this.m_applet.getString("rel.label"), 0));
        panel.add("Center", this.lstItems = new List(1, false));
        this.lstItems.setBackground(Color.white);
        final Panel panel2 = new Panel();
        panel2.setLayout(new BorderLayout(0, 5));
        final Panel panel3 = new Panel();
        panel3.setLayout(new GridLayout(1, 2, 8, 4));
        panel3.add(this.btnDisplay = new Button("  " + this.m_applet.getString("rel.display") + "  "));
        panel3.add(this.btnCancel = new Button("  " + this.m_applet.getString("rel.cancel") + "  "));
        panel2.add("East", panel3);
        panel2.add("South", new Canvas());
        panel.add("South", panel2);
        this.add("North", new Canvas());
        this.add("South", new Canvas());
        this.add("Center", panel);
        this.add("West", new Canvas());
        this.add("East", new Canvas());
        return this.m_fInitialized = true;
    }
    
    public void setCallingThread(final Thread callingThread) {
        this.m_callingThread = callingThread;
    }
    
    RelatedDialog(final String title, final String targetFrame, final HHCtrl applet, final Frame parent) {
        super(parent, title, true);
        this.m_parent = parent;
        this.m_applet = applet;
        this.m_targetFrame = targetFrame;
        this.setResizable(false);
        this.setBackground(Color.lightGray);
        this.setTitle(title);
    }
    
    void setItems(final Vector itemList) {
        if (this.lstItems.countItems() > 0) {
            this.lstItems.clear();
        }
        for (int i = 0; i < itemList.size(); ++i) {
            final String s = itemList.elementAt(i);
            if (s.indexOf(";") > 0) {
                this.lstItems.addItem(s.substring(0, s.indexOf(";")));
            }
        }
        if (itemList.size() > 0) {
            this.lstItems.select(0);
        }
        this.m_itemList = itemList;
    }
    
    public int getStatus() {
        return this.status;
    }
    
    public void show() {
        this.status = 0;
        final String property = System.getProperty("java.vendor");
        final Dimension screenSize = this.getToolkit().getScreenSize();
        if (property.indexOf("Microsoft") != -1) {
            final Dimension size = this.size();
            this.move(screenSize.width / 2 - size.width / 2, screenSize.height / 2 - size.height / 2);
            super.show();
            this.move(screenSize.width / 2 - size.width / 2, screenSize.height / 2 - size.height / 2);
            this.resize(size);
        }
        else {
            this.move(screenSize.width / 2 - this.size().width / 2, screenSize.height / 2 - this.size().height / 2);
            super.show();
            this.move(screenSize.width / 2 - this.size().width / 2, screenSize.height / 2 - this.size().height / 2);
        }
        this.lstItems.requestFocus();
    }
    
    public void run() {
        this.m_applet.showRelated();
    }
    
    public boolean handleEvent(final Event event) {
        if ((event.target == this.btnDisplay && event.id == 1001) || (event.target == this.lstItems && event.id == 1001)) {
            this.status = 1;
            this.hide();
            this.m_parent.requestFocus();
            this.m_callingThread.resume();
            return true;
        }
        if ((event.target == this.btnCancel && event.id == 1001) || (event.id == 402 && event.key == 27)) {
            this.status = 2;
            this.hide();
            this.m_parent.requestFocus();
            this.m_callingThread.resume();
            return true;
        }
        if (event.id == 201) {
            this.status = 2;
            this.hide();
            this.m_parent.requestFocus();
            this.m_callingThread.resume();
            return true;
        }
        if (event.target == this.lstItems && event.id == 402 && event.key == 10) {
            this.status = 1;
            this.hide();
            this.m_parent.requestFocus();
            this.m_callingThread.resume();
            return true;
        }
        return super.handleEvent(event);
    }
}
