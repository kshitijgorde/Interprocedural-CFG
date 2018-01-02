// 
// Decompiled by Procyon v0.5.30
// 

package matt;

import java.sql.SQLException;
import java.io.IOException;
import abc.notation.Tune;
import abc.parser.TuneBook;
import java.io.FilenameFilter;
import java.io.File;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.PreparedStatement;
import java.sql.Connection;
import java.sql.Statement;
import java.util.Vector;

public class CorpusIndex
{
    Vector<CorpusEntry> index;
    static CorpusIndex _instance;
    int current;
    private boolean createMIDI;
    private boolean createParsons;
    private boolean ready;
    private String lastUniqueId;
    private int lastTuneId;
    
    public static CorpusIndex instance() {
        if (CorpusIndex._instance == null) {
            CorpusIndex._instance = new CorpusIndex();
        }
        return CorpusIndex._instance;
    }
    
    public int getCurrentIndex() {
        return this.current;
    }
    
    private CorpusIndex() {
        this.index = new Vector<CorpusEntry>();
        this.createMIDI = true;
        this.createParsons = true;
        this.lastUniqueId = "IGNOREME";
        this.lastTuneId = -1;
        this.loadDatabaseIndex();
    }
    
    public void reset() {
        this.current = 0;
    }
    
    public int size() {
        return this.index.size();
    }
    
    public CorpusEntry get(final int i) {
        return this.index.get(i);
    }
    
    public synchronized CorpusEntry getNext() {
        if (this.current >= this.index.size()) {
            return null;
        }
        return this.index.get(this.current++);
    }
    
    public void loadDatabaseIndex() {
        this.index.clear();
        Connection conn = null;
        PreparedStatement s = null;
        ResultSet r = null;
        Logger.log("Loading index from the database");
        try {
            conn = DBHelper.getConnection();
            s = conn.prepareStatement("select tuneindex.id as id, notation, tunepalid, source, file_name, x, key_sig, title, alt_title, midi_file_name, search_key, parsons, tune_type, midi_sequence from tuneindex, tunekeys where tunekeys.tuneid = tuneindex.id");
            r = s.executeQuery();
            while (r.next()) {
                final CorpusEntry entry = new CorpusEntry(r);
                this.index.add(entry);
            }
            Logger.log("Loaded " + this.index.size() + " tunes into the index");
            this.ready = true;
        }
        catch (Exception e) {
            Logger.log("Could not read index");
            e.printStackTrace();
        }
        DBHelper.safeClose(conn, s, r);
    }
    
    public void makeSQLiteDatabase() {
        Connection conn = null;
        this.lastUniqueId = "DUMMY";
        int tuneid = -1;
        try {
            Class.forName("org.sqlite.JDBC");
            Logger.log("Creating iTunePal index...");
            conn = DriverManager.getConnection("jdbc:sqlite:itunepal.sqlite");
            MattGuiNB.instance().getProgressBar().setValue(0);
            MattGuiNB.instance().getProgressBar().setMaximum(this.index.size());
            for (int i = 0; i < this.index.size(); ++i) {
                System.out.println(i);
                MattGuiNB.instance().getProgressBar().setValue(i);
                final CorpusEntry ce = this.index.get(i);
                if (ce.getSource() == 2 && ("reel".equalsIgnoreCase(ce.getType()) || "jig".equalsIgnoreCase(ce.getType()))) {
                    if (!ce.getTunePalID().equals(this.lastUniqueId)) {
                        final PreparedStatement ps = conn.prepareStatement("insert into tuneindex(title, alt_title, x, notation, source, tunepalid) values(?, ?, ?, ?, ?,?)");
                        ps.setString(1, ce.getTitle());
                        ps.setString(2, ce.getAltTitle());
                        ps.setInt(3, ce.getX());
                        ps.setString(4, ce.getNotation());
                        ps.setInt(5, ce.getSource());
                        ps.setString(6, ce.getTunePalID());
                        ps.executeUpdate();
                        ps.close();
                        final Statement statement = conn.createStatement();
                        final ResultSet rs = statement.executeQuery("select max(id) from tuneindex");
                        tuneid = rs.getInt(1);
                        this.lastUniqueId = ce.getTunePalID();
                    }
                    final PreparedStatement ps = conn.prepareStatement("insert into tunekeys(search_key, tuneid) values (?, ?)");
                    ps.setString(1, ce.getKey());
                    ps.setInt(2, tuneid);
                    ps.executeUpdate();
                    ps.close();
                }
            }
        }
        catch (Exception e) {
            Logger.log("Could not update database");
            e.printStackTrace();
        }
        finally {
            safeClose(conn, null, null);
        }
        Logger.log("Done...");
    }
    
