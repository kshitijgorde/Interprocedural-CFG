// 
// Decompiled by Procyon v0.5.30
// 

package photostrip;

import java.awt.Component;
import java.awt.Color;
import java.awt.LayoutManager;
import java.awt.GridLayout;
import java.awt.Dimension;
import java.security.AccessControlException;
import java.io.IOException;
import javax.xml.xpath.XPathExpressionException;
import org.xml.sax.SAXException;
import javax.xml.parsers.ParserConfigurationException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.SwingUtilities;
import java.net.URL;
import java.util.List;
import javax.swing.JComponent;

public class PhotoStrip extends JComponent
{
    private String feedURL;
    private List<PhotoResource> photos;
    private int size;
    private String errorMessage;
    private URLShower shower;
    private int rows;
    private int cols;
    private Thread worker;
    
    public PhotoStrip(final String feedURL, final int cols, final int rows, final int size, final URLShower shower) {
        this.errorMessage = null;
        this.worker = new Thread(new Runnable() {
            public void run() {
                try {
                    final List<PhotoResource> photos = PhotoStream.parse(new URL(PhotoStrip.this.feedURL));
                    SwingUtilities.invokeLater(new Runnable() {
                        public void run() {
                            PhotoStrip.this.setPhotos(photos);
                        }
                    });
                }
                catch (ParserConfigurationException ex) {
                    Logger.getLogger(PhotoStrip.class.getName()).log(Level.SEVERE, null, ex);
                }
                catch (SAXException ex2) {
                    Logger.getLogger(PhotoStrip.class.getName()).log(Level.SEVERE, null, ex2);
                }
                catch (XPathExpressionException ex3) {
                    Logger.getLogger(PhotoStrip.class.getName()).log(Level.SEVERE, null, ex3);
                }
                catch (IOException ex4) {
                    Logger.getLogger(PhotoStrip.class.getName()).log(Level.SEVERE, null, ex4);
                }
                catch (AccessControlException ex5) {
                    PhotoStrip.this.p("couldn't access flickr. probably on an older jre");
                    PhotoStrip.this.setErrorMessage("Couldn't connect to Flickr. Access denied.", ex5);
                }
            }
        });
        this.shower = shower;
        this.feedURL = feedURL;
        this.size = size;
        this.rows = rows;
        this.cols = cols;
        this.setPreferredSize(new Dimension(cols * size, rows * size));
        this.setLayout(new GridLayout(rows, cols));
        this.setBackground(Color.BLACK);
        this.worker.start();
    }
    
    private void p(final String string) {
        System.out.println(string);
    }
    
    private void setPhotos(final List<PhotoResource> photos) {
        this.photos = photos;
        for (int i = 0; i < this.rows * this.cols && i < photos.size(); ++i) {
            final PhotoResource photo = photos.get(i);
            this.addPhoto(photo);
        }
        this.repaint();
    }
    
    private void addPhoto(final PhotoResource photo) {
        this.add(new PhotoView(photo, this.size, this.shower));
        this.repaint();
        this.revalidate();
    }
    
    private void setErrorMessage(final String string, final AccessControlException ex) {
        this.errorMessage = string;
        this.repaint();
    }
}
