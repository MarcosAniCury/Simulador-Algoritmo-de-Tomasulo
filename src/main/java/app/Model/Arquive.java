package app.Model;

import java.io.File;
import java.util.Scanner;

import java.util.ArrayList;
import java.util.List;

public class Arquive {
    List<Instruction> instructions;

    public Arquive(String path) {
        readArquive(path);
    }

    public void readArquive(String path) {
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
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public Instruction getOneInstruction(int index) throws Exception {
        if (index > instructions.size()) {
            throw new Exception("index not exist");
        }
        return instructions.get(index);
    }

    public Instruction[] getAllInstructions() {
        return instructions.toArray(new Instruction[0]);
    }
}
