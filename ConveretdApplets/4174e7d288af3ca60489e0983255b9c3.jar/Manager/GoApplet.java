// 
// Decompiled by Procyon v0.5.30
// 

package Manager;

import java.awt.event.WindowEvent;
import java.awt.event.WindowAdapter;
import Go.GoGameEvent;
import Go.GoGameAdapter;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.WindowListener;
import java.awt.Frame;
import java.awt.Color;
import java.awt.Component;
import java.awt.LayoutManager;
import Go.GoPosition;
import Go.GoGameListener;
import java.awt.event.ActionEvent;
import Debug.DebugUtils;
import java.awt.TextField;
import java.awt.Label;
import java.awt.Choice;
import java.awt.GridLayout;
import java.awt.FlowLayout;
import Go.strategy.AtariGoEstimatorCho;
import Goban.GobanView;
import Go.GoGame;
import java.util.Vector;
import java.awt.Button;
import java.awt.Panel;
import java.awt.BorderLayout;
import java.awt.event.ActionListener;
import java.applet.Applet;

public class GoApplet extends Applet implements ActionListener
{
    boolean isStandalone;
    BorderLayout borderLayout1;
    Panel panel1;
    Panel panel2;
    Button btnSettings;
    Button btnStop;
    Panel pnlGoban;
    BorderLayout borderLayout2;
    protected Vector listeners;
    private GameManager gameManager;
    private GoGame game;
    private GobanView gobanView;
    private AtariGoEstimatorCho estimator;
    private final String acStart = "Start game";
    private final String acReset = "Reset";
    private final String smGameNotStarted = "Not started yet...";
    private final String smGameOver = "Game over!";
    private final String smBlackThinking = "Black thinking...";
    private final String smWhiteThinking = "White thinking...";
    private final String smBlackWon = "Black won!";
    private final String smWhiteWon = "White won!";
    Panel panel5;
    Panel panel4;
    FlowLayout flowLayout2;
    GridLayout gridLayout1;
    Choice choiceBlackPlayerType;
    Label label1;
    TextField edtBoardSize;
    Panel panel6;
    Label label3;
    Panel panel3;
    Choice choiceWhitePlayerType;
    Label label2;
    FlowLayout flowLayout1;
    Panel panel7;
    Label lblStatus;
    Label lblStatusMessage;
    Button btnWhiteSettings;
    Button btnBlackSettings;
    
    public String getParameter(final String key, final String def) {
        return this.isStandalone ? System.getProperty(key, def) : ((this.getParameter(key) != null) ? this.getParameter(key) : def);
    }
    
    public GoApplet() {
        this.isStandalone = false;
        this.borderLayout1 = new BorderLayout();
        this.panel1 = new Panel();
        this.panel2 = new Panel();
        this.btnSettings = new Button();
        this.btnStop = new Button();
        this.pnlGoban = new Panel();
        this.borderLayout2 = new BorderLayout();
        this.listeners = new Vector();
        this.gameManager = null;
        this.game = null;
        this.gobanView = null;
        this.estimator = null;
        this.panel5 = new Panel();
        this.panel4 = new Panel();
        this.flowLayout2 = new FlowLayout();
        this.gridLayout1 = new GridLayout();
        this.choiceBlackPlayerType = new Choice();
        this.label1 = new Label();
        this.edtBoardSize = new TextField();
        this.panel6 = new Panel();
        this.label3 = new Label();
        this.panel3 = new Panel();
        this.choiceWhitePlayerType = new Choice();
        this.label2 = new Label();
        this.flowLayout1 = new FlowLayout();
        this.panel7 = new Panel();
        this.lblStatus = new Label();
        this.lblStatusMessage = new Label();
        this.btnWhiteSettings = new Button();
        this.btnBlackSettings = new Button();
    }
    
