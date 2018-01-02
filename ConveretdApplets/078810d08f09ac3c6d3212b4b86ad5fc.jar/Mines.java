import java.awt.event.ActionEvent;
import java.awt.Component;
import java.awt.Color;
import java.awt.LayoutManager;
import java.awt.TextField;
import java.awt.TextArea;
import java.awt.Label;
import java.awt.Button;
import java.awt.event.ActionListener;
import java.applet.Applet;

// 
// Decompiled by Procyon v0.5.30
// 

public class Mines extends Applet implements ActionListener
{
    private Button m_buttonAbout;
    private Button m_buttonAboutOk;
    private Button m_buttonCarry;
    private Button m_buttonDown;
    private Button m_buttonDrop;
    private Button m_buttonEast;
    private Button m_buttonMineNumberOk;
    private Button m_buttonNorth;
    private Button m_buttonSouth;
    private Button m_buttonUp;
    private Button m_buttonWayOut;
    private Button m_buttonWest;
    private char[] m_cDirection;
    private Label m_labelInstructions;
    private Label m_labelInventory;
    private Label m_labelLocation;
    private Label m_labelMineNumber;
    private Label m_labelStatus;
    private int m_nAddIndex;
    private int m_nChokepoints;
    private int[] m_nDirectionOpposite;
    private int m_nMoves;
    private int m_nReplaceIndex;
    private int[] m_nRN;
    private int m_nRNPartialSum;
    private int m_nRooms;
    private int m_nTreasures;
    private PathStack m_pathStackHead;
    private Room m_room;
    private Room m_roomEntrance;
    private Room[] m_roomWithName;
    private StringBuffer m_sbWayOut;
    private String m_strAbout;
    private String[] m_strDirection;
    private String[] m_strGuardian;
    private String[] m_strRoom;
    private String[] m_strTreasure;
    private String[] m_strWeapon;
    private TextArea m_textAreaAbout;
    private TextArea m_textAreaInventory;
    private TextArea m_textAreaLocation;
    private TextArea m_textAreaStatus;
    private TextField m_textFieldMineNumber;
    private Treasure[] m_treasure;
    
    private int RandomNumber() {
        int i;
        do {
            i = this.m_nRNPartialSum + this.m_nRN[this.m_nAddIndex];
            if (i >= 32771) {
                i -= 32771;
            }
            this.m_nRNPartialSum = i - this.m_nRN[this.m_nReplaceIndex];
            if (this.m_nRNPartialSum < 0) {
                this.m_nRNPartialSum += 32771;
            }
            this.m_nRN[this.m_nReplaceIndex] = i;
            this.m_nAddIndex = this.m_nReplaceIndex;
            if (++this.m_nReplaceIndex >= 8) {
                this.m_nReplaceIndex = 0;
            }
        } while (i > 32767);
        return i;
    }
    
    private void SeedRandomNumberGenerator(final String s) {
        final int length = s.length();
        int n = 0;
        for (int i = length; i < 8; ++i) {
            this.m_nRN[n++] = 49;
        }
        for (int n2 = 0; n2 < 8 && n2 < length; ++n2) {
            this.m_nRN[n++] = '\u0001' + s.charAt(n2);
        }
        this.m_nRNPartialSum = 0;
        for (int j = 7; j > 0; --j) {
            this.m_nRNPartialSum += this.m_nRN[j];
            if (this.m_nRNPartialSum >= 32771) {
                this.m_nRNPartialSum -= 32771;
            }
        }
        this.m_nReplaceIndex = 1;
        this.m_nAddIndex = 0;
        int n3 = 256;
        while (n3-- > 0) {
            this.RandomNumber();
        }
    }
    
    private void ShuffleRoomDescriptions() {
        for (int i = this.m_nRooms - 1; i > 0; --i) {
            final int n = this.RandomNumber() % i;
            final String s = this.m_strRoom[i];
            this.m_strRoom[i] = this.m_strRoom[n];
            this.m_strRoom[n] = s;
        }
    }
    
