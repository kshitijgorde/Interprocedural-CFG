// 
// Decompiled by Procyon v0.5.30
// 

package edu.wise.component;

import java.awt.Event;
import java.awt.Graphics;
import edu.wise.correl.Data;
import edu.wise.exceptions.DataNotFoundException;
import java.awt.LayoutManager;
import edu.wise.correl.gui.StyleSheet;
import edu.wise.correl.Cor_app;
import edu.wise.correl.CorrelData;
import java.awt.Dimension;
import java.awt.Label;
import java.awt.Color;
import java.awt.Font;
import java.awt.Panel;

public class DataEditor extends Panel
{
    private static final int MAX_CASES = 20;
    private static final int MAX_VARS = 2;
    String title;
    private Font titleFont;
    private Font inputFont;
    private int cellWidth;
    private int cellHeight;
    private int titleHeight;
    private int rowLabelWidth;
    private boolean fullUpdate;
    private int currentKey;
    private int selectedVar;
    private int selectedCase;
    private int vars;
    private int cases;
    private Color inputColor;
    private Color cellColor;
    private DataCell current;
    private Label x;
    private Label y;
    private Dimension dim;
    private DataCell[][] cells;
    private CorrelData cd;
    private int cells_start;
    private DataEditorInput dei;
    private Cor_app ca;
    public static final boolean DEBUG = false;
    
    public DataEditor() {
        this.title = "Data Editor";
        this.cellWidth = 100;
        this.cellHeight = 15;
        this.titleHeight = 15;
        this.rowLabelWidth = 15;
        this.fullUpdate = true;
        this.currentKey = -1;
        this.selectedVar = -1;
        this.selectedCase = -1;
        this.current = null;
        this.x = new Label("X");
        this.y = new Label("Y");
        this.dim = new Dimension(400, 380);
        this.cells = new DataCell[2][15];
        this.cells_start = 10;
    }
    
    public DataEditor(final Cor_app ca, final String s, final String s2) {
        this.title = "Data Editor";
        this.cellWidth = 100;
        this.cellHeight = 15;
        this.titleHeight = 15;
        this.rowLabelWidth = 15;
        this.fullUpdate = true;
        this.currentKey = -1;
        this.selectedVar = -1;
        this.selectedCase = -1;
        this.current = null;
        this.x = new Label("X");
        this.y = new Label("Y");
        this.dim = new Dimension(400, 380);
        this.cells = new DataCell[2][15];
        this.cells_start = 10;
        this.cellColor = Color.lightGray;
        this.inputColor = new Color(100, 100, 225);
        this.inputFont = StyleSheet.f_reg;
        this.titleFont = this.inputFont;
        this.vars = 2;
        this.cases = 30;
        this.ca = ca;
        this.cd = Cor_app.cd;
        this.cells = new DataCell[this.vars][this.cases];
        this.setLayout(null);
        this.setBackground(StyleSheet.BACKGROUND);
        this.setSize(this.dim);
        this.cd = this.cd;
        for (int i = 0; i < this.vars; ++i) {
            for (int j = 0; j < this.cases; ++j) {
                (this.cells[i][j] = new DataCell(this, StyleSheet.BACKGROUND, Color.black, this.cellColor, this.cellWidth - 2, this.cellHeight - 2)).setPos(i, j);
                try {
                    this.cells[i][j].setValue(CorrelData.get_val(i, j));
                }
                catch (DataNotFoundException ex) {}
                if (this.cases < CorrelData.N()) {
                    this.cells[i][j].setActive(true);
                }
            }
        }
        this.dei = new DataEditorInput(null, this, this.dim.width - 2, this.cellHeight - 1, this.inputColor, Color.white);
    }
    
    public void setCurrentValue(final double value) {
        if (this.selectedVar == -1 || this.selectedCase == -1) {
            return;
        }
        try {
            this.cells[this.selectedVar][this.selectedCase].setValue(value);
            CorrelData.set_val(this.selectedVar, this.selectedCase, value);
        }
        catch (Exception ex) {}
        this.readVariables();
        this.repaint();
    }
    
