import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner; //To return errors 
public class StoreRunner {
  public static Store readGunListFile(String fileName) {

    //Two arraylists to be returned in the form of a Store object.
    ArrayList<Skin> skinList = new ArrayList<>();
    ArrayList<Weapon> weaponList = new ArrayList<>();

    //what "try" does is attempt to run code within it.
    //If it results in an error, it will run the code in the "catch" block.
    try { 
      File myObj = new File(fileName); //Creates a file object according to the file name.
      Scanner myReader = new Scanner(myObj); //Scanner can also read files.

      String name = "", price = "", fireRate = "", magSize = ""; //variables for each weapon will be used in the while loop.
      while (myReader.hasNextLine()) { //Repeats until the file has been completely read.
        String data = myReader.nextLine(); //get the next line of the file
        if (data.substring(0, 9).equals("NEWWEAPON")) { //new weapon
          name = ""; price = ""; fireRate = ""; magSize = ""; //reset variables
          int flag = 0; //flag is which part of the string its reading

          for (int i = 10; i < data.length()-1; i++) {
            //if it's a space, then it's a new part of the string
            while (data.charAt(i) != ' ' && i < data.length()-1) {
              switch(flag) {
                case 0: //name
                  name += data.charAt(i);
                  break;
                case 1: //price
                  price += data.charAt(i);
                  break;
                case 2: //fireRate
                  fireRate += data.charAt(i);
                  break;
                case 3: //magSize
                  magSize += data.charAt(i);
                  break;
              }
              i++; //increment iterator
            }
            flag++; //increment flag
          }
          //add the last part of the string (too lazy to refactor, also it works correctly anyways)
          magSize += data.charAt(data.length() - 1);
          //add to the weaponlist
          weaponList.add(new Weapon(name, Integer.parseInt(price), Double.parseDouble(fireRate), Integer.parseInt(magSize)));

          //instead, we're doing skins
        } else {
          String skinName = "", skinPrice = "", skinRarity = ""; //reset skin variables
          int flag = 0; //again, using a flag
          
          for (int i = 0; i < data.length()-1; i++) {
            while (data.charAt(i) != ' ' && i < data.length()-1) {
              switch(flag) {
                case 0: //for skinName
                  if (data.charAt(i) == '_') { //we do this to remove underscores in the name of the skin
                    data = data.substring(0, i) + " " + data.substring(i + 1);
                  }
                  skinName += data.charAt(i);
                  break;
                case 1: //for skinPrice
                  skinPrice += data.charAt(i);
                  break;
                case 2: //for skinRarity
                  skinRarity += data.charAt(i);
                  break;
              }
              i++;
            }
            flag++;
          }
          //add the last part of the string (too lazy to refactor, also it works correctly anyways)
          skinRarity += data.charAt(data.length() - 1);
          //add to skinList
          skinList.add(new Skin(name, Integer.parseInt(price), Double.parseDouble(fireRate), Integer.parseInt(magSize), skinName, Integer.parseInt(skinPrice), skinRarity));
        }
      }
      //close the scanner (no memory leaks!!)
      myReader.close();
    } catch (FileNotFoundException error) { 
      //FileNotFoundException returns that a file is not found. 
      //Error is the error message to help with debugging (possibly)
      System.out.println("An error occurred loading existing guns. ERROR: " + error);
    }
    return new Store(weaponList, skinList); //return the store object
  }
  public static Store readAbilityListFile(Store store, String fileName) {
    //Ability list to add to the main Store object.
    ArrayList<Ability> abilityList = new ArrayList<>();

    //what "try" does is attempt to run code within it.
    //If it results in an error, it will run the code in the "catch" block.
    try { 
      File myObj = new File(fileName); //Creates a file object according to the file name.
      Scanner myReader = new Scanner(myObj); //Scanner can also read files.

      String name = "", price = "", agentName = "", abilityType = "", abilityData = "", magSize = ""; //variables for each ability will be used in the while loop.
      while (myReader.hasNextLine()) { //Repeats until the file has been completely read.
        String data = myReader.nextLine(); //get the next line of the file
        name = ""; price = ""; agentName = ""; abilityType = ""; abilityData = ""; magSize = ""; //reset variables
        int flag = 0; //flag is which part of the string its reading
        for (int i = 0; i < data.length()-1; i++) {
          //if it's a space, then it's a new part of the string
          while ((data.charAt(i) != ' ' || flag > 4) && i < data.length()-1) {
            switch(flag) {
              case 0: //agentName
                agentName += data.charAt(i);
                break;
              case 1: //abilityType
                abilityType += data.charAt(i);
                break;
              case 2: //name
                if (data.charAt(i) == '_') { //we do this to remove underscores in the name of the skin
                  data = data.substring(0, i) + " " + data.substring(i + 1);
                }
                name += data.charAt(i);
                break;
              case 3: //price
                price += data.charAt(i);
                break;
              case 4: //charges of ability
                magSize += data.charAt(i);
                break;
              default: //abilityData
                abilityData += data.charAt(i);
                break;
            }
            i++; //increment iterator
          }
          flag++; //increment flag
        }
        //add the last part of the string (too lazy to refactor, also it works correctly anyways)
        abilityData += data.charAt(data.length() - 1);
        //add to the weaponlist
        abilityList.add(new Ability(name, Integer.parseInt(price), (double)(0), Integer.parseInt(magSize), agentName, abilityType, abilityData));
      }
      //close the scanner (no memory leaks!!)
      myReader.close();
    } catch (FileNotFoundException error) { 
      //FileNotFoundException returns that a file is not found. 
      //Error is the error message to help with debugging (possibly)
      System.out.println("An error occurred loading existing abilities. ERROR: " + error);
    }
    store.setAbility(abilityList);
    return store;
  }
  //Attempts to print the skins of a weapon, checking if there are any skins within the skin list that match the gun name.
  //If not, it prints an error message.
  public static void printSkinList(ArrayList<Skin> skinList, Scanner scan) {
    System.out.println("What weapon do you want to see the skin list of?");
    String weaponName = scan.nextLine();
    System.out.println(weaponName.substring(0, 1).toUpperCase() + weaponName.substring(1) + " Skins:");
    boolean hasSkin = false;

    //checks within the skin list if there are any skins
    for (Skin skin : skinList) {
      if (weaponName.toLowerCase().equals(skin.getWeaponName().toLowerCase())) {
        hasSkin = true;
        System.out.println(skin);
      } 
    }
    //if the gun has no skins
    if (!hasSkin) {
      System.out.println("There are no skins here!");
    }
  }

