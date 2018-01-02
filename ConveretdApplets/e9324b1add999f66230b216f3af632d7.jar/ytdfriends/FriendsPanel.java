// 
// Decompiled by Procyon v0.5.30
// 

package ytdfriends;

import edu.berkeley.guir.prefusex.force.ForceSimulator;
import edu.berkeley.guir.prefusex.layout.ForceDirectedLayout;
import java.awt.geom.Rectangle2D;
import edu.berkeley.guir.prefuse.Display;
import edu.berkeley.guir.prefuse.util.display.DisplayLib;
import edu.berkeley.guir.prefuse.action.assignment.Layout;
import edu.berkeley.guir.prefuse.event.FocusEvent;
import edu.berkeley.guir.prefuse.event.FocusListener;
import edu.berkeley.guir.prefuse.action.animate.PolarLocationAnimator;
import edu.berkeley.guir.prefuse.action.AbstractAction;
import ytdfriends.action.InvertToggleAction;
import edu.berkeley.guir.prefuse.action.animate.SizeAnimator;
import ytdfriends.action.linkage.ReleaseFixedAction;
import edu.berkeley.guir.prefuse.action.animate.LocationAnimator;
import edu.berkeley.guir.prefuse.activity.Pacer;
import edu.berkeley.guir.prefuse.activity.SlowInSlowOutPacer;
import ytdfriends.action.linkage.FriendsCircleLayout;
import ytdfriends.action.linkage.LinkageFilter;
import ytdfriends.action.HighlightSettingAction;
import edu.berkeley.guir.prefuse.action.RepaintAction;
import ytdfriends.action.FriendsFontFunction;
import ytdfriends.action.CommunityLayout;
import edu.berkeley.guir.prefuse.action.Action;
import ytdfriends.action.CommunityConstructor;
import ytdfriends.action.CommunityEdgeLabeler;
import ytdfriends.action.CommunityFilter;
import ytdfriends.action.FriendsBrowsingColorFunction;
import ytdfriends.action.FriendsLayout;
import edu.berkeley.guir.prefusex.layout.FruchtermanReingoldLayout;
import edu.berkeley.guir.prefuse.action.filter.GarbageCollector;
import ytdfriends.action.AuraFilter;
import edu.berkeley.guir.prefuse.action.filter.FisheyeGraphFilter;
import edu.berkeley.guir.prefuse.render.RendererFactory;
import edu.berkeley.guir.prefuse.graph.GraphLib;
import edu.berkeley.guir.prefuse.graph.DefaultEdge;
import edu.berkeley.guir.prefuse.graph.DefaultNode;
import edu.berkeley.guir.prefuse.graph.Edge;
import java.util.Iterator;
import java.util.Collection;
import java.awt.EventQueue;
import edu.berkeley.guir.prefuse.graph.Entity;
import java.util.ArrayList;
import ytdfriends.action.FriendsSizeFunction;
import ytdfriends.ui.Legend;
import ytdfriends.action.FriendsXRayColorFunction;
import edu.berkeley.guir.prefuse.action.ActionSwitch;
import ytdfriends.action.HighlightAction;
import java.awt.geom.Point2D;
import java.awt.Point;
import edu.berkeley.guir.prefuse.NodeItem;
import edu.berkeley.guir.prefuse.VisualItem;
import ytdfriends.render.FriendsImageRenderer;
import java.awt.Image;
import edu.berkeley.guir.prefuse.FocusManager;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.imageio.ImageIO;
import java.net.URL;
import java.awt.Dimension;
import javax.swing.Box;
import java.awt.Component;
import java.awt.Container;
import javax.swing.BoxLayout;
import java.awt.Color;
import java.awt.event.MouseListener;
import ytdfriends.controls.FocusRequester;
import edu.berkeley.guir.prefusex.controls.PanControl;
import edu.berkeley.guir.prefusex.controls.DragControl;
import edu.berkeley.guir.prefuse.activity.Activity;
import ytdfriends.controls.HighlightControl;
import edu.berkeley.guir.prefusex.controls.FocusControl;
import edu.berkeley.guir.prefuse.event.ControlListener;
import ytdfriends.controls.ExpansionControl;
import edu.berkeley.guir.prefusex.community.CommunitySet;
import edu.berkeley.guir.prefuse.focus.FocusSet;
import edu.berkeley.guir.prefuse.focus.DefaultFocusSet;
import java.util.Comparator;
import edu.berkeley.guir.prefuse.graph.Graph;
import edu.berkeley.guir.prefuse.graph.DefaultGraph;
import java.awt.LayoutManager;
import java.awt.BorderLayout;
import ytdfriends.ui.ProfileCard;
import edu.berkeley.guir.prefuse.graph.Node;
import edu.berkeley.guir.prefuse.action.ActionMap;
import ytdfriends.render.FriendsRendererFactory;
import edu.berkeley.guir.prefuse.activity.ActionList;
import edu.berkeley.guir.prefuse.ItemRegistry;
import javax.swing.JLabel;
import ytdfriends.util.FriendsSearchPanel;
import ytdfriends.ui.CommunityPanel;
import ytdfriends.ui.ZoomPanel;
import ytdfriends.ui.GuiPanel;
import javax.swing.JPanel;

