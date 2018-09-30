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
    private JTextField revtextField;
    private final static List<Car> data = new ArrayList<>();

    public Update() {
        before.setText(String.valueOf(CouchCRUD.listAllDocs()));

        updateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                CouchCRUD.putCarDetails(idtextField.getText(), revtextField.getText(),registrationtextField.getText(), Boolean.valueOf(isSoldtextField.getText()));
                after.setText(String.valueOf(CouchCRUD.listAllDocs()));
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
