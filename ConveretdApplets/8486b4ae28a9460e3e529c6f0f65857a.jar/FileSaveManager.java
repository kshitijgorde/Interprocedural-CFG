import java.awt.Container;
import java.io.IOException;
import java.io.OutputStream;
import java.io.FileOutputStream;
import java.io.File;
import java.awt.FileDialog;
import java.awt.Frame;
import java.awt.Color;
import java.io.FilenameFilter;

// 
// Decompiled by Procyon v0.5.30
// 

public class FileSaveManager extends SaveManager implements FilenameFilter
{
    public String formatMessage(final Object o) {
        return o.toString();
    }
    
    public void performSave(final DesignCanvas designCanvas, final Color color) {
        this.getDesigns(designCanvas);
        if (super.designs == null || super.designs.length == 0) {
            this.setChanged();
            this.notifyObservers("No designs drawn; not saving.");
            return;
        }
        Container container;
        for (container = designCanvas.getParent(); container != null && !(container instanceof Frame); container = container.getParent()) {}
        if (container != null && container instanceof Frame) {
            final FileDialog fileDialog = new FileDialog((Frame)container, "Save Targa (.TGA) Image File", 1);
            fileDialog.setFilenameFilter(this);
            fileDialog.show();
            final String file = fileDialog.getFile();
            final String directory = fileDialog.getDirectory();
            if (file == null || directory == null) {
                return;
            }
            final File file2 = new File(directory, file);
            if (file != null) {
                try {
                    final FileOutputStream fileOutputStream = new FileOutputStream(file2);
                    if (designCanvas.writeData(super.designs, fileOutputStream, color, 2)) {
                        this.setChanged();
                        this.notifyObservers("Write to " + file + " succeeded.");
                    }
                    else {
                        this.setChanged();
                        this.notifyObservers("Write to " + file + " failed.");
                    }
                    fileOutputStream.close();
                }
                catch (IOException ex) {
                    this.setChanged();
                    this.notifyObservers("Write to " + file + " failed: " + ex);
                }
            }
        }
    }
    
    public boolean accept(final File file, final String s) {
        return s.toLowerCase().endsWith(".tga");
    }
}
