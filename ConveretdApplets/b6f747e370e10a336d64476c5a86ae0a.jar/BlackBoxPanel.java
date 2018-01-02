import javax.swing.table.TableModel;
import javax.swing.JTable;
import java.awt.event.MouseEvent;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.JScrollPane;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.Font;
import javax.swing.plaf.BorderUIResource;
import javax.swing.text.Document;
import java.awt.Component;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;
import java.awt.LayoutManager;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JLabel;
import java.awt.Color;
import java.awt.event.MouseListener;
import javax.swing.JPanel;

// 
// Decompiled by Procyon v0.5.30
// 

public class BlackBoxPanel extends JPanel implements MouseListener
{
    public static final int WINDOW_WIDTH = 760;
    public static final int WINDOW_HEIGHT = 600;
    public static final Color WINOW_COLOR;
    private JLabel _labelError;
    private JButton _buttonInput;
    private JTextField _textInput;
    private JTextField _textOutput;
    private JButton _buttonPass;
    private JButton _buttonFail;
    private static BlackBoxTable _tableBlackBox;
    
    public BlackBoxPanel() {
        this.setLayout(null);
        this.setBorder(new LineBorder(Color.orange, 1));
        this.setBackground(BlackBoxPanel.WINOW_COLOR);
        final BlackBox blackBox = new BlackBox(305, 20, 150, 150);
        this.add(blackBox);
        final Arrow arrowIn = new Arrow(255, 83, 50, 25);
        this.add(arrowIn);
        final Arrow arrowOut = new Arrow(454, 83, 50, 25);
        this.add(arrowOut);
        (this._textInput = new JTextField()).setDocument(new JTextFieldLimit(10));
        this._textInput.setBorder(new BorderUIResource.EtchedBorderUIResource(1, Color.orange, this.getBackground()));
        this._textInput.setFont(new Font("Dialog", 0, 12));
        this._textInput.setBounds(30, 85, 220, 20);
        this.add(this._textInput);
        JLabel label = new JLabel("Enter input to test:");
        label.setBounds(this._textInput.getX(), this._textInput.getY() - 25, 150, 20);
        label.setForeground(Color.white);
        this.add(label);
        (this._buttonInput = new JButton("Test")).setBounds(this._textInput.getX() + this._textInput.getWidth() - 100, this._textInput.getY() + this._textInput.getHeight() + 45, 100, 20);
        this._buttonInput.setEnabled(false);
        this.add(this._buttonInput);
        (this._textOutput = new JTextField()).setBorder(new BorderUIResource.EtchedBorderUIResource(1, Color.orange, this.getBackground()));
        this._textOutput.setFont(new Font("Dialog", 0, 12));
        this._textOutput.setBounds(510, 85, 220, 20);
        this.add(this._textOutput);
        label = new JLabel("Output:");
        label.setBounds(this._textOutput.getX(), this._textOutput.getY() - 25, 150, 20);
        label.setForeground(Color.white);
        this.add(label);
        (this._labelError = new JLabel("Error")).setBounds(this._textOutput.getX(), this._textOutput.getY() + 30, this._textOutput.getWidth(), 20);
        this._labelError.setForeground(Color.red);
        this._labelError.setHorizontalTextPosition(0);
        this._labelError.setHorizontalAlignment(0);
        this._labelError.setVisible(false);
        this.add(this._labelError);
        (this._buttonPass = new JButton("Pass")).setBounds(this._textOutput.getX(), this._textOutput.getY() + this._textOutput.getHeight() + 45, 100, 20);
        this._buttonPass.setEnabled(false);
        this.add(this._buttonPass);
        this._buttonPass.addActionListener(new ActionListener() {
            public void actionPerformed(final ActionEvent evt) {
                BlackBoxPanel.this.doPassClick();
            }
        });
        (this._buttonFail = new JButton("Fail")).setBounds(this._buttonPass.getX() + this._buttonPass.getWidth() + 20, this._buttonPass.getY(), 100, 20);
        this._buttonFail.setEnabled(false);
        this.add(this._buttonFail);
        this._buttonFail.addActionListener(new ActionListener() {
            public void actionPerformed(final ActionEvent evt) {
                BlackBoxPanel.this.doFailClick();
            }
        });
        BlackBoxPanel._tableBlackBox = new BlackBoxTable(this);
        final JScrollPane scrollTable = new JScrollPane(BlackBoxPanel._tableBlackBox.getTable());
        scrollTable.setBounds(10, 280, 750, 210);
        scrollTable.setVerticalScrollBarPolicy(22);
        this.add(scrollTable);
        this._buttonInput.addActionListener(new ActionListener() {
            public void actionPerformed(final ActionEvent evt) {
                BlackBoxPanel.this.doEnterClick();
            }
        });
        this._textInput.addKeyListener(new KeyListener() {
            public void keyPressed(final KeyEvent e) {
                if (e.getKeyChar() == '\n') {
                    BlackBoxPanel.this.doEnterClick();
                }
                else if (BlackBoxPanel.this._labelError.isVisible()) {
                    BlackBoxPanel.this._labelError.setVisible(false);
                }
            }
            
            public void keyReleased(final KeyEvent e) {
                if (!BlackBoxPanel.this._buttonInput.isEnabled() && BlackBoxPanel.this._textInput.getText().trim().length() > 0) {
                    BlackBoxPanel.this._buttonInput.setEnabled(true);
                }
                else if (BlackBoxPanel.this._buttonInput.isEnabled() && BlackBoxPanel.this._textInput.getText().trim().length() == 0) {
                    BlackBoxPanel.this._buttonInput.setEnabled(false);
                }
            }
            
            public void keyTyped(final KeyEvent e) {
            }
        });
    }
    
