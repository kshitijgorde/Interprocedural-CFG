// 
// Decompiled by Procyon v0.5.30
// 

package edu.hws.GA;

import java.awt.event.ActionEvent;
import java.awt.Dimension;
import java.awt.event.ItemEvent;
import java.awt.MenuShortcut;
import java.awt.Menu;
import java.awt.MenuBar;
import java.awt.image.ImageObserver;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Color;
import java.awt.MenuItem;
import java.awt.event.ActionListener;
import java.awt.Panel;

class GACanvas extends Panel implements ActionListener, Runnable
{
    private GAFrame owner;
    private MenuItem stopGo;
    private RadioGroupMenu speedMenu;
    private RadioGroupMenu plantBirth;
    private RadioGroupMenu plantCount;
    private RadioGroupMenu plantRegrowth;
    private RadioGroupMenu populationM;
    private RadioGroupMenu eaterBirth;
    private RadioGroupMenu mutationProbability;
    private RadioGroupMenu crossoverProbability;
    private static Color green;
    private Image OSC;
    private Graphics OSG;
    private int width;
    private int height;
    private int population;
    private int defaultPlantCt;
    private double m_prob;
    private double c_prob;
    private Thread runner;
    private static final int GO = 0;
    private static final int PAUSE = 1;
    private static final int RESTART = 2;
    private int status;
    ReportWin rWin;
    private static final int turnLeft = 0;
    private static final int turnRight = 1;
    private static final int forward = 2;
    private static final int back = 3;
    private static final int seeWall = 0;
    private static final int seeEater = 1;
    private static final int seeSpace = 2;
    private static final int seePlant = 3;
    private static final int aWall = 9999;
    private static final int daysPerYear = 250;
    private static final int sourceCt = 20;
    int rows;
    int cols;
    int cellSize;
    int centerRow;
    int centerCol;
    int[][] cellContents;
    Eater[] eater;
    Eater[] neweater;
    int eaterCt;
    int totalScore;
    int highscore;
    double avgScore;
    double bestAvg;
    int[][] plant;
    int[][] plantSource;
    int actualPlantCt;
    int year;
    int day;
    long lastIdle;
    private boolean draw;
    
    GACanvas(final GAFrame owner) {
        this.plant = new int[501][2];
        this.plantSource = new int[21][2];
        this.draw = true;
        this.owner = owner;
        this.setBackground(Color.white);
        this.setFont(new Font("Monospaced", 0, 12));
    }
    
    public synchronized void paint(final Graphics graphics) {
        if (this.OSC == null) {
            graphics.setColor(Color.white);
            graphics.fillRect(0, 0, this.getSize().width, this.getSize().height);
            return;
        }
        graphics.drawImage(this.OSC, 0, 0, this);
    }
    
    public void update(final Graphics graphics) {
        this.paint(graphics);
    }
    
    public synchronized void start() {
        this.width = this.getSize().width;
        this.height = this.getSize().height;
        this.OSC = this.createImage(this.width, this.height);
        (this.OSG = this.OSC.getGraphics()).setColor(Color.white);
        this.OSG.fillRect(0, 0, this.width, this.height);
        this.OSG.setColor(Color.black);
        this.OSG.drawLine(0, this.height - 19, this.width, this.height - 19);
        this.cellSize = this.width / 60;
        this.cols = 60;
        this.rows = (this.height - 20) / this.cellSize;
        this.cellContents = new int[this.rows + 2][this.cols + 2];
        this.centerRow = this.rows / 2;
        this.centerCol = this.cols / 2;
        for (int i = 0; i < this.rows + 2; ++i) {
            this.cellContents[i][0] = 9999;
            this.cellContents[i][this.cols + 1] = 9999;
        }
        for (int j = 0; j < this.cols + 2; ++j) {
            this.cellContents[0][j] = 9999;
            this.cellContents[this.rows + 1][j] = 9999;
        }
        this.eater = new Eater[150];
        for (int k = 0; k < 150; ++k) {
            this.eater[k] = new Eater();
        }
        this.neweater = new Eater[150];
        for (int l = 0; l < 150; ++l) {
            this.neweater[l] = new Eater();
        }
        this.runner = new Thread(this);
        this.status = 2;
        this.runner.start();
    }
    
