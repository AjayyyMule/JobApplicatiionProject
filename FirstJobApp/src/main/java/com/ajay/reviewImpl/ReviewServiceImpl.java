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

import java.net.http.HttpResponse;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.ajay.company.Company;
import com.ajay.company.CompanyService;
import com.ajay.review.Review;
import com.ajay.review.ReviewRepository;
import com.ajay.review.ReviewService;

@Service
public class ReviewServiceImpl implements ReviewService {

    private final ReviewRepository reviewRepository;
    private final CompanyService companyService;

    public ReviewServiceImpl(ReviewRepository reviewRepository, CompanyService companyService) {
        this.reviewRepository = reviewRepository;
        this.companyService = companyService;
    }

    @Override
    public List<Review> getAllReviews(Long companyId) {
        return reviewRepository.findByCompanyId(companyId); 
    }

    @Override
    public boolean addReview(Long companyId, Review review) {
        Company company = companyService.getCompanyById(companyId);
        if (company != null) {
            review.setCompany(company);
            reviewRepository.save(review);
            return true;
        }else {
        
        return false;
        }
    }

    @Override
    public Review getReview(Long companyId, Long reviewId) {
        List<Review> reviews = reviewRepository.findByCompanyId(companyId);
        return reviews.stream()
                .filter(review -> review.getId() == reviewId)  // Use == for comparing primitive types
                .findFirst()
                .orElse(null);
        
        
    }

	@Override
	public boolean updateReview(Long companyId, Long reviewId, Review updatedReview) {
		if (companyService.getCompanyById(reviewId) != null) {
			updatedReview.setCompany(companyService.getCompanyById(reviewId));
			updatedReview.setId(reviewId);
			reviewRepository.save(updatedReview);
			return true;	
			
			
		}else {
			return false;
		}
		
	}

	@Override
	public boolean deleteReview(Long companyId, Long reviewId) {
		// TODO Auto-generated method stub
		if (companyService.getCompanyById(companyId)!= null && reviewRepository.existsById(reviewId)) {
			Review review = reviewRepository.findById(reviewId).orElse(null);
			Company company = review.getCompany();
			company.getReviews().remove(review);
			review.setCompany(null);
			companyService.updateCompany(company, reviewId);
			reviewRepository.deleteById(reviewId);
			return true;
			
			
		}else {
			return false;
			
		}
	}

}

