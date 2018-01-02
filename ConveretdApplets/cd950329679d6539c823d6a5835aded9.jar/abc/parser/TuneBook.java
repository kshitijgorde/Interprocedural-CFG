// 
// Decompiled by Procyon v0.5.30
// 

package abc.parser;

import scanner.InvalidCharacterEvent;
import scanner.TokenEvent;
import scanner.ScannerListenerInterface;
import java.util.Set;
import java.util.Iterator;
import abc.notation.NoSuchTuneException;
import abc.notation.Tune;
import java.io.FileWriter;
import java.io.IOException;
import java.io.FileNotFoundException;
import java.io.Reader;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.FileInputStream;
import java.io.File;
import java.util.Vector;
import java.util.TreeMap;

public class TuneBook
{
    private AbcHeadersParser m_fileParser;
    private TuneParser m_parser;
    private TreeMap m_tunes;
    private Vector m_listeners;
    private Vector m_originalTunesOrder;
    private File m_file;
    
    public TuneBook(final File abcFile) throws FileNotFoundException {
        this();
        this.m_file = abcFile;
        this.buildTunesTreeMap(new BufferedReader(new InputStreamReader(new FileInputStream(abcFile))), null);
    }
    
    public TuneBook(final File abcFile, final AbcFileParserListenerInterface listener) throws FileNotFoundException {
        this();
        this.m_file = abcFile;
        this.buildTunesTreeMap(new BufferedReader(new InputStreamReader(new FileInputStream(abcFile))), listener);
    }
    
    public TuneBook(final Reader stream) throws IOException {
        this();
        this.buildTunesTreeMap(stream, null);
    }
    
    public TuneBook(final Reader stream, final AbcFileParserListenerInterface listener) throws IOException {
        this();
        this.buildTunesTreeMap(stream, listener);
    }
    
    public TuneBook() {
        this.m_fileParser = null;
        this.m_parser = null;
        this.m_tunes = null;
        this.m_listeners = null;
        this.m_originalTunesOrder = null;
        this.m_file = null;
        this.m_fileParser = new AbcHeadersParser();
        this.m_parser = new TuneParser();
        this.m_tunes = new TreeMap();
        this.m_originalTunesOrder = new Vector();
        this.m_listeners = new Vector();
    }
    
    public TuneBook(final BufferedReader stream) {
        this();
        this.buildTunesTreeMap(stream, null);
    }
    
    public TuneBook(final BufferedReader stream, final AbcFileParserListenerInterface listener) {
        this();
        this.buildTunesTreeMap(stream, listener);
    }
    
    public void saveTo(final File file) throws IOException {
        this.m_file = file;
        this.save();
    }
    
    public File getFile() {
        return this.m_file;
    }
    
    public void save() throws IOException {
        final FileWriter writer = new FileWriter(this.m_file);
        for (int i = 0; i < this.m_originalTunesOrder.size(); ++i) {
            final TranscribedTune tune = this.m_originalTunesOrder.elementAt(i);
            if (tune.header != null) {
                writer.write(tune.header);
            }
            writer.write(tune.notation);
            if (tune.notation.charAt(tune.notation.length() - 1) != '\n' || tune.notation.charAt(tune.notation.length() - 2) != '\n') {
                writer.write("\n");
            }
        }
        writer.flush();
        System.out.println("Saving to " + this.m_file.toString());
    }
    
    public Tune putTune(final String tuneNotation) {
        final Tune parsedTune = this.m_parser.parseHeader(tuneNotation);
        final Integer key = new Integer(parsedTune.getReferenceNumber());
        TranscribedTune tune = this.m_tunes.get(key);
        if (tune != null) {
            tune.notation = tuneNotation;
            tune.tune = parsedTune;
            tune.m_onlyHeader = true;
            this.notifyListenersForTuneChange(new TuneChangeEvent(this, 0, tune.tune, tuneNotation));
        }
        else {
            tune = new TranscribedTune();
            tune.notation = tuneNotation;
            tune.tune = parsedTune;
            tune.m_onlyHeader = true;
            tune.header = "";
            this.m_tunes.put(key, tune);
            this.m_originalTunesOrder.addElement(tune);
            this.notifyListenersForTuneChange(new TuneChangeEvent(this, 2, tune.tune, tuneNotation));
        }
        return parsedTune;
    }
    
    public Tune removeTune(final int referenceNumber) {
        if (this.m_tunes.remove(new Integer(referenceNumber)) != null) {
            TranscribedTune tune = null;
            for (int i = 0; i < this.m_originalTunesOrder.size(); ++i) {
                tune = this.m_originalTunesOrder.elementAt(i);
                if (tune.tune.getReferenceNumber() == referenceNumber) {
                    this.m_originalTunesOrder.removeElementAt(i);
                    this.notifyListenersForTuneChange(new TuneChangeEvent(this, 1, tune.tune, tune.notation));
                    return tune.tune;
                }
            }
        }
        return null;
    }
    
    public Tune getTune(final int referenceNumber) {
        final Integer key = new Integer(referenceNumber);
        final TranscribedTune tune = this.m_tunes.get(key);
        if (tune != null) {
            if (tune.m_onlyHeader) {
                tune.tune = this.m_parser.parse(tune.notation);
                tune.m_onlyHeader = false;
            }
            return tune.tune;
        }
        return null;
    }
    
