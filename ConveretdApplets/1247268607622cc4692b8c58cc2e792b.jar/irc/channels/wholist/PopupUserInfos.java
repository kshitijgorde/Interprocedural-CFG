// 
// Decompiled by Procyon v0.5.30
// 

package irc.channels.wholist;

import java.awt.Cursor;
import java.awt.Toolkit;
import java.awt.BorderLayout;
import javax.swing.JFrame;
import java.awt.event.MouseEvent;
import java.awt.event.ActionEvent;
import javax.swing.JToolBar;
import java.awt.GridBagConstraints;
import java.awt.LayoutManager;
import irc.channels.components.MyJButton;
import java.awt.GridBagLayout;
import javax.swing.JLabel;
import java.awt.event.MouseListener;
import java.awt.event.ActionListener;
import irc.channels.components.Myjpanel;
import java.awt.Image;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import irc.managers.avatar;
import irc.com.nick.NickInfos;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import javax.swing.JPopupMenu;

public class PopupUserInfos extends JPopupMenu
{
    String nick;
    WhoListPanel who;
    public InfosContainer infos;
    int selectedindex;
    
    public PopupUserInfos(final WhoListPanel who, final int selectedindex) {
        this.who = who;
        this.selectedindex = selectedindex;
        this.setBackground(who.getBackground());
        this.infos = new InfosContainer();
        this.setPreferredSize(new Dimension(170, 220));
        this.add(this.infos);
        this.pack();
    }
    
    public void colorForeground(final Color color) {
        this.infos.lbl_age.setForeground(color);
        this.infos.lbl_dept.setForeground(color);
        this.infos.lbl_nick.setForeground(color);
        this.infos.nick.setForeground(color);
        this.infos.dept.setForeground(color);
        this.infos.age.setForeground(color);
    }
    
    public void setNick(final String nick) {
        this.nick = nick;
        this.remove(this.infos);
        this.infos = new InfosContainer();
        this.infos.nick.setText(this.nick);
        this.infos.dept.setText(NickInfos.getLocation(this.nick));
        this.infos.age.setText("" + NickInfos.getAge(this.nick));
        if (this.who.eirc.isRegister()) {
            if (NickInfos.isRegister(this.nick)) {
                final Image avatar = NickInfos.getAvatar(this.nick);
                if (avatar == null) {
                    this.infos.image.setIcon(new ImageIcon(irc.managers.avatar.avatarloading));
                    new Thread() {
                        @Override
                        public void run() {
                            PopupUserInfos.this.infos.image.setIcon(new ImageIcon(irc.managers.avatar.getAvatar(PopupUserInfos.this.nick)));
                        }
                    }.start();
                }
                else {
                    this.infos.image.setIcon(new ImageIcon(avatar));
                }
            }
            else if (NickInfos.getSex(this.nick) == 1) {
                this.infos.image.setIcon(new ImageIcon(avatar.avatarInconnuH));
            }
            else {
                this.infos.image.setIcon(new ImageIcon(avatar.avatarInconnuF));
            }
        }
        else {
            this.infos.image.setIcon(new ImageIcon(avatar.avatarnonvip));
        }
        this.add(this.infos);
    }
    
    public class InfosContainer extends Myjpanel implements ActionListener, MouseListener
    {
        public JLabel image;
        public JLabel lbl_nick;
        public JLabel nick;
        public JLabel lbl_dept;
        public JLabel dept;
        public JLabel lbl_age;
        public JLabel age;
        GridBagLayout layout;
        public MyJButton openprivate;
        
