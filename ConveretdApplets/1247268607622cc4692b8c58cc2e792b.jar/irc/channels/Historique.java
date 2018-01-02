// 
// Decompiled by Procyon v0.5.30
// 

package irc.channels;

import java.awt.Color;
import java.util.Vector;
import java.awt.event.ActionEvent;
import javax.swing.ListCellRenderer;
import javax.swing.DefaultListCellRenderer;
import javax.swing.JLabel;
import java.awt.Component;
import java.awt.LayoutManager;
import java.awt.GridLayout;
import irc.EIRC;
import javax.swing.JPanel;
import javax.swing.JCheckBox;
import irc.Creation;
import javax.swing.JButton;
import javax.swing.JList;
import javax.swing.JScrollPane;
import java.awt.BorderLayout;
import java.awt.event.ActionListener;
import javax.swing.JFrame;

public class Historique extends JFrame implements ActionListener
{
    private BorderLayout layout;
    private String name;
    private JScrollPane scroll;
    private JList list;
    private JButton boutton;
    private JButton effacer;
    private Creation creation;
    public JCheckBox historique;
    private JPanel panel;
    private EIRC eirc;
    
    public Historique(final EIRC eirc) {
        this.panel = new JPanel(new GridLayout(1, 3));
        this.eirc = eirc;
        this.layout = new BorderLayout();
        this.scroll = new JScrollPane();
        this.list = new JList();
        this.boutton = new JButton("Fermer");
        this.effacer = new JButton("Effacer");
        this.historique = new JCheckBox("Activer l'historique de conversation");
        this.scroll.getViewport().add(this.list);
        this.getContentPane().setLayout(this.layout);
        this.panel.add(new JLabel());
        this.panel.add(this.boutton);
        this.panel.add(new JLabel());
        this.panel.add(this.effacer);
        this.panel.add(new JLabel());
        this.getContentPane().add(this.scroll, "Center");
        this.getContentPane().add(this.panel, "South");
        this.getContentPane().add(this.historique, "North");
        this.setSize(500, 500);
        this.boutton.addActionListener(this);
        this.effacer.addActionListener(this);
        this.list.setCellRenderer(new DefaultListCellRenderer() {
            @Override
            public Component getListCellRendererComponent(final JList list, final Object o, final int n, final boolean b, final boolean b2) {
                super.getListCellRendererComponent(list, o, n, b, b2);
                if (n % 2 == 0) {
                    this.setBackground(EIRC.mainbg);
                }
                return this;
            }
        });
        this.setBackground(EIRC.mainbg);
        this.panel.setBackground(EIRC.mainbg);
        this.historique.setBackground(EIRC.mainbg);
        this.panel.setBackground(EIRC.mainbg);
        this.getContentPane().setBackground(EIRC.mainbg);
        this.scroll.setOpaque(false);
        this.scroll.getViewport().setOpaque(false);
        this.historique.setSelected(eirc.actif_historique);
        this.historique.addActionListener(this);
    }
    
    @Override
    public void actionPerformed(final ActionEvent actionEvent) {
        if (actionEvent.getSource().equals(this.historique)) {
            this.eirc.actif_historique = this.historique.isSelected();
            return;
        }
        if (actionEvent.getSource().equals(this.boutton)) {
            this.dispose();
        }
        if (actionEvent.getSource().equals(this.effacer)) {
            this.creation.effacer(this.name);
            this.list.setListData(new Vector());
        }
    }
    
    public void colorBackground(final Color background) {
        this.setBackground(background);
        this.panel.setBackground(background);
        this.historique.setBackground(background);
        this.panel.setBackground(background);
        this.getContentPane().setBackground(background);
    }
    
    public void init(final String name, final Creation creation) {
        this.name = name;
        this.creation = creation;
        this.setTitle("Historique de conversation avec " + this.name);
        final Vector extraire = creation.extraire(name);
        final Vector<String> listData = new Vector<String>();
        for (int i = 0; i < extraire.size(); ++i) {
            listData.addElement(extraire.get(i).replaceFirst(":", "(").replaceFirst(";", ")"));
        }
        this.list.setListData(listData);
        this.historique.setSelected(this.eirc.actif_historique);
        this.setVisible(true);
    }
}