    public synchronized void stop() {
        if (this.runner != null && this.runner.isAlive()) {
            this.runner.stop();
        }
    }
    
    MenuBar getMenuBar() {
        final MenuBar menuBar = new MenuBar();
        final Menu menu = new Menu("Control");
        menu.addActionListener(this);
        menu.add(this.stopGo = new MenuItem("Pause", new MenuShortcut(80)));
        menu.add("Start From Scratch");
        menu.add("End the World");
        menuBar.add(menu);
        menuBar.add(this.speedMenu = new RadioGroupMenu("Speed", new String[] { "Fastest (Stats only)", "Fast", "Moderate", "Slow", "Slower" }, 1) {
            public void itemStateChanged(final ItemEvent itemEvent) {
                super.itemStateChanged(itemEvent);
                synchronized (GACanvas.this) {
                    if (GACanvas.this.draw && this.getSelectedIndex() == 0) {
                        GACanvas.access$1(GACanvas.this, false);
                        GACanvas.this.rWin.toFront();
                        if (GACanvas.this.OSG != null) {
                            GACanvas.this.OSG.setColor(Color.white);
                            GACanvas.this.OSG.fillRect(0, 0, GACanvas.this.width, GACanvas.this.height - 20);
                            GACanvas.this.OSG.fillRect(0, GACanvas.this.height - 18, GACanvas.this.width / 3, GACanvas.this.height - 18);
                            GACanvas.this.repaint();
                        }
                    }
                    else if (!GACanvas.this.draw && this.getSelectedIndex() > 0) {
                        GACanvas.access$1(GACanvas.this, true);
                        GACanvas.this.owner.toFront();
                        if (GACanvas.this.OSG != null) {
                            GACanvas.this.redrawAll(GACanvas.this.OSG);
                        }
                    }
                    GACanvas.this.notify();
                }
                // monitorexit(this.this$0)
            }
        });
        this.plantBirth = new RadioGroupMenu("Plants Grow", new String[] { "In Rows", "In Clumps", "At Random", "Along the Bottom", "Along the Edges" }, 1);
        this.plantCount = new RadioGroupMenu("Number Of Plants", new String[] { "50", "100", "150", "250", "500" }, 3);
        this.plantRegrowth = new RadioGroupMenu("When a Plant is Eaten", new String[] { "It grows back somewhere", "It grows back nearby", "It's Gone" }, 0);
        this.populationM = new RadioGroupMenu("Approximate Population", new String[] { "10", "20", "25", "30", "40", "50", "75", "100" }, 2);
        this.eaterBirth = new RadioGroupMenu("Eaters are Born", new String[] { "At the Center", "In a Corner", "At Random Location", "At Parent's Location" }, 2);
        this.mutationProbability = new RadioGroupMenu("Mutation Probability", new String[] { "Zero", "0.25%", "0.5%", "1%", "2%", "3%", "5%", "10%" }, 3);
        this.crossoverProbability = new RadioGroupMenu("Crossover Probability", new String[] { "Zero", "10%", "25%", "50%", "75%", "100%" }, 4);
        final Menu menu2 = new Menu("WorldDesign");
        menu2.add(this.plantBirth);
        menu2.add(this.plantCount);
        menu2.add(this.plantRegrowth);
        menu2.add(this.populationM);
        menu2.add(this.eaterBirth);
        menu2.add(this.mutationProbability);
        menu2.add(this.crossoverProbability);
        menuBar.add(menu2);
        return menuBar;
    }
    
    public Dimension getPreferredSize() {
        return new Dimension(600, 470);
    }
    
