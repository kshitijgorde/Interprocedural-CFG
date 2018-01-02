// 
// Decompiled by Procyon v0.5.30
// 

package XMLConsumer;

import java.net.MalformedURLException;
import hhapplet.URLFileHandler;
import BsscXML.IBsscXMLElementReader;
import java.net.URL;
import java.util.Vector;

public class Project extends Consumer
{
    private String m_sDataPath;
    private String m_sLangId;
    private Vector m_vecRemoteProject;
    private Toc m_toc;
    private Idx m_Idx;
    private Fts m_Fts;
    private Glossary m_Glossary;
    private static String m_sProjName;
    
    public static void setFileName(final String sProjName) {
        Project.m_sProjName = sProjName;
    }
    
    public static String getFileName() {
        return Project.m_sProjName;
    }
    
    public Toc getToc() {
        return this.m_toc;
    }
    
    public String getLangId() {
        return this.m_sLangId;
    }
    
    public Project(final URL url) {
        super(url);
        this.m_sDataPath = "";
        this.m_sLangId = "";
        this.m_vecRemoteProject = null;
        this.m_toc = null;
        this.m_Idx = null;
        this.m_Fts = null;
        this.m_Glossary = null;
        this.m_vecRemoteProject = new Vector();
    }
    
    public void consume(final IBsscXMLElementReader bsscXMLElementReader) {
        if (bsscXMLElementReader.getName().equals("project")) {
            final String attribute = bsscXMLElementReader.getAttribute("toc");
            final String attribute2 = bsscXMLElementReader.getAttribute("index");
            final String attribute3 = bsscXMLElementReader.getAttribute("fts");
            final String attribute4 = bsscXMLElementReader.getAttribute("glossary");
            this.m_sDataPath = bsscXMLElementReader.getAttribute("datapath");
            this.m_sLangId = bsscXMLElementReader.getAttribute("langid");
            if (this.m_sDataPath != null && this.m_sDataPath.length() != 0 && this.m_sDataPath.lastIndexOf("/") != this.m_sDataPath.length() - 1) {
                this.m_sDataPath += "/";
            }
            if (attribute != null && attribute.length() != 0) {
                try {
                    this.m_toc = new Toc(URLFileHandler.makeURL(this.getURL(), this.getDataPath() + attribute, null), this.getURL());
                }
                catch (MalformedURLException ex) {
                    ex.printStackTrace();
                }
            }
            if (attribute2 != null && attribute2.length() != 0) {
                try {
                    this.m_Idx = new Idx(URLFileHandler.makeURL(this.getURL(), this.getDataPath() + attribute2, null), this.getURL());
                }
                catch (MalformedURLException ex2) {
                    ex2.printStackTrace();
                }
            }
            if (attribute3 != null && attribute3.length() != 0) {
                try {
                    this.m_Fts = new Fts(URLFileHandler.makeURL(this.getURL(), this.getDataPath() + attribute3, null), this.getURL());
                }
                catch (MalformedURLException ex3) {
                    ex3.printStackTrace();
                }
            }
            if (attribute4 != null && attribute4.length() != 0) {
                try {
                    this.m_Glossary = new Glossary(URLFileHandler.makeURL(this.getURL(), this.getDataPath() + attribute4, null), this.getURL());
                }
                catch (MalformedURLException ex4) {
                    ex4.printStackTrace();
                }
            }
            int n = 0;
            while (true) {
                final IBsscXMLElementReader child = bsscXMLElementReader.getChild(n++);
                if (child == null) {
                    break;
                }
                if (!child.getName().equals("remote")) {
                    continue;
                }
                String s = child.getAttribute("url");
                if (s == null) {
                    continue;
                }
                if (s.length() != 0 && s.lastIndexOf("/") != s.length() - 1) {
                    s += "/";
                }
                this.m_vecRemoteProject.addElement(s);
            }
        }
    }
    
    static {
        Project.m_sProjName = "";
    }
    
    public Idx getIdx() {
        return this.m_Idx;
    }
    
    public Fts getFts() {
        return this.m_Fts;
    }
    
    public String getDataPath() {
        return this.m_sDataPath;
    }
    
    public Glossary getGlossary() {
        return this.m_Glossary;
    }
    
    public Vector getRemoteProject() {
        return this.m_vecRemoteProject;
    }
}
