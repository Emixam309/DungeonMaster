package fr.croisier.dungeonmaster.viewmodels;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import fr.croisier.dungeonmaster.world.Room;

/**
 * ViewModel class for managing the state of a Room.
 */
public class RoomViewModel extends ViewModel {
    private final MutableLiveData<Room> roomLiveData = new MutableLiveData<>();

    /**
     * Gets the LiveData object for the room.
     *
     * @return The LiveData object for the room.
     */
    public LiveData<Room> getRoom() {
        return roomLiveData;
    }

    /**
     * Sets the room and updates the LiveData object.
     *
     * @param room The room to set.
     */
    public void setRoom(Room room) {
        roomLiveData.setValue(room);
    }
}