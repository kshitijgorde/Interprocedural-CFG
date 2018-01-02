import java.awt.event.ActionEvent;
import java.awt.event.ComponentEvent;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Color;
import java.awt.Component;
import java.awt.LayoutManager;
import java.awt.BorderLayout;
import java.awt.Insets;
import java.awt.CardLayout;
import java.util.Vector;
import java.awt.Container;
import java.awt.event.ActionListener;
import java.awt.event.ComponentListener;

// 
// Decompiled by Procyon v0.5.30
// 

public class WToolBar extends WPanel implements ComponentListener, ActionListener
{
    String I;
    ActionListener J;
    private WToolPane selector;
    private WViewPort selView;
    private Container selPanel;
    private Vector selGroup;
    private Container toolPanel;
    private Container cardPanel;
    private CardLayout cardLayout;
    private WViewPort currentSectionView;
    private Vector sectionNames;
    private WButton[] scrollButton;
    protected int Z;
    public final WButton itemButtonModel;
    public final WButton selectButtonModel;
    public Insets sectionPadding;
    public Insets itemSpacing;
    protected WImage[] C;
    
    public WToolBar() {
        this(0);
    }
    
    public WToolBar(final int n) {
        this.I = "";
        this.J = null;
        this.scrollButton = new WButton[4];
        this.itemButtonModel = new WButton(null, null);
        this.selectButtonModel = new WButton(null, null);
        this.sectionPadding = new Insets(4, 4, 4, 4);
        this.itemSpacing = new Insets(1, 1, 1, 1);
        this.C = new WImage[18];
        this.setLayout(new BorderLayout());
        this.selPanel = null;
        this.toolPanel = new LightPanel(new BorderLayout());
        this.cardLayout = new CardLayout();
        this.cardPanel = new LightPanel(this.cardLayout);
        this.toolPanel.add(this.cardPanel, "Center");
        this.add(this.toolPanel, "Center");
        this.sectionNames = new Vector();
        if (n == 1) {
            this.Z = 1;
        }
        else {
            this.Z = 0;
        }
        this.selector = new WToolPane(1, n, this.selectButtonModel);
        this.selector.doubleBuffering = true;
    }
    
    public final int getOrientation() {
        return this.Z;
    }
    
    public final WImage getImage(final int n) {
        if (n >= this.C.length) {
            return null;
        }
        WImage image = this.C[n];
        if (image == null && n > 5 && n <= 17) {
            image = this.getImage(n - 4);
        }
        return image;
    }
    
    public final void setImagesList(final Vector vector) {
        vector.copyInto(this.C = new WImage[Math.max(18, vector.size())]);
        this.initButtonImages(this.itemButtonModel, 3);
        this.initButtonImages(this.selectButtonModel, 7);
        this.selector.glueImage = this.getImage(1);
    }
    
    public final void setBackground(final Color background) {
        super.setBackground(background);
        this.setVisible(false);
        this.setVisible(true);
        this.repaint();
    }
    
    public final void update(final Graphics graphics) {
        if (this.isShowing()) {
            super.paint(graphics);
        }
    }
    
    public final Dimension getMinimumSize() {
        final Dimension preferredSize = this.getPreferredSize();
        if (this.Z == 0) {
            preferredSize.width = 0;
        }
        else {
            preferredSize.height = 0;
        }
        return preferredSize;
    }
    
    public final WToolPane addSection(final String s, final int n) {
        return this.addSection(s, s, n);
    }
    
    public final WToolPane addSection(final String s, final String title, final int n) {
        final boolean contains = this.sectionNames.contains(s);
        WToolPane section;
        if (contains) {
            section = this.getSection(s);
        }
        else {
            section = new WToolPane(n, this.Z, this.itemButtonModel);
        }
        section.setBgImage(this.getImage(2), 0);
        section.sepImage = this.getImage(0);
        section.padding = this.sectionPadding;
        section.itemMargin = this.itemSpacing;
        if (!contains) {
            section.setName(s);
            section.setTitle(title);
            final WViewPort currentSectionView = new WViewPort(section);
            currentSectionView.setName(s);
            final int size = this.sectionNames.size();
            if (size != 0) {
                if (size == 1) {
                    final String selectedSection = this.sectionNames.elementAt(0);
                    final WToolPane section2 = this.getSection(selectedSection);
                    this.addSelectorButton(section2.getName(), section2.getTitle());
                    this.setSelectedSection(selectedSection);
                }
                this.addSelectorButton(s, title);
            }
            else {
                this.currentSectionView = currentSectionView;
            }
            this.cardPanel.add(currentSectionView, s);
            this.sectionNames.addElement(s);
            currentSectionView.addComponentListener(this);
        }
        return section;
    }
    
    private void addSelectorButton(final String actionCommand, final String s) {
        if (this.selPanel == null) {
            this.selector.setBgImage(this.getImage(6), 0);
            this.selPanel = new LightPanel(new BorderLayout());
            (this.selView = new WViewPort(this.selector)).addComponentListener(this);
            this.selPanel.add(this.selView, "Center");
            if (this.Z == 0) {
                this.add(this.selPanel, "North");
            }
            else {
                this.add(this.selPanel, "West");
            }
            this.selGroup = new Vector();
        }
        final WButton wButton = new WButton(null, "button_" + actionCommand, s, 3);
        wButton.synchronize(this.selGroup, true);
        wButton.setActionCommand(actionCommand);
        wButton.addActionListener(this);
        this.selector.add(wButton);
    }
    
    public final WToolPane getSection(final String s) {
        return this.getSection(this.sectionNames.indexOf(s));
    }
    
    public final WToolPane getSection(final int n) {
        if (n >= 0 && n < this.cardPanel.getComponentCount()) {
            return (WToolPane)((WViewPort)this.cardPanel.getComponent(n)).getComponent(0);
        }
        return null;
    }
    
