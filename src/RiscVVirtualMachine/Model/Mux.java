package RiscVVirtualMachine.Model;

public class Mux {
    private Buffer in1, in2, out;
    private ControlFlag key;
    Mux(Buffer in1, Buffer in2, ControlFlag key){
        this.in1 = in1;
        this.in2 = in2;
        this.key = key;
        out = new Buffer();
    }
    public Buffer getOut(){
        return this.out;
    }
    public void clock(){
        if(!key.read()){
            out.write(in1.read());
        }
        else{
            out.write(in2.read());
        }
    }

    public void setIn1(Buffer in1){
        this.in1 = in1;
    }
}
