package Controller;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;

import Model.Instruction;
import Utils.Arquive;

public class ArquiveController {
    private static Arquive arquive;

    public static void readArquive(String path) throws Exception {
        ArquiveController.arquive = new Arquive();
        try {
            File file = new File(path);
            Scanner scanner = new Scanner(file);
            while (scanner.hasNextLine()) {
                String[] line = scanner.nextLine().trim().split(" ");
                String[] options = line[1].split(",");
                if (line.length != 2 || options.length != 3) { 
                    //Pattern INS OP1,OP2,OP3
                    throw new Exception("Arquive is not in pattern"); 
                }
                if (!RegisterController.existRegisterName(options[0])) {
                    throw new Exception("Register not exist");
                }
                ArquiveController.arquive.addInstruction(new Instruction(line[0], options[0], options[1], options[2]));
            }
            scanner.close();
        } catch (IOException e) {
            e.printStackTrace();
            throw new Exception("Error in arquive");
        }
    }

    public static Instruction getFirstIntruction() throws Exception {
        return ArquiveController.arquive.get();
    }

    public static int getArquiveSize() {
        return ArquiveController.arquive.size();
    }
}
