// 
// Decompiled by Procyon v0.5.30
// 

package texttwist;

import java.awt.Rectangle;
import java.awt.MediaTracker;
import sprite.Sprite;
import java.awt.Image;
import java.util.Vector;
import inknet.NetUser;
import inknet.User;
import java.net.URL;
import sprite.ButtonSprite;
import java.awt.Event;
import sprite.SpriteBase;
import sprite.NumberSprite;
import sprite.AnimateSprite;
import sprite.GameCanvas;

public class TwistCanvas extends GameCanvas
{
    private volatile int \u00c0;
    private TwistNet \u00c1;
    private long \u00c2;
    private int \u00c3;
    private AnimateSprite[] \u00c4;
    private int \u00c5;
    private AnimateSprite[] \u00c6;
    private int \u00c7;
    private int \u00c8;
    private int \u00c9;
    private int \u00ca;
    private int \u00cb;
    private int \u00cc;
    private NumberSprite \u00cd;
    private NumberSprite \u00ce;
    private int \u00cf;
    private boolean \u00d0;
    private int \u00d1;
    private String \u00d2;
    private int \u00d3;
    private String \u00d4;
    private SpriteBase \u00d5;
    
    public boolean keyDown(final Event event, final int n) {
        if (this.\u00c0 == 1) {
            this.setEvent(event);
            return true;
        }
        return super.keyDown(event, n);
    }
    
    private void \u00c0(final char c) {
        for (int i = 0; i < this.\u00c6.length; ++i) {
            if (this.\u00c6[i] != null) {
                final char c2 = (char)(this.\u00c6[i].getFrame() + 65 - 1);
                if (c == (char)(this.\u00c6[i].getFrame() + 97 - 1) || c == c2) {
                    this.\u00c6[i].setDestination(this.\u00ca + this.\u00cc * this.\u00c5, this.\u00cb, 5);
                    this.\u00c4[this.\u00c5++] = this.\u00c6[i];
                    this.\u00c6[i] = null;
                    this.playSound(7);
                    return;
                }
            }
        }
    }
    
    private void \u00c1() {
        final AnimateSprite animateSprite = (AnimateSprite)super.m_sprites[18];
        final TwistTable twistTable = (TwistTable)super.m_sprites[9];
        String string = "";
        for (int i = 0; i < this.\u00c5; ++i) {
            string += (char)(this.\u00c4[i].getFrame() + 97 - 1);
        }
        this.\u00d2 = string;
        switch (twistTable.guessWord(string)) {
            case 1: {
                this.\u00cf += string.length() * string.length() * 10;
                if (string.length() >= 6) {
                    this.\u00cf += 100;
                    this.\u00d0 = true;
                    this.playSound(0);
                    animateSprite.setFrame(2);
                    animateSprite.show(true);
                }
                else {
                    this.playSound(6);
                }
                if (twistTable.foundAll()) {
                    this.playSound(5);
                    this.\u00cf += 200 * twistTable.getNumWords();
                    this.\u00c0 = 3;
                    this.\u00c2 = System.currentTimeMillis();
                    super.m_sprites[22].show(true);
                    animateSprite.setFrame(1);
                    animateSprite.show(true);
                    ++this.\u00c3;
                    super.m_user.runCommand("LEVEL " + this.\u00c3);
                }
                this.\u00c8();
                break;
            }
            case 0: {
                this.playSound(1);
                animateSprite.setFrame(0);
                animateSprite.show(true);
                break;
            }
            case -1: {
                this.playSound(1);
                animateSprite.setFrame(3);
                animateSprite.show(true);
                break;
            }
        }
        while (this.\u00c5 > 0) {
            this.\u00c6();
        }
    }
    
