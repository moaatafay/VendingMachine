package model;


public class Product {
    private int buttonNum;
    private String name;
    private int price;
    private int quantity;


 

    // @Override
    // public boolean equals(Object o){


    //     if(o.na==this.name)

    //     return true;
    //     else
    //     return false;
    // }
   

    public Product(int buttonNum,  String name, int price, int quantity) {
        this.buttonNum=buttonNum;
        this.name = name;
        this.price = price;
        this.quantity = quantity;
    }

    public int getButtonNum() {
        return buttonNum;
    }
 
    public String getName() {
        return name;
    }

    public int getPrice() {
        return price;
    }
    
    public int getQuantity() {
        return quantity;
    }

    

 

   public void setButtonNum(int buttonNum) {
        this.buttonNum = buttonNum;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }


   

    

    
}
