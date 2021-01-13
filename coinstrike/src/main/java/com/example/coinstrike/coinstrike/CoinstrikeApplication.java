package com.example.coinstrike.coinstrike;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import com.example.coinstrike.coinstrike.controllers.MatchController;

import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class CoinstrikeApplication {

	public static void main(String[] args) throws Exception{
		// File myObj = new File("./src/main/java/com/example/coinstrike/coinstrike/tests");
		File myObj = new File("./src/main/tests");
		
      	File files[] = myObj.listFiles();
      	for(File file: files){
			MatchController match = new MatchController();
			System.out.println("\n\nReading Inputs From:"+file.getName());
			try{
				Scanner myReader = new Scanner(file);
				while (myReader.hasNextLine()) {
					String data = myReader.nextLine();
					if(match.validateMoveAndPlay(data) == false){
					break;
					}
				}
				myReader.close();
			} catch (FileNotFoundException e) {
				System.out.println("An error occurred.");
				e.printStackTrace();
			}
			System.out.println("Reading Completed for:"+file.getName()+" \n\n");
		}   
	}
}
