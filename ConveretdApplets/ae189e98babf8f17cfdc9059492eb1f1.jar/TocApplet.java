import java.awt.event.MouseAdapter;
import java.awt.event.KeyAdapter;
import java.awt.event.ComponentAdapter;
import java.awt.event.WindowAdapter;
import java.util.Date;
import java.awt.event.MouseEvent;
import java.util.Vector;
import java.awt.event.KeyEvent;
import java.awt.event.ComponentEvent;
import java.awt.Cursor;
import java.awt.event.ActionEvent;
import symantec.itools.awt.TreeNode;
import java.io.InputStream;
import java.awt.Image;
import java.io.DataInputStream;
import java.awt.event.WindowEvent;
import java.awt.Point;
import java.awt.Dimension;
import java.awt.event.MouseListener;
import java.awt.event.ComponentListener;
import java.awt.event.KeyListener;
import java.awt.event.ActionListener;
import java.awt.event.WindowListener;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.beans.PropertyVetoException;
import java.awt.Component;
import java.awt.Color;
import java.awt.LayoutManager;
import java.awt.BorderLayout;
import symantec.itools.awt.WrappingLabel;
import java.net.URL;
import java.applet.AppletContext;
import java.applet.Applet;
import java.awt.Button;
import java.awt.TextField;
import java.awt.List;
import symantec.itools.awt.TreeView;
import java.awt.Label;
import symantec.itools.awt.TabPanel;
import java.awt.Panel;
import java.awt.Frame;

// 
// Decompiled by Procyon v0.5.30
// 

public class TocApplet extends Frame
{
    boolean fComponentsAdjusted;
    Panel panel1;
    TabPanel tabPnl;
    Panel contentsPnl;
    Label label2;
    Label label3;
    Label label4;
    Panel treePnl;
    TreeView tocTree;
    Label promptLbl;
    Panel indexPnl;
    Panel panel3;
    List indexLst;
    Panel panel2;
    Label indexPromptLbl;
    TextField entryTxt;
    Label label9;
    Label label10;
    Label label11;
    Panel buttonPnl;
    Button displayBtn;
    Button cancelBtn;
    Applet parentBase;
    String[] indexArray;
    String[] urlArray;
    String searchPage;
    AppletContext browser;
    long lastMouseClick;
    URL homeURL;
    Label label1;
    Label label5;
    Label label6;
    Label label7;
    WrappingLabel searchLbl;
    Panel searchPnl;
    UpdateTree updater;
    JMProps props;
    HtmlViewer viewer;
    
