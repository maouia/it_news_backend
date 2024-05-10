package tn.projetdemo.demo.entities;



import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.Entity;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Lob;
import jakarta.persistence.Column;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Image {
	
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idImage ;
    private String name ;
    private String type ;
    @Lob
    @Column(columnDefinition = "LONGBLOB")
    private byte[] image;




    public Long getIdImage() {
        return idImage;
    }

    public String getType() {
        return type;
    }

    public String getName() {
        return name;
    }

    public byte[] getImage() {
        return image;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setImage(byte[] image) {
        this.image = image;
    }


	public void setIdImage(Long idImage) {
		this.idImage = idImage;
	}


   
}