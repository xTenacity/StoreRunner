
public class Weapon {
    //Attributes of the Weapon class
    private String name;
    private int price;
    private double fireRate;
    private int magSize;
  
    //Constructor for the Weapon class
    public Weapon(String name, int price, double fireRate, int magSize) {
      this.name = name;
      this.price = price;
      this.fireRate = fireRate;
      this.magSize = magSize;
    }
    public Weapon() {
      this.name = "none";
      this.price = 0;
      this.fireRate = 0.0;
      this.magSize = 0;
    }
  
  
    //Getters
    public String getName() {
      return name;
    }
    public int getPrice() {
      return price;
    }
    public double getFireRate() {
      return fireRate;
    }
    public int getMagSize() {
      return magSize;
    }
  
    //Setters
    public void setName(String name) {
      this.name = name;
    }
    public void setPrice(int price) {
      this.price = price;
    }
    public void setFireRate(double fireRate) {
      this.fireRate = fireRate;
    }
    public void setMagSize(int magSize) {
      this.magSize = magSize;
    }
  
    //toString method
    @Override
    public String toString() {
      return(name + " - " + price + " Creds - " + fireRate + " rounds/sec - " + magSize + " Bullets per mag");
    }
  }
  
  