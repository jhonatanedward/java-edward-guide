package core.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

public class App {
    public static void main(String[] args) {
        var developer = new Developer();
        Processor.proccess(developer);
    }
}

class Processor {

    public static void proccess(Object obj) {
        var classObj = obj.getClass();

        var annotation = classObj.getDeclaredAnnotation(Backend.class);

        if (annotation != null) {
            System.out.println("You're a back-end");
        }
    }
}

@Backend
class Developer {
    private String name;
    private String age;

    public void showDevelopperInfo() {
        System.out.println(this.name + " " + this.age);
    }
}

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
@interface Backend {

}