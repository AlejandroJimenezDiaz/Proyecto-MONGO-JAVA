package model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.io.Serializable;
import java.util.ArrayList;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Profesor implements Serializable {

    private double rating;
    private int age;
    private String name;
    private String gender;
    private String email;
    private String phone;
    private ArrayList<String> subjects;
    private String title;

    public void mostrarDatos() {
        System.out.println("Rating: " + rating);
        System.out.println("Age: " + age);
        System.out.println("Nombre: " + name);
        System.out.println("Apellido: " + gender);
        System.out.println("Email: " + email);
        System.out.println("Telefono: " + phone);
        System.out.println("Subjects: " + subjects);
        System.out.println("Title: " + title);
        System.out.println("---------------------------\n☻");
    }

}