    public synchronized void actionPerformed(final ActionEvent actionEvent) {
        final String actionCommand = actionEvent.getActionCommand();
        System.out.println(actionCommand);
        if (actionCommand.equals("Pause")) {
            this.status = 1;
            this.stopGo.setLabel("Go");
            this.notify();
            return;
        }
        if (actionCommand.equals("Go")) {
            this.status = 0;
            this.stopGo.setLabel("Pause");
            this.notify();
            return;
        }
        if (actionCommand.equals("Start From Scratch")) {
            this.status = 2;
            this.stopGo.setLabel("Pause");
            this.notify();
            return;
        }
        if (actionCommand.equals("End the World")) {
            this.owner.close();
        }
    }
    
    public void run() {
        while (true) {
            synchronized (this) {
                while (this.status == 1) {
                    try {
                        this.wait();
                    }
                    catch (InterruptedException ex) {}
                }
                if (this.status == 2) {
                    this.year = 1;
                    this.day = 1;
                    this.rWin.clear();
                    this.newPopulation();
                    this.status = 0;
                }
            }
            if (this.status == 0) {
                int n = 0;
                final int selectedIndex = this.speedMenu.getSelectedIndex();
                if (this.day > 250) {
                    this.doEndOFYear();
                    if (selectedIndex == 0) {
                        n = 20;
                        final Graphics graphics = this.getGraphics();
                        this.showYearlyStats(graphics);
                        graphics.dispose();
                    }
                    ++this.year;
                    this.day = 1;
                }
                else {
                    this.doDay();
                    this.showDay(this.OSG);
                    ++this.day;
                    switch (selectedIndex) {
                        case 1: {
                            n = 10;
                            break;
                        }
                        case 2: {
                            n = 200;
                            break;
                        }
                        case 3: {
                            n = 400;
                            break;
                        }
                        case 4: {
                            n = 1000;
                            break;
                        }
                    }
                }
                if (selectedIndex > 0) {
                    this.repaint();
                }
                synchronized (this) {
                    if (n <= 0 || this.status != 0) {
                        continue;
                    }
                    try {
                        this.wait(n);
                    }
                    catch (InterruptedException ex2) {}
                }
            }
        }
    }
    
    private void redrawAll(final Graphics graphics) {
        graphics.setColor(Color.red);
        for (int i = 0; i < this.eaterCt; ++i) {
            this.DrawEater(this.eater[i].facing, this.eater[i].row, this.eater[i].col);
        }
        graphics.setColor(GACanvas.green);
        for (int j = 0; j < this.actualPlantCt; ++j) {
            this.DrawPlant(this.OSG, this.plant[j][0], this.plant[j][1]);
        }
    }
    
    private void getPopulation() {
        switch (this.populationM.getSelectedIndex()) {
            case 0: {
                this.population = 10;
            }
            case 1: {
                this.population = 20;
            }
            case 2: {
                this.population = 25;
            }
            case 3: {
                this.population = 30;
            }
            case 4: {
                this.population = 40;
            }
            case 5: {
                this.population = 50;
            }
            case 6: {
                this.population = 75;
            }
            case 7: {
                this.population = 100;
            }
            default: {}
        }
    }
    
    private void getDefaultPlantCount() {
        switch (this.plantCount.getSelectedIndex()) {
            case 0: {
                this.defaultPlantCt = 50;
            }
            case 1: {
                this.defaultPlantCt = 100;
            }
            case 2: {
                this.defaultPlantCt = 150;
            }
            case 3: {
                this.defaultPlantCt = 250;
            }
            case 4: {
                this.defaultPlantCt = 500;
            }
            default: {}
        }
    }
    
    private void getCrossoverProb() {
        switch (this.crossoverProbability.getSelectedIndex()) {
            case 0: {
                this.c_prob = 0.0;
            }
            case 1: {
                this.c_prob = 0.1;
            }
            case 2: {
                this.c_prob = 0.25;
            }
            case 3: {
                this.c_prob = 0.5;
            }
            case 4: {
                this.c_prob = 0.75;
            }
            case 5: {
                this.c_prob = 1.0;
            }
            default: {}
        }
    }
    
