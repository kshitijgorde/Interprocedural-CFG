// 
// Decompiled by Procyon v0.5.30
// 

package javax.swing.text.html;

import java.awt.event.MouseEvent;
import java.awt.event.MouseAdapter;
import javax.swing.SwingUtilities;
import java.io.Writer;
import java.io.PrintWriter;
import java.io.OutputStreamWriter;
import java.net.URLConnection;
import java.io.IOException;
import javax.swing.JEditorPane;
import java.io.InputStream;
import java.net.URLEncoder;
import javax.accessibility.Accessible;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.JRadioButton;
import javax.swing.JToggleButton;
import javax.swing.JCheckBox;
import java.awt.event.MouseListener;
import java.net.MalformedURLException;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import java.net.URL;
import javax.swing.ButtonModel;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.text.AttributeSet;
import javax.swing.JTextArea;
import javax.swing.text.Document;
import javax.swing.JComboBox;
import javax.swing.ComboBoxModel;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;
import javax.swing.JList;
import javax.swing.ListModel;
import javax.swing.text.StyleConstants;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.Point;
import javax.swing.text.Element;
import java.awt.event.ActionListener;
import javax.swing.text.ComponentView;

public class FormView extends ComponentView implements ActionListener
{
    public static final String SUBMIT;
    public static final String RESET;
    
    static {
        SUBMIT = new String("Submit Query");
        RESET = new String("Reset");
    }
    
    public FormView(final Element element) {
        super(element);
    }
    
    public void actionPerformed(final ActionEvent actionEvent) {
        final Element element = this.getElement();
        final StringBuffer sb = new StringBuffer();
        final HTMLDocument htmlDocument = (HTMLDocument)this.getDocument();
        final String s = (String)element.getAttributes().getAttribute(HTML.Attribute.TYPE);
        if (s.equals("submit")) {
            htmlDocument.getFormData(sb, element);
            this.submitData(sb.toString());
        }
        else if (s.equals("reset")) {
            htmlDocument.resetForm(element);
        }
        else if (s.equals("text") || s.equals("password")) {
            if (htmlDocument.isLastTextOrPasswordField(element)) {
                htmlDocument.getFormData(sb, element);
                this.submitData(sb.toString());
            }
            else {
                this.getComponent().transferFocus();
            }
        }
    }
    
    protected Component createComponent() {
        final AttributeSet attributes = this.getElement().getAttributes();
        final HTML.Tag tag = (HTML.Tag)attributes.getAttribute(StyleConstants.NameAttribute);
        JComponent inputComponent = null;
        final Object attribute = attributes.getAttribute(StyleConstants.ModelAttribute);
        if (tag == HTML.Tag.INPUT) {
            inputComponent = this.createInputComponent(attributes, attribute);
        }
        else if (tag == HTML.Tag.SELECT) {
            if (attribute instanceof OptionListModel) {
                final JList list = new JList((ListModel)attribute);
                list.setVisibleRowCount(HTML.getIntegerAttributeValue(attributes, HTML.Attribute.SIZE, 1));
                list.setOpaque(false);
                list.setSelectionModel((ListSelectionModel)attribute);
                inputComponent = new JScrollPane(list);
            }
            else {
                inputComponent = new JComboBox((ComboBoxModel)attribute);
            }
        }
        else if (tag == HTML.Tag.TEXTAREA) {
            final JTextArea textArea = new JTextArea((Document)attribute);
            textArea.setRows(HTML.getIntegerAttributeValue(attributes, HTML.Attribute.ROWS, 0));
            textArea.setColumns(HTML.getIntegerAttributeValue(attributes, HTML.Attribute.COLS, 0));
            inputComponent = new JScrollPane(textArea, 22, 32);
        }
        if (inputComponent != null) {
            inputComponent.setAlignmentY(1.0f);
            inputComponent.setOpaque(false);
        }
        return inputComponent;
    }
    
    private JComponent createInputComponent(final AttributeSet set, final Object o) {
        Accessible accessible = null;
        final String s = (String)set.getAttribute(HTML.Attribute.TYPE);
        if (s.equals("submit") || s.equals("reset")) {
            String s2 = (String)set.getAttribute(HTML.Attribute.VALUE);
            if (s2 == null) {
                if (s.equals("submit")) {
                    s2 = FormView.SUBMIT;
                }
                else {
                    s2 = FormView.RESET;
                }
            }
            final JButton button = new JButton(s2);
            if (o != null) {
                button.setModel((ButtonModel)o);
                button.addActionListener(this);
            }
            accessible = button;
        }
        else if (s.equals("image")) {
            final String s3 = (String)set.getAttribute(HTML.Attribute.SRC);
            JButton button2;
            try {
                button2 = new JButton(new ImageIcon(new URL(((HTMLDocument)this.getElement().getDocument()).getBase(), s3)));
            }
            catch (MalformedURLException ex) {
                button2 = new JButton(s3);
            }
            if (o != null) {
                button2.setModel((ButtonModel)o);
                button2.addMouseListener(new MouseEventListener());
            }
            accessible = button2;
        }
        else if (s.equals("checkbox")) {
            accessible = new JCheckBox();
            if (o != null) {
                ((JToggleButton.ToggleButtonModel)o).setSelected(set.getAttribute(HTML.Attribute.CHECKED) != null);
                ((JCheckBox)accessible).setModel((ButtonModel)o);
            }
        }
        else if (s.equals("radio")) {
            accessible = new JRadioButton();
            if (o != null) {
                ((JToggleButton.ToggleButtonModel)o).setSelected(set.getAttribute(HTML.Attribute.CHECKED) != null);
                ((JRadioButton)accessible).setModel((ButtonModel)o);
            }
        }
        else if (s.equals("text")) {
            final JTextField textField = (JTextField)(accessible = new JTextField());
            if (o != null) {
                textField.setDocument((Document)o);
            }
            final int integerAttributeValue = HTML.getIntegerAttributeValue(set, HTML.Attribute.SIZE, -1);
            if (integerAttributeValue > 0) {
                textField.setColumns(integerAttributeValue);
            }
            final String text = (String)set.getAttribute(HTML.Attribute.VALUE);
            if (text != null) {
                textField.setText(text);
            }
            textField.addActionListener(this);
        }
        else if (s.equals("password")) {
            final JPasswordField passwordField = (JPasswordField)(accessible = new JPasswordField());
            if (o != null) {
                passwordField.setDocument((Document)o);
            }
            final int integerAttributeValue2 = HTML.getIntegerAttributeValue(set, HTML.Attribute.SIZE, -1);
            if (integerAttributeValue2 > 0) {
                passwordField.setColumns(integerAttributeValue2);
            }
            final String text2 = (String)set.getAttribute(HTML.Attribute.VALUE);
            if (text2 != null) {
                passwordField.setText(text2);
            }
            passwordField.addActionListener(this);
        }
        return (JComponent)accessible;
    }
    
