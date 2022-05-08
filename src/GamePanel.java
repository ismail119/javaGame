import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.Timer;

public class GamePanel extends JPanel implements KeyListener, ActionListener{
      
    Chicken chicken ;
    Timer timer = new Timer(10,this);
    int LevelIndex=0;
    
    ArrayList<Egg> eggs = new ArrayList();
    ArrayList<Cat> cats = new ArrayList();
    

    
    public GamePanel(){
        chicken = new Chicken();
        timer.start();
        
    }
    
    
    @Override
    public void paint(Graphics g){
        super.paint(g);
        g.drawImage(chicken.getChicken(), chicken.chicken_xPosition, chicken.chicken_yPosition, 
                chicken.getChicken().getWidth()/13,chicken.getChicken().getHeight()/13, this);
    
        g.setColor(Color.yellow);
        for(var egg: eggs){
            g.fillOval(egg.x, egg.y,20, 30);
        }
        
        for(var cat: cats){
            g.drawImage(cat.getCat(),cat.x,cat.y, cat.getCat().getWidth()/11, cat.getCat().getHeight()/11, this);
        }
    }
    
    @Override
    public void repaint(){
        super.repaint();
    }
   
    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent e) {
        int c = e.getKeyCode();
        
        switch (c) {
            case KeyEvent.VK_LEFT -> chicken.ChickenMovement(-25);
            case KeyEvent.VK_RIGHT -> chicken.ChickenMovement(25);
            case KeyEvent.VK_SPACE -> eggs.add(new Egg(chicken.chicken_xPosition+15,chicken.chicken_yPosition+80));
            default -> {
            }
        }
        repaint();
    }

    @Override
    public void keyReleased(KeyEvent e) {
    }
    
    private void EggsMovement(){
        for(var egg: eggs){
             egg.Move();
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        boolean isGameContinue = chicken.Gravity();
        if(!isGameContinue){
            timer.stop();
            WinPanel();
        }
        EggsMovement();
        repaint();
    }
    
    public void WinPanel(){
        int input = JOptionPane.showOptionDialog(Singleton_GamePanel.getInstance(), "Tebrikler bölümü tamamladınız. Diğer bölüm için Ok a basınız.", "Tebrik Mesaji", JOptionPane.OK_CANCEL_OPTION, JOptionPane.INFORMATION_MESSAGE, null, null, null);

        if(input == JOptionPane.OK_OPTION)
        {
            if(LevelIndex==2){
                System.exit(0);
            }
            Singleton_GamePanel.getInstance().setVisible(false);
            LevelIndex+=1;
            ResetGame(LevelIndex);
        }
        else{
            System.exit(0);
        }
    }
    
    
    public void LosePanel(){
        int input = JOptionPane.showOptionDialog(Singleton_GamePanel.getInstance(), "Tebrikler bölümü tamamladınız.", "Tebrik Mesaji", JOptionPane.OK_CANCEL_OPTION, JOptionPane.INFORMATION_MESSAGE, null, null, null);

        if(input == JOptionPane.OK_OPTION)
        {
            Singleton_GamePanel.getInstance().setVisible(false);
            LevelIndex=0;
            ResetGame(0);
        }
        else{
            System.exit(0);
        }
    }
    
    
    public void ResetGame(int nextLevelIndex)
    {
       cats.clear();
       chicken.chicken_xPosition = 185;
       chicken.chicken_yPosition = 70;
       chicken.gravity=0;
       Singleton_GamePanel.getInstance().setVisible(true);
       int input = JOptionPane.showOptionDialog(Singleton_GamePanel.getInstance(),String.format("Level %d",LevelIndex+1), "Yeni Bolum", JOptionPane.OK_CANCEL_OPTION, JOptionPane.INFORMATION_MESSAGE, null, null, null);

        if(input == JOptionPane.OK_OPTION)
        {
            chicken.gravity=1;
            timer.start();
            if(LevelIndex==1){
                AddCatForLevel1();
            }
            else if(LevelIndex==2){
                AddCatForLevel2();
            }
        }
        else{
            System.exit(0);
        }
    }
    
    public void AddCatForLevel0(){
        Cat cat1 = new Cat(50,500);
        cats.add(cat1);
    }
    public void AddCatForLevel1(){
        Cat cat1 = new Cat(50,300);
        Cat cat2 = new Cat(300,500);
        cats.add(cat1);
        cats.add(cat2);
    }
    public void AddCatForLevel2(){
        Cat cat1 = new Cat(50,300);
        Cat cat2 = new Cat(300,500);
        Cat cat3 = new Cat(200,200);
        cats.add(cat1);
        cats.add(cat2);
        cats.add(cat3);
    }

}
