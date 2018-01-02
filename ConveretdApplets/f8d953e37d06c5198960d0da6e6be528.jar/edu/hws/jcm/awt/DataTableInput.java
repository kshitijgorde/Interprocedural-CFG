// 
// Decompiled by Procyon v0.5.30
// 

package edu.hws.jcm.awt;

import edu.hws.jcm.data.Cases;
import edu.hws.jcm.data.StackOfDouble;
import java.awt.event.ComponentEvent;
import java.awt.event.AdjustmentEvent;
import java.awt.event.TextEvent;
import java.awt.Rectangle;
import java.awt.Dimension;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Canvas;
import java.awt.Scrollbar;
import java.awt.event.ComponentListener;
import java.awt.event.AdjustmentListener;
import java.awt.event.MouseListener;
import java.awt.event.TextListener;
import java.awt.event.MouseEvent;
import java.awt.event.KeyEvent;
import java.awt.TextField;
import edu.hws.jcm.data.NumUtils;
import java.io.IOException;
import java.io.StreamTokenizer;
import java.io.Reader;
import edu.hws.jcm.data.Variable;
import edu.hws.jcm.data.MathObject;
import edu.hws.jcm.data.ExpressionCommand;
import edu.hws.jcm.data.ExpressionProgram;
import edu.hws.jcm.data.ParseError;
import edu.hws.jcm.data.ParserContext;
import edu.hws.jcm.data.Parser;
import java.awt.Component;
import java.awt.LayoutManager;
import java.awt.BorderLayout;
import java.awt.Color;
import java.util.Vector;
import edu.hws.jcm.data.ParserExtension;
import java.awt.Panel;

public class DataTableInput extends Panel implements ParserExtension
{
    private String objectName;
    private Vector rows;
    private Vector rowStrings;
    private String[] columnName;
    private int columnCount;
    private int currentRow;
    private double emptyCellValue;
    private boolean throwErrors;
    private boolean autoAddRows;
    private boolean showColumnTitles;
    private boolean showRowNumbers;
    private DisplayPanel canvas;
    private long serialNumber;
    private Color labelBackground;
    private Color cellBackground;
    private Color blankBackground;
    private Color gridColor;
    
    public DataTableInput() {
        this(null, 0);
    }
    
    public DataTableInput(final String s, final String[] array) {
        this(s, (array == null) ? 0 : array.length);
        if (array != null) {
            for (int i = 0; i < array.length; ++i) {
                this.setColumnName(i, array[i]);
            }
        }
    }
    
    public DataTableInput(final String name, int columnCount) {
        this.currentRow = 1;
        this.emptyCellValue = Double.NaN;
        this.throwErrors = false;
        this.autoAddRows = true;
        this.labelBackground = new Color(220, 220, 220);
        this.cellBackground = new Color(255, 255, 220);
        this.blankBackground = Color.gray;
        this.gridColor = Color.blue;
        if (columnCount < 0) {
            columnCount = 0;
        }
        this.setName(name);
        this.rowStrings = new Vector();
        this.rows = new Vector();
        this.rowStrings.addElement(null);
        this.rows.addElement(null);
        this.columnName = new String[columnCount];
        for (int i = 0; i < columnCount; ++i) {
            this.columnName[i] = "" + (char)(65 + i);
        }
        this.canvas = new DisplayPanel();
        this.setLayout(new BorderLayout());
        this.setBackground(Color.lightGray);
        this.add(this.canvas, "Center");
        this.add(this.canvas.vScroll, "East");
        this.columnCount = columnCount;
    }
    
    public void doParse(final Parser parser, final ParserContext parserContext) {
        if (parserContext.next() != 4 || !parserContext.tokenString.equals(".")) {
            throw new ParseError("Expected a '.' after the name of a data table.", parserContext);
        }
        if (parserContext.next() != 3) {
            throw new ParseError("Expected 'sum', 'count', or the name of a column after data table name.", parserContext);
        }
        final String tokenString = parserContext.tokenString;
        int n = -10;
        for (int i = 0; i < this.columnCount; ++i) {
            if (tokenString.equalsIgnoreCase(this.getColumnName(i))) {
                n = i;
                break;
            }
        }
        if (n == -10) {
            if (tokenString.equalsIgnoreCase("sum")) {
                n = -1;
            }
            else if (tokenString.equalsIgnoreCase("count")) {
                n = -2;
            }
        }
        if (n == -10) {
            throw new ParseError("Unrecognized table command \"" + tokenString + "\".", parserContext);
        }
        if (n == -2) {
            if (parserContext.look() == 4 && parserContext.tokenString.equals("(")) {
                parserContext.next();
                if (parserContext.next() != 4 || !parserContext.tokenString.equals(")")) {
                    throw new ParseError("Missing right parenthesis; \"count\" does not take a parameter.", parserContext);
                }
            }
            parserContext.prog.addCommandObject(new DTEC(-2, null));
            return;
        }
        if (parserContext.next() != 4 || !parserContext.tokenString.equals("(")) {
            throw new ParseError("Expected a left parentheses after table command \"" + tokenString + "\".", parserContext);
        }
        final ExpressionProgram prog = parserContext.prog;
        final ExpressionProgram prog2 = new ExpressionProgram();
        parserContext.prog = prog2;
        if (n == -1) {
            parserContext.mark();
            for (int j = 0; j < this.columnCount; ++j) {
                parserContext.add(this.getColumnVariable(j));
            }
            parserContext.add(this.getRowNumberVariable());
        }
        parser.parseExpression(parserContext);
        parserContext.prog = prog;
        if (parserContext.next() != 4 || !parserContext.tokenString.equals(")")) {
            throw new ParseError("Missing right parenthesis.", parserContext);
        }
        parserContext.prog.addCommandObject(new DTEC(n, prog2));
        if (n == -1) {
            parserContext.revert();
        }
    }
    
