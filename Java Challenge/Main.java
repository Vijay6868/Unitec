

import java.util.Scanner;

public class Main {

    /*
    Sabrian
    Joe
    James
    Pahyani
    Ahmad
     */

    public static void main(String[] args) {
	    Explorer explorer1 = new Explorer("TR-1");
        Scientific explorer2 = new Scientific("TR-2");
        boolean quit = false;
        boolean quit1 = false;
        boolean quit2 = false;
        Scanner scanner = new Scanner(System.in);
        float distance;

         while(!quit){
             mainMenu();
             int selection = scanner.nextInt();
             scanner.nextLine();
             switch (selection){
                 case 1:
                     while(!quit1){
                         explorer1Menu();
                         selection = scanner.nextInt();
                         scanner.nextLine();
                         switch(selection){
                             case 1:
                                 System.out.println("Name : " + explorer1.getName());
                                 System.out.println("Battery life : " + explorer1.getBatterylife() + "%");
                                 System.out.println("X position : " + explorer1.getxPos());
                                 System.out.println("Y position : " + explorer1.getyPos());
                                 break;
                             case 2:
                                 System.out.print("Enter distance to left : ");
                                 distance = scanner.nextFloat();
                                 explorer1.moveLeft(distance);
                                 break;
                             case 3:
                                 System.out.print("Enter distance to right : ");
                                 distance = scanner.nextFloat();
                                 explorer1.moveRight(distance);
                                 break;
                             case 4:
                                 explorer1.charge();
                                 break;
                             case 5:
                                 System.out.println("Returning to main menu");
                                 quit1 = true;
                                 break;
                             default:
                                 System.out.println("Invalid input");
                                 break;
                         }
                     }
                     break;
                 case 2:
                     while(!quit2){
                         explorer2Menu();
                         selection = scanner.nextInt();
                         scanner.nextLine();
                         switch(selection){
                             case 1:
                                 System.out.println("Name : " + explorer2.getName());
                                 System.out.println("Battery life : " + explorer2.getBatterylife() + "%");
                                 System.out.println("X position : " + explorer2.getxPos());
                                 System.out.println("Y position : " + explorer2.getyPos());
                                 break;
                             case 2:
                                 System.out.print("Enter distance to left : ");
                                 distance = scanner.nextFloat();
                                 explorer2.moveLeft(distance);
                                 break;
                             case 3:
                                 System.out.print("Enter distance to right : ");
                                 distance = scanner.nextFloat();
                                 explorer2.moveRight(distance);
                                 break;
                             case 4:
                                 explorer2.charge();
                                 break;
                             case 5:
                                 System.out.print("Enter substance weight : ");
                                 float weight = scanner.nextFloat();
                                 System.out.print("Enter substance volume : ");
                                 float volume = scanner.nextFloat();
                                 explorer2.checkSubstance(volume,weight);
                                 break;
                             case 6:
                                 System.out.println("Returning to main menu");
                                 quit2 = true;
                                 break;
                             default:
                                 System.out.println("Invalid input");
                                 break;
                         }
                     }
                     break;
                 case 3:
                     System.out.println("Exiting application");
                     quit = true;
                     break;
                 default:
                     System.out.println("Unknown input");
             }
         }
    }

    private static void mainMenu(){
        System.out.println("\n\n\n=====================Main Menu=====================");
        System.out.println("1. TR-1 (Explorer robot)");
        System.out.println("2. TR-2 (Scientific explorer robot");
        System.out.println("3. Exit");
        System.out.print("Please enter selection (1-3):");
    }

    private static void explorer1Menu(){
        System.out.println("\n\n\n====================TR-1 (Explorer robot)====================");
        System.out.println("1. Output details");
        System.out.println("2. Move left");
        System.out.println("3. Move right");
        System.out.println("4. Charge");
        System.out.println("5. Return to main menu");
        System.out.print("Please enter selection (1-5):");
    }

    private static void explorer2Menu(){
        System.out.println("\n\n\n====================TR-2 (Scientific Explorer robot)====================");
        System.out.println("1. Output details");
        System.out.println("2. Move left");
        System.out.println("3. Move right");
        System.out.println("4. Charge");
        System.out.println("5. Check substance");
        System.out.println("6. Return to main menu");
        System.out.print("Please enter selection (1-6):");
    }
}
