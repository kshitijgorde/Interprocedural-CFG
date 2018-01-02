// 
// Decompiled by Procyon v0.5.30
// 

package irc.files;

import java.io.IOException;
import java.io.BufferedInputStream;
import java.io.OutputStream;
import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.io.File;
import java.awt.Component;
import javax.swing.JOptionPane;
import irc.EIRC;
import java.net.Socket;

public class ReceivingFile extends Thread
{
    Socket client;
    long filesize;
    String ip;
    EIRC eirc;
    String save_file;
    public int port;
    FileReciever FileReciever;
    public int port1;
    String[] params;
    
    ReceivingFile(final FileReciever fileReciever, final EIRC eirc, final Socket client) {
        super("ReceivingFile");
        this.save_file = "";
        this.eirc = eirc;
        this.filesize = fileReciever.filesize;
        this.save_file = fileReciever.filepath;
        this.FileReciever = fileReciever;
        try {
            this.client = client;
        }
        catch (Exception ex) {
            JOptionPane.showMessageDialog(this.FileReciever, "Une erreur est survenue lors de la r\u00e9ception du fichier l'une des raison suivante peut en \u00eatre la cause:\n-Votre firewall bloque la r\u00e9c\u00e9ption du fichier\n-L'exp\u00e9diteur \u00e0 subit une d\u00e9connection.", "Envoi de fichier", 0);
        }
    }
    
    @Override
    public void run() {
        int value = 0;
        try {
            final BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(new FileOutputStream(new File(this.save_file)));
            final BufferedInputStream bufferedInputStream = new BufferedInputStream(this.client.getInputStream());
            final byte[] array = new byte[1024];
            int read;
            while ((read = bufferedInputStream.read(array)) != -1) {
                value += read;
                bufferedOutputStream.write(array, 0, read);
                bufferedOutputStream.flush();
                this.FileReciever.progress.setValue(value);
                if (value >= this.filesize) {
                    bufferedOutputStream.close();
                    bufferedInputStream.close();
                    this.client.close();
                    break;
                }
            }
        }
        catch (IOException ex) {
            this.FileReciever.notifyErrorRecieve();
        }
        if ((int)this.FileReciever.progress.getPercentComplete() == 1) {
            this.FileReciever.terminer = true;
            this.FileReciever.accept.setText("Lancer le fichier");
            this.FileReciever.accept.setVisible(true);
            this.FileReciever.accept.setEnabled(true);
            this.FileReciever.notifyFileRecieve();
        }
        else {
            this.FileReciever.notifyErrorRecieve();
        }
    }
}
