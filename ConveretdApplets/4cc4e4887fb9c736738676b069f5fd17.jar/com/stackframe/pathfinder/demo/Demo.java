// 
// Decompiled by Procyon v0.5.30
// 

package com.stackframe.pathfinder.demo;

import javax.swing.Action;
import javax.swing.AbstractAction;
import javax.swing.KeyStroke;
import javax.swing.JMenuItem;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JFrame;
import java.io.OutputStream;
import java.io.ObjectOutputStream;
import java.io.FileOutputStream;
import java.io.File;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.FileInputStream;
import javax.swing.JFileChooser;
import java.util.Iterator;
import javax.swing.ListCellRenderer;
import javax.swing.JList;
import javax.swing.DefaultListCellRenderer;
import java.util.ServiceLoader;
import java.util.Vector;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JPanel;
import java.awt.Component;
import javax.swing.border.Border;
import javax.swing.border.BevelBorder;
import java.awt.LayoutManager;
import java.awt.BorderLayout;
import java.util.concurrent.Executors;
import java.util.Collection;
import java.util.Collections;
import java.util.concurrent.Callable;
import java.lang.reflect.InvocationTargetException;
import com.stackframe.pathfinder.PathEvent;
import com.stackframe.pathfinder.PathListener;
import com.stackframe.pathfinder.PathFinder;
import javax.swing.SwingUtilities;
import java.util.List;
import java.util.concurrent.Future;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JComboBox;
import javax.swing.JButton;
import java.util.concurrent.ExecutorService;
import javax.swing.JApplet;

public class Demo extends JApplet
{
    private final ExecutorService executor;
    private Map map;
    private MapView mapView;
    private final JButton startButton;
    private final JButton newButton;
    private final JButton randomize;
    private Location start;
    private Location goal;
    private JComboBox algo;
    private static final String idle = "PathFinder idle.";
    private final JLabel status;
    private long startTime;
    private JCheckBox showSteps;
    private static final int bounds = 50;
    private Future<List<Location>> futurePath;
    
