// 
// Decompiled by Procyon v0.5.30
// 

package prefuse.data.search;

import java.util.Iterator;
import prefuse.data.Tuple;
import java.util.StringTokenizer;

public class PrefixSearchTupleSet extends SearchTupleSet
{
    private Trie m_trie;
    private Trie.TrieNode m_curNode;
    private String m_delim;
    private String m_query;
    
    public PrefixSearchTupleSet() {
        this(false);
    }
    
    public PrefixSearchTupleSet(final boolean b) {
        this.m_delim = " \t\n\r";
        this.m_query = "";
        this.m_trie = new Trie(b);
    }
    
    public String getDelimiterString() {
        return this.m_delim;
    }
    
    public void setDelimiterString(final String delim) {
        this.m_delim = delim;
    }
    
    public String getQuery() {
        return this.m_query;
    }
    
    public void search(String query) {
        if (query == null) {
            query = "";
        }
        if (query.equals(this.m_query)) {
            return;
        }
        final Tuple[] clearInternal = this.clearInternal();
        this.m_query = query;
        final StringTokenizer stringTokenizer = new StringTokenizer(this.m_query, this.m_delim);
        if (!stringTokenizer.hasMoreTokens()) {
            this.m_query = "";
        }
        while (stringTokenizer.hasMoreTokens()) {
            this.prefixSearch(stringTokenizer.nextToken());
        }
        this.fireTupleEvent((Tuple[])((this.getTupleCount() > 0) ? this.toArray() : null), clearInternal);
    }
    
    private void prefixSearch(final String s) {
        this.m_curNode = this.m_trie.find(s);
        if (this.m_curNode != null) {
            final Iterator trieIterator = this.trieIterator();
            while (trieIterator.hasNext()) {
                this.addInternal(trieIterator.next());
            }
        }
    }
    
    public void index(final Tuple tuple, final String s) {
        final String string;
        if ((string = tuple.getString(s)) == null) {
            return;
        }
        final StringTokenizer stringTokenizer = new StringTokenizer(string, this.m_delim);
        while (stringTokenizer.hasMoreTokens()) {
            this.addString(stringTokenizer.nextToken(), tuple);
        }
    }
    
    private void addString(final String s, final Tuple tuple) {
        this.m_trie.addString(s, tuple);
    }
    
    public boolean isUnindexSupported() {
        return true;
    }
    
    public void unindex(final Tuple tuple, final String s) {
        final String string;
        if ((string = tuple.getString(s)) == null) {
            return;
        }
        final StringTokenizer stringTokenizer = new StringTokenizer(string, this.m_delim);
        while (stringTokenizer.hasMoreTokens()) {
            this.removeString(stringTokenizer.nextToken(), tuple);
        }
    }
    
    public void clear() {
        this.m_trie = new Trie(this.m_trie.isCaseSensitive());
        super.clear();
    }
    
    private void removeString(final String s, final Tuple tuple) {
        this.m_trie.removeString(s, tuple);
    }
    
    private Iterator trieIterator() {
        return this.m_trie.new TrieIterator(this.m_curNode);
    }
}
