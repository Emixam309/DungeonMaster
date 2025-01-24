package fr.univlittoral.projetcroisier;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import fr.univlittoral.projetcroisier.world.Room;

public class RoomViewModel extends ViewModel {
    private final MutableLiveData<Room> roomLiveData = new MutableLiveData<>();

    public LiveData<Room> getRoom() {
        return roomLiveData;
    }

    public void setRoom(Room room) {
        roomLiveData.setValue(room);
    }
}