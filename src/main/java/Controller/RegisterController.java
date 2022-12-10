package Controller;

import Constants.Definitions;
import Model.Register;

public class RegisterController {
    private static Register[] registers;

    public static void defineRegisters() {
        RegisterController.registers = new Register[Definitions.N_REGISTER];
        char baseName = 'r';
        for (int i = 0;i < Definitions.N_REGISTER;i++) {
            String name = baseName + (i + "");
            RegisterController.registers[i] = new Register(name);
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
}
