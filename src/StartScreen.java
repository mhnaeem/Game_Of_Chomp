import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class StartScreen extends JFrame {

    private JLabel mainLbl, wLbl, hLbl;
    private JTextField wFld, hFld;
    private JButton start, cancel, small, medium, large;
    private Container contentPane = getContentPane();
    private JPanel mainPanel;

    public StartScreen(){
        setFrameDetails();
        createLabels();
        addLabelsToScreen();
        createLabels();
        createButtons();
        addButtonsToScreen();
        addButtonFunctionality();
        pack();
        setVisible(true);
    }

    private void setFrameDetails(){
        setSize(500,300);
        setPreferredSize(new Dimension(500,300));
        setTitle("Welcome to Game of Chomp");
        mainPanel = new JPanel();
        contentPane.add(mainPanel);
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
        setResizable(false);
    }

    private void createButtons(){
        start = new JButton("Send");
        cancel = new JButton("Cancel");
        small = new JButton("Small");
        medium = new JButton("Medium");
        large = new JButton("Large");
    }

    private void addButtonsToScreen(){
        //Dimensions for the button panels
        Dimension size = new Dimension(500, 150);

        JPanel btnPan = new JPanel();
        btnPan.setPreferredSize(size);
        btnPan.setLayout(new FlowLayout());

        JPanel btnPan1 = new JPanel();
        btnPan1.setPreferredSize(size);
        btnPan1.setLayout(new FlowLayout());

        btnPan1.add(small);
        btnPan1.add(medium);
        btnPan1.add(large);

        btnPan.add(start);
        btnPan.add(cancel);
        mainPanel.add(btnPan1);
        mainPanel.add(btnPan);
    }

    private void createLabels(){
        mainLbl = new JLabel("Welcome to Chomp");
        wLbl = new JLabel("Desired Columns: ");
        hLbl = new JLabel("Desired Rows:    ");
    }

    private void addLabelsToScreen(){
        Dimension size = new Dimension(500, 100);

        JPanel mainLblPan = new JPanel();
        mainLblPan.setPreferredSize(size);
        mainLbl.setFont(new Font(Font.SERIF, Font.BOLD, 20));
        mainLbl.setAlignmentX(Font.CENTER_BASELINE);
        mainLblPan.add(mainLbl);
        mainPanel.add(mainLblPan);

        JPanel wPan = new JPanel();
        wPan.setPreferredSize(size);
        wPan.setLayout(new FlowLayout());

        wFld = new JTextField(30);
        wPan.add(wLbl);
        wPan.add(wFld);
        mainPanel.add(wPan);

        JPanel hPan = new JPanel();
        hPan.setPreferredSize(size);
        hPan.setLayout(new FlowLayout());

        hFld = new JTextField(30);
        hPan.add(hLbl);
        hPan.add(hFld);
        mainPanel.add(hPan);
    }

    private void addButtonFunctionality(){
        cancel.addActionListener((ActionEvent ev) -> {
            System.exit(0);
        });

        small.addActionListener((ActionEvent ev) -> {
            hFld.setText("4");
            wFld.setText("4");
            start.doClick();
        });

        medium.addActionListener((ActionEvent ev) -> {
            hFld.setText("4");
            wFld.setText("6");
            start.doClick();
        });

        large.addActionListener((ActionEvent ev) -> {
            hFld.setText("4");
            wFld.setText("10");
            start.doClick();
        });

        start.addActionListener((ActionEvent ev) -> {

            try {
                if (!(Integer.parseInt(wFld.getText()) > 0) && !(Integer.parseInt(hFld.getText()) > 0)) {
                    JOptionPane.showMessageDialog(null, "Columns or Rows are less than or equal to 0", "Error!", JOptionPane.ERROR_MESSAGE);
                }
                else {
                    new GameAI(Integer.parseInt(wFld.getText()), Integer.parseInt(hFld.getText()));
                    StartScreen.this.dispose();

                }
            }
            catch (NumberFormatException e){
                JOptionPane.showMessageDialog(null, "Columns and Rows should be numbers", "Error!", JOptionPane.ERROR_MESSAGE);
            }
        });

        //Set start button as default key
        JRootPane rootPane = getRootPane();
        rootPane.setDefaultButton(start);
    }

    public static void main(String[] args) {
        new StartScreen();
    }}
