import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class CalculatorGUI extends JFrame implements ActionListener {

    // Create components
    private JTextField displayField;
    private JButton[] numberButtons;
    private JButton[] functionButtons;
    private JButton addButton, subButton, mulButton, divButton, eqButton, clrButton, piButton, sinButton, cosButton, tanButton, historyButton, expButton, sqrtButton; // Added expButton and sqrtButton
    private JPanel buttonPanel;
    private JTextArea historyArea; // Added text area for history

    // Variables to hold operands and operator
    private double num1, num2;
    private char operator;

    public CalculatorGUI() {

        // Set up frame
        setTitle("Calculator");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // Create display field
        displayField = new JTextField();
        displayField.setHorizontalAlignment(JTextField.RIGHT);
        displayField.setEditable(false);
        add(displayField, BorderLayout.NORTH);

        // Create buttons
        buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(5, 4)); // Increased rows to accommodate additional buttons

        numberButtons = new JButton[10];
        for (int i = 0; i < 10; i++) {
            numberButtons[i] = new JButton(String.valueOf(i));
            numberButtons[i].addActionListener(this);
            buttonPanel.add(numberButtons[i]);
        }

        addButton = new JButton("+");
        subButton = new JButton("-");
        mulButton = new JButton("*");
        divButton = new JButton("/");
        eqButton = new JButton("=");
        clrButton = new JButton("C");
        piButton = new JButton("\u03C0");
        sinButton = new JButton("sin");
        cosButton = new JButton("cos");
        tanButton = new JButton("tan");
        historyButton = new JButton("History"); // Added history button
        expButton = new JButton("^"); // Added exponent button
        sqrtButton = new JButton("\u221A"); // Added square root button

        functionButtons = new JButton[]{addButton, subButton, mulButton, divButton, eqButton, clrButton, piButton, sinButton, cosButton, tanButton, historyButton, expButton, sqrtButton};

        for (JButton button : functionButtons) {
            button.addActionListener(this);
            buttonPanel.add(button);
        }

        add(buttonPanel, BorderLayout.CENTER);

        // Create history area
        historyArea = new JTextArea();
        historyArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(historyArea);
        scrollPane.setPreferredSize(new Dimension(200, 100));
        add(scrollPane, BorderLayout.SOUTH);

        // Set up frame size
        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }

    public void actionPerformed(ActionEvent e) {
        JButton source = (JButton) e.getSource();
        String command = source.getText();

        if (command.matches("[0-9]")) {
            displayField.setText(displayField.getText() + command);
        } else if (command.equals("+") || command.equals("-") || command.equals("*") || command.equals("/")) {
            num1 = Double.parseDouble(displayField.getText());
            operator = command.charAt(0);
            displayField.setText("");
        } else if (command.equals("=")) {
            num2 = Double.parseDouble(displayField.getText());
            switch (operator) {
                case '+':
                    displayField.setText(String.valueOf(num1 + num2));
                    break;
                case '-':
                    displayField.setText(String.valueOf(num1 - num2));
                    break;
                case '*':
                    displayField.setText(String.valueOf(num1 * num2));
                    break;
                case '/':
                    displayField.setText(String.valueOf(num1 / num2));
                    break;
            }
            // Add calculation to history
            historyArea.append(num1 + " " + operator + " " + num2 + " = " + displayField.getText() + "\n");
        } else if (command.equals("\u03C0")) {
            displayField.setText(String.valueOf(Math.PI));
        } else if (command.equals("sin")) {
            double angle = Double.parseDouble(displayField.getText());
            displayField.setText(String.valueOf(Math.sin(Math.toRadians(angle))));
        } else if (command.equals("cos")) {
            double angle = Double.parseDouble(displayField.getText());
            displayField.setText(String.valueOf(Math.cos(Math.toRadians(angle))));
        } else if (command.equals("tan")) {
            double angle = Double.parseDouble(displayField.getText());
            displayField.setText(String.valueOf(Math.tan(Math.toRadians(angle))));
        } else if (command.equals("^")) {
            num1 = Double.parseDouble(displayField.getText());
            operator = '^';
            displayField.setText("");
        } else if (command.equals("\u221A")) {
            double value = Double.parseDouble(displayField.getText());
            displayField.setText(String.valueOf(Math.sqrt(value)));
        } else if (command.equals("C")) {
            displayField.setText("");
            num1 = num2 = 0;
            operator = ' ';
        } else if (command.equals("History")) {
            JOptionPane.showMessageDialog(this, historyArea.getText(), "Calculation History", JOptionPane.PLAIN_MESSAGE);
        }
    }

    public static void main(String[] args) {
        new CalculatorGUI();
    }
}
