// 
// Decompiled by Procyon v0.5.30
// 

package com.objectbox.runner.beans;

import java.awt.Frame;
import java.awt.Container;
import java.awt.BorderLayout;
import java.awt.event.KeyListener;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseListener;
import java.awt.Graphics;
import java.util.Enumeration;
import java.awt.Dimension;
import java.awt.Point;
import java.awt.FontMetrics;
import java.awt.Font;
import com.objectbox.runner.util.JBLogger;
import java.awt.image.ImageObserver;
import java.awt.event.AdjustmentEvent;
import java.beans.PropertyChangeListener;
import java.awt.LayoutManager;
import java.util.Hashtable;
import java.awt.Component;
import java.awt.Dialog;
import java.awt.Image;
import java.awt.Scrollbar;
import java.beans.PropertyChangeSupport;
import java.awt.Color;
import java.awt.Rectangle;
import java.awt.event.AdjustmentListener;
import java.awt.Panel;

public class TableView extends Panel implements ICellEditor, ICellRenderer, AdjustmentListener
{
    private int NumberOfcols;
    private int NumberOfRows;
    private boolean HasHeader;
    private AbstrTableModel datamodel;
    private int x;
    private int space;
    private int cellwidth;
    private int cellheight;
    private int headerheight;
    private int ymid;
    private int xsize;
    private int ysize;
    private int currentcol;
    private int currentrow;
    private int translateX;
    private int translateY;
    private int scrollxvalue;
    private int scrollyvalue;
    private boolean hasfocus;
    private boolean[] bestfit;
    private boolean hasvericalscroll;
    private boolean hashorisontalscroll;
    private int[] colwidths;
    Rectangle cliprect;
    private boolean initialized;
    protected transient CellselectedListener aCellselectedListener;
    private Color fieldHeaderforeground;
    protected transient PropertyChangeSupport propertyChange;
    private Color fieldHeaderbackground;
    protected transient CellChoosenListener aCellChoosenListener;
    private boolean fieldVerticallines;
    private boolean fieldHorizontallines;
    protected Scrollbar verticalScroll;
    protected Scrollbar horizontalScroll;
    private Image offScreenImage;
    private boolean DEBUG;
    private Image[] imagearray;
    private boolean fieldAutofit;
    private int editCol;
    private int editRow;
    private Dialog defaultEditor;
    private boolean dialogSvar;
    private boolean isEditing;
    private ICellRenderer cellrenderer;
    private ICellEditor celleditor;
    private Component activecelleditor;
    private boolean clipon;
    public static final int CELL_SELECT = 0;
    public static final int ROW_SELECT = 1;
    public static final int COL_SELECT = 2;
    private int selectmode;
    public static final int MODIFIER_NONE = 0;
    public static final int MODIFIER_SHIFT = 1;
    public static final int MODIFIER_CTRL = 2;
    protected Hashtable row_select_hash;
    private int modifier;
    
    public TableView() {
        this.NumberOfcols = 1;
        this.NumberOfRows = 0;
        this.HasHeader = true;
        this.datamodel = null;
        this.x = 0;
        this.space = 4;
        this.cellwidth = 40;
        this.cellheight = 25;
        this.headerheight = 25;
        this.ymid = 0;
        this.xsize = 0;
        this.ysize = 0;
        this.currentcol = 0;
        this.currentrow = 1;
        this.translateX = 0;
        this.translateY = 0;
        this.scrollxvalue = 0;
        this.scrollyvalue = 0;
        this.hasfocus = false;
        this.hasvericalscroll = false;
        this.hashorisontalscroll = false;
        this.cliprect = null;
        this.initialized = false;
        this.aCellselectedListener = null;
        this.fieldHeaderforeground = new Color(255);
        this.fieldHeaderbackground = new Color(100);
        this.aCellChoosenListener = null;
        this.fieldVerticallines = true;
        this.fieldHorizontallines = true;
        this.DEBUG = true;
        this.imagearray = null;
        this.fieldAutofit = true;
        this.editCol = 0;
        this.editRow = 0;
        this.defaultEditor = null;
        this.dialogSvar = true;
        this.isEditing = false;
        this.cellrenderer = this;
        this.celleditor = this;
        this.activecelleditor = null;
        this.clipon = true;
        this.selectmode = 0;
        this.row_select_hash = null;
        this.modifier = 0;
        this.setLayout(null);
        this.enableEvents(63L);
        this.initialize();
    }
    
