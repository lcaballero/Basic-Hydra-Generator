package basic;


public class App {

    private String[] args = null;

    public App(String[] args) {
        this.args = args;
    }

    public void start() {
        new Project(new GeneratorOptions(this.args).parse()).start();
    }
}
