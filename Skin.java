
public class Skin extends Weapon {
    //Attributes of the Skin class
    private String skinName;
    private int skinPrice;
    private String skinRarity;
  
    //Constructor for the Skin class
    public Skin(String name, int price, double fireRate, int magSize, String skinName, int skinPrice, String skinRarity) {
      super(name, price, fireRate, magSize);
      this.skinName = skinName;
      this.skinPrice = skinPrice;
      this.skinRarity = skinRarity;
    }
    public Skin() {
      super();
      this.skinName = "none";
      this.skinPrice = 0;
      this.skinRarity = "none";
    }
    public Skin(String skinName, int skinPrice, String skinRarity) {
      super();
      this.skinName = skinName;
      this.skinPrice = skinPrice;
      this.skinRarity = skinRarity;
    }
  
    //Setters
    public void setSkinName(String skinName) {
      this.skinName = skinName;
    }
    public void setSkinPrice(int skinPrice) {
      this.skinPrice = skinPrice;
    }
    public void setSkinRarity(String skinRarity) {
      this.skinRarity = skinRarity;
    }
  
    //Getters
    public String getSkinName() {
      return skinName;
    }
    public int getSkinPrice() {
      return skinPrice;
    }
    public String getSkinRarity() {
      return skinRarity;
    }
    public String getWeaponName() {
      return super.getName();
    }
  
    //toString method
    @Override
    public String toString() {
      return skinName + " " + super.getName() + " - " + skinPrice + " Creds - " + skinRarity + "";
    }
  }
  