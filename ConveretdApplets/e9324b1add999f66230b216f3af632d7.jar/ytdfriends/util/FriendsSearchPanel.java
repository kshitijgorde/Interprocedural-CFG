// 
// Decompiled by Procyon v0.5.30
// 

package ytdfriends.util;

import java.util.Iterator;
import edu.berkeley.guir.prefuse.focus.FocusSet;
import edu.berkeley.guir.prefuse.FocusManager;
import edu.berkeley.guir.prefuse.ItemRegistry;
import edu.berkeley.guir.prefuse.graph.Entity;
import edu.berkeley.guir.prefuse.graph.Node;
import java.awt.Color;
import java.awt.Component;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.Icon;
import java.awt.Image;
import javax.swing.ImageIcon;
import java.io.IOException;
import java.net.MalformedURLException;
import javax.imageio.ImageIO;
import java.net.URL;
import java.awt.Cursor;
import java.awt.image.BufferedImage;
import javax.swing.JTextField;
import javax.swing.JLabel;
import ytdfriends.FriendsPanel;
import javax.swing.JPanel;

public class FriendsSearchPanel extends JPanel
{
    private FriendsPanel fPanel;
    private JLabel label;
    private JTextField field;
    private JLabel erase;
    private BufferedImage eraseImage;
    
    public FriendsSearchPanel(final FriendsPanel fPanel) {
        this.fPanel = fPanel;
        this.label = new JLabel("Look for: ");
        this.field = new JTextField();
        (this.erase = new JLabel()).setCursor(Cursor.getPredefinedCursor(12));
        try {
            this.eraseImage = ImageIO.read(new URL(String.valueOf(fPanel.baseUrl) + "images/erase.png"));
        }
        catch (MalformedURLException e1) {
            e1.printStackTrace();
        }
        catch (IOException e2) {
            e2.printStackTrace();
        }
        this.erase.setIcon(new ImageIcon(this.eraseImage));
        this.field.setColumns(15);
        this.field.addKeyListener(new KeyListener() {
            public void keyTyped(final KeyEvent e) {
            }
            
            public void keyReleased(final KeyEvent e) {
                fPanel.search(FriendsSearchPanel.this.field.getText());
            }
            
            public void keyPressed(final KeyEvent e) {
            }
        });
        this.erase.addMouseListener(new MouseListener() {
            public void mouseReleased(final MouseEvent e) {
            }
            
            public void mousePressed(final MouseEvent e) {
                FriendsSearchPanel.this.field.setText("");
                FriendsSearchPanel.this.setQuery("");
            }
            
            public void mouseExited(final MouseEvent e) {
            }
            
            public void mouseEntered(final MouseEvent e) {
            }
            
            public void mouseClicked(final MouseEvent e) {
            }
        });
        this.add(this.label);
        this.add(this.field);
        this.add(this.erase);
        this.setBackground(Color.WHITE);
    }
    
    public String getQuery() {
        return this.field.getText();
    }
    
    public void setQuery(String query) {
        query = query.trim();
        final ItemRegistry registry = this.fPanel.registry;
        registry.getGraph();
        final FocusManager fm = registry.getFocusManager();
        final FocusSet fs = fm.getFocusSet((Object)"default");
        final FocusSet ss = fm.getFocusSet((Object)"search");
        ss.clear();
        if (query.length() > 0) {
            for (final Node n : fs) {
                if ((n.getAttribute("name").matches(".*(?i)" + query + ".*") || (n.getAttribute("companies") != null && n.getAttribute("companies").matches(".*(?i)" + query + ".*")) || (n.getAttribute("institutions") != null && n.getAttribute("institutions").matches(".*(?i)" + query + ".*"))) && !ss.contains((Entity)n)) {
                    ss.add((Entity)n);
                }
                final Iterator<Node> niter = (Iterator<Node>)n.getNeighbors();
                while (niter.hasNext()) {
                    final Node nn = niter.next();
                    if ((nn.getAttribute("name").matches(".*(?i)" + query + ".*") || (nn.getAttribute("companies") != null && nn.getAttribute("companies").matches(".*(?i)" + query + ".*")) || (nn.getAttribute("institutions") != null && nn.getAttribute("institutions").matches(".*(?i)" + query + ".*"))) && !ss.contains((Entity)nn)) {
                        ss.add((Entity)nn);
                    }
                }
            }
        }
    }
    
    public void reload() {
        this.setQuery(this.field.getText());
    }
}
