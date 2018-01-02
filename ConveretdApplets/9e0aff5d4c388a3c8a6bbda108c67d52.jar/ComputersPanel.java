import java.awt.event.ActionEvent;
import java.awt.Color;
import java.awt.image.ImageObserver;
import java.util.Collection;
import java.util.Vector;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.LayoutManager;
import java.awt.Graphics;
import java.awt.Image;
import java.util.Stack;
import javax.swing.JButton;
import java.awt.Point;
import java.awt.event.ActionListener;
import javax.swing.JPanel;

// 
// Decompiled by Procyon v0.5.30
// 

public class ComputersPanel extends JPanel implements ActionListener
{
    private int currentComp;
    private int nextComp;
    private int destComp;
    private int lineIndex;
    private int mode;
    private boolean demoMode;
    final Point[] comps;
    private JButton[] buttons;
    final String[] names;
    private int[] offsets;
    final Line[] lines;
    private int[][] connections;
    private Stack demoMoves;
    private Image computer;
    private int haloOnFrame;
    private int hFx;
    private int hFy;
    private int outlineOnFrame;
    private int outlineSpacer;
    
    public ComputersPanel(final Image _computer, final Graphics g, final String[] _names) {
        this.demoMode = false;
        this.comps = new Point[] { new Point(20, 30), new Point(80, 230), new Point(150, 130), new Point(220, 30), new Point(340, 30), new Point(290, 130), new Point(220, 230), new Point(420, 230) };
        this.buttons = new JButton[this.comps.length];
        this.lines = new Line[] { new Line(new Point(40, 80), new Point(100, 230)), new Line(new Point(60, 50), new Point(220, 50)), new Line(new Point(110, 230), new Point(145, 180)), new Line(new Point(180, 130), new Point(215, 80)), new Line(new Point(120, 250), new Point(220, 250)), new Line(new Point(190, 150), new Point(290, 150)), new Line(new Point(260, 50), new Point(340, 50)), new Line(new Point(240, 230), new Point(275, 180)), new Line(new Point(310, 130), new Point(345, 80)), new Line(new Point(260, 250), new Point(420, 250)), new Line(new Point(370, 80), new Point(430, 230)) };
        this.connections = new int[][] { { -1, 0, -1, 1, -1, -1, -1, -1 }, { 0, -1, 2, -1, -1, -1, 4, -1 }, { -1, 2, -1, 3, -1, 5, -1, -1 }, { 1, -1, 3, -1, 6, -1, -1, -1 }, { -1, -1, -1, 6, -1, 8, -1, 10 }, { -1, -1, 5, -1, 8, -1, 7, -1 }, { -1, 4, -1, -1, -1, 7, -1, 9 }, { -1, -1, -1, -1, 10, -1, 9, -1 } };
        this.haloOnFrame = 0;
        this.outlineOnFrame = 0;
        this.outlineSpacer = 0;
        this.names = _names;
        if (this.comps.length != this.names.length) {
            return;
        }
        this.setLayout(null);
        this.addButtons();
        this.offsets = new int[this.names.length];
        if (this.getGraphics() != null) {
            this.computerOffsets(this.getGraphics());
        }
        this.setPreferredSize(new Dimension(480, 300));
        this.computer = _computer;
        this.mode = -1;
    }
    
    private void addButtons() {
        for (int i = 0; i < this.buttons.length; ++i) {
            (this.buttons[i] = new JButton()).setName(Integer.toString(i));
            this.buttons[i].setBounds(this.comps[i].x, this.comps[i].y, 40, 40);
            this.buttons[i].setContentAreaFilled(false);
            this.buttons[i].setBorderPainted(false);
            this.buttons[i].setFocusPainted(false);
            this.buttons[i].addActionListener(this);
            this.add(this.buttons[i]);
        }
    }
    
    private void computerOffsets(final Graphics g) {
        this.offsets = Toolbox.getStringWidth(g, this.names);
        for (int i = 0; i < this.names.length; ++i) {
            this.offsets[i] = 20 - this.offsets[i] / 2;
        }
    }
    