    public void reindex() {
        Connection conn = null;
        final int lastX = -1;
        final String lastTitle = "";
        final int tuneid = -1;
        try {
            Logger.log("Reindexing tunes in database...");
            final String url = "" + MattProperties.getString("dburl");
            final String user = "" + MattProperties.getString("dbuser");
            final String password = "" + MattProperties.getString("dbpassword");
            final String driver = "" + MattProperties.getString("dbdriver");
            Class.forName(driver);
            conn = DriverManager.getConnection(url, user, password);
            final Statement statement = conn.createStatement();
            statement.execute("delete from tuneindex");
            final String folder = MattProperties.getString("MIDIIndex");
            Logger.log("Deleting MIDI files...");
            final File midiDir = new File(folder);
            final String[] children = midiDir.list();
            for (int ii = 0; ii < children.length; ++ii) {
                new File(midiDir, children[ii]).delete();
            }
            Logger.log("Done");
            final ABCFilter filter = new ABCFilter();
            this.index.clear();
            final String curDir = System.getProperty("user.dir");
            final File dir = new File(curDir + System.getProperty("file.separator") + MattProperties.getString("SearchCorpus"));
            final File[] dirs = dir.listFiles();
            MattGuiNB.instance().getProgressBar().setMaximum(dirs.length);
            for (int i = 0; i < dirs.length; ++i) {
                MattGuiNB.instance().getProgressBar().setValue(i);
                if (dirs[i].isDirectory()) {
                    int source = -1;
                    try {
                        String numberPart = "" + dirs[i];
                        numberPart = numberPart.substring(numberPart.lastIndexOf(System.getProperty("file.separator")) + 1);
                        source = Integer.parseInt("" + numberPart);
                    }
                    catch (Exception e2) {
                        Logger.log(dirs[i] + " is not a number, so I'm skipping that folder");
                        continue;
                    }
                    final File[] files = dirs[i].listFiles(filter);
                    for (int j = 0; j < files.length; ++j) {
                        this.addTunes(source, files[j], conn);
                    }
                }
                else {
                    this.addTunes(-1, dirs[i], conn);
                }
            }
        }
        catch (Exception e) {
            Logger.log("Could not update database");
            e.printStackTrace();
        }
        finally {
            safeClose(conn, null, null);
        }
        Logger.log("Done...");
    }
    
    private void addTunes(final int source, final File f, final Connection conn) throws IOException, SQLException {
        Tune tune = null;
        Logger.log("Indexing tunebook: " + f.toString());
        final TuneBook tuneBook = new TuneBook(f);
        final int numTunes = tuneBook.size();
        final int[] tuneRefs = tuneBook.getReferenceNumbers();
        for (int i = 0; i < tuneRefs.length; ++i) {
            try {
                tune = tuneBook.getTune(tuneRefs[i]);
                Logger.log("Indexing tune: " + tune.getReferenceNumber() + " " + tune.getTitles()[0]);
                final String notation = tuneBook.getTuneNotation(tuneRefs[i]);
                final int tuneStart = MattABCTools.skipHeaders(notation);
                String key = notation.substring(tuneStart);
                final String head = notation.substring(0, tuneStart);
                int iVariation = key.toUpperCase().indexOf("\"V");
                int start = 0;
                if (iVariation == 0) {
                    iVariation = key.indexOf("\"", iVariation + 1);
                    key = key.substring(iVariation + 1);
                    iVariation = key.indexOf("\"");
                }
                if (iVariation != -1) {
                    boolean endOfTune = false;
                    while (!endOfTune) {
                        String subKey = key.substring(start, iVariation);
                        this.createCorpusEntry(source, head, subKey, f.getName(), tune.getTitles()[0], tune.getReferenceNumber(), tune, tuneBook, conn);
                        iVariation = key.indexOf("\"", iVariation + 1);
                        start = iVariation + 1;
                        iVariation = key.indexOf("\"", start);
                        if (iVariation == -1) {
                            endOfTune = true;
                            subKey = key.substring(start, key.length());
                            this.createCorpusEntry(source, head, subKey, f.getName(), tune.getTitles()[0], tune.getReferenceNumber(), tune, tuneBook, conn);
                        }
                    }
                }
                else {
                    this.createCorpusEntry(source, head, key, f.getName(), tune.getTitles()[0], tune.getReferenceNumber(), tune, tuneBook, conn);
                }
            }
            catch (Exception e) {
                if (tune != null) {
                    Logger.log("Problem indexing tune " + tune.getReferenceNumber() + " " + tune.getTitles()[0] + " or the one after it.");
                }
                else {
                    Logger.log("Problem indexing a tune");
                }
                e.printStackTrace();
            }
        }
    }
    
