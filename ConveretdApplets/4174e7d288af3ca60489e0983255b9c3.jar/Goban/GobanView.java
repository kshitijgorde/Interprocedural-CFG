// 
// Decompiled by Procyon v0.5.30
// 

package Goban;

import java.awt.event.ComponentEvent;
import java.awt.event.ComponentAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.MouseAdapter;
import java.awt.event.ComponentListener;
import java.awt.event.WindowListener;
import java.awt.Frame;
import java.awt.event.MouseEvent;
import java.awt.Color;
import Go.GoGameEvent;
import java.util.Enumeration;
import Go.MoveInputDeviceListener;
import Go.Move;
import java.awt.Dimension;
import java.awt.image.ImageObserver;
import java.awt.Graphics;
import java.awt.event.MouseListener;
import java.awt.Image;
import java.util.Vector;
import Go.GoPosition;
import Go.GoGameListener;
import Go.MoveInputDevice;
import java.awt.Component;

public class GobanView extends Component implements MoveInputDevice, GoGameListener
{
    private int dx;
    private int dy;
    private int minStone;
    private GoPosition position;
    private int turn;
    private int gobanWidth;
    private int gobanHeight;
    private float cx;
    private float cy;
    private float halfcx;
    private float halfcy;
    private int actualRigthMargin;
    private int actualBottomMargin;
    private Vector listeners;
    private Image offscreen;
    
