package orders;

import com.github.javaparser.JavaParser;
import com.github.javaparser.ParseResult;
import com.github.javaparser.ast.CompilationUnit;
import com.github.javaparser.ast.body.ClassOrInterfaceDeclaration;
import com.github.javaparser.ast.body.FieldDeclaration;
import com.github.javaparser.ast.visitor.VoidVisitorAdapter;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

public class JavaToMermaid {
    public static void main(String[] args) throws Exception {

        File srcDir = new File("src");
        List<File> javaFiles = new ArrayList<>();
        getAllJavaFiles(srcDir, javaFiles);

        StringBuilder mermaidDiagram = new StringBuilder("classDiagram\n");

        for (File javaFile : javaFiles) {
            try (FileInputStream in = new FileInputStream(javaFile)) {
                JavaParser parser = new JavaParser();
                ParseResult<CompilationUnit> result = parser.parse(in);

                if (result.isSuccessful() && result.getResult().isPresent()) {
                    CompilationUnit cu = result.getResult().get();
                    cu.accept(new VoidVisitorAdapter<Void>() {
                        @Override
                        public void visit(ClassOrInterfaceDeclaration n, Void arg) {
                            super.visit(n, arg);
                            mermaidDiagram.append("class ").append(n.getName()).append(" {\n");

                            n.getMethods().forEach(method -> mermaidDiagram.append("  +").append(method.getDeclarationAsString(true, false, false)).append("\n"));
                            n.getFields().forEach(field -> mermaidDiagram.append("  -").append(field.getVariables().get(0).getName()).append(" : ").append(field.getElementType()).append("\n"));

                            mermaidDiagram.append("}\n");

                            n.getExtendedTypes().forEach(ext -> mermaidDiagram.append(n.getName()).append(" --|> ").append(ext.getName()).append("\n"));
                            n.getImplementedTypes().forEach(impl -> mermaidDiagram.append(n.getName()).append(" ..|> ").append(impl.getName()).append("\n"));
                        }
                    }, null);
                }
            } catch (FileNotFoundException e) {
                System.err.println("The file was not found: " + e.getMessage());
            } catch (Exception e) {
                System.err.println("An error occurred: " + e.getMessage());
            }
        }

        System.out.println(mermaidDiagram.toString());

        /*FileInputStream in = null;
        try {
            in = new FileInputStream("src");
            JavaParser parser = new JavaParser();
            ParseResult<CompilationUnit> result = parser.parse(in);

            if (result.isSuccessful() && result.getResult().isPresent()) {
                CompilationUnit cu = result.getResult().get();
                StringBuilder mermaidDiagram = new StringBuilder("classDiagram\n");

                cu.accept(new VoidVisitorAdapter<Void>() {
                    @Override
                    public void visit(ClassOrInterfaceDeclaration n, Void arg) {
                        super.visit(n, arg);
                        mermaidDiagram.append("class ").append(n.getName()).append(" {\n");

                        n.getMethods().forEach(method -> mermaidDiagram.append("  +").append(method.getDeclarationAsString(true, false, false)).append("\n"));
                        n.getFields().forEach(field -> mermaidDiagram.append("  -").append(field.getVariables().get(0).getName()).append(" : ").append(field.getElementType()).append("\n"));

                        mermaidDiagram.append("}\n");

                        n.getExtendedTypes().forEach(ext -> mermaidDiagram.append(n.getName()).append(" --|> ").append(ext.getName()).append("\n"));
                        n.getImplementedTypes().forEach(impl -> mermaidDiagram.append(n.getName()).append(" ..|> ").append(impl.getName()).append("\n"));
                    }
                }, null);

                System.out.println(mermaidDiagram.toString());
            } else {
                throw new RuntimeException("Parsing failed. Errors: " + result.getProblems());
            }
        } catch (FileNotFoundException e) {
            System.err.println("The file was not found: " + e.getMessage());
        } catch (Exception e) {
            System.err.println("An error occurred: " + e.getMessage());
        } finally {
            if (in != null) {
                try {
                    in.close();
                } catch (Exception e) {
                    System.err.println("Failed to close the input stream: " + e.getMessage());
                }
            }
        }*/

        /*FileInputStream in = new FileInputStream("src");
        // Create an instance of JavaParser
        JavaParser parser = new JavaParser();

        // Use the instance to parse the file
        CompilationUnit cu = parser.parse(in).getResult().orElseThrow(() -> new RuntimeException("Unable to parse the file."));

        StringBuilder mermaidDiagram = new StringBuilder("classDiagram\n");

        cu.accept(new VoidVisitorAdapter<Void>() {
            @Override
            public void visit(ClassOrInterfaceDeclaration n, Void arg) {
                super.visit(n, arg);
                // Handle class or interface name
                mermaidDiagram.append("class ").append(n.getName()).append(" {\n");

                // Handle methods
                n.getMethods().forEach(method -> mermaidDiagram.append("  +").append(method.getDeclarationAsString(true, false, false)).append("\n"));

                // Handle fields
                n.getFields().forEach(field -> mermaidDiagram.append("  -").append(field.getVariables().get(0).getName()).append(" : ").append(field.getElementType()).append("\n"));

                mermaidDiagram.append("}\n");

                // Handle relationships (e.g., extends)
                n.getExtendedTypes().forEach(ext -> mermaidDiagram.append(n.getName()).append(" --|> ").append(ext.getName()).append("\n"));
                n.getImplementedTypes().forEach(impl -> mermaidDiagram.append(n.getName()).append(" ..|> ").append(impl.getName()).append("\n"));
            }
        }, null);

        System.out.println(mermaidDiagram.toString());*/
    }

    private static void getAllJavaFiles(File dir, List<File> javaFiles) {
        File[] files = dir.listFiles();
        if (files != null) {
            for (File file : files) {
                if (file.isDirectory()) {
                    getAllJavaFiles(file, javaFiles);
                } else if (file.getName().endsWith(".java")) {
                    javaFiles.add(file);
                }
            }
        }
    }
}