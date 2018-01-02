// 
// Decompiled by Procyon v0.5.30
// 

package xfunctions.graphs;

import java.awt.Graphics;
import java.awt.Dimension;
import java.awt.Component;
import xfunctions.functions.Utilities;
import java.awt.TextComponent;
import java.awt.TextField;
import java.awt.LayoutManager;
import java.util.Vector;
import java.awt.Panel;

public class NumberInputPanel extends Panel
{
    private Vector items;
    private boolean twoColumn;
    
    public NumberInputPanel() {
        this(true);
    }
    
    public NumberInputPanel(final boolean twoColumn) {
        this.items = new Vector();
        this.twoColumn = twoColumn;
        this.items = new Vector();
        this.setLayout(null);
    }
    
    public boolean hasTextField(final TextField textField) {
        for (int i = 0; i < this.items.size(); ++i) {
            if (((InputPanelItem)this.items.elementAt(i)).inputBox == textField) {
                return true;
            }
        }
        return false;
    }
    
    public double[] getValues(final DisplayCanvas displayCanvas) {
        return this.getValues(displayCanvas, 0, this.items.size() - 1);
    }
    
    public double[] getValues(final DisplayCanvas displayCanvas, final int n, final int n2) {
        if (n > n2 || n < 0 || n2 >= this.items.size()) {
            return null;
        }
        final double[] array = new double[n2 - n + 1];
        TextComponent inputBox = null;
        String errorMessage = "";
        for (int i = n; i <= n2; ++i) {
            final InputPanelItem inputPanelItem = this.items.elementAt(i);
            inputBox = inputPanelItem.inputBox;
            final String text = inputBox.getText();
            if (text == null || text.trim().length() == 0) {
                errorMessage = "No value was specified in the input box \"" + inputPanelItem.label + "\".";
                break;
            }
            double doubleValue;
            try {
                doubleValue = new Double(text);
            }
            catch (NumberFormatException ex) {
                doubleValue = Double.NaN;
            }
            if (Double.isNaN(doubleValue) || Double.isInfinite(doubleValue)) {
                errorMessage = "The value in the input box \"" + inputPanelItem.label + "\" is not a legal number.";
                break;
            }
            if (inputPanelItem.type == 1 || inputPanelItem.type == 3 || inputPanelItem.type == 5) {
                if (doubleValue < -2.147483648E9 || doubleValue > 2.147483647E9 || Math.abs(doubleValue - Math.rint(doubleValue)) > 1.0E-10) {
                    errorMessage = "The value in the input box \"" + inputPanelItem.label + "\" must be an integer, in the range from " + (int)inputPanelItem.min + " to " + (int)inputPanelItem.max + ".";
                    break;
                }
                if (doubleValue < inputPanelItem.min || doubleValue > inputPanelItem.max) {
                    errorMessage = "The integer in the input box \"" + inputPanelItem.label + "\" must be in the range from " + (int)inputPanelItem.min + " to " + (int)inputPanelItem.max + ".";
                    break;
                }
            }
            else if (doubleValue < inputPanelItem.min || doubleValue > inputPanelItem.max) {
                errorMessage = "The number in the input box \"" + inputPanelItem.label + "\" must be in the range from " + inputPanelItem.min + " to " + inputPanelItem.max + ".";
                break;
            }
            if ((inputPanelItem.type == 3 || inputPanelItem.type == 4) && inputPanelItem.firstMustBeSmaller && i > n && doubleValue <= array[i - n - 1]) {
                errorMessage = "The number in the input box \"" + inputPanelItem.label + "\" must be larger than the number in \"" + this.items.elementAt(i - 1).label + "\".";
                break;
            }
            array[i - n] = doubleValue;
            inputBox = null;
        }
        if (inputBox == null) {
            return array;
        }
        if (displayCanvas != null) {
            displayCanvas.setErrorMessage(errorMessage);
        }
        inputBox.selectAll();
        inputBox.requestFocus();
        return null;
    }
    
