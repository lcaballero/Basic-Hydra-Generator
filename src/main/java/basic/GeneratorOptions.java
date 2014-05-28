package basic;

public class GeneratorOptions {

    private String[] args;
    private String projectName;
    private String namespace;

    public GeneratorOptions(String[] args) {
        this.args = args;
    }

    public GeneratorOptions parse() {

        if (this.args.length < 2) {
            throw new IllegalArgumentException(
                "Cannot generate project, expecting more arguments.  Found: " + this.args.length);
        }

        projectName = this.args[0];
        namespace = this.args[1];

        return this;
    }

    public String getProjectName() {
        return projectName;
    }

    public String getNamespace() {
        return namespace;
    }
}
