

public class Scientific extends Explorer{

    public Scientific(String name){
        super(name);
    }

    public void checkSubstance(float volume, float weight){
        float density = weight/volume;
        int density1 = Math.round(density*100);
        switch(density1){
            case 270:
                System.out.println("Type of substance is Aluminium, with density "+ ((float)density1/100)+" g/cm3");
                break;
            case 894:
                System.out.println("Type of substance is Copper, with density "+ ((float)density1/100)+" g/cm3");
                break;
            case 1930:
                System.out.println("Type of substance is Gold, with density "+ ((float)density1/100)+" g/cm3");
                break;
            case 786:
                System.out.println("Type of substance is Iron, with density "+ ((float)density1/100)+" g/cm3");
                break;
            case 1134:
                System.out.println("Type of substance is Lead, with density "+ ((float)density1/100)+" g/cm3");
                break;
            case 174:
                System.out.println("Type of substance is Magnesium, with density "+ ((float)density1/100)+" g/cm3");
                break;
            case 1050:
                System.out.println("Type of substance is Silver, with density "+ ((float)density1/100)+" g/cm3");
                break;
            case 575:
                System.out.println("Type of substance is Tin, with density "+ ((float)density1/100)+" g/cm3");
                break;
            case 714:
                System.out.println("Type of substance is Zinc, with density "+ ((float)density1/100)+" g/cm3");
                break;
            default:
                System.out.println("Unknown substance");
        }
    }
}