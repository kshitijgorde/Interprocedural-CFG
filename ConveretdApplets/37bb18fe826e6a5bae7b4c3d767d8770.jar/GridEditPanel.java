import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.Component;
import java.awt.Graphics;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseListener;
import java.awt.LayoutManager;
import java.awt.BorderLayout;
import java.util.ArrayList;
import java.awt.Point;
import java.awt.Color;
import javax.swing.event.MouseInputListener;
import javax.swing.JPanel;

// 
// Decompiled by Procyon v0.5.30
// 

public final class GridEditPanel extends JPanel implements MouseInputListener
{
    private Color[] colList;
    private boolean editMode;
    private boolean playMode;
    private boolean changed;
    private GridType gridType;
    private Coord[] floatShape;
    private int floatHandle;
    private boolean mouseInside;
    private Board board;
    private Point mousePoint;
    private Coord selectStartCoord;
    private Coord selectEndCoord;
    private boolean selecting;
    private Coord clickedCoord;
    private boolean mouseOverBlock;
    ArrayList actionListeners;
    
    public GridEditPanel(final boolean editMode, final boolean playMode) {
        this.changed = false;
        this.mouseInside = true;
        this.board = new Board();
        this.mousePoint = new Point();
        this.selectStartCoord = new Coord();
        this.selectEndCoord = new Coord();
        this.selecting = false;
        this.clickedCoord = new Coord();
        this.mouseOverBlock = false;
        this.actionListeners = new ArrayList();
        this.editMode = editMode;
        this.playMode = playMode;
        this.setLayout(new BorderLayout());
        this.addMouseListener(this);
        this.addMouseMotionListener(this);
        this.setBackground(Color.WHITE);
    }
    
    public void setNumColours(final int n) {
        this.colList = new Color[n];
        float n2 = 0.0f;
        final float n3 = 1.0f / n;
        for (int i = 0; i < n; ++i) {
            this.colList[i] = Color.getHSBColor(n2, 1.0f, 1.0f);
            n2 += n3;
        }
    }
    
    public void setFloat(final Coord[] floatShape, final int floatHandle) {
        if (floatShape == null) {
            this.floatShape = null;
        }
        else {
            this.floatHandle = floatHandle;
            this.floatShape = floatShape;
        }
    }
    
    public void setGridType(final GridType gridType) {
        (this.gridType = GridType.factory(gridType.getClass())).reset();
    }
    
    public GridType getGridType() {
        return this.gridType;
    }
    
    public void setBlockList(final boolean changed) {
        this.board.wipe();
        this.reset();
        this.changed = changed;
    }
    
    public void setBlockList(final Coord[] array, final int n) {
        this.board.setBoard(array, n);
        this.reset();
    }
    
    public void setBoard(final Board board) {
        this.board.setBoard(board);
        this.reset();
    }
    
    public void setBoard(final Solution board) {
        this.board.setBoard(board);
        this.reset();
    }
    
    public void reset() {
        this.gridType.reset();
        final boolean b = false;
        this.changed = b;
        this.mouseOverBlock = b;
        this.repaint();
    }
    
    public Board getBoard() {
        return this.board;
    }
    
    public boolean hasChanged() {
        final boolean changed = this.changed;
        this.changed = false;
        return changed;
    }
    
    public void toggleBlock(final Coord contents) {
        this.changed = true;
        if (this.board.getContents(contents) == contents.ori) {
            this.board.setContents(contents, -1);
        }
        else {
            this.board.setContents(contents);
        }
    }
    
    public void setBlock(final Coord contents) {
        this.changed = true;
        this.board.setContents(contents);
    }
    
    public void paintComponent(final Graphics graphics) {
        super.paintComponent(graphics);
        this.gridType.paintComponent(this, graphics, this.board, this.colList, this.editMode);
        if (this.floatShape != null) {
            if (this.mouseInside) {
                graphics.setColor(Color.RED);
                this.gridType.paintCentredOutline(graphics, this.floatShape, this.mousePoint.x, this.mousePoint.y, this.floatHandle);
            }
        }
        else if (this.editMode) {
            if (this.selecting) {
                graphics.setColor(Color.RED);
                this.gridType.paintOutline(graphics, this.selectStartCoord, this.selectEndCoord);
            }
            else if (this.mouseOverBlock) {
                graphics.setColor(Color.RED);
                this.gridType.paintOutline(graphics, this.selectEndCoord, this.selectEndCoord);
            }
        }
    }
    