    public double[] getValues(final DisplayCanvas displayCanvas, final int n) {
        return this.getValues(displayCanvas, 0, n - 1);
    }
    
    public void setValue(final int n, final double n2) {
        if (n < 0 || n >= this.items.size()) {
            return;
        }
        final InputPanelItem inputPanelItem = this.items.elementAt(n);
        if (Double.isNaN(n2) || Double.isInfinite(n2)) {
            inputPanelItem.inputBox.setText("");
        }
        else {
            inputPanelItem.inputBox.setText(Utilities.realToString(n2));
        }
    }
    
    public void setValues(final double[] array) {
        if (array == null) {
            return;
        }
        for (int i = 0; i < array.length; ++i) {
            this.setValue(i, array[i]);
        }
    }
    
    public void addRealRange(final String s) {
        this.addRealRange(s, -1.0E100, 1.0E100);
    }
    
    public void addRealRange(final String s, final double n, final double n2) {
        final InputPanelItem inputPanelItem = new InputPanelItem(2, String.valueOf(s) + "min", n, n2);
        this.items.addElement(inputPanelItem);
        this.add(inputPanelItem.inputBox);
        final InputPanelItem inputPanelItem2 = new InputPanelItem(4, String.valueOf(s) + "max", n, n2);
        this.items.addElement(inputPanelItem2);
        this.add(inputPanelItem2.inputBox);
    }
    
    public void addIntegerRange(final String s) {
        this.addIntegerRange(s, Integer.MIN_VALUE, Integer.MAX_VALUE);
    }
    
    public void addIntegerRange(final String s, final int n, final int n2) {
        final InputPanelItem inputPanelItem = new InputPanelItem(1, String.valueOf(s) + "min", n, n2);
        this.items.addElement(inputPanelItem);
        this.add(inputPanelItem.inputBox);
        final InputPanelItem inputPanelItem2 = new InputPanelItem(3, String.valueOf(s) + "max", n, n2);
        this.items.addElement(inputPanelItem2);
        this.add(inputPanelItem2.inputBox);
    }
    
    public void addSingleInteger(final String s) {
        this.addSingleInteger(s, Integer.MIN_VALUE, Integer.MAX_VALUE);
    }
    
    public void addSingleInteger(final String s, final int n, final int n2) {
        final InputPanelItem inputPanelItem = new InputPanelItem(5, s, n, n2);
        this.items.addElement(inputPanelItem);
        this.add(inputPanelItem.inputBox);
    }
    
    public void addSingleReal(final String s) {
        this.addSingleReal(s, -1.0E100, 1.0E100);
    }
    
    public void addSingleReal(final String s, final double n, final double n2) {
        final InputPanelItem inputPanelItem = new InputPanelItem(6, s, n, n2);
        this.items.addElement(inputPanelItem);
        this.add(inputPanelItem.inputBox);
    }
    
    public void addStartToFinishInt(final String s) {
        this.addStartToFinishInt(s, Integer.MIN_VALUE, Integer.MAX_VALUE);
    }
    
    public void addStartToFinishInt(final String s, final int n, final int n2) {
        final InputPanelItem inputPanelItem = new InputPanelItem(1, String.valueOf(s) + " at start", n, n2);
        inputPanelItem.firstMustBeSmaller = false;
        this.items.addElement(inputPanelItem);
        this.add(inputPanelItem.inputBox);
        final InputPanelItem inputPanelItem2 = new InputPanelItem(3, String.valueOf(s) + " at end", n, n2);
        inputPanelItem2.firstMustBeSmaller = false;
        this.items.addElement(inputPanelItem2);
        this.add(inputPanelItem2.inputBox);
    }
    
    public void addStartToFinishReal(final String s) {
        this.addStartToFinishReal(s, -1.0E100, 1.0E100);
    }
    
