import java.awt.event.ActionEvent;
import java.awt.Insets;
import java.awt.Label;
import java.awt.Panel;
import java.awt.Component;
import java.awt.LayoutManager;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Button;
import java.applet.AudioClip;
import java.awt.event.ActionListener;
import java.applet.Applet;

// 
// Decompiled by Procyon v0.5.30
// 

public class HickQuest extends Applet implements Runnable, ActionListener
{
    protected String title;
    private String vehicle;
    private String weapon;
    private String[] vArray;
    private String[] wArray;
    private int count1;
    private int count2;
    private int count3;
    private int count4;
    private int count5;
    private int count6;
    private int count7;
    private int count8;
    private int count9;
    private int count10;
    private int count11;
    private int count12;
    private int count13;
    private int count14;
    private int count15;
    private int count16;
    private int count17;
    private int count18;
    private int count19;
    private int count20;
    private int count21;
    private int count22;
    private int count23;
    private int count24;
    private int count25;
    private int BSC;
    private int answer;
    private int newHealth;
    private int power;
    private int eHealth;
    private int eDamage;
    private int defense;
    private int health;
    private int money;
    private int vcount;
    private int wcount;
    private int numb;
    private int vprice;
    private int wprice;
    private int hits;
    private boolean check1;
    private boolean check2;
    private boolean check3;
    private boolean check4;
    private boolean check5;
    private boolean finished;
    private boolean boss;
    AudioClip fighting;
    AudioClip inst;
    AudioClip choke;
    AudioClip kill;
    AudioClip win;
    AudioClip ff7;
    AudioClip main;
    AudioClip home;
    AudioClip doh;
    AudioClip dog;
    AudioClip burp;
    AudioClip snore;
    protected ConsolePanel console;
    private Button runButton;
    private Thread programThread;
    private boolean programRunning;
    private boolean firstTime;
    
    public HickQuest() {
        this.title = "Hick Quest";
        this.count1 = 0;
        this.count2 = 0;
        this.count3 = 0;
        this.count4 = 0;
        this.count5 = 0;
        this.count6 = 0;
        this.count7 = 0;
        this.count8 = 0;
        this.count9 = 0;
        this.count10 = 0;
        this.count11 = 0;
        this.count12 = 0;
        this.count13 = 0;
        this.count14 = 0;
        this.count15 = 0;
        this.count16 = 0;
        this.count17 = 0;
        this.count18 = 0;
        this.count19 = 0;
        this.count20 = 0;
        this.count21 = 0;
        this.count22 = 0;
        this.count23 = 0;
        this.count24 = 0;
        this.count25 = 0;
        this.BSC = 0;
        this.health = 100;
        this.money = 0;
        this.vcount = 0;
        this.wcount = 0;
        this.numb = 0;
        this.check1 = true;
        this.check2 = false;
        this.check3 = false;
        this.check4 = false;
        this.check5 = false;
        this.boss = false;
        this.programThread = null;
        this.programRunning = false;
        this.firstTime = true;
    }
    
    protected String getTitle() {
        return this.title;
    }
    
    protected void program() {
        this.finished = false;
        this.clear();
        while (!this.finished) {
            this.menu();
        }
        this.money = 0;
        this.console.putln("Thanks for Playing Hick Quest!");
    }
    
    public void run() {
        this.programRunning = true;
        this.program();
        this.programRunning = false;
        this.stopProgram();
    }
    
    private synchronized void startProgram() {
        this.runButton.setLabel("Quit");
        if (!this.firstTime) {
            this.console.clear();
            try {
                Thread.sleep(300L);
            }
            catch (InterruptedException ex) {}
        }
        this.firstTime = false;
        (this.programThread = new Thread(this)).start();
    }
    
    private synchronized void stopProgram() {
        if (this.programRunning) {
            this.programThread.stop();
            try {
                this.programThread.join(1000L);
            }
            catch (InterruptedException ex) {}
        }
        this.console.clearBuffers();
        this.programThread = null;
        this.programRunning = false;
        this.runButton.setLabel("Play");
        this.runButton.requestFocus();
    }
    
    public void init() {
        this.vArray = new String[5];
        this.wArray = new String[8];
        this.fillArray();
        this.fighting = this.getAudioClip(this.getCodeBase(), "sounds/punch123.au");
        this.inst = this.getAudioClip(this.getCodeBase(), "sounds/helpyoui.au");
        this.choke = this.getAudioClip(this.getCodeBase(), "sounds/choke.au");
        this.kill = this.getAudioClip(this.getCodeBase(), "sounds/fight.au");
        this.win = this.getAudioClip(this.getCodeBase(), "sounds/ff7win.au");
        this.ff7 = this.getAudioClip(this.getCodeBase(), "sounds/ff7.au");
        this.main = this.getAudioClip(this.getCodeBase(), "sounds/GDroad.au");
        this.home = this.getAudioClip(this.getCodeBase(), "sounds/Home.au");
        this.doh = this.getAudioClip(this.getCodeBase(), "sounds/doh.au");
        this.dog = this.getAudioClip(this.getCodeBase(), "sounds/bark.au");
        this.burp = this.getAudioClip(this.getCodeBase(), "sounds/burps.au");
        this.snore = this.getAudioClip(this.getCodeBase(), "sounds/zzzz.au");
        this.setBackground(Color.black);
        this.setLayout(new BorderLayout(2, 2));
        this.add("Center", this.console = new ConsolePanel());
        Panel temp = new Panel();
        temp.setBackground(Color.white);
        final Label lab = new Label(this.getTitle());
        temp.add(lab);
        lab.setForeground(new Color(180, 0, 0));
        this.add("North", temp);
        this.runButton = new Button("Play");
        temp = new Panel();
        temp.setBackground(Color.white);
        temp.add(this.runButton);
        this.runButton.addActionListener(this);
        this.add("South", temp);
    }
    
    public Insets getInsets() {
        return new Insets(2, 2, 2, 2);
    }
    
    public void stop() {
        if (this.programRunning) {
            this.stopProgram();
            this.console.putln();
            this.console.putln("*** Hick Quest paused");
        }
    }
    
    public synchronized void actionPerformed(final ActionEvent evt) {
        if (this.programThread != null) {
            this.stopProgram();
            this.console.putln();
            this.console.putln("*** Thanks for playing Hick Quest!");
            this.win.stop();
            this.main.stop();
            this.home.stop();
            this.ff7.stop();
            this.doh.play();
        }
        else {
            this.startProgram();
        }
    }
    
    private void clear() {
        for (int x = 0; x < 25; ++x) {
            this.console.putln();
        }
    }
    
    public void menu() {
        this.main.play();
        this.console.putln();
        this.update();
        this.console.putln("Hick Quest");
        this.console.putln();
        this.console.putln("Your vehicle: " + this.vehicle);
        this.console.putln("Your weapon: " + this.weapon);
        this.console.putln("Your BSC is " + this.BSC);
        this.console.putln("You have " + this.money + " dollars.");
        this.console.putln("Your health: " + this.health);
        this.console.putln();
        this.console.putln("1.  Go Fight");
        this.console.putln("2.  Go to Billy Bob's store");
        this.console.putln("3.  Go Home");
        this.console.putln("4.  View Instructions");
        switch (this.answer = this.console.getInt()) {
            case 0: {
                this.finished = true;
                break;
            }
            case 1: {
                this.fight();
                break;
            }
            case 2: {
                this.store();
                break;
            }
            case 3: {
                this.home();
                break;
            }
            case 4: {
                this.instructions();
                break;
            }
        }
    }
    
    private void instructions() {
        this.inst.play();
        this.console.putln();
        this.console.putln("You are a Hick on a Quest");
        this.console.putln("Type the corrosponding number to make your choice on your quest");
        this.console.putln("Fight different people at different locations for money");
        this.console.putln("Try to obtain 'Ultimate Hickness' by defeating all the people");
        this.console.putln("Upgrade your weapons and vehicles at Billy Bob's store");
        this.console.putln("Your BSC (Blood Steroid Content) is your defense (more steroids, more defense)");
        this.console.putln("Type the numbers given for your different choices");
        this.console.putln("Type '0' at any time to quit or just click the button on the applet");
        this.console.putln("Good luck on your quest");
        this.console.putln();
        this.console.putln("Text Based Adventure made by Jordan Haddock");
        this.console.getInt();
    }
    
    private void fillArray() {
        this.vArray[0] = "walk";
        this.vArray[1] = "a pig";
        this.vArray[2] = "a donkey";
        this.vArray[3] = "a 1935 Ford truck";
        this.vArray[4] = "a Harley";
        this.wArray[0] = "your fists";
        this.wArray[1] = "a salami";
        this.wArray[2] = "barb wire";
        this.wArray[3] = "a cattle prod";
        this.wArray[4] = "a hub cap";
        this.wArray[5] = "a fence post";
        this.wArray[6] = "a rusty bat";
        this.wArray[7] = "Pappy's Shotgun";
    }
    