    public static BlackBoxTable getTable() {
        return BlackBoxPanel._tableBlackBox;
    }
    
    private void doFailClick() {
        final VersionControl ctrl = CVSPanel.getCurrentVC();
        ctrl.addTest(this._textInput.getText(), this._textOutput.getText(), false);
        BlackBoxPanel._tableBlackBox.update(ctrl.getAllTests());
        this._textOutput.setText("");
        this._textInput.setText("");
        this._buttonInput.setEnabled(false);
        this._buttonFail.setEnabled(false);
        this._buttonPass.setEnabled(false);
    }
    
    private void doPassClick() {
        final VersionControl ctrl = CVSPanel.getCurrentVC();
        ctrl.addTest(this._textInput.getText(), this._textOutput.getText(), true);
        BlackBoxPanel._tableBlackBox.update(ctrl.getAllTests());
        this._textOutput.setText("");
        this._textInput.setText("");
        this._buttonInput.setEnabled(false);
        this._buttonFail.setEnabled(false);
        this._buttonPass.setEnabled(false);
    }
    
    private void doEnterClick() {
        final VersionControl ctrl = CVSPanel.getCurrentVC();
        this._textOutput.setText(ctrl.test(this._textInput.getText().trim()));
        this._buttonInput.setEnabled(false);
        this._buttonFail.setEnabled(true);
        this._buttonPass.setEnabled(true);
    }
    
    public void mouseClicked(final MouseEvent e) {
        if (e.getClickCount() == 2) {
            final JTable table = (JTable)e.getSource();
            final int intRow = table.getSelectedRow();
            if (intRow == -1) {
                return;
            }
            final TableModel modelTable = table.getModel();
            final String strInput = modelTable.getValueAt(intRow, 1).toString();
            this._textInput.setText(strInput);
            final VersionControl ctrl = CVSPanel.getCurrentVC();
            BlackBoxPanel._tableBlackBox.update(ctrl.removeTest(intRow));
            this.doEnterClick();
        }
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
        WINOW_COLOR = UI_SoftEng.WINOW_COLOR;
    }
}