    private boolean \u00c2(final Event event) {
        if (this.\u00c0 == 1) {
            super.m_sprites[18].show(false);
            switch (event.key) {
                case 32: {
                    this.\u00c7();
                    break;
                }
                case 8: {
                    if (this.\u00c5 > 0) {
                        this.playSound(7);
                        this.\u00c6();
                        break;
                    }
                    break;
                }
                case 10: {
                    if (this.\u00c5 > 0) {
                        this.\u00c1();
                        break;
                    }
                    this.\u00c3();
                    break;
                }
                default: {
                    if (event.key < 48 || event.key > 57) {
                        this.\u00c0((char)event.key);
                        this.\u00d3 = 0;
                        break;
                    }
                    this.\u00d3 *= 10;
                    this.\u00d3 += event.key - 48;
                    if (this.\u00d3 == 348) {
                        this.setEvent(new Event(this, 1001, "timeup"));
                        this.\u00d3 = 0;
                        break;
                    }
                    if (this.\u00d3 == 1969) {
                        this.\u00d0 = true;
                        this.setEvent(new Event(this, 1001, "timeup"));
                        this.\u00d3 = 0;
                        break;
                    }
                    break;
                }
            }
        }
        return true;
    }
    
    public boolean processEvent() {
        if (this.\u00c1 != null && super.m_saveEvent != null) {
            final Event saveEvent = super.m_saveEvent;
            if (saveEvent.id == 501) {
                this.waitCursor();
                this.\u00cd(saveEvent);
                this.normalCursor();
                this.\u00c4();
                this.\u00d5 = null;
            }
            else if (saveEvent.id == 503 || saveEvent.id == 506) {
                this.\u00c5(saveEvent);
            }
            else if (saveEvent.id == 401) {
                this.\u00c2(saveEvent);
                this.\u00c4();
            }
            else if (saveEvent.id == 1001 && saveEvent.arg instanceof String) {
                final String s = (String)saveEvent.arg;
                if (s.equals("timeup") && this.\u00c0 == 1) {
                    this.\u00c2 = System.currentTimeMillis();
                    ((TwistTable)super.m_sprites[9]).showAll();
                    if (this.\u00d0) {
                        this.\u00c0 = 3;
                        ++this.\u00c3;
                        super.m_user.runCommand("LEVEL " + this.\u00c3);
                        super.m_sprites[22].show(true);
                    }
                    else {
                        this.playSound(3);
                        this.\u00c0 = 0;
                        super.m_sprites[19].show(true);
                        super.m_user.cashOut(this.\u00cf);
                    }
                }
                else if (s.equals("next") && this.\u00c0 == 4) {
                    this.\u00ca();
                }
                this.\u00c4();
            }
        }
        return true;
    }
    
    public TwistCanvas() {
        this.\u00c0 = 2;
        this.\u00c1 = null;
        this.\u00c2 = 0L;
        this.\u00d5 = null;
        this.\u00d3 = 0;
    }
    
    private void \u00c3() {
        for (int i = 0; i < this.\u00d2.length(); ++i) {
            this.\u00c0(this.\u00d2.charAt(i));
        }
    }
    
    private void \u00c4() {
        if (this.\u00c0 == 1) {
            ((ButtonSprite)super.m_sprites[3]).enable(true);
            ((ButtonSprite)super.m_sprites[4]).enable(this.\u00c5 > 0);
            ((ButtonSprite)super.m_sprites[2]).enable(this.\u00c5 > 0);
            ((ButtonSprite)super.m_sprites[5]).enable(this.\u00c5 == 0 && this.\u00d2.length() > 0);
        }
        else {
            int n = 2;
            do {
                if (super.m_sprites[n] instanceof ButtonSprite) {
                    ((ButtonSprite)super.m_sprites[n]).enable(false);
                }
            } while (++n <= 5);
        }
        ((ButtonSprite)super.m_sprites[21]).enable(this.\u00c0 == 2);
        ((ButtonSprite)super.m_sprites[23]).enable(this.\u00c0 == 4);
    }
    
    public void destroy() {
        if (this.\u00c1 != null) {
            this.\u00c1.end();
        }
        super.destroy();
    }
    
    private boolean \u00c5(final Event event) {
        boolean b = false;
        if (this.\u00d5 != null) {
            if (this.\u00d5.hittest(event.x, event.y)) {
                return true;
            }
            if (this.\u00d5 instanceof ButtonSprite && ((ButtonSprite)this.\u00d5).enabled()) {
                ((ButtonSprite)this.\u00d5).setState(1);
            }
            this.\u00d5 = null;
        }
        for (int i = 0; i < super.m_sprites.length; ++i) {
            if (super.m_sprites[i] instanceof ButtonSprite && ((ButtonSprite)super.m_sprites[i]).enabled() && super.m_sprites[i].hittest(event.x, event.y)) {
                ((ButtonSprite)super.m_sprites[i]).setState(2);
                this.\u00d5 = super.m_sprites[i];
                b = true;
            }
        }
        if (b) {
            this.handCursor();
        }
        else {
            this.normalCursor();
        }
        return true;
    }
    