    private void update() {
        this.vehicle = this.vArray[this.vcount];
        this.weapon = this.wArray[this.wcount];
        if (this.vcount == 0) {
            this.check1 = true;
            this.vprice = 25;
        }
        if (this.vcount == 1) {
            this.vprice = 100;
            this.check2 = true;
        }
        if (this.vcount == 2) {
            this.vprice = 175;
            this.check3 = true;
        }
        if (this.vcount == 3) {
            this.vprice = 300;
            this.check4 = true;
        }
        if (this.vcount == 4) {
            this.check5 = true;
        }
        if (this.wcount == 0) {
            this.wprice = 5;
            this.power = 1;
        }
        if (this.wcount == 1) {
            this.wprice = 15;
            this.power = 3;
        }
        if (this.wcount == 2) {
            this.wprice = 30;
            this.power = 10;
        }
        if (this.wcount == 3) {
            this.wprice = 45;
            this.power = 15;
        }
        if (this.wcount == 4) {
            this.wprice = 60;
            this.power = 20;
        }
        if (this.wcount == 5) {
            this.wprice = 85;
            this.power = 30;
        }
        if (this.wcount == 6) {
            this.wprice = 150;
            this.power = 40;
        }
        if (this.wcount == 7) {
            this.power = 60;
        }
    }
    
    public void store() {
        this.console.putln();
        this.update();
        this.console.putln("Billy Bob's Store");
        this.console.putln();
        this.console.putln("You have $ " + this.money);
        if (this.vcount != 4) {
            this.console.putln("1. buy new vehicle: " + this.vArray[this.vcount + 1] + " $ " + this.vprice);
        }
        if (this.wcount != 7) {
            this.console.putln("2. buy new weapon:  " + this.wArray[this.wcount + 1] + " $ " + this.wprice);
        }
        this.console.putln("3. buy a dose of steroids: $ 2 ");
        this.console.putln("4. buy 10 doses of steroids: $ 20");
        this.console.putln("5. leave store ");
        switch (this.answer = this.console.getInt()) {
            case 0: {
                this.finished = true;
                break;
            }
            case 1: {
                if (this.money - this.vprice >= 0) {
                    this.money -= this.vprice;
                    this.console.putln("You bought a new vehicle!");
                    this.console.putln("New areas are now unlocked!");
                    ++this.vcount;
                    this.update();
                }
                else {
                    this.console.putln("You don't have enough money!");
                }
                this.console.getInt();
                break;
            }
            case 2: {
                if (this.money - this.wprice >= 0) {
                    this.money -= this.wprice;
                    this.console.putln("You bought a new weapon!");
                    this.console.putln("Your attack power goes up!");
                    ++this.wcount;
                    this.update();
                }
                else {
                    this.console.putln("You don't have enough money!");
                }
                this.console.getInt();
                break;
            }
            case 3: {
                if (this.money - 2 >= 0) {
                    this.money -= 2;
                    this.console.putln("You bought a dose of steroids!");
                    this.console.putln("Your BSC goes up.");
                    if (this.BSC + 1 < 100) {
                        ++this.BSC;
                    }
                    this.update();
                }
                else {
                    this.console.putln("You don't have enough money!");
                }
                this.console.getInt();
                break;
            }
            case 4: {
                if (this.money - 20 >= 0) {
                    this.money -= 20;
                    this.console.putln("You bought 10 doses of steroids!");
                    this.console.putln("Your BSC goes up.");
                    if (this.BSC + 10 < 100) {
                        this.BSC += 10;
                    }
                    this.update();
                }
                else {
                    this.console.putln("You don't have enough money!");
                }
                this.console.getInt();
                break;
            }
            case 5: {
                this.menu();
                break;
            }
        }
    }
    
    public void fight() {
        this.console.putln();
        this.console.putln("Where do you want to Fight?");
        this.console.putln();
        this.console.putln("1. Barn         " + this.check1);
        this.console.putln("2. Schoolyard   " + this.check2);
        this.console.putln("3. to Town      " + this.check3);
        this.console.putln("4. Bar          " + this.check3);
        this.console.putln("5. Race Track   " + this.check4);
        this.console.putln("6. Sturgis, SD  " + this.check5);
        this.console.putln();
        this.console.putln("7. Back to Menu ");
        switch (this.answer = this.console.getInt()) {
            case 0: {
                this.finished = true;
                break;
            }
            case 1: {
                if (this.check1) {
                    this.fLocation(1);
                    break;
                }
                break;
            }
            case 2: {
                if (this.check2) {
                    this.fLocation(2);
                    break;
                }
                break;
            }
            case 3: {
                if (this.check3) {
                    this.fLocation(3);
                    break;
                }
                break;
            }
            case 4: {
                if (this.check3) {
                    this.fLocation(4);
                    break;
                }
                break;
            }
            case 5: {
                if (this.check4) {
                    this.fLocation(5);
                    break;
                }
                break;
            }
            case 6: {
                if (this.check5) {
                    this.fLocation(6);
                    break;
                }
                break;
            }
            case 7: {
                this.menu();
                break;
            }
        }
    }
    
