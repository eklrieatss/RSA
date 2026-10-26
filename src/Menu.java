import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Menu extends JFrame {
    private JTextArea textArea1;
    private JPanel panel;
    private JButton inAsciiButton;
    private JTextField pInput;
    private JTextField qInput;
    private JTextField NInput;
    private JTextField φInput;
    private JButton button1;
    private JTextField eInput;
    private JButton umrechnenButton;
    private JTextField dInput;
    private JButton inKlarButton;
    private JTextField oeffentlichInput;
    private JTextField geheimInput;
    private JTextArea textArea2;
    private JTextArea textArea3;
    private JButton kodierenButton;
    private JButton dekodierenButton;

    private int q;
    private int p;
    private int e;
    private int φ;
    private int N;
    private int d;
    private int o;

    public Menu(){



        setContentPane(panel);
        inKlarButton.addActionListener(e -> {
            System.out.println(panel.getSize());
        });
        button1.addActionListener(event -> {
            q = Integer.parseInt(qInput.getText());
            p = Integer.parseInt(pInput.getText());
            e = 5;
            φ = (p-1)*(q-1);
            N = q*p;




            while (ggt(e, φ) != 1){
                e++;
            }

            NInput.setText(N + "");
            φInput.setText(φ + "");
            eInput.setText(e + "");


        });
        umrechnenButton.addActionListener(event -> {
            int d = 0;

            while (e*d % φ != 1){
                d++;
            }


            dInput.setText(d + "");
            geheimInput.setText(d + "");
            oeffentlichInput.setText(e + "," + N);

        });
        inAsciiButton.addActionListener(event -> {
            String text = "";
            for (char c: textArea1.getText().toCharArray()){
                textArea2.setText(text += (int) c + ",");
            }
        });
        kodierenButton.addActionListener(e -> {
            String text = "";
            for (String s: textArea2.getText().split(",")){
                int i = Integer.parseInt(s);
            }
        });
    }


    public static int ggt(int a, int b) {
        if (b == 0) {
            return a;
        }
        return ggt(b, a % b);
    }
}
