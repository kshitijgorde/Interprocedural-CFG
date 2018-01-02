// 
// Decompiled by Procyon v0.5.30
// 

package jstella.runner;

import javax.swing.SwingUtilities;
import java.awt.event.MouseEvent;
import java.awt.image.ImageObserver;
import java.awt.Image;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseListener;
import javax.swing.JPanel;
import java.awt.Graphics2D;
import java.awt.Graphics;
import java.net.URL;
import java.awt.Dimension;
import java.awt.geom.Point2D;
import java.awt.EventQueue;
import javax.swing.JFrame;
import java.awt.Component;
import java.awt.Frame;
import java.awt.image.BufferedImage;
import java.awt.Point;
import javax.swing.ImageIcon;
import javax.swing.JDialog;

public class VirtualJoystickDialog extends JDialog
{
    private static final long serialVersionUID = -5513424045595185851L;
    private static final double LEASH_FRACTION = 0.5;
    private static final double THRESHOLD_FRACTION = 0.2;
    private static final String DIRECTORY_RESOURCES = "/jstella/resources/";
    private static final String RESOURCE_JOYSTICK_HEAD = "/jstella/resources/joystickhead.gif";
    private static final String RESOURCE_JOYSTICK_BASE = "/jstella/resources/joystickbase.gif";
    private ImageIcon myBaseImage;
    private ImageIcon myHeadImage;
    private Point myCurrentHeadPoint;
    private Point myCurrentGrabPoint;
    private boolean myHeadIsGrabbed;
    private BufferedImage myBackBuffer;
    private CanvasPanel myCanvasPanel;
    private boolean[] myDirections;
    private Point myDragStartPoint;
    private boolean myDialogIsGrabbed;
    private int myHeadHalfWidth;
    private int myHeadHalfHeight;
    
    public VirtualJoystickDialog(final Frame parent, final boolean modal) {
        super(parent, modal);
        this.myBaseImage = null;
        this.myHeadImage = null;
        this.myCurrentHeadPoint = new Point();
        this.myCurrentGrabPoint = new Point();
        this.myHeadIsGrabbed = false;
        this.myBackBuffer = null;
        this.myCanvasPanel = new CanvasPanel();
        this.myDirections = new boolean[4];
        this.myDragStartPoint = new Point();
        this.myDialogIsGrabbed = false;
        this.myHeadHalfWidth = 0;
        this.myHeadHalfHeight = 0;
        this.initComponents();
        this.setUndecorated(true);
        this.getContentPane().add(this.myCanvasPanel, "Center");
        this.loadImages();
        this.validate();
        this.centerHead();
    }
    
    public VirtualJoystickDialog(final Frame aParent) {
        this(aParent, false);
    }
    
    private void initComponents() {
        this.setDefaultCloseOperation(2);
        this.setTitle("JStella Joystick");
        this.setFocusable(false);
        this.setFocusableWindowState(false);
    }
    
