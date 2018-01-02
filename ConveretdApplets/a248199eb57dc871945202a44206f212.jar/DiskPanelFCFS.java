import java.awt.event.ActionEvent;
import java.awt.Graphics;
import java.awt.Component;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;
import javax.swing.JScrollPane;
import java.awt.Insets;
import java.awt.Font;
import java.awt.Color;
import java.awt.LayoutManager;
import javax.swing.JTextArea;
import javax.swing.ImageIcon;
import java.awt.event.ActionListener;
import javax.swing.JPanel;

// 
// Decompiled by Procyon v0.5.30
// 

public class DiskPanelFCFS extends JPanel implements ActionListener, FileAddMethod
{
    private static final int DISK_RADIUS = 98;
    private static final int INT_TRACKS = 10;
    private static final int INT_SECTORS = 63;
    private static final double DBL_SECTORS = 0.09973310011396169;
    private static final ImageIcon _disk;
    private JTextArea _textArea;
    private int _intTop;
    private int _intLeft;
    private boolean _boolLoaded;
    private boolean _boolReading;
    private double _dblAngle;
    private int _intTrack;
    private int _intNextTrack;
    private boolean[][] _boolBits;
    private boolean[][] _boolYellows;
    
    public DiskPanelFCFS(final int x, final int y, final boolean right) {
        this._boolLoaded = false;
        this._boolReading = false;
        this.setLayout(null);
        this.setBounds(x, y, 280, 240);
        this.setBackground(new Color(123, 123, 123));
        this._boolBits = new boolean[10][63];
        this._boolYellows = new boolean[10][63];
        this._dblAngle = 0.0;
        this._intTrack = 0;
        (this._textArea = new JTextArea()).setBackground(this.getBackground());
        this._textArea.setFont(new Font("Dialog", 0, 14));
        this._textArea.setForeground(Color.orange);
        this._textArea.setEditable(false);
        this._textArea.setTabSize(2);
        this._textArea.setText("");
        this._textArea.setCaretPosition(0);
        this._textArea.setMargin(new Insets(5, 10, 0, 10));
        this._textArea.setLayout(null);
        final JScrollPane scrollMoves = new JScrollPane();
        scrollMoves.setBorder(new LineBorder(Color.orange, 1));
        scrollMoves.setViewportView(this._textArea);
        scrollMoves.setBounds(right ? 230 : 0, 0, 40, 220);
        scrollMoves.setHorizontalScrollBarPolicy(31);
        this.add(scrollMoves, 0);
        if (right) {
            this._intTop = 22;
            this._intLeft = 6;
        }
        else {
            this._intTop = 22;
            this._intLeft = 62;
        }
        this._boolLoaded = true;
    }
    
    public void paintComponent(final Graphics g) {
        if (!this._boolLoaded) {
            return;
        }
        super.paintComponent(g);
        DiskPanelFCFS._disk.paintIcon(this, g, this._intLeft - 6, this._intTop - 6);
        for (int i = 0; i < 10; ++i) {
            for (int j = 0; j < 63; ++j) {
                final double angle = (this._dblAngle + j * 1.0 * 0.09973310011396169) % 6.283185307179586;
                if (this._boolBits[i][j]) {
                    if (!g.getColor().equals(Color.red)) {
                        g.setColor(Color.red);
                    }
                    g.fillRect(this._intLeft + 98 + (int)((i + 1.0) / 10.0 * 94.0 * Math.cos(angle)), this._intTop + 98 + (int)((i + 1.0) / 10.0 * 94.0 * Math.sin(angle)), 4, 4);
                }
                if (this._boolYellows[i][j]) {
                    if (angle < 0.5 && i == this._intTrack && this._boolReading) {
                        this._boolYellows[i][j] = false;
                        if (this._textArea.getComponentCount() > 0) {
                            final FileComponent topFile = (FileComponent)this._textArea.getComponent(0);
                            topFile.resetBit(j);
                            if (topFile.isDone()) {
                                this.removeTopFile();
                            }
                        }
                    }
                    else {
                        if (!g.getColor().equals(Color.yellow)) {
                            g.setColor(Color.yellow);
                        }
                        g.fillRect(this._intLeft + 98 + (int)((i + 1.0) / 10.0 * 94.0 * Math.cos(angle)), this._intTop + 98 + (int)((i + 1.0) / 10.0 * 94.0 * Math.sin(angle)), 4, 4);
                    }
                }
            }
        }
        g.setColor(Color.black);
        g.fillRect(this._intLeft + 98 + 4, this._intTop + 98, 98, 4);
        g.setColor(Color.white);
        g.fillRect(this._intLeft + 98 + (int)((this._intTrack + 1.0) / 10.0 * 94.0), this._intTop + 98, 4, 4);
        g.setColor(this.getBackground());
        g.fillOval(this._intLeft + 98, this._intTop + 98, 4, 4);
    }
    
