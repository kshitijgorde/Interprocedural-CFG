import java.awt.event.MouseEvent;
import java.awt.Component;
import java.awt.Font;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import java.awt.image.ImageProducer;
import java.awt.image.ImageFilter;
import java.awt.image.FilteredImageSource;
import java.awt.image.CropImageFilter;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.LayoutManager;
import javax.swing.JLabel;
import java.awt.event.MouseListener;
import javax.swing.JPanel;

// 
// Decompiled by Procyon v0.5.30
// 

public class DesktopPanel extends JPanel implements MouseListener
{
    public static final int ICON_SIZE = 18;
    public static final String[] FILE_NAMES;
    private FileAddMethod _gui;
    private JLabel[] _labelIcons;
    
    public DesktopPanel(final FileAddMethod gui, final int x, final int y) {
        this._gui = gui;
        this.setLayout(null);
        this.setBounds(x, y, 100, 100);
        this.setMinimumSize(new Dimension(100, 100));
        this.setPreferredSize(new Dimension(100, 100));
        this.setBackground(new Color(123, 123, 123));
        this.setBorder(new LineBorder(Color.orange, 1));
        final ImageIcon icons = new ImageLoader().getImageIcon("images/icons.gif");
        if (icons == null) {
            return;
        }
        final int count = icons.getIconHeight() / 16;
        this._labelIcons = new JLabel[count];
        this.setSize(130, (count + 1) * 20);
        this.setMinimumSize(new Dimension(130, (count + 1) * 20));
        this.setMaximumSize(new Dimension(130, (count + 1) * 20));
        for (int i = 0; i < count; ++i) {
            final ImageIcon icon = new ImageIcon(this.createImage(new FilteredImageSource(icons.getImage().getSource(), new CropImageFilter(0, 16 * i, 16, 16))));
            (this._labelIcons[i] = new JLabel(" " + DesktopPanel.FILE_NAMES[i], icon, 4)).setBounds(10, 10 + i * 20, 200, 18);
            this._labelIcons[i].addMouseListener(this);
            this._labelIcons[i].setFont(new Font("InputDialog", 0, 12));
            this._labelIcons[i].setHorizontalAlignment(2);
            this._labelIcons[i].setHorizontalTextPosition(4);
            this._labelIcons[i].setVerticalAlignment(0);
            this._labelIcons[i].setVerticalTextPosition(0);
            this.add(this._labelIcons[i]);
        }
    }
    
    public void mouseClicked(final MouseEvent e) {
        int track = -1;
        for (int i = 0; i < this._labelIcons.length; ++i) {
            if (e.getSource().equals(this._labelIcons[i])) {
                track = i;
                break;
            }
        }
        if (track == -1) {
            return;
        }
        this._gui.addFile(new FileComponent((ImageIcon)this._labelIcons[track].getIcon(), track));
    }
    
    public void mouseEntered(final MouseEvent e) {
    }
    
    public void mouseExited(final MouseEvent e) {
    }
    
    public void mousePressed(final MouseEvent e) {
    }
    
    public void mouseReleased(final MouseEvent e) {
    }
    
    static {
        FILE_NAMES = new String[] { "datebase.mdb", "document.doc", "code.java", "picture.jpg", "movie.mpg", "portable.pdf", "notepad.txt", "visualcode.cls", "worksheet.xls", "archive.zip" };
    }
}