    private void ExcavateMine() {
        final int n = (int)(10.0 + Math.exp(Math.log(this.m_nRooms) / 3.0));
        int n2;
        int n3;
        int n4;
        do {
            n2 = this.RandomNumber() % (n - 3) + 3;
            n3 = this.RandomNumber() % (n - 3) + 3;
            n4 = this.RandomNumber() % (n - 3) + 3;
        } while (n2 * n3 * n4 < 2 * (this.m_nRooms - 1) || n2 * n3 * n4 > 3 * (this.m_nRooms - 1));
        final boolean b = false;
        final int n5 = this.RandomNumber() % n2;
        final int n6 = this.RandomNumber() % n4;
        Object o = null;
        Object o2 = null;
        for (int i = 0; i < n3; ++i) {
            Room room = null;
            for (int j = 0; j < n2; ++j) {
                Object room2 = o;
                Object room3 = room;
                Object room4 = null;
                for (int k = 0; k < n4; ++k) {
                    final Room room5 = new Room();
                    if (k == 0) {
                        room = room5;
                        if (j == 0) {
                            o2 = room5;
                        }
                    }
                    if (room4 != null) {
                        final Passage passage = new Passage();
                        passage.room1 = room5;
                        passage.room2 = (Room)room4;
                        room5.passage[5] = passage;
                        ((Room)room4).passage[4] = passage;
                    }
                    if (room3 != null) {
                        final Passage passage2 = new Passage();
                        passage2.room1 = room5;
                        passage2.room2 = (Room)room3;
                        room5.passage[1] = passage2;
                        ((Room)room3).passage[0] = passage2;
                        if (((Room)room3).passage[4] != null) {
                            if (((Room)room3).passage[4].room1 == room3) {
                                room3 = ((Room)room3).passage[4].room2;
                            }
                            else {
                                room3 = ((Room)room3).passage[4].room1;
                            }
                        }
                        else {
                            room3 = null;
                        }
                    }
                    if (room2 != null) {
                        final Passage passage3 = new Passage();
                        passage3.room1 = room5;
                        passage3.room2 = (Room)room2;
                        room5.passage[3] = passage3;
                        ((Room)room2).passage[2] = passage3;
                        if (((Room)room2).passage[4] != null) {
                            if (((Room)room2).passage[4].room1 == room2) {
                                room2 = ((Room)room2).passage[4].room2;
                            }
                            else {
                                room2 = ((Room)room2).passage[4].room1;
                            }
                        }
                        else {
                            room2 = null;
                        }
                    }
                    if (i == (b ? 1 : 0) && j == n5 && k == n6) {
                        final Passage passage4 = new Passage();
                        passage4.room1 = room5;
                        passage4.room2 = this.m_roomEntrance;
                        room5.passage[3] = passage4;
                        this.m_roomEntrance.passage[2] = passage4;
                    }
                    room4 = room5;
                }
                if (o != null && ((Room)o).passage[0] != null) {
                    if (((Room)o).passage[0].room1 == o) {
                        o = ((Room)o).passage[0].room2;
                    }
                    else {
                        o = ((Room)o).passage[0].room1;
                    }
                }
            }
            o = o2;
        }
        int nChokepoint = -1;
        int nChokepoints = 0;
        Room roomEntrance = this.m_roomEntrance;
        int l;
        for (l = 0; l < this.m_nRooms; ++l) {
            final int n7 = this.RandomNumber() % 6;
            final int n8 = this.m_nDirectionOpposite[n7];
            final int n9 = this.RandomNumber() % 3 + 1;
            int n10 = 0;
            while (roomEntrance.passage[n7] != null && n10 < n9) {
                final Passage passage5 = roomEntrance.passage[n7];
                Room room6;
                if (passage5.room1 == roomEntrance) {
                    room6 = passage5.room2;
                }
                else {
                    room6 = passage5.room1;
                }
                if (passage5.bBlocked) {
                    if (room6.nChokepoint == nChokepoint || !room6.bMined) {
                        roomEntrance = room6;
                        roomEntrance.bMined = true;
                        roomEntrance.nChokepoint = nChokepoint;
                        passage5.bBlocked = false;
                        if (roomEntrance.strDescription != null) {
                            ++n10;
                        }
                        else {
                            int n11 = 0;
                            for (int n12 = 6; n11 < 2 && n12-- > 0; ++n11) {
                                if (n12 != n8 && roomEntrance.passage[n12] != null && !roomEntrance.passage[n12].bBlocked) {}
                            }
                            if (n11 < 2) {
                                ++n10;
                            }
                            else {
                                n10 = n9;
                            }
                        }
                    }
                    else {
                        n10 = n9;
                    }
                }
                else {
                    ++n10;
                    roomEntrance = room6;
                    nChokepoint = roomEntrance.nChokepoint;
                }
            }
            if (n10 != 0 && roomEntrance.strDescription == null) {
                roomEntrance.strDescription = this.m_strRoom[l];
                int n13 = 0;
                int n14 = 0;
                for (int n15 = 6; n14 < 2 && n15-- > 0; ++n14) {
                    if (roomEntrance.passage[n15] != null && !roomEntrance.passage[n15].bBlocked) {
                        n13 = n15;
                    }
                }
                if (n14 == 1 && (this.m_nRooms - l) * this.RandomNumber() < 32768 * (this.m_nTreasures - nChokepoints)) {
                    nChokepoint = nChokepoints;
                    roomEntrance.passage[n13].treasureGuardian = this.m_treasure[nChokepoints];
                    ++nChokepoints;
                }
                roomEntrance.nChokepoint = nChokepoint;
                this.m_roomWithName[l] = roomEntrance;
            }
        }
        this.m_roomWithName[l] = this.m_roomEntrance;
        this.m_nChokepoints = nChokepoints;
    }
    
    private void HideTreasuresAndWeapons() {
        for (int i = 0; i < this.m_nChokepoints; ++i) {
            int n;
            do {
                n = this.RandomNumber() % this.m_nRooms;
            } while (this.m_roomWithName[n].nChokepoint != i);
            this.m_treasure[i].roomTreasure = this.m_roomWithName[n];
            int n2;
            do {
                n2 = this.RandomNumber() % (this.m_nRooms + 1);
            } while (this.m_roomWithName[n2].nChokepoint >= i);
            this.m_treasure[i].roomWeapon = this.m_roomWithName[n2];
        }
        for (int j = this.m_nChokepoints; j < this.m_nTreasures; ++j) {
            this.m_treasure[j].roomTreasure = this.m_roomWithName[this.RandomNumber() % this.m_nRooms];
        }
    }
    
    private void ListPassages() {
        this.m_buttonNorth.setEnabled(false);
        this.m_buttonSouth.setEnabled(false);
        this.m_buttonEast.setEnabled(false);
        this.m_buttonWest.setEnabled(false);
        this.m_buttonUp.setEnabled(false);
        this.m_buttonDown.setEnabled(false);
        for (int i = 0; i < 6; ++i) {
            if (this.m_room.passage[i] != null && !this.m_room.passage[i].bBlocked) {
                switch (i) {
                    case 0: {
                        this.m_buttonNorth.setEnabled(true);
                        break;
                    }
                    case 1: {
                        this.m_buttonSouth.setEnabled(true);
                        break;
                    }
                    case 2: {
                        this.m_buttonEast.setEnabled(true);
                        break;
                    }
                    case 3: {
                        this.m_buttonWest.setEnabled(true);
                        break;
                    }
                    case 4: {
                        this.m_buttonUp.setEnabled(true);
                        break;
                    }
                    default: {
                        this.m_buttonDown.setEnabled(true);
                        break;
                    }
                }
            }
        }
        for (int j = 0; j < 6; ++j) {
            String strDescription = null;
            for (Object o = this.m_room; ((Room)o).passage[j] != null && !((Room)o).passage[j].bBlocked && strDescription == null; strDescription = ((Room)o).strDescription) {
                final Treasure treasureGuardian;
                if ((treasureGuardian = ((Room)o).passage[j].treasureGuardian) != null) {
                    this.m_textAreaLocation.append("     The passage ");
                    this.m_textAreaLocation.append(this.m_strDirection[j]);
                    this.m_textAreaLocation.append(" is guarded by a");
                    final char char1 = treasureGuardian.strGuardian.charAt(0);
                    if (char1 == 'A' || char1 == 'a' || char1 == 'E' || char1 == 'e' || char1 == 'I' || char1 == 'i' || char1 == 'O' || char1 == 'o' || char1 == 'U' || char1 == 'u') {
                        this.m_textAreaLocation.append("n");
                    }
                    this.m_textAreaLocation.append(" ");
                    this.m_textAreaLocation.append(treasureGuardian.strGuardian);
                    this.m_textAreaLocation.append(".\n");
                }
                if (((Room)o).passage[j].room1 == o) {
                    o = ((Room)o).passage[j].room2;
                }
                else {
                    o = ((Room)o).passage[j].room1;
                }
            }
        }
    }
    
