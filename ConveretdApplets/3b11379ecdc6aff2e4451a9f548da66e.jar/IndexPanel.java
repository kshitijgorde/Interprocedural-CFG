import java.awt.Event;
import java.awt.image.ImageObserver;
import java.awt.Graphics;
import java.awt.Component;
import java.awt.LayoutManager;
import java.awt.BorderLayout;
import java.applet.Applet;
import java.net.URL;
import java.awt.Button;
import java.awt.List;
import java.awt.TextField;
import java.awt.Font;
import java.awt.Image;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Panel;

// 
// Decompiled by Procyon v0.5.30
// 

class IndexPanel extends Panel
{
    private HHCtrl m_applet;
    private DialogLayout m_Layout;
    private boolean m_fInitialized;
    private Dimension m_Size;
    private ElementList m_list;
    private Color m_backColor;
    private Image m_bgImage;
    private Font m_font;
    private Color m_foreColor;
    TextField txtUser;
    List lstIndex;
    Button btnDisplay;
    String m_targetFrame;
    private boolean m_isMatched;
    private int m_numMatched;
    private int m_lastLength;
    
    private void matchSeeAlso(final String s) {
        for (int i = 0; i < this.lstIndex.countItems(); ++i) {
            if (s.equalsIgnoreCase(((Element)this.m_list.elementAt(i)).m_text)) {
                this.lstIndex.select(i);
                this.lstIndex.makeVisible(i);
                this.lstIndex.requestFocus();
            }
        }
    }
    
    public void setBackgroundImage(final Image bgImage) {
        this.m_bgImage = bgImage;
    }
    
    boolean isInitialized() {
        return this.m_fInitialized;
    }
    
    public boolean loadFromHHC(final URL url) {
        final ElementList list = new ElementList(this.m_applet);
        list.addElement(new Element(this.m_applet.getString("idx.load.elementname"), 0, this.m_applet));
        this.setList(list);
        final ElementList list2 = new ElementList(this.m_applet);
        final SitemapParser sitemapParser = new SitemapParser(url, list2, this.m_applet);
        if (sitemapParser.success()) {
            this.m_targetFrame = sitemapParser.getFrame();
            this.setList(list2);
            this.m_applet.showStatus(this.m_applet.getString("idx.load.success"));
            this.repaint();
            this.txtUser.requestFocus();
            return true;
        }
        return false;
    }
    
    public void setBackground(final Color backColor) {
        this.m_backColor = backColor;
    }
    
    boolean CreateControls() {
        this.setLayout(new BorderLayout(0, 6));
        (this.txtUser = new TextField("")).setBackground(Color.white);
        this.txtUser.setEditable(true);
        this.txtUser.setFont(this.m_font);
        this.txtUser.setForeground(this.m_foreColor);
        this.add("North", this.txtUser);
        this.add("Center", this.lstIndex = new List(1, false));
        this.lstIndex.setBackground(Color.white);
        this.lstIndex.setForeground(this.m_foreColor);
        this.lstIndex.setFont(this.m_font);
        this.add("South", this.btnDisplay = new Button("  " + this.m_applet.getString("idx.display") + "  "));
        this.validate();
        return this.m_fInitialized = true;
    }
    
    public void paint(final Graphics graphics) {
        graphics.setColor(this.m_backColor);
        graphics.fillRect(0, 0, this.size().width, this.size().height);
        if (this.m_bgImage != null) {
            try {
                if (this.m_bgImage.getWidth(this) != -1 && this.m_bgImage.getHeight(this) != -1) {
                    for (int i = 0; i < this.size().width; i += this.m_bgImage.getWidth(this)) {
                        graphics.drawImage(this.m_bgImage, i, 0, this.m_backColor, this);
                        for (int j = 0; j < this.size().height; j += this.m_bgImage.getHeight(this)) {
                            graphics.drawImage(this.m_bgImage, i, j, this.m_backColor, this);
                        }
                    }
                }
            }
            catch (Exception ex) {}
        }
    }
    
    public String getFrame() {
        return this.m_targetFrame;
    }
    
    private void setList(final ElementList list) {
        this.m_list = list;
        if (this.lstIndex.countItems() > 0) {
            this.lstIndex.clear();
        }
        int i;
        for (i = 0; i < this.m_list.size(); ++i) {
            int j = 0;
            String string = "";
            while (j < list.elementAt(i).m_level) {
                string += "      ";
                ++j;
            }
            this.lstIndex.addItem(string + list.elementAt(i).m_text);
        }
        if (i > 0) {
            this.lstIndex.select(0);
        }
    }
    
    public void setFont(final Font font, final Color foreColor) {
        if (font != null && font instanceof Font) {
            this.m_font = font;
        }
        this.m_foreColor = foreColor;
    }
    
    private void activateItem() {
        if (this.m_list.elementAt(this.lstIndex.getSelectedIndex()).m_url.compareTo("") != 0 || this.m_list.elementAt(this.lstIndex.getSelectedIndex()).m_seeAlso.compareTo("") == 0) {
            this.postEvent(new Event(this, 1001, this.m_list.elementAt(this.lstIndex.getSelectedIndex())));
            return;
        }
        if (this.m_list.elementAt(this.lstIndex.getSelectedIndex()).m_seeAlso.compareTo("") != 0) {
            this.matchSeeAlso(this.m_list.elementAt(this.lstIndex.getSelectedIndex()).m_seeAlso);
        }
    }
    
    public boolean handleEvent(final Event event) {
        if ((event.target == this.btnDisplay && event.id == 1001) || (event.target == this.lstIndex && event.id == 1001) || (event.target == this.txtUser && event.id == 1001)) {
            this.activateItem();
            return true;
        }
        if (event.target == this.lstIndex && event.id == 701) {
            this.txtUser.setText(this.lstIndex.getSelectedItem().trim());
            this.m_isMatched = false;
            this.m_numMatched = 0;
            return true;
        }
        if (event.target == this.txtUser && event.id == 402) {
            this.matchText();
        }
        if (event.target == this.lstIndex && event.id == 402 && event.key == 10) {
            this.activateItem();
            return true;
        }
        return super.handleEvent(event);
    }
    
    IndexPanel(final HHCtrl applet) {
        this.m_backColor = Color.gray;
        this.m_foreColor = Color.black;
        this.m_applet = applet;
    }
    
    private void matchText() {
        int selectedIndex;
        if (this.m_isMatched && this.txtUser.getText().length() >= this.m_numMatched) {
            selectedIndex = this.lstIndex.getSelectedIndex();
        }
        else {
            selectedIndex = 0;
            this.m_isMatched = false;
            this.m_numMatched = 0;
        }
        for (int i = this.m_numMatched; i < this.txtUser.getText().length(); ++i) {
            int j;
            for (j = selectedIndex; j < this.lstIndex.countItems(); ++j) {
                if (((Element)this.m_list.elementAt(j)).m_text.toUpperCase().startsWith(this.txtUser.getText().substring(0, i + 1).toUpperCase()) && ((Element)this.m_list.elementAt(j)).m_level == 0) {
                    selectedIndex = j;
                    this.m_numMatched = i + 1;
                    this.lstIndex.select(selectedIndex);
                    this.lstIndex.makeVisible(selectedIndex);
                    this.txtUser.requestFocus();
                    this.m_isMatched = true;
                    break;
                }
            }
            if (j == this.lstIndex.countItems()) {
                break;
            }
        }
    }
}
