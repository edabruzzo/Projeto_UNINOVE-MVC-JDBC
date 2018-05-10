/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import Model.Contrato;
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
public class ContratoDAO {
    
OperacoesBancoDados fabrica = new OperacoesBancoDados();

       public void criarContrato(Contrato contrato) throws ClassNotFoundException, SQLException {

       
          String sql = "INSERT INTO tb_contrato(objetoContrato, orcamentoComprometido, "
                + "ATIVO, contratado) "
                + "VALUES ('"+contrato.getObjeto()
                + "', "+contrato.getOrcamentoComprometido()
                + ", "+contrato.isAtivo()
                + ", '"+contrato.getContratado()
                + "');";


            fabrica.executaQuerieUpdate(sql);

        }

    
       public void editarContrato(Contrato contrato) throws ClassNotFoundException, SQLException {


        String sql1 = "UPDATE tb_contrato "
                + "SET objetoContrato = '" + contrato.getObjeto()
                + "', orcamentoComprometido =  " + contrato.getOrcamentoComprometido()
                + ", contratado = '" + contrato.getContratado()
                + "', ativo = "+contrato.isAtivo()
                + " WHERE codigo = " + contrato.getCodigo()+";";


            fabrica.executaQuerieUpdate(sql1);
    }



      public void removerContrato(int codigo) throws SQLException, ClassNotFoundException {

      
              String sql = "DELETE FROM tb_contrato WHERE codigo = "
                    + codigo + ";";
            
            fabrica.executaQuerieUpdate(sql);
    }

    public List<Contrato> consultaContratos() throws ClassNotFoundException, SQLException {
   
        String sql = "SELECT * FROM tb_contrato;";
        
        
        ResultSet rs = fabrica.executaQuerieResultSet(sql);
        
        return this.extrairListaContratosResultSet(rs);
    
    }


    public Contrato findContrato(int codigo) throws ClassNotFoundException, SQLException {


        String sql = "SELECT * FROM tb_contrato WHERE codigo = "
                +codigo+" ORDER BY orcamentoComprometido DESC;";
        
        ResultSet rs = fabrica.executaQuerieResultSet(sql);
        Contrato contrato = this.extraiContratoResultSet(rs);
        rs.close();
        
        return contrato;

    }


    public Contrato findByObjeto(String objeto) throws ClassNotFoundException, SQLException {

            String sql = "SELECT * FROM tb_contrato WHERE objeto LIKE '"
                    + objeto + "';";

            ResultSet rs = fabrica.executaQuerieResultSet(sql);
            Contrato contrato  = this.extraiContratoResultSet(rs);
            rs.close();
            
            return contrato;

    }
    
    

    public Contrato findByCodigo(Integer codigo) throws ClassNotFoundException, SQLException {
    
    
        String sql = "SELECT * FROM tb_contrato WHERE codigo = "
                + codigo + ";";
        
        ResultSet rs = null;
        try {
            rs = fabrica.executaQuerieResultSet(sql);
        } catch (SQLException ex) {
            Logger.getLogger(ContratoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        Contrato contrato = this.extraiContratoResultSet(rs);
        rs.close();
        
        return contrato;

    }
    
    
    public Contrato findAtivos() throws ClassNotFoundException, SQLException {
    
    
        String sql = "SELECT * FROM tb_contrato WHERE ativo IS TRUE;";
        
        ResultSet rs = null;
        try {
            rs = fabrica.executaQuerieResultSet(sql);
        } catch (SQLException ex) {
            Logger.getLogger(ContratoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        Contrato contrato = this.extraiContratoResultSet(rs);
        rs.close();
        
        return contrato;

    }
    


    public Contrato findInativos() throws ClassNotFoundException, SQLException {
    
    
        String sql = "SELECT * FROM tb_contrato WHERE ativo IS FALSE;";
        
        ResultSet rs = null;
        try {
            rs = fabrica.executaQuerieResultSet(sql);
        } catch (SQLException ex) {
            Logger.getLogger(ContratoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        Contrato contrato = this.extraiContratoResultSet(rs);
        rs.close();
        
        return contrato;

    }
    
    
    public Contrato findByContratado(String contratado) throws ClassNotFoundException, SQLException {
    
    
        String sql = "SELECT * FROM tb_contrato WHERE contratado = '"
                + contratado + "';";
        
        ResultSet rs = null;
        try {
            rs = fabrica.executaQuerieResultSet(sql);
        } catch (SQLException ex) {
            Logger.getLogger(ContratoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        Contrato contrato = this.extraiContratoResultSet(rs);
        rs.close();
        
        return contrato;

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