    public GobanView(final GoPosition position) {
        this.dx = 15;
        this.dy = 15;
        this.minStone = 10;
        this.turn = 1;
        this.position = position;
        try {
            this.jbInit();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        this.listeners = new Vector();
        this.addMouseListener(new 1());
    }
    
    public GobanView() {
        this.dx = 15;
        this.dy = 15;
        this.minStone = 10;
        this.turn = 1;
        try {
            this.jbInit();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public void invalidate() {
        super.invalidate();
        this.offscreen = null;
    }
    
    public void paint(final Graphics g) {
        if (this.offscreen == null) {
            this.offscreen = this.createImage(this.getSize().width, this.getSize().height);
        }
        final Graphics og = this.offscreen.getGraphics();
        og.setClip(0, 0, this.getSize().width, this.getSize().height);
        this.paintEmptyGoban(og);
        this.displayGoPosition(this.position, og);
        g.drawImage(this.offscreen, 0, 0, null);
        og.dispose();
    }
    
    public void update(final Graphics g) {
        this.paint(g);
    }
    
    public Dimension getMinimumSize() {
        final int minStoneY = this.minStone + this.minStone / 5;
        return new Dimension((this.position.getSize() - 1) * this.minStone + 2 * this.dx, (this.position.getSize() - 1) * this.minStone + 2 * this.dy);
    }
    
    public Dimension getPreferredSize() {
        return this.getMinimumSize();
    }
    
    public void fireMoveAttempt(final Move move) {
        final Enumeration e = this.listeners.elements();
        while (e.hasMoreElements()) {
            e.nextElement().moveAttempt(move);
        }
    }
    
    public void positionChanged(final GoGameEvent goGameEvent) {
        this.position = goGameEvent.getNewPosition();
        this.repaint();
    }
    
    public void gameOver(final GoGameEvent goGameEvent) {
    }
    
    public void displayGoPosition(final GoPosition position, final Graphics g) {
        for (int size = position.getSize(), i = 0; i < size; ++i) {
            for (int k = 0; k < size; ++k) {
                this.displayMove(position.goban[i][k].x, position.goban[i][k].y, position.goban[i][k].state, g);
            }
        }
    }
    
    public void displayMove(final int x, final int y, final int state, final Graphics g) {
        final int px = this.xGobanToPhysical(x);
        final int py = this.yGobanToPhysical(y);
        if (state == 1) {
            g.setColor(Color.black);
            g.fillOval((int)(px - this.halfcx), (int)(py - this.halfcy), (int)this.cx, (int)this.cy);
        }
        else if (state == -1) {
            g.setColor(Color.white);
            g.fillOval((int)(px - this.halfcx), (int)(py - this.halfcy), (int)this.cx, (int)this.cy);
            g.setColor(Color.black);
            g.drawOval((int)(px - this.halfcx), (int)(py - this.halfcy), (int)this.cx, (int)this.cy);
        }
    }
    
    public void setGoPosition(final GoPosition newPosition) {
        this.position = newPosition;
        this.repaint();
    }
    
    public void addMoveInputDeviceListener(final MoveInputDeviceListener listener) {
        this.listeners.addElement(listener);
    }
    
    public void removeMoveInputDeviceListener(final MoveInputDeviceListener listener) {
        this.listeners.removeElement(listener);
    }
    
    public void removeAllMoveInputDeviceListeners() {
        final Enumeration e = this.listeners.elements();
        while (e.hasMoreElements()) {
            this.listeners.removeElement(e.nextElement());
        }
    }
    
    private void paintEmptyGoban(final Graphics g) {
        final int size = this.position.getSize();
        this.gobanWidth = this.getSize().width;
        this.gobanHeight = this.getSize().height;
        this.cx = this.gobanWidth / size;
        this.cy = this.gobanHeight / size;
        this.halfcx = (this.gobanWidth - (size - 1) * this.cx) / 2;
        this.halfcy = (this.gobanHeight - (size - 1) * this.cy) / 2;
        this.actualRigthMargin = (int)(this.halfcx + this.cx * (size - 1));
        this.actualBottomMargin = (int)(this.halfcy + this.cy * (size - 1));
        g.setColor(Color.lightGray);
        g.fillRect(0, 0, this.gobanWidth, this.gobanHeight);
        g.setColor(Color.black);
        for (int i = 0; i < size; ++i) {
            g.drawLine((int)this.halfcx, (int)(this.halfcy + this.cy * i), this.actualRigthMargin, (int)(this.halfcy + this.cy * i));
        }
        for (int k = 0; k < size; ++k) {
            g.drawLine((int)(this.halfcx + this.cx * k), (int)this.halfcy, (int)(this.halfcx + this.cx * k), this.actualBottomMargin);
        }
    }
    
    private void processMouseClicked(final MouseEvent e) {
        final int x = e.getX();
        final int y = e.getY();
        final Move moveAttempt = new Move();
        moveAttempt.x = this.xPhysicalToGoban(x);
        moveAttempt.y = this.yPhysicalToGoban(y);
        System.out.println(String.valueOf(String.valueOf(String.valueOf("Move attempt at: ").concat(String.valueOf(moveAttempt.x))).concat(String.valueOf("-"))).concat(String.valueOf(moveAttempt.y)));
        this.fireMoveAttempt(moveAttempt);
    }
    
    private int xPhysicalToGoban(final int x) {
        return (int)(x / this.cx);
    }
    
    private int yPhysicalToGoban(final int y) {
        return (int)(y / this.cy);
    }
    
    private int xGobanToPhysical(final int x) {
        return (int)(this.halfcx + x * this.cx);
    }
    
    private int yGobanToPhysical(final int y) {
        return (int)(this.halfcy + y * this.cy);
    }
    
    public static void main(final String[] args) {
        final GoPosition thePosition = new GoPosition(9);
        final GobanView goban = new GobanView(thePosition);
        final Frame mainFrame = new Frame();
        mainFrame.addWindowListener(new 2(mainFrame));
        mainFrame.add(goban);
        mainFrame.pack();
        mainFrame.setVisible(true);
    }
    
    private void jbInit() throws Exception {
        this.addComponentListener(new 3());
    }
    
    private void componentResizedHandler() {
        final int oldWidth = this.getSize().width;
        final int oldHeight = this.getSize().height;
        final int newHeigth = (oldHeight >= oldWidth) ? oldWidth : oldHeight;
        this.setSize(new Dimension(newHeigth, newHeigth));
    }
    
    class 1 extends MouseAdapter
    {
        public void mouseClicked(final MouseEvent e) {
            GobanView.this.processMouseClicked(e);
        }
    }
    
    static class 2 extends WindowAdapter
    {
        final /* synthetic */ Frame val$mainFrame;
        
        public void windowClosing(final WindowEvent we) {
            this.val$mainFrame.dispose();
            System.exit(0);
        }
        
        2(final Frame val$mainFrame) {
            this.val$mainFrame = val$mainFrame;
        }
    }
    
    class 3 extends ComponentAdapter
    {
        public void componentResized(final ComponentEvent e) {
            GobanView.this.componentResizedHandler();
        }
    }
}
