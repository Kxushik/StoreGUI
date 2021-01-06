//Class representing a single type of Fridge
public class Fridge extends Appliance{
  double cubicFeet;
  boolean hasFreezer;
  
  public Fridge(double initPrice, int initQuantity, int initWattage, String initColor, String initBrand, double initFeet, boolean initFreezer){
    super(initPrice, initQuantity, initWattage, initColor, initBrand);
    cubicFeet = initFeet;
    hasFreezer = initFreezer;
  }
  
  public String toString(){
    String result = cubicFeet + " cu. ft. " + getBrand() + " Fridge ";
    if(hasFreezer){
      result += "with Freezer ";
    }
    
    result += "(" + getColor() + ", " + getWattage() +" watts)";
    
    return result;
  }
  
  
}