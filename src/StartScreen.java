import javax.sound.sampled.BooleanControl;
import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.concurrent.Flow;

public class StartScreen extends JFrame {

    private JLabel mainLbl, wLbl, hLbl;
    private JTextField wFld, hFld;
    private JButton start, cancel;
    private Container contentPane = getContentPane();
    private JPanel mainPanel;

    public StartScreen(){
        setFrameDetails();
        setItems();
        addButtonFunctionality();
        pack();
        setVisible(true);
    }

    private void setFrameDetails(){
        setSize(500,500);
        setPreferredSize(new Dimension(500,300));
        setTitle("Welcome to Game of Chomp");
        mainPanel = new JPanel();
        contentPane.add(mainPanel);
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
    }

    private void setItems(){
        JPanel mainLblPan = new JPanel();
        mainLblPan.setPreferredSize(new Dimension(500, 100));

        mainLbl = new JLabel("Welcome to Chomp");
        mainLbl.setFont(new Font(Font.SERIF, Font.BOLD, 20));
        mainLbl.setAlignmentX(Font.CENTER_BASELINE);
        mainLblPan.add(mainLbl);
        mainPanel.add(mainLblPan);

        JPanel wPan = new JPanel();
        wPan.setPreferredSize(new Dimension(500, 150));
        wPan.setLayout(new FlowLayout());
        wLbl = new JLabel("Desired Columns: ");
        wFld = new JTextField(30);
        wPan.add(wLbl);
        wPan.add(wFld);
        mainPanel.add(wPan);

        JPanel hPan = new JPanel();
        hPan.setPreferredSize(new Dimension(500,150));
        hPan.setLayout(new FlowLayout());
        hLbl = new JLabel("Desired Rows:    ");
        hFld = new JTextField(30);
        hPan.add(hLbl);
        hPan.add(hFld);
        mainPanel.add(hPan);

        JPanel btnPan = new JPanel();
        btnPan.setPreferredSize(new Dimension(500, 150));
        btnPan.setLayout(new FlowLayout());
        start = new JButton("Send");
        cancel = new JButton("Cancel");
        btnPan.add(start);
        btnPan.add(cancel);
        mainPanel.add(btnPan);
    }

    private void addButtonFunctionality(){
        cancel.addActionListener((ActionEvent ev) -> {
            System.exit(0);
        });

        start.addActionListener((ActionEvent ev) -> {

            try {
                if (!(Integer.parseInt(wFld.getText()) > 0) && !(Integer.parseInt(hFld.getText()) > 0)) {
                    JOptionPane.showMessageDialog(null, "Columns or Rows are less than or equal to 0", "Error!", JOptionPane.ERROR_MESSAGE);
                }
                else {
                    new GameGUI(Integer.parseInt(wFld.getText()), Integer.parseInt(hFld.getText()));
                    StartScreen.this.dispose();

                }
            }
            catch (NumberFormatException e){
                JOptionPane.showMessageDialog(null, "Columns and Rows should be numbers", "Error!", JOptionPane.ERROR_MESSAGE);
            }

        });
    }

    public static void main(String[] args) {
        new StartScreen();
    }
}
