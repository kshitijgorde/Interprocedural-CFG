import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import java.awt.Color;
import java.awt.Font;
import javax.swing.JTabbedPane;
import java.awt.Point;
import javax.swing.JLabel;
import java.awt.Component;
import java.util.Vector;
import java.awt.LayoutManager;
import javax.swing.JPanel;
import javax.swing.JComboBox;
import java.util.Random;

// 
// Decompiled by Procyon v0.5.30
// 

public class NetManager
{
    public static char[] NETWORKS;
    public static char[] HOSTS;
    public static char LOST_PACKET;
    private static final int _width = 698;
    private static final int _height = 485;
    private static final int _seed;
    private static final Random _random;
    private static Network _netA;
    private static NetComponent _cloudA;
    private static HostComponent _hostA1;
    private static HostComponent _hostA2;
    private static LineComponent _lineA1;
    private static LineComponent _lineA2;
    private static Network _netB;
    private static NetComponent _cloudB;
    private static HostComponent _hostB1;
    private static HostComponent _hostB2;
    private static LineComponent _lineB1;
    private static LineComponent _lineB2;
    private static Network _netC;
    private static NetComponent _cloudC;
    private static Network _netD;
    private static NetComponent _cloudD;
    private static HostComponent _hostD1;
    private static HostComponent _hostD2;
    private static LineComponent _lineD1;
    private static LineComponent _lineD2;
    private static Network _netE;
    private static NetComponent _cloudE;
    private static HostComponent _hostE1;
    private static HostComponent _hostE2;
    private static LineComponent _lineE1;
    private static LineComponent _lineE2;
    private static JComboBox _comboFrom;
    private static JComboBox _comboTo;
    private static JComboBox _comboCount;
    private static LineComponent[] _lines;
    private static JPanel _panel;
    public static String[][] CONFIGURATIONS;
    
