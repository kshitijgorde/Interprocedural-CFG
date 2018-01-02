// 
// Decompiled by Procyon v0.5.30
// 

package JUpload;

import java.awt.Component;
import javax.swing.JOptionPane;
import java.io.File;
import java.awt.event.ActionEvent;
import javax.swing.DefaultListModel;
import java.applet.AppletContext;
import java.awt.event.ActionListener;

public class myUploadListener implements ActionListener
{
    AppletContext context;
    DefaultListModel tModel;
    JUpload parent;
    String newline;
    int fileSizes;
    private HTTPRequest httprequest;
    private UploadManager uploadmanager;
    
    myUploadListener(final JUpload parent, final DefaultListModel tModel, final AppletContext context) {
        this.newline = System.getProperty("line.separator");
        this.fileSizes = 0;
        this.debug("myUploadListener() created");
        this.parent = parent;
        this.tModel = tModel;
        this.context = context;
        (this.uploadmanager = new UploadManager()).start();
    }
    
    public void actionPerformed(final ActionEvent e) {
        this.debug("myUploadListener() user pressed Uploadbutton e=" + e);
        this.parent.add.setEnabled(false);
        this.parent.remove.setEnabled(false);
        this.parent.upload.setEnabled(false);
        if (!this.checkMaximumUploadSize()) {
            this.parent.add.setEnabled(true);
            this.parent.remove.setEnabled(true);
            this.parent.upload.setEnabled(true);
            return;
        }
        int i = 0;
        do {
            this.parent.doLayout();
            long iTotalRequestSize = 0L;
            final long iMaxTotalRequestSize = Configurator.getMaxTotalRequestSize();
            this.debug("myUploadListener() creating new HTTPPostRequest");
            try {
                this.debug("myUploadListener() creating new HTTPRequest");
                if (Configurator.getUsePutMethod()) {
                    this.httprequest = new HTTPPutRequest(this.parent, this.tModel);
                }
                else {
                    this.httprequest = new HTTPPostRequest(this.parent, this.tModel);
                }
            }
            catch (Exception e2) {
                this.debug("***PROBLEM CREATING HTTPPOSTREQUEST***");
                e2.printStackTrace();
            }
            this.httprequest.setActionURL(Configurator.getActionURL());
            this.debug("myUploadListener() checking iTotalRequestSize<iMaxTotalRequestSize");
            this.debug("                   checking " + iTotalRequestSize + "<" + iMaxTotalRequestSize);
            while (iTotalRequestSize < iMaxTotalRequestSize || iMaxTotalRequestSize == -1L) {
                final File f = this.tModel.getElementAt(i);
                iTotalRequestSize += f.length();
                this.debug(" total request size: " + iTotalRequestSize + " maxTotalRequest is " + iMaxTotalRequestSize);
                if (iTotalRequestSize < iMaxTotalRequestSize || iMaxTotalRequestSize == -1L) {
                    this.debug("myUploadListener() moving file from filequeue to uploadqueue file=" + f);
                    this.httprequest.addFile(f);
                }
                this.debug("myUploadListener() increasing i=" + i + " tmodel.size=" + this.tModel.size());
                ++i;
                if (this.tModel.size() <= i) {
                    this.debug("myUploadListener() end of queue reached");
                    this.httprequest.setLastRequest(true);
                    break;
                }
            }
            this.debug("myUploadListener() actionPerformed() starting upload thread");
            this.parent.statpanel.updateModel(this.tModel);
            this.uploadmanager.addThread(this.httprequest);
            try {
                Thread.sleep(2000L);
            }
            catch (InterruptedException e3) {
                e3.printStackTrace();
            }
        } while (i < this.tModel.size());
        this.debug("myUploadListener() finished with filequeue. reenabling buttons");
        this.parent.add.setEnabled(true);
        this.parent.remove.setEnabled(true);
        this.parent.upload.setEnabled(true);
    }
    
    public void debug(final String s) {
        if (Configurator.getDebug()) {
            System.out.println(s);
        }
    }
    
    private boolean checkMaximumUploadSize() {
        long totalSize = 0L;
        for (int i = 0; i < this.tModel.getSize(); ++i) {
            final MyFile f = this.tModel.getElementAt(i);
            totalSize += f.length();
            if (totalSize > Configurator.getMaxFreeSpaceOnServer() && Configurator.getMaxFreeSpaceOnServer() != -1L) {
                final String strMessage = String.valueOf(Configurator.getMaxFreeSpaceOnServerWarning()) + "\n" + f.getName() + "\n" + f.length() + " bytes";
                final String strTitle = Configurator.getMaxFreeSpaceOnServerTitle();
                JOptionPane.showMessageDialog(this.parent, strMessage, strTitle, 0);
                this.parent.list.setSelectedIndex(i);
                return false;
            }
        }
        return true;
    }
}
