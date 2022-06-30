package com.roshka.bootcamp.bd;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.io.Serializable;

public class ListaConsulta4 implements Serializable {


    public static List<Consulta4> listar(){
        ArrayList<Consulta4>list = new ArrayList<>();
        String sql = "  select prov.nombre, CAST(sum(f.cantidad) AS DECIMAL(12, 2)) as cantidad\n" +
                "  from proveedor as prov\n" +
                "  join producto as prod\n" +
                "  on prod.proveedor_id = prov.id\n" +
                "  join factura_detalle as f\n" +
                "  on f.producto_id = prod.id\n" +
                "  group by prov.id \n" +
                "  order by cantidad desc; ";
        try{
            Connection con= DB.getConnection();
            PreparedStatement ps=con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                Consulta4 consul = new Consulta4();
                consul.setId(rs.getString("nombre"));
                consul.setCantidad(rs.getDouble("cantidad"));
                System.out.println(consul.getCantidad() + " " + consul.getNombre());
                list.add(consul);
            }

            con.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return list;
    }
}
