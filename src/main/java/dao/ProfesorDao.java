package dao;

import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.model.Filters;
import database.MongoDBConnection;

import model.Profesor;

public class ProfesorDao {
    private MongoCollection coleccionProfesores;

    public ProfesorDao() {
        coleccionProfesores = new MongoDBConnection().getProfesoresCollection();
    }

    public void buscarProfesorRangoEdadPOJO(int edadmax, int edadmin) {
        coleccionProfesores = new MongoDBConnection().getProfesoresCollection();
        FindIterable<Profesor> iterable = coleccionProfesores.find(Filters.and(
                Filters.gte("age", edadmin),
                Filters.lte("age", edadmax)));
        MongoCursor<Profesor> mongoCursor = iterable.cursor();
        while (mongoCursor.hasNext()) {
            Profesor profesor = mongoCursor.next();
            profesor.mostrarDatos();
        }
    }

    public void obtenerTodosProfesoresPOJO() {
        MongoCollection coleccionProfesores = new MongoDBConnection().getProfesoresCollection();
        FindIterable<Profesor> iterable = coleccionProfesores.find();
        MongoCursor<Profesor> mongoCursor = iterable.cursor();
        while (mongoCursor.hasNext()) {
            Profesor profesor = mongoCursor.next();
            profesor.mostrarDatos();
        }
    }

    public void insertarProfesorPOJO(Profesor profesor) {
        MongoCollection coleccionProfesores = new MongoDBConnection().getProfesoresCollection();
        coleccionProfesores.insertOne(profesor);
    }

    public void actualizarRatingSegunEmailPOJO(String email, Double rating) {
        coleccionProfesores = new MongoDBConnection().getProfesoresCollection();
        FindIterable<Profesor> iterable = coleccionProfesores.find(Filters.eq("email", email));

        MongoCursor<Profesor> mongoCursor = iterable.cursor();
        while (mongoCursor.hasNext()) {
            Profesor profesor = mongoCursor.next();
            profesor.setRating(rating);
            profesor.mostrarDatos();
            coleccionProfesores.replaceOne(Filters.eq("email", email), profesor);
        }
    }
}
