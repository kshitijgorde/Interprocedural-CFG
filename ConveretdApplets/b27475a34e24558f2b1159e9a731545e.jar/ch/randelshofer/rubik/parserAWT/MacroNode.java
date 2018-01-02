// 
// Decompiled by Procyon v0.5.30
// 

package ch.randelshofer.rubik.parserAWT;

import java.io.IOException;
import java.util.Enumeration;
import ch.randelshofer.gui.tree.DefaultMutableTreeNode;
import java.io.Reader;
import java.io.StringReader;
import ch.randelshofer.io.ParseException;

public class MacroNode extends ScriptNode
{
    private String identifier;
    private String script;
    private ScriptParser parser;
    
    public void transform(final int n) {
        this.identifier = null;
        super.transform(n);
    }
    
    public String getIdentifier() {
        return this.identifier;
    }
    
    public int getSymbol() {
        return 114;
    }
    
    public MacroNode(final String identifier, final String script, final int n, final int n2) {
        super(n, n2);
        this.identifier = identifier;
        this.script = script;
        this.setAllowsChildren(true);
    }
    
    public void expand(final ScriptParser scriptParser) throws IOException {
        if (this.getChildCount() > 0) {
            return;
        }
        for (DefaultMutableTreeNode defaultMutableTreeNode = this.getParent(); defaultMutableTreeNode != null; defaultMutableTreeNode = defaultMutableTreeNode.getParent()) {
            if (defaultMutableTreeNode instanceof MacroNode && ((MacroNode)defaultMutableTreeNode).identifier.equals(this.identifier)) {
                throw new ParseException("Macro: Illegal Recursion", this.getStartPosition(), this.getEndPosition());
            }
        }
        final int startPosition = this.getStartPosition();
        final int endPosition = this.getEndPosition();
        scriptParser.parse(new StringReader(this.script), this);
        final Enumeration breadthFirstEnumeration = this.breadthFirstEnumeration();
        while (breadthFirstEnumeration.hasMoreElements()) {
            final ScriptNode scriptNode = breadthFirstEnumeration.nextElement();
            scriptNode.setStartPosition(startPosition);
            scriptNode.setEndPosition(endPosition);
        }
    }
}
