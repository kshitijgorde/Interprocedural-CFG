// 
// Decompiled by Procyon v0.5.30
// 

package prefuse.data.search;

import java.util.NoSuchElementException;
import java.util.LinkedList;
import java.util.Iterator;
import prefuse.data.Tuple;

public class Trie
{
    private TrieBranch root;
    private boolean caseSensitive;
    
    public Trie(final boolean caseSensitive) {
        this.root = new TrieBranch();
        this.caseSensitive = false;
        this.caseSensitive = caseSensitive;
    }
    
    public boolean isCaseSensitive() {
        return this.caseSensitive;
    }
    
    public void addString(final String s, final Tuple tuple) {
        this.addLeaf(this.root, new TrieLeaf(s, tuple), 0);
    }
    
    public void removeString(final String s, final Tuple tuple) {
        this.removeLeaf(this.root, s, tuple, 0);
    }
    
    private final int getIndex(final char[] array, final char c) {
        for (int i = 0; i < array.length; ++i) {
            if (array[i] == c) {
                return i;
            }
        }
        return -1;
    }
    
    private final char getChar(final String s, final int n) {
        final char c = (n < 0 || n >= s.length()) ? '\0' : s.charAt(n);
        return this.caseSensitive ? c : Character.toLowerCase(c);
    }
    
    private final TrieNode equalityCheck(final String s, final TrieLeaf trieLeaf) {
        if (this.caseSensitive) {
            return trieLeaf.word.startsWith(s) ? trieLeaf : null;
        }
        final int length = s.length();
        if (length > trieLeaf.word.length()) {
            return null;
        }
        for (int i = 0; i < length; ++i) {
            if (Character.toLowerCase(s.charAt(i)) != Character.toLowerCase(trieLeaf.word.charAt(i))) {
                return null;
            }
        }
        return trieLeaf;
    }
    
    private boolean removeLeaf(final TrieBranch trieBranch, final String s, final Tuple tuple, final int n) {
        final int index = this.getIndex(trieBranch.chars, this.getChar(s, n));
        if (index == -1) {
            return false;
        }
        final TrieNode trieNode = trieBranch.children[index];
        if (trieNode instanceof TrieBranch) {
            final TrieBranch trieBranch2 = (TrieBranch)trieNode;
            final boolean removeLeaf = this.removeLeaf(trieBranch2, s, tuple, n + 1);
            if (removeLeaf) {
                --trieBranch.leafCount;
                if (trieBranch2.leafCount == 1) {
                    trieBranch.children[index] = trieBranch2.children[trieBranch2.children[0] == null];
                }
            }
            return removeLeaf;
        }
        TrieLeaf trieLeaf = (TrieLeaf)trieNode;
        if (trieLeaf.tuple == tuple) {
            trieBranch.children[index] = trieLeaf.next;
            if (trieLeaf.next == null) {
                this.repairBranch(trieBranch, index);
            }
            --trieBranch.leafCount;
            return true;
        }
        TrieLeaf trieLeaf2;
        for (trieLeaf2 = trieLeaf.next; trieLeaf2 != null && trieLeaf2.tuple != tuple; trieLeaf2 = trieLeaf2.next) {
            trieLeaf = trieLeaf2;
        }
        if (trieLeaf2 == null) {
            return false;
        }
        for (TrieLeaf next = (TrieLeaf)trieNode; next.tuple != tuple; next = next.next) {
            final TrieLeaf trieLeaf3 = next;
            --trieLeaf3.leafCount;
        }
        trieLeaf.next = trieLeaf2.next;
        --trieBranch.leafCount;
        return true;
    }
    
    private void repairBranch(final TrieBranch trieBranch, final int n) {
        if (n == 0) {
            trieBranch.children[0] = null;
        }
        else {
            final int length = trieBranch.chars.length;
            final char[] chars = new char[length - 1];
            final TrieNode[] children = new TrieNode[length - 1];
            System.arraycopy(trieBranch.chars, 0, chars, 0, n);
            System.arraycopy(trieBranch.children, 0, children, 0, n);
            System.arraycopy(trieBranch.chars, n + 1, chars, n, length - n - 1);
            System.arraycopy(trieBranch.children, n + 1, children, n, length - n - 1);
            trieBranch.chars = chars;
            trieBranch.children = children;
        }
    }
    
