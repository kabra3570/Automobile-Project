/* File: Project2.java
 * Author: Kevin Abrahams
 * Date: 20-03-2020
 * Purpose: Create a Java GUI application which uses various kinds of automobile information entered by the user.
 */
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;

public class Project2 extends JFrame {                  

    private String[] splited;
    private String make = "";
    private String model = "";
    private int milesPerGallon = 0;
    private int weight = 0;
    private double purchasePrice = 0;
    
    // use the automobileType string to know which kind of automobile object to create
    // based on which radio button is clicked
    private String automobileType;
    private Automobile automobiles[] = new Automobile[5];
    private int indexTracker = 0;
    
    // NORTH/TOP section of GUI (consists of 2 labels and 2 textfields)
    private JPanel northPanel = new JPanel();
    
    private JLabel makeAndModelLabel = new JLabel("Make and Model");
    private JTextField makeAndModel = new JTextField();
    
    private JLabel salesPriceLabel = new JLabel("Sales Price");
    private JTextField salesPrice = new JTextField();
    
    // MID section of GUI (consists of 3 radiobuttons, 3 labels, and 2 textfields)
    private JPanel midPanel = new JPanel();
    
    private JLabel automobileTypeLabel = new JLabel("Automobile Type");
    
    private JRadioButton hybrid = new JRadioButton("Hybrid");
    private JRadioButton electric = new JRadioButton("Electric");
    private JRadioButton other = new JRadioButton("Other");
    
    private JLabel mpgLabel = new JLabel("Miles per Gallon   ");
    private JTextField mpgTextField = new JTextField();
    
    private JLabel weightLabel = new JLabel("Weight in Pounds    ");
    private JTextField weightTextField = new JTextField();
        
    //SOUTH section of GUI (consists of 3 buttons and 1 textfield)
    private JPanel southPanel = new JPanel();

    private JButton btnTax = new JButton("Comupte Sales Tax");
    private JButton btnClear = new JButton("Clear Fields");
    private JButton btnReport = new JButton("Display Report");
    private JLabel tax = new JLabel(); 
    
    int filledUp = 0;
    // JFrame constructor (used to add components to the JFrame)
    public Project2() {
        setLayout(new BoxLayout(getContentPane(), BoxLayout.PAGE_AXIS));

        JPanel northHelperPanel = new JPanel();
        northHelperPanel.setMaximumSize(new Dimension(210, 100));
        northHelperPanel.setLayout(new GridBagLayout());
        
        northPanel.setLayout(new GridLayout(2, 2, 20, 4));
       
        northPanel.add(makeAndModelLabel);
        northPanel.add(makeAndModel);
        northPanel.add(salesPriceLabel);
        northPanel.add(salesPrice);
        
        northHelperPanel.add(northPanel);
        add(northHelperPanel);
        JPanel container = new JPanel();
        container.setLayout(new GridBagLayout());
        
        container.add(midPanel);
        
        midPanel.setLayout(new GridLayout(3, 3));
        JPanel textFieldContainer = new JPanel();
        
        
        ButtonGroup radioGroup = new ButtonGroup();
        radioGroup.add(hybrid);
        radioGroup.add(electric);
        radioGroup.add(other);

        midPanel.add(hybrid);
        midPanel.add(mpgLabel);
        
        midPanel.add(mpgTextField);
        midPanel.add(electric);
        midPanel.add(weightLabel);
        
        midPanel.add(weightTextField);
        midPanel.add(other);
        
        midPanel.add(textFieldContainer);
        
        midPanel.setBorder(BorderFactory.createTitledBorder("Automobile Type"));
        container.setMaximumSize(new Dimension(350, 150));
        add(container);
        // adding event listeners to radiobuttons
        hybrid.addItemListener(new HybridListener());
        electric.addItemListener(new ElectricListener());
        other.addItemListener(new OtherListener());
        
        JPanel southHelperPanel = new JPanel();
        southHelperPanel.setLayout(new GridLayout());
        
        southHelperPanel.setMaximumSize(new Dimension(300, 150));
        southHelperPanel.add(southPanel);
        add(southHelperPanel);
        southPanel.setLayout(new GridLayout(2, 2, 5, 5));
        
        southPanel.add(btnTax);
        btnTax.addActionListener(new TaxButtonListener());
        
        southPanel.add(tax);
        tax.setBorder(BorderFactory.createTitledBorder(""));
        
        southPanel.add(btnClear);
        btnClear.addActionListener(new ClearButtonListener());
        
        southPanel.add(btnReport);
        btnReport.addActionListener(new ReportButtonListener());
        
        
        
        setSize(430, 300);
        
        setTitle("Automobile Sales Tax Calculator");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setVisible(true);
    }
    
    //EventListener for hyrbid radio button
    class HybridListener implements ItemListener {
        public void itemStateChanged(ItemEvent e) {
            automobileType = "Hybrid";
            
        }
    }