    private void ListTreasures() {
        int nTreasures = this.m_nTreasures;
        while (nTreasures-- > 0) {
            if (this.m_treasure[nTreasures].roomTreasure == this.m_room) {
                this.m_buttonCarry.setEnabled(true);
                this.m_textAreaLocation.append("     There is a");
                final char char1 = this.m_treasure[nTreasures].strTreasure.charAt(0);
                if (char1 == 'A' || char1 == 'a' || char1 == 'E' || char1 == 'e' || char1 == 'I' || char1 == 'i' || char1 == 'O' || char1 == 'o' || char1 == 'U' || char1 == 'u') {
                    this.m_textAreaLocation.append("n");
                }
                this.m_textAreaLocation.append(" ");
                this.m_textAreaLocation.append(this.m_treasure[nTreasures].strTreasure);
                this.m_textAreaLocation.append(" here.\n");
            }
        }
    }
    
    private void ListWeapons() {
        int nTreasures = this.m_nTreasures;
        while (nTreasures-- > 0) {
            if (this.m_treasure[nTreasures].roomWeapon == this.m_room) {
                this.m_buttonCarry.setEnabled(true);
                this.m_textAreaLocation.append("     There is a");
                final char char1 = this.m_treasure[nTreasures].strTreasure.charAt(0);
                if (char1 == 'A' || char1 == 'a' || char1 == 'E' || char1 == 'e' || char1 == 'I' || char1 == 'i' || char1 == 'O' || char1 == 'o' || char1 == 'U' || char1 == 'u') {
                    this.m_textAreaLocation.append("n");
                }
                this.m_textAreaLocation.append(" ");
                this.m_textAreaLocation.append(this.m_treasure[nTreasures].strWeapon);
                this.m_textAreaLocation.append(" here.\n");
            }
        }
    }
    
    private void UpdateInventory() {
        int n = 1;
        this.m_textAreaInventory.setText("");
        int nTreasures = this.m_nTreasures;
        while (nTreasures-- > 0) {
            if (this.m_treasure[nTreasures].roomTreasure == null) {
                if (n != 0) {
                    n = 0;
                    this.m_buttonDrop.setEnabled(true);
                }
                this.m_textAreaInventory.append("     ");
                this.m_textAreaInventory.append(this.m_treasure[nTreasures].strTreasure);
                this.m_textAreaInventory.append("\n");
            }
        }
        int nChokepoints = this.m_nChokepoints;
        while (nChokepoints-- > 0) {
            if (this.m_treasure[nChokepoints].roomWeapon == null && !this.m_treasure[nChokepoints].bSlain) {
                this.m_textAreaInventory.append("     ");
                this.m_textAreaInventory.append(this.m_treasure[nChokepoints].strWeapon);
                this.m_textAreaInventory.append("\n");
            }
        }
    }
    
    private void UpdateStatus() {
        final Integer n = new Integer(this.m_nRooms + 1);
        int n2 = 0;
        int n3 = 0;
        int n4 = 0;
        for (int i = 0; i <= this.m_nRooms; ++i) {
            if (this.m_roomWithName[i].bVisited) {
                ++n4;
            }
        }
        final Integer n5 = new Integer(n4);
        final Integer n6 = new Integer(this.m_nMoves);
        this.m_textAreaStatus.setText("     You have moved ");
        this.m_textAreaStatus.append(n6.toString());
        this.m_textAreaStatus.append(" times to visit ");
        this.m_textAreaStatus.append(n5.toString());
        this.m_textAreaStatus.append(" of ");
        this.m_textAreaStatus.append(n.toString());
        this.m_textAreaStatus.append(" locations.\n");
        final Integer n7 = new Integer(this.m_nTreasures);
        int nTreasures = this.m_nTreasures;
        while (nTreasures-- > 0) {
            if (this.m_treasure[nTreasures].roomTreasure == null) {
                ++n3;
            }
        }
        final Integer n8 = new Integer(n3);
        this.m_textAreaStatus.append("     You hold ");
        this.m_textAreaStatus.append(n8.toString());
        this.m_textAreaStatus.append(" of ");
        this.m_textAreaStatus.append(n7.toString());
        this.m_textAreaStatus.append(" treasures.\n");
        int nTreasures2 = this.m_nTreasures;
        while (nTreasures2-- > 0) {
            if (this.m_treasure[nTreasures2].roomTreasure == this.m_roomEntrance) {
                ++n2;
            }
        }
        final Integer n9 = new Integer(n2);
        this.m_textAreaStatus.append("     You have returned ");
        this.m_textAreaStatus.append(n9.toString());
        this.m_textAreaStatus.append(" of ");
        this.m_textAreaStatus.append(n7.toString());
        this.m_textAreaStatus.append(" treasures to the entrance of the mine.\n");
        int n10 = 25 * n4 / (this.m_nRooms + 1) + 75 * n2 / this.m_nTreasures + 45 * n3 / this.m_nTreasures;
        if (n4 > 5 * this.m_nRooms) {
            n10 -= n4 / (5 * this.m_nRooms);
            if (n10 < 0) {
                n10 = 0;
            }
        }
        final Integer n11 = new Integer(n10);
        this.m_textAreaStatus.append("     You have scored ");
        this.m_textAreaStatus.append(n11.toString());
        this.m_textAreaStatus.append(" of 100 points.\n");
        if (n10 < 25) {
            this.m_textAreaStatus.append("     Your score ranks you as a beginner.\n");
            return;
        }
        if (n10 < 50) {
            this.m_textAreaStatus.append("     Your score ranks you as a novice adventurer.\n");
            return;
        }
        if (n10 < 75) {
            this.m_textAreaStatus.append("     Your score ranks you as a seasoned explorer.\n");
            return;
        }
        if (n10 < 100) {
            this.m_textAreaStatus.append("     Your score ranks you as a grissly old prospector.\n");
            return;
        }
        this.m_textAreaStatus.append("     Your score ranks you as an expert treasure hunter; there is no higher rating.\n");
    }
    
