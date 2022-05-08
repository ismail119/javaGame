public class Egg {
    public int x,y;
    
    public Egg(int x,int y){
        this.x=x;
        this.y=y;
    }
    

    public boolean Move(){
        if(y>=700)return false;
        
        y+=5; 
        return true;
    } 
    
}