public class FriendsPanel extends JPanel
{
    private static final long serialVersionUID = 2034593846914822427L;
    public FriendsApplet fApplet;
    private FriendsDisplay display;
    private JPanel northContainer;
    private JPanel southContainer;
    private GuiPanel guiPanel;
    private ZoomPanel zoomPanel;
    public CommunityPanel commPanel;
    public FriendsSearchPanel searchPanel;
    public JLabel loadingLabel;
    private boolean loadingPics;
    public ItemRegistry registry;
    private ActionList redraw;
    private ActionList forces;
    private ActionList altForces;
    private ActionList altAnimate;
    private ActionList filter;
    private ActionList magnify;
    private ActionList aura;
    private ActionList community;
    private ActionList highlight;
    private ActionList linkLayout;
    private ActionList unfix;
    private ActionList border;
    private boolean xrayMode;
    public FriendsRendererFactory renderers;
    private ActionMap actionMap;
    public static final String CLICK_KEY = "clicked";
    public static final String MOUSE_KEY = "moused";
    public static final String SEARCH_KEY = "search";
    public static final String HIGHLIGHT_KEY = "highlight";
    public static final String COMMUNITY_KEY = "community";
    public static final String LOADING;
    public static final String AURA_CLASS = "aura";
    public static final int BROWSE_MODE = 0;
    public static final int COMPARE_MODE = 1;
    private boolean animate;
    public Node initial;
    public ProfileCard profileCard;
    public String baseUrl;
    private String loadedGraph;
    public static final String ID_FIELD = "uid";
    public static final String API_URL = "api/get_ytd_friends/?appkey=titiit&appid=23YTD6I02353PH4O6N75E726CC622466D2533E8Z1&section=normal&pid=";
    
    static {
        LOADING = new String("IMAGES ARE LOADING...");
    }
    
