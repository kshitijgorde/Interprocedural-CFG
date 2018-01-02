// 
// Decompiled by Procyon v0.5.30
// 

package irc.files;

import java.io.PrintWriter;
import java.io.Writer;
import java.io.BufferedWriter;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.net.InetAddress;
import java.io.File;
import javax.swing.JFileChooser;
import java.awt.Cursor;
import java.io.IOException;
import java.awt.event.MouseEvent;
import java.awt.GridLayout;
import java.awt.Component;
import java.awt.FlowLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.LayoutManager;
import java.awt.BorderLayout;
import javax.swing.Icon;
import irc.EIRC;
import java.net.ServerSocket;
import javax.swing.JProgressBar;
import javax.swing.JLabel;
import java.awt.event.MouseListener;
import javax.swing.JPanel;

public class FileReciever extends JPanel implements MouseListener
{
    JLabel info;
    JLabel file;
    JProgressBar progress;
    JPanel panel;
    JPanel panelac;
    String filetosend;
    String ip;
    String filepath;
    String filename;
    String target;
    ServerSocket server;
    long filesize;
    public EIRC eirc;
    String path;
    JLabel accept;
    JLabel refus;
    JLabel image;
    boolean terminer;
    
    public FileReciever(final EIRC eirc, final String filename, final String s, final String target) {
        this.info = new JLabel();
        this.file = new JLabel();
        this.progress = new JProgressBar();
        this.panel = new JPanel();
        this.panelac = new JPanel();
        this.accept = new JLabel("<html><u>Accepter</u></html>");
        this.refus = new JLabel("<html><u>Refuser</u></html>");
        this.image = new JLabel();
        this.terminer = false;
        this.eirc = eirc;
        this.filename = filename;
        this.filesize = Long.parseLong(s);
        this.target = target;
        try {
            this.jbInit();
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
        this.refus.addMouseListener(this);
        this.accept.addMouseListener(this);
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
    
    private String[] buildResponse() {
        return new String[] { this.target, "\u0001ACTION [Acceptfile]" + this.filename + " " + this.filesize + "\u0001" };
    }
    
    private void jbInit() throws Exception {
        this.setLayout(new BorderLayout());
        this.info.setText("R\u00e9ception de: " + this.target);
        this.file.setText("Nom:" + this.filename + "  Taille du fichier:" + this.filesize / 1000L + "KO");
        this.file.setPreferredSize(new Dimension(300, 15));
        this.refus.setPreferredSize(new Dimension(150, 15));
        this.accept.setPreferredSize(new Dimension(150, 15));
        this.accept.setForeground(Color.BLUE);
        this.refus.setForeground(Color.BLUE);
        this.setPreferredSize(new Dimension(350, 50));
        this.panelac.setLayout(new FlowLayout(0, 0, 0));
        this.panelac.add(this.accept);
        this.panelac.add(this.refus);
        this.panel.setLayout(new GridLayout(4, 1));
        this.panel.add(this.info);
        this.panel.add(this.progress);
        this.panel.add(this.file);
        this.panel.add(this.panelac);
        this.add(this.image, "West");
        this.add(this.panel, "Center");
    }
    
    @Override
    public void mouseClicked(final MouseEvent mouseEvent) {
        this.eirc.revenir();
        if (mouseEvent.getSource().equals(this.refus)) {
            if (!this.terminer) {
                this.eirc.sendMessage("PRIVMSG", new String[] { this.target, "\u0001ACTION [FILEREFUSE] " + this.filename + "\u0001" });
                this.removeAll();
                this.repaint();
            }
        }
        else if (mouseEvent.getSource().equals(this.accept)) {
            if (!this.terminer) {
                FileTransferManager.acceptFile(this.filename, this.target);
                FileTransferManager.removeReciever(this.filename, this.target);
                this.accept.setVisible(false);
                this.refus.setVisible(false);
                this.accept.setEnabled(false);
                this.refus.setEnabled(false);
                this.repaint();
            }
            else {
                try {
                    Runtime.getRuntime().exec("cmd /c \"" + this.filepath + "\"");
                }
                catch (IOException ex) {}
            }
        }
    }
    
    @Override
    public void mouseEntered(final MouseEvent mouseEvent) {
        this.eirc.revenir();
        this.setCursor(Cursor.getPredefinedCursor(12));
        ((JLabel)mouseEvent.getSource()).setForeground(Color.RED);
    }
    
    @Override
    public void mouseExited(final MouseEvent mouseEvent) {
        this.eirc.revenir();
        this.setCursor(Cursor.getDefaultCursor());
        ((JLabel)mouseEvent.getSource()).setForeground(Color.BLUE);
    }
    
    @Override
    public void mousePressed(final MouseEvent mouseEvent) {
        this.eirc.revenir();
    }
    
    @Override
    public void mouseReleased(final MouseEvent mouseEvent) {
        this.eirc.revenir();
    }
    
    public void notifyErrorRecieve() {
        this.info.setText("Erreur lors de la r\u00e9ception:" + this.filename);
        this.refus.setVisible(false);
        this.refus.setEnabled(false);
        this.panel.setLayout(new GridLayout(1, 1));
        this.file.setVisible(false);
        this.panel.remove(this.file);
        this.panel.remove(this.progress);
        this.image.setIcon(FileTransferManager.filebroken);
        FileTransferManager.removeReciever(this.filename, this.target);
    }
    
    public void notifyFileRecieve() {
        this.info.setText("R\u00e9ception termin\u00e9e:" + this.filename);
        this.refus.setVisible(false);
        this.refus.setEnabled(false);
        this.panel.setLayout(new GridLayout(1, 1));
        this.panel.remove(this.file);
        this.panel.remove(this.progress);
        FileTransferManager.removeReciever(this.filename, this.target);
    }
    
    public void ReceiveFile() {
        final JFileChooser fileChooser = new JFileChooser();
        fileChooser.setSelectedFile(new File(this.filename));
        final int showSaveDialog = fileChooser.showSaveDialog(this);
        if (showSaveDialog == 1) {
            this.eirc.sendMessage("PRIVMSG", new String[] { this.target, "\u0001ACTION [FILEREFUSE] \u0001" });
            this.removeAll();
            this.repaint();
        }
        else if (showSaveDialog == 0) {
            this.filepath = fileChooser.getSelectedFile().getPath();
            try {
                this.jbInit();
            }
            catch (Exception ex) {
                ex.printStackTrace();
            }
            this.progress.setMinimum(0);
            this.progress.setMaximum((int)this.filesize);
            this.eirc.sendMessage("PRIVMSG", this.buildResponse());
            this.eirc.getFileliste().addFileReciever(this);
            this.eirc.getFileliste().setVisible(true);
            try {
                final Socket socket = new Socket(InetAddress.getByName("java.chat-land.org").getHostAddress(), 1228);
                final PrintWriter printWriter = new PrintWriter(new BufferedWriter(new OutputStreamWriter(socket.getOutputStream())), true);
                printWriter.println(this.filename.toLowerCase() + " " + this.target.toLowerCase() + " " + this.eirc.getNick().toLowerCase());
                printWriter.flush();
                new ReceivingFile(this, this.eirc, socket).start();
            }
            catch (Exception ex2) {
                this.notifyErrorRecieve();
            }
        }
    }
}
