import java.io.DataOutputStream;
import java.net.Socket;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.WindowListener;
import java.awt.Insets;
import java.awt.LayoutManager;
import java.awt.Font;
import java.util.Date;
import java.awt.Component;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Button;
import java.awt.Color;
import java.awt.event.ActionListener;

// 
// Decompiled by Procyon v0.5.30
// 

class Screen extends FrameCloser implements ActionListener
{
    public static final int CANVAS_WIDTH = 546;
    public static final int CANVAS_HEIGHT = 270;
    public static final int BUTTON_WIDTH = 72;
    public static final int BUTTON_HEIGHT = 24;
    public static final int TIME_OUT = 50;
    public static final int DELAY = 500;
    private static final int INSET = 3;
    private static final int FRAME_WIDTH = 650;
    private static final int FRAME_HEIGHT = 396;
    private static final int TRAVERSAL = 1;
    private static final int HEAP_PRIORITY_QUEUE = 2;
    private static final int HEAP_SORT = 3;
    private static final int BINARY_SEARCH_TREE = 4;
    private static final int HEIGHT_BALANCING = 5;
    private static final int TREE_CATEGORIES = 6;
    private static final int ADJACENCY_MATRIX = 7;
    private static final int SEARCH = 8;
    private static final int GRAPH_CATEGORIES = 9;
    private static final int QUAD_SORTS = 10;
    private static final int EFF_SORTS = 11;
    public static final Color DARK_BLUE;
    public static final Color LIGHT_BLUE;
    public static final Color DARK_RED;
    private static final Color BACKGROUND_COLOR;
    private static final Color CANVAS_COLOR;
    public static Screen screen;
    public static AnimatorThread thread;
    public static PromptPanel prompt;
    public static AnswerPanel answer;
    public static Interaction interaction;
    public static DrawingCanvas canvas;
    public static ScreenPanel panel;
    public static Controls controls;
    public static ProgressPanel progress;
    public static int panelNo;
    private long startTime;
    private int helpCount;
    private int progressCount;
    private String helpHiddenHelp;
    private Button helpButton;
    private SizedComponent sizedHelpButton;
    private boolean helpOn;
    private String progressHiddenHelp;
    private String progressShowingHelp;
    private Button progressButton;
    private SizedComponent sizedProgressButton;
    private ProgressFrame frame;
    private boolean progressShowing;
    private GridBagLayout gridBag;
    private GridBagConstraints constraints;
    
    static {
        DARK_BLUE = new Color(0, 0, 128);
        LIGHT_BLUE = new Color(192, 192, 255);
        DARK_RED = new Color(128, 0, 64);
        BACKGROUND_COLOR = Screen.DARK_BLUE;
        CANVAS_COLOR = Screen.LIGHT_BLUE;
    }
    