    private void getMutationProb() {
        switch (this.mutationProbability.getSelectedIndex()) {
            case 0: {
                this.m_prob = 0.0;
            }
            case 1: {
                this.m_prob = 0.0025;
            }
            case 2: {
                this.m_prob = 0.005;
            }
            case 3: {
                this.m_prob = 0.01;
            }
            case 4: {
                this.m_prob = 0.02;
            }
            case 5: {
                this.m_prob = 0.03;
            }
            case 6: {
                this.m_prob = 0.05;
            }
            case 7: {
                this.m_prob = 0.1;
            }
            default: {}
        }
    }
    
    private int[] FindSpace() {
        int n;
        int n2;
        do {
            n = (int)(Math.random() * this.rows) + 1;
            n2 = (int)(Math.random() * this.cols) + 1;
        } while (this.cellContents[n][n2] != 0);
        return new int[] { n, n2 };
    }
    
    private int[] FindSource() {
        int rows;
        int cols;
        if (this.plantBirth.getSelectedIndex() < 4) {
            do {
                rows = (int)(Math.random() * this.rows) + 1;
                cols = (int)(Math.random() * this.cols) + 1;
            } while (this.cellContents[rows][cols] != 0);
        }
        else {
            do {
                final int n = (int)(Math.random() * (2 * (this.rows + this.cols)));
                if (n < this.rows) {
                    rows = (int)(Math.random() * this.rows) + 1;
                    cols = 1;
                }
                else if (n < 2 * this.rows) {
                    rows = (int)(Math.random() * this.rows) + 1;
                    cols = this.cols;
                }
                else if (n < 2 * this.rows + this.cols) {
                    rows = 1;
                    cols = (int)(Math.random() * this.cols) + 1;
                }
                else {
                    rows = this.rows;
                    cols = (int)(Math.random() * this.cols) + 1;
                }
            } while (this.cellContents[rows][cols] != 0);
        }
        return new int[] { rows, cols };
    }
    
    private int[] FindSpaceNear(int n, int n2) {
        int i = 0;
        while (i < 20) {
            if (this.cellContents[n][n2] == 0) {
                return new int[] { n, n2 };
            }
            ++i;
            switch ((int)(Math.random() * 4.0)) {
                case 0: {
                    if (n > 1) {
                        --n;
                        continue;
                    }
                    continue;
                }
                case 1: {
                    if (n < this.rows) {
                        ++n;
                        continue;
                    }
                    continue;
                }
                case 2: {
                    if (n2 > 1) {
                        --n2;
                        continue;
                    }
                    continue;
                }
                default: {
                    if (n2 < this.cols) {
                        ++n2;
                        continue;
                    }
                    continue;
                }
            }
        }
        int n3 = n;
        int n4 = n2;
    Label_0183_Outer:
        while (this.cellContents[n3][n4] != 0) {
            while (true) {
                int j;
                do {
                    final int n5 = (int)(Math.random() * 3.0) - 1;
                    j = (int)(Math.random() * 3.0) - 1;
                    if (n5 == 0) {
                        continue Label_0183_Outer;
                    }
                    while (this.cellContents[n3][n4] != 0 && this.cellContents[n3][n4] != 9999) {
                        n3 += n5;
                        n4 += j;
                    }
                    if (this.cellContents[n3][n4] == 9999) {
                        n3 = n;
                        n4 = n2;
                        continue Label_0183_Outer;
                    }
                    continue Label_0183_Outer;
                } while (j == 0);
                continue;
            }
        }
        return new int[] { n3, n4 };
    }
    