    private void handleDone(final List<Location> path) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                final long stopTime = System.currentTimeMillis();
                Demo.this.mapView.setPath(path);
                String message;
                if (path == null) {
                    message = new String("No path found in ");
                }
                else {
                    message = new String("Path of " + path.size() + " steps found in ");
                }
                message = message + (stopTime - Demo.this.startTime) + " milliseconds.";
                Demo.this.status.setText(message);
                Demo.this.mapView.setEnabled(true);
                Demo.this.newButton.setEnabled(true);
                Demo.this.randomize.setEnabled(true);
                Demo.this.algo.setEnabled(true);
                Demo.this.startButton.setText("Find Path");
            }
        });
    }
    
    private void connect(final PathFinder<Location> pathFinder) {
        pathFinder.addPathListener(new PathListener<Location>() {
            public void considered(final PathEvent<Location> pathEvent) {
                try {
                    SwingUtilities.invokeAndWait(new Runnable() {
                        public void run() {
                            if (Demo.this.showSteps.isSelected()) {
                                Demo.this.mapView.setPath(pathEvent.getPath());
                            }
                        }
                    });
                }
                catch (InterruptedException ie) {}
                catch (InvocationTargetException ite) {
                    throw new AssertionError((Object)ite);
                }
            }
        });
    }
    
    private void doNewSearch() {
        this.startButton.setText("Abort");
        this.randomize.setEnabled(false);
        this.algo.setEnabled(false);
        this.newButton.setEnabled(false);
        this.mapView.setEnabled(false);
        this.status.setText("PathFinder working ...");
        this.mapView.setPath(null);
        this.startTime = System.currentTimeMillis();
        this.futurePath = this.executor.submit((Callable<List<Location>>)new Callable<List<Location>>() {
            public List<Location> call() {
                final PathFinder<Location> pathfinder = (PathFinder<Location>)Demo.this.algo.getSelectedItem();
                final List<Location> path = pathfinder.findPath(Demo.this.map.getLocations(), Demo.this.start, Collections.singleton(Demo.this.goal));
                Demo.this.handleDone(path);
                return path;
            }
        });
    }
    
    public Demo() {
        this.executor = Executors.newSingleThreadExecutor();
        this.startButton = new JButton("Find Path");
        this.newButton = new JButton("Clear");
        this.randomize = new JButton("Randomize");
        this.status = new JLabel("PathFinder idle.");
        this.showSteps = new JCheckBox("Show Steps");
        this.map = new Map(50);
        this.getContentPane().setLayout(new BorderLayout());
        this.start = this.map.getLocation(0, 0);
        this.goal = this.map.getLocation(this.map.getXSize() - 1, this.map.getYSize() - 1);
        (this.mapView = new MapView(this.map, this.start, this.goal)).setBorder(new BevelBorder(1));
        this.getContentPane().add(this.mapView, "Center");
        this.getContentPane().add(this.status, "South");
        final JPanel controlBox = new JPanel();
        controlBox.add(this.startButton);
        controlBox.add(this.showSteps);
        this.getContentPane().add(controlBox, "North");
        this.startButton.addActionListener(new ActionListener() {
            public void actionPerformed(final ActionEvent actionEvent) {
                if (Demo.this.startButton.getText().equals("Find Path")) {
                    Demo.this.doNewSearch();
                }
                else {
                    final PathFinder pathFinder = (PathFinder)Demo.this.algo.getSelectedItem();
                    pathFinder.cancel();
                    Demo.this.futurePath.cancel(true);
                }
            }
        });
        controlBox.add(this.newButton);
        controlBox.add(this.randomize);
        this.newButton.addActionListener(new ActionListener() {
            public void actionPerformed(final ActionEvent actionEvent) {
                Demo.this.map = new Map(50);
                Demo.this.start = Demo.this.map.getLocation(0, 0);
                Demo.this.goal = Demo.this.map.getLocation(Demo.this.map.getXSize() - 1, Demo.this.map.getYSize() - 1);
                Demo.this.mapView.setStart(Demo.this.start);
                Demo.this.mapView.setGoal(Demo.this.goal);
                Demo.this.mapView.setPath(null);
                Demo.this.mapView.setMap(Demo.this.map);
            }
        });
        this.randomize.addActionListener(new ActionListener() {
            public void actionPerformed(final ActionEvent actionEvent) {
                Demo.this.map = new Map(50);
                Demo.this.start = Demo.this.map.getLocation(0, 0);
                Demo.this.goal = Demo.this.map.getLocation(Demo.this.map.getXSize() - 1, Demo.this.map.getYSize() - 1);
                Demo.this.map.randomize();
                Demo.this.mapView.setStart(Demo.this.start);
                Demo.this.mapView.setGoal(Demo.this.goal);
                Demo.this.mapView.setMap(Demo.this.map);
                Demo.this.mapView.setPath(null);
            }
        });
        final Vector<PathFinder<Location>> algorithms = new Vector<PathFinder<Location>>();
        final ServiceLoader<PathFinder> pathFinders = (ServiceLoader<PathFinder>)ServiceLoader.load(PathFinder.class);
        for (final PathFinder p : pathFinders) {
            this.addAlgorithm(p, algorithms);
        }
        (this.algo = new JComboBox((Vector<E>)algorithms)).setRenderer(new DefaultListCellRenderer() {
            public Component getListCellRendererComponent(final JList list, final Object value, final int index, final boolean isSelected, final boolean cellHasFocus) {
                final PathFinder pathFinder = (PathFinder)value;
                return super.getListCellRendererComponent(list, pathFinder.name(), index, isSelected, cellHasFocus);
            }
        });
        controlBox.add(this.algo);
    }
    
    private void addAlgorithm(final PathFinder<Location> pathFinder, final Vector<PathFinder<Location>> algorithms) {
        algorithms.add(pathFinder);
        this.connect(pathFinder);
    }
    
    public void loadMap() {
        final String dir = System.getProperty("user.dir");
        final JFileChooser chooser = new JFileChooser(dir);
        final int result = chooser.showOpenDialog(this.getParent());
        if (result == 0) {
            try {
                final File file = chooser.getSelectedFile();
                final FileInputStream stream = new FileInputStream(file);
                final ObjectInputStream oos = new ObjectInputStream(stream);
                this.map = (Map)oos.readObject();
                this.start = this.map.getLocation(0, 0);
                this.goal = this.map.getLocation(this.map.getXSize() - 1, this.map.getYSize() - 1);
                this.mapView.setPath(null);
                this.mapView.setGoal(this.goal);
                this.mapView.setStart(this.start);
                this.mapView.setMap(this.map);
            }
            catch (Exception e) {
                System.err.println("Could not load: " + e);
                e.printStackTrace();
            }
        }
    }
    
    public void saveMap() {
        final String dir = System.getProperty("user.dir");
        final JFileChooser chooser = new JFileChooser(dir);
        final int result = chooser.showSaveDialog(this.getParent());
        if (result == 0) {
            try {
                final File file = chooser.getSelectedFile();
                final FileOutputStream os = new FileOutputStream(file);
                final ObjectOutputStream oos = new ObjectOutputStream(os);
                oos.writeObject(this.map);
            }
            catch (Exception e) {
                System.err.println("Could not save: " + e);
                e.printStackTrace();
            }
        }
    }
    
    public static void main(final String[] args) {
        final Demo demo = new Demo();
        final JFrame frame = new JFrame("PathFinder");
        final JMenuBar menuBar = new JMenuBar();
        final JMenu file = new JMenu("File");
        file.setMnemonic(70);
        final JMenuItem load = new JMenuItem("Open");
        load.setMnemonic(79);
        load.setAccelerator(KeyStroke.getKeyStroke(79, frame.getToolkit().getMenuShortcutKeyMask(), false));
        file.add(load);
        load.addActionListener(new ActionListener() {
            public void actionPerformed(final ActionEvent actionEvent) {
                demo.loadMap();
            }
        });
        final JMenuItem save = new JMenuItem("Save");
        save.setMnemonic(83);
        save.setAccelerator(KeyStroke.getKeyStroke(83, frame.getToolkit().getMenuShortcutKeyMask(), false));
        file.add(save);
        save.addActionListener(new ActionListener() {
            public void actionPerformed(final ActionEvent actionEvent) {
                demo.saveMap();
            }
        });
        final JMenuItem exit = file.add(new AbstractAction("Exit") {
            public void actionPerformed(final ActionEvent evt) {
                System.exit(0);
            }
        });
        exit.setMnemonic(88);
        exit.setAccelerator(KeyStroke.getKeyStroke(81, frame.getToolkit().getMenuShortcutKeyMask(), false));
        menuBar.add(file);
        demo.setJMenuBar(menuBar);
        frame.getContentPane().add(demo);
        frame.pack();
        frame.setVisible(true);
        frame.setDefaultCloseOperation(3);
    }
}