    private void UpdateScreen(final boolean b) {
        if (b) {
            this.m_textAreaLocation.append("     You're in ");
        }
        else {
            this.m_textAreaLocation.setText("     You're in ");
        }
        this.m_textAreaLocation.append(this.m_room.strDescription);
        this.m_textAreaLocation.append("\n");
        this.ListPassages();
        this.m_buttonCarry.setEnabled(false);
        this.ListTreasures();
        this.ListWeapons();
        this.m_buttonDrop.setEnabled(false);
        this.UpdateInventory();
        this.UpdateStatus();
        this.m_textAreaStatus.setCaretPosition(0);
        this.m_textAreaInventory.setCaretPosition(0);
        this.m_textAreaLocation.setCaretPosition(0);
    }
    
    private void Move(final int n) {
        boolean b = false;
        if (this.m_room.passage[n] != null && !this.m_room.passage[n].bBlocked) {
            boolean b2 = false;
            String strDescription;
            Object room;
            for (strDescription = null, room = this.m_room; ((Room)room).passage[n] != null && strDescription == null; strDescription = ((Room)room).strDescription) {
                if (((Room)room).passage[n].treasureGuardian != null) {
                    if (((Room)room).passage[n].treasureGuardian.roomWeapon != null) {
                        b2 = true;
                        this.m_textAreaLocation.setText("     You carry nothing to overcome the ");
                        b = true;
                        this.m_textAreaLocation.append(((Room)room).passage[n].treasureGuardian.strGuardian);
                        this.m_textAreaLocation.append(".\n");
                    }
                    else {
                        this.m_textAreaLocation.setText("     Your ");
                        b = true;
                        this.m_textAreaLocation.append(((Room)room).passage[n].treasureGuardian.strWeapon);
                        this.m_textAreaLocation.append(" overcomes the ");
                        this.m_textAreaLocation.append(((Room)room).passage[n].treasureGuardian.strGuardian);
                        this.m_textAreaLocation.append(".\n");
                        ((Room)room).passage[n].treasureGuardian.bSlain = true;
                        ((Room)room).passage[n].treasureGuardian = null;
                    }
                }
                if (((Room)room).passage[n].room1 == room) {
                    room = ((Room)room).passage[n].room2;
                }
                else {
                    room = ((Room)room).passage[n].room1;
                }
            }
            if (!b2) {
                ++this.m_nMoves;
                ((Room)room).bVisited = true;
                this.m_room = (Room)room;
            }
        }
        else {
            this.m_textAreaLocation.setText("     You can't go that way.\n");
            b = true;
        }
        this.UpdateScreen(b);
    }
    
    private void FindPathToEntrance(final Room room, final int nDirectionUsedToEnterRoom) {
        final int[] array = new int[6];
        if (room == this.m_roomEntrance) {
            int n = 0;
            for (PathStack pathStack = this.m_pathStackHead; pathStack != null; pathStack = pathStack.pathStackNext) {
                if (pathStack.room.strDescription != null) {
                    ++n;
                }
            }
            this.m_sbWayOut = new StringBuffer(n);
            for (int i = 0; i < n; ++i) {
                this.m_sbWayOut.append(' ');
            }
            this.m_sbWayOut.setCharAt(--n, this.m_cDirection[nDirectionUsedToEnterRoom]);
            for (PathStack pathStack2 = this.m_pathStackHead; pathStack2 != null; pathStack2 = pathStack2.pathStackNext) {
                if (pathStack2.pathStackNext != null && pathStack2.room.strDescription != null) {
                    this.m_sbWayOut.setCharAt(--n, this.m_cDirection[pathStack2.nDirectionUsedToEnterRoom]);
                }
            }
            return;
        }
        boolean b = false;
        for (PathStack pathStack3 = this.m_pathStackHead; pathStack3 != null && !b; b = (pathStack3.room == room), pathStack3 = pathStack3.pathStackNext) {}
        if (!b) {
            final PathStack pathStackHead = new PathStack();
            pathStackHead.room = room;
            pathStackHead.nDirectionUsedToEnterRoom = nDirectionUsedToEnterRoom;
            pathStackHead.pathStackNext = this.m_pathStackHead;
            this.m_pathStackHead = pathStackHead;
            int n2 = 6;
            while (n2-- > 0) {
                array[n2] = n2;
            }
            for (int j = 5; j > 0; --j) {
                final int n3 = this.RandomNumber() % j;
                final int n4 = array[j];
                array[j] = array[n3];
                array[n3] = n4;
            }
            int n5 = 6;
            while (this.m_sbWayOut == null && n5-- > 0) {
                final int n6 = array[n5];
                if (room.passage[n6] != null && !room.passage[n6].bBlocked) {
                    if (room.passage[n6].room1 == room) {
                        this.FindPathToEntrance(room.passage[n6].room2, n6);
                    }
                    else {
                        this.FindPathToEntrance(room.passage[n6].room1, n6);
                    }
                }
            }
            this.m_pathStackHead = pathStackHead.pathStackNext;
        }
    }
    