    public void activateEditorComponent(final Component activeEditor, final int n, final int n2, final int n3, final int n4, final int n5, final int n6) {
        activeEditor.setLocation(n3 - this.getTranslate().x, n4 - this.getTranslate().y);
        activeEditor.setSize(n5 - n3, n6 - n4);
        activeEditor.setVisible(true);
        this.setActiveEditor(activeEditor);
        this.addNotify();
        this.add(activeEditor);
        activeEditor.requestFocus();
    }
    
    public void addCellChoosenListener(final CellChoosenListener cellChoosenListener) {
        this.aCellChoosenListener = CellChoosenEventMulticaster.add(this.aCellChoosenListener, cellChoosenListener);
    }
    
    public void addCellselectedListener(final CellselectedListener cellselectedListener) {
        this.aCellselectedListener = CellselectedEventMulticaster.add(this.aCellselectedListener, cellselectedListener);
    }
    
    public synchronized void addPropertyChangeListener(final PropertyChangeListener propertyChangeListener) {
        this.getPropertyChange().addPropertyChangeListener(propertyChangeListener);
    }
    
    public void adjustmentValueChanged(final AdjustmentEvent adjustmentEvent) {
        if (this.activecelleditor != null) {
            this.remove(this.activecelleditor);
        }
        this.translateX = this.horizontalScroll.getValue();
        this.scrollyvalue = this.verticalScroll.getValue();
        this.translateY = this.scrollyvalue * this.cellheight;
        this.repaint();
    }
    
    public void autoFitAll() {
        final int n = this.getSize().width / this.colwidths.length;
        for (int i = 0; i < this.colwidths.length; ++i) {
            this.colwidths[i] = n;
        }
    }
    
    public void autoFitColumn(final int n) {
        if (!this.getAutofit()) {
            return;
        }
        if (this.colwidths[n] != this.getCellwidth()) {
            this.colwidths[n] = this.getCellwidth();
        }
        else {
            final String s = new String();
            final Font font = this.getFont();
            if (font != null) {
                this.colwidths[n] = 10;
                final FontMetrics fontMetrics = this.getFontMetrics(font);
                for (int i = 0; i < this.NumberOfRows; ++i) {
                    int n2 = 0;
                    final int[] symbolarray = this.getModel().getCellAttribute(n, i).getSymbolarray();
                    if (symbolarray != null) {
                        for (int j = 0; j < symbolarray.length; ++j) {
                            n2 += this.imagearray[symbolarray[j]].getWidth(this) + 4;
                        }
                    }
                    this.colwidths[n] = Math.max(this.colwidths[n], fontMetrics.stringWidth(this.getModel().getCell(n, i)) + 10 + n2);
                }
            }
            else {
                JBLogger.log("f er null");
            }
        }
    }
    
    public void editCell(final int n, final int n2) {
    }
    
    protected void fireCellChoosen(final CellChoosenEvent cellChoosenEvent) {
        if (this.aCellChoosenListener == null) {
            return;
        }
        this.aCellChoosenListener.cellChoosen(cellChoosenEvent);
    }
    
    protected void fireCellselected(final CellselectedEvent cellselectedEvent) {
        if (this.aCellselectedListener == null) {
            return;
        }
        this.aCellselectedListener.cellselected(cellselectedEvent);
    }
    
    public void firePropertyChange(final String s, final Object o, final Object o2) {
        this.getPropertyChange().firePropertyChange(s, o, o2);
    }
    
    public Component getActiveEditor() {
        return this.activecelleditor;
    }
    
    public boolean getAutofit() {
        return this.fieldAutofit;
    }
    
    public ICellEditor getCelleditor() {
        return this.celleditor;
    }
    
    public int getCellheight() {
        return this.cellheight;
    }
    
    public ICellRenderer getCellrenderer() {
        return this.cellrenderer;
    }
    
    public int getCellScreenXPos(final int n) {
        int n2 = 0;
        if (n == 0) {
            return 0;
        }
        for (int i = 0; i < n; ++i) {
            n2 += this.colwidths[i];
        }
        return n2;
    }
    
