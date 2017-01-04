package com.loca.addressbook.userinterface;

import java.util.Scanner;

public class Console implements ConsolePrinter {

	private InputHandler inputHandler;
	private boolean quit = false;
 
    public void registerInputHandler(InputHandler inputHandler) {
    	this.inputHandler = inputHandler;
    }
 
    public void readUserInput() {
        Scanner scanner = new Scanner(System.in);
		while (!quit) {
			String userInput = scanner.nextLine();
			CommandLine commandLine = CommandLine.parse(userInput);
            inputHandler.handle(commandLine);
		}
		scanner.close();
	}

    @Override
    public void print(String output) {
        System.out.println(output);
    }

    public void close() {
        quit = true;
    }

}
