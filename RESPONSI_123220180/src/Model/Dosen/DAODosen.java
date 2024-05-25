/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model.Dosen;

import Model.Dosen.InterfaceDAODosen;
import Model.Dosen.ModelDosen;
import Model.Connector;
import Model.Connector;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Lab Informatika
 */
public class DAODosen implements InterfaceDAODosen {

    @Override
    public void insert(ModelDosen dosen) {
       try {
            PreparedStatement statement;
            statement = Connector.Connect().prepareStatement(query);
            statement.setString(1, dosen.getNama());
            statement.setString(2, dosen.getId());
            statement.executeUpdate();
            statement.close();
        } catch (SQLException e) {
            System.out.println("Input Failed: " + e.getLocalizedMessage());
        } 
    }

    @Override
    public void update(ModelDosen dosen) {
        try {
            String query = "UPDATE dosen SET nama=?, id=? WHERE id=?;";
            PreparedStatement statement;
            statement = Connector.Connect().prepareStatement(query);
            statement.setString(1, dosen.getNama());
            statement.setString(2, dosen.getId());
            statement.setInt(3, dosen.getId());
            statement.executeUpdate();
            
            statement.close();
        } catch (SQLException e) {
            System.out.println("update Failed! (" + e.getMessage() + ")");
        }
    }

    @Override
    public void delete(int id) {
        try {
            String query = "DELETE FROM dosen WHERE id=?;";
            PreparedStatement statement;
            statement = Connector.Connect().prepareStatement(query);
            statement.setInt(1, id);
            statement.executeUpdate();
            statement.close();
        } catch (SQLException e) {
            System.out.println("Delete Failed: " + e.getLocalizedMessage());
        }
    }

    @Override
    public List<ModelDosen> getAll() {
         List<ModelDosen> listDosen = null;

        try {
            listDosen = new ArrayList<>();
            Statement statement = Connector.Connect().createStatement();
            String query = "SELECT * FROM dosen;";
            ResultSet resultSet = statement.executeQuery(query);
            while (resultSet.next()) {
                ModelDosen dsn = new ModelDosen();
                dsn.setId(resultSet.getInt("id"));
                dsn.setNama(resultSet.getString("nama"));
                dsn.setNoHP(resultSet.getString("no_hp"));
                listDosen.add(dsn);
            }
            
            statement.close();
        } catch (SQLException e) {
            System.out.println("Error: " + e.getLocalizedMessage());
        }
        return listDosen;
    }
}
