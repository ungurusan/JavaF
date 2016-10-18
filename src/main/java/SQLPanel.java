
import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

/**
 * Created by Alin on 10/14/2016.
 */
public class SQLPanel {
    public SQLPanel(){

        JFrame mainFrame = new JFrame("FastCollect");
        mainFrame.setSize(640, 480);
        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainFrame.setResizable(false);

        JPanel contentPane = new JPanel();
        contentPane.setLayout(null);

        JButton connectButton = new JButton("Connect!");
        connectButton.setBounds(350,50,100,20);
        contentPane.add(connectButton);
        contentPane.setBorder(new TitledBorder(new EtchedBorder()));

        final LabeledTextField sqlServerTextArea = new LabeledTextField(contentPane,"SQL Server","sql2008",10,10,20,70 );
        final LabeledTextField userTextArea = new LabeledTextField(contentPane,"Username","sa",10,40,20,70 );
        final LabeledTextField passTextArea = new LabeledTextField(contentPane,"Password","Go4crazy",10,70,20,70 );
        final LabeledTextField databaseTextArea = new LabeledTextField(contentPane,"Database","Alin_a2a_ev",10,100,20,70 );
        final LabeledTextField queryTextArea = new LabeledTextField(contentPane,"Query","select * from [user] where id=293",10,130,20,300 );


        JPanel outputPane = new JPanel();
        outputPane.setBounds(10,160,600,250);
        contentPane.add(outputPane);

        final JTextArea outputArea = new JTextArea();
        outputArea.setEditable(false);
        outputArea.setBounds(10,180,610,250);
        outputArea.setLineWrap(true);
        outputArea.setWrapStyleWord(true);
        outputArea.setBorder(BorderFactory.createEtchedBorder());

        mainFrame.add(outputArea);
        contentPane.add(connectButton);

        connectButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ConnectSQL conn = null;
                String sqlServer = sqlServerTextArea.getText();
                String user = userTextArea.getText();
                String pass = passTextArea.getText();
                String database = databaseTextArea.getText();
                String query = queryTextArea.getText();

//                outputArea.append("Variables are:" +"\n");
//                outputArea.append(sqlServer+"\n");
//                outputArea.append(user+"\n");
//                outputArea.append(pass+"\n");
//                outputArea.append(database+"\n");
//                outputArea.append(query+"\n");

                try {
                    conn = new ConnectSQL(sqlServer,user, pass,database);
                } catch (SQLException e1) {
                    e1.printStackTrace();
                } catch (ClassNotFoundException e1) {
                    e1.printStackTrace();
                }
                java.util.List<java.util.List<String>> result = conn.getQuery(query);

                outputArea.append(result+"\n");

                conn.closeConnection();
            }
        });

        mainFrame.add(contentPane);
        mainFrame.setLocationRelativeTo ( null );
        mainFrame.setVisible(true);
    }
}
