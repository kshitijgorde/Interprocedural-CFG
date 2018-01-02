// 
// Decompiled by Procyon v0.5.30
// 

package DesignARoom;

import java.util.Enumeration;
import java.awt.FontMetrics;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.PrintJob;
import java.awt.Font;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.util.zip.GZIPOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.InputStream;
import java.util.zip.GZIPInputStream;
import java.io.FileInputStream;
import java.awt.FileDialog;
import java.awt.Frame;
import java.awt.Color;
import java.awt.Component;
import java.util.Vector;
import java.util.Properties;

public class AppWebApplet extends WebApplet
{
    private static Properties printprefs;
    public static AppFrame aF;
    AppWebCanvas aWC;
    private String lastDirectory;
    Vector ORIGoWVector;
    Vector ORIGoHVector;
    Vector ORIGmFVector;
    Vector ORIGfurnVVector;
    int roomWidth;
    int roomHeight;
    float multFactor;
    boolean saveOK;
    
    public AppWebApplet() {
        this.lastDirectory = "myrooms\\";
        this.ORIGoWVector = new Vector();
        this.ORIGoHVector = new Vector();
        this.ORIGmFVector = new Vector();
        this.ORIGfurnVVector = new Vector();
        this.saveOK = false;
    }
    
    public static void main(final String[] array) {
        final AppWebApplet awa = new AppWebApplet();
        AppWebApplet.aF = new AppFrame("Design-A-Room");
        AppWebApplet.aF.aWA = awa;
        AppWebApplet.aF.add("Center", awa);
        awa.init();
        awa.start();
        AppWebApplet.aF.show();
    }
    
    public void constructwC() {
        this.aWC = new AppWebCanvas(this, this.getParent().size().width - 260, this.getParent().size().height - 160);
        super.wC = this.aWC;
    }
    
    public void showStatus(final String s) {
    }
    
    public void decideAbles(final int n) {
        this.decideSave(n);
        if (n == 0) {
            AppWebApplet.aF.closeMI.disable();
            AppWebApplet.aF.saveasMI.disable();
            AppWebApplet.aF.printMI.disable();
        }
        else {
            AppWebApplet.aF.closeMI.enable();
            AppWebApplet.aF.saveasMI.enable();
            AppWebApplet.aF.printMI.enable();
        }
        if (this.aWC.fNmVector.size() > 2 || (this.aWC.fNmVector.size() == 2 && n == 0)) {
            AppWebApplet.aF.closeallMI.enable();
            AppWebApplet.aF.saveallMI.enable();
        }
        else {
            AppWebApplet.aF.closeallMI.disable();
            AppWebApplet.aF.saveallMI.disable();
        }
    }
    
    public void decideSave() {
        this.decideSave(super.rmCurr.getSelectedIndex());
    }
    
    public void decideSave(final int n) {
        if (this.aWC.fNmVector.elementAt(n) == "") {
            AppWebApplet.aF.saveMI.disable();
            if (n != 0) {
                this.indicateNotSaved(n);
            }
        }
        else if (this.sameAsOrig(n)) {
            AppWebApplet.aF.saveMI.disable();
            this.indicateSaved(n);
        }
        else {
            AppWebApplet.aF.saveMI.enable();
            this.indicateNotSaved(n);
        }
    }
    
    void indicateNotSaved(final int n) {
        super.wtC.c = Color.green;
        super.wtC.repaint();
    }
    
    void indicateSaved(final int n) {
        super.wtC.c = Color.red;
        super.wtC.repaint();
    }
    
    void open() {
        final FileDialog fileDialog = new FileDialog(new Frame(), "Open Room", 0);
        fileDialog.setDirectory(this.lastDirectory);
        fileDialog.show();
        if (fileDialog.getFile() != null) {
            final String concat = String.valueOf(fileDialog.getDirectory()).concat(String.valueOf(fileDialog.getFile()));
            try {
                final ObjectInputStream objectInputStream = new ObjectInputStream(new GZIPInputStream(new FileInputStream(concat)));
                final Vector vector = (Vector)objectInputStream.readObject();
                objectInputStream.close();
                this.aWC.fNmVector.addElement(concat);
                this.aWC.oWVector.addElement(vector.elementAt(0));
                this.aWC.oHVector.addElement(vector.elementAt(1));
                this.aWC.wUVector.addElement(vector.elementAt(2));
                this.aWC.hUVector.addElement(vector.elementAt(3));
                this.aWC.mFVector.addElement(vector.elementAt(4));
                this.aWC.furnVVector.addElement(vector.elementAt(5));
                super.rmCurr.addItem(fileDialog.getFile());
                super.rmCurr.select(super.rmCurr.countItems() - 1);
                this.addToOrigVectors(super.rmCurr.getSelectedIndex());
                super.opening = true;
                this.handleChoice(super.rmCurr);
                this.lastDirectory = fileDialog.getDirectory();
            }
            catch (IOException ex) {
                System.out.println(ex);
            }
            catch (Exception ex2) {
                System.out.println(ex2);
            }
        }
    }
    