    private void \u00c6() {
        int n = 5;
        while (this.\u00c6[n] != null) {
            if (--n < 0) {
                return;
            }
        }
        --this.\u00c5;
        this.\u00c6[n] = this.\u00c4[this.\u00c5];
        this.\u00c4[this.\u00c5] = null;
        this.\u00c6[n].setDestination(this.\u00c7 + this.\u00c9 * n, this.\u00c8, 5);
    }
    
    public void InitCanvas(final URL url, final User user, final String s, final int n) {
        super.InitCanvas(url, user, s, n);
        super.m_sprites = new SpriteBase[30];
        if (s == null || s.length() == 0) {
            super.m_strDataFile = "twist.dat";
        }
        this.loadDataFile(url);
    }
    
    public boolean initGame() {
        if (this.\u00c1 != null) {
            this.\u00c1.end();
        }
        this.\u00c1 = null;
        if (super.m_user instanceof NetUser) {
            this.\u00c1 = (TwistNet)((NetUser)super.m_user).getInterface("texttwist.TwistNet");
        }
        if (this.\u00c1 == null) {
            this.\u00c1 = new TwistLocal();
        }
        this.\u00c1.initialize(super.m_user);
        return true;
    }
    
    public synchronized void nextFrame() {
        boolean b = false;
        final int n = (int)((System.currentTimeMillis() - this.\u00c2) / 1000L);
        if (this.\u00c0 == 1) {
            int n2 = this.\u00d1 - n;
            if (n2 < 0) {
                this.setEvent(new Event(this, 1001, "timeup"));
                n2 = 0;
            }
            final int number = n2 / 60;
            final int number2 = n2 % 60;
            if (number2 != this.\u00ce.getNumber()) {
                if (number == 0 && number2 < 10) {
                    this.playSound(2);
                }
                this.\u00ce.setNumber(number2);
            }
            if (number != this.\u00cd.getNumber()) {
                this.\u00cd.setNumber(number);
            }
        }
        else if (this.\u00c0 == 0) {
            if (n >= 3) {
                super.m_sprites[19].show(false);
                super.m_sprites[18].show(false);
                if (this.\u00d4 != null) {
                    super.m_sprites[24].show(true);
                    ((ButtonSprite)super.m_sprites[25]).enable(true);
                    ((ButtonSprite)super.m_sprites[26]).enable(true);
                    this.\u00c0 = 5;
                }
                else {
                    super.m_sprites[20].show(true);
                    ((ButtonSprite)super.m_sprites[21]).enable(true);
                    this.\u00c0 = 2;
                }
            }
        }
        else if (this.\u00c0 == 3 && n > 3) {
            ((ButtonSprite)super.m_sprites[23]).enable(true);
            this.\u00c0 = 4;
        }
        if (super.m_bWaiting) {
            for (int i = 0; i < super.m_sprites.length; ++i) {
                if (super.m_sprites[i] != null && super.m_sprites[i] instanceof AnimateSprite && ((AnimateSprite)super.m_sprites[i]).Animating()) {
                    b = true;
                }
            }
            if (!b && super.m_bWaiting) {
                super.m_bWaiting = false;
                this.notifyAll();
            }
        }
        super.nextFrame();
    }
    
    private void \u00c7() {
        if (this.\u00cc()) {
            this.playSound(4);
        }
        int n = 0;
        do {
            if (this.\u00c6[n] != null) {
                this.\u00c6[n].setDestination(this.\u00c7 + this.\u00c9 * n, this.\u00c8, 10 + n * 2);
            }
        } while (++n < 6);
    }
    
    private void \u00c8() {
        ((NumberSprite)super.m_sprites[6]).setNumber(this.\u00cf);
        super.m_user.runCommand("SCORE " + this.\u00cf);
    }
    