    public final WToolPane getSelector() {
        return this.selector;
    }
    
    public final void setSelectedSection(final String s) {
        if (this.sectionNames.size() > 0) {
            final int index = this.sectionNames.indexOf(s);
            if (index >= 0 && this.selector.getComponentCount() > index) {
                this.cardLayout.show(this.cardPanel, s);
                ((WButton)this.selector.getComponent(index)).setSelected(true, false);
                this.currentSectionView = (WViewPort)this.cardPanel.getComponent(index);
            }
        }
    }
    
    public final String getSelectedSection() {
        if (this.currentSectionView == null) {
            return null;
        }
        return this.currentSectionView.getName();
    }
    
    private void initButtonImages(final WButton wButton, final int n) {
        wButton.backImage = this.getImage(n);
        wButton.backPressed = this.getImage(n + 1);
        wButton.maskOver = this.getImage(n + 2);
    }
    
    public final void removeContent() {
        this.removeAll();
        this.selector.removeAll();
        if (this.selPanel != null) {
            this.selView.remove(this.selector);
        }
        this.selPanel = null;
        this.selView = null;
        if (this.selGroup != null) {
            this.selGroup.removeAllElements();
        }
        final Component[] components = this.cardPanel.getComponents();
        for (int i = 0; i < components.length; ++i) {
            final Container container = (Container)components[i];
            ((Container)container.getComponent(0)).removeAll();
            container.removeComponentListener(this);
        }
        this.cardPanel.removeAll();
        this.currentSectionView = null;
        this.sectionNames.removeAllElements();
        this.toolPanel = new LightPanel(new BorderLayout());
        this.cardLayout = new CardLayout();
        this.cardPanel = new LightPanel(this.cardLayout);
        this.toolPanel.add(this.cardPanel, "Center");
        this.add(this.toolPanel, "Center");
        this.scrollButton = new WButton[4];
    }
    
    private WButton createScrollButton(final int n) {
        final WAutomaticButton wAutomaticButton = new WAutomaticButton(this.getImage(n), null);
        this.initButtonImages(wAutomaticButton, n + 1);
        wAutomaticButton.pressDeltaX = 0;
        wAutomaticButton.repeatTime = 50L;
        wAutomaticButton.pressDeltaY = 0;
        wAutomaticButton.addActionListener(this);
        return wAutomaticButton;
    }
    
    private void scrollUpdate(final WViewPort wViewPort) {
        int n;
        Container container;
        if (wViewPort == this.selView) {
            n = 2;
            container = this.selPanel;
        }
        else {
            n = 0;
            container = this.toolPanel;
        }
        final Dimension size = wViewPort.getSize();
        boolean b;
        if (this.scrollButton[n] != null && this.scrollButton[n].isVisible()) {
            b = true;
            if (this.Z == 0) {
                final Dimension dimension = size;
                dimension.width += this.scrollButton[n].getSize().width + this.scrollButton[n + 1].getSize().width;
            }
            else {
                final Dimension dimension2 = size;
                dimension2.height += this.scrollButton[n].getSize().height + this.scrollButton[n + 1].getSize().height;
            }
        }
        else {
            b = false;
        }
        if (wViewPort.needScrolling(size)[this.Z]) {
            if (!b) {
                if (this.scrollButton[n] == null) {
                    final int n2 = 10 + n * 2;
                    this.scrollButton[n] = this.createScrollButton(n2);
                    this.scrollButton[n + 1] = this.createScrollButton(n2);
                    final WImage iconImage = this.scrollButton[n + 1].iconImage;
                    if (iconImage != null) {
                        this.scrollButton[n + 1].iconImage = iconImage.reflect(this.Z == 0, this.Z == 1);
                    }
                    if (this.Z == 0) {
                        container.add(this.scrollButton[n], "West");
                        container.add(this.scrollButton[n + 1], "East");
                    }
                    else {
                        container.add(this.scrollButton[n], "North");
                        container.add(this.scrollButton[n + 1], "South");
                    }
                }
                this.scrollButton[n].setVisible(true);
                this.scrollButton[n + 1].setVisible(true);
            }
        }
        else if (b) {
            this.scrollButton[n].setVisible(false);
            this.scrollButton[n + 1].setVisible(false);
        }
        container.validate();
    }
    
    public final void componentMoved(final ComponentEvent componentEvent) {
    }
    
    public final void componentHidden(final ComponentEvent componentEvent) {
    }
    
    public final void componentResized(final ComponentEvent componentEvent) {
        final WViewPort wViewPort = (WViewPort)componentEvent.getSource();
        if (wViewPort.isVisible()) {
            this.scrollUpdate(wViewPort);
        }
    }
    
    public final void componentShown(final ComponentEvent componentEvent) {
        this.scrollUpdate((WViewPort)componentEvent.getSource());
    }
    
    public final void actionPerformed(final ActionEvent actionEvent) {
        final Object source = actionEvent.getSource();
        int n;
        int n2;
        if (this.Z == 0) {
            n = 16;
            n2 = 0;
        }
        else {
            n = 0;
            n2 = 16;
        }
        if (source == this.scrollButton[0]) {
            this.currentSectionView.translateViewPort(-n, -n2);
        }
        else if (source == this.scrollButton[1]) {
            this.currentSectionView.translateViewPort(n, n2);
        }
        else if (source == this.scrollButton[2]) {
            this.selView.translateViewPort(-n, -n2);
        }
        else if (source == this.scrollButton[3]) {
            this.selView.translateViewPort(n, n2);
        }
        else {
            this.setSelectedSection(actionEvent.getActionCommand());
        }
    }
}
