package app.Utils;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;

import app.Model.Instruction;

import java.util.ArrayList;
import java.util.List;

public class Arquive {
    private List<Instruction> instructions;

    public Arquive(String path) throws Exception {
        readArquive(path);
    }

    public void readArquive(String path) throws Exception {
        try {
            File file = new File(path);
            Scanner scanner = new Scanner(file);
            instructions = new ArrayList<Instruction>();
            while (scanner.hasNextLine()) {
                String[] line = scanner.nextLine().trim().split(" ");
                String[] options = line[1].split(",");
                if (line.length != 2 || options.length != 3) { 
                    //Pattern INS OP1,OP2,OP3
                    throw new Exception("Arquive is not in pattern"); 
                }
                instructions.add(new Instruction(line[0], options[0], options[1], options[2]));
            }
            scanner.close();
        } catch (IOException e) {
            e.printStackTrace();
            throw new Exception("Error in arquive");
        }
    }

    public Instruction get() throws Exception {
        return instructions.remove(0);
    }

    public Instruction[] getAllInstructions() {
        return instructions.toArray(new Instruction[0]);
    }
}