    public void init() {
        this.m_textAreaAbout = new TextArea("", 0, 0, 1);
        this.m_buttonAboutOk = new Button();
        this.m_labelInstructions = new Label();
        this.m_labelStatus = new Label();
        this.m_textAreaStatus = new TextArea("", 0, 0, 1);
        this.m_labelInventory = new Label();
        this.m_textAreaInventory = new TextArea("", 0, 0, 1);
        this.m_labelLocation = new Label();
        this.m_textAreaLocation = new TextArea("", 0, 0, 1);
        this.m_buttonWest = new Button();
        this.m_buttonEast = new Button();
        this.m_buttonNorth = new Button();
        this.m_buttonSouth = new Button();
        this.m_buttonDown = new Button();
        this.m_buttonUp = new Button();
        this.m_buttonCarry = new Button();
        this.m_buttonDrop = new Button();
        this.m_buttonWayOut = new Button();
        this.m_buttonAbout = new Button();
        this.m_labelMineNumber = new Label();
        this.m_textFieldMineNumber = new TextField();
        this.m_buttonMineNumberOk = new Button();
        this.setLayout(null);
        this.m_textAreaAbout.setBackground(new Color(255, 255, 255));
        this.m_textAreaAbout.setEditable(false);
        this.m_textAreaAbout.setVisible(false);
        this.add(this.m_textAreaAbout);
        this.m_textAreaAbout.setBounds(10, 10, 610, 250);
        this.m_buttonAboutOk.setLabel("Ok");
        this.m_buttonAboutOk.setVisible(false);
        this.m_buttonAboutOk.addActionListener(this);
        this.add(this.m_buttonAboutOk);
        this.m_buttonAboutOk.setBounds(290, 270, 31, 24);
        this.m_labelInstructions.setText("Visit all of the rooms and move all of the treasures to the entrance.");
        this.add(this.m_labelInstructions);
        this.m_labelInstructions.setBounds(10, 90, 592, 20);
        this.m_labelStatus.setText("Status");
        this.m_labelStatus.setVisible(false);
        this.add(this.m_labelStatus);
        this.m_labelStatus.setBounds(10, 10, 78, 20);
        this.m_textAreaStatus.setBackground(new Color(255, 255, 255));
        this.m_textAreaStatus.setEditable(false);
        this.m_textAreaStatus.setVisible(false);
        this.add(this.m_textAreaStatus);
        this.m_textAreaStatus.setBounds(10, 34, 280, 116);
        this.m_labelInventory.setText("Inventory");
        this.m_labelInventory.setVisible(false);
        this.add(this.m_labelInventory);
        this.m_labelInventory.setBounds(12, 156, 88, 20);
        this.m_textAreaInventory.setBackground(new Color(255, 255, 255));
        this.m_textAreaInventory.setEditable(false);
        this.m_textAreaInventory.setVisible(false);
        this.add(this.m_textAreaInventory);
        this.m_textAreaInventory.setBounds(10, 180, 280, 120);
        this.m_labelLocation.setText("Location");
        this.m_labelLocation.setVisible(false);
        this.add(this.m_labelLocation);
        this.m_labelLocation.setBounds(310, 10, 88, 20);
        this.m_textAreaLocation.setBackground(new Color(255, 255, 255));
        this.m_textAreaLocation.setEditable(false);
        this.m_textAreaLocation.setVisible(false);
        this.add(this.m_textAreaLocation);
        this.m_textAreaLocation.setBounds(310, 34, 310, 116);
        this.m_buttonWest.setLabel("West");
        this.m_buttonWest.setVisible(false);
        this.m_buttonWest.addActionListener(this);
        this.add(this.m_buttonWest);
        this.m_buttonWest.setBounds(360, 220, 50, 24);
        this.m_buttonEast.setLabel("East");
        this.m_buttonEast.setVisible(false);
        this.m_buttonEast.addActionListener(this);
        this.add(this.m_buttonEast);
        this.m_buttonEast.setBounds(460, 220, 50, 24);
        this.m_buttonNorth.setLabel("North");
        this.m_buttonNorth.setVisible(false);
        this.m_buttonNorth.addActionListener(this);
        this.add(this.m_buttonNorth);
        this.m_buttonNorth.setBounds(410, 190, 50, 24);
        this.m_buttonSouth.setLabel("South");
        this.m_buttonSouth.setVisible(false);
        this.m_buttonSouth.addActionListener(this);
        this.add(this.m_buttonSouth);
        this.m_buttonSouth.setBounds(410, 250, 50, 24);
        this.m_buttonDown.setLabel("Down");
        this.m_buttonDown.setVisible(false);
        this.m_buttonDown.addActionListener(this);
        this.add(this.m_buttonDown);
        this.m_buttonDown.setBounds(340, 260, 50, 24);
        this.m_buttonUp.setLabel("Up");
        this.m_buttonUp.setVisible(false);
        this.m_buttonUp.addActionListener(this);
        this.add(this.m_buttonUp);
        this.m_buttonUp.setBounds(480, 180, 50, 24);
        this.m_buttonCarry.setForeground(new Color(0, 0, 0));
        this.m_buttonCarry.setLabel("Carry");
        this.m_buttonCarry.setVisible(false);
        this.m_buttonCarry.addActionListener(this);
        this.add(this.m_buttonCarry);
        this.m_buttonCarry.setBounds(560, 170, 60, 24);
        this.m_buttonDrop.setLabel("Drop");
        this.m_buttonDrop.setVisible(false);
        this.m_buttonDrop.addActionListener(this);
        this.add(this.m_buttonDrop);
        this.m_buttonDrop.setBounds(560, 200, 60, 24);
        this.m_buttonWayOut.setLabel("Way Out");
        this.m_buttonWayOut.setVisible(false);
        this.m_buttonWayOut.addActionListener(this);
        this.add(this.m_buttonWayOut);
        this.m_buttonWayOut.setBounds(560, 230, 61, 24);
        this.m_buttonAbout.setLabel("About");
        this.m_buttonAbout.setVisible(false);
        this.m_buttonAbout.addActionListener(this);
        this.add(this.m_buttonAbout);
        this.m_buttonAbout.setBounds(560, 260, 60, 24);
        this.m_labelMineNumber.setAlignment(2);
        this.m_labelMineNumber.setText("Mine Number:");
        this.add(this.m_labelMineNumber);
        this.m_labelMineNumber.setBounds(120, 10, 90, 20);
        this.m_textFieldMineNumber.setText("1");
        this.add(this.m_textFieldMineNumber);
        this.m_textFieldMineNumber.setBounds(220, 8, 70, 22);
        this.m_buttonMineNumberOk.setLabel("Ok");
        this.m_buttonMineNumberOk.addActionListener(this);
        this.add(this.m_buttonMineNumberOk);
        this.m_buttonMineNumberOk.setBounds(290, 160, 31, 24);
        super.init();
    }
    
    private void buttonAboutOkClicked() {
        this.m_textAreaAbout.setVisible(false);
        this.m_buttonAboutOk.setVisible(false);
        this.m_labelMineNumber.setVisible(true);
        this.m_textFieldMineNumber.setVisible(true);
        this.m_labelStatus.setVisible(true);
        this.m_textAreaStatus.setVisible(true);
        this.m_labelInventory.setVisible(true);
        this.m_textAreaInventory.setVisible(true);
        this.m_labelLocation.setVisible(true);
        this.m_textAreaLocation.setVisible(true);
        this.m_buttonWest.setVisible(true);
        this.m_buttonEast.setVisible(true);
        this.m_buttonNorth.setVisible(true);
        this.m_buttonSouth.setVisible(true);
        this.m_buttonUp.setVisible(true);
        this.m_buttonDown.setVisible(true);
        this.m_buttonCarry.setVisible(true);
        this.m_buttonDrop.setVisible(true);
        this.m_buttonWayOut.setVisible(true);
        this.m_buttonAbout.setVisible(true);
    }
    
