package dao;

import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.model.Filters;
import database.MongoDBConnection;
import model.Alumno;
import org.bson.conversions.Bson;

public class AlumnosDao {
    private MongoCollection coleccionAlumnos;

    public AlumnosDao() {
        coleccionAlumnos = new MongoDBConnection().getAlumnosCollection();
    }

    //    public void insertarAlumno(Alumno alumno) {
//        Document doc = new Document()
//                .append("rating", alumno.getRating())
//                .append("age", alumno.getAge())
//                .append("name", alumno.getName())
//                .append("gender", alumno.getGender())
//                .append("email", alumno.getEmail())
//                .append("phone", alumno.getPhone())
//                .append("calification", alumno.getCalification())
//                .append("higher_grade", alumno.getHigher_grade());
//        coleccionAlumnos.insertOne(doc);
//    }
    public void insertarAlumnoPOJO(Alumno alumno) {
        MongoCollection coleccionAlumnos = new MongoDBConnection().getAlumnosCollection();
        coleccionAlumnos.insertOne(alumno);
    }

    public void obtenerTodosAlumnosPOJO() {
        MongoCollection coleccionAlumnos = new MongoDBConnection().getAlumnosCollection();
        FindIterable<Alumno> iterable = coleccionAlumnos.find();
        MongoCursor<Alumno> mongoCursor = iterable.cursor();
        while (mongoCursor.hasNext()) {
            Alumno alumno = mongoCursor.next();
            alumno.mostrarDatos();
        }
    }

//    public void eliminarAlumnoRangoEdadPOJO(int edadmax, int edadmin) {
//        Bson filtrado = Filters.and(
//                Filters.gte("age", edadmin),
//                Filters.lte("age", edadmax));
//        coleccionAlumnos = new MongoDBConnection().getAlumnosCollection();
//        coleccionAlumnos.deleteMany(filtrado);
//    }

    public void eliminarAlumnoNotaPOJO() {
        Bson filtrado = Filters.gte("calificaation", 5);
        coleccionAlumnos = new MongoDBConnection().getAlumnosCollection();
        coleccionAlumnos.deleteMany(filtrado);
    }

    public void buscarAlumnoEmailPOJO(String email) {
        coleccionAlumnos = new MongoDBConnection().getAlumnosCollection();
        FindIterable<Alumno> iterable = coleccionAlumnos.find(Filters.eq("email", email));
        MongoCursor<Alumno> mongoCursor = iterable.cursor();
        while (mongoCursor.hasNext()) {
            Alumno alumno = mongoCursor.next();
            alumno.mostrarDatos();
        }
    }

}
