package mermaidgenerator;

import com.github.javaparser.JavaParser;
import com.github.javaparser.ParserConfiguration;
import com.github.javaparser.ast.body.ClassOrInterfaceDeclaration;
import com.github.javaparser.ast.visitor.VoidVisitorAdapter;
import com.github.javaparser.utils.SourceRoot;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Optional;

public class MermaidDiagramGenerator {
    public static void main(String[] args) {
        String pathToSource = "src"; // modify with your source path
        String outputPath = "src/test/resources/project_mermaid_diagram.mmd";

        SourceRoot sourceRoot = new SourceRoot(Paths.get(pathToSource));
        ParserConfiguration configuration = new ParserConfiguration();
        JavaParser javaParser = new JavaParser(configuration);

        sourceRoot.tryToParseParallelized();

        StringBuilder mermaidSyntax = new StringBuilder("classDiagram\n");

        sourceRoot.getCompilationUnits().forEach(cu -> {
            cu.accept(new VoidVisitorAdapter<Void>() {
                @Override
                public void visit(ClassOrInterfaceDeclaration n, Void arg) {
                    super.visit(n, arg);
                    Optional<String> className = n.getNameAsString().describeConstable();
                    mermaidSyntax.append("class ").append(className.get()).append(" {\n");

                    // Adding fields
                    n.getFields().forEach(field -> {
                        mermaidSyntax.append("  ");
                        field.getVariables().forEach(variable -> {
                            mermaidSyntax.append(variable.getType().asString())
                                    .append(" ")
                                    .append(variable.getNameAsString());
                        });
                        mermaidSyntax.append("\n");
                    });

                    // Adding methods
                    n.getMethods().forEach(method -> {
                        mermaidSyntax.append("  ")
                                .append(method.getType().asString())
                                .append(" ")
                                .append(method.getNameAsString())
                                .append("(");
                        method.getParameters().forEach(param -> {
                            mermaidSyntax.append(param.getType().asString())
                                    .append(" ")
                                    .append(param.getNameAsString())
                                    .append(", ");
                        });
                        if (!method.getParameters().isEmpty()) {
                            mermaidSyntax.setLength(mermaidSyntax.length() - 2); // Remove trailing ", "
                        }
                        mermaidSyntax.append(")\n");
                    });

                    mermaidSyntax.append("}\n");

                    // Relations - inheritance
                    n.getExtendedTypes().forEach(ext -> {
                        mermaidSyntax.append(className.get())
                                .append(" <|-- ")
                                .append(ext.getNameAsString())
                                .append("\n");
                    });

                    // Relations - interfaces
                    n.getImplementedTypes().forEach(impl -> {
                        mermaidSyntax.append(className.get())
                                .append(" <|.. ")
                                .append(impl.getNameAsString())
                                .append("\n");
                    });
                }
            }, null);
        });

        // Save to file
        saveToFile(mermaidSyntax.toString(), outputPath);
    }

    private static void saveToFile(String data, String path) {
        Path file = Paths.get(path);
        try {
            Files.createDirectories(file.getParent()); // Ensure the directory exists
            if (Files.exists(file)) {
                System.out.println("File already exists. Overwriting...");
            }
            Files.write(file, data.getBytes());
            System.out.println("Mermaid diagram saved to: " + file);
        } catch (IOException e) {
            System.err.println("Failed to write to file: " + e.getMessage());
        }
    }
}