  //print the weapon list
  public static void printWeaponList(ArrayList<Weapon> weaponList) {
    for (Weapon weapon : weaponList) {
      System.out.println(weapon);
    }
  }

  //print the agent list
  public static void printAbilityList(ArrayList<Ability> abilityList, Scanner scan) {
    
    System.out.println("What agent do you want to see the ability list of?");
    String agentName = scan.nextLine();
    System.out.println(agentName.substring(0, 1).toUpperCase() + agentName.substring(1) + " Abilities:");
    boolean hasAbilities = false;

    //checks within the skin list if there are any skins
    for (Ability ability : abilityList) {
      if (agentName.toLowerCase().equals(ability.getAbilityAgent().toLowerCase())) {
        hasAbilities = true;
        System.out.println(ability);
      } 
    }
    //if the gun has no skins
    if (!hasAbilities) {
      System.out.println("There are no abilities for this agent!");
    }
  }

  //print the agent list
  public static void printAgentList(ArrayList<Ability> abilityList) {
    ArrayList<String> agents = new ArrayList<>();
    for (Ability ability : abilityList) {
      boolean exists = false;
      for (String agent : agents) {
        if (agent.equals(ability.getAbilityAgent())) {
          exists = true;
        }
      }
      if (exists == false) {
        agents.add(ability.getAbilityAgent());
      }
    }
    for (String agent : agents) {
      System.out.println(agent);
    }
  }

  public static boolean loadGuns(boolean invalid, Scanner scan) {
    if (invalid) System.out.println("That's not valid input."); //if the last input was invalid
    System.out.println("Would you like to add existing guns and abilities (As of Oct. 24) (Y/N)? - ");
    String load = scan.nextLine(); //read next line
    //if it's yes, return true, false, return false, if it's neither, then recursively loop the function with "invalid" being true.
    return load.toLowerCase().equals("y") ? true : load.toLowerCase().equals("n") ? false : loadGuns(true, scan);
  }

