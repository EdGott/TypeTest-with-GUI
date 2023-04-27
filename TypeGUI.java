import javax.swing.*;
import java.awt.event.*;

public class TypeGUI {
    private JFrame frame;    //creating instance of JFrame  
    private JPanel panel;    //creating panel for formatting
    private JTextField textField;
    private static JLabel sentence, wpm, word, errors, starter;
    private JButton startButton;
    private long timerStart = 0, timerEnd = 0;
    protected double timeToType = 0;
    String toType = "";

    public TypeGUI() {
        int frameW = 550, frameH = 300;
        int textFieldW = 500, textFieldH = 150;

        frame = new JFrame();
        panel = new JPanel();
        startButton = new JButton("Start");
        word = new JLabel("");
        sentence = new JLabel("");
        wpm = new JLabel("WPM: ");
        errors = new JLabel("Errors: ");
        starter = new JLabel("As soon as you hit start be ready to type.");

        textField = new JTextField("Enter text here.", 20);

        //  Set up the frame
        frame.setSize(frameW, frameH);
        frame.add(panel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setTitle("1B Terms Type Test");
        frame.setResizable(false);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        
        //  Set up the panel
        panel.setLayout(null); // new BorderLayout()
        panel.add(startButton);
        panel.add(word);
        panel.add(sentence);
        panel.add(textField);
        panel.add(wpm);
        panel.add(errors);
        panel.add(starter);

        //  startButton position
        startButton.setBounds(((frameW / 2) - (textFieldW / 2)), frameH - 85, 70, 35);
        //  starter position
        starter.setBounds(((frameW / 2) - (textFieldW / 4)), 5, textFieldW, 30);
        //  sentence position
        sentence.setBounds(((frameW / 2) - (textFieldW / 2)), 10, textFieldW, textFieldH);
        //  textField position
        textField.setBounds(((frameW / 2) - (textFieldW / 2)), frameH - 125, textFieldW, 20);
        //  wpm position
        wpm.setBounds(((frameW / 2) + (textFieldW / 8)), frameH - 85, 110, 30);
        //  errors position
        errors.setBounds(((frameW / 3) + (textFieldW / 2)), frameH - 85, 110, 30);
        
        // Start Button (begin test)
        startButton.addActionListener(new ActionListener () {
            public void actionPerformed(ActionEvent pressStart) {
                starter.setText("");
                timerStart = System.currentTimeMillis();
                System.out.println(timerStart);
                toType = TypeTest.getSentence();
                sentence.setText("<html>" + toType + "</html>");
                textField.setText("");
                textField.requestFocus();

                //  Moved in here for formatting
                int wordW = word.getText().length() * 9;
                //  word position
                word.setBounds(((frameW / 2) - (wordW / 2)), 15, wordW, 35);
            }
        });
        
        //  Returns what the user types once they hit enter
        textField.addActionListener(new ActionListener () {
            public void actionPerformed(ActionEvent pressEnter) {
                String textInput = textField.getText();
                timerEnd = System.currentTimeMillis();
                System.out.println(timerEnd);
                System.out.println(textInput);
                timeToType = (timerEnd - timerStart) / 1000;
                System.out.println(timeToType);
                wpm.setText(String.format("WPM: %.2f", TypeTest.calculateWpm(timeToType, toType.length())));
                errors.setText("Errors: " + TypeTest.calculateAccuracy(toType, textInput));
            }
        });
    }

    public static void setWord(String randomKey) {
        word.setText(randomKey);
    }
        
    public static void main(String[] args) { 
        new TypeGUI();
    }    
}
