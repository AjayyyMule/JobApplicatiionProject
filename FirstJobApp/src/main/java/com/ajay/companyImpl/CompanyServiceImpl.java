package com.ajay.companyImpl;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.ajay.company.Company;
import com.ajay.company.CompanyRepository;
import com.ajay.company.CompanyService;



@Service
public class CompanyServiceImpl implements CompanyService{

	
	private CompanyRepository companyRepository;
	
	
	
	public CompanyServiceImpl(CompanyRepository companyRepository) {
		super();
		this.companyRepository = companyRepository;
	}



	@Override
	public List<Company> getAllCompanies() {
		// TODO Auto-generated method stub
		return companyRepository.findAll();
	}






	@Override
	public boolean updateCompany(Company company, Long id) {
		Optional<Company> companyOptional = companyRepository.findById(id);
        if (companyOptional.isPresent()) {
            Company companyToUpdate = companyOptional.get();
            companyToUpdate.setDescription(company.getDescription());
            companyToUpdate.setName(company.getName());
            companyToUpdate.setJobs(company.getJobs());
            companyRepository.save(companyToUpdate);
            return true;
        }
        return false;
	}



	@Override
	public void createCompany(Company company) {
		
		companyRepository.save(company);
		// TODO Auto-generated method stub
		
	}



	@Override
	public boolean deleteCompanyById(Long id) {
		if(companyRepository.existsById(id)) {
			companyRepository.deleteById(id);
			return true;
		}else {
			
			return false;
		}
		
			
		
	}



	@Override
	public Company getCompanyById(Long id) {
		// TODO Auto-generated method stub
		return companyRepository.findById(id).orElse(null);
	}
	
	

}
