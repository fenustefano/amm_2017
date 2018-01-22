/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package project.nerd;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Ste2016
 */
public class GruppoFactory {

    private static GruppoFactory singleton;
    private String connectionString;

    public static GruppoFactory getInstance() {
        if (singleton == null) {
            singleton = new GruppoFactory();
        }
        return singleton;
    }

    private ArrayList<Gruppo> listaGruppi = new ArrayList<>();

    private GruppoFactory() {

    }

    public ArrayList<Gruppo> getList() {
        ArrayList<Gruppo> lista = new ArrayList<>();
        try {
            Connection conn = DriverManager.getConnection(connectionString, "amm", "2017");

            String sql = "select * from gruppi";
            PreparedStatement stmt = conn.prepareStatement(sql);
            ResultSet set = stmt.executeQuery();
            while (set.next()) {
                Gruppo grp = new Gruppo();
                grp.setId(set.getInt("id"));
                grp.setNome(set.getString("nome"));
                grp.setUrlFoto(set.getString("urlFoto"));
                lista.add(grp);

            }

            stmt.close();
            conn.close();
        } catch (SQLException ex) {
            Logger.getLogger(GruppoFactory.class.getName()).log(Level.SEVERE, null, ex);
        }
        return lista;
    }

    public Gruppo getGruppobyId(int id) {
        Gruppo grp = null;
        try {
            Connection conn = DriverManager.getConnection(connectionString, "amm", "2017");

            String sql = "select * from gruppi where id = ?";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1, id);
            ResultSet set = stmt.executeQuery();
            if (set.next()) {
                grp = new Gruppo();
                grp.setId(set.getInt("id"));
                grp.setNome(set.getString("nome"));
                grp.setUrlFoto(set.getString("urlFoto"));

                stmt.close();
                conn.close();
            }
        } catch (SQLException ex) {
            Logger.getLogger(GruppoFactory.class.getName()).log(Level.SEVERE, null, ex);
        }
        return grp;

    }

    public void setConnectionString(String s) {
        this.connectionString = s;
    }

    public String getConnectionString() {
        return this.connectionString;
    }

}