    public int getNonEmptyRowCount() {
        int size;
        for (size = this.rows.size(); size > 0 && this.rows.elementAt(size - 1) == null; --size) {}
        return size;
    }
    
    public double getCellContents(final int n, final int n2) {
        if (n < 1 || n > this.rows.size() || n2 < 0 || n2 > this.columnCount) {
            return Double.NaN;
        }
        return this.canvas.getValue(n2, n);
    }
    
    public void setCellContents(final int n, final int n2, final double n3) {
        if (n < 1 || n > this.rows.size() || n2 < 0 || n2 > this.columnCount) {
            return;
        }
        this.canvas.setValue(n2, n, n3);
    }
    
    public void setCurrentRowNumber(final int n) {
        this.currentRow = ((n < 1) ? 1 : n);
    }
    
    public int getCurrentRowNumber() {
        return this.currentRow;
    }
    
    protected Variable getColumnVariable(final int n) {
        if (n < 0 || n >= this.columnCount) {
            throw new IllegalArgumentException("Column number out of range.");
        }
        return new Variable(this.getColumnName(n), 0.0) {
            public void setVal(final double val) {
                if (DataTableInput.this.currentRow < DataTableInput.this.rows.size()) {
                    DataTableInput.this.canvas.setValue(n, DataTableInput.this.currentRow, val);
                }
                super.setVal(val);
            }
            
            public double getVal() {
                if (DataTableInput.this.currentRow > DataTableInput.this.rows.size()) {
                    return Double.NaN;
                }
                return DataTableInput.this.canvas.getValue(n, DataTableInput.this.currentRow);
            }
        };
    }
    
    protected Variable getRowNumberVariable() {
        return new Variable("rowNumber", 0.0) {
            public void setVal(final double n) {
                int n2 = (int)(n + 0.5);
                if (n2 < 1 || n2 > DataTableInput.this.getNonEmptyRowCount()) {
                    n2 = DataTableInput.this.getNonEmptyRowCount() + 1;
                }
                DataTableInput.this.currentRow = n2;
                super.setVal(n2);
            }
            
            public double getVal() {
                return DataTableInput.this.currentRow;
            }
        };
    }
    
    public void addVariablesToParser(final Parser parser) {
        parser.add(this.getRowNumberVariable());
        for (int i = 0; i < this.columnCount; ++i) {
            parser.add(this.getColumnVariable(i));
        }
    }
    
    public long getSerialNumber() {
        return this.serialNumber;
    }
    
    public void setThrowErrors(final boolean throwErrors) {
        this.throwErrors = throwErrors;
    }
    
    public boolean getThrowErrors() {
        return this.throwErrors;
    }
    
    public void setEmptyCellValue(final double emptyCellValue) {
        this.emptyCellValue = emptyCellValue;
    }
    
    public double getEmptyCellValue() {
        return this.emptyCellValue;
    }
    
    public void setAutoAddRows(final boolean autoAddRows) {
        this.autoAddRows = autoAddRows;
        this.canvas.lastRowAutoAdded = false;
    }
    
    public boolean getAutoAddRows() {
        return this.autoAddRows;
    }
    
    public void setName(final String objectName) {
        this.objectName = objectName;
    }
    
    public String getName() {
        return this.objectName;
    }
    
    public void setColumnName(final int n, final String s) {
        if (s != null) {
            this.columnName[n] = s;
        }
    }
    
    public String getColumnName(final int n) {
        return this.columnName[n];
    }
    
    public void addRows(final int n) {
        this.canvas.addRows(n, this.rows.size());
    }
    
    public void insertRow() {
        this.canvas.addRows(1, this.canvas.activeRow);
    }
    
    public void deleteCurrentRow() {
        if (this.canvas.activeRow == this.rows.size() - 1 && this.rows.size() > 1) {
            this.canvas.setActive(this.canvas.activeRow - 1, this.canvas.activeColumn);
            this.rows.removeElementAt(this.canvas.activeRow + 1);
            this.rowStrings.removeElementAt(this.canvas.activeRow + 1);
        }
        else {
            this.rows.removeElementAt(this.canvas.activeRow);
            this.rowStrings.removeElementAt(this.canvas.activeRow);
        }
        if (this.rows.size() == 0) {
            this.rows.addElement(null);
            this.rowStrings.addElement(null);
        }
        final String[] array = this.rowStrings.elementAt(this.canvas.activeRow);
        if (array == null || array[this.canvas.activeColumn] == null) {
            this.canvas.input.setText("");
        }
        else {
            this.canvas.input.setText(array[this.canvas.activeColumn]);
        }
        this.canvas.checkScroll();
        this.canvas.repaint();
        if (this.canvas.rowLabelCanvas != null) {
            this.canvas.rowLabelCanvas.repaint();
        }
        if (this.canvas.columnLabelCanvas != null) {
            this.canvas.columnLabelCanvas.repaint();
        }
        ++this.serialNumber;
    }
    
