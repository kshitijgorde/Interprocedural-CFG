// 
// Decompiled by Procyon v0.5.30
// 

package edu.hws.jcm.functions;

import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.ItemEvent;
import java.awt.event.ActionEvent;
import java.awt.Insets;
import edu.hws.jcm.data.NumUtils;
import edu.hws.jcm.awt.JCMError;
import java.awt.Component;
import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.LayoutManager;
import java.awt.FlowLayout;
import java.awt.Label;
import edu.hws.jcm.draw.Drawable;
import edu.hws.jcm.draw.Axes;
import edu.hws.jcm.draw.CoordinateRect;
import java.awt.Font;
import java.awt.Color;
import edu.hws.jcm.awt.Controller;
import java.awt.CheckboxGroup;
import java.awt.Checkbox;
import java.awt.Button;
import java.awt.List;
import edu.hws.jcm.draw.DisplayCanvas;
import edu.hws.jcm.awt.VariableInput;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseListener;
import java.awt.event.ActionListener;
import java.awt.event.ItemListener;
import java.awt.Panel;

public class TableFunctionInput extends Panel implements ItemListener, ActionListener, MouseListener, MouseMotionListener
{
    private VariableInput xInput;
    private VariableInput yInput;
    private DisplayCanvas canvas;
    private List pointList;
    private Button clearButton;
    private Button deleteButton;
    private Button addButton;
    private Checkbox[] styleCheckbox;
    private CheckboxGroup styleGroup;
    private Controller onChange;
    private TableFunction function;
    private TableFunction editFunction;
    private int dragPoint;
    private int startX;
    private int startY;
    private int prevY;
    private boolean moved;
    
    public TableFunctionInput() {
        this.styleCheckbox = new Checkbox[5];
        this.dragPoint = -1;
        (this.xInput = new VariableInput()).addActionListener(this);
        (this.yInput = new VariableInput()).addActionListener(this);
        (this.pointList = new List()).setBackground(Color.white);
        this.pointList.setFont(new Font("Monospaced", 0, 12));
        this.pointList.addItemListener(this);
        (this.clearButton = new Button("Remove All Points")).addActionListener(this);
        (this.deleteButton = new Button("Delete Point")).setEnabled(false);
        this.deleteButton.addActionListener(this);
        (this.addButton = new Button("Add/Modify Point")).addActionListener(this);
        this.styleGroup = new CheckboxGroup();
        this.styleCheckbox[0] = new Checkbox("Smooth", true, this.styleGroup);
        this.styleCheckbox[1] = new Checkbox("Piecewise Linear", false, this.styleGroup);
        this.styleCheckbox[2] = new Checkbox("Step (nearest value)", false, this.styleGroup);
        this.styleCheckbox[3] = new Checkbox("Step (value from left)", false, this.styleGroup);
        this.styleCheckbox[4] = new Checkbox("Step (value from right)", false, this.styleGroup);
        for (int i = 0; i < 5; ++i) {
            this.styleCheckbox[i].addItemListener(this);
        }
        (this.canvas = new DisplayCanvas(new CoordinateRect(-1.0, 1.0, -1.0, 1.0))).add(new Axes());
        this.canvas.addMouseListener(this);
        this.canvas.addMouseMotionListener(this);
        this.function = new TableFunction();
        this.canvas.add(new Draw());
        final Font font = new Font("Serif", 1, 12);
        final Label label = new Label("Input Area");
        label.setForeground(Color.red);
        label.setFont(font);
        final Label label2 = new Label("Type of Function", 1);
        label2.setForeground(Color.red);
        label2.setFont(font);
        final Label label3 = new Label("Table of Values", 1);
        label3.setForeground(Color.red);
        label3.setFont(font);
        final Panel panel = new Panel();
        panel.setLayout(new FlowLayout(1, 10000, 3));
        final Panel panel2 = new Panel();
        panel2.setLayout(new GridLayout(6, 1, 3, 3));
        final Panel panel3 = new Panel();
        panel3.setLayout(new BorderLayout());
        final Panel panel4 = new Panel();
        panel4.setLayout(new BorderLayout(3, 3));
        panel4.add(panel, "Center");
        panel4.add(panel2, "East");
        this.setLayout(new BorderLayout(3, 3));
        this.add(panel4, "North");
        this.add(panel3, "West");
        this.add(this.canvas, "Center");
        this.setBackground(Color.darkGray);
        panel.setBackground(Color.lightGray);
        panel2.setBackground(Color.lightGray);
        panel3.setBackground(Color.lightGray);
        final Panel panel5 = new Panel();
        panel5.add(new Label("x = "));
        panel5.add(this.xInput);
        panel5.add(new Label(" y = "));
        panel5.add(this.yInput);
        final Panel panel6 = new Panel();
        panel6.setLayout(new GridLayout(1, 2, 3, 3));
        panel6.add(this.deleteButton);
        panel6.add(this.clearButton);
        panel.add(label);
        panel.add(panel5);
        panel.add(this.addButton);
        panel.add(new Label("(Press RETURN in X to move to Y)"));
        panel.add(new Label("(Press RETURN in Y to add/modify point)"));
        panel2.add(label2);
        for (int j = 0; j < 5; ++j) {
            panel2.add(this.styleCheckbox[j]);
        }
        panel3.add(this.pointList, "Center");
        panel3.add(label3, "North");
        panel3.add(panel6, "South");
    }
    
