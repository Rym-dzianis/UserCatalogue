package com.epam.rymasheuski.userCatalogue.web;

import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.io.IOException;
import java.net.MalformedURLException;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.util.StringUtils;

@RestController
@RequestMapping("/rest/logos")
public class UserLogoController {
	// Save the uploaded file to this folder
	private final static String UPLOADED_FOLDER = "f://temp//";

	@GetMapping
	public List<String> listUploadedLogos() {

		return new ArrayList<>();
	}

	@PostMapping
	public ResponseEntity<String> uploadLogo(@RequestParam("file") MultipartFile uploadfile) {
		if (uploadfile.isEmpty()) {
			return new ResponseEntity<String>("please select a file!", HttpStatus.OK);
		}
		
		String fileName = StringUtils.cleanPath(uploadfile.getOriginalFilename());

		saveUploadedFile(uploadfile, fileName);

		System.out.println("Uploaded!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");

		return new ResponseEntity<String>("Successfully uploaded - " + uploadfile.getOriginalFilename(),
				new HttpHeaders(), HttpStatus.OK);
	}

	@GetMapping("/{filename:.+}")
	public ResponseEntity<Resource> serveLogo(@PathVariable String filename) {

		Resource file = loadAsResource(filename);

		return ResponseEntity.ok()
				.header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + file.getFilename() + "\"")
				.body(file);
	}

	private Resource loadAsResource(String fileName) {
		try {
			
			Path fileStorageLocation = Paths.get(UPLOADED_FOLDER)
	                .toAbsolutePath().normalize();
			
			Path filePath = fileStorageLocation.resolve(fileName).normalize();
			
			Resource resource = new UrlResource(filePath.toUri());
			
			if (resource.exists()) {
				return resource;
				
			} else {
				throw new RuntimeException();
			}
		} catch (MalformedURLException ex) {
			throw new RuntimeException();
		}
	}

	// save file
	private void saveUploadedFile(MultipartFile file, String filename) {
		try {
			byte[] bytes = file.getBytes();
			Path path = Paths.get(UPLOADED_FOLDER + filename);
			Files.write(path, bytes);

		} catch (IOException e) {
		}
	}
}