    public void clear() {
        this.rows = new Vector();
        this.rowStrings = new Vector();
        this.rows.addElement(null);
        this.rowStrings.addElement(null);
        this.canvas.setActive(0, 0);
        this.canvas.checkScroll();
        this.canvas.repaint();
        if (this.canvas.rowLabelCanvas != null) {
            this.canvas.rowLabelCanvas.repaint();
        }
        if (this.canvas.columnLabelCanvas != null) {
            this.canvas.columnLabelCanvas.repaint();
        }
        ++this.serialNumber;
    }
    
    public int getColumnCount() {
        return this.columnName.length;
    }
    
    public int addColumn() {
        return this.addColumn(null);
    }
    
    public int addColumn(final String s) {
        final int n = this.columnName.length + 1;
        final String[] columnName = new String[n];
        for (int i = 0; i < this.columnName.length; ++i) {
            columnName[i] = this.columnName[i];
        }
        if (s == null) {
            columnName[n - 1] = "" + (char)(65 + n - 1);
        }
        else {
            columnName[n - 1] = s;
        }
        this.columnName = columnName;
        for (int size = this.rows.size(), j = 0; j < size; ++j) {
            if (this.rows.elementAt(j) != null) {
                final double[] array = this.rows.elementAt(j);
                final double[] array2 = new double[n];
                for (int k = 0; k < array.length; ++k) {
                    array2[k] = array[k];
                }
                array2[n - 1] = Double.NaN;
                this.rows.setElementAt(array2, j);
            }
            if (this.rowStrings.elementAt(j) != null) {
                final String[] array3 = this.rows.elementAt(j);
                final String[] array4 = new String[n];
                for (int l = 0; l < array3.length; ++l) {
                    array4[l] = array3[l];
                }
                this.rowStrings.setElementAt(array4, j);
            }
        }
        if (this.canvas.hScroll != null) {
            this.canvas.checkScroll();
        }
        this.canvas.repaint();
        if (this.canvas.columnLabelCanvas != null) {
            this.canvas.columnLabelCanvas.repaint();
        }
        this.columnCount = this.columnName.length;
        ++this.serialNumber;
        return this.columnCount - 1;
    }
    
    public boolean getShowColumnTitles() {
        return this.showColumnTitles;
    }
    
    public void setShowColumnTitles(final boolean showColumnTitles) {
        if (showColumnTitles == this.showColumnTitles) {
            return;
        }
        this.showColumnTitles = showColumnTitles;
        if (this.showColumnTitles) {
            this.canvas.makeColumnLabelCanvas();
            this.add(this.canvas.columnLabelCanvas, "North");
        }
        else {
            this.remove(this.canvas.columnLabelCanvas);
            this.canvas.columnLabelCanvas = null;
        }
    }
    
    public boolean getShowRowNumbers() {
        return this.showRowNumbers;
    }
    
    public void setShowRowNumbers(final boolean showRowNumbers) {
        if (showRowNumbers == this.showRowNumbers) {
            return;
        }
        this.showRowNumbers = showRowNumbers;
        if (this.showRowNumbers) {
            this.canvas.makeRowLabelCanvas();
            this.add(this.canvas.rowLabelCanvas, "West");
        }
        else {
            this.remove(this.canvas.rowLabelCanvas);
            this.canvas.rowLabelCanvas = null;
        }
    }
    
    public Color getLabelBackground() {
        return this.labelBackground;
    }
    
    public void setLabelBackground(final Color labelBackground) {
        if (labelBackground != null) {
            this.labelBackground = labelBackground;
        }
    }
    
    public Color getCellBackground() {
        return this.cellBackground;
    }
    
    public void setCellBackground(final Color cellBackground) {
        if (cellBackground != null) {
            this.cellBackground = cellBackground;
        }
    }
    
    public Color getBlankBackground() {
        return this.blankBackground;
    }
    
    public void setBlankBackground(final Color blankBackground) {
        if (blankBackground != null) {
            this.blankBackground = blankBackground;
        }
    }
    
    public Color getGridColor() {
        return this.gridColor;
    }
    
    public void setGridColor(final Color gridColor) {
        if (gridColor != null) {
            this.gridColor = gridColor;
        }
    }
    
