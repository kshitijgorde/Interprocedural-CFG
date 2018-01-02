// 
// Decompiled by Procyon v0.5.30
// 

package matt;

import java.util.Hashtable;
import abc.parser.TuneBook;
import java.io.File;
import abc.notation.Tune;
import java.util.Comparator;

public class ABCMatch implements Comparator
{
    private String myStr;
    private String tunepalid;
    private String fileName;
    private String notation;
    private String line;
    private String title;
    private CorpusEntry corpusEntry;
    private float editDistance;
    private int x;
    private int source;
    private int index;
    private int which;
    private int repititions;
    private Tune tune;
    
    public ABCMatch() {
        this.notation = null;
        this.which = -1;
        this.repititions = -1;
    }
    
    public String getFileName() {
        return this.fileName;
    }
    
    public void setFileName(final String fileName) {
        this.fileName = fileName;
    }
    
    public Tune getTune() {
        if (this.tune == null) {
            Logger.log("Lazy loading tune: " + this.getX());
            try {
                final String curDir = System.getProperty("user.dir");
                final String fName = curDir + System.getProperty("file.separator") + ((Hashtable<K, Object>)MattProperties.instance()).get("SearchCorpus") + System.getProperty("file.separator") + this.getSource() + System.getProperty("file.separator") + this.getFileName();
                final File f = new File(fName);
                final TuneBook book = new TuneBook(f);
                this.setTune(book.getTune(this.x));
                this.setNotation(book.getTuneNotation(this.x));
            }
            catch (Exception e) {
                Logger.log("Lazy loading failed");
                e.printStackTrace();
            }
        }
        return this.tune;
    }
    
    public void setTune(final Tune tune) {
        this.tune = tune;
    }
    
    public String getNotation() {
        this.getTune();
        return this.notation;
    }
    
    public void setNotation(final String notation) {
        this.notation = notation;
    }
    
    public String getLine() {
        return this.line;
    }
    
    public void setLine(final String line) {
        this.line = line;
    }
    
    public float getEditDistance() {
        return this.editDistance;
    }
    
    public void setEditDistance(final float editDistance) {
        this.editDistance = editDistance;
    }
    
    public int compare(final Object o0, final Object o1) {
        final ABCMatch match0 = (ABCMatch)o0;
        final ABCMatch match2 = (ABCMatch)o1;
        if (match0.getRepititions() == -1) {
            if (match0.getEditDistance() < match2.getEditDistance()) {
                return -1;
            }
            if (match0.getEditDistance() == match2.getEditDistance()) {
                return 0;
            }
            return 1;
        }
        else {
            if (match0.getWhich() < match2.getWhich()) {
                return -1;
            }
            if (match0.getWhich() == match2.getWhich()) {
                return 0;
            }
            return 1;
        }
    }
    
    public String toString() {
        String ret = "Title: " + this.getTitle() + " File: " + this.getFileName() + " ED: " + this.getEditDistance();
        if (this.which != -1) {
            ret = ret + " Set order: " + this.which + " Repetitions: " + this.getRepititions();
        }
        ret = ret + " Line: " + this.getLine();
        return ret;
    }
    
    public int getX() {
        return this.x;
    }
    
    public void setX(final int x) {
        this.x = x;
    }
    
    public String getTitle() {
        return this.title;
    }
    
    public void setTitle(final String title) {
        this.title = title;
    }
    
    public int getIndex() {
        return this.index;
    }
    
    public void setIndex(final int index) {
        this.index = index;
    }
    
    public int getWhich() {
        return this.which;
    }
    
    public void setWhich(final int which) {
        this.which = which;
    }
    
    public int getRepititions() {
        return this.repititions;
    }
    
    public void setRepititions(final int repititions) {
        this.repititions = repititions;
    }
    
    public CorpusEntry getCorpusEntry() {
        return this.corpusEntry;
    }
    
    public void setCorpusEntry(final CorpusEntry corpusEntry) {
        this.corpusEntry = corpusEntry;
    }
    
    public String getMyStr() {
        return this.myStr;
    }
    
    public void setMyStr(final String myStr) {
        this.myStr = myStr;
    }
    
    public int getSource() {
        return this.source;
    }
    
    public void setSource(final int source) {
        this.source = source;
    }
    
    public String getTunepalid() {
        return this.tunepalid;
    }
    
    public void setTunepalid(final String tunepalid) {
        this.tunepalid = tunepalid;
    }
}
