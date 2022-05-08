
import javax.swing.JOptionPane;


public class LevelDesigns {
    private int level=0;
    
    public void setLevel(int levelIndex){
        System.out.println(String.format("Level: %d", levelIndex));
    } 
    public int getLevel(){
        return level;
    }
    
    
    public void GameLOSE(){
        System.out.println("Kaybettin");
    }
    
    public void OpenLevel(){}
    
}
