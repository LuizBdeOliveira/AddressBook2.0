package com.loca.addressbook.userinterface.commands;

import com.loca.addressbook.Application;
import com.loca.addressbook.exceptions.InvalidCommandParameterException;
import com.loca.addressbook.userinterface.ConsolePrinter;

import java.util.List;

public class QuitCommand implements Command {

    private CommandType commandType = CommandType.QUIT;
    private Application application;
    private List<String> parameters;
    private ConsolePrinter consolePrinter;
    public final static String NAME = "quit";
    public final static String DESCRIPTION = "quitting application";

    public QuitCommand(ConsolePrinter consolePrinter, List<String> parameters, Application application) {
        this.parameters = parameters;
        this.consolePrinter = consolePrinter;
        this.application = application;
    }

    @Override
    public String getName() {
        return NAME;
    }

    @Override
    public String getDescription() {
        return DESCRIPTION;
    }

    @Override
    public void execute() throws InvalidCommandParameterException {
        validate();
        consolePrinter.print(commandType.getSuccessMessage());
        application.quit();
    }

    private void validate() throws InvalidCommandParameterException {
        if (parameters.size() != commandType.getParametersCount()) {
            throw new InvalidCommandParameterException(commandType, parameters);
        }
    }
}
