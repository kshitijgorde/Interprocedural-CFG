// 
// Decompiled by Procyon v0.5.30
// 

package irc;

import java.util.Iterator;
import java.util.List;
import org.jdom.input.SAXBuilder;
import java.io.IOException;
import java.io.OutputStream;
import java.io.FileOutputStream;
import org.jdom.Document;
import org.jdom.output.XMLOutputter;
import org.jdom.output.Format;
import org.jdom.Content;
import org.jdom.CDATA;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Vector;
import org.jdom.Element;
import java.text.DateFormat;
import java.util.Date;

public class Creation
{
    private String home;
    private Modification m;
    private Date actuelle;
    private DateFormat dateFormat;
    private String dat;
    private Element pseudo;
    private Element message;
    private Vector vectorF;
    private String f2;
    private Element courant;
    private Element courant2;
    private Element racine;
    private String rep;
    private String line;
    
    public Creation(final String s) {
        this.home = System.getProperty("user.home");
        this.m = new Modification();
        this.pseudo = new Element("pseudo");
        this.message = new Element("message");
        this.f2 = "ISO-8859-15";
        this.rep = "/historique_ChatLand/";
        this.dateFormat = new SimpleDateFormat("dd/MM/yyyy   hh:mm a ; :");
        final File[] listFiles = new File(this.home.trim().substring(0, 3)).listFiles();
        final String property = System.getProperty("os.name");
        if (property.toLowerCase().indexOf("linux") == -1 && property.toLowerCase().indexOf("vista") != -1) {
            for (int i = 0; i < listFiles.length; ++i) {
                if (listFiles[i].getName().toLowerCase().equals("utilisateurs")) {
                    this.home = this.home.replaceFirst("Users", "utilisateurs");
                }
            }
        }
        this.home += this.rep;
        final File file = new File(this.home);
        this.vectorF = new Vector();
        if (file.exists()) {
            final File file2 = new File(this.home + s + "/");
            if (file2.exists()) {
                final String[] list = file2.list();
                for (int j = 0; j < list.length; ++j) {
                    if (list[j].endsWith(".xml")) {
                        this.vectorF.add(list[j].substring(0, list[j].length() - 4).toLowerCase());
                    }
                }
            }
            else {
                new File(this.home + s + "/").mkdir();
            }
        }
        else {
            new File(this.home).mkdir();
            new File(this.home + s + "/").mkdir();
        }
        this.home = this.home + s + "/";
    }
    
    public boolean contains_name(final String s) {
        return this.vectorF.contains(s.toLowerCase());
    }
    
    public void creer(String lowerCase, String string, final String string2) {
        this.dat = this.DateHeure();
        string = this.dat + "  " + string;
        lowerCase = lowerCase.toLowerCase();
        final Element element = new Element("log");
        final CDATA child = new CDATA(string2);
        final CDATA child2 = new CDATA(this.m.verfierlemessage(string));
        element.addContent(this.pseudo);
        this.pseudo.addContent(child);
        element.addContent(this.message);
        this.message.addContent(child2);
        this.enregistre(lowerCase.toLowerCase() + ".xml", element);
        child.detach();
        child2.detach();
        this.pseudo.detach();
        this.message.detach();
    }
    
    public String DateHeure() {
        this.actuelle = new Date();
        return this.dat = this.dateFormat.format(this.actuelle);
    }
    
    public void effacer(final String s) {
        new File(this.home + s + ".xml").delete();
    }
    
    public void enregistre(final String s, final Element rootElement) {
        try {
            final Format prettyFormat = Format.getPrettyFormat();
            prettyFormat.setEncoding(this.f2);
            new XMLOutputter(prettyFormat).output(new Document(rootElement), new FileOutputStream(this.home + s));
        }
        catch (IOException ex) {}
    }
    
    public Vector extraire(final String s) {
        final Vector<String> vector = new Vector<String>();
        if (this.vectorF.contains(s.toLowerCase())) {
            try {
                this.racine = new SAXBuilder().build(new File(this.home + s + ".xml")).getRootElement();
                final List children = this.racine.getChildren("pseudo");
                final List children2 = this.racine.getChildren("message");
                final Iterator<Element> iterator = children.iterator();
                final Iterator<Element> iterator2 = children2.iterator();
                while (iterator.hasNext()) {
                    this.courant = iterator.next();
                    this.courant2 = iterator2.next();
                    vector.addElement(this.line = this.courant.getText() + " : " + this.courant2.getText());
                }
            }
            catch (Exception ex) {}
        }
        return vector;
    }
    
    public Vector getVectorF() {
        return this.vectorF;
    }
    
    public void principale(final String s, final String s2, final String s3) {
        this.dat = this.DateHeure();
        if (this.vectorF.contains(s.toLowerCase())) {
            this.m.modifierFichier(s3, this.dat + "  " + s2, this.home + s.toLowerCase(), this);
        }
        else {
            this.creer(s, s2, s3);
            this.vectorF.addElement(s.toLowerCase());
        }
    }
}
