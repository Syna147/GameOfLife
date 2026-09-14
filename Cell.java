public class Cell {
    
    private boolean isLiving;
    private int xPos;
    private int yPos;
    private int numNeighbors;
        
    public Cell(int xPos, int yPos) {
        this.isLiving = false;
        this.xPos = xPos;
        this.yPos = yPos;
        this.numNeighbors = 0;
    }
    
    public int getXPos() {
        return xPos;
    }
    
    public int getYPos() {
        return yPos;
    }
    
    public int getNumNeighbors() {
        return numNeighbors;
    }
    
    public boolean getIsLiving() {
        return isLiving;
    }
    
    public void setIsLiving(boolean status) {
        isLiving = status;
    }
}
