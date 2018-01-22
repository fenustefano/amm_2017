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
public class PostFactory {

    private static PostFactory singleton;
    private String connectionString;

    public static PostFactory getInstance() {
        if (singleton == null) {
            singleton = new PostFactory();
        }
        return singleton;
    }

    private ArrayList<Post> listaPost = new ArrayList<>();

    private PostFactory() {

    }

    public Post getPostbyId(int id) {
        Post pst = null;
        try {
            Connection conn = DriverManager.getConnection(connectionString, "amm", "2017");

            String sql = "select * from posts where id = ?";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1, id);
            ResultSet set = stmt.executeQuery();
            if (set.next()) {
                pst = new Post();
                pst.setId(set.getInt("id"));
                pst.setAutore(UtenteFactory.getInstance().getUtentebyId(set.getInt("autore")));

                int temp = set.getInt("bacheca_ut");
                if (set.wasNull()) {
                    pst.setGruppo(GruppoFactory.getInstance().getGruppobyId(set.getInt("gruppo")));
                } else {
                    pst.setBacheca_ut(UtenteFactory.getInstance().getUtentebyId(set.getInt("bacheca_ut")));
                }
                pst.setContenuto(set.getString("contenuto"));
                pst.setAllegato(set.getString("allegato"));

                stmt.close();
                conn.close();
            }
        } catch (SQLException ex) {
            Logger.getLogger(GruppoFactory.class.getName()).log(Level.SEVERE, null, ex);
        }
        return pst;
    }

    public ArrayList<Post> getPostbyBacheca(Utente utente) {
        ArrayList<Post> lista = new ArrayList<>();
        try {
            Connection conn = DriverManager.getConnection(connectionString, "amm", "2017");

            String sql = "select * from posts where bacheca_ut = ?";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1, utente.getId());
            ResultSet set = stmt.executeQuery();
            while (set.next()) {
                Post pst = new Post();
                pst.setId(set.getInt("id"));
                pst.setAutore(UtenteFactory.getInstance().getUtentebyId(set.getInt("autore")));

                int temp = set.getInt("bacheca_ut");
                if (set.wasNull()) {
                    pst.setGruppo(GruppoFactory.getInstance().getGruppobyId(set.getInt("gruppo")));
                } else {
                    pst.setBacheca_ut(UtenteFactory.getInstance().getUtentebyId(set.getInt("bacheca_ut")));
                }
                pst.setContenuto(set.getString("contenuto"));
                switch (set.getInt("tipo")) {
                    case 3:
                        pst.setType(Post.Type.IMMAGINE);
                        break;
                    case 2:
                        pst.setType(Post.Type.LINK);
                        break;
                    case 1:
                        pst.setType(Post.Type.TESTO);
                        break;
                }

                pst.setAllegato(set.getString("allegato"));
                lista.add(pst);
            }

            stmt.close();
            conn.close();
        } catch (SQLException ex) {
            Logger.getLogger(GruppoFactory.class.getName()).log(Level.SEVERE, null, ex);
        }
        return lista;
    }

    public ArrayList<Post> getPostbyGruppo(Gruppo gruppo) {
        ArrayList<Post> lista = new ArrayList<>();
        try {
            Connection conn = DriverManager.getConnection(connectionString, "amm", "2017");

            String sql = "select * from posts where gruppo = ?";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1, gruppo.getId());
            ResultSet set = stmt.executeQuery();
            while (set.next()) {
                Post pst = new Post();
                pst.setId(set.getInt("id"));
                pst.setAutore(UtenteFactory.getInstance().getUtentebyId(set.getInt("autore")));

                int temp = set.getInt("bacheca_ut");
                if (set.wasNull()) {
                    pst.setGruppo(GruppoFactory.getInstance().getGruppobyId(set.getInt("gruppo")));
                } else {
                    pst.setBacheca_ut(UtenteFactory.getInstance().getUtentebyId(set.getInt("bacheca_ut")));
                }
                switch (set.getInt("tipo")) {
                    case 3:
                        pst.setType(Post.Type.IMMAGINE);
                        break;
                    case 2:
                        pst.setType(Post.Type.LINK);
                        break;
                    case 1:
                        pst.setType(Post.Type.TESTO);
                        break;
                }
                pst.setContenuto(set.getString("contenuto"));
                pst.setAllegato(set.getString("allegato"));
                lista.add(pst);

            }
            stmt.close();
            conn.close();
        } catch (SQLException ex) {
            Logger.getLogger(GruppoFactory.class.getName()).log(Level.SEVERE, null, ex);
        }
        return lista;
    }

    public void setConnectionString(String s) {
        this.connectionString = s;
    }

    public String getConnectionString() {
        return this.connectionString;
    }

    public void savePost(Post post) {
        try {
            Connection conn = DriverManager.getConnection(connectionString, "amm", "2017");
            String sql = "insert into posts (id, autore, bacheca_ut, gruppo, contenuto, tipo, allegato)"
                    + "values (default, ?, ?, ?, ?, ?, ?)";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1, post.getAutore().getId());
            if (post.getGruppo() == null) {
                stmt.setInt(2, post.getBacheca_ut().getId());
                stmt.setNull(3, java.sql.Types.INTEGER);
            } else {
                stmt.setNull(2, java.sql.Types.INTEGER); // il numero corrisponde al numero della colonna 
                stmt.setInt(3, post.getGruppo().getId());
            }
            stmt.setString(4, post.getContenuto());
            switch (post.getType()) {
                case IMMAGINE:
                    stmt.setInt(5, 3);
                    stmt.setString(6, post.getAllegato());
                    break;
                case LINK:
                    stmt.setInt(5, 2);
                    stmt.setString(6, post.getAllegato());
                    break;
                case TESTO:
                    stmt.setInt(5, 1);
                    stmt.setNull(6, java.sql.Types.VARCHAR);
                    break;
            }

            int value = stmt.executeUpdate();

            stmt.close();
            conn.close();

        } catch (SQLException ex) {
            Logger.getLogger(GruppoFactory.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
