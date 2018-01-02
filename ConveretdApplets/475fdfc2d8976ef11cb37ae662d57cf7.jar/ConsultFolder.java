import java.awt.image.ImageObserver;
import java.awt.FontMetrics;
import java.awt.Font;
import java.awt.Color;
import java.util.StringTokenizer;
import java.util.Enumeration;
import java.util.Vector;
import java.awt.Image;

// 
// Decompiled by Procyon v0.5.30
// 

class ConsultFolder
{
    Image[] folderImage;
    int state;
    ConsultTreeTrial consultTree;
    String thisFolderName;
    int xpos;
    int ypos;
    static int y;
    int width;
    int height;
    Vector childVector;
    Enumeration e;
    StringTokenizer t;
    String temp;
    ConsultFolder folder;
    int tempYpos;
    boolean show;
    ConsultFolder parentFolder;
    boolean root;
    String html;
    Color color;
    int discSize;
    Font f;
    FontMetrics fm;
    boolean active;
    boolean haveChild;
    ConsultCanvas consultCanvas;
    String htmlPath;
    String htmlTarget;
    String target;
    
    ConsultFolder(final ConsultTreeTrial consultTree, final Image closeFolder, final Image openFolder, final String thisFolderName, final boolean show, final ConsultFolder parentFolder, final boolean root, final String html, final FontMetrics fm, final ConsultCanvas consultCanvas, final String target) {
        this.folderImage = new Image[2];
        this.childVector = new Vector();
        this.e = this.childVector.elements();
        this.active = false;
        this.haveChild = false;
        this.consultTree = consultTree;
        this.folderImage[0] = closeFolder;
        this.folderImage[1] = openFolder;
        this.thisFolderName = thisFolderName;
        this.width = closeFolder.getWidth(consultTree) + 1;
        this.height = closeFolder.getHeight(consultTree) + 5;
        this.width = this.width;
        this.height = this.height;
        this.ypos = consultCanvas.starty;
        this.xpos = consultCanvas.startx;
        this.show = show;
        this.parentFolder = parentFolder;
        this.root = root;
        this.html = html;
        this.consultCanvas = consultCanvas;
        this.fm = fm;
        this.f = new Font("Courier", consultTree.fontStyle, 12);
        this.discSize = fm.stringWidth(thisFolderName) + 4;
        this.color = consultTree.descriptionColor;
        this.target = target;
        this.checkChild();
    }
    
    void handleChild() {
        this.setUpchild();
        this.setUpState();
    }
    
    void setUpchild() {
        if (this.childVector.size() > 0) {
            return;
        }
        String vectorElement = null;
        String temp = "";
        final Enumeration e = this.consultTree.iv.elements();
        while (e.hasMoreElements()) {
            vectorElement = e.nextElement();
            temp = vectorElement.substring(0, vectorElement.indexOf(";"));
            if (this.thisFolderName.equals(temp)) {
                this.addNewChild(vectorElement);
            }
        }
    }
    
    void checkChild() {
        String vectorElement = null;
        String temp = "";
        int index = 0;
        final Enumeration e = this.consultTree.iv.elements();
        while (e.hasMoreElements()) {
            vectorElement = e.nextElement();
            index = vectorElement.indexOf(";");
            temp = vectorElement.substring(0, index);
            if (this.thisFolderName.equals(temp)) {
                index = vectorElement.indexOf(";", index + 1);
                if (index >= 0) {
                    this.haveChild = true;
                    return;
                }
                break;
            }
        }
    }
    
    void addNewChild(final String vectorElement) {
        this.t = new StringTokenizer(vectorElement, ";");
        this.temp = this.t.nextToken();
        this.tempYpos = this.ypos + this.height;
        while (this.t.hasMoreTokens()) {
            this.getHTML(this.temp = this.t.nextToken());
            this.folder = new ConsultFolder(this.consultTree, this.folderImage[0], this.folderImage[1], this.temp, true, this, false, this.htmlPath, this.fm, this.consultCanvas, this.htmlTarget);
            this.consultTree.folderVector.addElement(this.folder);
            this.childVector.addElement(this.folder);
            this.tempYpos += this.height;
        }
    }
    
    void getHTML(final String FolderName) {
        String vectorElement = null;
        String temp = "";
        final Enumeration e = this.consultTree.dv.elements();
        while (e.hasMoreElements()) {
            vectorElement = e.nextElement();
            final StringTokenizer t = new StringTokenizer(vectorElement, ";");
            temp = t.nextToken();
            if (FolderName.equals(temp)) {
                this.htmlPath = t.nextToken();
                this.htmlTarget = null;
                while (t.hasMoreTokens()) {
                    this.htmlTarget = t.nextToken();
                }
            }
        }
    }
    
    void setUpState() {
        if (this.state == 0) {
            this.state = 1;
            this.active = true;
            return;
        }
        this.state = 0;
        this.active = false;
    }
    
    void setUpPos(int childXpos) {
        ConsultFolder.y += this.height;
        this.ypos = ConsultFolder.y;
        this.xpos = childXpos;
        childXpos = this.xpos + this.width;
        if (!this.active) {
            this.setShow(false);
            this.show = true;
            return;
        }
        this.show = true;
        final Enumeration e1 = this.childVector.elements();
        while (e1.hasMoreElements()) {
            final ConsultFolder childFolder = e1.nextElement();
            childFolder.setUpPos(childXpos);
        }
    }
    
    void setShow(final boolean show) {
        this.show = show;
        final Enumeration e1 = this.childVector.elements();
        while (e1.hasMoreElements()) {
            final ConsultFolder childFolder = e1.nextElement();
            childFolder.setShow(show);
        }
    }
}
