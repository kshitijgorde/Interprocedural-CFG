// 
// Decompiled by Procyon v0.5.30
// 

package xfunctions.functions;

import java.util.Hashtable;

public class SymbolTable
{
    private SymbolTable parent;
    private Hashtable symbols;
    private static SymbolTable predefined;
    
    SymbolTable() {
        this.parent = predefinedSymbols();
        this.symbols = new Hashtable();
    }
    
    SymbolTable(final SymbolTable parent) {
        this.parent = parent;
        this.symbols = new Hashtable();
    }
    
    SymbolTable getParent() {
        return this.parent;
    }
    
    public MathSymbol get(final String s) {
        if (s == null) {
            return null;
        }
        final MathSymbol value = this.symbols.get(s.toLowerCase());
        if (value != null) {
            return value;
        }
        if (this.parent != null) {
            return this.parent.get(s);
        }
        return null;
    }
    
    public void add(final MathSymbol mathSymbol) {
        if (mathSymbol == null) {
            return;
        }
        if (mathSymbol.getName() == null) {
            throw new IllegalArgumentException("Null name specified for symbol.");
        }
        this.symbols.put(mathSymbol.getName().toLowerCase(), mathSymbol);
    }
    
    public void remove(final String s) {
        if (s != null) {
            this.symbols.remove(s.toLowerCase());
        }
    }
    
    static void addToPredefined(final MathSymbol mathSymbol) {
        if (SymbolTable.predefined == null) {
            makePredefined();
        }
        SymbolTable.predefined.add(mathSymbol);
    }
    
    static SymbolTable predefinedSymbols() {
        if (SymbolTable.predefined == null) {
            makePredefined();
        }
        return SymbolTable.predefined;
    }
    
    private static void makePredefined() {
        (SymbolTable.predefined = new SymbolTable(null)).add(new NamedConstant("pi", 3.141592653589793));
        SymbolTable.predefined.add(new NamedConstant("e", 2.718281828459045));
        for (int i = 1; i <= StandardFunction.standardFunctionCount(); ++i) {
            SymbolTable.predefined.add(StandardFunction.function(i));
        }
    }
}