    public NetManager() {
        if (Main.DEBUG) {
            System.out.println("Seed = " + NetManager._seed);
        }
        final int xCloud = 50;
        final int xLeft = 50;
        final int xRight = 548;
        final int xMiddle = 349;
        final int yTop = 30;
        final int yMiddle = yTop + 140;
        final int yBottom = yMiddle + 140;
        int randomNum1 = NetManager._random.nextInt(9);
        int randomNum2 = NetManager._random.nextInt(9);
        (NetManager._panel = new JPanel(null)).setBackground(Main.WINDOW_COLOR);
        NetManager._cloudA = new NetComponent(xMiddle - xCloud, yBottom, 'A', 1);
        NetManager._cloudB = new NetComponent(xLeft, yMiddle, 'B', 2);
        NetManager._cloudC = new NetComponent(xMiddle - xCloud, yMiddle, 'C', 3);
        NetManager._cloudD = new NetComponent(xRight, yMiddle, 'D', 4);
        NetManager._cloudE = new NetComponent(xMiddle - xCloud, yTop, 'E', 5);
        final Vector vectorA = new Vector();
        final Vector vectorB = new Vector();
        final Vector vectorC = new Vector();
        final Vector vectorD = new Vector();
        final Vector vectorE = new Vector();
        final int config = NetManager._random.nextInt(NetManager.CONFIGURATIONS.length);
        this.privSetupConnections(config, vectorA, vectorB, vectorC, vectorD, vectorE);
        for (int i = 0; i < NetManager._lines.length; ++i) {
            NetManager._panel.add(NetManager._lines[i]);
        }
        NetManager._netA = new Network('A', vectorA, config);
        randomNum1 = this.pickNextHost(randomNum2);
        randomNum2 = this.pickNextHost(randomNum1);
        NetManager._hostA1 = new HostComponent(xMiddle - 125, yBottom + 50, 'A', (char)(Math.min(randomNum1, randomNum2) + 49));
        NetManager._hostA2 = new HostComponent(xMiddle + 77, yBottom + 50, 'A', (char)(Math.max(randomNum1, randomNum2) + 49));
        final JLabel labelA1 = new JLabel(NetManager._hostA1.getHost() + ".A");
        final JLabel labelA2 = new JLabel("A." + NetManager._hostA2.getHost());
        NetManager._lineA1 = new LineComponent(new Point(NetManager._cloudA.getWest().x - 31, NetManager._cloudA.getWest().y + 40), new Point(NetManager._cloudA.getWest().x + 4, NetManager._cloudA.getWest().y + 5), labelA1);
        NetManager._lineA2 = new LineComponent(new Point(NetManager._cloudA.getEast().x - 2, NetManager._cloudA.getEast().y + 0), new Point(NetManager._cloudA.getEast().x + 33, NetManager._cloudA.getEast().y + 35), labelA2);
        NetManager._panel.add(NetManager._cloudA);
        NetManager._panel.add(NetManager._hostA1);
        NetManager._panel.add(NetManager._hostA2);
        NetManager._panel.add(NetManager._lineA1);
        NetManager._panel.add(NetManager._lineA2);
        NetManager._netB = new Network('B', vectorB, config);
        randomNum1 = this.pickNextHost(randomNum2);
        randomNum2 = this.pickNextHost(randomNum1);
        NetManager._hostB1 = new HostComponent(xLeft - 10, yMiddle - 65, 'B', (char)(Math.min(randomNum1, randomNum2) + 49));
        NetManager._hostB2 = new HostComponent(xLeft - 10, yMiddle + 100, 'B', (char)(Math.max(randomNum1, randomNum2) + 49));
        final JLabel labelB1 = new JLabel(NetManager._hostB1.getHost() + ".B");
        final JLabel labelB2 = new JLabel(NetManager._hostB2.getHost() + ".B");
        NetManager._lineB1 = new LineComponent(new Point(NetManager._cloudB.getNorth().x - 35, NetManager._cloudB.getNorth().y - 30), new Point(NetManager._cloudB.getNorth().x - 0, NetManager._cloudB.getNorth().y + 5), labelB1);
        NetManager._lineB2 = new LineComponent(new Point(NetManager._cloudB.getSouth().x - 35, NetManager._cloudB.getSouth().y + 30), new Point(NetManager._cloudB.getSouth().x - 0, NetManager._cloudB.getSouth().y - 5), labelB2);
        NetManager._panel.add(NetManager._cloudB);
        NetManager._panel.add(NetManager._hostB1);
        NetManager._panel.add(NetManager._hostB2);
        NetManager._panel.add(NetManager._lineB1);
        NetManager._panel.add(NetManager._lineB2);
        NetManager._netC = new Network('C', vectorC, config);
        NetManager._panel.add(NetManager._cloudC);
        NetManager._netD = new Network('D', vectorD, config);
        randomNum1 = this.pickNextHost(randomNum2);
        randomNum2 = this.pickNextHost(randomNum1);
        NetManager._hostD1 = new HostComponent(xRight + 65, yMiddle - 65, 'D', (char)(Math.min(randomNum1, randomNum2) + 49));
        NetManager._hostD2 = new HostComponent(xRight + 65, yMiddle + 100, 'D', (char)(Math.max(randomNum1, randomNum2) + 49));
        final JLabel labelD1 = new JLabel("D." + NetManager._hostD1.getHost());
        final JLabel labelD2 = new JLabel("D." + NetManager._hostD2.getHost());
        NetManager._lineD1 = new LineComponent(new Point(NetManager._cloudD.getNorth().x, NetManager._cloudD.getNorth().y + 5), new Point(NetManager._cloudD.getNorth().x + 35, NetManager._cloudD.getNorth().y - 30), labelD1);
        NetManager._lineD2 = new LineComponent(new Point(NetManager._cloudD.getSouth().x, NetManager._cloudD.getSouth().y - 5), new Point(NetManager._cloudD.getSouth().x + 35, NetManager._cloudD.getSouth().y + 30), labelD2);
        NetManager._panel.add(NetManager._cloudD);
        NetManager._panel.add(NetManager._hostD1);
        NetManager._panel.add(NetManager._hostD2);
        NetManager._panel.add(NetManager._lineD1);
        NetManager._panel.add(NetManager._lineD2);
        NetManager._netE = new Network('E', vectorE, config);
        randomNum1 = this.pickNextHost(randomNum2);
        randomNum2 = this.pickNextHost(randomNum1);
        NetManager._hostE1 = new HostComponent(xMiddle - 125, yTop - 10, 'E', (char)(Math.min(randomNum1, randomNum2) + 49));
        NetManager._hostE2 = new HostComponent(xMiddle + 77, yTop - 10, 'E', (char)(Math.max(randomNum1, randomNum2) + 49));
        final JLabel labelE1 = new JLabel(NetManager._hostE1.getHost() + ".E");
        final JLabel labelE2 = new JLabel("E." + NetManager._hostE2.getHost());
        NetManager._lineE1 = new LineComponent(new Point(NetManager._cloudE.getWest().x - 31, NetManager._cloudE.getWest().y - 30), new Point(NetManager._cloudE.getWest().x + 4, NetManager._cloudE.getWest().y + 5), labelE1);
        NetManager._lineE2 = new LineComponent(new Point(NetManager._cloudE.getEast().x - 2, NetManager._cloudE.getEast().y + 0), new Point(NetManager._cloudE.getEast().x + 33, NetManager._cloudE.getEast().y - 35), labelE2);
        NetManager._panel.add(NetManager._cloudE);
        NetManager._panel.add(NetManager._hostE1);
        NetManager._panel.add(NetManager._hostE2);
        NetManager._panel.add(NetManager._lineE1);
        NetManager._panel.add(NetManager._lineE2);
        NetManager._panel.setVisible(true);
        this.privSetupMessages();
    }
    