    public void mousePressed(final MouseEvent mouseEvent) {
        if (this.editMode) {
            this.selecting = this.gridType.screen2Grid(this.selectStartCoord, mouseEvent.getX(), mouseEvent.getY());
            this.selectEndCoord.set(this.selectStartCoord);
        }
        this.repaint();
    }
    
    public void mouseDragged(final MouseEvent mouseEvent) {
        if (this.selecting && this.gridType.screen2Grid(this.selectEndCoord, mouseEvent.getX(), mouseEvent.getY())) {
            this.repaint();
        }
    }
    
    public void mouseMoved(final MouseEvent mouseEvent) {
        if (this.editMode) {
            final boolean screen2Grid = this.gridType.screen2Grid(this.selectEndCoord, mouseEvent.getX(), mouseEvent.getY());
            if (screen2Grid && !this.selectEndCoord.equals(this.selectStartCoord)) {
                this.mouseOverBlock = screen2Grid;
                this.selectStartCoord.set(this.selectEndCoord);
                this.repaint();
            }
            else if (screen2Grid != this.mouseOverBlock) {
                this.mouseOverBlock = screen2Grid;
                this.repaint();
            }
        }
        else if (this.floatShape != null) {
            this.mousePoint.x = mouseEvent.getX();
            this.mousePoint.y = mouseEvent.getY();
            this.repaint();
        }
    }
    
    public void mouseReleased(final MouseEvent mouseEvent) {
        this.mousePoint.x = mouseEvent.getX();
        this.mousePoint.y = mouseEvent.getY();
        int n = 0;
        if (this.editMode) {
            if (this.selecting) {
                final Coord coord = new Coord();
                this.gridType.getFirstBlock(coord, this.selectStartCoord, this.selectEndCoord);
                do {
                    this.toggleBlock(coord);
                } while (this.gridType.getNextBlock(coord, this.selectStartCoord, this.selectEndCoord));
                this.gridType.reset();
                n = 1;
                final boolean b = false;
                this.mouseOverBlock = b;
                this.selecting = b;
            }
        }
        else if (this.playMode) {
            boolean screen2Grid = this.gridType.screen2Grid(this.clickedCoord, mouseEvent.getX(), mouseEvent.getY());
            int contents = -1;
            if (screen2Grid) {
                contents = this.board.getContents(this.clickedCoord);
                screen2Grid &= (contents >= 0);
            }
            if (screen2Grid) {
                if (this.floatShape == null && contents > 0) {
                    n = -contents;
                }
                else if (this.floatShape != null && contents == 0) {
                    this.clickedCoord.sub(this.floatShape[this.floatHandle]);
                    if (this.gridType.numDim() < 3) {
                        this.clickedCoord.z = 0;
                    }
                    n = 2;
                }
            }
        }
        else {
            this.gridType.screen2Grid(this.clickedCoord, mouseEvent.getX(), mouseEvent.getY());
        }
        this.fireActionPerformed(n);
        this.repaint();
    }
    
    public Coord getClickedCoord() {
        return this.clickedCoord;
    }
    
    public void mouseEntered(final MouseEvent mouseEvent) {
        this.mouseInside = true;
    }
    
    public void mouseExited(final MouseEvent mouseEvent) {
        this.mouseInside = false;
        this.repaint();
    }
    
    public void mouseClicked(final MouseEvent mouseEvent) {
    }
    
    public void addActionListener(final ActionListener actionListener) {
        if (!this.actionListeners.contains(actionListener)) {
            this.actionListeners.add(actionListener);
        }
    }
    
    public void removeActionListener(final ActionListener actionListener) {
        this.actionListeners.remove(actionListener);
    }
    
    public ActionListener[] getActionListeners() {
        return (ActionListener[])this.actionListeners.toArray();
    }
    
    protected void fireActionPerformed(final int n) {
        int i = this.actionListeners.size();
        if (i > 0) {
            final ActionEvent actionEvent = new ActionEvent(this, n, "");
            --i;
            while (i >= 0) {
                this.actionListeners.get(i).actionPerformed(actionEvent);
                --i;
            }
        }
    }
}
