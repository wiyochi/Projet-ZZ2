package fr.isima.velo.clients;

import java.io.IOException;
import java.net.Socket;
import java.sql.Statement;

public class Client extends Thread {
	private static int ids = 0; // Nombre de clients qui se sont connectés au serveur.

	private final Socket socket; // Socke IPV4 correspondant à ce client.
	private final int id;
	private CommunicationHandler communicationHandler;
	private SQLHandler sqlHandler;
	private String clientID;

	private boolean isRunning = true; // Le client est connecté.

	/**
	 * Permet de construire un client, qui correspond à un socket de connection.
	 * 
	 * @param socket    Connexion au client distant.
	 * @param statement Accès à la base de données.
	 */
	public Client(Socket socket, Statement statement) {
		this.socket = socket;
		this.id = ++ids;
		sqlHandler = new SQLHandler(statement);
		try {
			communicationHandler = new CommunicationHandler(socket.getInputStream(), socket.getOutputStream());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Ferme convenablement le socket, et arrête les traitements du thread.
	 */
	public void preStop() {
		try {
			socket.close();
			isRunning = false;
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void run() {
		while (isRunning) {
			String input = communicationHandler.read();
			if (input.startsWith("Connect:")) { // Pas sécurisé, juste pour tester
				String[] ids = input.substring(11).split(":");
				clientID = sqlHandler.connect(Integer.parseInt(ids[0]), ids[1]);
			} else if (input.startsWith("Subscribe:")) {
				int id = sqlHandler.subscribe(input.split(":")[1], input.split(":")[2]);
				
			} else if (input.startsWith("History:")) {
				
			} else if (input.startsWith("NewP:")) {
				
			} else if (input.startsWith("NewJ:")) {
				
			} else if (input.startsWith("EditP:")) {
				
			}
		}
	}
}