    private int pickNextHost(final int prev) {
        int next;
        while ((next = NetManager._random.nextInt(9)) == prev) {}
        return next;
    }
    
    private void privSetupMessages() {
        final JTabbedPane msgs = new JTabbedPane();
        msgs.setBackground(Main.WINDOW_COLOR);
        msgs.setBounds(10, 405, 678, 71);
        final JPanel panel = new JPanel(null);
        panel.setBackground(Main.WINDOW_COLOR);
        final Vector vector = new Vector();
        vector.add("");
        vector.add("" + NetManager._hostA1.getNetwork() + "." + NetManager._hostA1.getHost());
        vector.add("" + NetManager._hostA2.getNetwork() + "." + NetManager._hostA2.getHost());
        vector.add("" + NetManager._hostB1.getNetwork() + "." + NetManager._hostB1.getHost());
        vector.add("" + NetManager._hostB2.getNetwork() + "." + NetManager._hostB2.getHost());
        vector.add("" + NetManager._hostD1.getNetwork() + "." + NetManager._hostD1.getHost());
        vector.add("" + NetManager._hostD2.getNetwork() + "." + NetManager._hostD2.getHost());
        vector.add("" + NetManager._hostE1.getNetwork() + "." + NetManager._hostE1.getHost());
        vector.add("" + NetManager._hostE2.getNetwork() + "." + NetManager._hostE2.getHost());
        final int yAll = 8;
        final int hAll = 25;
        final int fBold = 0;
        final int fSize = 14;
        final JLabel labelFrom = new JLabel("From");
        labelFrom.setBounds(30, yAll, 50, hAll);
        labelFrom.setFont(new Font("Dialog", fBold, fSize));
        labelFrom.setForeground(Color.white);
        panel.add(labelFrom);
        (NetManager._comboFrom = new JComboBox(vector)).setBounds(80, yAll, 75, hAll);
        panel.add(NetManager._comboFrom);
        final JLabel labelTo = new JLabel("To");
        labelTo.setBounds(190, yAll, 50, hAll);
        labelTo.setFont(new Font("Dialog", fBold, fSize));
        labelTo.setForeground(Color.white);
        panel.add(labelTo);
        (NetManager._comboTo = new JComboBox(vector)).setBounds(220, yAll, 75, hAll);
        panel.add(NetManager._comboTo);
        final JLabel labelCount = new JLabel("Count");
        labelCount.setBounds(335, yAll, 50, hAll);
        labelCount.setFont(new Font("Dialog", fBold, fSize));
        labelCount.setForeground(Color.white);
        panel.add(labelCount);
        (NetManager._comboCount = new JComboBox((E[])new String[] { "1", "2", "3", "4", "5", "6", "7", "8", "9" })).setBounds(390, yAll, 75, hAll);
        panel.add(NetManager._comboCount);
        final JButton button = new JButton("Send");
        button.setBounds(540, yAll, 100, hAll);
        button.addActionListener(new ActionListener() {
            public void actionPerformed(final ActionEvent evt) {
                NetManager.this.forwardPackets();
            }
        });
        panel.add(button);
        msgs.add("Packets", panel);
        NetManager._panel.add(msgs);
    }
    
