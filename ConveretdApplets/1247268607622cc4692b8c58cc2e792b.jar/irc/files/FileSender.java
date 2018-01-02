// 
// Decompiled by Procyon v0.5.30
// 

package irc.files;

import java.net.Socket;
import java.awt.GridLayout;
import java.awt.Dimension;
import java.awt.Component;
import java.awt.LayoutManager;
import java.awt.BorderLayout;
import javax.swing.Icon;
import irc.EIRC;
import javax.swing.JProgressBar;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class FileSender extends JPanel
{
    JLabel size;
    JLabel file;
    JProgressBar progress;
    String filetosend;
    String ip;
    String folderpath;
    String filepath;
    String filename;
    String target;
    int port;
    long filesize;
    EIRC eirc;
    JLabel image;
    JPanel pan;
    
    public FileSender() {
        this.size = new JLabel();
        this.file = new JLabel();
        this.progress = new JProgressBar();
        this.image = new JLabel();
        this.pan = new JPanel();
        try {
            this.jbInit();
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
    }
    
    public FileSender(final String target, final String filename, final String filepath, final long filesize, final EIRC eirc) {
        this.size = new JLabel();
        this.file = new JLabel();
        this.progress = new JProgressBar();
        this.image = new JLabel();
        this.pan = new JPanel();
        try {
            this.jbInit();
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
        this.eirc = eirc;
        this.target = target;
        this.filename = filename;
        this.folderpath = filepath.substring(0, filepath.length() - filename.length());
        this.filesize = filesize;
        this.filepath = filepath;
        this.size.setText("Envoie vers: " + target);
        this.file.setText("Nom: " + this.filename + " Taille du fichier:" + this.filesize / 1000L + " KO");
        final String substring = this.filename.substring(this.filename.lastIndexOf(".") + 1);
        if (substring.equalsIgnoreCase("rar") || substring.equalsIgnoreCase("zip") || substring.equalsIgnoreCase("jar") || substring.equalsIgnoreCase("war")) {
            this.image.setIcon(FileTransferManager.filearchive);
        }
        else if (substring.equalsIgnoreCase("jpg") || substring.equalsIgnoreCase("jpeg") || substring.equalsIgnoreCase("bmp") || substring.equalsIgnoreCase("png") || substring.equalsIgnoreCase("ico")) {
            this.image.setIcon(FileTransferManager.fileimage);
        }
        else if (substring.equalsIgnoreCase("mp3") || substring.equalsIgnoreCase("mp4") || substring.equalsIgnoreCase("avi") || substring.equalsIgnoreCase("wmv") || substring.equalsIgnoreCase("wma")) {
            this.image.setIcon(FileTransferManager.filemultimedia);
        }
        else if (substring.equalsIgnoreCase("doc") || substring.equalsIgnoreCase("docx")) {
            this.image.setIcon(FileTransferManager.filedoc);
        }
        else if (substring.equalsIgnoreCase("xls") || substring.equalsIgnoreCase("xlsx")) {
            this.image.setIcon(FileTransferManager.filexls);
        }
        else if (substring.equalsIgnoreCase("pdf")) {
            this.image.setIcon(FileTransferManager.filepdf);
        }
        else {
            this.image.setIcon(FileTransferManager.fileunknown);
        }
    }
    
    private void jbInit() throws Exception {
        this.setLayout(new BorderLayout());
        this.add(this.image, "West");
        this.file.setPreferredSize(new Dimension(300, 15));
        this.pan.setLayout(new GridLayout(3, 1));
        this.pan.add(this.size);
        this.pan.add(this.progress);
        this.pan.add(this.file);
        this.setPreferredSize(new Dimension(350, 50));
        this.add(this.pan, "Center");
    }
    
    public void notifyEndTransfer() {
        this.pan.setLayout(new GridLayout(1, 1));
        this.size.setText("Envoi termin\u00e9: (" + this.filename + ")");
        this.pan.remove(this.file);
        this.pan.remove(this.progress);
        FileTransferManager.removeSender(this.filename + "+" + this.target);
    }
    
    public void notifyErrorTransfer() {
        this.pan.setLayout(new GridLayout(1, 1));
        this.size.setText("Erreur lors de l'envoie: (" + this.filename + ")");
        this.image.setIcon(FileTransferManager.filebroken);
        this.pan.remove(this.file);
        this.pan.remove(this.progress);
    }
    
    public void SendFile(final Socket socket) {
        this.progress.setMinimum(0);
        this.progress.setMaximum(Integer.parseInt("" + this.filesize));
        new SendingFile(this, socket);
    }
}
