import java.awt.Frame;
import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.awt.Component;
import java.awt.Button;
import java.awt.TextArea;

// 
// Decompiled by Procyon v0.5.30
// 

class JavaDoc
{
    static int currentDoc;
    String dName;
    String fName;
    TextArea textDisp;
    
    JavaDoc(final JavaGUI javaGUI) {
        this.textDisp = new TextArea();
        this.dName = "Doc" + JavaGUI.docNo;
        javaGUI.buttonBar.add(new Button(this.dName));
        javaGUI.mainWin.add(this.dName, this.textDisp);
        this.textDisp.requestFocus();
        JavaDoc.currentDoc = JavaGUI.docNo;
        ++JavaGUI.docNo;
    }
    
    JavaDoc(final JavaGUI javaGUI, final String fName) {
        this.textDisp = new TextArea();
        try {
            this.dName = "Doc" + JavaGUI.docNo;
            this.fName = fName;
            final RandomAccessFile randomAccessFile = new RandomAccessFile(this.fName, "rw");
            ++JavaGUI.docNo;
            this.loadDoc(randomAccessFile);
            javaGUI.buttonBar.add(new Button(this.dName));
            javaGUI.mainWin.add(this.dName, this.textDisp);
            this.textDisp.requestFocus();
            JavaDoc.currentDoc = JavaGUI.docNo - 1;
            randomAccessFile.close();
        }
        catch (IOException ex) {}
    }
    
    private void loadDoc(final RandomAccessFile randomAccessFile) {
        final byte[] array = new byte[500];
        try {
            final int n = (int)randomAccessFile.length() / 500;
            final int n2 = (int)randomAccessFile.length() % 500;
            if (randomAccessFile.length() > 500L) {
                for (int i = 1; i <= n; ++i) {
                    randomAccessFile.read(array, 0, 500);
                    this.textDisp.append(new String(array));
                }
            }
            if (n2 != 0) {
                final byte[] array2 = new byte[n2];
                randomAccessFile.read(array2, 0, n2);
                this.textDisp.append(new String(array2));
            }
        }
        catch (IOException ex) {}
    }
    
    public void save() {
        try {
            if (new File(this.fName).delete()) {
                final RandomAccessFile randomAccessFile = new RandomAccessFile(this.fName, "rw");
                randomAccessFile.writeBytes(this.textDisp.getText());
                randomAccessFile.close();
                return;
            }
            new ErrorBox((Frame)null, "|File not deleted!").show();
        }
        catch (IOException ex) {}
    }
    
    static {
        JavaDoc.currentDoc = 1;
    }
}
