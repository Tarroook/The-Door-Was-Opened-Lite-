import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;


class UserView{

  static String userInfo = " ";


  public static void showMap(char[][]tab){
    int hauteur = tab.length;
    int largeur = tab[0].length;
    int[] playerPos = MapManager.getEntityPosition(tab, Player.playerChar);

    System.out.println("Room : " + Main.roomNumber);
    for(int y = 0; y < hauteur; y++){
      for(int x = 0; x < largeur; x++){
        if (playerPos[0] == x && playerPos[1] == y)
          System.out.print(tab[y][x] + "   ");
        else if ((tab[y][x] == MapManager.roof && (y == 0 || y == hauteur - 1)) || (tab[y][x] == MapManager.wall && (x == 0 || x == largeur - 1)))
          System.out.print(tab[y][x] + "   ");
        else if (tab[y][x] == MapManager.empty)
          System.out.print(tab[y][x] + "   ");
        else{
          boolean printed = false;
          for(int i = 0; i < Enemy.enemies.length; i++){
            for(char e : Enemy.enemies[i]){
              if (e == tab[y][x]){
                System.out.print(tab[y][x] + "   ");
                printed = true;
              }
            }
          }
          if (!printed)
            System.out.print(tab[y][x] + "   ");
        }
      }
      System.out.println();
    }
    System.out.println();
  }


  public static void movingUI(){
    System.out.println("\n" +  "HP : " + Player.playerHealth + "/" + Player.playerMaxHealth + "\t \t \t" 
                            + "Mana : " + Player.playerMana + "/" + Player.playerMaxMana + "\t \t \t" 
                            + "Gold : " + Player.playerMoney);
    showTextFile("uis/userUI.txt");
  }


  public static void combatUI(String textFile1, String textFile2){
    showTextFile(textFile1);
    System.out.println("\n" + "Your HP : " + Player.playerHealth + " / " + Player.playerMaxHealth + "\t \t \t \t \t \t \t \t" + Combat.currentEnemy +"'s HP : "+ Combat.enemyHealth + "/" + Combat.enemyMaxHealth + "\n");
    System.out.println("Your Mana : " + Player.playerMana + "/" + Player.playerMaxMana);
    showTextFile(textFile2);
  }


  public static void display1DTable(int width, String[] tab){
    for (int i = 0; i < 144; i++){
      System.out.print("@");
    }

    for (int j = 0; j < tab.length; j++){
      if(j % width == 0)
        System.out.println("\n");

      System.out.print("\t" + (j+1) + ": "+ tab[j] + "\t");
    }
    
    System.out.println("\n\n\tc: Cancel \n");
    
    for (int k = 0; k < 144; k++){
      System.out.print("@");
    }
    System.out.println();
  }


  public static void display1DList(int width, ArrayList<String> list){
    for (int i = 0; i < 144; i++){
      System.out.print("@");
    }
    for (int j = 0; j < list.size(); j++){
      if(j % width == 0)
        System.out.println("\n");
      System.out.print("\t" + (j+1) + ": "+ list.get(j) + "\t");
    }
    System.out.println("\n\n\tc: Cancel \n");
    for (int k = 0; k < 144; k++){
      System.out.print("@");
    }
    System.out.println();
  }


  public static void EquipmentUI(){

    for (int i = 0; i < 144; i++){
      System.out.print("@");
    }

    for (int j = 0; j < Player.playerEquipment.size(); j++){
      boolean printed = false;
      if(j % 4 == 0)
        System.out.println("\n");
      for(String e : Player.playerCurrentEquipment){
        if(e == Player.playerEquipment.get(j)){
          System.out.print("\t" + (j+1) + ": "+ Player.playerEquipment.get(j) + " (E)" +"\t");
          printed = true;
        }
      }
      if(!printed)
        System.out.print("\t" + (j+1) + ": "+ Player.playerEquipment.get(j) + "\t");
    }
    
    System.out.println("\n\n\tc: Cancel \n");
    
    for (int k = 0; k < 144; k++){
      System.out.print("@");
    }
    System.out.println();
  }

  
  public static void displayPlayerStats(){
    int spaces = 50;
    String nextPrint;
    nextPrint = "Health : " + Player.playerHealth + " / " + Player.playerMaxHealth;
    System.out.print(nextPrint);
    for(int i = nextPrint.length(); i <= spaces; i++)
      System.out.print(" ");
    System.out.print("Mana : " + Player.playerMana + " / " + Player.playerMaxMana);
    System.out.println();

    nextPrint = "Strength : " + Player.playerPhysPow;
    System.out.print(nextPrint);
    for(int i = nextPrint.length(); i <= spaces; i++)
      System.out.print(" ");
    System.out.print("Magic power : " + Player.playerMagPow);
    System.out.println();

    nextPrint = "Dexterity : " + Player.playerCritChance;
    System.out.print(nextPrint);
    for(int i = nextPrint.length(); i <= spaces; i++)
      System.out.print(" ");
    System.out.print("Mana control : " + Player.playerMagUse);
    System.out.println();

    nextPrint = "Physical defense : " + Player.playerPhysDefense;
    System.out.print(nextPrint);
    for(int i = nextPrint.length(); i <= spaces; i++)
      System.out.print(" ");
    System.out.print("Magic defense : " + Player.playerMagDefense);
    System.out.println();

    System.out.println("Luck : " + Player.playerRewardMultiplier);
  }


  public static String dialogueDisplay(boolean isRandom, int lineNumber, String dialogueFile){
    int totalLines = 0;
    try (BufferedReader br = new BufferedReader(new FileReader(dialogueFile))) {
      String line;
      while ((line = br.readLine()) != null) {
        totalLines++;
      }
    }catch(IOException e){}

    String chosedLine = "none";
    if (isRandom){
      int random = Main.randomNum(0, totalLines - 1);
      try (BufferedReader br = new BufferedReader(new FileReader(dialogueFile))){
        for(int i = 0; i < random; ++i)
          br.readLine();
        chosedLine = br.readLine();
      }catch(IOException e){}
    }
    else{
      try (BufferedReader br = new BufferedReader(new FileReader(dialogueFile))){
        for(int i = 0; i < lineNumber; ++i)
          br.readLine();
        chosedLine = br.readLine();
      }catch(IOException e){}
    }
    return chosedLine;
  }


  public static int getLineNumber(String file){
    int totalLines = 0;
    try (BufferedReader br = new BufferedReader(new FileReader(file))) {
      String line;
      while ((line = br.readLine()) != null) {
        totalLines++;
      }
    }catch(IOException e){}
    return totalLines;
  }


  public static void playDialogue(String frame, String text){
    int totalLines = UserView.getLineNumber(text);
    for (int i = 0; i < totalLines; i++){
      Main.clearConsole();
      UserView.showTextFile(frame);
      System.out.println();
      System.out.println(UserView.dialogueDisplay(false, i, text));
      Main.sc.nextLine();
    }
  }


  public static void showTextFile(String file){
    try (BufferedReader br = new BufferedReader(new FileReader(file))) {
      String line;
      while ((line = br.readLine()) != null) {
        System.out.println(line);
      }
    }catch(IOException e){}
  }


  public static void centerPrintln(String text){
    int screenWidth = 180;
    int size = text.length();
    int spaces = (screenWidth / 2) - size;
    for(int i = 0; i <= spaces; i++)
      System.out.print(" ");
    System.out.println(text);
  }
}