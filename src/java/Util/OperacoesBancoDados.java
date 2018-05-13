/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

/**
 *
 * @author Emm
 */
public class OperacoesBancoDados {

    private String URL;


    public void criaInfraestrutura() throws SQLException, ClassNotFoundException {


        ArrayList<String> listaSQLs = new ArrayList();

        String sql1 = "CREATE TABLE IF NOT EXISTS tb_contrato (codigo INTEGER AUTO_INCREMENT NOT NULL, "
                + " objetoContrato VARCHAR(255) NOT NULL, orcamentoComprometido DOUBLE NOT NULL, "
                + "ATIVO TINYINT(1) default 0, contratado VARCHAR(255) NOT NULL, PRIMARY KEY (codigo));";
        listaSQLs.add(sql1);

        String sql2 = "CREATE TABLE IF NOT EXISTS tb_usuario"
                + " (matricula INTEGER AUTO_INCREMENT NOT NULL, nome VARCHAR(255) NOT NULL UNIQUE, "
                + "departamento VARCHAR(255) NOT NULL, dataAdmissao DATE NOT NULL, "
                + " login VARCHAR(10) NOT NULL, password  VARCHAR(10) NOT NULL,  "
                + "PRIMARY KEY (matricula));";

        listaSQLs.add(sql2);

        String sql3 = "INSERT INTO tb_contrato(objetoContrato, orcamentoComprometido, "
                + "ATIVO, contratado) "
                + "VALUES('Manutenção parque tecnológico', 100000, true, 'Organizações Tabajara Ltda.');";

        listaSQLs.add(sql3);


        String sql4 = "INSERT INTO tb_contrato(objetoContrato, orcamentoComprometido, "
                + "ATIVO, contratado) "
                + "VALUES('Limpeza', 50000, true, 'Organizações Tabajara Ltda.');";

        listaSQLs.add(sql4);


        String sql5 = "INSERT INTO tb_contrato(objetoContrato, orcamentoComprometido, "
                + "ATIVO, contratado) "
                + "VALUES('Segurança', 200000, true, 'Organizações Tabajara Ltda.');";

        listaSQLs.add(sql5);

        String sql6 = "INSERT INTO tb_usuario (nome, departamento, dataAdmissao, "
                + "login, password) "
                + "VALUES ('Fulano', 'Operações Especiais', '2018-01-01', 'fulano', '123')";
        listaSQLs.add(sql6);

        
        String sql7 = "INSERT INTO tb_usuario (nome, departamento, dataAdmissao, "
                + "login, password) "
                + "VALUES ('Sicrano', 'Operações Especiais', '2018-01-01', 'sicrano', '123')";
        listaSQLs.add(sql7);

        

        executaBatchUpdate(this.criaConexao(), listaSQLs);

    }

    public void criaBaseDados() throws ClassNotFoundException, SQLException {

        Class.forName("com.mysql.jdbc.Driver");
        String USER = "root";
        String PASSWORD = "root";
        this.setURL("jdbc:mysql://localhost:3306/");

        Connection conn = null;
        Statement stmt = null;
        //STEP 3: Open a connection
        System.out.println("Conectando ao servidor com a seguinte URL : " + this.URL);

        try {
            conn = DriverManager.getConnection(URL, USER, PASSWORD);
            stmt = conn.createStatement();
            conn.setAutoCommit(false);
            stmt.execute("CREATE DATABASE IF NOT EXISTS controleFinanceiroUNINOVE");
            conn.commit();
        } catch (SQLException ex) {
            conn.rollback();
            Logger.getLogger(OperacoesBancoDados.class.getName()).log(Level.SEVERE, null, ex);
        } finally {

            fecharConexao(conn);
            stmt.close();

        }

    }