    public static void main(final String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                new VirtualJoystickDialog(new JFrame(), true).setVisible(true);
            }
        });
    }
    
    private boolean hitHead(final int aX, final int aY) {
        boolean zReturn = false;
        if (aX >= this.myCurrentHeadPoint.x && aY >= this.myCurrentHeadPoint.y && aX < this.myCurrentHeadPoint.x + this.myHeadImage.getIconWidth() && aY < this.myCurrentHeadPoint.y + this.myHeadImage.getIconHeight()) {
            zReturn = true;
        }
        return zReturn;
    }
    
    private void registerDirections() {
        final Point zCenterHead = this.getHeadCenterPoint();
        final Point zCenterBase = this.getBaseCenterPoint();
        final double zThreshold = this.myBaseImage.getIconWidth() / 2.0 * 0.2;
        final int zRelativeX = zCenterHead.x - zCenterBase.x;
        if (zRelativeX >= zThreshold) {
            this.myDirections[2] = false;
            this.myDirections[3] = true;
        }
        else if (zRelativeX <= zThreshold * -1.0) {
            this.myDirections[2] = true;
            this.myDirections[3] = false;
        }
        else {
            this.myDirections[2] = false;
            this.myDirections[3] = false;
        }
        final int zRelativeY = zCenterHead.y - zCenterBase.y;
        if (zRelativeY >= zThreshold) {
            this.myDirections[0] = false;
            this.myDirections[1] = true;
        }
        else if (zRelativeY <= zThreshold * -1.0) {
            this.myDirections[0] = true;
            this.myDirections[1] = false;
        }
        else {
            this.myDirections[0] = false;
            this.myDirections[1] = false;
        }
        InputMaster.setControllerADirections(this.myDirections);
    }
    
    private void startDialogDrag(final Point aEventPoint) {
        this.myDragStartPoint.setLocation(aEventPoint);
        this.myDialogIsGrabbed = true;
    }
    
    private void stopDialogDrag() {
        this.myDialogIsGrabbed = false;
    }
    
    private void dragDialog(final Point aEventPoint) {
        final Point zCurrentLoc = this.getLocation();
        final int zDeltaX = aEventPoint.x - this.myDragStartPoint.x;
        final int zDeltaY = aEventPoint.y - this.myDragStartPoint.y;
        this.setLocation(zCurrentLoc.x + zDeltaX, zCurrentLoc.y + zDeltaY);
    }
    
    private double distanceFromCenter(final double aProposedHeadX, final double aProposedHeadY) {
        final double zBaseCenterX = this.myBaseImage.getIconWidth() / 2.0;
        final double zBaseCenterY = this.myBaseImage.getIconHeight() / 2.0;
        final double zHeadCenterX = aProposedHeadX + this.myHeadImage.getIconWidth() / 2.0;
        final double zHeadCenterY = aProposedHeadY + this.myHeadImage.getIconHeight() / 2.0;
        return Math.abs(Point2D.distance(zBaseCenterX, zBaseCenterY, zHeadCenterX, zHeadCenterY));
    }
    
    private void loadImages() {
        final URL zBaseURL = this.getClass().getResource("/jstella/resources/joystickbase.gif");
        this.myBaseImage = new ImageIcon(zBaseURL);
        final URL zHeadURL = this.getClass().getResource("/jstella/resources/joystickhead.gif");
        this.myHeadImage = new ImageIcon(zHeadURL);
        this.myBackBuffer = new BufferedImage(this.myBaseImage.getIconWidth(), this.myBaseImage.getIconHeight(), 2);
        this.myCanvasPanel.setPreferredSize(new Dimension(this.myBackBuffer.getWidth(), this.myBackBuffer.getHeight()));
        this.myHeadHalfWidth = this.myHeadImage.getIconWidth() / 2;
        this.myHeadHalfHeight = this.myHeadImage.getIconHeight() / 2;
        this.pack();
    }
    
    private void paintBackBuffer() {
        if (this.myBackBuffer != null) {
            final Graphics2D z2D = this.myBackBuffer.createGraphics();
            this.myBaseImage.paintIcon(this, z2D, 0, 0);
            this.myHeadImage.paintIcon(this, z2D, this.myCurrentHeadPoint.x, this.myCurrentHeadPoint.y);
            z2D.dispose();
            this.myCanvasPanel.repaint();
        }
    }
    
    private Point getHeadCenterPoint(final int aHeadX, final int aHeadY) {
        final int zX = aHeadX + this.myHeadImage.getIconWidth() / 2;
        final int zY = aHeadY + this.myHeadImage.getIconHeight() / 2;
        return new Point(zX, zY);
    }
    
    private Point getHeadCenterPoint() {
        return this.getHeadCenterPoint(this.myCurrentHeadPoint.x, this.myCurrentHeadPoint.y);
    }
    
    private Point getBaseCenterPoint() {
        final int zX = this.myBaseImage.getIconWidth() / 2;
        final int zY = this.myBaseImage.getIconHeight() / 2;
        return new Point(zX, zY);
    }
    
    private void centerHead() {
        this.myCurrentHeadPoint.x = (int)(this.myBaseImage.getIconWidth() / 2.0 - this.myHeadImage.getIconWidth() / 2.0);
        this.myCurrentHeadPoint.y = (int)(this.myBaseImage.getIconHeight() / 2.0 - this.myHeadImage.getIconHeight() / 2.0);
        this.registerDirections();
        this.paintBackBuffer();
    }
    
    private void moveHeadCenter(final int aNewHeadCenterX, final int aNewHeadCenterY) {
        this.moveHead(aNewHeadCenterX - this.myHeadHalfWidth, aNewHeadCenterY - this.myHeadHalfHeight);
    }
    
    private void moveHead(final int aMouseX, final int aMouseY) {
        final double zProposedHeadX = aMouseX - this.myCurrentGrabPoint.x;
        final double zProposedHeadY = aMouseY - this.myCurrentGrabPoint.y;
        final double zDistanceFromCenter = this.distanceFromCenter(zProposedHeadX, zProposedHeadY);
        final double zLeashLength = this.myBaseImage.getIconWidth() / 2.0 * 0.5;
        if (zDistanceFromCenter < zLeashLength) {
            this.myCurrentHeadPoint.x = (int)zProposedHeadX;
            this.myCurrentHeadPoint.y = (int)zProposedHeadY;
        }
        else {
            final Point zBaseCenter = this.getBaseCenterPoint();
            final Point zProposedHeadCenter = this.getHeadCenterPoint((int)zProposedHeadX, (int)zProposedHeadY);
            final int zDeltaX = zProposedHeadCenter.x - zBaseCenter.x;
            final int zDeltaY = zProposedHeadCenter.y - zBaseCenter.y;
            final double zAcceptableFraction = zLeashLength / zDistanceFromCenter;
            this.myCurrentHeadPoint.x = zBaseCenter.x + (int)(zDeltaX * zAcceptableFraction) - this.myHeadHalfWidth;
            this.myCurrentHeadPoint.y = zBaseCenter.y + (int)(zDeltaY * zAcceptableFraction) - this.myHeadHalfHeight;
        }
        this.registerDirections();
        this.paintBackBuffer();
    }
    
    public class CanvasPanel extends JPanel implements MouseListener, MouseMotionListener
    {
        private static final long serialVersionUID = -7124482689826286909L;
        
        public CanvasPanel() {
            this.addMouseListener(this);
            this.addMouseMotionListener(this);
        }
        
        public void paint(final Graphics g) {
            super.paint(g);
            if (VirtualJoystickDialog.this.myBackBuffer != null) {
                g.drawImage(VirtualJoystickDialog.this.myBackBuffer, 0, 0, null);
            }
        }
        
        public void mouseReleased(final MouseEvent e) {
            if (SwingUtilities.isLeftMouseButton(e)) {
                VirtualJoystickDialog.this.centerHead();
                VirtualJoystickDialog.this.myHeadIsGrabbed = false;
            }
            else if (SwingUtilities.isRightMouseButton(e)) {
                InputMaster.setControllerAButton(false);
                VirtualJoystickDialog.this.stopDialogDrag();
            }
            else if (SwingUtilities.isMiddleMouseButton(e)) {
                VirtualJoystickDialog.this.stopDialogDrag();
            }
        }
        
        public void mousePressed(final MouseEvent e) {
            if (SwingUtilities.isLeftMouseButton(e)) {
                if (VirtualJoystickDialog.this.hitHead(e.getX(), e.getY())) {
                    VirtualJoystickDialog.this.myCurrentGrabPoint.x = e.getX() - VirtualJoystickDialog.this.myCurrentHeadPoint.x;
                    VirtualJoystickDialog.this.myCurrentGrabPoint.y = e.getY() - VirtualJoystickDialog.this.myCurrentHeadPoint.y;
                    VirtualJoystickDialog.this.myHeadIsGrabbed = true;
                }
                else {
                    VirtualJoystickDialog.this.myCurrentGrabPoint.x = VirtualJoystickDialog.this.myHeadHalfWidth;
                    VirtualJoystickDialog.this.myCurrentGrabPoint.y = VirtualJoystickDialog.this.myHeadHalfHeight;
                    VirtualJoystickDialog.this.myHeadIsGrabbed = true;
                    VirtualJoystickDialog.this.moveHead(e.getX(), e.getY());
                }
            }
            else if (SwingUtilities.isRightMouseButton(e)) {
                InputMaster.setControllerAButton(true);
                VirtualJoystickDialog.this.startDialogDrag(e.getPoint());
            }
            else if (SwingUtilities.isMiddleMouseButton(e)) {
                VirtualJoystickDialog.this.startDialogDrag(e.getPoint());
            }
        }
        
        public void mouseMoved(final MouseEvent e) {
            if (VirtualJoystickDialog.this.myHeadIsGrabbed) {
                VirtualJoystickDialog.this.moveHead(e.getX(), e.getY());
            }
        }
        
        public void mouseExited(final MouseEvent e) {
        }
        
        public void mouseEntered(final MouseEvent e) {
        }
        
        public void mouseDragged(final MouseEvent e) {
            if (VirtualJoystickDialog.this.myDialogIsGrabbed) {
                VirtualJoystickDialog.this.dragDialog(e.getPoint());
            }
            else if (VirtualJoystickDialog.this.myHeadIsGrabbed) {
                VirtualJoystickDialog.this.moveHead(e.getX(), e.getY());
            }
        }
        
        public void mouseClicked(final MouseEvent e) {
        }
    }
}
