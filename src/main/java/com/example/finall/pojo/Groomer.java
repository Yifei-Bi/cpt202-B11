package com.example.finall.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Groomer  implements Serializable {
    private static final long serialVersionUID = 1L;


    private Integer employeeId;

    /**
     *
     */
    private String groomerName;

    /**
     *
     */
    private Integer rank;

    /**
     *
     */
    private String experience;

    /**
     *
     */
    private Integer isFree;

    /**
     *
     */
    private byte[] groomerPicture;
    private String groomerPicture2;
    public Groomer(String groomerName, int rank, String experience, int employeeId, byte[] groomerPicture,Integer isFree) {
        this.groomerName = groomerName;
        this.rank = rank;
        this.experience = experience;
        this.employeeId = employeeId;
        this.groomerPicture = groomerPicture;
        this.isFree=isFree;
    }
    public Groomer(String groomerName, int rank, String experience, int employeeId, String groomerPicture2,Integer isFree) {
        this.groomerName = groomerName;
        this.rank = rank;
        this.experience = experience;
        this.employeeId = employeeId;
        this.groomerPicture2 = groomerPicture2;
        this.isFree=isFree;
    }

}
