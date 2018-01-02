// 
// Decompiled by Procyon v0.5.30
// 

package rene.viewer;

import java.awt.Graphics;
import java.awt.Frame;
import java.awt.event.KeyEvent;
import java.awt.event.ActionEvent;
import java.awt.event.MouseEvent;
import java.io.PrintWriter;
import java.awt.event.AdjustmentEvent;
import java.awt.Color;
import java.awt.Font;
import java.awt.MenuItem;
import rene.gui.Global;
import java.awt.Component;
import rene.gui.Panel3D;
import java.awt.LayoutManager;
import java.awt.BorderLayout;
import java.awt.PopupMenu;
import java.awt.Scrollbar;
import java.awt.event.KeyListener;
import java.awt.event.ActionListener;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseListener;
import java.awt.event.AdjustmentListener;
import java.awt.Panel;

public class Viewer extends Panel implements AdjustmentListener, MouseListener, MouseMotionListener, ActionListener, KeyListener
{
    TextDisplay TD;
    Scrollbar Vertical;
    Scrollbar Horizontal;
    TextPosition Start;
    TextPosition End;
    PopupMenu PM;
    int X;
    int Y;
    boolean Dragging;
    
    public Viewer(final boolean b, final boolean b2) {
        this.Dragging = false;
        this.TD = new TextDisplay(this);
        this.setLayout(new BorderLayout());
        final Panel3D panel3D;
        this.add("Center", panel3D = new Panel3D(this.TD));
        panel3D.setBackground(this.TD.getBackground());
        if (b) {
            this.add("East", this.Vertical = new Scrollbar(1, 0, 100, 0, 1100));
            this.Vertical.addAdjustmentListener(this);
        }
        if (b2) {
            this.add("South", this.Horizontal = new Scrollbar(0, 0, 100, 0, 1100));
            this.Horizontal.addAdjustmentListener(this);
        }
        this.TD.addMouseListener(this);
        this.TD.addMouseMotionListener(this);
        final TextPosition textPosition = null;
        this.End = textPosition;
        this.Start = textPosition;
        this.PM = new PopupMenu();
        final MenuItem menuItem = new MenuItem(Global.name("block.copy", "Copy"));
        menuItem.addActionListener(this);
        this.PM.add(menuItem);
        this.PM.addSeparator();
        final MenuItem menuItem2 = new MenuItem(Global.name("block.begin", "Begin Block"));
        menuItem2.addActionListener(this);
        this.PM.add(menuItem2);
        final MenuItem menuItem3 = new MenuItem(Global.name("block.end", "End Block"));
        menuItem3.addActionListener(this);
        this.PM.add(menuItem3);
        this.add(this.PM);
    }
    
    public Viewer() {
        this(true, true);
    }
    
    public Viewer(final String s) {
        this.Dragging = false;
    }
    
    public void setFont(final Font font) {
        this.TD.init(font);
    }
    
    public void appendLine(final String s) {
        this.TD.appendLine0(s);
    }
    
    public void appendLine(final String s, final Color color) {
        this.TD.appendLine0(s, color);
    }
    
    public void append(final String s) {
        this.append(s, Color.black);
    }
    
    public void append(final String s, final Color color) {
        this.TD.append(s, color);
    }
    
    public void doUpdate(final boolean b) {
        this.TD.doUpdate(b);
        this.setVerticalScrollbar();
    }
    
    public void adjustmentValueChanged(final AdjustmentEvent adjustmentEvent) {
        if (adjustmentEvent.getSource() == this.Vertical) {
            switch (adjustmentEvent.getAdjustmentType()) {
                case 1: {
                    this.TD.verticalUp();
                    break;
                }
                case 2: {
                    this.TD.verticalDown();
                    break;
                }
                case 4: {
                    this.TD.verticalPageUp();
                    break;
                }
                case 3: {
                    this.TD.verticalPageDown();
                    break;
                }
                default: {
                    final int value = this.Vertical.getValue();
                    this.Vertical.setValue(value);
                    this.TD.setVertical(value);
                    return;
                }
            }
            this.setVerticalScrollbar();
            return;
        }
        if (adjustmentEvent.getSource() == this.Horizontal) {
            this.Horizontal.setValue(this.TD.setHorizontal(this.Horizontal.getValue()));
        }
    }
    