    private synchronized void newPopulation() {
        this.getPopulation();
        this.getDefaultPlantCount();
        for (int i = 1; i <= this.rows; ++i) {
            for (int j = 1; j <= this.cols; ++j) {
                this.cellContents[i][j] = 0;
            }
        }
        for (int k = 1; k <= this.population; ++k) {
            this.eater[k].score = 1;
            this.eater[k].gene.randomize();
            this.eater[k].currentState = 0;
            this.eater[k].facing = (int)(Math.random() * 4.0);
            this.PutNewEater(k, false);
        }
        this.totalScore = this.population;
        this.eaterCt = this.population;
        this.bestAvg = 0.0;
        this.OSG.setColor(Color.white);
        this.OSG.fillRect(0, 0, this.width, this.height - 20);
        for (int l = 1; l <= 20; ++l) {
            this.plantSource[l] = this.FindSource();
        }
        for (int n = 1; n <= this.defaultPlantCt; ++n) {
            this.PutNewPlant(n);
        }
        this.actualPlantCt = this.defaultPlantCt;
    }
    
    private synchronized void doDay() {
        for (int i = 1; i <= this.eaterCt; ++i) {
            int row = this.eater[i].row;
            int col = this.eater[i].col;
            int facing = this.eater[i].facing;
            switch (this.eater[i].facing) {
                case 0: {
                    ++col;
                    break;
                }
                case 1: {
                    --row;
                    break;
                }
                case 2: {
                    --col;
                    break;
                }
                case 3: {
                    ++row;
                    break;
                }
            }
            final int n = this.cellContents[row][col];
            int n2;
            if (n == 0) {
                n2 = 2;
            }
            else if (n < 0) {
                n2 = 3;
            }
            else if (n == 9999) {
                n2 = 0;
            }
            else {
                n2 = 1;
            }
            final int currentState = this.eater[i].currentState;
            final byte currentState2 = this.eater[i].gene.newState[currentState][n2];
            final byte b = this.eater[i].gene.whatToDo[currentState][n2];
            this.eater[i].currentState = currentState2;
            int row2 = this.eater[i].row;
            int col2 = this.eater[i].col;
            switch (b) {
                case 0: {
                    if (--facing < 0) {
                        facing = 3;
                        break;
                    }
                    break;
                }
                case 1: {
                    if (++facing > 3) {
                        facing = 0;
                        break;
                    }
                    break;
                }
                case 2: {
                    row2 = row;
                    col2 = col;
                    if (this.cellContents[row2][col2] > 0) {
                        continue;
                    }
                    break;
                }
                case 3: {
                    int col3 = this.eater[i].col;
                    int row3 = this.eater[i].row;
                    switch (this.eater[i].facing) {
                        case 0: {
                            --col3;
                            break;
                        }
                        case 1: {
                            ++row3;
                            break;
                        }
                        case 2: {
                            ++col3;
                            break;
                        }
                        case 3: {
                            --row3;
                            break;
                        }
                    }
                    row2 = row3;
                    col2 = col3;
                    if (this.cellContents[row2][col2] > 0) {
                        continue;
                    }
                    break;
                }
            }
            this.MoveEater(i, facing, row2, col2);
        }
    }
    
    private synchronized void doEndOFYear() {
        this.OSG.setColor(Color.white);
        for (int i = 1; i <= this.actualPlantCt; ++i) {
            this.DrawPlant(this.OSG, this.plant[i][0], this.plant[i][1]);
            this.cellContents[this.plant[i][0]][this.plant[i][1]] = 0;
        }
        this.breed();
        this.getDefaultPlantCount();
        this.actualPlantCt = this.defaultPlantCt;
        for (int j = 1; j <= this.actualPlantCt; ++j) {
            this.PutNewPlant(j);
        }
        this.showYearlyStats(this.OSG);
        this.rWin.doReport(this.year, this.avgScore, this.highscore);
    }
    
    private synchronized void showYearlyStats(final Graphics graphics) {
        graphics.setColor(Color.white);
        graphics.fillRect(this.width / 3, this.height - 18, this.width, 18);
        graphics.setColor(Color.black);
        graphics.drawString("Year " + this.year + " Avg. Score = " + (int)(this.avgScore * 100.0) / 100.0 + ", High Score = " + this.highscore, this.width / 3, this.height - 4);
    }
    
    private synchronized void showDay(final Graphics graphics) {
        if (this.day == 0 || !this.draw) {
            return;
        }
        graphics.setColor(Color.white);
        graphics.fillRect(0, this.height - 18, this.width / 3, 18);
        graphics.setColor(Color.black);
        graphics.drawString("Year " + this.year + ", Day " + this.day, 5, this.height - 4);
    }
    
