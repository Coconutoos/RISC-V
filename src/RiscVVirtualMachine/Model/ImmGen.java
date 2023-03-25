package RiscVVirtualMachine.Model;

public class ImmGen {
    private Buffer opcode, instruction, immOut;
    public ImmGen(Buffer opcode, Buffer instruction){
        this.opcode = opcode;
        this.instruction = instruction;
        immOut = new Buffer();
    }

    public Buffer getImmOut() {
        return immOut;
    }

    public void clock(){
        int readInstruction = instruction.read();
        switch(opcode.read()){
            case Control.I_OPCODE, Control.l_OPCODE -> {
                int aux = (readInstruction >> 20) & 0xFFF;
                if ((aux >> 11) == 1)
                    aux |= 0xFFFFF000;
                immOut.write(aux);
            }
            case Control.B_OPCODE -> {
                int aux = ((readInstruction >> 8) & 0xF) << 1;
                int aux2 = ((readInstruction >> 25) & 0x3F) << 5;
                aux |= aux2;
                aux2 = ((readInstruction >> 7) & 0x1) << 11;
                aux |= aux2;
                aux2 = ((readInstruction >> 31) & 0x1) << 12;
                aux |= aux2;
                if ((aux2 >> 12) == 1)
                    aux |= 0xFFFFE000;
                immOut.write(aux);

            }
            case Control.S_OPCODE -> {
                int aux = (readInstruction >> 7) & 0x1F;
                int aux2 = ((readInstruction >> 25) & 0x7F) << 5;
                aux |= aux2;
                // Adiciona 1s caso o bit de mais alta ordem seja 1
                if ((aux >> 11) == 1)
                    aux |= 0xFFFFF000;
                immOut.write(aux);
            }
            default -> immOut.write(0);
        }
    }
}
