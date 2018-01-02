// 
// Decompiled by Procyon v0.5.30
// 

package com.chess.applet.ui;

import javax.swing.JOptionPane;
import java.awt.event.KeyEvent;
import java.awt.event.KeyAdapter;
import com.chess.chessboard.settings.ModePanel;
import com.chess.chessboard.settings.PiecesPanel;
import com.chess.chessboard.settings.BoardShapePanel;
import com.chess.game.MoveUndoer;
import java.io.IOException;
import javax.swing.SwingUtilities;
import java.lang.reflect.Method;
import java.net.URI;
import java.awt.event.MouseListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseAdapter;
import java.awt.Cursor;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.GridLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Component;
import java.awt.FlowLayout;
import java.awt.LayoutManager;
import java.awt.BorderLayout;
import java.applet.AppletContext;
import java.net.MalformedURLException;
import com.chess.util.Util;
import java.net.URL;
import java.awt.Container;
import javax.swing.JApplet;
import java.awt.event.KeyListener;
import com.chess.chessboard.MoveListViewer;
import com.chess.chessboard.StatusViewer;
import javax.swing.JButton;
import com.chess.chessboard.Chessboard;
import javax.swing.JLabel;
import com.chess.game.ChessGame;
import com.chess.applet.GameController;
import java.awt.Window;
import javax.swing.JPanel;

public class MainPanel extends JPanel
{
    private SettingsDialog settingsDialog;
    private Window parentWindow;
    private GameController gameController;
    private String version;
    private String shortVersion;
    private ChessGame chessGame;
    private JPanel boardPanel;
    private JPanel buttonsPanel;
    private JLabel chessComLabel;
    private Chessboard chessboard;
    private JPanel easternSettingsPanel;
    private GameControlPanel gameControlPanel;
    private JPanel listAndSettingsPanel;
    private MoveListPanel moveListPanel;
    private JButton savePGNButton;
    private JButton settingsButton;
    private JPanel statusAndVersionPanel;
    private StatusPanel statusPanel;
    private JButton switchSidesButton;
    private JLabel versionLabel;
    
    public MainPanel() {
        this.initComponents();
        this.settingsDialog = SettingsDialog.createSettingsDialog(this.chessboard.getOwner(), this.chessboard);
        this.chessboard.setStatusViewer((StatusViewer)this.statusPanel);
        this.chessboard.setMoveListViewer((MoveListViewer)this.moveListPanel);
        final VersionViewer versionViewer = new VersionViewer();
        this.addKeyListener(versionViewer);
        this.chessboard.setFocusable(true);
        this.chessboard.requestFocus();
        this.chessboard.addKeyListener((KeyListener)versionViewer);
    }
    
    private JApplet getApplet() {
        Container container;
        for (container = this.getParent(); container != null && !(container instanceof JApplet); container = container.getParent()) {}
        final JApplet applet = (JApplet)container;
        return applet;
    }
    
    private boolean goToChessComSite() {
        final JApplet applet = this.getApplet();
        if (applet != null) {
            try {
                final AppletContext context = applet.getAppletContext();
                final URL url = new URL("http://www.chess.com");
                if (context != null) {
                    context.showDocument(url, "_blank");
                    return true;
                }
                return false;
            }
            catch (MalformedURLException ex1) {
                Util.getLogger((Class)MainPanel.class).error((Object)"error going to chess.com site", (Throwable)ex1);
            }
        }
        return false;
    }
    