    public TocApplet(final JMProps props) {
        this.fComponentsAdjusted = false;
        this.props = props;
        this.setLayout(new BorderLayout(0, 0));
        this.setVisible(false);
        this.setSize(this.insets().left + this.insets().right + 390, this.insets().top + this.insets().bottom + 350);
        this.setBackground(new Color(12632256));
        (this.panel1 = new Panel()).setLayout(new BorderLayout(0, 0));
        this.panel1.setBounds(this.insets().left, this.insets().top, 390, 317);
        this.add("Center", this.panel1);
        this.tabPnl = new TabPanel();
        try {
            this.tabPnl.setPanelLabels(new String[] { this.props.language.getProperty("contents", "Contents"), this.props.language.getProperty("index", "Index"), this.props.language.getProperty("search", "Search") });
        }
        catch (PropertyVetoException ex) {}
        try {
            this.tabPnl.setCurrentPanelNdx(1);
        }
        catch (PropertyVetoException ex2) {}
        ((Component)this.tabPnl).setBounds(0, 0, 390, 317);
        this.panel1.add("Center", (Component)this.tabPnl);
        (this.contentsPnl = new Panel()).setLayout(new BorderLayout(0, 0));
        this.contentsPnl.setVisible(false);
        this.contentsPnl.setBounds(12, 33, 366, 273);
        this.tabPnl.add((Component)this.contentsPnl);
        (this.label2 = new Label(" ")).setBounds(0, 0, 18, 249);
        this.contentsPnl.add("West", this.label2);
        (this.label3 = new Label(" ")).setBounds(348, 0, 18, 249);
        this.contentsPnl.add("East", this.label3);
        (this.label4 = new Label(" ")).setBounds(0, 249, 366, 24);
        this.contentsPnl.add("South", this.label4);
        (this.treePnl = new Panel()).setLayout(new BorderLayout(0, 0));
        this.treePnl.setBounds(18, 0, 330, 249);
        this.contentsPnl.add("Center", this.treePnl);
        (this.tocTree = new TreeView()).setLayout((LayoutManager)null);
        ((Component)this.tocTree).setBounds(0, 24, 330, 225);
        ((Component)this.tocTree).setBackground(new Color(16777215));
        this.treePnl.add("Center", (Component)this.tocTree);
        (this.promptLbl = new Label(this.props.language.getProperty("labels.toc_prompt", "Click a topic, and then click Display"))).setBounds(0, 0, 330, 24);
        this.treePnl.add("North", this.promptLbl);
        (this.indexPnl = new Panel()).setLayout(new BorderLayout(0, 0));
        this.indexPnl.setBounds(12, 33, 366, 273);
        this.tabPnl.add((Component)this.indexPnl);
        (this.panel3 = new Panel()).setLayout(new BorderLayout(0, 5));
        this.panel3.setBounds(18, 0, 330, 249);
        (this.panel2 = new Panel()).setLayout(new GridLayout(2, 1, 0, 0));
        this.panel2.setBounds(0, 0, 330, 48);
        this.panel3.add("North", this.panel2);
        (this.indexPromptLbl = new Label(this.props.language.getProperty("labels.index_prompt", "Type the first few letters of the word you are looking for:"))).setBounds(0, 0, 330, 24);
        this.panel2.add(this.indexPromptLbl);
        (this.entryTxt = new TextField()).setBounds(0, 24, 330, 24);
        this.panel2.add(this.entryTxt);
        this.indexPnl.add("Center", this.panel3);
        (this.indexLst = new List(0, false)).setBounds(0, 53, 330, 196);
        this.indexLst.setBackground(new Color(16777215));
        this.panel3.add("Center", this.indexLst);
        (this.label9 = new Label(" ")).setBounds(0, 0, 18, 249);
        this.indexPnl.add("West", this.label9);
        (this.label10 = new Label(" ")).setBounds(348, 0, 18, 249);
        this.indexPnl.add("East", this.label10);
        (this.label11 = new Label(" ")).setBounds(0, 249, 366, 24);
        this.indexPnl.add("South", this.label11);
        (this.buttonPnl = new Panel()).setLayout(new FlowLayout(2, 5, 5));
        this.buttonPnl.setBounds(this.insets().left, this.insets().top + 317, 390, 34);
        this.add("South", this.buttonPnl);
        (this.displayBtn = new Button()).setActionCommand("button");
        this.displayBtn.setLabel(this.props.language.getProperty("button.display", "Display"));
        this.displayBtn.setBounds(272, 5, 56, 24);
        this.displayBtn.setBackground(new Color(12632256));
        this.buttonPnl.add(this.displayBtn);
        (this.cancelBtn = new Button()).setActionCommand("button");
        this.cancelBtn.setLabel(this.props.language.getProperty("button.cancel", "Cancel"));
        this.cancelBtn.setBounds(333, 5, 52, 24);
        this.cancelBtn.setBackground(new Color(12632256));
        this.buttonPnl.add(this.cancelBtn);
        this.addWindowListener(new SymWindow());
        final SymAction symAction = new SymAction();
        this.displayBtn.addActionListener(symAction);
        this.tocTree.addActionListener((ActionListener)symAction);
        this.cancelBtn.addActionListener(symAction);
        final SymKey symKey = new SymKey();
        this.entryTxt.addKeyListener(symKey);
        final SymComponent symComponent = new SymComponent();
        this.indexPnl.addComponentListener(symComponent);
        this.indexLst.addActionListener(symAction);
        ((Component)this.tocTree).addMouseListener(new SymMouse());
        ((Component)this.tocTree).addKeyListener(symKey);
        this.addComponentListener(symComponent);
        try {
            this.tabPnl.setCurrentPanelNdx(0);
        }
        catch (PropertyVetoException ex3) {}
    }
    
    public TocApplet(final Applet parentBase, final String searchPage, final String s, final JMProps jmProps) {
        this(jmProps);
        this.parentBase = parentBase;
        this.browser = this.parentBase.getAppletContext();
        this.homeURL = this.parentBase.getDocumentBase();
        this.setTitle(this.props.language.getProperty("title.help", s));
        this.searchPage = searchPage;
        if (this.searchPage != null) {
            this.addSearchPanel();
        }
        this.updater = new UpdateTree(this.tocTree, 1);
        this.setBackground(this.props.background);
        this.setForeground(this.props.foreground);
        this.setFont(this.props.font);
        ((Component)this.tocTree).setBackground(Color.white);
        ((Component)this.tocTree).setForeground(Color.black);
        this.indexLst.setBackground(Color.white);
        this.indexLst.setForeground(Color.black);
        this.props.addWindow(this);
    }
    
