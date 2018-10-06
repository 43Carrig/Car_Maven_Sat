import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Read extends JFrame {
    private JPanel ReadPanel;
    private JButton readButton;
    private JLabel result;
    private JTextField getCarFromIdTextField;
    private JLabel available;

    public Read() {

        available.setText(String.valueOf(CouchCRUD.listAllDocs()));

        readButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Car resultText = CouchCRUD.getCarFromId(getCarFromIdTextField.getText());
                result.setText(resultText.toString()); //result.setText(String.valueOf(resultText));
            }
        });
    }

    public static void main(String args[])
    {
        JFrame frame = new JFrame("Read");
        frame.setContentPane(new Read().ReadPanel); //
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setSize(300, 300);
        frame.setVisible(true);
    }
}
