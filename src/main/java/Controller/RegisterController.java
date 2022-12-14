package Controller;

import java.util.Random;
import Constants.Definitions;
import Model.Register;

public class RegisterController {
    public static Register[] registers;

    public static void defineRegisters() {
        Random rand = new Random();
        RegisterController.registers = new Register[Definitions.N_REGISTER];
        char baseName = 'r';
        for (int i = 0;i < Definitions.N_REGISTER;i++) {
            String name = baseName + (i + "");
            RegisterController.registers[i] = new Register(name, rand.nextInt(100));
        }
    }

    public static boolean existRegisterName(String name) {
        for(Register register : RegisterController.registers) {
            if (register.getName().equals(name)) {
                return true;
            }
        }
        return false;
    }

    public static Register findRegisterBasedInName(String name) throws Exception {
        for (Register register : RegisterController.registers) {
            if (register.getName().equals(name)) {
                return register;
            }
        }
        throw new Exception("Register asked not exist");
    }

}
