import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.Component;
import javax.swing.JOptionPane;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.FlowLayout;
import java.awt.LayoutManager;
import java.awt.BorderLayout;
import javax.swing.JComboBox;
import javax.swing.JTextField;
import javax.swing.JPanel;

// 
// Decompiled by Procyon v0.5.30
// 

public class Abacus extends JPanel
{
    abacusBoard aBoard;
    JTextField amountField;
    JTextField delayField;
    JComboBox operation;
    public static final int ADD = 0;
    public static final int SUBTRACT = 1;
    public static final long MAXVALUE = 9999999999L;
    
    public Abacus() {
        this.setLayout(new BorderLayout());
        this.aBoard = new abacusBoard(0);
        final JPanel panel = new JPanel(new FlowLayout(1));
        (this.amountField = new JTextField("0", 9)).setHorizontalAlignment(4);
        final KeyListener keyListener = new KeyListener() {
            public void keyTyped(final KeyEvent keyEvent) {
                if (Character.isLetter(keyEvent.getKeyChar()) || Character.isWhitespace(keyEvent.getKeyChar())) {
                    keyEvent.consume();
                }
            }
            
            public void keyPressed(final KeyEvent keyEvent) {
            }
            
            public void keyReleased(final KeyEvent keyEvent) {
            }
        };
        this.amountField.addKeyListener(keyListener);
        (this.delayField = new JTextField("2", 2)).setHorizontalAlignment(4);
        this.delayField.addKeyListener(keyListener);
        (this.operation = new JComboBox()).addItem("Add");
        this.operation.addItem("Subtract");
        final JButton button = new JButton("Show Steps");
        final ActionListener actionListener = new ActionListener() {
            public void actionPerformed(final ActionEvent actionEvent) {
                final int int1 = Integer.parseInt(Abacus.this.amountField.getText());
                Abacus.this.aBoard.setDelay(Integer.parseInt(Abacus.this.delayField.getText()) * 1000);
                if (Abacus.this.operation.getSelectedIndex() == 0) {
                    if (Abacus.this.aBoard.getValue() + int1 > 9999999999L) {
                        JOptionPane.showMessageDialog(null, "Total value would exceed abacus capacity", "Capacity Exceeded", 1);
                        return;
                    }
                    Abacus.this.aBoard.auto = true;
                    Abacus.this.aBoard.addSteps(int1);
                }
                else {
                    if (Abacus.this.aBoard.getValue() < int1) {
                        JOptionPane.showMessageDialog(null, "Cannot have negative result", "Error", 0);
                        return;
                    }
                    Abacus.this.aBoard.auto = true;
                    Abacus.this.aBoard.subSteps(int1);
                }
            }
        };
        button.addActionListener(actionListener);
        this.amountField.addActionListener(actionListener);
        final JButton button2 = new JButton("Reset");
        button2.addActionListener(new ActionListener() {
            public void actionPerformed(final ActionEvent actionEvent) {
                Abacus.this.aBoard.setValue(0);
            }
        });
        panel.add(this.operation);
        panel.add(this.amountField);
        panel.add(button);
        panel.add(new JLabel("    Delay (secs.) "));
        panel.add(this.delayField);
        panel.add(button2);
        this.add(this.aBoard, "North");
        this.add(panel, "South");
    }
    
    public static void main(final String[] array) {
        final Abacus abacus = new Abacus();
        final JFrame frame = new JFrame("Chinese Abacus");
        frame.setDefaultCloseOperation(3);
        frame.getContentPane().add(abacus);
        frame.setSize(500, 750);
        frame.pack();
        frame.show();
        frame.update(frame.getGraphics());
    }
}