    public boolean readFromStream(final Reader reader) {
        final Vector<double[]> rows = new Vector<double[]>();
        final int columnCount = this.columnCount;
        try {
            final StreamTokenizer streamTokenizer = new StreamTokenizer(reader);
            streamTokenizer.resetSyntax();
            streamTokenizer.eolIsSignificant(true);
            streamTokenizer.whitespaceChars(44, 44);
            streamTokenizer.whitespaceChars(32, 32);
            streamTokenizer.whitespaceChars(9, 9);
            streamTokenizer.wordChars(97, 122);
            streamTokenizer.wordChars(65, 90);
            streamTokenizer.wordChars(48, 57);
            streamTokenizer.wordChars(46, 46);
            streamTokenizer.wordChars(43, 43);
            streamTokenizer.wordChars(45, 45);
            int n = streamTokenizer.nextToken();
            while (true) {
                if (n == 10) {
                    n = streamTokenizer.nextToken();
                }
                else if (n == -1) {
                    if (this.rows.size() == 0) {
                        throw new IOException("Empty data was found.");
                    }
                    break;
                }
                else {
                    final double[] array = new double[columnCount];
                    for (int i = 0; i < columnCount; ++i) {
                        if (n == 10 || n == -1) {
                            array[i] = Double.NaN;
                        }
                        else {
                            if (n != -3) {
                                throw new IOException("Illegal non-numeric data encountered.");
                            }
                            if (streamTokenizer.sval.equalsIgnoreCase("undefined")) {
                                array[i] = Double.NaN;
                            }
                            else {
                                try {
                                    array[i] = new Double(streamTokenizer.sval);
                                }
                                catch (NumberFormatException ex2) {
                                    throw new IOException("Illegal non-numeric data (" + streamTokenizer.sval + ") encountered.");
                                }
                            }
                            n = streamTokenizer.nextToken();
                        }
                    }
                    rows.addElement(array);
                    while (n != 10 && n != -1) {
                        n = streamTokenizer.nextToken();
                    }
                }
            }
        }
        catch (Exception ex) {
            if (this.throwErrors) {
                throw new JCMError("Error while reading data:  " + ex, this);
            }
            return false;
        }
        this.canvas.setActive(0, 0);
        this.rows = rows;
        this.rowStrings = new Vector();
        for (int j = 0; j < this.rows.size(); ++j) {
            final String[] array2 = new String[columnCount];
            final double[] array3 = this.rows.elementAt(j);
            for (int k = 0; k < columnCount; ++k) {
                if (Double.isNaN(array3[k])) {
                    array2[k] = null;
                }
                else {
                    array2[k] = NumUtils.realToString(array3[k]);
                }
            }
            this.rowStrings.addElement(array2);
        }
        this.canvas.input.setText(((String[])this.rowStrings.elementAt(0))[0]);
        if (this.canvas.hScroll != null) {
            this.canvas.hScroll.setValue(0);
        }
        this.canvas.vScroll.setValue(0);
        this.canvas.checkScroll();
        this.canvas.repaint();
        if (this.canvas.rowLabelCanvas != null) {
            this.canvas.rowLabelCanvas.repaint();
        }
        if (this.canvas.columnLabelCanvas != null) {
            this.canvas.columnLabelCanvas.repaint();
        }
        ++this.serialNumber;
        return true;
    }
    
    private class InputBox extends TextField
    {
        InputBox() {
            super(12);
            this.setBackground(Color.white);
            this.setForeground(Color.black);
            this.enableEvents(24L);
        }
        
        public void processKeyEvent(final KeyEvent keyEvent) {
            if (keyEvent.getID() == 401) {
                final int keyCode = keyEvent.getKeyCode();
                final char keyChar = keyEvent.getKeyChar();
                if (((keyChar == '\0' || !Character.isDigit(keyChar)) && keyChar != '.' && keyChar != 'E' && keyChar != '-' && keyChar != '+' && keyChar != 'e' && keyCode != 127 && keyCode != 8 && keyChar != '\0') || keyCode == 10 || keyCode == 40 || keyCode == 38 || keyCode == 9) {
                    if (keyCode == 10 || keyCode == 40) {
                        DataTableInput.this.canvas.doRowDown();
                    }
                    else if (keyCode == 38) {
                        DataTableInput.this.canvas.doRowUp();
                    }
                    else if (keyCode == 9) {
                        DataTableInput.this.canvas.doColumnRight();
                    }
                    keyEvent.consume();
                }
                else if (keyCode == 37 && this.getCaretPosition() == 0) {
                    DataTableInput.this.canvas.doColumnLeft();
                    keyEvent.consume();
                }
                else if (keyCode == 39 && this.getCaretPosition() == this.getText().length()) {
                    DataTableInput.this.canvas.doColumnRight();
                    keyEvent.consume();
                }
            }
            super.processKeyEvent(keyEvent);
        }
        
        public void processMouseEvent(final MouseEvent mouseEvent) {
            if (mouseEvent.getID() == 501) {
                DataTableInput.this.canvas.ensureActiveVisible();
            }
            super.processMouseEvent(mouseEvent);
        }
    }
    
    private class DisplayPanel extends Panel implements TextListener, MouseListener, AdjustmentListener, ComponentListener
    {
        InputBox input;
        int activeRow;
        int activeColumn;
        int rowHeight;
        int columnWidth;
        Scrollbar hScroll;
        Scrollbar vScroll;
        Canvas rowLabelCanvas;
        Canvas columnLabelCanvas;
        boolean lastRowAutoAdded;
        
        DisplayPanel() {
            this.activeRow = 0;
            this.activeColumn = 0;
            this.rowHeight = -1;
            this.setBackground(DataTableInput.this.cellBackground);
            this.input = new InputBox();
            (this.vScroll = new Scrollbar(1)).setBackground(Color.lightGray);
            this.input.addTextListener(this);
            this.vScroll.addAdjustmentListener(this);
            this.addMouseListener(this);
            this.setLayout(null);
            this.add(this.input);
            this.addComponentListener(this);
        }
        
        void makeRowLabelCanvas() {
            (this.rowLabelCanvas = new Canvas() {
                public void paint(final Graphics graphics) {
                    final int n = DisplayPanel.this.vScroll.getValue() / DisplayPanel.this.rowHeight;
                    final int n2 = this.getSize().height / DisplayPanel.this.rowHeight + 1;
                    final int size = DataTableInput.this.rows.size();
                    final FontMetrics fontMetrics = graphics.getFontMetrics();
                    final int n3 = (DisplayPanel.this.rowHeight + fontMetrics.getAscent()) / 2;
                    final int value = DisplayPanel.this.vScroll.getValue();
                    for (int n4 = n; n4 < n2 + n && n4 < size; ++n4) {
                        final String string = "" + (n4 + 1);
                        graphics.drawString(string, (this.getSize().width - fontMetrics.stringWidth(string)) / 2, n3 + DisplayPanel.this.rowHeight * n4 - value);
                    }
                }
                
                public Dimension getPreferredSize() {
                    return new Dimension(35, 50);
                }
            }).setBackground(DataTableInput.this.labelBackground);
        }
        
