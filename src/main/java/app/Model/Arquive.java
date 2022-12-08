package main.java.app.Model;

import java.io.File;
import java.util.Scanner;

public class Arquive {
    Instruction[] instructions;

    public Arquive(String path) {
        System.out.println("arquiveController entrou");
        readArquive(path);
    }

    public void readArquive(String path) {
        try {
            File file = new File(path);
            Scanner scanner = new Scanner(file);
            while (scanner.hasNextLine()) {
                String[] line = scanner.nextLine().split(" ");
                if (line.length != 4) { 
                    throw new Exception("Arquive is not in pattern"); 
                }
                instructions[instructions.length] = new Instruction(line[0], line[1], line[2], line[3]);
            }
            scanner.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public Instruction getOneInstruction(int index) throws Exception {
        if (index > instructions.length) {
            throw new Exception("index not exist");
        }
        return instructions[index];
    }

    public Instruction[] getAllInstructions() {
        return instructions;
    }
}