    public void setDemoMode(final boolean b) {
        this.demoMode = b;
    }
    
    private void setCurrentComp(final int comp) {
        if (comp >= 0 && comp < this.comps.length) {
            this.currentComp = comp;
            this.hFx = this.comps[this.currentComp].x;
            this.hFy = this.comps[this.currentComp].y;
        }
    }
    
    public void setLinksDown() {
        for (int i = 0; i < this.lines.length; ++i) {
            this.lines[i].setOutOfOrder(false);
        }
        double seed = Math.random();
        if (Math.random() < 0.75) {
            if (seed < 0.5) {
                this.lines[0].setOutOfOrder(true);
            }
            else {
                this.lines[1].setOutOfOrder(true);
            }
        }
        if (Math.random() < 0.85) {
            seed = Math.random();
            if (seed < 0.1) {
                this.lines[2].setOutOfOrder(true);
            }
            else if (seed < 0.2) {
                this.lines[3].setOutOfOrder(true);
            }
            else if (seed < 0.3) {
                this.lines[4].setOutOfOrder(true);
            }
            else if (seed < 0.5) {
                this.lines[5].setOutOfOrder(true);
            }
            else if (seed < 0.6) {
                this.lines[6].setOutOfOrder(true);
            }
            else if (seed < 0.7) {
                this.lines[7].setOutOfOrder(true);
            }
            else if (seed < 1.0) {
                this.lines[8].setOutOfOrder(true);
            }
        }
        if (Math.random() < 0.75) {
            seed = Math.random();
            if (seed < 0.5) {
                this.lines[9].setOutOfOrder(true);
            }
            else {
                this.lines[10].setOutOfOrder(true);
            }
        }
    }
    
    private void getDemoModeMoves(int movesLeft) {
        --movesLeft;
        Vector a = new Vector();
        Vector b = new Vector();
        if (!this.lines[0].isOutOfOrder()) {
            a = this.getNextMove(movesLeft, 1);
        }
        if (!this.lines[1].isOutOfOrder()) {
            b = this.getNextMove(movesLeft, 3);
        }
        if (a.size() == 0) {
            this.fillStack(b);
        }
        else if (b.size() == 0) {
            this.fillStack(a);
        }
        else if (a.size() < b.size()) {
            this.fillStack(a);
        }
        else if (a.size() > b.size()) {
            this.fillStack(b);
        }
        else if (Math.random() > 0.5) {
            this.fillStack(a);
        }
        else {
            this.fillStack(b);
        }
    }
    
    private Vector getNextMove(int movesLeft, final int c) {
        --movesLeft;
        Vector v1 = new Vector();
        if (movesLeft == 0) {
            v1.add(new Integer(-1));
            return v1;
        }
        if (c == this.destComp) {
            v1.add(new Integer(c));
            return v1;
        }
        Vector v2 = new Vector();
        for (int i = 1; i < this.comps.length; ++i) {
            final int connection = this.connections[c][i];
            if (connection != -1 && !this.lines[connection].isOutOfOrder()) {
                v2 = this.getNextMove(movesLeft, i);
                if (v1.size() > 0) {
                    if (v2.size() < v1.size()) {
                        v1 = v2;
                    }
                    if (v2.size() == v1.size() && Math.random() > 0.5) {
                        v1 = v2;
                    }
                }
                else {
                    v1 = v2;
                }
            }
        }
        v2 = new Vector();
        v2.add(new Integer(c));
        v2.addAll(v1);
        return v2;
    }
    
    private void fillStack(final Vector v) {
        this.demoMoves = new Stack();
        for (int i = v.size(); i > 0; --i) {
            this.demoMoves.push(v.get(i - 1));
        }
    }
    
    public void initAnimation() {
        this.setCurrentComp(0);
        do {
            this.destComp = (int)(Math.random() * 8.0);
        } while (this.destComp == this.currentComp || this.destComp == 8);
        Network.dg.setFinalAddress(this.destComp);
        Network.dd.setStartState();
        Network.dg.setStartState();
        this.outlineOnFrame = 0;
        this.outlineSpacer = 0;
        this.mode = 0;
    }
    
