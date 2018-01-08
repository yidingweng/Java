/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package yiding.softwareguildweek4_vendingmachinespring;

import org.aspectj.lang.JoinPoint;

/**
 *
 * @author yidingweng
 */
public class LoggingAdvice {
    
    InventoryAuditDao auditDao;
    
    // use depandence inject to get audtiDao
    public LoggingAdvice(InventoryAuditDao auditDao){
        this.auditDao = auditDao;
    }
     
    // this is to grab the name of the method that i'm auditing
    public void createAuditEntryIsSoldOut(JoinPoint jp, Exception exception) throws InventoryService.NoItemInventoryException, VendingMachinePersistenceException{
        //build string for AuditEntry
        //first part, only the name, which is the signiture, and add :
        Object[] args = jp.getArgs();
        String auditEntry = jp.getSignature().getName() + ": " + exception;
        //cycle through the argument, before that we need toString method
        for (Object currentArg : args) {
            auditEntry += currentArg;
        }
        // in the try, let people know what happen, 
        try {
            auditDao.writeAuditEntry(auditEntry);
        } catch (VendingMachinePersistenceException e) {
            System.err.println(
               "ERROR: Could not create audit entry in LoggingAdvice.");
            //print to err, not out, very very very very very very very very very very very very important
        // the whole reason is to not log in make the system crash.
        //??????? he says he didn't want to throw this exception, so he can print to err?
        }
    }
    
    public void createAuditEntrySufficientFunds(JoinPoint jp, Exception exception) throws InventoryService.InsufficientFundsException, VendingMachinePersistenceException{
        //build string for AuditEntry
        //first part, only the name, which is the signiture, and add :
        Object[] args = jp.getArgs();
        String auditEntry = jp.getSignature().getName() + ":"+ exception;
        //cycle through the argument, before that we need toString method
        for (Object currentArg : args) {
            auditEntry += currentArg;
        }
        // in the try, let people know what happen, 
        try {
            auditDao.writeAuditEntry(auditEntry);
        } catch (VendingMachinePersistenceException e) {
            System.err.println(
               "ERROR: Could not create audit entry in LoggingAdvice.");
            //print to err, not out, very very very very very very very very very very very very important
        // the whole reason is to not log in make the system crash.
        //??????? he says he didn't want to throw this exception, so he can print to err?
        }
    }
    
}
