// 
// Decompiled by Procyon v0.5.30
// 

package prefuse.data.search;

import org.apache.lucene.document.Document;
import org.apache.lucene.queryParser.ParseException;
import org.apache.lucene.search.Query;
import org.apache.lucene.queryParser.MultiFieldQueryParser;
import org.apache.lucene.queryParser.QueryParser;
import org.apache.lucene.search.Hits;
import org.apache.lucene.search.IndexSearcher;
import java.io.IOException;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.store.RAMDirectory;
import java.util.HashMap;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexReader;
import org.apache.lucene.search.Searcher;
import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.store.Directory;

public class LuceneSearcher
{
    public static final String FIELD = "prefuse-text";
    public static final String ID = "prefuse-id";
    private Directory directory;
    private Analyzer analyzer;
    private String[] fields;
    private Searcher searcher;
    private IndexReader reader;
    private IndexWriter writer;
    private boolean m_readMode;
    private boolean m_readOnly;
    private HashMap m_hitCountCache;
    
    public LuceneSearcher() {
        this((Directory)new RAMDirectory(), "prefuse-text", false);
    }
    
    public LuceneSearcher(final Directory directory) {
        this(directory, "prefuse-text", false);
    }
    
    public LuceneSearcher(final Directory directory, final String s, final boolean b) {
        this(directory, new String[] { s }, b);
    }
    
    public LuceneSearcher(final Directory directory, final String[] array, final boolean readOnly) {
        this.m_readMode = true;
        this.m_readOnly = false;
        this.m_hitCountCache = new HashMap();
        this.directory = directory;
        this.analyzer = (Analyzer)new StandardAnalyzer();
        this.fields = array.clone();
        try {
            (this.writer = new IndexWriter(this.directory, this.analyzer, !readOnly)).close();
            this.writer = null;
        }
        catch (IOException ex) {
            ex.printStackTrace();
        }
        if (!(this.m_readOnly = readOnly)) {
            this.setReadMode(false);
        }
        else {
            this.m_readMode = false;
            this.setReadMode(true);
        }
    }
    
    public boolean setReadMode(final boolean readMode) {
        if (this.m_readOnly && !readMode) {
            return false;
        }
        if (this.m_readMode == readMode) {
            return true;
        }
        Label_0162: {
            if (!readMode) {
                try {
                    if (this.searcher != null) {
                        this.searcher.close();
                    }
                    if (this.reader != null) {
                        this.reader.close();
                    }
                }
                catch (Exception ex) {
                    ex.printStackTrace();
                    return false;
                }
                try {
                    this.writer = new IndexWriter(this.directory, this.analyzer, false);
                    break Label_0162;
                }
                catch (IOException ex2) {
                    ex2.printStackTrace();
                    return false;
                }
            }
            try {
                if (this.writer != null) {
                    this.writer.optimize();
                    this.writer.close();
                }
            }
            catch (IOException ex3) {
                ex3.printStackTrace();
                return false;
            }
            try {
                this.reader = IndexReader.open(this.directory);
                this.searcher = (Searcher)new IndexSearcher(this.reader);
            }
            catch (Exception ex4) {
                ex4.printStackTrace();
                return false;
            }
        }
        this.m_readMode = readMode;
        return true;
    }
    
    public Hits search(final String s) throws ParseException, IOException {
        if (this.m_readMode) {
            Query query;
            if (this.fields.length == 1) {
                query = QueryParser.parse(s, this.fields[0], this.analyzer);
            }
            else {
                query = MultiFieldQueryParser.parse(s, this.fields, this.analyzer);
            }
            return this.searcher.search(query);
        }
        throw new IllegalStateException("Searches can only be performed when the LuceneSearcher is in read mode");
    }
    
    public int numHits(final String s) throws ParseException, IOException {
        Integer n;
        if ((n = this.m_hitCountCache.get(s)) == null) {
            n = new Integer(this.search(s).length());
            this.m_hitCountCache.put(s, n);
        }
        return n;
    }
    
    public void addDocument(final Document document) {
        if (!this.m_readMode) {
            try {
                this.writer.addDocument(document);
                this.m_hitCountCache.clear();
            }
            catch (IOException ex) {
                ex.printStackTrace();
            }
            return;
        }
        throw new IllegalStateException("Documents can not be added to the index unlessthe LuceneSearcher is not in read mode");
    }
    
    public Analyzer getAnalyzer() {
        return this.analyzer;
    }
    
    public void setAnalyzer(final Analyzer analyzer) {
        this.analyzer = analyzer;
    }
    
    public String[] getFields() {
        return this.fields.clone();
    }
    
    public void setFields(final String[] array) {
        this.fields = array.clone();
    }
    
    public IndexReader getIndexReader() {
        return this.reader;
    }
    
    public Searcher getIndexSearcher() {
        return this.searcher;
    }
    
    public boolean isReadOnly() {
        return this.m_readOnly;
    }
}
