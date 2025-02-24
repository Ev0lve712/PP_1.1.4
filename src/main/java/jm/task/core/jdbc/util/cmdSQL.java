package jm.task.core.jdbc.util;

public interface cmdSQL {
    String CREATE_TABLES = "CREATE TABLE users (id INT AUTO_INCREMENT PRIMARY KEY, name varchar(255), lastName varchar(255) , age int)";
    String DROP_TABLES = "DROP TABLE IF EXISTS users";
    String GET_TABLES = "SELECT * FROM users";
    String CLEAN_TABLES = "TRUNCATE TABLE users";
    String DELETE_USER = "DELETE FROM users WHERE id = ?";
    String SAVE_USER = "INSERT INTO users (name, lastName, age) VALUES (?, ?, ?)";
}