    public void fLocation(final int choice) {
        this.main.stop();
        this.ff7.play();
        if (choice == 1) {
            this.console.putln();
            this.console.putln("Who do you want to fight?");
            this.console.putln();
            this.console.putln("1. Uncle Abner ");
            this.console.putln("2. Aunt Chuck ");
            this.console.putln("3. Cousin Ernie ");
            if (this.count4 == 0) {
                this.console.putln("4. Howie the Cow ");
            }
            switch (this.answer = this.console.getInt()) {
                case 0: {
                    this.finished = true;
                    break;
                }
                case 1: {
                    this.console.putln();
                    if (this.count1 == 5) {
                        this.console.putln("Uncle Abner doesn't want to fight you anymore.");
                        this.console.getInt();
                        this.ff7.stop();
                        break;
                    }
                    this.attack(1, this.answer);
                    break;
                }
                case 2: {
                    if (this.count2 == 5) {
                        this.console.putln("Aunt Chuck doesn't want to fight you anymore.");
                        this.console.getInt();
                        this.ff7.stop();
                        break;
                    }
                    this.attack(2, this.answer);
                    break;
                }
                case 3: {
                    if (this.count3 == 5) {
                        this.console.putln("Cousin Ernie doesn't want to fight you anymore.");
                        this.console.getInt();
                        this.ff7.stop();
                        break;
                    }
                    this.attack(3, this.answer);
                    break;
                }
                case 4: {
                    this.console.putln();
                    if (this.count4 == 0) {
                        this.attack(4, this.answer);
                        break;
                    }
                    break;
                }
            }
        }
        if (choice == 2) {
            this.console.putln();
            this.console.putln("Who do you want to fight?");
            this.console.putln();
            this.console.putln("1. Dexter the Geek ");
            this.console.putln("2. Tommy the Tattle Tale ");
            this.console.putln("3. Biff the Bully ");
            if (this.count8 == 0) {
                this.console.putln("4. the Teacher ");
            }
            switch (this.answer = this.console.getInt()) {
                case 0: {
                    this.finished = true;
                    break;
                }
                case 1: {
                    if (this.count5 == 5) {
                        this.console.putln("Dexter the Geek doesn't want to fight you anymore.");
                        this.console.getInt();
                        this.ff7.stop();
                        break;
                    }
                    this.attack(5, this.answer);
                    break;
                }
                case 2: {
                    if (this.count6 == 5) {
                        this.console.putln("Tommy doesn't want to fight you anymore.");
                        this.console.getInt();
                        this.ff7.stop();
                        break;
                    }
                    this.attack(6, this.answer);
                    break;
                }
                case 3: {
                    if (this.count7 == 5) {
                        this.console.putln("Biff the Bully doesn't want to fight you anymore.");
                        this.console.getInt();
                        this.ff7.stop();
                        break;
                    }
                    this.attack(7, this.answer);
                    break;
                }
                case 4: {
                    this.console.putln();
                    if (this.count8 == 0) {
                        this.attack(8, this.answer);
                        break;
                    }
                    break;
                }
            }
        }
        if (choice == 3) {
            this.console.putln();
            this.console.putln("Who do you want to fight?");
            this.console.putln();
            this.console.putln("1. Bernie the Bum ");
            this.console.putln("2. Bob the Builder ");
            this.console.putln("3. Mark the Mailman ");
            if (this.count12 == 0) {
                this.console.putln("4. Sheriff Woody ");
            }
            switch (this.answer = this.console.getInt()) {
                case 0: {
                    this.finished = true;
                    break;
                }
                case 1: {
                    if (this.count9 == 5) {
                        this.console.putln("Bernie the Bum doesn't want to fight you anymore.");
                        this.console.getInt();
                        this.ff7.stop();
                        break;
                    }
                    this.attack(9, this.answer);
                    break;
                }
                case 2: {
                    if (this.count10 == 5) {
                        this.console.putln("Bob the Builder doesn't want to fight you anymore.");
                        this.console.getInt();
                        this.ff7.stop();
                        break;
                    }
                    this.attack(10, this.answer);
                    break;
                }
                case 3: {
                    if (this.count11 == 5) {
                        this.console.putln("Mark the Mailman doesn't want to fight you anymore.");
                        this.console.getInt();
                        this.ff7.stop();
                        break;
                    }
                    this.attack(11, this.answer);
                    break;
                }
                case 4: {
                    this.console.putln();
                    if (this.count12 == 0) {
                        this.attack(12, this.answer);
                        break;
                    }
                    break;
                }
            }
        }
        if (choice == 4) {
            this.console.putln();
            this.console.putln("Who do you want to fight?");
            this.console.putln();
            this.console.putln("1. Derek the Drunk  ");
            this.console.putln("2. Peter the Piano playing, pool shooting, poker man ");
            this.console.putln("3. Barz the Bartender ");
            if (this.count16 == 0) {
                this.console.putln("4. Sebass  ");
            }
            switch (this.answer = this.console.getInt()) {
                case 0: {
                    this.finished = true;
                    break;
                }
                case 1: {
                    if (this.count13 == 5) {
                        this.console.putln("Derek the Drunk doesn't want to fight you anymore.");
                        this.console.getInt();
                        this.ff7.stop();
                        break;
                    }
                    this.attack(13, this.answer);
                    break;
                }
                case 2: {
                    if (this.count14 == 5) {
                        this.console.putln("Peter doesn't want to fight you anymore.");
                        this.console.getInt();
                        this.ff7.stop();
                        break;
                    }
                    this.attack(14, this.answer);
                    break;
                }
                case 3: {
                    if (this.count15 == 5) {
                        this.console.putln("Barz the Bartender doesn't want to fight you anymore.");
                        this.console.getInt();
                        this.ff7.stop();
                        break;
                    }
                    this.attack(15, this.answer);
                    break;
                }
                case 4: {
                    this.console.putln();
                    if (this.count16 == 0) {
                        this.attack(16, this.answer);
                        break;
                    }
                    break;
                }
            }
        }
        if (choice == 5) {
            this.console.putln();
            this.console.putln("Who do you want to fight?");
            this.console.putln();
            this.console.putln("1. Frank the Fan  ");
            this.console.putln("2. Willy White Trash ");
            this.console.putln("3. Jeff Gordon ");
            if (this.count20 == 0) {
                this.console.putln("4. Rocky the Rottweiler  ");
            }
            switch (this.answer = this.console.getInt()) {
                case 0: {
                    this.finished = true;
                    break;
                }
                case 1: {
                    if (this.count17 == 5) {
                        this.console.putln("Frank the Fan doesn't want to fight you anymore.");
                        this.console.getInt();
                        this.ff7.stop();
                        break;
                    }
                    this.attack(17, this.answer);
                    break;
                }
                case 2: {
                    if (this.count18 == 5) {
                        this.console.putln("Willy White Trash doesn't want to fight you anymore.");
                        this.console.getInt();
                        this.ff7.stop();
                        break;
                    }
                    this.attack(18, this.answer);
                    break;
                }
                case 3: {
                    if (this.count19 == 5) {
                        this.console.putln("Jeff Gordon doesn't want to fight you anymore.");
                        this.console.getInt();
                        this.ff7.stop();
                        break;
                    }
                    this.attack(19, this.answer);
                    break;
                }
                case 4: {
                    this.console.putln();
                    if (this.count20 == 0) {
                        this.attack(20, this.answer);
                        break;
                    }
                    break;
                }
            }
        }
        if (choice == 6) {
            if (this.count21 == 5 && this.count22 == 5 && this.count23 == 5 && this.count24 == 1) {
                this.boss = true;
            }
            this.console.putln();
            this.console.putln("Who do you want to fight?");
            this.console.putln();
            this.console.putln("1. Rambo the Rough Rider  ");
            this.console.putln("2. Wanda his Wife ");
            this.console.putln("3. Ruh Vogaluh ");
            if (this.count24 == 0) {
                this.console.putln("4. Head Honcho Harrison ");
            }
            this.console.putln("5. a good joke");
            if (this.boss) {
                this.console.putln("6. Mr. Tank!!!");
            }
            switch (this.answer = this.console.getInt()) {
                case 0: {
                    this.finished = true;
                    break;
                }
                case 1: {
                    if (this.count21 == 5) {
                        this.console.putln("Rambo the Rough Rider doesn't want to fight you anymore.");
                        this.console.getInt();
                        this.ff7.stop();
                        break;
                    }
                    this.attack(21, this.answer);
                    break;
                }
                case 2: {
                    if (this.count22 == 5) {
                        this.console.putln("Wanda doesn't want to fight you anymore.");
                        this.console.getInt();
                        this.ff7.stop();
                        break;
                    }
                    this.attack(22, this.answer);
                    break;
                }
                case 3: {
                    if (this.count23 == 5) {
                        this.console.putln("Ruh Vogaluh doesn't want to fight you anymore.");
                        this.console.getInt();
                        this.ff7.stop();
                        break;
                    }
                    this.attack(23, this.answer);
                    break;
                }
                case 4: {
                    if (this.count24 == 1) {
                        this.console.putln("Head Honcho Harrison doesn't want to fight you anymore.");
                        this.console.getInt();
                        this.ff7.stop();
                        break;
                    }
                    this.attack(24, this.answer);
                    break;
                }
                case 5: {
                    this.console.putln("What's the difference between a Biker and a vacuum?");
                    this.console.putln();
                    this.console.getInt();
                    this.console.putln("A vacuum only has one dirt bag on the back!");
                    this.console.getInt();
                    break;
                }
                case 6: {
                    if (this.boss) {
                        this.console.putln();
                        this.attack(25, this.answer);
                        break;
                    }
                    break;
                }
            }
        }
    }
    
    public void home() {
        this.main.stop();
        this.home.play();
        this.console.putln();
        this.console.putln("What do you want to do?");
        this.console.putln();
        this.console.putln("1. Talk to Mom ");
        this.console.putln("2. Eat some food ");
        this.console.putln("3. Go to sleep ");
        this.console.putln("4. Pet Dog ");
        switch (this.answer = this.console.getInt()) {
            case 1: {
                this.console.putln();
                this.console.putln("What do you want to say?");
                this.console.putln("1. Hiya Momma...how are you?");
                this.console.putln("2. What's fo' suppa?");
                this.console.putln("3. Give me some money.");
                this.console.putln("4. (burp)");
                switch (this.answer = this.console.getInt()) {
                    case 1: {
                        this.console.putln("I don't want to talk to you boy, go feed the hogs. ");
                        this.console.getInt();
                        break;
                    }
                    case 2: {
                        this.console.putln("How in tarnation am I spose ta no?");
                        this.console.getInt();
                        break;
                    }
                    case 3: {
                        this.console.putln("Money!!! I can't believe you boy. ");
                        this.console.putln("Go beat the snot outta someone and take theyr money.");
                        this.console.getInt();
                        break;
                    }
                    case 4: {
                        this.burp.play();
                        this.console.putln("That's my boy!");
                        this.console.getInt();
                        break;
                    }
                }
                this.home.stop();
                break;
            }
            case 2: {
                this.burp.play();
                this.console.putln();
                this.console.putln("You ate some food. ");
                if (this.BSC - 4 > 0) {
                    this.BSC -= 4;
                }
                else {
                    this.BSC = 0;
                }
                if (this.health + 20 <= 100) {
                    this.health += 20;
                }
                this.console.putln("Your health is now: " + this.health + "");
                this.console.putln("Your BSC is " + this.BSC);
                this.console.getInt();
                this.home.stop();
                break;
            }
            case 3: {
                this.console.putln();
                if (this.BSC - 10 > 0) {
                    this.BSC -= 10;
                }
                else {
                    this.BSC = 0;
                }
                this.health = 100;
                this.snore.play();
                this.console.putln("You took a nap. ");
                this.console.putln("You have full health ");
                this.console.putln("Your BSC is " + this.BSC);
                this.console.getInt();
                this.home.stop();
                break;
            }
            case 4: {
                this.console.putln();
                this.dog.play();
                this.console.putln("You pet Dog. ");
                this.console.getInt();
                this.home.stop();
                break;
            }
        }
    }
    