    private void privSetupConnections(final int config, final Vector vectorA, final Vector vectorB, final Vector vectorC, final Vector vectorD, final Vector vectorE) {
        NetManager._lines = new LineComponent[NetManager.CONFIGURATIONS[config].length];
        for (int i = 0; i < NetManager.CONFIGURATIONS[config].length; ++i) {
            final char cLeft = NetManager.CONFIGURATIONS[config][i].charAt(0);
            final char cRight = NetManager.CONFIGURATIONS[config][i].charAt(1);
            if (Main.DEBUG) {
                System.out.println("Left = " + cLeft + ", Right = " + cRight);
            }
            Point ptLeft = null;
            Point ptRight = null;
            Label_1143: {
                switch (cLeft) {
                    case 'A': {
                        vectorA.add("" + cRight);
                        switch (cRight) {
                            case 'B': {
                                vectorB.add("" + cLeft);
                                ptLeft = NetManager._cloudB.getSouthEast();
                                ptRight = NetManager._cloudA.getNorthWest();
                                break;
                            }
                            case 'C': {
                                vectorC.add("" + cLeft);
                                ptLeft = NetManager._cloudA.getNorth();
                                ptRight = NetManager._cloudC.getSouth();
                                break;
                            }
                            case 'D': {
                                vectorD.add("" + cLeft);
                                ptLeft = NetManager._cloudA.getNorthEast();
                                ptRight = NetManager._cloudD.getSouthWest();
                                break;
                            }
                        }
                        break;
                    }
                    case 'B': {
                        vectorB.add("" + cRight);
                        switch (cRight) {
                            case 'A': {
                                vectorA.add("" + cLeft);
                                ptLeft = NetManager._cloudB.getSouthEast();
                                ptRight = NetManager._cloudA.getNorthWest();
                                break;
                            }
                            case 'C': {
                                vectorC.add("" + cLeft);
                                ptLeft = NetManager._cloudB.getEast();
                                ptRight = NetManager._cloudC.getWest();
                                break;
                            }
                            case 'E': {
                                vectorE.add("" + cLeft);
                                ptLeft = NetManager._cloudB.getNorthEast();
                                ptRight = NetManager._cloudE.getSouthWest();
                                break;
                            }
                        }
                        break;
                    }
                    case 'C': {
                        vectorC.add("" + cRight);
                        switch (cRight) {
                            case 'A': {
                                vectorA.add("" + cLeft);
                                ptLeft = NetManager._cloudC.getSouth();
                                ptRight = NetManager._cloudA.getNorth();
                                break;
                            }
                            case 'B': {
                                vectorB.add("" + cLeft);
                                ptLeft = NetManager._cloudB.getEast();
                                ptRight = NetManager._cloudC.getWest();
                                break;
                            }
                            case 'D': {
                                vectorD.add("" + cLeft);
                                ptLeft = NetManager._cloudC.getEast();
                                ptRight = NetManager._cloudD.getWest();
                                break;
                            }
                            case 'E': {
                                vectorE.add("" + cLeft);
                                ptLeft = NetManager._cloudC.getNorth();
                                ptRight = NetManager._cloudE.getSouth();
                                break;
                            }
                        }
                        break;
                    }
                    case 'D': {
                        vectorD.add("" + cRight);
                        switch (cRight) {
                            case 'A': {
                                vectorA.add("" + cLeft);
                                ptLeft = NetManager._cloudD.getSouthWest();
                                ptRight = NetManager._cloudA.getNorthEast();
                                break;
                            }
                            case 'C': {
                                vectorC.add("" + cLeft);
                                ptLeft = NetManager._cloudD.getWest();
                                ptRight = NetManager._cloudC.getEast();
                                break;
                            }
                            case 'E': {
                                vectorE.add("" + cLeft);
                                ptLeft = NetManager._cloudE.getSouthEast();
                                ptRight = NetManager._cloudD.getNorthWest();
                                break;
                            }
                        }
                        break;
                    }
                    case 'E': {
                        vectorE.add("" + cRight);
                        switch (cRight) {
                            case 'B': {
                                vectorB.add("" + cLeft);
                                ptLeft = NetManager._cloudB.getNorthEast();
                                ptRight = NetManager._cloudE.getSouthWest();
                                break Label_1143;
                            }
                            case 'C': {
                                vectorC.add("" + cLeft);
                                ptLeft = NetManager._cloudE.getSouth();
                                ptRight = NetManager._cloudC.getNorth();
                                break Label_1143;
                            }
                            case 'D': {
                                vectorD.add("" + cLeft);
                                ptLeft = NetManager._cloudE.getSouthEast();
                                ptRight = NetManager._cloudD.getNorthWest();
                                break Label_1143;
                            }
                        }
                        break;
                    }
                }
            }
            final JLabel label = new JLabel("" + cLeft + "." + cRight);
            NetManager._lines[i] = new LineComponent(ptLeft, ptRight, label);
            NetManager._panel.add(label);
        }
    }
    
