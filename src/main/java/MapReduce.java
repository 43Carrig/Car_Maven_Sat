import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MapReduce {
    private JButton mapReduceButton;
    private JPanel panel1;
    private JTextField mapReducetextField;
    private JLabel result;

    public MapReduce() {
        mapReduceButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                CouchCRUD.simpleMapReduce(mapReducetextField.getText());
            }
        });
    }
}