    public int getCellwidth() {
        return this.cellwidth;
    }
    
    public int getColumnWidth(final int n) throws Exception {
        if (n < 0 || n > this.colwidths.length) {
            throw new Exception("No such column.");
        }
        return this.colwidths[n];
    }
    
    public Point getCurrent() {
        return new Point(this.currentcol, this.currentrow);
    }
    
    public int getCurrentCol() {
        return this.currentcol;
    }
    
    public int getCurrentRow() {
        return this.currentrow;
    }
    
    public AbstrTableModel getDefaultModel() {
        return this.datamodel;
    }
    
    public boolean getHasHeader() {
        return this.HasHeader;
    }
    
    public Color getHeaderbackground() {
        return this.fieldHeaderbackground;
    }
    
    public Color getHeaderforeground() {
        return this.fieldHeaderforeground;
    }
    
    public boolean getHorizontallines() {
        return this.fieldHorizontallines;
    }
    
    public Dimension getMaximumSize() {
        return this.getTotalSize();
    }
    
    public Dimension getMinimumSize() {
        return new Dimension(100, 100);
    }
    
    public AbstrTableModel getModel() {
        if (this.datamodel == null) {
            this.datamodel = new DefaultTableModel();
        }
        return this.datamodel;
    }
    
    public int getModifier() {
        return this.modifier;
    }
    
    public int getNumberOfcols() {
        return this.NumberOfcols = this.datamodel.getNumberOfColumns();
    }
    
    public int getNumberOfRows() {
        return this.NumberOfRows = this.datamodel.getNumberOfRows();
    }
    
    public Dimension getPreferredSize() {
        return this.getTotalSize();
    }
    
    protected PropertyChangeSupport getPropertyChange() {
        if (this.propertyChange == null) {
            this.propertyChange = new PropertyChangeSupport(this);
        }
        return this.propertyChange;
    }
    
    public Enumeration getRowSelectionEnumeration() {
        return this.row_select_hash.keys();
    }
    
    public int getSelectmode() {
        return this.selectmode;
    }
    
    public Dimension getTotalSize() {
        try {
            int n = 0;
            if (this.colwidths == null) {
                this.setNumberOfcols(this.getModel().getNumberOfColumns());
            }
            else {
                for (int i = 0; i < this.colwidths.length; ++i) {
                    n += this.colwidths[i];
                }
            }
            return new Dimension(n, this.getModel().getNumberOfRows() * this.cellheight);
        }
        catch (Exception ex) {
            JBLogger.log("Exception i TableView.getTotalSize " + this.getName() + "\n" + ex);
            return this.getSize();
        }
    }
    
    public Point getTranslate() {
        return new Point(this.translateX, this.translateY);
    }
    
    public boolean getVerticallines() {
        return this.fieldVerticallines;
    }
    
    private void handleException(final Throwable t) {
        JBLogger.log("--------- UNCAUGHT EXCEPTION ---------\n" + this.getName());
        t.printStackTrace(System.out);
    }
    
    public boolean hasEditor(final TableView tableView) {
        return false;
    }
    
    public boolean hasFocus() {
        return this.hasfocus;
    }
    
    public void highligthCell(final int n, final int n2) {
        final Graphics graphics = this.getGraphics();
        new String();
        graphics.translate(-this.translateX, -this.translateY);
        if (graphics != null) {
            if (this.selectmode == 1) {
                for (int i = 0; i < this.getModel().getNumberOfColumns(); ++i) {
                    final int cellScreenXPos = this.getCellScreenXPos(i);
                    final int n3 = n2 * this.cellheight;
                    if (this.clipon) {
                        graphics.setClip(cellScreenXPos, n3, this.colwidths[i], this.cellheight + 1);
                    }
                    if (n2 > 0) {
                        this.cellrenderer.renderCell(this, graphics, i, n2, cellScreenXPos, n3, cellScreenXPos + this.colwidths[i], n3 + this.cellheight, true);
                    }
                    else {
                        this.cellrenderer.renderCell(this, graphics, i, n2, cellScreenXPos, n3, cellScreenXPos + this.colwidths[i], n3 + this.cellheight, true);
                    }
                }
            }
            else {
                final int cellScreenXPos2 = this.getCellScreenXPos(n);
                final int n4 = n2 * this.cellheight;
                if (this.clipon) {
                    graphics.setClip(cellScreenXPos2, n4, this.colwidths[n], this.cellheight + 1);
                }
                if (n2 > 0) {
                    this.cellrenderer.renderCell(this, graphics, n, n2, cellScreenXPos2, n4, cellScreenXPos2 + this.colwidths[n], n4 + this.cellheight, true);
                }
                else {
                    this.cellrenderer.renderCell(this, graphics, n, n2, cellScreenXPos2, n4, cellScreenXPos2 + this.colwidths[n], n4 + this.cellheight, true);
                }
            }
        }
    }
    
