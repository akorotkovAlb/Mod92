package org.examle;

import org.examle.ptg.post.Post;
import org.examle.ptg.post.PostRepository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DBRepositoryImpl implements PostRepository {

    private static final String INSERT_STRING = "INSERT INTO posts (id, author, post) VALUES (?, ?, ?)";
    private static final String DELET_BY_ID_STRING = "DELETE FROM posts WHERE id = ?";
    private static final String SELECT_ALL_STRING = "SELECT id, author, post FROM posts";

    private Connection connection;
    private PreparedStatement insertStatement;
    private PreparedStatement selectAllStatement;
    private PreparedStatement deleteStatement;

    public DBRepositoryImpl(Connection connection) {
        this.connection = connection;
        try {
            this.insertStatement = connection.prepareStatement(INSERT_STRING);
            this.selectAllStatement = connection.prepareStatement(SELECT_ALL_STRING);
            this.deleteStatement = connection.prepareStatement(DELET_BY_ID_STRING);
        } catch(SQLException e) {
            System.out.println("User Service construction exception. Reason: " + e.getMessage());
        }
    }

    @Override
    public List<Post> getAllPosts () {
        List<Post> users = new ArrayList<>();
        try(ResultSet resultSet = this.selectAllStatement.executeQuery()) {
            while(resultSet.next()) {
                Post user = new Post(resultSet.getString("id"),
                        resultSet.getString("author"),
                        resultSet.getString("post"));
                users.add(user);
            }
        } catch(SQLException e) {
            System.out.println("Select ALL posts exception. Reason: " + e.getMessage());
        }
        return users;
    }

    @Override
    public void addPost (Post post) {
        try {
            this.insertStatement.setString(1, post.getId());
            this.insertStatement.setString(2, post.getAuthor());
            this.insertStatement.setString(3, post.getPost());
        } catch(SQLException e) {
            System.out.println("Insert post exception. Reason: " + e.getMessage());
        }
    }

    @Override
    public void deleteById (String id) {
        try {
            this.deleteStatement.setString(1, id);
        } catch(SQLException e) {
            System.out.println("Delete post exception. Reason: " + e.getMessage());
        }
    }
}
