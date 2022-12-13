package Controller;

import Model.ReorderBuffer;

public class ReorderBufferController {
    public static ReorderBuffer reorderBuffer;

    public static void startReorderBuffer() {
        ReorderBufferController.reorderBuffer = new ReorderBuffer();
    }
}