    private void DrawPlant(final Graphics graphics, final int n, final int n2) {
        graphics.drawOval((n2 - 1) * this.cellSize + 1, (n - 1) * this.cellSize + 1, this.cellSize - 3, this.cellSize - 3);
    }
    
    private void PutNewPlant(final int n) {
        int[] array = null;
        switch (this.plantBirth.getSelectedIndex()) {
            case 0: {
                int n2;
                if (Math.random() < 0.5) {
                    n2 = 1;
                }
                else {
                    n2 = -1;
                }
                int n3;
                int n4;
                int n5;
                do {
                    n5 = (int)(Math.random() * 20.0) + 1;
                    for (n4 = this.plantSource[n5][0], n3 = this.plantSource[n5][1]; n3 > 0 && n3 <= this.cols && this.cellContents[n4][n3] != 0; n3 += n2) {}
                } while (n3 <= 0 || n3 > this.cols || this.cellContents[n4][n3] != 0);
                array = new int[] { n4, n3 };
                if (Math.random() < 0.05) {
                    this.plantSource[n5] = this.FindSource();
                    break;
                }
                break;
            }
            case 1:
            case 4: {
                final int n6 = (int)(Math.random() * 20.0) + 1;
                array = this.FindSpaceNear(this.plantSource[n6][0], this.plantSource[n6][1]);
                if (Math.random() < 0.05) {
                    this.plantSource[n6] = this.FindSource();
                    break;
                }
                break;
            }
            case 2: {
                array = this.FindSpace();
                break;
            }
            default: {
                array = this.FindSpaceNear(this.rows, (int)(Math.random() * this.cols) + 1);
                break;
            }
        }
        this.plant[n] = array;
        this.cellContents[array[0]][array[1]] = -n;
        if (this.draw) {
            this.OSG.setColor(GACanvas.green);
            this.DrawPlant(this.OSG, array[0], array[1]);
        }
    }
    
    private void RemovePlant(final int n) {
        this.speedMenu.getSelectedIndex();
        final int selectedIndex = this.plantRegrowth.getSelectedIndex();
        if (this.draw) {
            this.OSG.setColor(Color.white);
            this.DrawPlant(this.OSG, this.plant[n][0], this.plant[n][1]);
        }
        if (selectedIndex == 0) {
            this.PutNewPlant(n);
        }
        else if (selectedIndex == 1) {
            final int[] findSpaceNear = this.FindSpaceNear(this.plant[n][0], this.plant[n][1]);
            this.plant[n] = findSpaceNear;
            this.cellContents[findSpaceNear[0]][findSpaceNear[1]] = -n;
        }
        else {
            this.cellContents[this.plant[this.actualPlantCt][0]][this.plant[this.actualPlantCt][1]] = -n;
            this.plant[n] = this.plant[this.actualPlantCt];
            --this.actualPlantCt;
        }
        if (this.draw && selectedIndex != 2) {
            this.OSG.setColor(GACanvas.green);
            this.DrawPlant(this.OSG, this.plant[n][0], this.plant[n][1]);
        }
    }
    
    private void DrawEater(final int n, final int n2, final int n3) {
        final int n4 = (n3 - 1) * this.cellSize + 1;
        final int n5 = (n2 - 1) * this.cellSize + 1;
        final int n6 = n4 + this.cellSize - 2;
        final int n7 = n5 + this.cellSize - 2;
        final int n8 = (n4 + n6) / 2;
        final int n9 = (n5 + n7) / 2;
        switch (n) {
            case 0: {
                this.OSG.drawLine(n4, n5, n4, n7 - 1);
                this.OSG.drawLine(n4, n9, n6 - 1, n9);
            }
            case 1: {
                this.OSG.drawLine(n4, n7 - 1, n6 - 1, n7 - 1);
                this.OSG.drawLine(n8, n5, n8, n7 - 1);
            }
            case 2: {
                this.OSG.drawLine(n6 - 1, n5, n6 - 1, n7 - 1);
                this.OSG.drawLine(n4, n9, n6 - 1, n9);
            }
            case 3: {
                this.OSG.drawLine(n4, n5, n6 - 1, n5);
                this.OSG.drawLine(n8, n5, n8, n7 - 1);
            }
            default: {}
        }
    }
    
