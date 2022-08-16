

public class Explorer {
    private String name;
    private int batterylife;
    private float xPos;
    private float yPos;

    public Explorer(String aName){
        this.name = aName;
        this.batterylife = 100;
        this.xPos = 0f;
        this.yPos = 0f;
    }

    public void moveLeft(float distance){
        if(this.batterylife<=0){
            System.out.println("Insufficient battery life");
        }else{
            if((int)distance>this.batterylife){
                int untravelled = -((this.batterylife-(int)distance));
                distance -= (float)untravelled;
                this.xPos += distance;
                this.batterylife -= (int)distance;
                System.out.println("Travelled to left "+ distance + "m , unable to travel "+ untravelled + "m , because of Insufficient battery life");
            }else{
                this.batterylife -= (int)distance;
                this.xPos += distance;
                System.out.println("Travelled to left "+ distance + "m , remaining battery life "+ this.batterylife + "%");
            }
            System.out.println("Current X position : "+this.xPos);
        }
    }

    public void moveRight(float distance){
        if(this.batterylife<=0){
            System.out.println("Insufficient battery life");
        }else{
            if((int)distance>this.batterylife){
                int untravelled = -((this.batterylife-(int)distance));
                distance -= (float)untravelled;
                this.yPos += distance;
                this.batterylife -= (int)distance;
                System.out.println("Travelled to right "+ distance + "m , unable to travel "+ untravelled + "m , because of Insufficient battery life");
            }else{
                this.batterylife -= (int)distance;
                this.yPos += distance;
                System.out.println("Travelled to right "+ distance + "m , remaining battery life "+ this.batterylife + "%");
            }
            System.out.println("Current Y position : "+this.yPos);
        }
    }

    public void charge(){
        this.batterylife = 100;
        System.out.println("Charging complete");
    }

    public String getName() {
        return name;
    }

    public int getBatterylife() {
        return batterylife;
    }

    public float getxPos() {
        return xPos;
    }

    public float getyPos() {
        return yPos;
    }
}