    static String createUniqueTunePalID(final int x, final int corpus, final String fileName, String title) {
        String tunePalId = "";
        title = title.replace(System.getProperty("file.separator").charAt(0), '~');
        title = title.replace('\'', '~');
        title = title.replace('?', '~');
        title = title.replace('\"', '~');
        title = title.replace(' ', '~');
        tunePalId = "" + x + "-" + fileName + "-" + corpus + "-" + title;
        return tunePalId;
    }
    
    private void createCorpusEntry(final int source, final String head, String body, final String fileName, final String title, final int x, final Tune tune, final TuneBook book, final Connection conn) throws Exception {
        String parsons = null;
        final String uniqueId = createUniqueTunePalID(x, source, fileName, title);
        final String midiFile = MIDITools.instance().createMIDI(head, body, fileName, title, x, uniqueId);
        final int[] midiSequence = MIDITools.instance().toMIDISequence(midiFile);
        parsons = MIDITools.instance().toParsons(midiSequence);
        int tuneid = -1;
        try {
            body = MattABCTools.stripComments(body);
            body = MattABCTools.stripWhiteSpace(body);
            body = MattABCTools.expandLongNotes(body);
            body = MattABCTools.expandParts(body);
            body = MattABCTools.stripBarDivisions(body);
            body = MattABCTools.removeTripletMarks(body);
            body = MattABCTools.removeExtraNotation(body);
            body = body.toUpperCase();
            body = body.replace("WWWFROMMUSICAVIVAHTTPWWW.MUSICAVIVA.COMWTHEINTERNETCENTERFORFREESHEETMUSICDOWNLOADS.", "");
        }
        catch (Exception e) {
            e.printStackTrace();
            body = "";
        }
        if (body.length() == 0) {
            Logger.log("Could not index: " + title);
        }
        else {
            final CorpusEntry ce = new CorpusEntry();
            ce.setFile(fileName);
            ce.setSource(source);
            final String[] titles = tune.getTitles();
            if (titles.length > 1) {
                ce.setAltTitle(tune.getTitles()[1]);
            }
            ce.setTitle(title);
            ce.setX(x);
            ce.setKey(body);
            ce.setParsons(parsons);
            ce.setMidiSequence(midiSequence);
            ce.setMidiFileName(midiFile);
            ce.setKeySignature(tune.getKey().toLitteralNotation());
            if (!this.lastUniqueId.equals(uniqueId)) {
                final PreparedStatement ps = conn.prepareStatement("insert into tuneindex(`file_name`, `title`, `alt_title`, `x`, `notation`, `source`, `tune_type`, `tunepalid`, `key_sig`) values(?, ?, ?,?, ?, ?,?,?,?)");
                ps.setString(1, ce.getFile());
                ps.setString(2, ce.getTitle());
                ps.setString(3, ce.getAltTitle());
                ps.setInt(4, x);
                ps.setString(5, book.getTuneNotation(x));
                ps.setInt(6, source);
                ps.setString(7, tune.getRhythm());
                ps.setString(8, uniqueId);
                ps.setString(9, ce.getKeySignature());
                ps.executeUpdate();
                tuneid = this.getLastId("tuneindex", conn);
                ps.close();
                this.lastUniqueId = uniqueId;
                this.lastTuneId = tuneid;
            }
            final PreparedStatement ps = conn.prepareStatement("insert into tunekeys(`search_key`, `tuneid`, `midi_file_name`, `midi_sequence`, `parsons`) values(?, ?, ?, ?, ?)");
            ps.setString(1, body);
            ps.setInt(2, this.lastTuneId);
            ps.setString(3, midiFile);
            ps.setString(4, MIDITools.instance().arrayToString(ce.getMidiSequence()));
            ps.setString(5, ce.getParsons());
            ps.executeUpdate();
            ps.close();
        }
    }
    
    public int getLastId(final String table, final Connection conn) throws SQLException {
        int lastId = 0;
        final Statement statement = conn.createStatement();
        final ResultSet rs = statement.executeQuery("select max(id) as max from " + table);
        if (rs.next()) {
            lastId = rs.getInt("max");
        }
        return lastId;
    }
    
    public boolean isReady() {
        return this.ready;
    }
    
    public void setReady(final boolean ready) {
        this.ready = ready;
    }
    
    public static void safeClose(final Connection c, final Statement s, final ResultSet r) {
        if (r != null) {
            try {
                r.close();
            }
            catch (Exception e) {
                e.printStackTrace();
            }
        }
        if (s != null) {
            try {
                s.close();
            }
            catch (Exception e) {
                e.printStackTrace();
            }
        }
        if (c != null) {
            try {
                c.close();
            }
            catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
    
    static {
        CorpusIndex._instance = null;
    }
}
