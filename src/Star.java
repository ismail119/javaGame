
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.imageio.stream.FileImageInputStream;

public class Star {
    public int x,y;
    private BufferedImage star;
    public boolean isCollected=false;

    public Star(int x, int y) {
        this.x = x;
        this.y = y;
        
        try {
            star = ImageIO.read(new FileImageInputStream(new File("star.png")));
        } catch (IOException ex) {
            Logger.getLogger(GamePanel.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
     public BufferedImage getStar(){
        return star;
    }
}