    private void initComponents() {
        this.boardPanel = new JPanel();
        this.chessboard = new Chessboard();
        this.statusAndVersionPanel = new JPanel();
        this.statusPanel = new StatusPanel();
        this.versionLabel = new JLabel();
        this.easternSettingsPanel = new JPanel();
        this.gameControlPanel = new GameControlPanel();
        this.listAndSettingsPanel = new JPanel();
        this.moveListPanel = new MoveListPanel();
        this.buttonsPanel = new JPanel();
        this.switchSidesButton = new JButton();
        this.savePGNButton = new JButton();
        this.settingsButton = new JButton();
        this.chessComLabel = new JLabel();
        this.setOpaque(false);
        this.setLayout(new BorderLayout(5, 5));
        this.boardPanel.setOpaque(false);
        this.boardPanel.setLayout(new BorderLayout());
        this.chessboard.setOpaque(false);
        this.chessboard.setLayout((LayoutManager)new FlowLayout());
        this.boardPanel.add((Component)this.chessboard, "Center");
        this.statusAndVersionPanel.setMinimumSize(new Dimension(31, 50));
        this.statusAndVersionPanel.setOpaque(false);
        this.statusAndVersionPanel.setPreferredSize(new Dimension(31, 50));
        this.statusAndVersionPanel.setLayout(new BorderLayout());
        this.statusAndVersionPanel.add(this.statusPanel, "Center");
        this.versionLabel.setForeground(new Color(201, 201, 201));
        this.versionLabel.setHorizontalAlignment(2);
        this.versionLabel.setVerticalAlignment(3);
        this.statusAndVersionPanel.add(this.versionLabel, "South");
        this.boardPanel.add(this.statusAndVersionPanel, "South");
        this.add(this.boardPanel, "West");
        this.easternSettingsPanel.setOpaque(false);
        this.easternSettingsPanel.setLayout(new BorderLayout(0, 8));
        this.gameControlPanel.setOpaque(false);
        this.easternSettingsPanel.add(this.gameControlPanel, "North");
        this.listAndSettingsPanel.setOpaque(false);
        this.listAndSettingsPanel.setLayout(new BorderLayout(0, 5));
        this.listAndSettingsPanel.add(this.moveListPanel, "Center");
        this.buttonsPanel.setOpaque(false);
        this.buttonsPanel.setLayout(new GridLayout(3, 0, 0, 5));
        this.switchSidesButton.setText("Switch Sides");
        this.switchSidesButton.addActionListener(new ActionListener() {
            public void actionPerformed(final ActionEvent evt) {
                MainPanel.this.switchSidesButtonActionPerformed(evt);
            }
        });
        this.buttonsPanel.add(this.switchSidesButton);
        this.savePGNButton.setText("Show PGN");
        this.savePGNButton.addActionListener(new ActionListener() {
            public void actionPerformed(final ActionEvent evt) {
                MainPanel.this.savePGNButtonActionPerformed(evt);
            }
        });
        this.buttonsPanel.add(this.savePGNButton);
        this.settingsButton.setText("Settings");
        this.settingsButton.addActionListener(new ActionListener() {
            public void actionPerformed(final ActionEvent evt) {
                MainPanel.this.settingsButtonActionPerformed(evt);
            }
        });
        this.buttonsPanel.add(this.settingsButton);
        this.listAndSettingsPanel.add(this.buttonsPanel, "South");
        this.easternSettingsPanel.add(this.listAndSettingsPanel, "Center");
        this.chessComLabel.setHorizontalAlignment(0);
        this.chessComLabel.setIcon(new ImageIcon(this.getClass().getResource("/appletlogo.png")));
        this.chessComLabel.setCursor(Cursor.getPredefinedCursor(12));
        this.chessComLabel.addMouseListener(new MouseAdapter() {
            public void mouseClicked(final MouseEvent evt) {
                MainPanel.this.chessComLabelMouseClicked(evt);
            }
            
            public void mouseEntered(final MouseEvent evt) {
                MainPanel.this.chessComLabelMouseEntered(evt);
            }
        });
        this.easternSettingsPanel.add(this.chessComLabel, "Last");
        this.add(this.easternSettingsPanel, "East");
    }
    
    private void settingsButtonActionPerformed(final ActionEvent evt) {
        this.settingsDialog.setVisible(true);
    }
    
    private void chessComLabelMouseClicked(final MouseEvent evt) {
        if (!this.goToChessComSite()) {
            try {
                final Class desktopClass = Class.forName("java.awt.Desktop");
                final Method isDesktopSupported = desktopClass.getMethod("isDesktopSupported", (Class[])new Class[0]);
                final Method getDesktop = desktopClass.getMethod("getDesktop", (Class[])new Class[0]);
                final Method browse = desktopClass.getMethod("browse", URI.class);
                final URI uri = URI.create("http://www.chess.com");
                if (isDesktopSupported.invoke(null, new Object[0])) {
                    browse.invoke(getDesktop.invoke(null, new Object[0]), uri);
                }
            }
            catch (Exception ex) {}
        }
    }
    
