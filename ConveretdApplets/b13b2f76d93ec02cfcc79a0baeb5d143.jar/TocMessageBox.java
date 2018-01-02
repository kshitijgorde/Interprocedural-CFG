import java.awt.Event;
import java.awt.Frame;
import java.awt.Component;
import java.awt.Panel;
import java.awt.LayoutManager;
import java.awt.BorderLayout;
import java.awt.Graphics;
import java.awt.TextArea;
import java.awt.Button;
import java.awt.Dialog;

// 
// Decompiled by Procyon v0.5.30
// 

public class TocMessageBox extends Dialog
{
    private static final int DIALOG_WIDTH = 300;
    private static final int DIALOG_LINE_HEIGHT = 17;
    private static final int MAX_BUTTON_HEIGHT = 50;
    private static final int TITLEBAR_HEIGHT = 25;
    public static final int OK = 0;
    public static final int CANCEL = 1;
    public static final int MB_OK = 1;
    public static final int MB_OKCANCEL = 2;
    public static final int MB_YESNO = 3;
    public static final String DEFAULT_TITLE = "Error";
    public static final int DEFAULT_FLAGS = 1;
    private Button[] m_buttons;
    private TextArea m_TextArea;
    private int m_nRetval;
    private boolean m_bDone;
    private static final int VERT_BORDER = 10;
    private static final int HORZ_BORDER = 10;
    
    public void paintComponents(final Graphics graphics) {
        super.paintComponents(graphics);
    }
    
    public void initialize(final String[] array, final int n) {
        this.setLayout(new BorderLayout(10, 10));
        final Panel panel = new Panel();
        switch (n) {
            case 2: {
                (this.m_buttons = new Button[2])[0] = new Button("Ok");
                this.m_buttons[1] = new Button("Cancel");
                break;
            }
            case 3: {
                (this.m_buttons = new Button[2])[0] = new Button("Yes");
                this.m_buttons[1] = new Button("No");
                break;
            }
            default: {
                (this.m_buttons = new Button[1])[0] = new Button("Ok");
                break;
            }
        }
        for (int i = 0; i < this.m_buttons.length; ++i) {
            panel.add(this.m_buttons[i]);
        }
        this.add("South", panel);
        int max = 0;
        for (int j = 0; j < array.length; ++j) {
            max = Math.max(max, array[j].length());
        }
        (this.m_TextArea = new TextArea(array.length, max)).setEditable(false);
        for (int k = 0; k < array.length; ++k) {
            this.m_TextArea.appendText(array[k]);
            this.m_TextArea.appendText("\r\n");
        }
        this.add("North", this.m_TextArea);
        this.resize(300, 17 * array.length + 25 + 50);
    }
    
    public void EndDialog(final int nRetval) {
        this.m_nRetval = nRetval;
        this.m_bDone = true;
        this.dispose();
    }
    
    public TocMessageBox(final Frame frame, final String[] array, final String s, final int n) {
        super(frame, s, true);
        this.initialize(array, n);
    }
    
    public TocMessageBox(final Frame frame, final String[] array, final String s) {
        this(frame, array, s, 1);
    }
    
    public TocMessageBox(final Frame frame, final String[] array, final int n) {
        this(frame, array, "Error", n);
    }
    
    public TocMessageBox(final Frame frame, final String[] array) {
        this(frame, array, "Error", 1);
    }
    
    public TocMessageBox(final Frame frame, final String s, final String s2, final int n) {
        super(frame, s2, true);
        String substring = s;
        int n2 = 1;
        int index;
        while ((index = substring.indexOf(10)) != -1) {
            ++n2;
            substring = substring.substring(index + 1);
        }
        final String[] array = new String[n2];
        String substring2 = s;
        int n3 = 0;
        int index2;
        while ((index2 = substring2.indexOf(10)) != -1) {
            array[n3] = substring2.substring(0, index2);
            ++n3;
            substring2 = substring2.substring(index2 + 1);
        }
        array[n3] = substring2;
        this.initialize(array, n);
    }
    
    public TocMessageBox(final Frame frame, final String s, final String s2) {
        this(frame, s, s2, 1);
    }
    
    public TocMessageBox(final Frame frame, final String s, final int n) {
        this(frame, s, "Error", n);
    }
    
    public TocMessageBox(final Frame frame, final String s) {
        this(frame, s, "Error", 1);
    }
    
    public int Result() {
        return this.m_nRetval;
    }
    
    public void printComponents(final Graphics graphics) {
        super.printComponents(graphics);
    }
    
    public synchronized boolean action(final Event event, final Object o) {
        TocDebug.TraceL2("Action: " + event.target.getClass().getName());
        final Object target = event.target;
        for (int i = 0; i < this.m_buttons.length; ++i) {
            if (target == this.m_buttons[i]) {
                this.EndDialog(i);
                break;
            }
        }
        return super.action(event, o);
    }
    
    public static void Show(final Frame frame, final String s, final String s2, final int n) {
        new TocMessageBox(frame, s, s2, n).show();
    }
    
    public static void Show(final Frame frame, final String s, final String s2) {
        Show(frame, s, s2, 1);
    }
    
    public static void Show(final Frame frame, final String s, final int n) {
        Show(frame, s, "Error", n);
    }
    
    public static void Show(final Frame frame, final String s) {
        Show(frame, s, "Error", 1);
    }
    
    public synchronized boolean handleEvent(final Event event) {
        TocDebug.TraceL2("Event: " + event.id);
        if (event.id == 201) {
            this.EndDialog(1);
        }
        return super.handleEvent(event);
    }
}