    private void initConnections() {
    }
    
    public void initGraphics() {
        this.initialized = true;
        this.setModel(this.getModel());
        this.addMouseListener(new TableViewMouseListener(this));
        this.addMouseMotionListener(new TableViewMouseMotionListener(this));
        this.addKeyListener(new TableViewKeyListener(this));
        final Container parent = this.getParent();
        if (parent != null) {
            final LayoutManager layout = parent.getLayout();
            if (layout != null && layout instanceof BorderLayout) {
                this.verticalScroll = new Scrollbar(1);
                this.horizontalScroll = new Scrollbar(0);
                this.verticalScroll.addAdjustmentListener(this);
                this.horizontalScroll.addAdjustmentListener(this);
                parent.add(this.verticalScroll, "East");
                parent.add(this.horizontalScroll, "South");
                final boolean b = true;
                this.hasvericalscroll = b;
                this.hashorisontalscroll = b;
                parent.doLayout();
            }
        }
    }
    
    private void initialize() {
        this.setName("TableView");
        this.setSize(150, 150);
        this.initConnections();
    }
    
    public void invalidate() {
        if (this.getActiveEditor() != null) {
            this.remove(this.getActiveEditor());
        }
        super.invalidate();
    }
    
    public void invokeEditor(final TableView tableView, final int n, final int n2, final int n3, final int n4, final int n5, final int n6) {
    }
    
    public boolean isFocusTraversable() {
        return true;
    }
    
    public static void main(final String[] array) {
        try {
            Frame frame;
            try {
                frame = (Frame)Class.forName("com.ibm.uvm.abt.edit.TestFrame").newInstance();
            }
            catch (Throwable t) {
                frame = new Frame();
            }
            final TableView tableView = new TableView();
            frame.add("Center", tableView);
            frame.setSize(tableView.getSize());
            frame.setVisible(true);
        }
        catch (Throwable t2) {}
    }
    
    public Point mapGridToScreen(final Point point) {
        return null;
    }
    
    public Point mapScreenToGrid(final Point point) {
        point.x += this.translateX;
        point.y += this.translateY;
        int n = 0;
        int numberOfcols = 0;
        int n2 = 0;
        if (this.colwidths.length > 1) {
            if (point.x < this.colwidths[0]) {
                numberOfcols = 0;
                n = 1;
            }
            if (n == 0) {
                for (int i = 1; i < this.colwidths.length; ++i) {
                    n2 += this.colwidths[i - 1];
                    final int n3 = n2 + this.colwidths[i];
                    if (point.x >= n2 && point.x <= n3) {
                        numberOfcols = i;
                        n = 1;
                        break;
                    }
                }
            }
        }
        if (n == 0) {
            numberOfcols = this.NumberOfcols;
        }
        final int n4 = point.y / this.cellheight;
        Point point2 = new Point(this.currentcol, this.currentrow);
        if (numberOfcols != this.currentcol || n4 != this.currentrow) {
            point2 = new Point(Math.min(numberOfcols, this.NumberOfcols - 1), Math.min(n4, this.NumberOfRows - 1));
        }
        return point2;
    }
    
