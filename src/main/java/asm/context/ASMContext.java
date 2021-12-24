package asm.context;

import asm.ofi.EvilObjectFactory;

public class ASMContext {
    private EvilObjectFactory of;

    public ASMContext(EvilObjectFactory of) {
        this.of = of;
    }

    public byte[] executeOF() {
        return this.of.getObjectFactory();
    }
}
