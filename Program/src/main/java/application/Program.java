/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package application;

import entities.Item;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;

/**
 *
 * @author Victor
 */
public class Program {

    public static void main(String[] args) {
        
        Locale.setDefault(Locale.US);
        Scanner sc = new Scanner(System.in);
        
        List<Item> list = new ArrayList<>();        
        
        System.out.print("Enter file path: ");
        String sourceFileStr = sc.nextLine();
        
        File sourceFile = new File(sourceFileStr);
        String sourceFolderStr = sourceFile.getParent();
        
        boolean sucess = new File(sourceFolderStr + "\\out").mkdir();
        
        String targetFileStr = sourceFolderStr + "\\out\\sumary.csv";
        
        try (BufferedReader br = new BufferedReader(new FileReader(sourceFileStr))) {
            
            String line = br.readLine();
            
            while(line != null) {                
                String[] valores = line.split(",");
                
                list.add(new Item(valores[0], Double.parseDouble(valores[1]), Integer.parseInt(valores[2])));
                
                line = br.readLine();
            }

        } catch (IOException e) {            
            System.out.println("Error: " + e.getMessage());
        }
        
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(targetFileStr))) {
            
            for(Item item : list){
                String line = item.getName() + "," + item.totalValue();
                bw.write(line);
                bw.newLine();
            }
            
        } catch (IOException e) {
            System.out.println("Error: " + e.getMessage());
        }
        
        sc.close();
    }
}
