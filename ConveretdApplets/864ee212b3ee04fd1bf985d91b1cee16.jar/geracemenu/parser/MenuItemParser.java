// 
// Decompiled by Procyon v0.5.30
// 

package geracemenu.parser;

import java.util.Vector;

public class MenuItemParser
{
    protected MenuItemTokenizer tokenizer;
    
    public ItemNode parseMenu() throws StructuralInconsistencyException, TokenExpectedException {
        final ItemNode options = this.options();
        final ItemNode menudef = this.menudef();
        if (menudef == null) {
            return null;
        }
        if (options != null) {
            options.sibling = menudef;
            return options;
        }
        return menudef;
    }
    
    protected ItemNode menudef() {
        final String matchToken = this.matchToken(2);
        if (!"menudef".equals(matchToken.toLowerCase())) {
            throw new TokenExpectedException(this.tokenizer.getLineNo(), "menudef", matchToken);
        }
        this.matchToken(5);
        final ItemNode menudef_interior = this.menudef_interior();
        this.matchToken(6);
        return menudef_interior;
    }
    
    protected ItemNode options() {
        try {
            if (!"options".equals(this.matchToken(2).toLowerCase())) {
                this.tokenizer.pushTokenBack();
                return null;
            }
        }
        catch (TokenExpectedException ex) {
            this.tokenizer.pushTokenBack();
            return null;
        }
        this.matchToken(5);
        final ItemNode var_decl_list = this.var_decl_list();
        this.matchToken(6);
        return var_decl_list;
    }
    
    protected ItemNode var_decl_list() {
        final VarNode varNode = new VarNode();
        if (!this.var_decl(varNode)) {
            return null;
        }
        while (this.var_decl(varNode)) {}
        return varNode;
    }
    
    protected boolean var_decl(final VarNode varNode) throws IllegalTypeException {
        String matchToken;
        try {
            matchToken = this.matchToken(2);
        }
        catch (TokenExpectedException ex) {
            this.tokenizer.pushTokenBack();
            return false;
        }
        this.matchToken(9);
        final String nextToken = this.nextToken();
        switch (this.tokenizer.ttype()) {
            case 1: {
                varNode.put(matchToken, new StringValue(nextToken));
                break;
            }
            case 10: {
                try {
                    varNode.put(matchToken, new IntValue(Integer.valueOf(nextToken)));
                }
                catch (NumberFormatException ex2) {
                    throw new IllegalTypeException(this.tokenizer.getLineNo(), nextToken + " is no integer.");
                }
                break;
            }
            case 11: {
                varNode.put(matchToken, new BoolValue(Boolean.valueOf(nextToken)));
                break;
            }
            default: {
                throw new IllegalTypeException(this.tokenizer.getLineNo(), nextToken);
            }
        }
        this.matchToken(8);
        return true;
    }
    
    protected ItemNode menudef_interior() {
        final ItemNode itemNode = null;
        ItemNode itemNode2;
        try {
            ItemNode itemStmtSeq = this.itemStmtSeq();
            if (itemStmtSeq == null) {
                return itemNode;
            }
            itemNode2 = itemStmtSeq;
            ItemNode itemStmtSeq2;
            while ((itemStmtSeq2 = this.itemStmtSeq()) != null) {
                itemStmtSeq.sibling = itemStmtSeq2;
                itemStmtSeq = itemStmtSeq2;
            }
        }
        catch (TokenExpectedException ex) {
            throw ex;
        }
        return itemNode2;
    }
    
    protected ItemNode itemStmtSeq() throws TokenExpectedException {
        ItemNode itemNode = null;
        try {
            this.matchToken(3);
        }
        catch (TokenExpectedException ex) {
            this.tokenizer.pushTokenBack();
            return null;
        }
        final String matchToken = this.matchToken(1);
        this.matchToken(7);
        switch (ItemNode.parseType(this.matchToken(2))) {
            case 4: {
                itemNode = new ItemNode(matchToken);
                break;
            }
            case 5: {
                itemNode = new SeparatorItemNode(matchToken);
                break;
            }
            case 6: {
                itemNode = new LabelledItemNode(matchToken);
                break;
            }
            case 7: {
                itemNode = new ListItemNode(matchToken);
                break;
            }
            case 8: {
                itemNode = new EmbossItemNode(matchToken);
                break;
            }
            case 1: {
                itemNode = new MenuNode(matchToken);
                break;
            }
            case 2: {
                itemNode = new FlatMenuNode(matchToken);
                break;
            }
            case 3: {
                itemNode = new DropDownMenuNode(matchToken);
                break;
            }
        }
        final Vector vector = new Vector<ItemNode>();
        final ItemNode options = this.options();
        if (options != null) {
            vector.addElement(options);
        }
        ItemNode itemStmtSeq;
        while ((itemStmtSeq = this.itemStmtSeq()) != null) {
            vector.addElement(itemStmtSeq);
        }
        if (vector.size() > 0) {
            vector.copyInto(itemNode.children = new ItemNode[vector.size()]);
        }
        this.matchToken(4);
        return itemNode;
    }
    
    private boolean hasMoreTokens() {
        return this.tokenizer.hasMoreTokens();
    }
    
    private String nextToken() throws TokenExpectedException {
        if (!this.tokenizer.hasMoreTokens()) {
            throw new TokenExpectedException(this.tokenizer.getLineNo(), "unexpected end of input");
        }
        return this.tokenizer.nextToken();
    }
    
    private String matchToken(final int n) throws TokenExpectedException {
        if (!this.hasMoreTokens()) {
            throw new TokenExpectedException(this.tokenizer.typeToString(n));
        }
        final String nextToken = this.nextToken();
        if (this.tokenizer.ttype() != n) {
            throw new TokenExpectedException(this.tokenizer.getLineNo(), this.tokenizer.typeToString(n), nextToken);
        }
        return nextToken;
    }
    
    public MenuItemParser(final MenuItemTokenizer tokenizer) {
        this.tokenizer = tokenizer;
    }
}