    public void paint(final Graphics graphics) {
        final int width = this.getSize().width;
        final int height = this.getSize().height;
        if (this.offScreenImage == null) {
            this.offScreenImage = this.createImage(width, height);
        }
        if (width != this.xsize || height != this.ysize) {
            this.xsize = width;
            this.ysize = height;
            this.offScreenImage.flush();
            this.offScreenImage = this.createImage(this.xsize, this.ysize);
        }
        final Graphics graphics2 = this.offScreenImage.getGraphics();
        graphics2.clearRect(0, 0, this.xsize, this.ysize);
        graphics2.setColor(graphics.getColor());
        graphics2.translate(-this.translateX, -this.translateY);
        if (this.datamodel == null) {
            this.setModel(new DefaultTableModel());
        }
        if (!this.initialized) {
            this.initGraphics();
        }
        final Dimension totalSize = this.getTotalSize();
        final Dimension size = this.getSize();
        final int maximum = totalSize.height / this.cellheight;
        final int visibleAmount = size.height / this.cellheight;
        final int n = totalSize.width - size.width + this.cellwidth;
        if (this.hasvericalscroll) {
            if (totalSize.height > size.height) {
                this.verticalScroll.setMinimum(0);
                this.verticalScroll.setMaximum(maximum);
                this.verticalScroll.setVisibleAmount(visibleAmount);
                this.verticalScroll.setValue(this.translateY / this.cellheight);
                if (!this.verticalScroll.isVisible()) {
                    this.verticalScroll.setVisible(true);
                    this.setSize(width - this.verticalScroll.getSize().width, height);
                    this.getParent().validate();
                }
            }
            else {
                this.verticalScroll.setVisible(false);
                this.setSize(this.getParent().getSize());
                this.translateY = 0;
            }
        }
        if (this.hashorisontalscroll) {
            if (this.translateX > 0 || totalSize.width + this.translateX > size.width) {
                this.horizontalScroll.setValues(this.translateX, 10, 0, n);
                this.horizontalScroll.setVisible(true);
                this.getParent().validate();
            }
            else {
                this.horizontalScroll.setVisible(false);
                this.getParent().validate();
            }
        }
        if (this.fieldAutofit) {
            this.autoFitAll();
        }
        this.showTable(graphics2);
        graphics.drawImage(this.offScreenImage, 0, 0, this);
        graphics2.dispose();
    }
    
    public void removeCellChoosenListener(final CellChoosenListener cellChoosenListener) {
        this.aCellChoosenListener = CellChoosenEventMulticaster.remove(this.aCellChoosenListener, cellChoosenListener);
    }
    
    public void removeCellselectedListener(final CellselectedListener cellselectedListener) {
        this.aCellselectedListener = CellselectedEventMulticaster.remove(this.aCellselectedListener, cellselectedListener);
    }
    
    public synchronized void removePropertyChangeListener(final PropertyChangeListener propertyChangeListener) {
        this.getPropertyChange().removePropertyChangeListener(propertyChangeListener);
    }
    
    public void renderCell(final TableView tableView, final Graphics graphics, final int n, final int n2, final int n3, final int n4, final int n5, final int n6, final boolean b) {
        final int n7 = this.cellheight / 2 - graphics.getFontMetrics().getHeight() / 2;
        if (n2 == 0) {
            graphics.setColor(Color.lightGray);
            graphics.fill3DRect(n3, n4, n5 - n3, n6 - n4, true);
            graphics.setColor(Color.black);
            graphics.drawString(this.getModel().getHeaders()[n], n3 + this.space, n4 + this.cellheight - n7);
        }
        else {
            graphics.setColor(Color.black);
            if (b) {
                graphics.setColor(Color.gray);
                graphics.fillRect(n3, n4, n5 - n3, n6 - n4);
                graphics.setColor(Color.white);
            }
            else {
                graphics.setColor(Color.white);
                graphics.fillRect(n3, n4, n5 - n3, n6 - n4);
                graphics.setColor(Color.black);
                graphics.drawRect(n3, n4, n5 - n3, n6 - n4);
            }
            graphics.drawString(this.getModel().getCell(n, n2), n3 + this.space, n4 + this.cellheight - n7);
        }
    }
    
    public int search(String lowerCase) {
        lowerCase = lowerCase.toLowerCase();
        for (int i = this.currentrow + 1; i < this.NumberOfRows; ++i) {
            if (this.getModel().getCell(this.currentcol, i).toLowerCase().startsWith(lowerCase)) {
                return i;
            }
        }
        for (int j = 1; j < this.currentrow; ++j) {
            if (this.getModel().getCell(this.currentcol, j).toLowerCase().startsWith(lowerCase)) {
                return j;
            }
        }
        return -1;
    }
    
