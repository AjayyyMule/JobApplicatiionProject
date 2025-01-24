//package com.ajay.reviewImpl;
//
//import java.util.List;
//
//import org.springframework.stereotype.Service;
//
//import com.ajay.company.Company;
//import com.ajay.company.CompanyService;
//import com.ajay.review.Review;
//import com.ajay.review.ReviewRepository;
//import com.ajay.review.ReviewService;
//
//
//@Service
//public class ReviewServiceImpl implements ReviewService {
//
//	
//	private ReviewRepository reviewRepository;
//	private CompanyService companyService;
//	
//	
//	
//	public ReviewServiceImpl(ReviewRepository reviewRepository, CompanyService companyService) {
//		super();
//		this.reviewRepository = reviewRepository;
//		this.companyService = companyService;
//	}
//
//
//
//	@Override
//	public List<Review> getAllReviews(Long companyId) {
//		
//		List<Review> reviews= reviewRepository.findByCompanyId(companyId); 
//			
//		
//		// TODO Auto-generated method stub
//		return reviews;
//	}
//
//
//
//	@Override
//	public void addReview(Long companyId, Review review) {
//	    Company company = companyService.getCompanyById(companyId);
//	    if (company != null) {
//	        review.setCompany(company);
//	        reviewRepository.save(review);
//	    }
//	}
//
//	
//	
//
//}











package com.ajay.reviewImpl;


import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;


import com.ajay.review.Review;
import com.ajay.review.ReviewRepository;
import com.ajay.review.ReviewService;

@Service
public class ReviewServiceImpl implements ReviewService {

    private final ReviewRepository reviewRepository;


    public ReviewServiceImpl(ReviewRepository reviewRepository) {
        this.reviewRepository = reviewRepository;

    }

    @Override
    public List<Review> getAllReviews(Long companyId) {
        return reviewRepository.findByCompanyId(companyId); 
    }

    @Override
    public boolean addReview(Long companyId, Review review) {
  
        if (companyId != null && review != null) {
            review.setCompanyId(companyId);
            reviewRepository.save(review);
            return true;
        }else {
        
        return false;
        }
    }

    @Override
    public Review getReview(Long reviewId) {
    	
        return reviewRepository.findById(reviewId).orElse(null);
               
        
    }

	@Override
	public boolean updateReview( Long reviewId, Review updatedReview) {
		Review review = reviewRepository.findById(reviewId).orElse(null);
		if (review!= null) {
			review.setTitle(updatedReview.getTitle());
			review.setDescription(updatedReview.getDescription());
			review.setRating(updatedReview.getRating());
			reviewRepository.save(review);
			return true;	
			
			
		}else {
			return false;
		}
		
	}

	@Override
	public boolean deleteReview( Long reviewId) {
		Review review = reviewRepository.findById(reviewId).orElse(null);
		// TODO Auto-generated method stub
		if (review != null) {			
			
			reviewRepository.delete(review);
			return true;
			
			
		}else {
			return false;
			
		}
	}

}

