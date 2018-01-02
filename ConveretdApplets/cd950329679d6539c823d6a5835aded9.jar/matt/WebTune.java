// 
// Decompiled by Procyon v0.5.30
// 

package matt;

import org.apache.commons.lang.StringEscapeUtils;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.util.Vector;
import java.io.Serializable;

public class WebTune implements Comparable, Serializable
{
    public int x;
    public String title;
    public String alt_title;
    public String source;
    public int sourceid;
    public String tunepalid;
    public String key_sig;
    public String type;
    public String notation;
    public String line;
    public String midiFileName;
    public int id;
    public float normalEd;
    public String searchKey;
    public Vector<String> keys;
    public float ed;
    
    public WebTune(final ResultSet r) throws SQLException {
        this.searchKey = r.getString("search_key");
        this.title = r.getString("title");
        this.alt_title = r.getString("alt_title");
        this.id = r.getInt("id");
        this.type = r.getString("tune_type");
        this.notation = r.getString("notation");
        this.tunepalid = r.getString("tunepalid");
        this.x = r.getInt("x");
        this.midiFileName = r.getString("midi_file_name");
        this.key_sig = r.getString("key_sig");
        this.type = r.getString("tune_type");
        this.source = r.getString("sourcename");
        this.sourceid = r.getInt("sourceid");
    }
    
    public int compareTo(final Object o1) {
        final WebTune match1 = (WebTune)o1;
        if (this.ed < match1.ed) {
            return -1;
        }
        if (this.ed == match1.ed) {
            return 0;
        }
        return 1;
    }
    
    public String writeTag(final int tabs, final String tag, final String value) {
        String ret = "";
        String val;
        if (value == null) {
            val = "";
        }
        else {
            val = value;
        }
        for (int i = 0; i < tabs; ++i) {
            ret += "\t";
        }
        return ret + "<" + tag + ">" + StringEscapeUtils.escapeXml(val) + "</" + tag + ">\n";
    }
    
    public String toXML() {
        final StringBuffer xml = new StringBuffer();
        xml.append("<?xml version=\"1.0\"?>\n");
        xml.append("<tune>\n");
        xml.append(this.writeTag(1, "title", this.title));
        xml.append(this.writeTag(1, "alt_title", this.alt_title));
        xml.append(this.writeTag(1, "x", "" + this.x));
        xml.append(this.writeTag(1, "id", "" + this.id));
        xml.append(this.writeTag(1, "tunepalid", this.tunepalid));
        xml.append(this.writeTag(1, "source", this.source));
        xml.append(this.writeTag(1, "sourceid", "" + this.sourceid));
        xml.append(this.writeTag(1, "type", this.type));
        xml.append(this.writeTag(1, "normalEd", "" + this.normalEd));
        xml.append("\t<search_keys>\n");
        for (int i = 0; i < this.keys.size(); ++i) {
            xml.append(this.writeTag(2, "key", this.keys.get(i)));
        }
        xml.append("\t</search_keys>\n");
        xml.append(this.writeTag(1, "midi_file_name", this.midiFileName));
        xml.append(this.writeTag(1, "notation", this.notation.trim()));
        xml.append("</tune>");
        return xml.toString();
    }
}
