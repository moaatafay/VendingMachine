package model;
public enum Coins {

    HALF_DIRHAM(0.5,"half dirham"),
    ONE_DIRHAM(1,"one dirham"),
    TWO_DIRHAM(2,"two dirham"),
    FIVE_DIRHAM(5,"five dirham"),
    TEN_DIRHAM(10,"ten dirham");


    private double value;
    private String libelle;

    private Coins(double value,String libelle){
        this.value=value;
        this.libelle=libelle;

    }

    public double getValue() {
        return value;
    }

    public String getLibelle() {
        return libelle;
    }
    


    
}