  public static int checkIntInput(Scanner scan) {
    //checks if the input is valid as an integer, recursively asking for a valid int.
    String userInput = scan.nextLine();
    int choice;
    try {
      choice = Integer.parseInt(userInput);
    } catch (NumberFormatException e) {
      System.out.println("Bad input, try again.");
      return checkIntInput(scan);
    }
    return choice;
  }
  
  public static double checkDoubleInput(Scanner scan) {
    //checks if the input is valid as a double, recursively asking for a valid double.
    String userInput = scan.nextLine();
    double choice;
    try {
      choice = Double.parseDouble(userInput);
    } catch (NumberFormatException e) {
      System.out.println("Bad input, try again.");
      return checkDoubleInput(scan);
    }
    return choice;
  }

  public static String checkStringInput(Scanner scan) {
    //Makes sure that the input value is valid, recursively asking for a valid string.
    String choice = scan.nextLine();
    return choice != null && !" ".equals(choice) && !"".equals(choice) ? choice : checkStringInput(scan);
  }
  
  public static void runShop(Store store, Scanner scan) {
    //recursively runs the runShop function until a valid choice has been selected.
    System.out.println("What would you like to do? (1) View Weapons (2) View Skins (3) Add Weapon (4) Add Skin (5) View Agents (6) View Abilities (7) Exit");
    int choice = checkIntInput(scan);
    switch (choice) {
      case 1: //View weapons
        printWeaponList(store.getWeapon());
        runShop(store, scan);
        break;
      case 2: //View skins
        printSkinList(store.getSkin(), scan);
        runShop(store, scan);
        break;
      case 3: //Add weapon
        System.out.println("What is the name of the weapon?");
        String name = checkStringInput(scan);
        System.out.println("What is the price of the weapon?");
        int price = checkIntInput(scan);
        System.out.println("What is the fire rate of the weapon?");
        double fireRate = checkDoubleInput(scan);
        System.out.println("What is the mag size of the weapon?");
        int magSize = checkIntInput(scan);
        store.addWeapon(new Weapon(name, price, fireRate, magSize));
        runShop(store, scan);
        break;
      case 4: //Add skin
        System.out.println("What is the name of the skin?");
        String skinName = checkStringInput(scan);
        System.out.println("What is the price of the skin?");
        int skinPrice = checkIntInput(scan);
        System.out.println("What is the rarity of the skin?");
        String skinRarity = checkStringInput(scan);
        System.out.println("What weapon is the skin of?");
        String weaponName = checkStringInput(scan);
        boolean exists = false;
        for (Weapon weapon : store.getWeapon()) {
          if (weapon.getName().toLowerCase().equals(weaponName.toLowerCase())) {
            store.addSkin(new Skin(weaponName.substring(0, 1).toUpperCase() + weaponName.substring(1), weapon.getPrice(), weapon.getFireRate(), weapon.getMagSize(), skinName.substring(0, 1).toUpperCase() + skinName.substring(1), skinPrice, skinRarity.substring(0, 1).toUpperCase() + skinRarity.substring(1)));
            exists = true;
          }
        }
        if (!exists) System.out.println("That weapon doesn't exist."); //if the weapon doesn't exist
        runShop(store, scan);
        break;
      case 5: //View agents
        printAgentList(store.getAbility());
        runShop(store,scan);
        break;
      case 6:
        printAbilityList(store.getAbility(), scan);
        runShop(store, scan);
        break;
      case 7:
        System.out.println("Thank you for coming!");
        break;
      default:
        System.out.println("That's not a valid option.");
        runShop(store, scan);
    }
  }

  public static void main(String[] args) {
    Store store = new Store(); //init store and its skins/weapons
      try (Scanner scan = new Scanner(System.in)) {
          System.out.println("Welcome to the Valorant Gun Store!");
          if (loadGuns(false, scan)) { //checks if user wants to add existing skins
              store = readGunListFile("gunList.txt");
              store = readAbilityListFile(store,"abilityList.txt");
          } 
          runShop(store, scan); //run the shop loop
      }
  }
}