    public String getTuneHeader(final int referenceNumber) {
        final Integer key = new Integer(referenceNumber);
        final TranscribedTune tune = this.m_tunes.get(key);
        if (tune != null) {
            return tune.header;
        }
        return null;
    }
    
    public String getTuneNotation(final int referenceNumber) throws NoSuchTuneException {
        final Integer key = new Integer(referenceNumber);
        final TranscribedTune tune = this.m_tunes.get(key);
        if (tune != null) {
            return tune.notation;
        }
        return null;
    }
    
    public Tune[] getTunesHeaders() {
        final Iterator it = this.m_tunes.keySet().iterator();
        final Tune[] tunes = new Tune[this.m_tunes.size()];
        int index = 0;
        while (it.hasNext()) {
            tunes[index] = this.m_tunes.get(it.next()).tune;
            ++index;
        }
        return tunes;
    }
    
    public int[] getReferenceNumbers() {
        final Iterator it = this.m_tunes.keySet().iterator();
        final int[] refNb = new int[this.m_tunes.size()];
        int index = 0;
        while (it.hasNext()) {
            refNb[index] = it.next();
            ++index;
        }
        return refNb;
    }
    
    public int getHighestReferenceNumber() {
        final int[] refNumbers = this.getReferenceNumbers();
        int hi = -1;
        for (int i = 0; i < refNumbers.length; ++i) {
            if (refNumbers[i] > hi) {
                hi = refNumbers[i];
            }
        }
        return hi;
    }
    
    public int size() {
        return this.m_tunes.size();
    }
    
    public Vector toVector() {
        final Set keys = this.m_tunes.keySet();
        final Vector v = new Vector();
        final Iterator keysIterator = keys.iterator();
        while (keysIterator.hasNext()) {
            v.addElement(this.m_tunes.get(keysIterator.next()).tune);
        }
        return v;
    }
    
    public void addListener(final TuneBookListenerInterface l) {
        this.m_listeners.addElement(l);
    }
    
    public void removeListener(final TuneBookListenerInterface l) {
        this.m_listeners.removeElement(l);
    }
    
    protected void notifyListenersForTuneChange(final TuneChangeEvent e) {
        for (int i = 0; i < this.m_listeners.size(); ++i) {
            this.m_listeners.elementAt(i).tuneChanged(e);
        }
    }
    
    private void buildTunesTreeMap(final Reader readerStram, final AbcFileParserListenerInterface clientListener) {
        this.m_tunes = new TreeMap();
        final ParserListener listener = new ParserListener();
        this.m_fileParser.addListener(listener);
        if (clientListener != null) {
            this.m_fileParser.addListener(clientListener);
        }
        this.m_fileParser.parseFile(readerStram);
        this.m_fileParser.removeListener(listener);
        this.m_fileParser.removeListener(clientListener);
    }
    
    private class TranscribedTune
    {
        public String header;
        public Tune tune;
        public String notation;
        public boolean m_onlyHeader;
        
        private TranscribedTune() {
            this.header = null;
            this.tune = null;
            this.notation = null;
            this.m_onlyHeader = true;
        }
    }
    
    private class ParserListener implements ScannerListenerInterface, AbcFileParserListenerInterface
    {
        private StringBuffer m_currentTuneNotation;
        private StringBuffer m_currentHeader;
        private boolean isInTune;
        
        private ParserListener() {
            this.m_currentTuneNotation = null;
            this.m_currentHeader = null;
            this.isInTune = false;
        }
        
        public void fileBegin() {
            this.m_currentHeader = new StringBuffer();
            this.m_currentTuneNotation = new StringBuffer();
        }
        
        public void tuneBegin() {
            this.isInTune = true;
        }
        
        public void invalidToken(final InvalidTokenEvent event) {
        }
        
        public void validToken(final TokenEvent event) {
        }
        
        public void invalidCharacter(final InvalidCharacterEvent event) {
        }
        
        public void tuneEnd(final Tune tune) {
            final TranscribedTune transcribedTune = new TranscribedTune();
            transcribedTune.tune = tune;
            transcribedTune.notation = new String(this.m_currentTuneNotation);
            if (this.m_currentHeader.length() > 0) {
                transcribedTune.header = new String(this.m_currentHeader);
            }
            this.m_currentTuneNotation = new StringBuffer();
            this.m_currentHeader = new StringBuffer();
            TuneBook.this.m_tunes.put(new Integer(tune.getReferenceNumber()), transcribedTune);
            TuneBook.this.m_originalTunesOrder.addElement(transcribedTune);
            TuneBook.this.notifyListenersForTuneChange(new TuneChangeEvent(this, 0, tune, transcribedTune.notation));
            this.isInTune = false;
        }
        
        public void fileEnd() {
        }
        
        public void lineProcessed(final String line) {
            if (this.isInTune) {
                this.m_currentTuneNotation.append(line);
            }
            else {
                this.m_currentHeader.append(line);
            }
        }
        
        public void tokenGenerated(final TokenEvent event) {
        }
    }
}
