import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Delete extends JFrame {

    private JPanel DeletePanel;
    private JTextField deleteTextField;
    private JButton deleteButton;
    private JLabel resultsBefore;
    private JLabel resultsAfter;

    public Delete() {
        resultsBefore.setText(String.valueOf(CouchCRUD.listAllDocs()));
        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                CouchCRUD.deleteCar(deleteTextField.getText());
                resultsAfter.setText(String.valueOf(CouchCRUD.listAllDocs()));
            }
        });
    }

    public static void main (String args[])
    {
        JFrame frame = new JFrame("Delete");
        frame.setContentPane(new Delete().DeletePanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setSize(300, 300);
        frame.setVisible(true);

    }

}