    public void init() {
        try {
            DebugUtils.DEBUG_ON = false;
            this.gameManager = new GameManager();
            this.estimator = new AtariGoEstimatorCho();
            this.addActionListener(this);
            this.btnSettings.addActionListener(this);
            this.btnStop.addActionListener(this);
            this.jbInit();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public void actionPerformed(final ActionEvent e) {
        if (e.getActionCommand().equals("Start game")) {
            this.gameManager.unregisterGobanView(this.gobanView);
            this.game = this.gameManager.createGame(Integer.parseInt(this.edtBoardSize.getText()), this.choiceBlackPlayerType.getSelectedItem(), this.choiceWhitePlayerType.getSelectedItem());
            this.btnSettings.setEnabled(false);
            this.choiceBlackPlayerType.setEnabled(false);
            this.choiceWhitePlayerType.setEnabled(false);
            this.edtBoardSize.setEnabled(false);
            this.lblStatusMessage.setText("Black thinking...");
            this.gobanView.setGoPosition(this.game.goPosition);
            this.gameManager.registerGobanView(this.gobanView);
            this.game.addListener(this.gobanView);
            this.game.addListener(new 1());
            this.setVisible(true);
            final StartGameThread startGameThread = new StartGameThread();
            startGameThread.start();
        }
        else if (e.getActionCommand().equals("Reset")) {
            this.gameManager.unregisterGobanView(this.gobanView);
            this.gobanView.setGoPosition(new GoPosition(Integer.parseInt(this.edtBoardSize.getText())));
            this.btnSettings.setEnabled(true);
            this.btnSettings.setEnabled(true);
            this.choiceBlackPlayerType.setEnabled(true);
            this.choiceWhitePlayerType.setEnabled(true);
            this.edtBoardSize.setEnabled(true);
            this.lblStatusMessage.setText("Not started yet...");
        }
        else if (!e.getActionCommand().equals("acFirstMove")) {
            if (!e.getActionCommand().equals("acPrevMove")) {
                if (!e.getActionCommand().equals("acNextMove")) {
                    if (e.getActionCommand().equals("acLastMove")) {}
                }
            }
        }
    }
    
    private void jbInit() throws Exception {
        this.setLayout(this.borderLayout1);
        this.setSize(400, 400);
        this.panel2.setLayout(this.borderLayout2);
        this.btnSettings.setLabel("Start game");
        this.btnSettings.setActionCommand("Start game");
        this.btnStop.setLabel("Reset");
        this.btnStop.setActionCommand("Reset");
        this.pnlGoban.setLayout(new BorderLayout());
        this.gobanView = new GobanView(new GoPosition(5));
        this.btnWhiteSettings.setLabel("Setings");
        this.btnWhiteSettings.addActionListener(new 2());
        this.btnBlackSettings.setLabel("Settings");
        this.btnBlackSettings.addActionListener(new 3());
        this.pnlGoban.add(this.gobanView, "Center");
        this.gridLayout1.setRows(4);
        this.edtBoardSize.setBackground(Color.white);
        this.add(this.panel1, "South");
        this.panel1.add(this.btnSettings, null);
        this.panel1.add(this.btnStop, null);
        this.add(this.panel2, "Center");
        this.panel2.add(this.pnlGoban, "Center");
        this.panel2.add(this.panel5, "North");
        this.panel5.add(this.panel4, null);
        this.panel4.add(this.label1, null);
        this.panel4.add(this.choiceBlackPlayerType, null);
        this.panel4.add(this.btnBlackSettings, null);
        this.panel5.add(this.panel3, null);
        this.panel3.add(this.label2, null);
        this.panel3.add(this.choiceWhitePlayerType, null);
        this.panel3.add(this.btnWhiteSettings, null);
        this.panel5.add(this.panel6, null);
        this.panel6.add(this.label3, null);
        this.panel6.add(this.edtBoardSize, null);
        this.panel5.add(this.panel7, null);
        this.panel7.add(this.lblStatus, null);
        this.panel7.add(this.lblStatusMessage, null);
        this.label2.setText("White is ");
        this.label3.setText("Board size");
        this.edtBoardSize.setText("5");
        this.edtBoardSize.setColumns(2);
        this.label1.setText("Black is ");
        this.gridLayout1.setColumns(1);
        this.panel5.setLayout(this.gridLayout1);
        this.panel4.setLayout(this.flowLayout2);
        this.lblStatusMessage.setText("Not started yet...");
        this.lblStatus.setText("Game status: ");
        this.choiceBlackPlayerType.addItem("Human player");
        this.choiceBlackPlayerType.addItem("Alpha-Beta Computer player");
        this.choiceBlackPlayerType.addItem("Greedy Computer player");
        this.choiceBlackPlayerType.addItem("MinMax Computer player");
        this.choiceBlackPlayerType.select(0);
        this.choiceWhitePlayerType.addItem("Human player");
        this.choiceWhitePlayerType.addItem("Alpha-Beta Computer player");
        this.choiceWhitePlayerType.addItem("Greedy Computer player");
        this.choiceWhitePlayerType.addItem("MinMax Computer player");
        this.choiceWhitePlayerType.select(1);
    }
    
    public String getAppletInfo() {
        return "Applet Information";
    }
    
    public String[][] getParameterInfo() {
        return null;
    }
    
    public void addActionListener(final ActionListener l) {
        this.listeners.addElement(l);
    }
    
    public void removeActionListener(final ActionListener l) {
        this.listeners.removeElement(l);
    }
    
    public static void main(final String[] args) {
        final GoApplet applet = new GoApplet();
        applet.isStandalone = true;
        final Frame frame = new Frame();
        frame.addWindowListener(new 4());
        frame.setTitle("Applet Frame");
        frame.add(applet, "Center");
        applet.init();
        applet.start();
        frame.setSize(400, 420);
        final Dimension d = Toolkit.getDefaultToolkit().getScreenSize();
        frame.setLocation((d.width - frame.getSize().width) / 2, (d.height - frame.getSize().height) / 2);
        frame.setVisible(true);
    }
    
    void btnWhiteSettings_actionPerformed(final ActionEvent e) {
        if (this.choiceWhitePlayerType.getSelectedItem().equals("Alpha-Beta Computer player") || this.choiceWhitePlayerType.getSelectedItem().equals("MinMax Computer player")) {
            final GeneralOKCancel dialog = new MinMaxSettingsFrame(this.gameManager.getWhitePlayer().getStrategy());
            dialog.setVisible(true);
        }
    }
    
    void btnBlackSettings_actionPerformed(final ActionEvent e) {
        if (this.choiceBlackPlayerType.getSelectedItem().equals("Alpha-Beta Computer player") || this.choiceBlackPlayerType.getSelectedItem().equals("MinMax Computer player")) {
            final GeneralOKCancel dialog = new MinMaxSettingsFrame(this.gameManager.getBlackPlayer().getStrategy());
            dialog.setVisible(true);
        }
    }
    
    static /* synthetic */ String access$471(final GoApplet this$) {
        return "White won!";
    }
    
    static /* synthetic */ String access$371(final GoApplet this$) {
        return "Black won!";
    }
    
    static /* synthetic */ String access$271(final GoApplet this$) {
        return "White thinking...";
    }
    
    static /* synthetic */ String access$171(final GoApplet this$) {
        return "Black thinking...";
    }
    
    class 1 extends GoGameAdapter
    {
        public void positionChanged(final GoGameEvent goGameEvent) {
            if (GoApplet.this.estimator.checkPositionState(goGameEvent.getNewPosition()) == 100) {
                if (goGameEvent.getNewPosition().turn() == 1) {
                    GoApplet.this.lblStatusMessage.setText(GoApplet.access$171(GoApplet.this));
                }
                else {
                    GoApplet.this.lblStatusMessage.setText(GoApplet.access$271(GoApplet.this));
                }
            }
            else if (GoApplet.this.estimator.checkPositionState(goGameEvent.getNewPosition()) == 1) {
                GoApplet.this.lblStatusMessage.setText(GoApplet.access$371(GoApplet.this));
            }
            else if (GoApplet.this.estimator.checkPositionState(goGameEvent.getNewPosition()) == -1) {
                GoApplet.this.lblStatusMessage.setText(GoApplet.access$471(GoApplet.this));
            }
        }
    }
    
    class 2 implements ActionListener
    {
        public void actionPerformed(final ActionEvent e) {
            GoApplet.this.btnWhiteSettings_actionPerformed(e);
        }
    }
    
    class 3 implements ActionListener
    {
        public void actionPerformed(final ActionEvent e) {
            GoApplet.this.btnBlackSettings_actionPerformed(e);
        }
    }
    
    static class 4 extends WindowAdapter
    {
        public void windowClosing(final WindowEvent e) {
            System.exit(0);
        }
    }
    
    class StartGameThread extends Thread
    {
        public void run() {
            GoApplet.this.game.playGame();
        }
    }
}