    private void buttonAboutClicked() {
        this.m_labelMineNumber.setVisible(false);
        this.m_textFieldMineNumber.setVisible(false);
        this.m_labelStatus.setVisible(false);
        this.m_textAreaStatus.setVisible(false);
        this.m_labelInventory.setVisible(false);
        this.m_textAreaInventory.setVisible(false);
        this.m_labelLocation.setVisible(false);
        this.m_textAreaLocation.setVisible(false);
        this.m_buttonWest.setVisible(false);
        this.m_buttonEast.setVisible(false);
        this.m_buttonNorth.setVisible(false);
        this.m_buttonSouth.setVisible(false);
        this.m_buttonUp.setVisible(false);
        this.m_buttonDown.setVisible(false);
        this.m_buttonCarry.setVisible(false);
        this.m_buttonDrop.setVisible(false);
        this.m_buttonWayOut.setVisible(false);
        this.m_buttonAbout.setVisible(false);
        this.m_labelInstructions.setVisible(false);
        this.m_textAreaAbout.setText(this.m_strAbout);
        this.m_textAreaAbout.setCaretPosition(0);
        this.m_textAreaAbout.setVisible(true);
        this.m_buttonAboutOk.setVisible(true);
    }
    
    private void buttonMineNumberOkClicked() {
        this.m_labelInstructions.setVisible(false);
        this.m_buttonMineNumberOk.setVisible(false);
        this.m_textFieldMineNumber.setEnabled(false);
        this.m_roomEntrance = new Room();
        this.m_roomEntrance.strDescription = new String("the entrance to the mine.");
        this.m_roomEntrance.bMined = true;
        this.m_roomEntrance.bVisited = true;
        this.SeedRandomNumberGenerator(this.m_textFieldMineNumber.getText());
        this.m_nRooms = 0;
        while (this.m_strRoom[this.m_nRooms] != null) {
            ++this.m_nRooms;
        }
        this.ShuffleRoomDescriptions();
        this.m_nTreasures = 0;
        while (this.m_strTreasure[this.m_nTreasures] != null) {
            ++this.m_nTreasures;
        }
        this.m_treasure = new Treasure[this.m_nTreasures];
        int nTreasures = this.m_nTreasures;
        while (nTreasures-- > 0) {
            this.m_treasure[nTreasures] = new Treasure(this.m_strTreasure[nTreasures], this.m_strGuardian[nTreasures], this.m_strWeapon[nTreasures]);
        }
        this.m_nChokepoints = 0;
        this.m_roomWithName = new Room[this.m_nRooms + 1];
        this.ExcavateMine();
        this.HideTreasuresAndWeapons();
        this.m_nMoves = 0;
        this.m_room = this.m_roomEntrance;
        this.UpdateScreen(false);
        this.m_labelStatus.setVisible(true);
        this.m_textAreaStatus.setVisible(true);
        this.m_labelInventory.setVisible(true);
        this.m_textAreaInventory.setVisible(true);
        this.m_labelLocation.setVisible(true);
        this.m_textAreaLocation.setVisible(true);
        this.m_buttonWest.setVisible(true);
        this.m_buttonEast.setVisible(true);
        this.m_buttonNorth.setVisible(true);
        this.m_buttonSouth.setVisible(true);
        this.m_buttonUp.setVisible(true);
        this.m_buttonDown.setVisible(true);
        this.m_buttonCarry.setVisible(true);
        this.m_buttonDrop.setVisible(true);
        this.m_buttonWayOut.setVisible(true);
        this.m_buttonAbout.setVisible(true);
    }
    
    private void buttonWayOutClicked() {
        if (this.m_room == this.m_roomEntrance) {
            this.m_textAreaLocation.setText("     You're already at the entrance.\n");
        }
        else {
            boolean b;
            int nTreasures;
            for (b = false, nTreasures = this.m_nTreasures; !b && nTreasures-- > 0; b = (this.m_treasure[nTreasures].roomTreasure == null)) {}
            if (b) {
                this.m_pathStackHead = null;
                this.m_sbWayOut = null;
                this.FindPathToEntrance(this.m_room, 0);
                int n;
                do {
                    n = this.RandomNumber() % this.m_nRooms;
                } while (this.m_roomWithName[n] == this.m_room);
                this.m_treasure[nTreasures].roomTreasure = this.m_roomWithName[n];
                this.m_textAreaLocation.setText("     The pirate takes one of your treasures.  ");
                if (this.m_sbWayOut.length() == 1) {
                    this.m_textAreaLocation.append("  As he leaves, he shouts the letter \"");
                }
                else {
                    this.m_textAreaLocation.append("  As he leaves, he shouts the letters \"");
                }
                this.m_textAreaLocation.append(this.m_sbWayOut.toString());
                this.m_textAreaLocation.append("\".\n");
            }
            else {
                this.m_textAreaLocation.setText("     Nothing happens.\n");
            }
        }
        this.UpdateScreen(true);
    }
    
    private void buttonDropClicked() {
        int nTreasures = this.m_nTreasures;
        while (nTreasures-- > 0) {
            if (this.m_treasure[nTreasures].roomTreasure == null) {
                this.m_treasure[nTreasures].roomTreasure = this.m_room;
            }
        }
        this.m_buttonDrop.setEnabled(false);
        this.UpdateScreen(false);
    }
    
    private void buttonCarryClicked() {
        int nTreasures = this.m_nTreasures;
        while (nTreasures-- > 0) {
            if (this.m_treasure[nTreasures].roomWeapon == this.m_room) {
                this.m_treasure[nTreasures].roomWeapon = null;
            }
            if (this.m_treasure[nTreasures].roomTreasure == this.m_room) {
                this.m_treasure[nTreasures].roomTreasure = null;
            }
        }
        this.m_buttonCarry.setEnabled(false);
        this.UpdateScreen(false);
    }
    
    private void buttonUpClicked() {
        this.Move(4);
    }
    
    private void buttonEastClicked() {
        this.Move(2);
    }
    
    private void buttonNorthClicked() {
        this.Move(0);
    }
    
    private void buttonWestClicked() {
        this.Move(3);
    }
    
    private void buttonDownClicked() {
        this.Move(5);
    }
    
    private void buttonSouthClicked() {
        this.Move(1);
    }
    