    public void setCurrentValue(final int n, final int n2, final double value) {
        try {
            this.cells[n][n2].setValue(value);
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
        this.repaint();
    }
    
    private void readVariables() {
        final Data data = new Data(0, 0);
        for (int i = 0; i < this.cases; ++i) {
            try {
                final double value = this.cells[0][i].getValue();
                final double value2 = this.cells[1][i].getValue();
                if (!Double.isNaN(value) && !Double.isNaN(value2)) {
                    data.addCase(value, value2);
                }
            }
            catch (NumberFormatException ex) {}
        }
        CorrelData.update(data);
        Cor_app.datav.getIDV().update(false);
        Cor_app.datav.repaint();
    }
    
    public void update() {
        for (int i = 0; i < this.vars; ++i) {
            for (int j = 0; j < this.cases; ++j) {
                try {
                    this.setCurrentValue(i, j, CorrelData.get_val(i, j));
                }
                catch (DataNotFoundException ex) {}
            }
        }
        this.repaint();
    }
    
    public void update(final Graphics graphics) {
        if (!this.fullUpdate) {
            graphics.setFont(this.titleFont);
            for (int i = 0; i < this.vars; ++i) {
                for (int j = 0; j < this.cases; ++j) {
                    if (this.cells[i][j].needRedisplay) {
                        this.cells[i][j].paint(graphics, i * this.cellWidth + 2 + this.rowLabelWidth, (j + 2) * this.cellHeight + 2 + this.titleHeight);
                    }
                }
            }
            this.paint(graphics);
        }
        else {
            this.paint(graphics);
            this.fullUpdate = false;
        }
    }
    
    public void paint(final Graphics graphics) {
        final char[] array = { '\0' };
        final Dimension size = this.size();
        graphics.setFont(this.titleFont);
        graphics.drawString((this.title == null) ? "Spreadsheet" : this.title, (size.width - graphics.getFontMetrics().stringWidth(this.title)) / 2, 12);
        graphics.setColor(this.inputColor);
        graphics.fillRect(0, this.cellHeight, size.width, this.cellHeight);
        graphics.setFont(this.inputFont);
        for (int i = 0; i < 20; ++i) {
            final int n = (i + 3) * this.cellHeight;
            graphics.setColor(this.getBackground());
            graphics.draw3DRect(0, n, size.width, 2, true);
            if (i < this.cases) {
                graphics.setColor(Color.red);
                graphics.drawString(String.valueOf(i + 1), 2, n + 12);
            }
        }
        graphics.setColor(StyleSheet.BACKGROUND);
        final int n2 = 2 * this.cellHeight + this.cellHeight / 2 + 4;
        for (int j = 0; j < this.vars + 2; ++j) {
            final int n3 = j * this.cellWidth;
            graphics.setColor(this.getBackground());
            graphics.draw3DRect(n3 + this.rowLabelWidth, 2 * this.cellHeight, 1, size.height, true);
            if (j < this.vars) {
                graphics.setColor(Color.red);
                graphics.drawString(new String(this.cd.getVarName(j)), n3 + this.rowLabelWidth + this.cellWidth / 2 - graphics.getFontMetrics().stringWidth(this.cd.getVarName(j)) / 2, n2);
            }
        }
        for (int k = 0; k < this.vars; ++k) {
            for (int l = 0; l < this.cases; ++l) {
                final int n4 = k * this.cellWidth + 2 + this.rowLabelWidth;
                final int n5 = (l + 2) * this.cellHeight + 2 + this.titleHeight;
                if (this.cells[k][l] != null) {
                    this.cells[k][l].paint(graphics, n4, n5);
                }
            }
        }
        graphics.setColor(this.getBackground());
        graphics.setColor(StyleSheet.BACKGROUND);
        graphics.draw3DRect(0, this.titleHeight, size.width, size.height - this.titleHeight, false);
        this.dei.paint(graphics, 1, this.titleHeight + 1);
    }
    
    public boolean mouseDown(final Event event, final int n, int n2) {
        n2 -= this.titleHeight;
        if (n2 < this.titleHeight + this.cellHeight) {
            this.selectedCase = -1;
            if (n2 <= this.titleHeight && this.current != null) {
                this.current.setActive(false);
                this.current = null;
            }
            return true;
        }
        if (n < this.rowLabelWidth) {
            this.selectedVar = -1;
            if (this.current != null) {
                this.current.setActive(false);
                this.current = null;
            }
            return true;
        }
        this.selectedCase = (n2 - this.cellHeight - this.titleHeight) / this.cellHeight;
        this.selectedVar = (n - this.rowLabelWidth) / this.cellWidth;
        if (this.selectedVar > this.vars || this.selectedCase >= this.cases) {
            this.selectedVar = -1;
            if (this.current != null) {
                this.current.setActive(false);
                this.current = null;
            }
        }
        else {
            if (this.selectedVar >= this.vars) {
                this.selectedVar = -1;
                if (this.current != null) {
                    this.current.setActive(false);
                    this.current = null;
                }
                return true;
            }
            final DataCell current = this.cells[this.selectedVar][this.selectedCase];
            this.dei.setText(new String(current.getPrintString()));
            if (this.current != null) {
                this.current.setActive(false);
            }
            (this.current = current).setActive(true);
            this.requestFocus();
            this.fullUpdate = true;
            this.repaint();
        }
        return true;
    }
    
    public boolean keyDown(final Event event, final int n) {
        this.fullUpdate = true;
        this.dei.keyDown(n);
        return true;
    }
}