    public void startEdit(final TableFunction editFunction) {
        this.editFunction = editFunction;
        this.revertEditFunction();
    }
    
    public void revertEditFunction() {
        if (this.editFunction == null) {
            this.clearAllPoints();
            return;
        }
        this.function.copyDataFrom(this.editFunction);
        this.pointList.removeAll();
        for (int pointCount = this.function.getPointCount(), i = 0; i < pointCount; ++i) {
            this.pointList.add(this.makePointString(this.function.getX(i), this.function.getY(i)));
        }
        this.styleGroup.setSelectedCheckbox(this.styleCheckbox[this.function.getStyle()]);
        this.checkCanvas();
        if (this.onChange != null) {
            this.onChange.compute();
        }
    }
    
    public TableFunction finishEdit() {
        TableFunction tableFunction;
        if (this.editFunction == null) {
            tableFunction = this.copyOfCurrentFunction();
        }
        else {
            this.editFunction.copyDataFrom(this.function);
            tableFunction = this.editFunction;
            this.editFunction = null;
        }
        return tableFunction;
    }
    
    public void cancelEdit() {
        this.editFunction = null;
    }
    
    public TableFunction copyOfCurrentFunction() {
        final TableFunction tableFunction = new TableFunction();
        tableFunction.copyDataFrom(this.function);
        tableFunction.setName(this.function.getName());
        return tableFunction;
    }
    
    public void setOnChange(final Controller onChange) {
        this.onChange = onChange;
    }
    
    public Controller getOnChange() {
        return this.onChange;
    }
    
    private void deletePoint() {
        final int selectedIndex = this.pointList.getSelectedIndex();
        if (selectedIndex >= 0) {
            this.pointList.remove(selectedIndex);
            this.function.removePointAt(selectedIndex);
            this.checkCanvas();
            if (this.onChange != null) {
                this.onChange.compute();
            }
        }
        this.deleteButton.setEnabled(false);
    }
    
    private void clearAllPoints() {
        this.function.removeAllPoints();
        this.pointList.removeAll();
        this.deleteButton.setEnabled(false);
        if (this.onChange != null) {
            this.onChange.compute();
        }
        this.checkCanvas();
    }
    