    private void addLeaf(final TrieBranch trieBranch, final TrieLeaf next, final int n) {
        trieBranch.leafCount += next.leafCount;
        final char char1 = this.getChar(next.word, n);
        final int index = this.getIndex(trieBranch.chars, char1);
        if (index == -1) {
            this.addChild(trieBranch, next, char1);
        }
        else {
            final TrieNode trieNode = trieBranch.children[index];
            if (trieNode == null) {
                trieBranch.children[index] = next;
            }
            else if (trieNode instanceof TrieBranch) {
                this.addLeaf((TrieBranch)trieNode, next, n + 1);
            }
            else {
                TrieLeaf next2 = (TrieLeaf)trieNode;
                Label_0154: {
                    if (index != 0) {
                        if (this.caseSensitive) {
                            if (next2.word.equals(next.word)) {
                                break Label_0154;
                            }
                        }
                        else if (next2.word.equalsIgnoreCase(next.word)) {
                            break Label_0154;
                        }
                        final TrieBranch trieBranch2 = new TrieBranch();
                        this.addLeaf((TrieBranch)(trieBranch.children[index] = trieBranch2), next2, n + 1);
                        this.addLeaf(trieBranch2, next, n + 1);
                        return;
                    }
                }
                while (next2.next != null) {
                    final TrieLeaf trieLeaf = next2;
                    ++trieLeaf.leafCount;
                    next2 = next2.next;
                }
                final TrieLeaf trieLeaf2 = next2;
                ++trieLeaf2.leafCount;
                next2.next = next;
            }
        }
    }
    
    private void addChild(final TrieBranch trieBranch, final TrieNode trieNode, final char c) {
        final int length = trieBranch.chars.length;
        final char[] chars = new char[length + 1];
        final TrieNode[] children = new TrieNode[length + 1];
        System.arraycopy(trieBranch.chars, 0, chars, 0, length);
        System.arraycopy(trieBranch.children, 0, children, 0, length);
        chars[length] = c;
        children[length] = trieNode;
        trieBranch.chars = chars;
        trieBranch.children = children;
    }
    
    public TrieNode find(final String s) {
        return (s.length() < 1) ? null : this.find(s, this.root, 0);
    }
    
    private TrieNode find(final String s, final TrieBranch trieBranch, final int n) {
        final int index = this.getIndex(trieBranch.chars, this.getChar(s, n));
        if (index == -1) {
            return null;
        }
        if (s.length() - 1 == n) {
            return trieBranch.children[index];
        }
        if (trieBranch.children[index] instanceof TrieLeaf) {
            return this.equalityCheck(s, (TrieLeaf)trieBranch.children[index]);
        }
        return this.find(s, (TrieBranch)trieBranch.children[index], n + 1);
    }
    
    public class TrieIterator implements Iterator
    {
        private LinkedList queue;
        
        public TrieIterator(final TrieNode trieNode) {
            (this.queue = new LinkedList()).add(trieNode);
        }
        
        public boolean hasNext() {
            return !this.queue.isEmpty();
        }
        
        public Object next() {
            if (this.queue.isEmpty()) {
                throw new NoSuchElementException();
            }
            final TrieNode trieNode = this.queue.removeFirst();
            if (trieNode instanceof TrieLeaf) {
                final TrieLeaf trieLeaf = (TrieLeaf)trieNode;
                final Tuple tuple = trieLeaf.tuple;
                if (trieLeaf.next != null) {
                    this.queue.addFirst(trieLeaf.next);
                }
                return tuple;
            }
            final TrieBranch trieBranch = (TrieBranch)trieNode;
            for (int i = trieBranch.chars.length - 1; i > 0; --i) {
                this.queue.addFirst(trieBranch.children[i]);
            }
            if (trieBranch.children[0] != null) {
                this.queue.addFirst(trieBranch.children[0]);
            }
            return this.next();
        }
        
        public void remove() {
            throw new UnsupportedOperationException();
        }
    }
    
    public class TrieNode
    {
        boolean isLeaf;
        int leafCount;
        
        public TrieNode() {
            this.leafCount = 0;
        }
    }
    
    public class TrieLeaf extends TrieNode
    {
        String word;
        Tuple tuple;
        TrieLeaf next;
        
        public TrieLeaf(final String word, final Tuple tuple) {
            this.word = word;
            this.tuple = tuple;
            this.next = null;
            this.leafCount = 1;
        }
    }
    
    public class TrieBranch extends TrieNode
    {
        char[] chars;
        TrieNode[] children;
        
        public TrieBranch() {
            this.chars = new char[] { '\0' };
            this.children = new TrieNode[1];
        }
    }
}