    public Screen(final int panelNo) {
        super("Interactive Data Structure Visualizations - Duane J. Jarc - Version 12/3/2005");
        this.helpCount = 0;
        this.progressCount = 0;
        this.helpHiddenHelp = "The help feature is on. Moving the mouse over any of the controls will display a help window such as this one. Clicking this button again will turn off this help.";
        this.helpButton = new Button("Help");
        this.sizedHelpButton = new SizedComponent(this.helpButton, 72, 24, this.helpHiddenHelp, "Northeast");
        this.helpOn = false;
        this.progressHiddenHelp = "Displays the progress window which shows the number of times you have watched an operation and the number of correct and incorrect responses.  Completed assignments are submitted from the progress window.";
        this.progressShowingHelp = "Closes the progress window.";
        this.progressButton = new Button("Progress");
        this.progressShowing = false;
        this.gridBag = new GridBagLayout();
        this.constraints = new GridBagConstraints();
        Screen.screen = this;
        Screen.panelNo = panelNo;
        this.startTime = new Date().getTime();
        this.setFont(new Font("Dialog", 0, 12));
        this.setLayout(this.gridBag);
        this.setSize(650, 396);
        (Screen.thread = new AnimatorThread()).start();
        final String progressHelp = this.progressHiddenHelp;
        this.sizedProgressButton = new SizedComponent(this.progressButton, 72, 24, progressHelp, "Southeast");
        Screen.canvas = new DrawingCanvas(Screen.CANVAS_COLOR);
        this.setBackground(Screen.BACKGROUND_COLOR);
        Screen.controls = new Controls();
        switch (panelNo) {
            case 1: {
                final String[] traversalButtons = { "Make Tree", "Preorder", "Inorder", "Postorder", "Level Order" };
                Screen.progress = new ProgressPanel(traversalButtons, 1, 25);
                Screen.panel = new TraversalPanel(traversalButtons, 1, Screen.BACKGROUND_COLOR);
                break;
            }
            case 2: {
                final String[] pqHeapButtons = { "Enqueue", "Dequeue" };
                Screen.progress = new ProgressPanel(pqHeapButtons, 0, 25);
                Screen.panel = new PQHeapPanel(pqHeapButtons, 0, Screen.BACKGROUND_COLOR);
                break;
            }
            case 3: {
                final String[] heapSortButtons = { "Make Tree", "Sort" };
                Screen.progress = new ProgressPanel(heapSortButtons, 1, 25);
                Screen.panel = new HeapSortPanel(heapSortButtons, 1, Screen.BACKGROUND_COLOR);
                break;
            }
            case 4: {
                final String[] bsTreeButtons = { "Insert", "Delete", "Find" };
                Screen.progress = new ProgressPanel(bsTreeButtons, 0, 15);
                Screen.panel = new BSTreePanel(bsTreeButtons, 0, Screen.BACKGROUND_COLOR);
                break;
            }
            case 5: {
                final String[] hbTreeButtons = { "Make Tree", "Heights", "Bal Factors", "Balanced?", "Is HB-n?" };
                Screen.progress = new ProgressPanel(hbTreeButtons, 1, 10);
                Screen.panel = new HBTreePanel(hbTreeButtons, 1, Screen.BACKGROUND_COLOR);
                break;
            }
            case 6: {
                final String[] treesButtons = { "Make Tree", "Complete?", "Is Full?", "Is BS Tree?", "Is Heap?", "Is AVL?" };
                Screen.progress = new ProgressPanel(treesButtons, 1, 10);
                Screen.panel = new TreesPanel(treesButtons, 1, Screen.BACKGROUND_COLOR);
                break;
            }
            case 7: {
                final String[] matrixButtons = { "Make One", "Create Other" };
                Screen.progress = new ProgressPanel(matrixButtons, 1, 25);
                Screen.panel = new MatrixPanel(matrixButtons, 1, Screen.BACKGROUND_COLOR);
                break;
            }
            case 8: {
                final String[] searchButtons = { "Make Graph", "Depth First", "Breadth First" };
                Screen.progress = new ProgressPanel(searchButtons, 1, 25);
                Screen.panel = new SearchPanel(searchButtons, 1, Screen.BACKGROUND_COLOR);
                break;
            }
            case 9: {
                final String[] graphsButtons = { "Make Graph", "Connected?", "Has Cycles?", "Is Tree?" };
                Screen.progress = new ProgressPanel(graphsButtons, 1, 10);
                Screen.panel = new GraphsPanel(graphsButtons, 1, Screen.BACKGROUND_COLOR);
                break;
            }
            case 10: {
                final String[] quadSortButtons = { "Make Array", "Bubble", "Insertion", "Selection" };
                Screen.progress = new ProgressPanel(quadSortButtons, 1, 25);
                Screen.panel = new QuadSortPanel(quadSortButtons, 1, Screen.BACKGROUND_COLOR);
                break;
            }
            case 11: {
                final String[] effSortButtons = { "Make Array", "Merge", "Quick" };
                Screen.progress = new ProgressPanel(effSortButtons, 1, 25);
                Screen.panel = new EffSortPanel(effSortButtons, 1, Screen.BACKGROUND_COLOR);
                break;
            }
            default: {
                return;
            }
        }
        this.constraints.gridx = 0;
        this.constraints.gridy = 0;
        this.constraints.insets = new Insets(3, 3, 3, 3);
        Screen.prompt = new PromptPanel();
        Screen.answer = new AnswerPanel();
        Screen.interaction = new Interaction(Screen.prompt, Screen.answer);
        this.gridBag.setConstraints(Screen.interaction, this.constraints);
        this.add(Screen.interaction);
        this.constraints.gridx = 1;
        this.gridBag.setConstraints(this.sizedHelpButton, this.constraints);
        this.add(this.sizedHelpButton);
        this.constraints.gridx = 0;
        this.constraints.gridy = 1;
        this.gridBag.setConstraints(Screen.canvas, this.constraints);
        this.add(Screen.canvas);
        Screen.canvas.setVisible(true);
        this.constraints.gridx = 1;
        this.gridBag.setConstraints(Screen.panel, this.constraints);
        this.add(Screen.panel);
        Screen.panel.setVisible(true);
        this.constraints.gridx = 0;
        this.constraints.gridy = 2;
        this.gridBag.setConstraints(Screen.controls, this.constraints);
        this.add(Screen.controls);
        Screen.controls.setVisible(true);
        this.constraints.gridx = 1;
        this.gridBag.setConstraints(this.sizedProgressButton, this.constraints);
        this.add(this.sizedProgressButton);
        this.frame = new ProgressFrame(Screen.progress);
        this.progressButton.addActionListener(this);
        this.helpButton.addActionListener(this);
        this.addWindowListener(this);
    }
    
