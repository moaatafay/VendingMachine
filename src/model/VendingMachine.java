package model;
import java.util.*;

import exceptions.InsufficientBalance;
import exceptions.InvalidButtonNum;
import exceptions.InvalidCoin;
import exceptions.InvalidProduct;

public class VendingMachine  {

    
    public HashSet<Product> products;
    public HashMap<String,Integer> coins;
    private double balance;
    private double totalMoney=0;
    

    public VendingMachine() {
        coins=new HashMap<>();
        coins.put(Coins.HALF_DIRHAM.getLibelle(),0);
        coins.put(Coins.ONE_DIRHAM.getLibelle(),0);
        coins.put(Coins.TWO_DIRHAM.getLibelle(),0);
        coins.put(Coins.FIVE_DIRHAM.getLibelle(),0);
        coins.put(Coins.TEN_DIRHAM.getLibelle(),0);
        products=new HashSet<>();
        balance = 0;
    }
    

    
    public double getTotalMoney() {
        return totalMoney;
    }
    public String coinValueToCoinName(double coin){

        if(coin==Coins.HALF_DIRHAM.getValue())
            return Coins.HALF_DIRHAM.getLibelle();
        if(coin==Coins.ONE_DIRHAM.getValue())
            return Coins.ONE_DIRHAM.getLibelle();
        if(coin==Coins.TWO_DIRHAM.getValue())
            return Coins.TWO_DIRHAM.getLibelle();
        if(coin==Coins.FIVE_DIRHAM.getValue())
            return Coins.FIVE_DIRHAM.getLibelle();
        if(coin==Coins.TEN_DIRHAM.getValue())
            return Coins.TEN_DIRHAM.getLibelle();

        return "";






    }



    public double getBalance() {
        return balance;
    }



    public void reset() {
        products.clear();
        Product mirendina=new Product(1,"Mirendina", 5,10);
        Product tango=new Product(2,"Tango", 3,10);
        Product kitkat=new Product(3,"KitKat", 13,10);
        Product snickers=new Product(4,"Snickers", 15,10);
        Product milka=new Product(5,"Milka", 26,0);
        products.add(mirendina);
        products.add(tango);
        products.add(kitkat);
        products.add(snickers);
        products.add(milka);
        balance = 0;
    }


    public void displayMenu() {
        System.out.println("Available products:");
        for (Product produit : products) {
            System.out.println(produit.getName()+" : "+produit.getPrice()+" MAD la quantité est: "+produit.getQuantity());
          }
    }

    public String selectProduct(int buttonNum) throws InvalidProduct , InsufficientBalance, InvalidButtonNum{

        Product produit=getProductBybuttonNum(buttonNum);
        if (!checkProductQuantity(buttonNum)) {
            throw new InvalidProduct("Invalid product selection.");
        }
        double price = produit.getPrice();
        if (price > balance) {
            throw new InsufficientBalance("Insufficient balance. Please insert more coins.");
        }
        double change = balance - price;
         rest(change);
        totalMoney=totalMoney+price;
        quantityDecrement(buttonNum);
        balance = 0;
        return "Dispensing " + produit.getName()+" Change:" + change +" MAD" ;        
    }



    public void quantityDecrement(int buttonNum){
        for (Product product : products) {
            if(product.getButtonNum()==buttonNum){
                    product.setQuantity(product.getQuantity()-1);
                    break;}
           }
    }

