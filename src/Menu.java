import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.math.BigInteger;

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
            String text = "";
            for (String s: textArea2.getText().split(" ")){
                text += (char) Integer.parseInt(s);
            }
            textArea1.setText(text);

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
            geheimInput.setText(d + "," + N);
            oeffentlichInput.setText(e + "," + N);

        });
        inAsciiButton.addActionListener(event -> {
            String text = "";
            for (char c: textArea1.getText().toCharArray()){
                textArea2.setText(text += (int) c + " ");
            }
        });
        kodierenButton.addActionListener(event -> {
            BigInteger e = new BigInteger(oeffentlichInput.getText().split(",")[0].trim());
            BigInteger N = new BigInteger(oeffentlichInput.getText().split(",")[1].trim());

            String s = "";

            for (String c : textArea2.getText().trim().split("\\s+")) {
                BigInteger m = new BigInteger(c);
                BigInteger verschluesselt = m.modPow(e, N);
                s += verschluesselt + " ";
            }

            textArea3.setText(s.trim());
        });

        dekodierenButton.addActionListener(event -> {
                BigInteger d = new BigInteger(geheimInput.getText().split(",")[0].trim());
                BigInteger N = new BigInteger(geheimInput.getText().split(",")[1].trim());


                String s = "";

                for (String t: textArea3.getText().split(" ")){
                    BigInteger c = new BigInteger(t.trim());
                    BigInteger m = c.modPow(d, N);
                    s += m + " ";
                }

                textArea2.setText(s);
        });
    }


    public static int ggt(int a, int b) {
        if (b == 0) {
            return a;
        }
        return ggt(b, a % b);
    }
}
