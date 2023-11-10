package client.main;

import client.ui.ClientView;
import server.main.Server;

public class Client {
    private String name;
    private ClientView clientView;
    private Server server;
    private boolean connected;

    public Client(Server server, ClientView clientView) {
        this.server = server;
        this.clientView = clientView;
    }

    public boolean connectToServer(String name){
        this.name = name;
        if (server.connectUser(this)) {
            printText("Подключение прошло успешно!");
            connected = true;
            String log = server.getHistory();
            if (log != null) {
                printText(log);
            }
            return true;
        } else {
            printText("Не удалось подключиться к серверу!");
            return false;
        }
    }

    public void sendMessage(String message) {
        if (connected) {
            if (!message.isEmpty()) {
                server.message(name + ": " + message);
             }
        } else {
            printText("Не удалось подключиться к серверу!");
        }
    }

    public void serverAnswer(String answer){
        printText(answer);
    }

    public void disconnect(){
        if (connected){
            connected = false;
            clientView.disconnectFromServer();
            server.disconnectUser(this);
            printText("Вы отключились от сервера!");
        }
    }

    public String getName() {
        return name;
    }

    private void printText(String text){
        clientView.showMessage(text);
    }
}