    private void MoveEater(final int n, final int facing, final int row, final int col) {
        final int row2 = this.eater[n].row;
        final int col2 = this.eater[n].col;
        this.cellContents[row2][col2] = 0;
        if (this.draw) {
            this.OSG.setColor(Color.white);
            this.DrawEater(this.eater[n].facing, row2, col2);
        }
        this.eater[n].row = row;
        this.eater[n].col = col;
        this.eater[n].facing = facing;
        if (this.cellContents[row][col] < 0) {
            ++this.eater[n].score;
            ++this.totalScore;
            this.RemovePlant(-this.cellContents[row][col]);
        }
        this.cellContents[row][col] = n;
        if (this.draw) {
            this.OSG.setColor(Color.red);
            this.DrawEater(facing, row, col);
        }
    }
    
    private void PutNewEater(final int n, final boolean b) {
        int[] array = null;
        switch (this.eaterBirth.getSelectedIndex()) {
            case 0: {
                array = this.FindSpaceNear(this.centerRow + ((int)(Math.random() * 12.0) - 7), this.centerCol + ((int)(Math.random() * 12.0) - 7));
                break;
            }
            case 1: {
                array = this.FindSpaceNear((int)(Math.random() * 12.0) + 1, (int)(Math.random() * 12.0) + 1);
                break;
            }
            case 2: {
                array = this.FindSpace();
                break;
            }
            default: {
                if (b) {
                    array = this.FindSpace();
                    break;
                }
                array = this.FindSpaceNear(this.eater[n].row, this.eater[n].col);
                break;
            }
        }
        this.cellContents[array[0]][array[1]] = n;
        this.eater[n].row = array[0];
        this.eater[n].col = array[1];
        this.eater[n].facing = (int)(Math.random() * 4.0);
        if (this.draw) {
            this.OSG.setColor(Color.red);
            this.DrawEater(this.eater[n].facing, array[0], array[1]);
        }
    }
    