        void makeColumnLabelCanvas() {
            (this.columnLabelCanvas = new Canvas() {
                public void paint(final Graphics graphics) {
                    int n = 0;
                    if (DisplayPanel.this.hScroll != null) {
                        n = DisplayPanel.this.hScroll.getValue() / DisplayPanel.this.columnWidth;
                    }
                    final int n2 = (DisplayPanel.this.rowLabelCanvas == null) ? 0 : 35;
                    final int n3 = (this.getSize().width - n2) / DisplayPanel.this.columnWidth + 1;
                    final FontMetrics fontMetrics = graphics.getFontMetrics();
                    final int n4 = (this.getSize().height + fontMetrics.getAscent()) / 2;
                    final int n5 = (DisplayPanel.this.hScroll == null) ? 0 : DisplayPanel.this.hScroll.getValue();
                    for (int n6 = n; n6 < n + n3 && n6 < DataTableInput.this.columnCount; ++n6) {
                        final String columnName = DataTableInput.this.getColumnName(n6);
                        graphics.drawString(columnName, n2 + n6 * DisplayPanel.this.columnWidth + (DisplayPanel.this.columnWidth - fontMetrics.stringWidth(columnName)) / 2 - n5, n4);
                    }
                    graphics.setColor(Color.gray);
                    graphics.fillRect(0, 0, n2, this.getSize().height);
                }
                
                public Dimension getPreferredSize() {
                    return new Dimension(50, 20);
                }
            }).setBackground(DataTableInput.this.labelBackground);
        }
        
        public void addNotify() {
            super.addNotify();
            if (this.rowHeight != -1) {
                return;
            }
            final Dimension preferredSize = this.input.getPreferredSize();
            this.rowHeight = preferredSize.height - 1;
            this.columnWidth = preferredSize.width - 1;
            this.input.setBounds(1, 1, this.columnWidth + 1, this.rowHeight + 1);
        }
        
        public void update(final Graphics graphics) {
            this.paint(graphics);
        }
        
        public void paint(final Graphics graphics) {
            final int n = (this.hScroll == null) ? 0 : this.hScroll.getValue();
            final int value = this.vScroll.getValue();
            final int width = this.getSize().width;
            final int height = this.getSize().height;
            final int n2 = DataTableInput.this.columnCount * this.columnWidth + 2;
            final int n3 = DataTableInput.this.rows.size() * this.rowHeight + 2;
            final int size = DataTableInput.this.rows.size();
            final Rectangle clipBounds = graphics.getClipBounds();
            int n4;
            int n5;
            if (clipBounds != null) {
                n4 = (value + clipBounds.y) / this.rowHeight;
                n5 = clipBounds.height / this.rowHeight + 1;
                final int n6 = (n + clipBounds.x) / this.columnWidth;
                final int n7 = clipBounds.width / this.columnWidth + 1;
            }
            else {
                n4 = value / this.rowHeight;
                n5 = height / this.rowHeight + 1;
                final int n8 = n / this.columnWidth;
                final int n9 = width / this.columnWidth + 1;
            }
            final int n10 = (this.rowHeight + graphics.getFontMetrics().getAscent()) / 2;
            for (int n11 = n4; n11 < n4 + n5 && n11 < size; ++n11) {
                final String[] array = DataTableInput.this.rowStrings.elementAt(n11);
                for (int i = 0; i < DataTableInput.this.columnCount; ++i) {
                    if (i != this.activeColumn || n11 != this.activeRow) {
                        graphics.setColor(DataTableInput.this.cellBackground);
                        graphics.fillRect(1 + i * this.columnWidth - n, 1 + n11 * this.rowHeight - value, this.columnWidth, this.rowHeight);
                        graphics.setColor(this.getForeground());
                        if (array != null && array[i] != null && array[i].length() > 0) {
                            graphics.drawString(array[i], i * this.columnWidth + 5 - n, n10 + n11 * this.rowHeight - value);
                        }
                    }
                }
            }
            if (width > n2) {
                graphics.setColor(DataTableInput.this.blankBackground);
                graphics.fillRect(n2, 0, width - n2, height);
            }
            if (height > n3) {
                graphics.setColor(DataTableInput.this.blankBackground);
                graphics.fillRect(0, n3, width, height - n3);
            }
            graphics.setColor(DataTableInput.this.gridColor);
            graphics.drawRect(0, 0, n2, n3);
            graphics.drawRect(1, 1, n2 - 2, n3 - 2);
            for (int n12 = -1; n12 < n4 + n5 && n12 < size; ++n12) {
                graphics.drawLine(0, 1 + (n12 + 1) * this.rowHeight - value, n2 - 1, 1 + (n12 + 1) * this.rowHeight - value);
            }
            for (int j = 0; j <= DataTableInput.this.columnCount; ++j) {
                graphics.drawLine(1 + j * this.columnWidth - n, 0, 1 + j * this.columnWidth - n, n3 - 1);
            }
        }
        
