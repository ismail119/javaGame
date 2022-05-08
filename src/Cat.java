
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.imageio.stream.FileImageInputStream;


class Cat {
    
    private BufferedImage cat;
    public int gravity=1;
    public int x,y;
    
    
    public Cat(int x,int y){
        this.x = x;
        this.y=y;
        
        try {
            cat = ImageIO.read(new FileImageInputStream(new File("cat.png")));
        } catch (IOException ex) {
            Logger.getLogger(GamePanel.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public BufferedImage getCat(){
        return cat;
    }
}
