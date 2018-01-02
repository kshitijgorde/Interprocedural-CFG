import java.awt.Color;
import java.util.Enumeration;
import java.util.Vector;

// 
// Decompiled by Procyon v0.5.30
// 

class SearchGraph extends Graph
{
    private static final Position LIST_POSITION;
    private boolean arrowShowing;
    private DrawableCircle circle;
    private DrawableArrow arrow;
    private DrawableString nodeLabel;
    private TraversalList searchList;
    
    static {
        LIST_POSITION = new Position(0, 250);
    }
    
    public SearchGraph(final Position position, final int rows, final int cols, final boolean named) {
        super(position, rows, cols, named);
        this.circle = new DrawableCircle(new Position(0, 0), 10, false, false);
        this.arrow = new DrawableArrow();
        this.nodeLabel = new DrawableString(new Position(0, 0));
        this.searchList = new TraversalList(SearchGraph.LIST_POSITION, super.noOfNodes);
    }
    
    private void depthFirstSearch(final int current) {
        if (!super.marked[current]) {
            this.arrow.setDirection(this.nodePosition(current), true);
            final Position[] positions = { this.nodePosition(current) };
            this.arrow.moveTo(positions);
            this.traceAndMark(current);
            final Vector nextQueue = new Vector();
            this.setNexts(current, nextQueue);
            while (!this.empty(nextQueue)) {
                final int next = this.getNext(nextQueue);
                this.depthFirstSearch(next);
                this.arrow.setDirection(this.nodePosition(current), false);
                positions[0] = this.nodePosition(current);
                this.arrow.moveTo(positions);
                this.traceAndMark(current);
            }
        }
    }
    
    public void doBreadthFirstSearch() {
        int depth = 0;
        this.searchList.init();
        int current = this.initSearch(false);
        final Vector[] nextQueues = new Vector[super.noOfNodes];
        nextQueues[depth] = new Vector();
        final Position[] positions = { this.nodePosition(current) };
        this.circle.moveTo(positions);
        this.traceAndMark(current);
        this.setNexts(current, nextQueues[depth]);
        nextQueues[++depth] = new Vector();
        while (!this.empty(nextQueues[depth - 1])) {
            current = this.getNext(nextQueues[depth - 1]);
            positions[0] = this.nodePosition(current);
            this.circle.moveTo(positions);
            this.traceAndMark(current);
            this.setNexts(current, nextQueues[depth]);
            if (nextQueues[depth - 1].size() == 0) {
                nextQueues[++depth] = new Vector();
            }
        }
        this.circle.hide();
        super.selectAny = true;
    }
    
    public void doDepthFirstSearch() {
        this.searchList.init();
        this.depthFirstSearch(this.initSearch(true));
        this.arrow.hide();
        super.selectAny = true;
    }
    
    private boolean empty(final Vector nextQueue) {
        boolean isEmpty = true;
        final Enumeration list = nextQueue.elements();
        while (list.hasMoreElements()) {
            final Integer object = list.nextElement();
            final int value = object;
            if (!super.marked[value]) {
                isEmpty = false;
            }
        }
        return isEmpty;
    }
    
    private int getNext(final Vector nextQueue) {
        int next = -1;
        boolean validResponse = false;
        if (Screen.panel.isInteractive()) {
            super.currentNextQueue = nextQueue;
            Screen.canvas.acceptSelection(this);
            if ((next = Screen.canvas.awaitSelection("Click A Correct Next Node")) >= 0) {
                nextQueue.removeElement(new Integer(next));
                Screen.interaction.displayMessage("Very Good, That's Correct");
                Screen.prompt.displayPrompt("");
                validResponse = true;
            }
            if (validResponse) {
                Screen.progress.oneMoreRight(Screen.thread.button());
            }
            else {
                Screen.progress.oneMoreWrong(Screen.thread.button());
            }
        }
        else {
            Screen.controls.nextStep(true, true);
        }
        if (!validResponse) {
            final int choice = this.randomNo(nextQueue.size());
            final Integer node = nextQueue.elementAt(choice);
            nextQueue.removeElementAt(choice);
            next = node;
        }
        return next;
    }
    
    private int getStart() {
        for (int node = 0; node < super.noOfNodes; ++node) {
            super.marked[node] = false;
        }
        if (!Screen.panel.userPicks()) {
            int start;
            do {
                start = this.randomNo(super.noOfNodes);
            } while (!super.nodes[start]);
            return start;
        }
        Screen.canvas.acceptSelection(this);
        int start;
        if ((start = Screen.canvas.awaitSelection("Click Starting Node")) > 0) {
            Screen.prompt.displayPrompt("");
            return start;
        }
        for (int node2 = 0; node2 < super.noOfNodes; ++node2) {
            if (super.nodes[node2] && !super.marked[node2]) {
                return node2;
            }
        }
        return -1;
    }
    
    public void init() {
        Screen.canvas.add(this.circle);
        Screen.canvas.add(this.arrow);
        Screen.canvas.add(this.searchList);
        Screen.canvas.add(this.nodeLabel);
        this.searchList.setColor(Screen.DARK_RED);
        this.searchList.show();
        this.circle.setColor(Color.white);
        this.arrow.setColor(Color.white);
    }
    
    private int initSearch(final boolean directional) {
        final int first = this.getStart();
        this.searchList.init();
        if (directional) {
            this.arrow.reposition(this.nodePosition(first));
            this.arrowShowing = false;
        }
        else {
            this.circle.reposition(this.nodePosition(first));
            this.circle.show();
            this.arrowShowing = true;
        }
        super.selectAny = false;
        return first;
    }
    
    private void setNexts(final int current, final Vector nextQueue) {
        for (int next = 0; next < super.noOfNodes; ++next) {
            if (super.edges[current][next] && !super.marked[next] && !nextQueue.contains(new Integer(next))) {
                nextQueue.addElement(new Integer(next));
            }
        }
    }
    
    private void traceAndMark(final int node) {
        Screen.canvas.awaitMovingCompletion();
        if (!super.marked[node]) {
            Screen.canvas.highlight(new DrawableCross(this.nodePosition(node)), Color.black, false);
            this.nodeLabel.reposition(this.namePosition(node));
            this.searchList.append(super.names[node], this.nodeLabel);
        }
        if (!this.arrowShowing) {
            this.arrow.show();
            this.arrowShowing = false;
        }
        super.marked[node] = true;
    }
}
