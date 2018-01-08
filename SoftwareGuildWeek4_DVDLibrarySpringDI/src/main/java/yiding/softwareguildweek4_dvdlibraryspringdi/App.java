/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package yiding.softwareguildweek4_dvdlibraryspringdi;

import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 *
 * @author yidingweng
 */
public class App {
    public static void main(String[] args)  throws Exception{
        
        //DVDCollectionController controller = new DVDCollectionController();
        //controller.execute();
        
        ApplicationContext ctx = new ClassPathXmlApplicationContext("applicationContext.xml");
        DVDCollectionController controller = ctx.getBean("controller",DVDCollectionController.class);
        controller.execute();
    }
}
