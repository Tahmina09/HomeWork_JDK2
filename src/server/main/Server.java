package server.main;

import client.main.Client;
import server.repository.Repository;
import server.ui.ServerView;

import java.util.ArrayList;
import java.util.List;

public class Server {
    private ServerView serverView;
    private Repository repository;
    boolean isServerWorking;
    List<Client> clientsList;

    public Server(ServerView serverView, Repository repository) {
        this.serverView = serverView;
        this.repository = repository;
        clientsList = new ArrayList<>();
    }

    public void start() {
        if (isServerWorking) {
            showOnWindow("Server is working!\n");
        } else {
            isServerWorking = true;
            showOnWindow("Server started!\n");
        }
    }

    public void stop() {
        if (!isServerWorking) {
            showOnWindow("Server is already stopped!\n");
        } else {
            isServerWorking = false;
            for (Client client : clientsList) {
                disconnectUser(client);
            }
            showOnWindow("Server stopped!\n");
        }
    }

    public boolean connectUser(Client client) {
        if (!isServerWorking) {
            return false;
        }
        clientsList.add(client);
        return true;
    }

    public void disconnectUser(Client client) {
        clientsList.remove(client);
    }

    public String getHistory() {
        return repository.fileReader();
    }
    public void showOnWindow(String text) {
        serverView.showMessage(text);
    }

    public void saveInHistory(String txt) {
        repository.fileWriter(txt);
    }

    private void answerAll(String answer) {
        for (Client client: clientsList) {
            client.serverAnswer(answer);
        }
    }

    public void message(String msg) {
        if (!isServerWorking) {
            showOnWindow("Не удалось подключиться к серверу!");
        }
        showOnWindow(msg);
        answerAll(msg);
        saveInHistory(msg);
    }
}
