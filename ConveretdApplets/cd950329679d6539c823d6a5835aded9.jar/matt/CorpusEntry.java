// 
// Decompiled by Procyon v0.5.30
// 

package matt;

import java.util.Vector;
import java.util.StringTokenizer;
import java.sql.ResultSet;

public class CorpusEntry
{
    private String key;
    private String tunePalID;
    private String type;
    private String keySignature;
    private String file;
    private int x;
    private String title;
    private String altTitle;
    private String parsons;
    private String notation;
    private int index;
    private int[] midiSequence;
    private String midiFileName;
    private int source;
    
    public CorpusEntry() {
    }
    
    public void reset() {
    }
    
    public CorpusEntry(final ResultSet rs) {
        int id = -1;
        try {
            id = rs.getInt("id");
            this.setTitle(rs.getString("title"));
            this.setAltTitle(rs.getString("alt_title"));
            this.setKey(rs.getString("search_key"));
            this.setFile(rs.getString("file_name"));
            this.setX(rs.getInt("x"));
            this.setNotation(rs.getString("notation"));
            this.setKeySignature(rs.getString("key_sig"));
            this.setSource(rs.getInt("source"));
            this.setMidiFileName(rs.getString("midi_file_name"));
            this.setParsons(rs.getString("parsons"));
            this.setTunePalID(rs.getString("tunepalid"));
            this.setType(rs.getString("tune_type"));
            final String midiSequence = rs.getString("midi_sequence");
            final StringTokenizer stTok = new StringTokenizer(midiSequence, ",");
            final Vector<Integer> v = new Vector<Integer>();
            while (stTok.hasMoreTokens()) {
                v.add(new Integer(stTok.nextToken()));
            }
            final int[] midiNotes = new int[v.size()];
            for (int i = 0; i < v.size(); ++i) {
                midiNotes[i] = v.get(i);
            }
            this.setMidiSequence(midiNotes);
        }
        catch (Exception e) {
            Logger.log("Could not load record: " + id);
            e.printStackTrace();
        }
    }
    
    public String getKey() {
        if (MattProperties.getString("searchMethod").equals("parsons")) {
            return this.parsons;
        }
        if (MattProperties.getString("searchMethod").equals("bryan")) {
            return this.key;
        }
        return this.key;
    }
    
    public void setKey(final String key) {
        this.key = key;
    }
    
    public String getFile() {
        return this.file;
    }
    
    public void setFile(final String file) {
        this.file = file;
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
    
    public String toIndexFile() {
        final StringBuffer sb = new StringBuffer();
        sb.append(this.key);
        sb.append("\t");
        sb.append(this.keySignature);
        sb.append("\t");
        sb.append(this.title);
        sb.append("\t");
        sb.append(this.file);
        sb.append("\t");
        sb.append(this.x);
        sb.append("\t");
        sb.append(this.source);
        sb.append("\t");
        sb.append(this.parsons);
        sb.append("\t");
        sb.append(this.midiFileName);
        sb.append("\t");
        sb.append(MIDITools.instance().arrayToString(this.midiSequence));
        return sb.toString();
    }
    
    public int getIndex() {
        return this.index;
    }
    
    public void setIndex(final int index) {
        this.index = index;
    }
    
    public String getParsons() {
        return this.parsons;
    }
    
    public void setParsons(final String parsons) {
        this.parsons = parsons;
    }
    
    public int[] getMidiSequence() {
        return this.midiSequence;
    }
    
    public void setMidiSequence(final int[] midiSequecne) {
        this.midiSequence = midiSequecne;
    }
    
    public String getMidiFileName() {
        return this.midiFileName;
    }
    
    public void setMidiFileName(final String midiFileName) {
        this.midiFileName = midiFileName;
    }
    
    public int getSource() {
        return this.source;
    }
    
    public void setSource(final int source) {
        this.source = source;
    }
    
    public String getKeySignature() {
        return this.keySignature;
    }
    
    public void setKeySignature(final String keySignature) {
        this.keySignature = keySignature;
    }
    
    public String getTunePalID() {
        return this.tunePalID;
    }
    
    public void setTunePalID(final String tunePalID) {
        this.tunePalID = tunePalID;
    }
    
    public String getAltTitle() {
        return this.altTitle;
    }
    
    public void setAltTitle(final String altTitle) {
        this.altTitle = altTitle;
    }
    
    public String getNotation() {
        return this.notation;
    }
    
    public void setNotation(final String notation) {
        this.notation = notation;
    }
    
    public String getType() {
        return this.type;
    }
    
    public void setType(final String type) {
        this.type = type;
    }
}
