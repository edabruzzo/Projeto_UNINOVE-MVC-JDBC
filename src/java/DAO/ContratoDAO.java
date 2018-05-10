/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import Model.Contrato;
import Util.OperacoesBancoDados;
import java.sql.Connection;
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
public class ContratoDAO {

    OperacoesBancoDados fabrica = new OperacoesBancoDados();

    public void criarContrato(Connection conn, Contrato contrato) throws ClassNotFoundException, SQLException {

        String sql = "INSERT INTO tb_contrato(objetoContrato, orcamentoComprometido, "
                + "ATIVO, contratado) "
                + "VALUES ('" + contrato.getObjeto()
                + "', " + contrato.getOrcamentoComprometido()
                + ", " + contrato.isAtivo()
                + ", '" + contrato.getContratado()
                + "');";

        fabrica.executaQuerieUpdate(conn, sql);

    }

    public void editarContrato(Connection conn, Contrato contrato) throws ClassNotFoundException, SQLException {

        String sql1 = "UPDATE tb_contrato "
                + "SET objetoContrato = '" + contrato.getObjeto()
                + "', orcamentoComprometido =  " + contrato.getOrcamentoComprometido()
                + ", contratado = '" + contrato.getContratado()
                + "', ativo = " + contrato.isAtivo()
                + " WHERE codigo = " + contrato.getCodigo() + ";";

        fabrica.executaQuerieUpdate(conn, sql1);
    }

    public void removerContrato(Connection conn, int codigo) throws SQLException, ClassNotFoundException {

        String sql = "DELETE FROM tb_contrato WHERE codigo = "
                + codigo + ";";

        fabrica.executaQuerieUpdate(conn, sql);
    }

    public List<Contrato> consultaContratos(Connection conn) throws ClassNotFoundException, SQLException {

        String sql = "SELECT * FROM tb_contrato;";

        ResultSet rs = fabrica.executaQuerieResultSet(conn, sql);

        return this.extrairListaContratosResultSet(rs);

    }

    public Contrato findContrato(Connection conn, int codigo) throws ClassNotFoundException, SQLException {

        String sql = "SELECT * FROM tb_contrato WHERE codigo = "
                + codigo + " ORDER BY orcamentoComprometido DESC;";

        ResultSet rs = fabrica.executaQuerieResultSet(conn, sql);
        Contrato contrato = this.extraiContratoResultSet(rs);
        rs.close();

        return contrato;

    }

    public List<Contrato> findByObjeto(Connection conn, String objeto) throws ClassNotFoundException, SQLException {

        String sql = "SELECT * FROM tb_contrato WHERE objeto LIKE '"
                + objeto + "';";

        ResultSet  rs = fabrica.executaQuerieResultSet(conn, sql);
        return this.extrairListaContratosResultSet(rs);

    }

    public Contrato findByCodigo(Connection conn, Integer codigo) throws ClassNotFoundException, SQLException {

        String sql = "SELECT * FROM tb_contrato WHERE codigo = "
                + codigo + ";";

        ResultSet rs = null;
        try {
            rs = fabrica.executaQuerieResultSet(conn, sql);
        } catch (SQLException ex) {
            Logger.getLogger(ContratoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        Contrato contrato = this.extraiContratoResultSet(rs);
        rs.close();

        return contrato;

    }

    public List<Contrato> findAtivos(Connection conn) throws ClassNotFoundException, SQLException {

        String sql = "SELECT * FROM tb_contrato WHERE ativo IS TRUE;";

        ResultSet rs = fabrica.executaQuerieResultSet(conn, sql);
        return this.extrairListaContratosResultSet(rs);

    }

    public List<Contrato> findInativos(Connection conn) throws ClassNotFoundException, SQLException {

        String sql = "SELECT * FROM tb_contrato WHERE ativo IS FALSE;";

        ResultSet rs = fabrica.executaQuerieResultSet(conn, sql);
        return this.extrairListaContratosResultSet(rs);

    }

    public List<Contrato> findByContratado(Connection conn, String contratado) throws ClassNotFoundException, SQLException {

        String sql = "SELECT * FROM tb_contrato WHERE contratado = '"
                + contratado + "';";

        ResultSet rs = fabrica.executaQuerieResultSet(conn, sql);
        return this.extrairListaContratosResultSet(rs);

    }

    
    
    public Contrato extraiContratoResultSet(ResultSet rs) throws SQLException, ClassNotFoundException {

        Contrato contrato = new Contrato();

        contrato.setCodigo(rs.getInt("codigo"));
        contrato.setObjeto(rs.getString("objeto"));
        contrato.setContratado(rs.getString("contratado"));
        contrato.setAtivo(rs.getBoolean("ativo"));
        contrato.setOrcamentoComprometido(rs.getDouble("orcamentoComprometido"));

        return contrato;
    }

    public List<Contrato> extrairListaContratosResultSet(ResultSet rs) throws SQLException, ClassNotFoundException {

        List<Contrato> listaContratos = new ArrayList();

        while (rs.next()) {
            listaContratos.add(this.extraiContratoResultSet(rs));
        }
        rs.close();

        return listaContratos;

    }

}
