// 
// Decompiled by Procyon v0.5.30
// 

package edu.hws.jcm.draw;

import java.awt.event.ComponentEvent;
import java.awt.FontMetrics;
import java.awt.Dimension;
import java.awt.Point;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.TextField;
import edu.hws.jcm.awt.JCMError;
import edu.hws.jcm.awt.Controller;
import java.awt.Color;
import java.awt.Button;
import java.awt.Component;
import java.awt.LayoutManager;
import java.util.Vector;
import edu.hws.jcm.awt.ErrorReporter;
import edu.hws.jcm.awt.Tie;
import edu.hws.jcm.awt.VariableInput;
import java.awt.event.ActionListener;
import edu.hws.jcm.awt.Limits;
import edu.hws.jcm.awt.Tieable;
import edu.hws.jcm.awt.InputObject;
import java.awt.Panel;

public class LimitControlPanel extends Panel implements InputObject, Tieable, Limits, ActionListener
{
    public static final int SET_LIMITS = 1;
    public static final int EQUALIZE = 2;
    public static final int ZOOM_IN = 4;
    public static final int ZOOM_OUT = 8;
    public static final int SAVE = 16;
    public static final int RESTORE = 32;
    public static final int ALL_BUTTONS = 63;
    private static final String[] buttonNames;
    protected int buttons;
    protected boolean twoColumn;
    protected VariableInput xmin;
    protected VariableInput xmax;
    protected VariableInput ymin;
    protected VariableInput ymax;
    protected long serialNumber;
    protected Tie syncWith;
    protected ErrorReporter errorReporter;
    protected CoordinateRect coords;
    protected Vector items;
    
    public LimitControlPanel() {
        this("xmin", "xmax", "ymin", "ymax", 1, false);
    }
    
    public LimitControlPanel(final int n, final boolean b) {
        this("xmin", "xmax", "ymin", "ymax", n, b);
    }
    
    public LimitControlPanel(final String s, final String s2, final String s3, final String s4, final int n, final boolean twoColumn) {
        this.serialNumber = -1L;
        this.items = new Vector();
        this.setLayout(null);
        this.enableEvents(1L);
        this.xmin = new VariableInput(s, "-5");
        this.xmax = new VariableInput(s2, "5");
        this.addRange(this.xmin, this.xmax);
        this.ymin = new VariableInput(s3, "-5");
        this.ymax = new VariableInput(s4, "5");
        this.addRange(this.ymin, this.ymax);
        this.addButtons(n);
        this.twoColumn = twoColumn;
    }
    
    public void addCoords(final CoordinateRect coords) {
        if (this.syncWith == null) {
            this.syncWith = new Tie(this);
        }
        this.syncWith.add(coords);
        coords.setSyncWith(this.syncWith);
        if (this.coords == null) {
            this.coords = coords;
        }
    }
    
    public void addCoords(final DisplayCanvas displayCanvas) {
        this.addCoords(displayCanvas.getCoordinateRect());
    }
    
    public void setErrorReporter(final ErrorReporter errorReporter) {
        this.errorReporter = errorReporter;
    }
    
    public ErrorReporter getErrorReporter() {
        return this.errorReporter;
    }
    
    public void setUseTwoColumnsIfPossible(final boolean twoColumn) {
        this.twoColumn = twoColumn;
    }
    
    public boolean getUseTwoColumnsIfPossible() {
        return this.twoColumn;
    }
    
    public void addComponent(final Component component) {
        super.add(component);
        this.items.addElement(component);
    }
    
    public void addComponentPair(final Component component, final Component component2) {
        super.add(component);
        super.add(component2);
        this.items.addElement(new Component[] { component, component2 });
    }
    
    public void addRange(final VariableInput variableInput, final VariableInput variableInput2) {
        super.add(variableInput);
        super.add(variableInput2);
        variableInput.addActionListener(this);
        variableInput2.addActionListener(this);
        this.items.addElement(new Component[] { variableInput, variableInput2, null });
    }
    