    private void chessComLabelMouseEntered(final MouseEvent evt) {
    }
    
    private void savePGNButtonActionPerformed(final ActionEvent evt) {
        new Thread("AWT-Child (Save PGN)") {
            public void run() {
                final String pgn = MainPanel.this.gameController.getGamePGN();
                SwingUtilities.invokeLater(new Runnable() {
                    public void run() {
                        MainPanel.this.showPGNDialog(pgn);
                    }
                });
            }
        }.start();
    }
    
    private void switchSidesButtonActionPerformed(final ActionEvent evt) {
        this.moveListPanel.takeBackComputerMove();
        this.chessGame.switchSides();
        this.chessboard.switchSides();
    }
    
    public void setHumanTurn(final boolean humanTurn) {
        this.switchSidesButton.setEnabled(humanTurn);
    }
    
    public Chessboard getChessboard() {
        return this.chessboard;
    }
    
    public void setGameController(final GameController controller) {
        this.gameController = controller;
        this.gameControlPanel.setGameController(controller);
    }
    
    public void setResourcesData(final byte[] resourcesData) throws IOException {
        this.settingsDialog.loadResources(resourcesData);
    }
    
    public void setMoveUndoer(final MoveUndoer moveUndoer) {
        this.moveListPanel.setMoveUndoer(moveUndoer);
    }
    
    public BoardShapePanel getShapePanel() {
        return this.settingsDialog.getShapePanel();
    }
    
    public PiecesPanel getPiecesPanel() {
        return this.settingsDialog.getPiecesPanel();
    }
    
    public void apply() {
        this.settingsDialog.apply();
    }
    
    public ModePanel getModePanel() {
        return this.settingsDialog.getModePanel();
    }
    
    public MoveListPanel getMoveListPanel() {
        return this.moveListPanel;
    }
    
    public void setOwner(final Window parentWindow) {
        this.parentWindow = parentWindow;
        this.chessboard.setOwner(parentWindow);
    }
    
    private void showPGNDialog(final String pgn) {
        PGNDialog dialog = null;
        try {
            dialog = new PGNDialog(this.parentWindow);
            dialog.setLocationRelativeTo(this.parentWindow);
        }
        catch (Throwable ex) {
            dialog = new PGNDialog();
            dialog.setLocationRelativeTo(null);
        }
        finally {
            dialog.setPGN(pgn);
            dialog.setVisible(true);
        }
    }
    
    public void setFullVersion(final String version) {
        this.version = version;
    }
    
    public void setShortVersion(final String version) {
        this.shortVersion = version;
        this.settingsDialog.setVersion("Version: " + version);
    }
    
    public GameControlPanel getGameControlPanel() {
        return this.gameControlPanel;
    }
    
    public ChessGame getChessGame() {
        return this.chessGame;
    }
    
    public void setChessGame(final ChessGame game) {
        this.chessGame = game;
    }
    
    public void setScoreVisible(final boolean visible) {
        this.statusPanel.setScoreVisible(visible);
    }
    
    private class VersionViewer extends KeyAdapter
    {
        String word;
        int cursor;
        
        public VersionViewer() {
            this.word = "version";
            this.cursor = 0;
        }
        
        public void keyTyped(final KeyEvent e) {
            char keyChar = e.getKeyChar();
            if (Character.isUpperCase(keyChar)) {
                keyChar = Character.toLowerCase(keyChar);
            }
            final char nextChar = this.word.charAt(this.cursor);
            if (nextChar == keyChar) {
                ++this.cursor;
                if (this.cursor == this.word.length()) {
                    this.showVersionDialog();
                    this.cursor = 0;
                }
            }
            else {
                this.cursor = 0;
            }
        }
        
        private void showVersionDialog() {
            final JPanel panel = new JPanel(new BorderLayout(5, 5));
            panel.add(new JLabel("Version: "), "West");
            panel.add(new JLabel(MainPanel.this.version), "Center");
            JOptionPane.showMessageDialog(MainPanel.this, panel);
        }
    }
}
