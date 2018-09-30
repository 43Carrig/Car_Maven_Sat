import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainGui extends JFrame{

    private JPanel MainPanel;
    private JButton createButton;
    private JButton readButton;
    private JButton updateButton;
    private JButton deleteButton;


    public MainGui() {

        createButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                Create create = new Create();
                create.setVisible(true);
                //new MainGui().setVisible(false);

            }
        });
        readButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Read read = new Read();
                read.setVisible(true);
            }
        });
        updateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Update update = new Update();
                update.setVisible(true);
            }
        });
        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Delete delete = new Delete();
                delete.setVisible(true);
            }
        });
    }

    public static void main(String[] args) {
        /*JFrame frame = new JFrame("MainGui");
        frame.setContentPane(new MainGui().MainPanel); //
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setSize(350, 350);
        frame.setVisible(true);*/

        java.awt.EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                //new MainGui().setVisible(true);


                JFrame frame = new JFrame("MainGui");
                frame.setContentPane(new MainGui().MainPanel); //
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame.pack();
                frame.setSize(350, 350);
                frame.setVisible(true);

            }
        });

    }
}
