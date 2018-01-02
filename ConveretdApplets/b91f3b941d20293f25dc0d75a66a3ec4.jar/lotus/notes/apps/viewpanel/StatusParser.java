// 
// Decompiled by Procyon v0.5.30
// 

package lotus.notes.apps.viewpanel;

import lotus.notes.util.XMLHandler;
import java.util.Vector;
import lotus.notes.util.XMLParser;
import java.io.InputStream;
import lotus.notes.util.XMLHandlerBase;

class StatusParser extends XMLHandlerBase
{
    private static final boolean PARSE_DEBUG = false;
    private String url;
    private InputStream is;
    private XMLParser parser;
    private int successCount;
    private Vector badUNIDS;
    private String commandName;
    private int status;
    private String errorMessage;
    
    public StatusParser(final String url, final InputStream is) {
        this.successCount = 0;
        this.badUNIDS = new Vector();
        this.commandName = null;
        this.status = 0;
        this.errorMessage = null;
        this.url = url;
        this.is = is;
        (this.parser = new XMLParser()).setHandler(this);
    }
    
    public StatusParser(final String url, final InputStream is, final boolean b) {
        this.successCount = 0;
        this.badUNIDS = new Vector();
        this.commandName = null;
        this.status = 0;
        this.errorMessage = null;
        this.url = url;
        this.is = is;
        (this.parser = new XMLParser(b)).setHandler(this);
    }
    
    public void parse() {
        try {
            this.successCount = 0;
            this.badUNIDS.removeAllElements();
            this.commandName = null;
            this.parser.parse(this.url, null, this.is);
        }
        catch (Exception ex) {
            System.out.println("An error occurred while parsing command status: " + ex);
        }
    }
    
    public void attribute(final String s, final String s2, final boolean b) throws Exception {
        if (s.equals("SIZE")) {
            this.successCount = Integer.valueOf(s2);
        }
        else if (s.equals("NAME")) {
            this.commandName = s2;
        }
        else if (s.equals("UNID")) {
            this.badUNIDS.addElement(s2);
        }
        else if (s.equals("ERROR")) {
            this.status = Integer.valueOf(s2);
        }
        else if (s.equals("MESSAGE")) {
            this.errorMessage = s2;
        }
    }
    
    public void startElement(final String s) throws Exception {
    }
    
    public void endElement(final String s) throws Exception {
    }
    
    public void error(final String s, final String s2, final int n, final int n2) {
    }
    
    public int getSuccessCount() {
        return this.successCount;
    }
    
    public String[] getBadUNIDS() {
        if (this.badUNIDS.size() > 0) {
            final String[] array = new String[this.badUNIDS.size()];
            this.badUNIDS.copyInto(array);
            return array;
        }
        return null;
    }
    
    public String getCommandName() {
        return this.commandName;
    }
    
    public int getStatus() {
        return this.status;
    }
    
    public String getMessage() {
        return this.errorMessage;
    }
}