    public synchronized void show() {
        this.move(10, 10);
        super.show();
    }
    
    public void addNotify() {
        final Dimension size = this.getSize();
        super.addNotify();
        if (this.fComponentsAdjusted) {
            return;
        }
        this.setSize(this.insets().left + this.insets().right + size.width, this.insets().top + this.insets().bottom + size.height);
        final Component[] components = this.getComponents();
        for (int i = 0; i < components.length; ++i) {
            final Point location = components[i].getLocation();
            location.translate(this.insets().left, this.insets().top);
            components[i].setLocation(location);
        }
        this.fComponentsAdjusted = true;
    }
    
    void Frame1_WindowClosing(final WindowEvent windowEvent) {
        this.hide();
    }
    
    void Frame1_WindowOpen(final WindowEvent windowEvent) {
        Image image = null;
        Image image2 = null;
        Image image3 = null;
        URL url = null;
        try {
            final InputStream resourceAsStream = this.getClass().getResourceAsStream("images/bookshut.gif");
            final byte[] array = new byte[resourceAsStream.available()];
            resourceAsStream.read(array);
            image = this.getToolkit().createImage(array);
            final InputStream resourceAsStream2 = this.getClass().getResourceAsStream("images/bookopen.gif");
            final byte[] array2 = new byte[resourceAsStream2.available()];
            resourceAsStream2.read(array2);
            image2 = this.getToolkit().createImage(array2);
            final InputStream resourceAsStream3 = this.getClass().getResourceAsStream("images/document.gif");
            final byte[] array3 = new byte[resourceAsStream3.available()];
            resourceAsStream3.read(array3);
            image3 = this.getToolkit().createImage(array3);
            url = new URL(this.parentBase.getDocumentBase(), "help/toc.txt");
            final URL url2 = new URL(this.parentBase.getDocumentBase(), "help/index.txt");
        }
        catch (Exception ex) {
            System.out.println("ERROR getting images: " + ex.toString());
        }
        if (this.tocTree.getCount() == 0) {
            try {
                TreeNode treeNode = null;
                int n = 1;
                int n2 = 0;
                String line;
                while ((line = new DataInputStream(url.openStream()).readLine()) != null) {
                    final tocParser tocParser = new tocParser(line);
                    final TreeNode node = tocParser.getNode();
                    final int level = tocParser.getLevel();
                    if (node.getDataObject() == null) {
                        node.setCollapsedImage(image);
                        node.setExpandedImage(image2);
                    }
                    else {
                        node.setCollapsedImage(image3);
                    }
                    if (n == 1) {
                        this.tocTree.append(node);
                        n = 0;
                        treeNode = node;
                        n2 = level;
                    }
                    else if (level == 0) {
                        this.tocTree.append(node);
                        treeNode = node;
                        n2 = level;
                    }
                    else if (level == n2) {
                        this.tocTree.insert(node, treeNode, 1);
                        treeNode = node;
                        n2 = level;
                    }
                    else if (level == n2 + 1) {
                        this.tocTree.insert(node, treeNode, 0);
                        treeNode = node;
                        n2 = level;
                    }
                    else {
                        if (level >= n2) {
                            continue;
                        }
                        TreeNode parent = treeNode;
                        TreeNode treeNode2 = null;
                        for (int n3 = n2 - level, i = 0; i < n3; ++i) {
                            treeNode2 = (parent = parent.getParent());
                        }
                        this.tocTree.insert(node, treeNode2, 1);
                        treeNode = node;
                        n2 = level;
                    }
                }
                if (!this.updater.isAlive()) {
                    (this.updater = new UpdateTree(this.tocTree, 3)).start();
                }
            }
            catch (Exception ex2) {
                System.out.println("ERROR-2: " + ex2.toString());
            }
        }
    }
    
    void displayBtn_Action(final ActionEvent actionEvent) {
        final int currentPanelNdx = this.tabPnl.getCurrentPanelNdx();
        if (currentPanelNdx == 0) {
            this.tocTree_displayDocument(true);
            return;
        }
        if (currentPanelNdx == 1) {
            this.indexLst_DblClicked(actionEvent);
        }
    }
    
    void tocTree_actionPerformed(final ActionEvent actionEvent) {
        if (!this.updater.isAlive()) {
            (this.updater = new UpdateTree(this.tocTree, 3)).start();
        }
    }
    
