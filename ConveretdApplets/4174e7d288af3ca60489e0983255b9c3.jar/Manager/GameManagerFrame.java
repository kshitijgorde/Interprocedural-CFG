// 
// Decompiled by Procyon v0.5.30
// 

package Manager;

import Go.GoGameListener;
import java.awt.event.ActionEvent;
import Go.GoPosition;
import java.awt.Component;
import java.awt.LayoutManager;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.BorderLayout;
import java.awt.Label;
import java.awt.Button;
import java.awt.Panel;
import Goban.GobanView;
import Go.GoGame;
import java.util.Vector;
import java.awt.MenuItem;
import java.awt.Menu;
import java.awt.MenuBar;
import java.awt.event.ActionListener;
import java.awt.Frame;

public class GameManagerFrame extends Frame implements ActionListener
{
    MenuBar Menu;
    Menu menu1;
    MenuItem menuItem1;
    Menu menu2;
    MenuItem menuItem2;
    MenuItem menuItem3;
    protected Vector listeners;
    private GameManager gameManager;
    private GoGame game;
    private GobanView gobanView;
    private Panel panel1;
    Button btnFirstMove;
    Button btnPrevMove;
    Button btnNextMove;
    Button btnLastMove;
    SettingsDialog settingsDialog;
    Label statusBar;
    Panel panel2;
    Panel panel3;
    BorderLayout borderLayout1;
    BorderLayout borderLayout2;
    
    public GameManagerFrame() {
        this.Menu = new MenuBar();
        this.menu1 = new Menu();
        this.menuItem1 = new MenuItem();
        this.menu2 = new Menu();
        this.menuItem2 = new MenuItem();
        this.menuItem3 = new MenuItem();
        this.listeners = new Vector();
        this.gameManager = null;
        this.game = null;
        this.gobanView = null;
        this.panel1 = new Panel();
        this.btnFirstMove = new Button("|<<");
        this.btnPrevMove = new Button("<");
        this.btnNextMove = new Button(">");
        this.btnLastMove = new Button(">>|");
        this.settingsDialog = null;
        this.statusBar = new Label();
        this.panel2 = new Panel();
        this.panel3 = new Panel();
        this.borderLayout1 = new BorderLayout();
        this.borderLayout2 = new BorderLayout();
        System.out.println("GameManagerFrame constructor started");
        this.settingsDialog = new SettingsDialog(this, "Settings...", true);
        this.addActionListener(this);
        this.gameManager = new GameManager();
        this.getSettingsDialog().addActionListener(this);
        try {
            this.jbInit();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        final Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        final Dimension frameSize = this.getSize();
        if (frameSize.height > screenSize.height) {
            frameSize.height = screenSize.height;
        }
        if (frameSize.width > screenSize.width) {
            frameSize.width = screenSize.width;
        }
        this.setLocation((screenSize.width - frameSize.width) / 2, (screenSize.height - frameSize.height) / 2);
        this.setVisible(true);
        this.settingsDialog.pack();
    }
    
    public Dimension getMinimumSize() {
        return new Dimension(224, 300);
    }
    
    public Dimension getPreferredSize() {
        return this.getMinimumSize();
    }
    
    public SettingsDialog getSettingsDialog() {
        return this.settingsDialog;
    }
    
    public void addActionListener(final ActionListener l) {
        this.listeners.addElement(l);
    }
    
    public void removeActionListener(final ActionListener l) {
        this.listeners.removeElement(l);
    }
    
    public static void main(final String[] args) {
        new GameManagerFrame();
    }
    
    private void jbInit() throws Exception {
        this.setSize(new Dimension(188, 303));
        this.setMenuBar(this.Menu);
        this.menu1.setLabel("Game");
        this.menuItem1.setLabel("Settings");
        this.menuItem1.addActionListener(new 1());
        this.menu2.setLabel("Exit");
        this.menuItem2.setLabel("Quit");
        this.menuItem2.addActionListener(new 2());
        this.Menu.add(this.menu1);
        this.Menu.add(this.menu2);
        this.menu1.add(this.menuItem1);
        this.menu2.add(this.menuItem2);
        this.panel3.setLayout(this.borderLayout2);
        this.panel2.setLayout(this.borderLayout1);
        this.btnFirstMove.setActionCommand("acFirstMove");
        this.btnFirstMove.addActionListener(this);
        this.btnPrevMove.setActionCommand("acPrevMove");
        this.btnPrevMove.addActionListener(this);
        this.btnNextMove.setActionCommand("acNextMove");
        this.btnNextMove.addActionListener(this);
        this.btnLastMove.setActionCommand("acLastMove");
        this.btnLastMove.addActionListener(this);
        this.add(this.panel2, "Center");
        this.gobanView = new GobanView(new GoPosition(5));
        this.statusBar.setText("Status bar");
        this.panel2.add(this.gobanView, "Center");
    }
    
    void menuSettings_actionPerformed(final ActionEvent e) {
        final Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        final Dimension frameSize = this.settingsDialog.getSize();
        if (frameSize.height > screenSize.height) {
            frameSize.height = screenSize.height;
        }
        if (frameSize.width > screenSize.width) {
            frameSize.width = screenSize.width;
        }
        this.settingsDialog.setLocation((screenSize.width - frameSize.width) / 2, (screenSize.height - frameSize.height) / 2);
        this.settingsDialog.setVisible(true);
    }
    
    void menuExit_actionPerformed(final ActionEvent e) {
        this.dispose();
        System.exit(0);
    }
    
    public void actionPerformed(final ActionEvent e) {
        if (e.getActionCommand().equals("ok")) {
            final SettingsDialog settingsDialog = this.getSettingsDialog();
            this.game = this.gameManager.createGame(Integer.parseInt(settingsDialog.edtBoardSize.getText()), settingsDialog.choiceBlackPlayerType.getSelectedItem(), settingsDialog.choiceWhitePlayerType.getSelectedItem());
            if (this.gobanView != null) {
                this.panel2.remove(this.gobanView);
            }
            this.gobanView = new GobanView(this.game.goPosition);
            this.gameManager.registerGobanView(this.gobanView);
            this.game.addListener(this.gobanView);
            this.panel2.add(this.gobanView, "Center");
            this.setVisible(true);
            final StartGameThread startGameThread = new StartGameThread();
            startGameThread.start();
        }
        else if (!e.getActionCommand().equals("switch")) {
            if (e.getActionCommand().equals("Settings")) {
                this.menuSettings_actionPerformed(e);
            }
            else if (e.getActionCommand().equals("acFirstMove")) {
                this.statusBar.setText("Go to beginning");
            }
            else if (e.getActionCommand().equals("acPrevMove")) {
                this.statusBar.setText("Go to previous move");
            }
            else if (e.getActionCommand().equals("acNextMove")) {
                this.statusBar.setText("Go to next move");
            }
            else if (e.getActionCommand().equals("acLastMove")) {
                this.statusBar.setText("Go to last move");
            }
        }
    }
    
    class 1 implements ActionListener
    {
        public void actionPerformed(final ActionEvent e) {
            GameManagerFrame.this.menuSettings_actionPerformed(e);
        }
    }
    
    class 2 implements ActionListener
    {
        public void actionPerformed(final ActionEvent e) {
            GameManagerFrame.this.menuExit_actionPerformed(e);
        }
    }
    
    class StartGameThread extends Thread
    {
        public void run() {
            GameManagerFrame.this.game.playGame();
        }
    }
}