    public void rest(double change){
       
        int naturelPart=(int) change;
        double fractionalPart = change - naturelPart;
        
        int value=(int) (change/Coins.TEN_DIRHAM.getValue());
        if(value!=0){
           
            decreaseMoneyPiece(Coins.TEN_DIRHAM.getValue(),value);
            // result+=value+" : "+Coins.TEN_DIRHAM.getLibelle()+"  ";
            value=(int) (change-value*Coins.TEN_DIRHAM.getValue());
            change=value;
        }
         value=(int) (change/Coins.FIVE_DIRHAM.getValue());
        if(value!=0){
            //result+=value+" : "+Coins.FIVE_DIRHAM.getLibelle()+"  ";
            decreaseMoneyPiece(Coins.FIVE_DIRHAM.getValue(),value);
            value=(int) (change-value*Coins.FIVE_DIRHAM.getValue());
            change=value;
        }
        value=(int) (change/Coins.TWO_DIRHAM.getValue());
        if(value!=0){
            //result+=value+" : "+Coins.TWO_DIRHAM.getLibelle()+"  ";
            decreaseMoneyPiece(Coins.TWO_DIRHAM.getValue(),value);
            value=(int) (change-value*Coins.TWO_DIRHAM.getValue());
            change=value;
        }
        value=(int) (change/Coins.ONE_DIRHAM.getValue());
        if(value!=0){
            decreaseMoneyPiece(Coins.ONE_DIRHAM.getValue(),value);
            //result+=value+" : "+Coins.ONE_DIRHAM.getLibelle()+"  ";
            value=(int) (change-value*Coins.ONE_DIRHAM.getValue());
            change=value;
        }
        // value=(int) (change/Coins.HALF_DIRHAM.getValue());
        if(fractionalPart!=0){
            decreaseMoneyPiece(Coins.HALF_DIRHAM.getValue(),1);
            //result+="1 : "+Coins.HALF_DIRHAM.getLibelle()+"  ";

        }
       
    }



    public  Product getProductBybuttonNum(int buttonNum) throws InvalidButtonNum {
        
        for (Product product : products) {
            if(product.getButtonNum()==buttonNum)
                    return product;
           }
            throw new InvalidButtonNum("Invalid Button");
       }

       public boolean checkProductQuantity(int buttonNum) throws InvalidButtonNum  {
            if(getProductBybuttonNum(buttonNum).getQuantity()>0)
                    return true;
            else
                   return false;
       }

       public void increaseMoneyPiece(double coin){

        String coinName=coinValueToCoinName(coin);
        for (String coinKey : coins.keySet()) {
            if(coinKey.compareTo(coinName)==0){

                coins.put(coinKey, coins.get(coinKey) + 1);
                break;
            }
            
          }
       }


       public void decreaseMoneyPiece(double coin,int count){

        String coinName=coinValueToCoinName(coin);
        for (String coinKey : coins.keySet()) {
            if(coinKey.compareTo(coinName)==0){

                coins.put(coinKey, coins.get(coinKey) -count);
                break;
            }
            
          }
       }





       public String insertCoin(double coin) throws InvalidCoin {

        if (coin == Coins.ONE_DIRHAM.getValue() || coin == Coins.TWO_DIRHAM.getValue() 
                        || coin == Coins.HALF_DIRHAM.getValue() || coin == Coins.FIVE_DIRHAM.getValue() 
                                        || coin == Coins.TEN_DIRHAM.getValue() ) {
            balance += coin;
            increaseMoneyPiece(coin);
            return "Balance: " + balance + " MAD";


        } else {
            throw new InvalidCoin("Invalid coin. Please insert a valid coin.");
        }
    }

        public String cancelRequest() {
            String result="Request cancelled. Returning balance: " + balance + " MAD";
            rest(balance);
            balance = 0;

            return result;
        }

    


    public static void main(String[] args) throws Exception {
        VendingMachine machine=new VendingMachine();
    
        machine.reset();
            System.out.println(machine.coins.toString());
            System.out.println(machine.insertCoin(10));
            System.out.println(machine.insertCoin(0.5));
            System.out.println(machine.insertCoin(1));
            System.out.println(machine.insertCoin(0.5));
            System.out.println(machine.coins.toString());
       

            
         System.out.println(machine.selectProduct(1));
         System.out.println(machine.coins.toString());
         
           
       

            
            

    }


    
}
