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

public class DiskPanelElevator extends JPanel implements ActionListener, FileAddMethod
{
    private static final int DISK_RADIUS = 98;
    private static final int INT_TRACKS = 10;
    private static final int INT_SECTORS = 63;
    private static final double DBL_SECTORS = 0.09973310011396169;
    private static final ImageIcon _disk;
    private static final int MAX_TRACK = 18;
    private boolean _boolLoaded;
    private boolean _boolReading;
    private double _dblAngle;
    private int _intTrack;
    private boolean[][] _boolBits;
    private boolean[][] _boolYellows;
    private JTextArea _textArea;
    private int _intLeft;
    private int _intTop;
    private int _intNextTrack;
    
    public DiskPanelElevator(final int x, final int y, final boolean right) {
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
        DiskPanelElevator._disk.paintIcon(this, g, this._intLeft - 6, this._intTop - 6);
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
                    if (angle < 0.5 && i == ((this._intTrack > 9) ? (18 - this._intTrack) : this._intTrack) && this._boolReading) {
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
        g.fillRect(this._intLeft + 98 + (int)((((this._intTrack > 9) ? (18 - this._intTrack) : this._intTrack) + 1.0) / 10.0 * 94.0), this._intTop + 98, 4, 4);
        g.setColor(this.getBackground());
        g.fillOval(this._intLeft + 98, this._intTop + 98, 4, 4);
    }
    
    public void actionPerformed(final ActionEvent e) {
        final int track = (this._intTrack > 9) ? (18 - this._intTrack) : this._intTrack;
        if (!this._boolLoaded) {
            return;
        }
        this._dblAngle += 0.15915494309189535;
        this._dblAngle %= 37.69911184307752;
        if (this._dblAngle >= 18.84955592153876 && this._intNextTrack != track) {
            this._boolReading = false;
            this._dblAngle %= 6.283185307179586;
            ++this._intTrack;
            if (this._intTrack >= 18) {
                this._intTrack = 0;
            }
            this.doBounce();
        }
        else if (this._dblAngle >= 12.566370614359172) {
            this._boolReading = true;
        }
        else if (this._dblAngle >= 6.283185307179586) {
            this._boolReading = true;
            if (this._intNextTrack != track) {
                this._dblAngle %= 6.283185307179586;
                ++this._intTrack;
                if (this._intTrack >= 18) {
                    this._intTrack = 0;
                }
                this.doBounce();
            }
        }
        else {
            this._boolReading = false;
        }
        this.repaint();
    }
    
    public void doBounce() {
        final int track = (this._intTrack > 9) ? (18 - this._intTrack) : this._intTrack;
        if (track == 0 || track == 9 || track == 17 || this._intNextTrack == track) {
            return;
        }
        if (this._intNextTrack < track && this._intTrack < 9) {
            this._intTrack = 18 - track;
        }
        else if (this._intNextTrack > track && this._intTrack > 9) {
            this._intTrack = track;
        }
    }
    
    public synchronized void addFile(final FileComponent file) {
        final int track = (this._intTrack > 9) ? (18 - this._intTrack) : this._intTrack;
        boolean[] boolBits = file.getBits();
        for (int j = 0; j < 63; ++j) {
            if (boolBits[j] && !this._boolBits[file.getTrack()][j]) {
                this._boolBits[file.getTrack()][j] = true;
            }
        }
        for (int i = 0; i < this._textArea.getComponentCount(); ++i) {
            final FileComponent topFile = (FileComponent)this._textArea.getComponent(i);
            System.out.println("i = " + i + " priority = " + topFile.getPriority(this._intTrack) + " track = " + topFile.getTrack());
        }
        System.out.println("--- track = " + track + " ---");
        this._textArea.add(file);
        this.sort();
        for (int i = 0; i < this._textArea.getComponentCount(); ++i) {
            final FileComponent topFile = (FileComponent)this._textArea.getComponent(i);
            System.out.println("i = " + i + " priority = " + topFile.getPriority(this._intTrack) + " track = " + topFile.getTrack());
        }
        System.out.println("--- track = " + track + " ---");
        if (this._textArea.getComponentCount() > 0) {
            final FileComponent topFile2 = (FileComponent)this._textArea.getComponent(0);
            this._intNextTrack = topFile2.getTrack();
            for (int k = 0; k < 10; ++k) {
                for (int l = 0; l < 63; ++l) {
                    if (this._boolYellows[k][l]) {
                        this._boolBits[k][l] = true;
                    }
                    this._boolYellows[k][l] = false;
                }
            }
            boolBits = topFile2.getBits();
            for (int m = 0; m < 63; ++m) {
                if (boolBits[m]) {
                    this._boolYellows[topFile2.getTrack()][m] = true;
                    this._boolBits[topFile2.getTrack()][m] = false;
                }
            }
        }
        String text = "";
        for (int k = 0; k < this._textArea.getComponentCount(); ++k) {
            this._textArea.getComponent(k).setLocation(0, k * (this._textArea.getComponent(k).getHeight() + 2) + 2);
            this._textArea.getComponent(k).setVisible(true);
            text = text + "\n" + ((k % 20 == 0) ? "\n" : "");
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
        this._intNextTrack = this._intTrack;
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
    
    public void sort() {
        final Component[] array = this._textArea.getComponents();
        final int count = array.length;
        for (int i = count - 1; i > 0; --i) {
            for (int j = 0; j < i; ++j) {
                final FileComponent file1 = (FileComponent)array[j];
                final FileComponent file2 = (FileComponent)array[j + 1];
                if (file1.getPriority(this._intTrack) > file2.getPriority(this._intTrack)) {
                    final Component temp = array[j];
                    array[j] = array[j + 1];
                    array[j + 1] = temp;
                }
            }
        }
        this._textArea.removeAll();
        for (int i = 0; i < array.length; ++i) {
            this._textArea.add(array[i]);
        }
        String text = "";
        for (int k = 0; k < this._textArea.getComponentCount(); ++k) {
            this._textArea.getComponent(k).setLocation(0, k * (this._textArea.getComponent(k).getHeight() + 2) + 2);
            this._textArea.getComponent(k).setVisible(true);
            text = text + "\n" + ((k % 20 == 0) ? "\n" : "");
        }
        this._textArea.setText(text);
        this._textArea.setCaretPosition(0);
        this._textArea.repaint();
    }
    
    static {
        _disk = new ImageLoader().getImageIcon("images/disk.gif");
    }
}