    public void selectCell(final int currentcol, final int currentrow) {
        this.unhighligthCell(this.currentcol, this.currentrow);
        this.currentcol = currentcol;
        this.currentrow = currentrow;
        this.highligthCell(this.currentcol, this.currentrow);
        this.fireCellselected(new CellselectedEvent(this, this.currentcol, this.currentrow));
    }
    
    public void setActiveEditor(final Component activecelleditor) {
        this.activecelleditor = activecelleditor;
    }
    
    public void setAutofit(final boolean fieldAutofit) {
        final boolean fieldAutofit2 = this.fieldAutofit;
        this.fieldAutofit = fieldAutofit;
        this.firePropertyChange("autofit", new Boolean(fieldAutofit2), new Boolean(fieldAutofit));
    }
    
    public void setCelleditor(final ICellEditor celleditor) {
        this.celleditor = celleditor;
    }
    
    public void setCellheight(final int cellheight) {
        this.cellheight = cellheight;
        this.repaint();
    }
    
    public void setCellrenderer(final ICellRenderer cellrenderer) {
        this.cellrenderer = cellrenderer;
    }
    
    public void setCellRenderer(final ICellRenderer cellrenderer) {
        this.cellrenderer = cellrenderer;
    }
    
    public void setCellwidth(final int cellwidth) {
        this.cellwidth = cellwidth;
        if (this.colwidths != null) {
            for (int i = 0; i < this.NumberOfcols; ++i) {
                this.colwidths[i] = this.cellwidth;
                this.bestfit[i] = true;
            }
        }
        this.repaint();
    }
    
    public void setClipCell(final boolean clipon) {
        this.clipon = clipon;
    }
    
    public void setColumnWidth(final int n, final int n2) throws Exception {
        if (n < 0 || n > this.colwidths.length) {
            throw new Exception("No such column.");
        }
        this.colwidths[n] = n2;
    }
    
    public void setCurrent(int currentcol, int currentrow) {
        if (this.activecelleditor != null) {
            this.remove(this.activecelleditor);
        }
        boolean b = true;
        if (currentcol < 0) {
            currentcol = 0;
        }
        else if (currentcol >= this.NumberOfcols) {
            currentcol = this.NumberOfcols - 1;
        }
        if (currentrow < 0) {
            currentrow = 0;
        }
        else if (currentrow >= this.NumberOfRows) {
            currentrow = this.NumberOfRows - 1;
        }
        this.currentcol = currentcol;
        this.currentrow = currentrow;
        final int n = this.getCellScreenXPos(currentcol) - this.translateX;
        final int n2 = n + this.colwidths[currentcol];
        final int n3 = currentrow * this.cellheight - this.translateY;
        final int n4 = n3 + this.cellheight;
        final Dimension size = this.getSize();
        if (n <= 0 && n2 >= size.width) {
            b = false;
        }
        if (n3 < this.cellheight) {
            this.translateY += n3;
            if (this.currentrow <= 1) {
                this.currentrow = 1;
                this.translateY = 0;
            }
            else {
                this.translateY -= this.cellheight * 1;
            }
            this.repaint();
        }
        else if (n4 > size.height) {
            this.translateY += ((n4 - size.height) / this.cellheight + 1) * this.cellheight;
            this.repaint();
        }
        if (n < 0 && b) {
            this.translateX += n;
            this.repaint();
        }
        else if (n2 > size.width && b) {
            this.translateX += n2 - size.width;
            this.repaint();
        }
    }
    
    public void setDefaultModel(final AbstrTableModel datamodel) {
        this.datamodel = datamodel;
        this.repaint();
    }
    
    public void setHasFocus(final boolean hasfocus) {
        this.hasfocus = hasfocus;
    }
    
    public void setHasHeader(final boolean hasHeader) {
        this.HasHeader = hasHeader;
        this.repaint();
    }
    
    public void setHeaderbackground(final Color fieldHeaderbackground) {
        this.firePropertyChange("headerbackground", this.fieldHeaderbackground, this.fieldHeaderbackground = fieldHeaderbackground);
        this.repaint();
    }
    
    public void setHeaderforeground(final Color fieldHeaderforeground) {
        this.firePropertyChange("headerforeground", this.fieldHeaderforeground, this.fieldHeaderforeground = fieldHeaderforeground);
        this.repaint();
    }
    
