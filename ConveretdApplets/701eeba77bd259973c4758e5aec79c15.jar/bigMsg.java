import java.awt.Color;
import java.awt.Image;
import java.awt.Graphics;

// 
// Decompiled by Procyon v0.5.30
// 

class bigMsg
{
    private Graphics paper;
    private Image gfx;
    String msg1;
    String msg2;
    String msg3;
    String msg4;
    String msg5;
    
    public bigMsg(final Graphics paper, final Image gfx) {
        this.paper = paper;
        this.gfx = gfx;
        this.msg1 = "";
        this.msg2 = "";
        this.msg3 = "";
        this.msg4 = "";
        this.msg5 = "";
    }
    
    public void doMsg(final int n, final int n2, final int n3) {
        this.msg5 = "[press ENTER to continue]";
        switch (n) {
            case 1: {
                this.msg1 = "You'll need to keep your food";
                this.msg2 = "levels up if you're going to ";
                this.msg3 = "go wandering around all day, ";
                this.msg4 = "or your health will suffer.  ";
                break;
            }
            case 2: {
                this.msg1 = "Did you know that those wizards";
                this.msg2 = "collect plants and things,";
                this.msg3 = "put them into a magic cauldron";
                this.msg4 = "and create spells?";
                break;
            }
            case 3: {
                this.msg1 = "Did you know that you can press";
                this.msg2 = "TAB to change your selected item?";
                this.msg3 = "And press 'd' to drop it?  Or";
                this.msg4 = "press SPACE to 'use' it?";
                break;
            }
            case 4: {
                this.msg1 = "I heard that the cauldron exists";
                this.msg2 = "in several places at once, don't";
                this.msg3 = "ask me how though - it's magic!";
                break;
            }
            case 5: {
                this.msg1 = "Did you know that there are";
                this.msg2 = "scrolls lying around the place";
                this.msg3 = "with spell instructions on";
                this.msg4 = "them?";
                break;
            }
            case 6: {
                this.msg1 = "I know a fellow that tried to";
                this.msg2 = "just put any old things into the";
                this.msg3 = "cauldron and ended off summoning";
                this.msg4 = "up death himself!";
                break;
            }
            case 7: {
                this.msg1 = "I heard that with spells you";
                this.msg2 = "can destroy the evil creatures";
                this.msg3 = "that curse this land, and each";
                this.msg4 = "creature has a special spell.";
                break;
            }
            case 8: {
                this.msg1 = "I heard that you can find";
                this.msg2 = "spells to make you healthy,";
                this.msg3 = "feed you, transform things and";
                this.msg4 = "even vanish!";
                break;
            }
            case 9: {
                this.msg1 = "When you create a spell, you";
                this.msg2 = "can pick it up and use it by";
                this.msg3 = "pressing SPACE.  It'll be cast";
                this.msg4 = "in the direction you face.";
                break;
            }
            case 10: {
                this.msg1 = "I heard of a spell once to feed";
                this.msg2 = "a man until he's fit to burst -";
                this.msg3 = "Poppy, Rowan and Datura I think";
                this.msg4 = "it was.";
                break;
            }
            case 11: {
                this.msg1 = "You know why the land is so";
                this.msg2 = "evil?  It's because the queen";
                this.msg3 = "of the faery folk has been";
                this.msg4 = "captured by an evil wizard.";
                break;
            }
            case 12: {
                this.msg1 = "I heard that the evil wizard";
                this.msg2 = "lives in the southwest of the land,";
                this.msg3 = "and that a river leads the";
                this.msg4 = "way there.";
                break;
            }
            case 13: {
                this.msg1 = "A spell I heard of once was Datura,";
                this.msg2 = "Slippery Elm and Bones.  Good";
                this.msg3 = "against anything except bats,";
                this.msg4 = "so I believe.";
                break;
            }
            case 14: {
                this.msg1 = "I once heard that wizards of old";
                this.msg2 = "could disappear and appear";
                this.msg3 = "somewhere else with ease?";
                this.msg4 = "Must be a special spell eh?";
                break;
            }
            case 15: {
                this.msg1 = "The Voidum spell connects the";
                this.msg2 = "magical stones together, and ";
                this.msg3 = "the plants for the spell always";
                this.msg4 = "grow nearby.";
                break;
            }
            case 70: {
                this.msg1 = "'Sorbum Granus'";
                this.msg2 = "Take Henbane &";
                this.msg3 = "add Slippery Elm";
                this.msg4 = "";
                break;
            }
            case 71: {
                this.msg1 = "'Treorbum Tanis'";
                this.msg2 = "Take Henbane &";
                this.msg3 = "add Poppy &";
                this.msg4 = "add Slippery Elm";
                break;
            }
            case 72: {
                this.msg1 = "'Fugit Vim'";
                this.msg2 = "Take Fly Agaric &";
                this.msg3 = "add Henbane";
                this.msg4 = "";
                break;
            }
            case 73: {
                this.msg1 = "'Caldus Divum'";
                this.msg2 = "Take Slippery Elm &";
                this.msg3 = "add Mandrake &";
                this.msg4 = "add Poppy";
                break;
            }
            case 74: {
                this.msg1 = "'Raniam Vim'";
                this.msg2 = "Take Datura &";
                this.msg3 = "add Fly Agaric &";
                this.msg4 = "add Belladona";
                break;
            }
            case 75: {
                this.msg1 = "'Beezaltium'";
                this.msg2 = "Take Belladona &";
                this.msg3 = "add Bones &";
                this.msg4 = "add Rowan";
                break;
            }
            case 76: {
                this.msg1 = "'Ticum Deum'";
                this.msg2 = "Take Bones &";
                this.msg3 = "add Rowan &";
                this.msg4 = "add Poppy";
                break;
            }
            case 77: {
                this.msg1 = "'Popus Lupis'";
                this.msg2 = "Poppy & Mandrake &";
                this.msg3 = "add Slippery Elm &";
                this.msg4 = "add Henbane";
                break;
            }
            case 78: {
                this.msg1 = "'Natum Tan'";
                this.msg2 = "Take Mandrake &";
                this.msg3 = "add Henbane &";
                this.msg4 = "add Belladona";
                break;
            }
            case 79: {
                this.msg1 = "'Frenis Vim'";
                this.msg2 = "Take Fly Agaric &";
                this.msg3 = "add Belladona &";
                this.msg4 = "add Bones";
                break;
            }
            case 80: {
                this.msg1 = "'Voidum'";
                this.msg2 = "Take Rowan &";
                this.msg3 = "add Belladona &";
                this.msg4 = "Datura & Poppy";
                break;
            }
            case 81: {
                this.msg1 = "'Asi Tacil'";
                this.msg2 = "Take Henbane &";
                this.msg3 = "add Mandrake &";
                this.msg4 = "add Psylocybin";
                break;
            }
            case 82: {
                this.msg1 = "'Fugor Arbum'";
                this.msg2 = "Take Psylocybin &";
                this.msg3 = "add Datura &";
                this.msg4 = "add Mandrake";
                break;
            }
            case 83: {
                this.msg1 = "'Ad Argum'";
                this.msg2 = "Take Psylocybin &";
                this.msg3 = "add Fly Agaric &";
                this.msg4 = "add Bones";
                break;
            }
            case 84: {
                this.msg1 = "'Transgildor'";
                this.msg2 = "Take Datura &";
                this.msg3 = "add Slippery Elm &";
                this.msg4 = "add Belladona";
                break;
            }
            case 100: {
                this.msg1 = "Mostly, monsters hurt you and";
                this.msg2 = "take health away.  If your ";
                this.msg3 = "health reaches 0, you will ";
                this.msg4 = "die.  Check your health below.";
                break;
            }
            case 101: {
                this.msg1 = "Collect gold by moving into";
                this.msg2 = "it.  See how much gold you";
                this.msg3 = "have below.  Gold is useful";
                this.msg4 = "for buying things.";
                break;
            }
            case 102: {
                this.msg1 = "Eat food by moving into it.";
                this.msg2 = "See how much food you have";
                this.msg3 = "in the panel below.";
                this.msg4 = "";
                break;
            }
            case 103: {
                this.msg1 = "Collect items by moving into";
                this.msg2 = "them - they will appear in the";
                this.msg3 = "panel below.  You can use the";
                this.msg4 = "items later.";
                break;
            }
            case 104: {
                this.msg1 = "The cauldron.  Someone else ";
                this.msg2 = "can tell you about the ";
                this.msg3 = "cauldron.  Try talking to the ";
                this.msg4 = "wandering man.";
                break;
            }
            case 105: {
                this.msg1 = "Open chests by moving into ";
                this.msg2 = "them.  Inside each chest is an ";
                this.msg3 = "unknown item - they can be good ";
                this.msg4 = "and bad.";
                break;
            }
            case 106: {
                this.msg1 = "This gives you extra health.";
                this.msg2 = "Check your health in the panel";
                this.msg3 = "below.  If your health reaches";
                this.msg4 = "0, you will die.";
                break;
            }
            case 107: {
                this.msg1 = "Hey!  You don't have enough";
                this.msg2 = "gold - come back and see me ";
                this.msg3 = "when you have some more!";
                this.msg4 = "";
                break;
            }
            case 108: {
                this.msg1 = "You can open a door with a key.";
                this.msg2 = "Select the key using TAB, and";
                this.msg3 = "then walk into the door.";
                this.msg4 = "";
                break;
            }
            case 109: {
                this.msg1 = "Scrolls contain 'recipies' for";
                this.msg2 = "spells.  Select a scroll and ";
                this.msg3 = "press SPACE to read it.";
                this.msg4 = "Note the order of the spell!";
                break;
            }
            case 110: {
                this.msg1 = "Magic spells can be 'cast' by";
                this.msg2 = "selecting them with TAB, then";
                this.msg3 = "facing left or right and ";
                this.msg4 = "pressing SPACE.";
                break;
            }
            case 111: {
                this.msg1 = "Some walls are not what they";
                this.msg2 = "seem to be, and will vanish";
                this.msg3 = "when touched. ";
                this.msg4 = "";
                break;
            }
            default: {
                this.msg1 = "Want to buy some information?";
                this.msg2 = "It will cost you only " + (n - 200);
                this.msg3 = "gold coins.";
                this.msg4 = "";
                this.msg5 = "[Press 'Y' or 'N']";
                break;
            }
        }
        this.showMsg(n2, n3);
    }
    
    private void showMsg(final int n, final int n2) {
        final int n3 = 30;
        final int n4 = 175;
        final int n5 = 100;
        int n6;
        int n8;
        int n7;
        if (n2 > 110) {
            n6 = 30;
            n7 = (n8 = 70);
        }
        else {
            n6 = 130;
            n7 = 70;
            n8 = n6;
        }
        this.paper.setColor(Color.gray);
        this.paper.fillRoundRect(n3, n6, n4, n7, 12, 12);
        if (n > 0) {
            final int[] array = new int[3];
            final int[] array2 = new int[3];
            array[0] = n5 - 5;
            array2[0] = n8;
            array[1] = n5 + 5;
            array2[1] = n8;
            array[2] = n + 10;
            array2[2] = n2 + 10;
            this.paper.fillPolygon(array, array2, 3);
        }
        this.paper.setColor(Color.white);
        this.paper.drawString(this.msg1, 45, n6 + 15);
        this.paper.drawString(this.msg2, 45, n6 + 25);
        this.paper.drawString(this.msg3, 45, n6 + 35);
        this.paper.drawString(this.msg4, 45, n6 + 45);
        this.paper.setColor(Color.black);
        this.paper.drawString(this.msg5, 45, n6 + 60);
    }
}