    public void actionPerformed(final ActionEvent actionEvent) {
        if (actionEvent.getSource() instanceof Button) {
            if (actionEvent.getSource() == this.m_buttonAboutOk) {
                this.buttonAboutOkClicked();
            }
            else if (actionEvent.getSource() == this.m_buttonMineNumberOk) {
                this.buttonMineNumberOkClicked();
            }
            else if (actionEvent.getSource() == this.m_buttonAbout) {
                this.buttonAboutClicked();
            }
            else if (actionEvent.getSource() == this.m_buttonWayOut) {
                this.buttonWayOutClicked();
            }
            else if (actionEvent.getSource() == this.m_buttonDrop) {
                this.buttonDropClicked();
            }
            else if (actionEvent.getSource() == this.m_buttonCarry) {
                this.buttonCarryClicked();
            }
            else if (actionEvent.getSource() == this.m_buttonDown) {
                this.buttonDownClicked();
            }
            else if (actionEvent.getSource() == this.m_buttonSouth) {
                this.buttonSouthClicked();
            }
            else if (actionEvent.getSource() == this.m_buttonEast) {
                this.buttonEastClicked();
            }
            else if (actionEvent.getSource() == this.m_buttonWest) {
                this.buttonWestClicked();
            }
            else if (actionEvent.getSource() == this.m_buttonNorth) {
                this.buttonNorthClicked();
            }
            else if (actionEvent.getSource() == this.m_buttonUp) {
                this.buttonUpClicked();
            }
            this.requestFocus();
        }
    }
    
    public Mines() {
        this.m_cDirection = new char[] { 'N', 'S', 'E', 'W', 'U', 'D' };
        this.m_nDirectionOpposite = new int[] { 1, 0, 3, 2, 5, 4 };
        this.m_nRN = new int[8];
        this.m_strAbout = "    Mines\n\n    Copyright (c) 1979, 2005 James L. Dean\n\n    Version 2.3 released August 4, 2005\n\n    This Java applet may be distributed or used without payment to its author.  Derivative works must credit its author.\n\n    \"Mines\" lets you explore mines. The mine you explore is determined by a mine number specified at the beginning of a game.\n\n    The object of a game is to visit all of the rooms and return all of the treasures to the entrance without making too many moves.\n\n    In a mine, the passages are straight.  So, for example, if you go North to leave a room, you can go South to reenter it.  The rooms are not evenly spaced.  However, the distance between adjacent rooms is always a multiple of the minimum distance between adjacent rooms.";
        this.m_strDirection = new String[] { "north", "south", "east", "west", "up", "down" };
        this.m_strGuardian = new String[] { "gorgon", "grizzly bear", "vampire", "crocodile", "giant crayfish", "troll", "werewolf", "harpy", "cobra", "ferocious snail", "vicious Doberman pinscher", "colossal cockroach", "giant", "Trekkie", "titanic ant", null };
        this.m_strRoom = new String[] { "a spherical room.", "the hall of bones.  Dinosaur bones are everywhere.", "a subway tunnel.  Don't touch that third rail!", "a railroad tunnel.  A whistle wails in the distance.", "an elfin quiche factory.  The elves are out mowing spinach.", "an abandoned Plymouth plant.  Beware of Road Runners and Barracudas.", "an abandoned Dodge plant.  There is a Dart embedded in the North wall.", "a mouse's nest.  You'd best exhale; this is a small room.", "a giant crayfish hole.  An immense chicken neck is hanging from a rope.", "an abandoned coal mine.  Beware of methane.", "the hall of winds.  Presently, the wind is from the south.", "a stove pipe!", "a totally darkened room.  Although you can see nothing, the sound of dripping water echoes from the walls.", "an industrial waste site.  Hold your breath and don't touch anything.", "the warehouse for an extremely popular brand of home computer.  Tacky plastic enclosures abound.", "a hobbit's bedroom.  The hobbit does not look pleased!", "a troll sewerage processing plant.  The air quality is not good.", "a rabbit hole.  There is a jar of marmalade on a shelf in the wall.", "the giant's oven.  Fortunately, it hasn't been used for years.", "a hobbit's drying room.  Tobacco leaves abound.", "a large circular room.  It is dark in here.", "the Crystal Palace.  Quartz crystals cover the walls.", "the Emerald Room.  Green crystals cover the ceiling.", "a curtained room.", "an air conditioning duct!", "a giant kiln.  Smoke stains the walls.", "the Hall of Mists.  Wisps of white vapor rise from the floor.", "an Aztec pyramid.  A mummy lies in the northwest corner.", "the Room of the Eternal Flame.  A large natural gas flame burns in the center of the room.  Coming from the west wall you can barely hear the words, 'Fee Fye Foe Fum'.", "the Giant's store room.  You are surrounded by old rugs, books, chairs, etc.", "the Leprechaun's Treasure Room.  Unfortunately, the leprechaun's treasure was stolen years ago.", "a large tiled room.  A girl is inquiring after a rabbit.  Feel free to ignore her.", "a former nuclear test site.  There is a large pile of rubble in the center of the floor.  The walls are streaked with a multitude of colors.", "a drainage system holding tank.  Either it is no longer used, or it hasn't rained in a long time; the room is dry.", "Mayor Schiro's bomb shelter.", "a room with a waterfall.  It is not very impressive; it looks like someone left a water faucet running.", "an abandoned Neanderthal home.", "a volcanic chimney.  The air is getting warmer.", "a pit full of snakes.  Let's get out of here!!!!", "a salt dome.", "Eleanor Roosevelt's privy.  Wendell Wilkie buttons cover the wall.", "Napoleon's wine cellar.  German wines line the shelves.", "the space behind the giant's bathroom wall. Large razor blades litter the floor.  Step lightly.", "the room where all old toe nail clipping come to rest.  Used corn pads litter the floor.", "the Den of the Horta.  The walls are covered with a sticky fluid.  Don't touch it; it is extremely corrosive.", "a damp room.  A small creek runs into a crack in the West wall.", "what appears to be a NOPSI manhole.", "the cafeteria of Mammoth Cave.  The aroma of rancid corned beef assails your nostrils.", "a small room with a large table.  On the table is a bottle that says, 'Drink me.'", "a Confederate States of America bank vault.  Once worthless currency papers the walls.", "an abandoned subway station.", "a mine shaft.  In the distance you can hear seven high pitched voices singing, 'Hi Ho, Hi Ho, ...'", "a Minuteman missile silo.", "the giant's mouse trap.  Fortunately, you are small enough to escape.", "Adolph Hitler's summer bunker.", "a dwarf work site.  A sign says, \"Under construction. Enter at your own risk.\"", "the giant's refrigerator.  Dwarf bodies hang from hooks.", "the Dragon's Lair.  Slightly melted suits of armor litter the floor.", "a nuclear waste depository.  The walls glow faintly.", "Millard Fillmore's tomb.  It is dull.", "an abandoned corridor of the Strategic Air Command Headquarters.  A graffito reads, \"Beware of Bat Guano.\"", "a gnome's workshop.  Half-completed whoopee cushions line the tables.", "the Mummy's Tomb.  You've triggered some mechanism and the ceiling is slowly descending.", "the Underground Gourmet's retreat.  Twinkie and King Don wrappers are piled knee deep.", "a Hoola Hoop warehouse.  The words \"Shoop Shoop\" echo from the walls.", "the first circle of hell.  The living are not allowed here.", "the hall of the pixies.  The furniture appears to have been made from cradles.", "a sulfur mine.  Molten sulfur churns in a nearby pit. It is becoming difficult to breath.", "a fairy mushroom farm.  Brilliantly colored mushrooms cover the floor.", "an ice cave.  Along the western wall, a brontosaurus is defrosting.", "the giant's stove.  Fortunately, the giant now owns a microwave oven.", "the rib cage of a long deceased whale.", "a room with six walls.  The walls appear to be made of wax.  A loud buzzing noise can be heard.", "the tomb of a Pharaoh.  It has obviously been visited by others; the tomb is in a state of total disarray.", "a coal bin.  There is a fossilized fern stump here.", "a diamond mine.  It is uncomfortably hot here.", "the bottom of an oil well.  The walls are slick.", "the lowest level of Project Mohole.  The funding bubble burst before the earth did.", "the giant's cesspool.  Fortunately, the giant was connected to the city sewerage system years ago.", "an eighteenth century sewer.  The walls are crumbling brick.  Small alligators snap at your feet.", "the lair of a giant trapdoor spider.", "a giant gopher tunnel.", "a shell -- the sole remains of a prehistoric turtle.", "a small chamber.  The walls are made of earth.  The air smells of formic acid.  A strange squeaking noise can be heard in the distance.", "a chamber of columns.  The stalagmites and stalactites join here.", "a service tunnel.  Ducts, pipes, and cables are everywhere.", "a gas tank below an abandoned service station.  No smoking!", "a huge dark chamber.  To one side, a dark, muddy river moves sluggishly.  A sign written in ancient Greek says, \"Ferry out of order.\"", "a small chamber. It is brightly lit by a peculiar lichen growing on the walls and ceiling.  The floor is rocky and steeply sloping.  Nearby, a cold, clear creek boils into the floor and out of sight.", "the nest of a very large pack rat.  There are discarded aluminum Christmas trees, broken steel utility poles, and other shiny, worthless items here.", "a dungeon.  An iron maiden, a rack, a piano, and other machines of torture can be seen.", "the hall of bats.  Thousands of bats hang from the ceiling.  Watch your step; the droppings are quite deep in places.", "a hobgoblin's hideaway.", "an electrical substation.  A transformer is humming loudly.  Nearby, cables crackle with high voltage.", "the \"gold\" room.  The walls are covered with iron pyrite.", "a room with one of Dracula's emergency coffins.  The Count is out.", "a saltpeter mine.  To one side there is a huge wooden evaporation vat.  Small crystals of saltpeter cause the walls to sparkle.", "the basement cafeteria of a local hospital.  Some say that there has been nothing edible here for years.", "a troll arsenal.  Kegs of gun powder surround you.", null };
        this.m_strTreasure = new String[] { "bag full of gold coins", "large blue-white diamond", "sixty carat emerald", "platinum crucifix", "string of pearls", "Ming vase", "list of the next ten winners of the Kentucky Derby", "pile of rubies", "previously undiscovered Rembrandt painting", "ounce of antimatter in magnetic containment", "stack of silver bars", "set of ivory tusks", "Holland and Holland double rifle chambered for .600 Nitro Express", "ancient Greek statue", "five kilograms of plutonium in a lead container", null };
        this.m_strWeapon = new String[] { "mirror", "pepper spray dispenser", "wooden stake", "roll of duct tape", "jar of Zatarain's Crab Boil", "elfin sword", "silver bullet", "crossbow", "flute fashioned from a dried-out pumpkin", "bag of salt", "soporific-laced dog biscuit", "block of boric acid", "slingshot", "recording of Leonard Nimoy singing \"Proud Mary\"", "Fresnel lens", null };
    }
    
