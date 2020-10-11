package org.kostuychenkov.model.repository;

import org.kostuychenkov.model.connection.DatabaseConnection;
import org.kostuychenkov.model.entities.Pet;
import org.kostuychenkov.model.entities.PetType;
import org.kostuychenkov.service.PetFactory;

import java.sql.*;

/**
 * Реализация DAO класса питомцев.
 */
public class PetRepositoryImpl implements PetRepository {

    private final static String TABLE_NAME = "pets";

    private Connection connection;
    private static final String LOAD_SQL_QUERY = "SELECT * FROM " + TABLE_NAME + " ORDER BY id DESC LIMIT 1";

    private static final String FOOD_EATEN_COLUMN_NAME = "food_eaten";
    private static final String FEEDING_DATE_COLUMN_NAME = "last_time_ate_milliseconds";
    private static final String PET_TYPE_COLUMN_NAME = "pet_type";

    private PetFactory petFactory;

    public PetRepositoryImpl(DatabaseConnection db) {
        this.connection = db.getConnection();
        this.petFactory = new PetFactory();
    }

    @Override
    public Pet load() {
        Pet pet = null;
        try {
            Statement st = connection.createStatement();
            ResultSet rs = st.executeQuery(LOAD_SQL_QUERY);
            if(rs.next()) {
                PetType type = PetType.valueOf(rs.getString(PET_TYPE_COLUMN_NAME));
                pet = petFactory.getPet(type);
                pet.setFoodEaten(rs.getInt(FOOD_EATEN_COLUMN_NAME));
                pet.setLastTimeAte(rs.getLong(FEEDING_DATE_COLUMN_NAME));
                pet.checkAge();
                pet.checkHunger();
            }
        } catch(SQLException e) {
            e.printStackTrace();
        }
        return pet;
    }

    @Override
    public void save(Pet pet) {
        try {
            PreparedStatement pst = connection.prepareStatement
                    ("INSERT INTO " + TABLE_NAME + "(food_eaten, last_time_ate_milliseconds, pet_type) VALUES(?, ?, ?)");

            pst.setInt(1, pet.getFoodEaten());
            pst.setLong(2,  pet.getLastTimeAte().getTime());
            pst.setString(3, pet.getPetType().toString());
            pst.executeUpdate();
        } catch(SQLException e) {
            e.printStackTrace();
        }
    }
}