    //EventListener for electric radio button
    class ElectricListener implements ItemListener {
        public void itemStateChanged(ItemEvent e) {
            automobileType = "Electric";
        }
    }
    
    //Eventlistener for other radio button
    class OtherListener implements ItemListener {
        public void itemStateChanged(ItemEvent e) {
            automobileType = "Other";
        }
    }
    
    //EventListener for Compute Sales Tax button
    class TaxButtonListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            //obtaining various info from swing components once 
            // the compute sales tax button is clicked
            splited = makeAndModel.getText().split("\\s+");
            make = splited[0];
            model = splited[1];
            milesPerGallon = 0;
            weight = 0;
            purchasePrice = 0;
            boolean badSalesPrice, badMpg, badWeight;
            badSalesPrice = badMpg = badWeight = false;
            
            // obtaining sales price from textfield (checks to see if textfield only contains numbers)
            if (!salesPrice.getText().isEmpty()) {
                for (int i = 0; i < salesPrice.getText().length(); i++) {
                    if (Character.isDigit(salesPrice.getText().charAt(i)) == false) {
                        JOptionPane.showMessageDialog(northPanel, "In the Sales Price textfield, please only enter numbers - no letters, words, or any other kinds of symbols.");
                        badSalesPrice = true;
                        break;
                    } 
                }
                if (badSalesPrice == false)
                    purchasePrice = Double.parseDouble(salesPrice.getText());
            }
            
            // obtaining mpg info from textfield (checks to see if textfield only contains numbers)
            if (!mpgTextField.getText().isEmpty()) {
                for (int i = 0; i < mpgTextField.getText().length(); i++) {
                    if (Character.isDigit(mpgTextField.getText().charAt(i)) == false) {
                        JOptionPane.showMessageDialog(northPanel, "In the Miles per Gallon textfield, please only enter numbers - no letters, words, or any other kinds of symbols.");
                        badMpg = true;
                    }
                }
                if (badMpg == false)
                    milesPerGallon = Integer.parseInt(mpgTextField.getText());
            }
            
            // obtaining weight info from textfield (checks to see if textfield only contains numbers)
            if (!weightTextField.getText().isEmpty()) {
                for (int i = 0; i < weightTextField.getText().length(); i++) {
                    if (Character.isDigit(weightTextField.getText().charAt(i)) == false) {
                        JOptionPane.showMessageDialog(northPanel, "In the Weight textfield, please only enter numbers - no letters, words, or any other kinds of symbols.");
                        badWeight = true;
                    }
                } 
                if (badWeight == false) {
                    weight = Integer.parseInt(weightTextField.getText());
                }
            }
            
            if (!automobileType.isEmpty()) {
                // create a Hybrid object using appropriate info if Hybrid radio button was clicked 
                if (automobileType.equals("Hybrid")) {
                    if (filledUp == 1)
                        filledUp++;
                    automobiles[indexTracker] = (new Hybrid(make, model, purchasePrice, milesPerGallon));
                    tax.setText("" + automobiles[indexTracker].salesTax());
                    if (indexTracker == 4) {
                        indexTracker = 0;
                        filledUp++;
                    }
                    else
                        indexTracker++;
                }
                
                // create an Electric object using appropriate info if Electric radio button was clicked
                if (automobileType.equals("Electric")) {
                    if (filledUp == 1)
                        filledUp++;
                        automobiles[indexTracker] = (new Electric(make, model, purchasePrice, weight));
                        tax.setText("" + automobiles[indexTracker].salesTax());
                        if (indexTracker == 4) {
                            indexTracker = 0;
                            filledUp++;
                        }
                        else
                            indexTracker++;
                    }
                
                // create a default Automobile object using appropriate info if Other radio button was clicked
                if (automobileType.equals("Other")) {
                    if (filledUp == 1)
                        filledUp++;
                    automobiles[indexTracker] = (new Automobile(make, model, purchasePrice));
                    tax.setText("" + automobiles[indexTracker].salesTax());
                    if (indexTracker == 4) {
                        indexTracker = 0;
                        filledUp++;
                    }
                    else
                        indexTracker++;
                    }
                }
        }
    }
    
    //EventListener for Clear Fields button
    class ClearButtonListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            makeAndModel.setText("");
            salesPrice.setText("");
            mpgTextField.setText("");
            weightTextField.setText("");
            tax.setText("");
        }
    }
    
    //EventListener for Display Report button
    class ReportButtonListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            if (filledUp >= 2) {
                if (automobiles[0] != null)
                System.out.println(automobiles[0].toString());
                for (int i = automobiles.length-1; i > 0; i--) {
                    if (automobiles[i] != null)
                    System.out.println(automobiles[i].toString());
                }
            }
            else {
                for (int i = automobiles.length-1; i >= 0; i--) {
                    if (automobiles[i] != null)
                    System.out.println(automobiles[i].toString());
                }
            }
        }
    }
    
    public static void main(String[] args) {
        Project2 frame = new Project2();
    }
}
