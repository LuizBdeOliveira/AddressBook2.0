package com.loca.addressbook;

import java.util.ArrayList;
import java.util.List;

import com.loca.addressbook.registry.AutoSave;
import com.loca.addressbook.registry.Registry;
import com.loca.addressbook.registry.RegistryPersister;
import com.loca.addressbook.remoteregistry.CatalogueLoader;
import com.loca.addressbook.remoteregistry.RemoteRegistry;
import com.loca.addressbook.userinterface.CommandLineInterface;
import com.loca.addressbook.userinterface.Console;

public class Application {

	private List<String> hostNames;
	private Console console = new Console();
    private Registry registry = new Registry();
    private RegistryPersister registryPersister = new RegistryPersister(registry);
    private RemoteRegistry remoteRegistry = new RemoteRegistry();

    public void start() {
		makeHostNames();
		registryPersister.load();
		initiateServerContacts();
		Runnable runnable = new AutoSave(registryPersister);
		Thread autoSave = new Thread(runnable);
		autoSave.setDaemon(true);
		autoSave.start();
		initiateCommandLineInterface();
    }


	private void initiateServerContacts() {
		for(String hostName : hostNames) {
			Runnable runnable = new CatalogueLoader(remoteRegistry, hostName);
			Thread catalogueLoader = new Thread(runnable);
	        catalogueLoader.setDaemon(true);
	        catalogueLoader.start();
		}
	}

	private void makeHostNames() {
		List<String> hostNames = new ArrayList<>();
		hostNames.add("172.20.200.157");
		hostNames.add("172.20.200.173");
		hostNames.add("172.20.201.62");
	}
	
	private void initiateCommandLineInterface() {
		CommandLineInterface commandLineInterface = new CommandLineInterface(registry, remoteRegistry, console, this);
		commandLineInterface.start();
	}
	 
	public void quit() {
		registryPersister.save();
		console.close();
	}
		
}
