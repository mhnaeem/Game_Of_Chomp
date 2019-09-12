import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.TimerTask;
import java.util.Timer;

public class GameGUI extends JFrame {

    private int columns, rows;
    private Container contentPane = getContentPane();
    public JPanel pnl, menuPnl;
    private ArrayList<ArrayList<JButton>> btns;
    private ArrayList<String> cubesNotEaten;
    private GameAI ai;
    private JMenuBar menuBar;
    private JMenu info;
    private JMenuItem about,howToPlay,exit;

    public GameGUI(Integer w, Integer h, GameAI aii){
        columns = w;
        rows = h;
        ai = aii;

        addMenu();
        pnl = new JPanel();
        pnl.setLayout(new GridBagLayout());
        contentPane.add(pnl,BorderLayout.CENTER);

        createChocolate();
        pnl.updateUI();

        setResizable(false);
        pack();
        setVisible(true);
    }

    private void addMenu(){
        menuPnl = new JPanel();
        menuPnl.setLayout(new BorderLayout());
        menuBar = new JMenuBar();
        info = new JMenu("Information");
        howToPlay = new JMenuItem("How to Play");
        about = new JMenuItem("About");
        exit = new JMenuItem("Exit");

        info.add(howToPlay);
        info.add(about);
        info.add(exit);
        menuBar.add(info);
        menuPnl.add(menuBar);
        contentPane.add(menuPnl,BorderLayout.NORTH);

        about.addActionListener((ActionEvent ev) -> {
            String message = "This game is called the Game of Chomp, created by Muhammad Hammad. \n V1.0";
            JOptionPane.showMessageDialog(null,message,"About",JOptionPane.INFORMATION_MESSAGE);
        });

        exit.addActionListener((ActionEvent ev) -> System.exit(0));

        howToPlay.addActionListener((ActionEvent ev) -> {
            String message = "The bar of chocolate is to be eaten however the top left corner has been exchanged with soap.\n " +
                    "Eat chunks of chocolate from the right and try to eat as much as possible while avoiding the soap.";
            JOptionPane.showMessageDialog(null,message,"How to Play",JOptionPane.INFORMATION_MESSAGE);
        });
    }

    private void createChocolate(){
        createButtonsList();

        GridBagConstraints gbc = new GridBagConstraints();
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
        //Create soap
        btns.get(0).get(0).setBackground(Color.MAGENTA);
        btns.get(0).get(0).setForeground(Color.black);
        btns.get(0).get(0).setText("S");
        pnl.updateUI();
   }

    private void createButtonsList(){
        btns = new ArrayList<>();
        cubesNotEaten = new ArrayList<>();

        for (int r = 0; r < rows; r++){
            btns.add(new ArrayList<>());
            for (int c = 0; c < columns; c++){
                JButton btn = new JButton();
                String nameStr = Integer.toString(r)+","+Integer.toString(c);
                btn.setName(nameStr);
                cubesNotEaten.add(nameStr);
                btns.get(r).add(btn);
            }
        }
        pnl.updateUI();
    }

    public void buttonClick(String btnName){
        btns.forEach(btnArr -> btnArr.forEach(btn -> {
            if (btn.getName().equals(btnName)) {
                btn.setForeground(Color.black);
                btn.setText("X");
                btn.setFont(new Font("TimesRoman", Font.BOLD, 25));
                Timer timer = new Timer();
                timer.schedule( new TimerTask() {
                    @Override
                    public void run() {
                        btn.setText("");
                        btn.doClick();
                    }
                }, 1700);
            }
        }));
    }

    public ArrayList<String> getCubesNotEaten(){
        return cubesNotEaten;
    }

    private class ChompHandler implements ActionListener{

        private int r = -1, c = -1;

        @Override
        public void actionPerformed(ActionEvent e) {
            String st = ((JButton) e.getSource()).getName();
            String stA[] = st.split(",");
            r = Integer.parseInt(stA[0]);
            c = Integer.parseInt(stA[1]);
            removeExcessButtons();
            pnl.updateUI();
            if(st.equals("0,0")){
                //TODO: Game Over
                String[] buttons = { "Start Again", "Exit"};

                int rc = JOptionPane.showOptionDialog(null, "Game Over! " + ai.whoseTurn() + " lost the game.", "Game Over",
                        JOptionPane.WARNING_MESSAGE, 0, null, buttons, buttons[0]);
                if (rc == 0){
                    GameGUI.this.dispose();
                    new StartScreen();
                }
                if(rc == 1){
                    System.exit(0);
                }
            }
            else {
                ai.changeTurn();
                ai.playGame();
            }

        }

        private void removeExcessButtons(){
            if (c < 0 || r < 0){
                JOptionPane.showMessageDialog(null, "Error with values in action listener");
            }
            else{
                for (int i = r; i < btns.size(); i++){
                    for (int j = c; j < btns.get(r).size(); j++) {
                        JButton btn = btns.get(i).get(j);
                        btn.setEnabled(false);
                        btn.setBackground(Color.white);
                        btn.setBorder(BorderFactory.createEmptyBorder());
                        cubesNotEaten.removeIf(name -> btn.getName().equals(name));
                    }
                }
            }
        }
    }
}