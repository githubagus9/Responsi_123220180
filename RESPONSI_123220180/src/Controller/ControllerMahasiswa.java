/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Model.Mahasiswa.*;
import View.Mahasiswa.*;
import java.util.List;
import javax.swing.JOptionPane;

/**
 *
 * @author Lab Informatika
 */
public class ControllerMahasiswa {
    ViewDataMahasiswa halamanTable;
    InputDataMahasiswa halamanInput;
    EditDataMahasiswa halamanEdit;

    InterfaceDAOMahasiswa daoMahasiswa;

   List<ModelMahasiswa> daftarMahasiswa;
    public ControllerMahasiswa(ViewDataMahasiswa halamanTable) {
        this.halamanTable = halamanTable;
        this.daoMahasiswa = new DAOMahasiswa();
    }
    
    public ControllerMahasiswa(InputDataMahasiswa halamanInput) {
        this.halamanInput = halamanInput;
        this.daoMahasiswa = new DAOMahasiswa();
    }
    
    public ControllerMahasiswa(EditDataMahasiswa halamanEdit) {
        this.halamanEdit = halamanEdit;
        this.daoMahasiswa = new DAOMahasiswa();
    }

    public void showAllMahasiswa() {
        daftarMahasiswa = daoMahasiswa.getAll();

        ModelTable table = new ModelTable(daftarMahasiswa);

        halamanTable.getTableMahasiswa().setModel(table);
    }

    public void insertMahasiswa() {
        try {
            ModelMahasiswa mahasiswaBaru = new ModelMahasiswa();
            
            String nama = halamanInput.getInputNama();
            String nim = halamanInput.getInputNIM();

            if ("".equals(nama) || "".equals(nim)) {
                throw new Exception("Nama atau NIM tidak boleh kosong!");
            }
            
            mahasiswaBaru.setNama(nama);
            mahasiswaBaru.setNim(nim);
            
            daoMahasiswa.insert(mahasiswaBaru);
            
            JOptionPane.showMessageDialog(null, "Mahasiswa baru berhasil ditambahkan.");
            
            halamanInput.dispose();
            new ViewDataMahasiswa();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error: " + e.getMessage());
        }
    }
    
    public void editMahasiswa(int id) {
        try {
            ModelMahasiswa mahasiswaYangMauDiedit = new ModelMahasiswa();
                        
           String nama = halamanEdit.getInputNama();
            String nim = halamanEdit.getInputNIM();

            if ("".equals(nama) || "".equals(nim)) {
                throw new Exception("Nama atau NIM tidak boleh kosong!");
            }
            
            mahasiswaYangMauDiedit.setId(id);
            mahasiswaYangMauDiedit.setNama(nama);
            mahasiswaYangMauDiedit.setNim(nim);
            
            daoMahasiswa.update(mahasiswaYangMauDiedit);

            JOptionPane.showMessageDialog(null, "Data mahasiswa berhasil diubah.");

            halamanEdit.dispose();
            new ViewDataMahasiswa();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error: " + e.getMessage());
        }
    }

    public void deleteMahasiswa(Integer baris) {
        Integer id = (int) halamanTable.getTableMahasiswa().getValueAt(baris, 0);
        String nama = halamanTable.getTableMahasiswa().getValueAt(baris, 1).toString();

        int input = JOptionPane.showConfirmDialog(
                null,
                "Hapus " + nama + "?",
                "Hapus Mahasiswa",
                JOptionPane.YES_NO_OPTION
        );

        if (input == 0) {
            daoMahasiswa.delete(id);
            
            JOptionPane.showMessageDialog(null, "Berhasil menghapus data.");

            showAllMahasiswa();
        }
    }
}
