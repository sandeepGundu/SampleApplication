package orders;

import com.github.javaparser.JavaParser;
import com.github.javaparser.ast.CompilationUnit;
import com.github.javaparser.ast.body.ClassOrInterfaceDeclaration;
import com.github.javaparser.ast.visitor.VoidVisitorAdapter;

import java.io.FileInputStream;

public class CodeStructureParser {
    public static void main(String[] args) throws Exception {
        FileInputStream in = new FileInputStream("src");
        /*CompilationUnit cu = JavaParser.parse(in);

        cu.accept(new VoidVisitorAdapter<Void>() {
            @Override
            public void visit(ClassOrInterfaceDeclaration n, Void arg) {
                super.visit(n, arg);
                System.out.println("Class/Interface Name: " + n.getName());
                n.getMethods().forEach(method -> System.out.println("Method Name: " + method.getName()));
            }
        }, null);*/
    }
}