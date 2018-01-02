// 
// Decompiled by Procyon v0.5.30
// 

package jstella.desktop;

import java.awt.image.ImageObserver;
import javax.swing.ImageIcon;
import java.awt.Graphics2D;
import javax.swing.JPopupMenu;
import java.awt.Graphics;
import javax.swing.Icon;
import java.awt.Component;
import java.awt.Insets;
import java.awt.GridBagConstraints;
import java.awt.LayoutManager;
import java.awt.GridBagLayout;
import java.awt.event.MouseListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseAdapter;
import javax.swing.BorderFactory;
import java.io.IOException;
import javax.swing.JLabel;
import java.io.File;
import jstella.runner.JStellaGame;
import java.awt.image.BufferedImage;
import java.awt.Color;
import javax.swing.JPanel;

public class JStellaGamePanel extends JPanel
{
    private static final long serialVersionUID = -5578907624507551853L;
    private static final Color COLOR_SELECTED_MASK;
    private static final Color COLOR_ROMLESS_MASK;
    private static double myIconScaleFactor;
    private static boolean myShowTitle;
    private static BufferedImage mySelectedMask;
    private static BufferedImage myROMlessMask;
    private IfcGamePanelClient myClient;
    private JStellaGame myGame;
    private File myGameFile;
    private int myCurrentRanking;
    private int myPreviousRanking;
    private int myCurrentImageHash;
    private JLabel LabelIcon;
    private JLabel LabelTitle;
    
    public JStellaGamePanel(final IfcGamePanelClient aClient) {
        this.myClient = null;
        this.myGame = null;
        this.myGameFile = null;
        this.myCurrentRanking = 0;
        this.myPreviousRanking = 0;
        this.myCurrentImageHash = 0;
        this.myClient = aClient;
        this.initComponents();
    }
    
    public JStellaGamePanel(final IfcGamePanelClient aClient, final File aGameFile) throws IOException, ClassNotFoundException {
        this(aClient);
        this.setGameFile(aGameFile);
    }
    
    private void initComponents() {
        this.LabelTitle = new JLabel();
        this.LabelIcon = new JLabel();
        this.setBorder(BorderFactory.createBevelBorder(0));
        this.setDoubleBuffered(false);
        this.addMouseListener(new MouseAdapter() {
            public void mouseClicked(final MouseEvent evt) {
                JStellaGamePanel.this.formMouseClicked(evt);
            }
            
            public void mousePressed(final MouseEvent evt) {
                JStellaGamePanel.this.formMousePressed(evt);
            }
        });
        this.setLayout(new GridBagLayout());
        this.LabelTitle.setText("<Game Title>");
        this.LabelTitle.setFocusable(false);
        GridBagConstraints gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.insets = new Insets(5, 5, 5, 5);
        this.add(this.LabelTitle, gridBagConstraints);
        this.LabelIcon.setHorizontalAlignment(0);
        this.LabelIcon.setFocusable(false);
        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = 1;
        gridBagConstraints.weightx = 100.0;
        gridBagConstraints.weighty = 100.0;
        this.add(this.LabelIcon, gridBagConstraints);
    }
    
    private void formMouseClicked(final MouseEvent evt) {
        if (evt.getClickCount() == 2) {
            this.myClient.runGame(this);
        }
    }
    
    private void formMousePressed(final MouseEvent evt) {
        this.selectGame();
    }
    
    public void updateGamePanel() {
        String zTitle = this.myGame.toString();
        if (zTitle.length() > 20) {
            zTitle = zTitle.substring(0, 20);
        }
        this.LabelTitle.setText(zTitle);
        this.LabelTitle.setVisible(JStellaGamePanel.myShowTitle);
        this.setToolTipText(this.myGame.toString());
        final int zGameImageHash = getObjHash(this.myGame.getGraphicData());
        if (zGameImageHash != this.myCurrentImageHash) {
            this.updateGamePanelIcon();
        }
        this.repaint();
    }
    
    private static int getObjHash(final Object aObj) {
        if (aObj == null) {
            return 0;
        }
        return aObj.hashCode();
    }
    
    public boolean hasIcon() {
        return this.myGame.getGraphicData() != null && this.myGame.getGraphicData().length != 0;
    }
    
    public void updateGamePanelIcon() {
        if (this.myGame.getGraphicData() != null) {
            final JStellaGameIcon zIcon = new JStellaGameIcon(this.myGame.getGraphicData());
            this.LabelIcon.setText("");
            this.LabelIcon.setIcon(zIcon);
            this.myCurrentImageHash = this.myGame.getGraphicData().hashCode();
        }
        else {
            this.LabelIcon.setText("<NO IMAGE>");
            this.LabelIcon.setIcon(null);
            this.myCurrentImageHash = 0;
        }
        this.repaint();
    }
    
    protected boolean isSelected() {
        return this.myClient.isSelected(this);
    }
    
    protected boolean isROMless() {
        return this.myGame.getROMData() == null || this.myGame.getROMData().length == 0;
    }
    
    protected void selectGame() {
        this.myClient.setSelectedGamePanel(this);
    }
    
    protected void reloadGame(final File aRepositoryDir) throws ClassNotFoundException, IOException {
        final File zGameFile = new File(aRepositoryDir, this.myGame.getGameFilename());
        this.setGame(JStellaGame.loadGameFile(zGameFile));
    }
    
