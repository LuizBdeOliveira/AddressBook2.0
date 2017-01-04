package com.loca.addressbook.userinterface.commands;

import com.loca.addressbook.registry.Registry;
import com.loca.addressbook.userinterface.ConsolePrinter;
import com.loca.addressbook.exceptions.InvalidCommandParameterException;
import com.loca.addressbook.exceptions.InvalidContactId;

import java.util.List;

public class DeleteContactCommand implements Command {

    private CommandType commandType = CommandType.DELETE;
    private ConsolePrinter consolePrinter;
    private Registry registry;
    private List<String> parameters;
    public final static String NAME = "delete";
    public final static String DESCRIPTION = "deletes a contact";

    public DeleteContactCommand (ConsolePrinter consolePrinter, Registry registry, List<String> parameters) {
    	this.consolePrinter = consolePrinter;
        this.registry = registry;
        this.parameters = parameters;
    }

    public DeleteContactCommand(){}
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
        deleteContactFromRegistry();
    }

    private void deleteContactFromRegistry() {
        String uuid = parameters.get(0);
        String message;
        try {
        	registry.deleteContact(uuid);
        	message = commandType.getSuccessMessage();
        } catch (InvalidContactId e) {
        	message = commandType.getFailureMessage();
        }
        consolePrinter.print(message);
    }

    private void validate() throws InvalidCommandParameterException {
        if (parameters.size() != commandType.getParametersCount()) {
            throw new InvalidCommandParameterException(commandType, parameters);
        }
    }
}