    private boolean \u00c9(final String s) {
        if (s.length() <= 1) {
            return true;
        }
        final char char1 = s.charAt(0);
        for (int i = 1; i < s.length(); ++i) {
            if (s.charAt(i) != char1) {
                return false;
            }
        }
        return true;
    }
    
    private void \u00ca() {
        this.\u00d0 = false;
        this.\u00cd.setNumber(2);
        this.\u00ce.setNumber(0);
        this.\u00d2 = "";
        final String randomLetters = this.\u00c1.getRandomLetters();
        if (randomLetters == null) {
            return;
        }
        final String lowerCase = randomLetters.toLowerCase();
        int n = 0;
        do {
            (this.\u00c6[n] = (AnimateSprite)super.m_sprites[10 + n]).setFrame(lowerCase.charAt(n) - 'a' + '\u0001');
            this.\u00c4[n] = null;
        } while (++n < 6);
        this.\u00cc();
        int n2 = 0;
        do {
            super.m_sprites[10 + n2] = this.\u00c6[n2];
            this.\u00c6[n2].setPosition(this.\u00c7 + this.\u00c9 * n2, this.\u00c8);
            this.\u00c6[n2].show(true);
        } while (++n2 < 6);
        this.\u00c5 = 0;
        final Vector wordList = this.\u00c1.getWordList(lowerCase);
        final TwistTable twistTable = (TwistTable)super.m_sprites[9];
        twistTable.setWords(wordList);
        this.\u00d1 = 120;
        if (twistTable.getNumWords() > 20) {
            this.\u00d1 += 30;
        }
        this.\u00c2 = System.currentTimeMillis();
        this.\u00c0 = 1;
        super.m_sprites[20].show(false);
        super.m_sprites[18].show(false);
        super.m_sprites[22].show(false);
        this.requestFocus();
    }
    
