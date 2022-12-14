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
                String[] arquiveLine = scanner.nextLine().trim().split(":");
                String line = "";
                String jumpTag = null;
                //Verify jump instruction
                if (arquiveLine.length == 1) {
                    line = arquiveLine[0];
                } else {
                    jumpTag = arquiveLine[0].trim();
                    line = arquiveLine[1];
                }

                String[] lines = line.trim().split(" ");
                String[] options = lines[1].split(",");
                if (lines.length != 2 || options.length != 3) { 
                    //Pattern INS OP1,OP2,OP3
                    throw new Exception("Arquive is not in pattern"); 
                }
                if (!RegisterController.existRegisterName(options[0])) {
                    throw new Exception("Register not exist");
                }
                ArquiveController.arquive.addInstruction(new Instruction(lines[0], options[0], options[1], options[2], jumpTag));
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