    private void addPoint() {
        double val;
        try {
            this.xInput.checkInput();
            val = this.xInput.getVal();
        }
        catch (JCMError jcmError) {
            this.canvas.setErrorMessage(null, "The input for x does is not a legal real number.");
            this.xInput.requestFocus();
            this.xInput.selectAll();
            return;
        }
        double val2;
        try {
            this.yInput.checkInput();
            val2 = this.yInput.getVal();
        }
        catch (JCMError jcmError2) {
            this.canvas.setErrorMessage(null, "The input for y does is not a legal real number.");
            this.yInput.requestFocus();
            this.yInput.selectAll();
            return;
        }
        final String pointString = this.makePointString(val, val2);
        final int point = this.function.findPoint(val);
        if (point >= 0 && val2 == this.function.getY(point)) {
            this.xInput.requestFocus();
            this.xInput.selectAll();
            return;
        }
        final int addPoint = this.function.addPoint(val, val2);
        if (point >= 0) {
            this.pointList.replaceItem(pointString, point);
        }
        else {
            this.pointList.addItem(pointString, addPoint);
        }
        this.deleteButton.setEnabled(this.pointList.getSelectedIndex() != -1);
        this.checkCanvas();
        if (this.onChange != null) {
            this.onChange.compute();
        }
        this.xInput.requestFocus();
        this.xInput.selectAll();
    }
    
    private String makePointString(final double n, final double n2) {
        String s = NumUtils.realToString(n);
        String s2 = NumUtils.realToString(n2);
        if (s.length() < 11) {
            s = "            ".substring(0, 11 - s.length()) + s;
        }
        if (s2.length() < 11) {
            s2 = "            ".substring(0, 11 - s2.length()) + s2;
        }
        return s + " " + s2;
    }
    
    private void selectPoint() {
        final int selectedIndex = this.pointList.getSelectedIndex();
        if (selectedIndex >= 0) {
            this.xInput.setVal(this.function.getX(selectedIndex));
            this.yInput.setVal(this.function.getY(selectedIndex));
            this.yInput.requestFocus();
            this.yInput.selectAll();
        }
        this.deleteButton.setEnabled(selectedIndex >= 0);
    }
    
    private void changeStyle() {
        int style = 0;
        final Checkbox selectedCheckbox = this.styleGroup.getSelectedCheckbox();
        for (int i = 1; i < 5; ++i) {
            if (selectedCheckbox == this.styleCheckbox[i]) {
                style = i;
            }
        }
        if (this.function.getStyle() == style) {
            return;
        }
        this.function.setStyle(style);
        this.canvas.doRedraw();
        if (this.onChange != null) {
            this.onChange.compute();
        }
    }
    
    private void checkCanvas() {
        final int pointCount = this.function.getPointCount();
        double x = -1.0;
        double x2 = 1.0;
        double n = -1.0;
        double n2 = 1.0;
        if (pointCount > 0) {
            if (pointCount == 1) {
                final double x3 = this.function.getX(0);
                if (Math.abs(x3) < 10000.0) {
                    x2 = x3 + 1.0;
                    x = x3 - 1.0;
                }
                else {
                    x2 = x3 - Math.abs(x3) / 10.0;
                    x = x3 - Math.abs(x3) / 10.0;
                }
            }
            else {
                x = this.function.getX(0);
                x2 = this.function.getX(pointCount - 1);
            }
            double y;
            double n3 = y = this.function.getY(0);
            for (int i = 1; i < pointCount; ++i) {
                final double y2 = this.function.getY(i);
                if (y2 < n3) {
                    n3 = y2;
                }
                else if (y2 > y) {
                    y = y2;
                }
            }
            final double abs = Math.abs(n3 - y);
            if (abs < 1.0E-10 && Math.abs(n3) < 10000.0 && Math.abs(y) < 10000.0) {
                n2 = y + 1.0;
                n = n3 - 1.0;
            }
            else {
                n2 = y + abs * 0.15;
                n = n3 - abs * 0.15;
            }
        }
        final CoordinateRect coordinateRect = this.canvas.getCoordinateRect(0);
        final double abs2 = Math.abs(coordinateRect.getYmin() - coordinateRect.getYmax());
        final double abs3 = Math.abs(n2 - n);
        if (x2 != coordinateRect.getXmax() || x != coordinateRect.getXmin() || abs3 > 1.3 * abs2 || abs3 < 0.5 * abs2 || n2 > coordinateRect.getYmax() - 0.1 * abs2 || n < coordinateRect.getYmin() + 0.1 * abs2) {
            coordinateRect.setLimits(x, x2, n, n2);
        }
        this.canvas.doRedraw();
    }
    
