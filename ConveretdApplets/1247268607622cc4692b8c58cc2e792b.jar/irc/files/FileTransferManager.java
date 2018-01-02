// 
// Decompiled by Procyon v0.5.30
// 

package irc.files;

import javax.swing.JFileChooser;
import java.util.StringTokenizer;
import javax.swing.ImageIcon;
import java.util.Hashtable;
import irc.EIRC;

public class FileTransferManager
{
    static EIRC eirc;
    static Hashtable filerecieve;
    static Hashtable filesend;
    public static final ImageIcon fileunknown;
    public static final ImageIcon filedoc;
    public static final ImageIcon filexls;
    public static final ImageIcon filearchive;
    public static final ImageIcon filepdf;
    public static final ImageIcon filemultimedia;
    public static final ImageIcon filebroken;
    public static final ImageIcon fileimage;
    
    public static void acceptFile(final String s, final String s2) {
        FileTransferManager.filerecieve.get(s + "+" + s2.toLowerCase()).ReceiveFile();
    }
    
    public static void addReciever(final String s, final String s2) {
        final StringTokenizer stringTokenizer = new StringTokenizer(s2.substring(s2.indexOf("]") + 1));
        final String trim = stringTokenizer.nextToken().trim();
        final FileReciever fileReciever = new FileReciever(FileTransferManager.eirc, trim, stringTokenizer.nextToken().trim(), s.toLowerCase());
        FileTransferManager.filerecieve.put(trim + "+" + s.toLowerCase(), fileReciever);
        FileTransferManager.eirc.getFileliste().addFileReciever(fileReciever);
        FileTransferManager.eirc.getFileliste().setVisible(true);
    }
    
    public static void addSender(final JFileChooser fileChooser, final String s) {
        final String name = fileChooser.getSelectedFile().getName();
        String string = "";
        for (int i = 0; i < name.length(); ++i) {
            if (name.charAt(i) != ' ' && name.charAt(i) != '-') {
                string += name.charAt(i);
            }
        }
        final FileSender fileSender = new FileSender(s, fileChooser.getSelectedFile().getName(), fileChooser.getSelectedFile().getPath(), fileChooser.getSelectedFile().length(), FileTransferManager.eirc);
        FileTransferManager.eirc.sendMessage("PRIVMSG", new String[] { s, "\u0001ACTION [SENDFILE]" + string + " " + fileChooser.getSelectedFile().length() + "\u0001" });
        FileTransferManager.filesend.put(string.toLowerCase() + "+" + s.toLowerCase(), fileSender);
        FileTransferManager.eirc.getFileliste().addFileSender(fileSender);
        FileTransferManager.eirc.getFileliste().setVisible(true);
    }
    
    public static void free() {
        FileTransferManager.filerecieve.clear();
        FileTransferManager.filesend.clear();
    }
    
    public static FileSender getSender(final String s) {
        return FileTransferManager.filesend.get(s);
    }
    
    public static void init(final EIRC eirc) {
        FileTransferManager.eirc = eirc;
    }
    
    public static void removeReciever(final String s, final String s2) {
        FileTransferManager.filerecieve.remove(s + "+" + s2.toLowerCase());
    }
    
    public static void removeSender(final String s) {
        FileTransferManager.filesend.remove(s);
    }
    
    public static void removeSender(final String s, final String s2) {
        FileTransferManager.filesend.remove(s.substring(s.indexOf("]") + 1) + "+" + s2.toLowerCase());
    }
    
    static {
        FileTransferManager.filerecieve = new Hashtable();
        FileTransferManager.filesend = new Hashtable();
        fileunknown = new ImageIcon(EIRC.class.getResource("gui/files/unknown.png"));
        filedoc = new ImageIcon(EIRC.class.getResource("gui/files/doc.png"));
        filexls = new ImageIcon(EIRC.class.getResource("gui/files/excel.png"));
        filearchive = new ImageIcon(EIRC.class.getResource("gui/files/archive.png"));
        filepdf = new ImageIcon(EIRC.class.getResource("gui/files/pdf.png"));
        filemultimedia = new ImageIcon(EIRC.class.getResource("gui/files/multimedia.png"));
        filebroken = new ImageIcon(EIRC.class.getResource("gui/files/broke.png"));
        fileimage = new ImageIcon(EIRC.class.getResource("gui/files/image.png"));
    }
}