    public void setVerticalScrollbar() {
        if (this.Vertical == null) {
            return;
        }
        final int computeVerticalSize = this.TD.computeVerticalSize();
        this.Vertical.setValues(this.TD.computeVertical(), computeVerticalSize, 0, 1000 + computeVerticalSize);
    }
    
    public void setText(final String text) {
        this.TD.unmark();
        final TextPosition textPosition = null;
        this.End = textPosition;
        this.Start = textPosition;
        this.TD.setText(text);
        this.setVerticalScrollbar();
    }
    
    public void save(final PrintWriter printWriter) {
        this.TD.save(printWriter);
    }
    
    public void appendLine0(final String s) {
        this.TD.appendLine0(s);
    }
    
    public void mouseClicked(final MouseEvent mouseEvent) {
    }
    
    public void mousePressed(final MouseEvent mouseEvent) {
        if (mouseEvent.isPopupTrigger() || mouseEvent.isMetaDown()) {
            this.PM.show(mouseEvent.getComponent(), mouseEvent.getX(), mouseEvent.getY());
            this.X = mouseEvent.getX();
            this.Y = mouseEvent.getY();
            return;
        }
        this.TD.unmark(this.Start, this.End);
        (this.Start = this.TD.getposition(mouseEvent.getX(), mouseEvent.getY())).oneleft();
        this.End = null;
    }
    
    public void mouseReleased(final MouseEvent mouseEvent) {
        this.Dragging = false;
    }
    
    public void mouseEntered(final MouseEvent mouseEvent) {
    }
    
    public void mouseExited(final MouseEvent mouseEvent) {
    }
    
    public void mouseMoved(final MouseEvent mouseEvent) {
    }
    
    public void mouseDragged(final MouseEvent mouseEvent) {
        this.TD.unmark(this.Start, this.End);
        final TextPosition getposition = this.TD.getposition(mouseEvent.getX(), mouseEvent.getY());
        if (getposition != null) {
            this.End = getposition;
        }
        this.TD.mark(this.Start, this.End);
    }
    
    public void actionPerformed(final ActionEvent actionEvent) {
        final String actionCommand = actionEvent.getActionCommand();
        if (actionCommand.equals(Global.name("block.copy", "Copy"))) {
            this.TD.copy(this.Start, this.End);
            return;
        }
        if (actionCommand.equals(Global.name("block.begin", "Begin Block"))) {
            this.TD.unmark(this.Start, this.End);
            (this.Start = this.TD.getposition(this.X, this.Y)).oneleft();
            if (this.End == null && this.TD.L.last() != null) {
                this.End = this.TD.lastpos();
            }
            this.TD.mark(this.Start, this.End);
            return;
        }
        if (actionCommand.equals(Global.name("block.end", "End Block"))) {
            this.TD.unmark(this.Start, this.End);
            this.End = this.TD.getposition(this.X, this.Y);
            if (this.Start == null && this.TD.L.first() != null) {
                this.Start = new TextPosition(this.TD.L.first(), 0, 0);
            }
            this.TD.mark(this.Start, this.End);
        }
    }
    
    public void keyPressed(final KeyEvent keyEvent) {
    }
    
    public void keyReleased(final KeyEvent keyEvent) {
        if (keyEvent.isControlDown() && keyEvent.getKeyCode() == 67 && this.Start != null && this.End != null) {
            this.TD.copy(this.Start, this.End);
        }
    }
    
    public void keyTyped(final KeyEvent keyEvent) {
    }
    
    public static void main(final String[] array) {
        final Frame frame = new Frame();
        frame.setLayout(new BorderLayout());
        final Viewer viewer = new Viewer(true, false);
        frame.add("Center", viewer);
        frame.setSize(300, 300);
        frame.setVisible(true);
        viewer.append("test");
        viewer.appendLine("test");
    }
    
    public void paint(final Graphics graphics) {
        this.setVerticalScrollbar();
        this.TD.doUpdate(true);
        super.paint(graphics);
    }
}