    public void paint(final Graphics g) {
        super.paint(g);
        for (int i = 0; i < this.comps.length; ++i) {
            g.drawImage(this.computer, this.comps[i].x, this.comps[i].y, this);
        }
        for (int i = 0; i < this.lines.length; ++i) {
            this.lines[i].paint(g);
        }
        switch (this.mode) {
            case 0: {
                this.drawHalo(g);
                if (Network.dg.getMode() == 0) {
                    this.setLinksDown();
                    if (this.demoMode) {
                        this.getDemoModeMoves(7);
                    }
                    final String str = Network.dd.getData();
                    Network.dg.setData(str);
                    Network.dg.setDataColor(Network.dd.getColorOfData());
                    Network.dg.setMode(7);
                    this.mode = 1;
                    break;
                }
                break;
            }
            case 1: {
                if (Network.dd.getMode() == 0 && Network.dg.getMode() == 0) {
                    Network.dg.setMode(9);
                    ++this.mode;
                }
                this.drawHalo(g);
                break;
            }
            case 2: {
                if (Network.dg.getMode() == 0) {
                    if (this.demoMode) {
                        if (this.outlineDestination(g)) {
                            ++this.mode;
                        }
                    }
                    else {
                        ++this.mode;
                    }
                }
                this.drawHalo(g);
                break;
            }
            case 3: {
                if (this.demoMode) {
                    this.findTheComputer(this.names[this.demoMoves.pop()]);
                }
                this.drawHalo(g);
                break;
            }
            case 4: {
                if (Network.dg.getMode() == 0) {
                    this.lines[this.lineIndex].startTransmission(this.comps[this.currentComp], this.comps[this.nextComp], Network.dd.getColorOfData());
                    ++this.mode;
                    break;
                }
                this.drawHalo(g);
                break;
            }
            case 5: {
                if (this.lines[this.lineIndex].isTransmitting()) {
                    break;
                }
                this.setCurrentComp(this.nextComp);
                if (this.currentComp == this.destComp) {
                    Network.dg.setMode(1);
                    this.mode = 7;
                    break;
                }
                Network.dg.setMode(3);
                this.mode = 6;
                break;
            }
            case 6: {
                if (Network.dg.getMode() == 0) {
                    this.mode = 1;
                }
                this.drawHalo(g);
                break;
            }
            case 7: {
                if (Network.dg.getMode() == 0) {
                    Network.dd.setDataTransfered();
                    Network.dg.setMode(8);
                    ++this.mode;
                }
                this.drawHalo(g);
                break;
            }
            case 8: {
                if (Network.dg.getMode() == 0 && Network.dd.getMode() == 0) {
                    if (Network.dd.isMoreData()) {
                        Network.dg.setMode(5);
                        ++this.mode;
                    }
                    else {
                        this.mode = 10;
                    }
                }
                this.drawHalo(g);
                break;
            }
            case 9: {
                if (Network.dg.getMode() == 0) {
                    Network.dg.setMode(6);
                    this.setCurrentComp(0);
                    this.mode = 0;
                    break;
                }
                break;
            }
            case 10: {
                g.setColor(Color.yellow);
                g.drawString("You have successfully transfered all the data!", 20, 100);
                break;
            }
        }
        g.setColor(Color.blue);
        for (int i = 0; i < this.names.length; ++i) {
            if (this.mode == -1 || this.mode == 5 || this.mode == 9 || this.currentComp != i) {
                g.drawString(this.names[i], this.comps[i].x + this.offsets[i], this.comps[i].y + 46);
            }
        }
    }
    
