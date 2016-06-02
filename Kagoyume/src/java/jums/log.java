/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jums;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Date;

/**
 *
 * @author Jumpei
 */
public class log {
    
    public static log getInstance() {
        return new log();
    }

    public void outlog(String log) throws IOException {
        File Ptxt = new File("C:\\Users\\Jumpei\\Documents\\NetBeansProjects\\Kagoyume\\web\\WEB-INF\\log.txt");
        FileWriter Pfwa = new FileWriter(Ptxt, true);
        Pfwa.write(log);
        Pfwa.write(" ");
        Date date = new Date();
        Pfwa.write(date.toString());
        Pfwa.write("<br>");
        Pfwa.close();
    }
}
