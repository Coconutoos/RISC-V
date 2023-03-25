package RiscVVirtualMachine.Model;

import java.io.*;

public class RiscV {
    private Control control;
    private final Registers registers;
    private final Mux aluMux, memoryMux, pcMux;
    private final And and;
    private final Adder pcAdder, immAdder;
    private final ALU alu;
    private final aluControl aluControl;
    private Buffer rs1, rs2, rd, opcode, curInstruction, funct7, funct3, memoryOut;
    private final InstructionMemory instructionMem;
    private final MainMemory mainMemory;
    private final ImmGen immGen;
    private PCRegister pc;

    public RiscV(){
        rs1 = new Buffer();
        rs2 = new Buffer();
        rd = new Buffer();
        pc = new PCRegister(null);
        opcode = new Buffer();
        curInstruction = new Buffer();
        funct7 = new Buffer();
        funct3 = new Buffer();

        control = new Control(opcode);

        immGen = new ImmGen(opcode, curInstruction);

        aluControl = new aluControl(curInstruction, control.getAluOp());

        mainMemory = new MainMemory(250, null, control.getMemRead(), control.getMemWrite(), null);
        memoryOut = mainMemory.getReadData();

        memoryMux = new Mux(null, mainMemory.getReadData(), control.getMemToReg());

        registers = new Registers(rs1, rs2, rd, memoryMux.getOut(), control.getRegWrite());
        mainMemory.setWriteData(registers.getOutRs2());

        aluMux = new Mux(registers.getOutRs2(), immGen.getImmOut(), control.getAluSrc());
        alu = new ALU(registers.getOutRs1(), aluMux.getOut(), aluControl.getControlOut());

        mainMemory.setAddress(alu.getAluRes());
        memoryMux.setIn1(alu.getAluRes());

        and = new And(control.getBranch(), alu.getZero());

        Buffer pcAdderBuffer = new Buffer();
        pcAdderBuffer.write(4);
        pcAdder = new Adder(pc.getOut(), pcAdderBuffer);
        immAdder = new Adder(pc.getOut(), immGen.getImmOut());

        pcMux = new Mux(pcAdder.getOut(), immAdder.getOut(), and.getOut());

        pc.setIn(pcMux.getOut());
        instructionMem = new InstructionMemory(250, pc.getOut(), 0);
        curInstruction = instructionMem.getReadData();
    }

    public boolean clock(){
        Buffer counter = pc.getOut();
        fetchInstruction();
        pcAdder.clock();
        parseInstruction();
        executeInstruction();
        immAdder.clock();
        and.clock();
        pcMux.clock();
        pc.clock();
        return ((counter.read()/4) < instructionMem.getMaxInstructions());
    }
    private void fetchInstruction(){
        instructionMem.clock();
    }

    private void executeInstruction(){
        control.clock();
        registers.clock();
        immGen.clock();
        aluMux.clock();
        aluControl.clock();
        alu.clock();
        mainMemory.clock();
        memoryMux.clock();
        registers.fallingClock();
    }

    private void parseInstruction(){
        int readInstruction = curInstruction.read();
        System.out.println(readInstruction);
        opcode.write((readInstruction) & 0x7F);
        rs1.write((readInstruction >> 15) & 0x1F);
        rs2.write((readInstruction >> 20) & 0x1F);
        rd.write((readInstruction >> 7) & 0x1F);
        funct3.write((readInstruction >> 11) & 0x7);
        funct7.write((readInstruction >> 24) & 0x7F);
    }

    public void loadInstructionFile(File file) throws IOException {
        FileReader fr = new FileReader(file);
        BufferedReader br = new BufferedReader(fr);
        int i = 0;
        String line;
        int[] buffer = new int[250];
        int high, low;
        while((line = br.readLine()) != null){
            high = Integer.parseInt(line.substring(0,2),2);
            low = Integer.parseInt(line.substring(1), 2);
            buffer[i] = (high << 31) | low;
            //System.out.println(buffer[i]);
            i++;
        }
        //System.out.println("\n");
        instructionMem.setMemory(buffer);
        instructionMem.setMaxInstructions(i);
    }

    public int[] getRegs(){
        return registers.getRegisters();
    }

    public int[] getMem(){
        return mainMemory.getMemory();
    }

    public int getPC() {
        Buffer aux = pc.getOut();
        return aux.read();
    }
}
