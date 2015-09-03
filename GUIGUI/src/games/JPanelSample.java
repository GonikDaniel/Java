package games;

/**
 * Created by Daniel_Gonik on 04.08.15.
 */

import javax.swing.*;
import java.awt.*;

public class JPanelSample {

    public JPanelSample() {
        JFrame app = new JFrame("Sample");
        app.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        app.setSize(new Dimension(800,600));

        JPanel panel = new JPanel();
        panel.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        panel.setLayout(new BorderLayout(5, 5));
        JPanel centerPanel = new JPanel();
        centerPanel.setLayout(new GridBagLayout());

        JButton but1 = new JButton("1");
        JButton but2 = new JButton("2");
        but2.setMinimumSize(new Dimension(250,0));
        String[] colNames = {"Name","Number","Scores"};
        Object[][] data = {
                { "Mark",11,12},
                {"Tommy",23,34},
                {"John",34,45}
        };
        JTable table = new JTable(data, colNames);

        JScrollPane scrollpane = new JScrollPane(table);
        JSplitPane jsplitpane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT,but2,scrollpane);

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.anchor = GridBagConstraints.FIRST_LINE_START;
        gbc.fill = GridBagConstraints.BOTH;
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weightx = 1.0;
        gbc.weighty = 0.3;

        centerPanel.add(but1, gbc);

        gbc.fill = GridBagConstraints.BOTH;
        gbc.gridy = 1;
        gbc.weighty = 0.7;

        centerPanel.add(jsplitpane, gbc);

        panel.add(centerPanel, BorderLayout.CENTER);

        app.add(panel);
        app.setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new JPanelSample();
            }
        });
    }
}