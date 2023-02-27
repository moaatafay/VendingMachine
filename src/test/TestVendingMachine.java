package test;
import static org.junit.Assert.assertThrows;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

import org.junit.*;
import exceptions.*;
import model.Product;
import model.VendingMachine;

public class TestVendingMachine {


    private VendingMachine machine;
    
    @Before
    public void setUp() {
        machine = new VendingMachine();
        machine.reset();
    }
    
    //InsertCoinTest
    @Test
    public void givenValidCoin_whenInsertCoin_thenSucceed() throws InvalidCoin {
        assertEquals("Balance: 0.5 MAD", machine.insertCoin(0.5));
    }
    
    @Test
    public void givenInvalidCoin_whenInsertCoin_thenThrowInvalidCoinException() throws InvalidCoin {
        assertThrows(InvalidCoin.class, ()->{machine.insertCoin(20);}); 
    }

    //GetProductByButtonNumTest
    @Test
    public void givenValidButtonNum_whenGetProductBybuttonNum_thenSucceed() throws InvalidButtonNum  {
   
        Product product=new Product(1,"Mirendina", 5,10);
        assertEquals( product.getName(),machine.getProductBybuttonNum(1).getName());   
        assertEquals( product.getPrice(),machine.getProductBybuttonNum(1).getPrice());  
        assertEquals( product.getButtonNum(),machine.getProductBybuttonNum(1).getButtonNum());  
        assertEquals( product.getQuantity(),machine.getProductBybuttonNum(1).getQuantity());  
    }
    @Test
    public void givenvalidButtonNum_whenGetProductBybuttonNum_thenThrowInvalidButtonNumException() throws InvalidButtonNum {
        assertThrows(InvalidButtonNum.class, ()->{machine.getProductBybuttonNum(20);}); 
    }


    //checkProductQuantityTest
    @Test
    public void givenvalidButtonNum_whenCheckProductQuantity_thenSucceed() throws InvalidButtonNum {
        assertTrue(machine.checkProductQuantity(1));
    }

    @Test
    public void givenInvalidButtonNum_whenCheckProductQuantity_thenNotSucceed() throws InvalidButtonNum {
        assertFalse(machine.checkProductQuantity(5));
    }

    //refundTest
    @Test
    public void testCancelRequest() throws InvalidCoin {

        machine.insertCoin(10);
        machine.cancelRequest();
        assertEquals(0, machine.getBalance(), 0);
    }

    //selectProductTest
    @Test
    public void givenValidButtonNum_whenSelectProduct_thenSucceed() throws InvalidProduct, InsufficientBalance, InvalidCoin, InvalidButtonNum {
        machine.insertCoin(10);
        String expectedOutput = "Dispensing Mirendina Change:5.0 MAD";
        assertEquals(expectedOutput, machine.selectProduct(1));
    }

    @Test
    public void givenInsufficientBalance_whenSelectProduct_thenThrowInsufficientBalanceException() throws InvalidProduct, InsufficientBalance, InvalidCoin, InvalidButtonNum {
        machine.insertCoin(10);
        assertThrows(InsufficientBalance.class, ()->{machine.selectProduct(4);}); 
    }
    @Test
    public void givenInvalidButtonNum_whenSelectProduct_thenThrowInvalidButtonNumException() throws InvalidProduct, InsufficientBalance, InvalidCoin, InvalidButtonNum {
        machine.insertCoin(10);
        assertThrows(InvalidButtonNum.class, ()->{machine.selectProduct(20);}); 
    }
    @Test
    public void givenInvalidProduct_whenSelectProduct_thenThrowInvalidProductException() throws InvalidProduct, InsufficientBalance, InvalidCoin, InvalidButtonNum {
        machine.insertCoin(10);
        assertThrows(InvalidProduct.class, ()->{machine.selectProduct(5);}); 
    }













//////////////////////////////////////////////
    



    
}
