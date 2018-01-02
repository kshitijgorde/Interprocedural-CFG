// 
// Decompiled by Procyon v0.5.30
// 

package jay.yydebug;

import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.ScrollPane;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.Button;
import java.awt.Color;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.Component;
import java.awt.Checkbox;
import java.awt.LayoutManager;
import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.TextArea;
import java.awt.TextField;
import java.awt.Panel;

public class yyAnimPanel extends Panel implements yyDebug
{
    protected transient TextField token;
    protected transient TextField value;
    protected transient TextArea comments;
    protected transient Stack stack;
    protected transient boolean tokenBreak;
    protected transient boolean stackBreak;
    protected transient boolean commentsBreak;
    
    public yyAnimPanel(final Font font) {
        super(new BorderLayout());
        this.tokenBreak = true;
        final Panel panel = new Panel(new BorderLayout());
        final Checkbox checkbox;
        panel.add(checkbox = new Checkbox("token ", this.tokenBreak), "West");
        checkbox.addItemListener(new ItemListener() {
            public void itemStateChanged(final ItemEvent itemEvent) {
                yyAnimPanel.this.tokenBreak = (itemEvent.getStateChange() == 1);
            }
        });
        final Panel panel2 = new Panel(new BorderLayout());
        panel2.add(this.token = new TextField(12), "West");
        this.token.setEditable(false);
        this.token.setBackground(Color.white);
        this.token.setFont(font);
        panel2.add(this.value = new TextField(24), "Center");
        this.value.setEditable(false);
        this.value.setBackground(Color.white);
        this.value.setFont(font);
        panel.add(panel2, "Center");
        final Button button;
        panel.add(button = new Button(" continue "), "East");
        button.addActionListener(new ActionListener() {
            public void actionPerformed(final ActionEvent actionEvent) {
                synchronized (yyAnimPanel.this) {
                    yyAnimPanel.this.notify();
                }
            }
        });
        this.add(panel, "North");
        final Panel panel3 = new Panel(new BorderLayout());
        final Panel panel4 = new Panel(new BorderLayout());
        final Checkbox checkbox2;
        panel4.add(checkbox2 = new Checkbox("stack", this.stackBreak), "North");
        checkbox2.addItemListener(new ItemListener() {
            public void itemStateChanged(final ItemEvent itemEvent) {
                yyAnimPanel.this.stackBreak = (itemEvent.getStateChange() == 1);
            }
        });
        panel4.add(this.stack = new Stack(font), "Center");
        panel3.add(panel4, "Center");
        final Panel panel5 = new Panel(new BorderLayout());
        final Checkbox checkbox3;
        panel5.add(checkbox3 = new Checkbox("comments", this.commentsBreak), "North");
        checkbox3.addItemListener(new ItemListener() {
            public void itemStateChanged(final ItemEvent itemEvent) {
                yyAnimPanel.this.commentsBreak = (itemEvent.getStateChange() == 1);
            }
        });
        panel5.add(this.comments = new TextArea(10, 40), "Center");
        this.comments.setEditable(false);
        this.comments.setBackground(Color.white);
        this.comments.setFont(font);
        panel3.add(panel5, "East");
        this.add(panel3, "Center");
    }
    
    protected synchronized void explain(final String s) {
        if (this.comments.getText().length() > 0) {
            this.comments.append("\n");
        }
        this.comments.append(s);
        if (this.commentsBreak) {
            try {
                this.wait();
            }
            catch (InterruptedException ex) {}
        }
    }
    
    public synchronized void lex(final int n, final int n2, final String text, final Object o) {
        this.token.setText(text);
        this.value.setText((o == null) ? "" : o.toString());
        this.explain("read " + text);
        if (this.tokenBreak && !this.commentsBreak) {
            try {
                this.wait();
            }
            catch (InterruptedException ex) {}
        }
    }
    
    public void shift(final int n, final int n2, final int n3) {
        switch (n3) {
            default: {
                this.explain("shift to " + n2);
                break;
            }
            case 0:
            case 1:
            case 2: {
                this.explain("shift to " + n2 + ", " + n3 + " left to recover");
                break;
            }
            case 3: {
                this.explain("shift to " + n2 + " on error");
                break;
            }
        }
    }
    
    public void discard(final int n, final int n2, final String s, final Object o) {
        this.explain("discard token " + s + ", value " + o);
    }
    
    public void shift(final int n, final int n2) {
        this.explain("go to " + n2);
    }
    
    public synchronized void accept(final Object o) {
        this.explain("accept, value " + o);
        this.stack.pop();
        if (this.stackBreak) {
            try {
                this.wait();
            }
            catch (InterruptedException ex) {}
        }
    }
    
    public void error(final String s) {
        this.explain("error message");
    }
    
    public void reject() {
        this.explain("reject");
        this.stack.pop();
        if (this.stackBreak) {
            try {
                this.wait();
            }
            catch (InterruptedException ex) {}
        }
    }
    
    public synchronized void push(final int n, final Object o) {
        this.stack.push(n, o);
        if (this.stackBreak) {
            try {
                this.wait();
            }
            catch (InterruptedException ex) {}
        }
    }
    
    public synchronized void pop(final int n) {
        this.explain("pop " + n + " on error");
        this.stack.pop(1);
        if (this.stackBreak) {
            try {
                this.wait();
            }
            catch (InterruptedException ex) {}
        }
    }
    
    public synchronized void reduce(final int n, final int n2, final int n3, final String s, final int n4) {
        this.explain("reduce (" + n3 + "), uncover " + n2 + "\n(" + n3 + ") " + s);
        this.stack.pop(n4);
        if (this.stackBreak) {
            try {
                this.wait();
            }
            catch (InterruptedException ex) {}
        }
    }
    
    protected static final class Stack extends ScrollPane
    {
        protected static final GridBagConstraints level;
        protected final Font font;
        protected final Panel panel;
        
        public Stack(final Font font) {
            super(0);
            this.font = font;
            this.setSize(50, 100);
            this.add(this.panel = new Panel(new GridBagLayout()));
        }
        
        public void push(final int n, final Object o) {
            final Panel panel = new Panel(new BorderLayout());
            final TextField textField;
            panel.add(textField = new TextField("" + n, 5), "West");
            textField.setEditable(false);
            textField.setBackground(Color.white);
            textField.setFont(this.font);
            final TextField textField2;
            panel.add(textField2 = new TextField((o != null) ? o.toString() : ""), "Center");
            textField2.setEditable(false);
            textField2.setBackground(Color.white);
            textField2.setFont(this.font);
            this.panel.add(panel, Stack.level, 0);
            this.validate();
        }
        
        public void pop(final int n) {
            for (int i = 0; i < n; ++i) {
                this.panel.remove(0);
                this.validate();
            }
        }
        
        public void pop() {
            this.panel.removeAll();
            this.validate();
        }
        
        static {
            level = new GridBagConstraints();
            final GridBagConstraints level2 = Stack.level;
            final GridBagConstraints level3 = Stack.level;
            level2.anchor = 11;
            final GridBagConstraints level4 = Stack.level;
            final GridBagConstraints level5 = Stack.level;
            level4.fill = 2;
            Stack.level.gridheight = 1;
            final GridBagConstraints level6 = Stack.level;
            final GridBagConstraints level7 = Stack.level;
            level6.gridwidth = 0;
            Stack.level.gridx = 0;
            final GridBagConstraints level8 = Stack.level;
            final GridBagConstraints level9 = Stack.level;
            level8.gridy = -1;
            Stack.level.weightx = 1.0;
        }
    }
}
