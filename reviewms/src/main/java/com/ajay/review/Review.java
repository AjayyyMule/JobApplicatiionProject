//package com.ajay.review;
//
//import java.util.List;
//
//import com.ajay.company.Company;
//import com.fasterxml.jackson.annotation.JsonIgnore;
//
//import jakarta.persistence.Entity;
//import jakarta.persistence.GeneratedValue;
//import jakarta.persistence.GenerationType;
//import jakarta.persistence.Id;
//import jakarta.persistence.ManyToOne;
//import jakarta.persistence.OneToMany;
//
//@Entity
//public class Review {
//	
//	
//	
//	@Id
//	@GeneratedValue(strategy = GenerationType.IDENTITY)
//	private long id;
//	private String title;
//	private String description;
//	private double rating;
//	
//	
//	@JsonIgnore
//	@ManyToOne
//	private Company company;
//	
//	
//	@OneToMany(mappedBy = "company")
//	private List<Review> reviews;
//	
//	
//	
//	public Review() {
//		super();
//	}
//	
//	
//	
//	
//	
//	
//	
//	
//	public Company getCompany() {
//		return company;
//	}
//
//
//
//
//
//
//
//
//	public void setCompany(Company company) {
//		this.company = company;
//	}
//
//
//
//
//
//
//
//
//	public long getId() {
//		return id;
//		
//	}
//	public void setId(long id) {
//		this.id = id;
//	}
//	public String getTitle() {
//		return title;
//	}
//	public void setTitle(String title) {
//		this.title = title;
//	}
//	public String getDescription() {
//		return description;
//	}
//	public void setDescription(String description) {
//		this.description = description;
//	}
//	public double getRating() {
//		return rating;
//	}
//	public void setRating(double rating) {
//		this.rating = rating;
//	}
//	
//	
//	
//	
//	
//	
//	
//	
//
//}










package com.ajay.review;


import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;

@Entity
public class Review {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String title;
    private String description;
    private double rating;
    private long companyId;

    
    public Review() {}

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getRating() {
        return rating;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }

	public long getCompanyId() {
		return companyId;
	}

	public void setCompanyId(long companyId) {
		this.companyId = companyId;
	}

    
}
