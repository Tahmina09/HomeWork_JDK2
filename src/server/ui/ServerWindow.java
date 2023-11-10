package server.ui;

import server.main.Server;
import server.ui.ServerView;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ServerWindow extends JFrame implements ServerView {
    private static final int WINDOW_HEIGHT = 500;
    private static final int WINDOW_WIDTH = 550;
    private static final int WINDOW_POSX = 500;
    private static final int WINDOW_POSY = 100;
    Server server;
    JButton btnStart, btnStop;
    JTextArea log;

    public ServerWindow() {
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setBounds(WINDOW_POSX, WINDOW_POSY, WINDOW_WIDTH, WINDOW_HEIGHT);
        setTitle("Chat server");
        setResizable(false);

        add(createTextArea());
        add(createButtonPanel(), BorderLayout.SOUTH);
        setVisible(true);
    }

    private Component createButtonPanel() {
        JPanel btnPanel = new JPanel(new GridLayout(1, 2));
        btnStart = new JButton("Start");
        btnStart.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                server.start();
            }
        });

        btnStop = new JButton("Stop");
        btnStop.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                server.stop();
            }
        });

        btnPanel.add(btnStart);
        btnPanel.add(btnStop);

        return btnPanel;
    }

    private Component createTextArea() {
        return log;
    }

    @Override
    public void showMessage(String msg) {
        log.append(msg);
    }
}
