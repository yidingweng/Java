/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.math.BigDecimal;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author yidingweng
 */
public class InventoryServiceTest {
    
    InventoryService service = new InventoryService();
    
    public InventoryServiceTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of requestInventoryInfo method, of class InventoryService.
     */
    @Test
    public void testRequestInventoryInfo() throws Exception {
    }

    /**
     * Test of authenticatePurchase method, of class InventoryService.
     */
    @Test
    public void testAuthenticatePurchase() throws Exception {
    }

    /**
     * Test of returnCash method, of class InventoryService.
     */
    @Test
    public void testReturnCash() {
        service.returnCash("Coke", 0.96);
        BigDecimal three = new BigDecimal("3");
        BigDecimal two = new BigDecimal("2");
        BigDecimal one = new BigDecimal("1");
        BigDecimal zero = new BigDecimal("0");
        assertEquals(service.getQuarters(), three);
        assertEquals(service.getDimes(), two);
        assertEquals(service.getNickels(), zero);
        assertEquals(service.getPennies(), one);
        
        BigDecimal seven = new BigDecimal("7");
        BigDecimal four = new BigDecimal("4");
        service.returnCash("water", 1.89);
        assertEquals(service.getQuarters(), seven);
        assertEquals(service.getDimes(), one);
        assertEquals(service.getNickels(), zero);
        assertEquals(service.getPennies(), four);
        
    }
    
    /**
     * Test of calculate method, of class InventoryService.
     */
    @Test
    public void testCalculate() {
    }
    
}