    public static JPanel getNetwork() {
        return NetManager._panel;
    }
    
    public static JPanel getNetworkA() {
        return NetManager._netA;
    }
    
    public static JPanel getNetworkB() {
        return NetManager._netB;
    }
    
    public static JPanel getNetworkC() {
        return NetManager._netC;
    }
    
    public static JPanel getNetworkD() {
        return NetManager._netD;
    }
    
    public static JPanel getNetworkE() {
        return NetManager._netE;
    }
    
    private void forwardPackets() {
        final String sFrom = NetManager._comboFrom.getSelectedItem().toString();
        final String sTo = NetManager._comboTo.getSelectedItem().toString();
        final String sCount = NetManager._comboCount.getSelectedItem().toString();
        if (sFrom.length() != 3 && sTo.length() != 3 && sCount.length() == 0) {
            return;
        }
        int count = 0;
        try {
            count = Integer.parseInt(sCount);
        }
        catch (NumberFormatException ex) {}
        if (NetManager._lineA1.toString().equalsIgnoreCase(sFrom)) {
            NetManager._lineA1.setLeftToRight(sTo, count);
            return;
        }
        if (NetManager._lineA1.toRevString().equalsIgnoreCase(sFrom)) {
            NetManager._lineA1.setLeftToRight(sTo, count);
            return;
        }
        if (NetManager._lineA2.toString().equalsIgnoreCase(sFrom)) {
            NetManager._lineA2.setRightToLeft(sTo, count);
            return;
        }
        if (NetManager._lineA2.toRevString().equalsIgnoreCase(sFrom)) {
            NetManager._lineA2.setRightToLeft(sTo, count);
            return;
        }
        if (NetManager._lineB1.toString().equalsIgnoreCase(sFrom)) {
            NetManager._lineB1.setLeftToRight(sTo, count);
            return;
        }
        if (NetManager._lineB1.toRevString().equalsIgnoreCase(sFrom)) {
            NetManager._lineB1.setLeftToRight(sTo, count);
            return;
        }
        if (NetManager._lineB2.toString().equalsIgnoreCase(sFrom)) {
            NetManager._lineB2.setLeftToRight(sTo, count);
            return;
        }
        if (NetManager._lineB2.toRevString().equalsIgnoreCase(sFrom)) {
            NetManager._lineB2.setLeftToRight(sTo, count);
            return;
        }
        if (NetManager._lineD1.toString().equalsIgnoreCase(sFrom)) {
            NetManager._lineD1.setRightToLeft(sTo, count);
            return;
        }
        if (NetManager._lineD1.toRevString().equalsIgnoreCase(sFrom)) {
            NetManager._lineD1.setRightToLeft(sTo, count);
            return;
        }
        if (NetManager._lineD2.toString().equalsIgnoreCase(sFrom)) {
            NetManager._lineD2.setRightToLeft(sTo, count);
            return;
        }
        if (NetManager._lineD2.toRevString().equalsIgnoreCase(sFrom)) {
            NetManager._lineD2.setRightToLeft(sTo, count);
            return;
        }
        if (NetManager._lineE1.toString().equalsIgnoreCase(sFrom)) {
            NetManager._lineE1.setLeftToRight(sTo, count);
            return;
        }
        if (NetManager._lineE1.toRevString().equalsIgnoreCase(sFrom)) {
            NetManager._lineE1.setLeftToRight(sTo, count);
            return;
        }
        if (NetManager._lineE2.toString().equalsIgnoreCase(sFrom)) {
            NetManager._lineE2.setRightToLeft(sTo, count);
            return;
        }
        if (NetManager._lineE2.toRevString().equalsIgnoreCase(sFrom)) {
            NetManager._lineE2.setRightToLeft(sTo, count);
        }
    }
    