    public void attack(final int guy, int answer) {
        if (guy == 1) {
            this.update();
            this.eHealth = 5;
            this.console.putln("You are fighting Uncle Abner ");
            this.console.putln("Type '1' to attack ");
            this.console.putln("Type '2' to run ");
            answer = this.console.getInt();
            switch (answer) {
                case 1: {
                    while (answer == 1) {
                        if (this.BSC / 4 < 100) {
                            if (this.BSC / 4 < 100) {
                                this.defense = this.BSC / 4;
                            }
                            else {
                                this.defense = 100;
                            }
                        }
                        if (this.health - (2 - this.defense) <= 0) {
                            this.choke.play();
                            this.console.putln("Uncle Abner has beat you! You lose!");
                            this.console.getInt();
                            this.ff7.stop();
                            this.doh.play();
                            this.finished = true;
                            break;
                        }
                        this.eHealth -= this.power;
                        if (this.eHealth <= 0) {
                            this.ff7.stop();
                            this.win.play();
                            this.kill.play();
                            this.console.putln("You have pummeled Uncle Abner into submission!");
                            this.console.putln("You find $2 on his body");
                            this.money += 2;
                            ++this.count1;
                            this.console.getInt();
                            this.win.stop();
                            break;
                        }
                        this.health -= 2 - this.defense;
                        this.fighting.play();
                        this.console.putln();
                        this.console.putln("You attack Uncle Abner ");
                        this.console.putln("Uncle Abner fights back ");
                        this.console.putln("Your health is " + this.health + "");
                        this.console.putln("Uncle Abner's health is " + this.eHealth);
                        answer = this.console.getInt();
                    }
                    break;
                }
                case 2: {
                    this.ff7.stop();
                    this.menu();
                    break;
                }
                default: {
                    this.ff7.stop();
                    break;
                }
            }
        }
        if (guy == 2) {
            this.update();
            this.eHealth = 8;
            this.console.putln("You are fighting Aunt Chuck ");
            this.console.putln("Type '1' to attack ");
            this.console.putln("Type '2' to run ");
            answer = this.console.getInt();
            switch (answer) {
                case 1: {
                    while (answer == 1) {
                        if (this.BSC / 4 < 100) {
                            if (this.BSC / 4 < 100) {
                                this.defense = this.BSC / 4;
                            }
                            else {
                                this.defense = 100;
                            }
                        }
                        if (this.health - (2 - this.defense) <= 0) {
                            this.choke.play();
                            this.console.putln("Aunt Chuck has beat you! You lose!");
                            this.console.getInt();
                            this.ff7.stop();
                            this.doh.play();
                            this.finished = true;
                            break;
                        }
                        this.eHealth -= this.power;
                        if (this.eHealth <= 0) {
                            this.ff7.stop();
                            this.win.play();
                            this.kill.play();
                            this.console.putln("You have slapped Aunt Chuck silly!");
                            this.console.putln("You find $3 on her body");
                            this.money += 3;
                            ++this.count2;
                            this.console.getInt();
                            this.win.stop();
                            break;
                        }
                        this.health -= 2 - this.defense;
                        this.fighting.play();
                        this.console.putln();
                        this.console.putln("You attack Aunt Chuck ");
                        this.console.putln("Aunt Chuck fights back ");
                        this.console.putln("Your health is " + this.health + "");
                        this.console.putln("Aunt Chuck's health is " + this.eHealth);
                        answer = this.console.getInt();
                    }
                    break;
                }
                case 2: {
                    this.ff7.stop();
                    this.menu();
                    break;
                }
                default: {
                    this.ff7.stop();
                    break;
                }
            }
        }
        if (guy == 3) {
            this.update();
            this.eHealth = 5;
            this.console.putln("You are fighting Cousin Ernie ");
            this.console.putln("Type '1' to attack ");
            this.console.putln("Type '2' to run ");
            answer = this.console.getInt();
            switch (answer) {
                case 1: {
                    while (answer == 1) {
                        if (this.BSC / 4 < 100) {
                            this.defense = this.BSC / 4;
                        }
                        else {
                            this.defense = 100;
                        }
                        if (this.health - (3 - this.defense) <= 0) {
                            this.choke.play();
                            this.console.putln("Cousin Ernie has beat you! You lose!");
                            this.console.getInt();
                            this.ff7.stop();
                            this.doh.play();
                            this.finished = true;
                            break;
                        }
                        this.eHealth -= this.power;
                        if (this.eHealth <= 0) {
                            this.ff7.stop();
                            this.win.play();
                            this.kill.play();
                            this.console.putln("You have spanked Cousin Ernie!");
                            this.console.putln("You find $4 on his body");
                            this.money += 4;
                            ++this.count3;
                            this.console.getInt();
                            this.win.stop();
                            break;
                        }
                        this.health -= 3 - this.defense;
                        this.fighting.play();
                        this.console.putln();
                        this.console.putln("You attack Cousin Ernie ");
                        this.console.putln("Cousin Ernie fights back ");
                        this.console.putln("Your health is " + this.health + "");
                        this.console.putln("Cousin Ernie's health is " + this.eHealth);
                        answer = this.console.getInt();
                    }
                    break;
                }
                case 2: {
                    this.ff7.stop();
                    this.menu();
                    break;
                }
                default: {
                    this.ff7.stop();
                    break;
                }
            }
        }
        if (guy == 4) {
            this.update();
            this.eHealth = 30;
            this.console.putln("You are fighting Howie the Cow ");
            this.console.putln("Type '1' to attack ");
            this.console.putln("Type '2' to run ");
            answer = this.console.getInt();
            switch (answer) {
                case 1: {
                    while (answer == 1) {
                        if (this.BSC / 4 < 100) {
                            this.defense = this.BSC / 4;
                        }
                        else {
                            this.defense = 100;
                        }
                        if (this.health - (10 - this.defense) <= 0) {
                            this.choke.play();
                            this.console.putln("Howie the Cow has beat you! You lose!");
                            this.console.getInt();
                            this.ff7.stop();
                            this.doh.play();
                            this.finished = true;
                            break;
                        }
                        this.eHealth -= this.power;
                        if (this.eHealth <= 0) {
                            this.ff7.stop();
                            this.win.play();
                            this.kill.play();
                            this.console.putln("You have bar-b-qued Howie the Cow!");
                            this.console.putln("You have full life");
                            this.console.putln("You sell the beef for $10");
                            this.money += 10;
                            ++this.count4;
                            this.health = 100;
                            this.console.getInt();
                            this.win.stop();
                            break;
                        }
                        this.health -= 10 - this.defense;
                        this.fighting.play();
                        this.console.putln();
                        this.console.putln("You attack Howie the Cow ");
                        this.console.putln("Howie the Cow fights back ");
                        this.console.putln("Your health is " + this.health + "");
                        this.console.putln("Howie the Cow's health is " + this.eHealth);
                        answer = this.console.getInt();
                    }
                    break;
                }
                case 2: {
                    this.ff7.stop();
                    this.menu();
                    break;
                }
                default: {
                    this.ff7.stop();
                    break;
                }
            }
        }
        if (guy == 5) {
            this.update();
            this.eHealth = 25;
            this.console.putln("You are fighting Dexter the Geek ");
            this.console.putln("Type '1' to attack ");
            this.console.putln("Type '2' to run ");
            answer = this.console.getInt();
            switch (answer) {
                case 1: {
                    while (answer == 1) {
                        if (this.BSC / 4 < 100) {
                            this.defense = this.BSC / 4;
                        }
                        else {
                            this.defense = 100;
                        }
                        if (this.health - (5 - this.defense) <= 0) {
                            this.choke.play();
                            this.console.putln("Dexter the Geek has beat you! You lose!");
                            this.console.getInt();
                            this.ff7.stop();
                            this.doh.play();
                            this.finished = true;
                            break;
                        }
                        this.eHealth -= this.power;
                        if (this.eHealth <= 0) {
                            this.ff7.stop();
                            this.win.play();
                            this.kill.play();
                            this.console.putln("You have profusely beaten Dexter the Geek!");
                            this.console.putln("You find $6 on his body");
                            this.money += 6;
                            ++this.count5;
                            this.console.getInt();
                            this.win.stop();
                            break;
                        }
                        this.health -= 5 - this.defense;
                        this.fighting.play();
                        this.console.putln();
                        this.console.putln("You attack Dexter the Geek ");
                        this.console.putln("Dexter the Geek fights back ");
                        this.console.putln("Your health is " + this.health + "");
                        this.console.putln("Dexter the Geek's health is " + this.eHealth);
                        answer = this.console.getInt();
                    }
                    break;
                }
                case 2: {
                    this.ff7.stop();
                    this.menu();
                    break;
                }
                default: {
                    this.ff7.stop();
                    break;
                }
            }
        }
        if (guy == 6) {
            this.update();
            this.eHealth = 25;
            this.console.putln("You are fighting Tommy the Tattle Tale ");
            this.console.putln("Type '1' to attack ");
            this.console.putln("Type '2' to run ");
            answer = this.console.getInt();
            switch (answer) {
                case 1: {
                    while (answer == 1) {
                        if (this.BSC / 4 < 100) {
                            this.defense = this.BSC / 4;
                        }
                        else {
                            this.defense = 100;
                        }
                        if (this.health - (5 - this.defense) <= 0) {
                            this.choke.play();
                            this.console.putln("Tommy the Tattle Tale has beat you! You lose!");
                            this.console.getInt();
                            this.ff7.stop();
                            this.doh.play();
                            this.finished = true;
                            break;
                        }
                        this.eHealth -= this.power;
                        if (this.eHealth <= 0) {
                            this.ff7.stop();
                            this.win.play();
                            this.kill.play();
                            this.console.putln("You have beaten Tommy the Tattle Tale into a bloody pulp!");
                            this.console.putln("You find $8 on his body");
                            this.money += 8;
                            ++this.count6;
                            this.console.getInt();
                            this.win.stop();
                            break;
                        }
                        this.health -= 5 - this.defense;
                        this.fighting.play();
                        this.console.putln();
                        this.console.putln("You attack Tommy the Tattle Tale ");
                        this.console.putln("Tommy the Tattle Tale fights back ");
                        this.console.putln("Your health is " + this.health + "");
                        this.console.putln("Tommy the Tattle Tale's health is " + this.eHealth);
                        answer = this.console.getInt();
                    }
                    break;
                }
                case 2: {
                    this.ff7.stop();
                    this.menu();
                    break;
                }
                default: {
                    this.ff7.stop();
                    break;
                }
            }
        }
        if (guy == 7) {
            this.update();
            this.eHealth = 30;
            this.console.putln("You are fighting Biff the Bully ");
            this.console.putln("Type '1' to attack ");
            this.console.putln("Type '2' to run ");
            answer = this.console.getInt();
            switch (answer) {
                case 1: {
                    while (answer == 1) {
                        if (this.BSC / 4 < 100) {
                            this.defense = this.BSC / 4;
                        }
                        else {
                            this.defense = 100;
                        }
                        if (this.health - (10 - this.defense) <= 0) {
                            this.choke.play();
                            this.console.putln("Biff the Bully has beat you! You lose!");
                            this.console.getInt();
                            this.ff7.stop();
                            this.doh.play();
                            this.finished = true;
                            break;
                        }
                        this.eHealth -= this.power;
                        if (this.eHealth <= 0) {
                            this.ff7.stop();
                            this.win.play();
                            this.kill.play();
                            this.console.putln("You have pummeled Biff the Bully into submission!");
                            this.console.putln("You find $15 on his body");
                            this.money += 15;
                            ++this.count7;
                            this.console.getInt();
                            this.win.stop();
                            break;
                        }
                        this.health -= 10 - this.defense;
                        this.fighting.play();
                        this.console.putln();
                        this.console.putln("You attack Biff the Bully ");
                        this.console.putln("Biff the Bully fights back ");
                        this.console.putln("Your health is " + this.health + "");
                        this.console.putln("Biff the Bully's health is " + this.eHealth);
                        answer = this.console.getInt();
                    }
                    break;
                }
                case 2: {
                    this.ff7.stop();
                    this.menu();
                    break;
                }
                default: {
                    this.ff7.stop();
                    break;
                }
            }
        }
        if (guy == 8) {
            this.update();
            this.eHealth = 40;
            this.console.putln("You are fighting the Teacher ");
            this.console.putln("Type '1' to attack ");
            this.console.putln("Type '2' to run ");
            answer = this.console.getInt();
            switch (answer) {
                case 1: {
                    while (answer == 1) {
                        if (this.BSC / 4 < 100) {
                            this.defense = this.BSC / 4;
                        }
                        else {
                            this.defense = 100;
                        }
                        if (this.health - (15 - this.defense) <= 0) {
                            this.choke.play();
                            this.console.putln("the Teacher has beat you! You lose!");
                            this.console.getInt();
                            this.ff7.stop();
                            this.doh.play();
                            this.finished = true;
                            break;
                        }
                        this.eHealth -= this.power;
                        if (this.eHealth <= 0) {
                            this.ff7.stop();
                            this.win.play();
                            this.kill.play();
                            this.console.putln("You have beaten up the Teacher!");
                            this.console.putln("You find $20 on his body");
                            this.money += 20;
                            ++this.count8;
                            this.console.getInt();
                            this.win.stop();
                            break;
                        }
                        this.health -= 15 - this.defense;
                        this.fighting.play();
                        this.console.putln();
                        this.console.putln("You attack the Teacher ");
                        this.console.putln("the Teacher fights back ");
                        this.console.putln("Your health is " + this.health + "");
                        this.console.putln("the Teacher's health is " + this.eHealth);
                        answer = this.console.getInt();
                    }
                    break;
                }
                case 2: {
                    this.ff7.stop();
                    this.menu();
                    break;
                }
                default: {
                    this.ff7.stop();
                    break;
                }
            }
        }
        if (guy == 9) {
            this.update();
            this.eHealth = 45;
            this.console.putln("You are fighting Bernie the Bum ");
            this.console.putln("Type '1' to attack ");
            this.console.putln("Type '2' to run ");
            answer = this.console.getInt();
            switch (answer) {
                case 1: {
                    while (answer == 1) {
                        if (this.BSC / 4 < 100) {
                            this.defense = this.BSC / 4;
                        }
                        else {
                            this.defense = 100;
                        }
                        if (this.health - (10 - this.defense) <= 0) {
                            this.choke.play();
                            this.console.putln("Bernie the Bum has beat you! You lose!");
                            this.console.getInt();
                            this.ff7.stop();
                            this.doh.play();
                            this.finished = true;
                            break;
                        }
                        this.eHealth -= this.power;
                        if (this.eHealth <= 0) {
                            this.ff7.stop();
                            this.win.play();
                            this.kill.play();
                            this.console.putln("You have flogged Bernie the Bum to unconsciousness!");
                            this.console.putln("You find $20 on his body");
                            this.money += 20;
                            ++this.count9;
                            this.console.getInt();
                            this.win.stop();
                            break;
                        }
                        this.health -= 10 - this.defense;
                        this.fighting.play();
                        this.console.putln();
                        this.console.putln("You attack Bernie the Bum ");
                        this.console.putln("Bernie the Bum fights back ");
                        this.console.putln("Your health is " + this.health + "");
                        this.console.putln("Bernie the Bum's health is " + this.eHealth);
                        answer = this.console.getInt();
                    }
                    break;
                }
                case 2: {
                    this.ff7.stop();
                    this.menu();
                    break;
                }
                default: {
                    this.ff7.stop();
                    break;
                }
            }
        }
        if (guy == 10) {
            this.update();
            this.eHealth = 45;
            this.console.putln("You are fighting Bob the Builder ");
            this.console.putln("Type '1' to attack ");
            this.console.putln("Type '2' to run ");
            answer = this.console.getInt();
            switch (answer) {
                case 1: {
                    while (answer == 1) {
                        if (this.BSC / 4 < 100) {
                            this.defense = this.BSC / 4;
                        }
                        else {
                            this.defense = 100;
                        }
                        if (this.health - (10 - this.defense) <= 0) {
                            this.choke.play();
                            this.console.putln("Bob the Builder has beat you! You lose!");
                            this.console.getInt();
                            this.ff7.stop();
                            this.doh.play();
                            this.finished = true;
                            break;
                        }
                        this.eHealth -= this.power;
                        if (this.eHealth <= 0) {
                            this.ff7.stop();
                            this.win.play();
                            this.kill.play();
                            this.console.putln("You have blown apart Bob the Builder!");
                            this.console.putln("You find $25 on his body");
                            this.money += 25;
                            ++this.count10;
                            this.console.getInt();
                            this.win.stop();
                            break;
                        }
                        this.health -= 10 - this.defense;
                        this.fighting.play();
                        this.console.putln();
                        this.console.putln("You attack Bob the Builder ");
                        this.console.putln("Bob the Builder fights back ");
                        this.console.putln("Your health is " + this.health + "");
                        this.console.putln("Bob the Builder's health is " + this.eHealth);
                        answer = this.console.getInt();
                    }
                    break;
                }
                case 2: {
                    this.ff7.stop();
                    this.menu();
                    break;
                }
                default: {
                    this.ff7.stop();
                    break;
                }
            }
        }
        if (guy == 11) {
            this.update();
            this.eHealth = 50;
            this.console.putln("You are fighting Mark the Mailman ");
            this.console.putln("Type '1' to attack ");
            this.console.putln("Type '2' to run ");
            answer = this.console.getInt();
            switch (answer) {
                case 1: {
                    while (answer == 1) {
                        if (this.BSC / 4 < 100) {
                            this.defense = this.BSC / 4;
                        }
                        else {
                            this.defense = 100;
                        }
                        if (this.health - (15 - this.defense) <= 0) {
                            this.choke.play();
                            this.console.putln("Mark the Mailman has beat you! You lose!");
                            this.console.getInt();
                            this.ff7.stop();
                            this.doh.play();
                            this.finished = true;
                            break;
                        }
                        this.eHealth -= this.power;
                        if (this.eHealth <= 0) {
                            this.ff7.stop();
                            this.win.play();
                            this.kill.play();
                            this.console.putln("You have pummeled Mark the Mailman into submission!");
                            this.console.putln("You find $30 on his body");
                            this.money += 30;
                            ++this.count11;
                            this.console.getInt();
                            this.win.stop();
                            break;
                        }
                        this.health -= 15 - this.defense;
                        this.fighting.play();
                        this.console.putln();
                        this.console.putln("You attack Mark the Mailman ");
                        this.console.putln("Mark the Mailman fights back ");
                        this.console.putln("Your health is " + this.health + "");
                        this.console.putln("Mark the Mailman's health is " + this.eHealth);
                        answer = this.console.getInt();
                    }
                    break;
                }
                case 2: {
                    this.ff7.stop();
                    this.menu();
                    break;
                }
                default: {
                    this.ff7.stop();
                    break;
                }
            }
        }
        if (guy == 12) {
            this.update();
            this.eHealth = 60;
            this.console.putln("You are fighting Sheriff Woody ");
            this.console.putln("Type '1' to attack ");
            this.console.putln("Type '2' to run ");
            answer = this.console.getInt();
            switch (answer) {
                case 1: {
                    while (answer == 1) {
                        if (this.BSC / 4 < 100) {
                            this.defense = this.BSC / 4;
                        }
                        else {
                            this.defense = 100;
                        }
                        if (this.health - (20 - this.defense) <= 0) {
                            this.choke.play();
                            this.console.putln("Sheriff Woody has beat you! You lose!");
                            this.console.getInt();
                            this.ff7.stop();
                            this.doh.play();
                            this.finished = true;
                            break;
                        }
                        this.eHealth -= this.power;
                        if (this.eHealth <= 0) {
                            this.ff7.stop();
                            this.win.play();
                            this.kill.play();
                            this.console.putln("You have beaten the crap out of Sheriff Woody !");
                            this.console.putln("You find $30 on his body");
                            this.money += 30;
                            ++this.count12;
                            this.console.getInt();
                            this.win.stop();
                            break;
                        }
                        this.health -= 20 - this.defense;
                        this.fighting.play();
                        this.console.putln();
                        this.console.putln("You attack Sheriff Woody ");
                        this.console.putln("Sheriff Woody fights back ");
                        this.console.putln("Your health is " + this.health + "");
                        this.console.putln("Sheriff Woody's health is " + this.eHealth);
                        answer = this.console.getInt();
                    }
                    break;
                }
                case 2: {
                    this.ff7.stop();
                    this.menu();
                    break;
                }
                default: {
                    this.ff7.stop();
                    break;
                }
            }
        }
        if (guy == 13) {
            this.update();
            this.eHealth = 60;
            this.console.putln("You are fighting Derek the Drunk ");
            this.console.putln("Type '1' to attack ");
            this.console.putln("Type '2' to run ");
            answer = this.console.getInt();
            switch (answer) {
                case 1: {
                    while (answer == 1) {
                        if (this.BSC / 4 < 100) {
                            this.defense = this.BSC / 4;
                        }
                        else {
                            this.defense = 100;
                        }
                        if (this.health - (20 - this.defense) <= 0) {
                            this.choke.play();
                            this.console.putln("Derek the Drunk has beat you! You lose!");
                            this.console.getInt();
                            this.ff7.stop();
                            this.doh.play();
                            this.finished = true;
                            break;
                        }
                        this.eHealth -= this.power;
                        if (this.eHealth <= 0) {
                            this.ff7.stop();
                            this.win.play();
                            this.kill.play();
                            this.console.putln("You have trashed Derek the Drunk!");
                            this.console.putln("You find $30 on his body");
                            this.money += 30;
                            ++this.count13;
                            this.console.getInt();
                            this.win.stop();
                            break;
                        }
                        this.health -= 20 - this.defense;
                        this.fighting.play();
                        this.console.putln();
                        this.console.putln("You attack Derek the Drunk ");
                        this.console.putln("Derek the Drunk fights back ");
                        this.console.putln("Your health is " + this.health + "");
                        this.console.putln("Derek the Drunk's health is " + this.eHealth);
                        answer = this.console.getInt();
                    }
                    break;
                }
                case 2: {
                    this.ff7.stop();
                    this.menu();
                    break;
                }
                default: {
                    this.ff7.stop();
                    break;
                }
            }
        }
        if (guy == 14) {
            this.update();
            this.eHealth = 60;
            this.console.putln("You are fighting Peter ");
            this.console.putln("Type '1' to attack ");
            this.console.putln("Type '2' to run ");
            answer = this.console.getInt();
            switch (answer) {
                case 1: {
                    while (answer == 1) {
                        if (this.BSC / 4 < 100) {
                            this.defense = this.BSC / 4;
                        }
                        else {
                            this.defense = 100;
                        }
                        if (this.health - (20 - this.defense) <= 0) {
                            this.choke.play();
                            this.console.putln("Peter has beat you! You lose!");
                            this.console.getInt();
                            this.ff7.stop();
                            this.doh.play();
                            this.finished = true;
                            break;
                        }
                        this.eHealth -= this.power;
                        if (this.eHealth <= 0) {
                            this.ff7.stop();
                            this.win.play();
                            this.kill.play();
                            this.console.putln("You have pummeled Peter until he peed his pants!");
                            this.console.putln("You find $40 on his body");
                            this.money += 40;
                            ++this.count14;
                            this.console.getInt();
                            this.win.stop();
                            break;
                        }
                        this.health -= 20 - this.defense;
                        this.fighting.play();
                        this.console.putln();
                        this.console.putln("You attack Peter ");
                        this.console.putln("Peter fights back ");
                        this.console.putln("Your health is " + this.health + "");
                        this.console.putln("Peter's health is " + this.eHealth);
                        answer = this.console.getInt();
                    }
                    break;
                }
                case 2: {
                    this.ff7.stop();
                    this.menu();
                    break;
                }
                default: {
                    this.ff7.stop();
                    break;
                }
            }
        }
        if (guy == 15) {
            this.update();
            this.eHealth = 65;
            this.console.putln("You are fighting Barz the Bartender ");
            this.console.putln("Type '1' to attack ");
            this.console.putln("Type '2' to run ");
            answer = this.console.getInt();
            switch (answer) {
                case 1: {
                    while (answer == 1) {
                        if (this.BSC / 4 < 100) {
                            this.defense = this.BSC / 4;
                        }
                        else {
                            this.defense = 100;
                        }
                        if (this.health - (20 - this.defense) <= 0) {
                            this.choke.play();
                            this.console.putln("Barz the Bartender has beat you! You lose!");
                            this.console.getInt();
                            this.ff7.stop();
                            this.doh.play();
                            this.finished = true;
                            break;
                        }
                        this.eHealth -= this.power;
                        if (this.eHealth <= 0) {
                            this.ff7.stop();
                            this.win.play();
                            this.kill.play();
                            this.console.putln("You have beaten the crap out of Barz the Bartender!");
                            this.console.putln("You find $45 on his body");
                            this.money += 45;
                            ++this.count15;
                            this.console.getInt();
                            this.win.stop();
                            break;
                        }
                        this.health -= 20 - this.defense;
                        this.fighting.play();
                        this.console.putln();
                        this.console.putln("You attack Barz the Bartender ");
                        this.console.putln("Barz the Bartender fights back ");
                        this.console.putln("Your health is " + this.health + "");
                        this.console.putln("Barz the Bartender's health is " + this.eHealth);
                        answer = this.console.getInt();
                    }
                    break;
                }
                case 2: {
                    this.ff7.stop();
                    this.menu();
                    break;
                }
                default: {
                    this.ff7.stop();
                    break;
                }
            }
        }
        if (guy == 16) {
            this.update();
            this.eHealth = 75;
            this.console.putln("You are fighting Sebass ");
            this.console.putln("Type '1' to attack ");
            this.console.putln("Type '2' to run ");
            answer = this.console.getInt();
            switch (answer) {
                case 1: {
                    while (answer == 1) {
                        if (this.BSC / 4 < 100) {
                            this.defense = this.BSC / 4;
                        }
                        else {
                            this.defense = 100;
                        }
                        if (this.health - (30 - this.defense) <= 0) {
                            this.choke.play();
                            this.console.putln("Sebass has beat you! You lose!");
                            this.console.getInt();
                            this.ff7.stop();
                            this.doh.play();
                            this.finished = true;
                            break;
                        }
                        this.eHealth -= this.power;
                        if (this.eHealth <= 0) {
                            this.ff7.stop();
                            this.win.play();
                            this.kill.play();
                            this.console.putln("You have spit into Sebass' burger!");
                            this.console.putln("You find $75 on his body");
                            this.money += 75;
                            ++this.count16;
                            this.console.getInt();
                            this.win.stop();
                            break;
                        }
                        this.health -= 30 - this.defense;
                        this.fighting.play();
                        this.console.putln();
                        this.console.putln("You attack Sebass ");
                        this.console.putln("Sebass fights back ");
                        this.console.putln("Your health is " + this.health + "");
                        this.console.putln("Sebass's health is " + this.eHealth);
                        answer = this.console.getInt();
                    }
                    break;
                }
                case 2: {
                    this.ff7.stop();
                    this.menu();
                    break;
                }
                default: {
                    this.ff7.stop();
                    break;
                }
            }
        }
        if (guy == 17) {
            this.update();
            this.eHealth = 80;
            this.console.putln("You are fighting Frank the Fan ");
            this.console.putln("Type '1' to attack ");
            this.console.putln("Type '2' to run ");
            answer = this.console.getInt();
            switch (answer) {
                case 1: {
                    while (answer == 1) {
                        if (this.BSC / 4 < 100) {
                            this.defense = this.BSC / 4;
                        }
                        else {
                            this.defense = 100;
                        }
                        if (this.health - (25 - this.defense) <= 0) {
                            this.choke.play();
                            this.console.putln("Frank the Fan has beat you! You lose!");
                            this.console.getInt();
                            this.ff7.stop();
                            this.doh.play();
                            this.finished = true;
                            break;
                        }
                        this.eHealth -= this.power;
                        if (this.eHealth <= 0) {
                            this.ff7.stop();
                            this.win.play();
                            this.kill.play();
                            this.console.putln("You freaked out on Frank the Fan!");
                            this.console.putln("You find $70 on his body");
                            this.money += 70;
                            ++this.count17;
                            this.console.getInt();
                            this.win.stop();
                            break;
                        }
                        this.health -= 25 - this.defense;
                        this.fighting.play();
                        this.console.putln();
                        this.console.putln("You attack Frank the Fan ");
                        this.console.putln("Frank the Fan fights back ");
                        this.console.putln("Your health is " + this.health + "");
                        this.console.putln("Frank the Fan's health is " + this.eHealth);
                        answer = this.console.getInt();
                    }
                    break;
                }
                case 2: {
                    this.ff7.stop();
                    this.menu();
                    break;
                }
                default: {
                    this.ff7.stop();
                    break;
                }
            }
        }
        if (guy == 18) {
            this.update();
            this.eHealth = 85;
            this.console.putln("You are fighting Willy White Trash ");
            this.console.putln("Type '1' to attack ");
            this.console.putln("Type '2' to run ");
            answer = this.console.getInt();
            switch (answer) {
                case 1: {
                    while (answer == 1) {
                        if (this.BSC / 4 < 100) {
                            this.defense = this.BSC / 4;
                        }
                        else {
                            this.defense = 100;
                        }
                        if (this.health - (25 - this.defense) <= 0) {
                            this.choke.play();
                            this.console.putln("Willy White Trash has beat you! You lose!");
                            this.console.getInt();
                            this.ff7.stop();
                            this.doh.play();
                            this.finished = true;
                            break;
                        }
                        this.eHealth -= this.power;
                        if (this.eHealth <= 0) {
                            this.ff7.stop();
                            this.win.play();
                            this.kill.play();
                            this.console.putln("You have whapped Willy White Trash!");
                            this.console.putln("You find 50 food tickets on his body and sell them for $80");
                            this.money += 80;
                            ++this.count18;
                            this.console.getInt();
                            this.win.stop();
                            break;
                        }
                        this.health -= 25 - this.defense;
                        this.fighting.play();
                        this.console.putln();
                        this.console.putln("You attack Willy White Trash ");
                        this.console.putln("Willy White Trash fights back ");
                        this.console.putln("Your health is " + this.health + "");
                        this.console.putln("Willy White Trash's health is " + this.eHealth);
                        answer = this.console.getInt();
                    }
                    break;
                }
                case 2: {
                    this.ff7.stop();
                    this.menu();
                    break;
                }
                default: {
                    this.ff7.stop();
                    break;
                }
            }
        }
        if (guy == 19) {
            this.update();
            this.eHealth = 90;
            this.console.putln("You are fighting Jeff Gordon ");
            this.console.putln("Type '1' to attack ");
            this.console.putln("Type '2' to run ");
            answer = this.console.getInt();
            switch (answer) {
                case 1: {
                    while (answer == 1) {
                        if (this.BSC / 4 < 100) {
                            this.defense = this.BSC / 4;
                        }
                        else {
                            this.defense = 100;
                        }
                        if (this.health - (25 - this.defense) <= 0) {
                            this.choke.play();
                            this.console.putln("Jeff Gordon has beat you! You lose!");
                            this.console.getInt();
                            this.ff7.stop();
                            this.doh.play();
                            this.finished = true;
                            break;
                        }
                        this.eHealth -= this.power;
                        if (this.eHealth <= 0) {
                            this.ff7.stop();
                            this.win.play();
                            this.kill.play();
                            this.console.putln("You have ran over Jeff Gordon with your " + this.vehicle + "!");
                            this.console.putln("You find $80 on his body");
                            this.money += 90;
                            ++this.count19;
                            this.console.getInt();
                            this.win.stop();
                            break;
                        }
                        this.health -= 25 - this.defense;
                        this.fighting.play();
                        this.console.putln();
                        this.console.putln("You attack Jeff Gordon ");
                        this.console.putln("Jeff Gordon fights back ");
                        this.console.putln("Your health is " + this.health + "");
                        this.console.putln("Jeff Gordon's health is " + this.eHealth);
                        answer = this.console.getInt();
                    }
                    break;
                }
                case 2: {
                    this.ff7.stop();
                    this.menu();
                    break;
                }
                default: {
                    this.ff7.stop();
                    break;
                }
            }
        }
        if (guy == 20) {
            this.update();
            this.eHealth = 95;
            this.console.putln("You are fighting Rocky the Rottweiler ");
            this.console.putln("Type '1' to attack ");
            this.console.putln("Type '2' to run ");
            answer = this.console.getInt();
            switch (answer) {
                case 1: {
                    while (answer == 1) {
                        if (this.BSC / 4 < 100) {
                            this.defense = this.BSC / 4;
                        }
                        else {
                            this.defense = 100;
                        }
                        if (this.health - (30 - this.defense) <= 0) {
                            this.choke.play();
                            this.console.putln("Rocky the Rottweiler has beat you! You lose!");
                            this.console.getInt();
                            this.ff7.stop();
                            this.doh.play();
                            this.finished = true;
                            break;
                        }
                        this.eHealth -= this.power;
                        if (this.eHealth <= 0) {
                            this.ff7.stop();
                            this.win.play();
                            this.kill.play();
                            this.console.putln("You have battered Rocky the Rottweiler!");
                            this.console.putln("You beat up his owner and take $100");
                            this.money += 2;
                            ++this.count20;
                            this.console.getInt();
                            this.win.stop();
                            break;
                        }
                        this.health -= 30 - this.defense;
                        this.fighting.play();
                        this.console.putln();
                        this.console.putln("You attack Rocky the Rottweiler ");
                        this.console.putln("Rocky the Rottweilerr fights back ");
                        this.console.putln("Your health is " + this.health + "");
                        this.console.putln("Rocky the Rottweiler's health is " + this.eHealth);
                        answer = this.console.getInt();
                    }
                    break;
                }
                case 2: {
                    this.ff7.stop();
                    this.menu();
                    break;
                }
                default: {
                    this.ff7.stop();
                    break;
                }
            }
        }
        if (guy == 21) {
            this.update();
            this.eHealth = 100;
            this.console.putln("You are fighting Rambo the Rough Rider ");
            this.console.putln("Type '1' to attack ");
            this.console.putln("Type '2' to run ");
            answer = this.console.getInt();
            switch (answer) {
                case 1: {
                    while (answer == 1) {
                        if (this.BSC / 4 < 100) {
                            this.defense = this.BSC / 4;
                        }
                        else {
                            this.defense = 100;
                        }
                        if (this.health - (45 - this.defense) <= 0) {
                            this.choke.play();
                            this.console.putln("Rambo the Rough Rider has beat you! You lose!");
                            this.console.getInt();
                            this.ff7.stop();
                            this.doh.play();
                            this.finished = true;
                            break;
                        }
                        this.eHealth -= this.power;
                        if (this.eHealth <= 0) {
                            this.ff7.stop();
                            this.win.play();
                            this.kill.play();
                            this.console.putln("You have pummeled Rambo the Rough Rider into submission!");
                            this.console.putln("You find $100 on his body");
                            this.money += 100;
                            ++this.count21;
                            this.console.getInt();
                            this.win.stop();
                            break;
                        }
                        this.health -= 45 - this.defense;
                        this.fighting.play();
                        this.console.putln();
                        this.console.putln("You attack Rambo the Rough Rider ");
                        this.console.putln("Rambo the Rough Rider fights back ");
                        this.console.putln("Your health is " + this.health + "");
                        this.console.putln("Rambo the Rough Rider's health is " + this.eHealth);
                        answer = this.console.getInt();
                    }
                    break;
                }
                case 2: {
                    this.ff7.stop();
                    this.menu();
                    break;
                }
                default: {
                    this.ff7.stop();
                    break;
                }
            }
        }
        if (guy == 22) {
            this.update();
            this.eHealth = 100;
            this.console.putln("You are fighting Wanda ");
            this.console.putln("Type '1' to attack ");
            this.console.putln("Type '2' to run ");
            answer = this.console.getInt();
            switch (answer) {
                case 1: {
                    while (answer == 1) {
                        if (this.BSC / 4 < 100) {
                            this.defense = this.BSC / 4;
                        }
                        else {
                            this.defense = 100;
                        }
                        if (this.health - (45 - this.defense) <= 0) {
                            this.choke.play();
                            this.console.putln("Wanda has beat you! You lose!");
                            this.console.getInt();
                            this.ff7.stop();
                            this.doh.play();
                            this.finished = true;
                            break;
                        }
                        this.eHealth -= this.power;
                        if (this.eHealth <= 0) {
                            this.ff7.stop();
                            this.win.play();
                            this.kill.play();
                            this.console.putln("You have whapped Wanda into wonderland!");
                            this.console.putln("You find $100 on his body");
                            this.money += 100;
                            ++this.count22;
                            this.console.getInt();
                            this.win.stop();
                            break;
                        }
                        this.health -= 45 - this.defense;
                        this.fighting.play();
                        this.console.putln();
                        this.console.putln("You attack Wanda ");
                        this.console.putln("Wanda fights back ");
                        this.console.putln("Your health is " + this.health + "");
                        this.console.putln("Wanda's health is " + this.eHealth);
                        answer = this.console.getInt();
                    }
                    break;
                }
                case 2: {
                    this.ff7.stop();
                    this.menu();
                    break;
                }
                default: {
                    this.ff7.stop();
                    break;
                }
            }
        }
        if (guy == 23) {
            this.update();
            this.eHealth = 100;
            this.console.putln("You are fighting Ruh Vogaluh ");
            this.console.putln("Type '1' to attack ");
            this.console.putln("Type '2' to run ");
            answer = this.console.getInt();
            switch (answer) {
                case 1: {
                    while (answer == 1) {
                        if (this.BSC / 4 < 100) {
                            this.defense = this.BSC / 4;
                        }
                        this.defense = 100;
                        if (this.health - (50 - this.defense) <= 0) {
                            this.choke.play();
                            this.console.putln("Ruh Vogaluh has beat you! You lose!");
                            this.console.getInt();
                            this.ff7.stop();
                            this.doh.play();
                            this.finished = true;
                            break;
                        }
                        this.eHealth -= this.power;
                        if (this.eHealth <= 0) {
                            this.ff7.stop();
                            this.win.play();
                            this.kill.play();
                            this.console.putln("You have pummeled Ruh Vogaluh into submission!");
                            this.console.putln("You find $100 on his body");
                            this.money += 100;
                            ++this.count23;
                            this.console.getInt();
                            this.win.stop();
                            break;
                        }
                        this.health -= 40 - this.defense;
                        this.fighting.play();
                        this.console.putln();
                        this.console.putln("You attack Ruh Vogaluh ");
                        this.console.putln("Ruh Vogaluh fights back ");
                        this.console.putln("Your health is " + this.health + "");
                        this.console.putln("Ruh Vogaluh's health is " + this.eHealth);
                        answer = this.console.getInt();
                    }
                    break;
                }
                case 2: {
                    this.ff7.stop();
                    this.menu();
                    break;
                }
                default: {
                    this.ff7.stop();
                    break;
                }
            }
        }
        if (guy == 24) {
            this.update();
            this.eHealth = 150;
            this.console.putln("You are fighting Head Honcho Harrison ");
            this.console.putln("Type '1' to attack ");
            this.console.putln("Type '2' to run ");
            answer = this.console.getInt();
            switch (answer) {
                case 1: {
                    while (answer == 1) {
                        if (this.BSC / 4 < 100) {
                            this.defense = this.BSC / 4;
                        }
                        else {
                            this.defense = 100;
                        }
                        if (this.health - (50 - this.defense) <= 0) {
                            this.choke.play();
                            this.console.putln("Head Honcho Harrison has beat you! You lose!");
                            this.console.getInt();
                            this.ff7.stop();
                            this.doh.play();
                            this.finished = true;
                            break;
                        }
                        this.eHealth -= this.power;
                        if (this.eHealth <= 0) {
                            this.ff7.stop();
                            this.win.play();
                            this.kill.play();
                            this.console.putln("You have honked Head Honcho Harrison horn!");
                            this.console.putln("You find $120 on his body");
                            this.money += 120;
                            ++this.count24;
                            this.console.getInt();
                            this.win.stop();
                            break;
                        }
                        this.health -= 50 - this.defense;
                        this.fighting.play();
                        this.console.putln();
                        this.console.putln("You attack Head Honcho Harrison ");
                        this.console.putln("Head Honcho Harrison fights back ");
                        this.console.putln("Your health is " + this.health + "");
                        this.console.putln("Head Honcho Harrison's health is " + this.eHealth);
                        answer = this.console.getInt();
                    }
                    break;
                }
                case 2: {
                    this.ff7.stop();
                    this.menu();
                    break;
                }
                default: {
                    this.ff7.stop();
                    break;
                }
            }
        }
        if (guy == 25) {
            this.update();
            this.eHealth = 200;
            this.console.putln("You are fighting Mr. Tank!!! ");
            this.console.putln("Type '1' to attack ");
            this.console.putln("Type '2' to run ");
            answer = this.console.getInt();
            switch (answer) {
                case 1: {
                    while (answer == 1) {
                        if (this.BSC / 4 < 100) {
                            this.defense = this.BSC / 4;
                        }
                        else {
                            this.defense = 100;
                        }
                        if (this.health - (60 - this.defense) <= 0) {
                            this.choke.play();
                            this.console.putln("Mr. Tank has beat you! You lose!");
                            this.console.getInt();
                            this.ff7.stop();
                            this.doh.play();
                            this.finished = true;
                            break;
                        }
                        this.eHealth -= this.power;
                        if (this.eHealth <= 0) {
                            this.ff7.stop();
                            this.win.play();
                            this.kill.play();
                            this.console.putln("You have defeated Mr. Tank!");
                            this.console.putln("You have beat the game!");
                            this.ff7.stop();
                            this.finished = true;
                            break;
                        }
                        this.health -= 60 - this.defense;
                        this.fighting.play();
                        this.console.putln();
                        this.console.putln("You attack Mr. Tank ");
                        this.console.putln("Mr. Tank fights back ");
                        this.console.putln("Your health is " + this.health + "");
                        this.console.putln("Mr. Tank's health is " + this.eHealth);
                        answer = this.console.getInt();
                    }
                    break;
                }
                case 2: {
                    this.ff7.stop();
                    this.menu();
                    break;
                }
                default: {
                    this.ff7.stop();
                    break;
                }
            }
        }
    }
}