    public void actionPerformed(final ActionEvent event) {
        if (event.getActionCommand().equals(this.helpButton.getLabel())) {
            this.helpOn ^= true;
            if (this.helpOn) {
                this.sizedHelpButton.showHelp();
                Screen.interaction.displayMessage("");
            }
            else {
                Screen.interaction.displayMessage("Help Is Now Turned Off");
            }
            ++this.helpCount;
        }
        if (event.getActionCommand().equals(this.progressButton.getLabel())) {
            this.clickProgress();
        }
    }
    
    public void clickProgress() {
        if (this.progressShowing) {
            this.frame.setVisible(false);
            this.progressShowing = false;
            this.sizedProgressButton.changeMessage(this.progressHiddenHelp);
        }
        else {
            this.frame.setVisible(true);
            this.progressShowing = true;
            this.sizedProgressButton.changeMessage(this.progressShowingHelp);
            ++this.progressCount;
        }
    }
    
    public void closeProgress() {
        if (this.progressShowing) {
            this.frame.setVisible(false);
        }
    }
    
    public void closeWindow() {
        this.closeProgress();
        super.closeWindow();
    }
    
    public Dimension getMinimumSize() {
        return new Dimension(650, 396);
    }
    
    public boolean isHelpOn() {
        return this.helpOn;
    }
    
    public void sendLog() {
        final long elapsedTime = (new Date().getTime() - this.startTime) / 1000L;
        String eventLog = String.valueOf(elapsedTime) + "_";
        eventLog = String.valueOf(eventLog) + "L:" + Screen.panelNo + "_";
        eventLog = String.valueOf(eventLog) + Screen.progress.getLog();
        eventLog = String.valueOf(eventLog) + Screen.panel.getLog();
        eventLog = String.valueOf(eventLog) + Screen.controls.getLog();
        eventLog = String.valueOf(eventLog) + "H:" + this.helpCount + "_P:" + this.progressCount;
        try {
            final Socket socket = new Socket("tangle.seas.gwu.edu", 80);
            final DataOutputStream out = new DataOutputStream(socket.getOutputStream());
            out.writeBytes("GET /cgi-bin/cgiwrap/idsv/log?" + eventLog + "\n\n");
            out.close();
            socket.close();
        }
        catch (Exception ex) {}
    }
}