    public void setHorizontallines(final boolean fieldHorizontallines) {
        final boolean fieldHorizontallines2 = this.fieldHorizontallines;
        this.fieldHorizontallines = fieldHorizontallines;
        this.firePropertyChange("horizontallines", new Boolean(fieldHorizontallines2), new Boolean(fieldHorizontallines));
        this.repaint();
    }
    
    public void setImages(final Image[] imagearray) {
        this.imagearray = imagearray;
    }
    
    public void setModel(final AbstrTableModel datamodel) {
        final int currentrow = this.currentrow;
        final int currentcol = this.currentcol;
        if (this.getActiveEditor() != null) {
            this.remove(this.getActiveEditor());
        }
        this.datamodel = datamodel;
        final String[] headers = this.datamodel.getHeaders();
        this.setNumberOfRows(datamodel.getNumberOfRows());
        this.setNumberOfcols(headers.length);
        this.row_select_hash = new Hashtable();
        this.setCurrent(Math.min(this.currentcol, currentrow), Math.min(this.currentrow, currentrow));
    }
    
    public void setModifier(final int modifier) {
        this.modifier = modifier;
    }
    
    public void setNumberOfcols(final int numberOfcols) {
        try {
            this.NumberOfcols = numberOfcols;
            this.colwidths = new int[numberOfcols];
            this.bestfit = new boolean[numberOfcols];
            if (this.getModel() instanceof DefaultTableModel) {
                final DefaultTableModel defaultTableModel = (DefaultTableModel)this.getModel();
                final String[] headers = new String[this.NumberOfcols];
                for (int i = 0; i < headers.length; ++i) {
                    final String[] array = headers;
                    final int n = i;
                    new String();
                    array[n] = String.valueOf(i);
                }
                defaultTableModel.setHeaders(headers);
            }
            for (int j = 0; j < numberOfcols; ++j) {
                this.colwidths[j] = this.cellwidth;
                this.bestfit[j] = true;
            }
            this.repaint();
        }
        catch (Exception ex) {
            JBLogger.log("Exception i TableView.setNumberofcols " + this.getName() + "\n" + ex);
        }
    }
    
    public void setNumberOfRows(final int n) {
        this.NumberOfRows = n;
        if (this.getModel() instanceof DefaultTableModel) {
            ((DefaultTableModel)this.getModel()).setNumberOfRows(n);
        }
        this.repaint();
    }
    
    public void setSelectmode(final int selectmode) {
        this.selectmode = selectmode;
    }
    
    public void setVerticallines(final boolean fieldVerticallines) {
        final boolean fieldVerticallines2 = this.fieldVerticallines;
        this.fieldVerticallines = fieldVerticallines;
        this.firePropertyChange("verticallines", new Boolean(fieldVerticallines2), new Boolean(fieldVerticallines));
        this.repaint();
    }
    
    public void showHeaders(final Graphics graphics) {
        final int numberOfcols = this.getNumberOfcols();
        this.ymid = this.cellheight / 2;
        int n = 1;
        for (int i = 0; i < numberOfcols; ++i) {
            graphics.setColor(this.getHeaderbackground());
            graphics.fill3DRect(n, 0, this.colwidths[i], this.cellheight, true);
            graphics.setColor(this.getHeaderforeground());
            graphics.drawString(this.datamodel.getHeaders()[i], n + this.space, this.cellheight);
            n += this.colwidths[i];
        }
    }
    
