/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Model.Connector.*;
import View.Dosen.*;
import View.Dosen.EditDataDosen;
import View.Dosen.InputDataDosen;
import View.Dosen.ViewDataDosen;
import java.util.List;
import javax.swing.JOptionPane;

/**
 *
 * @author Lab Informatika
 */
public class ControllerDosen {
    ViewDataDosen halamanTable;
    InputDataDosen halamanInput;
    EditDataDosen halamanEdit;

    InterfaceDAODosen daoDosen;
    
    List<ModelDosen> daftarDosen;
    
    public ControllerDosen(ViewDataDosen halamanTable) {
        this.halamanTable = halamanTable;
        this.daoDosen = new DAODosen();
    }
    
    public ControllerDosen(InputDataDosen halamanInput) {
        this.halamanInput = halamanInput;
        this.daoDosen = new DAODosen();
    }
    
    public ControllerDosen(EditDataDosen halamanEdit) {
        this.halamanEdit = halamanEdit;
        this.daoDosen = new DAODosen();
    }

    public void showAllDosen() {
        daftarDosen = daoDosen.getAll();
        ModelTable table = new ModelTable(daftarDosen);
        halamanTable.getTableDosen().setModel(table);
    }

    public void insertDosen() {
        try {
            ModelDosen dosenBaru = new ModelDosen();
            String nama = halamanInput.getInputNama();
            String nidn = halamanInput.getInputNIDN();

            if ("".equals(id) || "".equals(nama)) {
                throw new Exception("Id atau Nama tidak boleh kosong!");
            }
            
            dosenBaru.setId(id);
            dosenBaru.setNama(nama);
            dosenBaru.setNoHP(no_hp);
            dosenBaru.setEmail(emaiil);
            
            daoDosen.insert(dosenBaru);
            
           JOptionPane.showMessageDialog(null, "Dosen baru berhasil ditambahkan.");
            
            halamanInput.dispose();
            new ViewDataDosen();
        } catch (Exception e) {
           JOptionPane.showMessageDialog(null, "Error: " + e.getMessage());
        }
    }
    
    public void editDosen(int id) {
        try {
            ModelDosen dosenYangMauDiedit = new ModelDosen();
                        
            String id = halamanEdit.getInputId();
            String nama = halamanEdit.getInputNama();
            String no_hp = halamanEdit.getInputNoHP();
            String email = halamanEdit.getInputEmail();

            if ("".equals(id) || "".equals(nama)) {
                throw new Exception("Nama atau NIDN tidak boleh kosong!");
            }
            
            dosenYangMauDiedit.setId(id);
            dosenYangMauDiedit.setNama(nama);
            dosenYangMauDiedit.setNoHP(no_hp);
            dosenYangMauDiedit.setEmail(email);
            
            daoDosen.update(dosenYangMauDiedit);

            JOptionPane.showMessageDialog(null, "Data dosen berhasil diubah.");

            halamanEdit.dispose();
            new ViewDataDosen();
        } catch (Exception e) {
           JOptionPane.showMessageDialog(null, "Error: " + e.getMessage());
        }
    }

    public void deleteDosen(Integer baris) {
        Integer id = (int) halamanTable.getTableDosen().getValueAt(baris, 0);
        String nama = halamanTable.getTableDosen().getValueAt(baris, 1).toString();

        int input = JOptionPane.showConfirmDialog(
                null,
                "Hapus " + nama + "?",
                "Hapus Dosen",
                JOptionPane.YES_NO_OPTION
        );

        if (input == 0) {
            daoDosen.delete(id);
            
            JOptionPane.showMessageDialog(null, "Berhasil menghapus data.");

            showAllDosen();
        }
    }
}

