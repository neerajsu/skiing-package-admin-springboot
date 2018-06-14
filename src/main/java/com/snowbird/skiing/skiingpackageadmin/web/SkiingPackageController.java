package com.snowbird.skiing.skiingpackageadmin.web;

import java.util.List;
import java.util.stream.Collectors;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.snowbird.skiing.skiingpackageadmin.common.CommonConstants;
import com.snowbird.skiing.skiingpackageadmin.domain.SkiingPackage;
import com.snowbird.skiing.skiingpackageadmin.exception.APIException;
import com.snowbird.skiing.skiingpackageadmin.repo.SkiingPackageRepository;
import com.snowbird.skiing.skiingpackageadmin.service.SkiingPackageService;

@RestController
@RequestMapping(path = "skiingpackage/")
public class SkiingPackageController {
	
	private static final Logger LOG = LogManager.getLogger(SkiingPackageController.class.getName());

	@Autowired
	SkiingPackageRepository skiingPackageRepository;
	
	@Autowired
	SkiingPackageService skiingPackageService;

	protected SkiingPackageController() {

	}

	@RequestMapping(method = RequestMethod.POST)
	@ResponseStatus(HttpStatus.CREATED)
	public void createSkiingPackage(@RequestBody @Validated SkiingPackageDto skiingPackageDto) {
		skiingPackageService.createSkiingPackage(skiingPackageDto.getName(), skiingPackageDto.getSkisType(),
				skiingPackageDto.getLiftLevel(), skiingPackageDto.isHasLesson(), skiingPackageDto.getPrice());
	}
	
	@RequestMapping(method = RequestMethod.GET)
	public Page<SkiingPackageDto> getAllSkkingPackages(Pageable pageable) {
		Page<SkiingPackage> skiingPackagePage = skiingPackageRepository.findAll(pageable);
		List<SkiingPackageDto> skiingPackageDtoList = skiingPackagePage.getContent().stream()
				.map(skiingPackage -> toDto(skiingPackage)).collect(Collectors.toList());
		return new PageImpl<SkiingPackageDto>(skiingPackageDtoList, pageable, skiingPackagePage.getTotalPages());
	}
	
	@RequestMapping(method = RequestMethod.GET, path = "/{name}")
	public SkiingPackageDto getSkiingPackage(@PathVariable String name) {
		SkiingPackage skiingPackage = skiingPackageRepository.findByName(name);
		if(skiingPackage == null) {
			throw new APIException(HttpStatus.NOT_FOUND, "Skiing Package with name: " +name+" does not exist");
		} else {
			return toDto(skiingPackage);
		}
	}
	
	@RequestMapping(method = RequestMethod.DELETE, path = "/{name}")
	@ResponseStatus(HttpStatus.CREATED)
	public void delete(@PathVariable(value = "name") String name) {
		SkiingPackage skiingPackage = skiingPackageRepository.findByName(name);
		if(skiingPackage == null) {
			throw new APIException(HttpStatus.NOT_FOUND, "Skiing Package with name: " +name+" does not exist");
		} else {
			skiingPackageRepository.delete(skiingPackage);
		}
	}

	private SkiingPackageDto toDto(SkiingPackage skiingPackage) {
		return new SkiingPackageDto(skiingPackage.getName(), skiingPackage.getSkisType(), skiingPackage.getLiftLevel(),
				skiingPackage.isHasLesson(), skiingPackage.getPrice());
	}
	
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<ErrorInfo> exceptionHandlerForValidation(MethodArgumentNotValidException exception) {
		
		LOG.error("MethodArgumentNotValidException occured ::" ,exception);
		return new ResponseEntity<ErrorInfo>(new ErrorInfo(CommonConstants.FAIL_STATUS, "Validation of request body failed", HttpStatus.BAD_REQUEST.getReasonPhrase()),
				HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(APIException.class)
	public ResponseEntity<ErrorInfo> exceptionHandlerForApiException(APIException exception) {
		
		LOG.error("APIException occured ::" ,exception);
		return new ResponseEntity<ErrorInfo>(new ErrorInfo(CommonConstants.FAIL_STATUS, exception.getMessage(), exception.getStatus().getReasonPhrase()),
				exception.getStatus());
	}
	
	@ExceptionHandler(Exception.class)
	public ResponseEntity<ErrorInfo> exceptionHandler(Exception exception) {
		
		LOG.error("Exception occured ::" ,exception);
		return new ResponseEntity<ErrorInfo>(new ErrorInfo(CommonConstants.FAIL_STATUS, exception.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase()),
				HttpStatus.INTERNAL_SERVER_ERROR);
	}
}