    public FriendsPanel(final FriendsApplet applet) {
        super(new BorderLayout());
        this.loadingPics = false;
        this.xrayMode = false;
        this.animate = true;
        (this.registry = new ItemRegistry((Graph)new DefaultGraph())).setItemComparator((Comparator)new FriendsItemComparator());
        this.registry.addItemClass("aura", (Class)DecoratorItem.class);
        final FocusManager fmanager = this.registry.getFocusManager();
        fmanager.putFocusSet((Object)"clicked", (FocusSet)new DefaultFocusSet());
        fmanager.putFocusSet((Object)"moused", (FocusSet)new DefaultFocusSet());
        fmanager.putFocusSet((Object)"highlight", (FocusSet)new DefaultFocusSet());
        fmanager.putFocusSet((Object)"community", (FocusSet)new CommunitySet(true));
        fmanager.putFocusSet((Object)"search", (FocusSet)new DefaultFocusSet());
        this.fApplet = applet;
        final URL base = applet.getCodeBase();
        this.baseUrl = String.valueOf(base.getProtocol()) + "://" + base.getHost() + '/';
        this.setDisplay(new FriendsDisplay(this));
        this.initPrefuse();
        this.display.addControlListener((ControlListener)new ExpansionControl(this, 1));
        this.display.addControlListener((ControlListener)new FocusControl(1, (Object)"clicked"));
        this.display.addControlListener((ControlListener)new HighlightControl((Activity)this.highlight, "moused"));
        this.display.addControlListener((ControlListener)new DragControl((Activity)this.redraw, true));
        this.display.addControlListener((ControlListener)new PanControl(true));
        this.display.addMouseListener((MouseListener)new FocusRequester());
        this.guiPanel = new GuiPanel(this);
        this.commPanel = new CommunityPanel(this);
        this.zoomPanel = new ZoomPanel(this);
        this.searchPanel = new FriendsSearchPanel(this);
        this.getDisplay().setSize(600, 600);
        (this.loadingLabel = new JLabel(FriendsPanel.LOADING)).setVisible(true);
        this.loadingLabel.setForeground(Color.RED);
        (this.northContainer = new JPanel()).setLayout(new BoxLayout(this.northContainer, 0));
        this.northContainer.add(this.guiPanel);
        this.northContainer.add(Box.createHorizontalGlue());
        this.northContainer.add(this.loadingLabel);
        this.northContainer.add(this.searchPanel);
        this.northContainer.setBackground(Color.WHITE);
        final Dimension zd = this.zoomPanel.getPreferredSize();
        this.zoomPanel.setPreferredSize(new Dimension(40, zd.height));
        Image logo = null;
        try {
            logo = ImageIO.read(new URL(String.valueOf(this.baseUrl) + "images/logo_s.gif"));
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        (this.southContainer = new JPanel()).setLayout(new BoxLayout(this.southContainer, 0));
        if (logo != null) {
            this.southContainer.add(new JLabel(new ImageIcon(logo)));
        }
        this.southContainer.add(Box.createHorizontalGlue());
        this.southContainer.add(this.commPanel);
        this.southContainer.setBackground(Color.WHITE);
        this.add(this.northContainer, "North");
        this.add((Component)this.display, "Center");
        this.add(this.zoomPanel, "West");
        this.add(this.southContainer, "South");
        this.profileCard = new ProfileCard(this);
        this.display.add((Component)this.profileCard, 0);
        this.profileCard.setVisible(false);
        this.display.validate();
        this.setShowImages(true);
        this.setSize(600, 600);
        this.loadGraph(this.fApplet.uid);
    }
    
    private void drawLoading() {
        if (!this.loadingPics) {
            final Thread t = new Thread("ImageLoadingLabel") {
                public void run() {
                    NodeItem nodeItem;
                    for (nodeItem = null; nodeItem == null; nodeItem = FriendsPanel.this.registry.getNodeItem(FriendsPanel.this.initial)) {}
                    final FriendsImageRenderer renderer = (FriendsImageRenderer)FriendsPanel.this.renderers.getRenderer((VisualItem)nodeItem);
                    FriendsPanel.this.loadingLabel.setVisible(true);
                    try {
                        Thread.sleep(1000L);
                    }
                    catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    while (!renderer.getImageFactory().hasFinishedLoading()) {
                        FriendsPanel.this.loadingLabel.setVisible(true);
                        try {
                            Thread.sleep(1000L);
                        }
                        catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        FriendsPanel.this.loadingLabel.setVisible(false);
                        try {
                            Thread.sleep(1000L);
                        }
                        catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                    FriendsPanel.this.loadingLabel.setVisible(false);
                }
            };
            t.setPriority(1);
            t.start();
        }
    }
    
    public void zoom(final double value) {
        final int x = this.display.getWidth() / 2;
        final int y = this.display.getHeight() / 2;
        this.display.zoom((Point2D)new Point(x, y), value);
    }
    
    public void zoomIn(final int value) {
        this.zoom(value / (value - 1));
    }
    
    public void zoomOut(final int value) {
        this.zoom(value / (value + 1));
    }
    
    public void setShowImages(final boolean s) {
        ((FriendsRendererFactory)this.registry.getRendererFactory()).setDrawImages(s);
    }
    
    public void setLinkHighlighting(final boolean s) {
        ((HighlightAction)this.actionMap.get((Object)"highlight")).setShowEdges(s);
    }
    
    public void setPassHighlightTroughFocus(final boolean s) {
        ((HighlightAction)this.actionMap.get((Object)"highlight")).setSkipFoci(s);
    }
    
    public void setHighlightHops(final int h) {
        ((HighlightAction)this.actionMap.get((Object)"highlight")).setHops(h);
    }
    
    public void setMode(final int mode) {
        if (mode != 0 && mode != 1) {
            return;
        }
        final boolean b = mode == 0;
        this.xrayMode = !b;
        final Color bg = b ? Color.WHITE : Color.BLACK;
        final Color fg = b ? Color.BLACK : Color.WHITE;
        this.setBackground(bg);
        this.setForeground(fg);
        this.display.setBackground(bg);
        this.display.setForeground(fg);
        final ActionSwitch as = (ActionSwitch)this.actionMap.get((Object)"colorSwitch");
        as.setSwitchValue((int)(b ? 0 : 1));
        if (!b) {
            final FriendsXRayColorFunction cf = (FriendsXRayColorFunction)this.actionMap.get((Object)"compareColors");
            final Legend l = new Legend(cf.getLabels(), cf.getColorMap());
            this.display.setLegend(l);
        }
        else {
            this.display.setLegend(null);
        }
    }
    
    public void setMagnify(final boolean state) {
        final FriendsSizeFunction sizeFunc = (FriendsSizeFunction)this.actionMap.get((Object)"size");
        sizeFunc.setMagnify(state);
        this.magnify.runNow();
        if (state) {
            this.border.runNow();
        }
    }
    
    public void runStaticLayout() {
        this.altAnimate.runAfter((Activity)this.altForces);
        this.altForces.runNow();
    }
    
    public void runFilter() {
        this.filter.runNow();
    }
    
    public ItemRegistry getItemRegistry() {
        return this.registry;
    }
    
    public boolean isXRayMode() {
        return this.xrayMode;
    }
    
    public void loadGraph(final String startUID) {
        try {
            this.loadFriendGraph(startUID, true, null, null, 0);
        }
        catch (Exception e) {
            e.printStackTrace();
            FriendsLib.defaultError(this, "Couldn't load input graph.");
            return;
        }
        this.initial = this.getInitialNode(startUID);
        this.registry.getDefaultFocusSet().set((Entity)this.initial);
        this.registry.getFocusManager().getFocusSet((Object)"clicked").set((Entity)this.initial);
        this.centerDisplay();
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                NodeItem nodeItem;
                for (nodeItem = null; nodeItem == null; nodeItem = FriendsPanel.this.registry.getNodeItem(FriendsPanel.this.initial)) {}
                FriendsPanel.this.profileCard.loadNode(nodeItem);
                FriendsPanel.this.profileCard.setVisible(true);
            }
        });
    }
    
    public void loadFriendsGraph() {
        final Thread t = new Thread("FriendsGraphLoader-") {
            public void run() {
                final Graph graph = FriendsPanel.this.registry.getGraph();
                final Iterator<Node> nodes = (Iterator<Node>)graph.getNodes();
                final ArrayList<Node> queue = new ArrayList<Node>();
                nodes.next();
                while (nodes.hasNext()) {
                    final Node node = nodes.next();
                    queue.add(node);
                }
                final ArrayList<Node> running = new ArrayList<Node>(queue);
                String lastUid = null;
                FriendsPanel.access$0(FriendsPanel.this, null);
                while (!queue.isEmpty()) {
                    final Node qn = queue.remove(queue.size() - 1);
                    final String nUid = qn.getAttribute("uid");
                    if (!nUid.matches(FriendsPanel.this.initial.getAttribute("uid"))) {
                        FriendsPanel.this.loadFriendGraph(nUid, false, running, qn, 0);
                        lastUid = nUid;
                    }
                }
                while (!running.isEmpty()) {}
                FriendsPanel.this.filter.runNow();
            }
        };
        t.start();
    }
    
    public void loadFriendGraph(final String startUid, final boolean runFilter, final ArrayList<Node> running, final Node qn, final int page) {
        try {
            final Graph g = FriendsLib.loadGraph(new URL(String.valueOf(this.baseUrl) + "api/get_ytd_friends/?appkey=titiit&appid=23YTD6I02353PH4O6N75E726CC622466D2533E8Z1&section=normal&pid=" + startUid + "&page=" + String.valueOf(page)));
            this.loadGraphInRegistry(g, startUid);
            this.drawLoading();
            this.loadedGraph = startUid;
            if (runFilter) {
                this.filter.runNow();
            }
            final int nCount = g.getNodeCount() - 1;
            final Node startNode = this.getGraphNode(g, startUid);
            final int nFriends = Integer.valueOf(startNode.getAttribute("nfriends"));
            startNode.setAttribute("page", String.valueOf(page));
            if (running != null) {
                running.remove(qn);
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    private Node getGraphNode(final Graph graph, final String uid) {
        synchronized (graph) {
            final Iterator<Node> nodes = (Iterator<Node>)graph.getNodes();
            while (nodes.hasNext()) {
                final Node node = nodes.next();
                if (node.getAttribute("uid").matches(uid)) {
                    // monitorexit(graph)
                    return node;
                }
            }
        }
        return null;
    }
    
    private Edge getGraphEdge(final Graph graph, final String uid1, final String uid2) {
        synchronized (graph) {
            final Iterator<Edge> edges = (Iterator<Edge>)graph.getEdges();
            while (edges.hasNext()) {
                final Edge edge = edges.next();
                if ((edge.getFirstNode().getAttribute("uid").matches(uid1) && edge.getSecondNode().getAttribute("uid").matches(uid2)) || (edge.getFirstNode().getAttribute("uid").matches(uid2) && edge.getSecondNode().getAttribute("uid").matches(uid1))) {
                    // monitorexit(graph)
                    return edge;
                }
            }
        }
        return null;
    }
    
    private void loadGraphInRegistry(final Graph newG, final String startUid) {
        final Graph graph = this.registry.getGraph();
        if (graph.getNodeCount() > 0) {
            this.removeNeighboors(graph, startUid);
            final Iterator<Node> nodes = (Iterator<Node>)newG.getNodes();
            final Node root = this.getGraphNode(graph, startUid);
            final Node first = nodes.next();
            root.setAttribute("nfriends", first.getAttribute("nfriends"));
            while (nodes.hasNext()) {
                final Node node = nodes.next();
                Node newN = this.getGraphNode(graph, node.getAttribute("uid"));
                if (newN == null) {
                    newN = (Node)new DefaultNode();
                    newN.setAttributes(node.getAttributes());
                    graph.addNode(newN);
                }
                final Edge oldE = this.getGraphEdge(newG, root.getAttribute("uid"), newN.getAttribute("uid"));
                final Edge newE = (Edge)new DefaultEdge(root, newN, graph.isDirected());
                newE.setAttribute("network", oldE.getAttribute("network"));
                graph.addEdge(newE);
            }
        }
        else {
            this.registry.setGraph(newG);
        }
    }
    
    private void removeNeighboors(final Graph graph, final String startUid) {
        final Node root = this.getGraphNode(graph, startUid);
        final Iterator<Node> nite = (Iterator<Node>)root.getNeighbors();
        final ArrayList<Node> neighbors = new ArrayList<Node>();
        while (nite.hasNext()) {
            final Node neighbor = nite.next();
            neighbors.add(neighbor);
        }
        final FocusSet fs = this.registry.getFocusManager().getDefaultFocusSet();
        while (!neighbors.isEmpty()) {
            final Node neighbor2 = neighbors.remove(0);
            if (!fs.contains((Entity)neighbor2) && neighbor2.getEdgeCount() == 1) {
                graph.removeNode(neighbor2);
            }
        }
    }
    
    private Node getInitialNode(final String uid) {
        Node r = null;
        if (uid == null) {
            r = GraphLib.getMostConnectedNodes(this.registry.getGraph())[0];
        }
        else {
            final Node[] matches = GraphLib.search(this.registry.getGraph(), "uid", uid);
            if (matches.length > 0) {
                r = matches[0];
            }
            else {
                r = GraphLib.getMostConnectedNodes(this.registry.getGraph())[0];
            }
        }
        return r;
    }
    
    private void initPrefuse() {
        this.actionMap = new ActionMap();
        this.renderers = new FriendsRendererFactory(this);
        this.registry.setRendererFactory((RendererFactory)this.renderers);
        final FisheyeGraphFilter feyeFilter = new FisheyeGraphFilter(-1, true, false);
        final AuraFilter auraFilter = new AuraFilter();
        final GarbageCollector gcFilter = new GarbageCollector(new String[] { "node", "edge" });
        feyeFilter.setEdgesInteractive(false);
        final Layout frLayout = (Layout)new FruchtermanReingoldLayout(400);
        final Layout fdLayout = (Layout)new FriendsLayout();
        final FriendsBrowsingColorFunction vcolor = new FriendsBrowsingColorFunction();
        final FriendsXRayColorFunction xcolor = new FriendsXRayColorFunction();
        final HighlightAction hilite = new HighlightAction(2);
        final CommunityFilter commFilter = new CommunityFilter("community");
        final CommunityEdgeLabeler commLabeler = new CommunityEdgeLabeler("community");
        final CommunityConstructor commConstruct = new CommunityConstructor("community", vcolor);
        final ActionSwitch colorSwitch = new ActionSwitch(new Action[] { vcolor, xcolor }, 0);
        (this.redraw = new ActionList(this.registry)).add((Action)new CommunityLayout("community"));
        this.redraw.add((Action)colorSwitch);
        this.redraw.add(this.actionMap.put((Object)"fonts", (Action)new FriendsFontFunction()));
        this.redraw.add((Action)new RepaintAction());
        final ActionList linkageList = new ActionList(this.registry);
        linkageList.add((Action)new HighlightSettingAction(-1));
        linkageList.add((Action)new LinkageFilter(false));
        (this.community = new ActionList(this.registry)).add((Action)commConstruct);
        this.community.add((Action)commFilter);
        this.community.add((Action)commLabeler);
        (this.linkLayout = new ActionList(this.registry)).add((Action)new FriendsCircleLayout());
        final ActionList linkAnimate = new ActionList(this.registry, 800L);
        linkAnimate.setPacingFunction((Pacer)new SlowInSlowOutPacer());
        linkAnimate.add((Action)new LocationAnimator());
        linkAnimate.alwaysRunAfter((Activity)this.linkLayout);
        (this.unfix = new ActionList(this.registry)).add((Action)new ReleaseFixedAction());
        final ActionList unfixAnimate = new ActionList(this.registry, 800L);
        unfixAnimate.add((Action)new LocationAnimator());
        unfixAnimate.alwaysRunAfter((Activity)this.unfix);
        (this.filter = new ActionList(this.registry)).add((Action)feyeFilter);
        this.filter.add((Action)linkageList);
        this.filter.add((Action)gcFilter);
        this.filter.add((Action)commFilter);
        this.filter.add((Action)commLabeler);
        this.filter.add((Action)auraFilter);
        this.filter.add((Action)colorSwitch);
        (this.aura = new ActionList(this.registry)).add((Action)auraFilter);
        this.aura.add((Action)hilite);
        this.aura.add((Action)colorSwitch);
        (this.highlight = new ActionList(this.registry)).add((Action)hilite);
        (this.magnify = new ActionList(this.registry)).add(this.actionMap.put((Object)"size", (Action)new FriendsSizeFunction()));
        final ActionList magnifyAnimate = new ActionList(this.registry, 1000L);
        magnifyAnimate.add((Action)new SizeAnimator());
        magnifyAnimate.alwaysRunAfter((Activity)this.magnify);
        (this.border = new ActionList(this.registry, 350L)).add((Action)new InvertToggleAction());
        (this.forces = new ActionList(this.registry, -1L, 20L)).add((Action)new AbstractAction() {
            public void run(final ItemRegistry registry, final double frac) {
                for (final Entity e : registry.getDefaultFocusSet()) {
                    if (e instanceof Node) {
                        final NodeItem item = registry.getNodeItem((Node)e);
                        if (item == null) {
                            continue;
                        }
                        item.setFixed(true);
                    }
                }
            }
        });
        this.forces.add((Action)fdLayout);
        this.forces.add((Action)this.redraw);
        (this.altForces = new ActionList(this.registry, 0L)).add((Action)frLayout);
        this.altForces.add((Action)colorSwitch);
        (this.altAnimate = new ActionList(this.registry, 2000L, 20L)).setPacingFunction((Pacer)new SlowInSlowOutPacer());
        this.altAnimate.add((Action)new PolarLocationAnimator());
        this.altAnimate.add((Action)this.redraw);
        this.actionMap.put((Object)"filter", (Action)feyeFilter);
        this.actionMap.put((Object)"linkSwitch", (Action)linkageList);
        this.actionMap.put((Object)"browseColors", (Action)vcolor);
        this.actionMap.put((Object)"compareColors", (Action)xcolor);
        this.actionMap.put((Object)"colorSwitch", (Action)colorSwitch);
        this.actionMap.put((Object)"dynamicForces", (Action)fdLayout);
        this.actionMap.put((Object)"staticForces", (Action)frLayout);
        this.actionMap.put((Object)"highlight", (Action)hilite);
        this.actionMap.put((Object)"commConstruct", (Action)commConstruct);
        final FocusSet defaultSet = this.registry.getDefaultFocusSet();
        defaultSet.addFocusListener((FocusListener)new FocusListener() {
            public void focusChanged(final FocusEvent e) {
                final Entity[] removed = e.getRemovedFoci();
                for (int i = 0; i < removed.length; ++i) {
                    final NodeItem n = FriendsPanel.this.registry.getNodeItem((Node)removed[i]);
                    if (n != null) {
                        n.setFixed(false);
                        n.setWasFixed(false);
                    }
                }
                final Node added = (Node)e.getFirstAdded();
                final NodeItem addedItem = FriendsPanel.this.registry.getNodeItem(added);
                if (addedItem != null) {
                    FriendsPanel.this.setReferrer(addedItem);
                }
                FriendsPanel.this.setLinkageMode(false);
                FriendsPanel.this.filter.runNow();
                FriendsPanel.this.setAnimate(true);
            }
        });
        final FocusManager fmanager = this.registry.getFocusManager();
        final FocusSet clickedSet = fmanager.getFocusSet((Object)"clicked");
        clickedSet.addFocusListener((FocusListener)new FocusListener() {
            public void focusChanged(final FocusEvent e) {
                final Node f = (Node)e.getFirstAdded();
                final NodeItem item = FriendsPanel.this.registry.getNodeItem(f);
                if (item != null) {
                    FriendsPanel.this.setReferrer(item);
                }
            }
        });
        final FocusSet searcher = fmanager.getFocusSet((Object)"search");
        searcher.addFocusListener((FocusListener)new FocusListener() {
            public void focusChanged(final FocusEvent e) {
                if (e.getAddedFoci().length > 0 || e.getRemovedFoci().length > 0) {
                    FriendsPanel.this.aura.runNow();
                }
            }
        });
    }
    
    public void search(final String query) {
        this.searchPanel.setQuery(query);
    }
    
    public void setLinkageMode(final boolean state) {
        final Action linkage = this.actionMap.get((Object)"linkSwitch");
        final boolean enabled = linkage.isEnabled();
        if (state != enabled) {
            this.highlight.setEnabled(!state);
            linkage.setEnabled(state);
            this.filter.runNow();
            if (!state) {
                this.highlight.runNow();
            }
        }
    }
    
    public boolean isAnimate() {
        return this.animate;
    }
    
    public void redraw() {
    }
    
    public void constructCommunities(final int idx) {
        ((CommunityConstructor)this.actionMap.get((Object)"commConstruct")).setIndex(idx);
        this.community.runNow();
    }
    
    public void resetDisplay() {
        final Rectangle2D b = DisplayLib.getNodeBounds(this.registry, 50.0);
        DisplayLib.fitViewToBounds((Display)this.display, b);
    }
    
    public void centerDisplay() {
        final Point2D centroid = DisplayLib.getCentroid(this.registry, this.registry.getDefaultFocusSet().iterator());
        this.centerDisplay(centroid);
    }
    
    public void centerDisplay(final Point2D p) {
        this.display.animatePanToAbs(p, 2000L);
    }
    
    public void setAnimate(final boolean b) {
        if (b) {
            this.forces.runNow();
        }
        else {
            this.forces.cancel();
        }
        this.animate = b;
    }
    
    public void setReferrer(final NodeItem item) {
        ((ForceDirectedLayout)this.actionMap.get((Object)"dynamicForces")).setReferrer(item);
    }
    
    public ForceSimulator getForceSimulator() {
        return ((FriendsLayout)this.actionMap.get((Object)"dynamicForces")).getForceSimulator();
    }
    
    public FriendsXRayColorFunction getComparisonColorFunction() {
        return (FriendsXRayColorFunction)this.actionMap.get((Object)"compareColors");
    }
    
    public FriendsBrowsingColorFunction getBrowsingColorFunction() {
        return (FriendsBrowsingColorFunction)this.actionMap.get((Object)"browseColors");
    }
    
    public void setDisplay(final FriendsDisplay display) {
        this.display = display;
    }
    
    public FriendsDisplay getDisplay() {
        return this.display;
    }
    
    static /* synthetic */ void access$0(final FriendsPanel friendsPanel, final String loadedGraph) {
        friendsPanel.loadedGraph = loadedGraph;
    }
}
