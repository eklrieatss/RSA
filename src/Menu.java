import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Menu extends JFrame {
    private JTextArea textArea1;
    private JPanel panel;
    private JButton inAsciiButton;
    private JTextField textField1;
    private JTextField textField2;
    private JTextField textField3;
    private JTextField textField4;
    private JButton button1;
    private JTextField textField5;
    private JButton umrechnenButton;
    private JTextField textField6;
    private JButton inKlarButton;
    private JTextField textField7;
    private JTextField textField8;
    private JTextArea textArea2;
    private JTextArea textArea3;
    private JButton kodierenButton;
    private JButton dekodierenButton;

    public Menu(){
        setContentPane(panel);
        inKlarButton.addActionListener(e -> {
            System.out.println(panel.getSize());
        });
    }
}
