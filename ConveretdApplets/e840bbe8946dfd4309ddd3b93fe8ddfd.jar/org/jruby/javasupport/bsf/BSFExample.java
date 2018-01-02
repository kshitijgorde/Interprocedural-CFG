// 
// Decompiled by Procyon v0.5.30
// 

package org.jruby.javasupport.bsf;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JPanel;
import java.awt.FlowLayout;
import java.awt.LayoutManager;
import java.awt.BorderLayout;
import org.apache.bsf.BSFException;
import java.awt.Component;
import javax.swing.JOptionPane;
import javax.swing.JButton;
import javax.swing.JTextArea;
import javax.swing.JMenuBar;
import javax.swing.JFrame;
import org.apache.bsf.BSFManager;

public class BSFExample
{
    private BSFManager manager;
    
    public static void main(final String[] args) {
        BSFManager.registerScriptingEngine("ruby", "org.jruby.javasupport.bsf.JRubyEngine", new String[] { "rb" });
        new BSFExample(new BSFManager());
    }
    
    public BSFExample(final BSFManager manager) {
        this.manager = manager;
        this.initUI();
    }
    
    private void initUI() {
        final JFrame frame = new JFrame("A sample BSF application");
        final JMenuBar menubar = new JMenuBar();
        final JTextArea input = new JTextArea("$frame.setTitle(\"A new title\")");
        final JButton execute = new JButton("Execute");
        final JButton eval = new JButton("Eval");
        try {
            this.manager.declareBean("frame", (Object)frame, (Class)JFrame.class);
            this.manager.declareBean("menubar", (Object)menubar, (Class)JMenuBar.class);
            this.manager.declareBean("input", (Object)input, (Class)JTextArea.class);
            this.manager.declareBean("execute", (Object)execute, (Class)JButton.class);
            this.manager.declareBean("eval", (Object)eval, (Class)JButton.class);
        }
        catch (BSFException excptn) {
            excptn.printStackTrace();
            JOptionPane.showMessageDialog(null, excptn.getMessage());
        }
        frame.getContentPane().setLayout(new BorderLayout(12, 12));
        frame.getContentPane().add(input, "Center");
        final JPanel buttonPane = new JPanel(new FlowLayout(12));
        frame.getContentPane().add(buttonPane, "South");
        buttonPane.add(execute, "East");
        buttonPane.add(eval, "East");
        try {
            this.manager.exec("ruby", "initUI", 1, 1, (Object)"$frame.setJMenuBar($menubar)");
        }
        catch (BSFException excptn2) {
            excptn2.printStackTrace();
            JOptionPane.showMessageDialog(null, excptn2.getMessage());
        }
        execute.addActionListener(new ActionListener() {
            public void actionPerformed(final ActionEvent e) {
                try {
                    BSFExample.this.manager.exec("ruby", "initUI", 1, 1, (Object)input.getText());
                }
                catch (BSFException excptn) {
                    excptn.printStackTrace();
                    JOptionPane.showMessageDialog(frame, excptn.getMessage());
                }
            }
        });
        eval.addActionListener(new ActionListener() {
            public void actionPerformed(final ActionEvent e) {
                try {
                    final String expression = JOptionPane.showInputDialog(frame, "Please enter a Ruby expression:");
                    input.setText(String.valueOf(BSFExample.this.manager.eval("ruby", "initUI", 1, 1, (Object)expression)));
                }
                catch (BSFException excptn) {
                    excptn.printStackTrace();
                    JOptionPane.showMessageDialog(frame, excptn.getMessage());
                }
            }
        });
        frame.pack();
        frame.setVisible(true);
        frame.setDefaultCloseOperation(3);
    }
}
