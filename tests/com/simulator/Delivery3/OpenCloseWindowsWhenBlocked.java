package com.simulator.Delivery3;

import com.simulator.model.*;
import javafx.fxml.FXML;
import javafx.scene.control.ToggleButton;
import org.junit.Assert;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class OpenCloseWindowsWhenBlocked{

    @Test
    void OpenWindowsWhenBlocked() throws HouseLoadException {
        House.getInstance().getRoomByName("Garage").getWindowByName("EastWindow").setBlockedTrue();
        Throwable exception = assertThrows(ExceptionInInitializerError.class, () -> House.getInstance().getRoomByName("Garage").openAllWindows());
        assertEquals(null, exception.getMessage());
    }

    @Test
    void CloseWindowsWhenBlocked() throws HouseLoadException {
        House.getInstance().getRoomByName("Garage").getWindowByName("EastWindow").setOpen();
        House.getInstance().getRoomByName("Garage").getWindowByName("EastWindow").setBlockedTrue();
        Throwable exception = assertThrows(ExceptionInInitializerError.class, () -> House.getInstance().getRoomByName("Garage").getWindowByName("EastWindow").setClosed());
        assertEquals(null, exception.getMessage());
    }
}
