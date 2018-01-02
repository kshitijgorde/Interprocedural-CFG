// 
// Decompiled by Procyon v0.5.30
// 

package ytdfriends.ui;

import edu.berkeley.guir.prefuse.graph.Node;
import java.util.ArrayList;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.applet.AppletContext;
import java.net.URLEncoder;
import edu.berkeley.guir.prefuse.graph.Entity;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import edu.berkeley.guir.prefuse.FocusManager;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import javax.swing.border.EmptyBorder;
import java.awt.Image;
import java.awt.MediaTracker;
import javax.imageio.ImageIO;
import java.net.URL;
import java.awt.Toolkit;
import javax.swing.Icon;
import javax.swing.border.Border;
import java.awt.Component;
import javax.swing.BorderFactory;
import java.awt.Color;
import java.awt.LayoutManager;
import java.awt.BorderLayout;
import edu.berkeley.guir.prefuse.focus.FocusSet;
import edu.berkeley.guir.prefuse.NodeItem;
import ytdfriends.FriendsPanel;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class ProfileCard extends JPanel
{
    private JLabel name;
    private JLabel cardTitle;
    private JLabel cardDesc;
    private ImageIcon nopic;
    private JLabel photoL;
    private JPanel rightPanel;
    private ImageIcon expandIcon;
    private ImageIcon prevIcon;
    private ImageIcon nextIcon;
    private ImageIcon shrinkIcon;
    private JLabel expand;
    private JLabel prev;
    private JLabel curPage;
    private JLabel totalPage;
    private JLabel next;
    private boolean expanded;
    private ImageIcon closeIcon;
    private JLabel close;
    private JPanel buttonsPanel;
    private JPanel controls;
    private JLabel view;
    private JPanel viewPanel;
    private static final String EXPAND = "Expand";
    private static final String PREV = "Prev 100";
    private static final String NEXT = "Next 100";
    private static final String SHRINK = "Shrink";
    private static final String CLOSE = "Close";
    private static final String VIEW_PROFILE = "<html><u>See Profile</u></html>";
    private FriendsPanel fPanel;
    private ProfileCard self;
    private NodeItem current;
    private FocusSet clickedSet;
    private Thread t;
    private boolean running;
    private boolean expandable;
    private JPanel infosPanel;
    
    public ProfileCard(final FriendsPanel fPanel) {
        this.curPage = new JLabel("1");
        this.totalPage = new JLabel("");
        this.expanded = false;
        this.view = new JLabel("<html><u>See Profile</u></html>");
        this.current = null;
        this.t = null;
        this.running = false;
        this.expandable = false;
        this.self = this;
        this.fPanel = fPanel;
        this.initUI();
    }
    
    private void initUI() {
        this.setLayout(new BorderLayout());
        this.initPhoto();
        this.initRightPanel();
        this.setForeground(Color.black);
        this.setBackground(Color.white);
        final Border b = BorderFactory.createLineBorder(new Color(201, 215, 241));
        this.setBorder(b);
        this.add(this.photoL, "West");
        this.add(this.rightPanel, "Center");
        this.setSize(300, 120);
    }
    
    public void loadNode(final NodeItem node) {
        if (this.current != node) {
            this.current = node;
            this.setVisible(false);
            this.loadPhoto();
            this.loadButtons();
            String nameString = node.getAttribute("name");
            if (nameString.length() > 24) {
                nameString = nameString.substring(0, 24);
                nameString = String.valueOf(nameString) + "...";
            }
            this.name.setText("<html>" + nameString + "</html>");
            this.cardTitle.setText((node.getAttribute("cardTitle") != null) ? ("<html>" + node.getAttribute("cardTitle") + "</html>") : " ");
            this.cardDesc.setText((node.getAttribute("cardDesc") != null) ? ("<html>" + node.getAttribute("cardDesc") + "</html>") : " ");
            this.setPosition();
        }
    }
    
    private void loadButtons() {
        this.expandable = false;
        if (this.clickedSet.contains(this.current.getEntity())) {
            this.expanded = true;
            if (!this.current.getAttribute("uid").matches(this.fPanel.initial.getAttribute("uid"))) {
                this.expand.setIcon(this.shrinkIcon);
                this.expand.setToolTipText("Shrink");
                this.expandable = true;
            }
        }
        else {
            this.expand.setIcon(this.expandIcon);
            this.expand.setToolTipText("Expand");
            this.expanded = false;
            this.expandable = true;
        }
        this.expand.setVisible(this.expandable);
        boolean nextable = false;
        if (this.expanded) {
            final int nfriends = Integer.valueOf(this.current.getAttribute("nfriends"));
            if (nfriends > 100) {
                final int page = (this.current.getAttribute("page") == null) ? 0 : Integer.valueOf(this.current.getAttribute("page"));
                this.curPage.setText(String.valueOf(page + 1));
                this.totalPage.setText("/ " + String.valueOf(nfriends / 100 + ((nfriends % 100 > 0) ? 1 : 0)));
                nextable = true;
            }
            else {
                nextable = false;
            }
        }
        this.prev.setVisible(nextable);
        this.curPage.setVisible(nextable);
        this.totalPage.setVisible(nextable);
        this.next.setVisible(nextable);
    }
    
    private void initPhoto() {
        (this.photoL = new JLabel()).setVerticalAlignment(1);
        try {
            this.nopic = new ImageIcon(Toolkit.getDefaultToolkit().getImage(new URL(String.valueOf(this.fPanel.baseUrl) + "images/profil/nopic.png")).getScaledInstance(100, 120, 4));
        }
        catch (Exception e1) {
            e1.printStackTrace();
        }
        this.photoL.setSize(50, 120);
        this.photoL.setIcon(this.nopic);
    }
    
    private void loadPhoto() {
        this.photoL.setIcon(this.nopic);
        if (this.t != null || this.running) {
            this.t.interrupt();
            this.t = null;
            this.running = false;
        }
        (this.t = new Thread(new Runnable() {
            public void run() {
                try {
                    final String photoUrl = ProfileCard.this.current.getAttribute("photo");
                    if (photoUrl.length() > 0) {
                        Image img = ImageIO.read(new URL(photoUrl));
                        final MediaTracker mt = new MediaTracker(ProfileCard.this.self);
                        mt.addImage(img, 0);
                        mt.waitForID(0);
                        while (mt.statusID(0, false) == 1) {}
                        if (mt.statusID(0, false) == 8 && img != null) {
                            img = img.getScaledInstance(100, 120, 4);
                            ProfileCard.this.photoL.setIcon(new ImageIcon(img));
                        }
                    }
                }
                catch (Exception ex) {
                    ex.printStackTrace();
                }
                ProfileCard.access$3(ProfileCard.this, false);
                ProfileCard.access$4(ProfileCard.this, null);
            }
        })).start();
        this.running = true;
    }
    
    private void initRightPanel() {
        try {
            this.expandIcon = new ImageIcon(ImageIO.read(new URL(String.valueOf(this.fPanel.baseUrl) + "images/plus.png")));
            this.prevIcon = new ImageIcon(ImageIO.read(new URL(String.valueOf(this.fPanel.baseUrl) + "images/refresh_reversed.png")));
            this.nextIcon = new ImageIcon(ImageIO.read(new URL(String.valueOf(this.fPanel.baseUrl) + "images/refresh.png")));
            this.shrinkIcon = new ImageIcon(ImageIO.read(new URL(String.valueOf(this.fPanel.baseUrl) + "images/minus.png")));
            this.closeIcon = new ImageIcon(ImageIO.read(new URL(String.valueOf(this.fPanel.baseUrl) + "images/close.png")));
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        final Border margin = new EmptyBorder(2, 4, 2, 4);
        (this.name = new JLabel()).setFont(new Font("Tahoma", 1, 13));
        this.name.setPreferredSize(new Dimension(250, 14));
        this.name.setBorder(margin);
        (this.cardTitle = new JLabel()).setFont(new Font("Tahoma", 0, 10));
        this.cardTitle.setPreferredSize(new Dimension(250, 10));
        this.cardTitle.setHorizontalAlignment(4);
        (this.cardDesc = new JLabel()).setFont(new Font("Tahoma", 0, 10));
        this.cardDesc.setPreferredSize(new Dimension(250, 30));
        this.expand = new JLabel();
        this.prev = new JLabel(this.prevIcon);
        this.next = new JLabel(this.nextIcon);
        this.close = new JLabel(this.closeIcon);
        this.prev.setToolTipText("Prev 100");
        this.next.setToolTipText("Next 100");
        this.close.setToolTipText("Close");
        final FocusManager fmanager = this.fPanel.registry.getFocusManager();
        this.clickedSet = fmanager.getFocusSet((Object)"default");
        this.initListeners();
        this.view.setFont(new Font("Tahoma", 0, 13));
        this.view.setForeground(Color.BLUE);
        this.view.setSize(100, 30);
        this.view.setCursor(Cursor.getPredefinedCursor(12));
        this.view.setBackground(new Color(235, 239, 249));
        (this.viewPanel = new JPanel()).add(this.view);
        this.viewPanel.setBackground(new Color(235, 239, 249));
        (this.buttonsPanel = new JPanel()).add(this.prev);
        this.buttonsPanel.add(this.curPage);
        this.buttonsPanel.add(this.totalPage);
        this.buttonsPanel.add(this.next);
        this.buttonsPanel.add(this.expand);
        this.buttonsPanel.add(this.close);
        this.buttonsPanel.setCursor(Cursor.getPredefinedCursor(12));
        this.buttonsPanel.setBackground(new Color(235, 239, 249));
        (this.controls = new JPanel(new BorderLayout())).add(this.viewPanel, "West");
        this.controls.add(this.buttonsPanel, "East");
        this.controls.setBackground(new Color(235, 239, 249));
        (this.infosPanel = new JPanel(new BorderLayout())).add(this.cardTitle, "North");
        this.infosPanel.add(this.cardDesc, "Center");
        this.infosPanel.setForeground(Color.gray);
        this.infosPanel.setBackground(Color.white);
        this.infosPanel.setBorder(margin);
        this.infosPanel.setSize(250, 90);
        (this.rightPanel = new JPanel(new BorderLayout())).setForeground(Color.black);
        this.rightPanel.setBackground(Color.white);
        this.rightPanel.setSize(250, 120);
        this.rightPanel.add(this.name, "North");
        this.rightPanel.add(this.infosPanel, "Center");
        this.rightPanel.add(this.controls, "South");
    }
    
    private void initListeners() {
        if (this.expand != null) {
            this.expand.addMouseListener(new MouseListener() {
                public void mouseReleased(final MouseEvent arg0) {
                }
                
                public void mousePressed(final MouseEvent arg0) {
                    final Entity entity = ProfileCard.this.current.getEntity();
                    ProfileCard.this.expand(entity);
                }
                
                public void mouseExited(final MouseEvent arg0) {
                }
                
                public void mouseEntered(final MouseEvent arg0) {
                }
                
                public void mouseClicked(final MouseEvent arg0) {
                }
            });
        }
        this.view.addMouseListener(new MouseListener() {
            public void mouseReleased(final MouseEvent arg0) {
            }
            
            public void mousePressed(final MouseEvent arg0) {
                try {
                    final AppletContext a = ProfileCard.this.fPanel.fApplet.getAppletContext();
                    a.showDocument(new URL(String.valueOf(ProfileCard.this.fPanel.baseUrl) + "profil/?q=\"" + URLEncoder.encode(ProfileCard.this.current.getAttribute("name"), "UTF-8") + "\"&uid=" + ProfileCard.this.current.getAttribute("uid") + "&c=normal"), "_blank");
                }
                catch (Exception e1) {
                    e1.printStackTrace();
                }
            }
            
            public void mouseExited(final MouseEvent arg0) {
            }
            
            public void mouseEntered(final MouseEvent arg0) {
            }
            
            public void mouseClicked(final MouseEvent arg0) {
            }
        });
        this.close.addMouseListener(new MouseListener() {
            public void mouseReleased(final MouseEvent arg0) {
            }
            
            public void mousePressed(final MouseEvent arg0) {
                ProfileCard.this.self.setVisible(false);
            }
            
            public void mouseExited(final MouseEvent arg0) {
            }
            
            public void mouseEntered(final MouseEvent arg0) {
            }
            
            public void mouseClicked(final MouseEvent arg0) {
            }
        });
        this.prev.addMouseListener(new MouseListener() {
            public void mouseReleased(final MouseEvent arg0) {
            }
            
            public void mousePressed(final MouseEvent arg0) {
                ProfileCard.this.changePage("prev");
            }
            
            public void mouseExited(final MouseEvent arg0) {
            }
            
            public void mouseEntered(final MouseEvent arg0) {
            }
            
            public void mouseClicked(final MouseEvent arg0) {
            }
        });
        this.next.addMouseListener(new MouseListener() {
            public void mouseReleased(final MouseEvent arg0) {
            }
            
            public void mousePressed(final MouseEvent arg0) {
                ProfileCard.this.changePage("next");
            }
            
            public void mouseExited(final MouseEvent arg0) {
            }
            
            public void mouseEntered(final MouseEvent arg0) {
            }
            
            public void mouseClicked(final MouseEvent arg0) {
            }
        });
        this.fPanel.getDisplay().addComponentListener((ComponentListener)new ComponentListener() {
            public void componentShown(final ComponentEvent arg0) {
            }
            
            public void componentResized(final ComponentEvent arg0) {
                ProfileCard.this.self.setPosition();
            }
            
            public void componentMoved(final ComponentEvent arg0) {
            }
            
            public void componentHidden(final ComponentEvent arg0) {
            }
        });
    }
    
    public void expand(final Entity entity) {
        if (this.expandable) {
            if (this.expanded) {
                this.expand.setIcon(this.expandIcon);
                this.expand.setToolTipText("Expand");
            }
            else {
                if (entity.getAttribute("expandedOnce") == null) {
                    String pageS = entity.getAttribute("page");
                    if (pageS == null) {
                        pageS = "0";
                    }
                    final int page = Integer.valueOf(pageS);
                    this.fPanel.loadFriendGraph(entity.getAttribute("uid"), true, null, null, page);
                    entity.setAttribute("expandedOnce", "1");
                }
                this.expand.setIcon(this.shrinkIcon);
                this.expand.setToolTipText("Shrink");
            }
            if (this.clickedSet.contains(entity)) {
                if (this.clickedSet.size() > 1) {
                    this.clickedSet.remove(entity);
                }
            }
            else {
                this.clickedSet.add(entity);
            }
            this.fPanel.registry.touch("node");
            this.expanded = !this.expanded;
            if (this.fPanel.commPanel.enableButton.getText() == "Disable") {
                this.fPanel.constructCommunities(-1);
            }
            if (this.fPanel.searchPanel.getQuery() != null) {
                this.fPanel.searchPanel.reload();
            }
            this.loadButtons();
        }
    }
    
    private void changePage(final String option) {
        final int nfriends = Integer.valueOf(this.current.getAttribute("nfriends"));
        final int maxPage = nfriends / 100 + ((nfriends % 100 > 0) ? 1 : 0);
        int page = (this.current.getAttribute("page") == null) ? 0 : Integer.valueOf(this.current.getAttribute("page"));
        if (option == "next") {
            if (++page == maxPage) {
                page = 0;
            }
        }
        else if (option == "prev" && --page == -1) {
            page = maxPage - 1;
        }
        this.fPanel.loadFriendGraph(this.current.getAttribute("uid"), true, null, null, page);
        this.current.setAttribute("page", String.valueOf(page));
        this.curPage.setText(String.valueOf(page + 1));
        if (this.fPanel.searchPanel.getQuery() != null) {
            this.fPanel.searchPanel.reload();
        }
    }
    
    private void setPosition() {
        this.setLocation(this.fPanel.getDisplay().getWidth() - this.getWidth() - 10, 10);
    }
    
    public void setExpand() {
    }
    
    static /* synthetic */ void access$3(final ProfileCard profileCard, final boolean running) {
        profileCard.running = running;
    }
    
    static /* synthetic */ void access$4(final ProfileCard profileCard, final Thread t) {
        profileCard.t = t;
    }
}
