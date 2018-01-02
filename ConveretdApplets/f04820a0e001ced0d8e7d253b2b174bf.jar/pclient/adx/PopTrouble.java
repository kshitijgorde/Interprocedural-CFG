// 
// Decompiled by Procyon v0.5.30
// 

package pclient.adx;

import javax.swing.Box;
import java.awt.Dimension;
import javax.swing.border.Border;
import pclient.adv.CompUtil;
import java.awt.Container;
import javax.swing.BoxLayout;
import javax.swing.JPanel;
import javax.swing.JComponent;
import javax.swing.Icon;
import java.awt.LayoutManager;
import java.awt.BorderLayout;
import javax.swing.JTabbedPane;
import java.net.Socket;
import java.util.Vector;
import pclient.shd.SmileDef;
import java.net.URL;
import pclient.shd.ClientUtil;
import javax.swing.JViewport;
import java.awt.Point;
import javax.swing.SwingUtilities;
import pclient.adv.SimpleQueueItem;
import java.awt.event.ActionEvent;
import java.awt.Component;
import com.pchat.sc.WindowUtil;
import pclient.adv.SimpleBankQueue;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JButton;
import pclient.adv.AppletSpice;
import java.awt.event.ActionListener;
import pclient.adv.ComInter;
import javax.swing.JFrame;

public class PopTrouble extends JFrame implements ComInter, ActionListener, Runnable
{
    private static final String A_START = "START";
    private static final String A_STOP = "STOP";
    private static final String A_REG = "REG";
    private static final String A_ALT = "ALT";
    private static final String A_SEC = "SEC";
    private AppletSpice paraApplet;
    private JButton startButton;
    private long lastPingCheck;
    private int TOTAL_PING;
    private int pingCount;
    private boolean pingGoing;
    private JTextArea displayArea;
    private JScrollPane scrollPane;
    private PopTroubleJob pingThread;
    private long lastClick;
    private long lastConnect;
    private JTextArea displayConnect;
    private JScrollPane connectPane;
    private SimpleBankQueue textQueue;
    
    public PopTrouble() {
        this.lastPingCheck = 0L;
        this.TOTAL_PING = 10;
        this.pingCount = 0;
        this.pingGoing = false;
        this.pingThread = null;
        this.lastClick = 0L;
        this.lastConnect = 0L;
        this.textQueue = new SimpleBankQueue();
    }
    
