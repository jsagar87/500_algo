package com.sagar.watcher;

import static java.nio.file.StandardWatchEventKinds.ENTRY_CREATE;
import static java.nio.file.StandardWatchEventKinds.ENTRY_DELETE;
import static java.nio.file.StandardWatchEventKinds.ENTRY_MODIFY;

import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.WatchEvent;
import java.nio.file.WatchKey;
import java.nio.file.WatchService;

public class FileWatcher {

	public static void main(String[] args) {

		try {

			WatchService watcher = FileSystems.getDefault().newWatchService();
			Path dir = Paths.get("C:/Users/jariwabh/Documents");
			dir.register(watcher, ENTRY_CREATE, ENTRY_DELETE, ENTRY_MODIFY);

			System.out.println("Watch Service registered for dir: " + dir.getFileName());

			while (true) {
				WatchKey key;
				System.out.println("in while...");
				try {
					key = watcher.take();
				} catch (InterruptedException ex) {
					return;
				}
				System.out.println("After take...");

				for (WatchEvent<?> event : key.pollEvents()) {
					WatchEvent.Kind<?> kind = event.kind();

					@SuppressWarnings("unchecked")
					WatchEvent<Path> ev = (WatchEvent<Path>) event;
					Path fileName = ev.context();

					System.out.println(kind.name() + ": " + fileName);

					if (kind == ENTRY_MODIFY && fileName.toString().equals("sample.txt")) {
						System.out.println("My source file has changed!!!");
					}
				}

				boolean valid = key.reset();
				if (!valid) {
					break;
				}
			}

		} catch (Exception ex) {
			System.err.println(ex);
		}
	}

}
