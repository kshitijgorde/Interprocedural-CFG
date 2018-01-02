import java.awt.Event;
import java.awt.Component;
import java.awt.LayoutManager;
import java.awt.BorderLayout;
import java.awt.Color;
import java.util.Vector;
import java.net.URL;
import java.util.ResourceBundle;
import java.awt.Button;
import java.applet.Applet;

// 
// Decompiled by Procyon v0.5.30
// 

public class SearchApplet4ech extends Applet implements Runnable
{
    boolean timed;
    boolean stop_search;
    Button launch;
    FileLoadThread flt;
    ResourceBundle bundle;
    SearchFrame sf;
    String encoding;
    String resource_file;
    String target;
    String[] filedata;
    String[] lc_filedata;
    Thread woohoo;
    URL[] fileurls;
    Vector matched_docs;
    
    public void init() {
        if (this.getParameter("TIMED") != null) {
            this.timed = new Boolean(this.getParameter("TIMED"));
        }
        this.resource_file = this.getParameter("RESOURCE.FILE");
        this.encoding = this.getParameter("ENCODING");
        this.target = ((this.getParameter("TARGET") != null) ? this.getParameter("TARGET") : "right_frame");
        try {
            this.bundle = ResourceBundle.getBundle("SearchAppletProperties");
        }
        catch (Exception ex) {
            System.out.println(ex);
        }
        this.setBackground(new Color(51, 51, 153));
        this.setLayout(new BorderLayout(0, 0));
        (this.launch = new Button(this.getMessage("searchapplet.launch.button"))).setBackground(Color.white);
        this.launch.setForeground(new Color(51, 51, 153));
        this.launch.disable();
        this.add("Center", this.launch);
    }
    
    public boolean handleEvent(final Event event) {
        if (event.id == 1001 && event.target == this.launch && !this.sf.isShowing()) {
            this.sf.setVisible(true);
        }
        return super.handleEvent(event);
    }
    
    public void start() {
        if (this.woohoo == null) {
            this.woohoo = new Thread(this);
        }
        this.woohoo.start();
    }
    
    public void run() {
        if (this.filedata == null) {
            (this.flt = new FileLoadThread(this, this.resource_file, this.encoding)).start();
        }
        (this.sf = new SearchFrame(this)).pack();
    }
    
    public void prompt() {
        this.filedata = this.flt.getFileData();
        this.lc_filedata = new String[this.filedata.length];
        this.fileurls = this.flt.getFileURLs();
        this.launch.enable();
        if (this.timed) {
            System.out.println("File Load Time: " + this.flt.getLoadTime() + "ms");
        }
        this.sf.setStatus("");
    }
    
    public void stop() {
        if (this.woohoo != null) {
            this.woohoo.stop();
            this.woohoo = null;
        }
    }
    
    public void destroy() {
        if (this.sf != null) {
            if (this.sf.isShowing()) {
                this.sf.setVisible(false);
            }
            this.sf.dispose();
        }
    }
    
    public void search(final String[] array, final boolean b) {
        new SearchThread(this, this.sf, array, this.filedata, this.lc_filedata, b).start();
    }
    
    public void setStopSearch(final boolean stop_search) {
        this.stop_search = stop_search;
    }
    
    public boolean getStopSearch() {
        return this.stop_search;
    }
    
    public boolean getTimedStatus() {
        return this.timed;
    }
    
    public String getMessage(final String s) {
        final String string = this.bundle.getString(s);
        if (string != null) {
            return string;
        }
        return "";
    }
    
    public void showDoc(final int n) {
        final int intValue = this.matched_docs.elementAt(n);
        try {
            this.getAppletContext().showDocument(this.fileurls[intValue], this.target);
        }
        catch (Exception ex) {
            System.out.println(ex);
        }
    }
    
    public void clearMatchedDocuments() {
        this.matched_docs.removeAllElements();
    }
    
    public void addMatchedDocument(final int n) {
        this.matched_docs.addElement(new Integer(n));
    }
    
    public void setLowerCaseFileData(final int n, final String s) {
        this.lc_filedata[n] = s;
    }
    
    public SearchApplet4ech() {
        this.timed = false;
        this.stop_search = false;
        this.matched_docs = new Vector(17);
    }
}