    public void run() {
        try {
            this.doChanges();
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
    }
    
    public void checkPing() {
        this.addPingEvent();
    }
    
    public void setPara(final AppletSpice paraApplet) {
        this.paraApplet = paraApplet;
        this.setTitle(this.paraApplet.paraConf.title());
        this.setSize(440, 420);
        this.setDefaultCloseOperation(1);
        this.buildGUI();
        WindowUtil.center(this);
    }
    
    public void process(final int n, final String[] array) {
        if (array.length > 0) {
            this.serverEcho(array[0]);
        }
    }
    
    public void restart() {
        this.setVisible(true);
    }
    
    public void destroy() {
        this.stopClicked();
        this.setVisible(false);
        this.dispose();
    }
    
    public void actionPerformed(final ActionEvent actionEvent) {
        final String actionCommand = actionEvent.getActionCommand();
        this.paraApplet.paraConf.printer().print("cm=" + actionCommand);
        if (actionCommand == null) {
            return;
        }
        if (actionCommand.equals("START")) {
            this.startClicked();
        }
        else if (actionCommand.equals("STOP")) {
            this.stopClicked();
        }
        else if (actionCommand.equals("REG")) {
            this.regConnect();
        }
        else if (actionCommand.equals("ALT")) {
            this.altConnect();
        }
        else if (actionCommand.equals("SEC")) {
            this.secConnect();
        }
    }
    
    private void serverEcho(final String s) {
        final long currentTimeMillis = System.currentTimeMillis();
        long long1;
        try {
            long1 = Long.parseLong(s);
        }
        catch (NumberFormatException ex) {
            System.out.println("Ping:" + s);
            long1 = 0L;
        }
        if (long1 == 0L) {
            return;
        }
        this.displayLine(this.paraApplet.paraConf.get("Msg.Ts.Pg", "Round trip in seconds:") + " " + (currentTimeMillis - long1) / 1000.0f + ". (#" + this.pingCount + " of " + this.TOTAL_PING + ")");
        System.out.println("ping:" + s + " round trip:" + (currentTimeMillis - long1));
    }
    
    private void displayLine(final String s) {
        this.displayArea.append(s);
        this.displayArea.append("\n");
        final SimpleQueueItem simpleQueueItem = new SimpleQueueItem();
        simpleQueueItem.type = 10;
        this.textQueue.add(simpleQueueItem);
        SwingUtilities.invokeLater(this);
    }
    
    private void startClicked() {
        this.startButton.setEnabled(false);
        this.pingCount = 0;
        this.pingGoing = true;
        this.lastPingCheck = 0L;
        this.pingThread = new PopTroubleJob(this);
        new Thread(this.pingThread).start();
        this.addPingEvent();
    }
    
    protected void addPingEvent() {
        final SimpleQueueItem simpleQueueItem = new SimpleQueueItem();
        simpleQueueItem.type = 2;
        this.textQueue.add(simpleQueueItem);
        SwingUtilities.invokeLater(this);
    }
    
    private void stopClicked() {
        this.pingGoing = false;
        this.startButton.setEnabled(true);
        if (this.pingThread != null) {
            this.pingThread.stopIt();
            this.pingThread = null;
        }
    }
    
    private void handlePing() {
        if (!this.pingGoing) {
            return;
        }
        if (this.pingCount >= this.TOTAL_PING) {
            this.stopClicked();
            return;
        }
        final long currentTimeMillis = System.currentTimeMillis();
        if (currentTimeMillis - this.lastPingCheck >= 3000L) {
            this.lastPingCheck = currentTimeMillis;
            ++this.pingCount;
            this.pingServer();
        }
    }
    
    private void pingServer() {
        if (!this.paraApplet.chatModel.cmIsConnected()) {
            this.displayLine(this.paraApplet.paraConf.get("Msg.Ts.P.Not", "Not Connected.") + ". (#" + this.pingCount + " of " + this.TOTAL_PING + ")");
            return;
        }
        final long currentTimeMillis = System.currentTimeMillis();
        if (currentTimeMillis - this.lastClick < 1000L) {
            return;
        }
        this.lastClick = currentTimeMillis;
        this.paraApplet.chatModel.cmPing();
    }
    
    private void handleScroll(final JScrollPane scrollPane) {
        final JViewport viewport = scrollPane.getViewport();
        final Point viewPosition = new Point(0, (int)(viewport.getViewSize().height * 1.0f) - viewport.getExtentSize().height);
        try {
            viewport.setViewPosition(viewPosition);
        }
        catch (IndexOutOfBoundsException ex) {
            ex.printStackTrace();
        }
    }
    
    private void doChanges() {
        final SimpleQueueItem[] dequeueAll = this.textQueue.dequeueAll();
        if (dequeueAll == null) {
            return;
        }
        for (int i = 0; i < dequeueAll.length; ++i) {
            final SimpleQueueItem simpleQueueItem = dequeueAll[i];
            switch (simpleQueueItem.type) {
                case 10: {
                    this.handleScroll(this.scrollPane);
                    break;
                }
                case 2: {
                    this.handlePing();
                    break;
                }
                default: {
                    System.err.println("Err78274." + simpleQueueItem);
                    break;
                }
            }
        }
    }
    
    private void displayConnection(final String s) {
        this.displayConnect.append(s);
        this.displayConnect.append("\n");
        this.handleScroll(this.connectPane);
    }
    
    private void regConnect() {
        final long currentTimeMillis = System.currentTimeMillis();
        if (currentTimeMillis - this.lastConnect < 2000L) {
            return;
        }
        this.lastConnect = currentTimeMillis;
        this.makeConnect(this.paraApplet.paraConf.getApplet().getCodeBase().getHost(), ClientUtil.getPort(this.paraApplet.paraConf));
    }
    
    private void altConnect() {
        final long currentTimeMillis = System.currentTimeMillis();
        if (currentTimeMillis - this.lastConnect < 2000L) {
            return;
        }
        this.lastConnect = currentTimeMillis;
        final String host = this.paraApplet.paraConf.getApplet().getCodeBase().getHost();
        final int int1 = this.paraApplet.paraConf.getInt("Net.HTTP", 8080);
        final String value = this.paraApplet.paraConf.get("Net.Tunnel", "/pchat/chat.jsp");
        final String s = "http";
        this.displayConnection(this.paraApplet.paraConf.get("Msg.Ts.C.Ing", "Connecting to") + " " + host + " " + int1);
        final String string = value + "?" + "chatcom" + "=TEST";
        URL url;
        try {
            url = new URL(s, host, int1, string);
        }
        catch (Exception ex) {
            ex.printStackTrace();
            this.displayConnection(ex.getLocalizedMessage());
            return;
        }
        final Vector webData = SmileDef.getWebData(url);
        if (webData != null) {
            this.displayConnection(this.paraApplet.paraConf.get("Msg.Ts.C.Ed", "Connected. Connection took(in seconds)") + ": " + this.timeInSeconds(currentTimeMillis));
            for (int i = 0; i < webData.size(); ++i) {
                System.out.println(webData.elementAt(i));
            }
        }
        else {
            this.displayConnection(this.paraApplet.paraConf.get("Msg.Ts.C.Not", "Cannot connect. It took(in seconds)") + ": " + this.timeInSeconds(currentTimeMillis));
        }
    }
    
    private void secConnect() {
        final long currentTimeMillis = System.currentTimeMillis();
        if (currentTimeMillis - this.lastConnect < 2000L) {
            return;
        }
        this.lastConnect = currentTimeMillis;
        final String host = this.paraApplet.paraConf.getApplet().getCodeBase().getHost();
        final int secPort = ClientUtil.getSecPort(this.paraApplet.paraConf);
        if (secPort <= 0) {
            this.displayConnection("Cannot connect. Secure port not set.");
        }
        this.makeConnect(host, secPort);
    }
    
    private void makeConnect(final String s, final int n) {
        this.displayConnection(this.paraApplet.paraConf.get("Msg.Ts.C.Ing", "Connecting to ") + " " + s + " " + n);
        final long currentTimeMillis = System.currentTimeMillis();
        Socket connectSocket;
        try {
            connectSocket = this.connectSocket(s, n);
        }
        catch (Exception ex) {
            connectSocket = null;
            ex.printStackTrace();
            this.displayConnection(ex.getLocalizedMessage());
        }
        if (connectSocket != null) {
            this.displayConnection(this.paraApplet.paraConf.get("Msg.Ts.C.Ed", "Connected. Connection took(in seconds)") + ": " + this.timeInSeconds(currentTimeMillis));
            this.closeSock(connectSocket);
        }
        else {
            this.displayConnection(this.paraApplet.paraConf.get("Msg.Ts.C.Not", "Cannot connect. It took(in seconds)") + ": " + this.timeInSeconds(currentTimeMillis));
        }
    }
    
    private Socket connectSocket(final String s, final int n) throws Exception {
        return new Socket(s, n);
    }
    
    private void closeSock(final Socket socket) {
        try {
            socket.close();
        }
        catch (Exception ex) {
            System.out.println("warn, closing conn");
            ex.printStackTrace();
        }
    }
    
    private float timeInSeconds(final long n) {
        return (System.currentTimeMillis() - n) / 1000.0f;
    }
    
    private void buildGUI() {
        final JTabbedPane tabbedPane = new JTabbedPane();
        this.getContentPane().setLayout(new BorderLayout());
        this.getContentPane().add(tabbedPane, "Center");
        final String value = this.paraApplet.paraConf.get("Tab.Ts.P", "Ping");
        final JComponent ping = this.createPing();
        if (true) {
            tabbedPane.addTab(value, null, ping, null);
        }
        final String value2 = this.paraApplet.paraConf.get("Tab.Ts.C", "Connectivity");
        final JComponent connectivity = this.createConnectivity();
        if (this.paraApplet.paraConf.getBool("Op.Tb.Ts.C", true)) {
            tabbedPane.addTab(value2, null, connectivity, null);
        }
    }
    
    private JComponent createPing() {
        final JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, 0));
        panel.setBorder(CompUtil.border5);
        final JPanel verticalPanel = CompUtil.createVerticalPanel(true);
        verticalPanel.setAlignmentY(0.0f);
        panel.add(verticalPanel);
        verticalPanel.add(this.getPingText());
        verticalPanel.add(Box.createRigidArea(new Dimension(1, 6)));
        verticalPanel.add(this.getPingButtons());
        return panel;
    }
    
    private JComponent getPingText() {
        final JTextArea displayArea = new JTextArea();
        displayArea.setEditable(false);
        displayArea.setLineWrap(true);
        displayArea.setWrapStyleWord(true);
        final JScrollPane scrollPane = new JScrollPane(displayArea, 20, 31);
        this.scrollPane = scrollPane;
        this.displayArea = displayArea;
        return scrollPane;
    }
    
    private JComponent getPingButtons() {
        final JButton startButton = new JButton(this.paraApplet.paraConf.get("Bt.Ts.S", "Start"));
        startButton.setActionCommand("START");
        startButton.addActionListener(this);
        final JButton button = new JButton(this.paraApplet.paraConf.get("Bt.Ts.Sp", "Stop"));
        button.setActionCommand("STOP");
        button.addActionListener(this);
        final JPanel horizontalPanel = CompUtil.createHorizontalPanel(false);
        horizontalPanel.setAlignmentX(0.5f);
        horizontalPanel.add(startButton);
        horizontalPanel.add(Box.createRigidArea(new Dimension(2, 2)));
        horizontalPanel.add(button);
        this.startButton = startButton;
        return horizontalPanel;
    }
    
    private JComponent createConnectivity() {
        final JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, 0));
        panel.setBorder(CompUtil.border5);
        final JPanel verticalPanel = CompUtil.createVerticalPanel(true);
        verticalPanel.setAlignmentY(0.0f);
        panel.add(verticalPanel);
        verticalPanel.add(this.getConnectText());
        verticalPanel.add(Box.createRigidArea(new Dimension(1, 6)));
        verticalPanel.add(this.getConnectButtons());
        return panel;
    }
    
    private JComponent getConnectText() {
        final JTextArea displayConnect = new JTextArea();
        displayConnect.setEditable(false);
        displayConnect.setLineWrap(true);
        displayConnect.setWrapStyleWord(true);
        final JScrollPane connectPane = new JScrollPane(displayConnect, 20, 31);
        this.connectPane = connectPane;
        this.displayConnect = displayConnect;
        return connectPane;
    }
    
    private JComponent getConnectButtons() {
        final JButton button = new JButton(this.paraApplet.paraConf.get("Bt.Ts.C.R", "Regular Connection"));
        button.setActionCommand("REG");
        button.addActionListener(this);
        final JButton button2 = new JButton(this.paraApplet.paraConf.get("Bt.Ts.C.A", "Alternative"));
        button2.setActionCommand("ALT");
        button2.addActionListener(this);
        final JButton button3 = new JButton(this.paraApplet.paraConf.get("Bt.Ts.C.S", "Secure"));
        button3.setActionCommand("SEC");
        button3.addActionListener(this);
        final JPanel horizontalPanel = CompUtil.createHorizontalPanel(false);
        horizontalPanel.setAlignmentX(0.5f);
        horizontalPanel.add(button);
        horizontalPanel.add(Box.createRigidArea(new Dimension(2, 2)));
        horizontalPanel.add(button2);
        horizontalPanel.add(Box.createRigidArea(new Dimension(2, 2)));
        horizontalPanel.add(button3);
        return horizontalPanel;
    }
}
