package com.snowbird.skiing.skiingpackageadmin.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.snowbird.skiing.skiingpackageadmin.domain.SkiingPackage;
import com.snowbird.skiing.skiingpackageadmin.repo.SkiingPackageRepository;

@Service
public class SkiingPackageService {
	
	private SkiingPackageRepository skiingPackageRepository; 
	
	@Autowired
	public SkiingPackageService(SkiingPackageRepository skiingPackageRepository) {
		this.skiingPackageRepository = skiingPackageRepository;
	}
	
	public SkiingPackage createSkiingPackage(String name, String skisType, String liftLevel, boolean hasLesson, int price) {
		if(name != null) {
			SkiingPackage skiingPackage = skiingPackageRepository.findByName(name);
			if(skiingPackage != null) {
				skiingPackage.setName(name);
				skiingPackage.setSkisType(skisType);
				skiingPackage.setLiftLevel(liftLevel);
				skiingPackage.setHasLesson(hasLesson);
				skiingPackage.setPrice(price);
				return skiingPackageRepository.save(skiingPackage);
			}
            return skiingPackageRepository.save(new SkiingPackage(name, skisType, liftLevel, hasLesson, price));
		} else {
            return null;
        }
	}
}
