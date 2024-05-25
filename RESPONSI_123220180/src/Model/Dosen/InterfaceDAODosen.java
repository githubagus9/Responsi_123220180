/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model.Dosen;

import Model.Dosen.ModelDosen;
import java.util.List;

/**
 *
 * @author Lab Informatika
 */
public class InterfaceDAODosen {
    public void insert(ModelDosen dosen);
    
    public void update(ModelDosen dosen);
    
    public void delete(int id);
    
    public List<ModelDosen> getAll();
}
