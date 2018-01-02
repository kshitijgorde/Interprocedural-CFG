// 
// Decompiled by Procyon v0.5.30
// 

package jlog.jim;

import java.awt.image.ImageObserver;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.LayoutManager;
import java.awt.BorderLayout;
import java.io.FilenameFilter;
import jlog.io.$P4;
import jlog.util.$A;
import java.awt.Component;
import java.awt.MediaTracker;
import jlog.awt.image.$A6;
import java.awt.Image;
import java.awt.Frame;
import java.awt.Dimension;
import java.net.URL;
import java.io.File;
import java.awt.FileDialog;
import java.awt.Button;
import java.awt.Label;
import java.awt.Panel;

class $ZVC extends Panel implements $G7B
{
    Label $OQC;
    Label $RWC;
    Button $SWC;
    static FileDialog $TWC;
    File $ERC;
    URL $CRC;
    Dimension $B_;
    Frame $KB;
    boolean $DE;
    
    public Image $EHB(final File file) throws $A6 {
        if (file == null) {
            throw new $A6("file=null");
        }
        final String path = file.getPath();
        final Image image = this.getToolkit().getImage(path);
        final MediaTracker mediaTracker = new MediaTracker(this);
        mediaTracker.addImage(image, 1);
        try {
            mediaTracker.waitForAll();
        }
        catch (InterruptedException ex) {
            image.flush();
            throw new $A6("load image interrupted: " + path);
        }
        if (mediaTracker.isErrorAny()) {
            image.flush();
            throw new $A6("wrong image data: " + path);
        }
        return image;
    }
    
    public File $T8(final Frame frame) throws $A {
        if ($ZVC.$TWC == null) {
            $ZVC.$TWC = this.$WWC(frame);
        }
        $ZVC.$TWC.setFile("");
        $ZVC.$TWC.pack();
        $ZVC.$TWC.show();
        final String directory = $ZVC.$TWC.getDirectory();
        final String file = $ZVC.$TWC.getFile();
        if (file == null || directory == null || file.trim().length() == 0 || directory.trim().length() == 0) {
            throw new $A();
        }
        final File file2 = new File(directory, file);
        if (file2.equals(this.$ERC)) {
            throw new $A();
        }
        return file2;
    }
    
    public void $UWC() {
        try {
            this.$VWC();
            this.$CRC = $P4.$U4(this.$ERC.getPath());
            this.$RWC.setText(this.$ERC.getName());
            this.$DE = true;
        }
        catch ($A $a) {}
        catch (Exception ex) {}
    }
    
    void $VWC() throws $A, $A6 {
        final File $t8 = this.$T8(this.$KB);
        final Image $ehb = this.$EHB($t8);
        try {
            this.$B_ = this.getImageSize($ehb);
        }
        finally {
            $ehb.flush();
        }
        this.$ERC = $t8;
    }
    
    FileDialog $WWC(final Frame frame) {
        final FileDialog fileDialog = new FileDialog(frame, "Select Image (.gif, .jpg)", 0);
        fileDialog.setFilenameFilter(new FilenameFilter() {
            public boolean accept(final File file, String lowerCase) {
                if (lowerCase == null) {
                    return false;
                }
                lowerCase = lowerCase.toLowerCase();
                return lowerCase.endsWith(".gif") || lowerCase.endsWith(".jpg") || lowerCase.endsWith(".jpeg") || lowerCase.endsWith(".png");
            }
        });
        return fileDialog;
    }
    
    static {
        $ZVC.$TWC = null;
    }
    
    $ZVC(final Frame $kb) {
        super(new BorderLayout());
        this.$ERC = null;
        this.$CRC = null;
        this.$B_ = null;
        this.$DE = false;
        this.$KB = $kb;
        this.add("West", this.$OQC = new Label());
        this.add("Center", this.$RWC = new Label());
        this.add("East", this.$SWC = new Button("..."));
        this.$SWC.addActionListener(new ActionListener() {
            public void actionPerformed(final ActionEvent actionEvent) {
                $ZVC.this.$UWC();
            }
        });
    }
    
    public Dimension getImageSize(final Image image) throws $A6 {
        if (image == null) {
            throw new $A6("image=null");
        }
        final Dimension dimension = new Dimension(image.getWidth(null), image.getHeight(null));
        if (dimension.width < 1 || dimension.height < 1) {
            throw new $A6("wrong image size");
        }
        return dimension;
    }
}
