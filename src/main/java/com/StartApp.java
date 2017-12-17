package com;

import com.ui.UserInterface;

import java.io.IOException;

public class StartApp {

    public static void main(String[] args) throws IOException {
        UserInterface userInterface = new UserInterface();
        userInterface.runMenu();
    }

}