    void tocTree_displayDocument(final boolean b) {
        final TreeNode selectedNode = this.tocTree.getSelectedNode();
        if (selectedNode.getDataObject() != null) {
            try {
                final URL url = new URL(this.homeURL, (String)selectedNode.getDataObject());
                this.setCursor(Cursor.getPredefinedCursor(3));
                if (this.props.appletviewer) {
                    if (this.viewer == null) {
                        this.viewer = new HtmlViewer(url);
                        this.props.addWindow(this.viewer);
                    }
                    else {
                        this.viewer.show();
                        this.viewer.changeNewDocument(new HtmlDocument(url));
                    }
                }
                else {
                    this.browser.showDocument(url, "blank");
                }
                this.setCursor(Cursor.getPredefinedCursor(0));
                return;
            }
            catch (Exception ex) {
                System.out.println("ERROR: " + ex.toString());
                return;
            }
        }
        if (b) {
            if (!selectedNode.isExpanded()) {
                selectedNode.expand();
                if (!this.updater.isAlive()) {
                    (this.updater = new UpdateTree(this.tocTree, 3)).start();
                }
            }
            else {
                selectedNode.collapse();
                if (!this.updater.isAlive()) {
                    (this.updater = new UpdateTree(this.tocTree, 1)).start();
                }
            }
        }
    }
    
    void cancelBtn_Action(final ActionEvent actionEvent) {
        this.hide();
    }
    
    void searchPnl_ComponentShown(final ComponentEvent componentEvent) {
        try {
            this.searchLbl.setText(this.props.language.getProperty("labels.search", "The search facility for these documents has now been displayed in your browser window. Use the fields on the displayed web page to carry out your serach."));
            this.parentBase.getAppletContext().showDocument(new URL(this.searchPage), "docs");
        }
        catch (Exception ex) {
            System.out.println("Search page error: " + ex.toString());
        }
    }
    
    void entryTxt_KeyRelease(final KeyEvent keyEvent) {
        final String lowerCase = this.entryTxt.getText().toLowerCase();
        if (lowerCase.length() > 0) {
            String s = this.indexLst.getItem(0).toLowerCase();
            int n = 0;
            for (int length = this.indexArray.length, n2 = 0; n2 < length && lowerCase.compareTo(s) > 0; s = this.indexLst.getItem(n).toLowerCase(), ++n2) {
                ++n;
            }
            this.indexLst.makeVisible(n);
        }
    }
    
    void indexPnl_ComponentShown(final ComponentEvent componentEvent) {
        if (this.indexLst.getItemCount() == 0) {
            try {
                final URL url = new URL(this.parentBase.getDocumentBase(), "help/index.txt");
                new Vector();
                final Vector vector = new Vector<String>();
                String line;
                while ((line = new DataInputStream(url.openStream()).readLine()) != null) {
                    if (line.startsWith("<")) {
                        final int index = line.indexOf("\"");
                        vector.addElement(line.substring(index + 1, line.indexOf("\"", index + 1)));
                        final int index2 = line.indexOf(">");
                        this.indexLst.add(line.substring(index2 + 1, line.indexOf("<", index2 + 1)));
                    }
                }
                final int size = vector.size();
                this.indexArray = new String[size];
                this.urlArray = new String[size];
                for (int i = 0; i < size; ++i) {
                    this.indexArray[i] = this.indexLst.getItem(i);
                    this.urlArray[i] = vector.elementAt(i);
                }
            }
            catch (Exception ex) {
                System.out.println("ERROR-2: " + ex.toString());
            }
        }
    }
    
    void indexLst_DblClicked(final ActionEvent actionEvent) {
        this.setCursor(Cursor.getPredefinedCursor(3));
        final int selectedIndex = this.indexLst.getSelectedIndex();
        try {
            final URL url = new URL(this.parentBase.getDocumentBase(), this.urlArray[selectedIndex]);
            if (this.props.appletviewer) {
                if (this.viewer == null) {
                    this.viewer = new HtmlViewer(url);
                    this.props.addWindow(this.viewer);
                }
                else {
                    this.viewer.show();
                    this.viewer.changeNewDocument(new HtmlDocument(url));
                }
            }
            else {
                this.browser.showDocument(url, "blank");
            }
        }
        catch (Exception ex) {
            System.out.println("ERROR-2: " + ex.toString());
        }
        this.setCursor(Cursor.getPredefinedCursor(0));
    }
    
