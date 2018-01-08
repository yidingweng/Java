/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.util.HashMap;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;


import java.util.Set;
import java.util.Collection;
import java.util.Scanner;

/**
 *
 * @author yidingweng
 */
public class InventoryDaoTest {
    
    //private InventoryDao dao = new InventoryDao();
    
    public InventoryDaoTest() {
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
     * Test of isSoldOut method, of class InventoryDao.
     */
    @Test
    public void testIsSoldOut() throws Exception {
        InventoryDao dao = new InventoryDao();
        dao.LoadInventoryfromFileTest();
        assertFalse(dao.isSoldOut("Coke"));
        
    }
/*
    /**
     * Test of sufficientFunds method, of class InventoryDao.
     */
    @Test
    public void testSufficientFunds() throws Exception {
        InventoryDao dao = new InventoryDao();
        dao.LoadInventoryfromFileTest();
        assertTrue(dao.sufficientFunds(2, 1.5));
        assertTrue(dao.sufficientFunds(2, 2));
    }

    /**
     * Test of getSnackPrice method, of class InventoryDao.
     */
    @Test
    public void testGetSnackPrice() throws Exception{
        InventoryDao dao = new InventoryDao();
        dao.LoadInventoryfromFileTest();
        assertTrue(1.70 == dao.getSnackPrice("water"));
        assertFalse(1.75 == dao.getSnackPrice("water"));
        assertTrue(2.04 == dao.getSnackPrice("Coke"));
    }

    /**
     * Test of changeInventory method, of class InventoryDao.
     */
    @Test
    public void testChangeInventory() throws Exception {
        InventoryDao dao = new InventoryDao();
        Snacks water = new Snacks();
        
        water.setAmount(2);
        water.setPrice(1.75);
        water.setName("water");
        
        dao.vendingMachine.put(water.getName(),water);
        dao.changeInventory("water");
        assertEquals(1,water.getAmount());
    }

    /**
     * Test of listInventory method, of class InventoryDao.
     */
    @Test
    public void testListInventory() throws Exception {
    }

    /**
     * Test of printSnackInfo method, of class InventoryDao.
     */
    @Test
    public void testPrintSnackInfo() {
    }
   
}