    boolean close(final int n) {
        final String s = this.aWC.fNmVector.elementAt(n);
        final String[] splitFileDir = this.splitFileDir(s);
        final String s2 = splitFileDir[0];
        final String s3 = splitFileDir[1];
        if (this.aWC.fNmVector.elementAt(n) == "" || !this.sameAsOrig(n)) {
            final AppConfirmDialog appConfirmDialog = new AppConfirmDialog(String.valueOf(String.valueOf("  Save changes to '").concat(String.valueOf(s2))).concat(String.valueOf("'?")));
            appConfirmDialog.show();
            if (appConfirmDialog.bValue.equals("Cancel")) {
                return true;
            }
            if (appConfirmDialog.bValue.equals("Yes")) {
                if (s != "") {
                    this.save(n);
                }
                else {
                    super.rmCurr.select(n);
                    this.handleChoice(super.rmCurr);
                    this.saveAs(n);
                }
            }
        }
        this.aWC.fNmVector.removeElementAt(n);
        this.aWC.oWVector.removeElementAt(n);
        this.aWC.oHVector.removeElementAt(n);
        this.aWC.wUVector.removeElementAt(n);
        this.aWC.hUVector.removeElementAt(n);
        this.aWC.mFVector.removeElementAt(n);
        this.aWC.furnVVector.removeElementAt(n);
        super.rmCurr.remove(n);
        this.removeFromOrigVectors(n);
        super.rmCurr.select(0);
        this.handleChoice(super.rmCurr);
        return false;
    }
    
    boolean closeAll() {
        boolean b = true;
        for (int i = 1; i < this.aWC.fNmVector.size(); ++i, b = false) {
            super.rmCurr.select(i);
            this.handleChoice(super.rmCurr);
            if (this.close(i)) {}
        }
        return b;
    }
    
    public void save(final int n) {
        if (AppWebApplet.aF.saveMI.isEnabled() || this.saveOK) {
            this.saveOK = false;
            final String s = this.aWC.fNmVector.elementAt(n);
            final Vector<Character> vector = new Vector<Character>();
            vector.addElement(this.aWC.oWVector.elementAt(n));
            vector.addElement(this.aWC.oHVector.elementAt(n));
            vector.addElement(this.aWC.wUVector.elementAt(n));
            vector.addElement(this.aWC.hUVector.elementAt(n));
            vector.addElement(this.aWC.mFVector.elementAt(n));
            vector.addElement(this.aWC.furnVVector.elementAt(n));
            try {
                final ObjectOutputStream objectOutputStream = new ObjectOutputStream(new GZIPOutputStream(new FileOutputStream(s)));
                objectOutputStream.writeObject(vector);
                objectOutputStream.flush();
                objectOutputStream.close();
            }
            catch (IOException ex) {
                System.out.println(ex);
            }
            this.updateOrigVectors(n);
            AppWebApplet.aF.saveMI.disable();
            this.indicateSaved(n);
        }
    }
    
    void saveAs(final int n) {
        final String[] splitFileDir = this.splitFileDir(this.aWC.fNmVector.elementAt(n));
        final String file = splitFileDir[0];
        final String directory = splitFileDir[1];
        final FileDialog fileDialog = new FileDialog(new Frame(), "Save Room", 1);
        fileDialog.setDirectory(directory);
        fileDialog.setFile(file);
        fileDialog.show();
        if (fileDialog.getFile() != null) {
            this.aWC.fNmVector.setElementAt(String.valueOf(fileDialog.getDirectory()).concat(String.valueOf(fileDialog.getFile())), n);
            this.saveOK = true;
            this.save(n);
            super.rmCurr.remove(n);
            super.rmCurr.insert(fileDialog.getFile(), n);
            super.rmCurr.select(n);
            this.handleChoice(super.rmCurr);
        }
    }
    
    void saveAll() {
        for (int i = 1; i < this.aWC.fNmVector.size(); ++i) {
            if (this.aWC.fNmVector.elementAt(i) != "") {
                this.saveOK = true;
                this.save(i);
            }
            else {
                super.rmCurr.select(i);
                this.handleChoice(super.rmCurr);
                this.saveAs(i);
            }
        }
    }
    
