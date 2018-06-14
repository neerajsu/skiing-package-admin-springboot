package com.snowbird.skiing.skiingpackageadmin.repo;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.snowbird.skiing.skiingpackageadmin.domain.SkiingPackage;

@RepositoryRestResource(exported = false)
public interface SkiingPackageRepository extends PagingAndSortingRepository<SkiingPackage, String> {
	
	SkiingPackage findByName(@Param("name") String name);

}