    private class Room
    {
        String strDescription;
        int nChokepoint;
        boolean bMined;
        boolean bVisited;
        Passage[] passage;
        
        public Room() {
            this.passage = new Passage[6];
            this.strDescription = null;
            this.nChokepoint = -1;
            this.bMined = false;
            this.bVisited = false;
            this.passage[0] = null;
            this.passage[1] = null;
            this.passage[2] = null;
            this.passage[3] = null;
            this.passage[4] = null;
            this.passage[5] = null;
        }
    }
    
    private class Treasure
    {
        String strTreasure;
        Room roomTreasure;
        String strGuardian;
        boolean bSlain;
        String strWeapon;
        Room roomWeapon;
        
        public Treasure() {
            this.strTreasure = null;
            this.roomTreasure = null;
            this.strGuardian = null;
            this.bSlain = false;
            this.strWeapon = null;
            this.roomWeapon = null;
        }
        
        public Treasure(final String strTreasure, final String strGuardian, final String strWeapon) {
            this.strTreasure = strTreasure;
            this.strGuardian = strGuardian;
            this.strWeapon = strWeapon;
            this.roomTreasure = null;
            this.bSlain = false;
            this.roomWeapon = null;
        }
    }
    
    private class Passage
    {
        boolean bBlocked;
        Treasure treasureGuardian;
        Room room1;
        Room room2;
        
        public Passage() {
            this.bBlocked = true;
            this.treasureGuardian = null;
            this.room1 = null;
            this.room2 = null;
        }
    }
    
    private class PathStack
    {
        Room room;
        Passage passageUsedToEnterRoom;
        int nDirectionUsedToEnterRoom;
        PathStack pathStackNext;
        
        public PathStack() {
            this.room = null;
            this.passageUsedToEnterRoom = null;
            this.nDirectionUsedToEnterRoom = 0;
            this.pathStackNext = null;
        }
    }
}
