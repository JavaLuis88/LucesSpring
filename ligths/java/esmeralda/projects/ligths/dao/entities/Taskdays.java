/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package esmeralda.projects.ligths.dao.entities;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;


/**
 *
 * @author LingMoy
 */
@Entity
@Table(name = "taskdays")
public class Taskdays implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    
    @Basic(optional = false)
    @Column(name = "datetask")
    @Temporal(TemporalType.TIME)
    private Date datetask;
    @Basic(optional = false)
    @Column(name = "weekday")
    private int weekday;
    @Basic(optional = false)
    @Column(name = "idtask")
    private Integer idtask;

    public Taskdays() {
    }

    public Taskdays(Integer id) {
        this.id = id;
    }

    public Taskdays(Integer id, Date datetask,int weekday) {
        this.id = id;
        this.datetask=datetask;
        this.weekday = weekday;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
    
    public Date getDatetask() {
        return datetask;
    }

    public void setDatetask(Date datetask) {
        this.datetask = datetask;
    }

    public int getWeekday() {
        return this.weekday;
    }

    public void setWeekday(int weekday) {
        this.weekday = weekday;
    }

    public Integer getIdtask() {
        return this.idtask;
    }

    public void setIdtask(Integer idtask) {
        this.idtask = idtask;
    }



    @Override
    public String toString() {
        return "entities.Taskdays[ id=" + id + " ]";
    }
    
}
