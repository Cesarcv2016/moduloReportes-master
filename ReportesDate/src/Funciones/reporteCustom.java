package Funciones;

import java.awt.event.ActionEvent;
import java.io.File;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileFilter;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.view.JRSaveContributor;
import net.sf.jasperreports.view.JRViewer;
import net.sf.jasperreports.view.save.JRPrintSaveContributor;

public class reporteCustom extends JRViewer {

    JasperPrint printtot = new JasperPrint();
    String nombreArchivo = "";

    public reporteCustom(JasperPrint print) {
        super(print);
        printtot = print;

        super.btnSave.removeActionListener(btnSave.getActionListeners()[0]);

        btnSave.addActionListener(new java.awt.event.ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                btnSaveActionPerformed(e);
            }
        });
    }

    public void setNombre(String name) {
        this.nombreArchivo = name;
    }

    public void btnSaveActionPerformed(java.awt.event.ActionEvent evt) {
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setSelectedFile(new File(nombreArchivo));

        for (int i = 0; i < saveContributors.size(); i++) {
            fileChooser.addChoosableFileFilter((JRSaveContributor) saveContributors.get(i));
        }

        if (saveContributors.size() > 0) {
            fileChooser.setFileFilter((JRSaveContributor) saveContributors.get(0));
        }

        int retValue = fileChooser.showSaveDialog(this);
        if (retValue == JFileChooser.APPROVE_OPTION) {
            FileFilter fileFilter = fileChooser.getFileFilter();
            File file = fileChooser.getSelectedFile();
            JRSaveContributor contributor = null;
            if (fileFilter instanceof JRSaveContributor) {
                contributor = (JRSaveContributor) fileFilter;
            } else {
                int i = 0;
                while (contributor == null && i < saveContributors.size()) {
                    contributor = (JRSaveContributor) saveContributors.get(i++);
                    if (!contributor.accept(file)) {
                        contributor = null;
                    }
                }
                if (contributor == null) {
                    contributor = new JRPrintSaveContributor(super.getLocale(), null);
                }
            }

            try {
                contributor.save(printtot, file);
            } catch (JRException e) {
                JOptionPane.showMessageDialog(this, getBundleString("error.saving"));
            }
        }
    }

}