    public void showTable(final Graphics graphics) {
        new String();
        final Dimension totalSize = this.getTotalSize();
        final Dimension size = this.getSize();
        final Point mapScreenToGrid = this.mapScreenToGrid(new Point(0, 0));
        final Point mapScreenToGrid2 = this.mapScreenToGrid(new Point(size.width, size.height));
        final int width = totalSize.width;
        final int height = totalSize.height;
        for (int i = mapScreenToGrid.x; i <= mapScreenToGrid2.x; ++i) {
            final int cellScreenXPos = this.getCellScreenXPos(i);
            if (this.clipon) {
                graphics.setClip(cellScreenXPos, this.translateY, cellScreenXPos + this.colwidths[i], this.cellheight);
            }
            this.cellrenderer.renderCell(this, graphics, i, 0, cellScreenXPos, this.translateY, cellScreenXPos + this.colwidths[i], this.translateY + this.cellheight, false);
        }
        int n = 0;
        final Point point = new Point(0, 0);
        for (int j = mapScreenToGrid.x; j <= mapScreenToGrid2.x; ++j) {
            final int cellScreenXPos2 = this.getCellScreenXPos(j);
            for (int k = mapScreenToGrid.y + 1; k <= mapScreenToGrid2.y; ++k) {
                final int n2 = k * this.cellheight;
                point.y = k;
                this.datamodel.getCell(j, k);
                if (this.selectmode == 0) {
                    n = ((k == this.currentrow && j == this.currentcol) ? 1 : 0);
                }
                else if (this.selectmode == 1) {
                    n = ((k == this.currentrow || this.row_select_hash.get(point) != null) ? 1 : 0);
                }
                if (n != 0) {
                    if (this.clipon) {
                        graphics.setClip(cellScreenXPos2, n2, cellScreenXPos2 + this.colwidths[j], this.cellheight + 1);
                    }
                    this.cellrenderer.renderCell(this, graphics, j, k, cellScreenXPos2, n2, cellScreenXPos2 + this.colwidths[j], n2 + this.cellheight, true);
                }
                else {
                    if (this.clipon) {
                        graphics.setClip(cellScreenXPos2, n2, cellScreenXPos2 + this.colwidths[j], this.cellheight + 1);
                    }
                    this.cellrenderer.renderCell(this, graphics, j, k, cellScreenXPos2, n2, cellScreenXPos2 + this.colwidths[j], n2 + this.cellheight, false);
                }
            }
        }
    }
    
    public boolean startCellEditor(final int n, final int n2) {
        try {
            if (this.getCelleditor() == null) {
                return false;
            }
            if (this.getActiveEditor() != null) {
                this.remove(this.getActiveEditor());
            }
            final int cellScreenXPos = this.getCellScreenXPos(n);
            final int n3 = n2 * this.getCellheight();
            this.getCelleditor().invokeEditor(this, n, n2, cellScreenXPos, n3, cellScreenXPos + this.getColumnWidth(n), n3 + this.getCellheight());
            return true;
        }
        catch (Exception ex) {
            System.out.println(ex);
            return false;
        }
    }
    
    public void translate(final int translateX, final int translateY) {
        this.translateX = translateX;
        this.translateY = translateY;
    }
    
    public void unhighligthCell(final int n, final int n2) {
        final Graphics graphics = this.getGraphics();
        new String();
        graphics.translate(-this.translateX, -this.translateY);
        if (graphics != null) {
            if (this.selectmode == 1) {
                for (int i = 0; i < this.getModel().getNumberOfColumns(); ++i) {
                    final int cellScreenXPos = this.getCellScreenXPos(i);
                    final int n3 = n2 * this.cellheight;
                    if (this.clipon) {
                        graphics.setClip(cellScreenXPos, n3, this.colwidths[i], this.cellheight + 1);
                    }
                    if (n2 > 0) {
                        this.cellrenderer.renderCell(this, graphics, i, n2, cellScreenXPos, n3, cellScreenXPos + this.colwidths[i], n3 + this.cellheight, false);
                    }
                    else {
                        this.cellrenderer.renderCell(this, graphics, i, n2, cellScreenXPos, n3, cellScreenXPos + this.colwidths[i], n3 + this.cellheight, false);
                    }
                }
            }
            else {
                final int cellScreenXPos2 = this.getCellScreenXPos(n);
                final int n4 = n2 * this.cellheight;
                if (this.clipon) {
                    graphics.setClip(cellScreenXPos2, n4, this.colwidths[n], this.cellheight + 1);
                }
                if (n2 > 0) {
                    this.cellrenderer.renderCell(this, graphics, n, n2, cellScreenXPos2, n4, cellScreenXPos2 + this.colwidths[n], n4 + this.cellheight, false);
                }
                else {
                    this.cellrenderer.renderCell(this, graphics, n, n2, cellScreenXPos2, n4, cellScreenXPos2 + this.colwidths[n], n4 + this.cellheight, false);
                }
            }
        }
    }
    
    public void update(final Graphics graphics) {
        this.paint(graphics);
    }
}