    public void addStartToFinishReal(final String s, final double n, final double n2) {
        final InputPanelItem inputPanelItem = new InputPanelItem(2, String.valueOf(s) + " at start", n, n2);
        inputPanelItem.firstMustBeSmaller = false;
        this.items.addElement(inputPanelItem);
        this.add(inputPanelItem.inputBox);
        final InputPanelItem inputPanelItem2 = new InputPanelItem(4, String.valueOf(s) + " at end", n, n2);
        inputPanelItem2.firstMustBeSmaller = false;
        this.items.addElement(inputPanelItem2);
        this.add(inputPanelItem2.inputBox);
    }
    
    public void addTwoReals(final String s, final String s2) {
        final InputPanelItem inputPanelItem = new InputPanelItem(2, s, -1.0E100, 1.0E100);
        inputPanelItem.firstMustBeSmaller = false;
        this.items.addElement(inputPanelItem);
        this.add(inputPanelItem.inputBox);
        final InputPanelItem inputPanelItem2 = new InputPanelItem(4, s2, -1.0E100, 1.0E100);
        inputPanelItem2.firstMustBeSmaller = false;
        this.items.addElement(inputPanelItem2);
        this.add(inputPanelItem2.inputBox);
    }
    
    public void reshape(final int n, final int n2, final int n3, final int n4) {
        super.reshape(n, n2, n3, n4);
        if (this.items.size() == 0) {
            return;
        }
        final Dimension preferredSize = this.items.elementAt(0).inputBox.preferredSize();
        final int n5 = 2 * preferredSize.height - 4;
        final int width = preferredSize.width;
        boolean twoColumn = false;
        for (int i = 0; i < this.items.size(); ++i) {
            if (((InputPanelItem)this.items.elementAt(i)).type <= 4) {
                twoColumn = this.twoColumn;
                break;
            }
        }
        int n6 = this.countLines();
        if (n3 < 0.6 * width) {
            twoColumn = false;
            n6 = this.items.size();
        }
        final int min = Math.min((n4 - 3) / n6, preferredSize.height * 3);
        int height = min / 2;
        if (height > preferredSize.height) {
            height = preferredSize.height;
        }
        int n7 = 5 + height;
        int n8;
        if (twoColumn) {
            n8 = (n3 - 15) / 2;
        }
        else {
            n8 = n3 - 10;
        }
        final int x = 5;
        for (int j = 0; j < this.items.size(); ++j) {
            final InputPanelItem inputPanelItem = this.items.elementAt(j);
            if ((inputPanelItem.type == 3 || inputPanelItem.type == 4) && twoColumn) {
                inputPanelItem.inputBox.reshape(x + n8 + 5, n7, n8, height);
                inputPanelItem.x = x + n8 + 5;
            }
            else {
                inputPanelItem.inputBox.reshape(x, n7, n8, height);
                inputPanelItem.x = x;
            }
            inputPanelItem.y = n7 - 5;
            if (!twoColumn || inputPanelItem.type >= 3) {
                n7 += min;
            }
        }
    }
    
    public void paint(final Graphics graphics) {
        for (int i = 0; i < this.items.size(); ++i) {
            final InputPanelItem inputPanelItem = this.items.elementAt(i);
            graphics.drawString(inputPanelItem.label, inputPanelItem.x, inputPanelItem.y);
        }
    }
    
    public Dimension preferredSize() {
        if (this.items.size() == 0) {
            return new Dimension(10, 10);
        }
        final int countLines = this.countLines();
        final boolean b = countLines < this.items.size();
        final Dimension preferredSize = this.items.elementAt(0).inputBox.preferredSize();
        if (b) {
            return new Dimension(preferredSize.width * 2 + 15, 2 * (preferredSize.height + 5) * countLines + 5);
        }
        return new Dimension(preferredSize.width + 10, 2 * (preferredSize.height + 5) * countLines + 5);
    }
    
    public Dimension minimumSize() {
        return this.preferredSize();
    }
    
    private int countLines() {
        if (this.twoColumn) {
            int n = 0;
            for (int i = 0; i < this.items.size(); ++i) {
                final InputPanelItem inputPanelItem = this.items.elementAt(i);
                if (inputPanelItem.type != 3 && inputPanelItem.type != 4) {
                    ++n;
                }
            }
            return n;
        }
        return this.items.size();
    }
}
