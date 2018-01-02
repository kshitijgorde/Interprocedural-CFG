// 
// Decompiled by Procyon v0.5.30
// 

package lotus.notes.apps.outlinepanel;

import lotus.notes.util.XMLHandler;
import lotus.notes.util.XMLParser;
import java.util.Vector;
import lotus.notes.util.XMLHandlerBase;

public class XMLOutlineEntriesParser extends XMLHandlerBase
{
    private static final boolean PARSE_DEBUG = false;
    Vector vParsedLines;
    public int topLevelEntries;
    public int maxLevels;
    String lastOutlineEntryPos;
    String url;
    Integer i;
    OutlineEntriesLine line;
    XMLParser parser;
    
    public XMLOutlineEntriesParser(final String url, final Vector vParsedLines) {
        this.vParsedLines = null;
        this.topLevelEntries = 0;
        this.maxLevels = 0;
        this.lastOutlineEntryPos = null;
        this.url = null;
        this.line = null;
        this.parser = null;
        this.vParsedLines = vParsedLines;
        this.url = url;
        (this.parser = new XMLParser()).setHandler(this);
    }
    
    public void parse() {
        try {
            this.parser.parse(this.url, null);
        }
        catch (Exception ex) {
            System.out.println("Exception occurred while reading outline entry data: " + ex);
        }
    }
    
    public void attribute(final String s, final String s2, final boolean b) throws Exception {
        if (s.equalsIgnoreCase("level")) {
            this.i = Integer.valueOf(s2);
            this.line.Level = this.i;
            if (this.line.Level > this.maxLevels) {
                this.maxLevels = this.line.Level;
            }
            if (this.line.Level == 0) {
                ++this.topLevelEntries;
            }
        }
        else if (s.equalsIgnoreCase("position")) {
            this.line.Position = s2;
            this.lastOutlineEntryPos = this.line.Position;
        }
        else if (s.equalsIgnoreCase("expandable")) {
            this.line.isExpanded = s2.equalsIgnoreCase("true");
            this.line.isExpandable = true;
        }
        else if (s.equalsIgnoreCase("refuseselection")) {
            this.line.isRefuseSelection = s2.equalsIgnoreCase("true");
        }
        else if (s.equalsIgnoreCase("private")) {
            this.line.isPrivate = s2.equalsIgnoreCase("true");
        }
        else if (s.equalsIgnoreCase("title")) {
            this.line.entryLabel = s2;
        }
        else if (s.equalsIgnoreCase("url")) {
            this.line.URL = s2;
        }
        else if (s.equalsIgnoreCase("framesetname")) {
            this.line.targetFrame = s2;
        }
        else if (s.equalsIgnoreCase("type")) {
            this.i = Integer.valueOf(s2);
            this.line.entryType = this.i;
        }
        else if (s.equalsIgnoreCase("unid")) {
            this.line.UNID = s2;
        }
        else if (s.equalsIgnoreCase("icon")) {
            this.line.Icon = s2;
        }
        else if (s.equalsIgnoreCase("id")) {
            this.i = Integer.valueOf(s2);
            this.line.actionID = this.i;
        }
        else if (s.equalsIgnoreCase("imagewellwidth")) {
            this.i = Integer.valueOf(s2);
            this.line.iconImagesWide = this.i;
        }
        else if (s.equalsIgnoreCase("imagewellheight")) {
            this.i = Integer.valueOf(s2);
            this.line.iconImagesHigh = this.i;
        }
        else if (s.equalsIgnoreCase("readingorder")) {
            this.i = Integer.valueOf(s2);
            this.line.readingOrder = this.i;
        }
    }
    
    public void startElement(final String s) throws Exception {
        if (!s.equalsIgnoreCase("outlinedata")) {
            if (s.equalsIgnoreCase("outlineentry")) {
                this.line = new OutlineEntriesLine();
                this.vParsedLines.addElement(this.line);
            }
        }
    }
    
    public void endElement(final String s) throws Exception {
    }
}