    public void addButtons(final int n) {
        if ((n & 0x1) != 0x0 && (this.buttons & 0x1) == 0x0 && (n & 0x2) != 0x0 && (this.buttons & 0x2) == 0x0) {
            this.addComponentPair(this.makeButton(0), this.makeButton(1));
        }
        else if ((n & 0x1) != 0x0 && (this.buttons & 0x1) == 0x0) {
            this.addComponent(this.makeButton(0));
        }
        else if ((n & 0x2) != 0x0 && (this.buttons & 0x2) == 0x0) {
            this.addComponent(this.makeButton(1));
        }
        if ((n & 0x4) != 0x0 && (this.buttons & 0x4) == 0x0 && (n & 0x8) != 0x0 && (this.buttons & 0x8) == 0x0) {
            this.addComponentPair(this.makeButton(2), this.makeButton(3));
        }
        else if ((n & 0x4) != 0x0 && (this.buttons & 0x4) == 0x0) {
            this.addComponent(this.makeButton(2));
        }
        else if ((n & 0x8) != 0x0 && (this.buttons & 0x8) == 0x0) {
            this.addComponent(this.makeButton(3));
        }
        if ((n & 0x10) != 0x0 && (this.buttons & 0x10) == 0x0 && (n & 0x20) != 0x0 && (this.buttons & 0x20) == 0x0) {
            this.addComponentPair(this.makeButton(4), this.makeButton(5));
        }
        else if ((n & 0x10) != 0x0 && (this.buttons & 0x10) == 0x0) {
            this.addComponent(this.makeButton(4));
        }
        else if ((n & 0x20) != 0x0 && (this.buttons & 0x20) == 0x0) {
            this.addComponent(this.makeButton(5));
        }
        this.buttons |= n;
    }
    
    public Button getButton(final int n) {
        int n2;
        if (n == 1) {
            n2 = 0;
        }
        else if (n == 2) {
            n2 = 1;
        }
        else if (n == 4) {
            n2 = 2;
        }
        else if (n == 8) {
            n2 = 3;
        }
        else if (n == 16) {
            n2 = 4;
        }
        else {
            if (n != 32) {
                throw new IllegalArgumentException("Unknown button code passed to getButton().");
            }
            n2 = 5;
        }
        final Button button = this.makeButton(n2);
        button.setActionCommand(LimitControlPanel.buttonNames[n2]);
        return button;
    }
    
    private Button makeButton(final int n) {
        final Button button = new Button(LimitControlPanel.buttonNames[n]);
        button.setBackground(Color.lightGray);
        button.addActionListener(this);
        return button;
    }
    
    public Component add(final Component component) {
        this.addComponent(component);
        return component;
    }
    
    public void notifyControllerOnChange(final Controller controller) {
    }
    
    public void checkInput() {
        try {
            boolean b = false;
            for (int i = 0; i < this.items.size(); ++i) {
                final Object element = this.items.elementAt(i);
                if (element instanceof Component[] && ((Component[])element).length == 3) {
                    final VariableInput variableInput = (VariableInput)((Component[])element)[0];
                    final VariableInput variableInput2 = (VariableInput)((Component[])element)[1];
                    final double val = variableInput.getVal();
                    variableInput.checkInput();
                    final double val2 = variableInput.getVal();
                    if (val != val2) {
                        b = true;
                    }
                    final double val3 = variableInput2.getVal();
                    variableInput2.checkInput();
                    final double val4 = variableInput2.getVal();
                    if (val3 != val4) {
                        b = true;
                    }
                    if (val2 >= val4) {
                        throw new JCMError("The value of " + variableInput2.getName() + " must be greater than the value of " + variableInput.getName() + ".", variableInput2);
                    }
                }
            }
            if (this.errorReporter != null) {
                this.errorReporter.clearErrorMessage();
            }
            if (b) {
                ++this.serialNumber;
                if (this.syncWith != null) {
                    this.syncWith.check();
                }
            }
        }
        catch (JCMError jcmError) {
            if (this.errorReporter != null) {
                this.errorReporter.setErrorMessage(null, jcmError.getMessage());
            }
            else {
                System.out.println("***** Error:  " + jcmError.getMessage());
            }
            if (jcmError.object instanceof TextField) {
                ((TextField)jcmError.object).selectAll();
                ((TextField)jcmError.object).requestFocus();
            }
        }
        catch (RuntimeException ex) {
            if (this.errorReporter != null) {
                this.errorReporter.setErrorMessage(null, ex.toString());
            }
            ex.printStackTrace();
        }
    }
    
