import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class GameGUI extends JFrame {

    private int columns, rows;
    private Container contentPane = getContentPane();
    private JPanel pnl;
    private ArrayList<ArrayList<JButton>> btns;


    public GameGUI(Integer w, Integer h){

        columns = w;
        rows = h;

        pnl = new JPanel();
        pnl.setLayout(new GridBagLayout());

        contentPane.add(pnl);

        createChocolate();

        pack();
        setVisible(true);
    }

    private void createChocolate(){
        btns = new ArrayList<>();

        GridBagConstraints gbc = new GridBagConstraints();

        for (int r = 0; r < rows; r++){
            btns.add(new ArrayList<>());
            for (int c = 0; c < columns; c++){
                btns.get(r).add(new JButton(Integer.toString(r)+","+Integer.toString(c)));
            }
        }

        for (int r = 0; r < rows; r++){
            gbc.gridy = r;
            for (int c = 0; c < columns; c++){
                gbc.gridx = c;
                JButton btn =  btns.get(r).get(c);
                btn.setForeground(new Color(139,69,19));
                btn.setBackground(new Color(139,69,19));
                btn.addActionListener(new ChompHandler());
                btn.setPreferredSize(new Dimension(80,80));
                btn.setFocusable(false);
                pnl.add(btn,gbc);

            }
        }
    }

    private class ChompHandler implements ActionListener{

        private int r = -1, c = -1;

        @Override
        public void actionPerformed(ActionEvent e) {
            String st = ((JButton) e.getSource()).getText();
            String stA[] = st.split(",");
            r = Integer.parseInt(stA[0]);
            c = Integer.parseInt(stA[1]);
            removeExcessButtons();
        }

        private void removeExcessButtons(){
            if (c < 0 || r < 0){
                JOptionPane.showMessageDialog(null, "Error with values in action listener");
            }
            else{
                for (int i = r; i < btns.size(); i++){
                    for(int j = c; j < btns.get(r).size(); j++){
                        JButton btn = btns.get(i).get(j);
                        btn.setVisible(false);
                    }
                }
            }
        }
    }

}
