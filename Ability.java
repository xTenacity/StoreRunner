
public class Ability extends Weapon {
    //Attributes of the Skin class
    private String abilityAgent;
    private String abilityType;
    private String abilityData;
  
    //Constructor for the Skin class
    public Ability(String name, int price, double fireRate, int magSize, String abilityAgent, String abilityType, String abilityData) {
      super(name, price, fireRate, magSize);
      this.abilityAgent = abilityAgent;
      this.abilityType = abilityType;
      this.abilityData = abilityData;
    }
    public Ability() {
      super();
      this.abilityAgent = "AGENT";
      this.abilityType = "ability";
      this.abilityData = "This is an ability.";
    }
    public Ability(String abilityAgent, String abilityType, String abilityData) {
      super();
      this.abilityAgent = abilityAgent;
      this.abilityType = abilityType;
      this.abilityData = abilityData;
    }
  
    //Setters
    public void setAbilityAgent(String abilityAgent) {
        this.abilityAgent = abilityAgent;
      }
    public void setAbilityType(String abilityType) {
        this.abilityType = abilityType;
    }
    public void setAbilityData(String abilityData) {
        this.abilityData = abilityData;
    }

    //Getters
    public String getAbilityAgent() {
      return abilityAgent;
    }
    public String getAbilityType() {
      return abilityType;
    }
    public String getAbilityData() {
      return abilityData;
    }

    //toString method
    @Override
    public String toString() {
      if (abilityType.equals("Ability")) {
        return super.getName() + " - " + 
        super.getMagSize() + " x $" + 
        super.getPrice() + ", " + 
        abilityData;
      } else {
        return "<" + 
        super.getName() + "> - " + 
        super.getPrice() + " Ultpoints, " + 
        abilityData;
      }
    }
  }
  