    public long getSerialNumber() {
        return this.serialNumber;
    }
    
    public void sync(final Tie tie, final Tieable tieable) {
        if (tieable == this) {
            return;
        }
        if (!(tieable instanceof Tieable)) {
            throw new IllegalArgumentException("Internal Error:  A LimitControlPanel can only sync with a Limits object.");
        }
        this.setLimits(((Limits)tieable).getLimits());
        this.serialNumber = tieable.getSerialNumber();
    }
    
    public double[] getLimits() {
        return new double[] { this.xmin.getVal(), this.xmax.getVal(), this.ymin.getVal(), this.ymax.getVal() };
    }
    
    public void setLimits(final double[] array) {
        if (array == null || array.length < 4) {
            throw new IllegalArgumentException("Internal Error:  Not enough values supplied for setLimits.");
        }
        for (int i = 0; i < 4; ++i) {
            if (Double.isNaN(array[i]) || Double.isInfinite(array[i])) {
                return;
            }
        }
        boolean b = false;
        if (array[0] != this.xmin.getVal()) {
            b = true;
            this.xmin.setVal(array[0]);
        }
        if (array[1] != this.xmax.getVal()) {
            b = true;
            this.xmax.setVal(array[1]);
        }
        if (array[2] != this.ymin.getVal()) {
            b = true;
            this.ymin.setVal(array[2]);
        }
        if (array[3] != this.ymax.getVal()) {
            b = true;
            this.ymax.setVal(array[3]);
        }
        if (b) {
            ++this.serialNumber;
        }
    }
    
    public void actionPerformed(final ActionEvent actionEvent) {
        final String actionCommand = actionEvent.getActionCommand();
        if (actionEvent.getSource() instanceof VariableInput || actionCommand.equals(LimitControlPanel.buttonNames[0])) {
            this.checkInput();
        }
        else {
            if (this.coords == null) {
                return;
            }
            if (actionCommand.equals(LimitControlPanel.buttonNames[1])) {
                this.coords.equalizeAxes();
            }
            else if (actionCommand.equals(LimitControlPanel.buttonNames[2])) {
                this.coords.zoomIn();
            }
            else if (actionCommand.equals(LimitControlPanel.buttonNames[3])) {
                this.coords.zoomOut();
            }
            else if (actionCommand.equals(LimitControlPanel.buttonNames[4])) {
                this.coords.setRestoreBuffer();
            }
            else if (actionCommand.equals(LimitControlPanel.buttonNames[5])) {
                this.coords.restore();
            }
        }
    }
    
    public void paint(final Graphics graphics) {
        for (int componentCount = this.getComponentCount(), i = 0; i < componentCount; ++i) {
            final Component component = this.getComponent(i);
            if (component instanceof VariableInput) {
                final Point location = component.getLocation();
                graphics.drawString(component.getName(), location.x + 4, location.y - 4);
            }
        }
    }
    
    public Dimension getPreferredSize() {
        int n = 0;
        int max = 5;
        final FontMetrics fontMetrics = this.getFontMetrics(this.getFont());
        final int n2 = (fontMetrics == null) ? 12 : (4 + fontMetrics.getAscent());
        for (int i = 0; i < this.items.size(); ++i) {
            final Object element = this.items.elementAt(i);
            if (element instanceof Component) {
                final Component component = (Component)element;
                final Dimension preferredSize = component.getPreferredSize();
                max += preferredSize.height + 5;
                if (component instanceof VariableInput) {
                    max += n2;
                }
                if (preferredSize.width > n) {
                    n = preferredSize.width;
                }
            }
            else {
                final Component[] array = (Component[])element;
                final Dimension preferredSize2 = array[0].getPreferredSize();
                if (array[0] instanceof VariableInput) {
                    final Dimension dimension = preferredSize2;
                    dimension.height += n2;
                }
                final Dimension preferredSize3 = array[1].getPreferredSize();
                if (array[1] instanceof VariableInput) {
                    final Dimension dimension2 = preferredSize3;
                    dimension2.height += n2;
                }
                if (this.twoColumn) {
                    n = Math.max(n, preferredSize2.width + preferredSize3.width);
                    max = max + Math.max(preferredSize2.height, preferredSize3.height) + 5;
                }
                else {
                    final int n3 = max + (preferredSize2.height + preferredSize3.height + 10);
                    n = Math.max(n, preferredSize2.width);
                    max = Math.max(n3, preferredSize3.height);
                }
            }
        }
        return new Dimension(n + (this.twoColumn ? 15 : 10), max);
    }
    