        void setActive(final int activeRow, final int activeColumn) {
            if (activeRow != this.activeRow || activeColumn != this.columnWidth) {
                this.input.setLocation(-((this.hScroll == null) ? 0 : this.hScroll.getValue()) + activeColumn * this.columnWidth + 1, -this.vScroll.getValue() + activeRow * this.rowHeight + 1);
                this.activeRow = activeRow;
                this.activeColumn = activeColumn;
                final String[] array = DataTableInput.this.rowStrings.elementAt(this.activeRow);
                if (array == null || array[this.activeColumn] == null) {
                    this.input.setText("");
                }
                else {
                    this.input.setText(array[this.activeColumn]);
                }
            }
            this.ensureActiveVisible();
            this.input.selectAll();
            this.input.requestFocus();
        }
        
        void doRowDown() {
            final int size = DataTableInput.this.rows.size();
            if (this.activeRow == size - 1 && DataTableInput.this.autoAddRows && DataTableInput.this.rows.elementAt(size - 1) != null) {
                this.addRows(1, size);
                this.lastRowAutoAdded = true;
            }
            if (this.activeRow < DataTableInput.this.rows.size() - 1) {
                this.setActive(this.activeRow + 1, this.activeColumn);
            }
            else {
                this.ensureActiveVisible();
                this.input.requestFocus();
            }
        }
        
        void doRowUp() {
            if (this.activeRow == 0) {
                return;
            }
            this.setActive(this.activeRow - 1, this.activeColumn);
            if (DataTableInput.this.autoAddRows && this.lastRowAutoAdded && this.activeRow == DataTableInput.this.rows.size() - 2 && DataTableInput.this.rows.elementAt(this.activeRow + 1) == null) {
                DataTableInput.this.rows.removeElementAt(DataTableInput.this.rows.size() - 1);
                DataTableInput.this.rowStrings.removeElementAt(DataTableInput.this.rowStrings.size() - 1);
                this.checkScroll();
                this.repaint();
                if (this.rowLabelCanvas != null) {
                    this.rowLabelCanvas.repaint();
                }
                this.input.requestFocus();
            }
            this.lastRowAutoAdded = false;
        }
        
        void doColumnRight() {
            int n = this.activeColumn + 1;
            if (n >= DataTableInput.this.columnCount) {
                n = 0;
            }
            this.setActive(this.activeRow, n);
        }
        
        void doColumnLeft() {
            int n = this.activeColumn - 1;
            if (n < 0) {
                n = DataTableInput.this.columnCount - 1;
            }
            this.setActive(this.activeRow, n);
        }
        
        void ensureActiveVisible() {
            final int n = this.columnWidth * this.activeColumn + 1;
            final int n2 = this.rowHeight * this.activeRow + 1;
            final int n3 = (this.hScroll == null) ? 0 : this.hScroll.getValue();
            final int value = this.vScroll.getValue();
            final int n4 = n3 + this.getSize().width;
            final int n5 = value + this.getSize().height;
            int n6 = 0;
            int n7 = 0;
            if (n + this.columnWidth > n4) {
                n6 = -(n + this.columnWidth - n4);
            }
            if (n < n3) {
                n6 = n3 - n;
            }
            if (n2 + this.rowHeight > n5) {
                n7 = -(n2 + this.rowHeight - n5);
            }
            if (n2 < value) {
                n7 = value - n2;
            }
            if (n6 == 0 && n7 == 0) {
                return;
            }
            if (n6 != 0) {
                if (this.hScroll != null) {
                    this.hScroll.setValue(n3 - n6);
                }
                if (this.columnLabelCanvas != null) {
                    this.columnLabelCanvas.repaint();
                }
            }
            if (n7 != 0) {
                this.vScroll.setValue(value - n7);
                if (this.rowLabelCanvas != null) {
                    this.rowLabelCanvas.repaint();
                }
            }
            this.input.setLocation(n - ((this.hScroll == null) ? 0 : this.hScroll.getValue()), n2 - this.vScroll.getValue());
            this.repaint();
        }
        
        void addRows(final int n, int n2) {
            DataTableInput.this.serialNumber++;
            if (n <= 0) {
                return;
            }
            if (n2 >= DataTableInput.this.rows.size()) {
                for (int i = 0; i < n; ++i) {
                    DataTableInput.this.rows.addElement(null);
                    DataTableInput.this.rowStrings.addElement(null);
                    this.lastRowAutoAdded = false;
                }
            }
            else {
                if (n2 < 0) {
                    n2 = 0;
                }
                for (int j = 0; j < n; ++j) {
                    DataTableInput.this.rows.insertElementAt(null, n2);
                    DataTableInput.this.rowStrings.insertElementAt(null, n2);
                }
                if (this.activeRow >= n2) {
                    final String[] array = DataTableInput.this.rowStrings.elementAt(this.activeRow);
                    if (array == null || array[this.activeColumn] == null) {
                        this.input.setText("");
                    }
                    else {
                        this.input.setText(array[this.activeColumn]);
                    }
                }
            }
            this.checkScroll();
            this.repaint();
            if (this.rowLabelCanvas != null) {
                this.rowLabelCanvas.repaint();
            }
        }
        
        void setValue(final int n, final int n2, final double n3) {
            final String realToString = NumUtils.realToString(n3);
            this.setRowData(n2 - 1, n, realToString, n3);
            if (n == this.activeColumn && n2 - 1 == this.activeRow) {
                this.input.setText(realToString);
            }
            else {
                this.repaintItem(n2 - 1, n);
            }
        }
        
