
import java.util.ArrayList;
public class Store {
  //Attributes of the Store class
  private ArrayList<Weapon> weapon;
  private ArrayList<Skin> skin;
  private ArrayList<Ability> ability;

  //Constructor for the Store class
  public Store(ArrayList<Weapon> weapon, ArrayList<Skin> skin) {
    this.weapon = weapon;
    this.skin = skin;
    this.ability = new ArrayList<>();
  }
  public Store() {
    this.weapon = new ArrayList<>();
    this.skin = new ArrayList<>();
    this.ability = new ArrayList<>();
  }

  //Setters
  public void setWeapon(ArrayList<Weapon> weapon) {
    this.weapon = weapon;
  }
  public void setSkin(ArrayList<Skin> skin) {
    this.skin = skin;
  }
  public void setAbility(ArrayList<Ability> ability) {
    this.ability = ability;
  }
  public void addWeapon(Weapon weapon) {
    this.weapon.add(weapon);
  }
  public void addSkin(Skin skin) {
    this.skin.add(skin);
  }
  public void addAbility(Ability ability) {
    this.ability.add(ability);
  }

  //Getters
  public ArrayList<Weapon> getWeapon() {
    return weapon;
  }
  public ArrayList<Skin> getSkin() {
    return skin;
  }
  public ArrayList<Ability> getAbility() {
    return ability;
  }

  //toString method
  @Override
  public String toString() {
    return "Weapon: " + weapon + "\nSkin: " + skin + "\nAbility: " + ability;
  }
}
