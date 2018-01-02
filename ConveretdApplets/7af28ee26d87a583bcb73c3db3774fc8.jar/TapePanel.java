import javax.swing.ImageIcon;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.Icon;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;
import java.awt.LayoutManager;
import java.awt.Color;
import javax.swing.JButton;
import javax.swing.JLabel;
import java.util.Vector;
import javax.swing.JPanel;

// 
// Decompiled by Procyon v0.5.30
// 

public class TapePanel extends JPanel
{
    private static final int MAX_BOXES = 17;
    private Vector _vector;
    private JLabel[] _labelTape;
    private JButton _buttonLeft;
    private JButton _buttonRight;
    private int _intIndex;
    
    public TapePanel(final int x, final int y, final Color background) {
        this.setLayout(null);
        this.setBounds(x, y, 500, 80);
        this.setBackground(background);
        this.setBorder(new LineBorder(Color.orange, 1, true));
        this.setVisible(true);
        final ImageLoader loader = new ImageLoader();
        final ImageIcon imgLeft = loader.getImageIcon("/images/left.gif");
        final ImageIcon imgLeftPressed = loader.getImageIcon("/images/leftPressed.gif");
        final ImageIcon imgRight = loader.getImageIcon("/images/right.gif");
        final ImageIcon imgRightPressed = loader.getImageIcon("/images/rightPressed.gif");
        (this._buttonLeft = new JButton(imgLeft)).setPressedIcon(imgLeftPressed);
        this._buttonLeft.setBounds(4, (this.getHeight() - 32) / 2, 32, 32);
        this._buttonLeft.setFocusable(false);
        this._buttonLeft.setBorder(new LineBorder(Color.black, 1));
        this._buttonLeft.addActionListener(new ActionListener() {
            public void actionPerformed(final ActionEvent evt) {
                TapePanel.this.moveLeft();
            }
        });
        this.add(this._buttonLeft);
        (this._buttonRight = new JButton(imgRight)).setPressedIcon(imgRightPressed);
        this._buttonRight.setBounds(this.getWidth() - 32 - 4, (this.getHeight() - 32) / 2, 32, 32);
        this._buttonRight.setFocusable(false);
        this._buttonRight.setBorder(new LineBorder(Color.black, 1));
        this._buttonRight.addActionListener(new ActionListener() {
            public void actionPerformed(final ActionEvent evt) {
                TapePanel.this.moveRight();
            }
        });
        this.add(this._buttonRight);
        this._labelTape = new JLabel[17];
        this._vector = new Vector();
        this.pCreateLabels();
        this._intIndex = 0;
        this.pDrawTape();
    }
    
    public boolean moveRight() {
        --this._intIndex;
        if (this._intIndex < 0) {
            this._intIndex = 0;
            return false;
        }
        this.pDrawTape();
        return true;
    }
    
    public boolean move(final int direction) {
        boolean boolReturn = true;
        this._intIndex += direction;
        if (this._intIndex < 0) {
            this._intIndex = 0;
            boolReturn = false;
        }
        else {
            for (int i = this._vector.size(); i <= this._intIndex + 8; ++i) {
                this._vector.addElement(new String(""));
            }
        }
        this.pDrawTape();
        return boolReturn;
    }
    
    public boolean moveLeft() {
        boolean boolReturn = true;
        ++this._intIndex;
        for (int i = this._vector.size(); i <= this._intIndex + 8; ++i) {
            this._vector.addElement(new String(""));
            boolReturn = false;
        }
        this.pDrawTape();
        return boolReturn;
    }
    
    public String getTape() {
        return this._vector.elementAt(this._intIndex);
    }
    
    public void setTape(final String value) {
        this._vector.setElementAt(value, this._intIndex);
    }
    
    public String getTapeLeft() {
        String stringReturn = "";
        try {
            stringReturn = this._vector.elementAt(this._intIndex + 1);
        }
        catch (IndexOutOfBoundsException ex) {}
        return stringReturn;
    }
    
    public String getTapeRight() {
        String stringReturn = "";
        try {
            stringReturn = this._vector.elementAt(this._intIndex - 1);
        }
        catch (IndexOutOfBoundsException ex) {}
        return stringReturn;
    }
    
    private void pDrawTape() {
        int intBoxes;
        int intHighlight;
        int intStart;
        if (this._intIndex > 8) {
            intBoxes = 17;
            intHighlight = 8;
            intStart = this._intIndex - 8;
        }
        else {
            intBoxes = 8 + this._intIndex + 1;
            intHighlight = this._intIndex;
            intStart = 0;
        }
        for (int i = 0; i < intBoxes; ++i) {
            this._labelTape[i].setLocation(44 + (17 - intBoxes + i) * 24, (this.getHeight() - 24) / 2);
            this._labelTape[i].setBorder(new LineBorder(Color.black, 1));
            this._labelTape[i].setVisible(true);
            this._labelTape[i].setText(this._vector.elementAt(intStart + i));
        }
        for (int i = intBoxes; i < this._labelTape.length; ++i) {
            this._labelTape[i].setVisible(false);
        }
        this._labelTape[intHighlight].setBorder(new LineBorder(Color.red, 2));
        this.remove(this._labelTape[intHighlight]);
        this.add(this._labelTape[intHighlight], 0);
        this.repaint();
    }
    
    private void pCreateLabels() {
        for (int i = 0; i < this._labelTape.length; ++i) {
            (this._labelTape[i] = new JLabel("")).setForeground(Color.orange);
            this._labelTape[i].setBorder(new LineBorder(Color.black, 1));
            this._labelTape[i].setVerticalAlignment(0);
            this._labelTape[i].setHorizontalAlignment(0);
            this._labelTape[i].setSize(25, 25);
            this._labelTape[i].setLocation(44 + i * 24, (this.getHeight() - 24) / 2);
            this._labelTape[i].setVisible(false);
            this.add(this._labelTape[i]);
            this._vector.add(new String(""));
        }
    }
}
