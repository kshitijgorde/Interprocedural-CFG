// 
// Decompiled by Procyon v0.5.30
// 

package lotus.notes.apps.viewpanel;

import java.awt.Color;
import lotus.notes.util.XMLHandler;
import java.util.Stack;
import java.io.InputStream;
import lotus.notes.util.XMLParser;
import java.util.Vector;
import lotus.notes.util.XMLHandlerBase;

public class XMLViewEntriesParser extends XMLHandlerBase
{
    private static final boolean PARSE_DEBUG = false;
    public static final char LIST_SEP = '\u0001';
    private Vector vParsedLines;
    private String url;
    private XMLParser parser;
    private ViewEntriesLine line;
    private ViewEntriesColumn col;
    private InputStream is;
    int topLevelEntries;
    String lastViewEntryPos;
    private Stack elementStack;
    private boolean isListElement;
    private StringBuffer buffer;
    
    public XMLViewEntriesParser(final String url, final Vector vParsedLines) {
        this.vParsedLines = null;
        this.topLevelEntries = 0;
        this.lastViewEntryPos = null;
        this.elementStack = new Stack();
        this.isListElement = false;
        this.buffer = new StringBuffer();
        this.vParsedLines = vParsedLines;
        this.url = url;
        this.is = null;
        (this.parser = new XMLParser()).setHandler(this);
    }
    
    public XMLViewEntriesParser(final String s, final InputStream is, final Vector vector) {
        this(s, vector);
        this.is = is;
    }
    
    public void parse() {
        this.buffer.setLength(0);
        try {
            if (this.is == null) {
                this.parser.parse(this.url, null);
            }
            else {
                this.parser.parse(this.url, null, this.is);
            }
        }
        catch (Exception ex) {
            System.out.println("Exception occurred while reading view entry data: " + ex);
        }
    }
    
    public void attribute(final String s, final String noteID, final boolean b) throws Exception {
        if (s.equalsIgnoreCase("columnnumber")) {
            this.col.columnNumber = Integer.valueOf(noteID);
        }
        if (s.equalsIgnoreCase("color")) {
            this.col.customTextColor = this.ConvertStringToColor(noteID);
        }
        if (s.equalsIgnoreCase("bgcolor")) {
            this.col.customBGColor = this.ConvertStringToColor(noteID);
        }
        else if (s.equalsIgnoreCase("unid")) {
            this.line.UNID = noteID;
        }
        else if (s.equalsIgnoreCase("position")) {
            this.line.Position = noteID;
            this.lastViewEntryPos = this.line.Position;
        }
        else if (s.equalsIgnoreCase("indent")) {
            this.line.indentLevel = Integer.valueOf(noteID);
        }
        else if (s.equalsIgnoreCase("children")) {
            this.line.children = Integer.valueOf(noteID);
        }
        else if (s.equalsIgnoreCase("collapsed")) {
            this.line.isCollapsed = true;
        }
        else if (s.equalsIgnoreCase("markedfordel")) {
            this.line.isMarkedForDelete = true;
        }
        else if (s.equalsIgnoreCase("descendants")) {
            this.line.descendants = Integer.valueOf(noteID);
        }
        else if (s.equalsIgnoreCase("toplevelentries")) {
            this.topLevelEntries = Integer.valueOf(noteID);
        }
        else if (s.equalsIgnoreCase("conflict")) {
            this.line.isConflict = true;
        }
        else if (s.equalsIgnoreCase("categorytotal")) {
            this.line.isCategoryTotal = true;
        }
        else if (s.equalsIgnoreCase("siblings")) {
            this.line.siblings = Integer.valueOf(noteID);
        }
        else if (s.equalsIgnoreCase("category")) {
            this.col.isCategory = true;
        }
        else if (s.equalsIgnoreCase("noteid")) {
            this.line.noteID = noteID;
        }
    }
    
    public void startElement(final String s) throws Exception {
        this.elementStack.push(s);
        if (!s.equalsIgnoreCase("viewentries")) {
            if (s.equalsIgnoreCase("viewentry")) {
                this.line = new ViewEntriesLine();
                this.vParsedLines.addElement(this.line);
            }
            else if (s.equalsIgnoreCase("entrydata")) {
                if (this.line.vColumn == null) {
                    this.line.vColumn = new Vector();
                }
                this.col = new ViewEntriesColumn();
                this.line.vColumn.addElement(this.col);
            }
            else if (s.equalsIgnoreCase("textlist")) {
                this.isListElement = true;
                this.col.isMultiValue = true;
                this.buffer.setLength(0);
            }
            else if (s.equalsIgnoreCase("numberlist")) {
                this.isListElement = true;
                this.col.isMultiValue = true;
                this.buffer.setLength(0);
            }
            else if (s.equalsIgnoreCase("datetimelist")) {
                this.isListElement = true;
                this.col.isMultiValue = true;
                this.buffer.setLength(0);
            }
            else if (s.equalsIgnoreCase("number")) {
                this.col.isNumber = true;
            }
            else if (s.equalsIgnoreCase("datetime")) {
                this.col.isDateTime = true;
            }
        }
    }
    
    public void endElement(final String s) throws Exception {
        this.elementStack.pop();
        if (s.equalsIgnoreCase("textlist") || s.equalsIgnoreCase("numberlist") || s.equalsIgnoreCase("datetimelist")) {
            this.isListElement = false;
            this.col.dataValue = this.buffer.toString();
            this.buffer.setLength(0);
        }
    }
    
    public void charData(final char[] array, final int n, final int n2) throws Exception {
        final String dataValue = new String(array, n, n2);
        if (this.isCurrentElement("text") || this.isCurrentElement("number") || this.isCurrentElement("datetime")) {
            if (this.isListElement) {
                if (this.buffer.length() > 0) {
                    this.buffer.append('\u0001');
                }
                this.buffer.append(dataValue);
            }
            else {
                this.col.dataValue = dataValue;
            }
        }
    }
    
    private Color ConvertStringToColor(String substring) {
        if (substring.startsWith("#")) {
            substring = substring.substring(1, 7);
        }
        Color color;
        try {
            color = new Color(Integer.parseInt(substring, 16));
        }
        catch (Exception ex) {
            ex.printStackTrace();
            color = null;
        }
        return color;
    }
    
    private boolean isCurrentElement(final String s) {
        return this.elementStack.peek().equals(s);
    }
}
