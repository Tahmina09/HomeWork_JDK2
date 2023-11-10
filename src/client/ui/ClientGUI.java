package client.ui;

import client.main.Client;
import server.main.Server;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ClientGUI extends JFrame implements ClientView {
    private static final int WINDOW_HEIGHT = 300;
    private static final int WINDOW_WIDTH = 400;
    private static final int WINDOW_POSX = 500;
    private static final int WINDOW_POSY = 100;

    private Client client;
    JTextArea log;
    JTextField loginField, passwordField, IPField, portField, messageField;
    JButton sendMessageButton, loginButton;

    public ClientGUI(Server server) {
        this.client = new Client(server, this);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setBounds(WINDOW_POSX, WINDOW_POSY,WINDOW_WIDTH,WINDOW_HEIGHT);
        setTitle("Client Chat");
        setResizable(false);


        add(createTextFields(), BorderLayout.NORTH);
        add(createTextAndButtonArea(), BorderLayout.SOUTH);
        add(createLog());
        setVisible(true);
    }

    private Component createTextFields() {
        JPanel textFieldPanel = new JPanel(new GridLayout(2,5));
        loginField = new JTextField("ivan_igorevich");
        passwordField = new JPasswordField("123456");
        IPField = new JTextField("127.0.0.1");
        portField = new JTextField("8189");
        loginButton = new JButton("Login");
        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                logIn();
            }
        });

        textFieldPanel.add(IPField);
        textFieldPanel.add(portField);
        textFieldPanel.add(loginField);
        textFieldPanel.add(passwordField);
        textFieldPanel.add(loginButton);

        return textFieldPanel;
    }

    private Component createTextAndButtonArea() {
        JPanel textBtnPanel = new JPanel(new BorderLayout());
        messageField = new JTextField();
        messageField.addActionListener(new ActionListener() {
//                        TODO добавить keylistener
            @Override
            public void actionPerformed(ActionEvent e) {
                message();
            }
        });

        sendMessageButton = new JButton("Send");
        sendMessageButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                message();
            }
        });

        textBtnPanel.add(messageField, BorderLayout.CENTER);
        textBtnPanel.add(sendMessageButton,BorderLayout.EAST);
        return textBtnPanel;
    }


    private Component createLog() {
        log = new JTextArea();
        log.setEditable(false);
        JScrollPane scrollLog = new JScrollPane(log);
        return scrollLog;
    }

    private void appendLog(String str) {
        log.append(str + "\n");
    }

    public void message() {
        client.sendMessage(messageField.getText());
        messageField.setText("");
    }

    private void logIn(){
        if (client.connectToServer(loginField.getText())){
            hideTextFields(false);
        }
    }

    @Override
    public void showMessage(String text) {
        appendLog(text);
    }

    @Override
    public void disconnectFromServer() {
        hideTextFields(true);
        client.disconnect();
    }

    private void hideTextFields(boolean visible){
        createTextFields().setVisible(visible);
    }
}

