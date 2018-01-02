// 
// Decompiled by Procyon v0.5.30
// 

package pclient.adv;

import com.pchat.sc.WindowUtil;
import com.pchat.sc.StringUtil;
import pclient.shd.RoomItem;
import java.util.ArrayList;

public class RoamRooms
{
    private ArrayList roamRooms;
    
    public RoamRooms() {
        this.roamRooms = new ArrayList();
    }
    
    public void clear() {
        synchronized (this.roamRooms) {
            this.roamRooms.clear();
        }
    }
    
    public int size() {
        synchronized (this.roamRooms) {
            return this.roamRooms.size();
        }
    }
    
    public RoomItem[] toArray() {
        synchronized (this.roamRooms) {
            return this.roamRooms.toArray(new RoomItem[0]);
        }
    }
    
    public String toString() {
        final StringBuffer sb = new StringBuffer();
        synchronized (this.roamRooms) {
            for (int i = 0; i < this.size(); ++i) {
                sb.append("room:" + this.get(i) + ",");
            }
        }
        return sb.toString();
    }
    
    public String[] getRoomNames() {
        final String[] array;
        synchronized (this.roamRooms) {
            array = new String[this.size()];
            for (int i = 0; i < array.length; ++i) {
                array[i] = this.get(i).name;
            }
        }
        return array;
    }
    
    public void replaceRoomList(final String s) {
        synchronized (this.roamRooms) {
            this.roamRooms.clear();
            this.parseRooms(s);
        }
    }
    
    public void setLockForRooms(final String s) {
        synchronized (this.roamRooms) {
            for (int i = 0; i < this.size(); ++i) {
                this.get(i).locked = false;
            }
        }
        final String[] splitString = StringUtil.splitString(s, "|", false);
        for (int j = 0; j < splitString.length; ++j) {
            final RoomItem lookupRoom = this.lookupRoom(splitString[j]);
            if (lookupRoom != null) {
                lookupRoom.locked = true;
            }
        }
    }
    
    public void updateCount(final String s, final int count, final boolean locked) {
        final RoomItem insertIfNotFound = this.insertIfNotFound(s);
        insertIfNotFound.count = count;
        insertIfNotFound.locked = locked;
    }
    
    public RoomItem insertIfNotFound(final String s) {
        final RoomItem roomItem = new RoomItem(s, 0);
        synchronized (this.roamRooms) {
            final RoomItem lookupRoom = this.lookupRoom(s);
            if (lookupRoom != null) {
                return lookupRoom;
            }
            final int size = this.size();
            int i = 0;
            while (i < size) {
                final RoomItem value = this.get(i);
                final int compareToRoom = value.compareToRoom(roomItem);
                if (compareToRoom < 0) {
                    ++i;
                }
                else {
                    if (compareToRoom == 0) {
                        return value;
                    }
                    this.add(i, roomItem);
                    return roomItem;
                }
            }
            this.add(roomItem);
        }
        return roomItem;
    }
    
    public RoomItem lookupRoom(final String s) {
        final RoomItem roomItem = new RoomItem(s, 0);
        synchronized (this.roamRooms) {
            for (int i = 0; i < this.roamRooms.size(); ++i) {
                final RoomItem roomItem2 = this.roamRooms.get(i);
                if (roomItem2.equals(roomItem)) {
                    return roomItem2;
                }
            }
        }
        return null;
    }
    
    public boolean deleteRoom(final String s) {
        final RoomItem roomItem = new RoomItem(s, 0);
        synchronized (this.roamRooms) {
            for (int i = 0; i < this.roamRooms.size(); ++i) {
                if (((RoomItem)this.roamRooms.get(i)).equals(roomItem)) {
                    this.roamRooms.remove(i);
                    return true;
                }
            }
        }
        return false;
    }
    
    private RoomItem get(final int n) {
        return this.roamRooms.get(n);
    }
    
    private void add(final int n, final RoomItem roomItem) {
        this.roamRooms.add(n, roomItem);
    }
    
    private void add(final RoomItem roomItem) {
        this.roamRooms.add(roomItem);
    }
    
    private void parseRooms(final String s) {
        final String[] splitString = StringUtil.splitString(s, "|", false);
        for (int i = 0, length = splitString.length; i < length; ++i) {
            final String s2 = splitString[i];
            String s3;
            if (++i < length) {
                s3 = splitString[i];
            }
            else {
                s3 = "0";
            }
            final RoomItem roomItem = new RoomItem(s2, WindowUtil.parseInt(s3, 0));
            if (!roomItem.isRemovable()) {
                this.add(roomItem);
            }
        }
    }
}