    public void processComponentEvent(final ComponentEvent componentEvent) {
        if (componentEvent.getID() == 101) {
            final Dimension size = this.getSize();
            Dimension dimension = this.getPreferredSize();
            int twoColumn = this.twoColumn ? 1 : 0;
            if (twoColumn != 0 && size.width < dimension.width - 20) {
                twoColumn = 0;
                this.twoColumn = false;
                dimension = this.getPreferredSize();
                this.twoColumn = true;
            }
            int size2 = this.items.size();
            if (twoColumn == 0) {
                for (int i = 0; i < this.items.size(); ++i) {
                    if (this.items.elementAt(i) instanceof Component[]) {
                        ++size2;
                    }
                }
            }
            double n;
            double n2;
            if (size.height >= dimension.height) {
                n = 1.0;
                n2 = 5 + (size.height - dimension.height) / (size2 + 2);
                if (n2 > 15.0) {
                    n2 = 15.0;
                }
            }
            else if (size.height >= dimension.height - 4 * (size2 + 2)) {
                n = 1.0;
                n2 = (size.height - (dimension.height - 5 * (size2 + 2))) / (size2 + 2);
            }
            else {
                n = size.height / (dimension.height - 4 * (size2 + 2));
                n2 = 1.0;
            }
            int n3 = (size.width - (dimension.width - ((twoColumn != 0) ? 15 : 10))) / ((twoColumn != 0) ? 3 : 2);
            if (n3 < 1) {
                n3 = 1;
            }
            else if (n3 > 10) {
                n3 = 10;
            }
            double n4 = n2;
            final int n5 = 4 + this.getFontMetrics(this.getFont()).getAscent();
            for (int j = 0; j < this.items.size(); ++j) {
                final Object element = this.items.elementAt(j);
                if (element instanceof Component) {
                    final Component component = (Component)element;
                    final Dimension preferredSize = component.getPreferredSize();
                    if (component instanceof VariableInput) {
                        n4 += n5 * n;
                    }
                    if (preferredSize.width + 2 * n3 < size.width - 10) {
                        component.setBounds((size.width - preferredSize.width) / 2, (int)n4, preferredSize.width, (int)(preferredSize.height * n));
                    }
                    else {
                        component.setBounds(n3, (int)n4, preferredSize.width - 2 * n3, (int)(preferredSize.height * n));
                    }
                    n4 += n * preferredSize.height + n2;
                }
                else {
                    final Component[] array = (Component[])element;
                    final Dimension preferredSize2 = array[0].getPreferredSize();
                    final Dimension preferredSize3 = array[1].getPreferredSize();
                    if (twoColumn != 0) {
                        if (array[0] instanceof VariableInput || array[1] instanceof VariableInput) {
                            n4 += n5 * n;
                        }
                        final int n6 = (int)(n * Math.max(preferredSize2.height, preferredSize3.height));
                        array[0].setBounds(n3, (int)n4, (size.width - 3 * n3) / 2, n6);
                        array[1].setBounds(n3 * 2 + (size.width - 3 * n3) / 2, (int)n4, (size.width - 3 * n3) / 2, n6);
                        n4 += n * Math.max(preferredSize2.height, preferredSize3.height) + n2;
                    }
                    else {
                        if (array[0] instanceof VariableInput) {
                            n4 += n5 * n;
                        }
                        array[0].setBounds(n3, (int)n4, size.width - 2 * n3, (int)(preferredSize2.height * n));
                        double n7 = n4 + (preferredSize2.height * n + n2);
                        if (array[1] instanceof VariableInput) {
                            n7 += n5 * n;
                        }
                        array[1].setBounds(n3, (int)n7, size.width - 2 * n3, (int)(preferredSize3.height * n));
                        n4 = n7 + (preferredSize3.height * n + n2);
                    }
                }
            }
        }
        super.processComponentEvent(componentEvent);
    }
    
    static {
        buttonNames = new String[] { "Set Limits", "Equalize Axes", "Zoom In", "Zoom Out", "Save Limits", "Restore Limits" };
    }
}
