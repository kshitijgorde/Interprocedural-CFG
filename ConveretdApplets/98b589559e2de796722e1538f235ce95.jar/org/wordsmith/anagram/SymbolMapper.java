// 
// Decompiled by Procyon v0.5.30
// 

package org.wordsmith.anagram;

import java.util.Map;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedList;

public class SymbolMapper
{
    private final String[] mySourceText;
    private final String[] myTargetText;
    private Boolean myCachedIsValidAnagram;
    private final LinkedList myMappings;
    
    public SymbolMapper(final String s, final String s2) {
        this(new String[] { s }, new String[] { s2 });
    }
    
    public SymbolMapper(final String[] mySourceText, final String[] myTargetText) {
        this.myMappings = new LinkedList();
        this.mySourceText = mySourceText;
        this.myTargetText = myTargetText;
        this.registerMappings();
    }
    
    public String[] getSourceText() {
        return this.mySourceText;
    }
    
    public String[] getTargetText() {
        return this.myTargetText;
    }
    
    public Iterator allMappings() {
        return this.myMappings.iterator();
    }
    
    public boolean isValidAnagram() {
        if (this.myCachedIsValidAnagram == null) {
            boolean b = true;
            SymbolMapping symbolMapping;
            for (Iterator allMappings = this.allMappings(); b && allMappings.hasNext(); b = !Character.isLetterOrDigit(symbolMapping.getSubjectChar())) {
                symbolMapping = allMappings.next();
                if (symbolMapping.isDeleted() || symbolMapping.isNew()) {}
            }
            this.myCachedIsValidAnagram = b;
        }
        return this.myCachedIsValidAnagram;
    }
    
    private void registerMappings() {
        final Symbol2PositionMap symbol2PositionMap = new Symbol2PositionMap(this.mySourceText);
        for (int i = 0; i < this.myTargetText.length; ++i) {
            final String s = this.myTargetText[i];
            for (int j = 0; j < s.length(); ++j) {
                final char char1 = s.charAt(j);
                if (char1 != ' ') {
                    this.myMappings.add(new SymbolMapping(symbol2PositionMap.removeSymbolPosition(char1), new SymbolPosition(i, j, char1)));
                }
            }
        }
        final Iterator positions = symbol2PositionMap.positions();
        while (positions.hasNext()) {
            this.myMappings.add(new SymbolMapping(positions.next(), null));
        }
        Collections.shuffle(this.myMappings);
    }
    
    private static class Symbol2PositionMap
    {
        private final HashMap mySymbol2PositionList;
        
        public Symbol2PositionMap(final String[] array) {
            this.mySymbol2PositionList = new HashMap();
            for (int i = 0; i < array.length; ++i) {
                final String s = array[i];
                for (int j = 0; j < s.length(); ++j) {
                    final char char1 = s.charAt(j);
                    if (char1 != ' ') {
                        this.mapSymbol(i, j, char1);
                    }
                }
            }
        }
        
        public SymbolPosition removeSymbolPosition(final char c) {
            LinkedList list = this.findPositionsList(c);
            if (list == null) {
                final char c2 = Character.isUpperCase(c) ? Character.toLowerCase(c) : Character.toUpperCase(c);
                if (c2 != c) {
                    list = this.findPositionsList(c2);
                }
            }
            if (list == null) {
                return null;
            }
            return list.removeFirst();
        }
        
        private LinkedList findPositionsList(final char c) {
            final LinkedList list = this.mySymbol2PositionList.get(new Character(c));
            return (list != null && !list.isEmpty()) ? list : null;
        }
        
        public Iterator positions() {
            final LinkedList list = new LinkedList();
            final Iterator<Map.Entry<K, Collection>> iterator = this.mySymbol2PositionList.entrySet().iterator();
            while (iterator.hasNext()) {
                list.addAll(iterator.next().getValue());
            }
            return list.iterator();
        }
        
        private void mapSymbol(final int n, final int n2, final char c) {
            final Character c2 = new Character(c);
            List<E> list = (List<E>)this.mySymbol2PositionList.get(c2);
            if (list == null) {
                list = (List<E>)new LinkedList<SymbolPosition>();
                this.mySymbol2PositionList.put(c2, list);
            }
            ((LinkedList<SymbolPosition>)list).add(new SymbolPosition(n, n2, c));
        }
    }
}
