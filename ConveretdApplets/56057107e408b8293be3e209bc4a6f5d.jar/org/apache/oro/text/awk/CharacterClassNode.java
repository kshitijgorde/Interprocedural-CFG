// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.oro.text.awk;

import java.util.BitSet;

class CharacterClassNode extends LeafNode
{
    BitSet _characterSet;
    
    CharacterClassNode(final int n) {
        super(n);
        this._characterSet = new BitSet(257);
    }
    
    void _addToken(final int n) {
        this._characterSet.set(n);
    }
    
    void _addTokenRange(int i, final int n) {
        while (i <= n) {
            this._characterSet.set(i++);
        }
    }
    
    boolean _matches(final char c) {
        return this._characterSet.get(c);
    }
    
    SyntaxNode _clone(final int[] array) {
        final CharacterClassNode characterClassNode = new CharacterClassNode(array[0]++);
        characterClassNode._characterSet = (BitSet)this._characterSet.clone();
        return characterClassNode;
    }
}
