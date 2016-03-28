package Funciones;

import conexion.conexConsultas;
import java.io.InputStream;
import java.util.HashMap;
import net.sf.jasperreports.engine.JREmptyDataSource;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.view.JRViewer;

public class generarReporte {

    private JRViewer P;
    conexConsultas con =  new conexConsultas();
    public generarReporte() {
    }

    public void generar(String fecha) {
        InputStream fileName = ClassLoader.getSystemResourceAsStream("docs/report1.jasper");
        HashMap param = new HashMap();
        param.put("fecha", fecha);
        try {
            con.conectar();
            JasperPrint print = JasperFillManager.fillReport(fileName, param,con.getConnection());
            
            print.setName("Reporte " + fecha);
            MostrarReporte ok = new MostrarReporte(null, true, print, "Reporte " + fecha);
            ok.setVisible(true);
            con.close();
        } catch (Exception e) {
            System.exit(1);
        }
    } 
}

