// 
// Decompiled by Procyon v0.5.30
// 

package prefuse.data.search;

import prefuse.data.tuple.DefaultTupleSet;
import java.util.Iterator;
import prefuse.data.Tuple;
import prefuse.data.tuple.TupleSet;
import prefuse.util.StringLib;
import java.util.logging.Logger;
import java.util.regex.Pattern;
import java.util.LinkedHashMap;

public class RegexSearchTupleSet extends SearchTupleSet
{
    private String m_query;
    private boolean m_caseSensitive;
    private LinkedHashMap m_source;
    
    public RegexSearchTupleSet() {
        this(false);
    }
    
    public RegexSearchTupleSet(final boolean caseSensitive) {
        this.m_query = "";
        this.m_source = new LinkedHashMap();
        this.m_caseSensitive = caseSensitive;
    }
    
    public String getQuery() {
        return this.m_query;
    }
    
    public void search(String lowerCase) {
        if (lowerCase == null) {
            lowerCase = "";
        }
        if (!this.m_caseSensitive) {
            lowerCase = lowerCase.toLowerCase();
        }
        if (lowerCase.equals(this.m_query)) {
            return;
        }
        Pattern compile;
        try {
            compile = Pattern.compile(lowerCase);
        }
        catch (Exception ex) {
            Logger.getLogger(this.getClass().getName()).warning("Pattern compile failed.\n" + StringLib.getStackTrace(ex));
            return;
        }
        final Tuple[] clearInternal = this.clearInternal();
        this.m_query = lowerCase;
        for (final String s : this.m_source.keySet()) {
            final Iterator tuples = this.m_source.get(s).tuples();
            while (tuples.hasNext()) {
                final Tuple tuple = tuples.next();
                String s2 = tuple.getString(s);
                if (!this.m_caseSensitive) {
                    s2 = s2.toLowerCase();
                }
                if (compile.matcher(s2).matches()) {
                    this.addInternal(tuple);
                }
            }
        }
        this.fireTupleEvent((Tuple[])((this.getTupleCount() > 0) ? this.toArray() : null), clearInternal);
    }
    
    public void index(final Tuple tuple, final String s) {
        TupleSet set = this.m_source.get(s);
        if (set == null) {
            set = new DefaultTupleSet();
            this.m_source.put(s, set);
        }
        set.addTuple(tuple);
    }
    
    public void unindex(final Tuple tuple, final String s) {
        final TupleSet set = this.m_source.get(s);
        if (set != null) {
            set.removeTuple(tuple);
        }
    }
    
    public boolean isUnindexSupported() {
        return true;
    }
    
    public void clear() {
        this.m_source.clear();
        super.clear();
    }
}
