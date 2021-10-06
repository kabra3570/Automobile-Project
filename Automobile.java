/* File: Automobile.java
 * Author: Kevin Abrahams
 * Date: 20-03-2020
 * Purpose: Use inheritance to create a hierarchy of classes storing automobile information. 
 */
public class Automobile {
    private String make, model; // make and model of automobile (parent class)
    private double purchasePrice; // purchase or sales price of automobile
    
    // constructor of the Automobile class (superclass constructor)
    public Automobile(String make, String model, double purchasePrice) {
        // initializing data members (instance variables)
        this.make = make;
        this.model = model;
        this.purchasePrice = purchasePrice;
    }
    
    // return computed sales tax (5 percent of purchase/sales price)
    public double salesTax() {
        double salesTax = purchasePrice * 0.05;
        return salesTax;
    }
    
    // return String representation of the automobile object's instance variables
    public String toString() {
        return "\nMake and Model: " + make + " " + model + "\nSales Price: " + purchasePrice + "\nSales Tax: " + this.salesTax();
    }
}

// subclass of Automobile parent class 
class Electric extends Automobile {
    // one new instance variable added onto those inherited from parent class
    private int weight; 
    public Electric(String make, String model, double purchasePrice, int weight) {
        // calling super class's constructor to initialize inherited instance variables
        super(make, model, purchasePrice);
        
        // initializing instance variable unique to subclass
        this.weight = weight;
    }
    
    // overriding the parent class's salesTax() method 
    public double salesTax() {
        // calling super class's salesTax() method to obtain regular salesTax
        double salesTax = super.salesTax(); 
        
        // salesTax reduced by 200 if the weight is less than 3000 (otherwise, reduced by 150)
        if (weight < 3000)
            salesTax -= 200;
        else
            salesTax -= 150;
        return salesTax;
    }
    
    // return String representation of Electric object (adding on to the super class's toString() method)
    public String toString() {
        return super.toString() + "\nElectric Vehicle" + "\nWeight: " + weight;
    }
}

// second subclass of Automobile parent class
class Hybrid extends Automobile {
    // miles per gallon
    private int mpg;
    
    // constructor for Hybrid class (initializes instance variables)
    public Hybrid(String make, String model, double purchasePrice, int mpg) {
        super(make, model, purchasePrice);
        this.mpg = mpg;
    }
    
    // overriding the parent class's salesTax() method 
    public double salesTax() {
        // obtain regular salesTax through parent class's salesTax() method
        double salesTax = super.salesTax();
        // salesTax reduced by 100 if mpg less than 40 (otherwise, also reduced by $2 for every mpg over 40)
        if (mpg < 40)
            salesTax -= 100;
        else {
            salesTax -= (2 * (mpg - 40)) + 100;
        }
        return salesTax;
    }
    
    // return String representation of Hybrid object (adds on to parent class's toString() method)
    public String toString() {
        return super.toString() + "\nHybrid Vehicle" + "\nMPG: " + mpg;
    }
}
