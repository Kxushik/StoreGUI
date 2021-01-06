//Class representing a single toaster oven product
public class ToasterOven extends Appliance{
  private int width;
  private boolean convection;
  
  public ToasterOven(double initPrice, int initQuantity, int initWattage, String initColor, String initBrand, int initWidth, boolean initConvection){
    super(initPrice, initQuantity, initWattage, initColor, initBrand);
    width = initWidth;
    convection = initConvection;
  }
  
  public String toString(){
    String result = width + " inch " + getBrand() + " Toaster ";
    if(convection){
      result += "with convection ";
    }
    
    result += "(" + getColor() + ", " + getWattage() +" watts)";
    
    return result;
  }
}