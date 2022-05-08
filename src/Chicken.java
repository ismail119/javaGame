
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.imageio.stream.FileImageInputStream;


public class Chicken {
    private BufferedImage chicken;
    public int chicken_xPosition = 185, chicken_yPosition=70;
    private LevelDesigns results = new LevelDesigns();
    public int gravity=1;
    
    
    public Chicken(){
        try {
            chicken = ImageIO.read(new FileImageInputStream(new File("chicken.png")));
        } catch (IOException ex) {
            Logger.getLogger(GamePanel.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public BufferedImage getChicken(){
        return chicken;
    }
    
    public void ChickenMovement(int changeAmount){
        
        if(changeAmount<0){
            if(chicken_xPosition<=0)return;   
        }
        else{      
            if(chicken_xPosition>=420)return; 
        }
            
        chicken_xPosition += changeAmount;
    }
    public boolean Gravity(){
        if(chicken_yPosition<600){
            chicken_yPosition += gravity;
            return true;
            }
        return false;
    }
}
