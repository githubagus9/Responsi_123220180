/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model.Mahasiswa;

/**
 *
 * @author Lab Informatika
 */
public class ModelMahasiswa {
    private Integer id;
    private String nama, no_hp, email;
    
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getNoHP(String no_hp) {
        return no_hp;
    }

    public void setNoHP(String no_hp) {
        this.no_hp = no_hp;
    }
    
    public String getEmail(String email) {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
