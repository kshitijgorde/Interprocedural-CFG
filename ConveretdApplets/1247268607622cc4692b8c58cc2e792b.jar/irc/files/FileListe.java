// 
// Decompiled by Procyon v0.5.30
// 

package irc.files;

import java.awt.Component;
import java.awt.BorderLayout;
import java.awt.Insets;
import java.awt.LayoutManager;
import java.awt.GridBagLayout;
import irc.managers.Resources;
import java.awt.GridBagConstraints;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import java.awt.GridLayout;
import javax.swing.JFrame;

public class FileListe extends JFrame
{
    GridLayout flowlayout;
    JScrollPane scoll;
    JPanel cp;
    GridBagConstraints gbc;
    
    public FileListe() {
        this.flowlayout = new GridLayout(20, 1);
        this.scoll = new JScrollPane();
        this.gbc = new GridBagConstraints();
        this.setTitle("Liste des tansferts de fichiers");
        this.setIconImage(Resources.getImages("minlogoa"));
        this.cp = new JPanel();
        this.setSize(380, 400);
        this.setResizable(false);
        this.cp.setLayout(new GridBagLayout());
        this.gbc.gridx = 0;
        this.gbc.gridy = -1;
        this.gbc.fill = 2;
        this.gbc.insets = new Insets(0, 0, 0, 0);
        final JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());
        panel.add(this.cp, "North");
        this.scoll.getViewport().add(panel);
        this.scoll.setVerticalScrollBarPolicy(22);
        this.scoll.setHorizontalScrollBarPolicy(32);
        this.getContentPane().add(this.scoll);
    }
    
    public void addFileReciever(final FileReciever fileReciever) {
        this.cp.add(fileReciever, this.gbc);
    }
    
    public void addFileSender(final FileSender fileSender) {
        this.cp.add(fileSender, this.gbc);
    }
}