    public Insets getInsets() {
        return new Insets(3, 3, 3, 3);
    }
    
    public void actionPerformed(final ActionEvent actionEvent) {
        final Object source = actionEvent.getSource();
        if (source == this.deleteButton) {
            this.deletePoint();
        }
        else if (source == this.clearButton) {
            this.clearAllPoints();
        }
        else if (source == this.xInput) {
            this.yInput.requestFocus();
            this.yInput.selectAll();
        }
        else {
            this.addPoint();
        }
    }
    
    public void itemStateChanged(final ItemEvent itemEvent) {
        if (itemEvent.getSource() == this.pointList) {
            this.selectPoint();
        }
        else {
            this.changeStyle();
        }
    }
    
    public void mousePressed(final MouseEvent mouseEvent) {
        this.dragPoint = -1;
        this.moved = false;
        final int pointCount = this.function.getPointCount();
        final CoordinateRect coordinateRect = this.canvas.getCoordinateRect(0);
        for (int i = 0; i < pointCount; ++i) {
            final int xToPixel = coordinateRect.xToPixel(this.function.getX(i));
            final int yToPixel = coordinateRect.yToPixel(this.function.getY(i));
            if (mouseEvent.getX() >= xToPixel - 3 && mouseEvent.getX() <= xToPixel + 3 && mouseEvent.getY() >= yToPixel - 3 && mouseEvent.getY() <= yToPixel + 3) {
                this.startX = mouseEvent.getX();
                final int y = mouseEvent.getY();
                this.startY = y;
                this.prevY = y;
                this.pointList.deselect(this.pointList.getSelectedIndex());
                this.pointList.select(i);
                this.selectPoint();
                this.dragPoint = i;
                return;
            }
        }
    }
    
    public void mouseReleased(final MouseEvent mouseEvent) {
        if (this.dragPoint == -1) {
            return;
        }
        if (!this.moved) {
            this.dragPoint = -1;
            return;
        }
        this.mouseDragged(mouseEvent);
        this.pointList.replaceItem(this.makePointString(this.function.getX(this.dragPoint), this.function.getY(this.dragPoint)), this.dragPoint);
        this.pointList.select(this.dragPoint);
        this.dragPoint = -1;
        if (this.onChange != null) {
            this.onChange.compute();
        }
    }
    
    public void mouseDragged(final MouseEvent mouseEvent) {
        if (this.dragPoint == -1 || this.prevY == mouseEvent.getY()) {
            return;
        }
        if (!this.moved && Math.abs(mouseEvent.getY() - this.startY) < 3) {
            return;
        }
        this.moved = true;
        int prevY = mouseEvent.getY();
        final CoordinateRect coordinateRect = this.canvas.getCoordinateRect(0);
        if (prevY < coordinateRect.getTop() + 4) {
            prevY = coordinateRect.getTop() + 4;
        }
        else if (prevY > coordinateRect.getTop() + coordinateRect.getHeight() - 4) {
            prevY = coordinateRect.getTop() + coordinateRect.getHeight() - 4;
        }
        if (Math.abs(mouseEvent.getX() - this.startX) > 72) {
            prevY = this.startY;
        }
        if (prevY == this.prevY) {
            return;
        }
        this.prevY = prevY;
        this.function.setY(this.dragPoint, coordinateRect.pixelToY(this.prevY));
        this.yInput.setVal(this.function.getY(this.dragPoint));
        this.canvas.doRedraw();
    }
    
    public void mouseClicked(final MouseEvent mouseEvent) {
    }
    
    public void mouseEntered(final MouseEvent mouseEvent) {
    }
    
    public void mouseExited(final MouseEvent mouseEvent) {
    }
    
    public void mouseMoved(final MouseEvent mouseEvent) {
    }
    
