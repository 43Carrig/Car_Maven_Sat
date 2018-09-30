import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class Create extends JFrame {
    private JPanel CreatePanel;
    private JButton createButton;
    private JTextField idtextField;
    private JTextField registrationTextField;
    private JTextField soldtextField;
    private JLabel before;
    private JLabel after;

    private final static List<Car> carData = new ArrayList<>();

    public Create() {
        super("Create");

        before.setText(String.valueOf(CouchCRUD.listAllDocs()));
        createButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                //carData.add(new Car(idtextField.getText(),revtextField.getText()));

                Car car = new Car();

                car.set_id(idtextField.getText());
                car.set_rev(null);
                car.setSold(Boolean.valueOf(soldtextField.getText()));
                car.setRegistration(registrationTextField.getText());

                CouchCRUD.postToCouch(car);//carData.get(0),carData.get(1)
                after.setText(String.valueOf(CouchCRUD.listAllDocs()));

                //CouchCRUD.postToCouch(new Car(idtextField.getText(),registrationTextField.getText()));//carData.get(0),carData.get(1)

                //CouchCRUD.postToCouch(new Car(idtextField.getText(),registrationTextField.getText()));

                //CouchCRUD.postToCouch(new Car(idtextField.getText()));

            }
        });

    }
    public static void main(String args[])
    {

        java.awt.EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                //new Create().setVisible(true);

                JFrame frame = new JFrame("Create");
                frame.setContentPane(new Create().CreatePanel); //
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame.pack();
                frame.setSize(350, 350);
                frame.setVisible(true);
            }
        });

    }

    }



