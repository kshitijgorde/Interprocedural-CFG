// 
// Decompiled by Procyon v0.5.30
// 

package JUpload;

import java.security.AccessControlException;
import javax.swing.JOptionPane;
import java.awt.Component;
import javax.swing.JComponent;
import javax.swing.filechooser.FileFilter;
import javax.swing.JFileChooser;
import java.awt.event.ActionEvent;
import java.io.File;
import javax.swing.DefaultListModel;
import java.awt.event.ActionListener;

public class myAddListener implements ActionListener
{
    DefaultListModel tModel;
    JUpload parent;
    private File currentDirectory;
    private startup applet;
    
    myAddListener(final JUpload parent, final DefaultListModel tModel) {
        this.debug("myAddListener()");
        this.parent = parent;
        this.applet = this.parent.applet;
        this.tModel = tModel;
    }
    
    public void actionPerformed(final ActionEvent e) {
        this.debug("myAddListener() actionPerformed() e=" + e);
        try {
            final JFileChooser jfc = new JFileChooser();
            jfc.setMultiSelectionEnabled(true);
            jfc.setFileSelectionMode(2);
            if (Configurator.getImageFileFilter()) {
                this.debug("myAddListener() trying to set image file filter");
                final ImageFileFilter iff = new ImageFileFilter();
                jfc.setFileFilter(iff);
            }
            if (Configurator.getCustomFileFilter()) {
                this.debug("myAddListener() trying to set custom file filter");
                final CustomFileFilter cff = new CustomFileFilter();
                jfc.setFileFilter(cff);
            }
            if (!Configurator.getDefaultAddDirectory().equalsIgnoreCase("")) {
                this.debug("myAddListener() trying to set new default directory.");
                final File defDir = new File(Configurator.getDefaultAddDirectory());
                jfc.setCurrentDirectory(defDir);
            }
            if (Configurator.getHideShowAll()) {
                this.debug("myAddListener() switching off Show-All filefilter");
                jfc.setAcceptAllFileFilterUsed(false);
            }
            if (Configurator.getShowPicturePreview()) {
                this.debug("myAddListener() starting up the ImagePreview feature");
                jfc.setAccessory(new ImagePreview(jfc));
            }
            final int rVal = jfc.showDialog(this.parent, Configurator.getAddWindowTitle());
            this.currentDirectory = jfc.getCurrentDirectory();
            this.debug("myAddListener() back from file chooser. current directory = " + this.currentDirectory);
            if (rVal == 0) {
                this.walkFileTree(jfc.getSelectedFiles());
            }
            if (rVal == 1) {
                this.debug("myAddListener() You pressed cancel");
            }
        }
        catch (AccessControlException ace) {
            JOptionPane.showMessageDialog(this.parent, "You clicked NO at the permission request dialog.\nPlease restart your browser, reload the page\nand press YES", "No permission", 0);
            ace.printStackTrace();
        }
        catch (Exception exc) {
            exc.printStackTrace();
        }
        this.debug("myAddListener() Updating file queue");
        this.parent.statpanel.updateModel(this.tModel);
    }
    
    public void debug(final String s) {
        if (Configurator.getDebug()) {
            System.out.println(s);
        }
    }
    
    private void walkFileTree(final File[] files) {
        for (int i = 0; i < files.length; ++i) {
            if (files[i].isDirectory()) {
                this.debug("myAddListener() walkFileTree(): Adding Directory: " + files[i].getPath());
                final File[] f_recurse = files[i].listFiles();
                this.walkFileTree(f_recurse);
            }
            else {
                this.debug("myAddListener() walkFileTree(): Adding File " + files[i].getName() + " size=" + files[i].length());
                final MyFile newFile = new MyFile(files[i].toString());
                newFile.setCurrentDirectory(this.currentDirectory);
                if (newFile.exists()) {
                    if (newFile.length() > Configurator.getMaxFreeSpaceOnServer() && Configurator.getMaxFreeSpaceOnServer() != -1L) {
                        final String strMessage = String.valueOf(Configurator.getMaxFreeSpaceOnServerWarning()) + "\n" + newFile.getName() + "\n" + newFile.length() + " bytes";
                        final String strTitle = Configurator.getMaxFreeSpaceOnServerTitle();
                        JOptionPane.showMessageDialog(this.applet, strMessage, strTitle, 0);
                    }
                    else if (newFile.length() > Configurator.getMaxTotalRequestSize() && Configurator.getMaxTotalRequestSize() != -1L) {
                        final String strMessage = String.valueOf(Configurator.getMaxTotalRequestSizeWarning()) + "\n" + newFile.getName() + "\n" + newFile.length() + " bytes";
                        final String strTitle = Configurator.getMaxTotalRequestSizeTitle();
                        JOptionPane.showMessageDialog(this.parent, strMessage, strTitle, 0);
                    }
                    else {
                        this.tModel.addElement(newFile);
                    }
                }
                else {
                    this.debug("myAddListener() file does not exist:" + newFile);
                }
            }
        }
    }
}