    private void drawHalo(final Graphics g) {
        switch (this.haloOnFrame / 10) {
            case 0: {
                g.setColor(Color.red);
                g.drawLine(this.hFx + 16, this.hFy, this.hFx + 13, this.hFy - 4);
                g.drawLine(this.hFx + 24, this.hFy + 40, this.hFx + 27, this.hFy + 44);
                g.setColor(Color.pink);
                g.drawLine(this.hFx + 20, this.hFy, this.hFx + 20, this.hFy - 4);
                g.drawLine(this.hFx + 24, this.hFy, this.hFx + 27, this.hFy - 4);
                g.drawLine(this.hFx + 20, this.hFy + 40, this.hFx + 20, this.hFy + 44);
                g.drawLine(this.hFx + 16, this.hFy + 40, this.hFx + 13, this.hFy + 44);
                break;
            }
            case 1: {
                g.setColor(Color.red);
                g.drawLine(this.hFx + 20, this.hFy, this.hFx + 20, this.hFy - 4);
                g.drawLine(this.hFx + 20, this.hFy + 40, this.hFx + 20, this.hFy + 44);
                g.setColor(Color.pink);
                g.drawLine(this.hFx + 16, this.hFy, this.hFx + 13, this.hFy - 4);
                g.drawLine(this.hFx + 24, this.hFy + 40, this.hFx + 27, this.hFy + 44);
                g.drawLine(this.hFx + 24, this.hFy, this.hFx + 27, this.hFy - 4);
                g.drawLine(this.hFx + 16, this.hFy + 40, this.hFx + 13, this.hFy + 44);
                break;
            }
            case 2: {
                g.setColor(Color.red);
                g.drawLine(this.hFx + 24, this.hFy, this.hFx + 27, this.hFy - 4);
                g.drawLine(this.hFx + 16, this.hFy + 40, this.hFx + 13, this.hFy + 44);
                g.setColor(Color.pink);
                g.drawLine(this.hFx + 16, this.hFy, this.hFx + 13, this.hFy - 4);
                g.drawLine(this.hFx + 24, this.hFy + 40, this.hFx + 27, this.hFy + 44);
                g.drawLine(this.hFx + 20, this.hFy, this.hFx + 20, this.hFy - 4);
                g.drawLine(this.hFx + 20, this.hFy + 40, this.hFx + 20, this.hFy + 44);
                break;
            }
        }
        this.haloOnFrame += 4;
        if (this.haloOnFrame >= 30) {
            this.haloOnFrame = 0;
        }
    }
    
    public boolean outlineDestination(final Graphics g) {
        if (this.outlineOnFrame == 160) {
            Network.dg.setOutline(false);
            return true;
        }
        if (this.outlineOnFrame == 0) {
            Network.dg.setOutline(true);
        }
        if (this.outlineOnFrame > 120) {
            this.outlineSpacer = 6;
        }
        else if (this.outlineOnFrame > 80) {
            this.outlineSpacer = 4;
        }
        else if (this.outlineOnFrame > 40) {
            this.outlineSpacer = 2;
        }
        g.setColor(Color.red);
        g.drawRect(this.comps[this.destComp].x + this.outlineSpacer, this.comps[this.destComp].y + this.outlineSpacer, 40 - 2 * this.outlineSpacer, 40 - 2 * this.outlineSpacer);
        ++this.outlineOnFrame;
        return false;
    }
    
    private void findTheComputer(final String addr) {
        this.nextComp = -1;
        for (int i = 0; i < this.names.length; ++i) {
            if (this.names[i].equals(addr)) {
                this.nextComp = i;
                break;
            }
        }
        if (this.nextComp >= 0 && this.currentComp != this.nextComp) {
            this.lineIndex = this.connections[this.currentComp][this.nextComp];
            if (this.lineIndex != -1 && !this.lines[this.lineIndex].isOutOfOrder()) {
                this.mode = 4;
                Network.dg.setMode(2);
                Network.dg.setLinkAddress(this.nextComp);
            }
        }
    }
    
    public void actionPerformed(final ActionEvent e) {
        if (this.mode != 3 || this.demoMode) {
            return;
        }
        if (e.getSource() instanceof JButton) {
            final String name = ((JButton)e.getSource()).getName();
            final int index = Integer.parseInt(name);
            this.findTheComputer(this.names[index]);
        }
    }
}
