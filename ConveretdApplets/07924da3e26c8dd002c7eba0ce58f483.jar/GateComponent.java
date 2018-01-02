import java.awt.event.MouseListener;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JMenuItem;
import java.awt.Font;
import javax.swing.JPopupMenu;
import java.awt.Component;
import java.awt.Graphics;
import java.awt.Point;
import javax.swing.border.Border;
import javax.swing.plaf.BorderUIResource;
import javax.swing.ImageIcon;
import javax.swing.JComponent;

// 
// Decompiled by Procyon v0.5.30
// 

public class GateComponent extends JComponent implements BitSource, UpdateEvent
{
    public static final int HEIGHT_INPUT0 = 16;
    public static final int HEIGHT_INPUT1 = 30;
    public static final int HEIGHT_OUTPUT = 23;
    public static final int GATE_NADA = 0;
    public static final int GATE_AND = 1;
    public static final int GATE_NAND = -1;
    public static final int GATE_OR = 2;
    public static final int GATE_NOR = -2;
    public static final int GATE_XOR = 3;
    public static final int GATE_NXOR = -3;
    public static final int GATE_NOT = 4;
    private ImageIcon _imgBlank;
    private ImageIcon _img00;
    private ImageIcon _img01;
    private ImageIcon _img10;
    private ImageIcon _img11;
    private BitTerminal _bitInput0;
    private BitTerminal _bitInput1;
    private BitTerminal _bitOutput;
    private boolean _boolSelected;
    private int _intGateType;
    private UpdateEvent _downStream;
    
    public GateComponent(final int x, final int y) {
        this.setLocation(x, y);
        final ImageLoader loader = new ImageLoader();
        this._imgBlank = loader.getImageIcon("images/blank.gif");
        this._intGateType = 0;
        this.pSetImages("images/blank.gif", "images/blank.gif", "images/blank.gif", "images/blank.gif");
        this._bitInput0 = new BitTerminal(this, false, x + 1, y + 16);
        this._bitInput1 = new BitTerminal(this, false, x + 1, y + 30);
        this._bitOutput = new BitTerminal(this, false, x + this.getWidth() - 2, y + 23);
        this.createPopupMenu();
    }
    
    private void pSetImages(final String sImg00, final String sImg01, final String sImg10, final String sImg11) {
        final ImageLoader loader = new ImageLoader();
        this._img00 = loader.getImageIcon(sImg00);
        this._img01 = loader.getImageIcon(sImg01);
        this._img10 = loader.getImageIcon(sImg10);
        this._img11 = loader.getImageIcon(sImg11);
        final int width = (this._img00 != null) ? (this._img00.getIconWidth() + 2) : 48;
        final int height = (this._img00 != null) ? (this._img00.getIconHeight() + 2) : 48;
        if (this.getSize().getWidth() != width || this.getSize().getHeight() != height) {
            this.setSize(width, height);
        }
        this.setBorder((this._intGateType == 0) ? new BorderUIResource.EtchedBorderUIResource(1) : null);
    }
    
    public boolean isSelected() {
        final boolean boolAnd = this.getInput0().isSelected() && this.getInput1().isSelected();
        final boolean boolOr = this.getInput0().isSelected() || this.getInput1().isSelected();
        final boolean boolXor = this.getInput0().isSelected() ^ this.getInput1().isSelected();
        switch (Math.abs(this._intGateType)) {
            case 1: {
                this._boolSelected = ((this._intGateType < 0) ? (!boolAnd) : boolAnd);
                break;
            }
            case 2: {
                this._boolSelected = ((this._intGateType < 0) ? (!boolOr) : boolOr);
                break;
            }
            case 3: {
                this._boolSelected = ((this._intGateType < 0) ? (!boolXor) : boolXor);
                break;
            }
            case 4: {
                this._boolSelected = !boolXor;
                break;
            }
            default: {
                this._boolSelected = false;
                break;
            }
        }
        return this._boolSelected;
    }
    
    public void setSelected(final boolean selected) {
        this.update();
    }
    
    public void setGateType(final int intGateType) {
        if (this._intGateType == intGateType) {
            return;
        }
        switch (this._intGateType = intGateType) {
            case 1: {
                this.pSetImages("images/and_00.gif", "images/and_01.gif", "images/and_10.gif", "images/and_11.gif");
                break;
            }
            case 2: {
                this.pSetImages("images/or_00.gif", "images/or_01.gif", "images/or_10.gif", "images/or_11.gif");
                break;
            }
            case 3: {
                this.pSetImages("images/xor_00.gif", "images/xor_01.gif", "images/xor_10.gif", "images/xor_11.gif");
                break;
            }
            case -1: {
                this.pSetImages("images/nand_00.gif", "images/nand_01.gif", "images/nand_10.gif", "images/nand_11.gif");
                break;
            }
            case -2: {
                this.pSetImages("images/nor_00.gif", "images/nor_01.gif", "images/nor_10.gif", "images/nor_11.gif");
                break;
            }
            case -3: {
                this.pSetImages("images/nxor_00.gif", "images/nxor_01.gif", "images/nxor_10.gif", "images/nxor_11.gif");
                break;
            }
            case 4: {
                this.pSetImages("images/not_00.gif", "images/not_01.gif", "images/not_10.gif", "images/not_11.gif");
                this.removeMouseListener(this.getMouseListeners()[0]);
                break;
            }
            default: {
                this._intGateType = 0;
                this.pSetImages("images/blank.gif", "images/blank.gif", "images/blank.gif", "images/blank.gif");
                break;
            }
        }
        this.update();
        if (this._downStream != null) {
            this._downStream.update();
        }
    }
    