    private String getImageData(final Point point) {
        final String string = String.valueOf(point.x) + ":" + point.y;
        int index = string.indexOf(58);
        final String substring = string.substring(0, index);
        final String substring2 = string.substring(++index);
        final String s = (String)this.getElement().getAttributes().getAttribute(HTML.Attribute.NAME);
        String s2;
        if (s == null || s.equals("")) {
            s2 = "x=" + substring + "&y=" + substring2;
        }
        else {
            final String encode = URLEncoder.encode(s);
            s2 = String.valueOf(encode) + ".x" + "=" + substring + "&" + encode + ".y" + "=" + substring2;
        }
        return s2;
    }
    
    protected void imageSubmit(final String s) {
        final StringBuffer sb = new StringBuffer();
        ((HTMLDocument)this.getElement().getDocument()).getFormData(sb, this.getElement());
        if (sb.length() > 0) {
            sb.append('&');
        }
        sb.append(s);
        this.submitData(sb.toString());
    }
    
    protected void submitData(final String s) {
        new SubmitThread(this.getElement(), s).start();
    }
    
    class SubmitThread extends Thread
    {
        String data;
        HTMLDocument hdoc;
        HTMLDocument newDoc;
        AttributeSet formAttr;
        InputStream in;
        
        public SubmitThread(final Element element, final String data) {
            this.data = data;
            this.hdoc = (HTMLDocument)element.getDocument();
            this.formAttr = this.hdoc.getFormAttributes(element.getAttributes());
        }
        
        public String getAction() {
            if (this.formAttr == null) {
                return null;
            }
            String substring = (String)this.formAttr.getAttribute(HTML.Attribute.ACTION);
            final int index = substring.indexOf(63);
            if (index != -1) {
                substring = substring.substring(0, index);
            }
            return substring;
        }
        
        String getMethod() {
            if (this.formAttr != null) {
                final String s = (String)this.formAttr.getAttribute(HTML.Attribute.METHOD);
                if (s != null) {
                    return s.toLowerCase();
                }
            }
            return null;
        }
        
        public void loadDocument() {
            final JEditorPane editorPane = (JEditorPane)FormView.this.getContainer();
            try {
                editorPane.read(this.in, (Object)this.newDoc);
            }
            catch (IOException ex) {}
        }
        
        public void postData(final URLConnection urlConnection, final String s) {
            urlConnection.setDoOutput(true);
            PrintWriter printWriter = null;
            try {
                printWriter = new PrintWriter(new OutputStreamWriter(urlConnection.getOutputStream()));
                printWriter.print(s);
                printWriter.flush();
            }
            catch (IOException ex) {}
            finally {
                if (printWriter != null) {
                    printWriter.close();
                }
            }
        }
        
        public void run() {
            if (this.data.length() > 0) {
                final String method = this.getMethod();
                final String action = this.getAction();
                try {
                    final URL base = this.hdoc.getBase();
                    URL url;
                    if (action == null) {
                        String s = base.getFile();
                        final int index = s.indexOf(63);
                        if (index >= 0) {
                            s = s.substring(0, index);
                        }
                        url = new URL(base.getProtocol(), base.getHost(), base.getPort(), s);
                    }
                    else {
                        url = new URL(base, action);
                    }
                    URL url2;
                    URLConnection urlConnection;
                    if ("post".equals(method)) {
                        url2 = url;
                        urlConnection = url2.openConnection();
                        this.postData(urlConnection, this.data);
                    }
                    else {
                        url2 = new URL(String.valueOf(String.valueOf(url)) + "?" + this.data);
                        urlConnection = url2.openConnection();
                    }
                    this.in = urlConnection.getInputStream();
                    (this.newDoc = (HTMLDocument)((HTMLEditorKit)((JEditorPane)FormView.this.getContainer()).getEditorKit()).createDefaultDocument()).putProperty("stream", url2);
                    SwingUtilities.invokeLater(new Runnable() {
                        private final /* synthetic */ SubmitThread this$1 = this$1;
                        
                        public void run() {
                            this.this$1.loadDocument();
                        }
                    });
                }
                catch (MalformedURLException ex) {}
                catch (IOException ex2) {}
            }
        }
    }
    
    protected class MouseEventListener extends MouseAdapter
    {
        public void mouseReleased(final MouseEvent mouseEvent) {
            FormView.this.imageSubmit(FormView.this.getImageData(mouseEvent.getPoint()));
        }
    }
}