        double getValue(final int n, final int n2) {
            if (DataTableInput.this.rows.elementAt(n2 - 1) == null) {
                return DataTableInput.this.emptyCellValue;
            }
            final double n3 = ((double[])DataTableInput.this.rows.elementAt(n2 - 1))[n];
            if (!Double.isNaN(n3)) {
                return n3;
            }
            final String s = ((String[])DataTableInput.this.rowStrings.elementAt(n2 - 1))[n];
            if (s == null || s.length() == 0) {
                return DataTableInput.this.emptyCellValue;
            }
            if (DataTableInput.this.throwErrors) {
                throw new JCMError("Invalid numerical input in data table, column \"" + DataTableInput.this.getColumnName(n) + "\", row " + n2 + ".", this);
            }
            return Double.NaN;
        }
        
        public void textValueChanged(final TextEvent textEvent) {
            final String trim = this.input.getText().trim();
            if (trim.length() == 0) {
                this.setRowData(this.activeRow, this.activeColumn, trim, Double.NaN);
            }
            else {
                double doubleValue;
                try {
                    doubleValue = new Double(trim);
                }
                catch (NumberFormatException ex) {
                    doubleValue = Double.NaN;
                }
                if (Double.isNaN(doubleValue)) {
                    this.setRowData(this.activeRow, this.activeColumn, "bad input", doubleValue);
                }
                else {
                    this.setRowData(this.activeRow, this.activeColumn, trim, doubleValue);
                }
            }
        }
        
        void setRowData(final int n, final int n2, String realToString, final double n3) {
            DataTableInput.this.serialNumber++;
            double[] array = DataTableInput.this.rows.elementAt(n);
            String[] array2 = DataTableInput.this.rowStrings.elementAt(n);
            if (realToString.length() == 0) {
                if (array2 == null || array2[n2] == null) {
                    return;
                }
                array2[n2] = null;
                array[n2] = Double.NaN;
                boolean b = true;
                for (int i = 0; i < array2.length; ++i) {
                    if (array2[i] != null) {
                        b = false;
                        break;
                    }
                }
                if (b) {
                    DataTableInput.this.rows.setElementAt(null, n);
                    DataTableInput.this.rowStrings.setElementAt(null, n);
                }
            }
            else {
                if (realToString.length() > 12) {
                    realToString = NumUtils.realToString(n3, 12);
                }
                if (array2 == null) {
                    final int access$600 = DataTableInput.this.columnCount;
                    array = new double[access$600];
                    array2 = new String[access$600];
                    for (int j = 0; j < access$600; ++j) {
                        array[j] = Double.NaN;
                    }
                    DataTableInput.this.rows.setElementAt(array, n);
                    DataTableInput.this.rowStrings.setElementAt(array2, n);
                }
                array2[n2] = realToString;
                array[n2] = n3;
            }
        }
        
        protected void repaintItem(final int n, final int n2) {
            final int n3 = n * this.rowHeight - this.vScroll.getValue();
            int n4 = n2 * this.columnWidth;
            if (this.hScroll != null) {
                n4 -= this.hScroll.getValue();
            }
            this.repaint(n4 + 1, n3 + 1, this.columnWidth - 1, this.rowHeight - 1);
        }
        
        public void adjustmentValueChanged(final AdjustmentEvent adjustmentEvent) {
            this.repaint();
            if (adjustmentEvent.getSource() == this.vScroll) {
                if (this.rowLabelCanvas != null) {
                    this.rowLabelCanvas.repaint();
                }
            }
            else if (this.columnLabelCanvas != null) {
                this.columnLabelCanvas.repaint();
            }
            int n = this.columnWidth * this.activeColumn + 1;
            if (this.hScroll != null) {
                n -= this.hScroll.getValue();
            }
            this.input.setLocation(n, this.rowHeight * this.activeRow + 1 - this.vScroll.getValue());
        }
        
        public void mousePressed(final MouseEvent mouseEvent) {
            final int n = (this.hScroll == null) ? 0 : this.hScroll.getValue();
            int n2 = (mouseEvent.getY() + this.vScroll.getValue() - 1) / this.rowHeight;
            if (n2 < 0) {
                n2 = 0;
            }
            else if (n2 >= DataTableInput.this.rows.size()) {
                n2 = DataTableInput.this.rows.size() - 1;
            }
            int n3 = (mouseEvent.getX() + n - 1) / this.columnWidth;
            if (n3 < 0) {
                n3 = 0;
            }
            else if (n3 >= DataTableInput.this.columnCount) {
                n3 = DataTableInput.this.columnCount - 1;
            }
            if (n2 == this.activeRow && n3 == this.activeColumn) {
                this.ensureActiveVisible();
            }
            else {
                this.setActive(n2, n3);
            }
            final int n4 = DataTableInput.this.rows.size() - 1;
            if (!this.lastRowAutoAdded || n4 == n2) {
                return;
            }
            this.lastRowAutoAdded = false;
            if (DataTableInput.this.rows.elementAt(n4) != null) {
                return;
            }
            DataTableInput.this.rows.removeElementAt(n4);
            DataTableInput.this.rowStrings.removeElementAt(n4);
            this.checkScroll();
            this.repaint();
            if (this.rowLabelCanvas != null) {
                this.rowLabelCanvas.repaint();
            }
        }
        
        public void componentResized(final ComponentEvent componentEvent) {
            this.checkScroll();
        }
        