    public Connection criaConexao()  {

        System.out.println("Criando a conexao com o banco de dados ...");
        InitialContext  contextoInicial = null;
        try {
            contextoInicial = new InitialContext();
        } catch (NamingException ex) {
            Logger.getLogger(OperacoesBancoDados.class.getName()).log(Level.SEVERE, null, ex);
        }
        Object lookup = null;
        try {
            /*
            
            ATENÇÃO:
            
            SE FOR TOMCAT TEM QUE ACRESCENTAR java:env ou java:comp/env/jdbc/nomeDoDatasource
            
            NO GLASSFISH É "java:app/jdbc/nomeDoDataSource", mas parece que não precisa acrescentar java:app
            */
            
            lookup = contextoInicial.lookup("java:comp/env/jdbc/projetoUNINOVE");
        } catch (NamingException ex) {
            Logger.getLogger(OperacoesBancoDados.class.getName()).log(Level.SEVERE, null, ex);
        }
        DataSource dataSource = (DataSource) lookup;
        
        Connection conn = null;
        try {
       conn = dataSource.getConnection();
       System.out.println("CONEXÃO CRIADA COM SUCESSO!");
    
        } catch (SQLException ex) {
            Logger.getLogger(OperacoesBancoDados.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return conn;

    }

    public void fecharConexao(Connection conn) {

        System.out.println("Fechando a conexão com o banco de dados");
        try {
            if (conn != null) {
                conn.close();

                System.out.println("Conexão com o banco de dados fechada com sucesso");
            }

        } catch (SQLException ex) {
            Logger.getLogger(OperacoesBancoDados.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("********ATENÇÃO ! Conexão com o banco de dados não foi fechada !");

        }
    }

    public ResultSet executaQuerieResultSet(Connection conn, String sql) throws ClassNotFoundException, SQLException {

        Statement stmt = conn.createStatement();

        ResultSet rs = null;
        try {
            System.out.println("Executando a seguinte query .....");
            System.out.println(sql);
            rs = stmt.executeQuery(sql);
            System.out.println("Executada com sucesso!");

        } catch (SQLException ex) {
            System.out.println("Query não executada!");
            Logger.getLogger(OperacoesBancoDados.class.getName()).log(Level.SEVERE, null, ex);
        } finally {

            //rs.close(); NÃO POSSO FECHAR O RESULTSET AQUI
            //stmt.close();
            //fecharConexao(conn);

        }

        return rs;

    }

    public void executaQuerieSemResultado(Connection conn, String sql) throws ClassNotFoundException, SQLException {

       
        Statement stmt = conn.createStatement();

        try {
            System.out.println("Executando a seguinte query .....");
            System.out.println(sql);
            stmt.execute(sql);
            System.out.println("Executada com sucesso!");

        } catch (SQLException ex) {

            System.out.println("Query não executada!");
            Logger.getLogger(OperacoesBancoDados.class.getName()).log(Level.SEVERE, null, ex);
        } finally {

            stmt.close();
            fecharConexao(conn);

        }

    }

    public void executaQuerieUpdate(Connection conn, String sql) throws ClassNotFoundException, SQLException {

       
        Statement stmt = conn.createStatement();
        conn.setAutoCommit(false);

        try {
            System.out.println("Executando commit da seguinte query .....");
            System.out.println(sql);
            stmt.executeUpdate(sql);
            conn.commit();
           System.out.println("Executada com sucesso!");

        } catch (SQLException ex) {

            System.out.println("Query não executada! Efetuando rollback");
            conn.rollback();
            Logger.getLogger(OperacoesBancoDados.class.getName()).log(Level.SEVERE, null, ex);
        } finally {

            stmt.close();
            fecharConexao(conn);

        }

    }

    public String getURL() {
        return URL;
    }

    public void setURL(String URL) {
        this.URL = URL;
    }

    public void executaBatchUpdate(Connection conn, ArrayList listaSQLs) throws SQLException, ClassNotFoundException {

        conn = criaConexao();
        Statement stmt = conn.createStatement();
        conn.setAutoCommit(false);
        for (int i = 0; i < listaSQLs.size(); i++) {
            System.out.println(listaSQLs.get(i));
            stmt.addBatch((String) listaSQLs.get(i));

        }

        try {
           System.out.println("Executando commit da seguinte query .....");
            stmt.executeBatch();
            conn.commit();
           System.out.println("Executada com sucesso!");

        } catch (SQLException ex) {

           System.out.println("Query não executada! Efetuando rollback");
            conn.rollback();
            Logger.getLogger(OperacoesBancoDados.class.getName()).log(Level.SEVERE, null, ex);
        } finally {

            stmt.close();
            fecharConexao(conn);
        }
    }

    
    

    public void deletaBanco() throws ClassNotFoundException, SQLException{
        
        Class.forName("com.mysql.jdbc.Driver");
        String USER = "root";
        String PASSWORD = "root";
        this.setURL("jdbc:mysql://localhost:3306/");

        Connection conn = null;
        Statement stmt = null;
        //STEP 3: Open a connection
        System.out.println("Conectando ao servidor com a seguinte URL : " + this.URL);

        try {
            conn = DriverManager.getConnection(URL, USER, PASSWORD);
            stmt = conn.createStatement();
            conn.setAutoCommit(false);
            System.out.println("DELETANDO BASE DE DADOS controleFinanceiroUNINOVE");
            stmt.execute("DROP DATABASE IF EXISTS controleFinanceiroUNINOVE");
            System.out.println("BASE DE DADOS controleFinanceiroUNINOVE DELETADA COM SUCESSO!");
            conn.commit();
        } catch (SQLException ex) {
            conn.rollback();
            Logger.getLogger(OperacoesBancoDados.class.getName()).log(Level.SEVERE, null, ex);
        } finally {

            fecharConexao(conn);
            stmt.close();
            
           }
    }
}