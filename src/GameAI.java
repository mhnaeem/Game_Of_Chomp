import java.util.ArrayList;
import java.util.Random;

public class GameAI{

    private boolean playerTurn = false;
    private boolean AITurn = false;
    private GameGUI currGUI;

    public GameAI(Integer w, Integer h){
        currGUI = new GameGUI(w,h,this);
        firstTurn();
        playGame();
    }

    private void firstTurn(){
        Random r = new Random();
        int randomNum = r.nextInt(2);
        switch (randomNum){
            case 0:
                playerTurn = true;
                break;
            case 1:
                AITurn = true;
                break;
            default:
                AITurn = false;
                playerTurn = false;
                break;
        }
    }

    public void playGame(){
        if(AITurn){
            currGUI.setTitle("Computer's Turn");
            ArrayList<String> notEaten = currGUI.getCubesNotEaten();
            Random r = new Random();
            int ranNum = r.nextInt(notEaten.size());
            if (notEaten.size() > 1){
                while (ranNum == 0){
                    ranNum = r.nextInt(notEaten.size());
                }
            }
            currGUI.buttonClick(notEaten.get(ranNum));
        }
        if (playerTurn){
            currGUI.setTitle("Player's Turn");
        }
    }

    public void changeTurn(){
        if(playerTurn){
            playerTurn = false;
            AITurn = true;
        }
        else {
            playerTurn = true;
            AITurn = false;
        }
    }

    public String whoseTurn(){
        if(AITurn){
            return "Computer";
        }
        else {
            return "Player";
        }
    }
}