        void checkScroll() {
            final int width = this.getSize().width;
            int height = this.getSize().height;
            if (this.rowHeight == -1 || width <= 1) {
                return;
            }
            final int n = this.columnWidth * DataTableInput.this.columnCount + 2;
            final int n2 = this.rowHeight * DataTableInput.this.rows.size() + 2;
            final int value = this.vScroll.getValue();
            final int n3 = (this.hScroll == null) ? 0 : this.hScroll.getValue();
            boolean b = false;
            if (width >= n - 2) {
                if (this.hScroll != null) {
                    final int height2 = this.hScroll.getPreferredSize().height;
                    DataTableInput.this.remove(this.hScroll);
                    this.hScroll = null;
                    height += height2;
                    b = true;
                }
            }
            else {
                if (this.hScroll == null) {
                    (this.hScroll = new Scrollbar(0)).setBackground(Color.lightGray);
                    height -= this.hScroll.getPreferredSize().height;
                    DataTableInput.this.add(this.hScroll, "South");
                    this.hScroll.addAdjustmentListener(this);
                    b = true;
                }
                if (n3 > n - width) {
                    this.hScroll.setValues(n - width, width, 0, n);
                }
                else {
                    this.hScroll.setValues(n3, width, 0, n);
                }
                this.hScroll.setUnitIncrement(this.columnWidth / 4);
                if (width > 1) {
                    this.hScroll.setBlockIncrement(3 * width / 4);
                }
            }
            if (height >= n2 - 2) {
                this.vScroll.setEnabled(false);
                this.vScroll.setValues(0, 1, 0, 1);
            }
            else {
                if (value > n2 - height) {
                    this.vScroll.setValues(n2 - height, height, 0, n2);
                }
                else {
                    this.vScroll.setValues(value, height, 0, n2);
                }
                this.vScroll.setUnitIncrement(this.rowHeight);
                if (height > 1) {
                    this.vScroll.setBlockIncrement(3 * height / 4);
                }
                this.vScroll.setEnabled(true);
            }
            int n4 = this.columnWidth * this.activeColumn + 1;
            if (this.hScroll != null) {
                n4 -= this.hScroll.getValue();
            }
            this.input.setLocation(n4, this.rowHeight * this.activeRow + 1 - this.vScroll.getValue());
            if (b) {
                DataTableInput.this.validate();
            }
        }
        
        public Dimension getPreferredSize() {
            if (this.rowHeight == -1) {
                return new Dimension(350, 200);
            }
            if (DataTableInput.this.columnCount >= 4) {
                return new Dimension(4 * this.columnWidth + 2, 6 * this.rowHeight + 2);
            }
            return new Dimension(DataTableInput.this.columnCount * this.columnWidth + 2, 6 * this.rowHeight + 2);
        }
        
        public void mouseEntered(final MouseEvent mouseEvent) {
        }
        
        public void mouseExited(final MouseEvent mouseEvent) {
        }
        
        public void mouseClicked(final MouseEvent mouseEvent) {
        }
        
        public void mouseReleased(final MouseEvent mouseEvent) {
        }
        
        public void componentHidden(final ComponentEvent componentEvent) {
        }
        
        public void componentShown(final ComponentEvent componentEvent) {
        }
        
        public void componentMoved(final ComponentEvent componentEvent) {
        }
    }
    
    private class DTEC implements ExpressionCommand
    {
        ExpressionProgram prog;
        int command;
        
        DTEC(final int command, final ExpressionProgram prog) {
            this.command = command;
            this.prog = prog;
        }
        
        public void apply(final StackOfDouble stackOfDouble, final Cases cases) {
            if (this.command >= 0) {
                final double val = this.prog.getVal();
                if (Double.isNaN(val) || val < 0.5 || val >= DataTableInput.this.rows.size() + 0.5) {
                    stackOfDouble.push(Double.NaN);
                }
                else {
                    stackOfDouble.push(DataTableInput.this.canvas.getValue(this.command, (int)(val + 0.5)));
                }
            }
            else if (this.command == -1) {
                double n = 0.0;
                for (int nonEmptyRowCount = DataTableInput.this.getNonEmptyRowCount(), i = 1; i <= nonEmptyRowCount; ++i) {
                    DataTableInput.this.setCurrentRowNumber(i);
                    n += this.prog.getVal();
                }
                stackOfDouble.push(n);
            }
            else if (this.command == -2) {
                stackOfDouble.push(DataTableInput.this.getNonEmptyRowCount());
            }
        }
        
        public void compileDerivative(final ExpressionProgram expressionProgram, final int n, final ExpressionProgram expressionProgram2, final Variable variable) {
            if (this.command != -1) {
                expressionProgram2.addConstant(0.0);
            }
            else {
                expressionProgram2.addCommandObject(new DTEC(this.command, (ExpressionProgram)this.prog.derivative(variable)));
            }
        }
        
        public int extent(final ExpressionProgram expressionProgram, final int n) {
            return 1;
        }
        
        public boolean dependsOn(final Variable variable) {
            return this.command != -2 && this.prog.dependsOn(variable);
        }
        
        public void appendOutputString(final ExpressionProgram expressionProgram, final int n, final StringBuffer sb) {
            sb.append(DataTableInput.this.getName());
            sb.append('.');
            if (this.command == -2) {
                sb.append("count");
            }
            else if (this.command == -1) {
                sb.append("sum");
            }
            else {
                sb.append(DataTableInput.this.getColumnName(this.command));
            }
            sb.append("(");
            if (this.command != -2) {
                sb.append(this.prog.toString());
            }
            sb.append(")");
        }
    }
}