    public int getValue() {
        return this.isSelected() ? 1 : 0;
    }
    
    public void setValue(final int value) {
        this.setSelected(value != 0);
    }
    
    public Point getTerminal() {
        return this._bitOutput.getTerminal();
    }
    
    public void setTerminal(final Point point) {
    }
    
    public void setDownStream(final UpdateEvent downStream) {
        this._downStream = downStream;
    }
    
    public BitSource getInput0() {
        return this._bitInput0;
    }
    
    public BitSource getInput1() {
        return this._bitInput1;
    }
    
    public BitSource getOutput() {
        return this._bitOutput;
    }
    
    public void paintComponent(final Graphics g) {
        final boolean boolInput0 = this._bitInput0.isSelected();
        final boolean boolInput2 = this._bitInput1.isSelected();
        if (this._intGateType == 0) {
            this._imgBlank.paintIcon(this, g, 0, 0);
        }
        else if (boolInput0 && boolInput2) {
            this._img11.paintIcon(this, g, 0, 0);
        }
        else if (boolInput2) {
            this._img01.paintIcon(this, g, 0, 0);
        }
        else if (boolInput0) {
            this._img10.paintIcon(this, g, 0, 0);
        }
        else {
            this._img00.paintIcon(this, g, 0, 0);
        }
        if (this._intGateType == 4) {
            this._bitInput0.setTerminal(new Point(this.getLocation().x + 1, this.getLocation().y + 23));
            this._bitInput1.setTerminal(new Point(this.getLocation().x + 1, this.getLocation().y + 23));
            this._bitOutput.setTerminal(new Point(this.getLocation().x + this.getWidth() - 2, this.getLocation().y + 23));
        }
        else {
            this._bitInput0.setTerminal(new Point(this.getLocation().x + 1, this.getLocation().y + 16));
            this._bitInput1.setTerminal(new Point(this.getLocation().x + 1, this.getLocation().y + 30));
            this._bitOutput.setTerminal(new Point(this.getLocation().x + this.getWidth() - 2, this.getLocation().y + 23));
        }
    }
    
    public void update() {
        final boolean boolAnd = this.getInput0().isSelected() && this.getInput1().isSelected();
        final boolean boolOr = this.getInput0().isSelected() || this.getInput1().isSelected();
        final boolean boolXor = this.getInput0().isSelected() ^ this.getInput1().isSelected();
        switch (Math.abs(this._intGateType)) {
            case 1: {
                this._boolSelected = ((this._intGateType < 0) ? (!boolAnd) : boolAnd);
                break;
            }
            case 2: {
                this._boolSelected = ((this._intGateType < 0) ? (!boolOr) : boolOr);
                break;
            }
            case 3: {
                this._boolSelected = ((this._intGateType < 0) ? (!boolXor) : boolXor);
                break;
            }
            case 4: {
                this._boolSelected = !boolXor;
                break;
            }
            default: {
                this._boolSelected = false;
                break;
            }
        }
        if (this._bitOutput != null) {
            this._bitOutput.setSelected(this._boolSelected);
        }
        this.repaint();
    }
    
    public void createPopupMenu() {
        final JPopupMenu popupMenu = new JPopupMenu();
        popupMenu.setFont(new Font("Dialog", 0, 12));
        JMenuItem menuItem = new JMenuItem("Blank");
        menuItem.setFont(popupMenu.getFont());
        menuItem.addActionListener(new ActionListener() {
            public void actionPerformed(final ActionEvent evt) {
                GateComponent.this.setGateType(0);
            }
        });
        popupMenu.add(menuItem);
        popupMenu.addSeparator();
        menuItem = new JMenuItem("AND gate");
        menuItem.setFont(popupMenu.getFont());
        menuItem.addActionListener(new ActionListener() {
            public void actionPerformed(final ActionEvent evt) {
                GateComponent.this.setGateType(1);
            }
        });
        popupMenu.add(menuItem);
        menuItem = new JMenuItem("NAND gate");
        menuItem.setFont(popupMenu.getFont());
        menuItem.addActionListener(new ActionListener() {
            public void actionPerformed(final ActionEvent evt) {
                GateComponent.this.setGateType(-1);
            }
        });
        popupMenu.add(menuItem);
        popupMenu.addSeparator();
        menuItem = new JMenuItem("OR gate");
        menuItem.setFont(popupMenu.getFont());
        menuItem.addActionListener(new ActionListener() {
            public void actionPerformed(final ActionEvent evt) {
                GateComponent.this.setGateType(2);
            }
        });
        popupMenu.add(menuItem);
        menuItem = new JMenuItem("NOR gate");
        menuItem.setFont(popupMenu.getFont());
        menuItem.addActionListener(new ActionListener() {
            public void actionPerformed(final ActionEvent evt) {
                GateComponent.this.setGateType(-2);
            }
        });
        popupMenu.add(menuItem);
        popupMenu.addSeparator();
        menuItem = new JMenuItem("XOR gate");
        menuItem.setFont(popupMenu.getFont());
        menuItem.addActionListener(new ActionListener() {
            public void actionPerformed(final ActionEvent evt) {
                GateComponent.this.setGateType(3);
            }
        });
        popupMenu.add(menuItem);
        menuItem = new JMenuItem("XNOR gate");
        menuItem.setFont(popupMenu.getFont());
        menuItem.addActionListener(new ActionListener() {
            public void actionPerformed(final ActionEvent evt) {
                GateComponent.this.setGateType(-3);
            }
        });
        popupMenu.add(menuItem);
        this.addMouseListener(new PopupListener(popupMenu));
    }
}
