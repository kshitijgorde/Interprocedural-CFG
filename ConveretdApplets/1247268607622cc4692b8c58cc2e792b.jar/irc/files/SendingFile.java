// 
// Decompiled by Procyon v0.5.30
// 

package irc.files;

import java.io.IOException;
import java.io.BufferedOutputStream;
import java.io.InputStream;
import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.File;
import java.awt.Component;
import javax.swing.JOptionPane;
import java.net.Socket;

public class SendingFile extends Thread
{
    Socket client;
    String filename;
    String ip;
    String sender;
    int port;
    long filesize;
    public int port_sender;
    FileSender filetransfer;
    
    SendingFile(final FileSender filetransfer, final Socket client) {
        super("SendingFile");
        this.filesize = filetransfer.filesize;
        this.filename = filetransfer.filename;
        this.sender = filetransfer.target;
        this.filetransfer = filetransfer;
        try {
            this.client = client;
            this.start();
        }
        catch (Exception ex) {
            JOptionPane.showMessageDialog(this.filetransfer, "Une erreur est survenue lors de l'envoi l'une des raison possible:\n-Blocage du firewall du destinataire\n-Le destinataire s'est d\u00e9connecter lors de la r\u00e9ception du fichier", "Envoi de fichier", 0);
        }
    }
    
    @Override
    public void run() {
        int value = 0;
        try {
            final File file = new File(this.filetransfer.filepath);
            final BufferedInputStream bufferedInputStream = new BufferedInputStream(new FileInputStream(file));
            final BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(this.client.getOutputStream());
            final byte[] array = new byte[1024];
            int read;
            while ((read = bufferedInputStream.read(array)) != -1) {
                value += read;
                bufferedOutputStream.write(array, 0, read);
                bufferedOutputStream.flush();
                this.filetransfer.progress.setValue(value);
            }
            this.client.close();
            if (value == file.length()) {
                this.filetransfer.notifyEndTransfer();
            }
            else {
                this.filetransfer.notifyErrorTransfer();
            }
        }
        catch (IOException ex) {
            this.filetransfer.notifyErrorTransfer();
        }
    }
}
