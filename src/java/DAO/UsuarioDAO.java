/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import Model.Usuario;
import Util.OperacoesBancoDados;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Emm
 */
public class UsuarioDAO {

    OperacoesBancoDados fabrica = new OperacoesBancoDados();

    public void criarUsuario(Connection conn, Usuario usuario) throws ClassNotFoundException, SQLException {

        String sql = "INSERT INTO tb_usuario (NOME, DEPARTAMENTO, dataAdmissao, "
                + "LOGIN, PASSWORD) "
                + "VALUES ('" + usuario.getNome()
                + "', '" + usuario.getDepartamento()
                + "', '" + usuario.getDataAdmissao()
                + "', '" + usuario.getLogin()
                + "', '" + usuario.getPassword()
                + "')";

        fabrica.executaQuerieUpdate(conn, sql);

    }

    public void editarUsuario(Connection conn, Usuario usuario) throws ClassNotFoundException, SQLException {

        String sql1 = "UPDATE tb_usuario "
                + "SET LOGIN = '" + usuario.getLogin()
                + "', NOME =  '" + usuario.getNome()
                + "', PASSWORD = '" + usuario.getPassword()
                + "', departamento = '" + usuario.getDepartamento()
                + "', dataAdmissao = '" + usuario.getDataAdmissao()
                + " WHERE matricula = " + usuario.getMatricula();

        fabrica.executaQuerieUpdate(conn, sql1);
    }

    public void removerUsuario(Connection conn, int matricula) throws SQLException, ClassNotFoundException {

        String sql = "DELETE FROM tb_usuario WHERE matricula = "
                + matricula + ";";

        fabrica.executaQuerieUpdate(conn, sql);
    }

    public List<Usuario> consultaUsuarios(Connection conn) throws ClassNotFoundException, SQLException {

        String sql = "SELECT * FROM tb_usuario;";

        ResultSet rs = fabrica.executaQuerieResultSet(conn, sql);

        return this.extrairListaUsuarios(rs);
                
                
    }

    public Usuario findUsuario(Connection conn, int matricula) throws ClassNotFoundException, SQLException {

        String sql = "SELECT * FROM tb_usuario WHERE matricula = "
                + matricula + ";";

        ResultSet rs = fabrica.executaQuerieResultSet(conn, sql);
        
        return this.extraiUsuarioResultSet(rs);
       
    }

    
    public Usuario findByLoginSenha(Connection conn, String login, String senha) throws ClassNotFoundException, SQLException {

        String sql = "SELECT * FROM tb_usuario WHERE LOGIN LIKE '"
                + login + "' AND PASSWORD LIKE '" + senha + "'";

        ResultSet rs = fabrica.executaQuerieResultSet(conn, sql);
        
        return this.extraiUsuarioResultSet(rs);
        
    }


    public Usuario findByNome(Connection conn, String nome) throws ClassNotFoundException, SQLException {

        String sql = "SELECT * FROM tb_usuario WHERE nome LIKE '"
                + nome + "'";

        ResultSet    rs = fabrica.executaQuerieResultSet(conn, sql);
        
        return this.extraiUsuarioResultSet(rs);
       
    }

    public List<Usuario> findByDepartamento(Connection conn, String departamento) throws ClassNotFoundException, SQLException {

        String sql = "SELECT * FROM tb_usuario WHERE departamento = '" + departamento + "';";

        ResultSet rs = fabrica.executaQuerieResultSet(conn, sql);

        return this.extrairListaUsuarios(rs);

    }

    public List<Usuario> findByDataAdmissao(Connection conn, Date dataInicial, Date dataFinal) throws ClassNotFoundException, SQLException {

        String sql = "SELECT * FROM tb_usuario WHERE dataAdmissao between '" + dataInicial
                + " AND '" + dataFinal + "';";

        ResultSet rs = fabrica.executaQuerieResultSet(conn, sql);

        return this.extrairListaUsuarios(rs);

    }

    

    public Usuario extraiUsuarioResultSet(ResultSet rs) throws SQLException, ClassNotFoundException {

        Usuario usuario = new Usuario();

        while (rs.next()) {

            usuario.setMatricula(rs.getInt("matricula"));
            usuario.setNome(rs.getString("nome"));
            usuario.setDepartamento(rs.getString("departamento"));
            usuario.setLogin(rs.getString("login"));
            usuario.setPassword(rs.getString("password"));
            usuario.setDataAdmissao(rs.getDate("dataAdmissao"));

        }

        rs.close();

        return usuario;
    }



    public List<Usuario> extrairListaUsuarios(ResultSet rs) throws SQLException, ClassNotFoundException {

        List<Usuario> listaUsuarios = new ArrayList();

        while (rs.next()) {

            Usuario usuario = new Usuario();

            usuario.setMatricula(rs.getInt("matricula"));
            usuario.setNome(rs.getString("nome"));
            usuario.setDepartamento(rs.getString("departamento"));
            usuario.setLogin(rs.getString("login"));
            usuario.setPassword(rs.getString("password"));
            usuario.setDataAdmissao(rs.getDate("dataAdmissao"));

            listaUsuarios.add(usuario);

        }

        rs.close();

        return listaUsuarios;

    }

}
