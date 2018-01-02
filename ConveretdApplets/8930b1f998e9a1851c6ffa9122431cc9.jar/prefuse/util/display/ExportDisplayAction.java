// 
// Decompiled by Procyon v0.5.30
// 

package prefuse.util.display;

import javax.swing.JOptionPane;
import java.io.OutputStream;
import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.io.File;
import prefuse.util.io.IOLib;
import java.awt.Component;
import java.awt.Image;
import java.awt.event.ActionEvent;
import javax.swing.JComponent;
import javax.swing.filechooser.FileFilter;
import prefuse.util.io.SimpleFileFilter;
import javax.imageio.ImageIO;
import java.util.HashSet;
import javax.swing.JFileChooser;
import prefuse.Display;
import javax.swing.AbstractAction;

public class ExportDisplayAction extends AbstractAction
{
    private Display display;
    private JFileChooser chooser;
    private ScaleSelector scaler;
    
    public ExportDisplayAction(final Display display) {
        this.display = display;
    }
    
    private void init() {
        this.scaler = new ScaleSelector();
        (this.chooser = new JFileChooser()).setDialogType(1);
        this.chooser.setDialogTitle("Export Prefuse Display...");
        this.chooser.setAcceptAllFileFilterUsed(false);
        final HashSet<String> set = new HashSet<String>();
        final String[] writerFormatNames = ImageIO.getWriterFormatNames();
        for (int i = 0; i < writerFormatNames.length; ++i) {
            final String lowerCase = writerFormatNames[i].toLowerCase();
            if (lowerCase.length() == 3 && !set.contains(lowerCase)) {
                set.add(lowerCase);
                this.chooser.setFileFilter(new SimpleFileFilter(lowerCase, lowerCase.toUpperCase() + " Image (*." + lowerCase + ")"));
            }
        }
        set.clear();
        this.chooser.setAccessory(this.scaler);
    }
    
    public void actionPerformed(final ActionEvent actionEvent) {
        if (this.chooser == null) {
            this.init();
        }
        this.scaler.setImage(this.display.getOffscreenBuffer());
        if (this.chooser.showSaveDialog(this.display) == 0) {
            File selectedFile = this.chooser.getSelectedFile();
            final String extension = ((SimpleFileFilter)this.chooser.getFileFilter()).getExtension();
            if (!extension.equals(IOLib.getExtension(selectedFile))) {
                selectedFile = new File(selectedFile.toString() + "." + extension);
            }
            final double scale = this.scaler.getScale();
            boolean saveImage;
            try {
                final BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(new FileOutputStream(selectedFile));
                System.out.print("Saving image " + selectedFile.getName() + ", " + extension + " format...");
                saveImage = this.display.saveImage(bufferedOutputStream, extension, scale);
                bufferedOutputStream.flush();
                bufferedOutputStream.close();
                System.out.println("\tDONE");
            }
            catch (Exception ex) {
                saveImage = false;
            }
            if (!saveImage) {
                JOptionPane.showMessageDialog(this.display, "Error Saving Image!", "Image Save Error", 0);
            }
        }
    }
}
