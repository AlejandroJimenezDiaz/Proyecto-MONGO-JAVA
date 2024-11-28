package model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.bson.codecs.pojo.annotations.BsonProperty;

import java.io.Serializable;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter

public class Alumno implements Serializable {


    private double rating;
    private int age;
    private String name;
    private String gender;
    private String email;
    private String phone;
    @BsonProperty("calificaation")
    private int calification;
    private String higher_grade;


    public void mostrarDatos() {
        System.out.println("Rating: " + rating);
        System.out.println("Edad: " + age);
        System.out.println("Nombre: " + name);
        System.out.println("Apellido: " + gender);
        System.out.println("Email: " + email);
        System.out.println("Telefono: " + phone);
        System.out.println("Calificacion: " + calification);
        System.out.println("Higher grade: " + higher_grade);
        System.out.println("------------------------------\n");
    }
}
