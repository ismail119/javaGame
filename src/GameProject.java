import javax.swing.JFrame;

public class GameProject {

    public static void main(String[] args) {
        
        JFrame gameframe = Singleton_GamePanel.getInstance();
        
        GamePanel gamePanel=new GamePanel(); 
        gamePanel.requestFocus();
        gamePanel.addKeyListener(gamePanel);
        gamePanel.setFocusable(true);
        gamePanel.setFocusTraversalKeysEnabled(false);
        gamePanel.AddCatForLevel0();
        gameframe.add(gamePanel);
        gameframe.setVisible(true);
       
    }
    
}