        InfosContainer() {
            this.setLayout(this.layout = new GridBagLayout());
            this.addMouseListener(this);
            this.setOpaque(false);
            final GridBagConstraints gridBagConstraints = new GridBagConstraints();
            gridBagConstraints.anchor = 19;
            final JToolBar toolBar = new JToolBar();
            toolBar.setOpaque(false);
            toolBar.setFloatable(false);
            this.openprivate = new MyJButton(avatar.MybackGround, "Ouvrir une discussion priv\u00e9e", null, 0);
            this.image = new JLabel();
            this.lbl_nick = new JLabel("Pseudo:  ");
            this.nick = new JLabel("Testpseudo");
            this.lbl_age = new JLabel("\u00c2ge:  ");
            this.age = new JLabel("test dept");
            this.lbl_dept = new JLabel("D\u00e9partement:  ");
            this.dept = new JLabel("test dept");
            this.openprivate.addMouseListener(this);
            toolBar.add(this.openprivate);
            gridBagConstraints.fill = 11;
            gridBagConstraints.anchor = 19;
            gridBagConstraints.gridx = 0;
            gridBagConstraints.gridy = 0;
            gridBagConstraints.gridwidth = 2;
            this.add(toolBar, gridBagConstraints);
            this.image.setIcon(new ImageIcon(avatar.avatarInconnuH));
            this.image.setBackground(Color.white);
            this.image.addMouseListener(this);
            gridBagConstraints.fill = 10;
            gridBagConstraints.anchor = 19;
            gridBagConstraints.gridx = 0;
            gridBagConstraints.gridy = 1;
            gridBagConstraints.gridwidth = 2;
            this.add(this.image, gridBagConstraints);
            gridBagConstraints.fill = 2;
            gridBagConstraints.gridx = 0;
            gridBagConstraints.gridy = 2;
            gridBagConstraints.gridwidth = 2;
            this.add(new JLabel(" "), gridBagConstraints);
            this.lbl_nick.setHorizontalAlignment(4);
            this.lbl_nick.setForeground(Color.blue);
            gridBagConstraints.fill = 2;
            gridBagConstraints.gridx = 0;
            gridBagConstraints.gridy = 3;
            gridBagConstraints.gridwidth = 1;
            this.add(this.lbl_nick, gridBagConstraints);
            gridBagConstraints.fill = 2;
            gridBagConstraints.gridx = 1;
            gridBagConstraints.gridy = 3;
            gridBagConstraints.gridwidth = 1;
            this.add(this.nick, gridBagConstraints);
            this.lbl_age.setHorizontalAlignment(4);
            this.lbl_age.setForeground(Color.blue);
            gridBagConstraints.fill = 2;
            gridBagConstraints.gridx = 0;
            gridBagConstraints.gridy = 4;
            gridBagConstraints.gridwidth = 1;
            this.add(this.lbl_age, gridBagConstraints);
            gridBagConstraints.fill = 2;
            gridBagConstraints.gridx = 1;
            gridBagConstraints.gridy = 4;
            gridBagConstraints.gridwidth = 1;
            this.add(this.age, gridBagConstraints);
            this.lbl_dept.setHorizontalAlignment(4);
            this.lbl_dept.setForeground(Color.blue);
            gridBagConstraints.fill = 2;
            gridBagConstraints.gridx = 0;
            gridBagConstraints.gridy = 5;
            gridBagConstraints.gridwidth = 1;
            this.add(this.lbl_dept, gridBagConstraints);
            gridBagConstraints.fill = 2;
            gridBagConstraints.gridx = 1;
            gridBagConstraints.gridy = 5;
            gridBagConstraints.gridwidth = 1;
            this.add(this.dept, gridBagConstraints);
            final JLabel label = new JLabel(" ");
            gridBagConstraints.fill = 2;
            gridBagConstraints.gridx = 0;
            gridBagConstraints.gridy = 6;
            gridBagConstraints.gridwidth = 2;
            this.add(label, gridBagConstraints);
            this.setBackground(Color.decode("0xADD8E6"));
        }
        
        @Override
        public void actionPerformed(final ActionEvent actionEvent) {
        }
        
        @Override
        public void mouseClicked(final MouseEvent mouseEvent) {
            if (mouseEvent.getSource().equals(this.nick) && NickInfos.getBigAvatar(this.nick.getText()) != null) {
                final Image bigAvatar = NickInfos.getBigAvatar(this.nick.getText());
                final JFrame frame = new JFrame();
                frame.getContentPane().setLayout(new BorderLayout());
                final JLabel label = new JLabel(new ImageIcon(bigAvatar));
                frame.getContentPane().add(label, "Center");
                if (label.getWidth() > 0 && label.getHeight() > 0) {
                    frame.setSize(label.getWidth(), label.getHeight());
                }
                else {
                    frame.setSize(50, 50);
                }
                frame.setDefaultCloseOperation(2);
                final Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
                final Dimension size = frame.getSize();
                if (size.height > screenSize.height) {
                    size.height = screenSize.height;
                }
                if (size.width > screenSize.width) {
                    size.width = screenSize.width;
                }
                frame.setLocation((screenSize.width - size.width) / 3, (screenSize.height - size.height) / 3);
                frame.pack();
                frame.setVisible(true);
                PopupUserInfos.this.setVisible(false);
            }
        }
        
        @Override
        public void mouseEntered(final MouseEvent mouseEvent) {
            if (mouseEvent.getSource().equals(this.image)) {
                if (NickInfos.getBigAvatar(this.nick.getText()) != null) {
                    this.image.setCursor(Cursor.getPredefinedCursor(12));
                }
            }
            else {
                PopupUserInfos.this.who.list.setSelectedIndex(PopupUserInfos.this.selectedindex);
            }
        }
        
        @Override
        public void mouseExited(final MouseEvent mouseEvent) {
            this.image.setCursor(Cursor.getPredefinedCursor(0));
            this.setCursor(Cursor.getPredefinedCursor(0));
        }
        
        @Override
        public void mousePressed(final MouseEvent mouseEvent) {
            if (mouseEvent.getSource().equals(this.openprivate)) {
                PopupUserInfos.this.who.handleNick(this.nick.getText());
                PopupUserInfos.this.setVisible(false);
            }
        }
        
        @Override
        public void mouseReleased(final MouseEvent mouseEvent) {
        }
    }
}