    public void print() {
        final PrintJob printJob = this.getToolkit().getPrintJob(AppWebApplet.aF, "Room", AppWebApplet.printprefs);
        if (printJob == null) {
            return;
        }
        final Graphics graphics = printJob.getGraphics();
        final Dimension pageDimension = printJob.getPageDimension();
        graphics.setFont(new Font("Dialog", 1, 24));
        final FontMetrics fontMetrics = graphics.getFontMetrics();
        final String selectedItem = super.rmCurr.getSelectedItem();
        final int n = fontMetrics.getHeight() * 2;
        final float n2 = pageDimension.width / Math.max(this.aWC.roomWidth, this.aWC.roomHeight);
        final float floatValue = Float.valueOf(this.aWC.oW);
        final float floatValue2 = Float.valueOf(this.aWC.oH);
        this.multFactor = pageDimension.width / Math.max(floatValue, floatValue2);
        this.roomWidth = (int)(this.multFactor * floatValue);
        this.roomHeight = (int)(this.multFactor * floatValue2);
        graphics.translate(75, 0);
        if (this.roomWidth < pageDimension.width) {
            graphics.translate((pageDimension.width - this.roomWidth) / 2, 0);
        }
        graphics.drawString(selectedItem, this.roomWidth / 2 - fontMetrics.stringWidth(selectedItem) / 2, fontMetrics.getHeight());
        graphics.drawRect(0, n, this.roomWidth, this.roomHeight);
        graphics.setClip(0, n, this.roomWidth, this.roomHeight);
        this.drawGrid(graphics, n);
        final Enumeration<Webfurn> elements = this.aWC.furnVVector.elementAt(super.rmCurr.getSelectedIndex()).elements();
        while (elements.hasMoreElements()) {
            final Webfurn webfurn = (Webfurn)elements.nextElement().clone();
            if (webfurn.getTop() < this.aWC.roomHeight) {
                webfurn.grabbed = false;
                webfurn.setActualWidth((int)(this.multFactor * Float.valueOf(webfurn.oW)));
                webfurn.setActualHeight((int)(this.multFactor * Float.valueOf(webfurn.oH)));
                webfurn.figureWandH();
                webfurn.setLeft((int)(n2 * webfurn.getLeft()));
                webfurn.setTop((int)(n2 * webfurn.getTop()) + n);
                webfurn.figurePolyPoints();
                webfurn.paint(graphics);
            }
        }
        graphics.dispose();
        printJob.end();
    }
    
    void drawGrid(final Graphics graphics, final int n) {
        if (this.aWC.gridOn) {
            graphics.setColor(Color.lightGray);
            for (int n2 = 1; n2 <= (this.roomWidth - 1) / this.multFactor; ++n2) {
                graphics.drawLine((int)(this.multFactor * n2), 0 + n, (int)(this.multFactor * n2), this.roomHeight - 1 + n);
            }
            for (int n3 = 1; n3 <= (this.roomHeight - 1) / this.multFactor; ++n3) {
                graphics.drawLine(0, (int)(this.multFactor * n3) + n, this.roomWidth - 1, (int)(this.multFactor * n3) + n);
            }
        }
    }
    
    String[] splitFileDir(final String s) {
        String s2;
        String s3;
        if (s == "") {
            s2 = super.rmCurr.getSelectedItem();
            s3 = this.lastDirectory;
        }
        else {
            s2 = s.substring(s.lastIndexOf(92) + 1);
            s3 = s.substring(0, s.lastIndexOf(92));
        }
        return new String[] { s2, s3 };
    }
    
    public void addToOrigVectors(final int n) {
        this.ORIGoWVector.addElement(this.aWC.oWVector.elementAt(n));
        this.ORIGoHVector.addElement(this.aWC.oHVector.elementAt(n));
        this.ORIGmFVector.addElement(this.aWC.mFVector.elementAt(n));
        final Vector<Object> vector = new Vector<Object>();
        final Enumeration<Webfurn> elements = this.aWC.furnVVector.elementAt(n).elements();
        while (elements.hasMoreElements()) {
            vector.addElement(elements.nextElement().clone());
        }
        this.ORIGfurnVVector.addElement(vector);
    }
    
    public void updateOrigVectors(final int n) {
        this.ORIGoWVector.setElementAt(this.aWC.oWVector.elementAt(n), n);
        this.ORIGoHVector.setElementAt(this.aWC.oHVector.elementAt(n), n);
        this.ORIGmFVector.setElementAt(this.aWC.mFVector.elementAt(n), n);
        final Vector<Object> vector = new Vector<Object>();
        final Enumeration<Webfurn> elements = this.aWC.furnVVector.elementAt(n).elements();
        while (elements.hasMoreElements()) {
            vector.addElement(elements.nextElement().clone());
        }
        this.ORIGfurnVVector.setElementAt(vector, n);
    }
    
    void removeFromOrigVectors(final int n) {
        this.ORIGoWVector.removeElementAt(n);
        this.ORIGoHVector.removeElementAt(n);
        this.ORIGmFVector.removeElementAt(n);
        this.ORIGfurnVVector.removeElementAt(n);
    }
    
    boolean sameAsOrig(final int n) {
        if (!this.aWC.oWVector.elementAt(n).equals(this.ORIGoWVector.elementAt(n)) || !this.aWC.oHVector.elementAt(n).equals(this.ORIGoHVector.elementAt(n)) || !this.aWC.mFVector.elementAt(n).equals(this.ORIGmFVector.elementAt(n))) {
            return false;
        }
        final Vector<Webfurn> vector = this.aWC.furnVVector.elementAt(n);
        final Vector<Webfurn> vector2 = this.ORIGfurnVVector.elementAt(n);
        if (vector.size() != vector2.size()) {
            return false;
        }
        for (int i = 0; i < vector.size(); ++i) {
            if (!vector.elementAt(i).equals(vector2.elementAt(i))) {
                return false;
            }
        }
        return true;
    }
    
    static {
        AppWebApplet.printprefs = new Properties();
    }
}
