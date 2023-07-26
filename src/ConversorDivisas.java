import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DecimalFormat;

public class ConversorDivisas {
    private JFrame frame;
    private JComboBox<String> fromCurrencyComboBox;
    private JComboBox<String> toCurrencyComboBox;
    private JTextField amountField;
    private JButton convertButton;
    private JLabel resultLabel;

    private final double[][] conversionRates = {
            // Dólares,    Reales,     Euros,      Yenes,      Pesos Argentinos
            {1.00,        6.15,       1.18,       182.58,     500.15},  // Dólares
            {0.16,        1.00,       0.19,       29.45,      57.21},  // Reales
            {0.85,        5.23,       1.00,       155.27,     300.15}, // Euros
            {182.59,       29.68,      155.27,     1.00,       1.93},   // Yenes
            {500.15,       57.16,      300.15,     1.93,       1.00}    // Pesos Argentinos
    };

    private final String[] monedas = {"Dólares", "Reales", "Euros", "Yenes", "Pesos Argentinos"};

    public ConversorDivisas() {
        frame = new JFrame("Conversor de Divisas");
        frame.setLayout(new FlowLayout());
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(350, 200);

        fromCurrencyComboBox = new JComboBox<>(monedas);
        toCurrencyComboBox = new JComboBox<>(monedas);
        amountField = new JTextField(10);
        convertButton = new JButton("Aceptar");
        resultLabel = new JLabel("Resultado: ");

        convertButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int fromIndex = fromCurrencyComboBox.getSelectedIndex();
                int toIndex = toCurrencyComboBox.getSelectedIndex();
                double conversionRate = conversionRates[fromIndex][toIndex];

                try {
                    double amount = Double.parseDouble(amountField.getText());
                    double convertedAmount = amount * conversionRate;
                    DecimalFormat df = new DecimalFormat("#.##");
                    String resultText = df.format(amount) + " " + monedas[fromIndex] + " equivale a " +
                            df.format(convertedAmount) + " " + monedas[toIndex];
                    resultLabel.setText("Resultado: " + resultText);
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(frame, "Ingrese un valor numérico válido.", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        frame.add(fromCurrencyComboBox);
        frame.add(new JLabel("a"));
        frame.add(toCurrencyComboBox);
        frame.add(amountField);
        frame.add(convertButton);
        frame.add(resultLabel);

        frame.setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new ConversorDivisas();
            }
        });
    }
}