    public void actionPerformed(final ActionEvent e) {
        if (!this._boolLoaded) {
            return;
        }
        this._dblAngle += 0.15915494309189535;
        if (this._dblAngle >= 18.84955592153876 && this._intNextTrack != this._intTrack) {
            this._boolReading = false;
            this._dblAngle %= 6.283185307179586;
            if (this._intNextTrack > this._intTrack) {
                ++this._intTrack;
            }
            else if (this._intNextTrack < this._intTrack) {
                --this._intTrack;
            }
        }
        else if (this._dblAngle >= 12.566370614359172) {
            this._boolReading = true;
        }
        else if (this._dblAngle >= 6.283185307179586) {
            this._boolReading = true;
            if (this._intNextTrack > this._intTrack) {
                ++this._intTrack;
                this._dblAngle %= 6.283185307179586;
            }
            else if (this._intNextTrack < this._intTrack) {
                --this._intTrack;
                this._dblAngle %= 6.283185307179586;
            }
        }
        else {
            this._boolReading = false;
        }
        this.repaint();
    }
    
    public synchronized void addFile(final FileComponent file) {
        boolean[] boolBits = file.getBits();
        for (int j = 0; j < 63; ++j) {
            if (boolBits[j] && !this._boolBits[file.getTrack()][j]) {
                this._boolBits[file.getTrack()][j] = true;
            }
        }
        this._textArea.add(file);
        if (this._textArea.getComponentCount() > 0) {
            final FileComponent topFile = (FileComponent)this._textArea.getComponent(0);
            this._intNextTrack = topFile.getTrack();
            for (int i = 0; i < 10; ++i) {
                for (int k = 0; k < 63; ++k) {
                    if (this._boolYellows[i][k]) {
                        this._boolBits[i][k] = true;
                    }
                    this._boolYellows[i][k] = false;
                }
            }
            boolBits = topFile.getBits();
            for (int l = 0; l < 63; ++l) {
                if (boolBits[l]) {
                    this._boolYellows[topFile.getTrack()][l] = true;
                    this._boolBits[topFile.getTrack()][l] = false;
                }
            }
        }
        String text = "";
        for (int i = 0; i < this._textArea.getComponentCount(); ++i) {
            this._textArea.getComponent(i).setLocation(0, i * (this._textArea.getComponent(i).getHeight() + 2) + 2);
            this._textArea.getComponent(i).setVisible(true);
            text = text + "\n" + ((i % 20 == 0) ? "\n" : "");
        }
        this._textArea.setText(text);
        this._textArea.setCaretPosition(0);
        this._textArea.repaint();
    }
    
    public synchronized void removeTopFile() {
        if (this._textArea.getComponentCount() < 1) {
            return;
        }
        FileComponent topFile = (FileComponent)this._textArea.getComponent(0);
        if (!topFile.isDone()) {
            return;
        }
        this._textArea.remove(0);
        String text = "";
        for (int i = 0; i < this._textArea.getComponentCount(); ++i) {
            this._textArea.getComponent(i).setLocation(0, i * (this._textArea.getComponent(i).getHeight() + 2) + 2);
            text = text + "\n" + ((i % 20 == 0) ? "\n" : "");
            topFile = (FileComponent)this._textArea.getComponent(i);
            final boolean[] boolBits = topFile.getBits();
            for (int j = 0; j < 63; ++j) {
                if (boolBits[j] && !this._boolBits[topFile.getTrack()][j]) {
                    this._boolBits[topFile.getTrack()][j] = true;
                }
            }
        }
        this._textArea.setText(text);
        this._textArea.setCaretPosition(0);
        this._textArea.repaint();
        this._intNextTrack = this._intTrack;
        if (this._textArea.getComponentCount() < 1) {
            return;
        }
        topFile = (FileComponent)this._textArea.getComponent(0);
        if (topFile.isDone()) {
            this.removeTopFile();
        }
        this._intNextTrack = topFile.getTrack();
        final boolean[] boolBits = topFile.getBits();
        for (int k = 0; k < 63; ++k) {
            if (boolBits[k]) {
                this._boolYellows[topFile.getTrack()][k] = true;
                this._boolBits[topFile.getTrack()][k] = false;
            }
        }
        this.repaint();
    }
    
    static {
        _disk = new ImageLoader().getImageIcon("images/disk.gif");
    }
}
