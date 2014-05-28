package basic;

import hydra.CreateTempDir;
import hydra.ProjectGen;
import hydra.ZipExtractor;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;

public class Project {

    private Path source = null;
    private Path target = Paths.get("base/");
    private GeneratorOptions options;

    public Project(GeneratorOptions opts) {
        // Appending template/ because ZipExtractor checks for the template prefix, and considers items
        // in this directory the source files of the template which will processed by the project
        // generator to produce the Generated artifacts.
        this.source = extractFiles().resolve("template");
        this.target = Paths.get(opts.getProjectName());
        this.options = opts;
    }

    public Path extractFiles() {
        Path tempDir = new CreateTempDir().apply();
        new ZipExtractor(tempDir).apply();
        return tempDir;
    }

    public void start() {
        Map<String, Object> model = getModel();

        Path cwd = Paths.get(".");
        System.out.println("Generating in dir: " + cwd);
        System.out.println("Current target dir: " + target);

        ProjectGen.<Map<String, Object>>of(source, target, model)
            .mkdir()
            .apply((p) ->
                p.in("src/main/java/")
                    .toNamespace("base", model.get("namespace"))
                    .process(
                        "App.ftl.java",
                        "Main.ftl.java"
                    ))
            .apply((p) ->
                p.in("src/test/java/")
                    .toNamespace("base", model.get("namespace"))
                    .process("AppTest.ftl.java"))
            .translate("pom.ftl.xml", "pom.xml")
            .translate("install.ftl.sh", "install.sh")
            .apply();
    }

    private Map<String, Object> getModel() {
        Map<String, Object> map = new HashMap<>();
        map.put("namespace", this.options.getNamespace());
        map.put("projectName", this.options.getProjectName());
        return map;
    }
}
