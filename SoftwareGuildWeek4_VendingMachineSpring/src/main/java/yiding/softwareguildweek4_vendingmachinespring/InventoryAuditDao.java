/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package yiding.softwareguildweek4_vendingmachinespring;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDateTime;

/**
 *
 * @author yidingweng
 */
public class InventoryAuditDao {
    public static final String AUDIT_FILE = "audit.txt";

    public void writeAuditEntry(String entry) throws VendingMachinePersistenceException {
        
        PrintWriter out;
        
        try {
            out = new PrintWriter(new FileWriter(AUDIT_FILE,true));
        } catch (IOException ex) {
            throw new VendingMachinePersistenceException("Could not persist audit information", ex);
        }
        
        LocalDateTime timestamp = LocalDateTime.now();
        out.println(timestamp.toString() + " : "+ entry);
        out.flush();
    }
    
}
