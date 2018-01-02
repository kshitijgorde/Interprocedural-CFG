import java.awt.FileDialog;
import java.awt.event.WindowEvent;
import java.awt.Container;
import java.awt.Button;
import java.awt.MenuItem;
import java.awt.Event;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Component;
import java.awt.MenuBar;
import java.awt.Color;
import java.awt.BorderLayout;
import java.awt.LayoutManager;
import java.awt.GridLayout;
import java.awt.CardLayout;
import java.awt.Panel;
import java.awt.event.WindowListener;
import java.awt.Frame;

// 
// Decompiled by Procyon v0.5.30
// 

class JavaGUI extends Frame implements WindowListener
{
    static int docNo;
    JavaMenu main;
    Panel mainWin;
    Panel buttonBar;
    CardLayout cardMain;
    JavaDoc[] jDocs;
    
    public JavaGUI(final String h) {
        super(h);
        this.main = new JavaMenu();
        this.mainWin = new Panel();
        this.buttonBar = new Panel(new GridLayout(20, 1));
        this.cardMain = new CardLayout();
        this.jDocs = new JavaDoc[20];
        this.setLayout(new BorderLayout());
        this.setBackground(Color.lightGray);
        this.setMenuBar(this.main);
        this.mainWin.setLayout(this.cardMain);
        this.add("East", this.buttonBar);
        this.add("Center", this.mainWin);
        this.addWindowListener(this);
        this.addDoc();
    }
    
    public void paint(final Graphics g) {
        final int height = this.getSize().height;
        final int width = this.getSize().width;
        super.paint(g);
        g.setFont(new Font("Courier", 1, 12));
        g.setColor(Color.gray);
        g.drawLine(width - 72, 15, width - 72, height);
        g.setColor(Color.white);
        g.drawLine(width - 71, 16, width - 71, height + 1);
        g.setColor(Color.black);
    }
    
    public boolean action(final Event evt, final Object arg) {
        if (evt.target instanceof MenuItem) {
            if ("New".equals(arg)) {
                if (JavaGUI.docNo == 21) {
                    final ErrorBox eb = new ErrorBox(this, "You can open only 20 documents at one time!");
                    eb.show();
                    return true;
                }
                this.addDoc();
                this.buttonBar.validate();
                this.mainWin.validate();
            }
            if ("Open".equals(arg)) {
                if (JavaGUI.docNo == 21) {
                    final ErrorBox eb = new ErrorBox(this, "You can open only 20 documents at one time!");
                    eb.show();
                    return true;
                }
                this.openDoc();
                this.buttonBar.validate();
                this.mainWin.validate();
            }
            if ("Close".equals(arg)) {
                new ErrorBox(this, "Not implemented yet!").show();
            }
            if ("Save".equals(arg)) {
                this.saveDoc();
            }
            if ("Exit".equals(arg)) {
                this.dispose();
                System.exit(0);
                return true;
            }
            if ("About".equals(arg)) {
                if (AboutBox.boxExists) {
                    AboutBox.thisBox.requestFocus();
                }
                else {
                    final String s = "JavaNote 1.0|Multiplatform Java Text Editor|by|Fahim A. Farook|Far Rook's Inc. (c) 1997|Developed using Kawa 2.1|http://www.tek-tools.com/kawa|and|JDK 1.1.3 from Sun|http://www.javasoft.com";
                    final AboutBox about = new AboutBox(this, s);
                    about.setTitle("About ...");
                    about.show();
                }
            }
            if ("What is JavaNote?".equals(arg)) {
                if (AboutBox.boxExists) {
                    AboutBox.thisBox.requestFocus();
                }
                else {
                    final String s = "JavaNote is a simple Java text editor which is capable of editing upto 20 separate files. It is still in a really rough state and I would be grateful for any bugs reports or suggestions you may have. Please submit them to: FahimF@usa.net";
                    final AboutBox about = new AboutBox(this, s);
                    about.setTitle("What is JavaNote?");
                    about.show();
                }
            }
        }
        else if (evt.target instanceof Button && ((String)arg).substring(0, 3).equals("Doc")) {
            final String st = (String)arg;
            final Integer val = new Integer((st.length() == 5) ? st.substring(3, 5) : st.substring(3, 4));
            JavaDoc.currentDoc = val;
            this.cardMain.show(this.mainWin, (String)arg);
        }
        return false;
    }
    
    public void windowClosed(final WindowEvent event) {
    }
    
    public void windowDeiconified(final WindowEvent event) {
    }
    
    public void windowIconified(final WindowEvent event) {
    }
    
    public void windowActivated(final WindowEvent event) {
    }
    
    public void windowDeactivated(final WindowEvent event) {
    }
    
    public void windowOpened(final WindowEvent event) {
    }
    
    public void windowClosing(final WindowEvent evt) {
        this.dispose();
        System.exit(0);
    }
    
    public void addDoc() {
        this.jDocs[JavaGUI.docNo - 1] = new JavaDoc(this);
        this.cardMain.show(this.mainWin, "Doc" + (JavaGUI.docNo - 1));
    }
    
    public void openDoc() {
        final FileDialog fd = new FileDialog(this, "Open File", 0);
        fd.show();
        String fName = fd.getDirectory();
        fName = String.valueOf(fName) + fd.getFile();
        this.jDocs[JavaGUI.docNo - 1] = new JavaDoc(this, fName);
        this.cardMain.show(this.mainWin, "Doc" + (JavaGUI.docNo - 1));
    }
    
    public void saveDoc() {
        if (this.jDocs[JavaDoc.currentDoc - 1].fName == null) {
            final FileDialog fd = new FileDialog(this, "Save File", 1);
            fd.show();
            String fName = fd.getDirectory();
            fName = String.valueOf(fName) + fd.getFile();
            this.jDocs[JavaDoc.currentDoc - 1].fName = fName;
        }
        this.jDocs[JavaDoc.currentDoc - 1].save();
    }
    
    static {
        JavaGUI.docNo = 1;
    }
}