    protected Color getAssignedBackgroundColor() {
        if (this.isSelected()) {
            return this.myClient.getSelectedColor();
        }
        if (this.getGame().getROMData() == null) {
            return this.myClient.getROMlessColor();
        }
        return this.myClient.getRegularColor();
    }
    
    protected void setGame(final JStellaGame aGame) {
        this.myGame = aGame;
        this.updateGamePanel();
    }
    
    protected JStellaGame getGame() {
        return this.myGame;
    }
    
    protected void setGameFile(final File aGameFile) throws ClassNotFoundException, IOException {
        this.myGameFile = aGameFile;
        this.loadGame();
    }
    
    private void clearGame() {
        if (this.myGame != null) {
            this.myGame.dispose();
        }
        this.myGame = null;
    }
    
    private void loadGame() throws ClassNotFoundException, IOException {
        this.clearGame();
        this.setGame(JStellaGame.loadGameFile(this.myGameFile));
    }
    
    public static void setIconScaleFactor(final double aFactor) {
        JStellaGamePanel.myIconScaleFactor = aFactor;
    }
    
    public static double getIconScaleFactor() {
        return JStellaGamePanel.myIconScaleFactor;
    }
    
    public String toString() {
        return "" + this.getGame();
    }
    
    public static boolean getShowTitle() {
        return JStellaGamePanel.myShowTitle;
    }
    
    public static void setShowTitle(final boolean aShowTitle) {
        JStellaGamePanel.myShowTitle = aShowTitle;
    }
    
    protected void paintComponent(final Graphics g) {
        this.setBackground(this.getAssignedBackgroundColor());
        super.paintComponent(g);
    }
    
    public JPopupMenu getComponentPopupMenu() {
        if (this.myClient != null) {
            return this.myClient.getPopupMenu();
        }
        return super.getComponentPopupMenu();
    }
    
    static {
        COLOR_SELECTED_MASK = new Color(JStellaRepository.COLOR_SELECTED.getRed(), JStellaRepository.COLOR_SELECTED.getGreen(), JStellaRepository.COLOR_SELECTED.getBlue(), 128);
        COLOR_ROMLESS_MASK = new Color(JStellaRepository.COLOR_ROMLESS.getRed(), JStellaRepository.COLOR_ROMLESS.getGreen(), JStellaRepository.COLOR_ROMLESS.getBlue(), 128);
        JStellaGamePanel.myIconScaleFactor = 1.0;
        JStellaGamePanel.myShowTitle = true;
        JStellaGamePanel.mySelectedMask = null;
        JStellaGamePanel.myROMlessMask = null;
        Graphics2D z2D = null;
        JStellaGamePanel.mySelectedMask = new BufferedImage(20, 20, 2);
        z2D = JStellaGamePanel.mySelectedMask.createGraphics();
        z2D.setColor(JStellaGamePanel.COLOR_SELECTED_MASK);
        z2D.fillRect(0, 0, JStellaGamePanel.mySelectedMask.getWidth(), JStellaGamePanel.mySelectedMask.getHeight());
        z2D.dispose();
        JStellaGamePanel.myROMlessMask = new BufferedImage(20, 20, 2);
        z2D = JStellaGamePanel.myROMlessMask.createGraphics();
        z2D.setColor(JStellaGamePanel.COLOR_ROMLESS_MASK);
        z2D.fillRect(0, 0, JStellaGamePanel.myROMlessMask.getWidth(), JStellaGamePanel.myROMlessMask.getHeight());
        z2D.dispose();
    }
    
    public class JStellaGameIcon implements Icon
    {
        private byte[] myImageData;
        private int myOriginalWidth;
        private int myOriginalHeight;
        
        public JStellaGameIcon(final byte[] aImage) {
            this.myImageData = null;
            this.myOriginalWidth = 250;
            this.myOriginalHeight = 250;
            this.myImageData = aImage;
            this.determineDimensions();
        }
        
        private void determineDimensions() {
            final ImageIcon zIconSource = new ImageIcon(this.myImageData);
            this.myOriginalWidth = zIconSource.getIconWidth();
            this.myOriginalHeight = zIconSource.getIconHeight();
        }
        
        public void paintIcon(final Component c, final Graphics g, final int x, final int y) {
            final ImageIcon zIconSource = new ImageIcon(this.myImageData);
            this.myOriginalWidth = zIconSource.getIconWidth();
            this.myOriginalHeight = zIconSource.getIconHeight();
            final Graphics2D z2D = (Graphics2D)g;
            final int zDestX2 = x + this.getIconWidth();
            final int zDestY2 = y + this.getIconHeight();
            z2D.drawImage(zIconSource.getImage(), x, y, zDestX2, zDestY2, 0, 0, zIconSource.getIconWidth(), zIconSource.getIconHeight(), null);
        }
        
        public int getIconWidth() {
            return (int)(this.myOriginalWidth * JStellaGamePanel.getIconScaleFactor());
        }
        
        public int getIconHeight() {
            return (int)(this.myOriginalHeight * JStellaGamePanel.getIconScaleFactor());
        }
    }
    
    public interface IfcGamePanelClient
    {
        Color getRegularColor();
        
        Color getSelectedColor();
        
        Color getROMlessColor();
        
        boolean isSelected(final JStellaGamePanel p0);
        
        void setSelectedGamePanel(final JStellaGamePanel p0);
        
        JPopupMenu getPopupMenu();
        
        void runGame(final JStellaGamePanel p0);
    }
}
