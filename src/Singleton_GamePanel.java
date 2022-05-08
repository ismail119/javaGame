import javax.swing.JFrame;

public class Singleton_GamePanel extends JFrame{
    
    private static JFrame gameFrame=null;
    
//Singleton Pattern
    private Singleton_GamePanel(){}
    
    public static JFrame getInstance(){
        if(gameFrame==null){
           
            //Setting of Frame
            gameFrame = new JFrame(); 
            gameFrame.setResizable(false);
            gameFrame.setFocusable(false);
            gameFrame.setSize(500,700);
            gameFrame.setTitle("Java Game Project");
            gameFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        }
        return gameFrame;
    }
       
}
