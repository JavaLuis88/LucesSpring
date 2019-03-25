/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package esmeralda.projects.ligths.dao.entities;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;


/**
 *
 * @author LingMoy
 */
@Entity
@Table(name = "roles")
public class Roles {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @Column(name = "role")
    private String role;
    @Basic(optional = false)
    @Column(name = "iduser")
    private Integer iduser;

    public Roles() {
    }

    public Roles(Integer id) {
        this.id = id;
    }

    public Roles(Integer id, String role,Integer iduser) {
        this.id = id;
        this.role = role;
        this.iduser=iduser;
    }

    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getRole() {
        return this.role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public Integer getIduser() {
        return this.iduser;
    }

    public void setIduser(Integer iduser) {
        this.iduser = iduser;
    }


    @Override
    public String toString() {
        return "entities.Roles[ id=" + id + " ]";
    }
    
}
