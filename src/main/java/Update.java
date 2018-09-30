import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class Update extends JFrame {

    private JPanel UpdatePanel;
    private JButton updateButton;
    private JTextField idtextField;
    private JTextField isSoldtextField;
    private JTextField registrationtextField;
    private JLabel before;
    private JLabel after;
    private final static List<Car> data = new ArrayList<>();

    public Update() {
        updateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                CouchCRUD.putCarDetails(idtextField.getText(), registrationtextField.getText(), Boolean.valueOf(isSoldtextField.getText()));
            }
        });
    }

    public static void main(String args[])
    {
        JFrame frame = new JFrame("Update");
        frame.setContentPane(new Update().UpdatePanel); //
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setSize(300, 300);
        frame.setVisible(true);
    }

}
