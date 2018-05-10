/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import Model.Usuario;
import Util.OperacoesBancoDados;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Emm
 */
public class UsuarioDAO {
    
OperacoesBancoDados fabrica = new OperacoesBancoDados();

       public void criarUsuario(Usuario usuario) throws ClassNotFoundException, SQLException {

       
          String sql = "INSERT INTO tb_usuario (NOME, DEPARTAMENTO, dataAdmissao, "
                + "LOGIN, PASSWORD) "
                + "VALUES ('"+usuario.getNome()
                + "', '"+usuario.getDepartamento()
                + "', '"+usuario.getDataAdmissao()
                + "', '"+usuario.getLogin()
                + "', '"+usuario.getPassword()
                + "')";


            fabrica.executaQuerieUpdate(sql);

        }

    
       public void editarUsuario(Usuario usuario) throws ClassNotFoundException, SQLException {


        String sql1 = "UPDATE tb_usuario "
                + "SET LOGIN = '" + usuario.getLogin()
                + "', NOME =  '" + usuario.getNome()
                + "', PASSWORD = '" + usuario.getPassword()
                + "', departamento = '"+usuario.getDepartamento()
                + "', dataAdmissao = '"+usuario.getDataAdmissao()
                + " WHERE matricula = " + usuario.getMatricula();


            fabrica.executaQuerieUpdate(sql1);
    }



      public void removerUsuario(int matricula) throws SQLException, ClassNotFoundException {

      
              String sql = "DELETE FROM tb_usuario WHERE matricula = "
                    + matricula + ";";
            
            fabrica.executaQuerieUpdate(sql);
    }

    public List<Usuario> consultaUsuarios() throws ClassNotFoundException, SQLException {
   
        String sql = "SELECT * FROM tb_usuario;";
        
        
        ResultSet rs = fabrica.executaQuerieResultSet(sql);
        
        return this.extrairListaUsuariosResultSet(rs);
    
    }


    public Usuario findUsuario(int matricula) throws ClassNotFoundException, SQLException {


        String sql = "SELECT * FROM tb_usuario WHERE matricula = "
                +matricula+";";
        
        ResultSet rs = fabrica.executaQuerieResultSet(sql);
        Usuario usuario = this.extraiUsuarioResultSet(rs);
        rs.close();
        
        return usuario;

    }


    public Usuario findByLoginSenha(String login, String senha) throws ClassNotFoundException, SQLException {

            String sql = "SELECT * FROM tb_usuario WHERE LOGIN LIKE '"
                    + login + "' AND PASSWORD LIKE '" + senha + "'";

            ResultSet rs = fabrica.executaQuerieResultSet(sql);
            Usuario usuario  = this.extraiUsuarioResultSet(rs);
            rs.close();
            
            return usuario;

    }
    
    

    public Usuario findByLogin(String login) throws ClassNotFoundException, SQLException {
    
    
        String sql = "SELECT * FROM tb_usuario WHERE login LIKE '"
                + login + "'";
        
        ResultSet rs = null;
        try {
            rs = fabrica.executaQuerieResultSet(sql);
        } catch (SQLException ex) {
            Logger.getLogger(UsuarioDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        Usuario usuario = this.extraiUsuarioResultSet(rs);
        rs.close();
        
        return usuario;

    }


    public Usuario extraiUsuarioResultSet(ResultSet rs) throws SQLException, ClassNotFoundException {

        Usuario usuario = new Usuario();

        usuario.setMatricula(rs.getInt("matricula"));
        usuario.setNome(rs.getString("nome"));
        usuario.setDepartamento(rs.getString("departamento"));
        usuario.setLogin(rs.getString("login"));
        usuario.setPassword(rs.getString("password"));
        
        return usuario;
    }

    public List<Usuario> extrairListaUsuariosResultSet(ResultSet rs) throws SQLException, ClassNotFoundException {

        List<Usuario> listaUsuarios = new ArrayList();

        while (rs.next()) {
            listaUsuarios.add(this.extraiUsuarioResultSet(rs));
        }
        rs.close();

        return listaUsuarios;

    }

}