    private void breed() {
        final int[] array = new int[151];
        this.highscore = 0;
        this.OSG.setColor(Color.white);
        for (int i = 1; i <= this.eaterCt; ++i) {
            if (this.eater[i].score > this.highscore) {
                this.highscore = this.eater[i].score;
            }
            if (this.draw) {
                this.DrawEater(this.eater[i].facing, this.eater[i].row, this.eater[i].col);
            }
            this.cellContents[this.eater[i].row][this.eater[i].col] = 0;
        }
        this.avgScore = this.totalScore / this.eaterCt;
        this.totalScore = 0;
        int j = 0;
        this.getPopulation();
        final double n = 1.0 / this.avgScore * (this.population / this.eaterCt);
        do {
            for (int k = 1; k <= this.eaterCt; ++k) {
                double n2;
                for (n2 = this.eater[k].score * n; n2 >= 1.0 && j < 150; --n2, ++j, this.neweater[j].row = this.eater[k].row, this.neweater[j].col = this.eater[k].col, this.neweater[j].gene = this.eater[k].gene.copy(), this.neweater[j].score = 1, this.neweater[j].facing = (int)(Math.random() * 4.0), this.neweater[j].currentState = 0) {}
                if (Math.random() < n2 && j < 150) {
                    ++j;
                    this.neweater[j].row = this.eater[k].row;
                    this.neweater[j].col = this.eater[k].col;
                    this.neweater[j].gene = this.eater[k].gene.copy();
                    this.neweater[j].score = 1;
                    this.neweater[j].facing = (int)(Math.random() * 4.0);
                    this.neweater[j].currentState = 0;
                }
            }
        } while (j == 0);
        final Eater[] eater = this.eater;
        this.eater = this.neweater;
        this.neweater = eater;
        this.eaterCt = j;
        this.totalScore = this.eaterCt;
        this.getMutationProb();
        if (this.m_prob > 0.0) {
            for (int l = 1; l <= this.eaterCt; ++l) {
                for (int n3 = 0; n3 < 16; ++n3) {
                    for (int n4 = 0; n4 <= 3; ++n4) {
                        if (Math.random() < this.m_prob) {
                            this.eater[l].gene.newState[n3][n4] = (byte)(Math.random() * 16.0);
                        }
                        if (Math.random() < this.m_prob) {
                            this.eater[l].gene.whatToDo[n3][n4] = (byte)(Math.random() * 4.0);
                        }
                    }
                }
            }
        }
        this.getCrossoverProb();
        if (this.c_prob > 0.0) {
            for (int n5 = 1; n5 <= this.eaterCt; ++n5) {
                array[n5] = n5;
            }
            int eaterCt = this.eaterCt;
            int n6 = this.eaterCt - (int)(this.eaterCt * this.c_prob);
            if (n6 < 1) {
                n6 = 1;
            }
            while (eaterCt > n6) {
                final int n7 = (int)(Math.random() * eaterCt) + 1;
                final int n8 = array[n7];
                array[n7] = array[eaterCt];
                final int n9 = eaterCt - 1;
                final int n10 = (int)(Math.random() * n9) + 1;
                final int n11 = array[n10];
                array[n10] = array[n9];
                eaterCt = n9 - 1;
                this.eater[n8].gene.crossWith(this.eater[n11].gene);
            }
        }
        for (int n12 = 1; n12 <= this.eaterCt; ++n12) {
            this.PutNewEater(n12, false);
        }
    }
    
    static /* synthetic */ void access$1(final GACanvas gaCanvas, final boolean draw) {
        gaCanvas.draw = draw;
    }
    
    static /* synthetic */ int access$7() {
        return 0;
    }
    
    static /* synthetic */ int access$8() {
        return 3;
    }
    
    static {
        GACanvas.green = new Color(0, 180, 0);
    }
    
    private static class Eater
    {
        int row;
        int col;
        int score;
        Chromosome gene;
        int currentState;
        int facing;
        
        Eater() {
            this.gene = new Chromosome();
        }
    }
    
    private static class Chromosome
    {
        byte[][] newState;
        byte[][] whatToDo;
        
        void randomize() {
            for (int i = 0; i < 16; ++i) {
                for (int j = GACanvas.access$7(); j <= GACanvas.access$8(); ++j) {
                    this.newState[i][j] = (byte)(Math.random() * 16.0);
                    this.whatToDo[i][j] = (byte)(Math.random() * 4.0);
                }
            }
        }
        
        void crossWith(final Chromosome chromosome) {
            final int n = (int)(Math.random() * 128.0);
            int n2 = 0;
            for (int i = 0; i < 16; ++i) {
                for (int j = 0; j < 4; ++j) {
                    if (n2 > n) {
                        final byte b = this.newState[i][j];
                        this.newState[i][j] = chromosome.newState[i][j];
                        chromosome.newState[i][j] = b;
                    }
                    if (++n2 > n) {
                        final byte b2 = this.whatToDo[i][j];
                        this.whatToDo[i][j] = chromosome.whatToDo[i][j];
                        chromosome.whatToDo[i][j] = b2;
                    }
                    ++n2;
                }
            }
        }
        
        Chromosome copy() {
            final Chromosome chromosome = new Chromosome();
            for (int i = 0; i < 16; ++i) {
                for (int j = 0; j < 4; ++j) {
                    chromosome.whatToDo[i][j] = this.whatToDo[i][j];
                    chromosome.newState[i][j] = this.newState[i][j];
                }
            }
            return chromosome;
        }
        
        Chromosome() {
            this.newState = new byte[16][4];
            this.whatToDo = new byte[16][4];
        }
    }
}