    private class Draw extends Drawable
    {
        public void draw(final Graphics graphics, final boolean b) {
            final int pointCount = TableFunctionInput.this.function.getPointCount();
            if (pointCount == 0) {
                return;
            }
            graphics.setColor(Color.magenta);
            switch (TableFunctionInput.this.function.getStyle()) {
                case 0: {
                    if (pointCount > 1) {
                        try {
                            final double x = TableFunctionInput.this.function.getX(0);
                            final double val = TableFunctionInput.this.function.getVal(x);
                            int xToPixel = super.coords.xToPixel(x);
                            int yToPixel = super.coords.yToPixel(val);
                            final int xToPixel2 = super.coords.xToPixel(TableFunctionInput.this.function.getX(pointCount - 1));
                            int i = xToPixel;
                            while (i < xToPixel2) {
                                i += 3;
                                if (i > xToPixel2) {
                                    i = xToPixel2;
                                }
                                final int yToPixel2 = super.coords.yToPixel(TableFunctionInput.this.function.getVal(super.coords.pixelToX(i)));
                                graphics.drawLine(xToPixel, yToPixel, i, yToPixel2);
                                xToPixel = i;
                                yToPixel = yToPixel2;
                            }
                        }
                        catch (Exception ex) {
                            ex.printStackTrace();
                        }
                        break;
                    }
                    break;
                }
                case 1: {
                    int xToPixel3 = super.coords.xToPixel(TableFunctionInput.this.function.getX(0));
                    int yToPixel3 = super.coords.yToPixel(TableFunctionInput.this.function.getY(0));
                    for (int j = 1; j < pointCount; ++j) {
                        final int xToPixel4 = super.coords.xToPixel(TableFunctionInput.this.function.getX(j));
                        final int yToPixel4 = super.coords.yToPixel(TableFunctionInput.this.function.getY(j));
                        graphics.drawLine(xToPixel3, yToPixel3, xToPixel4, yToPixel4);
                        xToPixel3 = xToPixel4;
                        yToPixel3 = yToPixel4;
                    }
                    break;
                }
                case 2: {
                    double x2 = TableFunctionInput.this.function.getX(0);
                    int xToPixel5 = super.coords.xToPixel(x2);
                    for (int k = 0; k < pointCount; ++k) {
                        double n;
                        if (k < pointCount - 1) {
                            final double x3 = TableFunctionInput.this.function.getX(k + 1);
                            n = (x2 + x3) / 2.0;
                            x2 = x3;
                        }
                        else {
                            n = x2;
                        }
                        final int xToPixel6 = super.coords.xToPixel(n);
                        final int yToPixel5 = super.coords.yToPixel(TableFunctionInput.this.function.getY(k));
                        graphics.drawLine(xToPixel5, yToPixel5, xToPixel6, yToPixel5);
                        xToPixel5 = xToPixel6;
                    }
                    break;
                }
                case 3: {
                    int xToPixel7 = super.coords.xToPixel(TableFunctionInput.this.function.getX(0));
                    for (int l = 1; l < pointCount; ++l) {
                        final int xToPixel8 = super.coords.xToPixel(TableFunctionInput.this.function.getX(l));
                        final int yToPixel6 = super.coords.yToPixel(TableFunctionInput.this.function.getY(l - 1));
                        graphics.drawLine(xToPixel7, yToPixel6, xToPixel8, yToPixel6);
                        xToPixel7 = xToPixel8;
                    }
                    break;
                }
                case 4: {
                    int xToPixel9 = super.coords.xToPixel(TableFunctionInput.this.function.getX(0));
                    for (int n2 = 1; n2 < pointCount; ++n2) {
                        final int xToPixel10 = super.coords.xToPixel(TableFunctionInput.this.function.getX(n2));
                        final int yToPixel7 = super.coords.yToPixel(TableFunctionInput.this.function.getY(n2));
                        graphics.drawLine(xToPixel9, yToPixel7, xToPixel10, yToPixel7);
                        xToPixel9 = xToPixel10;
                    }
                    break;
                }
            }
            for (int n3 = 0; n3 < pointCount; ++n3) {
                graphics.fillOval(super.coords.xToPixel(TableFunctionInput.this.function.getX(n3)) - 2, super.coords.yToPixel(TableFunctionInput.this.function.getY(n3)) - 2, 5, 5);
            }
        }
    }
}