    void tocTree_mouseClicked(final MouseEvent mouseEvent) {
        final long time = new Date().getTime();
        final long n = time - this.lastMouseClick;
        this.lastMouseClick = time;
        if (n < 333L) {
            this.tocTree_displayDocument(false);
        }
    }
    
    void tocTree_keyPressed(final KeyEvent keyEvent) {
        if (KeyEvent.getKeyText(keyEvent.getKeyCode()).equalsIgnoreCase("Enter")) {
            this.tocTree_displayDocument(true);
            this.tocTree.redraw();
        }
    }
    
    void addSearchPanel() {
        (this.searchPnl = new Panel()).setLayout(new BorderLayout(0, 0));
        this.searchPnl.setBounds(12, 33, 366, 273);
        this.tabPnl.add((Component)this.searchPnl);
        (this.label1 = new Label("")).setBounds(0, 0, 366, 24);
        this.searchPnl.add("North", this.label1);
        (this.label5 = new Label(" ")).setBounds(0, 24, 18, 225);
        this.searchPnl.add("West", this.label5);
        (this.label6 = new Label(" ")).setBounds(348, 24, 18, 225);
        this.searchPnl.add("East", this.label6);
        (this.label7 = new Label(" ")).setBounds(0, 249, 366, 24);
        this.searchPnl.add("South", this.label7);
        ((Component)(this.searchLbl = new WrappingLabel())).setBounds(0, 0, 20, 40);
        this.searchPnl.add("Center", (Component)this.searchLbl);
        this.searchPnl.addComponentListener(new SymComponent());
    }
    
    class UpdateTree extends Thread
    {
        TreeView theTree;
        int num;
        
        public UpdateTree(final TreeView theTree, final int num) {
            this.theTree = theTree;
            this.num = num;
        }
        
        public void run() {
            int i = 0;
            while (i < this.num) {
                ++i;
                this.theTree.redraw();
                ((Component)this.theTree).repaint();
                try {
                    Thread.sleep(1000L);
                }
                catch (Exception ex) {
                    System.out.println("ERROR: " + ex.toString());
                }
            }
        }
    }
    
    class SymWindow extends WindowAdapter
    {
        public void windowOpened(final WindowEvent windowEvent) {
            if (windowEvent.getSource() == TocApplet.this) {
                TocApplet.this.Frame1_WindowOpen(windowEvent);
            }
        }
        
        public void windowClosing(final WindowEvent windowEvent) {
            if (windowEvent.getSource() == TocApplet.this) {
                TocApplet.this.Frame1_WindowClosing(windowEvent);
            }
        }
    }
    
    class SymAction implements ActionListener
    {
        public void actionPerformed(final ActionEvent actionEvent) {
            final Object source = actionEvent.getSource();
            if (source == TocApplet.this.displayBtn) {
                TocApplet.this.displayBtn_Action(actionEvent);
                return;
            }
            if (source == TocApplet.this.tocTree) {
                TocApplet.this.tocTree_actionPerformed(actionEvent);
                return;
            }
            if (source == TocApplet.this.cancelBtn) {
                TocApplet.this.cancelBtn_Action(actionEvent);
                return;
            }
            if (source == TocApplet.this.indexLst) {
                TocApplet.this.indexLst_DblClicked(actionEvent);
            }
        }
    }
    
    class SymComponent extends ComponentAdapter
    {
        public void componentShown(final ComponentEvent componentEvent) {
            final Object source = componentEvent.getSource();
            if (source == TocApplet.this.searchPnl) {
                TocApplet.this.searchPnl_ComponentShown(componentEvent);
                return;
            }
            if (source == TocApplet.this.indexPnl && TocApplet.this.indexLst.getItemCount() == 0) {
                TocApplet.this.indexPnl_ComponentShown(componentEvent);
            }
        }
    }
    
    class SymKey extends KeyAdapter
    {
        public void keyPressed(final KeyEvent keyEvent) {
            if (keyEvent.getSource() == TocApplet.this.tocTree) {
                TocApplet.this.tocTree_keyPressed(keyEvent);
            }
        }
        
        public void keyReleased(final KeyEvent keyEvent) {
            if (keyEvent.getSource() == TocApplet.this.entryTxt) {
                TocApplet.this.entryTxt_KeyRelease(keyEvent);
            }
        }
    }
    
    class SymMouse extends MouseAdapter
    {
        public void mouseClicked(final MouseEvent mouseEvent) {
            if (mouseEvent.getSource() == TocApplet.this.tocTree) {
                TocApplet.this.tocTree_mouseClicked(mouseEvent);
            }
        }
    }
}
