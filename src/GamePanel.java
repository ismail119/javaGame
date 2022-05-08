import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.geom.Point2D;
import java.util.ArrayList;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.Timer;

public class GamePanel extends JPanel implements KeyListener, ActionListener{
      
    Chicken chicken ;
    Timer timer = new Timer(10,this);
    int LevelIndex=0;
    public static int total_score=0;
    JLabel score = new JLabel();
    
    ArrayList<Egg> eggs = new ArrayList();
    ArrayList<Cat> cats = new ArrayList();
    ArrayList<Star> stars = new ArrayList();
    
    
    public GamePanel(){
        chicken = new Chicken();
        UpgradeScore();
        this.add(score);
        timer.start();
        setBackground(new Color(192,192,192));
    }
    private void UpgradeScore(){
        score.setText(String.format("Score: %d",total_score));
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
            g.drawImage(cat.getCat(),cat.x,cat.y, cat.getCat().getWidth()/10, cat.getCat().getHeight()/10, this);
        }
        
        for(var star:stars){
            g.drawImage(star.getStar(),star.x,star.y, star.getStar().getWidth()/5, star.getStar().getHeight()/5, this);
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
    
    private void ControlCrashes(){
        for(var cat: cats){
            var distance = Point2D.distance(cat.x, cat.y, chicken.chicken_xPosition, chicken.chicken_yPosition);
            if(distance<=35){
                LosePanel();
            }
        }
        
        
        for(var j=0; j<eggs.size(); j++){
            for(var i=0;i<stars.size();i++){
                   var distance = Point2D.distance(eggs.get(j).x, eggs.get(j).y, stars.get(i).x, stars.get(i).y);
                    if(distance<=35 && !stars.get(i).isCollected){
                        stars.get(i).isCollected=true;
                        total_score++;
                        stars.remove(i);
                        eggs.remove(j);
                        UpgradeScore();
                    }
            }
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
        ControlCrashes();
        repaint();
    }
    
    public void WinPanel(){
        int input = JOptionPane.showOptionDialog(Singleton_GamePanel.getInstance(), "Tebrikler bölümü tamamladınız.", "Tebrik Mesaji", JOptionPane.OK_CANCEL_OPTION, JOptionPane.INFORMATION_MESSAGE, null, null, null);

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
        int input = JOptionPane.showOptionDialog(Singleton_GamePanel.getInstance(), "Kaybettiniz!", "Yenilgi Mesaji", JOptionPane.OK_CANCEL_OPTION, JOptionPane.INFORMATION_MESSAGE, null, null, null);

        if(input == JOptionPane.OK_OPTION)
        {
            Singleton_GamePanel.getInstance().setVisible(false);
            System.exit(0);
        }
        else{
            System.exit(0);
        }
    }
    
    
    public void ResetGame(int nextLevelIndex)
    {
       cats.clear();
       stars.clear();
       chicken.chicken_xPosition = 185;
       chicken.chicken_yPosition = 40;
       chicken.gravity=0;
       Singleton_GamePanel.getInstance().setVisible(true);
       int input = JOptionPane.showOptionDialog(Singleton_GamePanel.getInstance(),String.format("Level %d",LevelIndex+1), "Yeni Bolum", JOptionPane.OK_CANCEL_OPTION, JOptionPane.INFORMATION_MESSAGE, null, null, null);

        if(input == JOptionPane.OK_OPTION)
        {
            chicken.gravity=1;
            timer.start();
           switch (LevelIndex) {
               case 1 -> AddCatAndStarForLevel1();
               case 2 -> AddCatAndStarForLevel2();
           }
        }
        else{
            System.exit(0);
        }
    }
    
    public void AddCatAndStarForLevel0(){
        Cat cat1 = new Cat(50,300);
        Cat cat2 = new Cat(330,550);
        Star star1 = new Star(300,400);
        stars.add(star1);
        cats.add(cat1);
        cats.add(cat2);
    }
    public void AddCatAndStarForLevel1(){
        Cat cat1 = new Cat(20,500);
        Cat cat2 = new Cat(350,500);
        Cat cat3 = new Cat(185,220);
        Star star1 = new Star(30,300);
        Star star2 = new Star(200,500);
        stars.add(star1);
        stars.add(star2);
        cats.add(cat1);
        cats.add(cat2);
        cats.add(cat3);
    }
    public void AddCatAndStarForLevel2(){
        Cat cat1 = new Cat(50,300);
        Cat cat2 = new Cat(300,500);
        Cat cat3 = new Cat(200,200);
        Star star1 = new Star(350,400);
        Star star2 = new Star(100,600);
        stars.add(star1);
        stars.add(star2);
        cats.add(cat1);
        cats.add(cat2);
        cats.add(cat3);
    }

}