    public static LineComponent getLine(final String req) {
        if (req.length() < 3) {
            return null;
        }
        for (int i = 0; i < NetManager._lines.length; ++i) {
            if (NetManager._lines[i].toString().equalsIgnoreCase(req)) {
                return NetManager._lines[i];
            }
        }
        if (NetManager._lineA1.toString().equalsIgnoreCase(req)) {
            return NetManager._lineA1;
        }
        if (NetManager._lineA2.toString().equalsIgnoreCase(req)) {
            return NetManager._lineA2;
        }
        if (NetManager._lineB1.toString().equalsIgnoreCase(req)) {
            return NetManager._lineB1;
        }
        if (NetManager._lineB2.toString().equalsIgnoreCase(req)) {
            return NetManager._lineB2;
        }
        if (NetManager._lineD1.toString().equalsIgnoreCase(req)) {
            return NetManager._lineD1;
        }
        if (NetManager._lineD2.toString().equalsIgnoreCase(req)) {
            return NetManager._lineD2;
        }
        if (NetManager._lineE1.toString().equalsIgnoreCase(req)) {
            return NetManager._lineE1;
        }
        if (NetManager._lineE2.toString().equalsIgnoreCase(req)) {
            return NetManager._lineE2;
        }
        return null;
    }
    
    static {
        NetManager.NETWORKS = new char[] { 'A', 'B', 'C', 'D', 'E' };
        NetManager.HOSTS = new char[] { '1', '2', '3', '4', '5', '6', '7', '8', '9' };
        NetManager.LOST_PACKET = '\0';
        _seed = new Random().nextInt();
        _random = new Random(NetManager._seed);
        NetManager.CONFIGURATIONS = new String[][] { { "BA", "AC", "AD", "BC", "BE", "CE", "CD", "ED" }, { "BA", "AC", "BC", "BE", "CE", "CD", "ED" }, { "AC", "AD", "BC", "BE", "CE", "CD", "ED" }, { "BA", "AD", "BC", "BE", "CE", "CD", "ED" }, { "BA", "BC", "BE", "CE", "CD", "ED" }, { "AC", "BC", "BE", "CE", "CD", "ED" }, { "AD", "BC", "BE", "CE", "CD", "ED" }, { "BA", "AC", "AD", "BC", "BE", "CD", "ED" }, { "BA", "AC", "BC", "BE", "CD", "ED" }, { "BA", "AD", "BC", "BE", "CD", "ED" }, { "AC", "AD", "BC", "BE", "CD", "ED" }, { "BA", "BC", "BE", "CD", "ED" }, { "AC", "BC", "BE", "CD", "ED" }, { "AD", "BC", "BE", "CD", "ED" }, { "BA", "AC", "AD", "BC", "BE", "CE", "ED" }, { "BA", "AC", "BC", "BE", "CE", "ED" }, { "BA", "AD", "BC", "BE", "CE", "ED" }, { "AC", "AD", "BC", "BE", "CE", "ED" }, { "BA", "BC", "BE", "CE", "ED" }, { "AC", "BC", "BE", "CE", "ED" }, { "AD", "BC", "BE", "CE", "ED" }, { "BA", "AC", "AD", "BE", "CE", "CD", "ED" }, { "BA", "AC", "BE", "CE", "CD", "ED" }, { "BA", "AD", "BE", "CE", "CD", "ED" }, { "AC", "AD", "BE", "CE", "CD", "ED" }, { "BA", "BE", "CE", "CD", "ED" }, { "AC", "BE", "CE", "CD", "ED" }, { "AD", "BE", "CE", "CD", "ED" }, { "AC", "BE", "CE", "ED" }, { "BA", "BE", "CE", "ED" }, { "AD", "BE", "CE", "ED" }, { "BA", "AC", "CD", "ED" }, { "BA", "AD", "CD", "CE" }, { "BA", "AD", "BC", "CE" }, { "BA", "AD", "CD", "BE" }, { "BA", "AD", "CD", "CE" }, { "BA", "BC", "CD", "CE" }, { "BA", "BC", "CD", "ED" }, { "BA", "BC", "BE", "CD" }, { "BA", "AD", "BE", "ED" }, { "BA", "AC", "BE", "CD" }, { "BA", "AD", "BC", "CE" }, { "BA", "BE", "CD", "ED" }, { "AC", "CB", "CD", "CE" }, { "AC", "BC", "CD", "BE" }, { "AC", "BC", "CD", "ED" }, { "AC", "AD", "BC", "ED" }, { "AD", "CD", "CB", "CE" }, { "AD", "BC", "BE", "ED" }, { "AD", "BC", "CD", "CE" }, { "AD", "AC", "BC", "BE" } };
    }
}
