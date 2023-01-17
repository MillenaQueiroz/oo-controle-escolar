package dao;
import factory.ConnectionFactory;
import modelo.Usuario;
import java.sql.*;
import java.sql.PreparedStatement;
import java.util.Date;

public class StudentDAO {
    private Connection connection;
    private int id;
    private String cpf;
    private String nome;
    private String email;
    private String senha;
    private Date dataNascimento;
    private String matricula;
    private String filiacao;

    public StudentDAO(){
        this.connection = new ConnectionFactory().getConnection();
    }
    public void addStudent(Student student){
        String sql = "INSERT INTO student(id,cpf,nome,email,senha,dataNascimento,matricula,filiacao) VALUES(?,?,?,?)";
        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setString(1, usuario.getId());
            stmt.setString(2, usuario.getCpf());
            stmt.setString(3, usuario.getNome());
            stmt.setString(4, usuario.getEmail());
            stmt.setString(5, usuario.getSenha());
            stmt.setString(6, usuario.getDataNasc());
            stmt.setString(7, usuario.getMatricula());
            stmt.setString(8, usuario.getFiliacao());
            stmt.execute();
            stmt.close();
        }
        catch (SQLException u) {
            throw new RuntimeException(u);
        }

    }

}