    public void setImages(final Image[] array, final Object[] clips) {
        super.m_clips = clips;
        final Rectangle bounds = this.bounds();
        this.setImageSize(bounds.width, bounds.height);
        int n = 0;
        int n2 = 0;
        this.\u00c4 = new AnimateSprite[6];
        this.\u00c6 = new AnimateSprite[6];
        final Sprite sprite = new Sprite();
        sprite.setImage(array[n++]);
        super.m_sprites[0] = sprite;
        final AnimateSprite animateSprite = new AnimateSprite(array[n++], super.m_nDataFile[n2], super.m_nDataFile[n2 + 1], null);
        animateSprite.setPosition(super.m_nDataFile[n2 + 2], super.m_nDataFile[n2 + 3]);
        animateSprite.show(false);
        n2 += 4;
        super.m_sprites[18] = animateSprite;
        final Sprite sprite2 = new Sprite();
        sprite2.setImage(array[n++]);
        sprite2.setPosition(super.m_nDataFile[n2], super.m_nDataFile[n2 + 1]);
        sprite2.show(true);
        n2 += 2;
        super.m_sprites[20] = sprite2;
        final ButtonSprite buttonSprite = new ButtonSprite(array[n++], super.m_nDataFile[n2], super.m_nDataFile[n2 + 1], super.m_nDataFile[n2 + 2], super.m_nDataFile[n2 + 3], super.m_nDataFile[n2 + 4]);
        buttonSprite.enable(true);
        ((Sprite)(super.m_sprites[21] = buttonSprite)).setPosition(super.m_nDataFile[n2 + 5], super.m_nDataFile[n2 + 6]);
        n2 += 7;
        final Sprite sprite3 = new Sprite();
        sprite3.setImage(array[n++]);
        sprite3.setPosition(super.m_nDataFile[n2], super.m_nDataFile[n2 + 1]);
        sprite3.show(false);
        n2 += 2;
        super.m_sprites[19] = sprite3;
        final NumberSprite numberSprite = new NumberSprite(false, false);
        numberSprite.setImage(array[n]);
        numberSprite.setPosition(super.m_nDataFile[n2], super.m_nDataFile[n2 + 1]);
        n2 += 2;
        numberSprite.setAlignment(super.m_nDataFile[n2++]);
        numberSprite.setNumber(0);
        super.m_sprites[6] = numberSprite;
        final NumberSprite \u00ed = new NumberSprite(false, false);
        \u00ed.setImage(array[n]);
        \u00ed.setPosition(super.m_nDataFile[n2], super.m_nDataFile[n2 + 1]);
        n2 += 2;
        \u00ed.setAlignment(super.m_nDataFile[n2++]);
        \u00ed.setNumber(2);
        super.m_sprites[7] = (this.\u00cd = \u00ed);
        final NumberSprite \u00ee = new NumberSprite(false, false);
        \u00ee.setImage(array[n]);
        \u00ee.setPosition(super.m_nDataFile[n2], super.m_nDataFile[n2 + 1]);
        n2 += 2;
        \u00ee.setAlignment(super.m_nDataFile[n2++]);
        \u00ee.setPadding(2);
        \u00ee.setNumber(0);
        super.m_sprites[8] = (this.\u00ce = \u00ee);
        ++n;
        final TwistTable twistTable = new TwistTable(array[n], array[n + 1], super.m_nDataFile[n2], super.m_nDataFile[n2 + 1], super.m_nDataFile[n2 + 2], super.m_nDataFile[n2 + 3]);
        super.m_sprites[9] = twistTable;
        final Vector<String> words = new Vector<String>();
        words.addElement("TEXT");
        words.addElement("TWIST");
        twistTable.setWords(words);
        twistTable.showAll();
        n2 += 4;
        n += 2;
        final AnimateSprite animateSprite2 = new AnimateSprite(array[n++], 9, 3, null);
        animateSprite2.show(false);
        int n3 = 10;
        do {
            super.m_sprites[n3] = animateSprite2.copy();
        } while (++n3 <= 16);
        this.\u00c7 = super.m_nDataFile[n2++];
        this.\u00c8 = super.m_nDataFile[n2++];
        this.\u00c9 = super.m_nDataFile[n2++];
        this.\u00ca = super.m_nDataFile[n2++];
        this.\u00cb = super.m_nDataFile[n2++];
        this.\u00cc = super.m_nDataFile[n2++];
        final Sprite sprite4 = new Sprite();
        sprite4.setImage(array[n++]);
        sprite4.setPosition(super.m_nDataFile[n2], super.m_nDataFile[n2 + 1]);
        sprite4.show(false);
        n2 += 2;
        super.m_sprites[22] = sprite4;
        final ButtonSprite buttonSprite2 = new ButtonSprite(array[n++], super.m_nDataFile[n2], super.m_nDataFile[n2 + 1], super.m_nDataFile[n2 + 2], super.m_nDataFile[n2 + 3], super.m_nDataFile[n2 + 4]);
        buttonSprite2.enable(true);
        ((Sprite)(super.m_sprites[23] = buttonSprite2)).setPosition(super.m_nDataFile[n2 + 5], super.m_nDataFile[n2 + 6]);
        n2 += 7;
        this.\u00d4 = super.m_user.getApplet().getParameter("BUYURL");
        if (this.\u00d4 != null) {
            final Sprite sprite5 = new Sprite();
            sprite5.setImage(array[n++]);
            sprite5.setPosition(super.m_nDataFile[n2], super.m_nDataFile[n2 + 1]);
            sprite5.show(false);
            n2 += 2;
            super.m_sprites[24] = sprite5;
            final ButtonSprite buttonSprite3 = new ButtonSprite(array[n++], super.m_nDataFile[n2], super.m_nDataFile[n2 + 1], super.m_nDataFile[n2 + 2], super.m_nDataFile[n2 + 3], super.m_nDataFile[n2 + 4]);
            buttonSprite3.enable(false);
            ((Sprite)(super.m_sprites[26] = buttonSprite3)).setPosition(super.m_nDataFile[n2 + 5], super.m_nDataFile[n2 + 6]);
            n2 += 7;
            final ButtonSprite buttonSprite4 = new ButtonSprite(array[n++], super.m_nDataFile[n2], super.m_nDataFile[n2 + 1], super.m_nDataFile[n2 + 2], super.m_nDataFile[n2 + 3], super.m_nDataFile[n2 + 4]);
            buttonSprite4.enable(false);
            ((Sprite)(super.m_sprites[25] = buttonSprite4)).setPosition(super.m_nDataFile[n2 + 5], super.m_nDataFile[n2 + 6]);
            n2 += 7;
        }
        else {
            n += 3;
            n2 += 16;
        }
        int i = 2;
        do {
            if (array[n] != null) {
                ((Sprite)(super.m_sprites[i] = new ButtonSprite(array[n], super.m_nDataFile[n2], super.m_nDataFile[n2 + 1], super.m_nDataFile[n2 + 2], super.m_nDataFile[n2 + 3], super.m_nDataFile[n2 + 4]))).setPosition(super.m_nDataFile[n2 + 5], super.m_nDataFile[n2 + 6]);
            }
            n2 += 7;
            ++i;
            ++n;
        } while (i <= 5);
        this.\u00c0 = 2;
        this.\u00c4();
        super.setImages(array, clips);
    }
    
