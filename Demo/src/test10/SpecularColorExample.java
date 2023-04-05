package test10;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.PointLight;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.scene.paint.PhongMaterial;
import javafx.scene.shape.Sphere;
import javafx.stage.Stage;
public class SpecularColorExample {

    public static class specularColorExample extends Application {
        private View view;
        @Override
        public void start(Stage stage) throws Exception {
            view = new View();
            stage.setTitle("Specular Color Example");
            stage.setScene(view.scene);
            stage.show();
        }
        public static void main(String[] args) {
            launch(args);
        }
        private static class View {
            public Scene scene;
            public Sphere sphere1;
            public Sphere sphere2;
            public PointLight light;
            private View() {
                sphere1 = new Sphere(100);
                sphere2 = new Sphere(100);
                PhongMaterial material = new PhongMaterial(Color.BLUE);
                material.setSpecularColor(Color.LIGHTBLUE);
                material.setSpecularPower(10.0d);
                sphere1.setMaterial(material);
                sphere1.setTranslateZ(300);
                sphere1.setTranslateX(0);
                sphere2.setMaterial(material);
                sphere2.setTranslateZ(300);
                sphere2.setTranslateX(130);
                light = new PointLight(Color.WHITE);
                Group group = new Group(sphere1,sphere2,light);
                group.setTranslateY(240);
                group.setTranslateX(320);
                scene = new Scene(group, 640, 480);

            }
        }
    }
}
