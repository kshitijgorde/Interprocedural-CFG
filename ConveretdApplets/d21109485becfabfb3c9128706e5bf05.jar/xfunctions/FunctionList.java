// 
// Decompiled by Procyon v0.5.30
// 

package xfunctions;

import java.awt.Dimension;
import xfunctions.functions.MathSymbol;
import xfunctions.functions.StandardFunction;
import java.awt.Color;
import java.util.Vector;
import java.awt.List;

class FunctionList extends List
{
    Vector functions;
    
    FunctionList() {
        this.functions = new Vector(40);
        this.setBackground(Color.white);
        final int standardFunctionCount = StandardFunction.standardFunctionCount();
        for (int i = 1; i <= standardFunctionCount; ++i) {
            this.insert(StandardFunction.function(i));
        }
        for (int j = 0; j < standardFunctionCount; ++j) {
            this.addItem(((MathSymbol)this.functions.elementAt(j)).getName());
        }
        this.select(0);
    }
    
    void addFunction(final MathSymbol mathSymbol) {
        this.addItem(mathSymbol.getName(), this.insert(mathSymbol));
    }
    
    private int insert(final MathSymbol mathSymbol) {
        int size;
        String name;
        int n;
        for (size = this.functions.size(), name = mathSymbol.getName(), n = 0; n < size && name.compareTo(((MathSymbol)this.functions.elementAt(n)).getName()) > 0; ++n) {}
        if (n < size) {
            this.functions.insertElementAt(mathSymbol, n);
        }
        else {
            this.functions.addElement(mathSymbol);
        }
        return n;
    }
    
    public void selectByName(final String s) {
        final int size = this.functions.size();
        final int selectedIndex = this.getSelectedIndex();
        for (int i = 0; i < size; ++i) {
            if (s.equals(((MathSymbol)this.functions.elementAt(i)).getName())) {
                if (selectedIndex >= 0) {
                    this.deselect(selectedIndex);
                }
                this.select(i);
                return;
            }
        }
    }
    
    MathSymbol getSelectedFunction() {
        final int selectedIndex = this.getSelectedIndex();
        if (selectedIndex == -1) {
            return null;
        }
        return (MathSymbol)this.functions.elementAt(selectedIndex);
    }
    
    public Dimension preferredSize() {
        final Dimension preferredSize = super.preferredSize();
        preferredSize.width = 100;
        return preferredSize;
    }
}