    private String \u00cb() {
        String string = "";
        for (int i = 0; i < this.\u00c6.length; ++i) {
            if (this.\u00c6[i] != null) {
                string += (char)(this.\u00c6[i].getFrame() + 97 - 1);
            }
        }
        return string;
    }
    
    private boolean \u00cc() {
        final String \u00eb = this.\u00cb();
        if (this.\u00c9(\u00eb)) {
            return false;
        }
        for (int n = 0; n < 13 || \u00eb.equals(this.\u00cb()); ++n) {
            final int n2 = (int)(Math.random() * 6.0);
            final int n3 = (int)(Math.random() * 6.0);
            final AnimateSprite animateSprite = this.\u00c6[n3];
            this.\u00c6[n3] = this.\u00c6[n2];
            this.\u00c6[n2] = animateSprite;
        }
        return true;
    }
    
    private boolean \u00cd(final Event event) {
        if (this.\u00c0 == 2) {
            if (super.m_sprites[21].hittest(event.x, event.y)) {
                this.\u00cf = 0;
                this.\u00c3 = 1;
                super.m_user.runCommand("LEVEL " + this.\u00c3);
                this.\u00c8();
                this.\u00ca();
            }
        }
        else if (this.\u00c0 == 1) {
            super.m_sprites[18].show(false);
            if (super.m_sprites[3].hittest(event.x, event.y)) {
                this.\u00c7();
            }
            else if (super.m_sprites[4].hittest(event.x, event.y)) {
                while (this.\u00c5 > 0) {
                    this.\u00c6();
                }
            }
            else if (super.m_sprites[2].hittest(event.x, event.y)) {
                this.\u00c1();
            }
            else if (super.m_sprites[5].hittest(event.x, event.y)) {
                this.\u00c3();
            }
            else if (this.\u00c5 > 0 && this.\u00c4[this.\u00c5 - 1].hittest(event.x, event.y)) {
                this.playSound(7);
                this.\u00c6();
            }
            else {
                int n = 0;
                while (this.\u00c6[n] == null || !this.\u00c6[n].hittest(event.x, event.y)) {
                    if (++n >= 6) {
                        return true;
                    }
                }
                this.\u00c6[n].setDestination(this.\u00ca + this.\u00cc * this.\u00c5, this.\u00cb, 5);
                this.\u00c4[this.\u00c5++] = this.\u00c6[n];
                this.\u00c6[n] = null;
                this.playSound(7);
            }
        }
        else if (this.\u00c0 == 4) {
            if (super.m_sprites[23].hittest(event.x, event.y)) {
                this.setEvent(new Event(this, 1001, "next"));
            }
        }
        else if (this.\u00c0 == 5 && this.\u00d4 != null) {
            if (super.m_sprites[26].hittest(event.x, event.y)) {
                ((ButtonSprite)super.m_sprites[26]).enable(false);
                ((ButtonSprite)super.m_sprites[25]).enable(false);
                super.m_sprites[24].show(false);
                super.m_sprites[20].show(true);
                ((ButtonSprite)super.m_sprites[21]).enable(true);
                this.\u00c0 = 2;
            }
            else if (super.m_sprites[25].hittest(event.x, event.y)) {
                this.waitCursor();
                super.m_user.runCommand("casino");
                this.normalCursor();
            }
        }
        return true;
    }
}
