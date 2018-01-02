// 
// Decompiled by Procyon v0.5.30
// 

package prefuse.data.search;

import org.apache.lucene.document.Field;
import org.apache.lucene.document.Document;
import org.apache.lucene.search.Hits;
import java.io.IOException;
import org.apache.lucene.queryParser.ParseException;
import prefuse.util.StringLib;
import prefuse.data.Tuple;
import prefuse.util.collections.IntObjectHashMap;
import java.util.logging.Logger;

public class KeywordSearchTupleSet extends SearchTupleSet
{
    private static final Logger s_logger;
    protected IntObjectHashMap m_map;
    protected String m_query;
    protected LuceneSearcher m_lucene;
    protected boolean m_storeTermVectors;
    protected int m_id;
    
    public KeywordSearchTupleSet() {
        this.m_map = new IntObjectHashMap();
        this.m_query = "";
        this.m_lucene = null;
        this.m_storeTermVectors = false;
        this.m_id = 1;
        this.m_lucene = new LuceneSearcher();
    }
    
    public KeywordSearchTupleSet(final LuceneSearcher lucene) {
        this.m_map = new IntObjectHashMap();
        this.m_query = "";
        this.m_lucene = null;
        this.m_storeTermVectors = false;
        this.m_id = 1;
        this.m_lucene = lucene;
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
        (this.m_query = query).trim();
        if (query.length() == 0) {
            this.fireTupleEvent(null, -1);
            return;
        }
        this.m_lucene.setReadMode(true);
        try {
            final Hits search = this.m_lucene.search(query);
            for (int i = 0; i < search.length(); ++i) {
                this.addInternal(this.getMatchingTuple(search.doc(i)));
            }
            this.fireTupleEvent((Tuple[])((this.getTupleCount() > 0) ? this.toArray() : null), clearInternal);
        }
        catch (ParseException ex) {
            KeywordSearchTupleSet.s_logger.warning("Lucene query parse exception.\n" + StringLib.getStackTrace((Throwable)ex));
        }
        catch (IOException ex2) {
            KeywordSearchTupleSet.s_logger.warning("Lucene IO exception.\n" + StringLib.getStackTrace(ex2));
        }
    }
    
    protected Tuple getMatchingTuple(final Document document) {
        return (Tuple)this.m_map.get(Integer.parseInt(document.get("prefuse-id")));
    }
    
    public void index(final Tuple tuple, final String s) {
        this.m_lucene.setReadMode(false);
        final String string;
        if ((string = tuple.getString(s)) == null) {
            return;
        }
        final int n = this.m_id++;
        this.m_lucene.addDocument(this.getDocument(n, string));
        this.m_map.put(n, tuple);
    }
    
    public boolean isUnindexSupported() {
        return false;
    }
    
    public void unindex(final Tuple tuple, final String s) {
        throw new UnsupportedOperationException();
    }
    
    protected Document getDocument(final int n, final String s) {
        final Document document = new Document();
        document.add(Field.Text("prefuse-text", s, this.m_storeTermVectors));
        document.add(Field.Keyword("prefuse-id", String.valueOf(n)));
        return document;
    }
    
    public LuceneSearcher getLuceneSearcher() {
        return this.m_lucene;
    }
    
    public IntObjectHashMap getTupleMap() {
        return (IntObjectHashMap)this.m_map.clone();
    }
    
    public void clear() {
        this.m_lucene = new LuceneSearcher();
        super.clear();
    }
    
    static {
        s_logger = Logger.getLogger(KeywordSearchTupleSet.class.getName());
    }
}
